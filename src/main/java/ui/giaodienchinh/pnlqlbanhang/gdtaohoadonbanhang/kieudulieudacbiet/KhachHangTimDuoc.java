package ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.kieudulieudacbiet;

public class KhachHangTimDuoc {
    private String sdt;
    private String hoTen;

    public KhachHangTimDuoc(String sdt, String hoTen) {
        this.sdt = sdt;
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Override
    public String toString() {
        return "KhachHangTimDuoc{" +
                "sdt='" + sdt + '\'' +
                ", hoTen='" + hoTen + '\'' +
                '}';
    }
}
