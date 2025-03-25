

package xss.it.jfx.events;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;


public class StartUpEvent extends ApplicationEvent {

    public StartUpEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage(){
        return (Stage) getSource();
    }
}
