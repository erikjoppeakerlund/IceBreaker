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
import se.BaseUlterior.Context.Info;
import se.BaseUlterior.GUI.Alignment;
import se.BaseUlterior.GUI.Panel;
import se.BaseUlterior.GUI.ToolBox;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectFalling;
import se.BaseUlterior.GameObject.GameObjectSprite;
import se.BaseUlterior.GameObject.WorldBuiderForce;
import se.BaseUlterior.GameObject.WorldBuilderGroundSolid;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class BreakingPoint extends BasicGame {

	ActionListenablers actions = null;

	Circle circ = new Circle(20, 20, 20);

	// public static WorldBuiderForce generalGravity = null;

	public static boolean insertMode = true;

	public BreakingPoint(String title) {
		super(title);

	}

	public static List<GameObject> all = null;

	public static List<GameObjectFalling> allFalliing = null;

	public static List<GameObject> objsToAdd = null;

	public static List<GameObject> objsToRemove = null;

	public static Info info = new Info();

	private WorldCreator worldCreator = null;

	private List<ActionListenable> actionListeners;

	public void addObjToGame(GameObject g) {
		allFalliing.add((GameObjectFalling) g);
		objsToAdd.add(g);
	}

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

		allFalliing = new ArrayList<>();

		actionListeners = new ArrayList<>();
		actionListeners.add(worldCreator);
		BreakingPoint.all = new ArrayList<>();
		BreakingPoint.objsToAdd = new ArrayList<>();
		objsToRemove = new ArrayList<>();

		actions = new ActionListenablers();

		Level levelDummy = new Level();
		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
				Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0.0f };

		WorldBuiderForce generalGravity = new WorldBuiderForce(wholeScene, 0, Constants.GENERAL_GRAVITY,
				Color.transparent);

		levelDummy.levelPieces.add(generalGravity);
		Panel toolbox = new ToolBox(Alignment.LEFT);
		toolbox.pack();

		float fat = 150.0f;

		float BOTTOM = Constants.CANVAS_HEIGHT_FULL;

		float[] wallScene = new float[] { 0.0f - fat, 0.0f - fat, Constants.CANVAS_WIDTH_FULL + fat, 0.0f - fat,
				Constants.CANVAS_WIDTH_FULL + fat, BOTTOM + fat, 0.0f - fat, BOTTOM + fat, 0.0f - fat, 0.0f - fat, 0.0f,
				0.0f, Constants.CANVAS_WIDTH_FULL, 0.0f, Constants.CANVAS_WIDTH_FULL, BOTTOM, 0.0f, BOTTOM, 0.0f,
				0.0f };

		GameObject wall = new WorldBuilderGroundSolid(wallScene);

		BreakingPoint.objsToAdd.add(wall);

		GameObject otherGravity = new WorldBuiderForce(
				new float[] { 1385, 357, 1177, 267, 1314, 241, 1213, 155, 1335, 117, 1237, 83, 1306, 10, 1383, 15, },
				-0.02f, -0.0f);

		// levelDummy.levelPieces.add(otherGravity);

		GameObject randomWater = new WorldBuilderLiquid(

				new float[] { 1083, 556, 929, 489, 840, 534, 438, 530, 350, 523, 310, 627, 495, 693, 863, 653, },
				Density.LAVA);
		// levelDummy.levelPieces.add(randomWater);

		GameObject sprite = new GameObjectSprite(
				new Circle(Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT / 2, Constants.SPRITE_RADIUS)
						.getPoints(),
				0.0f);

		addObjToGame(sprite);

		levelDummy.levelPieces.add(toolbox);
		BreakingPoint.all.addAll(levelDummy.levelPieces);
		BreakingPoint.objsToAdd.add(info);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

		// if (!insertMode) {
		for (GameObject go : BreakingPoint.all) {
			go.update(container, delta);
		}
		// }
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
		AppGameContainer appGameContainer = new AppGameContainer(new BreakingPoint("Ice Breaker"));
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

	@Override
	public void mouseWheelMoved(int change) {

		actions.wasMouseWheelMoved(change);

	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// if (newx > Constants.CANVAS_WIDTH - 50) {
		// for (GameObject go : BreakingPoint.all) {
		// go.setX(go.getX() - 4);
		// }
		// }
		// if (newx < 50) {
		// for (GameObject go : BreakingPoint.all) {
		// go.setX(go.getX() + 4);
		// }
		// }
		// if (newy > Constants.CANVAS_HEIGHT - 50) {
		// for (GameObject go : BreakingPoint.all) {
		// go.setY(go.getY() - 4);
		// }
		// }
		// if (newy < 50) {
		// for (GameObject go : BreakingPoint.all) {
		// go.setY(go.getY() + 4);
		// }
		// }
	}

	public static void moveScreen(float x, float y) {
		for (GameObject go : BreakingPoint.all) {
			if (!go.isSolid()) {
				go.setX(go.getX() - x);
			}
		}
		for (GameObject go : BreakingPoint.all) {
			if (!go.isSolid()) {
				go.setY(go.getY() - y);
			}
		}
	}

	public static void addOnTop(GameObject anySort) {
		objsToRemove.add(anySort);
		objsToAdd.add(anySort);
		// anySort.putOnTop();
	}

}
