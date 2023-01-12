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
import models.KhaiBaoLoTrinhModel;
import models.TestCovidModel;
import services.CovidService;

import java.net.URL;
import java.util.ResourceBundle;

public class KhaiBaoLoTrinhController implements Initializable {
    @FXML
    Label ID;
    @FXML
    Label hoTen;
    @FXML
    DatePicker thoiGianBatDau;
    @FXML
    DatePicker thoiGianKetThuc;
    @FXML
    TextField diaDiem;


    CovidService covidService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        covidService = new CovidService();
    }

    public void xacNhan(ActionEvent event){
        if (!isMissingField()){
            KhaiBaoLoTrinhModel khaiBaoLoTrinhModel = new KhaiBaoLoTrinhModel();
            khaiBaoLoTrinhModel.setID(Integer.parseInt(ID.getText()));
            khaiBaoLoTrinhModel.setHoTen(hoTen.getText());
            khaiBaoLoTrinhModel.setThoiGianBatDau(thoiGianBatDau.getValue().toString());
            khaiBaoLoTrinhModel.setThoiGianKetThuc(thoiGianKetThuc.getValue().toString());
            khaiBaoLoTrinhModel.setDiaDiem(diaDiem.getText());
            try{
                covidService.themThongTinLoTrinh(khaiBaoLoTrinhModel);
            } catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Người này đã được thêm thông tin lộ trình");
                return;
            }

            Alert themThanhCongAlert = new Alert(Alert.AlertType.INFORMATION);
            themThanhCongAlert.setTitle("");
            themThanhCongAlert.setContentText("Thêm thông tin lộ trình thành công");
            themThanhCongAlert.show();
            huy(event);
        }
    }

    public boolean isMissingField(){
        if (thoiGianBatDau.getValue().equals("") || thoiGianKetThuc.getValue().equals("") || diaDiem.getText().isBlank()){
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
