package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.TextInfo;

public abstract class Button extends Component {
	String text;
	private final static int tabSize = 14;
	private SingleText label;
	private boolean active = true;
	public final static int PREFERED_HEIGHT = 30;
	public final static int PREFERED_WIDTH = 260;
	TextInfo buttonText;

	public Button(String text, float width, float height, boolean active) {
		super(new float[] { 0, 0, width, 0, width, height - tabSize, width - tabSize, height, 0, height });
		this.active = active;
		init(text);
	}

	public Button(String text) {
		super(PREFERED_WIDTH, PREFERED_HEIGHT);
		init(text);
	}

	private void init(String text) {
		this.color = Color.white;
		this.text = text;
		padding = 4;
	}

	protected void setText(String text) {
		// buttonText = new TextInfo((GameInfo) IceBreaker.info, getX(), getY(),
		// false);
		label = new SingleText(getX() + padding, getY(), 20, text, getIsActiveColor(active));
		// buttonText.singleTexts.add(label);
		// IceBreaker.info.textInfos.add(buttonText);
	}

	private boolean mouseIsOutside = true;

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		if (isPaused) {
			Input in = container.getInput();
			float mouseX = in.getMouseX();
			float mouseY = in.getMouseY();
			if (mouseX > this.getMinX() && mouseX < this.getMaxX() && mouseY > this.getMinY()
					&& mouseY < this.getMaxY()) {
				if (mouseIsOutside) {
					onMouseOver();
				}
				if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					onClickImpl();
				}
			} else if (!mouseIsOutside) {
				onMouseOut();
				mouseIsOutside = true;
			}
		}
	}

	private void onMouseOut() {
		if (active) {
			setCenterX(getCenterX() - 10);
			// buttonText.setX(buttonText.getX() - 10);
			label.setColor(getIsActiveColor(active));
			label.setX(label.getX() - 10);
		}
	}

	private void onClickImpl() {
		if (active) {
			onClick();
		}
	}

	public abstract void onClick();

	public void onMouseOver() {
		if (active) {
			setCenterX(getCenterX() + 10);
			// buttonText.setX(buttonText.getX() + 10);
			label.setX(label.getX() + 10);
			label.setColor(Constants.THEME_COLOR);
		}
		mouseIsOutside = false;
	}

	@Override
	protected void finalAction() {
		setText(text);
	}

	public void updateText(String value) {
		this.label.setValue(value);
	}

	public Color getIsActiveColor(boolean active) {
		return active ? Color.lightGray : Color.lightGray;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		// super.render(container, graphics);
		// if (!hide) {
		if (isPaused) {
			graphics.setLineWidth(3);
			graphics.setColor(Color.darkGray);
			graphics.draw(this);
			label.drawString();
		}
	}

}
