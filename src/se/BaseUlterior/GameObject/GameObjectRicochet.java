package se.BaseUlterior.GameObject;

import java.util.Iterator;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;

/**
 * Stateful game object which produces
 * se.BaseUlterior.GameObject.GameObjectSplinter instances during
 * 
 * @author Johan Akerlund
 */
public class GameObjectRicochet extends GameObject {

	GameObject target = null;

	private int LIFE_SPAN_LIMIT = 40;
	private int LIFE_SPAN_SPLITS = 9;
	private int lifeSpan = 0;
	private float gunFireStartAtX;
	private float gunFireStartAtY;
	private float aimAtX;
	private float aimAtY;
	private Color rayColor = new Color(0.61f, 0.61f, 0.61f);
	private float weight;
	private float startX;
	private float startY;

	public GameObjectRicochet(float[] fs, GameObject target, float gunFireStartAtX, float gunFireStartAtY, float aimAtX,
			float aimAtY, float weight) {
		super(fs);
		this.target = target;
		this.gunFireStartAtX = gunFireStartAtX;
		this.gunFireStartAtY = gunFireStartAtY;
		this.aimAtX = aimAtX;
		this.aimAtY = aimAtY;
		this.weight = weight;
		this.LIFE_SPAN_LIMIT *= weight;
		startX = getX();
		startY = getY();
		isBackgroundObj = true;

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		if (lifeSpan < LIFE_SPAN_SPLITS && lifeSpan % 2 == 0) {
			runEffect();
		}
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			IceBreaker.objsToRemove.add(this);
		}
		lifeSpan++;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		rayColor.a = 1f - (float) lifeSpan / (float) LIFE_SPAN_LIMIT;
		graphics.setColor(rayColor);
		graphics.setLineWidth(weight * 2f);
		float newX = getX();
		float newY = getY();
		graphics.drawLine(gunFireStartAtX + newX - startX, gunFireStartAtY + newY - startY, aimAtX + newX - startX,
				aimAtY + newY - startY);

	}

	private Set<Vector2> normals = null;

	private void runEffect() {

		normals = target.getMyNormalsAfterHitBy(this);
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
		N.setTheta(N.getTheta() + Math.random() * 46 - 23);
		float dot = angled.dot(N) * (2.0f);

		N.scale(dot);
		angled.sub(N);

		GameObjectSplinter random = new GameObjectSplinter(this.getCenterX(), this.getCenterY(), angled);
		IceBreaker.objsToAdd.add(random);

	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		// return new ImpactRicochet(target, this);
		return null;
	}

}
