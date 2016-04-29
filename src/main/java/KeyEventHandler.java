import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/* Understands how to react upon different key press or key type */
public class KeyEventHandler implements EventHandler<KeyEvent> {

    private StringBuilder displayString;
    private Text displayText;
    private int startingPositionX;
    private int startingPositionY;

    // Performs to update display text accordingly
    public KeyEventHandler(Text text, int startingX, int startingY) {
        displayString = new StringBuilder();
        displayText = text;
        startingPositionX = startingX;
        startingPositionY = startingY;
    }

    // Performs only as a key handler
    public KeyEventHandler() {
        displayString = new StringBuilder();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
            String charTyped = keyEvent.getCharacter();
            // Ignore backspace and delete key
            if (charTyped.charAt(0) != '\b' && charTyped.charAt(0) != '\u007F') {
                displayString.append(charTyped);
                keyEvent.consume();
                if (displayText != null) {
                    displayText.setText(getText());
                    updateText();
                }
            }
        } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            KeyCode code = keyEvent.getCode();
            // Delete the last character if length > 0
            if (code == KeyCode.BACK_SPACE) {
                displayString.setLength(Math.max(displayString.length() - 1, 0));
            }
        }
    }

    public String getText() {
        return displayString.toString();
    }

    private void updateText() {
        displayText.setX(startingPositionX);
        displayText.setY(startingPositionY);

        displayText.toFront();
    }

}
