package se.BaseUlterior.Entity.Processed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Bouncable, rotatable, gravitation and time dependent game object
 * 
 * @author Johan Akerlund
 */
public class Grenade extends ImagableObject {

	public static final int GRENADE_SIZE = 60;
	private boolean isReleased = false;

	private static float BOUNCINESS = Constants.GRENADE_BOUNCINESS;
	protected long wasReleasedAt;

	protected final long TIME_UNTIL_EXPLOTION = 3280;

	protected Entity explotionShape = null;

	protected int sizeOfExplostion = Constants.GRENADE_SIZE_OF_EXPLOSION;

	private final float GRENADE_BUMP_EFFECT = 0.9f;

	public Grenade(float[] nodes) {
		super(nodes, BOUNCINESS, "res/img/grenade.png", false);
		color = Color.darkGray;
		setX(Constants.FAKE_START_COORDINATES);
		setY(Constants.FAKE_START_COORDINATES);
		isRotatingObject = true;
		motionLess = false;
	}

	public void initMotion(Vector2 motion) {
		this.motion = motion;
		isReleased = true;
		wasReleasedAt = System.currentTimeMillis();
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (!isReleased) {
			return;
		}
		if (System.currentTimeMillis() - wasReleasedAt > TIME_UNTIL_EXPLOTION) {
			UlteriorUtils.removeGround(getCenterX(), getCenterY(), Constants.WEAPON_EFFECT_GRENADE, sizeOfExplostion,
					GRENADE_BUMP_EFFECT);
			ParallaxPhysicsEngine.objsToRemove.add(this);
		} else {
			super.update(container, delta);
		}

	}

	@Override
	public Impact getImpact(Entity other) {
		boolean selfImpact = other.motionLess ? true : false;
		return new ImpactBounce(this, other, bounciness, selfImpact);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
	}

}
