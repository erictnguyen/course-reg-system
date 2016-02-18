import java.util.ArrayList;

public class Student extends Person implements PersonManipulation {

//	declare constants
	private static final int MIN = 17000000;
	private static final int MAX = 17999999;
	private static final char N = 'N';
	
	private int dob;
	private String major;
	private double gpa = 4.0;
	
//	default constructor
	public Student() {	
		super();
		int num = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
		this.id = Character.toString(N) + Integer.toString(num);
		registeredCourses = new ArrayList<String>();
	}
	
//	overloaded constructor
	public Student(String name, String ssn, String address, String phone) {
		super(name, ssn, address, phone);
	}

//	getter methods
	public String getName() {
		return this.name;
	}
	public String getID() {
		return this.id;
	}
	public String getSSN() {
		return this.ssn;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhone() {
		return this.phone;
	}
	public int getDOB() {
		return this.dob;
	}
	public double getGPA() {
		return this.gpa;
	}
	public String getMajor() {
		return this.major;
	}
	public ArrayList<String> getRegisteredCourses() {
		return this.registeredCourses;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}

//	setter methods
	public void setName(String name) {
		this.name = name;
	}
	public void setID(String id) {
		this.id = id;
	}
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setDOB(int dob) {
		this.dob = dob;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

//	@Override
//	public void create(String) {
//		Scanner input = new Scanner(System.in);
//		System.out.println("Enter student's first and last name: ");
//		setName(input.nextLine());
//	}

	@Override
	public void print() {
		System.out.println(name + " " + id);
//				+ "\nMajor: " + major
//				+ "\nDOB: " + dob
//				+ "\nAddress: " + address 
//				+ "\nPhone: " + phone);
	}
	
}
