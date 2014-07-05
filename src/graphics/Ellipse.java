package graphics;

import org.lwjgl.opengl.Display;

/**
 * Represents an ellipse.
 */
public class Ellipse extends Shape {
	
	/**
	 * Creates a new ellipse.
	 * @param centre - the centre of the ellipse
	 * @param radius - the ellipse's radius
	 * @param precision - the number of vertices to use to specify the
	 * ellipse
	 */
	public Ellipse(Vertex centre, float xRadius, float yRadius, int precision) {
		super(formIndexArray(100),
				createVertices(centre, xRadius, yRadius, precision));
	}
	
	/**
	 * Creates a new ellipse.
	 * @param centre - the centre of the ellipse
	 * @param radius - the ellipse's radius
	 */
	public Ellipse(Vertex centre, float xRadius, float yRadius) {
		this(centre, xRadius, yRadius, 100);
	}
	
	
	/**
	 * Forms the index array for a specific precision.
	 * @param precision - the number of vertices which should be used
	 * to define the circle 
	 * @return the array of vertex indices
	 */
	private static byte[] formIndexArray(int precision) {
		// Check that the precision is at least 3
		// A precision of 3 is a triangle
		if (precision < 3) {
			throw new IllegalArgumentException(
					"The precision must be at least 3");
		}
		
		// Create a byte array to store the indices
		byte[] indices = new byte[precision * 3];
		
		// Generate the indices
		// These should be of the form:
		//   0, 1, 2,
		//   0, 2, 3,
		//   ...
		//   0, n, n+1
		// where n is the specified precision
		int curIndex = 0;
		for (int i = 0; i < precision; i += 1) {
			indices[curIndex] = 0;
			indices[curIndex + 1] = (byte) (i + 1);
			indices[curIndex + 2] = (byte) (i + 2);
			
			curIndex += 3;
		}
		
		// Set the last element in the index to loop back round to
		// the first triangle
		indices[(precision * 3) - 1] = 1;
		
		return indices;
	}
	
	private static Vertex[] createVertices(Vertex centre, 
			float xRadius, float yRadius, int precision) {
		Vertex[] vertices = new Vertex[precision + 1];
		
		vertices[0] = centre;
		
		double segmentSize = (Math.PI * 2) / (precision - 1);
		
		for (int i = 1; i <= precision; i++) {
            double angle = i * segmentSize;
            vertices[i] = new Vertex(
            		(float) (centre.relX() + (Math.cos(angle) * xRadius)),
            		(float) (centre.relY() + (Math.sin(angle) * yRadius)),
            		1,
            		centre.r(),
            		centre.g(),
            		centre.b(),
            		centre.a()
            );
        }
		
		return vertices;
	}
	
	
	/**
	 * Represents a circle.
	 */
	public static class Circle extends Ellipse {
		
		/**
		 * Creates a new circle.
		 * @param centre - the centre of the circle
		 * @param radius - the circle's radius
		 * @param precision - the number of vertices to use to specify the
		 * circle
		 */
		public Circle(Vertex centre, float radius, int precision) {
			super(centre, radius, radius * Display.getWidth()
					/ Display.getHeight(), precision);
		}
		
		/**
		 * Creates a new circle.
		 * @param centre - the centre of the circle
		 * @param radius - the circle's radius
		 */
		public Circle(Vertex centre, float radius) {
			this(centre, radius, 100);
		}
		
	}
	
}
