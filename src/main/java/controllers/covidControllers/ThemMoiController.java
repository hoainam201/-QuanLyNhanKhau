package controllers.covidControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.TestCovidModel;
import services.CovidService;
import services.MysqlConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ThemMoiController implements Initializable {
    @FXML
    TextField soCMTText;
    @FXML
    ImageView checkedIcon;
    @FXML
    Label hoTenLB;
    @FXML
    DatePicker thoiGianTestDP;
    @FXML
    ComboBox<String> ketQuaTestCB;
    @FXML
    TextField hinhThucTestText;
    @FXML
    Button xacNhanButton;
    @FXML
    Button huyButton;
    @FXML
    ComboBox<String> mucDoCB;

    TestCovidModel testCovidModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> ketQuaTestList = FXCollections.observableArrayList("Âm tính", "Dương tính");
        ObservableList<String> mucDo = FXCollections.observableArrayList("F0", "F1","F2","F3","F4","Ko rõ");
        ketQuaTestCB.setItems(ketQuaTestList);
        mucDoCB.setItems(mucDo);
        testCovidModel = new TestCovidModel();
        setUpForCheck();
    }

    public void check(ActionEvent event) {
        String tempCMT = soCMTText.getText().trim();
        if (tempCMT.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập số CMT");
            alert.show();
            return;
        } else {
            try {
                Long.parseLong(tempCMT);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("Vui lòng nhập số CMT đúng định dạng");
                alert.show();
                return;
            }
        }
        int tempID = checkCMT(soCMTText.getText());
        String tempName = checkCMTgetName(soCMTText.getText());
        if (tempID != -1) {
            soCMTText.setEditable(false);
            checkedIcon.setVisible(true);
            thoiGianTestDP.setDisable(false);
            ketQuaTestCB.setDisable(false);
            hinhThucTestText.setDisable(false);
            xacNhanButton.setDisable(false);
            huyButton.setDisable(false);
            hoTenLB.setText(tempName);
            mucDoCB.setDisable(false);

            testCovidModel.setID(tempID);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Không tìm thấy số CMT trong hệ thống");
            alert.show();
        }
    }

    public int checkCMT(String cmt) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM nhan_khau LEFT JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE soCMT = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cmt);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (Exception e) {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setTitle("Warning!");
            errorMessage.setContentText("Có lỗi xảy ra! vui lòng kiểm tra lại.");
            errorMessage.show();
        }
        return -1;
    }

    public String checkCMTgetName(String cmt) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM nhan_khau LEFT JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE soCMT = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cmt);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("hoTen");
            }
        } catch (Exception e) {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setTitle("Warning!");
            errorMessage.setContentText("Có lỗi xảy ra! vui lòng kiểm tra lại.");
            errorMessage.show();
        }
        return null;
    }

    public void xacNhan(ActionEvent event) {
        if (!validateForm()) {
            testCovidModel.setHoTen(hoTenLB.getText());
            testCovidModel.setThoiGianTest(String.valueOf(thoiGianTestDP.getValue()));
            testCovidModel.setKetQuaTest(ketQuaTestCB.getValue().trim());
            testCovidModel.setHinhThucTest(hinhThucTestText.getText().trim());
            testCovidModel.setMucDo(mucDoCB.getValue().trim());
            if (new CovidService().addNew(testCovidModel)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Thêm thành công");
                alert.show();
                soCMTText.setText("");
                hoTenLB.setText("");
                thoiGianTestDP.setValue(null);
                ketQuaTestCB.setValue(null);
                mucDoCB.setValue(null);
                hinhThucTestText.setText("");
                setUpForCheck();
            }

        }
    }

    public void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    public void setUpForCheck() {
        checkedIcon.setVisible(false);
        thoiGianTestDP.setDisable(true);
        ketQuaTestCB.setDisable(true);
        hinhThucTestText.setDisable(true);
        xacNhanButton.setDisable(true);
        huyButton.setDisable(true);
        hoTenLB.setText("");
        mucDoCB.setDisable(true);
    }

    private boolean validateForm() {
        return (thoiGianTestDP.getValue() == null
                || ketQuaTestCB.getValue().trim().isEmpty()
                || hinhThucTestText.getText().trim().isEmpty());
    }

}
