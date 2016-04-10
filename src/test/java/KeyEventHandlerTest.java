import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertTrue;

public class KeyEventHandlerTest {


    @Test
    public void keyEventHandlerShouldBeAbleToSuccessfullySubstantiated() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
    }

    @Test
    public void letterAShouldBeDisplayedWhenKeyAWasTyped() {
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

}
