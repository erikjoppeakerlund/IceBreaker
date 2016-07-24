package se.BaseUlterior.GameObject;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactExplosion;
import se.BaseUlterior.Utils.UlteriorUtils;

/*
 * Class not used.
 */

public class GameObjectExplosion extends GameObject {

	private static final long MAX_TIME = 79;
	protected long timeSinceCreation;
	private float power;
	private ArrayList<GameObject> affectedObjects;

	public GameObjectExplosion(float[] nodes, float power, Color color, boolean isBackground) {
		super(nodes, false, true, false, true, false, true);
		this.power = power;
		this.color = color;
		this.timeSinceCreation = System.currentTimeMillis();
		piercable = true;
		affectedObjects = new ArrayList<>();
	}

	public GameObjectExplosion(float[] nodes, float power, boolean isBackground) {
		super(nodes, false, true, false, true, false, false);
		init(power);
		piercable = true;
		affectedObjects = new ArrayList<>();
	}

	public GameObjectExplosion(float[] nodes, float power) {
		super(nodes, false, true, false, true, false, false);
		this.power = power;
		this.color = Color.transparent;
		this.timeSinceCreation = System.currentTimeMillis();
		piercable = true;
		affectedObjects = new ArrayList<>();
	}

	private void init(float power) {
		this.power = power;
		this.color = new Color(1, 0, 0, 0.39f);
		this.timeSinceCreation = System.currentTimeMillis();

	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		affectedObjects.add(agileObject);
		return new ImpactExplosion(this, agileObject, power, getBoundingCircleRadius());
	}

	@Override
	public void update(GameContainer container, int arg) {
		if (System.currentTimeMillis() - timeSinceCreation > MAX_TIME) {
			UlteriorUtils.cleanUpImpactFromWorldBuilderObject(this);
			for (GameObject go : affectedObjects) {
				UlteriorUtils.cleanUpImpactFromWorldBuilderObject(go);
			}
			IceBreaker.objsToRemove.add(this);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

}
