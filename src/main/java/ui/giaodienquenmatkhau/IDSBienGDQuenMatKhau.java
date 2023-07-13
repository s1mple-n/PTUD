package ui.giaodienquenmatkhau;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDQuenMatKhau extends IDSBienMacDinh {
    String tieuDe = "Khôi phục mật khẩu";

    Dimension dimGDQuenMatKhau = new Dimension(
            400,
            550
    );

    int demTren = 30;
    int demTrai = 40;

    JPanel pnlChinh = new JPanel();

    JPanel pnlTieuDe = new JPanel();
    Dimension dimPnlTieuDe = new Dimension(
            dimGDQuenMatKhau.width - demTrai * 2,
            80
    );

    JLabel lblTieuDe = new JLabel(tieuDe);
    Font fntLblTieuDe = new Font(
            tenFontMacDinh,
            Font.PLAIN,
            27
    );
    Color frgLblTieuDe = new Color(30, 122, 141);

    JPanel pnlNoiDung = new JPanel();
    Dimension dimPnlNoiDung = new Dimension(
            dimGDQuenMatKhau.width - demTrai * 2,
            dimGDQuenMatKhau.height
            - demTren * 2
            - dimPnlTieuDe.height
    );

    Dimension dimTxtBtn = new Dimension(
            dimPnlNoiDung.width,
            48
    );

    JTextField txtMaNhanVien = new JTextField();
    String plhTxtMaNhanVien = "Mã nhân viên";

    JTextField txtSoCMND = new JTextField();
    String plhTxtSoCMND = "Số CMND";

    JTextField txtSoDienThoai = new JTextField();
    String plhTxtSoDienThoai = "Số điện thoại";

    JPasswordField pwfMatKhau = new JPasswordField();
    String plhPwfMatKhau = "Mật khẩu";

    JPasswordField pwfXacNhanMK = new JPasswordField();
    String plhPwfXacNhanMK = "Xác nhận mật khẩu";

    JButton btnXacNhan = new JButton("Xác nhận");
    Color bgrBtnXacNhan = new Color(30, 122, 141);
}
