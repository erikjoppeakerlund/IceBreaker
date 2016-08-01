package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

/**
 * Game object which getImpact method generates
 * se.BaseUlterior.Physics.ImpactForce
 * 
 * @author Johan Akerlund
 */
public class PartForce extends Entity {

	protected float gravityY;
	protected float gravityX;

	public PartForce(float[] nodes, float gravityX, float gravityY) {
		super(nodes, false, true, false, false, false, true);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = new Color(0, 0, 1, 0.19f);
	}

	public PartForce(float[] nodes, float gravityX, float gravityY, Color color) {
		super(nodes, false, true, false, false, false, true);
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = color;
	}

	@Override
	public Impact getImpact(Entity piece) {
		return new ImpactForce(this, piece, gravityX, gravityY);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	@Override
	public void update(GameContainer container, int arg) {
		// TODO Auto-generated method stub

	}

}
