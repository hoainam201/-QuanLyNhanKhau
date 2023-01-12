package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.MysqlConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    Label tongNhanKhauLb;
    @FXML
    Label tongHoKhauLb;
    @FXML
    Label nhanKhauTamTruLb;
    @FXML
    Label nhanKhauTamVangLb;
    @FXML
    Label soCaMacCovid;
    @FXML
    Label theoDoiCachLy;
    @FXML
    Button homeButton;

    SceneSwitch sceneSwitch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");
        setData();
        sceneSwitch = new SceneSwitch();
    }

    public void changeSceneLogin(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneLogin(event);
    }
    
    public void changeSceneHoKhau(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneHoKhau(event);
    }

    public void changeSceneNhanKhau(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneNhanKhau(event);
    }

    public void changeSceneThongKe(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneThongKe(event);
    }

    public void changeSceneCovid(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneCovid(event);
    }

    public void setData() {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            //TONG SO NHAN KHAU
            String query = "SELECT COUNT(*) AS tong FROM nhan_khau";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongNhanKhauLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            //TONG SO HO KHAU
            query = "SELECT COUNT(*) AS tong FROM ho_khau";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongHoKhauLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            //NHAN KHAU TAM TRU
            query = "SELECT COUNT(*) AS tong FROM tam_tru WHERE denNgay < NOW()";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.nhanKhauTamTruLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            //SO NGUOI VANG MAT
            query = "SELECT COUNT(*) AS tong FROM tam_vang WHERE  NOW() < denNgay ";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.nhanKhauTamVangLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            //Nut so ca mac
            query = "SELECT COUNT(IF(ketQuaTest = 'Dương tính', 1, NULL))  AS tong FROM testcovid";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
            	this.soCaMacCovid.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            //Nut so nguoi theo doi cach ly
            query = "SELECT COUNT(*) AS tong FROM testcovid";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
            	this.theoDoiCachLy.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            connection.close();
        } catch (Exception e) {
        }
    }
}
