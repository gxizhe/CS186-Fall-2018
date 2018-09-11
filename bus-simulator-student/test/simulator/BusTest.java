/*
 * Copyright 2017 Marc Liberatore.
 */

package simulator;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class BusTest {
	@Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds 
	
	@Test
	public void testConstructor() {
		Bus b = new Bus(1, new RoadMap(0, 0), 2, 3);
		assertEquals(1, b.number);
		assertEquals(2, b.getX());
		assertEquals(3, b.getY());
	}

	@Test
	public void testMoveNorthFromStop() {
		Bus b = new Bus(0, RoadMap.fromString("X.X X.X X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
	}
	
	@Test
	public void testMoveNorthTwice() {
		Bus b = new Bus(0, RoadMap.fromString("X.X X.X X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());		
	}

	@Test
	public void testMoveNorthThenEastOneWay() {
		Bus b = new Bus(0, RoadMap.fromString("XXX X.. X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());		
	}
	
	@Test
	public void testMoveSouthThenWestAtTee() {
		Bus b = new Bus(0, RoadMap.fromString("X.X ... XXX"), 1, 0);
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(0, b.getX());
		assertEquals(1, b.getY());		
	}

	@Test
	public void testSmallWorldStuck() {
		Bus b = new Bus(0, RoadMap.fromString("."), 0, 0);
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
	}

	@Test
	public void testLessSmallWorldStuck() {
		Bus b = new Bus(0, RoadMap.fromString("XXX X.X XXX"), 1, 1);
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
	}
	
	@Test
	public void testLessSmallWorldLoopCW() {
		Bus b = new Bus(0, RoadMap.fromString("... .X. ..."), 1, 0);
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
	}	
	
	@Test
	public void testLessSmallWorldLoopCCW() {
		Bus b = new Bus(0, RoadMap.fromString("... .X. ..."), 0, 0);
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
	}
	
	@Test
	public void testZigZag() {
		Bus b = new Bus(0, RoadMap.fromString("..X X.. XX."), 0, 0);
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
	}

	@Test
	public void testFigureEight() {
		Bus b = new Bus(0, RoadMap.fromString("XX... XX.X. ..... .X.XX ...XX"), 4, 0);
		assertEquals(4, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(4, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(4, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(3, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(3, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(4, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(4, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(4, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(3, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(3, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(4, b.getX());
		assertEquals(0, b.getY());
	}
}
