package controllers;

import beans.HoKhauBean;
import controllers.hoKhauControllers.HoKhauHolder;
import controllers.hoKhauControllers.SceneSwitchHoKhau;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.HoKhauService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HoKhauController implements Initializable {
    @FXML
    TableView<HoKhauBean> table;
    @FXML
    TableColumn<HoKhauBean, String> maHoKhau;
    @FXML
    TableColumn<HoKhauBean, String> hoTen;
    @FXML
    TableColumn<HoKhauBean, String> diaChi;
    @FXML
    Button hoKhauButton;

    HoKhauService hoKhauService;
    List<HoKhauBean> listHoKhauBeans;
    ObservableList<HoKhauBean> observableListHoKhauBeans;
    SceneSwitch sceneSwitch;
    SceneSwitchHoKhau sceneSwitchHoKhau;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hoKhauButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");
        sceneSwitch = new SceneSwitch();
        sceneSwitchHoKhau = new SceneSwitchHoKhau();
        hoKhauService = new HoKhauService();
        setDataTable();
    }

    public void setDataTable(){
        listHoKhauBeans = hoKhauService.getListHoKhau();
        observableListHoKhauBeans = FXCollections.observableList(listHoKhauBeans);
        maHoKhau.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getMaHoKhau()));
        hoTen.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getChuHo().getHoTen()));
        diaChi.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getDiaChi()));
        table.setItems(observableListHoKhauBeans);
    }

    public void themMoi(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/HoKhau/ThemMoi.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Thêm mới hộ khẩu");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataTable();
    }

    public void tachHoKhau(ActionEvent event) throws IOException{
        sceneSwitchHoKhau.changeSceneTachHoKhau(event);
    }

    public void chuyenDi(ActionEvent event) throws IOException{
        sceneSwitchHoKhau.changeSceneChuyenDi(event);
    }

    public void xemChiTiet(MouseEvent event) throws IOException {
        HoKhauBean selectedHoKhau = table.getSelectionModel().getSelectedItem();
        HoKhauBean hoKhau = listHoKhauBeans.stream().filter(hoKhauBean -> hoKhauBean.getHoKhauModel().getMaHoKhau().equals(selectedHoKhau.getHoKhauModel().getMaHoKhau())).findFirst().orElse(new HoKhauBean());
        HoKhauHolder hoKhauHolder = HoKhauHolder.getInstance();
        hoKhauHolder.setHoKhauBean(hoKhau);
        if(event.getClickCount() == 2 && selectedHoKhau != null){
            sceneSwitchHoKhau.changeSceneChiTiet(event);
        }
    }

    public void changeSceneHome(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneHome(event);
    }

    public void changeSceneNhanKhau(ActionEvent event) throws IOException{
        sceneSwitch.changeSceneNhanKhau(event);
    }

    public void changeSceneThongKe(ActionEvent event) throws IOException{
        sceneSwitch.changeSceneThongKe(event);
    }

    public void changeSceneCovid(ActionEvent event) throws IOException{
        sceneSwitch.changeSceneCovid(event);
    }
}
