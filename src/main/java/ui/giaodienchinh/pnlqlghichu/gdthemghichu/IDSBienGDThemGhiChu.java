package ui.giaodienchinh.pnlqlghichu.gdthemghichu;

import com.github.lgooddatepicker.components.TimePicker;
import services.CacHamDungSan;
import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDThemGhiChu extends IDSBienMacDinh {
    Dimension dimGDThemGhiChu = new Dimension(
        500, 520
    );

    int demTrai = 50;

    JPanel pnlChinh = new JPanel();

    JPanel pnlTieuDe = new JPanel();
    Dimension dimPnlTieuDe = new Dimension(
            dimGDThemGhiChu.width - demTrai * 2,
            60
    );

    JLabel lblTieuDe = new JLabel("Tạo ghi chú");
    Font fntLblTieuDe = new Font(tenFontMacDinh, Font.PLAIN, 30);
    Color frgLblTieuDe = new Color(17, 139, 150);

    JPanel pnlChuaBtn = new JPanel();
    Dimension dimPnlChuaBtn = new Dimension(
            dimGDThemGhiChu.width - demTrai * 2,
            70
    );
    JButton btnThemGhiChu = new JButton("Thêm ghi chú");

    JPanel pnlNoiDung = new JPanel();
    Dimension dimPnlNoiDung = new Dimension(
            dimGDThemGhiChu.width - demTrai * 2,
            dimGDThemGhiChu.height
            - dimPnlTieuDe.height
    );

    Dimension dimPnlChuDe = new Dimension(
            dimPnlNoiDung.width,
            90
    );

    Dimension dimPnlNoiDungGhiChu = new Dimension(
            dimPnlNoiDung.width,
            120
    );

    Dimension dimTxt = new Dimension(
            dimPnlNoiDung.width,
            48
    );

    Dimension dimTxa = new Dimension(
            dimPnlNoiDung.width,
            95
    );

    JPanel pnlChuDe = new JPanel();
    JLabel lblChuDe = new JLabel("Chủ đề");
    JTextField txtChuDe = new JTextField();

    JPanel pnlHanThucHien = new JPanel();
    JLabel lblHanThucHien = new JLabel("Hạn thực hiện");
    TimePicker tmpHanThucHien = CacHamDungSan.traVeTimePicker(dimTxt);

    JPanel pnlNoiDungGhiChu = new JPanel();
    JLabel lblNoiDungGhiChu = new JLabel("Nội dung");
    JTextArea txaNoiDungGhiChu = new JTextArea();
    JScrollPane scrNoiDungGhiChu = new JScrollPane(txaNoiDungGhiChu);
}
