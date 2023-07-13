package ui.giaodienchinh.pnlqlthuchi.gdtaophieudoichung;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public interface IDSBienGDTaoPhieuDoiChung extends IDSBienMacDinh {
    Color mauNenMacDinh = new Color(221, 237, 233);
    Dimension kichThuocGDTaoPhieuDoiChung = new Dimension(500, 400);

    JPanel panelChinh = new JPanel();

    JPanel panelGioiThieu = new JPanel();
    Dimension kichThuocPanelGioiThieu = new Dimension(
            kichThuocGDTaoPhieuDoiChung.width - 4,
            60
    );
    JLabel lbGioiThieu = new JLabel("TẠO PHIẾU ĐỐI CHỨNG");
    Font fontLbGioiThieu = new Font(tenFontMacDinh, Font.PLAIN, 25);
    Color mauLbGioiThieu = new Color(28, 140, 140);

    JPanel panelChuaCacBtn = new JPanel();
    Dimension kichThuocPanelChuaCacBtn = new Dimension(
            kichThuocGDTaoPhieuDoiChung.width - 4, 85
    );

    Dimension kichThuocCacBtn = new Dimension(
            kichThuocGDTaoPhieuDoiChung.width / 2 - 40,
            48
    );
    JButton btnXacNhanTaoPhieuDC = new JButton("Tạo phiếu");
    Color mauNenBtnXacNhanTaoPhieuDC = new Color(9, 125, 171);
    JButton btnThoat = new JButton("Thoát");


    JPanel panelNoiDungPhieu = new JPanel();
    Dimension kichThuocPanelNoiDungPhieu = new Dimension(
            kichThuocGDTaoPhieuDoiChung.width - 4,
            kichThuocGDTaoPhieuDoiChung.height
                    - kichThuocPanelGioiThieu.height
                    - kichThuocPanelChuaCacBtn.height
                    - 4
    );

    Dimension kichThuocTxtLoai1 = new Dimension(
            kichThuocPanelNoiDungPhieu.width / 2 - 40,
            40
    );

    Dimension kichThuocPanelChuaMaPhieuDCVaTGLap = new Dimension(
            kichThuocPanelNoiDungPhieu.width,
            72
    );
    Dimension kichThuocPanelMaPhieuVaTGLap = new Dimension(
            kichThuocPanelNoiDungPhieu.width / 2 - 40,
            kichThuocPanelChuaMaPhieuDCVaTGLap.height
    );

    JPanel panelChuaTTSoTienHeThongTinh = new JPanel();

    JPanel panelSoTienBanDau = new JPanel();
    JLabel lbSoTienBanDau = new JLabel("Số tiền ban đầu:");
    JTextField txtSoTienBanDau = new JTextField();

    JPanel panelSoTienConLaiHTTinh = new JPanel();
    JLabel lbSoTienConLaiHTTinh = new JLabel("Số tiền còn lại HT tính:");
    JTextField txtSoTienConLaiHTTinh = new JTextField();

    JPanel panelChuaTTNhanVienLapPhieu = new JPanel();

    JPanel panelMaNhanVienLP = new JPanel();
    JLabel lbMaNhanVienLP = new JLabel("Mã NV lập phiếu:");
    JTextField txtMaNhanVienLP = new JTextField();

    JPanel panelSoTienNVLPTinh = new JPanel();
    JLabel lbSoTienNVLPTinh = new JLabel("Số tiền NVLP tính:");
    JTextField txtSoTienNVLPTinh = new JTextField();

    JPanel panelChuaTTNhanVienKiemPhieu = new JPanel();

    JPanel panelMaNhanVienKP = new JPanel();
    JLabel lbMaNhanVienKP = new JLabel("Mã NV kiểm phiếu:");
    JTextField txtMaNhanVienKP = new JTextField();

    JPanel panelSoTienNVKPTinh = new JPanel();
    JLabel lbSoTienNVKPTinh = new JLabel("Số tiền NVKP tính:");
    JTextField txtSoTienNVKPTinh = new JTextField();

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    DecimalFormat df = new DecimalFormat("#,###.###");

    AtomicReference<Double> soTienBanDau = new AtomicReference<>(0.0);
    AtomicReference<Double> soTienConLaiTrongKetHTTinh = new AtomicReference<>(0.0);
}
