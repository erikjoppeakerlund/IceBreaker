package se.BaseUlterior.Physics;

import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.Config.Constants;
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

	public ImpactBounce(Entity origin, Entity go, float bounciness, boolean self) {
		super(origin, go);
		if (self) {
			affectedPiece = origin.getMotion();
		}
		this.self = self;
		normals = origin.getMyNormalsAfterHitBy(other);
		this.bounciness = bounciness;
	}

	@Override
	public void calculateImpact(int delta) {
		if (!origin.intersects(other)) {
			return;
		}
		if (self) {
			normalsTester = origin.getMyNormalsAfterHitBy(other);
		} else {
			normalsTester = other.getMyNormalsAfterHitBy(origin);
		}

		if ((!normalsTester.isEmpty())) {
			normals = normalsTester;
		}
		if (normals.isEmpty()) {
			return;
		}
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
		if (other.isRotatingObject) {
			other.rotation = N.dot(other.getMotion());
		}

		if (N.dot(affectedPiece.copy().normalise()) < 0) {
			return;
		}

		/*
		 * using the algorithm: V´ = V - (2*(V . N)) * N
		 * 
		 * ...where N is the normal of the hit surface, V is the moving particle
		 * ('affectedPiece'), Vï¿½ is the resulting vector
		 */
		float dot = affectedPiece.dot(N) * (1.0f + bounciness);

		N.scale(dot);
		affectedPiece.scale(Constants.GROUND_FRICTION);
		affectedPiece.sub(N);
	}

}
