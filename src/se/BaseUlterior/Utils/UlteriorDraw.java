package se.BaseUlterior.Utils;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class UlteriorDraw {

	private static SpriteSheet gunFire = null;
	private static Animation animationGunfire = null;

	private static int gunFireFrameWidth;
	private static int gunFireFrameHeight;

	public UlteriorDraw() {
		try {
			gunFire = new SpriteSheet("res/img/GUNFIREsimple.png", 96, 96);
			animationGunfire = new Animation(gunFire, 10);
			gunFireFrameWidth = animationGunfire.getCurrentFrame().getWidth();
			gunFireFrameHeight = animationGunfire.getCurrentFrame().getHeight();
			gunFire.setCenterOfRotation(gunFireFrameWidth / 2f, gunFireFrameHeight / 2f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void gunFire(Graphics g, float x, float y, float angle) {
		animationGunfire.getCurrentFrame().setRotation((float) Math.toDegrees(angle + (Math.PI / 2f - 0.5f)));
		animationGunfire.draw(x - gunFireFrameWidth / 2, y - gunFireFrameHeight / 2);
	}
}
