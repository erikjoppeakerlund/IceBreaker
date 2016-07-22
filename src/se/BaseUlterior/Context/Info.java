package se.BaseUlterior.Context;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Physics.Impact;

public abstract class Info extends GameObject {
	// @Override
	// public boolean isSolid() {
	// return true;
	// }
	public Info() {
		super(new float[0], false, false, false, false, true, true);
		// super();
		// isSolid = true;
		// forceRender = true;
	}

	public List<TextInfo> textInfos = new ArrayList<>();

	public void update(GameContainer container, int arg) {
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		for (TextInfo infos : textInfos) {
			infos.draw();
		}
	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		return null;
	}

}
