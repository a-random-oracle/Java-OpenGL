package main;

/**
 * The scene template.
 * <p>
 * This class specifies the functionality which scenes must implement.
 * </p>
 */
public abstract class Scene {
	
	/**
	 * Enter the scene.
	 * <p>
	 * Used to perform scene initialisation actions.
	 *</p>
	 */
	protected abstract void enter();

	/**
	 * Update the scene.
	 * <p>
	 * Runs the scene's logic.
	 *</p>
	 */
	protected abstract void update(int delta);
	
	/**
	 * Render the scene.
	 * <p>
	 * Draws the objects present in the scene to the screen.
	 *</p>
	 */
	protected abstract void render();
	
	/**
	 * Handle mouse press events.
	 */
	protected abstract void mousePress(int button, int mx, int my);
	
	/**
	 * Handle mouse release events.
	 */
	protected abstract void mouseRelease(int button, int mx, int my);
	
	/**
	 * Handle mouse scroll events.
	 */
	protected abstract void scroll(int amount, int mx, int my);
	
	/**
	 * Handle key press events.
	 */
	protected abstract void keyPress(int key);
	
	/**
	 * Handle key release events.
	 */
	protected abstract void keyRelease(int key);
	
	/**
	 * Exit the scene.
	 * <p>
	 * Closes the scene.
	 *</p>
	 */
	protected abstract int exit();
	
}
