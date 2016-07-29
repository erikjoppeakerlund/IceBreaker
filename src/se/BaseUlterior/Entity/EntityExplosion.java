package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactExplosion;
import se.BaseUlterior.Utils.UlteriorUtils;

/*
 * Class not used.
 */

public class EntityExplosion extends Entity {

	private static final long MAX_TIME = 79;
	protected long timeSinceCreation;
	private float power;
	private float bumpEffect;
	private boolean invisible;

	public EntityExplosion(float[] nodes, float damage, float bumpEffect, boolean invisible) {
		super(nodes, false, true, false, true, false, false);
		init(damage, bumpEffect);
		piercable = true;
		this.invisible = invisible;
	}

	public EntityExplosion(float[] nodes, float damage, float bumpEffect) {
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
	public Impact getImpact(Entity agileObject) {
		return new ImpactExplosion(this, agileObject, power, getBoundingCircleRadius(), bumpEffect);
	}

	@Override
	public void update(GameContainer container, int arg) {
		if (System.currentTimeMillis() - timeSinceCreation > MAX_TIME) {
			UlteriorUtils.cleanUpImpactFromWorldBuilderObject(this);

			ParallaxPhysicsEngine.objsToRemove.add(this);
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
