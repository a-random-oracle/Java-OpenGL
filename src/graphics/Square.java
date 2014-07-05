package graphics;

/**
 * Represents a square.
 */
public class Square extends Quad {
	
	/**
	 * Creates a new square.
	 * @param topLeft - the top-left point of the square
	 * @param width - the width of the square
	 */
	public Square(Vertex topLeft, float width) {
		super(topLeft,
				new Vertex(topLeft.relX(),
						topLeft.relY() + width,
						1,
						topLeft.r(),
						topLeft.g(),
						topLeft.b(),
						topLeft.a()
				),
				new Vertex(topLeft.relX() + width,
						topLeft.relY() + width,
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
