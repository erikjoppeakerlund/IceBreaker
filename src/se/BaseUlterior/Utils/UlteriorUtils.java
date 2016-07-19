package se.BaseUlterior.Utils;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectExplosion;
import se.BaseUlterior.GameObject.WorldBuilderForce;
import se.BaseUlterior.GameObject.WorldBuilderGround;

public class UlteriorUtils {
	public static boolean isWithinRange(GameObject go1, GameObject go2) {
		float x1 = go1.getCenterX();
		float x2 = go2.getCenterX();

		float y1 = go1.getCenterY();
		float y2 = go2.getCenterY();

		float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		dist -= go2.maxRadiusStart;
		if (go1.maxRadiusStart > dist) {
			return true;
		}
		return false;
	}

	public static float angleToPoint(float x1, float y1, float x2, float y2) {
		return (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
	}

	public static int distance(GameObject go1, GameObject go2) {
		float x1 = go1.getCenterX();
		float x2 = go2.getCenterX();

		float y1 = go1.getCenterY();
		float y2 = go2.getCenterY();

		return (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public static void createVisualPointAt(float pointX, float pointY) {
		float fat = 3.0f;
		GameObject point = new WorldBuilderForce(new float[] { pointX - fat, pointY - fat, pointX - fat, pointY + fat,
				pointX + fat, pointY + fat, pointX + fat, pointY - fat });
		BreakingPoint.objsToAdd.add(point);
	}

	public static void cleanUpImpactFromWorldBuilderObject(GameObject clearWhichBelongToThis) {
		for (GameObject go : BreakingPoint.all) {
			go.removeCurrentImpactsWhichBelingTo(clearWhichBelongToThis);
		}
	}

	/*
	 * Not current used, due to game play decision.
	 */
	public static void removeGround(float x, float y, float sizeOfExplostion, GameObject origin) {
		GameObject explotionShape = new GameObjectExplosion(new Circle(x, y, sizeOfExplostion, 8).getPoints());
		BreakingPoint.objsToAdd.add(explotionShape);
		ArrayList<GameObject> newShapes = new ArrayList<>();
		boolean wasFound = false;
		for (GameObject target : BreakingPoint.all) {
			if (target == origin || target == origin) {
				continue;
			}
			if (UlteriorUtils.isWithinRange(target, explotionShape)) {
				if (target.intersects(explotionShape)) {
					Shape[] result = target.subtract(explotionShape);
					if (result.length > 0) {
						for (int i = 0; i < result.length; i++) {
							Shape go = result[i];
							GameObject gog = new WorldBuilderGround(go.getPoints());
							newShapes.add(gog);
							wasFound = true;
						}

						BreakingPoint.objsToRemove.add(target);
						UlteriorUtils.cleanUpImpactFromWorldBuilderObject(target);
						// for (GameObject anySort : BreakingPoint.all) {
						// if (anySort.isSolid()) {
						// // BreakingPoint.addOnTop(anySort);
						// // BreakingPoint.addOnTop(BreakingPoint.info);
						// }
						// }
					}
				}
			}
			if (wasFound) {
				break;
			}
		}
		for (GameObject go : newShapes) {
			BreakingPoint.objsToAdd.add(go);
		}

	}

	public static void removeGroundReal(float x, float y, float sizeOfExplostion, GameObject origin) {
		GameObject explotionShape = new GameObjectExplosion(new Circle(x, y, sizeOfExplostion).getPoints());
		BreakingPoint.objsToAdd.add(explotionShape);
	}
}
