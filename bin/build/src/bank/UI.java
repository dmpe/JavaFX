package bank;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class UI {

	@FXML private TextField InsertPinNumber;
	@FXML private TextField InsertAccNumber;
	@FXML private TextField InfoTetx;
	@FXML private TextField AmmountMoneyToWid;
	
	@FXML private Button PinAccept;
	@FXML private Button CardAccept;
	@FXML private Button StateOfAccount;
	@FXML private Button MoneyToWith;
	@FXML private Button CardOut;
	
	@FXML private RadioButton FreeMoney;
	@FXML private RadioButton ChoiceMoney;
	@FXML private ComboBox<Integer> ComboBox;
	
	
	public void chooseAmountOfMoney(ActionEvent d) {
		InfoTetx.setText("You must choose your amount to take out");
	}
	
	
}
