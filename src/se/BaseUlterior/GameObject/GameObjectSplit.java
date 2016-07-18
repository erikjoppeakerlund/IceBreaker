package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Geom.Vector2;

public class GameObjectSplit extends GameObjectFalling {
	private final int LIFE_SPAN_LIMIT = 32;
	private int lifeSpan = 0;

	public GameObjectSplit(float x, float y, Vector2 motion) {
		super(new Circle(x, y, 2f + (float) (Math.random() * 3f), 4).getPoints(), 1.0f);
		this.motion = motion;
		isBackgroundObj = true;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.black);
		graphics.fill(this);
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (lifeSpan > LIFE_SPAN_LIMIT) {
			BreakingPoint.objsToRemove.add(this);
		}
		lifeSpan++;
	}

}
