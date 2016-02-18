import java.util.ArrayList;

public class Professor extends Person implements PersonManipulation {

//	declare constants
	private static final int MIN = 16000000;
	private static final int MAX = 16999999;
	private static final char N = 'N';
	
	private String office;
	
//	default constructor
	public Professor() {
		super();
		int num = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
		this.id = Character.toString(N) + Integer.toString(num);
		registeredCourses = new ArrayList<String>();
	}
	
//	overloaded constructor
	public Professor(String name, String ssn, String address, String phone) {
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
	public String getOffice() {
		return this.office;
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
	public void setOffice(String office) {
		this.office = office;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void print() {
		System.out.println(name + " " + id);
	}
}
