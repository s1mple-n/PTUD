package ui.giaodiendangnhap;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDDangNhap extends IDSBienMacDinh {
    String tieuDe = "Đăng nhập";
    Dimension dimGDDangNhap = new Dimension(
        500, 460
    );

    int demTrai = 50;
    int demTren = 40;

    JPanel pnlChinh = new JPanel();

    JPanel pnlTieuDe = new JPanel();
    Dimension dimPnlTieuDe = new Dimension(
            dimGDDangNhap.width - demTrai * 2,
            110
    );

    JLabel lblTieuDeChinh = new JLabel(
            "Chào mừng trở lại!"
    );
    Font fntLblTieuDeChinh = new Font(
            tenFontMacDinh,
            Font.PLAIN,
            33
    );
    Color frgLblTieuDeChinh = new Color(42, 78, 105);

    JLabel lblTieuDePhu = new JLabel(
            "Điền thông tin đăng nhập của bạn để tiếp tục"
    );
    Font fntLblTieuDePhu = new Font(
            tenFontMacDinh,
            Font.PLAIN,
            18
    );
    Color frgLblTieuDePhu = new Color(150, 150, 150);

    JPanel pnlNoiDung = new JPanel();
    Dimension dimPnlNoiDung = new Dimension(
            dimGDDangNhap.width - demTrai * 2,
            dimGDDangNhap.height
            - dimPnlTieuDe.height
            - demTren * 2
    );

    Dimension dimTxtTenDangNhapPwfMatKhauBtn = new Dimension(
            dimPnlNoiDung.width,
            48
    );

    JTextField txtTenDangNhap = new JTextField();
    String plhTxtTenDangNhap = "Tên đăng nhập";

    JPasswordField pwfMatKhau = new JPasswordField();
    String plhPwfMatKhau = "Mật khẩu";

    JButton btnDangNhap = new JButton(
            "Đăng nhập",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Login_24px_1.png")
            )
    );

    Color bgrBtnDangNhap = new Color(30, 122, 141);
    Color bgrBtnDangNhapKhiHover = new Color(36, 179, 105);

    JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu");
    Font fntLblQuenMatKhau = new Font(
            tenFontMacDinh,
            Font.PLAIN,
            15
    );
}
