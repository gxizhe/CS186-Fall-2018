/*
 * Copyright 2017 Marc Liberatore.
 */

package simulator;

import processing.core.PApplet;

/**
 * This class, when run, lets you interactively test your Bus and RoadMap code.
 * 
 * Use the left mouse button to add/remove roads (dark squares).
 * 
 * Use the right mouse button to add a bus (represented as a number).
 * 
 * Use delete (or backspace) to remove the most recently added bus.
 * 
 * Use spacebar to advance the simulation one step (that is, tell the buses to move()).
 * 
 * @author liberato
 *
 */
public class BusVisualizer extends PApplet {
	static RoadMap roadMap;
	static Bus[] buses;
	static int busCount;

	public static void main(String[] args) {
		PApplet.main("simulator.BusVisualizer");
	}

	public void settings() {
		roadMap = new RoadMap(10, 10); // you could replace this with a call to
		                               // RoadMap.fromString(); see the tests for an example.
		
		buses = new Bus[5];
		busCount = 0;
		size(roadMap.xSize * 20, roadMap.ySize * 20);
	}
	
	public void setup() {
	}

	public void draw() {
		background(223);

		// first draw the road/non-road
		stroke(255);
		for (int y = 0; y < roadMap.ySize; y++) {
			for (int x = 0; x < roadMap.xSize; x++) {
				if (roadMap.isRoad(x, y)) {
					fill(31);
				}
				else {
					fill(223);
				}
			    rect(x*20,y*20,20,20); 
			}
		}
		
		// now draw the buses (numbers)
		fill(255);
		stroke(255);
		for (int i = 0; i < busCount; i++) {
			Bus b = buses[i];
			text(Integer.toString(b.number), b.getX()*20, b.getY()*20, 20, 20); 					
		}
	}
	
	public void keyPressed() {
		if (key == ' ') {
			for (int i = 0; i < busCount; i++) {
				buses[i].move();
			}
		} 
		else if (key == BACKSPACE || key == DELETE) {
			if (busCount > 0) {
				busCount--;
				buses[busCount] = null;
			}
		}
	}
	
	public void mousePressed() {
		int x = mouseX / 20;
		int y = mouseY / 20;
		if (mouseButton == LEFT) {
			boolean isClear = true;
			for (int i = 0; i < busCount; i++) {
				Bus b = buses[i];
				if (b.getX() == x && b.getY() == y) {
					isClear = false;
					break;
				}
			}
			if (isClear) {
				roadMap.setRoad(x, y, !roadMap.isRoad(x, y));
			}
		}
		
		if (mouseButton == RIGHT) {
			// is there space for a new bus and is the cell a road?
			if ((busCount < buses.length) && (roadMap.isRoad(x, y))) {
				// and, is it clear (no bus)?
				boolean isClear = true;
				for (int i = 0; i < busCount; i++) {
					Bus b = buses[i];
					if (b.getX() == x && b.getY() == y) {
						isClear = false;
						break;
					}
				}
				if (isClear) {
					buses[busCount] = new Bus(busCount, roadMap, x, y);
					busCount++;
				}
			}
		}
	}
}
