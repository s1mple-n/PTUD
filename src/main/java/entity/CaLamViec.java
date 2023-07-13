package entity;

import java.sql.Time;

public class CaLamViec {
    /**
     * - True: Ca sang
     * - False: Ca toi
     */
    private boolean isCaSang;
    private String tenCaLam;
    private Time gioBatDauCa;
    private Time gioKetThucCa;

    public CaLamViec(boolean isCaSang, String tenCaLam, Time gioBatDauCa, Time gioKetThucCa) {
        this.isCaSang = isCaSang;
        this.tenCaLam = tenCaLam;
        this.gioBatDauCa = gioBatDauCa;
        this.gioKetThucCa = gioKetThucCa;
    }

    public CaLamViec() {
    }

    public CaLamViec(boolean isCaSang) {
        this.isCaSang = isCaSang;
    }

    public String getTenCaLam() {
        String tenCaLam = (isCaSang == true) ? "Sáng" : "Tối";
        return tenCaLam;
    }

    public boolean isCaSang() {
        return isCaSang;
    }

    public void setMaCa(boolean maCa) {
        this.isCaSang = maCa;
    }

    public Time getGioBatDauCa() {
        return gioBatDauCa;
    }

    public void setGioBatDauCa(Time gioBatDauCa) {
        this.gioBatDauCa = gioBatDauCa;
    }

    public Time getGioKetThucCa() {
        return gioKetThucCa;
    }

    public void setGioKetThucCa(Time gioKetThucCa) {
        this.gioKetThucCa = gioKetThucCa;
    }

    @Override
    public String toString() {
        return "CaLamViec{" +
                "isCaSang=" + isCaSang +
                ", tenCaLam='" + tenCaLam + '\'' +
                ", gioBatDauCa=" + gioBatDauCa +
                ", gioKetThucCa=" + gioKetThucCa +
                '}';
    }
}
