package entity;


public class TaiKhoan {
    private NhanVien nhanVien;
    private String tenDangNhap;
    private String matKhau;
    /**
     * - true: Da kich hoat - da doi mat khau lan dau
     * - False: Chua kich hoat - chua doi mat khau lan dau
     */
    private boolean isKichHoat;

    public TaiKhoan(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        this.tenDangNhap = nhanVien.getMaNV();
        this.matKhau = "1111";
        this.isKichHoat = false;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(NhanVien nhanVien, String tenDangNhap, String matKhau, boolean isKichHoat) {
        this.nhanVien = nhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.isKichHoat = isKichHoat;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = nhanVien.getMaNV();
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isKichHoat() {
        return isKichHoat;
    }

    public void setKichHoat(boolean kichHoat) {
        isKichHoat = kichHoat;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "nhanVien=" + nhanVien +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", isKichHoat=" + isKichHoat +
                '}';
    }
}
