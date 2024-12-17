package Controller;

import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.AdminStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LibrarianStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LoginStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.SetStageData;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.UserStage;

/**
 * FXML Controller class
 *
 * @author nA dA
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginBtn;

    @FXML
    private TextField UsernameTf;

    @FXML
    private ImageView loginImageView;

    @FXML
    private Label UsernameLabelError;

    @FXML
    private Label PasswordLabelError;

    @FXML
    private TextField PasswordTf;

    @FXML
    void LoginUser(ActionEvent event) throws IOException {
        UsernameLabelError.setText("");
        PasswordLabelError.setText("");

        String username = UsernameTf.getText();
        String password = PasswordTf.getText();
        User user = validateUser(username, password);

        if (user == null) {
            if (username.isEmpty()) {
                UsernameLabelError.setText("User Name is Required!");
            } else if (password.isEmpty()) {
                PasswordLabelError.setText("Password is Required!");
            } else {
                UsernameLabelError.setText("Invalid Username or Password!");
            }
        } else {
            SceneBuilderLibraryManagementSystem.userLogin = user;
            if (user.getRole().equals("Admin")) {
                System.out.println("Welcome Admin..");
                /*الادمن بدها تحميل اسم وصورة المستخدم اللي سجل دخول اول م يشتغل البرنامج
                بالتالي هيظهر اكسبشن طالما المستخدم لسا معملش تسجيل دخول
                عشان هيك حطيناها بعد م المستخدم يسجل دخول*/
                Parent AdminStageRoot = FXMLLoader.load(getClass().getResource("/View/AdminDashboard.fxml"));
                Scene AdminScene = new Scene(AdminStageRoot);
                SetStageData(AdminStage, AdminScene, "logo.jpg", "Admin Dashboard", 50, 50);
                LoginStage.hide();
                AdminStage.show();

            } else if (user.getRole().equals("Librarian")) {
                System.out.println("Welcome Librarian..");
                Parent LibrarianStageRoot = FXMLLoader.load(getClass().getResource("/View/LibrarianDashboard.fxml"));
                Scene LibrarianScene = new Scene(LibrarianStageRoot);
                SetStageData(LibrarianStage, LibrarianScene, "logo.jpg", "Librarian Dashboard", 50, 50);
                LoginStage.hide();
                LibrarianStage.show();

            } else {
                System.out.println("Welcome User..");
                Parent UserStageRoot = FXMLLoader.load(getClass().getResource("/View/UserDashboard.fxml"));
                Scene UserScene = new Scene(UserStageRoot);
                SetStageData(UserStage, UserScene, "logo.jpg", "User Dashboard", 50, 50);
                LoginStage.hide();
                UserStage.show();

            }
        }
    }

    @FXML
    void ShowRegisterPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Register.fxml"));
        Scene registerScene = new Scene(root);
        SceneBuilderLibraryManagementSystem.SetStageData(SceneBuilderLibraryManagementSystem.RegisterStage, registerScene, "logo.jpg", "Register", 600, 250);
        SceneBuilderLibraryManagementSystem.RegisterStage.show();
        SceneBuilderLibraryManagementSystem.LoginStage.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize..");
        SceneBuilderLibraryManagementSystem.Users.add(new User("Nada Ali Al-nabahin", "admin", "admin123", "n@gmail.com", "0599584921", "Admin", "/images/main.jpg"));
        SceneBuilderLibraryManagementSystem.Users.add(new User("Hadeel Ali Al-nabahin", "lib", "lib123", "h@gmail.com", "0599568542", "Librarian", "/images/main.jpg"));
        SceneBuilderLibraryManagementSystem.Users.add(new User("Areej Ali Al-nabahin", "user", "user123", "a@gmail.com", "0599584988", "User", "/images/main.jpg"));

    }

    //   ------------------------Helper Functions------------------------------------
    //validate user
    public User validateUser(String username, String password) {
        for (User user : SceneBuilderLibraryManagementSystem.Users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

}
