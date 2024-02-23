			//Program Name: Traffic Citation Project----License Adapter
			//Author Name: Amaan Sajina
			//Date: 26/11/2023

public class License
{
	
	//variable declaration
	
    private int id;
    private String license_number;
    private String license_status;
    private String owner_name;

    //default constructor
    public License()
    {
 
    }

    //second constructor
    public License(String license_number, String ownerName, String owner_name) {
        this.license_number = license_number;
        this.license_status = ownerName;
        this.owner_name = owner_name;
    }

    //getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return license_number;
    }

    public void setNumber(String license_number) {
        this.license_number = license_number;
    }

    public String getStatus() {
        return license_status;
    }

    public void setStatus(String license_status) {
        this.license_status = license_status;
    }
    
    public String getOwnerName() {
        return owner_name;
    }

    public void setOwnerName(String owner_name) {
        this.owner_name = owner_name;
    }
}//end of adapter

