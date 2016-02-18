import java.util.ArrayList;

public abstract class Person {

	protected String name;
	protected String ssn;
	protected String address;
	protected String phone;
	protected String id;
	protected String username;
	protected String password;
	protected ArrayList<String> registeredCourses;
	
//	default constructor
	public Person() {
		
	}
	
//	overloaded constructor
	public Person (String name, String ssn, String address, String phone) {
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.phone = phone;
	}
	
	public abstract void print();
}
