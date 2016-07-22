package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

/**
 * Game object which getImpact method generates
 * se.BaseUlterior.Physics.ImpactForce
 * 
 * @author Johan Akerlund
 */
public class WorldBuilderForce extends WorldBuilder {

	protected float gravityY;
	protected float gravityX;

	public WorldBuilderForce(float[] nodes, boolean isBackground) {
		super(nodes, isBackground, true, false, false, false, true);
		// super(nodes);
		// isBackgroundObj = isBackground;
		// isSolid = true;
		this.color = new Color(0, 1, 0, 0.19f);
		// invisible = true;
	}

	public WorldBuilderForce(float[] nodes, float gravityX, float gravityY, boolean isBackground) {
		super(nodes, isBackground, true, false, false, false, true);
		// super(nodes);
		// isBackgroundObj = isBackground;
		// isSolid = true;
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = new Color(0, 1, 0, 0.19f);
		// invisible = true;
	}

	public WorldBuilderForce(float[] nodes, float gravityX, float gravityY, Color color, boolean isBackground) {
		super(nodes, isBackground, true, false, false, false, true);
		// super(nodes);
		isBackgroundObj = isBackground;
		// isSolid = true;
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.color = color;
		// invisible = true;
	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactForce(this, piece, gravityX, gravityY);
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

}
