package scenebuilderlibrarymanagementsystem;

import Model.Book;
import Model.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author nAdA
 */
public class SceneBuilderLibraryManagementSystem extends Application {

    public static Stage LoginStage = new Stage();
    public static Stage RegisterStage = new Stage();
    public static Stage AdminStage = new Stage();
    public static Stage UserStage = new Stage();
    public static Stage LibrarianStage = new Stage();
    public static Stage UserProfileStage = new Stage();
    public static Stage AddCategoryStage = new Stage();

    public static User userLogin = null;

    public static ObservableList<User> Users = FXCollections.observableArrayList();
    public static ObservableList<Book> Books = FXCollections.observableArrayList();
    public static ObservableList<String> Categories = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        Scene loginScene = new Scene(root);
        SetStageData(LoginStage, loginScene, "logo.jpg", "Login", 600, 250);
        LoginStage.show();

    }

    public static void SetStageImageIcon(Stage s, String imageName) {
        s.getIcons().add(new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream("/images/" + imageName)));
    }

    public static void SetStageData(Stage s, Scene sc, String logo, String title, int sx, int sy) {
        s.setScene(sc);
        s.setTitle(title);
        s.setX(sx);
        s.setY(sy);
        SetStageImageIcon(s, logo);
        s.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
