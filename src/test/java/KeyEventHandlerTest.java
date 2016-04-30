import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import static javafx.application.Application.launch;
import static org.junit.Assert.*;

public class KeyEventHandlerTest {

    @Test
    public void keyEventHandlerShouldBeAbleToSuccessfullySubstantiated() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
    }

    @Test
    public void letter_abc_ShouldBeDisplayedWhenKey_abc_WasTyped() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        KeyEvent keyEventA = new KeyEvent(KeyEvent.KEY_TYPED, "a", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventB = new KeyEvent(KeyEvent.KEY_TYPED, "b", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventC = new KeyEvent(KeyEvent.KEY_TYPED, "c", "", KeyCode.UNDEFINED, false, false, false, false);
        keyEventHandler.handle(keyEventA);
        assertTrue("a".equals(keyEventHandler.getText()));
        keyEventHandler.handle(keyEventB);
        assertTrue("ab".equals(keyEventHandler.getText()));
        keyEventHandler.handle(keyEventC);
        assertTrue("abc".equals(keyEventHandler.getText()));
    }

    @Test
    public void letter_ABC_ShouldBeDisplayedWhenKey_abc_WasTypedAndShiftWasPressed() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        KeyEvent keyEventA = new KeyEvent(KeyEvent.KEY_TYPED, "A", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventB = new KeyEvent(KeyEvent.KEY_TYPED, "B", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventC = new KeyEvent(KeyEvent.KEY_TYPED, "C", "", KeyCode.UNDEFINED, false, false, false, false);
        keyEventHandler.handle(keyEventA);
        assertTrue("A".equals(keyEventHandler.getText()));
        keyEventHandler.handle(keyEventB);
        assertTrue("AB".equals(keyEventHandler.getText()));
        keyEventHandler.handle(keyEventC);
        assertTrue("ABC".equals(keyEventHandler.getText()));
    }

    @Test
    public void deleteKeyAndBackspaceKeyShouldBeIgnoredAsCharacter() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        KeyEvent backspace = new KeyEvent(KeyEvent.KEY_TYPED, "\b", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent delete = new KeyEvent(KeyEvent.KEY_TYPED, "\u007F", "", KeyCode.UNDEFINED, false, false, false, false);
        keyEventHandler.handle(backspace);
        assertTrue("".equals(keyEventHandler.getText()));
        keyEventHandler.handle(delete);
        assertEquals("", keyEventHandler.getText());
    }

    @Test
    public void letter_A_ShouldBeDisplayedWhenKey_abc_WasTypedAndBackspaceWasPressedTwice() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        KeyEvent keyEventA = new KeyEvent(KeyEvent.KEY_TYPED, "A", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventB = new KeyEvent(KeyEvent.KEY_TYPED, "B", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventC = new KeyEvent(KeyEvent.KEY_TYPED, "C", "", KeyCode.UNDEFINED, false, false, false, false);
        keyEventHandler.handle(keyEventA);
        keyEventHandler.handle(keyEventB);
        keyEventHandler.handle(keyEventC);
        assertTrue("ABC".equals(keyEventHandler.getText()));
        KeyEvent backspace = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.BACK_SPACE, false, false, false, false);
        keyEventHandler.handle(backspace);
        keyEventHandler.handle(backspace);
        assertTrue("A".equals(keyEventHandler.getText()));
    }

    @Test
    public void newlineShouldBeInsertedWhenKey_ENTER_WasTyped() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        KeyEvent keyEventA = new KeyEvent(KeyEvent.KEY_TYPED, "A", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventB = new KeyEvent(KeyEvent.KEY_TYPED, "B", "", KeyCode.UNDEFINED, false, false, false, false);
        KeyEvent keyEventC = new KeyEvent(KeyEvent.KEY_TYPED, "C", "", KeyCode.UNDEFINED, false, false, false, false);
        keyEventHandler.handle(keyEventA);
        keyEventHandler.handle(keyEventB);
        keyEventHandler.handle(keyEventC);
        assertTrue("ABC".equals(keyEventHandler.getText()));
        KeyEvent enter = new KeyEvent(KeyEvent.KEY_TYPED, "\n", "", KeyCode.ENTER, false, false, false, false);
        keyEventHandler.handle(enter);
        assertTrue("ABC\n".equals(keyEventHandler.getText()));
    }

    @Test
    public void letter_ABC_ShouldBeDisplayedWhenClipboardWasPasted() {

        KeyEventHandler keyEventHandler = new KeyEventHandler();
        String content = "ABC";
        keyEventHandler.passClipboardContent(content);
        KeyEvent paste = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.V, false, true, false, false);
        keyEventHandler.handle(paste);
        assertTrue("ABC".equals(keyEventHandler.getText()));
    }

}
