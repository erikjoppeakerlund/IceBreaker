package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AimRifle extends AimBulletWeapon {

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;

	private boolean animationsIsDrawn = false;

	public AimRifle(Animation gunFire) {
		super("res/img/GUNSrifle.png");
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		graphics.setColor(Color.red);
		graphics.drawLine(startShotX, startShotY, aimAtX, aimAtY);
		if (wasJustShoot) {
			gunFire.restart();
			animationsIsDrawn = true;
		}
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) Math.toDegrees(angle + (Math.PI / 2f - 0.5f)));
			gunFire.draw(startShotX - gunFireFrameWidth / 2, startShotY - gunFireFrameHeight / 2);
			if (gunFire.getFrame() >= gunFire.getFrameCount() - 1) {
				gunFire.setCurrentFrame(0);
				gunFire.stop();
				animationsIsDrawn = false;
			}
		}

	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		updateAim();
	}

	@Override
	public void primaryPushed() {
		wasShoot();
	}

}
