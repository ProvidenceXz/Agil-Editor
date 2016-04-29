import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/* Understands how to react upon different key press or key type */
public class KeyEventHandler implements EventHandler<KeyEvent> {

    private StringBuilder displayString;
    private Text displayText;

    private int startingPositionX;
    private int startingPositionY;


    private Rectangle textCursor;
    // Performs to update display text accordingly
    public KeyEventHandler(Text text, int startingX, int startingY, Rectangle cursor) {
        displayString = new StringBuilder();
        displayText = text;
        startingPositionX = startingX;
        startingPositionY = startingY;
        textCursor = cursor;
        updateText();
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
                updateText();
                keyEvent.consume();
            }
        } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            KeyCode code = keyEvent.getCode();
            // Delete the last character if length > 0
            if (code == KeyCode.BACK_SPACE) {
                displayString.setLength(Math.max(displayString.length() - 1, 0));
                updateText();
            } else if (code == KeyCode.ENTER) {
                updateText();
            }
        }
    }

    public String getText() {
        return displayString.toString();
    }

    // Understands how to update text and cursor
    private void updateText() {
        if (displayText == null) return; // If this handler is not for display Text, ignore

        // Update text content and position
        displayText.setText(getText());
        displayText.setX(startingPositionX);
        displayText.setY(startingPositionY);

        // Get the numbers of current text
        double fontSize = displayText.getFont().getSize();
        double textHeight = displayText.getLayoutBounds().getHeight();
        double textWidth = displayText.getLayoutBounds().getWidth();

        // Update text cursor position
        textCursor.setHeight(fontSize);
        textCursor.setWidth(2);

        textCursor.setX(startingPositionX + textWidth);
        textCursor.setY(textHeight);

        displayText.toFront();
    }

}
