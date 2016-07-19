package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.Game.IceBreaker;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Concrete game sprite which can be controlled all by keyboard. FIXME: enable mobile touch input.
 * 
 * @author Johan Akerlund
 */
public class GameObjectSpriteDesktop extends GameObjectSprite {

	@Override
	public void update(GameContainer container, int delta) {
		if (IceBreaker.pause) {
			return;
		}

		Input in = container.getInput();

		if (in.isKeyDown(Input.KEY_A)) {
			if (directionIsRight) {
				directionIsRight = false;
			}
			animationMoveLeft.update(delta);
			if (motion.getX() > -MAX_SPEED) {
				motion.add(-speed * delta, 0.0f);
			}
		} else if (in.isKeyDown(Input.KEY_D)) {
			if (!directionIsRight) {
				directionIsRight = true;
			}
			animationMoveRight.update(delta);
			if (motion.getX() < MAX_SPEED) {
				motion.add(speed * delta, 0.0f);
			}
		} else {
			animationMoveRight.setCurrentFrame(0);
			animationMoveLeft.setCurrentFrame(0);
		}
		if (in.isKeyDown(Input.KEY_W)) {
			if (motion.getY() > -MAX_SPEED) {
				motion.add(0, -JUMP_POWER * delta);
			} else {
				motion.set(motion.x, -MAX_SPEED);
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
