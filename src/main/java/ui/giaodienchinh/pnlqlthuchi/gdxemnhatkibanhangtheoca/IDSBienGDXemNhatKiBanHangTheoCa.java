package ui.giaodienchinh.pnlqlthuchi.gdxemnhatkibanhangtheoca;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDXemNhatKiBanHangTheoCa extends IDSBienMacDinh {
    Color mauNenMacDinh = new Color(209, 229, 237);
    Dimension kichThuocGDTaoNhatKiBanHangTheoCa = new Dimension(500, 480);

    JPanel panelChinh = new JPanel();

    JPanel panelGioiThieu = new JPanel();
    Dimension kichThuocPanelGioiThieu = new Dimension(
            kichThuocGDTaoNhatKiBanHangTheoCa.width - 4,
            60
    );
    JLabel lbGioiThieu = new JLabel("NHẬT KÍ BÁN HÀNG THEO CA");
    Font fontLbGioiThieu = new Font(tenFontMacDinh, Font.PLAIN, 25);
    Color mauLbGioiThieu = new Color(28, 140, 140);

    JPanel panelXacNhan = new JPanel();
    Dimension kichThuocPanelXacNhan = new Dimension(
            kichThuocGDTaoNhatKiBanHangTheoCa.width - 4,
            85
    );

    JButton btnXacNhan = new JButton("Thoát");
    Dimension kichThuocBtn = new Dimension(
            kichThuocPanelXacNhan.width / 2 - 80,
            48
    );
    Color mauNenBtnXacNhan = new Color(9, 125, 171);

    JPanel panelNoiDungNhatKi = new JPanel();
    Dimension kichThuocPanelNoiDungNhatKi = new Dimension(
            kichThuocGDTaoNhatKiBanHangTheoCa.width - 4,
            kichThuocGDTaoNhatKiBanHangTheoCa.height
                    - kichThuocPanelXacNhan.height
                    - kichThuocPanelGioiThieu.height
                    - 4
    );

    Font fontLbTieuDe = new Font(tenFontMacDinh, Font.PLAIN, 17);

    JPanel panelChuaHoTenVaMaNV = new JPanel();
    Dimension kichThuocPanelChuaHotenVaMaNV = new Dimension(
            kichThuocGDTaoNhatKiBanHangTheoCa.width - 10,
            72
    );

    Dimension kichThuocPanelHoTenVaMaNV = new Dimension(
            kichThuocPanelChuaHotenVaMaNV.width / 2 - 15,
            kichThuocPanelChuaHotenVaMaNV.height
    );
    Dimension kichThuocTxtLoai1 = new Dimension(
            kichThuocPanelHoTenVaMaNV.width,
            40
    );

    JPanel panelHoTen = new JPanel();
    JLabel lbHoTen = new JLabel("Họ tên:");
    JTextField txtHoTen = new JTextField();

    JPanel panelMaNV = new JPanel();
    JLabel lbMaNV = new JLabel("Mã nhân viên:");
    JTextField txtMaNV = new JTextField();

    JPanel panelChuaMaNhatKiVaNgayLap = new JPanel();
    JPanel panelMaNhatKi = new JPanel();
    JLabel lbMaNhatKi = new JLabel("Mã nhật kí BHTC:");
    JTextField txtMaNhatKi = new JTextField();

    JPanel panelNgayLap = new JPanel();
    JLabel lbNgayLap = new JLabel("Ngày lập:");
    JTextField txtNgayLap = new JTextField();

    JPanel panelChuaTTVeSLSanPham = new JPanel();
    Dimension kichThuocPanelTTVeSLSanPham = new Dimension(
            kichThuocPanelChuaHotenVaMaNV.width / 3 - 15,
            kichThuocPanelChuaHotenVaMaNV.height
    );
    Dimension kichThuocTxtLoai2 = new Dimension(
            kichThuocPanelTTVeSLSanPham.width,
            40
    );

    JPanel panelSLSPDaBan = new JPanel();
    JLabel lbSLSPDaBan = new JLabel("SLSP đã bán:");
    JTextField txtSLSPDaBan = new JTextField();

    JPanel panelSLSPConLai = new JPanel();
    JLabel lbSLSPConLai = new JLabel("SLSP còn lại:");
    JTextField txtSLSPConLai = new JTextField();

    JPanel panelSLSPMoiNhap = new JPanel();
    JLabel lbSLSPMoiNhap = new JLabel("SLSP mới nhập:");
    JTextField txtSLSPMoiNhap = new JTextField();

    Dimension kichThuocTxtLoai3 = new Dimension(
            kichThuocPanelChuaHotenVaMaNV.width - 20,
            40
    );
    JPanel panelDoanhThu = new JPanel();
    JLabel lbDoanhThu = new JLabel("Doanh thu:");
    JTextField txtDoanhThu = new JTextField();
}
