package graphics;

/**
 * Represents a triangle.
 */
public class Triangle extends Shape {
	
	/** The vector index mapping for triangles */
	private static final byte[] TRIANGLE_INDEX_ARRAY = new byte[] {
		0, 1, 2
	};

	
	/**
	 * Creates a new triangle.
	 * @param vertex1 - the first vertex
	 * @param vertex2 - the second vertex
	 * @param vertex3 - the third vertex
	 */
	public Triangle(Vertex vertex1, Vertex vertex2, Vertex vertex3) {
		super(TRIANGLE_INDEX_ARRAY, vertex1, vertex2, vertex3);
	}
	
}
