package graphics;

/**
 * Represents a rectangle.
 */
public class Rectangle extends Quad {
	
	/**
	 * Creates a new rectangle.
	 * @param topLeft - the top-left point of the rectangle
	 * @param width - the width of the rectangle
	 * @param height - the height of the rectangle
	 */
	public Rectangle(Vertex topLeft, float width, float height) {
		super(topLeft,
				new Vertex(topLeft.relX(),
						topLeft.relY() + height,
						1,
						topLeft.r(),
						topLeft.g(),
						topLeft.b(),
						topLeft.a()
				),
				new Vertex(topLeft.relX() + width,
						topLeft.relY() + height,
						1,
						topLeft.r(),
						topLeft.g(),
						topLeft.b(),
						topLeft.a()
				),
				new Vertex(topLeft.relX() + width,
						topLeft.relY(),
						1,
						topLeft.r(),
						topLeft.g(),
						topLeft.b(),
						topLeft.a()
				)
		);
	}
	
}