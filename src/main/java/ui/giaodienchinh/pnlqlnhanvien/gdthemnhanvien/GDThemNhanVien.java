package ui.giaodienchinh.pnlqlnhanvien.gdthemnhanvien;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.CaLamViec;
import entity.NhanVien;
import entity.TaiKhoan;
import services.CacHamDungSan;
import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlnhanvien.PnlQLNhanVien;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GDThemNhanVien extends JFrame implements IDSBienGDThemNhanVien {
    private static GDThemNhanVien gdThemNhanVien = null;

    private Map<String, NhanVien> dsNhanVien = PnlQLNhanVien.dsNhanVien;

    private GDThemNhanVien() {
        setTitle("Thêm nhân viên mới");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setSize(kichThuocGDThemNhanVien);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * <li>Trước khi dùng hàm này, hãy dùng hàm setCheDoSuDung() để đặt chế độ sử dụng</li>
     * <li>Có 2 chế độ sử dụng là:</li>
     * <p>  1: <i>THEMNV</i></p>
     * <p>-1: <i>XEMTHONGTINNV</i></p>
     * <li>Gọi hàm này xong, nhớ gọi thêm hàm SetVisible(true) và requestFocusInWindows()</li>
     *
     * @return: Giao diện thêm nhân viên
     */
    public static GDThemNhanVien getGdThemNhanVien() {
        if (gdThemNhanVien == null)
            gdThemNhanVien = new GDThemNhanVien();
        return gdThemNhanVien;
    }

    private void dungUI() {
        dungPanelGDChinh();

        getContentPane().add(panelGDChinh, BorderLayout.CENTER);
    }

    private void dungPanelGDChinh() {
        panelGDChinh.setBackground(mauNenMacDinh);
        panelGDChinh.setPreferredSize(kichThuocGDThemNhanVien);
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

        dungPanelCap1(panelHoTen, lbHoTen, txtHoTen);
        panelThongTinNV.add(panelHoTen);
        datHanhDongChoTxtHoten();

        dungPanelChuaSDTVaCMND();
        panelThongTinNV.add(panelChuaSDTVaCMND);
        datHanhDongChoTxtSDT();
        datHanhDongChoTxtCMND();

        dungPanelCap1(panelDiaChi, lbDiaChi, txtDiaChi);
        panelThongTinNV.add(panelDiaChi);
        datHanhDongChoTxtDiaChi();

        dungPanelChuaGioiTinhVaCaLamViec();
        panelThongTinNV.add(panelChuaGioiTinhVaCaLamViec);

        dungPanelCap1(panelChuaCapBac, lbCapBac, cbCapBac);
        panelThongTinNV.add(panelChuaCapBac);
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
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txt);
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

        CacHamDungSan.datThuocTinhChoCbb(cbb, kichThuocTxtSDTVaCMND);
        pnl.add(cbb);
    }

    private void dungPanelCap1(JPanel pnl, JLabel lbl, JComboBox cbb) {
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));
        pnl.setBackground(mauNenMacDinh);
        pnl.setPreferredSize(kichThuocCacPanelChuaTTNV);

        lbl.setForeground(frgMacDinh);
        lbl.setFont(fontLbTieuDe);
        pnl.add(lbl);

        CacHamDungSan.datThuocTinhChoCbb(
                cbb,
                kichThuocTxt
        );
        cbb.setForeground(frgMacDinh);
        pnl.add(cbb);
    }

    private void dungPanelChuaCacBtn() {
        panelChuaCacBtn.setBackground(mauNenMacDinh);
        panelChuaCacBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaCacBtn.setPreferredSize(kichThuocPanelChuaCacBtn);

        CacHamDungSan.datThuocTinhChoBtn(
                btnDongGD,
                null,
                frgMacDinh,
                kichThuocBtn
        );
        datHanhDongChoBtnDongGD(btnDongGD);
        panelChuaCacBtn.add(btnDongGD);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXacNhanThemNV,
                mauNenBtnXacNhanThemNV,
                mauNenMacDinh,
                kichThuocBtn
        );
        datHanhDongChoBtnThemNhanVien(btnXacNhanThemNV);
        panelChuaCacBtn.add(btnXacNhanThemNV);
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
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtSoCMND.setText("");
        cbGioiTinh.setSelectedIndex(0);
        cbCaLamViec.setSelectedIndex(0);
        cbCapBac.setSelectedIndex(0);
    }

    private void datHanhDongChoBtnThemNhanVien(JButton btnThemNhanVien) {
        btnThemNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!kiemTraDuLieu())
                    return;

                    NhanVien nv = layNhanVien();

                    if(NhanVienDAO.themNhanVienMoi(nv)){

                        TaiKhoan taiKhoan = new TaiKhoan(nv);

                            if(TaiKhoanDAO.themTaiKhoanMoi(taiKhoan)) {
                                String msg = String.format("         Tên đăng nhập       : %s" +
                                                "\nMật khẩu mặc định: %s",
                                        taiKhoan.getTenDangNhap(), taiKhoan.getMatKhau());

                                CacHamDungSan.hienThiThongBaoKetQua(
                                        1, msg
                                );

                                PnlQLNhanVien.locLaiDuLieuSauKhiThemHoacCapNhat();
                                PnlQLNhanVien.capNhatDSNhanVien();
                            }

                        datDuLieuTTNhanVienVienMacDinh();
                        dispose();
                }
            }
        });
    }

    private NhanVien layNhanVien(){
        int stt = NhanVienDAO.xacDinhSoLuongNhanVien() + 1;

        boolean capBac = cbCapBac.getSelectedIndex() == 0;

        boolean maCaLamViec = cbCaLamViec.getSelectedIndex() == 0;

        CaLamViec caLamViec = new CaLamViec(maCaLamViec);

        String hoTen = txtHoTen.getText().trim();
        String sDT = txtSDT.getText().trim();
        String soCMND = txtSoCMND.getText().trim();
        String diChi = txtDiaChi.getText().trim();

        boolean gioiTinh = cbGioiTinh.getSelectedIndex() == 0;

        NhanVien quanLy = GDChinh.getNhanVienDangSuDung();

        return new NhanVien(caLamViec, hoTen, sDT, soCMND, diChi, gioiTinh, capBac, quanLy, stt);
    }

    private boolean kiemTraHoTen(){
        String hoTen = txtHoTen.getText().trim();
        if(hoTen.isEmpty()){
            CacHamDungSan.hienThiThongBaoKetQua(
                    -1, "Vui lòng nhập họ tên!"
            );
            txtHoTen.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraDiaChi(){
        String diaChi = txtDiaChi.getText().trim();
        if(diaChi.isEmpty()){
            CacHamDungSan.hienThiThongBaoKetQua(
                    -1, "Vui lòng nhập địa chỉ!"
            );
            txtDiaChi.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraSDT(){
        String sdt = txtSDT.getText().trim();
        String pattern = "^(032|033|034|035|036|037|038|039|086|096|097|098|" +
                "070|079|077|076|078|089|090|093|" +
                "083|084|085|081|082|088|091|094|" +
                "056|058|092|" +
                "059|099)[0-9]{7}$";

        if (sdt.isEmpty()) {
            CacHamDungSan.hienThiThongBaoKetQua(
                    -1, "Vui lòng nhập số điện thoại!"
            );
            txtSDT.requestFocus();
            return false;
        }

        if(!sdt.matches(pattern)) {
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Số điện thoại phải bắt đầu bằng các đầu số của các nhà mạng Việt Nam!"
            );
            txtSDT.requestFocus();
            return false;
        }

        AtomicBoolean rsKiemTraKhongTrungSoSDT = new AtomicBoolean(true);

        dsNhanVien.values().forEach(V -> {
            if (V.getSoCMND().equals(sdt)){
                rsKiemTraKhongTrungSoSDT.set(true);

                CacHamDungSan.hienThiThongBaoKetQua(
                        -1, "Số CMND này đang thuộc về nhân viên khác!"
                );
                txtSoCMND.requestFocus();
            }
        });

        return rsKiemTraKhongTrungSoSDT.get();
    }

    private boolean kiemTraCMND(){
        String soCMND = txtSoCMND.getText().trim();

        if(soCMND.isEmpty()){
            CacHamDungSan.hienThiThongBaoKetQua(
                    -1, "Vui lòng nhập số CMND!"
            );
            txtSoCMND.requestFocus();
            return false;
        }

        if(!soCMND.matches("[0-9]{9}|[0-9]{12}")) {
            CacHamDungSan.hienThiThongBaoKetQua(
                    -1, "Số CMND phải là số và có độ dài là 9 hoặc 12 kí tự!"
            );
            txtSoCMND.requestFocus();

            return false;
        }

        AtomicBoolean rsKiemTraKhongTrungSoCMND = new AtomicBoolean(true);

        dsNhanVien.values().forEach(V -> {
            if (V.getSoCMND().equals(soCMND)){
                rsKiemTraKhongTrungSoCMND.set(true);

                CacHamDungSan.hienThiThongBaoKetQua(
                        -1, "Số CMND này đang thuộc về nhân viên khác!"
                );
                txtSoCMND.requestFocus();
            }
        });

        return rsKiemTraKhongTrungSoCMND.get();
    }

    private boolean kiemTraDuLieu(){
        return kiemTraHoTen() && kiemTraSDT() && kiemTraCMND() && kiemTraDiaChi();
    }

    private void datHanhDongChoTxtHoten(){
        txtHoTen.addActionListener((e) -> {
            if(kiemTraHoTen())
                txtSDT.requestFocus();
        });
    }

    private void datHanhDongChoTxtSDT(){
        txtSDT.addActionListener((e) -> {
            if(kiemTraSDT())
                txtSoCMND.requestFocus();
        });
    }

    private void datHanhDongChoTxtCMND(){
        txtSoCMND.addActionListener((e) -> {
            if(kiemTraCMND())
                txtDiaChi.requestFocus();
        });
    }

    private void datHanhDongChoTxtDiaChi(){
        txtDiaChi.addActionListener((e) -> {
            if(kiemTraDiaChi())
                cbGioiTinh.requestFocus();
        });
    }
}