package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * GamObject which should represent a splinter from a ricochet
 * 
 * @author Johan Akerlund
 */
public class EntitySplinter extends EntityAgile {
	private final float LIFE_SPAN_LIMIT = 29;
	private float lifeSpan = 0;

	public EntitySplinter(float x, float y, Vector2 motion, boolean blood) {
		super(new Circle(x, y, 2f + (float) (Math.random() * 6f)).getPoints(), 1.0f);
		this.motion = motion;
		forceUpdate = true;
		piercable = true;
		motionLess = true;
		if (blood) {
			motion.scale(0.2f);
			color = new Color((float) (Math.random() * 0.7f), 0f, 0f);
		} else {
			color = Color.gray;
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(color);
		// graphics.setLineWidth(4);
		graphics.fill(this);
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			ParallaxPhysicsEngine.objsToRemove.add(this);
		}
		lifeSpan++;
	}

}
