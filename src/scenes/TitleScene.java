package scenes;

import org.lwjgl.input.Keyboard;

import utilities.FadeString;
import utilities.FadeString.FadeDirection;
import main.Scene;
import main.SceneManager;
import static utilities.Graphics.*;

public class TitleScene extends Scene {
	
	/** The list of titles to draw */
	private static final FadeString[] titleTextArray = new FadeString[] {
		new FadeString("Team GOA Presents ...", FadeDirection.FADE_IN,
				1000, Align.CENTRE, BLUE, 0.5, 0.5),
		new FadeString("Fly Hard", FadeDirection.FADE_IN,
				1000, Align.CENTRE, BLUE, 0.5, 0.5),
	};

	/** The current title text stage */
	private int titleTextStage;

	@Override
	protected void enter() {
		titleTextStage = 0;
	}

	@Override
	protected void update(int delta) {
		titleTextArray[titleTextStage].update(delta);
		
		if (titleTextArray[titleTextStage].duration() > 3000) {
			titleTextStage++;
		}
	}

	@Override
	protected void render() {
		titleTextArray[titleTextStage].render();
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
	protected void exit() {}

}
