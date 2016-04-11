import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/* Understands how to react upon different key press or key type */
public class KeyEventHandler implements EventHandler<KeyEvent> {

    private StringBuilder displayString;

    public KeyEventHandler() {
        displayString = new StringBuilder();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
            String charTyped = keyEvent.getCharacter();
            // Ignore backspace and delete key
            if (charTyped.charAt(0) != '\b' && charTyped.charAt(0) != '\u007F') {
                System.out.println(charTyped.charAt(0));
                displayString.append(charTyped);
            }

        }
    }

    public String getText() {
        return displayString.toString();
    }

}
