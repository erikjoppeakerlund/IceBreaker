package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public abstract class WorldBuilder extends GameObject {

	public WorldBuilder(float[] nodes) {
		super(nodes);

	}

	protected abstract void animateHit();

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
	}

	@Override
	public Impact getImpact(GameObjectFalling piece) {
		return new ImpactBounce(this, piece);
	}
}
