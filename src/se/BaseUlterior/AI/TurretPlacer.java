package se.BaseUlterior.AI;

import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Geom.Vector2;

/**
 * @author Johan Akerlund
 */

public class TurretPlacer {

	float turretsDistance;

	public TurretPlacer() {
		turretsDistance = Constants.TURRETS_DISTANCE;
	}

	int nrOfTurrets = 0;

	public void placeTurretsOnMe(GameObject groundPiece) {

		float[] points = groundPiece.getPoints();
		Vector2 direction = new Vector2();
		float beyond = 0;
		for (int i = 0; i < points.length - 2; i += 2) {
			float fullLength = (float) Math
					.sqrt(Math.pow(points[i + 2] - points[i], 2) + Math.pow(points[i + 3] - points[i + 1], 2));
			int nrOfTurretsThis = (int) (fullLength / turretsDistance);
			if (beyond < fullLength) {
				direction.set(points[i + 2] - points[i], points[i + 3] - points[i + 1]);
				direction.normalise();
				for (int j = 0; j < nrOfTurretsThis || j == 0; j++) {
					float turretX = points[i] + (turretsDistance * j + beyond) * direction.x;
					float turretY = points[i + 1] + (turretsDistance * j + beyond) * direction.y;

					AITurretBulletLauncher turretInLoop = new AITurretBulletLauncher(
							new Circle(turretX, turretY, 60).getPoints(), direction.getPerpendicular());
					IceBreaker.objsToAdd.add(turretInLoop);
					nrOfTurrets++;
				}
				beyond = turretsDistance - fullLength % turretsDistance;
			} else {
				beyond -= fullLength;
			}
		}
		IceBreaker.nrOfTurrets = nrOfTurrets;
	}
}
