package se.BaseUlterior.Utils;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.EntityExplosion;
import se.BaseUlterior.Entity.PartSolid;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * Any logic which does not require instantiation
 * 
 * @author Johan Akerlund
 */
public class UlteriorUtils {
	public static boolean isWithinRange(Entity go1, Entity go2) {
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

	public static int distance(Entity go1, Entity go2) {
		float x1 = go1.getCenterX();
		float x2 = go2.getCenterX();

		float y1 = go1.getCenterY();
		float y2 = go2.getCenterY();

		return (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public static int distance(float x1, float y1, float x2, float y2) {
		return (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public static void cleanUpImpactFromWorldBuilderObject(Entity clearWhichBelongToThis) {
		for (Entity go : ParallaxPhysicsEngine.all) {
			go.removeCurrentImpactsWhichBelingTo(clearWhichBelongToThis);
		}
	}

	/*
	 * Not current used, due to game play decision.
	 */
	public static void removeGroundReal(float x, float y, float sizeOfExplostion, Entity origin) {
		Entity explotionShape = new EntityExplosion(new Circle(x, y, sizeOfExplostion, 8).getPoints(), 0f,
				0.0f);
		ParallaxPhysicsEngine.objsToAdd.add(explotionShape);
		ArrayList<Entity> newShapes = new ArrayList<>();
		boolean wasFound = false;
		for (Entity target : ParallaxPhysicsEngine.all) {
			if (target == origin || target == origin) {
				continue;
			}
			if (UlteriorUtils.isWithinRange(target, explotionShape)) {
				if (target.intersects(explotionShape)) {
					Shape[] result = target.subtract(explotionShape);
					if (result.length > 0) {
						for (int i = 0; i < result.length; i++) {
							Shape go = result[i];
							Entity gog = new PartSolid(go.getPoints());
							newShapes.add(gog);
							wasFound = true;
						}

						ParallaxPhysicsEngine.objsToRemove.add(target);
						UlteriorUtils.cleanUpImpactFromWorldBuilderObject(target);
					}
				}
			}
			if (wasFound) {
				break;
			}
		}
		for (Entity go : newShapes) {
			ParallaxPhysicsEngine.objsToAdd.add(go);
		}

	}

	public static void removeGround(float x, float y, float damage, float sizeOfExplostion, float bumpEffect) {
		Entity explotionShape = new EntityExplosion(new Circle(x, y, sizeOfExplostion).getPoints(), damage,
				bumpEffect);
		ParallaxPhysicsEngine.objsToAdd.add(explotionShape);
	}

	public static void removeGroundInvisible(float x, float y, float damage, float sizeOfExplostion, float bumpEffect) {
		Entity explotionShape = new EntityExplosion(new Circle(x, y, sizeOfExplostion).getPoints(), damage,
				bumpEffect, true);
		ParallaxPhysicsEngine.objsToAdd.add(explotionShape);
	}
}
