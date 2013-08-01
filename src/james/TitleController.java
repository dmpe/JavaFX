package james;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
 
public class TitleController implements Initializable {
 
    @FXML
    private TextField nameField;
    @FXML
    private TextField valueField;
    @FXML
    private HBox title;
    @FXML
    private TitleController titleController;
    private Model model;
 
    public TitleController(Model model) {
        this.model = model;
        // titleController = new TitleController(model);
    }
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
        nameField.setText(model.nameProperty().get());
        model.nameProperty().bind(nameField.textProperty());
 
        valueField.setText(model.valueProperty().get());
        model.valueProperty().bind(valueField.textProperty());
 
        // titleController.initialize(location, resources);
 
    }
}