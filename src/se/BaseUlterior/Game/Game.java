package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.GameObject.AgileObject;
import se.BaseUlterior.GameObject.GameObject;

public class Game extends BasicGame {

	Circle circ = new Circle(20, 20, 20);

	public Game(String title) {
		super(title);
		// TODO Auto-generated constructor stub
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

		all.add(new AgileObject(new Circle(20, 20, 20).getPoints()));

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		for (AgileObject pa : this.agiles) {
			pa.update(container, arg);
		}
	}

}
