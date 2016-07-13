package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuiderForce;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class Level {
	public List<GameObject> levelPieces;

	Color colorGravity = new Color(0.7f, 0.7f, 1.0f, 0.30f);

	public Level() {
		levelPieces = new ArrayList<>();

		GameObject worldPieceLava = new WorldBuilderLiquid(new float[] { 0, Constants.CANVAS_HEIGHT_FULL,
				Constants.CANVAS_WIDTH_FULL, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
				Constants.CANVAS_HEIGHT * 2.5f, 0, Constants.CANVAS_HEIGHT * 2.5f }, Density.LAVA);

		levelPieces.add(worldPieceLava);

		levelPieces.add(
				new WorldBuiderForce(new float[] { 3148, 679, 4471, 1270, 4507, 49, 2541, 39, 1956, 181, 2966, 326, },
						0.0f, -Constants.GENERAL_GRAVITY * 1.2f, colorGravity));

		levelPieces.add(new WorldBuilderGround(new float[] { 3570, 2610, 3821, 2541, 3425, 2570, 3458, 2772, 3742, 2739,
				4491, 2719, 4422, 1128, 4098, 1775, 3781, 2069, 3438, 2237, 3052, 2392, 2567, 2478, 3019, 2567, 3214,
				2593, 3257, 2814, 3636, 2818, 3722, 2623, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 63, 1755, 33, 2007, 63, 2310, 39, 2526, 255, 2586, 1011,
				2574, 1377, 2586, 1257, 2238, 1290, 2031, 2094, 2025, 1452, 1875, 1083, 1677, 756, 1395, 720, 1059, 783,
				744, 633, 969, 552, 1443, 480, 1707, 249, 1680, 87, 1218, 33, 987, 21, 1383, 72, 1554, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 2535, 1422, 2589, 1410, 2913, 1305, 3267, 1101, 3240, 822,
				3054, 1011, 2622, 1002, 2199, 873, 2013, 1095, 1473, 1086, 1002, 873, 1281, 1149, 1551, 1488, 1998,
				1497, 2283, 1386, 2415, 1386, }));

		levelPieces.add(new WorldBuilderGround(
				new float[] { 2691, 1809, 2796, 1806, 2841, 1887, 3246, 1968, 3585, 1881, 3687, 1710, 3426, 1797, 3165,
						1788, 2871, 1683, 2619, 1575, 2454, 1626, 2106, 1626, 1893, 1653, 2082, 1779, 2622, 1767, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 393, 81, 210, 117, 135, 273, 99, 498, 411, 543, 405, 348,
				516, 309, 804, 276, 1173, 249, 1440, 243, 1584, 162, 972, 162, 492, 162, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 4273, 429, 4319, 735, 4164, 405, 4339, 330, 4441, 42, 3049,
				39, 3352, 419, 3781, 280, 4098, 508, 4095, 739, 4560, 1082, 4468, 224,

		}, 20.0f));

	}
}
