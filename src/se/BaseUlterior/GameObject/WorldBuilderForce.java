package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

public class WorldBuilderForce extends WorldBuilder {

	protected float gravityY;
	protected float gravityX;

	public WorldBuilderForce(float[] nodes) {
		super(nodes);
		this.color = new Color(0, 1, 0, 0.19f);
	}

	public WorldBuilderForce(float[] nodes, float gravityX, float gravityY) {
		super(nodes);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = new Color(0, 1, 0, 0.19f);
	}

	public WorldBuilderForce(float[] nodes, float gravityX, float gravityY, Color color) {
		super(nodes);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = color;
	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactForce(this, piece, gravityX, gravityY);
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

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

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBackgroundObj() {
		return true;
	}

}
