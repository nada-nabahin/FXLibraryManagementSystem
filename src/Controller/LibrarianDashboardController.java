package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.AdminStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LibrarianStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LoginStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.SetStageData;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.UserProfileStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.userLogin;

/**
 * FXML Controller class
 *
 * @author nAdA
 */
public class LibrarianDashboardController implements Initializable {

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
        LibrarianStage.close();
        LoginStage.show();
    }

    @FXML
    private void ShowUserProfile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene userProfileScene = new Scene(root);
        SetStageData(UserProfileStage, userProfileScene, "logo.jpg", "UserProfile", 600, 150);
        LibrarianStage.close();
        UserProfileStage.show();
    }

}
