package se.BaseUlterior.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Concrete game sprite which can be controlled all by keyboard
 * 
 * @author Johan Akerlund
 */
public class EntitySpriteControlledDesktop extends EntitySpriteControlled {

	public EntitySpriteControlledDesktop() {
		super();
		START_HP = 1000;
		HP = START_HP;
		HPCompare = HP;
		ParallaxPhysicsEngine.gameInfo.setWeapon(aim.getSlug());
		ParallaxPhysicsEngine.gameInfo.setHP(HP);
	}

	@Override
	public void update(GameContainer container, int delta) {

		Input in = container.getInput();

		if (in.isKeyDown(Input.KEY_A)) {
			if (directionIsRight) {
				directionIsRight = false;
			}
			animationMoveLeft.update(delta);
			if (motion.getX() > -MAX_SPEED) {
				motion.add(-JET_POWER * delta, 0.0f);
			}
		} else if (in.isKeyDown(Input.KEY_D)) {
			if (!directionIsRight) {
				directionIsRight = true;
			}
			animationMoveRight.update(delta);
			if (motion.getX() < MAX_SPEED) {
				motion.add(JET_POWER * delta, 0.0f);
			}
		} else {
			animationMoveRight.setCurrentFrame(0);
			animationMoveLeft.setCurrentFrame(0);
		}
		if (in.isKeyDown(Input.KEY_W)) {
			if (motion.getY() > -MAX_SPEED) {
				motion.add(0, -JET_POWER * (1f - motion.y / -MAX_SPEED) * delta);
			}
		} else if (in.isKeyDown(Input.KEY_S)) {
			if (motion.getY() < MAX_SPEED) {
				motion.add(0, JET_POWER * 0.5f * (1f - motion.y / MAX_SPEED) * delta);
			}
		}
		if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			aim.primaryPushed();
			if (!mouseButtonPirmaryDown) {
				mouseButtonPirmaryDown = true;
			}
		} else if (!in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && mouseButtonPirmaryDown) {
			mouseButtonPirmaryDown = false;
			aim.primaryReleased();
		}
		if (in.isKeyPressed(Input.KEY_Q)) {

			int i = aims.indexOf(aim);
			aim.cleanUp();
			aim = i == aims.size() - 1 ? aims.get(0) : aims.get(i + 1);
			aim.onThisWasChoosen();
			ParallaxPhysicsEngine.gameInfo.setWeapon(aim.getSlug());

		}
		float centerX = getCenterX();
		float centerY = getCenterY();

		aim.setAngleToMouse(UlteriorUtils.angleToPoint(centerX, centerY, in.getMouseX(), in.getMouseY()));

		aim.spriteX = centerX;
		aim.spriteY = centerY;

		aim.update(container, delta);

		super.update(container, delta);
		// switchScreens();
		screenFollowPlayer();
	}
}
