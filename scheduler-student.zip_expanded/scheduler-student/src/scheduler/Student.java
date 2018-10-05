/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a student.
 * 
 * @author liberato
 *
 */
public class Student {
	/**
	 * 
	 * Instantiates a new Student object. The student's maximum course load must be greater
	 * than zero, and the preferences list must contain at least one course.
	 * 
	 * The preference list is copied into this Student object.
	 * 
	 * @param name        the student's name
	 * @param maxCourses  the maximum number of courses that can be on this student's schedule
	 * @param preferences the student's ordered list of preferred courses
	 * @throws IllegalArgumentException thrown if the maxCourses or preferences are invalid
	 */
	private String name;
	private int maxCourses;
	private List<Course> preferences;
	private List<Course> schedule;
	private List<Course> timeTable;

	
	public Student(String name, int maxCourses, List<Course> preferences) throws IllegalArgumentException {
		if(name.isEmpty() == true || maxCourses <= 0 || preferences.size() < 1) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.maxCourses = maxCourses;
		this.preferences = preferences;
		this.schedule = new ArrayList<Course>();
	}
	
	/**
	 * 
	 * @return the student's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return the student's max course load
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * Returns the student's list of course preferences, ordered from most- to least-desired.
	 * 
	 * This returned object does not share state with the internal state of the Student.
	 * 
	 * @return the student's preference list
	 */
	public List<Course> getPreferences() {
		return preferences;
	}
	
	/**
	 * Returns the student's current schedule.
	 * 
	 * This returned object does not share state with the internal state of the Student.
     *
	 * @return the student's schedule
	 */
	public List<Course> getSchedule() {
		timeTable = new ArrayList<Course>(schedule);
		return timeTable;
	}
	
	public void addClass(Course courseName) {
		schedule.add(courseName);
	}
	
	public void dropClass(Course courseName) {
		schedule.remove(courseName);
	}
	
	public void useless() {
		schedule.clear();
	}
}
