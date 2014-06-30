package main;

import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Manages game scenes.
 * <p>
 * This class loads and unloads the scenes used in the game.
 * </p>
 * <p>
 * Scenes should be loaded by calling {@link #load()} when the game starts,
 * and they will then remain in memory until the game is closed.
 * </p>
 */
public abstract class SceneManager {
	
	/** The dictionary of scenes */
	private static TreeMap<Integer, Scene> scenes;
	
	/** The current scene */
	private static Scene currentScene;

	/**
	 * Perform the initial scene load.
	 * <p>
	 * This creates an instance of each scene. The scene instances are then
	 * stored in {@link #scenes}.
	 * </p>
	 */
	public static void load() {
		// Initialise the list of scenes
		// This is done here (rather than in the initial declaration) to keep
		// all of the loading operations in one place
		scenes = new TreeMap<Integer, Scene>();
		
		// Create an instance of each of the scenes
		// To load more scenes, add them below - an example entry is provided
		// NOTE: The first scene in this list will be displayed as the first
		// scene when the game is run
		// Other than the first scene, the order the scenes appear in below is
		// not important
		addScene(new scenes.TitleScene());
		//addScene(new scenes.#SCENE_NAME_HERE#());
		
		// Set the first scene loaded as the current scene
		// NOTE: There *must* be at least one scene loaded by this point
		currentScene = scenes.get(0);
	}
	
	/**
	 * Gets the current scene.
	 * @return the current scene
	 * @throws IllegalStateException - if this method is called before the
	 * dictionary of scenes has been initialised
	 */
	public static Scene currentScene() {
		if (scenes != null) {
			return currentScene;
		} else {
			// If the dictionary hasn't been initialised, throw an error back to
			// the caller
			// It would be possible to handle this behind the scenes by calling
			// load(), but load() should be called when the game starts, and
			// if this is not the case it is likely that something has gone
			// seriously wrong
			throw new IllegalStateException(
					"The dictionary of scenes has not been loaded.");
		}
	}
	
	/**
	 * Gets a scene from it's scene index.
	 * @param sceneIndex - the index of the scene to retrieve
	 * @return the scene referenced by the specified scene index
	 * @throws IllegalArgumentException - if the specified scene index is not
	 * present in the dictionary of scenes
	 * @throws IllegalStateException - if this method is called before the
	 * dictionary of scenes has been initialised
	 */
	public static Scene getScene(int sceneIndex)
			throws IllegalArgumentException, IllegalStateException {
		// Start by ensuring that the dictionary of scenes has been loaded
		// If it hasn't been loaded, retrieving a scene is meaningless
		if (scenes != null) {
			// Then check that the index supplied is valid (i.e. that there is
			// a scene instance at that position in the dictionary)
			if (scenes.containsKey(sceneIndex)) {
				// Get the scene from the index provided
				return scenes.get(sceneIndex);
			} else {
				// If the index is invalid, throw an error back to the caller
				throw new IllegalArgumentException(
						"The scene referenced is not loaded.");
			}
		} else {
			// If the dictionary hasn't been initialised, throw an error back to
			// the caller
			// It would be possible to handle this behind the scenes by calling
			// load(), but load() should be called when the game starts, and
			// if this is not the case it is likely that something has gone
			// seriously wrong
			throw new IllegalStateException(
					"The dictionary of scenes has not been loaded.");
		}
	}
	
	/**
	 * Gets a scene index from a scene instance.
	 * @param scene - the scene instance to retrieve the index of
	 * @return the scene index of the specified scene
	 * @throws IllegalArgumentException - if the specified scene is not present
	 * in the dictionary of scenes
	 * @throws IllegalStateException - if this method is called before the
	 * dictionary of scenes has been initialised
	 */
	public static Integer getIndex(Scene scene)
			throws IllegalArgumentException, IllegalStateException {
		// Start by ensuring that the dictionary of scenes has been loaded
		// If it hasn't been loaded, retrieving a scene index is meaningless
		if (scenes != null) {
			// Loop through each of the scenes in the dictionary
			for (Entry<Integer, Scene> entry : scenes.entrySet()) {
				// Check if the scene matches the scene given in the method
				// parameters
				if (scene.equals(entry.getValue())) {
					// Return the corresponding key
					return entry.getKey();
				}
			}
			
			// If the scene instance is not in the dictionary, throw an error
			// back to the caller
			throw new IllegalArgumentException(
					"The scene specified is not loaded.");
		} else {
			// If the dictionary hasn't been initialised, throw an error back to
			// the caller
			// It would be possible to handle this behind the scenes by calling
			// load(), but load() should be called when the game starts, and
			// if this is not the case it is likely that something has gone
			// seriously wrong
			throw new IllegalStateException(
					"The dictionary of scenes has not been loaded.");
		}
	}
	
	/**
	 * Adds a scene to the list of scenes.
	 * <p>
	 * The scene will be given an index one higher than the last scene
	 * added to the dictionary.
	 * </p>
	 * <p>
	 * If the scene is already in the dictionary, this will fail.
	 * </p>
	 * <p>
	 * If the scene is null, this will fail
	 * </p>
	 * @return <tt>true</tt> if the scene was added successfully, <tt>false</tt>
	 * if adding the scene failed
	 */
	private static boolean addScene(Scene scene) {
		// Check that the scene to be added is not null
		if (scene == null) {
			return false;
		}
		
		// Initialise the highest scene index found to -1
		// This causes the first scene added to the dictionary to take index 0
		int highestIndex = -1;
		
		// Loop through each of the scenes already in the dictionary
		for (Entry<Integer, Scene> entry : scenes.entrySet()) {
			// Check that the scene is not equal to the scene given in the
			// method parameters
			if (scene.equals(entry.getValue())) {
				return false;
			}
			
			// If the key currently being examined is higher than the previous
			// highest index, update the highest index
			if (entry.getKey() > highestIndex) {
				highestIndex = entry.getKey();
			}
		}
		
		// Add the new scene to the dictionary of scenes, with an index one
		// higher than the previous highest index
		scenes.put(highestIndex + 1, scene);
		return true;
	}
	
	/**
	 * Switches the current scene.
	 * <p>
	 * This calls the current scene's {@link Scene#exit()} method, then switches
	 * to the scene specified by the scene index provided.
	 * </p>
	 * @param nextSceneIndex - the index of the scene to switch to
	 * @throws IllegalArgumentException - if the specified scene is not present
	 * in the dictionary of scenes
	 */
	public static void switchScene(int nextSceneIndex)
			throws IllegalArgumentException {
		// Exit the current scene
		currentScene.exit();
		
		// Negative scene indexes cause the game to close
		if (nextSceneIndex < 0) {
			Main.requestClose();
		} else {
			// Check that the scene to switch to is valid
			if (scenes.containsKey(nextSceneIndex)) {
				// Set the scene to switch to as the current scene
				currentScene = scenes.get(nextSceneIndex);
				
				// Run the new scene's enter() method
				currentScene.enter();
			} else {
				// If the scene instance is not in the dictionary, throw an error
				// back to the caller
				throw new IllegalArgumentException(
						"The scene specified is not loaded.");
			}
		}
	}
	
}
