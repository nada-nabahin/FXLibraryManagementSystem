package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private SimpleStringProperty fullName;
    private SimpleStringProperty userName;
    private SimpleStringProperty password;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty role;
    private SimpleStringProperty profileImagePath;

    public User(String fullName, String userName, String password, String email, String phone, String role, String profileImagePath) {
        this.fullName = new SimpleStringProperty( fullName);
        this.userName = new SimpleStringProperty(userName);
        this.password =new SimpleStringProperty (password);
        this.email = new SimpleStringProperty(email);
        this.phone =new SimpleStringProperty (phone);
        this.role =new SimpleStringProperty (role);
        this.profileImagePath = new SimpleStringProperty(profileImagePath);
    }


    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
     public SimpleStringProperty fullNameProperty() {
        return fullName; 
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getProfileImagePath() {
        return profileImagePath.get();
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath.set(profileImagePath);
    }
    public StringProperty ProfileImagePathProperty(){
        return this.profileImagePath;
    }


}
