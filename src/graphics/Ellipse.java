package graphics;

/**
 * Represents an ellipse.
 */
public class Ellipse extends Polygon {
	
	/**
	 * Creates a new ellipse.
	 * @param centre - the centre of the ellipse
	 * @param xRadius - (half) the ellipse's width
	 * @param yRadius - (half) the ellipse's height
	 * @param precision - the number of vertices to use to specify the
	 * ellipse
	 */
	public Ellipse(Vertex centre, float xRadius, float yRadius,
			int precision) {
		super(centre, xRadius, yRadius, precision);
	}
	
	/**
	 * Creates a new ellipse.
	 * @param centre - the centre of the ellipse
	 * @param xRadius - (half) the ellipse's width
	 * @param yRadius - (half) the ellipse's height
	 */
	public Ellipse(Vertex centre, float xRadius, float yRadius) {
		this(centre, xRadius, yRadius, 100);
	}
	
}