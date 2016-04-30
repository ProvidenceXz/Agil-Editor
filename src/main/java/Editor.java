import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/* Understands how to display text through GUI in a reasonable manner. */
public class Editor extends Application {

    private static final int windowWidth = 500;
    private static final int windowHeight = 500;

    private static final int startPositionX = 20;
    private static final int startPositionY = 20;

    private static final int fontSize = 20;
    private static final String fontName = "Consolas";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, windowWidth, windowHeight, Color.WHITE);

        // Initialize display text environment
        Text text = new Text(windowWidth, windowHeight, "");
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(fontName, fontSize));
        Rectangle textCursor = new Rectangle(0, 0);
        KeyEventHandler keyEventHandler = new KeyEventHandler(text, startPositionX, startPositionY, textCursor);

        // Add associated nodes to root
        root.getChildren().add(text);
        root.getChildren().add(textCursor);

        // Bind EventHandler onto scene
        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnKeyTyped(keyEventHandler);

        primaryStage.setTitle("AgilEditor");

        // Trigger the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
