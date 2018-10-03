/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class SchedulerTest {

	// uncomment the following if you're trying to diagnose an infinite loop in a
	// test
	// @Rule
	// public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private Scheduler scheduler;
	
	private Scheduler singleScheduler;
	private Course courseOne;
	private Student studentOne;

	private Course a;
	private Course b;
	private Course c;
	private List<Course> listA;
	private List<Course> listB;
	private List<Course> listAB;
	private List<Course> listBA;	
	private List<Course> listAC;
	private List<Course> listCA;	

	// This method is run before each test, performing initialization
	// on objects for the test.
	@Before
	public void setup() {
		scheduler = new Scheduler();
		courseOne = new Course("ONE1", 1);
		List<Course> l = new ArrayList<>();
		l.add(courseOne);
		studentOne = new Student("one", 1, l);
		singleScheduler = new Scheduler();
		singleScheduler.addStudent(studentOne);
		singleScheduler.addCourse(courseOne);

		a = new Course("ANTHRO100", 2);
		b = new Course("BIO100", 2);
		c = new Course("COMM100", 1);
		
		listA = Arrays.asList(new Course[] {a});
		listB = Arrays.asList(new Course[] {b});
		listAB = Arrays.asList(new Course[] {a, b});
		listBA = Arrays.asList(new Course[] {b, a});
		listAC = Arrays.asList(new Course[] {a, c});
		listCA = Arrays.asList(new Course[] {c, a});
	}

	private static <E> void checkList(List<E> expected, List<E> actual) {
		assertEquals(expected.size(), actual.size());
		for (E e: expected) {
			assertTrue(actual.contains(e));
		}
	}
	
	@Test
	public void testGetCoursesEmpty() {
		assertTrue(scheduler.getCourses().isEmpty());
	}

	@Test
	public void testGetStudentsEmpty() {
		assertTrue(scheduler.getStudents().isEmpty());
	}

	@Test
	public void testAddOneCourse() {
		assertEquals(1, singleScheduler.getCourses().size());
		assertTrue(singleScheduler.getCourses().contains(courseOne));
	}

	@Test
	public void testAddOneStudent() {
		assertEquals(1, singleScheduler.getStudents().size());
		assertTrue(singleScheduler.getStudents().contains(studentOne));
	}

	@Test
	public void testGetCoursesNotShared() {
		List<Course> l = singleScheduler.getCourses();
		l.clear();
		assertTrue(singleScheduler.getCourses().contains(courseOne));
	}

	@Test
	public void testGetStudentsNotShared() {
		List<Student> l = singleScheduler.getStudents();
		l.clear();
		assertTrue(singleScheduler.getStudents().contains(studentOne));
	}
	
	@Test
	public void testSingleCourse() {		
		Student s = new Student("s", 1, listA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		assertEquals(listA, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertEquals(listA, s.getSchedule());
	}
	
	@Test
	public void testDropSimple() {		
		Student s = new Student("s", 1, listA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.assignAll();
		scheduler.drop(s, a);
		
		assertEquals(listS, scheduler.getStudents());
		assertEquals(listA, scheduler.getCourses());
		assertTrue(s.getSchedule().isEmpty());
		assertTrue(a.getRoster().isEmpty());
	}

	@Test
	public void testSingleUnavailableCourse() {		
		Student s = new Student("s", 1, listA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		assertEquals(Arrays.asList(new Course[] {b}), scheduler.getCourses());
		assertTrue(a.getRoster().isEmpty());
		assertTrue(b.getRoster().isEmpty());
	}
	
	@Test
	public void testPreferFirst() {		
		Student s = new Student("s", 1, listAB);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listAB, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertEquals(listA, s.getSchedule());
		assertTrue(b.getRoster().isEmpty());
	}

	@Test
	public void testPreferSecond() {		
		Student s = new Student("s", 1, listBA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listBA, scheduler.getCourses());
		assertEquals(listS, b.getRoster());
		assertEquals(listB, s.getSchedule());
		assertTrue(a.getRoster().isEmpty());
	}
	
	@Test
	public void testAddTwo() {		
		Student s = new Student("s", 2, listAB);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listBA, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertEquals(listS, b.getRoster());
		
		assertEquals(2, s.getSchedule().size());
		checkList(listAB, s.getSchedule());
	}
	
	@Test
	public void testAddOnlyOne() {		
		Student s = new Student("s", 1, listAB);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listBA, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertTrue(b.getRoster().isEmpty());
		assertEquals(listA, s.getSchedule());
	}
	
	@Test
	public void testTwoInTwo() {		
		Student s = new Student("s", 2, listAB);
		Student t = new Student("t", 2, listBA);
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.assignAll();
		
		checkList(listST, scheduler.getStudents());
		checkList(listAB, scheduler.getCourses());
		checkList(listST, a.getRoster());
		checkList(listTS, b.getRoster());
		checkList(listAB, s.getSchedule());
		checkList(listBA, t.getSchedule());
	}
	
	@Test
	public void testDropFromOne() {		
		Student s = new Student("s", 2, listAB);
		Student t = new Student("t", 2, listBA);
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.assignAll();
		scheduler.drop(t, a);
		
		checkList(listST, scheduler.getStudents());
		checkList(listAB, scheduler.getCourses());
		assertEquals(Arrays.asList(new Student[] {s}), a.getRoster());
		checkList(listTS, b.getRoster());
		checkList(listAB, s.getSchedule());
		assertEquals(listB, t.getSchedule());
	}
	
	@Test
	public void testUnenrollOne() {		
		Student s = new Student("s", 2, listAB);
		Student t = new Student("t", 2, listBA);
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.assignAll();
		scheduler.unenroll(t);
		
		checkList(listST, scheduler.getStudents());
		checkList(listAB, scheduler.getCourses());
		checkList(Arrays.asList(new Student[] {s}), a.getRoster());
		assertEquals(Arrays.asList(new Student[] {s}), b.getRoster());
		checkList(listAB, s.getSchedule());
		assertEquals(new ArrayList<>(), t.getSchedule());
	}
	
	@Test
	public void testOneFull() {		
		Student s = new Student("s", 2, listAC);
		Student t = new Student("t", 2, listCA);
		List<Student> listT = Arrays.asList(new Student[] {t});
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(t);
		scheduler.addStudent(s);
		scheduler.addCourse(c);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		checkList(listTS, scheduler.getStudents());
		checkList(listCA, scheduler.getCourses());
		checkList(listST, a.getRoster());
		assertEquals(listT, c.getRoster());
		assertEquals(listA, s.getSchedule());
		checkList(listCA, t.getSchedule());
	}
	
	@Test
	public void testOtherFull() {		
		Student s = new Student("s", 2, listAC);
		Student t = new Student("t", 2, listCA);
		List<Student> listT = Arrays.asList(new Student[] {t});
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(t);
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.addCourse(c);
		scheduler.assignAll();
		
		checkList(listTS, scheduler.getStudents());
		checkList(listAC, scheduler.getCourses());
		checkList(listST, a.getRoster());
		assertEquals(listT, c.getRoster());
		assertEquals(listA, s.getSchedule());
		checkList(listCA, t.getSchedule());
	}
	
	@Test
	public void testThree() {
		Course d = new Course("DUTCH100", 2);
		Course e = new Course("ECON100", 3);
		
		Student s = new Student("s", 3, Arrays.asList(new Course[] {a, b, c, d, e}));
		Student t = new Student("t", 4, Arrays.asList(new Course[] {c, a, d, e, b}));
		Student u = new Student("u", 5, Arrays.asList(new Course[] {b, a, d, c, e}));
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addStudent(u);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.addCourse(c);
		scheduler.addCourse(d);
		scheduler.addCourse(e);
		scheduler.assignAll();
		
		checkList(Arrays.asList(new Student[] {s, t, u}), scheduler.getStudents());		
		checkList(Arrays.asList(new Course[] {a, b, c, d, e}), scheduler.getCourses());
		checkList(Arrays.asList(new Student[] {s, t}), a.getRoster());
		checkList(Arrays.asList(new Student[] {u, s}), b.getRoster());
		assertEquals(Arrays.asList(new Student[] {t}), c.getRoster());
		checkList(Arrays.asList(new Student[] {u, s}), d.getRoster());
		checkList(Arrays.asList(new Student[] {t, u}), e.getRoster());
		checkList(Arrays.asList(new Course[] {a, b, d}), s.getSchedule());
		checkList(Arrays.asList(new Course[] {c, a, e}), t.getSchedule());
		checkList(Arrays.asList(new Course[] {b, d, e}), u.getSchedule());
	}
	
	@Test
	public void testThreeThenUnenrollAndReschedule() {
		Course d = new Course("DUTCH100", 2);
		Course e = new Course("ECON100", 3);
		
		Student s = new Student("s", 3, Arrays.asList(new Course[] {a, b, c, d, e}));
		Student t = new Student("t", 4, Arrays.asList(new Course[] {c, a, d, e, b}));
		Student u = new Student("u", 5, Arrays.asList(new Course[] {b, a, d, c, e}));
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addStudent(u);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.addCourse(c);
		scheduler.addCourse(d);
		scheduler.addCourse(e);
		scheduler.assignAll();
		scheduler.unenroll(s);
		scheduler.assignAll();
		
		checkList(Arrays.asList(new Student[] {s, t, u}), scheduler.getStudents());		
		checkList(Arrays.asList(new Course[] {a, b, c, d, e}), scheduler.getCourses());
		checkList(Arrays.asList(new Student[] {t, s}), a.getRoster());
		checkList(Arrays.asList(new Student[] {u, s}), b.getRoster());
		assertEquals(Arrays.asList(new Student[] {t}), c.getRoster());
		checkList(Arrays.asList(new Student[] {u, t}), d.getRoster());
		checkList(Arrays.asList(new Student[] {t, u, s}), e.getRoster());
		checkList(Arrays.asList(new Course[] {a, b, e}), s.getSchedule());
		checkList(Arrays.asList(new Course[] {c, a, e, d}), t.getSchedule());
		checkList(Arrays.asList(new Course[] {b, d, e}), u.getSchedule());
	}
}
