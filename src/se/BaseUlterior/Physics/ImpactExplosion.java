package se.BaseUlterior.Physics;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class ImpactExplosion extends Impact {

	public ImpactExplosion(GameObject origin, GameObjectAgile other) {
		super(origin, other);
	}

	@Override
	public void calculateEffect(Vector2 affectedPiece) {
		ArrayList<GameObject> newShapes = new ArrayList<>();
		for (GameObject target : BreakingPoint.all) {
			if (target == other || target == origin) {
				continue;
			}
			if (UlteriorUtils.isWithinRange(target, origin)) {
				if (target.intersects(origin)) {
					Shape[] result = target.subtract(origin);
					if (result.length > 0) {
						for (int i = 0; i < result.length; i++) {
							Shape go = result[i];
							GameObject gog = new WorldBuilderGround(go.getPoints(), Color.black);
							newShapes.add(gog);
						}
						BreakingPoint.objsToRemove.add(target);
					}
				}
			}
		}
		// System.out.println(newShapes.size());
		BreakingPoint.objsToRemove.add(origin);
		for (GameObject go : newShapes) {
			BreakingPoint.objsToAdd.add(go);
		}

	}

}
