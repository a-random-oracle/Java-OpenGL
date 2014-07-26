package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

/**
 * Manages resources.
 */
public abstract class ResourceManager {
	
	/** The first example texture location */
	public static final String TEX1 = "src/resources/textures/texture1.png";
	
	/** The second example texture location */
	public static final String TEX2 = "src/resources/textures/texture2.png";
	
	/** The aircraft example texture location */
	public static final String AIR_TEX = "src/resources/textures/aircraft.png";

	/**
	 * Loads resources.
	 */
	public static void load() {
		//TODO: Load resources
	}
	
	/**
	 * Loads resources which require an OpenGL context.
	 */
	public static void loadOpenGLDependant() {
		//TODO: Load OpenGL-dependent resources
	}
	
	
	/**
	 * Uses PNGDecoder to load a texture from a PNG file.
	 * @param filename - the texture file
	 * @param textureType - the type of texture to load
	 * @return the texture's ID
	 */
	public static int loadTexture(String filename, int textureType) {
		ByteBuffer buffer = null;
		int textureWidth = 0;
		int textureHeight = 0;
		
		try {
			// Open the PNG file
			InputStream inStream = new FileInputStream(filename);

			// Create a PNG decoder to decode the texture file
			PNGDecoder decoder = new PNGDecoder(inStream);

			// Get the texture's width and height
			textureWidth = decoder.getWidth();
			textureHeight = decoder.getHeight();

			// Decode the PNG file
			// Assumes that the PNG file is in RGBA form
			buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth()
					* decoder.getHeight());
			decoder.decode(buffer, decoder.getWidth() * 4, Format.RGBA);
			buffer.flip();

			// Close the input stream
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		// Create the texture object
		int texturePointer = glGenTextures();
		glActiveTexture(textureType);
		glBindTexture(GL_TEXTURE_2D, texturePointer);
		
		// Set up the memory being used to store the texture
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		
		// Add the texture data
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, textureWidth, textureHeight, 0,
				GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		// Generate a mipmap for the texture
		// This is used to scale the image
		glGenerateMipmap(GL_TEXTURE_2D);

		// Set up the coordinate system
		// Turn on repeated wrapping
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		// Define texture scaling
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,
				GL_LINEAR_MIPMAP_LINEAR);

		return texturePointer;
	}

}
