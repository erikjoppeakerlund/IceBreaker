package se.BaseUlterior.Utils;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectExplosion;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.GameObject.WorldBuiderForce;
import se.BaseUlterior.GameObject.WorldBuilderGround;

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
				pointX + fat, pointY + fat, pointX + fat, pointY - fat }, Color.green);
		BreakingPoint.objsToAdd.add(point);
	}

	public static void cleanUpImpactFromWorldBuilderObject(GameObject clearWhichBelongToThis) {
		for (GameObjectFalling falling : BreakingPoint.allFalliing) {
			falling.removeCurrentImpactsWhichBelingTo(clearWhichBelongToThis);
		}
	}

	public static void removeGround(float x, float y, float sizeOfExplostion, GameObject origin) {
		GameObject explotionShape = new GameObjectExplosion(new Circle(x, y, sizeOfExplostion).getPoints());
		BreakingPoint.objsToAdd.add(new GameObjectExplosion(new Circle(x, y, sizeOfExplostion).getPoints()));
		ArrayList<GameObject> newShapes = new ArrayList<>();
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
						}
						UlteriorUtils.cleanUpImpactFromWorldBuilderObject(target);
						BreakingPoint.objsToRemove.add(target);
						for (GameObject anySort : BreakingPoint.all) {
							if (anySort.isSolid()) {
								// BreakingPoint.addOnTop(anySort);
								BreakingPoint.addOnTop(BreakingPoint.info);
							}
						}
					}
				}
			}
		}
		for (GameObject go : newShapes) {
			BreakingPoint.objsToAdd.add(go);
		}

	}
}
