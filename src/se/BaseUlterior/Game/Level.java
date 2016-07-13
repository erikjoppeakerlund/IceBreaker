package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuilderGround;

public class Level {
	public List<GameObject> levelPieces;

	public Level() {
		levelPieces = new ArrayList<>();

		GameObject worldPiece1 = new WorldBuilderGround(new float[] { 4155, 2592, 4089, 1374, 3846, 1716, 3633, 2001,
				3399, 2175, 3048, 2295, 2712, 2349, 2274, 2364, 1932, 2379, 1821, 2490, 1491, 2511, 1782, 2619, 2385,
				2574, 3249, 2556, 3627, 2559, 3912, 2541, });

		levelPieces.add(worldPiece1);

		GameObject worldPiece2 = new WorldBuilderGround(new float[] { 63, 1755, 33, 2007, 63, 2310, 39, 2526, 255, 2586,
				1011, 2574, 1377, 2586, 1257, 2238, 1290, 2031, 2094, 2025, 1452, 1875, 1083, 1677, 756, 1395, 720,
				1059, 783, 744, 633, 969, 552, 1443, 480, 1707, 249, 1680, 87, 1218, 33, 987, 21, 1383, 72, 1554, });

		levelPieces.add(worldPiece2);
		GameObject worldPiece3 = new WorldBuilderGround(new float[] { 3891, 624, 3870, 417, 3816, 225, 3579, 45, 3162,
				45, 2676, 48, 2163, 135, 1920, 375, 2040, 588, 2265, 513, 2463, 432, 2727, 399, 3015, 420, 3210, 522,
				3426, 603, 3549, 783, 3654, 1026, 3660, 1404, 3864, 1236, 3846, 876, });

		levelPieces.add(worldPiece3);

		GameObject worldPiece5 = new WorldBuilderGround(
				new float[] { 2691, 1809, 2796, 1806, 2841, 1887, 3246, 1968, 3585, 1881, 3687, 1710, 3426, 1797, 3165,
						1788, 2871, 1683, 2619, 1575, 2454, 1626, 2106, 1626, 1893, 1653, 2082, 1779, 2622, 1767, });

		levelPieces.add(worldPiece5);
		GameObject worldPiece6 = new WorldBuilderGround(new float[] { 393, 81, 210, 117, 135, 273, 99, 498, 411, 543,
				405, 348, 516, 309, 804, 276, 1173, 249, 1440, 243, 1584, 162, 972, 162, 492, 162, });

		levelPieces.add(worldPiece6);
	}
}
