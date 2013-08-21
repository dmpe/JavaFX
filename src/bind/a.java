package bind;

import javafx.beans.value.*;

public class a {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bill electricBill = new bill();

		electricBill.amountDueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue o, Object oldVal, Object newVal) {
				System.out.println("Electric bill has changed!");
			}
		});

		electricBill.setAmountDue(100.00);
	}

}
