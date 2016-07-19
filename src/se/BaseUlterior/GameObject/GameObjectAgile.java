package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

/**
 * Bouncable, gravitation dependend game object. During statefulness searching
 * for applicable impact
 * 
 * @author Johan Akerlund
 */

public abstract class GameObjectAgile extends WorldBuilderMateriaFirm {
	protected boolean underImpact = false;

	public GameObjectAgile(float[] nodes, float bouncyness) {
		super(nodes);
		this.bouncyness = bouncyness;
		noForce = false;
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		setCenterX(getCenterX() + this.motion.getX() * delta);
		setCenterY(getCenterY() + this.motion.getY() * delta);
	}

	@Override
	public Impact getImpact(GameObject other) {
		return new ImpactBounce(this, other, bouncyness, true);
	}

	public float getBouncyness() {
		return this.bouncyness;
	}
	//
	// @Override
	// public void render(GameContainer container, Graphics graphics) throws
	// SlickException {
	// graphics.setColor(this.color);
	// graphics.fill(this);
	// }

	public void addImpact(Impact im) {
		currentImpacts.add(im);
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	// public void removeCurrentImpactsWhichBelingTo(GameObject go) {
	// for (Impact im : currentImpacts) {
	// if (im.getTrigger() == go || im.getAffected() == go) {
	// removeImpact(im);
	// }
	// }
	// }

}
