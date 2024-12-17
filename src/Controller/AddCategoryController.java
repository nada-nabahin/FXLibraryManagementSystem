package Controller;

import static Controller.AdminDashboardController.BookFilter;
import static Controller.AdminDashboardController.formCategoryComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.AddCategoryStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.AdminStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Categories;

/**
 * FXML Controller class
 *
 * @author nAdA
 */
public class AddCategoryController implements Initializable {

    @FXML
    private TextField categoryTf;
    @FXML
    private Button addCategoryBtn;
    @FXML
    private Button cancelCategoryBtn;
    @FXML
    private Label addCategoryLabelError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddCategory(MouseEvent event) {
        String category = categoryTf.getText().trim();

        if (category.isEmpty()) {
            addCategoryLabelError.setText("Category cannot be empty!");
            return;
        }

        if (Categories.contains(category)) {
            addCategoryLabelError.setText("Category already exists!");
            return;
        } else {

            Categories.add(category);
//  String newCategory = categoryTf.getText().trim();
//                if (!newCategory.isEmpty() && !BookFilter.getItems().contains(newCategory)) {
//                    BookFilter.getItems().add(newCategory);
//                    formCategoryComboBox.getItems().add(newCategory);
//                }
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Category Added Successfully");
//            alert.showAndWait();
//
//            categoryTf.clear();

            AddCategoryStage.close();
            AdminStage.show();

        }
    }

    @FXML
    private void CancelCategory(MouseEvent event) {
        AddCategoryStage.close();
        AdminStage.show();

    }

}
