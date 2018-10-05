/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	
	private List<Course> listOfCourses;
	private List<Student> listOfStudents;
	
	/**
	 * Instantiates a new, empty scheduler.
	 */
	public Scheduler() {
		listOfCourses = new ArrayList<Course>();
		listOfStudents = new ArrayList<Student>();
	}
	
	/**
	 * Adds a course to the scheduler.
	 * 
	 * @param course  the course to be added
	 */
	public void addCourse(Course course) {
		listOfCourses.add(course);
	}
	
	/** 
	 * Returns the list of courses that this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 *
	 * @return the list of courses
	 */
	public List<Course> getCourses() {
		List<Course> courseList = new ArrayList<Course>(listOfCourses);
		return courseList;
	}
	
	/**
	 * Adds a student to the scheduler.
	 * 
	 * @param student the student to add
	 */
	public void addStudent(Student student) {
		listOfStudents.add(student);
	}
	
	/**
	 * Returns a list of the students this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 * @return
	 */
	public List<Student> getStudents() {	
		List<Student> studentList = new ArrayList<Student>(listOfStudents);
		return studentList;
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
		Student johnOrJaneDoe;
		Course desiredCourse;
		Course backUpChoice;
		int times = 0;
		for(int i = 0; i < listOfStudents.size(); i++) {
			if(listOfStudents.get(i).getPreferences().size() > times) {
				times = listOfStudents.get(i).getPreferences().size();
			}
		}
		while(true) {
			for(int i = 0; i < times; i++) {
				for(int st = 0; st < listOfStudents.size(); st++) {
					johnOrJaneDoe = listOfStudents.get(st);
					desiredCourse = johnOrJaneDoe.getPreferences().get(i);
					if(johnOrJaneDoe.getMaxCourses() > johnOrJaneDoe.getSchedule().size()) {
						if(listOfCourses.contains(desiredCourse)) {
							if(desiredCourse.getRoster().contains(johnOrJaneDoe) == false) {
								if(desiredCourse.getRoster().size() < desiredCourse.getCapacity()) {
									johnOrJaneDoe.addClass(desiredCourse);
									desiredCourse.enroll(johnOrJaneDoe);
								}
								else {
									for(int rip = i; rip < johnOrJaneDoe.getPreferences().size(); rip++) {
										backUpChoice = johnOrJaneDoe.getPreferences().get(rip);
										if(backUpChoice.getRoster().size() < backUpChoice.getCapacity()) {
											johnOrJaneDoe.addClass(backUpChoice);
											backUpChoice.enroll(johnOrJaneDoe);
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			break;
		}
	}

	/**
	 * Drops a student from a course.
	 * 
	 * @param student
	 * @param course 
	 * @throws IllegalArgumentException  if either the student or the course are not known to this scheduler
	 */
	public void drop(Student student, Course course) throws IllegalArgumentException {
		if(listOfStudents.contains(student) == false || listOfCourses.contains(course) == false) {
			throw new IllegalArgumentException();
		}
		student.dropClass(course);
		course.kickStudent(student);
	}
	
	/**
	 * Drops a student from all of their courses.
	 * 
	 * @param student
	 * @throws IllegalArgumentException  if the student is not known to this scheduler
	 */
	public void unenroll(Student student) throws IllegalArgumentException{
		if(listOfStudents.contains(student) == false) {
			throw new IllegalArgumentException();
		}
		student.useless();
		for(int i = 0; i < listOfCourses.size(); i++) {
			if(listOfCourses.get(i).getRoster().contains(student)) {
				listOfCourses.get(i).kickStudent(student);
			}
		}
	}
}
