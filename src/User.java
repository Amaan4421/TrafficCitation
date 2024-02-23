			//Program Name: Traffic Citation Project----User/Profile Adapter
			//Author Name: Amaan Sajina
			//Date: 26/11/2023

public class User
{
	
	//global variables
	
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    
    //default constructor
    public User(){

    }

    //2nd constructor

    public User(String email, String password, String username, String address, String phoneNumber, String dateOfBirth) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    //getter and setter methods

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}//end of adapter
