package ui.giaodienchinh.pnlqlnhanvien.kieudulieudacbiet;

public class NhanVienTimDuoc {
    private String maNV;
    private boolean isCaSang;
    private String hoTen;
    private String sdt;

    public NhanVienTimDuoc(String maNV, boolean isCaSang, String hoTen, String sdt) {
        this.maNV = maNV;
        this.isCaSang = isCaSang;
        this.hoTen = hoTen;
        this.sdt = sdt;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public boolean isCaSang() {
        return isCaSang;
    }

    public void setCaSang(boolean caSang) {
        this.isCaSang = caSang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "NhanVienTimDuoc{" +
                "maNV='" + maNV + '\'' +
                ", caLam=" + isCaSang +
                ", hoTen='" + hoTen + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
