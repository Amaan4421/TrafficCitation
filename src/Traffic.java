			//Program Name: Traffic Citation Project----Home/Splash Screen
			//Author Name: Amaan Sajina
			//Date: 23/11/2023

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;


//class start
public class Traffic extends Application
{

    //main login screen with buttons
    @Override
    public void start(Stage buttonStage)
    {
    	//page title
    
    	buttonStage.setTitle("Traffic Citation and Reporting System");

    	//general note on screen
    	
    	Text screenText1 = new Text("Welcome to traffic citation management system!!");
    	Text screenText2 = new Text("Click an appropriate button from below list for log in!!!");
    	
    	
    	//font style for text 1
    	
    	screenText1.setFill(Color.BLUE);
    	screenText1.setFont(Font.font("Times new roman",30));
    	screenText1.setStyle("-fx-font-weight: bold;");
    	
    	//font style for text 2
    	
    	screenText2.setFill(Color.BLUE);
    	screenText2.setFont(Font.font("Times new roman",20));
    	screenText2.setStyle("-fx-font-weight: bold;");
    	
    	
    	//Log in buttons method
        
    	Button policeButton = new Button("Local Police");
        policeButton.setOnAction(e -> loginScreen("Local Police", buttonStage));

        Button agencyButton = new Button("Government Agency");
        agencyButton.setOnAction(e -> loginScreen("Government Agency", buttonStage));

        Button schoolButton = new Button("Traffic School");
        schoolButton.setOnAction(e -> loginScreen("Traffic School", buttonStage));

        //css style
        
        policeButton.setStyle("-fx-border-color: black");
        agencyButton.setStyle("-fx-border-color: black");
        schoolButton.setStyle("-fx-border-color: black");
        
        
        //set all buttons vertically in screen
        
        VBox buttonsLogin = new VBox(30);
        buttonsLogin.getChildren().addAll(screenText1,screenText2,policeButton, agencyButton, schoolButton);
        buttonsLogin.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));
        buttonsLogin.setAlignment(Pos.CENTER);

        
               
        //show main scene on stage with notes and buttons
        
        Scene scene = new Scene(buttonsLogin, 800, 400);
        buttonStage.setScene(scene);
        buttonStage.show();
        
    }//end of start method

    
    
    //calling login screen class
    
    private void loginScreen(String userType,Stage stage)
    {
    	LoginScreen screen = new LoginScreen(userType, stage);
		screen.show();
		
	}//end of class
	
    
    
	//main method
    public static void main(String[] args)
    {
        launch(args);
    }//end of main
    
}//end of class
