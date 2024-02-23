			//Program Name: Traffic Citation Project----License Details Screen
			//Author Name: Amaan Sajina
			//Date: 26/11/2023


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//class start
public class LicenseScreen
{
	//show screen
	public static void show(License license) 
	{
		//stage title
		
		Stage stage = new Stage();
        stage.setTitle("License Details");
        
        Text text1 = new Text("License Details");
        text1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30; ");
        
        //labels of particular data
        
        Label l1 = new Label("License Number: ");
        Label l2 = new Label("Owner Name: ");
        Label l3 = new Label("License Status: ");
        
        //text fields to display data from table
        Text t1 = new Text(license.getNumber());
        Text t3 = new Text(license.getOwnerName());
        Text t2 = new Text(license.getStatus());

        //vbox pane to add all labels
        
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");
        vbox.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));
        vbox.getChildren().addAll(text1, l1, t1, l2, t2, l3, t3);

        //set scene on stage
        Scene scene = new Scene(vbox, 500, 500);
        stage.setScene(scene);
        stage.show();
        
	}//end of show method

}//end of class
