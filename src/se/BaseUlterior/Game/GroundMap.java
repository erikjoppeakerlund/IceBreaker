package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuilderForce;
import se.BaseUlterior.GameObject.WorldBuilderGroundSolid;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class GroundMap {
	public List<GameObject> levelPieces;

	public GroundMap() {
		levelPieces = new ArrayList<>();

		// ______________________________________________________

		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
				Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0.0f };

		WorldBuilderForce generalGravity = new WorldBuilderForce(wholeScene, 0, Constants.GENERAL_GRAVITY,
				Color.transparent);

		levelPieces.add(generalGravity);

		levelPieces.add(new WorldBuilderLiquid(wholeScene, Density.AIR, true));

		float fat = 150.0f;

		float BOTTOM = Constants.CANVAS_HEIGHT_FULL;

		float[] wallScene = new float[] { 0.0f - fat, 0.0f - fat, Constants.CANVAS_WIDTH_FULL + fat, 0.0f - fat,
				Constants.CANVAS_WIDTH_FULL + fat, BOTTOM + fat, 0.0f - fat, BOTTOM + fat, 0.0f - fat, 0.0f - fat, 0.0f,
				0.0f, Constants.CANVAS_WIDTH_FULL, 0.0f, Constants.CANVAS_WIDTH_FULL, BOTTOM, 0.0f, BOTTOM, 0.0f,
				0.0f };

		GameObject wall = new WorldBuilderGroundSolid(wallScene);

		levelPieces.add(wall);

	}

}
