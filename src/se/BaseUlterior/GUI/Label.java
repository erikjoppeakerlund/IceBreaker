package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Context.SingleText;

public class Label extends Component {

	private SingleText label;

	public Label(SingleText label, String value, float width, float height) {

		super(width, height);

		label = new SingleText(getX() + padding, getY(), 20, value, Color.black, "plain");
		this.label = label;
	}

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}

	public void setLabel(String value) {
		label.setValue(value);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.fill(this);
		label.drawString();
	}

}
