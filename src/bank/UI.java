package bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * prepsat bez pouziti fxml-ka, nevim jak na to 
 */
public class UI {
	/**
	 * Sample Skeleton for "UI.fxml" Controller Class You can copy and paste
	 * this code into your favorite IDE
	 **/

	// ResourceBundle that was given to the FXMLLoader
	ResourceBundle resources;

	// URL location of the FXML file that was given to the FXMLLoader
	URL location;

	// This method is called by the FXMLLoader when initialization is complete
	@SuppressWarnings("unused")
	void initialize() {

		// Initialize your logic here: all variables will have been
		// injected

		ObservableList<Integer> options = FXCollections.observableArrayList(10,
				100, 200, 500, 800, 1000);

		TextField InsertPinNumber;

		TextField InsertAccNumber;

		TextField InfoTetx;

		TextField AmmountMoneyToWid;

		Button PinAccept;

		Button CardAccept;

		Button StateOfAccount;

		Button MoneyToWith;

		Button CardOut;

		RadioButton FreeMoney;

		RadioButton ChoiceMoney;

		ComboBox<Integer> ComboBox = new ComboBox<Integer>(options);
	}

}
