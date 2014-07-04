package scenes;

import main.Scene;
import main.SceneManager;

import graphics.Vertex;
import graphics.Quad;
import graphics.Quad.Rectangle;

import org.lwjgl.input.Keyboard;

public class TitleScene extends Scene {
	
	private Quad quad;
	private Rectangle rect;
	
	@Override
	protected void enter() {
		quad = new Quad(
				new Vertex(0.4f, 0.1f, 1f, 1, 0, 0),
				new Vertex(0.4f, 0.3f, 1f, 0, 1, 0),
				new Vertex(0.6f, 0.3f, 1f, 0, 0, 1),
				new Vertex(0.6f, 0.1f, 1f, 1, 1, 1)
		);
		
		rect = new Rectangle(
				new Vertex(0.4f, 0.6f, 1f, 0.5f, 0.3f, 0.8f, 0.3f),
				0.2f, 0.2f
		);
	}

	@Override
	protected void update(double delta) {}

	@Override
	protected void render() {
		quad.render();
		rect.render();
	}

	@Override
	protected void mousePress(int button, int mx, int my) {}

	@Override
	protected void mouseRelease(int button, int mx, int my) {}

	@Override
	protected void scroll(int amount, int mx, int my) {}

	@Override
	protected void keyPress(int key) {
		switch (key) {
		case Keyboard.KEY_ESCAPE:
			SceneManager.switchScene(-1);
			break;
		}
	}

	@Override
	protected void keyRelease(int key) {}

	@Override
	protected void exit() {
		quad.destroy();
		rect.destroy();
	}

}
