/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import java.util.List;

public class Scheduler {
	/**
	 * Instantiates a new, empty scheduler.
	 */
	public Scheduler() {
	}
	
	/**
	 * Adds a course to the scheduler.
	 * 
	 * @param course  the course to be added
	 */
	public void addCourse(Course course) {
	}
	
	/** 
	 * Returns the list of courses that this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 *
	 * @return the list of courses
	 */
	public List<Course> getCourses() {
		return null;
	}
	
	/**
	 * Adds a student to the scheduler.
	 * 
	 * @param student the student to add
	 */
	public void addStudent(Student student) {
	}
	
	/**
	 * Returns a list of the students this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 * @return
	 */
	public List<Student> getStudents() {	
		return null;
	}
	
	/**
	 * Assigns all students to courses in the following manner:
	 * 
	 * For a given student, check their list of preferred courses. Add them to the course that:
	 * 	 - exists in the scheduler's list of courses
	 *   - the student most prefers (that is, comes first in their preference list)
	 *   - the student is not not already enrolled in
	 *   - and is not full (in other words, at capacity)
	 * Adds courses to the *end* of the student's current list of classes. Adds students to 
	 * the *end* of the course's roster.
	 *   
	 * Repeat this process for each student, one-by-one; each student will now have one course,
	 * usually (but not always) their most preferred course.
	 * 
	 * Then repeat this whole process (adding one course per student, when possible, proceeding
	 * round-robin among students), until there is nothing left to do: Students might 
	 * all be at their maximum number of courses, or there may be no available seats in courses 
	 * that students want.
	 */
	public void assignAll() {
	}

	/**
	 * Drops a student from a course.
	 * 
	 * @param student
	 * @param course 
	 * @throws IllegalArgumentException  if either the student or the course are not known to this scheduler
	 */
	public void drop(Student student, Course course) throws IllegalArgumentException {
	}
	
	/**
	 * Drops a student from all of their courses.
	 * 
	 * @param student
	 * @throws IllegalArgumentException  if the student is not known to this scheduler
	 */
	public void unenroll(Student student) throws IllegalArgumentException{
	}
}
