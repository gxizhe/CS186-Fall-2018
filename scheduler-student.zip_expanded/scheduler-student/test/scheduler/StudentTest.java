/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class StudentTest {

//  uncomment the following if you're trying to diagnose an infinite loop in a test
//	@Rule
//	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private Student joe;
	private List<Course> courseList;
	
	// This method is run before each test, performing initialization
	// on objects for the test.
	@Before
	public void setup() {
		courseList = new ArrayList<>();
		courseList.add(new Course("COMPSCI190D", 120));
		courseList.add(new Course("MATH132", 50));
		courseList.add(new Course("ENGLWRIT112", 20));
		
		joe = new Student("Joe", 4, courseList);
	}
	
	@Test
	public void testGetName() {
		assertEquals("Joe", joe.getName());
	}

	@Test
	public void testGetMaxCourses() {
		assertEquals(4, joe.getMaxCourses());
	}


	@Test
	public void testGetPreferences() {
		assertEquals(courseList, joe.getPreferences());
	}

	@Test
	public void testGetPreferencesNotShared() {
		List<Course> prefs =  joe.getPreferences();
		prefs.clear();
		assertEquals(courseList, joe.getPreferences());
	}
	
	@Test
	public void testGetScheduleEmpty() {
		assertTrue(joe.getSchedule().isEmpty());
	}

	@Test
	public void testGetScheduleEmptyNotShared() {
		List<Course> schedule =  joe.getSchedule();
		schedule.add(new Course("COMPSCI190D", 120));
		assertTrue(joe.getSchedule().isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMaxCourses() {
		Student s = new Student("NoCourses", 0, courseList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyPreferences() {
		Student s = new Student("DontCare", 3, new ArrayList<Course>());
	}
}
