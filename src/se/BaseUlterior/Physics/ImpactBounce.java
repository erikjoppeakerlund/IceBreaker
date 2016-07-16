package se.BaseUlterior.Physics;

import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBounce extends Impact {

	protected Set<Vector2> normals = null;
	protected Set<Vector2> normalsTester = null;
	protected float bouncyness;
	protected boolean self;
	protected boolean reset = false;

	public ImpactBounce(GameObject origin, GameObject go, float bouncyness, boolean self) {
		super(origin, go);
		if (self) {
			affectedPiece = origin.getMotion();
		}
		this.self = self;
		normals = origin.getMyNormalsAfterHitBy(other);
		this.bouncyness = bouncyness;
	}

	@Override
	public void calculateImpact(int delta) {
		reset = true;
		if (!origin.intersects(other) || !other.intersects(origin)) {
			origin.noForce = false;
			other.noForce = false;
			return;
		}
		normalsTester = origin.getMyNormalsAfterHitBy(other);
		if ((!normalsTester.isEmpty() || reset) && !(self && (origin.noForce))) {
			normals = normalsTester;
		}
		if (normals.isEmpty()) {
			return;
		}
		reset = false;
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

		if (N.dot(affectedPiece.copy().normalise()) < 0) {
			return;
		}

		/*
		 * using the algorithm: V� = V - (2*(V . N)) * N
		 * 
		 * ...where N is the normal of the hit surface, V is the moving particle
		 * ('affectedPiece'), V� is the resulting vector
		 */

		float dot = affectedPiece.dot(N) * (1.0f + bouncyness);

		N.scale(dot);
		if (!(self & origin.noForce)) {
			affectedPiece.sub(N);
		}
	}

	@Override
	public void onReset() {
		other.noForce = false;
		origin.noForce = false;
	}

}
