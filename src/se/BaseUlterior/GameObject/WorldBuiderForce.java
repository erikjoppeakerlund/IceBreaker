package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

public class WorldBuiderForce extends WorldBuilder {

	protected float gravityY;
	protected float gravityX;

	public WorldBuiderForce(float[] nodes, float gravityX, float gravityY, Color color) {
		super(nodes);
		this.color = color;
		this.gravityX = gravityX;
		this.gravityY = gravityY;
	}

	@Override
	public Impact getImpact(GameObjectAgile piece) {
		return new ImpactForce(this, gravityY, gravityX);
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub
		super.update(container, arg);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

}
