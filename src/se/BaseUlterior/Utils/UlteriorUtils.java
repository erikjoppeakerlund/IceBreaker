package se.BaseUlterior.Utils;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectExplosion;
import se.BaseUlterior.GameObject.WorldBuilderGround;

/**
 * Any logic which does not require instantiation
 * 
 * @author Johan Akerlund
 */
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

	public static void cleanUpImpactFromWorldBuilderObject(GameObject clearWhichBelongToThis) {
		for (GameObject go : IceBreaker.all) {
			go.removeCurrentImpactsWhichBelingTo(clearWhichBelongToThis);
		}
	}

	/*
	 * Not current used, due to game play decision.
	 */
	public static void removeGroundReal(float x, float y, float sizeOfExplostion, GameObject origin) {
		GameObject explotionShape = new GameObjectExplosion(new Circle(x, y, sizeOfExplostion, 8).getPoints(), 0f,
				false);
		IceBreaker.objsToAdd.add(explotionShape);
		ArrayList<GameObject> newShapes = new ArrayList<>();
		boolean wasFound = false;
		for (GameObject target : IceBreaker.all) {
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

						IceBreaker.objsToRemove.add(target);
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
			IceBreaker.objsToAdd.add(go);
		}

	}

	public static void removeGround(float x, float y, float sizeOfExplostion, float effect) {
		GameObject explotionShape = new GameObjectExplosion(new Circle(x, y, sizeOfExplostion).getPoints(), effect,
				false);
		IceBreaker.objsToAdd.add(explotionShape);
	}

	public static void removeGroundInvisible(float x, float y, float sizeOfExplostion, float effect) {
		GameObject explotionShape = new GameObjectExplosion(new Circle(x, y, sizeOfExplostion).getPoints(), effect);
		IceBreaker.objsToAdd.add(explotionShape);
	}
}
