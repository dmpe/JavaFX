package bank;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/*
 * prepsat bez pouziti fxml-ka, nevim jak na to 
 */
public class Controller {

	@FXML
	TextField InsertPinNumber;
	@FXML
	TextField InsertAccNumber;
	@FXML
	TextField InfoTetx;
	@FXML
	TextField AmmountMoneyToWid;
	@FXML
	Button PinAccept;
	@FXML
	Button CardAccept;
	@FXML
	Button StateOfAccount;
	@FXML
	Button MoneyToWith;
	@FXML
	Button CardOut;
	@FXML
	RadioButton FreeMoney;
	@FXML
	RadioButton ChoiceMoney;
	@FXML
	ComboBox<Integer> ComboBox = new ComboBox<Integer>(
			FXCollections.observableArrayList(10, 20, 30));

	@FXML
	ResourceBundle resources;
	@FXML
	URL location;

	@FXML
	public void initialize() {
//		ComboBox.getItems().clear();
//		ComboBox.getItems().addAll(10, 20, 30);

	}

}
