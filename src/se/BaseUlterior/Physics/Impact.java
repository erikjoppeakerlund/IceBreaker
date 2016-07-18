package se.BaseUlterior.Physics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public abstract class Impact {

	protected GameObject origin;
	protected GameObject other;
	protected Vector2 affectedPiece;

	protected int diff;

	public Impact(GameObject origin, GameObject other) {
		this.origin = origin;
		this.other = other;
		affectedPiece = other.getMotion();
		diff = UlteriorUtils.distance(origin, other);
	}

	public GameObject getTrigger() {
		return origin;
	}

	public void checkCalculate(int delta) {
		if (mightQuitMethod()) {
			return;
		}
		calculateImpact(delta);
	}

	public GameObject getAffected() {
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
