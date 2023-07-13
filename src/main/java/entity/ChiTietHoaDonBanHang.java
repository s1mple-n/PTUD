package entity;

public class ChiTietHoaDonBanHang {
    private SanPham sanPham;
    private int soLuongBan;
    private double donGiaBan;
    /**
     * - Thuoc tinh dan xuat - Derived Property
     */
    private double thanhTien;

    public ChiTietHoaDonBanHang(SanPham sanPham, int soLuongBan, double donGiaBan, double thanhTien) {
        this.sanPham = sanPham;
        this.soLuongBan = soLuongBan;
        this.donGiaBan = donGiaBan;
        this.thanhTien = thanhTien;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public double getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(double donGiaBan) {
        this.donGiaBan = donGiaBan;
    }

    public double getThanhTien() {
        return this.soLuongBan * this.donGiaBan;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonBanHang{" +
                "sanPham=" + sanPham +
                ", soLuongBan=" + soLuongBan +
                ", donGiaBan=" + donGiaBan +
                ", thanhTien=" + thanhTien +
                '}';
    }
}
