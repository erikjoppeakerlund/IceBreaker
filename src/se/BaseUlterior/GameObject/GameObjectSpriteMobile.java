package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimBlade;
import se.BaseUlterior.Aim.AimGrenade;
import se.BaseUlterior.Aim.AimMachineGun;
import se.BaseUlterior.Aim.AimRifle;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.BreakingPoint;

public class GameObjectSpriteMobile extends GameObjectFalling {

	protected Aim aim = null;
	protected List<Aim> aims = null;
	protected float speed = 0.0038f;
	protected final float MAX_SPEED = 0.49f;
	protected boolean mouseButtonPirmaryDown = false;

	protected boolean right = true;

	protected float JUMP_POWER = -0.46f;
	private SpriteSheet sprite = null;
	protected Animation animationMoveRight = null;
	protected Animation animationMoveLeft = null;
	private static final int SHEET_UPDATE_RATE = 85;

	private boolean directionIsRight = true;

	private SpriteSheet gunFire = null;
	private Animation animationGunfire = null;

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;

	public GameObjectSpriteMobile() {
		super(new Circle(Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT / 2, Constants.SPRITE_RADIUS).getPoints(),
				0.3f);
		this.setCenterX(Constants.CANVAS_WIDTH / 2);
		this.setCenterY(Constants.CANVAS_HEIGHT / 2);
		color = Constants.THEME_COLOR;
		try {
			sprite = new SpriteSheet("res/img/spriteSheet.png", 88, 88);
			animationMoveRight = new Animation(false);
			animationMoveLeft = new Animation(false);
			for (int i = 0; i < 4; i++) {
				animationMoveRight.addFrame(sprite.getSprite(i, 0), SHEET_UPDATE_RATE);
			}
			for (int i = 0; i < 4; i++) {
				animationMoveLeft.addFrame(sprite.getSprite(i, 0).getFlippedCopy(true, false), SHEET_UPDATE_RATE);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			gunFire = new SpriteSheet("res/img/GUNFIREsimple.png", 96, 96);
			animationGunfire = new Animation(gunFire, 10);
			animationGunfire.setAutoUpdate(false);
			gunFireFrameWidth = animationGunfire.getCurrentFrame().getWidth();
			gunFireFrameHeight = animationGunfire.getCurrentFrame().getHeight();
			gunFire.setCenterOfRotation(gunFireFrameWidth / 2f, gunFireFrameHeight / 2f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		initAims();
	}

	private void initAims() {
		aims = new ArrayList<Aim>();
		aims.add(new AimBlade());
		aims.add(new AimGrenade());
		aims.add(new AimRifle(animationGunfire));
		aims.add(new AimMachineGun(animationGunfire));
		aim = aims.get(2);
	}

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
				motion.add(0, -speed * delta);
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
			aim = i == aims.size() - 1 ? aims.get(0) : aims.get(i + 1);
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
		// switchScreens();
		screenFollowPlayer();
	}

	private void screenFollowPlayer() {
		float moveScreenX = getCenterX() - Constants.CANVAS_WIDTH / 2f;
		float moveScreenY = getCenterY() - Constants.CANVAS_HEIGHT / 2f;

		if (motion.x < 0 && BreakingPoint.currentX <= 0
				|| motion.x > 0 && BreakingPoint.currentX + Constants.CANVAS_WIDTH >= Constants.CANVAS_WIDTH_FULL
				|| motion.x > 0 && getCenterX() < Constants.CANVAS_WIDTH / 2 || motion.x < 0 && getCenterX()
						+ BreakingPoint.currentX > Constants.CANVAS_WIDTH_FULL - Constants.CANVAS_WIDTH / 2) {
			moveScreenX = 0;
		}

		if (motion.y < 0 && BreakingPoint.currentY <= 0
				|| motion.y > 0 && BreakingPoint.currentY + Constants.CANVAS_HEIGHT >= Constants.CANVAS_HEIGHT_FULL
				|| motion.y > 0 && getCenterY() < Constants.CANVAS_HEIGHT / 2 || motion.y < 0 && getCenterY()
						+ BreakingPoint.currentY > Constants.CANVAS_HEIGHT_FULL - Constants.CANVAS_HEIGHT / 2) {
			moveScreenY = 0;
		}

		BreakingPoint.moveScreen(moveScreenX, moveScreenY);

	}

	private void switchScreens() {
		float moveScreenX = 0;
		float moveScreenY = 0;

		float centerX = getCenterX();
		float centerY = getCenterY();

		if (!(centerX > Constants.CANVAS_WIDTH
				&& BreakingPoint.currentX >= Constants.CANVAS_WIDTH_FULL - Constants.CANVAS_WIDTH
				|| centerX < 0 && BreakingPoint.currentX <= 0)) {
			if (centerX > Constants.CANVAS_WIDTH) {
				moveScreenX = Constants.CANVAS_WIDTH;
			} else if (centerX < 0) {
				moveScreenX = -Constants.CANVAS_WIDTH;
			}
		}

		if (!(centerY > Constants.CANVAS_HEIGHT
				&& BreakingPoint.currentY >= Constants.CANVAS_HEIGHT_FULL - Constants.CANVAS_HEIGHT
				|| centerY < 0 && BreakingPoint.currentY <= 0)) {
			if (centerY > Constants.CANVAS_HEIGHT) {
				moveScreenY = Constants.CANVAS_HEIGHT;
			} else if (centerY < 0) {
				moveScreenY = -Constants.CANVAS_HEIGHT;
			}
		}

		BreakingPoint.moveScreen(moveScreenX, moveScreenY);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		if (directionIsRight) {
			animationMoveRight.draw(getX(), getY());
		} else {
			animationMoveLeft.draw(getX(), getY());
		}
		// graphics.fill(this);
		aim.render(container, graphics);
	}

}
