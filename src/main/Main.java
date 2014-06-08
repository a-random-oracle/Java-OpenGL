package main;

public class Main {
	
	/** Game status - whether the game is running or not */
	private static boolean running;

	
	/**
	 * Starts the game.
	 * @param args - variadic command line parameters
	 */
	public static void main(String... args) {
		// Set up the game window
		setUpWindow();
		
		// Start the game running
		running = true;
		
		// Do the update loop
		while (running) {
			update();
			render();
		}
		
		// Finish by handling game exit
		exit();
	}
	
	
	/**
	 * Sets up the game window.
	 */
	private static void setUpWindow() {
		
	}
	
	/**
	 * Updates the current scene.
	 */
	private static void update() {
		
	}
	
	/**
	 * Renders graphical elements.
	 */
	private static void render() {
		
	}
	
	/**
	 * Handles game exit.
	 */
	private static void exit() {
		System.exit(0);
	}

}
