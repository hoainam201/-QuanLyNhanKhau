package controllers;

import beans.NhanKhauBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.NhanKhauModel;
import services.NhanKhauService;
import services.StringService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {

    @FXML
    TableView table;
    @FXML
    TableColumn ID;
    @FXML
    TableColumn hoTen;
    @FXML
    TableColumn namSinh;
    @FXML
    TableColumn gioiTinh;
    @FXML
    TableColumn diaChiHienNay;
    @FXML
    ComboBox<String> gioiTinhCB;
    @FXML
    ComboBox<String> tinhTrangCB;
    @FXML
    TextField tuTuoiText;
    @FXML
    TextField denTuoiText;
    @FXML
    TextField tuNamText;
    @FXML
    TextField denNamText;
    @FXML
    Button showButton;
    @FXML
    Button thongKeButton;


    SceneSwitch sceneSwitch;
    List<NhanKhauBean> listNhanKhauBeans;
    NhanKhauService nhanKhauService;
    ObservableList<NhanKhauModel> observablelistNhanKhau;
    ObservableList<String> gioiTinhList;
    ObservableList<String> tinhTrangList;
    int accessCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneSwitch = new SceneSwitch();
        nhanKhauService = new NhanKhauService();
        setData();
        showButton.setStyle("-fx-background-color: #0063B7; -fx-text-fill: white");
        thongKeButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");
        gioiTinhList = FXCollections.observableArrayList("Toàn bộ", "Nam", "Nữ");
        tinhTrangList = FXCollections.observableArrayList("Toàn bộ", "Thường trú", "Tạm trú", "Tạm vắng");
        gioiTinhCB.setItems(gioiTinhList);
        gioiTinhCB.getSelectionModel().selectFirst();
        tinhTrangCB.setItems(tinhTrangList);
        tinhTrangCB.getSelectionModel().selectFirst();
    }

    public void setData() {
        int tuTuoi = -1;
        int denTuoi = 200;
        int tuNam = 0;
        int denNam = 2100;
        String gender = "Toan Bo";
        String status = "Toan Bo";
        if (accessCount != 0){
            gender = StringService.covertToString(gioiTinhCB.getSelectionModel().getSelectedItem());
            status = StringService.covertToString(tinhTrangCB.getSelectionModel().getSelectedItem());
        }
        accessCount++;
        try {
            if (!tuTuoiText.getText().trim().isEmpty()) {
                tuTuoi = Integer.parseInt(tuTuoiText.getText().trim());
            } else {
                tuTuoi = -1;
            }
            if (!denTuoiText.getText().trim().isEmpty()) {
                denTuoi = Integer.parseInt(denTuoiText.getText().trim());
            } else {
                denTuoi = 200;
            }
            if (!tuNamText.getText().trim().isEmpty()) {
                tuNam = Integer.parseInt(tuNamText.getText().trim());
            }
            if (!denNamText.getText().trim().isEmpty()) {
                denNam = Integer.parseInt(denNamText.getText().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập đúng kiểu dữ liệu");
            alert.show();
        }
        listNhanKhauBeans = nhanKhauService.statisticNhanKhau(tuTuoi, denTuoi, gender, status, tuNam, denNam);
        setDataTable();
    }

    public void setDataTable() {
        List<NhanKhauModel> listItem = new ArrayList<>();
        listNhanKhauBeans.forEach(nhanKhau -> {
            listItem.add(nhanKhau.getNhanKhauModel());
        });
        observablelistNhanKhau = FXCollections.observableList(listItem);
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        namSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiHienNay.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        table.setItems(observablelistNhanKhau);
    }

    public void show() {
        setData();
    }

    public void changeSceneHome(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneHome(event);
    }

    public void changeSceneNhanKhau(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneNhanKhau(event);
    }

    public void changeSceneHoKhau(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneHoKhau(event);
    }

    public void changeSceneCovid(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneCovid(event);
    }
}
