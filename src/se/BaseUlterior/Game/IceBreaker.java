package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.AI.AIBot;
import se.BaseUlterior.AI.TurretPlacer;
import se.BaseUlterior.Actions.Action;
import se.BaseUlterior.Actions.ActionListenablers;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.InfoStats;
import se.BaseUlterior.GUI.Alignment;
import se.BaseUlterior.GUI.Stats;
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

	public static List<Parallax> parallaxBackground = null;

	public static List<Parallax> parallaxForground = null;

	private ToolBox toolbox;

	public static InfoStats gameInfo = new InfoStats();

	public static float currentX = 0;
	public static float currentY = 0;
	public static GameObject wholeSceene = null;

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {

		for (Parallax goBackground : parallaxBackground) {
			goBackground.render(container, graphics);
		}
		for (GameObject go : IceBreaker.all) {
			if (UlteriorUtils.isWithinRange(go, wholeSceene) || go.forceRender) {
				go.render(container, graphics);
			}
		}
		for (Parallax goForground : parallaxForground) {
			goForground.render(container, graphics);
		}
		graphics.setBackground(Color.darkGray);
	}

	private static GameObject sprite;
	private static GameObject spriteMobile;
	private static GameObject spriteDesktop;

	@Override
	public void init(GameContainer container) throws SlickException {

		patternWidth = container.getWidth();
		patternHeight = container.getHeight();

		parallaxBackground = new ArrayList<>();
		parallaxForground = new ArrayList<>();
		IceBreaker.all = new ArrayList<>();
		IceBreaker.objsToAdd = new ArrayList<>();
		objsToRemove = new ArrayList<>();

		wholeSceene = new GameObjectEmpty(new float[] { 0, 0, 0, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, 0 });
		IceBreaker.objsToAdd.add(wholeSceene);

		ParallaxHolder parallaxHolderBackground = new ParallaxHolder(0);
		ParallaxHolder parallaxHolderForground = new ParallaxHolder(1);
		parallaxBackground.addAll(parallaxHolderBackground.getGameObjects());
		parallaxForground.addAll(parallaxHolderForground.getGameObjects());

		LevelDummy levelDummy = new LevelDummy();

		GroundMap requiredWallBuilderObjects = new GroundMap();
		all.addAll(requiredWallBuilderObjects.getLevelPieces());

		IceBreaker.objsToAdd.addAll(levelDummy.levelPieces);

		TurretPlacer turretPlacer = new TurretPlacer();

		for (GameObject groundPiece : levelDummy.levelPieces) {
			if (!groundPiece.piercable) {
				turretPlacer.placeTurretsOnMe(groundPiece);
			}
		}
		AIBot bot = new AIBot(41f);
		IceBreaker.objsToAdd.add(bot);
		toolbox = new ToolBox(Alignment.LEFT);
		toolbox.pack();
		Stats stats = new Stats();
		IceBreaker.objsToAdd.add(stats);
		spriteDesktop = new GameObjectSpriteDesktop();
		spriteMobile = new GameObjectSpriteMobile();
		sprite = spriteDesktop;
		IceBreaker.objsToAdd.add(sprite);
		// actions = new ActionListenablers();

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.setPaused(!container.isPaused());
		}
		for (GameObject go : IceBreaker.all) {
			// if (UlteriorUtils.isWithinRange(go, sprite) || go.forceUpdate) {
			if (UlteriorUtils.isWithinRange(go, wholeSceene) || go.forceUpdate) {
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
		AppGameContainer appGameContainer = new AppGameContainer(new IceBreaker("ParallaX"));
		int maxFPS = 60;
		appGameContainer.setTargetFrameRate(maxFPS);
		appGameContainer.setDisplayMode((int) Constants.CANVAS_WIDTH, (int) Constants.CANVAS_HEIGHT, true);
		appGameContainer.setAlwaysRender(true);
		appGameContainer.start();

	}

	public static void moveScreen(float x, float y) {
		currentX += x;
		currentY += y;
		for (Parallax parallax : parallaxBackground) {
			parallax.moveParallax(currentX, currentY);
		}
		for (GameObject go : IceBreaker.all) {
			if (!go.isBackgroundObj) {
				go.setX(go.getX() - x);
			}
			if (!go.isBackgroundObj) {
				go.setY(go.getY() - y);
			}
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
