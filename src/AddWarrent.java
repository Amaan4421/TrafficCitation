			//Program Name: Traffic Citation Project----Add Warrent Screen
			//Author Name: Amaan Sajina
			//Date: 26/11/2023

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



//class code start
public class AddWarrent implements SetScreen
{
		
	//global variables
	
	private Stage stage;
	TextField dateField, wField, iField, lField;
		 
		 
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
	        stage.setTitle("Add Warrent");

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
	       
	        Text text1 = new Text("Warrent Form");
	        text1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30; ");
	        
	        
	        //Data fields to take input
	        
	        Label dateLabel = new Label ("Date:");
	        dateField = new TextField();
	        
	        Label wLabel = new Label("Warrent Reason:");
	        wField = new TextField();

	        Label iLabel = new Label("Issue To:");
	        iField = new TextField();
	        
	        Label lLabel = new Label("License Number:");
	        lField = new TextField();
	        
	        
	        //add all field into pane
	        
	        vbox.getChildren().addAll(text1, dateLabel, dateField, wLabel, wField, iLabel, iField, lLabel, lField);
	        
	        vbox.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));

	        return vbox;
	        
	    }//end of form method
	    
	    
	    
	    //submit form method to add data into database
	    private void submit() 
	    {  
	    	//calling adapter
	    	
	    	Warrent warrent = new Warrent();
	    	
	    	
	    	//get text from form and set into database
	    	warrent.setDate(dateField.getText());
	    	warrent.setWarrentReason(wField.getText());
	    	warrent.setIssuedTo(iField.getText());
	    	warrent.setLicenseNumber(lField.getText());

	        //inserting data into table/database
	        insertWarrent(warrent);
	       
	        
	        //back going method call
	    	backToScreenDialogBox();
	    	
	    }//end of submit method
	    
	    
	    
	   

	    //insert warrent data into database/table
		private static void insertWarrent(Warrent warrent) 
		{
			//insert query 
	        
	        String warrentTable = "INSERT INTO warrent (date, warrent_reason, warrent_issued_to, license_number)"
	                                            + "VALUES (?, ?, ?, ?)";

	        //try statement to make connection
	        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	        		
	        		//preparing connection bridge
	        		
	           PreparedStatement preparedStatement = connection.prepareStatement(warrentTable)) 
	        	{

	            //set values into table
	            preparedStatement.setString(1, warrent.getDate());
	            preparedStatement.setString(2, warrent.getWarrentReason());
	            preparedStatement.setString(3, warrent.getIssuedTo());
	            preparedStatement.setString(4, warrent.getLicenseNumber());

	            //execution
	            preparedStatement.executeUpdate();

	        } //end of try block
	        //catch block
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }//end of catch block
			
		}
		
		


		//method for back going
	    
	    private void backToScreenDialogBox() 
	    {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Success");
	        alert.setHeaderText("Warrent Issued to mentioned person Successfully!!!");
	        alert.setContentText("The warrent has been added to database!!!");

	        ButtonType backButton = new ButtonType("Back to Home Screen");
	        alert.getButtonTypes().setAll(backButton);

	        alert.showAndWait().ifPresent(response -> {
	        	
	        	//condition
	            if (response == backButton) 
	            {

	                // Close the current stage (WarrentFormScreen)
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
	    	
	    	dateField.clear();
			wField.clear();
	        iField.clear();
	        lField.clear();
	        
	    }//end of clear method
	    
	    
	    //back to screen button without submitting form
	    private void back()
	    {
	    	PoliceScreen screen = new PoliceScreen();
	    	screen.show(stage);
	    }
	    
}//end of class code
