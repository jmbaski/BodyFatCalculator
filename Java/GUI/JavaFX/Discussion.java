/**
 * @author Joseph Baskin
 * @version 2.0 */
/* Date : 4/2/2025
 * CMSC335 : Discussion - Week 4
 * Discussion class
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Discussion extends Application {
    private static Scene scene;

    public void start(Stage primaryStage) {
        DiscussionPane discussionPane = new DiscussionPane();
        scene = new Scene(discussionPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Body Fat Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
