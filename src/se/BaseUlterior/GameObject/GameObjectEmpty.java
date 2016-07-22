package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameObjectEmpty extends GameObject {

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub

	}

	public GameObjectEmpty(float[] points) {
		super(points, true, true, false, false, false, true);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		// TODO Auto-generated method stub

	}

}
