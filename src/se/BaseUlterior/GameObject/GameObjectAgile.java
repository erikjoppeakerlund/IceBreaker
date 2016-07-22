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

public abstract class GameObjectAgile extends MateriaFirm {
	protected boolean underImpact = false;

	public GameObjectAgile(float[] nodes, float bouncyness) {
		super(nodes);
		this.bouncyness = bouncyness;
		forceUpdate = true;
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

	public void addImpact(Impact im) {
		currentImpacts.add(im);
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

}
