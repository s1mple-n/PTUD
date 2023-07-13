package ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.giaodiencanhbaohuyhoadonnhaphang;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDCanhBaoHuyHoaDonNhapHang extends IDSBienMacDinh {
    String tieuDe = "Cảnh báo";

    Dimension dimGDCanhBaoHuyHoaDonNhapHang = new Dimension(
            400, 275
    );

    JPanel pnlChinh = new JPanel();

    Color bgrChuDao = new Color(207, 23, 118);

    JPanel pnlDanhDauMau = new JPanel();
    Dimension dimPnlDanhDauMau = new Dimension(
            5,
            dimGDCanhBaoHuyHoaDonNhapHang.height
            - 2
    );

    JPanel pnlNoiDung = new JPanel();
    Dimension dimPnlNoiDung = new Dimension(
            dimGDCanhBaoHuyHoaDonNhapHang.width
            - dimPnlDanhDauMau.width
            - 2,
            dimGDCanhBaoHuyHoaDonNhapHang.height
            - 2
    );

    JPanel pnlTieuDe = new JPanel();
    Dimension dimPnlTieuDe = new Dimension(
            dimPnlNoiDung.width
            - 40,
            80
    );

    JLabel lblTieuDe = new JLabel("Cảnh báo");
    Font fntLblTieuDe = new Font(tenFontMacDinh, Font.BOLD, 23);

    JPanel pnlChuaCacNut = new JPanel();
    Dimension dimPnlChuaCacNut = new Dimension(
        dimPnlTieuDe.width,
        80
    );

    Dimension dimBtn = new Dimension(
            150, 45
    );

    JButton btnHuyDon = new JButton("Huỷ đơn");

    JButton btnOLai = new JButton("Ở lại");

    JTextPane txpThongDiep = new JTextPane();
    Dimension dimTxpThongDiep = new Dimension(
            dimPnlTieuDe.width,
            dimPnlNoiDung.height
            - dimPnlTieuDe.height
            - dimPnlChuaCacNut.height
    );

    String thongDiep = "Bạn chắc chắn muốn huỷ hoá đơn nhập hàng này chứ? " +
            "Hãy cẩn thận vì khi đã thực hiện thì không thể hoàn tác.";

}
