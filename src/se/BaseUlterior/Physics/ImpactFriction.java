package se.BaseUlterior.Physics;

import se.BaseUlterior.Entity.Entity;

/**
 * Scale other game objects motion vector
 * 
 * @author Johan Akerlund
 */
public class ImpactFriction extends Impact {

	protected float effect;
	protected boolean contains = false;

	public ImpactFriction(Entity origin, Entity other, float effect) {
		super(origin, other);
		this.effect = effect;
	}

	@Override
	public void calculateImpact(int delta) {
		if (origin.contains(other)) {
			if (other.isRotatingObject) {
				other.rotation *= effect;
			}
			affectedPiece.scale(effect);
		}
	}

}
