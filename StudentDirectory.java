import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentDirectory implements PersonMethods {

	static ArrayList<Student> StudentList;
	
//	create empty ArrayList object
	public StudentDirectory() {
		StudentList = new ArrayList<Student>();
	}
	
	public void add() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter student's first and last name: ");
		String student = input.nextLine();
//		if student-to-be-added is already in system, confirm choice to add student with the same name
		if (containsName(StudentList, student)) {
			System.out.println("Student named '" + student + "' already exists in the system"
					+ "\nAdd student anyways? Enter (Y/N)");
			if (input.next().charAt(0) == 'y' || input.next().charAt(0) == 'Y') {
				input.nextLine();
				StudentList.add(new Student());
				StudentList.get((StudentList.size()) - 1).setName(student);
				System.out.println("Enter a username for '" + student + "': ");
				String username = input.next();
				System.out.println("Enter password for '" + student + "': ");
				String password = input.next();
				while (username.equals(password)) {
					System.out.println("Username and password cannot be the same");
					System.out.println("Enter a username for '" + student + "': ");
					username = input.next();
					System.out.println("Enter password for '" + student + "': ");
					password = input.next();
				}
				StudentList.get((StudentList.size()) - 1).setUsername(username);
				StudentList.get((StudentList.size()) - 1).setPassword(password);
				try {
					FileWriter writer = new FileWriter("students.txt", true);
					BufferedWriter bufferedWriter = new BufferedWriter(writer);
					bufferedWriter.write(username + " " + password);
					bufferedWriter.newLine();
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (input.next().charAt(0) == 'n' || input.next().charAt(0) == 'N') {
				return;
			}
			return;
		} else {
//			if student not in the system, add new student
			StudentList.add(new Student());
			StudentList.get((StudentList.size()) - 1).setName(student);
			System.out.println("Enter a username for '" + student + "': ");
			String username = input.next();
			System.out.println("Enter password for '" + student + "': ");
			String password = input.next();
			while (username.equals(password)) {
				System.out.println("Username and password cannot be the same");
				System.out.println("Enter a username for '" + student + "': ");
				username = input.next();
				System.out.println("Enter password for '" + student + "': ");
				password = input.next();
			}
			StudentList.get((StudentList.size()) - 1).setUsername(username);
			StudentList.get((StudentList.size()) - 1).setPassword(password);
			try {
				FileWriter writer = new FileWriter("students.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(writer);
				bufferedWriter.write(username + " " + password);
				bufferedWriter.newLine();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void edit() {
		Scanner input = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter student's first and last name: ");
		String student = input.nextLine();
		if (containsName(StudentList, student)) {
			for (Student s : StudentList) {
				if (s.getName().equals(student)) {
					int option = 0;
					while (option != 4) {
						System.out.println("What would you like to change for " + student + "?"
								+ "\n1. Address"
								+ "\n2. Phone number"
								+ "\n3. Major"
								+ "\n4. Return to Student Affairs");
						option = input.nextInt();
						System.out.println("");
						input.nextLine();
//						print current attribute value and get user inputs for edits
						if (option == 1) {
							System.out.println("Current address: " + s.getAddress()
									+ "\nEnter new address: ");
							s.setAddress(input.nextLine());
							System.out.println("Address change successful");
						} else if (option == 2) {
							System.out.println("Current phone number: " + s.getPhone()
									+ "\nEnter new phone number (in format 408-123-0000): ");
							s.setPhone(input.next());
							System.out.println("Phone number change successful");
						} else if (option == 3) {
							System.out.println("Current major: " + s.getMajor()
									+ "\nEnter new major: ");
							s.setMajor(input.nextLine());
							System.out.println("Major change successful");
						} 
					}
				}
			}
		}
		else
			System.out.println(student + " is not in the system");
	}
	
	public void removeStudent() {
		Scanner input = new Scanner(System.in);
		int option = 0;
		System.out.println("Enter student's first and last name: ");
		String student = input.nextLine();
		if (containsName(StudentList, student)) {
			System.out.println("1. Remove " + student + " from a course"
					+ "\n2. Remove " + student + " from the system"
					+ "\n3. Return to Student Affairs");
			option = input.nextInt();
			if (option == 1) {
				removeStudentFromCourse(student);
			} else if (option == 2) {
				removeFromSystem(student);
			}
			return;
		}
		else
			System.out.println("'" + student + "' not registered in the system");
	}
	
	public void removeStudentFromCourse(String student) {
		Scanner input = new Scanner(System.in);
		System.out.println("What course would you like to remove " + student + " from?");
		String course = input.nextLine();
//		check if course exists in the system
		if (CourseDirectory.containsName(CourseDirectory.CourseList, course)) {
//			remove course from student registeredCourses
			for (Student s : StudentList) {
				if (s.getName().equals(student)) {
					s.getRegisteredCourses().remove(course);
				}
			}
//			remove student from course studentsEnrolled
			CourseDirectory.removeStudentFromCourse(course, student);
			System.out.println(student + " successfully removed from " + course);
			System.out.println("");
			return;
		}
	}
	
	public void removeFromSystem(String student) {
//		remove student object from student list
		for (Iterator<Student> iterator = StudentList.iterator(); iterator.hasNext(); ) {
			Student s = iterator.next();
			if (s.getName().equals(student)) {
				iterator.remove();
			}
		}
//		remove student from all enrolled courses
		CourseDirectory.removeStudentFromCourse(student);
//		remove student login information from students.txt
		System.out.println(student + " successfully removed from the system");
	}
	
//	print every student in StudentList
	public void printPersonList() {
		for (Student s : StudentList) {
			s.print();
		}
	}
	
//	register student in a course
	public void registerStudentInCourse(String student) {
		Scanner input = new Scanner(System.in);
//		check if student exists in system
		if (containsName(StudentList, student)) {
			boolean cont = true;
			do {
				System.out.println("Enter course name to register '" + student + "': ");
				String course = input.nextLine();
//				check if course is in the system
				if (CourseDirectory.containsName(CourseDirectory.CourseList, course)) {
					for (Course c : CourseDirectory.CourseList) {
						if (c.getcourseName().equals(course)) {
							if (!c.getisFull()) {
//								add course to student object registeredCourses list
								for (Student s : StudentList) {
									if (s.getName().equals(student)) {
										s.getRegisteredCourses().add(course);
									}
								}
//								add student to course object studentsEnrolled list
								c.getStudentsEnrolled().add(student);
								c.setnumStudents();
								System.out.println(student + " successfully registered for " + course);
							}
							else {
								System.out.println(course + " is full");
							}
						}
					}
				}
				System.out.println("\nDo you want to register " + student + " for another course? Enter (Y/N): ");
				char another = input.next().charAt(0);
				if (another == 'n' || another == 'N') {
					cont = false;
				} 
				input.nextLine();
			} while (cont);
		}
		else {
			System.out.println("'" + student + "' not registered in the system");
		}
	}
	
//	display courses taken by a student (from Admin menu)
	public void displayStudentCourses() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter student's first and last name: ");
		String student = input.nextLine();
//		check if student exists in system
		if (containsName(StudentList, student)) {
			System.out.println("\n" + student + "'s Course List"
					+ "\n_________________________");
			for (Student s : StudentList) {
				if (s.getName().equals(student)) {
					for (String course : s.getRegisteredCourses()) {
						System.out.println(course);
					}
				}
			}
		}
		else 
			System.out.println("'" + student + "' not registered in the system");
	}
	
//	print courses the student is registered in (from Student menu)
	public void displayCourses(int index) {
		for (String course : StudentDirectory.StudentList.get(index).getRegisteredCourses()) {
			System.out.println(course);
		}
		System.out.println("");
	}
	
//	course management menu (view, add, remove course)
	public void courseManage(int index) {
		Scanner input = new Scanner(System.in);
		System.out.println("");
		int option = 0;
		while (option != 4) {
			System.out.println("1. View My Courses"
					+ "\n2. Add a Course"
					+ "\n3. Drop a Course"
					+ "\n4. Back to Main Menu");
			option = input.nextInt();
			System.out.println("");
			input.nextLine();
			if (option == 1) {
//				display enrolled courses
				displayCourses(index);
			} else if (option == 2) {
//				add a course
				registerStudentInCourse(StudentList.get(index).getName());
			} else if (option == 3) {
//				drop a course
				removeStudentFromCourse(StudentList.get(index).getName());
			}
		}
	}
	
//	edit personal information
	public void editPersonal(int index) {
		Scanner input = new Scanner(System.in);
		System.out.println("");
		int option = 0;
		while (option != 7) {
			System.out.println("1. Social Security Number" 
					+ "\n2. Address"
					+ "\n3. Phone Number"
					+ "\n4. Major"
					+ "\n5. Change username"
					+ "\n6. Change password"
					+ "\n7. Back to Main Menu");
			option = input.nextInt();
			System.out.println("");
			input.nextLine();
//			print current attribute value and get user input for edits
			if (option == 1) {
				System.out.println("Enter your social security number (in 000-00-0000 format): ");
				StudentList.get(index).setSSN(input.next());
				System.out.println("Social security number successfully entered");
			} else if (option == 2) {
				System.out.println("Current address: " + StudentList.get(index).getAddress()
						+ "\nEnter new address: ");
				StudentList.get(index).setAddress(input.nextLine());
				System.out.println("Address change successful");
			} else if (option == 3) {
				System.out.println("Current phone number: " + StudentList.get(index).getPhone()
						+ "\nEnter new phone number (in format 408-123-0000): ");
				StudentList.get(index).setPhone(input.next());
				System.out.println("Phone number change successful");
			} else if (option == 4) {
				System.out.println("Current major: " + StudentList.get(index).getMajor()
						+ "\nEnter new major: ");
				StudentList.get(index).setMajor(input.nextLine());
				System.out.println("Major change successful");
			} else if (option == 5) {
				System.out.println("Current username: " + StudentList.get(index).getUsername()
						+ "\nEnter new username: ");
				String newUsername = input.next();
				try {
//					open a temp file to write to
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("students.temp")));
					
					FileReader reader = new FileReader("students.txt");
					BufferedReader br = new BufferedReader(reader);
					String line;
					
					while ((line = br.readLine()) != null) {
						if (line.contains(StudentList.get(index).getUsername())) {
							line = line.replace(StudentList.get(index).getUsername(), newUsername);
						}
//						always write the line, whether it is changed or not
						writer.println(line);
					}
//					close the writer
					writer.close();
					
					File realName = new File("students.txt");
//					remove the old file
					realName.delete();
//					rename temp file
					new File("students.temp").renameTo(realName);
					reader.close();
					
					StudentList.get(index).setUsername(newUsername);
					System.out.println("");
					System.out.println("Username successfully changed");
					System.out.println("");
				} catch (IOException e) {
					e.printStackTrace();
				} 
			} else if (option == 6) {
				System.out.println("Current password: " + StudentList.get(index).getPassword()
						+ "\nEnter new username: ");
				String newPassword = input.next();
				try {
//					open a temp file to write to
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("students.temp")));
					
					FileReader reader = new FileReader("students.txt");
					BufferedReader br = new BufferedReader(reader);
					String line;
					
					while ((line = br.readLine()) != null) {
						if (line.contains(StudentList.get(index).getPassword())) {
							line = line.replace(StudentList.get(index).getPassword(), newPassword);
						}
//						always write the line, whether it is changed or not
						writer.println(line);
					}
//					close the writer
					writer.close();
					
					File realName = new File("students.txt");
//					remove the old file
					realName.delete();
//					rename temp file
					new File("students.temp").renameTo(realName);
					reader.close();
					
					StudentList.get(index).setPassword(newPassword);
					System.out.println("");
					System.out.println("Password successfully changed");
					System.out.println("");
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
//	check if student name exists in arraylist
	public boolean containsName(ArrayList<Student> s, String name) {
		for (Student o : s) {
			if (o != null && o.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
