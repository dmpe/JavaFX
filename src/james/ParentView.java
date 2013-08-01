package james;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ParentView {

	protected VBox template;

	// public ParentView(ParentController controller) {

	public ParentView(final Model model) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
		// loader.setController(controller);
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> clazz) {
				if (clazz.equals(ParentController.class)) {
					return new ParentController(model);
				} else if (clazz.equals(TitleController.class)) {
					return new TitleController(model);
				} else
					return null;
			}
		});
		try {
			loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		template = loader.getRoot();
	}

	public VBox getTemplate() {
		return template;
	}
}