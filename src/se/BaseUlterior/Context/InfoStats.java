package se.BaseUlterior.Context;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Actions.Action;

public class InfoStats {
	public List<Observer> textInfos = new ArrayList<>();

	public void update(GameContainer container, int arg) {
	}

	// @Override
	// public void render(GameContainer container, Graphics graphics) {
	// for (Observer infos : textInfos) {
	// infos.draw();
	// }
	// }

	public void notifyObservers() {
		for (Observer text : textInfos) {
			text.updateData();
		}
	}

	private int HP;
	private int kills;
	private HIT lastHit;
	private String weapon;
	private Action gameState;

	public Action getGameState() {
		return gameState;
	}

	public void setGameState(Action gameState) {
		this.gameState = gameState;
	}

	// @Override
	// public Impact getImpact(GameObject agileObject) {
	// return null;
	// }

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
		notifyObservers();
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
		notifyObservers();
	}

	public HIT getLastHit() {
		return lastHit;
	}

	public void setLastHit(HIT lastHit) {
		this.lastHit = lastHit;
		notifyObservers();
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
		notifyObservers();
	}

}
