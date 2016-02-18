import java.util.ArrayList;
import java.util.Scanner;

public class Course {

	private static int COUNTER = 0;
	
	private String name;
	private int number;
	private String time;
	private String room;
	private String instructor;
	private int maxCapacity = 5;
	private int numStudents = 0;
	private boolean isFull = false;
	private ArrayList<String> studentsEnrolled;
	
//	default constructor
	public Course() {
		this.number = COUNTER++;
		studentsEnrolled = new ArrayList<String>();
	}
	
//	overloaded constructor
	public Course(String name, String time, String room, String instructor) {
		this.name = name;
		this.number = COUNTER++;
		time = this.time;
		room = this.room;
		instructor = this.instructor;
	}
	
//	getter methods
	public String getcourseName() {
		return this.name;
	}
	public int getcourseNumber() {
		return this.number;
	}
	public String getcourseTime() {
		return this.time;
	}
	public String getcourseRoom() {
		return this.room;
	}
	public String getcourseInstructor() {
		return this.instructor;
	}
	public int getnumStudents() {
		return this.numStudents;
	}
	public int getmaxCapacity() {
		return this.maxCapacity;
	}
	public ArrayList<String> getStudentsEnrolled() {
		return this.studentsEnrolled;
	}
	public boolean getisFull() {
		return this.isFull;
	}
	
//	setter methods
	public void setcourseName(String name) {
		this.name = name;
	}
	public void setcourseTime(String time) {
		this.time = time;
	}
	public void setcourseRoom(String room) {
		this.room = room;
	}
	public void setcourseInstructor(String instructor) {
		this.instructor = instructor;
	}
	public void setmaxCapacity(int max) {
		this.maxCapacity = max;
	}
	public void setnumStudents() {
		numStudents++;
		if (numStudents == maxCapacity)
			this.isFull = true;
	}
	
	public void createCourse() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter Course name: ");
		setcourseName(input.nextLine());
		System.out.println("Enter Course time (in 12-hour format): ");
		setcourseTime(input.next());
		System.out.println("Enter Course room: ");
		setcourseRoom(input.next());
//		System.out.println("Enter Course Instructor: ");
//		setcourseInstructor(input.nextLine());
	}
	
	public void printCourse() {
		System.out.println("Course: " + name + " (Number "+ number + ")");
//				+ "\nTime: " + time
//				+ "\nRoom " + room
//				+ "\nInstructor: " + instructor
//				+ "\nNumber of students enrolled: " + numStudents);
	}
	
}
