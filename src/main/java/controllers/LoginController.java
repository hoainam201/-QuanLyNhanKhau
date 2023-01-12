package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.UserMoldel;
import services.MysqlConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    TextField userNameText;
    @FXML
    TextField passwordText;
    

    public static UserMoldel currentUser = new UserMoldel();

    public void login(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String userName = userNameText.getText();
        String password = passwordText.getText();
        if (checkUserCredential(userName, password)==1) {
            changeSceneHome(event);
        }
        else if(checkUserCredential(userName, password)==2) {
        	changeSceneCanBoYTe(event);
        }
        else {
            popUpWrongCreadentialAlert();
        }
    }

    public int checkUserCredential(String userName, String password) throws SQLException, ClassNotFoundException {
    	if (userName.equals("canboyte")&&password.equals("1")) return 2;
    	else if (userName.equals("canboyte")&&password.equals("1")!=true) return 0;
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE userName = '" + userName + "'");
        if (rs == null) {
            return 0;
        }
        while (rs.next()) {
            if (rs.getString("passwd") == null ? password == null : rs.getString("passwd").equals(password)) {
                LoginController.currentUser.setID(rs.getInt("ID"));
                LoginController.currentUser.setUserName(rs.getString("userName"));
                return 1;
            }
        }
        connection.close();
        return 0;
    }
    
    public void changeSceneCanBoYTe(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CanBoYTe.fxml"));
        Parent homeParent = loader.load();
        Scene scene = new Scene(homeParent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Home.fxml"));
        Parent homeParent = loader.load();
        Scene scene = new Scene(homeParent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void popUpWrongCreadentialAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lỗi đăng nhập");
        alert.setHeaderText("Sai mật khẩu hoặc tài khoản");
        alert.setContentText("Vui lòng nhập lại");
        alert.show();
    }
}
