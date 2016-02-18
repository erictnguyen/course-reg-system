import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfessorDirectory implements PersonMethods {

	static ArrayList<Professor> ProfessorList;
	
//	create empty ArrayList object
	public ProfessorDirectory() {
		ProfessorList = new ArrayList<Professor>();
	}
	
	public void add() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter professor's first and last name: ");
		String prof = input.nextLine();
//		if professor not in the system, add new professor
		ProfessorList.add(new Professor());
		ProfessorList.get((ProfessorList.size()) - 1).setName(prof);
		System.out.println("Enter a username for '" + prof + "': ");
		String username = input.next();
		System.out.println("Enter password for '" + prof + "': ");
		String password = input.next();
		while (username.equals(password)) {
			System.out.println("Username and password cannot be the same");
			System.out.println("Enter a username for '" + prof + "': ");
			username = input.next();
			System.out.println("Enter password for '" + prof + "': ");
			password = input.next();
		}
		ProfessorList.get((ProfessorList.size()) - 1).setUsername(username);
		ProfessorList.get((ProfessorList.size()) - 1).setPassword(password);
		try {
			FileWriter writer = new FileWriter("professors.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(username + " " + password);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void edit() {
		Scanner input = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter professor's first and last name: ");
		String prof = input.nextLine();
		if (containsName(ProfessorList, prof)) {
			for (Professor p : ProfessorList) {
				if (p.getName().equals(prof)) {
					int option = 0;
					while (option != 4) {
						System.out.println("What would you like to change for " + prof + " ?"
								+ "\n1. Address"
								+ "\n2. Office location"
								+ "\n3. Phone number"
								+ "\n4. Return to Professor Affairs");
						option = input.nextInt();
						System.out.println("");
						input.nextLine();
//						print current attribute value and get user input for edits
						if (option == 1) {
							System.out.println("Current address: " + p.getAddress()
									+ "\nEnter new address: ");
							p.setAddress(input.nextLine());
							System.out.println("Address change successful");
						} else if (option == 2) {
							System.out.println("Current office location: " + p.getOffice()
									+ "\nEnter new office location: ");
							p.setOffice(input.next());
							System.out.println("Office location change successful");
						} else if (option == 3) {
							System.out.println("Current phone number: " + p.getPhone()
									+ "\nEnter new phone number (in format 408-123-0000): ");
							p.setPhone(input.next());
							System.out.println("Phone number change successful");
						} 
					}
				}
			}
		}
		else
			System.out.println("'" + prof + "' not registered in the system");
	}
	
	public void removeFromSystem(String professor) {
		if (containsName(ProfessorList, professor)) {
//			remove professor object from professor list
			for (Iterator<Professor> iterator = ProfessorList.iterator(); iterator.hasNext(); ) {
				Professor p = iterator.next();
				if (p.getName().equals(professor)) {
					iterator.remove();
				}
			}
//			remove professor from all registered classes
			for (Course c : CourseDirectory.CourseList) {
				if (c.getcourseInstructor().equals(professor)) {
					c.setcourseInstructor(null);
				}
			}
			System.out.println("'" + professor + "' successfully removed from the system");
		}
		else 
			System.out.println("'" + professor + "' not registered in the system");
	}
	
//	print every professor in ProfessorList
	public void printPersonList() {
		for (Professor p : ProfessorList) {
			p.print();
		}
	}
	
//	print courses the professor is assigned to
	public void displayCourses(int index) {
		for (String course : ProfessorList.get(index).registeredCourses) {
			System.out.println(course);
		}
	}
	
//	print students in assigned courses
	public void printStudents(int index) {
		for (String course : ProfessorList.get(index).registeredCourses) {
			System.out.println("");
			System.out.println("COURSE: " + course);
			for (Course c : CourseDirectory.CourseList) {
				if (c.getcourseName().equals(course)) {
					for (String student : c.getStudentsEnrolled()) {
						System.out.println(student);
					}
				}
			}
		}
	}
	
//	edit professor's personal information
	public void editPersonal(int index) {
		Scanner input = new Scanner(System.in);
		System.out.println("");
		int option = 0;
		while (option != 7) {
			System.out.println("1. Social Security Number"
					+ "\n2. Address"
					+ "\n3. Office location"
					+ "\n4. Phone number"
					+ "\n5. Change username"
					+ "\n6. Change password"
					+ "\n7. Back to Main Menu");
			option = input.nextInt();
			System.out.println("");
			input.nextLine();
//			print current attribute value and get user input for edits
			if (option == 1) {
				System.out.println("Enter your social security number (in 000-00-0000 format): ");
				ProfessorList.get(index).setSSN(input.next());
				System.out.println("Social security number successfully entered");
			} else if (option == 2) {
				System.out.println("Current address: " + ProfessorList.get(index).getAddress()
						+ "\nEnter new address: ");
				ProfessorList.get(index).setAddress(input.nextLine());
				System.out.println("Address change successful");
			} else if (option == 3) {
				System.out.println("Current office location: " + ProfessorList.get(index).getOffice()
						+ "\nEnter new office location: ");
				ProfessorList.get(index).setOffice(input.next());
				System.out.println("Office location change successful");
			} else if (option == 4) {
				System.out.println("Current phone number: " + ProfessorList.get(index).getPhone()
						+ "\nEnter new phone number (in format 408-123-0000): ");
				ProfessorList.get(index).setPhone(input.next());
				System.out.println("Phone number change successful");
			} else if (option == 5) {
				System.out.println("Current username: " + ProfessorList.get(index).getUsername()
						+ "\nEnter new username: ");
				String newUsername = input.next();
				try {
//					open a temp file to write to
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("professors.temp")));
					
					FileReader reader = new FileReader("professors.txt");
					BufferedReader br = new BufferedReader(reader);
					String line;
					
					while ((line = br.readLine()) != null) {
						if (line.contains(ProfessorList.get(index).getUsername())) {
							line = line.replace(ProfessorList.get(index).getUsername(), newUsername);
						}
//						always write the line, whether it is changed or not
						writer.println(line);
					}
//					close the writer
					writer.close();
					
					File realName = new File("professors.txt");
//					remove the old file
					realName.delete();
//					rename temp file
					new File("professors.temp").renameTo(realName);
					reader.close();
					
					ProfessorList.get(index).setUsername(newUsername);
					System.out.println("");
					System.out.println("Username successfully changed");
					System.out.println("");
				} catch (IOException e) {
					e.printStackTrace();
				} 
			} else if (option == 6) {
				System.out.println("Current password: " + ProfessorList.get(index).getPassword()
						+ "\nEnter new username: ");
				String newPassword = input.next();
				try {
//					open a temp file to write to
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("professors.temp")));
					
					FileReader reader = new FileReader("professors.txt");
					BufferedReader br = new BufferedReader(reader);
					String line;
					
					while ((line = br.readLine()) != null) {
						if (line.contains(ProfessorList.get(index).getPassword())) {
							line = line.replace(ProfessorList.get(index).getPassword(), newPassword);
						}
//						always write the line, whether it is changed or not
						writer.println(line);
					}
//					close the writer
					writer.close();
					
					File realName = new File("professors.txt");
//					remove the old file
					realName.delete();
//					rename temp file
					new File("professors.temp").renameTo(realName);
					reader.close();
					
					ProfessorList.get(index).setPassword(newPassword);
					System.out.println("");
					System.out.println("Password successfully changed");
					System.out.println("");
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
//	check if professor exists in arraylist
	public static boolean containsName(ArrayList<Professor> p, String name) {
		for (Professor o : p) {
			if (o != null && o.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
