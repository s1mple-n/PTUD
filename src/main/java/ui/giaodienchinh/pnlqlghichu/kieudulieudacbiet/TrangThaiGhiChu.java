package ui.giaodienchinh.pnlqlghichu.kieudulieudacbiet;

public enum TrangThaiGhiChu {
    DA_XOA(-1),
    QUA_HAN(0),
    DA_HOAN_THANH(1),
    CHUA_HOAN_THANH(2);

    private int trangThaiGhiChu;

    TrangThaiGhiChu(int trangThaiGhiChu) {
        this.trangThaiGhiChu = trangThaiGhiChu;
    }

    public int getTrangThaiGhiChu() {
        return trangThaiGhiChu;
    }
}
