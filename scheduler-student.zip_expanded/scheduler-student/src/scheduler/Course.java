/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import java.util.List;

/**
 * A class representing a Course.
 * 
 * @author liberato
 *
 */
public class Course {
	/**
	 * Instantiates a new Course object. The course number must be non-empty, and the 
	 * capacity must be greater than zero.
	 * @param courseNumber a course number, like "COMPSCI190D"
	 * @param capacity     the maximum number of students that can be in the class
	 * @throws IllegalArgumentException thrown if the courseNumber or capacity are invalid
	 */
	public Course(String courseNumber, int capacity) throws IllegalArgumentException {
	}
	
	/**
	 * 
	 * @return the capacity of the course
	 */
	public int getCapacity() {
		return -1;
	}
	
	/**
	 * 
	 * @return the course number
	 */
	public String getCourseNumber() {
		return null;
	}

	/**
	 * Returns the list of students enrolled in the course. 
	 * 
	 * This returned object does not share state with the internal state of the Course.
	 * 
	 * @return the list of students currently in the course
	 */
	public List<Student> getRoster() {
		return null;
	}
}
