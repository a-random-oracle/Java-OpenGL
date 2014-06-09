package main;

import scenes.Scene;
import scenes.TitleScene;

import jog.window;
import jog.window.WindowMode;
import jog.graphics;
import jog.input;
import jog.input.InputEventHandler;

/**
 * Creates an instance of the game, and handles it's execution.
 */
public class Main implements InputEventHandler {
	
	/** The game title */
	private static final String TITLE = "Fly Hard";
	
	/** The current scene */
	private Scene currentScene;

	
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
		// Note: currentScene must be set by this point to enable us to detect
		// when the game has been closed
		while (currentScene != null) {
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
		// Creates a full-screen window with resizing disabled
		window.initialise(TITLE, 0, 0, 60,
				WindowMode.BORDERLESS_FULLSCREEN, false);
		
		// Initialise the window's graphics - necessary to start rendering
		// the game's graphics
		graphics.initialise();
		
		// Set up the first scene
		currentScene = new TitleScene();
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
		
		// Update the current scene (provided that a scene exists)
		if (currentScene != null) currentScene.update();
	}
	
	/**
	 * Renders graphical elements.
	 */
	private void render() {
		// Start by clearing the graphics
		graphics.clear();
		
		// Render the current scene
		if (currentScene != null) currentScene.render();
	}
	
	/**
	 * Routes mouse presses to the current scene.
	 */
	@Override
	public void mousePressed(int key, int x, int y) {
		if (currentScene != null) currentScene.mousePressed(key, x, y);
	}

	/**
	 * Routes mouse releases to the current scene.
	 */
	@Override
	public void mouseReleased(int key, int x, int y) {
		if (currentScene != null) currentScene.mouseReleased(key, x, y);
	}

	/**
	 * Routes key presses to the current scene.
	 */
	@Override
	public void keyPressed(int key) {
		switch (key) {
		case (input.KEY_ESCAPE):
			// If the escape key is pressed, close the current scene
			// Set the new scene to the scene returned by the close() call
			currentScene = currentScene.close();
			break;
		}
		
		if (currentScene != null) currentScene.keyPressed(key);
	}

	/**
	 * Routes key releases to the current scene.
	 */
	@Override
	public void keyReleased(int key) {
		if (currentScene != null) currentScene.keyReleased(key);
	}
	
	/**
	 * Handles game exit.
	 */
	public void exit() {
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
