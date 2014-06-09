package main;

import jog.window;
import jog.graphics;
import jog.input;
import jog.window.WindowMode;

public class Main implements jog.input.InputEventHandler {
	
	/** The game title */
	private static final String TITLE = "Fly Hard With A Vengence";

	
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
		// Play the splash screen first
		splash();
		
		// Then set up the game window
		setUpWindow();
		
		// Do the update and render loop
		while (!jog.window.isClosed()) {
			update();
			render();
		}
		
		// Finish by handling game exit
		exit();
	}
	
	
	/**
	 * Runs the pre-game splash screen.
	 */
	private void splash() {}
	
	/**
	 * Sets up the game window.
	 */
	private void setUpWindow() {
		// The values here are arbitrary - they will never be seen
		// EXCEPT the FPS, which is set to 60 (the standard for such games)
		window.initialise(TITLE, 500, 500, 60);
		
		// Force the game to be full-screen, without any border
		window.setMode(WindowMode.BORDERLESS_FULLSCREEN);
		
		// The window is full-screen, so it shouldn't be resizable
		window.setResizable(false);
		
		// Initialise the window's graphics - necessary to start rendering
		// the game's graphics
		graphics.initialise();
	}
	
	/**
	 * Updates the current scene.
	 */
	private void update() {
		// Update any inputs
		input.update(this);
		
		// Then update the window
		// This is done second so that the results of processing
		// the detected inputs are displayed immediately
		window.update();
	}
	
	/**
	 * Renders graphical elements.
	 */
	private void render() {
		// Start by clearing the graphics
		graphics.clear();
	}
	
	/**
	 * Routes mouse presses to the current scene.
	 */
	@Override
	public void mousePressed(int key, int x, int y) {}

	/**
	 * Routes mouse releases to the current scene.
	 */
	@Override
	public void mouseReleased(int key, int x, int y) {}

	/**
	 * Routes key presses to the current scene.
	 */
	@Override
	public void keyPressed(int key) {}

	/**
	 * Routes key releases to the current scene.
	 */
	@Override
	public void keyReleased(int key) {}
	
	/**
	 * Handles game exit.
	 */
	private void exit() {
		// Dispose of the graphics slate
		graphics.dispose();
		
		// Dispose of input handelers
		input.dispose();
		
		// Close the window
		window.dispose();
		
		// Exit the game
		System.exit(0);
	}

}
