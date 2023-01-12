package services;

import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CovidService {

    public List<TestCovidModel> getListTestCovid(){
        List<TestCovidModel> list = new ArrayList<>();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM testcovid";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                TestCovidModel testCovidModel = new TestCovidModel();
                testCovidModel.setID(rs.getInt("ID"));
                testCovidModel.setHoTen(rs.getString("hoTen"));
                testCovidModel.setThoiGianTest(rs.getString("thoiGianTest"));
                testCovidModel.setKetQuaTest(rs.getString("ketQuaTest"));
                testCovidModel.setHinhThucTest(rs.getString("hinhThucTest"));
                list.add(testCovidModel);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean addNew(TestCovidModel testCovidModel){
        int ID = testCovidModel.getID();
        String hoTen = testCovidModel.getHoTen();
        String thoiGianTest = testCovidModel.getThoiGianTest();
        String ketQuaTest = testCovidModel.getKetQuaTest();
        String hinhThucTest = testCovidModel.getHinhThucTest();
        String mucDo = testCovidModel.getMucDo();
        if (thoiGianTest.isBlank() || ketQuaTest.isBlank() || hinhThucTest.isBlank()){
            try{
                Connection connection = MysqlConnection.getMysqlConnection();
                Statement statement = connection.createStatement();
                String query = "INSERT INTO `QuanLyNhanKhau`.`testcovid` (`ID`, `hoTen`) VALUES ('" + ID + "', '" + hoTen + "');";
                statement.executeUpdate(query);
                return true;
            } catch(Exception e){
                e.printStackTrace();
                return false;
            }
        } else if (!thoiGianTest.isBlank() && !ketQuaTest.isBlank() && !hinhThucTest.isBlank()){
            try{
                Connection connection = MysqlConnection.getMysqlConnection();
                Statement statement = connection.createStatement();
                String query = "INSERT INTO `QuanLyNhanKhau`.`testcovid` (`ID`, `hoTen`, `thoiGianTest`, `ketQuaTest`, `hinhThucTest`,`mucDo`) VALUES ('" + ID + "', '" + hoTen + "' , '" + thoiGianTest + "', '" + ketQuaTest + "', '" + hinhThucTest + "', '"+ mucDo +  "')";
                statement.executeUpdate(query);
                return true;
            } catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void themThongTinCachLy(KhaiBaoCachLyModel khaiBaoCachLyModel) {
        int ID = khaiBaoCachLyModel.getID();
        String hoTen = khaiBaoCachLyModel.getHoTen();
        String thoiGianBatDau = khaiBaoCachLyModel.getThoiGianBatDau();
        String thoiGianKetThuc = khaiBaoCachLyModel.getThoiGianKetThuc();
        String mucDo = khaiBaoCachLyModel.getMucDo();
        String diaDiemCachLy = khaiBaoCachLyModel.getDiaDiemCachLy();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();

            String query = "INSERT INTO `QuanLyNhanKhau`.`khai_bao_canh_ly` (`ID`, `hoTen`, `thoiGianBatDau`, `thoiGianKetThuc`, `mucDo`, `diaDiemCachLy`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, hoTen);
            preparedStatement.setString(3, thoiGianBatDau);
            preparedStatement.setString(4, thoiGianKetThuc);
            preparedStatement.setString(5, mucDo);
            preparedStatement.setString(6, diaDiemCachLy);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void themThongTinLoTrinh(KhaiBaoLoTrinhModel khaiBaoLoTrinhModel) {
        int ID = khaiBaoLoTrinhModel.getID();
        String hoTen = khaiBaoLoTrinhModel.getHoTen();
        String thoiGianBatDau = khaiBaoLoTrinhModel.getThoiGianBatDau();
        String thoiGianKetThuc = khaiBaoLoTrinhModel.getThoiGianKetThuc();
        String diaDiem = khaiBaoLoTrinhModel.getDiaDiem();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "INSERT INTO khai_bao_lo_trinh VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, hoTen);
            preparedStatement.setString(3, thoiGianBatDau);
            preparedStatement.setString(4, thoiGianKetThuc);
            preparedStatement.setString(5, diaDiem);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void themThongTinSucKhoe(KhaiBaoSucKhoeModel khaiBaoSucKhoeModel){
        int ID = khaiBaoSucKhoeModel.getID();
        String hoTen = khaiBaoSucKhoeModel.getHoTen();
        String nguoiTiepXuc = khaiBaoSucKhoeModel.getNguoiTiepXuc();

        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "INSERT INTO khai_bao_suc_khoe VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, hoTen);
            preparedStatement.setString(3, nguoiTiepXuc);
            preparedStatement.setInt(4, khaiBaoSucKhoeModel.getTc1());
            preparedStatement.setInt(5, khaiBaoSucKhoeModel.getTc2());
            preparedStatement.setInt(6, khaiBaoSucKhoeModel.getTc3());
            preparedStatement.setInt(7, khaiBaoSucKhoeModel.getTc4());
            preparedStatement.setInt(8, khaiBaoSucKhoeModel.getTc5());
            preparedStatement.setInt(9,  khaiBaoSucKhoeModel.getBn1());
            preparedStatement.setInt(10, khaiBaoSucKhoeModel.getBn2());
            preparedStatement.setInt(11, khaiBaoSucKhoeModel.getBn3());
            preparedStatement.setInt(12, khaiBaoSucKhoeModel.getBn4());
            preparedStatement.setInt(13, khaiBaoSucKhoeModel.getBn5());
            preparedStatement.setInt(14, khaiBaoSucKhoeModel.getBn6());
            preparedStatement.setInt(15, khaiBaoSucKhoeModel.getBn7());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getCMTfromID(int ID){
        String soCMT = null;
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String idString = String.valueOf(ID);
            String query = "SELECT * FROM chung_minh_thu WHERE idNhanKhau = '" + idString + "'";
            ResultSet rs = connection.createStatement().executeQuery(query);
            if(rs.next()){
                soCMT = rs.getString("soCMT");

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return soCMT;
    }

    public List<KhaiBaoCachLyModel> getKhaiBaoCachLyByID(int ID){
        List<KhaiBaoCachLyModel> list = new ArrayList<>();
        try{
            Connection  connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM QuanLyNhanKhau.khai_bao_canh_ly WHERE ID = '" + ID + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                KhaiBaoCachLyModel khaiBaoCachLyModel = new KhaiBaoCachLyModel();
                khaiBaoCachLyModel.setID(rs.getInt("ID"));
                khaiBaoCachLyModel.setHoTen(rs.getString("hoTen"));
                khaiBaoCachLyModel.setThoiGianBatDau(rs.getString("thoiGianBatDau"));
                khaiBaoCachLyModel.setThoiGianKetThuc(rs.getString("thoiGianKetThuc"));
                khaiBaoCachLyModel.setMucDo(rs.getString("mucDo"));
                khaiBaoCachLyModel.setDiaDiemCachLy(rs.getString("diaDiemCachLy"));
                list.add(khaiBaoCachLyModel);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<KhaiBaoLoTrinhModel> getKhaiBaoLoTrinhByID(int ID){
        List<KhaiBaoLoTrinhModel> list = new ArrayList<>();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM khai_bao_lo_trinh WHERE ID = '" + ID + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                KhaiBaoLoTrinhModel khaiBaoLoTrinhModel = new KhaiBaoLoTrinhModel();
                khaiBaoLoTrinhModel.setID(rs.getInt("ID"));
                khaiBaoLoTrinhModel.setHoTen(rs.getString("hoTen"));
                khaiBaoLoTrinhModel.setThoiGianBatDau(rs.getString("thoiGianBatDau"));
                khaiBaoLoTrinhModel.setThoiGianKetThuc(rs.getString("thoiGianKetThuc"));
                khaiBaoLoTrinhModel.setDiaDiem(rs.getString("diaDiem"));
                list.add(khaiBaoLoTrinhModel);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public KhaiBaoSucKhoeModel getKhaiBaoSucKhoeByID(int ID){
        KhaiBaoSucKhoeModel khaiBaoSucKhoeModel = new KhaiBaoSucKhoeModel();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM khai_bao_suc_khoe WHERE ID = '" + ID + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                khaiBaoSucKhoeModel.setID(rs.getInt("ID"));
                khaiBaoSucKhoeModel.setHoTen(rs.getString("hoTen"));
                khaiBaoSucKhoeModel.setNguoiTiepXuc(rs.getString("nguoiTiepXuc"));
                khaiBaoSucKhoeModel.setTc1(rs.getInt("ho"));
                khaiBaoSucKhoeModel.setTc2(rs.getInt("sot"));
                khaiBaoSucKhoeModel.setTc3(rs.getInt("khotho"));
                khaiBaoSucKhoeModel.setTc4(rs.getInt("dauhong"));
                khaiBaoSucKhoeModel.setTc5(rs.getInt("khongtrieuchung"));
                khaiBaoSucKhoeModel.setBn1(rs.getInt("gan"));
                khaiBaoSucKhoeModel.setBn2(rs.getInt("than"));
                khaiBaoSucKhoeModel.setBn3(rs.getInt("phoi"));
                khaiBaoSucKhoeModel.setBn4(rs.getInt("timmach"));
                khaiBaoSucKhoeModel.setBn5(rs.getInt("caohuyetap"));
                khaiBaoSucKhoeModel.setBn6(rs.getInt("tieuduong"));
                khaiBaoSucKhoeModel.setBn7(rs.getInt("khongcobenhmantinh"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return khaiBaoSucKhoeModel;
    }

}
