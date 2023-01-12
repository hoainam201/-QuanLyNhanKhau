package controllers;

import controllers.covidControllers.SceneSwitchCovid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.TestCovidModel;
import services.CovidService;
import services.MysqlConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class CanBoYTeThongKeController implements Initializable {
    @FXML
    Button covidButton;
    @FXML
    Button xoaButton;
    @FXML
    Button exitButton;
    @FXML
    Button thongKeButton;
    @FXML
    PieChart fChart;
    @FXML
    BarChart<String, Number> trieuChungChart;
    @FXML
    BarChart<String, Number> benhNenChart;
    

    SceneSwitch sceneSwitch;
    SceneSwitchCovid sceneSwitchCovid;
    CovidService covidService;
    ObservableList<TestCovidModel> testCovidModelList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){

    	
        thongKeButton.setStyle("-fx-background-color: #0B82FA; -fx-text-fill: white");

        sceneSwitch = new SceneSwitch();
        sceneSwitchCovid = new SceneSwitchCovid();
        covidService = new CovidService();
        
        
        //Tao bieu do F0 F1 F2 F3
        int F0value=countF0();
        int F1value=countF1();
        int F2value=countF2();
        int F3value=countF3();
        int F4value=countF4();
        PieChart.Data f0=new PieChart.Data("F0", F0value);
        PieChart.Data f1=new PieChart.Data("F1", F1value);
        PieChart.Data f2=new PieChart.Data("F2", F2value);
        PieChart.Data f3=new PieChart.Data("F3", F3value);
        PieChart.Data f4=new PieChart.Data("F4", F4value);
        fChart.getData().clear();
        fChart.getData().addAll(f0,f1,f2,f3,f4);
        for (PieChart.Data data : fChart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chi tiết");
                alert.setContentText(data.getName()+":  "+data.getPieValue());
                alert.showAndWait();
            });
        }
        //Tao bieu do cot trieu chung
        int ho=countHo();
        int sot=countSot();
        int khotho=countKhotho();
        int dauhong=countDauhong();
        int khongtrieuchung=countKhongtrieuchung();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Triệu chứng");
  
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Người");
        
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
        dataSeries.getData().add(new XYChart.Data<String, Number>("Ho", ho));
        dataSeries.getData().add(new XYChart.Data<String, Number>("Sốt", sot));
        dataSeries.getData().add(new XYChart.Data<String, Number>("Khó thở",khotho));
        dataSeries.getData().add(new XYChart.Data<String, Number>("Đau họng", dauhong));
        dataSeries.getData().add(new XYChart.Data<String, Number>("Không có triệu chứng", khongtrieuchung));
        dataSeries.setName("Số người mắc triệu chứng");
        
        trieuChungChart.getData().add(dataSeries);
        trieuChungChart.setTitle("Thống kê các triệu chứng ");
        
        //Tao bieu do cot benh nen
        int gan=countGan();
        int than=countThan();
        int phoi=countPhoi();
        int timmach=countTimmach();
        int caohuyetap=countCaohuyetap();
        int tieuduong=countTieuduong();
        int khongcobenhmantinh=countKhongcobenhmantinh();
        CategoryAxis xAxis1 = new CategoryAxis();
        xAxis.setLabel("Bệnh nền");
  
        NumberAxis yAxis1 = new NumberAxis();
        yAxis.setLabel("Người");
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Gan",gan ));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Thận", than));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Phổi",phoi));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Tim mạch", timmach));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Cao huyết áp", caohuyetap));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Tiểu đường", tieuduong));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Không có triệu chứng", khongcobenhmantinh));
        dataSeries1.setName("Số người mắc bệnh nền");
        
        benhNenChart.getData().add(dataSeries1);
        benhNenChart.setTitle("Thống kê các bệnh nền ");
    }
    
    public int countGan() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(gan = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countThan() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(than = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countPhoi() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(phoi = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countTimmach() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(timmach = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countCaohuyetap() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(caohuyetap = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countTieuduong() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(tieuduong = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countKhongcobenhmantinh() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(khongcobenhmantinh = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countF0() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(mucDo = 'F0', 1, NULL))  AS tong FROM testcovid";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countF1() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(mucDo = 'F1', 1, NULL))  AS tong FROM testcovid";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countF2() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(mucDo = 'F2', 1, NULL))  AS tong FROM testcovid";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countF3() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(mucDo = 'F3', 1, NULL))  AS tong FROM testcovid";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countF4() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(mucDo = 'F4', 1, NULL))  AS tong FROM testcovid";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countHo() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(ho = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countSot() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(sot = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countKhotho() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(khotho = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countDauhong() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(dauhong = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public int countKhongtrieuchung() {
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(IF(khongtrieuchung = '1', 1, NULL))  AS tong FROM khai_bao_suc_khoe";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("tong");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return -1;
    }
    
    public void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Login.fxml"));
        Parent homeParent = loader.load();
        Scene scene = new Scene(homeParent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    
    public void backToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CanBoYTe.fxml"));
        Parent homeParent = loader.load();
        Scene scene = new Scene(homeParent);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    
}
