package controllers.covidControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.KhaiBaoSucKhoeModel;
import models.TestCovidModel;
import services.CovidService;

import java.net.URL;
import java.util.ResourceBundle;

public class KhaiBaoSucKhoeController implements Initializable {
    @FXML
    Label ID;
    @FXML
    Label hoTen;
    @FXML
    TextArea nguoiTiepXuc;
    @FXML
    CheckBox tc1;//Ho
    @FXML
    CheckBox tc2;//Kho tho
    @FXML
    CheckBox tc3;//Dau hong
    @FXML
    CheckBox tc4;//Sot
    @FXML
    CheckBox tc5;//Khong trieu chung
    @FXML
    CheckBox bn1;//Gan
    @FXML
    CheckBox bn2;//Than
    @FXML
    CheckBox bn3;//Phoi
    @FXML
    CheckBox bn4;//Tim mach
    @FXML
    CheckBox bn5;//Cao huyet ap
    @FXML
    CheckBox bn6;//Tieu duong
    @FXML
    CheckBox bn7;//Khong co benh nen
    

    CovidService covidService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        covidService = new CovidService();
    }

    public int toInt(boolean n) {
    	if (n==true) return 1;
    	else return 0;
    }
    
    public void xacNhan(ActionEvent event){
        if (!isMissingField()){
            KhaiBaoSucKhoeModel khaiBaoSucKhoeModel = new KhaiBaoSucKhoeModel();
            khaiBaoSucKhoeModel.setID(Integer.parseInt(ID.getText()));
            khaiBaoSucKhoeModel.setHoTen(hoTen.getText());
            khaiBaoSucKhoeModel.setNguoiTiepXuc(nguoiTiepXuc.getText());
            khaiBaoSucKhoeModel.setTc1(toInt(tc1.isSelected()));
            khaiBaoSucKhoeModel.setTc2(toInt(tc2.isSelected()));
            khaiBaoSucKhoeModel.setTc3(toInt(tc3.isSelected()));
            khaiBaoSucKhoeModel.setTc4(toInt(tc4.isSelected()));
            khaiBaoSucKhoeModel.setTc5(toInt(tc5.isSelected()));
            khaiBaoSucKhoeModel.setBn1(toInt(bn1.isSelected()));
            khaiBaoSucKhoeModel.setBn2(toInt(bn2.isSelected()));
            khaiBaoSucKhoeModel.setBn3(toInt(bn3.isSelected()));
            khaiBaoSucKhoeModel.setBn4(toInt(bn4.isSelected()));
            khaiBaoSucKhoeModel.setBn5(toInt(bn5.isSelected()));
            khaiBaoSucKhoeModel.setBn6(toInt(bn6.isSelected()));
            khaiBaoSucKhoeModel.setBn7(toInt(bn7.isSelected()));
            try{
                covidService.themThongTinSucKhoe(khaiBaoSucKhoeModel);
            } catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Người này đã được thêm thông tin sức khỏe");
            }

            Alert themThanhCongAlert = new Alert(Alert.AlertType.INFORMATION);
            themThanhCongAlert.setTitle("");
            themThanhCongAlert.setContentText("Thêm thông tin sức khỏe thành công");
            themThanhCongAlert.show();
        }
        huy(event);
    }

    public boolean isMissingField(){
        if (nguoiTiepXuc.getText().equals("")){
            return true;
        } else return false;
    }

    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    public void setData(TestCovidModel testCovidModel) {
        ID.setText(String.valueOf(testCovidModel.getID()));
        hoTen.setText(testCovidModel.getHoTen());
    }
}
