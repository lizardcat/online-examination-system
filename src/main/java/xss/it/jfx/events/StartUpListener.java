

package xss.it.jfx.events;

import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import xss.it.jfx.ui.FxView;

import java.io.IOException;


@Component
public class StartUpListener implements ApplicationListener<StartUpEvent> {
    /**
     * Handles the StartUpEvent by creating and displaying the login window.
     *
     * @param event The StartUpEvent instance.
     */
    @Override
    public void onApplicationEvent(@NonNull StartUpEvent event) {
        try {
            new FxView(event.getStage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
