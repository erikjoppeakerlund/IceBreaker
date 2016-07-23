package se.BaseUlterior.AI;

import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

public class TurretPlacer {

	float turretsDistance;

	public TurretPlacer() {
		turretsDistance = Constants.TURRETS_DISTANCE;

	}

	public void placeTurretsOnMeReal(GameObject groundPiece) {

		float[] points = groundPiece.getPoints();
		Vector2 direction = new Vector2();
		for (int i = 0; i < points.length - 2; i += 2) {
			float fullLength = (float) Math
					.sqrt(Math.pow(points[i + 2] - points[i], 2) + Math.pow(points[i + 3] - points[i + 1], 2));
			// float beyond = turretsDistance - fullLength % turretsDistance;
			int nrOfTurretsThis = (int) (fullLength / turretsDistance);
			direction.set(points[i + 2] - points[i], points[i + 3] - points[i + 1]);
			direction.normalise();
			for (int j = 0; j < nrOfTurretsThis; j++) {
				float turretX = points[i] + (turretsDistance) * direction.x;
				float turretY = points[i + 1] + (turretsDistance) * direction.y;
				AITurretVectorShotLauncher turret = new AITurretVectorShotLauncher(
						new Circle(turretX, turretY, 40).getPoints(), direction);
				IceBreaker.objsToAdd.add(turret);
			}
		}
	}

	public void placeTurretsOnMe(GameObject groundPiece) {

		float[] points = groundPiece.getPoints();
		Vector2 direction = new Vector2();
		for (int i = 0; i < points.length - 2; i += 2) {
			float fullLength = (float) Math
					.sqrt(Math.pow(points[i + 2] - points[i], 2) + Math.pow(points[i + 3] - points[i + 1], 2));
			// float beyond = turretsDistance - fullLength % turretsDistance;
			int nrOfTurretsThis = (int) (fullLength / turretsDistance);
			direction.set(points[i + 2] - points[i], points[i + 3] - points[i + 1]).getPerpendicular();
			direction.normalise();
			for (int j = 0; j < nrOfTurretsThis; j++) {
				float turretX = points[i] + (turretsDistance) * direction.x;
				float turretY = points[i + 1] + (turretsDistance) * direction.y;

				AITurretVectorShotLauncher turret = new AITurretVectorShotLauncher(
						new Circle(turretX, turretY, 140).getPoints(), direction);
				IceBreaker.objsToAdd.add(turret);

			}
		}
	}
}
