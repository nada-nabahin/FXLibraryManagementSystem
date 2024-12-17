package Controller;

import Model.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Books;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Categories;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LoginStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.SetStageData;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.UserProfileStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.UserStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.userLogin;

/**
 * FXML Controller class
 *
 * @author nAdA
 */
public class UserDashboardController implements Initializable {

    @FXML
    private Label sidebarHome;
    @FXML
    private Label sidebarUserMangement;
    @FXML
    private Label sidebarBookManagement;
    @FXML
    private AnchorPane HomeAnchorPane;
    @FXML
    private AnchorPane UserMangAnchorPane;
    @FXML
    private AnchorPane BookMangAnchorPane;
    @FXML
    private ImageView userProfileImageview;
    @FXML
    private Label userFullname;
    @FXML
    private TextField titleTf;
    @FXML
    private Label titleLabelError;
    @FXML
    private TextField authorTf;
    @FXML
    private Label authorLabelError;
    @FXML
    private TextField isbnTf;
    @FXML
    private Label isbnLabelError;
    @FXML
    private TextField publishDateTf;
    @FXML
    private Label publishDateLabelError;
    @FXML
    private TextField copyCountTf;
    @FXML
    private Label copyCountLabelError;
    @FXML
    private TextField pageCountTf;
    @FXML
    private Label pageCountLabelError;
    @FXML
    private TextField publisherTf;
    @FXML
    private Label publisherLabelError;
    @FXML
    private TextField categoryTf;
    @FXML
    private Label categoryLabelError;
    @FXML
    private ImageView bookImage;
    @FXML
    private Button borrowBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private ComboBox<String> searchByCategoryComboBox;
    @FXML
    private ComboBox<Book> searchByBookComboBox;
    @FXML
    private Button searchBtn;
    @FXML
    private Label bookLabelError;
    @FXML
    private Label categoryComboBoxLabelError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sidebarHome.getStyleClass().add("sidebar-label");
        sidebarUserMangement.getStyleClass().add("sidebar-label");
        sidebarBookManagement.getStyleClass().add("sidebar-label");
        SetSelectedSidebar(sidebarHome, HomeAnchorPane);
        //       --------------Set User Login Data----------------------
//        userProfileImageview.setImage(new Image(userLogin.getProfileImagePath()));
//        userFullname.setText(userLogin.getFullName());
        userProfileImageview.imageProperty().bind(
                Bindings.createObjectBinding(
                        () -> new Image(userLogin.getProfileImagePath()), userLogin.ProfileImagePathProperty())
        );
        userFullname.textProperty().bind(
                userLogin.fullNameProperty());
        
        
    searchByCategoryComboBox.setItems(Categories);


    }

    @FXML
    private void ShowSidebarHome(MouseEvent event) {
        SetSelectedSidebar(sidebarHome, HomeAnchorPane);
    }

    @FXML
    private void ShowSidebarUserManagement(MouseEvent event) {
        SetSelectedSidebar(sidebarUserMangement, UserMangAnchorPane);
    }

    @FXML
    private void ShowSidebarBookManagement(MouseEvent event) {
        SetSelectedSidebar(sidebarBookManagement, BookMangAnchorPane);
    }

    public void SetSelectedSidebar(Label selectedLabel, AnchorPane SelectedAnchorPane) {
        sidebarHome.getStyleClass().remove("Selected");
        sidebarUserMangement.getStyleClass().remove("Selected");
        sidebarBookManagement.getStyleClass().remove("Selected");
        selectedLabel.getStyleClass().add("Selected");

        HomeAnchorPane.setVisible(false);
        UserMangAnchorPane.setVisible(false);
        BookMangAnchorPane.setVisible(false);
        SelectedAnchorPane.setVisible(true);

    }

    @FXML
    private void Logout(MouseEvent event) {
        UserStage.close();
        LoginStage.show();
    }

    @FXML
    private void ShowUserProfile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene userProfileScene = new Scene(root);
        SetStageData(UserProfileStage, userProfileScene, "logo.jpg", "UserProfile", 600, 150);
        UserStage.close();
        UserProfileStage.show();
    }

    @FXML
    private void Borrow(MouseEvent event) {
    }

    @FXML
    private void Clear(MouseEvent event) {
    }

    private void SearchByCategory(String category) {
    ObservableList<Book> filteredBooks = FXCollections.observableArrayList();
    
    for (Book book : Books) {
        if (book.getCategory().equals(category)) {
            filteredBooks.add(book);
        }
    }

    searchByBookComboBox.setItems(filteredBooks);

    if (searchByBookComboBox.getItems().size() > 0) {
        searchByBookComboBox.getSelectionModel().selectFirst(); 
    }
    }

    @FXML
    private void SearchByBook(MouseEvent event) {
    }

    @FXML
    private void Search(MouseEvent event) {
    if (searchByCategoryComboBox.getValue() == null) {
        searchByCategoryComboBox.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        categoryComboBoxLabelError.setText("Select Category!");
    } else {
        searchByCategoryComboBox.setStyle("-fx-border-color: none;");
        categoryLabelError.setText(""); 
    }

    if (searchByBookComboBox.getValue() == null) {
        searchByBookComboBox.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        bookLabelError.setText("Select Book!");
    } else {
        searchByBookComboBox.setStyle("-fx-border-color: none;");
        bookLabelError.setText(""); 
    }

    if (searchByCategoryComboBox.getValue() != null && searchByBookComboBox.getValue() != null) {
        String selectedCategory = (String) searchByCategoryComboBox.getValue();
        SearchByCategory(selectedCategory);
    }
    }

    @FXML
    private void SearchByCategory(MouseEvent event) {
    }

}
