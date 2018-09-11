/*
 * Copyright 2017 Marc Liberatore.
 */

package simulator;

/**
 * A map of road(s), where each cell is numbered with an x and y coordinate. The
 * north-western-most corner is at location(0,0). Moving south means increasing
 * the y value; moving east means increasing the x value. Each cell either is or
 * is not a road.
 * 
 * @author liberato
 *
 */
public class RoadMap {
	public final int xSize;
	public final int ySize;
	private final boolean[][] isRoad;

	public RoadMap(int x, int y) {
		isRoad = new boolean[x][y];
		xSize = x;
		ySize = y;
	}

	/**
	 * Use this method to create a RoadMap from a String. Use 'X' for non-road and
	 * '.' for road.
	 * 
	 * For example, the string
	 * 
	 * "XXX 
	 *  X.. 
	 *  X.X"
	 * 
	 * (including whitespace!) creates a 3x3 map, where there is a short road
	 * that travels north from the center of the bottom of the map, and then
	 * turns east to the center of the right-hand side of the map.
	 * 
	 * @param s a string representing a map of roads
	 * @return a new RoadMap instance
	 */
	public static RoadMap fromString(String s) {
		String[] lines = s.trim().split("\\s+");
		final int xSize = lines[0].length();
		final int ySize = lines.length;

		RoadMap roadMap = new RoadMap(xSize, ySize);
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (lines[y].charAt(x) == '.') {
					roadMap.isRoad[x][y] = true;
				}
			}
		}

		return roadMap;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (isRoad[x][y]) {
					sb.append('.');
				} else {
					sb.append('X');
				}
			}
			sb.append('\n');
		}
		return sb.substring(0, sb.length() - 1);
	}

	public boolean isRoad(int x, int y) {
		return isRoad[x][y];
	}

	public void setRoad(int x, int y, boolean isRoad) {
		this.isRoad[x][y] = isRoad;
	}

}
