package main;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Creates an instance of the game, and handles it's execution.
 */
public class Main {
	
	/** The possible game statuses.
	 * <p>
	 * <table>
	 * <thead>Valid statuses:</thead>
	 * <tr><td><b>Splashing:</b></td><td>the splash screen is in effect</td></tr>
	 * <tr><td><b>Running:</b></td><td>the game is running</td></tr>
	 * <tr><td><b>Waiting:</b></td><td>the game is waiting</td></tr>
	 * <tr><td><b>Paused:</b></td><td>the game has been temporarily paused</td></tr>
	 * <tr><td><b>Exiting:</b></td><td>the game is exiting</td></tr>
	 * </table>
	 * </p>
	 */
	public enum Status {SPLASHING, RUNNING, WAITING, PAUSED, EXITING}
	
	/** The current game instance */
	private static Main instance;
	
	/** The full-screen toggle.
	 * <p>
	 * If this is true, the game <b>can</b> run in full-screen mode
	 * (provided that other conditions are met).
	 * <br>
	 * If this is false, the game <b>will not</b> run in full-screen mode.
	 * </p>
	 */
	private static final boolean fullscreenToggle = true;
	
	/** The game's current status */
	private Status gameStatus;
	
	/** The monitor used to block the control thread until the splash ends */
	private Object splashMonitor = new Object();

	
	/**
	 * Starts the game.
	 * @param args - variadic command line parameters
	 */
	public static void main(String... args) {
		instance = new Main();
	}
	
	
	/**
	 * Creates a new game instance.
	 */
	private Main() {
		// Start by playing the splash screen
		gameStatus = Status.SPLASHING;
		splash();
		
		// Load game scenes and resources
		// This occurs concurrently to the splash screen
		//TODO: Load scenes and resources
		
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
		gameStatus = Status.RUNNING;
		
		// Do the update and render loop
		while (gameStatus == Status.RUNNING && !Display.isCloseRequested()) {
			update();
			render();
		}
		
		// Finish by handling game exit
		gameStatus = Status.EXITING;
		exit();
	}
	
	
	/**
	 * Runs the pre-game splash screen.
	 * <p>
	 * This is an asynchronous call, i.e. a new thread will be created
	 * to run the splash screen, and control will pass back to the
	 * caller without blocking.
	 * </p>
	 */
	private void splash() {
		Thread splashThread = new Thread(new Runnable() {
			@Override
			public void run() {
				//TODO: Do splash
				
				// End by signalling that the main control thread
				// can proceed
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
			} else {
				// Work out the fall-back screen size (for cases where
				// full-screen fails)
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
				// The x and y alterations are not necessary, but are provided
				// for completeness
				bounds.x += insets.left;
				bounds.y += insets.top;
				bounds.width -= (insets.left + insets.right);
				bounds.height -= (insets.top + insets.bottom);
				
				Display.setDisplayMode(
						new DisplayMode(bounds.width - 40, bounds.height - 50)
				);
				
				Display.setLocation(bounds.x + 20, bounds.y + 10);
			}
			
			// Create the display
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the current scene.
	 */
	private void update() {
		//TODO
	}
	
	/**
	 * Renders graphical elements.
	 */
	private void render() {
		// Update the display
		Display.update();
	}
	
	/**
	 * Handles game exit.
	 */
	public void exit() {
		// Close the window
		Display.destroy();
		
		// Exit the game
		System.exit(0);
	}
	
	
	/**
	 * Gets the current game instance.
	 * @return the current game instance
	 */
	public static Main instance() {
		return instance;
	}
	
	/**
	 * Gets the game's status.
	 * <p>
	 * <table>
	 * <thead>Valid statuses:</thead>
	 * <tr><td><b>Splashing:</b></td><td>the splash screen is in effect</td></tr>
	 * <tr><td><b>Running:</b></td><td>the game is running</td></tr>
	 * <tr><td><b>Waiting:</b></td><td>the game is waiting</td></tr>
	 * <tr><td><b>Paused:</b></td><td>the game has been temporarily paused</td></tr>
	 * <tr><td><b>Exiting:</b></td><td>the game is exiting</td></tr>
	 * </table>
	 * </p>
	 * @return the game's status
	 */
	public static Status gameStatus() {
		return instance().gameStatus;
	}

}
