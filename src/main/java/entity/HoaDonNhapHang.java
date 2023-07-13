package entity;

import java.time.LocalDateTime;
import java.util.List;

public class HoaDonNhapHang {
    private int maHDNH;
    private NhanVien nhanVienLapHDNH;
    private int maLoHang;
    private String tenNguoiGiaoHang;
    private LocalDateTime thoiGianDatHang;
    private LocalDateTime thoiGianGiaoHang;
    private List<ChiTietHoaDonNhapHang> danhSachChiTietHDNhapHang;
    private int tongSLSP;
    private double tongTien;

    public HoaDonNhapHang() {
    }

    public HoaDonNhapHang(int maHDNH) {
        this.maHDNH = maHDNH;
    }

    public HoaDonNhapHang(int maHDNH, NhanVien nhanVienLapHDNH, int maLoHang, String tenNguoiGiaoHang, LocalDateTime thoiGianDatHang, LocalDateTime thoiGianGiaoHang) {
        this.maHDNH = maHDNH;
        this.nhanVienLapHDNH = nhanVienLapHDNH;
        this.maLoHang = maLoHang;
        this.tenNguoiGiaoHang = tenNguoiGiaoHang;
        this.thoiGianDatHang = thoiGianDatHang;
        this.thoiGianGiaoHang = thoiGianGiaoHang;
        this.tongSLSP = getTongSLSP();
        this.tongTien = getTongTien();
    }

    public HoaDonNhapHang(int maHDNH, NhanVien nhanVienLapHDNH, int maLoHang, String tenNguoiGiaoHang, LocalDateTime thoiGianDatHang, LocalDateTime thoiGianGiaoHang, List<ChiTietHoaDonNhapHang> danhSachChiTietHDNhapHang) {
        this.maHDNH = maHDNH;
        this.nhanVienLapHDNH = nhanVienLapHDNH;
        this.maLoHang = maLoHang;
        this.tenNguoiGiaoHang = tenNguoiGiaoHang;
        this.thoiGianDatHang = thoiGianDatHang;
        this.thoiGianGiaoHang = thoiGianGiaoHang;
        this.danhSachChiTietHDNhapHang = danhSachChiTietHDNhapHang;
        this.tongSLSP = getTongSLSP();
        this.tongTien = getTongTien();
    }

    public HoaDonNhapHang(NhanVien nhanVienLapHDNH, int maLoHang, String tenNguoiGiaoHang, LocalDateTime thoiGianDatHang, LocalDateTime thoiGianGiaoHang, List<ChiTietHoaDonNhapHang> danhSachChiTietHDNhapHang) {
        this.nhanVienLapHDNH = nhanVienLapHDNH;
        this.maLoHang = maLoHang;
        this.tenNguoiGiaoHang = tenNguoiGiaoHang;
        this.danhSachChiTietHDNhapHang = danhSachChiTietHDNhapHang;
        this.thoiGianDatHang = thoiGianDatHang;
        this.thoiGianGiaoHang = thoiGianGiaoHang;
        this.tongSLSP = getTongSLSP();
        this.tongTien = getTongTien();
    }

    public int getMaHDNH() {
        return maHDNH;
    }

    public void setMaHDNH(int maHDNH) {
        this.maHDNH = maHDNH;
    }

    public NhanVien getNhanVienLapHDNH() {
        return nhanVienLapHDNH;
    }

    public void setNhanVienLapHDNH(NhanVien nhanVienLapHDNH) {
        this.nhanVienLapHDNH = nhanVienLapHDNH;
    }

    public int getMaLoHang() {
        return maLoHang;
    }

    public void setMaLoHang(int maLoHang) {
        this.maLoHang = maLoHang;
    }

    public String getTenNguoiGiaoHang() {
        return tenNguoiGiaoHang;
    }

    public void setTenNguoiGiaoHang(String tenNguoiGiaoHang) {
        this.tenNguoiGiaoHang = tenNguoiGiaoHang;
    }

    public LocalDateTime getThoiGianDatHang() {
        return thoiGianDatHang;
    }

    public void setThoiGianDatHang(LocalDateTime thoiGianDatHang) {
        this.thoiGianDatHang = thoiGianDatHang;
    }

    public LocalDateTime getThoiGianGiaoHang() {
        return thoiGianGiaoHang;
    }

    public void setThoiGianGiaoHang(LocalDateTime thoiGianGiaoHang) {
        this.thoiGianGiaoHang = thoiGianGiaoHang;
    }

    public List<ChiTietHoaDonNhapHang> getDanhSachChiTietHDNhapHang() {
        return danhSachChiTietHDNhapHang;
    }

    public void setDanhSachChiTietHDNhapHang(List<ChiTietHoaDonNhapHang> danhSachChiTietHDNhapHang) {
        this.danhSachChiTietHDNhapHang = danhSachChiTietHDNhapHang;
    }

    public int getTongSLSP() {
        int tongSLSP = danhSachChiTietHDNhapHang.stream().mapToInt(ChiTietHoaDonNhapHang::getSoLuongNhap).sum();

        return tongSLSP;
    }

    public void setTongSLSP(int tongSLSP) {
        this.tongSLSP = tongSLSP;
    }

    public double getTongTien() {
        double tongTien = danhSachChiTietHDNhapHang.stream().mapToDouble(ChiTietHoaDonNhapHang::getThanhTien).sum();

        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDonNhapHang{" +
                "maHDNH=" + maHDNH +
                ", nhanVienLapHDNH=" + nhanVienLapHDNH +
                ", maLoHang=" + maLoHang +
                ", tenNguoiGiaoHang='" + tenNguoiGiaoHang + '\'' +
                ", thoiGianDatHang=" + thoiGianDatHang +
                ", thoiGianGiaoHang=" + thoiGianGiaoHang +
                ", danhSachSanPham=" + danhSachChiTietHDNhapHang +
                ", tongSLSP=" + tongSLSP +
                ", tongTien=" + tongTien +
                '}';
    }
}
