			//Program Name: Traffic Citation Project----Vehicle Details Screen
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
public class VehicleScreen
{
	//show screen
	public static void show(Vehicle vehicle) 
	{
		//stage title
		
		Stage stage = new Stage();
        stage.setTitle("Vehicle Details");
        
        Text text1 = new Text("Vehicle Details");
        text1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30; ");
        
        //labels of particular data
        
        Label l1 = new Label("Vehicle Number: ");
        Label l2 = new Label("Vehicle Status: ");
        Label l3 = new Label("Vehicle Color: ");
        Label l4 = new Label("Vehicle Model: ");
        Label l5 = new Label("Vehicle Year: ");
        Label l6 = new Label("Owner Name: ");
        
        
        //text fields to display data from table
        Text t1 = new Text(vehicle.getNumber());
        Text t2 = new Text(String.valueOf(vehicle.getStatus()));
        Text t3 = new Text(vehicle.getColor());
        Text t4 = new Text(vehicle.getModel());
        Text t5 = new Text(String.valueOf(vehicle.getYear()));
        Text t6 = new Text(vehicle.getOwnerName());

        //vbox pane to add all labels
        
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");
        vbox.setBackground(new Background (new BackgroundFill(Color.LIGHTCORAL, null, null)));
        vbox.getChildren().addAll(text1, l1, t1, l2, t2, l3, t3, l4, t4, l5, t5, l6, t6);


        //set scene on stage
        Scene scene = new Scene(vbox, 500, 700);
        stage.setScene(scene);
        stage.show();
        
	}//end of show method

}//end of class
