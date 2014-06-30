package utilities;

import utilities.Graphics.Align;

/**
 * Creates a string which will either fade in or fade out.
 * <p>
 * To display the string, the caller must call both the {@link #update(int)}
 * and {@link #render()} methods.
 * </p>
 */
public class FadeString {
	
	/** The possible fade directions */
	public enum FadeDirection {FADE_IN, FADE_OUT};
	
	/** The string to display */
	private final String string;
	
	/** The direction to fade in */
	private final FadeDirection direction;
	
	/** The amount of time (in milliseconds) to perform the fade */ 
	private final int fadeTime;
	
	/** The string's alignment */
	private final Align alignment;
	
	/** The string's colour */
	private final int[] colour;
	
	/** The string's x position */
	private final double x;
	
	/** The string's y position */
	private final double y;
	
	/** The current alpha level for the text */
	private double alpha;
	
	/** The amount of time the text has been at alpha=255 */
	private int duration;
	
	
	public FadeString(String string, FadeDirection direction, int fadeTime,
			 Align alignment, int[] colour, double x, double y) {
		this.string = string;
		this.direction = direction;
		this.fadeTime = fadeTime;
		this.alignment = alignment;
		this.colour = colour;
		this.x = x;
		this.y = y;
		
		this.alpha = 0;
		this.duration = 0;
	}
	
	public FadeString(String string, FadeDirection direction, int fadeTime,
			 Align alignment, double x, double y) {
		this(string, direction, fadeTime, alignment, Graphics.WHITE, x, y);
	}
	
	
	/**
	 * Updates the string.
	 * @param delta - the time (in milliseconds) since the last update
	 */
	public void update(int delta) {
		if (alpha <= 255) {
			alpha += (double) delta / (60 * ((double) fadeTime / 10000));
		} else {
			duration += delta;
		}
	}
	
	/**
	 * Renders the string.
	 */
	public void render() {
		switch (direction) {
		case FADE_IN:
			Graphics.drawString(string, alignment, colour,
					(int) alpha, x, y);
			break;
		case FADE_OUT:
			Graphics.drawString(string, alignment, colour,
					(int) (255 - alpha), x, y);
			break;
		}
	}
	
	/**
	 * Gets the amount of time (in milliseconds) that the string has been
	 * fully visible.
	 * @return the amount of time the string has been fully visible
	 */
	public int duration() {
		return duration;
	}
	
}
