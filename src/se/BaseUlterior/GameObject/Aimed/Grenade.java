package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;
import se.BaseUlterior.Utils.UlteriorUtils;

public class Grenade extends GameObjectFalling {

	public static final int GRENADE_SIZE = 30;
	private boolean isReleased = false;

	private static float BOUNCYNESS = 0.9f;
	protected long wasReleasedAt;

	protected final long TIME_UNTIL_EXPLOTION = 2500;

	protected GameObject explotionShape = null;

	protected int sizeOfExplostion = 210;

	public Grenade(float[] nodes) {
		super(nodes, BOUNCYNESS);
		// color = Constants.THEME_COLOR;
		color = Color.darkGray;
		setX(-70f);
		setY(-70f);
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
			BreakingPoint.objsToRemove.add(this);
		} else {
			super.update(container, delta);
		}

	}

	@Override
	public Impact getImpact(GameObject other) {
		return new ImpactBounce(this, other, -bouncyness, true);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		color.a = 1;
		super.render(container, graphics);
		graphics.setColor(Color.white);
		graphics.draw(this);
	}

}
