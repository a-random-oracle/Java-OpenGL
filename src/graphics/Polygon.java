package graphics;

import main.Main;

/**
 * Represents a polygon.
 */
public class Polygon extends Shape {
	
	/**
	 * Creates a new polygon.
	 * @param centre - the centre of the ellipse
	 * @param xRadius - the distance between the centre of the ellipse
	 * and the vertices (horizontally)
	 * @param yRadius - the distance between the centre of the ellipse
	 * and the vertices (vertically)
	 * @param vertices - the number of vertices to use to specify the
	 * polygon
	 */
	public Polygon(Vertex centre, float xRadius, float yRadius, int vertices) {
		super(formIndexArray(vertices),
				createVertices(centre, xRadius, yRadius, vertices));
	}
	
	/**
	 * Creates a new polygon.
	 * @param centre - the centre of the ellipse
	 * @param radius - the distance between the centre of the ellipse
	 * and the vertices
	 * @param vertices - the number of vertices to use to specify the
	 * polygon
	 */
	public Polygon(Vertex centre, float radius, int vertices) {
		super(formIndexArray(vertices),
				createVertices(centre, radius, vertices));
	}
	
	/**
	 * Creates a new polygon.
	 * @param centre - the centre of the ellipse
	 * @param xRadius - the distance between the centre of the ellipse
	 * and the vertices (horizontally)
	 * @param yRadius - the distance between the centre of the ellipse
	 * and the vertices (vertically)
	 * @param vertices - the number of vertices to use to specify the
	 * polygon
	 */
	public Polygon(Vertex centre, float xRadius, float yRadius,
			int vertices, float rotation) {
		super(formIndexArray(vertices),
				createVertices(centre, xRadius, yRadius, vertices, rotation));
	}
	
	/**
	 * Creates a new polygon.
	 * @param centre - the centre of the ellipse
	 * @param radius - the distance between the centre of the ellipse
	 * and the vertices
	 * @param vertices - the number of vertices to use to specify the
	 * polygon
	 */
	public Polygon(Vertex centre, float radius,
			int vertices, float rotation) {
		super(formIndexArray(vertices),
				createVertices(centre, radius, vertices, rotation));
	}
	
	
	/**
	 * Forms the index array for a specific number of vertices.
	 * @param vertices - the number of vertices which should be used
	 * to define the polygon 
	 * @return the array of vertex indices
	 */
	private static byte[] formIndexArray(int vertices) {
		// Check that the number of vertices is at least 3
		// A precision of 3 is a triangle
		if (vertices < 3) {
			throw new IllegalArgumentException(
					"The precision must be at least 3");
		}
		
		// Create a byte array to store the indices
		byte[] indices = new byte[vertices * 3];
		
		// Generate the indices
		// These should be of the form:
		//   0, 1, 2,
		//   0, 2, 3,
		//   ...
		//   0, n, n+1
		// where n is the specified precision
		int curIndex = 0;
		for (int i = 0; i < vertices; i += 1) {
			indices[curIndex] = 0;
			indices[curIndex + 1] = (byte) (i + 1);
			indices[curIndex + 2] = (byte) (i + 2);
			
			curIndex += 3;
		}
		
		// Set the last element in the index to loop back round to
		// the first triangle
		indices[(vertices * 3) - 1] = 1;
		
		return indices;
	}
	
	/**
	 * Forms the vertex array for a specific number of vertices.
	 * @param centre - the centre of the polygon
	 * @param xRadius
	 * @param yRadius
	 * @param vertices - the number of vertices which should be used
	 * to define the polygon
	 * @param rotation - the rotation to apply to the polygon
	 * @return the vertices from which the polygon can be constructed
	 */
	public static Vertex[] createVertices(Vertex centre, 
			float xRadius, float yRadius, int vertices, float rotation) {
		Vertex[] verticesArray = new Vertex[vertices + 1];
		
		verticesArray[0] = centre;
		
		double segmentSize = (Math.PI * 2) / vertices;
		double halfPI = Math.PI / 2;
		
		for (int i = 0; i < vertices; i++) {
            double angle = (i * segmentSize) - halfPI + rotation;
            verticesArray[i + 1] = new Vertex(
            		(float) (centre.relX() + (Math.cos(angle) * xRadius)),
            		(float) (centre.relY() + (Math.sin(angle) * yRadius)),
            		1,
            		centre.r(),
            		centre.g(),
            		centre.b(),
            		centre.a(),
            		1,
            		1
            );
        }
		
		return verticesArray;
	}
	
	/**
	 * Forms the vertex array for a specific number of vertices.
	 * @param centre - the centre of the polygon
	 * @param radius - the distance between the centre of the polygon and
	 * its vertices
	 * @param vertices - the number of vertices which should be used
	 * to define the polygon
	 * @param rotation - the rotation to apply to the polygon
	 * @return the vertices from which the polygon can be constructed
	 */
	public static Vertex[] createVertices(Vertex centre, 
			float radius, int vertices, float rotation) {
		return createVertices(
				centre,
				radius,
				(float) (radius * Main.widthHeightRatio()),
				vertices,
				rotation
		);
	}
	
	/**
	 * Forms the vertex array for a specific number of vertices.
	 * @param centre - the centre of the polygon
	 * @param xRadius
	 * @param yRadius
	 * @param vertices - the number of vertices which should be used
	 * to define the polygon
	 * @return the vertices from which the polygon can be constructed
	 */
	public static Vertex[] createVertices(Vertex centre, 
			float xRadius, float yRadius, int vertices) {
		return createVertices(
				centre,
				xRadius,
				yRadius,
				vertices,
				0
		);
	}
	
	/**
	 * Forms the vertex array for a specific number of vertices.
	 * @param centre - the centre of the polygon
	 * @param radius - the distance between the centre of the polygon and
	 * its vertices
	 * @param vertices - the number of vertices which should be used
	 * to define the polygon
	 * @return the vertices from which the polygon can be constructed
	 */
	public static Vertex[] createVertices(Vertex centre, 
			float radius, int vertices) {
		return createVertices(
				centre,
				radius,
				(float) (radius * Main.widthHeightRatio()),
				vertices,
				0
		);
	}
	
}
