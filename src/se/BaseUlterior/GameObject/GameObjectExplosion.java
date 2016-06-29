package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactExplosion;

public class GameObjectExplosion extends GameObject {

	protected long timeSinceCreation;
	private ImpactExplosion impactExplosion;

	public GameObjectExplosion(float[] nodes, Color color) {
		super(nodes);
		this.color = color;
		this.timeSinceCreation = System.currentTimeMillis();
		impactExplosion = new ImpactExplosion(this, null);
	}

	public GameObjectExplosion(float[] nodes) {
		super(nodes);
		this.color = Color.yellow;
		impactExplosion = new ImpactExplosion(this, null);
	}

	public GameObjectExplosion() {
	}

	@Override
	public Impact getImpact(GameObjectAgile agileObject) {
		impactExplosion.setAgileObject(agileObject);
		return impactExplosion;
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		impactExplosion.updateUntilGone();
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
