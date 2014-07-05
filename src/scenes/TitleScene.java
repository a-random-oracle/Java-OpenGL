package scenes;

import main.Scene;
import main.SceneManager;

import graphics.Vertex;
import graphics.Ellipse.Circle;
import graphics.Quad;

import org.lwjgl.input.Keyboard;

public class TitleScene extends Scene {
	
	private Quad quad;
	private Circle circ;
	private Quad quad2;
	
	@Override
	protected void enter() {
		quad = new Quad(
				new Vertex(0.4f, 0.05f, 1f, 1, 0, 0),
				new Vertex(0.4f, 0.25f, 1f, 0, 1, 0),
				new Vertex(0.6f, 0.25f, 1f, 0, 0, 1),
				new Vertex(0.6f, 0.05f, 1f, 0, 0, 0)
		);
		
		circ = new Circle(
				new Vertex(0.5f, 0.5f, 1, 0.4f, 0.5f, 1),
				0.05f
		);
		
		quad2 = new Quad(
				new Vertex(0.4f, 0.75f, 1f, 0, 0, 1),
				new Vertex(0.4f, 0.95f, 1f, 0, 1, 0),
				new Vertex(0.6f, 0.95f, 1f, 1, 0, 0),
				new Vertex(0.6f, 0.75f, 1f, 1, 1, 1)
		);
	}

	@Override
	protected void update(double delta) {}

	@Override
	protected void render() {
		quad.render();
		circ.render();
		quad2.render();
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
		circ.destroy();
		quad2.destroy();
	}

}
