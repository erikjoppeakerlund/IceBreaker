package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

/**
 * Any aimed game object which is rendered as an image
 * 
 * @author Johan Akerlund
 */

public abstract class ImagableObject extends GameObjectAgile {
	protected Image rifleImageRight = null;
	protected Image rifleImageLeft = null;
	protected int imageHeight;
	protected int imageWidth;
	protected float drawX;

	protected Vector2 angle;

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
		angle = new Vector2(0.0);

	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);
		if (motion.x <= 0) {
			angle.add(rotation * 11.1);
		} else {
			angle.sub(rotation * 11.1);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		rifleImageRight.setRotation((float) angle.getTheta());
		rifleImageRight.draw(getCenterX() - imageWidth / 2, y);
	}

}
