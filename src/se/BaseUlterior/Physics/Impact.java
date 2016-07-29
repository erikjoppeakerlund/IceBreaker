package se.BaseUlterior.Physics;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Any sort of impact
 * 
 * @author Johan Akerlund
 */
public abstract class Impact {

	protected Entity origin;
	protected Entity other;
	protected Vector2 affectedPiece;

	protected int diff;

	public Impact(Entity origin, Entity other) {
		this.origin = origin;
		this.other = other;
		affectedPiece = other.getMotion();
		diff = UlteriorUtils.distance(origin, other);
	}

	public Entity getTrigger() {
		return origin;
	}

	public void checkCalculate(int delta) {
		if (mightQuitMethod()) {
			return;
		}
		calculateImpact(delta);
	}

	public Entity getAffected() {
		return other;
	}

	public abstract void calculateImpact(int delta);

	protected boolean mightQuitMethod() {
		if (UlteriorUtils.distance(origin, other) == diff) {
			return true;
		}
		return false;
	}

}
