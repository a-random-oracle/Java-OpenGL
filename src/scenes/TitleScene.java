package scenes;

import org.lwjgl.input.Keyboard;

import main.Scene;
import main.SceneManager;
import static main.Graphics.*;

public class TitleScene extends Scene {

	@Override
	protected void enter() {}

	@Override
	protected void update(int delta) {}

	@Override
	protected void render() {
		drawRect(CYAN, 200, 0.25, 0.25, 0.5, 0.5);
		drawStringCentred(GREEN, "Hello", 0.5, 0.8);
	}

	@Override
	protected void mousePress(int button, int mx, int my) {
		
	}

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
	protected void exit() {}

}
