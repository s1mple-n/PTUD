package ui.giaodienchinh.pnlqlkhachhang.kieudulieudacbiet;

public class KhachHangTimDuoc {
    private int maKH;
    private String sdt;
    private String tenKH;

    public KhachHangTimDuoc(int maKH, String sdt, String tenKH) {
        this.maKH = maKH;
        this.sdt = sdt;
        this.tenKH = tenKH;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    @Override
    public String toString() {
        return "KhachHangTimDuoc{" +
                "maKH=" + maKH +
                ", sdt='" + sdt + '\'' +
                ", tenKH='" + tenKH + '\'' +
                '}';
    }
}
