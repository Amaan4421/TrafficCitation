			//Program Name: Traffic Citation Project----Citation Adpater
			//Author Name: Amaan Sajina
			//Date: 25/11/2023


import javafx.scene.control.Toggle;


public class Citation 
{
	//variable declaration
	
    private int id;  
    private String date;
    private String vehicleNumber;
    private String driverName, licenseNumber;
    private String violation;
    private boolean vehicleStolen;
    private boolean licenseSuspended;
    private String fineAmount;
	private Toggle fineStatus;

   
    //default constructor
    public Citation() 
    {
    }

    //second constructor
    
    public Citation(String date, String vehicleNumber, String driverName, String licenseNumber, String violation, boolean vehicleStolen,
                    boolean licenseSuspended, Toggle fineStatus, String fineAmount) 
    {
    	this.date = date;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.licenseNumber = licenseNumber;
        this.violation = violation;
        this.vehicleStolen = vehicleStolen;
        this.licenseSuspended = licenseSuspended;
        this.fineStatus = fineStatus;
        this.fineAmount = fineAmount;
    }

    //getter setter method
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date)
    {
    	this.date = date;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public boolean isVehicleStolen() {
        return vehicleStolen;
    }

    public void setVehicleStolen(boolean vehicleStolen) {
        this.vehicleStolen = vehicleStolen;
    }

    public boolean isLicenseSuspended() {
        return licenseSuspended;
    }

    public void setLicenseSuspended(boolean licenseSuspended) {
        this.licenseSuspended = licenseSuspended;
    }

    public Toggle isfineStatus() {
        return fineStatus;
    }

    public void setfineStatus(Toggle toggle) {
        this.fineStatus = toggle;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

}//end of adapter
