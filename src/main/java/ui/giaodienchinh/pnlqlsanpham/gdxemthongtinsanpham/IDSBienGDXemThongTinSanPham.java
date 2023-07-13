package ui.giaodienchinh.pnlqlsanpham.gdxemthongtinsanpham;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDXemThongTinSanPham extends IDSBienMacDinh {
    Dimension dimGDXemThongTinSanPham = new Dimension(500, 620);

    JPanel pnlChinh = new JPanel();

    JPanel pnlChuaBtn = new JPanel();
    Dimension dimPnlChuaBtn = new Dimension(
            dimGDXemThongTinSanPham.width,
            65
    );

    Dimension dimBtnThoat = new Dimension(
            dimGDXemThongTinSanPham.width / 2 - 40,
            48
    );
    JButton btnThoat = new JButton("Thoát");

    JPanel pnlHienThiTTSanPham = new JPanel();
    Dimension dimPnlHienThiTTSanPham = new Dimension(
            dimGDXemThongTinSanPham.width,
            dimGDXemThongTinSanPham.height
                    - dimPnlChuaBtn.height
    );

    Dimension dimPnlConLoai1 = new Dimension(
            dimPnlHienThiTTSanPham.width - 30,
            73
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
            dimPnlConLoai2.width - 4,
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

    JPanel pnlChuaDonGiaVaSLTon = new JPanel();

    JPanel pnlDonGia = new JPanel();
    JLabel lblDonGia = new JLabel("Đơn giá:");
    JTextField txtDonGia = new JTextField();

    JPanel pnlSLTon = new JPanel();
    JLabel lblSLTon = new JLabel("Số lượng tồn:");
    JTextField txtSLTon = new JTextField();

    JPanel pnlMota = new JPanel();
    Dimension dimPnlMoTa = new Dimension(
            dimPnlConLoai1.width,
            dimPnlConLoai1.height
                    + dimTxtLoai1.height
                    + 20
    );

    JLabel lblMoTa = new JLabel("Mô tả sản phẩm:");
    JTextArea txaMoTa = new JTextArea();
    Dimension dimTxaMoTa = new Dimension(
            dimPnlConLoai1.width,
            dimTxtLoai1.height * 2
    );
}
