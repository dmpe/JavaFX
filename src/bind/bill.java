package bind;

import javafx.beans.property.*;

public class bill {
	// Define a variable to store the property
	private DoubleProperty amountDue = new SimpleDoubleProperty();

	// Define a getter for the property's value
	public final double getAmountDue() {
		return amountDue.get();
	}

	// Define a setter for the property's value
	public final void setAmountDue(double value) {
		amountDue.set(value);
	}

	// Define a getter for the property itself
	public DoubleProperty amountDueProperty() {
		return amountDue;
	}
}
