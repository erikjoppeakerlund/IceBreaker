package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.Game.IceBreaker;

/**
 * Concrete game sprite which can be controlled both keyboard and mouse input.
 * 
 * @author Johan Akerlund
 */

public class GameObjectSpriteMobile extends GameObjectSprite {
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
				motion.add(0, JET_POWER * 0.79f * (1f - motion.y / MAX_SPEED) * delta);
			}
		}
		if (in.isKeyPressed(Input.KEY_SPACE) || in.isKeyPressed(Input.KEY_E)) {
			aim.primaryPushed();
			if (!mouseButtonPirmaryDown) {
				mouseButtonPirmaryDown = true;
			}
		} else if (!(in.isKeyDown(Input.KEY_SPACE) || in.isKeyDown(Input.KEY_E)) && mouseButtonPirmaryDown) {
			mouseButtonPirmaryDown = false;
			aim.primaryReleased();
		}
		if (in.isKeyDown(Input.KEY_UP)) {
			aim.angleUp();
		} else if (in.isKeyDown(Input.KEY_DOWN)) {
			aim.angleDown();
		}
		if (in.isKeyPressed(Input.KEY_Q)) {
			int i = aims.indexOf(aim);
			aim.cleanUp();
			int currentAngle = aim.getCurrentThete();
			aim = i == aims.size() - 1 ? aims.get(0) : aims.get(i + 1);
			aim.setAngleToMouse(currentAngle);
			aim.onThisWasChoosen();

		}
		if (in.isKeyPressed(Input.KEY_LEFT)) {
			aim.setIsRight(false);

		} else if (in.isKeyPressed(Input.KEY_RIGHT)) {
			aim.setIsRight(true);

		}

		aim.spriteX = getCenterX();
		aim.spriteY = getCenterY();

		aim.update(container, delta);

		super.update(container, delta);
		screenFollowPlayer();
	}

}
