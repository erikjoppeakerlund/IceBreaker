package se.BaseUlterior.Utils;

import org.newdawn.slick.Color;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuiderForce;

public class UlteriorUtils {
	public static boolean isWithinRange(GameObject go1, GameObject go2) {
		float x1 = go1.getCenterX();
		float x2 = go2.getCenterX();

		float y1 = go1.getCenterY();
		float y2 = go2.getCenterY();

		float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		dist -= go2.getBoundingCircleRadius();
		if (go1.getBoundingCircleRadius() > dist) {
			return true;
		}
		return false;
	}

	public static float angleToPoint(float x1, float y1, float x2, float y2) {
		return (float) Math.atan2(y2 - y1, x2 - x1);
	}

	public static float distance(GameObject go1, GameObject go2) {
		float x1 = go1.getCenterX();
		float x2 = go2.getCenterX();

		float y1 = go1.getCenterY();
		float y2 = go2.getCenterY();

		return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public static void createVisualPointAt(float pointX, float pointY) {
		float fat = 3.0f;
		GameObject point = new WorldBuiderForce(new float[] { pointX - fat, pointY - fat, pointX - fat, pointY + fat,
				pointX + fat, pointY + fat, pointX + fat, pointY - fat }, Color.red);
		BreakingPoint.objsToAdd.add(point);
	}

}
