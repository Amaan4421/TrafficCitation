			//Program Name: Traffic Citation Project----Agency Screen
			//Author Name: Abdur Rafay
			//Date: 26/11/2023


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgencyScreen extends Application implements SetScreen {
    private Connection connection;
    
     private static final String url = "jdbc:mysql://localhost:3306/traffic";
	 private static final String username = "root";
	 private static final String password = "rockstar4580";

	 
	 public void show(Stage stage) {

			connectToDatabase();

	        stage.setTitle("Agency Traffic System");
	        BorderPane borderPane = new BorderPane();

	        // Menu setup
	        MenuButton menuButton = new MenuButton("Menu");
	        MenuItem agencyProfile = new MenuItem("Agency Profile");
	        MenuItem settings = new MenuItem("Settings");
	        menuButton.getItems().addAll(agencyProfile, settings);

	        VBox leftsideBox = new VBox(menuButton);
	        leftsideBox.setAlignment(Pos.CENTER_LEFT);
	        leftsideBox.setPadding(new Insets(10, 10, 10, 10));
	        borderPane.setTop(leftsideBox);

	        // Buttons for main functionalities
	        Button managePersonnelButton = new Button("Manage Personnel");
	        Button trackVehiclesButton = new Button("Track Vehicles");

	        // Event Handlers
	        managePersonnelButton.setOnAction(e -> managePersonnel());
	        trackVehiclesButton.setOnAction(e -> trackVehicles());

	        VBox centerBox = new VBox(managePersonnelButton, trackVehiclesButton);
	        centerBox.setAlignment(Pos.CENTER);
	        centerBox.setSpacing(20);

	        borderPane.setCenter(centerBox);

	        Scene scene = new Scene(borderPane, 600, 400);
	        stage.setScene(scene);
	        stage.show();
			
		}
	 
   

    private void connectToDatabase() {
        try {
            
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection failure
        }
    }

    private void managePersonnel() {
        // New window for managing personnel
        Stage personnelStage = new Stage();
        VBox personnelLayout = new VBox(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField roleField = new TextField();
        roleField.setPromptText("Role");
        Button addButton = new Button("Add Personnel");
        addButton.setOnAction(e -> addPersonnel(nameField.getText(), roleField.getText()));

        personnelLayout.getChildren().addAll(new Label("Add Personnel"), nameField, roleField, addButton);
        personnelLayout.setAlignment(Pos.CENTER);
        personnelLayout.setPadding(new Insets(15));

        personnelStage.setScene(new Scene(personnelLayout, 300, 200));
        personnelStage.show();
    }

    private void addPersonnel(String name, String role) {
        // Logic to add personnel to the database
        try {
            String sql = "INSERT INTO personnel (name, role) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, role);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    private void trackVehicles() {
        // New window for tracking vehicles
        Stage vehiclesStage = new Stage();
        VBox vehiclesLayout = new VBox(10);

        ListView<String> vehiclesList = new ListView<>();
        loadVehicles(vehiclesList);

        vehiclesLayout.getChildren().addAll(new Label("Track Vehicles"), vehiclesList);
        vehiclesLayout.setAlignment(Pos.CENTER);
        vehiclesLayout.setPadding(new Insets(15));

        vehiclesStage.setScene(new Scene(vehiclesLayout, 400, 300));
        vehiclesStage.show();
    }

    private void loadVehicles(ListView<String> vehiclesList) {
        // Logic to load vehicle data from the database
        try {
            String sql = "SELECT * FROM vehicle"; // Adjust SQL query based on your schema
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String vehicleInfo = rs.getString("vehicle_id") + " - " + rs.getString("status");
                vehiclesList.getItems().add(vehicleInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    
    @Override
    public void start(Stage stage) {
        
    }
	
}