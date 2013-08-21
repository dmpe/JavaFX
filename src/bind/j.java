package bind;

import javafx.beans.*;
import javafx.beans.binding.*;

public class j {
	public static void main(String[] args) {
		
	
	 bill bill1 = new bill();
	 bill bill2 = new bill();
	 bill bill3 = new bill();
	NumberBinding total =
	          Bindings.add(bill1.amountDueProperty().add(bill2.amountDueProperty()),
	              bill3.amountDueProperty());
	        total.addListener(new InvalidationListener() {
	 
	        @Override public void invalidated(Observable o) {
	                System.out.println("The binding is now invalid.");
	            }
	        });

	        // First call makes the binding invalid
	        bill1.setAmountDue(200.00);

	        // The binding is now invalid
	        bill2.setAmountDue(100.00);
	        bill3.setAmountDue(75.00);

	        // Make the binding valid...
	        System.out.println(total.getValue());

	        // Make invalid... 
	        bill3.setAmountDue(150.00);

	        // Make valid...
	        System.out.println(total.getValue());
	}
}
