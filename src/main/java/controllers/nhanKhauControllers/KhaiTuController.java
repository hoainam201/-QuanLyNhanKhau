package controllers.nhanKhauControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.KhaiTuModel;
import services.HoKhauService;
import services.MysqlConnection;
import services.NhanKhauService;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class KhaiTuController implements Initializable {

    @FXML
    ImageView checkedIcon;
    @FXML
    TextField soCMTnguoiChet;
    @FXML
    TextField tenNguoiKhai;
    @FXML
    TextField soCMTnguoiKhai;
    @FXML
    TextField soGiayKhaiTu;
    @FXML
    DatePicker ngayKhai;
    @FXML
    DatePicker ngayMat;
    @FXML
    TextArea lyDoChet;
    @FXML
    Button checkButton;
    @FXML
    Button xacNhanButton;
    @FXML
    Button huyButton;


    boolean isValid, isValid1;
    int idNguoiChet;
    NhanKhauService nhanKhauService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nhanKhauService = new NhanKhauService();
        checkedIcon.setVisible(false);
        soGiayKhaiTu.setDisable(true);
        ngayKhai.setDisable(true);
        ngayMat.setDisable(true);
        lyDoChet.setDisable(true);
    }

    public void check(ActionEvent event) {
        String tempCMT = soCMTnguoiChet.getText().trim();
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
        int tempID = checkCMT(tempCMT);
        if (tempID != -1 && !soCMTnguoiKhai.getText().isBlank() && !tenNguoiKhai.getText().isBlank()) {
            soCMTnguoiKhai.setEditable(false);
            checkedIcon.setVisible(true);
            soGiayKhaiTu.setDisable(false);
            ngayKhai.setDisable(false);
            ngayMat.setDisable(false);
            lyDoChet.setDisable(false);
            xacNhanButton.setDisable(false);
            huyButton.setDisable(false);
            idNguoiChet = checkCMT(soCMTnguoiChet.getText());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Chưa nhập đủ thông tin người khai hoặc không tìm thấy số CMT trong hệ thống");
            alert.show();
        }
    }
    
    public void xacNhan(ActionEvent event) {
    	Connection connection;
    	String query;
    	PreparedStatement preparedStatement;
    	int ID = checkCMT(soCMTnguoiChet.getText());
        try {          
        	int soCMTnguoiChetT = checkCMT(soCMTnguoiChet.getText());
            int soCMTnguoiKhaiT = checkCMT(soCMTnguoiKhai.getText());
            String soGiayKhaiTuT = soGiayKhaiTu.getText();
            String ngayKhaiT = ngayKhai.getValue().toString();
            String ngayMatT = ngayMat.getValue().toString();
            String lyDoChetT = lyDoChet.getText();
            connection = MysqlConnection.getMysqlConnection();
            query = "INSERT INTO khai_tu  "
            		+ "VALUES (?,?,?,?,?,?);";   
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1,soGiayKhaiTuT );
	        preparedStatement.setInt(2, soCMTnguoiKhaiT);
	        preparedStatement.setInt(3,soCMTnguoiChetT);
	        preparedStatement.setString(4,ngayKhaiT );
	        preparedStatement.setString(5, ngayMatT);
	        preparedStatement.setString(6,lyDoChetT );
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }         
        updateQuaDoi(ID);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Đã lưu");
        alert.show();
        huy(event);
    }

    public void updateQuaDoi(int id) {
    	Connection connection;
    	String query;
    	PreparedStatement preparedStatement;
    	int ID = checkCMT(soCMTnguoiChet.getText());
        try {          
        	connection = MysqlConnection.getMysqlConnection();
            query =  " UPDATE nhan_khau "
            		+ "SET ghiChu = '1'"
            		+ "where ID = ? ;";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setInt(1,ID );
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
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

}
