/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class CourseTest {
	
//  uncomment the following if you're trying to diagnose an infinite loop in a test
//	@Rule
//	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private Course course;
	private String courseNumber;
	private int capacity;
	
	// This method is run before each test, performing initialization
	// on objects for the test.
	@Before
	public void setup() {
		courseNumber = "COMPSCI190D";
		capacity = 120;		
		course = new Course(courseNumber, capacity);
	}
	
	@Test
	public void testGetCourseNumber() {
		assertEquals(courseNumber, course.getCourseNumber());
	}

	@Test
	public void testGetCapacity() {
		assertEquals(capacity, course.getCapacity());
	}
	
	@Test
	public void testGetRosterEmpty() {
		assertTrue(course.getRoster().isEmpty());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCourseNumber() {
		Course c = new Course("", 20);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCapacity() {
		Course c = new Course("COMPSCI190D", 0);
	}
}
