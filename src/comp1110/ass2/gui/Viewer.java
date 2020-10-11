package comp1110.ass2.gui;

import comp1110.ass2.FitGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * A very simple viewer for piece placements in the IQ-Fit game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 720;
    private static final int VIEWER_HEIGHT = 480;

    private static final String URI_BASE = "/assets/";
    private static final String BG_URI = Viewer.class.getResource(URI_BASE + "board.png").toString();

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;
    private final Group bg = new Group();

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        if (!FitGame.isPlacementWellFormed(placement) || !FitGame.isPlacementValid(placement)) {
            System.out.println("BAD INPUT! PLEASE TYPE AGAIN");
            return;
        }
        if (placement.length() == 0) {
            return;
        }
        int pic = placement.length() / 4;
        for (int i = 0; i < pic; i++) {
            char choosepic = placement.charAt(4 * i);
            String S = "";
            if (Character.isUpperCase(choosepic)) {
                S = choosepic + "2";
            } else {
                S = Character.toUpperCase(choosepic) + "1";
            }
            System.out.println(placement);
            double xGive = Double.parseDouble(placement.charAt(1 + 4 * i) + "") * 105;
            double yGive = Double.parseDouble(placement.charAt(2 + 4 * i) + "") * 115;
            char orientation = placement.charAt(3 + 4 * i);
            ImageView iv = new ImageView(new Image(Viewer.class.getResource("assets/" + S + ".png").toString()));

            if (orientation == 'E') {
                iv.setRotate(90);
            } else if (orientation == 'S') {
                iv.setRotate(180);
            } else if (orientation == 'W') {
                iv.setRotate(270);
            }
            iv.setX(xGive);
            iv.setY(yGive);
            root.getChildren().add(iv);
        }
        // FIXME Task 4: implement the simple placement viewer
    }

    /**
     * this part I use the background made by myself, if the further tasks ask to set
     * the board.png as background, change the "background.png"to "board.png""
     * Jeff
     */
    private void makebg() {
        bg.getChildren().clear();

        ImageView background = new ImageView();
        background.setImage(new Image(BG_URI));
        background.setFitWidth(VIEWER_WIDTH);
        background.setFitHeight(VIEWER_HEIGHT);

        bg.getChildren().add(background);

        bg.toBack();

    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FitGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(bg);

        makeControls();
        makebg();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
