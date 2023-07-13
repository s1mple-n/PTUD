package ui.giaodiendoimatkhau;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDDoiMatKhau extends IDSBienMacDinh {
    String tieuDe = "Đổi mật khẩu";

    Dimension dimGDDoiMatKhau = new Dimension(
            400, 430
    );

    int demTren = 10;
    int demTrai = 40;

    JPanel pnlChinh = new JPanel();

    JPanel pnlTieuDe = new JPanel();
    Dimension dimPnlTieuDe = new Dimension(
            dimGDDoiMatKhau.width - demTrai * 2,
            70
    );

    JLabel lblTieuDe = new JLabel("Đổi mật khẩu");
    Font fntLblTieuDe = new Font(
            tenFontMacDinh,
            Font.PLAIN,
            30
    );
    Color frgLblTieuDe = new Color(14, 129, 171);

    JPanel pnlNoiDung = new JPanel();
    Dimension dimPnlNoiDung = new Dimension(
            dimGDDoiMatKhau.width - demTrai * 2,
            dimGDDoiMatKhau.height
            - dimPnlTieuDe.height
            - demTren * 2
    );

    Dimension dimPwfBtn = new Dimension(
            dimPnlNoiDung.width,
            48
    );

    JPasswordField pwfMatKhauCu = new JPasswordField();
    String plhMatKhauCu = "Mật khẩu cũ";

    JPasswordField pwfMatKhauMoi = new JPasswordField();
    String plhMatKhauMoi = "Mật khẩu mới";

    JPasswordField pwfXacNhanMatKhauMoi = new JPasswordField();
    String plhXacNhanMatKhauMoi = "Xác nhận mật khẩu";

    JButton btnXacNhan = new JButton("Xác nhận");
    Color bgrBtnXacNhan = new Color(14, 129, 171);
}
