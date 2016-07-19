package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.GameObject.GameObjectFalling;

public abstract class ImagableObject extends GameObjectFalling {
	protected Image rifleImageRight = null;
	protected Image rifleImageLeft = null;
	protected int imageHeight;
	protected int imageWidth;
	protected float drawX;

	protected float angle;

	public ImagableObject(float[] nodes, float bouncyness, String pathToImage) {
		super(nodes, bouncyness);
		init(pathToImage);
	}

	private void init(String pathToImage) {
		try {
			rifleImageRight = new Image(pathToImage);
			imageHeight = (int) (rifleImageRight.getHeight());
			imageWidth = (int) (rifleImageRight.getWidth());
			rifleImageRight.setCenterOfRotation((imageWidth / 2), (imageHeight / 2));

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (motion.x <= 0) {
			angle += rotation * 5.9f;
		} else {
			angle -= rotation * 5.9f;
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		rifleImageRight.setRotation(angle);
		rifleImageRight.draw(getCenterX() - imageWidth / 2, y);
	}

}
