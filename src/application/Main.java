package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/*the main class*/
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
		
		
			Process pane = new Process();
			Scene scene = new Scene(pane.pane());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		}catch (Exception e) {
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}