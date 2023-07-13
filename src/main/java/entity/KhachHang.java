package entity;

import java.time.LocalDateTime;

public class KhachHang {
    private int maKH;
    private String hoTen;
    private String soDT;
    private String diaChi;
    /**
     * - True: Nam
     * - False: Nu
     */
    private boolean isNam = true;
    private LocalDateTime ngayThem;

    public KhachHang() {
    }

    public KhachHang(int maKH) {
        this.maKH = maKH;
    }

    public KhachHang(int maKH, String hoTen, String soDT, String diaChi, boolean isNam, LocalDateTime ngayThem) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.isNam = isNam;
        this.ngayThem = ngayThem;
    }

    public KhachHang(String hoTen, String soDT, String diaChi, boolean isNam) {
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.isNam = isNam;
        this.ngayThem = LocalDateTime.now();
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isNam() {
        return isNam;
    }

    public void setNam(boolean nam) {
        this.isNam = nam;
    }

    public LocalDateTime getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(LocalDateTime ngayThem) {
        this.ngayThem = ngayThem;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH=" + maKH +
                ", hoTen='" + hoTen + '\'' +
                ", soDT='" + soDT + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh=" + ((isNam) ? "Nam" : "Nữ") +
                ", ngayThem=" + ngayThem +
                '}';
    }
}
