package se.BaseUlterior.Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.GameObject.GameObject;

public class Parallax extends GameObject {
	float parallaxEffect;
	Image image;

	public Parallax(float[] edges, Image image, float parallaxEffect) {
		super(edges);
		this.image = image;
		this.parallaxEffect = parallaxEffect;
		isBackgroundObj = true;
	}

	public void moveParalax(float xParam, float yParam) {
		x = xParam * parallaxEffect;
		y = yParam * parallaxEffect;
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		image.draw(x, y);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
