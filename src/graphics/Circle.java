package graphics;

import main.Main;

/**
 * Represents a circle.
 */
public class Circle extends Ellipse {
	
	/**
	 * Creates a new circle.
	 * @param centre - the centre of the circle
	 * @param radius - the circle's radius
	 * @param precision - the number of vertices to use to specify the
	 * circle
	 */
	public Circle(Vertex centre, float radius, int precision) {
		super(centre, radius, (float) (radius * Main.widthHeightRatio()),
				precision);
	}
	
	/**
	 * Creates a new circle.
	 * @param centre - the centre of the circle
	 * @param radius - the circle's radius
	 */
	public Circle(Vertex centre, float radius) {
		super(centre, radius, (float) (radius * Main.widthHeightRatio()));
	}
	
}