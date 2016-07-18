package se.BaseUlterior.Physics;

import java.util.Iterator;
import java.util.Set;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectSplit;
import se.BaseUlterior.Geom.Vector2;

public class ImpactRicochet extends Impact {

	protected Set<Vector2> normals = null;

	public ImpactRicochet(GameObject origin, GameObject other) {
		super(origin, other);
		normals = origin.getMyNormalsAfterHitBy(other);
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
		Vector2 angled = N.copy();
		N.setTheta(N.getTheta() + Math.random() * 40 - 20);
		float dot = angled.dot(N) * (2.0f);

		N.scale(dot);
		angled.sub(N);

		GameObjectSplit random = new GameObjectSplit(other.getCenterX(), other.getCenterY(), angled);
		BreakingPoint.objsToAdd.add(random);

	}

	@Override
	public void calculateImpact(int delta) {

	}

}
