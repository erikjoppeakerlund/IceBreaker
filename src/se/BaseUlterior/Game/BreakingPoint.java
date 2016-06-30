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

import se.BaseUlterior.Actions.ActionListenable;
import se.BaseUlterior.Actions.ActionListenablers;
import se.BaseUlterior.Actions.WorldCreator;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectSprite;
import se.BaseUlterior.GameObject.WorldBuiderForce;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.GameObject.WorldBuilderGroundSolid;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class BreakingPoint extends BasicGame {

	ActionListenablers actions = null;

	Circle circ = new Circle(20, 20, 20);

	public static WorldBuiderForce generalGravity = null;

	public static boolean insertMode = true;

	public BreakingPoint(String title) {
		super(title);

	}

	public static List<GameObject> all = null;

	public static List<GameObject> objsToAdd = null;

	public static List<GameObject> objsToRemove = null;

	private WorldCreator worldCreator = null;

	private List<ActionListenable> doubleClickListeners;

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		for (GameObject go : BreakingPoint.all) {
			go.render(container, graphics);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		/*
		 * TODO: ROTATIONS!
		 */
		doubleClickListeners = new ArrayList<>();
		doubleClickListeners.add(worldCreator);
		BreakingPoint.all = new ArrayList<>();
		BreakingPoint.objsToAdd = new ArrayList<>();
		objsToRemove = new ArrayList<>();

		actions = new ActionListenablers();

		float fat = 150.0f;

		float[] wallScene = new float[] { 0.0f - fat, 0.0f - fat, Constants.CANVAS_WIDTH + fat, 0.0f - fat,
				Constants.CANVAS_WIDTH + fat, Constants.CANVAS_HEIGHT + fat, 0.0f - fat, Constants.CANVAS_HEIGHT + fat,
				0.0f - fat, 0.0f - fat, 0.0f, 0.0f, Constants.CANVAS_WIDTH, 0.0f, Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT, 0.0f, Constants.CANVAS_HEIGHT, 0.0f, 0.0f };

		GameObject wall = new WorldBuilderGroundSolid(wallScene);

		BreakingPoint.all.add(wall);

		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, 0.0f };

		generalGravity = new WorldBuiderForce(wholeScene, Constants.GENERAL_GRAVITY, 0.0f, Color.lightGray);

		BreakingPoint.all.add(generalGravity);

		GameObject otherGravity = new WorldBuiderForce(
				new float[] { 400.0f, 10.0f, 400.0f, 580.0f, 900.0f, 580.0f, 900.0f, 10.0f }, -0.00f, -0.17f);

		BreakingPoint.all.add(otherGravity);

		GameObject randomWater = new WorldBuilderLiquid(

				new float[] { 170.0f, 100.0f, 700.0f, 190.0f, 620.0f, 310.0f }, Density.SOIL);
		BreakingPoint.all.add(randomWater);

		GameObject worldPiece1 = new WorldBuilderGround(
				new float[] { 5.0f, 400.0f, 250.0f, 620.0f, 495f, 600.0f, 495.0f, 695.0f, 5.0f, 695.0f });

		BreakingPoint.all.add(worldPiece1);

		GameObject sprite = new GameObjectSprite(new Circle(910, 190, Constants.SPRITE_RADIUS).getPoints(), 0.0f);

		BreakingPoint.all.add(sprite);

		WorldCreator wc = new WorldCreator();

		// GameObject randomBouncyObject = new GameObjectAgile(new Circle(120,
		// 20, 42).getPoints(), 1.0f, Color.lightGray);
		//
		// BreakingPoint.all.add(randomBouncyObject);
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {

		if (!insertMode) {
			for (GameObject go : BreakingPoint.all) {
				go.update(container, arg);
			}
		}
		if (!objsToRemove.isEmpty()) {
			all.removeAll(objsToRemove);
			objsToRemove.clear();
		}
		if (!objsToAdd.isEmpty()) {
			all.addAll(objsToAdd);
			objsToAdd.clear();
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

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (clickCount == 1) {
			actions.wasMouseClickedOnce(button, x, y);
		}
		if (clickCount == 2) {
			actions.wasMouseClickedTwice(button, x, y);
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		actions.wasKeyPressed(key, c);

	}

	@Override
	public void keyReleased(int key, char c) {
		actions.wasKeyReleased(key, c);
	}

}
