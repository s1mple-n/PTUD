package entity;

import java.time.LocalDateTime;

public class NhatKiBanHangTheoCa {
    private int maNKBHTC;
    private NhanVien nhanVienLapNKBHTC;
    private LocalDateTime thoiGianLap;
    private int tongSLSPBanDuoc;
    private int tongSLSPConLai;
    private int tongSLSPMoiNhap;
    private double tongDoanhThu;

    public NhatKiBanHangTheoCa(int maNKBHTC, NhanVien nhanVienLapNKBHTC, LocalDateTime thoiGianLap, int tongSLSPBanDuoc, int tongSLSPConLai, int tongSLSPMoiNhap, double tongDoanhThu) {
        this.maNKBHTC = maNKBHTC;
        this.nhanVienLapNKBHTC = nhanVienLapNKBHTC;
        this.thoiGianLap = thoiGianLap;
        this.tongSLSPBanDuoc = tongSLSPBanDuoc;
        this.tongSLSPConLai = tongSLSPConLai;
        this.tongSLSPMoiNhap = tongSLSPMoiNhap;
        this.tongDoanhThu = tongDoanhThu;
    }

    public NhatKiBanHangTheoCa(NhanVien nhanVienLapNKBHTC, LocalDateTime thoiGianLap, int tongSLSPBanDuoc, int tongSLSPConLai, int tongSLSPMoiNhap, double tongDoanhThu) {
        this.nhanVienLapNKBHTC = nhanVienLapNKBHTC;
        this.thoiGianLap = thoiGianLap;
        this.tongSLSPBanDuoc = tongSLSPBanDuoc;
        this.tongSLSPConLai = tongSLSPConLai;
        this.tongSLSPMoiNhap = tongSLSPMoiNhap;
        this.tongDoanhThu = tongDoanhThu;
    }

    public NhatKiBanHangTheoCa() {

    }

    public NhatKiBanHangTheoCa(int maNKBHTC) {
        this.maNKBHTC = maNKBHTC;
    }

    public int getMaNKBHTC() {
        return maNKBHTC;
    }

    public void setMaNKBHTC(int maNKBHTC) {
        this.maNKBHTC = maNKBHTC;
    }

    public NhanVien getNhanVienLapNKBHTC() {
        return nhanVienLapNKBHTC;
    }

    public void setNhanVienLapNKBHTC(NhanVien nhanVienLapNKBHTC) {
        this.nhanVienLapNKBHTC = nhanVienLapNKBHTC;
    }

    public LocalDateTime getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(LocalDateTime thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public int getTongSLSPBanDuoc() {
        return tongSLSPBanDuoc;
    }

    public void setTongSLSPBanDuoc(int tongSLSPBanDuoc) {
        this.tongSLSPBanDuoc = tongSLSPBanDuoc;
    }

    public int getTongSLSPConLai() {
        return tongSLSPConLai;
    }

    public void setTongSLSPConLai(int tongSLSPConLai) {
        this.tongSLSPConLai = tongSLSPConLai;
    }

    public int getTongSLSPMoiNhap() {
        return tongSLSPMoiNhap;
    }

    public void setTongSLSPMoiNhap(int tongSLSPMoiNhap) {
        this.tongSLSPMoiNhap = tongSLSPMoiNhap;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    @Override
    public String toString() {
        return "NhatKiBanHangTheoCa{" +
                "maNKBHTC=" + maNKBHTC +
                ", nhanVienLapNKBHTC=" + nhanVienLapNKBHTC +
                ", thoiGianLap=" + thoiGianLap +
                ", tongSLSPBanDuoc=" + tongSLSPBanDuoc +
                ", tongSLSPConLai=" + tongSLSPConLai +
                ", tongSLSPMoiNhap=" + tongSLSPMoiNhap +
                ", tongDoanhThu=" + tongDoanhThu +
                '}';
    }
}
