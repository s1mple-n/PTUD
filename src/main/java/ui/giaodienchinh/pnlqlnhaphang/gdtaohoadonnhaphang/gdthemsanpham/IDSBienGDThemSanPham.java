package ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.gdthemsanpham;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public interface IDSBienGDThemSanPham {
    String tenFont = "Sans Serif";
    Color frgMacDinh = Color.BLACK;
    Color mauNenMacDinh = new Color(215, 239, 245);
    Dimension dimGDXemTTSanPham = new Dimension(500, 600);
    Color mauKhungVien = new Color(224, 224, 224);
    String pathLogo = "src/main/resources/BieuTuong/Logo2.jpg";
    Font fontChinh = new Font(tenFont, Font.PLAIN, 17);

    JPanel pnlChinh = new JPanel();

    JPanel panelChuaBtn = new JPanel();
    Dimension kichThuocPanelChuaBtn = new Dimension(dimGDXemTTSanPham.width, 65);

    Dimension dimBtn = new Dimension(
            dimGDXemTTSanPham.width / 2 - 20,
            45
    );

    Color mauNenBtnThemSanPham = new Color(12, 132, 150);
    JButton btnThemSanPham = new JButton("Thêm");

    JButton btnThoat = new JButton("Thoát");
    Color mauChuBtn = Color.white;

    JPanel pnlHienThiTTSanPham = new JPanel();
    Dimension dimPnlHienThiTTSanPham = new Dimension(
            dimGDXemTTSanPham.width,
            dimGDXemTTSanPham.height - kichThuocPanelChuaBtn.height
    );

    Dimension dimPnlConLoai1 = new Dimension(
            dimPnlHienThiTTSanPham.width - 30,
            70
    );
    Dimension dimTxtLoai1 = new Dimension(
            dimPnlConLoai1.width,
            40
    );

    JPanel pnlTenSanPham = new JPanel();
    JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
    JTextField txtTenSanPham = new JTextField();

    JPanel pnlChuaMaSPVaMauSac = new JPanel();

    Dimension dimPnlConLoai2 = new Dimension(
            dimPnlHienThiTTSanPham.width / 2 - 20,
            dimPnlConLoai1.height
    );
    Dimension dimTxtLoai2 = new Dimension(
            dimPnlConLoai2.width - 2,
            40
    );

    JPanel pnlMaSP = new JPanel();
    JLabel lblMaSP = new JLabel("Mã sản phẩm:");
    JTextField txtMaSP = new JTextField();

    JPanel pnlMauSac = new JPanel();
    JLabel lblMauSac = new JLabel("Màu sắc:");
    JTextField txtMauSac = new JTextField();

    JPanel pnlChuaSizeVaChatLieu = new JPanel();

    JPanel pnlSize = new JPanel();
    JLabel lblSize = new JLabel("Size:");
    JTextField txtSize = new JTextField();

    JPanel pnlChatLieu = new JPanel();
    JLabel lblChatLieu = new JLabel("Chất liệu:");
    JTextField txtChatLieu = new JTextField();

    JPanel pnlChuaThuongHieuVaNguonGoc = new JPanel();

    JPanel pnlThuongHieu = new JPanel();
    JLabel lblThuongHieu = new JLabel("Thương hiệu:");
    JTextField txtThuongHieu = new JTextField();

    JPanel pnlNguonGoc = new JPanel();
    JLabel lblNguonGoc = new JLabel("Nguồn gốc:");
    JTextField txtNguonGoc = new JTextField();

    JPanel pnlChuaDonGiaNhapVaSLNhap = new JPanel();

    JPanel pnlDonGiaNhap = new JPanel();
    JLabel lblDonGiaNhap = new JLabel("Đơn giá nhập:");
    JTextField txtDonGiaNhap = new JTextField();

    JPanel pnlSLNhap = new JPanel();
    JLabel lblSLNhap = new JLabel("Số lượng nhập:");
    JTextField txtSLNhap = new JTextField();

    JPanel pnlMota = new JPanel();
    Dimension dimPnlMoTa = new Dimension(
            dimPnlConLoai1.width,
            dimPnlConLoai1.height + dimTxtLoai1.height
    );

    JLabel lblMoTa = new JLabel("Mô tả sản phẩm:");
    JTextArea txaMoTa = new JTextArea();
    Dimension dimTxaMoTa = new Dimension(
            dimPnlConLoai1.width,
            dimTxtLoai1.height * 2
    );

    DecimalFormat df = new DecimalFormat("#,###.###");
}
