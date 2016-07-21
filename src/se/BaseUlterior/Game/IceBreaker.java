package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Actions.Action;
import se.BaseUlterior.Actions.ActionListenable;
import se.BaseUlterior.Actions.ActionListenablers;
import se.BaseUlterior.Actions.WorldCreator;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.GameInfo;
import se.BaseUlterior.Context.Info;
import se.BaseUlterior.GUI.Alignment;
import se.BaseUlterior.GUI.Component;
import se.BaseUlterior.GUI.ToolBox;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.GameObjectEmpty;
import se.BaseUlterior.GameObject.GameObjectSpriteDesktop;
import se.BaseUlterior.GameObject.GameObjectSpriteMobile;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Main class, extends BasicGame of Slick2d library
 * 
 * @author Johan Akerlund, but credibility to author of Slick2d
 */
public class IceBreaker extends BasicGame {

	ActionListenablers actions = null;

	Circle circ = new Circle(20, 20, 20);

	public static boolean pause = true;

	public static Action MODE_ACTUAL;
	public static Action MODE_LATEST_ACTION = Action.ACTION_MODE_DESKTOP;

	public IceBreaker(String title) {
		super(title);

	}

	public float patternPositionX;
	public float patternPositionY;
	public float tileCountX;
	public float tileCountY;
	public float patternWidth;
	public float patternHeight;

	public static List<GameObject> all = null;

	public static List<GameObject> objsToAdd = null;

	public static List<GameObject> objsToRemove = null;

	public static List<Parallax> parallaxList = null;

	public static Info info = new GameInfo();

	private GameObject toolbox;

	private WorldCreator worldCreator = null;

	private List<ActionListenable> actionListeners;

	public static float currentX = 0;
	public static float currentY = 0;
	private GameObject wholeSceene = null;

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {

		for (GameObject go : IceBreaker.all) {
			if (UlteriorUtils.isWithinRange(go, wholeSceene) || go.forceRender) {
				go.render(container, graphics);
			}
		}

	}

	private static GameObject sprite;
	private static GameObject spriteMobile;
	private static GameObject spriteDesktop;

	@Override
	public void init(GameContainer container) throws SlickException {

		patternWidth = container.getWidth();
		patternHeight = container.getHeight();

		actionListeners = new ArrayList<>();
		actionListeners.add(worldCreator);
		parallaxList = new ArrayList<>();
		IceBreaker.all = new ArrayList<>();
		IceBreaker.objsToAdd = new ArrayList<>();
		objsToRemove = new ArrayList<>();

		actions = new ActionListenablers();
		wholeSceene = new GameObjectEmpty(new float[] { 0, 0, 0, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, 0 });
		IceBreaker.objsToAdd.add(wholeSceene);

		LevelDummy levelDummy = new LevelDummy();
		GroundMap requiredWallBuilderObjects = new GroundMap();
		all.addAll(requiredWallBuilderObjects.levelPieces);

		spriteDesktop = new GameObjectSpriteDesktop();
		spriteMobile = new GameObjectSpriteMobile();
		sprite = spriteDesktop;

		IceBreaker.objsToAdd.addAll(levelDummy.levelPieces);
		toolbox = new ToolBox(Alignment.LEFT);
		((Component) toolbox).pack();
		IceBreaker.objsToAdd.add(info);
		IceBreaker.objsToAdd.add(sprite);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (GameObject go : IceBreaker.all) {
			if (UlteriorUtils.isWithinRange(go, sprite) || go.forceUpdate) {
				go.update(container, delta);
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
		AppGameContainer appGameContainer = new AppGameContainer(new IceBreaker("ICE:BREAKER"));
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

	public static void moveScreen(float x, float y) {
		currentX += x;
		currentY += y;
		for (Parallax parallax : parallaxList) {
			parallax.moveParalax(currentX, currentY);
		}
		for (GameObject go : IceBreaker.all) {
			if (!go.isSolid) {
				go.setX(go.getX() - x);
			}
		}
		for (GameObject go : IceBreaker.all) {
			if (!go.isSolid) {
				go.setY(go.getY() - y);
			}
		}
	}

	public static void setActionModeAction(Action action) {
		for (GameObject go : IceBreaker.all) {
			go.wasActionStateSet(action);
		}
	}

	public static void setSprite(Action actionModeCell) {
		if (actionModeCell != MODE_LATEST_ACTION) {
			if (all.contains(sprite)) {
				objsToRemove.add(sprite);
			}

			sprite = (actionModeCell == Action.ACTION_MODE_CELL) ? spriteMobile : spriteDesktop;
			objsToAdd.add(sprite);
		}
	}
}
