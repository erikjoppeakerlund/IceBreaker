package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.Geom.Vector2;

public class Plasma extends GameObjectFalling {

	private boolean hitWall = false;

	public Plasma() {
		super(new Circle(-80, -80, 30).getPoints(), 0.0f);
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (!hitWall) {
			super.update(container, delta);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.orange);
		graphics.fill(this);
	}

	public void initMotion(Vector2 motion) {
		this.motion = motion;
	}

}
