package scenes;

import jog.input.InputEventHandler;

/**
 * The template class for game scenes.
 * <p>
 * Provides means for:
 * <ul>
 * <li>Updating a scene,</li>
 * <li>Rendering a scene,</li>
 * <li>Interacting with a scene and</li>
 * <li>Closing a scene</li>
 * </ul>
 * </p>
 */
public abstract class Scene implements InputEventHandler {

	/**
	 * Updates the scene.
	 */
	public abstract void update();

	/**
	 * Renders the scene.
	 */
	public abstract void render();

	/**
	 * Handles mouse presses.
	 */
	@Override
	public abstract void mousePressed(int key, int x, int y);

	/**
	 * Handles mouse releases.
	 */
	@Override
	public abstract void mouseReleased(int key, int x, int y);

	/**
	 * Handles key presses.
	 */
	@Override
	public abstract void keyPressed(int key);

	/**
	 * Handles key releases.
	 */
	@Override
	public abstract void keyReleased(int key);

	/**
	 * Closes the scene.
	 * <p>
	 * This method <b>must</b> return a new scene, or the game will exit.
	 * </p>
	 */
	public abstract Scene close();

}
