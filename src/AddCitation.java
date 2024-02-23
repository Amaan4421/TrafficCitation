			//Program Name: Traffic Citation Project----Add citation Screen
			//Author Name: Amaan Sajina
			//Date: 25/11/2023

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//class start
public class AddCitation implements SetScreen 
{
	
	 //global variables
	
	 private Stage stage;
	 TextField vField, dField, lField, vnField,fField,dateField;
	 CheckBox vehicleStolen, licenseSuspended;
	 ToggleGroup fineStatus;
	 private SQLException e;
	 
	 
	 //my sql details with username and password

	 
	 private static final String URL = "jdbc:mysql://localhost:3306/traffic";
	 private static final String USERNAME = "root";
	 private static final String PASSWORD = "rockstar4580";
	
	 
	//start method 
	 
	@Override
	public void start(Stage stage)
	{
		//override method
	}//end of start


	
    //show method
    public void show(Stage stage) 
    {
        this.stage = stage;
        stage.setTitle("Add Citation");

        //set pane in vBox style
        VBox vBox = fillForm();

        //two functional buttons
        
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> submit());

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clear());

        Button backButton = new Button("Back to Home Screen");
        backButton.setOnAction(e -> back());

        //2nd pane to fit both buttons
        HBox buttonBox = new HBox(submitButton, clearButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(20);
        
        
        //3rd pane
        HBox backButtonBox = new HBox(backButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(20);
        
        vBox.getChildren().addAll(buttonBox, backButtonBox);

        //add grid pane into scene
        Scene scene = new Scene(vBox, 500, 700);
        
        //set scene on stage
        stage.setScene(scene);
        
    }//end of show method

    
    
    //form method to collect data
    private VBox fillForm() 
    {
    	
    	//form set in grid style
    	VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(25, 25, 25, 25));
       
        Text text1 = new Text("Citation Form");
        text1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30; ");
        
        
        //Data fields to take input
        
        Label dateLabel = new Label ("Date:");
        dateField = new TextField();
        
        Label vLabel = new Label("Vehicle Number:");
        vField = new TextField();

        Label dLabel = new Label("Driver Name:");
        dField = new TextField();
        
        Label lLabel = new Label("License Number:");
        lField = new TextField();

        Label vnLabel = new Label("Violation Type:");
        vnField = new TextField();
        
        //select more than one option
        vehicleStolen = new CheckBox("Vehicle Stolen");
        licenseSuspended = new CheckBox("License Suspended");
        
        
        //select only one option for fine
        fineStatus = new ToggleGroup();

        RadioButton paid = new RadioButton("Paid");
        paid.setToggleGroup(fineStatus);

        RadioButton notPaid = new RadioButton("Not Paid");
        notPaid.setToggleGroup(fineStatus);

        //fine label
        Label fLabel = new Label("Fine Amount(In CAD):");
        fField = new TextField(); 

        
        //add all field into pane
        
        vbox.getChildren().addAll(text1, dateLabel, dateField, vLabel, vField, dLabel, dField, lLabel, lField,  vnLabel, vnField, vehicleStolen,
        		licenseSuspended, fLabel, fField, paid, notPaid);
        
        vbox.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));

        return vbox;
        
    }//end of form method

    
    
    
    //submit form method to add data into database
    private void submit() 
    {  
    	//calling adapter
    	
    	Citation citation = new Citation();
    	
    	
    	//get text from form and set into database
    	citation.setDate(dateField.getText());
        citation.setVehicleNumber(vField.getText());
        citation.setDriverName(dField.getText());
        citation.setLicenseNumber(lField.getText());
        citation.setViolation(vnField.getText());
        citation.setVehicleStolen(vehicleStolen.isSelected());
        citation.setLicenseSuspended(licenseSuspended.isSelected());
        citation.setFineAmount(fField.getText());
        citation.setfineStatus(fineStatus.getSelectedToggle());

        //inserting data into table/database
        insertCitation(0, citation);
    	
        //add data at specific vehicle id
        int vehicleId = AddCitation.insertVehicleDetails(citation);
        
        //if else condition to check id
        if (vehicleId != -1)
        {
            AddCitation.insertCitation(vehicleId, citation);
            
        }//end of if
        else 
        {
        	e = null;
        	e.printStackTrace();
        }//end of else
        
        //back going method call
    	backToScreenDialogBox();
    	
    }//end of submit method

    


	//insert citation data
    private static void insertCitation(int vehiclId, Citation citation) 
    {
        
        //insert query 
        
        String citationTable = "INSERT INTO citation (date, vehicle_number, driver_name, license_number, violation_type, vehicle_stolen, license_suspend, fine_amount) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        //try statement to make connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        		
        		//preparing connection bridge
        		
        PreparedStatement preparedStatement = connection.prepareStatement(citationTable, Statement.RETURN_GENERATED_KEYS)) 
        	{

            //set values into table
            preparedStatement.setString(1, citation.getDate());
            preparedStatement.setString(2, citation.getVehicleNumber());
            preparedStatement.setString(3, citation.getDriverName());
            preparedStatement.setString(4, citation.getLicenseNumber());
            preparedStatement.setString(5, citation.getViolation());
            preparedStatement.setBoolean(6, citation.isVehicleStolen());
            preparedStatement.setBoolean(7, citation.isLicenseSuspended());
            preparedStatement.setString(8, citation.getFineAmount());

            //execution
            preparedStatement.executeUpdate();
            
            
            ResultSet citationKeys = preparedStatement.getGeneratedKeys();
            int citationId = -1;
            if (citationKeys.next())
            {
                citationId = citationKeys.getInt(1);
            }

            // insert data into license table
            if (citationId != -1) 
            {
                insertLicenseDetails(citation.getLicenseNumber(), citation.getDriverName(), citation.isLicenseSuspended());
            }

        } //end of try block
        //catch block
        catch (SQLException e)
        {
            e.printStackTrace();
        }//end of catch block
        
    }//end of method
    
    
    
    //add license data into database 
    
    private static void insertLicenseDetails(String licenseNumber, String ownerName, boolean licenseSuspended) 
    {
        //sql query
        String licenseTable = "INSERT INTO license (license_number, owner_name, license_status) VALUES (?, ?, ?)";

        //try statement to make connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             // preparing connection bridge
             PreparedStatement licenseStatement = connection.prepareStatement(licenseTable)) {

            //set data into table
            licenseStatement.setString(1, licenseNumber);
            licenseStatement.setString(2, ownerName);
            licenseStatement.setBoolean(3, licenseSuspended);

            // execution
            licenseStatement.executeUpdate();

        }//end of try block
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    //add vehicle data into database 
    private static int insertVehicleDetails(Citation citation) 
    {
    	
        //insert query 
        
    	String vehicleTable = "INSERT INTO vehicle (vehicle_number, vehicle_status) VALUES (?, ?)";

    	
        //try statement to make connection
    	
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        		
        		//preparing connection bridge
        		
             PreparedStatement vehicleStatement = connection.prepareStatement(vehicleTable, PreparedStatement.RETURN_GENERATED_KEYS)) {

            //set values into table
            vehicleStatement.setString(1, citation.getVehicleNumber());
            vehicleStatement.setBoolean(2, citation.isVehicleStolen());

            //execution
            vehicleStatement.executeUpdate();

            //retrieve vehicle id
            ResultSet vehicleKeys = vehicleStatement.getGeneratedKeys();
            
            int vehicleId = -1;
            if (vehicleKeys.next()) 
            {
                vehicleId = vehicleKeys.getInt(1);
            }

            return vehicleId;
        } //end of try block
        
        catch (SQLException e) 
        {
            e.printStackTrace();
            return -1;
        }//end of catch block
	}//end of method
    

    
    
    //method for back going
    
    private void backToScreenDialogBox() 
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Citation Issued Successfully!!!");
        alert.setContentText("The citation has been added to database!!!");

        ButtonType backButton = new ButtonType("Back to Home Screen");
        alert.getButtonTypes().setAll(backButton);

        alert.showAndWait().ifPresent(response -> {
        	
        	//condition
            if (response == backButton) 
            {

                // Close the current stage (CitationFormScreen)
                stage.close();

                // Show the PoliceScreen
                PoliceScreen policeScreen = new PoliceScreen();
                policeScreen.show(new Stage());
            }
        });
    }

    
    //clear or reset form method
    private void clear() 
    {
    	//clear all data in form
    	
		vField.clear();
        dField.clear();
        vnField.clear();
        lField.clear();
        fField.clear();
        vehicleStolen.setSelected(false);
        licenseSuspended.setSelected(false);
        fineStatus.getSelectedToggle().setSelected(false);
        
    }//end of clear method
    
     
    //back to screen button without submitting form
    private void back()
    {
    	PoliceScreen screen = new PoliceScreen();
    	screen.show(stage);
    }


}//end of class


