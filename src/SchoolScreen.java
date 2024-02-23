			//Program Name: Traffic Citation Project----School Screen
			//Author Name: Omer Syed
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

public class SchoolScreen extends Application implements SetScreen {
    private Connection connection;
    
     private static final String url = "jdbc:mysql://localhost:3306/traffic";
	 private static final String username = "root";
	 private static final String password = "rockstar4580";
    
    
public void show(Stage stage) {
		
		connectToDatabase();

        stage.setTitle("School Traffic System");
        BorderPane borderPane = new BorderPane();

        // Menu setup
        MenuButton menuButton = new MenuButton("Menu");
        MenuItem schoolProfile = new MenuItem("School Profile");
        MenuItem settings = new MenuItem("Settings");
        menuButton.getItems().addAll(schoolProfile, settings);

        VBox leftsideBox = new VBox(menuButton);
        leftsideBox.setAlignment(Pos.CENTER_LEFT);
        leftsideBox.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(leftsideBox);

        // Buttons for main functionalities
        Button reportViolationButton = new Button("Report Violation");
        Button viewReportsButton = new Button("View Reports");

        // Event Handlers
        reportViolationButton.setOnAction(e -> reportViolation());
        viewReportsButton.setOnAction(e -> viewReports());

        VBox centerBox = new VBox(reportViolationButton, viewReportsButton);
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

    private void reportViolation() {
        // Sample form for reporting a violation
        Stage formStage = new Stage();
        VBox formLayout = new VBox(10);

        Label label = new Label("Report a Traffic Violation");
        TextField violationField = new TextField();
        violationField.setPromptText("Describe the violation");

        Button submitButton = new Button("Submit Report");
        submitButton.setOnAction(e -> submitViolationReport(violationField.getText()));

        formLayout.getChildren().addAll(label, violationField, submitButton);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setPadding(new Insets(15));

        formStage.setScene(new Scene(formLayout, 300, 200));
        formStage.show();
    }

    private void submitViolationReport(String violationDescription) {
        try {
            // Assuming there is a 'violations' table with a 'description' column
            String sql = "INSERT INTO violations (description) VALUES (?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, violationDescription);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exceptions
        }
    }

    private void viewReports() {
        // Basic view for reports
        Stage reportStage = new Stage();
        VBox reportLayout = new VBox(10);

        Label label = new Label("Violation Reports");

        ListView<String> reportList = new ListView<>();
        try {
            String sql = "SELECT description FROM violations";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reportList.getItems().add(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exceptions
        }

        reportLayout.getChildren().addAll(label, reportList);
        reportLayout.setAlignment(Pos.CENTER);
        reportLayout.setPadding(new Insets(15));

        reportStage.setScene(new Scene(reportLayout, 400, 300));
        reportStage.show();
    }

    @Override
    public void start(Stage stage) {
        
    }
    
    

	
}