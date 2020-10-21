package screenPack;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenPackClass {
	public static void showLoginScreen() throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("/view/LoginPageFXML.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setTitle("TnP Portal");
		stage.setScene(scene);
		stage.show();
	}
}
