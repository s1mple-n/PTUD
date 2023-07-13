package ui.giaodienthongbaongoaivi;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDThongBaoKetQua extends IDSBienMacDinh {
    String tieuDe = "Thông báo";

    Dimension dimGDThongBao = new Dimension(
            400, 300
    );

    JPanel pnlChinh = new JPanel();

    JPanel pnlDanhDauMau = new JPanel();
    Dimension dimPnlDanhDauMau = new Dimension(
            dimGDThongBao.width,
            10
    );

    JPanel pnlHienThiBieuTuong = new JPanel();
    Dimension dimPnlHienThiBieuTuong = new Dimension(
            dimGDThongBao.width,
            70
    );

    Color bgrThatBai = new Color(219, 35, 93);
    String pathIcnThatBai = "src/main/resources/BieuTuong/404Error_48px_1.png";

    Color bgrBinhThuong = new Color(66,195,207);
    String pathIcnBinhThuong = "src/main/resources/BieuTuong/Clock_48px_1.png";

    Color bgrThanhCong = new Color(36, 181, 142);
    String pathIcnThanhCong = "src/main/resources/BieuTuong/Success_48px_1.png";

    JLabel lblBieuTuong = new JLabel();

    JPanel pnlHienThiTieuDe = new JPanel();
    Dimension dimPnlHienThiTieuDe = new Dimension(
        dimGDThongBao.width,
        dimPnlHienThiBieuTuong.height
    );

    String tieuDeThatBai = "Chà! Không được rồi.";
    String tieuDeBinhThuong = "Để ý xíu nè.";
    String tieuDeThanhCong = "Thành công";

    JLabel lblTieuDe = new JLabel();
    Font fntLblTieuDe = new Font(
            tenFontMacDinh,
            Font.BOLD,
            25
    );

    JPanel pnlHienThiThongBao = new JPanel();
    Dimension dimPnlHienThiThongBao = new Dimension(
            dimGDThongBao.width,
            80
    );

    JTextPane txpHienThiNoiDungThongBao = new JTextPane();
    Dimension dimTxpHienThiNoiDungThongBao = new Dimension(
            dimGDThongBao.width - 20,
            dimPnlHienThiThongBao.height
    );

    JPanel pnlChuaBtnXacNhan = new JPanel();
    Dimension dimPnlChuaBtnXacNhan = new Dimension(
            dimGDThongBao.width,
            dimGDThongBao.height
            - dimPnlDanhDauMau.height
            - dimPnlHienThiBieuTuong.height
            - dimPnlHienThiThongBao.height
    );

    JButton btnXacNhan = new JButton("Đã hiểu");
    Dimension dimBtnXacNhan = new Dimension(
            150, 45
    );
    Font fntBtnXacNhan = new Font(
            tenFontMacDinh,
            Font.BOLD,
            17
    );
}
