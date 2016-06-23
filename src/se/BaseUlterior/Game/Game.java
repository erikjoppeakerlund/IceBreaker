package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.AgileObject;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.PieceWorldBuiderForce;

public class Game extends BasicGame {

	Circle circ = new Circle(20, 20, 20);

	public static PieceWorldBuiderForce generalGravity = null;

	public Game(String title) {
		super(title);
	}

	private List<AgileObject> agiles = null;
	public static List<GameObject> all = null;

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		for (GameObject go : Game.all) {
			go.render(container, graphics);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		this.agiles = new ArrayList<>();
		Game.all = new ArrayList<>();

		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_SIZE, Constants.CANVAS_SIZE,
				Constants.CANVAS_SIZE, Constants.CANVAS_SIZE, 0.0f };

		generalGravity = new PieceWorldBuiderForce(wholeScene, 0.04f, 0.01f);

		all.add(generalGravity);

		GameObject sprite = new AgileObject(new Circle(20, 20, 10).getPoints());

		all.add(sprite);

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {

		for (GameObject go : Game.all) {
			go.update(container, arg);
		}
	}

	public static void main(String[] s) throws SlickException {
		AppGameContainer appGameContainer = new AppGameContainer(new Game("Breking Point"));
		int maxFPS = 60;
		appGameContainer.setTargetFrameRate(maxFPS);
		appGameContainer.setDisplayMode(1000, 700, false);
		appGameContainer.setAlwaysRender(true);
		appGameContainer.start();

	}
}
