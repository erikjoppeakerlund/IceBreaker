package se.BaseUlterior.Entity.Processed;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Entity.EntityAgile;
import se.BaseUlterior.Geom.Vector2;

/**
 * Any aimed game object which is rendered as an image
 * 
 * @author Johan Akerlund
 */

public abstract class ImagableObject extends EntityAgile {
	protected Image grenadeImage = null;
	protected int imageHeight;
	protected int imageWidth;
	protected float drawX;

	protected Vector2 angle;

	public ImagableObject(float[] nodes, float bounciness, String pathToImage, boolean noImpact) {
		super(nodes, bounciness, noImpact);
		init(pathToImage);
	}

	private void init(String pathToImage) {
		try {
			grenadeImage = new Image(pathToImage);
			grenadeImage.bind();
			imageHeight = (int) (grenadeImage.getHeight());
			imageWidth = (int) (grenadeImage.getWidth());
			grenadeImage.setCenterOfRotation((imageWidth / 2), (imageHeight / 2));

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
			// rotate(rotation);
		} else {
			angle.sub(rotation * 11.1);
			// rotate(-rotation);
		}

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		grenadeImage.setRotation((float) angle.getTheta());
		grenadeImage.draw(getCenterX() - imageWidth / 2, y);
	}

}
