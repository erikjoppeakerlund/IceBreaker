package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;

public class Panel extends Component {
	protected Alignment alignmentInContainer = Alignment.LEFT;

	public Panel(Alignment alignment) {
		super(500f, 500f);
		this.alignmentInContainer = alignment;
		color = Color.white;
		this.add(new Button("[GROUND]"));
		this.add(new Button("[FRICTION]"));
		this.add(new Button("[GRAVITY]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));
		this.add(new Button("[TEST]"));

	}

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}
}
