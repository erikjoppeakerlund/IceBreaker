package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactExplosion;

public class GameObjectExplosion extends GameObject {

	private static final long MAX_TIME = 69;
	protected long timeSinceCreation;
	private ImpactExplosion impactExplosion;

	public GameObjectExplosion(float[] nodes, Color color) {
		super(nodes);
		this.color = color;
		this.timeSinceCreation = System.currentTimeMillis();
	}

	public GameObjectExplosion(float[] nodes) {
		super(nodes);
		this.color = Color.red;
		this.timeSinceCreation = System.currentTimeMillis();
	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		// impactExplosion = new ImpactExplosion(this, agileObject);
		// return impactExplosion;
		return null;
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		if (System.currentTimeMillis() - timeSinceCreation > MAX_TIME) {
			if (impactExplosion != null) {
				impactExplosion.disappear();
			}
			BreakingPoint.objsToRemove.add(this);
		}
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
