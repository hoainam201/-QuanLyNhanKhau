package controllers.covidControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.KhaiBaoCachLyModel;
import models.TestCovidModel;
import services.CovidService;

import java.net.URL;
import java.util.ResourceBundle;

public class KhaiBaoCachLyController implements Initializable {
    @FXML
    Label ID;
    @FXML
    Label hoTen;
    @FXML
    DatePicker thoiGianBatDau;
    @FXML
    DatePicker thoiGianKetThuc;
    @FXML
    TextField mucDo;
    @FXML
    TextField diaDiemCachLy;


    CovidService covidService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        covidService = new CovidService();
    }

    public void xacNhan(ActionEvent event){
        if (!isMissingField()){
            KhaiBaoCachLyModel khaiBaoCachLyModel = new KhaiBaoCachLyModel();
            khaiBaoCachLyModel.setID(Integer.parseInt(ID.getText()));
            khaiBaoCachLyModel.setHoTen(hoTen.getText());
            khaiBaoCachLyModel.setThoiGianBatDau(thoiGianBatDau.getValue().toString());
            khaiBaoCachLyModel.setThoiGianKetThuc(thoiGianKetThuc.getValue().toString());
            khaiBaoCachLyModel.setMucDo(mucDo.getText());
            khaiBaoCachLyModel.setDiaDiemCachLy(diaDiemCachLy.getText());
            try{
                covidService.themThongTinCachLy(khaiBaoCachLyModel);
            } catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Người này đã được thêm thông tin cách ly");
                alert.show();
                return;
            }

            Alert themThanhCongAlert = new Alert(Alert.AlertType.INFORMATION);
            themThanhCongAlert.setTitle("");
            themThanhCongAlert.setContentText("Thêm thông tin cách ly thành công");
            themThanhCongAlert.show();
            huy(event);
        }
    }

    public boolean isMissingField(){
        if (thoiGianBatDau.getValue().equals("") || thoiGianKetThuc.getValue().equals("") || mucDo.getText().equals("") || diaDiemCachLy.getText().isBlank()){
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
