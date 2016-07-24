package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import se.BaseUlterior.Context.SingleText;

public abstract class Button extends Component {
	String text;
	private final static int tabSize = 14;
	private SingleText label;
	private boolean active = true;
	public final static int PREFERED_HEIGHT = 30;
	public final static int PREFERED_WIDTH = 260;

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
		this.color = new Color(1f, 1f, 1f, 0f);
		this.text = text;
		padding = 4;
	}

	protected void setText(String text) {
		label = new SingleText(getX() + padding, getY(), 20, text, getIsActiveColor(active));
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
			if (active) {
				if (mouseIsOutside) {
					if (color.a > 0.0f)
						color.a -= 0.02f;
				} else {
					if (color.a < 0.4f)
						color.a += 0.02f;
				}
			}
		}
	}

	private void onMouseOut() {
		if (active) {
			setCenterX(getCenterX() - 10);
			label.setX(label.getX() - 10);
			label.setColor(getIsActiveColor(active));
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
			label.setX(label.getX() + 10);
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
		return active ? Color.lightGray : Color.darkGray;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		// if (!hide) {
		if (isPaused) {
			graphics.setLineWidth(3);
			graphics.setColor(Color.darkGray);
			graphics.draw(this);
			label.drawString();
		}
	}

}
