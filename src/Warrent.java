			//Program Name: Traffic Citation Project----Warrent Adapter
			//Author Name: Amaan Sajina
			//Date: 26/11/2023

public class Warrent
{
	//variable declaration
	
    private int id;
    private String date;
    private String warrentReason;
    private String issuedTo;
    private String licenseNumber; 
    
    //default constructor
    public Warrent() {
    	
    }
    
    
    //second constructor

    public Warrent(String date, String warrentReason, String issuedTo, String licenseNumber) {
        this.date = date;
        this.warrentReason = warrentReason;
        this.issuedTo = issuedTo;
        this.licenseNumber = licenseNumber;
    }

    //getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWarrentReason() {
        return warrentReason;
    }

    public void setWarrentReason(String warrentReason) {
        this.warrentReason = warrentReason;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}//end of adapter
