/*
 * Copyright 2017 Marc Liberatore.
 */

package simulator;

import static org.junit.Assert.*;

import org.junit.Test;


public class RoadMapTest {
	@Test
	public void testConstructor() {
		RoadMap r = new RoadMap(1,1);
		assertNotNull(r);
	}

	@Test 
	public void testFromString() {
		RoadMap r = RoadMap.fromString("... " +
									   ".X. " +
				                       "X.X");
		assertTrue(r.isRoad(0, 0));
		assertTrue(r.isRoad(1, 0));
		assertTrue(r.isRoad(2, 0));
		assertTrue(r.isRoad(0, 1));
		assertFalse(r.isRoad(1, 1));
		assertTrue(r.isRoad(2, 1));
		assertFalse(r.isRoad(0, 2));
		assertTrue(r.isRoad(1, 2));
		assertFalse(r.isRoad(2, 2));
	}
	
	@Test
	public void testToString() {
		RoadMap r = RoadMap.fromString("X.X ... .X.");
		assertEquals("X.X\n...\n.X.", r.toString());
	}
	
	@Test
	public void testSetRoad() {
		RoadMap r = new RoadMap(2,2);
		assertFalse(r.isRoad(0, 1));
		r.setRoad(0, 1, true);
		assertTrue(r.isRoad(0, 1));
		r.setRoad(0, 1, false);
		assertFalse(r.isRoad(0, 1));
	}
}
