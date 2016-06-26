package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Impact;

public class GameObjectSimple extends GameObject {

	public GameObjectSimple(float[] nodes) {
		super(nodes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Impact getImpact(GameObjectAgile agileObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

}
