package Controller;

import Model.Book;
import Model.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.AddCategoryStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.AdminStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Books;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Categories;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.LoginStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.SetStageData;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.UserProfileStage;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.Users;
import static scenebuilderlibrarymanagementsystem.SceneBuilderLibraryManagementSystem.userLogin;

/**
 * FXML Controller class
 *
 * @author nAdA
 */
public class AdminDashboardController implements Initializable {

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
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private ComboBox<String> userRoleFilter;
    @FXML
    private TableView<User> tableViewUser;
    @FXML
    private TableColumn<User, String> fullNameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, ImageView> imageviewColumn;
    @FXML
    private ImageView formBookmage;
    @FXML
    private Label formBookPicLabelError1;
    @FXML
    public static ComboBox<String> formCategoryComboBox;
    @FXML
    private Label categoryLabelError;
    @FXML
    private ComboBox<String> formLangComboBox;
    @FXML
    private Label languageLabelError;
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
    private TextField pageCountTf;
    @FXML
    private Label pageCountLabelError;
    @FXML
    private TextField copyCountTf;
    @FXML
    private Label copyCountLabelError;
    @FXML
    private TextField publisherTf;
    @FXML
    private Label publisherLabelError;
    @FXML
    private Button addBtn1;
    @FXML
    private Button deleteBtn1;
    @FXML
    private Button updateBtn1;
    @FXML
    private Button cancelBtn1;
    @FXML
    public static ComboBox<String> BookFilter;
    @FXML
    private TableView<Book> tableViewBook;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> categoryColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> copyCountColumn;
    @FXML
    private TableColumn<Book, String> pageCountColumn;
    @FXML
    private TableColumn<Book, ImageView> bookImageviewColumn;
    @FXML
    private TableColumn<Book, String> languageColumn;

    ///----User---------
    public String imageName = null;
    Image[] profileImage = {null};
    ///----Book---------
    public String bookImageName = null;
    Image[] bookImage = {null};
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sidebarHome.getStyleClass().add("sidebar-label");
        sidebarUserMangement.getStyleClass().add("sidebar-label");
        sidebarBookManagement.getStyleClass().add("sidebar-label");
        SetSelectedSidebar(sidebarHome, HomeAnchorPane);
//      ----------------------Set User Login Data----------------------
//        userProfileImageview.setImage(new Image(userLogin.getProfileImagePath()));
//        userFullname.setText(userLogin.getFullName());
        userProfileImageview.imageProperty().bind(
                Bindings.createObjectBinding(
                        () -> new Image(userLogin.getProfileImagePath()), userLogin.ProfileImagePathProperty())
        );
        userFullname.textProperty().bind(
                userLogin.fullNameProperty());

//      -----------------------Set Role Data ---------------------------
        roleComboBox.getItems().addAll("User", "Librarian", "Admin");
        roleComboBox.setValue("User");
        userRoleFilter.getItems().addAll("All", "User", "Librarian", "Admin");
        userRoleFilter.setValue("All");
//      -----------------------Set Book ComboBox------------------

        formLangComboBox.getItems().addAll("AR", "EN");
        Categories.add("Story");
        Categories.add("Languages");
        formCategoryComboBox.getItems().addAll(SceneBuilderLibraryManagementSystem.Categories);
        BookFilter.getItems().add("All");
        BookFilter.getItems().addAll(SceneBuilderLibraryManagementSystem.Categories);
//      -----------------------Set Cell Data for User table-------------
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        copyCountColumn.setCellValueFactory(new PropertyValueFactory<>("copyCount"));
        pageCountColumn.setCellValueFactory(new PropertyValueFactory<>("pageCount"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));

        bookImageviewColumn.setCellValueFactory(cellData -> {
            ImageView bookImage = new ImageView(cellData.getValue().getBookImagePath());
            bookImage.setFitWidth(40);
            bookImage.setFitHeight(40);

            return new SimpleObjectProperty<>(bookImage);
        });
        tableViewBook.setItems(Books);

        //      -----------------------Set Cell Data for Book table-------------
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        imageviewColumn.setCellValueFactory(cellData -> {
            ImageView userImage = new ImageView(cellData.getValue().getProfileImagePath());
            userImage.setFitWidth(40);
            userImage.setFitHeight(40);

            return new SimpleObjectProperty<>(userImage);
        });
        tableViewUser.setItems(Users);
        

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
        AdminStage.close();
        LoginStage.show();
    }

    @FXML
    private void UploadUserProfileImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(AdminStage);
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
    }

    @FXML
    private void AddUser(ActionEvent event) {

        addBtn.setOnAction(e -> {

            fullNameLabelError.setText("");
            userNameLabelError.setText("");
            passwordLabelError.setText("");
            emailLabelError.setText("");
            phoneLabelError.setText("");
            emailLabelError.setText("");
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
            if (this.roleComboBox.getValue().isEmpty()) {
                roleLabelError.setText("Role is Required");
            }

            if (!hasError) {

                boolean isFoundUser = UserExists(userNameTf.getText(), passwordTf.getText());
                if (!isFoundUser) {
                    User newUser = new User(fullNameTf.getText(), userNameTf.getText(), passwordTf.getText(), emailTf.getText(), phoneTf.getText(), roleComboBox.getValue(), this.imageName.toString());
                    Users.add(newUser);

//                    ------Alert---------
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "User has been Registered");

                    alert.showAndWait();

//                 ---------clear input-------
                    fullNameTf.clear();
                    userNameTf.clear();
                    passwordTf.clear();
                    emailTf.clear();
                    phoneTf.clear();
                    UserProfileImage.setImage(new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream("/images/userIcon.png")));
                    roleComboBox.setValue("");

                } else {
                    userNameLabelError.setText("User is already exist with username & password");

                }
            }

        });
    }

    @FXML
    private void DeleteUser(ActionEvent event) {
        User SelectedUser = tableViewUser.getSelectionModel().getSelectedItem();

        if (SelectedUser != null) {
            for (User user : Users) {
                if (user.getUserName().equals(SelectedUser.getUserName())) {
                    Users.remove(user);
                    tableViewUser.refresh();
                    fullNameTf.clear();
                    userNameTf.clear();
                    passwordTf.clear();
                    emailTf.clear();
                    phoneTf.clear();
                    this.imageName = null;
                    UserProfileImage.setImage(new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream("/images/userIcon.png")));
                    if (SelectedUser.equals(userLogin)) {
                        AdminStage.close();
                        LoginStage.show();
                    }
                    break;

                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select user to Delete!");
            alert.showAndWait();
        }
    }

    @FXML
    private void UpdateUser(ActionEvent event) {

        User SelectedUser = tableViewUser.getSelectionModel().getSelectedItem();
        boolean SameUserAuth = false;
        int userIndex = 0;

        if (SelectedUser != null) {

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

            // تحقق من الصورة: إذا لم يتم تحديثها، استخدم الصورة الحالية
            if (this.imageName == null && (SelectedUser.getProfileImagePath() == null || SelectedUser.getProfileImagePath().isEmpty())) {
                profilePictureLabelError.setText("Profile picture is Required");
                hasError = true;
            }

            if (this.roleComboBox.getValue() == null || this.roleComboBox.getValue().isEmpty()) {
                roleLabelError.setText("Role is Required");
                hasError = true;
            }

            if (!hasError) {
                for (User user : Users) {
                    if (user.getUserName().equals(SelectedUser.getUserName())) {
                        SelectedUser = user;
                        userIndex = Users.indexOf(user);
                        if (Users.indexOf(user) == Users.indexOf(userLogin)) {
                            SameUserAuth = true;
                        }
                        break;
                    }
                }

                boolean SameUserFound = false;
                for (User user : Users) {
                    if (SelectedUser.getUserName().equals(userNameTf.getText())) {
                        continue;
                    }
                    if (user.getUserName().equals(userNameTf.getText())) {
                        SameUserFound = true;
                        break;
                    }
                }

                if (!SameUserFound) {
                    SelectedUser.setFullName(fullNameTf.getText());
                    SelectedUser.setUserName(userNameTf.getText());
                    SelectedUser.setPassword(passwordTf.getText());
                    SelectedUser.setEmail(emailTf.getText());
                    SelectedUser.setPhone(phoneTf.getText());
                    SelectedUser.setRole(roleComboBox.getValue());

                    // تحديث الصورة فقط إذا تم اختيار صورة جديدة
                    if (this.imageName != null) {
                        SelectedUser.setProfileImagePath(profileImage[0].toString().replace("file:", ""));
                    }

                    Users.set(userIndex, SelectedUser);
                    RefreshUserProfileSidebarData(SameUserAuth);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Updated!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The UserName is used by other user!");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select user to update!");
            alert.showAndWait();
        }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        fullNameTf.clear();
        userNameTf.clear();
        passwordTf.clear();
        emailTf.clear();
        phoneTf.clear();
        roleComboBox.setValue("");
        this.imageName = null;
        UserProfileImage.setImage(new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream("/images/userIcon.png")));

        fullNameLabelError.setText("");
        userNameLabelError.setText("");
        passwordLabelError.setText("");
        emailLabelError.setText("");
        phoneLabelError.setText("");
        emailLabelError.setText("");
        roleLabelError.setText("");
    }

    @FXML
    private void SetSelectedUserToForm(MouseEvent event) {
        //        -----------TableView Listener---------
        tableViewUser.getSelectionModel().selectedItemProperty().addListener(e -> {
            User user = tableViewUser.getSelectionModel().getSelectedItem();

            if (user != null) {
                fullNameTf.setText(user.getFullName());
                userNameTf.setText(user.getUserName());
                passwordTf.setText(user.getPassword());
                emailTf.setText(user.getEmail());
                phoneTf.setText(user.getPhone());
                roleComboBox.setValue(user.getRole());
                profileImage[0] = new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream(user.getProfileImagePath()));
                UserProfileImage.setImage(profileImage[0]);

            }
        });

    }

    @FXML
    private void FilterTableData(MouseEvent event) {

        String selectedRole = userRoleFilter.getValue();
        ObservableList<User> FilterUsers = FXCollections.observableArrayList();
        FilterUsers.clear();
        for (User user : Users) {
            if (selectedRole.equals("All")) {
                FilterUsers.add(user);
                continue;
            }
            if (user.getRole().equals(selectedRole)) {
                FilterUsers.add(user);
            }
            tableViewUser.refresh();

        }
        tableViewUser.setItems(FilterUsers);
        tableViewUser.refresh();
    }

    @FXML
    private void ShowUserProfile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene userProfileScene = new Scene(root);
        SetStageData(UserProfileStage, userProfileScene, "logo.jpg", "UserProfile", 600, 150);
        AdminStage.close();
        UserProfileStage.show();
    }

    @FXML
    private void UploadformBookImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(AdminStage);
        if (file != null) {
            bookImage[0] = new Image(file.toURI().toString());
            formBookmage.setImage(bookImage[0]);
            this.bookImageName = "/images/" + file.getName();
            try {
                saveImage(bookImage[0], file.getName());
            } catch (IOException ex) {
                Logger.getLogger(SceneBuilderLibraryManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void AddBook(ActionEvent event) {
        formBookPicLabelError1.setText("");
        categoryLabelError.setText("");
        languageLabelError.setText("");
        titleLabelError.setText("");
        authorLabelError.setText("");
        isbnLabelError.setText("");
        publishDateLabelError.setText("");
        pageCountLabelError.setText("");
        copyCountLabelError.setText("");
        publisherLabelError.setText("");

        boolean hasError = false;
        if (bookImageName == null) {
            formBookPicLabelError1.setText("Image is Required");
            hasError = true;
        }
        if (formCategoryComboBox.getValue() == null || formCategoryComboBox.getValue().isEmpty()) {
            categoryLabelError.setText("Category is Required");
            hasError = true;
        }
        if (formLangComboBox.getValue() == null || formLangComboBox.getValue().isEmpty()) {
            languageLabelError.setText("Language is Required");
            hasError = true;
        }
        if (titleTf.getText().isEmpty()) {
            titleLabelError.setText("Title is Required");
            hasError = true;
        }
        if (authorTf.getText().isEmpty()) {
            authorLabelError.setText("Author is Required");
            hasError = true;
        }
        if (isbnTf.getText().isEmpty()) {
            isbnLabelError.setText("Isbn is Required");
            hasError = true;
        }
        if (publishDateTf.getText().isEmpty()) {
            publishDateLabelError.setText("Isbn is Required");
            hasError = true;
        }
        if (pageCountTf.getText().isEmpty()) {
            pageCountLabelError.setText("Page count is Required");
            hasError = true;
        }
        if (copyCountTf.getText().isEmpty()) {
            copyCountLabelError.setText("Copy count is Required");
            hasError = true;
        }
        if (publisherTf.getText().isEmpty()) {
            publisherLabelError.setText("Publisher is Required");
            hasError = true;
        }

        if (!hasError) {

            boolean isFoundBook = BookExists(isbnTf.getText());
            if (!isFoundBook) {
                Book newBook = new Book(this.bookImageName, formCategoryComboBox.getValue(), formLangComboBox.getValue(), titleTf.getText(), authorTf.getText(), isbnTf.getText(), publishDateTf.getText(), pageCountTf.getText(),
                        copyCountTf.getText(),
                        publisherTf.getText());
                Books.add(newBook);

//                    ------Alert---------
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User has been Registered");

                alert.showAndWait();

//                 ---------clear input-------
                titleTf.clear();
                authorTf.clear();
                isbnTf.clear();
                copyCountTf.clear();
                pageCountTf.clear();
                publisherTf.clear();
                publishDateTf.clear();
                formCategoryComboBox.setValue(null);
                formLangComboBox.setValue(null);
                this.bookImageName = null;
                formBookmage.setImage(new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream("/images/book.jpg")));

            } else {
                isbnLabelError.setText("Book is already exist with isbn");

            }
        }

    }

    @FXML
    private void DeleteBook(ActionEvent event) {

        Book SelectedBook = tableViewBook.getSelectionModel().getSelectedItem();

        if (SelectedBook != null) {
            for (Book book : Books) {
                if (book.getIsbn().equals(SelectedBook.getIsbn())) {
                    Books.remove(book);
                    //RefreshListviewBookData();
                    tableViewBook.refresh();
                    titleTf.clear();
                    authorTf.clear();
                    isbnTf.clear();
                    pageCountTf.clear();
                    copyCountTf.clear();
                    publisherTf.clear();
                    publishDateTf.clear();
                    formCategoryComboBox.setValue("");
                    formLangComboBox.setValue("");
                    this.bookImageName = null;
                    break;

                }
            }

        }
    }

    @FXML
    private void UpdateBook(ActionEvent event) {
        Book SelectedBook = tableViewBook.getSelectionModel().getSelectedItem();
        formBookPicLabelError1.setText("");
        categoryLabelError.setText("");
        languageLabelError.setText("");
        titleLabelError.setText("");
        authorLabelError.setText("");
        isbnLabelError.setText("");
        publishDateLabelError.setText("");
        pageCountLabelError.setText("");
        copyCountLabelError.setText("");
        publisherLabelError.setText("");

        boolean hasError = false;
        if (bookImageName == null) {
            formBookPicLabelError1.setText("Image is Required");
            hasError = true;
        }
        if (formCategoryComboBox.getValue() == null || formCategoryComboBox.getValue().isEmpty()) {
            categoryLabelError.setText("Category is Required");
            hasError = true;
        }
        if (formLangComboBox.getValue() == null || formLangComboBox.getValue().isEmpty()) {
            languageLabelError.setText("Language is Required");
            hasError = true;
        }
        if (titleTf.getText().isEmpty()) {
            titleLabelError.setText("Title is Required");
            hasError = true;
        }
        if (authorTf.getText().isEmpty()) {
            authorLabelError.setText("Author is Required");
            hasError = true;
        }
        if (isbnTf.getText().isEmpty()) {
            isbnLabelError.setText("Isbn is Required");
            hasError = true;
        }
        if (publishDateTf.getText().isEmpty()) {
            publishDateLabelError.setText("Isbn is Required");
            hasError = true;
        }
        if (pageCountTf.getText().isEmpty()) {
            pageCountLabelError.setText("Page count is Required");
            hasError = true;
        }
        if (copyCountTf.getText().isEmpty()) {
            copyCountLabelError.setText("Copy count is Required");
            hasError = true;
        }
        if (publisherTf.getText().isEmpty()) {
            publisherLabelError.setText("Publisher is Required");
            hasError = true;
        }

        if (!hasError) {

            if (SelectedBook != null) {
                for (Book book : Books) {
                    if (book.getIsbn().equals(SelectedBook.getIsbn())) {
                        SelectedBook = book;
                        break;
                    }
                }

                boolean SameBookFound = false;
                for (Book book : Books) {
                    if (SelectedBook.getIsbn().equals(isbnTf.getText())) {
                        continue;
                    }
                    if (book.getIsbn().equals(isbnTf.getText())) {
                        SameBookFound = true;
                        break;
                    }
                }

                if (SelectedBook != null) {

                    if (!SameBookFound) {
                        SelectedBook.setTitle(titleTf.getText());
                        SelectedBook.setAuthor(authorTf.getText());
                        SelectedBook.setIsbn(isbnTf.getText());
                        SelectedBook.setPublishDate(publishDateTf.getText());
                        SelectedBook.setPageCount(pageCountTf.getText());
                        SelectedBook.setCopyCount(copyCountTf.getText());
                        SelectedBook.setPublisher(publisherTf.getText());
                        SelectedBook.setLanguage(formLangComboBox.getValue());
                        SelectedBook.setCategory(formCategoryComboBox.getValue());

                        if (this.bookImageName != null) {
                            SelectedBook.setBookImagePath(bookImage[0].toString().replace("file:", ""));

                        }
                        //RefreshListviewBookData();
                        tableViewBook.refresh();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book Updated!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The Isbn is used by other user!");
                        alert.showAndWait();
                    }

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select book to update!");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void CancelBook(ActionEvent event) {
        titleTf.clear();
        authorTf.clear();
        isbnTf.clear();
        copyCountTf.clear();
        pageCountTf.clear();
        publisherTf.clear();
        publishDateTf.clear();
        formCategoryComboBox.setValue(null);
        formLangComboBox.setValue(null);
        this.bookImageName = null;
        formBookmage.setImage(new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream("/images/book.jpg")));

    }

    @FXML
    private void SetBookCategoryFilter(MouseEvent event) {
        String categoryName = BookFilter.getValue();
        ObservableList<Book> FilteredBooks = FXCollections.observableArrayList();
        FilteredBooks.clear();
        for (Book b : Books) {
            if (categoryName.equals("All")) {
                FilteredBooks.add(b);
                continue;
            }
            if (b.getCategory().equals(categoryName)) {
                FilteredBooks.add(b);
            }
        }
        tableViewBook.setItems(FilteredBooks);
    }

    @FXML
    private void ShowAddCategoryStage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCategory.fxml"));
//مستخدمتش set stage هنا لانو ظهرتلي مشاكل في الستيج
        Scene addCategoryScene = new Scene(root);
        System.out.println("FXML Loaded Successfully");

        Stage stage = new Stage();
        stage.setTitle("Add Category");
        stage.getIcons().add(new Image("/images/logo.jpg"));
        stage.setScene(addCategoryScene);
        stage.show();
        AdminStage.close();

    }

    @FXML
    private void SetSelectedBookToForm(MouseEvent event) {
        //        -----------TableView Listener---------
        tableViewBook.getSelectionModel().selectedItemProperty().addListener(e -> {
            Book book = tableViewBook.getSelectionModel().getSelectedItem();

            if (book != null) {
                bookImage[0] = new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream(book.getBookImagePath()));
                formBookmage.setImage(bookImage[0]);
                formCategoryComboBox.setValue(book.getCategory());
                formLangComboBox.setValue(book.getLanguage());
                titleTf.setText(book.getTitle());
                authorTf.setText(book.getAuthor());
                isbnTf.setText(book.getIsbn());
                publishDateTf.setText(book.getPublishDate());
                pageCountTf.setText(book.getPageCount());
                copyCountTf.setText(book.getCopyCount());
                publisherTf.setText(book.getPublisher());

            }
        });
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

    public boolean BookExists(String isbn) {
        boolean bookFound = false;
        for (Book book : Books) {
            if (book.getIsbn().equals(isbn)) {
                bookFound = true;
            }
        }
        return bookFound;
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

    public void RefreshUserProfileSidebarData(boolean sameUserAuth) {
        if (sameUserAuth) {
            UserProfileImage.setImage(new Image(SceneBuilderLibraryManagementSystem.class.getResourceAsStream(userLogin.getProfileImagePath())));
            UserProfileImage.setFitWidth(70);
            UserProfileImage.setFitHeight(70);
            userFullname.setText(userLogin.getFullName());

        }

    }

}
