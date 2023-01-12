package controllers.nhanKhauControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.ChungMinhThuModel;
import models.NhanKhauModel;
import services.CovidService;
import services.MysqlConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChiTietController implements Initializable {
	@FXML
    TextField bietDanhTxb;
    @FXML
    TextField hoTenTxb;
    @FXML
    Label dateNamSinh;
    @FXML
    Label gioiTinh;
    @FXML
    TextField nguyenQuanTxb;
    @FXML
    TextField tonGiaoTxb;
    @FXML
    TextField danTocTxb;
    @FXML
    TextField quocTichTxb;
    @FXML
    TextField soCMTTxb;
    @FXML
    TextField soHoChieuTxb;
    @FXML
    TextField noiThuongTruTxb;
    @FXML
    TextField diaChiHienNayTxb;
    @FXML
    TextField trinhDoHocVanTxb;
    @FXML
    TextField trinhDoChuyenMonTxb;
    @FXML
    TextField trinhDoNgoaiNguTxb;
    @FXML
    TextField bietTiengDanTocTxb;
    @FXML
    TextField ngheNghiepTxb;
    @FXML
    TextField noiLamViecTxb;
    @FXML
    TextField tienAnTxb;
    @FXML
    TextField tieuSuTxb;
    @FXML
    Button saveButton;
    @FXML 
    Label idLabel;
    @FXML
    Label lb1;
    @FXML
    Label lb2;
    @FXML
    Label lb3;
    @FXML
    Label lb4;
    @FXML
    Label lb5;
    @FXML
    Label lb6;
    @FXML
    Label lb7;
    @FXML
    Label lb8;
    @FXML
    Label lb9;
    @FXML
    Label lb10;

    CovidService covidService;
    private SceneSwitchNhanKhau sceneSwitchNhanKhau;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        covidService = new CovidService();

    }
    
    public void setData(NhanKhauModel nhanKhauModel) {
    	//Tim ra nhan khau
    	
    	int ID = nhanKhauModel.getID();
    	NhanKhauModel nhanKhauModel2 = new NhanKhauModel();
    	//lay nhankhau co id
    	Connection connection;
		try {
			connection = MysqlConnection.getMysqlConnection();
			String query = "SELECT * FROM NHAN_KHAU WHERE ID = " + ID +";";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	nhanKhauModel2.setID(rs.getInt("ID"));       	
	        	nhanKhauModel2.setHoTen(rs.getString("hoTen"));
	        	nhanKhauModel2.setNamSinh(rs.getDate("namSinh"));
	        	nhanKhauModel2.setNguyenQuan(rs.getString("nguyenQuan"));
	        	nhanKhauModel2.setDanToc(rs.getString("danToc"));
	        	nhanKhauModel2.setNoiThuongTru(rs.getString("noiThuongTru"));
	        	nhanKhauModel2.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
	        	nhanKhauModel2.setTrinhDoNgoaiNgu(rs.getString("trinhDoNgoaiNgu"));
	        	nhanKhauModel2.setNgheNghiep(rs.getString("ngheNghiep"));
	        	nhanKhauModel2.setTienAn(rs.getString("tienAn"));
	        	nhanKhauModel2.setBietDanh(rs.getString("bietDanh"));
	        	nhanKhauModel2.setGioiTinh(rs.getString("gioiTinh"));     	
	        	nhanKhauModel2.setTonGiao(rs.getString("tonGiao"));
	        	nhanKhauModel2.setQuocTich(rs.getString("quocTich"));
	        	nhanKhauModel2.setSoHoChieu(rs.getString("soHoChieu"));
	        	nhanKhauModel2.setDiaChiHienNay(rs.getString("diaChiHienNay"));
	        	nhanKhauModel2.setTrinhDoChuyenMon(rs.getString("trinhDoChuyenMon"));
	        	nhanKhauModel2.setBietTiengDanToc(rs.getString("bietTiengDanToc"));
	        	nhanKhauModel2.setNoiLamViec(rs.getString("noiLamViec"));
	        	nhanKhauModel2.setTieuSu(rs.getString("tieuSu"));
	        	nhanKhauModel2.setGhiChu(rs.getString("ghiChu"));
	        }
	        preparedStatement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	//Neu da chet thi hien thong bao
		if (nhanKhauModel2.getGhiChu().equals("1")) {
			try {
				daChet(ID);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        String cmtFromID = covidService.getCMTfromID(nhanKhauModel2.getID());
        idLabel.setText(String.valueOf(ID));
        hoTenTxb.setText(nhanKhauModel2.getHoTen());
        dateNamSinh.setText(String.valueOf(nhanKhauModel2.getNamSinh()));
        gioiTinh.setText(nhanKhauModel2.getGioiTinh());
        nguyenQuanTxb.setText(nhanKhauModel2.getNguyenQuan());
        danTocTxb.setText(nhanKhauModel2.getDanToc());
        soCMTTxb.setText(cmtFromID);
        noiThuongTruTxb.setText(nhanKhauModel2.getNoiThuongTru());
        trinhDoHocVanTxb.setText(nhanKhauModel2.getTrinhDoHocVan());
        trinhDoNgoaiNguTxb.setText(nhanKhauModel2.getTrinhDoNgoaiNgu());
        ngheNghiepTxb.setText(nhanKhauModel2.getNgheNghiep());
        tienAnTxb.setText(nhanKhauModel2.getTienAn());
        bietDanhTxb.setText(nhanKhauModel2.getBietDanh());
        tonGiaoTxb.setText(nhanKhauModel2.getTonGiao());
        quocTichTxb.setText(nhanKhauModel2.getQuocTich());
        soHoChieuTxb.setText(nhanKhauModel2.getSoHoChieu());
        diaChiHienNayTxb.setText(nhanKhauModel2.getDiaChiHienNay());
        trinhDoChuyenMonTxb.setText(nhanKhauModel2.getTrinhDoChuyenMon());
        bietTiengDanTocTxb.setText(nhanKhauModel2.getBietTiengDanToc());
        noiLamViecTxb.setText(nhanKhauModel2.getNoiLamViec());
        tieuSuTxb.setText(nhanKhauModel2.getTieuSu());
        
        //tat editable
        hoTenTxb.setEditable(false);
        nguyenQuanTxb.setEditable(false);
        danTocTxb.setEditable(false);
        soCMTTxb.setEditable(false);
        noiThuongTruTxb.setEditable(false);
        trinhDoHocVanTxb.setEditable(false);
        trinhDoNgoaiNguTxb.setEditable(false);
        ngheNghiepTxb.setEditable(false);
        tienAnTxb.setEditable(false);
        bietDanhTxb.setEditable(false);
        tonGiaoTxb.setEditable(false);
        quocTichTxb.setEditable(false);
        soHoChieuTxb.setEditable(false);
        diaChiHienNayTxb.setEditable(false);
        trinhDoChuyenMonTxb.setEditable(false);
        bietTiengDanTocTxb.setEditable(false);
        noiLamViecTxb.setEditable(false);
        tieuSuTxb.setEditable(false);
    }
    
    public void save(){
    	int id=Integer.parseInt(idLabel.getText());
    	System.out.printf("id: %d\n",id);
    	Connection connection;
    	try {
    		connection = MysqlConnection.getMysqlConnection();
			String query = "UPDATE nhan_khau "
						+	" SET hoTen = ? ," //1
						+	" nguyenQuan = ? ,"//2
						+	" danToc = ?,"//3
						+	" noiThuongTru = ? ,"//4
						+	" trinhDoHocVan = ? ,"//5
						+	" trinhDoNgoaiNgu = ? ,"//6
						+	" ngheNghiep = ? ,"//7 
						+	" tienAn = ? ,"//8
						+	" bietDanh = ? ,"//9
						+	" tonGiao = ? ,"//10
						+	" quocTich = ? ,"//11
						+	" soHoChieu = ? ,"//12
						+	" diaChiHienNay = ? ,"//13
						+	" trinhDoChuyenMon = ? ,"//14
						+	" bietTiengDanToc = ? ,"//15
						+	" noiLamViec = ? ,"//16
						+	" tieuSu = ? "//17
						+	" where id = " +id +";";
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, hoTenTxb.getText());
	        preparedStatement.setString(2, nguyenQuanTxb.getText());
	        preparedStatement.setString(3,danTocTxb.getText() );
	        preparedStatement.setString(4, noiThuongTruTxb.getText());
	        preparedStatement.setString(5, trinhDoHocVanTxb.getText());
	        preparedStatement.setString(6,trinhDoNgoaiNguTxb.getText() );
	        preparedStatement.setString(7, ngheNghiepTxb.getText());
	        preparedStatement.setString(8, tienAnTxb.getText());
	        preparedStatement.setString(9, bietDanhTxb.getText());
	        preparedStatement.setString(10, tonGiaoTxb.getText());
	        preparedStatement.setString(11, quocTichTxb.getText());
	        preparedStatement.setString(12, soHoChieuTxb.getText());
	        preparedStatement.setString(13, diaChiHienNayTxb.getText());
	        preparedStatement.setString(14, trinhDoChuyenMonTxb.getText());
	        preparedStatement.setString(15, bietTiengDanTocTxb.getText());
	        preparedStatement.setString(16, noiLamViecTxb.getText());
	        preparedStatement.setString(17, tieuSuTxb.getText());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	        
	        
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    }
    
    public void enableModify() {
    	 hoTenTxb.setEditable(true);
         nguyenQuanTxb.setEditable(true);
         danTocTxb.setEditable(true);
         noiThuongTruTxb.setEditable(true);
         trinhDoHocVanTxb.setEditable(true);
         trinhDoNgoaiNguTxb.setEditable(true);
         ngheNghiepTxb.setEditable(true);
         tienAnTxb.setEditable(true);
         bietDanhTxb.setEditable(true);
         tonGiaoTxb.setEditable(true);
         quocTichTxb.setEditable(true);
         soHoChieuTxb.setEditable(true);
         diaChiHienNayTxb.setEditable(true);
         trinhDoChuyenMonTxb.setEditable(true);
         bietTiengDanTocTxb.setEditable(true);
         noiLamViecTxb.setEditable(true);
         tieuSuTxb.setEditable(true);
         saveButton.setDisable(false);
    }
    
    
    public void daChet(int id) throws ClassNotFoundException, SQLException {
    	Connection connection = MysqlConnection.getMysqlConnection();
    	lb1.setText("Ngày mất: ");
    	lb2.setText("Người khai: ");
    	lb3.setText("Lý do qua đời: ");
    	lb4.setText("Số giấy khai tử: ");
    	lb5.setText("Ngày khai: ");

    	
		String query = "SELECT * FROM khai_tu where idNguoiChet = "+id+";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
        	lb6.setText(rs.getString("ngayChet"));
        	lb7.setText(String.valueOf(rs.getInt("idNguoiKhai")));
        	lb8.setText(rs.getString("lyDoChet"));
        	lb9.setText(rs.getString("soGiayKhaiTu"));
        	lb10.setText(rs.getString("ngayKhai"));
        }
        preparedStatement.close();
    }
}
