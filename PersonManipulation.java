
public interface PersonManipulation {

	String getName();
	String getID();
	String getSSN();
	String getAddress();
	String getPhone();
	String getUsername();
	String getPassword();
	
	void setName(String n);
	void setSSN(String ssn);
	void setAddress(String a);
	void setPhone(String p);
	void setUsername(String u);
	void setPassword(String p);
}
