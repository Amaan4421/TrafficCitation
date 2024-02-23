			//Program Name: Traffic Citation Project----Vehicle Adapter
			//Author Name: Amaan Sajina
			//Date: 26/11/2023

public class Vehicle 
{
	
	//declare variables
	
    private int id;
    private String vehicle_number;
    private boolean vehicle_status;
    private String vehicle_model;
    private String vehicle_color;
    private int vehicle_year;
    private String owner_Name;

    
    //default constructor 
    public Vehicle() 
    {
    }
    
    
    //second constructor

    public Vehicle(String vehicle_number, boolean vehicle_status, String vehicle_model, String vehicle_color, int vehicle_year, String owner_Name) {
        this.vehicle_number = vehicle_number;
        this.vehicle_status = vehicle_status;
        this.vehicle_model = vehicle_model;
        this.vehicle_color = vehicle_color;
        this.vehicle_year = vehicle_year;
        this.owner_Name = owner_Name;
    }
    

    //getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return vehicle_number;
    }

    public void setNumber(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public boolean getStatus() {
        return vehicle_status;
    }

    public void setStatus(boolean b) {
        this.vehicle_status = b;
    }

    public String getModel() {
        return vehicle_model;
    }

    public void setModel(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getColor() {
        return vehicle_color;
    }

    public void setColor(String vehicle_color) {
        this.vehicle_color = vehicle_color;
    }

    public int getYear() {
        return vehicle_year;
    }

    public void setYear(int vehicle_year) {
        this.vehicle_year = vehicle_year;
    }

    public String getOwnerName() {
        return owner_Name;
    }

    public void setOwnerName(String owner_Name) {
        this.owner_Name = owner_Name;
    }
    
}//end of adapter