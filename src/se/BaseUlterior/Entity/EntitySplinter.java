package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * GamObject which should represent a splinter from a ricochet
 * 
 * @author Johan Akerlund
 */
public class EntitySplinter extends Entity {
	private final float LIFE_SPAN_LIMIT = 19;
	private float lifeSpan = 0;
	private final float RESCALE = 0.29f;
	private float[] tail;
	private float startX;
	private float startY;
	private Color tailColor;

	public EntitySplinter(float x, float y, Vector2 motion, boolean blood) {
		super(new Circle(x, y, 2f + (float) (Math.random() * 6f)).getPoints(), false, false, false, false, false, true,
				true);
		this.motion = motion;
		forceUpdate = true;
		piercable = true;
		motionLess = true;
		if (blood) {
			motion.scale(RESCALE);
			color = Color.red;
			tailColor = new Color((float) (Math.random() * 0.7f), 0f, 0f);
		} else {
			tailColor = new Color(0.7f, 0.7f, 0.7f, 1.0f);
			color = Color.white;
		}
		tail = new float[(int) LIFE_SPAN_LIMIT * 2];
		startX = getX();
		startY = getY();

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(color);
		graphics.fill(this);

		graphics.setLineWidth(13);

		for (int i = 0; i < lifeSpan - 3; i += 2) {
			tailColor.a = 1f - (float) i / LIFE_SPAN_LIMIT;
			graphics.setColor(tailColor);
			graphics.drawLine(getX() - tail[i], getY() - tail[i + 1], getX() - tail[i + 2], getY() - tail[i + 3]);
		}
	}

	@Override
	public void update(GameContainer container, int delta) {

		setX(getX() + this.motion.x * delta);
		setY(getY() + this.motion.y * delta);
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			UlteriorUtils.cleanUpImpactFromWorldBuilderObject(this);
			ParallaxPhysicsEngine.objsToRemove.add(this);
		} else if (lifeSpan < LIFE_SPAN_LIMIT - 1) {
			float currentX = getX();
			float currentY = getY();
			float diffX = currentX - startX;
			float diffY = currentY - startY;
			tail[(int) (lifeSpan * 2)] = diffX;
			tail[(int) (lifeSpan * 2) + 1] = diffY;
		}

		lifeSpan++;

	}

	@Override
	public Impact getImpact(Entity other) {
		System.out.println("From EntitySplinter, this shouldn't appear :/");
		return super.getImpact(other);
	}
}
