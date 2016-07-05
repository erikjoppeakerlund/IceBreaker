package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

public class WorldBuiderForce extends WorldBuilder {

	protected float gravityY;
	protected float gravityX;

	public WorldBuiderForce(float[] nodes) {
		super(nodes);
		this.color = Color.gray;
	}

	public WorldBuiderForce(float[] nodes, Color color) {
		super(nodes);
		this.color = color;
	}

	public WorldBuiderForce(float[] nodes, float gravityX, float gravityY) {
		super(nodes);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = Color.gray;
	}

	public WorldBuiderForce(float[] nodes, float gravityX, float gravityY, Color color) {
		super(nodes);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = color;
	}

	@Override
	public Impact getImpact(GameObjectAgile piece) {
		return new ImpactForce(this, piece, gravityY, gravityX);
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

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

}
