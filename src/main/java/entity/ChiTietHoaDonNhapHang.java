package entity;

public class ChiTietHoaDonNhapHang {
    private SanPham sanPham;
    private int soLuongNhap;
    private double donGiaNhap;
    /**
     * - Thuoc tinh dan xuat - Derived Property
     */
    private double thanhTien;

    public ChiTietHoaDonNhapHang(SanPham sanPham, int soLuongNhap, double donGiaNhap, double thanhTien) {
        this.sanPham = sanPham;
        this.soLuongNhap = soLuongNhap;
        this.donGiaNhap = donGiaNhap;
        this.thanhTien = thanhTien;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(double donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public double getThanhTien() {
        return this.soLuongNhap * this.donGiaNhap;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonNhapHang{" +
                "sanPham=" + sanPham +
                ", soLuongNhap=" + soLuongNhap +
                ", donGiaNhap=" + donGiaNhap +
                ", thanhTien=" + thanhTien +
                '}';
    }
}
