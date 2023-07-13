package ui.giaodienchinh.pnlqlthuchi.gdxemthongtinphieudoichung;

import entity.PhieuDoiChung;
import services.CacHamDungSan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GDXemTTPhieuDoiChung extends JDialog implements IDSBienGDXemTTPhieuDoiChung {
    private static GDXemTTPhieuDoiChung gdXemTTPhieuDoiChung = null;

    private GDXemTTPhieuDoiChung() {
        setTitle("Phiếu đối chứng");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setModal(true);
        setSize(kichThuocGDTaoPhieuDoiChung);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static GDXemTTPhieuDoiChung getGdXemTTPhieuDoiChung() {
        if (gdXemTTPhieuDoiChung == null)
            gdXemTTPhieuDoiChung = new GDXemTTPhieuDoiChung();
        return gdXemTTPhieuDoiChung;
    }

    private void dungUI() {
        dungPanelChinh();

        getContentPane().add(panelChinh, BorderLayout.CENTER);
    }

    private void dungPanelChinh() {
        panelChinh.setBackground(mauNenMacDinh);
        panelChinh.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelChinh.setPreferredSize(kichThuocGDTaoPhieuDoiChung);
        panelChinh.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 2
                )
        );

        dungPanelGioiThieu();
        panelChinh.add(panelGioiThieu);

        dungPanelNoiDungPhieuDoiChung();
        panelChinh.add(panelNoiDungPhieu);

        dungPanelChuaCacBtn();
        panelChinh.add(panelChuaCacBtn);
    }

    private void dungPanelGioiThieu() {
        panelGioiThieu.setBackground(mauNenMacDinh);
        panelGioiThieu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelGioiThieu.setPreferredSize(kichThuocPanelGioiThieu);

        lbGioiThieu.setFont(fontLbGioiThieu);
        lbGioiThieu.setForeground(mauLbGioiThieu);

        panelGioiThieu.add(lbGioiThieu);
    }

    private void dungPanelNoiDungPhieuDoiChung() {
        panelNoiDungPhieu.setPreferredSize(kichThuocPanelNoiDungPhieu);
        panelNoiDungPhieu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelNoiDungPhieu.setBackground(mauNenMacDinh);

        dungPanelChuaMaPhieuVaTGLap();
        panelNoiDungPhieu.add(panelChuaMaPhieuDCVaTGLap);

        dungPanelChuaTTSoTienHHTinh();
        panelNoiDungPhieu.add(panelChuaTTSoTienHeThongTinh);

        dungPanelChuaTTNhanVienLapPhieu();
        panelNoiDungPhieu.add(panelChuaTTNhanVienLapPhieu);

        dungPanelChuaTTNhanVienKiemPhieu();
        panelNoiDungPhieu.add(panelChuaTTNhanVienKiemPhieu);
    }

    private void dungPanelChuaMaPhieuVaTGLap() {
        panelChuaMaPhieuDCVaTGLap.setBackground(mauNenMacDinh);
        panelChuaMaPhieuDCVaTGLap.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaMaPhieuDCVaTGLap.setPreferredSize(kichThuocPanelChuaMaPhieuDCVaTGLap);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelMaPhieuDC, lbMaPhieuDC, txtMaPhieuDC);
        panelChuaMaPhieuDCVaTGLap.add(panelMaPhieuDC);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelTGLap, lbTGLap, txtTGLap);
        panelChuaMaPhieuDCVaTGLap.add(panelTGLap);
    }

    private void dungPanelConCuaPanelChuaMaPhieuVaTGLap(JPanel panel, JLabel lb, JTextField txt) {
        panel.setBackground(mauNenMacDinh);
        panel.setPreferredSize(kichThuocPanelMaPhieuVaTGLap);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lb.setFont(fntMacDinh);
        lb.setForeground(frgMacDinh);
        panel.add(lb);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                kichThuocTxtLoai1
        );
        txt.setForeground(frgMacDinh);
        txt.setEditable(false);
        panel.add(txt);
    }

    private void dungPanelChuaTTSoTienHHTinh() {
        panelChuaTTSoTienHeThongTinh.setBackground(mauNenMacDinh);
        panelChuaTTSoTienHeThongTinh.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaTTSoTienHeThongTinh.setPreferredSize(kichThuocPanelChuaMaPhieuDCVaTGLap);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienBanDau, lbSoTienBanDau, txtSoTienBanDau);
        panelChuaTTSoTienHeThongTinh.add(panelSoTienBanDau);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienConLaiHTTinh, lbSoTienConLaiHTTinh, txtSoTienConLaiHTTinh);
        panelChuaTTSoTienHeThongTinh.add(panelSoTienConLaiHTTinh);
    }

    private void dungPanelChuaTTNhanVienLapPhieu() {
        panelChuaTTNhanVienLapPhieu.setBackground(mauNenMacDinh);
        panelChuaTTNhanVienLapPhieu.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaTTNhanVienLapPhieu.setPreferredSize(kichThuocPanelChuaMaPhieuDCVaTGLap);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelMaNhanVienLP, lbMaNhanVienLP, txtMaNhanVienLP);
        panelChuaTTNhanVienLapPhieu.add(panelMaNhanVienLP);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienNVLPTinh, lbSoTienNVLPTinh, txtSoTienNVLPTinh);
        panelChuaTTNhanVienLapPhieu.add(panelSoTienNVLPTinh);
    }

    private void dungPanelChuaTTNhanVienKiemPhieu() {
        panelChuaTTNhanVienKiemPhieu.setBackground(mauNenMacDinh);
        panelChuaTTNhanVienKiemPhieu.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaTTNhanVienKiemPhieu.setPreferredSize(kichThuocPanelChuaMaPhieuDCVaTGLap);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelMaNhanVienKP, lbMaNhanVienKP, txtMaNhanVienKP);
        panelChuaTTNhanVienKiemPhieu.add(panelMaNhanVienKP);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienNVKPTinh, lbSoTienNVKPTinh, txtSoTienNVKPTinh);
        panelChuaTTNhanVienKiemPhieu.add(panelSoTienNVKPTinh);
    }

    private void dungPanelChuaCacBtn() {
        panelChuaCacBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelChuaCacBtn.setPreferredSize(kichThuocPanelChuaCacBtn);
        panelChuaCacBtn.setBackground(mauNenMacDinh);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                mauNenBtnThoat,
                mauNenMacDinh,
                kichThuocCacBtn
        );
        datHanhDongChoBtnThoat(btnThoat);

        panelChuaCacBtn.add(btnThoat);
    }

    private void datHanhDongChoBtnThoat(JButton btnThoat) {
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    dispose();
                });
            }
        });
    }

    public static void hienThiThongTinPhieuDoiChung(PhieuDoiChung phieuDoiChung){
        NumberFormat nf = NumberFormat.getCurrencyInstance(
                new Locale("vi", "vn")
        );
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

        txtMaPhieuDC.setText(phieuDoiChung.getMaPhieuDoiChung() + "");
        txtSoTienBanDau.setText(nf.format(phieuDoiChung.getSoTienBanDau()));
        txtSoTienConLaiHTTinh.setText(nf.format(phieuDoiChung.getSoTienTrongKetHeThongTinh()));
        txtMaNhanVienLP.setText(phieuDoiChung.getNhanVienLapPhieu().getMaNV());
        txtSoTienNVLPTinh.setText(nf.format(phieuDoiChung.getSoTienNguoiGiaoCaTinh()));
        txtMaNhanVienKP.setText(phieuDoiChung.getNhanVienKiemPhieu().getMaNV());
        txtSoTienNVKPTinh.setText(nf.format(phieuDoiChung.getSoTienNguoiNhanCaTinh()));
        txtTGLap.setText(dtf.format(phieuDoiChung.getThoiGianLapPhieu()));
    }
}
