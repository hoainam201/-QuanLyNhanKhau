package controllers.covidControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.TestCovidModel;

import java.io.IOException;

public class SceneSwitchCovid {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;

    public void changeSceneCachLy(ActionEvent event, TestCovidModel testCovidModel) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Covid/KhaiBaoCachLy.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        KhaiBaoCachLyController khaiBaoCachLyController = loader.getController();
        khaiBaoCachLyController.setData(testCovidModel);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Khai báo cách ly");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }

    public void changeSceneLoTrinh(ActionEvent event, TestCovidModel testCovidModel) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Covid/KhaiBaoLoTrinh.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        KhaiBaoLoTrinhController khaiBaoLoTrinhController = loader.getController();
        khaiBaoLoTrinhController.setData(testCovidModel);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Khai báo lộ trình");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }

    public void changeSceneSucKhoe (ActionEvent event, TestCovidModel testCovidModel) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Covid/KhaiBaoSucKhoe.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        KhaiBaoSucKhoeController khaiBaoSucKhoeController = loader.getController();
        khaiBaoSucKhoeController.setData(testCovidModel);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Khai báo sức khỏe");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }


    public void changeSceneChiTiet(MouseEvent event, TestCovidModel testCovidModel) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Covid/ChiTiet.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        ChiTietController chiTietController = loader.getController();
        chiTietController.setData(testCovidModel);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Chi tiết người theo dõi");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.show();
    }
}
