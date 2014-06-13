package main;

/**
 * Creates an instance of the game, and handles it's execution.
 */
public class Main {
	
	/** The possible game statuses
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
	
	/** The game's status */
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
		splash(); // Splash happens asynchronously, so this will not block
		//TODO: Load scenes and resources
		
		// Wait for the splash screen to finish
		while (gameStatus == Status.SPLASHING) {
			try {
				splashMonitor.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Then set up the game window
		gameStatus = Status.WAITING;
		setUpWindow();
		gameStatus = Status.RUNNING;
		
		// Do the update and render loop
		while (gameStatus == Status.RUNNING) {
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
				gameStatus = Status.WAITING;
				splashMonitor.notifyAll();
			}
		});
		
		// Start the splash thread
		splashThread.start();
	}
	
	/**
	 * Sets up the game window.
	 */
	private void setUpWindow() {
		//TODO
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
		//TODO
	}
	
	/**
	 * Handles game exit.
	 */
	public void exit() {
		// Exit the game
		System.exit(0);
	}
	
	
	/**
	 * Gets the current game instance.
	 * @return the current game instance
	 */
	public Main instance() {
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
	public Status gameStatus() {
		return gameStatus;
	}

}
