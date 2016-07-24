package se.BaseUlterior.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Panel extends Component {
	protected Alignment alignmentInContainer = Alignment.LEFT;

	public Panel(float width, float height, Alignment alignment) {
		super(width, height);
		this.alignmentInContainer = alignment;

	}

	public Panel(float[] points, Alignment alignment) {
		super(points);
		this.alignmentInContainer = alignment;
	}

	@Override
	public void update(GameContainer container, int arg) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
	}
}
