package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.GameObject.GameObjectSimple;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class Grenade extends GameObjectAgile {

	public static final float GRENADE_SIZE = 27.0f;
	private boolean isReleased = false;

	private static float BOUNCYNESS = 0.9f;
	protected long wasReleasedAt;

	protected final long TIME_UNTIL_EXPLOTION = 2500;

	protected Shape explotionShape = null;

	public Grenade(float[] nodes) {
		super(nodes, BOUNCYNESS);
		color = color.black;
		explotionShape = new GameObjectSimple(new Circle(getCenterX(), getCenterY(), 200.0f).getPoints());
	}

	public void initMotion(Vector2 motion) {
		this.motion = motion;
		isReleased = true;
		resetForceException();
		wasReleasedAt = System.currentTimeMillis();
	}

	@Override
	public void resetForceException() {
		if (isReleased) {
			forceException = false;
		} else {
			addForceException();
		}
	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		if (!isReleased) {
			return;
		}
		if (System.currentTimeMillis() - wasReleasedAt > TIME_UNTIL_EXPLOTION) {
			for (GameObject target : BreakingPoint.all) {
				explotionShape.setCenterX(getCenterX());
				explotionShape.setCenterY(getCenterY());
				if (UlteriorUtils.isWithinRange(target, (GameObject) explotionShape)) {
					if (target.intersects(explotionShape) || target.contains(explotionShape)) {

						Shape[] result = target.subtract(explotionShape);
						for (int i = 0; i < result.length; i++) {
							Shape go = result[i];
							GameObject gog = new WorldBuilderGround(go.getPoints(), Color.black);
							BreakingPoint.objsToAdd.add(gog);
						}
						System.out.println(result.length);
						if (result.length > 0) {
							BreakingPoint.objsToRemove.add(target);
						}
						BreakingPoint.objsToRemove.add(this);
					}
				}
			}
		}

	}

}
