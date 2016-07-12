package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;

public class Panel extends Component {
	protected Alignment alignmentInContainer = Alignment.LEFT;

	public Panel(Alignment alignment) {
		super(400f, 500f);
		this.alignmentInContainer = alignment;
		color = Color.white;
		this.add(new Button("GROUND", 120f, 30f));
		this.add(new Button("FRICTION", 120f, 30f));
		this.add(new Button("GRAVITY", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
		this.add(new Button("test", 120f, 30f));
	}

	@Override
	protected void finalAction() {
		// TODO Auto-generated method stub

	}
}
