package scenes;

import main.Scene;
import main.SceneManager;

import graphics.Vertex;
import graphics.Polygon;
import graphics.Quad;

import org.lwjgl.input.Keyboard;

public class TitleScene extends Scene {
	
	private Quad quad;
	private Polygon pentagon;
	private Quad quad2;
	
	private float currentOffsetTop;
	private float currentOffsetBottom;
	private float speed;
	
	@Override
	protected void enter() {
		currentOffsetTop = 0.6f;
		currentOffsetBottom = 0.4f;
		speed = 0.2f;
		
		quad = new Quad(
				new Vertex(0.4f, 0.05f, 1f, 1, 0, 0, 0, 0),
				new Vertex(0.4f, 0.25f, 1f, 0, 1, 0, 0, 1),
				new Vertex(0.6f, 0.25f, 1f, 0, 0, 1, 1, 1),
				new Vertex(0.6f, 0.05f, 1f, 0, 0, 0, 1, 0)
		);
		
		pentagon = new Polygon(
				new Vertex(0.5f, 0.5f, 1, 0.4f, 0.5f, 1),
				0.05f,
				3
		);
		
		quad2 = new Quad(
				new Vertex(0.4f, 0.75f, 1f, 0, 0, 1),
				new Vertex(0.4f, 0.95f, 1f, 0, 1, 0),
				new Vertex(0.6f, 0.95f, 1f, 1, 0, 0),
				new Vertex(0.6f, 0.75f, 1f, 1, 1, 1)
		);
	}

	@Override
	protected void update(double delta) {
		Vertex[] quadVertices = quad.vertices();

		quadVertices[0].setX(currentOffsetTop - 0.2f);
		quadVertices[1].setX(currentOffsetTop - 0.2f);
		quadVertices[2].setX(currentOffsetTop);
		quadVertices[3].setX(currentOffsetTop);
		
		Vertex[] quad2Vertices = quad2.vertices();

		quad2Vertices[0].setX(currentOffsetBottom);
		quad2Vertices[1].setX(currentOffsetBottom);
		quad2Vertices[2].setX(currentOffsetBottom + 0.2f);
		quad2Vertices[3].setX(currentOffsetBottom + 0.2f);
		
		quad.resetVertices();
		quad2.resetVertices();

		currentOffsetTop += (delta * speed / 10000);
		currentOffsetBottom -= (delta * speed / 10000);
		currentOffsetTop %= 1.2;
		if (currentOffsetBottom < -0.2) currentOffsetBottom = 1;
	}

	@Override
	protected void render() {
		quad.render();
		pentagon.render();
		quad2.render();
	}

	@Override
	protected void mousePress(int button, int mx, int my) {
		speed = 6f;
	}

	@Override
	protected void mouseRelease(int button, int mx, int my) {
		speed = 1f;
	}

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
		pentagon.destroy();
		quad2.destroy();
	}

}
