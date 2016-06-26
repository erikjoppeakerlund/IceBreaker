package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

public class GameObjectExplosion extends GameObject {

	protected long timeSinceCreation;
	protected final long TIME_UNTIL_GONE = 90;

	public GameObjectExplosion(float[] nodes, Color color) {
		super(nodes);
		this.color = color;
		this.timeSinceCreation = System.currentTimeMillis();
		// TODO Auto-generated constructor stub
	}

	public GameObjectExplosion(float[] nodes) {
		super(nodes);
		// TODO Auto-generated constructor stub
	}

	public GameObjectExplosion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Impact getImpact(GameObjectAgile agileObject) {
		// TODO Auto-generated method stub
		return new ImpactForce(this, agileObject, 0.0f, 0.0f);
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		if (System.currentTimeMillis() - timeSinceCreation > TIME_UNTIL_GONE) {
			System.out.println("hejjjj");
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
