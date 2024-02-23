			//Program Name: Traffic Citation Project----Profile Screen
			//Author Name: Amaan Sajina
			//Date: 26/11/2023


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileScreen
{
	//global variables
	
    private Label name, address, email, dob, number;
    private VBox vbox;

    
    
    //constructor
    
    public ProfileScreen() 
    {
        name = new Label();
        address = new Label();
        email = new Label();
        dob = new Label();
        number = new Label();
        vbox = new VBox();
    }

    
    
    // show method
    public void show(Stage primaryStage, User user) 
    {
        initialize(user);

        // Create a new stage for the profile screen
        Stage profileStage = new Stage();
        profileStage.setTitle("User Profile");

        // Set up a scene with the VBox
        Scene scene = new Scene(vbox, 300, 200);

        // Set the scene for the stage
        profileStage.setScene(scene);

        // Show the stage
        profileStage.showAndWait();
    }

    
    //show data method
    
    public void initialize(User user) 
    {
    	//retrieve data from table and show on screen
    	
        name.setText("Name: " + user.getUsername());
        address.setText("Address: " + user.getAddress());
        email.setText("Email: " + user.getEmail());
        dob.setText("Date of Birth: " + user.getDateOfBirth());
        number.setText("Mobile Number: " + user.getPhoneNumber());

        vbox.getChildren().addAll(name, address, email, dob, number);
    }//end of method

}//end of class
