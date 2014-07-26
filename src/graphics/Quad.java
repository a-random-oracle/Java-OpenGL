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
	public Quad(Vertex vertex1, Vertex vertex2, Vertex vertex3, Vertex vertex4) {
		super(QUAD_INDEX_ARRAY, vertex1, vertex2, vertex3, vertex4);
	}
	
}
