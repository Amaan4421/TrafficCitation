			//Program Name: Traffic Citation Project----Login Screen
			//Author Name: Amaan Sajina
			//Date: 25/11/2023


import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Insets;


//class start
public class LoginScreen implements SetScreen
{
	
	//initial variable for all method
	private final String userType;
	private Stage stage;
	
	
	//my sql details with username and password

	 
	 private static final String URL = "jdbc:mysql://localhost:3306/traffic";
	 private static final String USERNAME = "root";
	 private static final String PASSWORD = "rockstar4580";
		
	
	
	//store button clicked value ex.police,agency,school
	public LoginScreen(String userType, Stage stage) 
	{
        this.userType = userType;
        this.stage = stage;
        
    }//end of method

	
	//show UI screen
	public void show()
	{
		//main screen to set buttons and text-field
		Stage loginStage = new Stage();
        loginStage.setTitle("Login");

        //page title
        Text text1 = new Text("Log in");
        text1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30; ");
        
        
        //log in form
        
        //user-name field
        Label idLabel = new Label("Badge Number/ID/username:");
        TextField idField = new TextField();
        idLabel.setStyle("-fx-font-weight: bold;");
        idField.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        
        //password field
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordLabel.setStyle("-fx-font-weight: bold;");
        passwordField.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        
        
        //login button
        Button loginButton = new Button("Login"); 
        loginButton.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        
        
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        
		//calling method
        loginButton.setOnAction(e -> handleLogin(idField.getText(), passwordField.getText()));
        
        signUpButton.setOnAction(e -> signUp());

        
        //vBox Pane to set text-fields vertically
        VBox loginLayout = new VBox(15);
        loginLayout.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));
        loginLayout.getChildren().addAll(text1, idLabel, idField, passwordLabel, passwordField, loginButton, signUpButton);
        loginLayout.setPadding(new Insets(10, 10, 10, 10));
        
        //set vBox in scene
        Scene loginScene = new Scene(loginLayout, 500, 500);
        loginStage.setScene(loginScene);
        loginStage.show();
		
	}//end of show method
	
	
	private void signUp() 
	{
		SignUpScreen screen = new SignUpScreen();
		screen.show(stage);
	}


	//login handling method
    
    private void handleLogin(String username, String password) 
    {
    	//take string of user type in if condition
    	if ("Local Police".equals(userType) || "Government Agency".equals(userType) || "Traffic School".equals(userType)) 
    	{
    		//call method to match user data in table
            if (authenticateUser(username, password)) 
            {
            	//open screen respectively
                switch (userType) 
                {
                    case "Local Police":
                        policeScreen();
                        break;
                    case "Government Agency":
                        agencyScreen();
                        break;
                    case "Traffic School":
                        schoolScreen();
                        break;
                }//end of switch
            }//end of if 
            else 
            {
                showAlert("Invalid Credentials", "Email/ID not found or incorrect password.");
            }//end of else
        }//end of if
    }//end of method
    
    
    
    //check user data exist in table or not
    
    private boolean authenticateUser(String username, String password) 
    {
    	//sql query
    	
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        
        //try block
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query))
        {

            statement.setString(1, username);
            statement.setString(2, password);

            //inner try block
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                return resultSet.next();
            }//end of inner try block
        }//end of try block 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }//end of catch block
    }//end of method
    
    
    

    //alert box pop up method
    private void showAlert(String title, String content) 
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    	

    //calling police screen class
    
	private void policeScreen() 
	{
		PoliceScreen screen = new PoliceScreen();
		screen.show(stage);
	
	}//end of method

	
	//calling School screen class
    
	private void schoolScreen() 
	{
		SchoolScreen screen = new SchoolScreen();
		screen.show(stage);
		
	}//end of method
	
	
	//calling Agency screen class
    
	private void agencyScreen() 
	{
		AgencyScreen screen = new AgencyScreen();
		screen.show(stage);
			
	}//end of method

	@Override
	public void start(Stage stage)
	{
		//overriding method
	}
	
}//end of class code