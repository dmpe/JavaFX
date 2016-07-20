package uka;

import java.util.*;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class uka extends Application {

	@Override
	public void start(final Stage primary) {
		final Stage q = new Stage();
		final List<Account> i = new LinkedList<Account>();

		AnchorPane s = new AnchorPane();
		Scene p = new Scene(s);

		Button w = new Button("Display Account info");
		w.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				GridPane s = new GridPane();

				Account a = new Account(50, 650, 60);

				i.add(a);

				TextArea o = new TextArea();
				o.setLayoutX(20);
				o.setLayoutY(20);
				o.appendText(a.getAccountNumber() + " " + a.getBankDeposit()
						+ " " + a.getOverdraft());

				s.add(o, 1, 1);
				Scene d = new Scene(s);
				q.setHeight(200);
				q.setWidth(200);
				q.setScene(d);
				q.show();
			}
		});

		// add a trigger to hide the secondary stage when the primary stage is
		// hidden.
		// this will cause all stages to be hidden (which will cause the app to
		// terminate).

		q.setOnHidden(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent onClosing) {
				primary.hide();
			}
		});

		s.getChildren().add(w);
		primary.setScene(p);
		primary.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
