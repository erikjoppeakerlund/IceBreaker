package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Geom.Vector2;

public class GameObjectSplit extends GameObjectFalling {

	public GameObjectSplit(float x, float y, Vector2 motion) {
		super(new Circle(x, y, 2f + (float) (Math.random() * 3f), 4).getPoints(), 1.0f);
		this.motion = motion;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.black);
		graphics.fill(this);
	}

}
