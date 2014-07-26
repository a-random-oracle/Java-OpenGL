package scenes;

import java.util.Random;

import main.*;
import graphics.*;

import org.lwjgl.input.Keyboard;

public class TitleScene extends Scene {
	
	private Quad quad;
	private Polygon pentagon;
	private Quad quad2;
	
	private float currentOffsetTop;
	private float currentOffsetBottom;
	private float pentRotation;
	private int pentDirection;
	private float pentDirectionTime;
	private float speed;
	
	@Override
	protected void enter() {
		currentOffsetTop = 0.6f;
		currentOffsetBottom = 0.4f;
		pentRotation = 0;
		pentDirection = 1;
		pentDirectionTime = 2000 + ((new Random()).nextInt(8) * 1000);
		speed = 0.2f;
		
		quad = new Quad(
				new Vertex(0.475f, 0.22f, 1f, 1, 0, 0, 0, 0),
				new Vertex(0.495f, 0.22f + (float) (0.019f * Main.widthHeightRatio()), 1f, 0, 1, 0, 0, 1),
				new Vertex(0.515f, 0.22f + (float) (0.019f * Main.widthHeightRatio()), 1f, 0, 0, 1, 1, 1),
				new Vertex(0.525f, 0.22f, 1f, 0, 0, 0, 1, 0)
		);
		
		quad.applyTexture(ResourceManager.AIR_TEX);
		
		pentagon = new Polygon(
				new Vertex(0.5f, 0.5f, 1f, 0.4f, 0.5f, 1),
				0.05f,
				5
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

		quadVertices[0].setX(currentOffsetTop - 0.02f);
		quadVertices[1].setX(currentOffsetTop - 0.02f);
		quadVertices[2].setX(currentOffsetTop);
		quadVertices[3].setX(currentOffsetTop);
		
		Vertex[] pentVertices = pentagon.vertices();
		Vertex[] pentVerticesNew = Polygon.createVertices(
				pentVertices[0],
				0.05f,
				5,
				pentRotation
		);
		
		for (int i = 0; i < pentVertices.length; i++) {
			pentVertices[i] = pentVerticesNew[i];
		}
		
		Vertex[] quad2Vertices = quad2.vertices();

		quad2Vertices[0].setX(currentOffsetBottom);
		quad2Vertices[1].setX(currentOffsetBottom);
		quad2Vertices[2].setX(currentOffsetBottom + 0.2f);
		quad2Vertices[3].setX(currentOffsetBottom + 0.2f);
		
		quad.resetVertices();
		pentagon.resetVertices();
		quad2.resetVertices();

		currentOffsetTop += (delta * speed / 10000);
		currentOffsetTop %= 1.02;
		
		pentRotation += (delta * speed / 1000) * pentDirection;
		if (pentRotation > (2 * Math.PI)) pentRotation = 0;
		
		pentDirectionTime -= delta;
		if (pentDirectionTime < 0) {
			pentDirection *= -1;
			pentDirectionTime = 2000 + ((new Random()).nextInt(8) * 1000);
		}
		
		currentOffsetBottom -= (delta * speed / 10000);
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
