package graphics;

import main.Main;

/**
 * Represents a square.
 */
public class Square extends Rectangle {
	
	/**
	 * Creates a new square.
	 * @param topLeft - the top-left point of the square
	 * @param width - the width of the square
	 */
	public Square(Vertex topLeft, float width) {
		super(topLeft, width, (float) (width * Main.widthHeightRatio()));
	}
	
}
