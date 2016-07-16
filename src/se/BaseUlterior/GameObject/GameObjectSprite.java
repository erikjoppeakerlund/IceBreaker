package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimBlade;
import se.BaseUlterior.Aim.AimGrenade;
import se.BaseUlterior.Aim.AimMachineGun;
import se.BaseUlterior.Aim.AimRifle;
import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class GameObjectSprite extends GameObjectFalling {

	protected Aim aim = null;
	protected List<Aim> aims = null;
	protected float speed = 0.0006f;
	protected final float MAX_SPEED = 0.31f;
	protected boolean mouseButtonPirmaryDown = false;

	protected boolean right = true;

	protected float JUMP_POWER = -0.41f;
	private SpriteSheet sprite = null;
	protected Animation animationMoveRight = null;
	protected Animation animationMoveLeft = null;
	private static final int SHEET_UPDATE_RATE = 85;

	private boolean directionIsRight = true;

	private SpriteSheet gunFire = null;
	private Animation animationGunfire = null;

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;

	public GameObjectSprite(float[] nodes, float bouncyness) {
		super(nodes, bouncyness);
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
		aim = aims.get(1);
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (BreakingPoint.insertMode) {
			return;
		}

		Input in = container.getInput();

		float mouseX = in.getMouseX();
		float mouseY = in.getMouseY();

		if (Mouse.getDWheel() > 0) {
			int i = aims.indexOf(aim);
			aim.cleanUp();
			aim = i == aims.size() - 1 ? aims.get(0) : aims.get(i + 1);
			aim.onThisWasChoosen();

		} else if (Mouse.getDWheel() < 0) {
			int i = aims.indexOf(aim);
			aim = i == 0 ? aims.get(aims.size() - 1) : aims.get(i - 1);
		}
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
			setCenterY(getCenterY() - 1);
			motion.set(new Vector2(motion.getX(), JUMP_POWER));

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

		float angleToPoint = UlteriorUtils.angleToPoint(getCenterX(), getCenterY(), mouseX, mouseY);

		aim.setPosition(getCenterX(), getCenterY());

		aim.setAngleToMouse(angleToPoint);

		aim.update(container, delta);

		super.update(container, delta);

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
