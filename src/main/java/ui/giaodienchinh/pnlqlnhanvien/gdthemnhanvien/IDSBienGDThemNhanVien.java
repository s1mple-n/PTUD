package ui.giaodienchinh.pnlqlnhanvien.gdthemnhanvien;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDThemNhanVien extends IDSBienMacDinh {
    Dimension kichThuocGDThemNhanVien = new Dimension(450, 470);
    Color mauNenMacDinh = new Color(
            250, 255, 255
    );

    JPanel panelGDChinh = new JPanel();

    JPanel panelChuaCacBtn = new JPanel();
    Dimension kichThuocPanelChuaCacBtn = new Dimension(
            kichThuocGDThemNhanVien.width - 4,
            65
    );

    Dimension kichThuocBtn = new Dimension(
            kichThuocPanelChuaCacBtn.width / 2 - 30,
            48
    );
    JButton btnXacNhanThemNV = new JButton("Thêm nhân viên");
    Color mauNenBtnXacNhanThemNV = new Color(12, 132, 150);

    JButton btnDongGD = new JButton("Thoát");

    JPanel panelThongTinNV = new JPanel();
    Dimension kichThuocPanelThongTinNV = new Dimension(
            kichThuocGDThemNhanVien.width - 4,
            kichThuocGDThemNhanVien.height
                    - kichThuocPanelChuaCacBtn.height
                    - 4
    );

    Font fontLbTieuDe = new Font(tenFontMacDinh, Font.PLAIN, 16);
    Dimension kichThuocCacPanelChuaTTNV = new Dimension(
            kichThuocGDThemNhanVien.width - 40,
            72
    );
    Dimension kichThuocTxt = new Dimension(
            kichThuocCacPanelChuaTTNV.width,
            40
    );

    JPanel panelHoTen = new JPanel();
    JLabel lbHoTen = new JLabel("Họ tên:");
    JTextField txtHoTen = new JTextField();

    JPanel panelChuaSDTVaCMND = new JPanel();
    Dimension kichThuocPanelSDTVaCMND = new Dimension(
            kichThuocCacPanelChuaTTNV.width / 2 - 10,
            kichThuocCacPanelChuaTTNV.height
    );
    Dimension kichThuocTxtSDTVaCMND = new Dimension(
            kichThuocPanelSDTVaCMND.width, 40
    );

    JPanel panelSDT = new JPanel();
    JLabel lbSDT = new JLabel("Số điện thoại:");
    JTextField txtSDT = new JTextField();

    JPanel panelSoCMND = new JPanel();
    JLabel lbSoCMND = new JLabel("Số CMND:");
    JTextField txtSoCMND = new JTextField();

    JPanel panelDiaChi = new JPanel();
    JLabel lbDiaChi = new JLabel("Địa chỉ:");
    JTextField txtDiaChi = new JTextField();

    JPanel panelChuaGioiTinhVaCaLamViec = new JPanel();
    JPanel panelGioiTinh = new JPanel();
    JLabel lbGioiTinh = new JLabel("Giới tính:");
    String[] dsGioiTinh = {"Nam", "Nữ"};
    JComboBox<String> cbGioiTinh = new JComboBox<>(dsGioiTinh);

    JPanel panelCaLamViec = new JPanel();
    JLabel lbCaLamViec = new JLabel("Ca làm việc:");
    String[] dsCaLamViec = {"Ca sáng", "Ca tối"};
    JComboBox<String> cbCaLamViec = new JComboBox<>(dsCaLamViec);

    JPanel panelChuaCapBac = new JPanel();
    JPanel panelCapBac = new JPanel();
    JLabel lbCapBac = new JLabel("Cấp bậc:");
    String[] dsCapBac = {"Người quản lí", "NV bán hàng"};
    JComboBox<String> cbCapBac = new JComboBox<>(dsCapBac);
}
