package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;

/**
 * Rifle gun Aim
 * 
 * @author Johan Akerlund
 */
public class AimRifle extends AimBulletWeapon {

	private boolean animationsIsDrawn = false;

	public AimRifle(Animation gunFire) {
		super("res/img/GUNSrifle.png", IMAGE_SCALE_STANDARD);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
		weight = 5.0f;
		recoilPower = 0.6f;
		damage = Constants.WEAPON_EFFECT_RIFLE;
		slug = "AVP";
		initFireVectors();

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		graphics.setColor(Color.red);
		graphics.drawLine(spriteX + startShot.x, spriteY + startShot.y, aimAtX, aimAtY);
		if (animationsIsDrawn) {
			gunFire.getCurrentFrame().setRotation((float) theta + 65);
			gunFire.draw((spriteX + gunFireStartAt.x) - gunFireFrameWidth / 2,
					(spriteY + gunFireStartAt.y) - gunFireFrameHeight / 2);
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
