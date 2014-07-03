package graphics;

/**
 * Represents a 3D vertex.
 * <p>
 * The third dimension is optional.
 * </p>
 */
public class Vertex {
	
	/** The vertex's x position */
	private double x;
	
	/** The vertex's y position */
	private double y;
	
	/** The vertex's z position */
	private double z;
	
	/** The vertex's w position */
	private double w;
	
	/** The vertex's red value */
	private double r;
	
	/** The vertex's green value */
	private double g;
	
	/** The vertex's blue value */
	private double b;
	
	/** The vertex's alpha value */
	private double a;
	
	
	/**
	 * Constructs a vertex.
	 * @param x - the vertex's x position
	 * @param y - the vertex's y position
	 * @param z - the vertex's z position
	 * @param r - the vertex's red value
	 * @param g - the vertex's green value
	 * @param b - the vertex's blue value
	 * @param a - the vertex's alpha value
	 */
	public Vertex(double x, double y, double z,
			double r, double g, double b, double a) {
		this.x = (x * 2) - 1;
		this.y = ((y * 2) - 1) * -1;
		this.z = z;
		this.w = 1;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	/**
	 * Constructs a vertex.
	 * @param x - the vertex's x position
	 * @param y - the vertex's y position
	 * @param r - the vertex's red value
	 * @param g - the vertex's green value
	 * @param b - the vertex's blue value
	 * @param a - the vertex's alpha value
	 */
	public Vertex(double x, double y,
			double r, double g, double b, double a) {
		this(x, y, 0, r, g, b, a);
	}
	
	
	/**
	 * Gets the vertex's x position.
	 * @return the vertex's x position
	 */
	public double x() {
		return x;
	}
	
	/**
	 * Gets the vertex's y position.
	 * @return the vertex's y position
	 */
	public double y() {
		return y;
	}
	
	/**
	 * Gets the vertex's z position.
	 * @return the vertex's z position
	 */
	public double z() {
		return z;
	}
	
	/**
	 * Gets the vertex's w position.
	 * @return the vertex's w position
	 */
	public double w() {
		return w;
	}
	
	
	/**
	 * Gets the vertex's relative x position.
	 * @return the vertex's relative x position
	 */
	public double relX() {
		return (x + 1) / 2;
	}
	
	/**
	 * Gets the vertex's relative y position.
	 * @return the vertex's relative y position
	 */
	public double relY() {
		return (((y * -1) + 1) / 2);
	}
	
	/**
	 * Gets the vertex's relative z position.
	 * @return the vertex's relative z position
	 */
	public double relZ() {
		return z;
	}
	
	/**
	 * Gets the vertex's red value.
	 * @return the vertex's red value
	 */
	public double r() {
		return r;
	}
	
	/**
	 * Gets the vertex's green value.
	 * @return the vertex's green value
	 */
	public double g() {
		return g;
	}
	
	/**
	 * Gets the vertex's blue value.
	 * @return the vertex's blue value
	 */
	public double b() {
		return b;
	}
	
	/**
	 * Gets the vertex's alpha value.
	 * @return the vertex's alpha value
	 */
	public double a() {
		return a;
	}
	
}