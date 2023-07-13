package entity;

import java.time.LocalDateTime;

public class PhieuDoiChung {
    private int maPhieuDoiChung;
    private NhanVien nhanVienLapPhieu;
    private NhanVien nhanVienKiemPhieu;
    private LocalDateTime thoiGianLapPhieu;
    private double soTienBanDau;
    private double soTienTrongKetHeThongTinh;
    private double soTienNguoiGiaoCaTinh;
    private double soTienNguoiNhanCaTinh;

    /**
     * @param maPhieuDoiChung:           Tự tăng bên SQL
     * @param nhanVienLapPhieu:          Hệ thống tự lấy
     * @param nhanVienKiemPhieu:         Người dùng nhập vào
     * @param thoiGianLapPhieu:          Hệ thống tự tính -> là ngày hôm nay
     * @param soTienBanDau:              Hê thống tự lấy lên từ DB PhieuDoiChung
     * @param soTienTrongKetHeThongTinh: Hệ thống lấy soTienBanDau - số tiền đã thối của các hóa đơn bán hàng
     * @param soTienNguoiGiaoCaTinh:     Người dùng nhập vào
     * @param soTienNguoiNhanCaTinh:     Người dùng nhập vào
     */
    public PhieuDoiChung(int maPhieuDoiChung, NhanVien nhanVienLapPhieu, NhanVien nhanVienKiemPhieu, LocalDateTime thoiGianLapPhieu, double soTienBanDau, double soTienTrongKetHeThongTinh, double soTienNguoiGiaoCaTinh, double soTienNguoiNhanCaTinh) {
        this.maPhieuDoiChung = maPhieuDoiChung;
        this.nhanVienLapPhieu = nhanVienLapPhieu;
        this.nhanVienKiemPhieu = nhanVienKiemPhieu;
        this.thoiGianLapPhieu = thoiGianLapPhieu;
        this.soTienBanDau = soTienBanDau;
        this.soTienTrongKetHeThongTinh = soTienTrongKetHeThongTinh;
        this.soTienNguoiGiaoCaTinh = soTienNguoiGiaoCaTinh;
        this.soTienNguoiNhanCaTinh = soTienNguoiNhanCaTinh;
    }

    public PhieuDoiChung(NhanVien nhanVienLapPhieu, NhanVien nhanVienKiemPhieu, LocalDateTime thoiGianLapPhieu, double soTienBanDau, double soTienTrongKetHeThongTinh, double soTienNguoiGiaoCaTinh, double soTienNguoiNhanCaTinh) {
        this.nhanVienLapPhieu = nhanVienLapPhieu;
        this.nhanVienKiemPhieu = nhanVienKiemPhieu;
        this.thoiGianLapPhieu = thoiGianLapPhieu;
        this.soTienBanDau = soTienBanDau;
        this.soTienTrongKetHeThongTinh = soTienTrongKetHeThongTinh;
        this.soTienNguoiGiaoCaTinh = soTienNguoiGiaoCaTinh;
        this.soTienNguoiNhanCaTinh = soTienNguoiNhanCaTinh;
    }

    public PhieuDoiChung() {
    }

    public PhieuDoiChung(int maPhieuDoiChung) {
        this.maPhieuDoiChung = maPhieuDoiChung;
    }

    public int getMaPhieuDoiChung() {
        return maPhieuDoiChung;
    }

    public NhanVien getNhanVienLapPhieu() {
        return nhanVienLapPhieu;
    }

    public NhanVien getNhanVienKiemPhieu() {
        return nhanVienKiemPhieu;
    }

    public void setNhanVienKiemPhieu(NhanVien nhanVienKiemPhieu) {
        this.nhanVienKiemPhieu = nhanVienKiemPhieu;
    }

    public LocalDateTime getThoiGianLapPhieu() {
        return thoiGianLapPhieu;
    }

    public double getSoTienBanDau() {
        return soTienBanDau;
    }

    public double getSoTienTrongKetHeThongTinh() {
        return soTienTrongKetHeThongTinh;
    }

    public double getSoTienNguoiGiaoCaTinh() {
        return soTienNguoiGiaoCaTinh;
    }

    public void setSoTienNguoiGiaoCaTinh(double soTienNguoiGiaoCaTinh) {
        this.soTienNguoiGiaoCaTinh = soTienNguoiGiaoCaTinh;
    }

    public double getSoTienNguoiNhanCaTinh() {
        return soTienNguoiNhanCaTinh;
    }

    public void setSoTienNguoiNhanCaTinh(double soTienNguoiNhanCaTinh) {
        this.soTienNguoiNhanCaTinh = soTienNguoiNhanCaTinh;
    }

    @Override
    public String toString() {
        return "PhieuDoiChung{" +
                "maPhieuDoiChung=" + maPhieuDoiChung +
                ", nhanVienLapPhieu=" + nhanVienLapPhieu +
                ", nhanVienKiemPhieu=" + nhanVienKiemPhieu +
                ", thoiGianLapPhieu=" + thoiGianLapPhieu +
                ", soTienBanDau=" + soTienBanDau +
                ", soTienTrongKetHeThongTinh=" + soTienTrongKetHeThongTinh +
                ", soTienNguoiGiaoCaTinh=" + soTienNguoiGiaoCaTinh +
                ", soTienNguoiNhanCaTinh=" + soTienNguoiNhanCaTinh +
                '}';
    }
}
