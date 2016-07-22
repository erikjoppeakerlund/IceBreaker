package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class Panel extends Component {
	protected Alignment alignmentInContainer = Alignment.LEFT;

	public Panel(float width, float height, Alignment alignment) {
		super(width, height);
		this.alignmentInContainer = alignment;
		this.color = Color.transparent;

	}

	public Panel(float[] points, Alignment alignment) {
		super(points);
		this.alignmentInContainer = alignment;
		this.color = Color.transparent;
	}

	@Override
	public void update(GameContainer container, int arg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}
}
