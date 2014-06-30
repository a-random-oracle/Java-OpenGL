package main;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Graphics {
	
	public static final int[] RED = new int[] {255, 0, 0};
	public static final int[] GREEN = new int[] {0, 255, 0};
	public static final int[] BLUE = new int[] {0, 0, 255};
	public static final int[] ORANGE = new int[] {255, 77, 0};
	public static final int[] MAGENTA = new int[] {204, 0, 102};
	public static final int[] CYAN = new int[] {51, 255, 255};
	public static final int[] BLACK = new int[] {0, 0, 0};
	public static final int[] WHITE = new int[] {255, 255, 255};
	
	public static double windowWidth;
	public static double windowHeight;
	public static TrueTypeFont font;
	
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
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawString(String string, double x, double y) {
		font.drawString((float) (x * windowWidth), (float) (y * windowHeight),
				string);
	}
	
	/**
	 * Draws a string centred horizontally.
	 * @param string - the string to draw
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawStringCentred(String string, double x, double y) {
		font.drawString((float) ((x * windowWidth)
				- (font.getWidth(string) / 2)),
				(float) (y * windowHeight), string);
	}
	
	/**
	 * Draws a string.
	 * @param string - the string to draw
	 * @param colour - the colour to draw the string
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawString(int[] colour,
			String string, double x, double y) {
		font.drawString((float) (x * windowWidth), (float) (y * windowHeight),
				string, new Color(colour[0], colour[1], colour[2]));
	}
	
	/**
	 * Draws a string centred horizontally.
	 * @param string - the string to draw
	 * @param colour - the colour to draw the string
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawStringCentred(int[] colour,
			String string, double x, double y) {
		font.drawString((float) ((x * windowWidth)
				- (font.getWidth(string) / 2)), (float) (y * windowHeight),
				string, new Color(colour[0], colour[1], colour[2]));
	}
	
	/**
	 * Draws a string.
	 * @param string - the string to draw
	 * @param colour - the colour to draw the string
	 * @param transp - the alpha level to use
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawString(int[] colour, int transp,
			String string, double x, double y) {
		font.drawString((float) (x * windowWidth), (float) (y * windowHeight),
				string, new Color(colour[0], colour[1], colour[2], transp));
	}
	
	/**
	 * Draws a string centred horizontally.
	 * @param string - the string to draw
	 * @param colour - the colour to draw the string
	 * @param transp - the alpha level to use
	 * @param x - the x position to draw the string at
	 * @param y - the y position to draw the string at
	 */
	public static void drawStringCentred(int[] colour, int transp,
			String string, double x, double y) {
		font.drawString((float) ((x * windowWidth)
				- (font.getWidth(string) / 2)), (float) (y * windowHeight),
				string, new Color(colour[0], colour[1], colour[2], transp));
	}
	
}
