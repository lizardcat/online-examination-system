package xss.it;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import xss.it.backend.controller.LoginController; // Optional: if you want to preload controller

@SpringBootApplication
public class FxApplication extends Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        // Initialize Spring context
        context = new SpringApplicationBuilder(FxApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load login.fxml and wire with Spring controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xss/it/login.fxml"));
        loader.setControllerFactory(context::getBean); // Enables Spring injection in controllers
        Parent root = loader.load();

        // Set title and icon
        stage.setTitle("Login Page");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/xss/it/icon.png")));

        // Lock down the UI
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
    }
}
