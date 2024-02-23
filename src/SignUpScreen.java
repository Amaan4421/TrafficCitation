
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpScreen implements SetScreen 
{
    private TextField usernameField,emailAddress,dateOfBirth,mobileNumber,userAddress;
    private PasswordField passwordField;

    private static final String URL = "jdbc:mysql://localhost:3306/traffic";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rockstar4580";

    
    public void show(Stage stage) 
    {
		stage.setTitle("Sign Up");

		
		Text text1 = new Text("Sign Up");
        text1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30; ");


        //input fields
        
        emailAddress = new TextField();
        emailAddress.setPromptText("Email");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        
        mobileNumber = new TextField();
        mobileNumber.setPromptText("Mobile Number");
        
        dateOfBirth = new TextField();
        dateOfBirth.setPromptText("Date of Birth");
        
        userAddress = new TextField();
        userAddress.setPromptText("Address");


        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> handleSignUp(stage));

        //add all fields into vbox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(text1, emailAddress, passwordField, usernameField, mobileNumber, dateOfBirth, userAddress, signUpButton);
        vbox.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setSpacing(10);

        //set scene and show the stage
        Scene scene = new Scene(vbox, 500, 700);
        stage.setScene(scene);
        stage.show();
    }

    
    //sign up method
    private void handleSignUp(Stage stage) 
    {
    	//store all data in backend for profile 
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailAddress.getText();
        String number = mobileNumber.getText();
        String dob = dateOfBirth.getText();
        String address = userAddress.getText();

        //add into database
        saveUserToDatabase(email, password, username, number, dob, address);

        PoliceScreen screen = new PoliceScreen();
		screen.show(stage);
    }	


    //insert data into table method
    
    private void saveUserToDatabase(String email, String password, String userName, String userNumber, String userDob, String userAddress ) 
    {
    	// try statement for making connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
        	
        	//insert query
        	
            String userTable = "INSERT INTO user (email, password, user_name, user_number, user_dob, user_address) VALUES (?, ?, ?, ?, ?, ?)";
            
            //2nd try block
            try (PreparedStatement preparedStatement = connection.prepareStatement(userTable)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, userName);
                preparedStatement.setString(4, userNumber);
                preparedStatement.setString(5, userDob);
                preparedStatement.setString(6, userAddress);
                preparedStatement.executeUpdate();
            }//end of inner try
        }//end of outer try
        catch (SQLException e)
        {
            e.printStackTrace();
        }//end of catch
    }//end of method

	@Override
	public void start(Stage stage)
	{
		//overriding method
	}

}//end of class
