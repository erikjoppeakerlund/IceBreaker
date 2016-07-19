package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.Geom.Vector2;

/**
 * GamObject which should represent a splinter from a ricochet
 * 
 * @author Johan Akerlund
 */
public class GameObjectSplinter extends GameObjectAgile {
	private final int LIFE_SPAN_LIMIT = 32;
	private int lifeSpan = 0;

	public GameObjectSplinter(float x, float y, Vector2 motion) {
		super(new Circle(x, y, 2f + (float) (Math.random() * 3f), 4).getPoints(), 1.0f);
		this.motion = motion;
		color = new Color(0.2f, 0, 0);
		isBackgroundObj = true;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		color.a = 1f - (float) lifeSpan / (float) LIFE_SPAN_LIMIT;
		graphics.setColor(color);
		graphics.fill(this);
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			IceBreaker.objsToRemove.add(this);
		}
		lifeSpan++;
	}

}
