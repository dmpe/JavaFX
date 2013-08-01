package ex;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EventHandlerDemoController {
	
	@FXML
	private TextArea outputTextArea;
	@FXML
	private CheckBox checkBox;
	@FXML
	private ComboBox<Person> comboBox;
	@FXML
	private Slider slider;
	@FXML
	private TextField textField;
	@FXML
	private ListView<Person> listView;
	
	
	private ObservableList<Person> comboBoxData = FXCollections.observableArrayList();
	private ObservableList<Person> listViewData = FXCollections.observableArrayList();
	
	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public EventHandlerDemoController() {
		// Create some sample data for the ComboBox and ListView
		comboBoxData.add(new Person("Hans", "Muster"));
		comboBoxData.add(new Person("Ruth", "Mueller"));
		comboBoxData.add(new Person("Heinz", "Kurz"));
		comboBoxData.add(new Person("Cornelia", "Meier"));
		comboBoxData.add(new Person("Werner", "Meyer"));
		
		listViewData.add(new Person("Lydia", "Kunz"));
		listViewData.add(new Person("Anna", "Best"));
		listViewData.add(new Person("Stefan", "Meier"));
		listViewData.add(new Person("Martin", "Mueller"));
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Init ComboBox
		comboBox.setItems(comboBoxData);
		
		// Listen for Slider value changes
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				
				outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");
			}
		});
		
		// Listen for TextField text changes
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
				outputTextArea.appendText("TextField Text Changed (newValue: " + newValue + ")\n");
			}
		});
		
		// Init ListView and listen for selection changes
		listView.setItems(listViewData);
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
			@Override
			public void changed(ObservableValue<? extends Person> observable,
					Person oldValue, Person newValue) {
				
				outputTextArea.appendText("ListView Selection Changed (newValue: " + newValue + ")\n");
			}
		});
	}
	
	@FXML
	private void handleButtonAction() {
		outputTextArea.appendText("Button Action\n");
	}
	
	@FXML
	private void handleCheckBoxAction() {
		outputTextArea.appendText("CheckBox Action (selected: " + checkBox.isSelected() + ")\n");
	}
	
	@FXML
	private void handleComboBoxAction() {
		Person selectedPerson = comboBox.getSelectionModel().getSelectedItem();
		outputTextArea.appendText("ComboBox Action (selected: " + selectedPerson + ")\n");
	}
	
	@FXML
	private void handleHyperlinkAction() {
		outputTextArea.appendText("Hyperlink Action\n");
	}
	
	@FXML
	private void handleTextFieldAction() {
		outputTextArea.appendText("TextField Action\n");
	}
}