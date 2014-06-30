package main;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Rectangle;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;

import utilities.Graphics;

/**
 * Creates an instance of the game, and handles it's execution.
 */
public class Main {
	
	/** The possible game statuses.
	 * <p>
	 * <table>
	 * <thead>Valid statuses:</thead>
	 * <tr><td><b>Splashing:</b></td><td>the splash screen is in effect</td></tr>
	 * <tr><td><b>Waiting:</b></td><td>the game is waiting</td></tr>
	 * <tr><td><b>Running:</b></td><td>the game is running</td></tr>
	 * <tr><td><b>Paused:</b></td><td>the game has been temporarily paused</td></tr>
	 * <tr><td><b>Closing:</b></td><td>the current scene is closing</td></tr>
	 * <tr><td><b>Exiting:</b></td><td>the game is exiting</td></tr>
	 * </table>
	 * </p>
	 */
	private enum Status {SPLASHING, WAITING, RUNNING, PAUSED, CLOSING, EXITING}
	
	/** The full-screen toggle.
	 * <p>
	 * If this is true, the game <b>can</b> run in full-screen mode
	 * (provided that the computer is capable of displaying the application
	 * full-screen).
	 * <br>
	 * If this is false, the game <b>will not</b> run in full-screen mode.
	 * </p>
	 */
	private static final boolean fullscreenToggle = true;
	
	/** The game's current status */
	private static Status gameStatus;
	
	/** The monitor used to block the control thread until the splash ends */
	private Object splashMonitor = new Object();
	
	/** The time at which the game last updated */
	private long lastUpdated;

	
	/**
	 * Starts the game.
	 * @param args - variadic command line parameters
	 */
	public static void main(String... args) {
		new Main();
	}
	
	
	/**
	 * Creates a new game instance.
	 */
	private Main() {
		// Start by playing the splash screen
		gameStatus = Status.SPLASHING;
		splash();
		
		// Load game scenes and resources
		// This occurs concurrently to the splash screen playing
		SceneManager.load();
		ResourceManager.load();
		
		// Wait for the splash screen to finish
		synchronized (splashMonitor) {
			while (gameStatus == Status.SPLASHING) {
				try {
					splashMonitor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Set up the game window
		gameStatus = Status.WAITING;
		setUpWindow();
		ResourceManager.loadOpenGLDependant();
		gameStatus = Status.RUNNING;
		
		// Enter the first scene
		SceneManager.currentScene().enter();
		
		// Initialise the last update time
		lastUpdated = time();
		
		// Do the update and render loop
		while (gameStatus == Status.RUNNING && !Display.isCloseRequested()) {
			update(delta());
			render();
			io();
		}
		
		// Finish by handling game exit
		gameStatus = Status.EXITING;
		exit();
	}
	
	
	/**
	 * Runs the pre-game splash screen.
	 * <p>
	 * This is an asynchronous call, i.e. a new thread will be created to run
	 * the splash screen, and control will pass back to the
	 * caller without blocking.
	 * </p>
	 */
	private void splash() {
		Thread splashThread = new Thread(new Runnable() {
			@Override
			public void run() {
				//TODO: Do splash
				
				// End by signalling that the main control thread can proceed
				synchronized (splashMonitor) {
					gameStatus = Status.WAITING;
					splashMonitor.notifyAll();
				}
			}
		});
		
		// Start the splash thread
		splashThread.start();
	}
	
	/**
	 * Sets up the game window.
	 * @param fullscreen - <tt>true</tt> if the game should be presented
	 * 			full-screen
	 */
	private void setUpWindow() {
		// Check if the screen supports full-screen
		boolean fullscreenEnabled =
				Display
				.getDisplayMode()
				.isFullscreenCapable();
		
		try {
			// Set the display mode
			if (fullscreenEnabled && fullscreenToggle) {
				Display.setFullscreen(true);

				// Enable vsync
				Display.setVSyncEnabled(true);
			} else {
				GraphicsConfiguration conf =
						GraphicsEnvironment
						.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice()
						.getDefaultConfiguration();

				// Get window offsets
				Insets insets =
						Toolkit
						.getDefaultToolkit()
						.getScreenInsets(conf);

				// Get screen dimensions
				Rectangle bounds = conf.getBounds();

				// Get the available screen space: dimensions - insets
				bounds.x += insets.left;
				bounds.y += insets.top;
				bounds.width -= (insets.left + insets.right);
				bounds.height -= (insets.top + insets.bottom);

				Display.setDisplayMode(
						new DisplayMode(bounds.width - 40, bounds.height - 50));

				Display.setLocation(bounds.x + 20, bounds.y + 10);
			}
			
			// Create the display
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		// Set up the display
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		// Set up the graphics class
		Graphics.windowWidth = Display.getWidth();
		Graphics.windowHeight = Display.getHeight();
	}
	
	/**
	 * Updates the current scene.
	 * @param delta - the time (in milliseconds) since the last update
	 */
	private void update(int delta) {
		// Update the current scene
		SceneManager.currentScene().update(delta);
	}
	
	/**
	 * Renders graphical elements.
	 */
	private void render() {
		// Set FPS to 60
		// This must be run between every graphical update
		Display.sync(60);
					
		// Update the display
		// Note: this also updates any inputs (keyboard, mouse etc.)
		Display.update();
		
		// Clear the screen and depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		// Render the current scene
		SceneManager.currentScene().render();
	}
	
	/**
	 * Handles input and output events.
	 */
	private void io() {
		// Handle mouse inputs
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				// Handle mouse presses
				SceneManager.currentScene().mousePress(Mouse.getEventButton(),
						Mouse.getEventX(), Mouse.getEventY());
			} else {
				// Handle mouse releases
				SceneManager.currentScene().mouseRelease(Mouse.getEventButton(),
						Mouse.getEventX(), Mouse.getEventY());
			}
		}

		// Handle mouse scroll inputs
		SceneManager.currentScene().scroll(Mouse.getDWheel(),
					Mouse.getEventX(), Mouse.getEventY());
		
		// Handle keyboard inputs
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				// Handle key presses
				SceneManager.currentScene().keyPress(Keyboard.getEventKey());
			} else {
				// Handle key releases
				SceneManager.currentScene().keyRelease(Keyboard.getEventKey());
			}
		}

		
	}
	
	/**
	 * Handles game exit.
	 */
	private void exit() {
		// Close the window
		Display.destroy();
		
		// Close the inputs
		Keyboard.destroy();
		Mouse.destroy();
		
		// Exit the game
		System.exit(0);
	}
	
	
	/**
	 * Gets the current time in milliseconds.
	 * @return the current time
	 */
	private long time() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Gets the time (in milliseconds) since the last update.
	 * @return the time since the last update
	 */
	private int delta() {
		long time = time();
		int delta = (int) (time - lastUpdated);
		lastUpdated = time;
		return delta;
	}
	
	/**
	 * Gets the game's status.
	 * <p>
	 * <table>
	 * <thead>Valid statuses:</thead>
	 * <tr><td><b>Splashing:</b></td><td>the splash screen is in effect</td></tr>
	 * <tr><td><b>Waiting:</b></td><td>the game is waiting</td></tr>
	 * <tr><td><b>Running:</b></td><td>the game is running</td></tr>
	 * <tr><td><b>Paused:</b></td><td>the game has been temporarily paused</td></tr>
	 * <tr><td><b>Closing:</b></td><td>the current scene is closing</td></tr>
	 * <tr><td><b>Exiting:</b></td><td>the game is exiting</td></tr>
	 * </table>
	 * </p>
	 * @return the game's status
	 */
	protected static Status gameStatus() {
		return gameStatus;
	}
	
	/**
	 * Causes the game's status to change to closing.
	 */
	protected static void requestClose() {
		gameStatus = Status.CLOSING;
	}

}
