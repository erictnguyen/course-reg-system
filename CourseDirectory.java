import java.util.ArrayList;
import java.util.Scanner;

public class CourseDirectory {

	static ArrayList <Course> CourseList;
	
//	create empty ArrayList object
	public CourseDirectory() {
		CourseList = new ArrayList<Course>();
	}
	
	public void addCourse() {
//		add course to CourseList
		CourseList.add(new Course()); 
//		prompt user for input through Course
		CourseList.get((CourseList.size()) - 1).createCourse(); 
	}
	
//	print every course in CourseList
	public void displayCourseList() {
		for (Course c : CourseList) {
			c.printCourse();
		}
	}
	
//	display full courses
	public void displayFullCourses() {
		System.out.println("");
		for (Course c : CourseList) {
			if (c.getisFull()) {
				c.printCourse();
			}
		}
	}
	
//	search for course to edit by course name
	public void editCourse() {
		Scanner input = new Scanner(System.in);
		System.out.println("");
		System.out.println("Which course would you like to edit? Enter Course name: ");
		String course = input.nextLine();
		if (containsName(CourseList, course)) {
			for (Course c : CourseList) {
				if (c.getcourseName().equals(course)) {
					int option = 0;
					while (option != 6) {
						System.out.println("");
						System.out.println("What would you like to change about " + course + "?"
								+ "\n1. Name"
								+ "\n2. Meeting time"
								+ "\n3. Room"
								+ "\n4. Teaching instructor"
								+ "\n5. Class capacity"
								+ "\n6. Return to Course Affairs");
						option = input.nextInt();
						System.out.println("");
						input.nextLine();
//						print current attribute and get user input for edits
						if (option == 1) {
							System.out.println("Current Course name: " + c.getcourseName()
									+ "\nEnter new Course name: ");
							String newCourse = input.nextLine();
//							update course name in professor list
							for (Professor p : ProfessorDirectory.ProfessorList) {
								if (p.getRegisteredCourses().contains(c.getcourseName())) {
									p.getRegisteredCourses().set(p.getRegisteredCourses().indexOf(c.getcourseName()), newCourse);
								}
							}
//							update course name in student list
							for (Student s : StudentDirectory.StudentList) {
								if (s.getRegisteredCourses().contains(c.getcourseName())) {
									s.getRegisteredCourses().set(s.getRegisteredCourses().indexOf(c.getcourseName()), newCourse);
								}
							}
//							change course name
							c.setcourseName(newCourse);
							System.out.println("Course name change successful");
						} else if (option == 2) {
							System.out.println("Current meeting time: " + c.getcourseTime()
									+ "\nEnter new meeting time (in 12-hour format): ");
							c.setcourseTime(input.next());
							System.out.println("Meeting time change successful");
						} else if (option == 3) {
							System.out.println("Current room: " + c.getcourseRoom()
									+ "\nEnter new room location: ");
							c.setcourseRoom(input.next());
							System.out.println("Room change successful");
						} else if (option == 4) {
							System.out.println("Current teaching instructor: " + c.getcourseInstructor()
									+ "\nEnter new teaching instructor's first and last name: ");
							String newProf = input.nextLine();
//							check if professor name input corresponds to an existing professor
							if (ProfessorDirectory.ProfessorList.isEmpty()) {
								System.out.println("No Professors registered in the system");
								break;
							} 
							else if (ProfessorDirectory.containsName(ProfessorDirectory.ProfessorList, newProf)) {
								for (Professor p : ProfessorDirectory.ProfessorList) {
//									remove course from oldProf registeredCourses
									if (p.getName().equals(c.getcourseInstructor())) {
										p.getRegisteredCourses().remove(c.getcourseName());
									}
//									add course to newProf registeredCourses
									else if (p.getName().equals(newProf)) {
										p.getRegisteredCourses().add(c.getcourseName());
									}
								}
								c.setcourseInstructor(newProf);
								System.out.println("\nCourse instructor change successful");
							}
							else {
								System.out.println("'" + newProf + "' not registered in the system");
							}
						} else if (option == 5) {
							System.out.println("Current course capacity: " + c.getmaxCapacity() + " students" 
									+ "\nEnter new course capacity: ");
							c.setmaxCapacity(input.nextInt());
						} 
					}
				}
			}	
		}
		else
			System.out.println("Invalid course name");
	}

//	remove user input course
	public void removeCourse() {
		Scanner input = new Scanner(System.in);
		System.out.println("What course would you like to remove? Enter Course name: ");
		String course = input.nextLine();
		if (containsName(CourseList, course)) {
			for (Course c : CourseList) {
				if (c.getcourseName().equals(course)) {
					System.out.println("Are you sure you want to delete " + course + "? Enter (Y/N)");
					if (input.next().charAt(0) == 'y' || input.next().charAt(0) == 'Y') {
						input.nextLine();
//						remove course from professor registeredCourses list
						for (Professor p : ProfessorDirectory.ProfessorList) {
							if (p.getRegisteredCourses().contains(course)) {
								p.getRegisteredCourses().remove(course);
							}
						}
//						remove course from students registeredCourses list
						for (Student s : StudentDirectory.StudentList) {
							if (s.getRegisteredCourses().contains(course)) {
								s.getRegisteredCourses().remove(course);
							}
						}
//						remove course from CourseList
						CourseList.remove(c);
						System.out.println(course + " successfully removed");
					}
					else
						break;
				}
			}
		}
		else
			System.out.println("invalid course name");
	}

	public static void removeStudentFromCourse(String course, String student) {
		for (Course c : CourseList) {
			if (c.getcourseName().equals(course)) {
				c.getStudentsEnrolled().remove(student);
			}
		}
	}
	
//	overloaded method
	public static void removeStudentFromCourse(String student) {
		for (Course c : CourseList) {
			if (c.getStudentsEnrolled().contains(student)) {
				c.getStudentsEnrolled().remove(student);
			}
		}
	}
	
//	check if course name exists in arraylist
	public static boolean containsName(ArrayList<Course> c, String course) {
		for (Course o : c) {
			if (o != null && o.getcourseName().equals(course)) {
				return true;
			}
		}
		return false;
	}
}
