package se.BaseUlterior.GUI;

import se.BaseUlterior.Game.BreakingPoint;

public class ToolBox extends Panel {

	public ToolBox(Alignment alignment) {
		super(190, 300, alignment);

		this.add(new Button("MODE", 180f, 30f) {

			@Override
			public void onClick() {
				BreakingPoint.insertMode = !BreakingPoint.insertMode;
			}
		});
		this.add(new Button("GROUND", 180f, 30f) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		this.add(new Button("FRICTION", 180f, 30f) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		this.add(new Button("GRAVITY", 180f, 30f) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		this.add(new Button("STATS", 180f, 30f) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});

		this.add(new Button("SETTINGS", 180f, 30f) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		this.add(new Button("LEVELS", 180f, 30f) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		this.add(new Button("COUNTER FORCES", 180f, 30f) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
	}

}
