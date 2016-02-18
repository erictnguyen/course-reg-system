import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CourseRegistration {

	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		
//		create instance of CourseDirectory, StudentDirectory, ProfessorDirectory objects
		CourseDirectory CourseAffairs = new CourseDirectory();
		StudentDirectory StudentAffairs = new StudentDirectory();
		ProfessorDirectory ProfessorAffairs = new ProfessorDirectory();
		
//		declare Admin object
		Admin admin = new Admin();
		
//		read from admin.txt and save username & password on admin object
		try {
			File f = new File("admin.txt");
			Scanner sc = new Scanner(f);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] adminAssign = line.split(",");
				admin.setUsername(adminAssign[0]);
				admin.setPassword(adminAssign[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
//		declare and initialize login choice 
		int option = 0;
		boolean login = false;
		
		while (option != 4) {
//			print initial login screen
			loginMenu();
			option = input.nextInt();
			if (option == 1) {
				int subOption = 0;
				login = adminLogin(admin);
				if (login) {
					System.out.println("\n*Login successful*");
					while (subOption != 4) {
						adminMenu();
						subOption = input.nextInt();
						if (subOption == 1) {
							int courseOption = 0;
							while (courseOption != 6) {
								courseAffairsMenu();
								courseOption = input.nextInt();
								if (courseOption == 1) {
//									add a course
									CourseAffairs.addCourse();
								} else if (courseOption == 2) {
//									edit a course
									CourseAffairs.editCourse();
								} else if (courseOption == 3) {
//									remove a course
									CourseAffairs.removeCourse();
								} else if (courseOption == 4) {
//									display all courses
									System.out.println("");
									CourseAffairs.displayCourseList();
								} else if (courseOption == 5) {
//									display all courses that are full
									CourseAffairs.displayFullCourses();
								}
							}
						} else if (subOption == 2) {
							int studentOption = 0;
							while (studentOption != 8) {
								studentAffairsMenu();
								studentOption = input.nextInt();
								if (studentOption == 1) {
//									add a student
									boolean cont = true;
									do {
										StudentAffairs.add();
										System.out.println("Do you want to add another Student? Enter (Y/N): ");
										char another = input.next().charAt(0);
										if (another == 'n' || another == 'N') {
											cont = false;
										}
									} while (cont);
								} else if (studentOption == 2) {
//									edit a student
									StudentAffairs.edit();
								} else if (studentOption == 3) {
//									remove a student
									boolean cont = true;
									do {
										StudentAffairs.removeStudent();
										System.out.println("\nDo you want to remove another Student? Enter (Y/N): ");
										char another = input.next().charAt(0);
										if (another == 'n' || another == 'N') {
											cont = false;
										}
									} while (cont);
								} else if (studentOption == 4) {
//									register a course(s) for a student
									System.out.println("\nEnter student's first and last name: ");
									input.nextLine();
									String student = input.nextLine();
									StudentAffairs.registerStudentInCourse(student);
								} else if (studentOption == 5) {
//									display student list
									StudentAffairs.printPersonList();
								} else if (studentOption == 6) {
//									display course list
									CourseAffairs.displayCourseList();
								} else if (studentOption == 7) {
//									display Course(s) taken by a student
									StudentAffairs.displayStudentCourses();
								}
							}
						} else if (subOption == 3) {
							int profOption = 0;
							while (profOption != 5) {
								professorAffairsMenu();
								profOption = input.nextInt();
								if (profOption == 1) {
//									add a professor
									boolean cont = true;
									do {
										ProfessorAffairs.add();
										System.out.println("Do you want to add another Professor? Enter (Y/N): ");
										char another = input.next().charAt(0);
										if (another == 'n' || another == 'N') {
											cont = false;
										}
									} while (cont);
								} else if (profOption == 2) {
//									edit a professor
									ProfessorAffairs.edit();
								} else if (profOption == 3) {
//									remove a professor
									System.out.println("Enter professor's first and last name: ");
									input.nextLine();
									String professor = input.nextLine();
									ProfessorAffairs.removeFromSystem(professor);
								} else if (profOption == 4) {
//									display all professors
									ProfessorAffairs.printPersonList();
								}
							}
						}
					}
					login = false;
				}	
			} else if (option == 2) {
				int subOption = 0;
				int index = 0;
				String profName = "";
				ArrayList<String> profLogin = new ArrayList<String>();
//				assign professor login details to arraylist
				profLogin = profDetails();
				String[] userProfLogin = professorLogin();
//				twice verify correct login info
				for (int i = 0; i < profLogin.size(); i += 2) {
					if ((userProfLogin[0].equals(profLogin.get(i))) && (userProfLogin[1].equals(profLogin.get(i + 1)))) {
						for (Professor p : ProfessorDirectory.ProfessorList) {
							if ((p.getUsername().equals(userProfLogin[0])) && (p.getPassword().equals(userProfLogin[1]))) {
								login = true;
								profName = p.getName();
								index = StudentDirectory.StudentList.indexOf(p);
							}
							if (login) {
								System.out.println("\n*Login successful*"
										+ "\nWelcome Professor " + profName + "!");
								while (subOption != 4) {
									profMenu();
									subOption = input.nextInt();
									if (subOption == 1) {
//										print assigned courses
										System.out.println("");
										ProfessorAffairs.displayCourses(index);
									} else if (subOption == 2) {
//										print students enrolled in assigned courses
										ProfessorAffairs.printStudents(index);
									} else if (subOption == 3) {
//										edit personal info menu
										ProfessorAffairs.editPersonal(index);
									}
								}
								login = false;
							}
						}
					}
				}
			} else if (option == 3) {
				int subOption = 0;
				int index = 0;
				String stuName = "";
				ArrayList<String> stuLogin = new ArrayList<String>();
//				assign student login details to arraylist
				stuLogin = stuDetails();
				String[] userStuLogin = studentLogin();
//				twice verify correct login info
				for (int i = 0; i < stuLogin.size(); i += 2) {
					if ((userStuLogin[0].equals(stuLogin.get(i))) && (userStuLogin[1].equals(stuLogin.get(i + 1)))) {
						for (Student s : StudentDirectory.StudentList) {
							if ((s.getUsername().equals(userStuLogin[0])) && (s.getPassword().equals(userStuLogin[1]))) {
								login = true;
								stuName = s.getName();
								index = StudentDirectory.StudentList.indexOf(s);
							}
							if (login) {
								System.out.println("\n*Login successful*"
										+ "\nWelcome " + stuName + "!");
								while (subOption != 3) {
									studentMenu();
									subOption = input.nextInt();
									if (subOption == 1) {
//										course management menu
										StudentAffairs.courseManage(index);
									} else if (subOption == 2) {
//										edit personal information menu
										StudentAffairs.editPersonal(index);
									}
								}
								login = false;
							}
						}
					}
				}
			}
		}
	}
	
	public static void loginMenu() {
		System.out.println("===================="
				+ "\n        LOGIN"
				+ "\n===================="
				+ "\n\nLogin as.. (Enter option)"
				+ "\n1. Admin"
				+ "\n2. Professor"
				+ "\n3. Student"
				+ "\n4. Exit");
	}
	
	public static void adminMenu() {
		System.out.println("\nGo to.."
				+ "\n1. Course Affairs"
				+ "\n2. Student Affairs"
				+ "\n3. Professor Affairs"
				+ "\n4. Sign Out");
	}
	
	public static void profMenu() {
		System.out.println("\nGo to.."
				+ "\n1. View All Courses"
				+ "\n2. View All Students"
				+ "\n3. Change Personal Information"
				+ "\n4. Sign Out");
	}
	
	public static void studentMenu() {
		System.out.println("\nGo to.."
				+ "\n1. Course Management"
				+ "\n2. Change Personal Information"
				+ "\n3. Sign Out");
	}
	
	public static void courseAffairsMenu() {
		System.out.println("\n===================="
				+ "\n   COURSE AFFAIRS"
				+ "\n===================="
				+ "\n1. Add a Course"
				+ "\n2. Edit a Course"
				+ "\n3. Remove a Course"
				+ "\n4. Display All Course"
				+ "\n5. Display all Courses that are currently full"
				+ "\n6. Back to Main Menu");
	}
	
	public static void studentAffairsMenu() {
		System.out.println("\n===================="
				+ "\n   STUDENT AFFAIRS"
				+ "\n===================="
				+ "\n1. Add a Student"
				+ "\n2. Edit a Student"
				+ "\n3. Remove a Student"
				+ "\n4. Register a Course(s) for a Student"
				+ "\n5. Display Student list"
				+ "\n6. Display Course list"
				+ "\n7. Display Course(s) taken by a Student"
				+ "\n8. Back to Main Menu");
	}
	
	public static void professorAffairsMenu() {
		System.out.println("\n===================="
				+ "\n  PROFESSOR AFFAIRS"
				+ "\n===================="
				+ "\n1. Add a Professor"
				+ "\n2. Edit a Professor"
				+ "\n3. Remove a Professor"
				+ "\n4. Display All Professors"
				+ "\n5. Back to Main Menu");
	}
	
	public static boolean adminLogin(Admin admin) {
		Scanner input = new Scanner(System.in);
		boolean verify = false;
		String username = "";
		String password = "";
		
//		display admin login banner
		System.out.println("\n===================="
				+ "\n    ADMIN LOGIN"
				+ "\n====================");
		
//		verify username and password input matches that on file
		while ((!username.equals(admin.getUsername())) || (!password.equals(admin.getPassword()))) {
			System.out.println("Username:");
			username = input.next();
			System.out.println("Password: ");
			password = input.next();
			
			if ((!username.equals(admin.getUsername())) && (password.equals(admin.getPassword())))
				System.out.println("\n*Incorrect username*");
			else if ((username.equals(admin.getUsername())) && (!password.equals(admin.getPassword())))
				System.out.println("\n*Incorrect password*");
			else if ((!username.equals(admin.getUsername())) && (!password.equals(admin.getPassword())))
				System.out.println("\n*Incorrect username & password*");
			else if ((username.equals(admin.getUsername())) && (password.equals(admin.getPassword())))
				verify = true;
		}
		
		return verify;
	}
	
	public static ArrayList<String> profDetails() throws IOException {
//		open professors.txt and assign usernames and passwords to array
		ArrayList<String> loginInfo = new ArrayList<String>();
		
		BufferedReader inFile = new BufferedReader(new FileReader("professors.txt"));
		
		String inLine;
		
		while (true) {
			inLine = inFile.readLine();
			if (inLine == null) {
				break;
			}
			String[] parts = inLine.split(" ");
			String part1 = parts[0];
			String part2 = parts[1];
			loginInfo.add(part1);
			loginInfo.add(part2);
		}
		inFile.close();
		return loginInfo;
	}
	
	public static String[] professorLogin() {
		Scanner input = new Scanner(System.in);
		String username = "";
		String password = "";
		
//		display professor login banner
		System.out.println("\n===================="
				+ "\n  PROFESSOR LOGIN"
				+ "\n====================");
		
//		verify username and password input matches that on file
		System.out.println("Username:");
		username = input.next();
		System.out.println("Password: ");
		password = input.next();
		
		return new String[] {username, password};
	}
	
	public static ArrayList<String> stuDetails() throws IOException {
//		open students.txt and assign usernames and passwords to array
		ArrayList<String> loginInfo = new ArrayList<String>();
		
		BufferedReader inFile = new BufferedReader(new FileReader("students.txt"));
		
		String inLine;
		
		while (true) {
			inLine = inFile.readLine();
			if (inLine == null) {
				break;
			}
			String[] parts = inLine.split(" ");
			String part1 = parts[0];
			String part2 = parts[1];
			loginInfo.add(part1);
			loginInfo.add(part2);
		}
		inFile.close();
		return loginInfo;
	}
	
	public static String[] studentLogin() {
		Scanner input = new Scanner(System.in);
		String username = "";
		String password = "";
		
//		display professor login banner
		System.out.println("\n===================="
				+ "\n   STUDENT LOGIN"
				+ "\n====================");
		
//		verify username and password input matches that on file
		System.out.println("Username:");
		username = input.next();
		System.out.println("Password: ");
		password = input.next();
		
		return new String[] {username, password};
	}
}
