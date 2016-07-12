package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.TextInfo;
import se.BaseUlterior.Game.BreakingPoint;

public abstract class Button extends Component {
	String text;
	private final static float tabSize = 12f;
	private SingleText label;
	private boolean active = true;

	public Button(String text, float width, float height, boolean active) {
		super(new float[] { 0, 0, width, 0, width, height - tabSize, width - tabSize, height, 0, height });
		this.active = active;
		init(text);
	}

	public Button(String text) {
		super(120f, 30f);
		init(text);
	}

	private void init(String text) {
		this.color = Color.gray;
		this.text = text;
	}

	protected void setText(String text) {
		TextInfo buttonText = new TextInfo(getX(), getY());
		label = new SingleText(0, 0, 20, text, getIsActiveColor(active));
		buttonText.singleTexts.add(label);
		BreakingPoint.info.textInfos.add(buttonText);
	}

	private boolean mouseIsOutside = true;

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		Input in = container.getInput();
		float mouseX = in.getMouseX();
		float mouseY = in.getMouseY();
		if (mouseX > this.getMinX() && mouseX < this.getMaxX() && mouseY > this.getMinY() && mouseY < this.getMaxY()) {
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

	private void onMouseOut() {
		if (active)
			label.setColor(Color.black);
	}

	private void onClickImpl() {
		if (active) {
			onClick();
		}
	}

	public abstract void onClick();

	public void onMouseOver() {
		if (active)
			label.setColor(Color.darkGray);
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
		// label.setColor(active ? Color.black : Color.darkGray);
		return active ? Color.black : Color.darkGray;
	}
}
