package ui.giaodienchinh.pnlqlghichu.kieudulieudacbiet;

import java.sql.Time;

public class GhiChu {
    private String chuDe;
    private Time hanThucHien;
    private String noiDungGhiChu;
    private TrangThaiGhiChu trangThaiGhiChu;

    public GhiChu(String chuDe, Time hanThucHien, String noiDungGhiChu) {
        this.chuDe = chuDe;
        this.hanThucHien = hanThucHien;
        this.noiDungGhiChu = noiDungGhiChu;
        this.trangThaiGhiChu = TrangThaiGhiChu.CHUA_HOAN_THANH;
    }

    public String getChuDe() {
        return chuDe;
    }

    public void setChuDe(String chuDe) {
        this.chuDe = chuDe;
    }

    public Time getHanThucHien() {
        return hanThucHien;
    }

    public void setHanThucHien(Time hanThucHien) {
        this.hanThucHien = hanThucHien;
    }

    public String getNoiDungGhiChu() {
        return noiDungGhiChu;
    }

    public void setNoiDungGhiChu(String noiDungGhiChu) {
        this.noiDungGhiChu = noiDungGhiChu;
    }

    public TrangThaiGhiChu getTrangThaiGhiChu() {
        return trangThaiGhiChu;
    }

    public void setTrangThaiGhiChu(TrangThaiGhiChu trangThaiGhiChu) {
        this.trangThaiGhiChu = trangThaiGhiChu;
    }

    @Override
    public String toString() {
        return "GhiChu{" +
                ", chuDe='" + chuDe + '\'' +
                ", hanThucHien=" + hanThucHien +
                ", noiDungGhiChu='" + noiDungGhiChu + '\'' +
                ", trangThaiGhiChu=" + trangThaiGhiChu +
                '}';
    }
}
