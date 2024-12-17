package Controller;

import Model.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.AdminStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LibrarianStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LoginStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.RegisterStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.UserProfileStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.UserStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Users;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.userLogin;

/**
 * FXML Controller class
 *
 * @author nAdA
 */
public class UserProfileController implements Initializable {

    @FXML
    private ImageView UserProfileImage;
    @FXML
    private Label profilePictureLabelError;
    @FXML
    private TextField fullNameTf;
    @FXML
    private Label fullNameLabelError;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private Label roleLabelError;
    @FXML
    private TextField userNameTf;
    @FXML
    private Label userNameLabelError;
    @FXML
    private PasswordField passwordTf;
    @FXML
    private Label passwordLabelError;
    @FXML
    private TextField emailTf;
    @FXML
    private Label emailLabelError;
    @FXML
    private TextField phoneTf;
    @FXML
    private Label phoneLabelError;

    String imageName = null;
    private Image[] profileImage = {null};
    @FXML
    private AnchorPane AnchorPaneColor;

    void ShowLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        Scene loginScene = new Scene(root);
        SceneBuilderLibraryManagementSystem.SetStageData(SceneBuilderLibraryManagementSystem.LoginStage, loginScene, "logo.jpg", "Login", 600, 250);
        SceneBuilderLibraryManagementSystem.LoginStage.show();
        SceneBuilderLibraryManagementSystem.RegisterStage.hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleComboBox.getItems().addAll("User", "Librarian", "Admin");
        roleComboBox.setDisable(true);

        if (userLogin != null) {
            roleComboBox.setValue(userLogin.getRole());
            fullNameTf.setText(userLogin.getFullName());
            userNameTf.setText(userLogin.getUserName());
            passwordTf.setText(userLogin.getPassword());
            emailTf.setText(userLogin.getEmail());
            phoneTf.setText(userLogin.getPhone());
            roleComboBox.setValue(userLogin.getRole());
            profileImage[0] = new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream(userLogin.getProfileImagePath()));
            UserProfileImage.setImage(profileImage[0]);

        }
        
        if (userLogin.getRole().equals("Admin")) {
            AnchorPaneColor.getStyleClass().add("AnchorPaneColorAdmin");
        } else if (userLogin.getRole().equals("Librarian")) {
            AnchorPaneColor.getStyleClass().add("AnchorPaneColorLibrarian");
        } else {
            AnchorPaneColor.getStyleClass().add("AnchorPaneColorUser");
        }

    }

    @FXML
    private void UploadUserProfileImage(MouseEvent event) {
        //--------image selection---------
        Image[] profileImage = {null};
        UserProfileImage.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(RegisterStage);
            //till now the user has chosen the image & saved it in file object
            if (file != null) {
                profileImage[0] = new Image(file.toURI().toString());
                UserProfileImage.setImage(profileImage[0]);
                this.imageName = "/images/" + file.getName();
                try {
                    saveImage(profileImage[0], file.getName());
                } catch (IOException ex) {
                    Logger.getLogger(SceneBuilderLibraryManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void Update(ActionEvent event) {
        fullNameLabelError.setText("");
        userNameLabelError.setText("");
        passwordLabelError.setText("");
        emailLabelError.setText("");
        phoneLabelError.setText("");
        profilePictureLabelError.setText("");
        roleLabelError.setText("");

        boolean hasError = false;
        if (fullNameTf.getText().isEmpty()) {
            fullNameLabelError.setText("Full Name is Required");
            hasError = true;
        }
        if (userNameTf.getText().isEmpty()) {
            userNameLabelError.setText("User Name is Required");
            hasError = true;
        }
        if (passwordTf.getText().isEmpty()) {
            passwordLabelError.setText("Password is Required");
            hasError = true;
        }
        if (emailTf.getText().isEmpty()) {
            emailLabelError.setText("E-mail is Required");
            hasError = true;
        }
        if (phoneTf.getText().isEmpty()) {
            phoneLabelError.setText("Phone is Required");
            hasError = true;
        }

        if (!hasError) {

            boolean SameUserFound = false;
            for (User user : Users) {
                if (userLogin.getUserName().equals(userNameTf.getText())) {
                    continue;
                }
                if (user.getUserName().equals(userNameTf.getText())) {
                    SameUserFound = true;
                    break;
                }
            }
            if (userLogin != null) {

                if (!SameUserFound) {
                    userLogin.setFullName(fullNameTf.getText());
                    userLogin.setUserName(userNameTf.getText());
                    userLogin.setPassword(passwordTf.getText());
                    userLogin.setEmail(emailTf.getText());
                    userLogin.setPhone(phoneTf.getText());
                    userLogin.setRole(roleComboBox.getValue());
                    if (this.imageName != null) {
                        userLogin.setProfileImagePath(profileImage[0].toString().replace("file:", ""));

                    }
                    //tableViewUser.refresh();
                    Users.set(Users.indexOf(userLogin), userLogin);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Updated!");
                    alert.showAndWait();
                    UserProfileStage.close();
                    if (userLogin.getRole().equals("Admin")) {
                        AdminStage.show();
                    } else if (userLogin.getRole().equals("Librarian")) {
                        LibrarianStage.show();
                    } else {
                        UserStage.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The UserName is used by other user!");
                    alert.showAndWait();
                }

            }
        }
    }

    @FXML
    private void ShowDashboard(ActionEvent event) {
        UserProfileStage.close();
        AdminStage.show();
    }

    //  -----------------------Helper Functions------------------
    public boolean UserExists(String username, String password) {
        boolean userFound = false;
        for (User user : Users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                userFound = true;
            }
        }
        return userFound;
    }

    public void saveImage(Image img, String name) throws IOException {
        String projectPath = System.getProperty("user.dir");
        //the path which i'll save the image in (project path + src-> images)
        String imagesFolderPath = projectPath + "/src/images";

        //if images folder not exist , will create it 
        File imagesFolder = new File(imagesFolderPath);
        if (!imagesFolder.exists()) {
            imagesFolder.mkdir();
        }

        String fullFilePath = imagesFolderPath + "/" + name;
        File file = new File(fullFilePath);
        BufferedImage BI = SwingFXUtils.fromFXImage(img, null);
        ImageIO.write(BI, "jpg", file);
    }

}
