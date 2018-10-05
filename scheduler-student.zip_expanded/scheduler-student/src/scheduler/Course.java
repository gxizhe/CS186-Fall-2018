/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import java.util.ArrayList;
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
	private String courseNumber;
	private int capacity;
	private List<Student> roster;
	
	public Course(String courseNumber, int capacity) throws IllegalArgumentException {
		if (courseNumber.isEmpty() == true || capacity <= 0) {
			throw new IllegalArgumentException();
		}
		this.courseNumber = courseNumber;
		this.capacity = capacity;
		this.roster = new ArrayList<Student>();
	}
	
	/**
	 * 
	 * @return the capacity of the course
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * 
	 * @return the course number
	 */
	public String getCourseNumber() {
		return courseNumber;
	}

	/**
	 * Returns the list of students enrolled in the course. 
	 * 
	 * This returned object does not share state with the internal state of the Course.
	 * 
	 * @return the list of students currently in the course
	 */
	public List<Student> getRoster() {
		List<Student> listed = new ArrayList<Student>(roster);
		return listed;
	}
	
	public void enroll(Student name) {
		roster.add(name);
	}
	
	public void kickStudent(Student name) {
		roster.remove(name);
	}
}
