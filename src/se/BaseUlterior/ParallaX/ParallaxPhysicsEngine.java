package se.BaseUlterior.ParallaX;

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

import se.BaseUlterior.AI.TurretPlacer;
import se.BaseUlterior.Actions.Action;
import se.BaseUlterior.Actions.ActionListenablers;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.InfoStats;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.EntityEmpty;
import se.BaseUlterior.Entity.EntitySpriteControlledDesktop;
import se.BaseUlterior.Entity.EntitySpriteControlledResponsive;
import se.BaseUlterior.GUI.Alignment;
import se.BaseUlterior.GUI.Stats;
import se.BaseUlterior.GUI.ToolBox;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Main class, extends BasicGame of Slick2d library
 * 
 * @author Johan Akerlund, but credibility to author of Slick2d
 */
public class ParallaxPhysicsEngine extends BasicGame {

	ActionListenablers actions = null;

	Circle circ = new Circle(20, 20, 20);

	public static Action MODE_LATEST_ACTION = Action.ACTION_MODE_DESKTOP;

	public ParallaxPhysicsEngine(String title) {
		super(title);

	}

	public float patternPositionX;
	public float patternPositionY;
	public float tileCountX;
	public float tileCountY;
	public float patternWidth;
	public float patternHeight;

	public static List<Entity> all = null;

	public static List<Entity> objsToAdd = null;

	public static List<Entity> objsToRemove = null;

	public static List<Parallax> parallaxBackground = null;

	public static List<Parallax> parallaxForground = null;

	private ToolBox toolbox;

	public static InfoStats gameInfo;

	private Stats stats;

	public static float currentX = 0;
	public static float currentY = 0;
	public static Entity wholeSceene = null;

	ParallaxHolder parallaxHolderBackground;
	ParallaxHolder parallaxHolderForground;

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {

		parallaxHolderBackground.render(container, graphics);
		for (Entity go : ParallaxPhysicsEngine.all) {
			if (UlteriorUtils.isWithinRange(go, wholeSceene) || go.forceRender) {
				go.render(container, graphics);
			}
		}
		parallaxHolderForground.render(container, graphics);
		graphics.setBackground(Color.darkGray);
	}

	private static Entity sprite;
	private static Entity spriteMobile;
	private static Entity spriteDesktop;

	public static int nrOfTurrets;
	private int startTime = 0;

	@Override
	public void init(GameContainer container) throws SlickException {

		patternWidth = container.getWidth();
		patternHeight = container.getHeight();

		parallaxBackground = new ArrayList<>();
		parallaxForground = new ArrayList<>();
		ParallaxPhysicsEngine.all = new ArrayList<>();
		ParallaxPhysicsEngine.objsToAdd = new ArrayList<>();
		objsToRemove = new ArrayList<>();

		wholeSceene = new EntityEmpty(new float[] { 0, 0, 0, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, 0 });
		ParallaxPhysicsEngine.objsToAdd.add(wholeSceene);

		parallaxHolderBackground = new ParallaxHolder(2);
		parallaxHolderForground = new ParallaxHolder(1);

		gameInfo = new InfoStats();

		LevelDummy levelDummy = new LevelDummy();

		MapEssentials requiredWallBuilderObjects = new MapEssentials();
		all.addAll(requiredWallBuilderObjects.getLevelPieces());

		ParallaxPhysicsEngine.objsToAdd.addAll(levelDummy.levelPieces);

		TurretPlacer turretPlacer = new TurretPlacer();
		//
		for (Entity groundPiece : levelDummy.levelPieces) {
			if (!groundPiece.piercable) {
				turretPlacer.placeTurretsOnMe(groundPiece);
			}
		}
		// AIBot bot = new AIBot(41f);
		// IceBreaker.objsToAdd.add(bot);
		toolbox = new ToolBox(Alignment.LEFT);
		toolbox.pack();
		stats = new Stats();
		ParallaxPhysicsEngine.objsToAdd.add(stats);
		spriteDesktop = new EntitySpriteControlledDesktop();
		spriteMobile = new EntitySpriteControlledResponsive();
		sprite = spriteDesktop;
		ParallaxPhysicsEngine.objsToAdd.add(sprite);
		// actions = new ActionListenablers();

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (!container.isPaused()) {
			startTime += delta;
			stats.setTime(startTime / 1000);
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.setPaused(!container.isPaused());
		}
		for (Entity go : ParallaxPhysicsEngine.all) {
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
		AppGameContainer appGameContainer = new AppGameContainer(new ParallaxPhysicsEngine("ParallaX"));
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
		for (Entity go : ParallaxPhysicsEngine.all) {
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

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// if (clickCount == 2) {
		// actions.wasMouseClickedTwice(button, x, y);
		// }
		// if (clickCount == 1) {
		// actions.wasMouseClickedOnce(button, x, y);
		// }
	}
}
