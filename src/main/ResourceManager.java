package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public abstract class ResourceManager {
	
	/** The main font template */
	private static Font mainFontTemplate;
	
	/** The main font */
	private static TrueTypeFont mainFont;

	/**
	 * Loads resources.
	 */
	public static void load() {
		//TODO: Load initial resources
		
		// Load the font templates
		InputStream inputStream =
				ResourceLoader.getResourceAsStream(
						"resources/fonts/frutiger.ttf");
		
		try {
			mainFontTemplate = Font.createFont(
					Font.TRUETYPE_FONT, inputStream);
			mainFontTemplate = mainFontTemplate.deriveFont(24f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads resources which require an OpenGL context.
	 */
	public static void loadOpenGLDependant() {
		// Create the true-type font
		try {
			mainFont = new TrueTypeFont(mainFontTemplate, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Set the current graphics font to the main font
		Graphics.font = mainFont;
	}
	
	
	/**
	 * Gets the main font.
	 * @return the main font
	 */
	public static TrueTypeFont mainFont() {
		return mainFont;
	}
	
}
