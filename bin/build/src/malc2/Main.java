package malc2;

import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	/*
	 * Display text from a method, Whether a webpage is 404 - yes or not
	 * (non-Javadoc) taken from
	 * http://java-buddy.blogspot.de/2012/01/javafx-exercise
	 * -display-text-on-scene.html
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */

	@Override
	public void start(Stage primaryStage) throws MalformedURLException,
			IOException {

		// icon
		Image icon = new Image("resources/icon.png");
		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));

		TextArea textArea1;
		ListView list1;


		Methods cl = new Methods();
		String sit = "http://www.google.com/";
		// int ado = cl.getResponseCode(sit);
		String bobob = cl.getImp(cl.getResponseCode(sit)); // then ado

		Text t = new Text(10, 20, bobob);
		Group ab = new Group(root);

		Scene sc = new Scene(root, 600, 330);

		primaryStage.setScene(sc);
		primaryStage.setTitle("Maly Test");
		primaryStage.getIcons().add(icon);
		primaryStage.setResizable(true);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}
