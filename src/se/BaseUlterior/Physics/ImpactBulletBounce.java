package se.BaseUlterior.Physics;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Geom.Vector2;

public class ImpactBulletBounce extends Impact {

	private Vector2 direction;
	boolean hasSetRotation = false;

	public ImpactBulletBounce(Entity origin, Entity other, Vector2 direction) {
		super(origin, other);
		this.direction = direction;
	}

	@Override
	public void calculateImpact(int delta) {
		affectedPiece.add(direction);

		if (other.isRotatingObject && !hasSetRotation) {
			Vector2 fromHitSpot = new Vector2(origin.getCenterX() - other.getCenterX(),
					origin.getCenterY() - other.getCenterY());
			other.rotation = -(fromHitSpot.x * direction.y - fromHitSpot.y * direction.x) * 0.399f;
			hasSetRotation = true;
		}
	}

}
