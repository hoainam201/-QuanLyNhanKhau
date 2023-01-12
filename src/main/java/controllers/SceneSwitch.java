package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitch {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;

    public SceneSwitch(){

    }

    public void changeSceneLogin(ActionEvent event) throws IOException{
    	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Login.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    
    public void changeSceneHome(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Home.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneHoKhau(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneNhanKhau(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanKhau.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneThongKe(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKe.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeSceneCovid(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Covid.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    
    public void changeSceneCanBoYTeThongKe(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CanBoYTeThongKe.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
