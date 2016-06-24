package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.GameObject.GameObjectSprite;
import se.BaseUlterior.GameObject.WorldBuiderForce;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class BreakingPoint extends BasicGame {

	Circle circ = new Circle(20, 20, 20);

	public static WorldBuiderForce generalGravity = null;

	public BreakingPoint(String title) {
		super(title);
	}

	private List<GameObjectAgile> agiles = null;
	public static List<GameObject> all = null;

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		for (GameObject go : BreakingPoint.all) {
			go.render(container, graphics);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		this.agiles = new ArrayList<>();
		BreakingPoint.all = new ArrayList<>();

		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, 0.0f };

		generalGravity = new WorldBuiderForce(wholeScene, 0.05f, 0.0f, Color.white);

		BreakingPoint.all.add(generalGravity);

		GameObject otherGravity = new WorldBuiderForce(
				new float[] { 400.0f, 10.0f, 400.0f, 580.0f, 900.0f, 580.0f, 900.0f, 10.0f }, -0.00f, -0.17f,
				Color.gray);

		BreakingPoint.all.add(otherGravity);

		GameObject randomWater = new WorldBuilderLiquid(

				new float[] { 170.0f, 100.0f, 700.0f, 190.0f, 620.0f, 310.0f }, Density.WATER);
		BreakingPoint.all.add(randomWater);

		GameObject worldPiece1 = new WorldBuilderGround(
				new float[] { 5.0f, 400.0f, 250.0f, 620.0f, 495f, 600.0f, 495.0f, 695.0f, 5.0f, 695.0f }, 2.0f,
				Color.black);

		BreakingPoint.all.add(worldPiece1);

		GameObject sprite = new GameObjectSprite(new Circle(310, 20, 75).getPoints());

		BreakingPoint.all.add(sprite);

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {

		for (GameObject go : BreakingPoint.all) {
			go.update(container, arg);
		}
	}

	public static void main(String[] s) throws SlickException {
		AppGameContainer appGameContainer = new AppGameContainer(new BreakingPoint("Breking Point"));
		int maxFPS = 60;
		appGameContainer.setTargetFrameRate(maxFPS);
		appGameContainer.setDisplayMode((int) Constants.CANVAS_WIDTH, (int) Constants.CANVAS_HEIGHT, false);
		appGameContainer.setAlwaysRender(true);
		appGameContainer.start();

	}
}
