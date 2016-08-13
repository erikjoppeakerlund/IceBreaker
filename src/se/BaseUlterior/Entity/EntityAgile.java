package se.BaseUlterior.Entity;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

/**
 * Bouncable, gravitation dependent game object. During statefulness searching
 * for applicable impact
 * 
 * @author Johan Akerlund
 */

public abstract class EntityAgile extends Materia {
	private static final long serialVersionUID = 1L;
	protected boolean underImpact = false;

	public EntityAgile(float[] nodes, float bounciness, boolean noImpact) {
		super(nodes, noImpact);
		this.bounciness = bounciness;
		forceUpdate = true;
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		setX(getX() + this.motion.x * delta);
		setY(getY() + this.motion.y * delta);
	}

	@Override
	public Impact getImpact(Entity other) {
		return new ImpactBounce(this, other, bounciness, true);
	}

	public float getBounciness() {
		return this.bounciness;
	}

	public void addImpact(Impact im) {
		currentImpacts.add(im);
	}

}
