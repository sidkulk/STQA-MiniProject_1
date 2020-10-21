package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import screenPack.ScreenPackClass;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		ScreenPackClass.showLoginScreen();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
