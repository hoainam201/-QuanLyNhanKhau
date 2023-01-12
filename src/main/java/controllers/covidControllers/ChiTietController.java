package controllers.covidControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.KhaiBaoCachLyModel;
import models.KhaiBaoLoTrinhModel;
import models.KhaiBaoSucKhoeModel;
import models.TestCovidModel;
import services.CovidService;

import java.util.List;

public class ChiTietController {
    @FXML
    Label ID;
    @FXML
    Label hoTen;
    @FXML
    TableView cachLyTable;
    @FXML
    TableView loTrinhTable;
    @FXML
    TableColumn thoiGianBatDauCachLy;
    @FXML
    TableColumn thoiGianKetThucCachLy;
    @FXML
    TableColumn mucDo;
    @FXML
    TableColumn diaDiemCachLy;
    @FXML
    TableColumn thoiGianBatDauLoTrinh;
    @FXML
    TableColumn thoiGianKetThucLoTrinh;
    @FXML
    TableColumn diaDiem;
    @FXML
    Label trieuChung;
    @FXML
    Label nguoiTiepXuc;
    @FXML
    Label tieuSuBenh;
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
    List<KhaiBaoCachLyModel> khaiBaoCachLyModels;
    List<KhaiBaoLoTrinhModel> khaiBaoLoTrinhModels;
    KhaiBaoSucKhoeModel khaiBaoSucKhoeModel;
    int id;

    public void setDataChiTiet(){
        id = Integer.parseInt(ID.getText().trim());
        covidService = new CovidService();
        khaiBaoCachLyModels = covidService.getKhaiBaoCachLyByID(id);
        khaiBaoLoTrinhModels = covidService.getKhaiBaoLoTrinhByID(id);

        ObservableList khaiBaoCachLy = FXCollections.observableList(khaiBaoCachLyModels);
        thoiGianBatDauCachLy.setCellValueFactory(new PropertyValueFactory<>("thoiGianBatDau"));
        thoiGianKetThucCachLy.setCellValueFactory(new PropertyValueFactory<>("thoiGianKetThuc"));
        mucDo.setCellValueFactory(new PropertyValueFactory<>("mucDo"));
        diaDiemCachLy.setCellValueFactory(new PropertyValueFactory<>("diaDiemCachLy"));
        cachLyTable.setItems(khaiBaoCachLy);

        ObservableList khaiBaoLoTrinh = FXCollections.observableList(khaiBaoLoTrinhModels);
        thoiGianBatDauLoTrinh.setCellValueFactory(new PropertyValueFactory<>("thoiGianBatDau"));
        thoiGianKetThucLoTrinh.setCellValueFactory(new PropertyValueFactory<>("thoiGianKetThuc"));
        diaDiem.setCellValueFactory(new PropertyValueFactory<>("diaDiem"));
        loTrinhTable.setItems(khaiBaoLoTrinh);

        khaiBaoLoTrinhModels = covidService.getKhaiBaoLoTrinhByID(id);

        khaiBaoSucKhoeModel = covidService.getKhaiBaoSucKhoeByID(id);
        nguoiTiepXuc.setText(khaiBaoSucKhoeModel.getNguoiTiepXuc());
        if (khaiBaoSucKhoeModel.getBn1()==1) bn1.setSelected(true);
    	if (khaiBaoSucKhoeModel.getBn2()==1) bn2.setSelected(true);
    	if (khaiBaoSucKhoeModel.getBn3()==1) bn3.setSelected(true);
    	if (khaiBaoSucKhoeModel.getBn4()==1) bn4.setSelected(true);
    	if (khaiBaoSucKhoeModel.getBn5()==1) bn5.setSelected(true);
    	if (khaiBaoSucKhoeModel.getBn6()==1) bn6.setSelected(true);
    	if (khaiBaoSucKhoeModel.getBn7()==1) bn7.setSelected(true);
    	if (khaiBaoSucKhoeModel.getTc1()==1) tc1.setSelected(true);
    	if (khaiBaoSucKhoeModel.getTc2()==1) tc2.setSelected(true);
    	if (khaiBaoSucKhoeModel.getTc3()==1) tc3.setSelected(true);
    	if (khaiBaoSucKhoeModel.getTc4()==1) tc4.setSelected(true);
    	if (khaiBaoSucKhoeModel.getTc5()==1) tc5.setSelected(true);
    }


    
    public void setData(TestCovidModel testCovidModel){
        ID.setText(String.valueOf(testCovidModel.getID()));
        hoTen.setText(testCovidModel.getHoTen());
        setDataChiTiet();
    }
}
