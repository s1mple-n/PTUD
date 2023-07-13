package ui.giaodienchinh.pnlqlsanpham.gdcapnhatthongtinsanpham;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDCapNhatThongTinSanPham extends IDSBienMacDinh {
    Dimension kichThuocGDXemTTSanPham = new Dimension(500, 620);
    JPanel panelChinh = new JPanel();

    JPanel panelChuaBtn = new JPanel();
    Dimension kichThuocPanelChuaBtn = new Dimension(kichThuocGDXemTTSanPham.width, 65);

    Dimension kichThuocBtn = new Dimension(
            kichThuocGDXemTTSanPham.width / 2 - 30,
            45
    );
    JButton btnThoat = new JButton("Thoát");

    JButton btnCapNhat = new JButton("Cập nhật");
    Color mauNenBtnCapNhat = new Color(12, 132, 150);

    JPanel panelHienThiTTSanPham = new JPanel();
    Dimension kichThuocPanelHienThiTTSanPham = new Dimension(
            kichThuocGDXemTTSanPham.width,
            kichThuocGDXemTTSanPham.height - kichThuocPanelChuaBtn.height
    );

    Dimension kichThuocPanelConLoai1 = new Dimension(
            kichThuocPanelHienThiTTSanPham.width - 30,
            74
    );
    Dimension kichThuocTxtLoai1 = new Dimension(
            kichThuocPanelConLoai1.width,
            40
    );

    JPanel panelTenSanPham = new JPanel();
    JLabel lbTenSanPham = new JLabel("Tên sản phẩm:");
    JTextField txtTenSanPham = new JTextField();

    JPanel panelChuaMaSPVaMauSac = new JPanel();

    Dimension kichThuocPanelConLoai2 = new Dimension(
            kichThuocPanelHienThiTTSanPham.width / 2 - 20,
            kichThuocPanelConLoai1.height
    );
    Dimension kichThuocTxtLoai2 = new Dimension(
            kichThuocPanelConLoai2.width - 4,
            40
    );

    JPanel panelMaSP = new JPanel();
    JLabel lbMaSP = new JLabel("Mã sản phẩm:");
    JTextField txtMaSP = new JTextField();

    JPanel panelMauSac = new JPanel();
    JLabel lbMauSac = new JLabel("Màu sắc:");
    JTextField txtMauSac = new JTextField();

    JPanel panelChuaSizeVaChatLieu = new JPanel();

    JPanel panelSize = new JPanel();
    JLabel lbSize = new JLabel("Size:");
    JTextField txtSize = new JTextField();

    JPanel panelChatLieu = new JPanel();
    JLabel lbChatLieu = new JLabel("Chất liệu:");
    JTextField txtChatLieu = new JTextField();

    JPanel panelChuaThuongHieuVaNguonGoc = new JPanel();

    JPanel panelThuongHieu = new JPanel();
    JLabel lbThuongHieu = new JLabel("Thương hiệu:");
    JTextField txtThuongHieu = new JTextField();

    JPanel panelNguonGoc = new JPanel();
    JLabel lbNguonGoc = new JLabel("Nguồn gốc:");
    JTextField txtNguonGoc = new JTextField();

    JPanel panelChuaDonGiaVaSLTon = new JPanel();

    JPanel panelDonGia = new JPanel();
    JLabel lbDonGia = new JLabel("Đơn giá:");
    JTextField txtDonGia = new JTextField();

    JPanel panelSLTon = new JPanel();
    JLabel lbSLTon = new JLabel("Số lượng tồn:");
    JTextField txtSLTon = new JTextField();

    JPanel panelMoTa = new JPanel();
    Dimension kichThuocPanelMoTa = new Dimension(
            kichThuocPanelConLoai1.width,
            kichThuocPanelConLoai1.height
                    + kichThuocTxtLoai1.height
            + 10
    );

    JLabel lbMoTa = new JLabel("Mô tả sản phẩm:");
    JTextArea txaMoTa = new JTextArea();
    Dimension kichThuocTxaMoTa = new Dimension(
            kichThuocPanelConLoai1.width,
            kichThuocTxtLoai1.height * 2
    );
}
