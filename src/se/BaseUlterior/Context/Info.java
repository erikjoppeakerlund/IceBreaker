package se.BaseUlterior.Context;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Physics.Impact;

public abstract class Info extends GameObject {
	public Info() {
		super(new float[0], false, false, false, false, true, true);
	}

	public List<StatsObserver> textInfos = new ArrayList<>();

	public void update(GameContainer container, int arg) {
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		for (StatsObserver infos : textInfos) {
			infos.draw();
		}
	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		return null;
	}

	public void notifyObservers() {
		for (StatsObserver text : textInfos) {
			text.updateData();
		}
	}

}
