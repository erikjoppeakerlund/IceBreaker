package se.BaseUlterior.Physics;

import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;

/**
 * Impact which affect the another game objects' motion vector based on either
 * it's own shape or the other game objects' shape
 * 
 * @author Johan Akerlund
 */
public class ImpactBounce extends Impact {

	protected Set<Vector2> normals = null;
	protected Set<Vector2> normalsTester = null;
	protected float bounciness;
	protected boolean self;
	protected Vector2 temp;

	// protected boolean restart = false;

	public ImpactBounce(Entity origin, Entity go, float bounciness) {
		super(origin, go);
		normals = other.getMyNormalsAfterHitBy(origin);

		this.bounciness = bounciness;
	}

	@Override
	public void calculateImpact(int delta) {
		normalsTester = other.getMyNormalsAfterHitBy(origin);
		if (normalsTester.isEmpty()) {
			return;
		}
		Iterator<Vector2> ni = normalsTester.iterator();
		Vector2 N = null;
		int i = 0;
		while (ni.hasNext()) {
			Vector2 no = ni.next();
			if (i < 1) {
				N = no;
			} else {
				N.add(no).normalise();
			}
			i++;
		}

		N.normalise();
		if (other.isRotatingObject) {
			other.rotation = N.dot(affectedPiece);
		}
		boolean invert = false;
		if (N.dot(affectedPiece) < 0) {
			invert = true;
		}

		/*
		 * using the algorithm: V� = V - (2*(V . N)) * N
		 * 
		 * ...where N is the normal of the hit surface, V is the moving particle
		 * ('affectedPiece'), V� is the resulting vector
		 */
		float dot = affectedPiece.dot(N) * (1.0f + bounciness);

		N.scale(dot);
		if (invert) {
			affectedPiece.add(N);
		} else {
			affectedPiece.sub(N);
		}
	}

	@Override
	public void checkCalculate(int delta) {
		if (mightQuitMethod()) {
			return;
		}
		if (origin.intersects(other)) {
			calculateImpact(delta);
		}
	}

}
