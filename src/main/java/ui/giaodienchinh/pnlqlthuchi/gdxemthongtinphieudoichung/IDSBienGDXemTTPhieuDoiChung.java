package ui.giaodienchinh.pnlqlthuchi.gdxemthongtinphieudoichung;

import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDXemTTPhieuDoiChung extends IDSBienMacDinh {
    Dimension kichThuocGDTaoPhieuDoiChung = new Dimension(500, 480);

    JPanel panelChinh = new JPanel();
    Color mauNenMacDinh = new Color(221, 237, 233);

    JPanel panelGioiThieu = new JPanel();
    Dimension kichThuocPanelGioiThieu = new Dimension(
            kichThuocGDTaoPhieuDoiChung.width - 4,
            60
    );
    JLabel lbGioiThieu = new JLabel("PHIẾU ĐỐI CHỨNG");
    Font fontLbGioiThieu = new Font(tenFontMacDinh, Font.PLAIN, 25);
    Color mauLbGioiThieu = new Color(28, 140, 140);

    JPanel panelChuaCacBtn = new JPanel();
    Dimension kichThuocPanelChuaCacBtn = new Dimension(
            kichThuocGDTaoPhieuDoiChung.width - 4,
            85
    );

    Dimension kichThuocCacBtn = new Dimension(
            kichThuocGDTaoPhieuDoiChung.width / 2 - 40,
            48
    );
    JButton btnThoat = new JButton("Thoát");
    Color mauNenBtnThoat = new Color(9, 125, 171);


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


    JPanel panelChuaMaPhieuDCVaTGLap = new JPanel();
    Dimension kichThuocPanelChuaMaPhieuDCVaTGLap = new Dimension(
            kichThuocPanelNoiDungPhieu.width,
            72
    );
    Dimension kichThuocPanelMaPhieuVaTGLap = new Dimension(
            kichThuocPanelNoiDungPhieu.width / 2 - 40,
            kichThuocPanelChuaMaPhieuDCVaTGLap.height
    );

    JPanel panelMaPhieuDC = new JPanel();
    JLabel lbMaPhieuDC = new JLabel("Mã phiếu:");
    JTextField txtMaPhieuDC = new JTextField();

    JPanel panelTGLap = new JPanel();
    JLabel lbTGLap = new JLabel("Thời gian lập phiếu:");
    JTextField txtTGLap = new JTextField();

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
}
