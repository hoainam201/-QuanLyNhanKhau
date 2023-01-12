package controllers;
import beans.NhanKhauBean;
import controllers.nhanKhauControllers.SceneSwitchNhanKhau;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.NhanKhauModel;
import services.MysqlConnection;
import services.NhanKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {

    @FXML
    TableView<NhanKhauModel> table;
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
    Button nhanKhauButton;


    private NhanKhauService nhanKhauService;
    private List<NhanKhauBean> listNhanKhauBeans;
    
    private ObservableList<NhanKhauModel> observablelistNhanKhau;
    private SceneSwitch sceneSwitch;
    private SceneSwitchNhanKhau sceneSwitchNhanKhau;
    private List<NhanKhauModel> listItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneSwitchNhanKhau = new SceneSwitchNhanKhau();
        nhanKhauService = new NhanKhauService();
        sceneSwitch = new SceneSwitch();
        setDataTable();
        nhanKhauButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");
    }

    public void xoaNhanKhau() {
    	TextInputDialog dialog = new TextInputDialog("");   	
    	dialog.setTitle("Chức năng xoá nhân khẩu ");
    	dialog.setHeaderText("Xoá khỏi danh sách nhân khẩu ");
    	dialog.setContentText("Nhập ID muốn xoá:");
    	
    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    try {
    	    	Connection connection = MysqlConnection.getMysqlConnection();
                String query = "SET FOREIGN_KEY_CHECKS=0;";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                query = "DELETE FROM nhan_khau WHERE ID = "+ result.get();
                statement = connection.createStatement();
                statement.executeUpdate(query);
                query = "SET FOREIGN_KEY_CHECKS=1";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                
    	    }
    	    catch (Exception e) {
            }
    	    
    	}
    	setDataTable();
    }
    
    public void setDataTable(){
        listNhanKhauBeans = nhanKhauService.getListNhanKhau();
        listItem = new ArrayList<>();
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

    public void chonXemNhanKhau(MouseEvent event) throws IOException {
        NhanKhauModel nhanKhauModel = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (nhanKhauModel != null)){
            sceneSwitchNhanKhau.changeSceneChiTiet(event, nhanKhauModel);
        }
    }

    public void updateAddedNhanKhau(NhanKhauBean nhanKhauBean){
        NhanKhauModel nhanKhauAdded = nhanKhauBean.getNhanKhauModel();
        observablelistNhanKhau.add(nhanKhauAdded);
    }

    public void themMoi(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanKhau/ThemMoi.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Thêm mới nhân khẩu");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataTable();
    }

    public void dangKyTamVang(ActionEvent event) throws IOException {
        sceneSwitchNhanKhau.changeSceneTamVang(event);
    }

    public void dangKyTamTru(ActionEvent event) throws IOException{
        sceneSwitchNhanKhau.changeSceneTamTru(event);
    }

    public void khaiTu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanKhau/KhaiTu.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setTitle("Khai tử");
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataTable();
    }

    public void refreshData(NhanKhauBean nhanKhauBean) {
        listNhanKhauBeans = nhanKhauService.getListNhanKhau();
        updateAddedNhanKhau(nhanKhauBean);
    }

    public void changeSceneHome(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneHome(event);
    }

    public void changeSceneHoKhau(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneHoKhau(event);
    }

    public void changeSceneThongKe(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneThongKe(event);
    }

    public void changeSceneCovid(ActionEvent event) throws IOException {
        sceneSwitch.changeSceneCovid(event);
    }
}
