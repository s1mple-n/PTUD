package entity;

public class SanPham {
    private String maSP;
    private String tenSP;
    private String mauSac;
    private String size;
    private String chatLieu;
    private double donGia;
    private String thuongHieu;
    private String nguonGoc;
    private int soLuongTon;
    private String moTa;

    public SanPham(String maSP) {
        this.maSP = maSP;
    }

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public SanPham(String maSP, String tenSP, String mauSac, String size, String chatLieu, double donGia, String thuongHieu, String nguonGoc, int soLuongTon, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.mauSac = mauSac;
        this.size = size;
        this.chatLieu = chatLieu;
        this.donGia = donGia;
        this.thuongHieu = thuongHieu;
        this.nguonGoc = nguonGoc;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
    }


    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getNguonGoc() {
        return nguonGoc;
    }

    public void setNguonGoc(String nguonGoc) {
        this.nguonGoc = nguonGoc;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", mauSac='" + mauSac + '\'' +
                ", size='" + size + '\'' +
                ", chatLieu='" + chatLieu + '\'' +
                ", donGia=" + donGia +
                ", thuongHieu='" + thuongHieu + '\'' +
                ", nguonGoc='" + nguonGoc + '\'' +
                ", soLuongTon=" + soLuongTon +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
