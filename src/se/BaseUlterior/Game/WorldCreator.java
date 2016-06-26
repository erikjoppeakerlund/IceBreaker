package se.BaseUlterior.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.GameObject.WorldBuilder;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.Utils.DoubleClickListener;

public class WorldCreator implements DoubleClickListener {

	private WorldBuilder wb = null;

	public WorldCreator() {
		wb = new WorldBuilderGround(new float[0], Color.black);
	}

	public void update(GameContainer container, int arg) throws SlickException {

		Input in = container.getInput();

		if (in.isMouseButtonDown(in.MOUSE_LEFT_BUTTON)) {
			wb.addPoint(in.getMouseX(), in.getMouseY());
		}

	}

	@Override
	public void wasDoubleClicked(int button, int x, int y) {

	}
}
