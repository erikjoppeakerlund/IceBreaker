package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Utils.UlteriorUtils;

public class GameObjectSpriteDesktop extends GameObjectSprite {

	@Override
	public void update(GameContainer container, int delta) {
		if (BreakingPoint.pause) {
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
			aim.setIsRight(false);
		} else if (in.isKeyDown(Input.KEY_D)) {
			if (!directionIsRight) {
				directionIsRight = true;
			}
			animationMoveRight.update(delta);
			if (motion.getX() < MAX_SPEED) {
				motion.add(speed * delta, 0.0f);
			}
			aim.setIsRight(true);
		} else {
			animationMoveRight.setCurrentFrame(0);
			animationMoveLeft.setCurrentFrame(0);
		}
		if (in.isKeyDown(Input.KEY_W)) {
			if (motion.getY() > -MAX_SPEED) {
				motion.add(0, -speed * delta);
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

		aim.setAngleToMouse(UlteriorUtils.angleToPoint(x, y, in.getMouseX(), in.getMouseY()));

		aim.spriteX = getCenterX();
		aim.spriteY = getCenterY();

		aim.update(container, delta);

		super.update(container, delta);
		// switchScreens();
		screenFollowPlayer();
	}
}
