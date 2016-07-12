package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Panel extends Component {
	protected Alignment alignmentInContainer = Alignment.LEFT;

	public Panel(float width, float height, Alignment alignment) {
		super(width, height);
		this.alignmentInContainer = alignment;
		color = Color.white;

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}
}
