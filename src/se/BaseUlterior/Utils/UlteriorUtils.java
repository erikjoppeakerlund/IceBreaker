package se.BaseUlterior.Utils;

import se.BaseUlterior.GameObject.GameObject;

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
}
