package se.BaseUlterior.Entity;

import java.util.Iterator;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Stateful game object which produces
 * se.BaseUlterior.GameObject.GameObjectSplinter instances during
 * 
 * @author Johan Akerlund
 */
public class EntityRicochet extends Entity {

	Entity target = null;

	private int LIFE_SPAN_LIMIT = 32;
	private int LIFE_SPAN_SPLITS = 9;
	private int lifeSpan = 0;
	private float gunFireStartAtX;
	private float gunFireStartAtY;
	private float aimAtX;
	private float aimAtY;
	private Color rayColor = new Color(0.61f, 0.61f, 0.61f);
	private float startX;
	private float startY;
	private final float SIZE_OF_EXPLOSION = 24f;

	public EntityRicochet(Entity target, float gunFireStartAtX, float gunFireStartAtY, float aimAtX, float aimAtY,
			float weight, float damage) {
		super(new float[] { aimAtX, aimAtY }, false, true, false, true, true, true);
		this.target = target;
		this.gunFireStartAtX = gunFireStartAtX;
		this.gunFireStartAtY = gunFireStartAtY;
		this.aimAtX = aimAtX;
		this.aimAtY = aimAtY;
		this.LIFE_SPAN_LIMIT *= weight;
		startX = getX();
		startY = getY();
		UlteriorUtils.removeGroundInvisible(aimAtX, aimAtY, damage, SIZE_OF_EXPLOSION, 0);
		piercable = true;
		if (!target.motionLess) {
			rate = 2;
		}
	}

	private int rate = 5;

	@Override
	public void update(GameContainer container, int arg) {
		if (lifeSpan < LIFE_SPAN_SPLITS && lifeSpan % rate == 0) {
			runEffect();
		}
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			ParallaxPhysicsEngine.objsToRemove.add(this);
		}
		lifeSpan++;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		rayColor.a = 1f - (float) lifeSpan / (float) LIFE_SPAN_LIMIT;
		float newX = getX();
		float newY = getY();
		graphics.setColor(rayColor);
		// graphics.setLineWidth(weight * 2f);
		graphics.drawLine(gunFireStartAtX + newX - startX, gunFireStartAtY + newY - startY, aimAtX + newX - startX,
				aimAtY + newY - startY);
		graphics.resetLineWidth();

	}

	private Set<Vector2> normals = null;

	private void runEffect() {

		normals = target.getMyNormalsAfterHitBy(new EntityEmpty(new Circle(x, y, 59f).getPoints()));
		if (normals.isEmpty()) {
			return;
		}
		Iterator<Vector2> ni = normals.iterator();
		Vector2 N = null;
		int i = 0;
		while (ni.hasNext()) {
			Vector2 no = ni.next();
			if (i < 1) {
				N = no;
			} else {
				N.add(no);
			}
			i++;
		}
		N.normalise();
		Vector2 angled = N.copy();
		N.setTheta(N.getTheta() + Math.random() * 60 - 30);
		float dot = angled.dot(N) * (1.39f);

		N.scale(dot);
		angled.sub(N);
		angled.scale(2f);

		boolean blood = target.motionLess || target.isRotatingObject ? false : true;

		EntitySplinter random = new EntitySplinter(this.getCenterX(), this.getCenterY(), angled, blood);
		ParallaxPhysicsEngine.objsToAdd.add(random);

	}

}
