package graphics;

/**
 * Represents a 3D vertex.
 */
public class Vertex {
	
	/** The vertex's position */
	private float[] pos;
	
	/** The vertex's colour */
	private float[] col;
	
	/** The vertex's texture position */
	private float[] tex;
	
	
	/**
	 * Constructs a textured vertex.
	 * @param x - the vertex's x position
	 * @param y - the vertex's y position
	 * @param z - the vertex's z position
	 * @param r - the vertex's red value
	 * @param g - the vertex's green value
	 * @param b - the vertex's blue value
	 * @param a - the vertex's alpha value
	 * @param s - the vertex's texture s position
	 * @param t - the vertex's texture t position
	 */
	public Vertex(float x, float y, float z,
			float r, float g, float b, float a,
			float s, float t) {
		pos = new float[] {
				(x * 2) - 1,
				((y * 2) - 1) * -1,
				z,
				1
		};
		
		col = new float[] {
				r,
				g,
				b,
				a
		};
		
		tex = new float[] {
				s,
				t
		};
	}
	
	/**
	 * Constructs a textured vertex.
	 * @param x - the vertex's x position
	 * @param y - the vertex's y position
	 * @param z - the vertex's z position
	 * @param r - the vertex's red value
	 * @param g - the vertex's green value
	 * @param b - the vertex's blue value
	 * @param s - the vertex's texture s position
	 * @param t - the vertex's texture t position
	 */
	public Vertex(float x, float y, float z,
			float r, float g, float b,
			float s, float t) {
		this(x, y, z, r, g, b, 1, s, t);
	}
	
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
	public Vertex(float x, float y, float z,
			float r, float g, float b, float a) {
		this(x, y, z, r, g, b, a, 0, 0);
	}
	
	/**
	 * Constructs a vertex.
	 * @param x - the vertex's x position
	 * @param y - the vertex's y position
	 * @param z - the vertex's z position
	 * @param r - the vertex's red value
	 * @param g - the vertex's green value
	 * @param b - the vertex's blue value
	 */
	public Vertex(float x, float y, float z,
			float r, float g, float b) {
		this(x, y, z, r, g, b, 1, 0, 0);
	}
	
	
	/**
	 * Gets the vertex's x position.
	 * @return the vertex's x position
	 */
	public float x() {
		return pos[0];
	}
	
	/**
	 * Gets the vertex's y position.
	 * @return the vertex's y position
	 */
	public float y() {
		return pos[1];
	}
	
	/**
	 * Gets the vertex's z position.
	 * @return the vertex's z position
	 */
	public float z() {
		return pos[2];
	}
	
	/**
	 * Gets the vertex's w position.
	 * @return the vertex's w position
	 */
	public float w() {
		return pos[3];
	}
	
	
	/**
	 * Gets the vertex's relative x position.
	 * @return the vertex's relative x position
	 */
	public float relX() {
		return (x() + 1) / 2;
	}
	
	/**
	 * Gets the vertex's relative y position.
	 * @return the vertex's relative y position
	 */
	public float relY() {
		return (((y() * -1) + 1) / 2);
	}
	
	/**
	 * Gets the vertex's x, y, z and w positions.
	 * @return the vertex's x, y, z and w positions
	 */
	public float[] xyzw() {
		return new float[] {x(), y(), z(), w()};
	}
	
	/**
	 * Gets the vertex's red value.
	 * @return the vertex's red value
	 */
	public float r() {
		return col[0];
	}
	
	/**
	 * Gets the vertex's green value.
	 * @return the vertex's green value
	 */
	public float g() {
		return col[1];
	}
	
	/**
	 * Gets the vertex's blue value.
	 * @return the vertex's blue value
	 */
	public float b() {
		return col[2];
	}
	
	/**
	 * Gets the vertex's alpha value.
	 * @return the vertex's alpha value
	 */
	public float a() {
		return col[3];
	}
	
	/**
	 * Gets the vertex's r, g, b and a values.
	 * @return the vertex's r, g, b and a values
	 */
	public float[] rgba() {
		return new float[] {r(), g(), b(), a()};
	}
	
	
	/**
	 * Gets the vertex's texture s value.
	 * @return the vertex's texture s value
	 */
	public float s() {
		return tex[0];
	}
	
	/**
	 * Gets the vertex's texture t value.
	 * @return the vertex's texture t value
	 */
	public float t() {
		return tex[1];
	}
	
	/**
	 * Gets the vertex's texture s and t values.
	 * @return the vertex's texture s and t values
	 */
	public float[] st() {
		return new float[] {tex[0], tex[1]};
	}
	
	
	/**
	 * Sets the vertex's x position.
	 * @param x - the new x position
	 */
	public void setX(float x) {
		this.pos[0] = (x * 2) - 1;
	}
	
	/**
	 * Sets the vertex's y position.
	 * @param y - the new y position
	 */
	public void setY(float y) {
		this.pos[1] = ((y * 2) - 1) * -1;
	}
	
	/**
	 * Sets the vertex's z position.
	 * @param z - the new z position
	 */
	public void setZ(float z) {
		this.pos[2] = z;
	}
	
	/**
	 * Sets the vertex's x, y, z and w positions.
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param z - the new z position
	 * @param w - the new w position
	 */
	public void setXYZW(float x, float y, float z, float w) {
		this.pos[0] = (x * 2) - 1;
		this.pos[1] = ((y * 2) - 1) * -1;
		this.pos[2] = z;
		this.pos[3] = w;
	}

}