package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;

public class AimMachineGun extends AimTriggerHoldable {

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;

	private boolean animationsIsDrawn = false;

	public AimMachineGun(Animation gunFire) {
		super("res/img/GUNSmachineGun.png", Constants.PERFERED_ARM_LENGTH * 0.7f);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) Math.toDegrees(angle + (Math.PI / 2f - 0.5f)));
			gunFire.draw(startShotX - gunFireFrameWidth / 2, startShotY - gunFireFrameHeight / 2);
		}
	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		if (wasJustShoot && !animationsIsDrawn) {
			gunFire.setCurrentFrame(0);
			animationsIsDrawn = true;
		} else if (animationsIsDrawn) {
			if (gunFire.getFrame() >= gunFire.getFrameCount() - 1) {
				if (isTriggerDown) {
					gunFire.setCurrentFrame(0);
				} else {
					animationsIsDrawn = false;
				}
			}
			gunFire.update(arg);
		}

	}

}