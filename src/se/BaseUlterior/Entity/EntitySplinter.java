package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactFriction;

/**
 * GamObject which should represent a splinter from a ricochet
 * 
 * @author Johan Akerlund
 */
public class EntitySplinter extends Entity {
	private final float LIFE_SPAN_LIMIT = 29;
	private float lifeSpan = 0;
	private final float RESCALE = 0.2f;

	public EntitySplinter(float x, float y, Vector2 motion, boolean blood) {
		// super(new Circle(x, y, 2f + (float) (Math.random() *
		// 6f)).getPoints(), 1.0f);

		super(new Circle(x, y, 2f + (float) (Math.random() * 6f)).getPoints(), false, false, false, false, false, true);

		this.motion = motion;
		forceUpdate = true;
		piercable = true;
		motionLess = true;
		if (blood) {
			motion.scale(RESCALE);
			color = new Color((float) (Math.random() * 0.7f), 0f, 0f);
		} else {
			color = Color.darkGray;
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(color);
		graphics.fill(this);
	}

	@Override
	public void update(GameContainer container, int delta) {

		setX(getX() + this.motion.x * delta);
		setY(getY() + this.motion.y * delta);
		// super.update(container, delta);
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			ParallaxPhysicsEngine.objsToRemove.add(this);
		}
		lifeSpan++;
	}

	@Override
	public Impact getImpact(Entity other) {
		return new ImpactFriction(this, other, Constants.SPLINTER_FRICTION);
	}
}
