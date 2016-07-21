package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Bouncable, rotatable, gravitation and time dependent game object
 * 
 * @author Johan Akerlund
 */
public class Grenade extends ImagableObject {

	public static final int GRENADE_SIZE = 64;
	private boolean isReleased = false;

	private static float BOUNCYNESS = 1.3f;
	protected long wasReleasedAt;

	protected final long TIME_UNTIL_EXPLOTION = 2500;

	protected GameObject explotionShape = null;

	protected int sizeOfExplostion = 319;

	public Grenade(float[] nodes) {
		super(nodes, BOUNCYNESS, "res/img/grenade.png");
		// color = Constants.THEME_COLOR;
		color = Color.darkGray;
		setX(-270f);
		setY(-270f);
		isRotatingObject = true;
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
			UlteriorUtils.removeGround(getCenterX(), getCenterY(), sizeOfExplostion, this);
			IceBreaker.objsToRemove.add(this);
		} else {
			super.update(container, delta);
		}

	}

	@Override
	public Impact getImpact(GameObject other) {
		if (!other.isBackgroundObj) {
			return new ImpactBounce(this, other, bouncyness, true);
		}
		return null;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
	}
}
