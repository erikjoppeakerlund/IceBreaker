package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;

/**
 * MachineGun Aim
 * 
 * @author Johan Akerlund
 */
public class AimMachineGun extends AimTriggerHoldable {

	private boolean animationsIsDrawn = false;

	public AimMachineGun(Animation gunFire) {
		super("res/img/GUNSmachineGun.png", Constants.PERFERED_ARM_LENGTH * 0.7f);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
		recoilPower = 0.05f;
		damage = Constants.WEAPON_EFFECT_UZI;
		slug = "UZI";
		initFireVectors();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) theta + 65);

			gunFire.draw((spriteX + gunFireStartAt.x) - gunFireFrameWidth / 2,
					(spriteY + gunFireStartAt.y) - gunFireFrameHeight / 2);
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

	@Override
	protected void wasShoot() {
		updateAim();
		super.wasShoot();
	}

}
