package se.BaseUlterior.ParallaX;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Parallax extends ParallaxDefault {
	private static final long serialVersionUID = -8657229360844031751L;
	float parallaxEffect;
	Image image;

	public Parallax(float[] edges, Image image, float parallaxEffect) {
		super(edges, true, true, true, false, false, true, true);
		this.image = image;
		this.parallaxEffect = parallaxEffect;
		image.bind();
	}

	public void moveParallax(float xParam, float yParam) {
		x = xParam * parallaxEffect;
		y = yParam * parallaxEffect;
	}

	@Override
	public void update(GameContainer container, int arg) {
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		image.draw(0, 0, container.getWidth(), container.getHeight(), x, y, x + container.getWidth(),
				y + container.getHeight());
	}

	public void setParallaxEffect(float effect) {
		parallaxEffect = effect;
	}
}
