package main;

/**
 * The scene template.
 * <p>
 * This class specifies the functionality which scenes must implement.
 * </p>
 */
public abstract class Scene {
	
	/**
	 * Enters the scene.
	 * <p>
	 * Used to perform scene initialisation.
	 *</p>
	 */
	protected abstract void enter();

	/**
	 * Updates the scene.
	 * <p>
	 * Runs the scene's logic.
	 *</p>
	 *@param delta - the time (in milliseconds) since the last update
	 */
	protected abstract void update(int delta);
	
	/**
	 * Renders the scene.
	 * <p>
	 * Draws the objects present in the scene to the screen.
	 *</p>
	 */
	protected abstract void render();
	
	/**
	 * Handles mouse press events.
	 */
	protected abstract void mousePress(int button, int mx, int my);
	
	/**
	 * Handles mouse release events.
	 */
	protected abstract void mouseRelease(int button, int mx, int my);
	
	/**
	 * Handles mouse scroll events.
	 */
	protected abstract void scroll(int amount, int mx, int my);
	
	/**
	 * Handles key press events.
	 */
	protected abstract void keyPress(int key);
	
	/**
	 * Handles key release events.
	 */
	protected abstract void keyRelease(int key);
	
	/**
	 * Exits the scene.
	 */
	protected abstract void exit();
	
}
