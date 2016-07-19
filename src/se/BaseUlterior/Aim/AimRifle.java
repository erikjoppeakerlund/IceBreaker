package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AimRifle extends AimBulletWeapon {

	private boolean animationsIsDrawn = false;

	public AimRifle(Animation gunFire) {
		super("res/img/GUNSrifle.png");
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
		weight = 3.0f;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		graphics.setColor(Color.red);
		graphics.drawLine(startShotX, startShotY, aimAtX, aimAtY);
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) arm.getTheta() + 65);
			gunFire.draw(gunFireStartAtX - gunFireFrameWidth / 2, gunFireStartAtY - gunFireFrameHeight / 2);
		}

	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		updateAim();
		if (wasJustShoot) {
			gunFire.setCurrentFrame(0);
			gunFire.update(arg);
			animationsIsDrawn = true;
		} else if (animationsIsDrawn) {
			if (gunFire.getFrame() < gunFire.getFrameCount() - 1) {
				gunFire.update(arg);
			} else {
				animationsIsDrawn = false;
			}
		}
	}

	@Override
	public void primaryPushed() {
		wasShoot();
	}

}
