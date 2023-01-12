package models;

public class KhaiBaoSucKhoeModel {
    private int ID;
    private String hoTen;
    private String trieuChung;
    private String nguoiTiepXuc;
    private String tieuSuBenh;
    private int tc1;//Tc la trieu chung
    private int tc2;
    private int tc3;
    private int tc4;
    private int tc5;
    private int bn1;//Bn la benh nen
    private int bn2;
    private int bn3;
    private int bn4;
    private int bn5;
    private int bn6;
    private int bn7;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getNguoiTiepXuc() {
        return nguoiTiepXuc;
    }

    public void setNguoiTiepXuc(String nguoiTiepXuc) {
        this.nguoiTiepXuc = nguoiTiepXuc;
    }

    public String getTieuSuBenh() {
        return tieuSuBenh;
    }

    public void setTieuSuBenh(String tieuSuBenh) {
        this.tieuSuBenh = tieuSuBenh;
    }
    
    public void setTc1(int n) {
    	this.tc1=n;
    }
    
    public int getTc1() {
    	return tc1;
    }
    //
    public void setTc2(int n) {
    	this.tc2=n;
    }
    
    public int getTc2() {
    	return tc2;
    }
    //
    public void setTc3(int n) {
    	this.tc3=n;
    }
    
    public int getTc3() {
    	return tc3;
    }
    //
    public void setTc4(int n) {
    	this.tc4=n;
    }
    
    public int getTc4() {
    	return tc4;
    }
    //
    public void setTc5(int n) {
    	this.tc5=n;
    }
    
    public int getTc5() {
    	return tc5;
    }
    //
    public void setBn1(int n) {
    	this.bn1=n;
    }
    
    public int getBn1() {
    	return bn1;
    }
    //
    public void setBn2(int n) {
    	this.bn2=n;
    }
    
    public int getBn2() {
    	return bn2;
    }
    //
    public void setBn3(int n) {
    	this.bn3=n;
    }
    
    public int getBn3() {
    	return bn3;
    }
    //
    public void setBn4(int n) {
    	this.bn4=n;
    }
    
    public int getBn4() {
    	return bn4;
    }
    //
    public void setBn5(int n) {
    	this.bn5=n;
    }
    
    public int getBn5() {
    	return bn5;
    }
    //
    public void setBn6(int n) {
    	this.bn6=n;
    }
    
    public int getBn6() {
    	return bn6;
    }
    //
    public void setBn7(int n) {
    	this.bn7=n;
    }
    
    public int getBn7() {
    	return bn7;
    }
}
