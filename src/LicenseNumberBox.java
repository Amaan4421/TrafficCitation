			//Program Name: Traffic Citation Project----License Number Dialouge Box
			//Author Name: Amaan Sajina
			//Date: 26/11/2023


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;


//class method
public class LicenseNumberBox {

	//method start
    public static String showAndWait() 
    {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Enter License Number");

        //take input
        TextField licenseNumberField = new TextField();
        GridPane grid = new GridPane();
        grid.add(licenseNumberField, 0, 0);
        alert.getDialogPane().setContent(grid);

        //button 
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();

        return licenseNumberField.getText();
    }
}
