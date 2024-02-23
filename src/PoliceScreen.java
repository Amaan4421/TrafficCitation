			//Program Name: Traffic Citation Project----Local Police Screen
			//Author Name: Amaan Sajina
			//Date: 23/11/2023

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//class start 
public class PoliceScreen extends Application implements SetScreen 
{
	//global variable for all method
	private Stage stage;
	
	
	//my sql details with username and password

	 
	private static final String URL = "jdbc:mysql://localhost:3306/traffic";
	private static final String USERNAME = "root";	
	private static final String PASSWORD = "rockstar4580";
	
	
	//stage method 
	
	public void show(Stage stage) 
	{
		this.stage = stage;
		stage.setTitle("Application");
		
		User user = getUser(1);

        //Border pane used to manage UI in manner
        
        BorderPane borderPane = new BorderPane();

        //logout button at top right corner
        
        Button logoutButton = new Button("Log Out");
        logoutButton.setTextFill(Color.RED);
        logoutButton.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white;"
        		+ " -fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 16");
        
        logoutButton.setOnAction(e -> logOut());
        
        
        //Menu with all functional options(Drop-down feature)
        
        MenuButton menuButton = new MenuButton("Menu");
        MenuItem menuItem1 = new MenuItem("Profile");
        MenuItem menuItem2 = new MenuItem("Settings");
        MenuItem menuItem3 = new MenuItem("Collect Payment");
        menuButton.getItems().addAll(menuItem1, menuItem2, menuItem3);
        
        menuItem1.setOnAction(e -> openProfileScreen(user));
        
        //css style for button
        
        menuButton.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white; "
        		+ "-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 16");

        
        //Menu and log out button on top with hBox pane to show horizontally in one line
        
        HBox leftsideBox = new HBox(400);
        leftsideBox.setAlignment(Pos.CENTER_LEFT);
        leftsideBox.setPadding(new Insets(10, 10, 10, 10));
        leftsideBox.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));
        
        //add both buttons in pane
        
        leftsideBox.getChildren().addAll(menuButton, logoutButton);

        
        //set vBox in border pane(Main scene)left side
        
        borderPane.setTop(leftsideBox);

        
        //Functional buttons
        
        Text text1 = new Text("Stay Safe!!!");
        text1.setFill(Color.BLUE);
    	text1.setFont(Font.font("Times new roman",20));
    	
    	
    	//features of local police
    	
        Button issueWarrent = new Button("Issue Warrents");
        Button issueCitation = new Button("Issue Traffic Violation Ticket/Citation");
        Button lDetails = new Button("Get License Details");
        Button vDetails = new Button("Get Vehicle Details");
        
        //css style for buttons
        
        issueWarrent.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white; -fx-font-weight: bold;");
        issueCitation.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white; -fx-font-weight: bold;");
        lDetails.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white; -fx-font-weight: bold;");
        vDetails.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white; -fx-font-weight: bold;");
        
        
        //button click events 
        
        issueWarrent.setOnAction(e -> addWarrentScreen());
        issueCitation.setOnAction(e -> addCitationScreen());
        lDetails.setOnAction(e -> getLicenseDetails());
        vDetails.setOnAction(e -> getVehicleDetails(null));

        
        //Set all functions in another Pane and set that Pane in center
        
        VBox centerBox = new VBox(issueWarrent, issueCitation, lDetails, vDetails, text1);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(20);
        centerBox.setBackground(new Background (new BackgroundFill(Color.FLORALWHITE, null, null)));

        
        //add 2nd vBox in border pane(Main scene) in center
        
        borderPane.setCenter(centerBox);

        //set pane in scene
        Scene scene = new Scene(borderPane, 600, 400);
        stage.setScene(scene);
        stage.show();
        
	}//end of stage method
	

	
	//get user details from database

	private User getUser(int userId) 
	{
		User user = null;

		//try statement for making connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) 
        {
        	//query to select data
            String userTable = "SELECT * FROM user WHERE id = ?";
            
            //2nd try block
            try (PreparedStatement preparedStatement = connection.prepareStatement(userTable)) {
                preparedStatement.setInt(1, userId);
                
                //3rd try block
                try (ResultSet resultSet = preparedStatement.executeQuery()) 
                {
                	//check condition
                    if (resultSet.next())
                    {
                        String email =  resultSet.getString("email");
                        String password = resultSet.getString("password");
                        String username = resultSet.getString("user_name");
                        String address =  resultSet.getString("user_address");
                        String number = resultSet.getString("user_number");
                        String dob = resultSet.getString("user_dob");
						user = new User(email, password, username, address, number, dob);
                    }//end of if
                }//end of 3rd try block
            }//end of 2nd try block
        }//end of 1st try block 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }//end of catch block

        //return user details
        return user;
        
	}//end of method

	


	//button click event methods
	
	
	//calling profile screen

	private void openProfileScreen(User user) 
	{
		ProfileScreen screen = new ProfileScreen();
		screen.show(stage, user);
	
	}//end of method

	
	
	//calling warrent class method(Open warrent screen)
	
	private void addWarrentScreen() 
	{	
		AddWarrent addWarrent = new AddWarrent();
		addWarrent.show(stage);
		
	}//end of method


	//calling citation class method(Open citation screen)
	
	private void addCitationScreen() 
	{
		AddCitation addCitation = new AddCitation();
		addCitation.show(stage);
		
    }//end of method
	
	
	
	
	
	//calling license screen and show data on screen
	
	private void getLicenseDetails() 
	{
		
		//calling dialogue box method
		
        String licenseNumber = LicenseNumberBox.showAndWait();

        //get data from database
        License license = getLicenseDetails(licenseNumber);

        
        //condition
        if (license != null) 
        {
            LicenseScreen.show(license);
        }//end of if 
        else 
        {
        	Alert alert = new Alert(Alert.AlertType.NONE);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Data not found!!!");
        }//end of else
    }//end of method

	
	
	//retrieve license details from database(license table)
	
    private License getLicenseDetails(String licenseNumber) 
    {

        //select query
        
        String sql = "SELECT * FROM license WHERE license_number = ?";
        License license = null;

        
        //try statement
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {

            statement.setString(1, licenseNumber);

            //execution in 2nd try block
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next()) 
                {
                    //show status
                	String retrievedNumber = resultSet.getString("license_number");
                    String ownerName = resultSet.getString("owner_name");
                    String status = resultSet.getString("license_status");
                    
                    license = new License(retrievedNumber, ownerName, status);
                }//end of if
            }//end of inner try

        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }//end of catch block

        return license;
    }//end of method
    
	
    
	
    
    //calling vehicle screen and show data on screen
    
  	private void getVehicleDetails(Vehicle vehicle1) 
  	{
  		
  		//calling dialogue box method
  		
          String vehicleNumber = VehicleNumberBox.showAndWait();

          //get data from database
          Vehicle vehicle = getVehicleDetails(vehicleNumber,  vehicle1);

          
          //condition
          if (vehicle != null) 
          {
              VehicleScreen.show(vehicle);
          }//end of if 
          else 
          {
          	  Alert alert = new Alert(Alert.AlertType.NONE);
              alert.initStyle(StageStyle.UTILITY);
              alert.setTitle("Data not found!!!");
          }//end of else
      }//end of method

  	
  	
  	  //retrieve vehicle details from database(vehicle table)
  	
      private Vehicle getVehicleDetails(String vehicleNumber, Vehicle vehicle) 
      {
      	 
          //select query
          
          String sql = "SELECT * FROM vehicle WHERE vehicle_number = ?";

          
          //try statement
          try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
               PreparedStatement statement = connection.prepareStatement(sql))
          {

			statement.setString(1, vehicleNumber);

              //execution in 2nd try block
              try (ResultSet resultSet = statement.executeQuery())
              {
                  if (resultSet.next()) 
                  {
                      //show status
                  	  String retrievedNumber = resultSet.getString("vehicle_number");
                      Boolean status = resultSet.getBoolean("vehicle_status");
                      String color = resultSet.getString("vehicle_color");
                      String model = resultSet.getString("vehicle_model");
                      int year = resultSet.getInt("vehicle_year");
                      String ownerName = resultSet.getString("owner_name");
                      
                      vehicle = new Vehicle(retrievedNumber, status , color, model, year, ownerName);
                  }//end of if
              }//end of inner try

          } 
          catch (SQLException e)
          {
              e.printStackTrace();
          }//end of catch block

          return vehicle;
      }//end of method
	
    
      
    //take user back to login screen
      
    private void logOut() 
    {
    	stage.close();
    	LoginScreen loginScreen = new LoginScreen("Local Police", stage);
    	loginScreen.show();
    }
    
    
	 @Override
	 public void start(Stage stage)
	 {
		 //overriding method
	 }
	 
}//end of class
