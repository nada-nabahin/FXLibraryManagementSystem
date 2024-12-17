
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
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LoginStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.RegisterStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Users;

/**
 * FXML Controller class
 *
 * @author nAdA
 */
public class RegisterController implements Initializable {

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

    @FXML
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
        roleComboBox.setValue("User");
        roleComboBox.setDisable(true);
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
    private void Register(ActionEvent event) {

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
        if (this.imageName == null) {
            profilePictureLabelError.setText("Profile picture is Required");
            hasError = true;
        }
        if (!hasError) {

            boolean isFoundUser = UserExists(userNameTf.getText(), passwordTf.getText());
            if (!isFoundUser) {
                User newUser = new User(fullNameTf.getText(), userNameTf.getText(), passwordTf.getText(), emailTf.getText(), phoneTf.getText(), roleComboBox.getValue(), this.imageName.toString());
                Users.add(newUser);

//                    ------Alert---------
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User has been Registered");
                alert.showAndWait();

//                    ---------clear input-------
                fullNameTf.clear();
                userNameTf.clear();
                passwordTf.clear();
                emailTf.clear();
                phoneTf.clear();
                this.imageName = null;
                SceneBuilderLibraryManagementSystem.LoginStage.show();
                SceneBuilderLibraryManagementSystem.RegisterStage.hide();

            } else {
                userNameLabelError.setText("User is already exist with username & password");

            }
        }

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
