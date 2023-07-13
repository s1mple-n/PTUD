package ui.giaodienchinh.pnllapthongke;

import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienPnlLapThongKe extends IDSBienMacDinh, IDSBienGDChinh {
    JPanel pnlLapTK = new JPanel();
    Dimension dimPnlLapTk = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JTabbedPane tabNoiDung = new JTabbedPane();
    Dimension dimTabNoiDung = new Dimension(
            dimPnlLapTk.width,
            dimPnlLapTk.height
    );

    Dimension dimPnlConCuaTabNoiDung = new Dimension(
            dimTabNoiDung.width,
            dimTabNoiDung.height
    );

    JPanel pnlThongKeDoanhThu = new JPanel();

    JTabbedPane tabThongKeDoanhThu = new JTabbedPane();

    JPanel pnlTKDTTheoHoaDonBanHang = new JPanel();

    /**
     *
     */

    JPanel pnlTKDTTheoNhanVien = new JPanel();

    /**
     *
     */

    JPanel pnlTKDTTheoKhachHang = new JPanel();

    /**
     *
     */

    JPanel pnlThongKeSanPham = new JPanel();
}
