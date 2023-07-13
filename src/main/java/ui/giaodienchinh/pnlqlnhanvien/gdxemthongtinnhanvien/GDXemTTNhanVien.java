package ui.giaodienchinh.pnlqlnhanvien.gdxemthongtinnhanvien;

import entity.NhanVien;
import services.CacHamDungSan;
import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GDXemTTNhanVien extends JDialog implements I_DsBienGDXemTTNhanVien {
    private static GDXemTTNhanVien gdXemTTNhanVien = null;

    private GDXemTTNhanVien() {
        setTitle("Xem thông tin nhân viên");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setModal(true);
        setSize(kichThuocGDXemTTNhanVien);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static GDXemTTNhanVien getGdXemTTNhanVien() {
        if (gdXemTTNhanVien == null){
            gdXemTTNhanVien = new GDXemTTNhanVien();
        }
        return gdXemTTNhanVien;
    }

    private void dungUI() {
        dungPanelGDChinh();

        getContentPane().add(panelGDChinh, BorderLayout.CENTER);
    }

    private void dungPanelGDChinh() {
        panelGDChinh.setBackground(mauNenMacDinh);
        panelGDChinh.setPreferredSize(kichThuocGDXemTTNhanVien);
        panelGDChinh.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panelGDChinh.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 2
                )
        );

        dungPanelThongTinNV();
        panelGDChinh.add(panelThongTinNV);

        dungPanelChuaCacBtn();
        panelGDChinh.add(panelChuaCacBtn);
    }

    private void dungPanelThongTinNV() {
        panelThongTinNV.setBackground(mauNenMacDinh);
        panelThongTinNV.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelThongTinNV.setPreferredSize(kichThuocPanelThongTinNV);

        dungPanelCap1(panelMaNhanVien, lbMaNhanVien, txtMaNhanVien);
        panelThongTinNV.add(panelMaNhanVien);

        dungPanelCap1(panelHoTen, lbHoTen, txtHoTen);
        panelThongTinNV.add(panelHoTen);

        dungPanelChuaSDTVaCMND();
        panelThongTinNV.add(panelChuaSDTVaCMND);

        dungPanelCap1(panelDiaChi, lbDiaChi, txtDiaChi);
        panelThongTinNV.add(panelDiaChi);

        dungPanelCap1(panelNgayVaoLam, lbNgayVaoLam, txtNgayVaoLam);
        panelThongTinNV.add(panelNgayVaoLam);

        dungPanelChuaGioiTinhVaCaLamViec();
        panelThongTinNV.add(panelChuaGioiTinhVaCaLamViec);

        dungPanelChuaCapBacVaTTLamViec();
        panelThongTinNV.add(panelChuaCapBacVaTTLamViec);
    }

    private void dungPanelCap1(JPanel pnl, JLabel lbl, JTextField txt) {
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));
        pnl.setBackground(mauNenMacDinh);
        pnl.setPreferredSize(kichThuocCacPanelChuaTTNV);

        lbl.setForeground(frgMacDinh);
        lbl.setFont(fontLbTieuDe);
        pnl.add(lbl);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                kichThuocTxt
        );
        txt.setForeground(frgMacDinh);
        txt.setEditable(false);
        pnl.add(txt);
    }

    private void dungPanelChuaSDTVaCMND() {
        panelChuaSDTVaCMND.setLayout(new BoxLayout(panelChuaSDTVaCMND, BoxLayout.X_AXIS));
        panelChuaSDTVaCMND.setBackground(mauNenMacDinh);
        panelChuaSDTVaCMND.setPreferredSize(kichThuocCacPanelChuaTTNV);

        dungPanelCap2(panelSDT, lbSDT, txtSDT);
        panelChuaSDTVaCMND.add(panelSDT);

        dungPanelCap2(panelSoCMND, lbSoCMND, txtSoCMND);
        panelChuaSDTVaCMND.add(Box.createHorizontalStrut(20));
        panelChuaSDTVaCMND.add(panelSoCMND);
    }

    private void dungPanelCap2(JPanel pnl, JLabel lbl, JTextField txt) {
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));
        pnl.setBackground(mauNenMacDinh);
        pnl.setPreferredSize(kichThuocPanelSDTVaCMND);

        lbl.setForeground(frgMacDinh);
        lbl.setFont(fontLbTieuDe);
        pnl.add(lbl);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                kichThuocTxtSDTVaCMND
        );
        txt.setForeground(frgMacDinh);
        txt.setEditable(false);
        pnl.add(txt);
    }

    private void dungPanelChuaGioiTinhVaCaLamViec() {
        panelChuaGioiTinhVaCaLamViec.setPreferredSize(kichThuocCacPanelChuaTTNV);
        panelChuaGioiTinhVaCaLamViec.setBackground(mauNenMacDinh);
        panelChuaGioiTinhVaCaLamViec.setLayout(new BoxLayout(panelChuaGioiTinhVaCaLamViec, BoxLayout.X_AXIS));

        dungPanelCap2(panelGioiTinh, lbGioiTinh, cbGioiTinh);
        panelChuaGioiTinhVaCaLamViec.add(panelGioiTinh);

        dungPanelCap2(panelCaLamViec, lbCaLamViec, cbCaLamViec);
        panelChuaGioiTinhVaCaLamViec.add(Box.createHorizontalStrut(20));
        panelChuaGioiTinhVaCaLamViec.add(panelCaLamViec);
    }

    private void dungPanelCap2(JPanel pnl, JLabel lbl, JComboBox cbb) {
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));
        pnl.setBackground(mauNenMacDinh);
        pnl.setPreferredSize(kichThuocPanelSDTVaCMND);

        lbl.setForeground(frgMacDinh);
        lbl.setFont(fontLbTieuDe);
        pnl.add(lbl);

        CacHamDungSan.datThuocTinhChoCbb(
                cbb,
                kichThuocTxtSDTVaCMND
        );
        pnl.add(cbb);
    }

    private void dungPanelChuaCapBacVaTTLamViec() {
        panelChuaCapBacVaTTLamViec.setPreferredSize(kichThuocCacPanelChuaTTNV);
        panelChuaCapBacVaTTLamViec.setBackground(mauNenMacDinh);
        panelChuaCapBacVaTTLamViec.setLayout(new BoxLayout(panelChuaCapBacVaTTLamViec, BoxLayout.X_AXIS));

        dungPanelCap2(panelCapBac, lbCapBac, cbCapBac);
        panelChuaCapBacVaTTLamViec.add(panelCapBac);

        dungPanelCap2(panelTTLamViec, lbTTLamViec, cbTTLamViec);
        panelChuaCapBacVaTTLamViec.add(Box.createHorizontalStrut(20));
        panelChuaCapBacVaTTLamViec.add(panelTTLamViec);
    }

    private void dungPanelChuaCacBtn() {
        panelChuaCacBtn.setBackground(mauNenMacDinh);
        panelChuaCacBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaCacBtn.setPreferredSize(kichThuocPanelChuaCacBtn);

        datHanhDongChoBtnDongGD(btnDongGD);
        CacHamDungSan.datThuocTinhChoBtn(
                btnDongGD,
                IDSBienMacDinh.bgrBtnThoat,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnDongGD
        );
        panelChuaCacBtn.add(btnDongGD);
    }

    private void datHanhDongChoBtnDongGD(JButton btnDong) {
        btnDong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    datDuLieuTTNhanVienVienMacDinh();
                    dispose();
                });
            }
        });
    }

    private void datDuLieuTTNhanVienVienMacDinh() {
        txtMaNhanVien.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtNgayVaoLam.setText("");
        txtSoCMND.setText("");
        cbGioiTinh.setSelectedIndex(0);
        cbCaLamViec.setSelectedIndex(0);
        cbCapBac.setSelectedIndex(0);
        cbTTLamViec.setSelectedIndex(0);
    }

    public static void hienThiThongTinNhanVienLenTxt(NhanVien nv) {
        txtMaNhanVien.setText(nv.getMaNV());
        txtHoTen.setText(nv.getHoTen());
        txtSDT.setText(nv.getSoDT());
        txtSoCMND.setText(nv.getSoCMND());
        txtDiaChi.setText(nv.getDiaChi());
        txtDiaChi.setToolTipText(nv.getDiaChi());
        txtNgayVaoLam.setText(dtf.format(nv.getNgayVaoLam()));

        cbCaLamViec.setSelectedIndex((nv.getCaLamViec().isCaSang()) ? 0 : 1);
        cbCapBac.setSelectedIndex((nv.isQuanLi()) ? 0 : 1);
        cbGioiTinh.setSelectedIndex((nv.isNam()) ? 0 : 1);
        cbTTLamViec.setSelectedIndex((nv.isNghiLam()) ? 1 : 0);
    }
}
