package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactExplosion;
import se.BaseUlterior.Utils.UlteriorUtils;

/*
 * Class not used.
 */

public class GameObjectExplosion extends GameObject {

	private static final long MAX_TIME = 79;
	protected long timeSinceCreation;
	private float power;
	private float bumpEffect;
	private boolean invisible;

	public GameObjectExplosion(float[] nodes, float damage, float bumpEffect, boolean invisible) {
		super(nodes, false, true, false, true, false, false);
		init(damage, bumpEffect);
		piercable = true;
		this.invisible = invisible;
	}

	public GameObjectExplosion(float[] nodes, float damage, float bumpEffect) {
		super(nodes, false, true, false, true, false, false);
		init(damage, bumpEffect);
		piercable = true;
	}

	private void init(float power, float bumpEffect) {
		this.power = power;
		this.color = new Color(1, 0, 0, 0.39f);
		this.timeSinceCreation = System.currentTimeMillis();
		this.bumpEffect = bumpEffect;
	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		return new ImpactExplosion(this, agileObject, power, getBoundingCircleRadius(), bumpEffect);
	}

	@Override
	public void update(GameContainer container, int arg) {
		if (System.currentTimeMillis() - timeSinceCreation > MAX_TIME) {
			UlteriorUtils.cleanUpImpactFromWorldBuilderObject(this);

			IceBreaker.objsToRemove.add(this);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		if (!invisible) {
			graphics.setColor(this.color);
			graphics.fill(this);
		}
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

}
