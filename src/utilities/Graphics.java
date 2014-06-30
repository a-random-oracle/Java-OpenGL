package utilities;

import static org.lwjgl.opengl.GL11.*;
import main.ResourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 * Handles low-level rendering of shapes, text etc.
 */
public class Graphics {
	
	/** Text alignment options */
	public enum Align {
		TOP_LEFT, TOP_CENTRE, TOP_RIGHT,
		MID_LEFT, MID_CENTRE, MID_RIGHT,
		BOTTOM_LEFT, BOTTOM_CENTRE, BOTTOM_RIGHT,
	};
	
	/* Colours */
	public static final int[] RED = new int[] {255, 0, 0};
	public static final int[] GREEN = new int[] {0, 255, 0};
	public static final int[] BLUE = new int[] {0, 0, 255};
	public static final int[] ORANGE = new int[] {255, 77, 0};
	public static final int[] MAGENTA = new int[] {204, 0, 102};
	public static final int[] CYAN = new int[] {51, 255, 255};
	public static final int[] BLACK = new int[] {0, 0, 0};
	public static final int[] WHITE = new int[] {255, 255, 255};
	
	/* Game properties */
	public static double windowWidth;
	public static double windowHeight;
	public static TrueTypeFont font = ResourceManager.mainFont();
	
	
	
	
	
	
	
	/*###########################   Rectangles   ###########################*/
	/**
	 * Draws a rectangle.
	 * <p>
	 * The x and y values are relative to the screen size, so to position
	 * the rectangle at the far left of the screen use x=0, for the far
	 * right use x=1, and for halfway across the screen use x=0.5.
	 * Similarly for y values, and the width and height.
	 * </p>
	 * @param x - the x position of the top-left corner of the rectangle
	 * @param y - the y position of the top-left corner of the rectangle
	 * @param width - the width of the rectangle
	 * @param height - the height of the rectangle
	 */
	public static void drawRect(double x, double y,
			double width, double height) {
		double actualX = x * windowWidth;
		double actualY = y * windowHeight;
		double actualWidth = width * windowWidth;
		double actualHeight = height * windowHeight;
		
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			glVertex2d(actualX, actualY);
			glVertex2d(actualX + actualWidth, actualY);
			glVertex2d(actualX + actualWidth, actualY + actualHeight);
			glVertex2d(actualX, actualY + actualHeight);
		}
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	/**
	 * Draws a rectangle.
	 * <p>
	 * The x and y values are relative to the screen size, so to position
	 * the rectangle at the far left of the screen use x=0, for the far
	 * right use x=1, and for halfway across the screen use x=0.5.
	 * Similarly for y values, and the width and height.
	 * </p>
	 * @param colour - the colour to draw the rectangle
	 * @param x - the x position of the top-left corner of the rectangle
	 * @param y - the y position of the top-left corner of the rectangle
	 * @param width - the width of the rectangle
	 * @param height - the height of the rectangle
	 */
	public static void drawRect(int[] colour,
			double x, double y, double width, double height) {
		glColor3ub((byte) colour[0], (byte) colour[1], (byte) colour[2]);
		drawRect(x, y, width, height);
	}
	
	/**
	 * Draws a rectangle.
	 * <p>
	 * The x and y values are relative to the screen size, so to position
	 * the rectangle at the far left of the screen use x=0, for the far
	 * right use x=1, and for halfway across the screen use x=0.5.
	 * Similarly for y values, and the width and height.
	 * </p>
	 * @param colour - the colour to draw the rectangle
	 * @param transp - the alpha level to use
	 * @param x - the x position of the top-left corner of the rectangle
	 * @param y - the y position of the top-left corner of the rectangle
	 * @param width - the width of the rectangle
	 * @param height - the height of the rectangle
	 */
	public static void drawRect(int[] colour, double transp,
			double x, double y, double width, double height) {
		glColor4ub((byte) colour[0], (byte) colour[1], (byte) colour[2],
				(byte) transp);
		drawRect(x, y, width, height);
	}
	
	
	
	
	
	
	
	/*#############################   Strings   #############################*/
	/**
	 * Draws a string.
	 * @param string - the string to draw
	 * @param alignment - the alignment to give the string
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawString(String string, Align alignment,
			double x, double y) {
		switch (alignment) {
		case TOP_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) (y * windowHeight), string);
			break;
		case TOP_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) (y * windowHeight), string);
			break;
		case TOP_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) (y * windowHeight), string);
			break;
		case MID_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string);
			break;
		case MID_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string);
			break;
		case MID_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string);
			break;
		case BOTTOM_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string);
			break;
		case BOTTOM_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string);
			break;
		case BOTTOM_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string);
			break;
		}
	}
	
	/**
	 * Draws a string.
	 * @param string - the string to draw
	 * @param alignment - the alignment to give the string
	 * @param colour - the colour to draw the string
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawString(String string, Align alignment,
			int[] colour, double x, double y) {
		switch (alignment) {
		case TOP_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) (y * windowHeight), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case TOP_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) (y * windowHeight), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case TOP_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) (y * windowHeight), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case MID_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case MID_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case MID_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case BOTTOM_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case BOTTOM_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		case BOTTOM_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string,
					new Color(colour[0], colour[1], colour[2]));
			break;
		}
	}
	
	/**
	 * Draws a string.
	 * @param string - the string to draw
	 * @param alignment - the alignment to give the string
	 * @param colour - the colour to draw the string
	 * @param transp - the alpha level to use
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawString(String string, Align alignment,
			int[] colour, int transp, double x, double y) {
		switch (alignment) {
		case TOP_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) (y * windowHeight), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case TOP_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) (y * windowHeight), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case TOP_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) (y * windowHeight), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case MID_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case MID_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case MID_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) ((y * windowHeight)
					- (font.getHeight(string) / 2)), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case BOTTOM_LEFT:
			font.drawString((float) (x * windowWidth),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case BOTTOM_CENTRE:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string) / 2)),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		case BOTTOM_RIGHT:
			font.drawString((float) ((x * windowWidth)
					- (font.getWidth(string))),
					(float) ((y * windowHeight)
					- (font.getHeight(string))), string,
					new Color(colour[0], colour[1], colour[2], transp));
			break;
		}
	}
	
	/**
	 * Sets the font size of the main font.
	 * @param size - the size to draw the main font
	 */
	public static void setFontSize(int size) {
		ResourceManager.setMainFontSize(size);
		font = ResourceManager.mainFont();
	}
	
}
