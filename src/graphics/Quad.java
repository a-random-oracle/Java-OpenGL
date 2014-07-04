package graphics;

/**
 * Represents a quadrilateral.
 */
public class Quad extends Shape {
	
	/** The vector index mapping for quads */
	private static final byte[] QUAD_INDEX_ARRAY = new byte[] {
		0, 1, 2,
		2, 3, 0
	};

	
	/**
	 * Creates a new quadrilateral.
	 * @param vertex1 - the first vertex
	 * @param vertex2 - the second vertex
	 * @param vertex3 - the third vertex
	 * @param vertex4 - the fourth vertex
	 */
	public Quad(Vertex vertex1, Vertex vertex2,
			Vertex vertex3, Vertex vertex4) {
		super(QUAD_INDEX_ARRAY, vertex1, vertex2,
				vertex3, vertex4);
	}
	
	
	/**
	 * Represents a rectangle.
	 */
	public static class Rectangle extends Quad {
		
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
	
	
	/**
	 * Represents a square.
	 */
	public static class Square extends Quad {
		
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
	
}
