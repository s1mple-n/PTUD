package ui.giaodienchinh.giaodiencanhbaodangxuat;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDCanhBaoDangXuat extends IDSBienMacDinh {
    String tieuDe = "Cảnh báo đăng xuất";
    Dimension dimGDCanhBaoDangXuat = new Dimension(500, 450);

    int demTren = 30;
    int demTrai = 50;

    JPanel pnlChinh = new JPanel();

    JPanel pnlNoiDung = new JPanel();
    Dimension dimPnlNoiDung = new Dimension(
            dimGDCanhBaoDangXuat.width - demTrai * 2,
            dimGDCanhBaoDangXuat.height - demTren * 2
    );

    JPanel pnlTieuDe = new JPanel();
    Dimension dimPnlTieuDe = new Dimension(
            dimPnlNoiDung.width,
            40
    );
    JLabel lblTieuDe = new JLabel("Bạn thật sự muốn đăng xuất?");
    Font fntLblTieuDe = new Font(tenFontMacDinh, Font.PLAIN, 25);

    JPanel pnlBieuTuong = new JPanel();
    Dimension dimPnlBieuTuong = new Dimension(
            dimPnlNoiDung.width,
            115
    );
    JLabel lblBieuTuong = new JLabel(
        new ImageIcon("src/main/resources/BieuTuong/SadMeo.png")
    );

    JPanel pnlThongDiep = new JPanel();
    Dimension dimPnlThongDiep = new Dimension(
            dimPnlNoiDung.width,
            130
    );
    Color bgrPnlThongDiep = new Color(255,232,217);

    JPanel pnlDanhDauMau = new JPanel();
    Dimension dimPnlDanhDauMau = new Dimension(
        5,
            dimPnlThongDiep.height
    );
    Color bgrPnlDanhDauMau = new Color(249,117,68);

    JPanel pnlBieuTuongCanhBao = new JPanel();
    Dimension dimPnlBieuTuongCanhBao = new Dimension(
            70,
            dimPnlThongDiep.height
    );
    JLabel lblBieuTuongCanhBao = new JLabel(
            new ImageIcon("src/main/resources/BieuTuong/Warning_24px_1.png")
    );

    JPanel pnlHienThiThongDiep = new JPanel();
    Dimension dimPnlHienThiThongDiep = new Dimension(
              dimPnlThongDiep.width
            - dimPnlDanhDauMau.width
            - dimPnlBieuTuongCanhBao.width,
            dimPnlThongDiep.height
    );

    JLabel lblTieuDeCanhBao = new JLabel("Nhắc nhở");
    Color frgLblTieuDeCanhBao = new Color(164,72,58);
    Font fntLblTieuDeCanhBao = new Font(tenFontMacDinh, Font.BOLD, 18);

    JTextPane txpThongDiepCanhBao = new JTextPane();
    Dimension dimTxpThongDiepCanhBao = new Dimension(
            dimPnlHienThiThongDiep.width
            - 10,
            dimPnlHienThiThongDiep.height - 50
    );
    Font fntTxpThongDiepCanhBao = new Font(tenFontMacDinh, Font.PLAIN, 16);
    Color frgTxpThongDiepCanhBao = new Color(216,102,76);
    String thongDiepCanhBao = "Hãy chắc rằng bạn đã lập phiếu đối chứng, nhật kí bán hàng " +
            "và thực hiện các ghi chú chưa hoàn thành trước khi đăng xuất.";

    JPanel pnlChuaCacNutDieuHuong = new JPanel();
    Dimension dimPnlChuaCacNutDieuHuong = new Dimension(
            dimPnlNoiDung.width,
            dimPnlNoiDung.height
            - dimPnlTieuDe.height
            - dimPnlBieuTuong.height
            - dimPnlThongDiep.height
    );

    Dimension dimBtn = new Dimension(
            dimPnlChuaCacNutDieuHuong.width / 2 - 10,
            48
    );

    JButton btnDangXuat = new JButton("Đăng xuất");
    Color bgrBtnDangXuat = new Color(193, 201, 199);
    Color bgrBtnDangXuatKhiHover = new Color(199, 24, 88);

    JButton btnOLai = new JButton("Ở lại");
    Color bgrBtnOLai = new Color(18, 167, 184);

}
