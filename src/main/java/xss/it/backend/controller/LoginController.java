package xss.it.backend.controller;

import jakarta.annotation.Resource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import xss.it.backend.entity.User;
import xss.it.backend.service.UserService;

import java.util.Optional;

@Component
public class LoginController {

    @Resource
    private UserService userService;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label labelLabel;

    @FXML
    public void onLoginClicked() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            labelLabel.setText("Username and password are required.");
            labelLabel.setVisible(true);
            return;
        }

        boolean valid = userService.validateUser(username, password);

        if (valid) {
            labelLabel.setText("");

            Optional<User> userOpt = userService.findByUsername(username);
            if (userOpt.isPresent()) {
                User user = userOpt.get();

                switch (user.getRole()) {
                    case "student":
                        loadFXML("/xss/it/student_dashboard.fxml");
                        break;
                    case "teacher":
                        loadFXML("/xss/it/teacher_panel.fxml");
                        break;
                    case "admin":
                        loadFXML("/xss/it/admin_panel.fxml");
                        break;
                    default:
                        labelLabel.setText("Unknown role: " + user.getRole());
                        labelLabel.setVisible(true);
                        break;
                }
            } else {
                labelLabel.setText("Something went wrong. User not found.");
            }
        } else {
            labelLabel.setText("Invalid username or password.");
            labelLabel.setVisible(true);
        }
    }

    @FXML
    public void onRegisterClicked() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            labelLabel.setText("Username and password are required.");
            labelLabel.setVisible(true);
            return;
        }

        boolean created = userService.registerUser(username, password);

        if (created) {
            labelLabel.setText("Registration successful! You can now log in.");
        } else {
            labelLabel.setText("Username already exists.");
        }
        labelLabel.setVisible(true);
    }

    private void loadFXML(String resourcePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            loader.setControllerFactory(c -> userService); // Optional: for dependency injection if needed
            Parent root = loader.load();
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            labelLabel.setText("Failed to load view.");
        }
    }
}
