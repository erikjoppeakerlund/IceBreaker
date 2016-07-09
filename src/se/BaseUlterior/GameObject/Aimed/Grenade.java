package se.BaseUlterior.GameObject.Aimed;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectExplosion;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class Grenade extends GameObjectFalling {

	public static final float GRENADE_SIZE = 27.0f;
	private boolean isReleased = false;

	private static float BOUNCYNESS = 0.9f;
	protected long wasReleasedAt;

	protected final long TIME_UNTIL_EXPLOTION = 1500;

	protected GameObject explotionShape = null;

	protected float sizeOfExplostion = 210;

	public Grenade(float[] nodes) {
		super(nodes, BOUNCYNESS);
		color = color.darkGray;
		// explotionShape = new GameObjectExplosion(new Circle(getCenterX(),
		// getCenterY(), sizeOfExplostion).getPoints());
	}

	public void initMotion(Vector2 motion) {
		this.motion = motion;
		isReleased = true;
		// resetForceException();
		wasReleasedAt = System.currentTimeMillis();
	}

	// @Override
	// public void resetForceException() {
	// if (isReleased) {
	// // forceException = false;
	// } else {
	// addForceException();
	// }
	// }

	@Override
	public void update(GameContainer container, int arg) {
		if (!isReleased) {
			return;
		}
		if (System.currentTimeMillis() - wasReleasedAt > TIME_UNTIL_EXPLOTION) {
			explotionShape = new GameObjectExplosion(
					new Circle(getCenterX(), getCenterY(), sizeOfExplostion).getPoints());
			BreakingPoint.objsToAdd
					.add(new GameObjectExplosion(new Circle(getCenterX(), getCenterY(), sizeOfExplostion).getPoints()));
			ArrayList<GameObject> newShapes = new ArrayList<>();
			for (GameObject target : BreakingPoint.all) {
				if (target == this || target == this) {
					continue;
				}
				explotionShape.setCenterX(getCenterX());
				explotionShape.setCenterY(getCenterY());
				if (UlteriorUtils.isWithinRange(target, explotionShape)) {
					if (target.intersects(explotionShape)) {
						Shape[] result = target.subtract(explotionShape);
						if (result.length > 0) {
							for (int i = 0; i < result.length; i++) {
								Shape go = result[i];
								GameObject gog = new WorldBuilderGround(go.getPoints());
								newShapes.add(gog);
							}
							BreakingPoint.objsToRemove.add(target);
						}
					}
				}
			}
			for (GameObject go : newShapes) {
				BreakingPoint.objsToAdd.add(go);
			}

			BreakingPoint.objsToRemove.add(this);
		} else {
			super.update(container, arg);
		}

	}

}
