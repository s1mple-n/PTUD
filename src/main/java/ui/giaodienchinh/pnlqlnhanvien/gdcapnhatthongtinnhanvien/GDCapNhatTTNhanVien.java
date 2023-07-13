package ui.giaodienchinh.pnlqlnhanvien.gdcapnhatthongtinnhanvien;

import dao.NhanVienDAO;
import entity.CaLamViec;
import entity.NhanVien;
import services.CacHamDungSan;
import ui.giaodienchinh.pnlqlnhanvien.PnlQLNhanVien;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GDCapNhatTTNhanVien extends JFrame implements I_DSBienGDCapNhatTTNhanVien {
    private static GDCapNhatTTNhanVien gdCapNhatTTNhanVien = null;

    private static NhanVien nhanVienHienTai = null;
    private Map<String, NhanVien> dsNhanVien = PnlQLNhanVien.dsNhanVien;

    private GDCapNhatTTNhanVien() {
        setTitle("Xem thông tin nhân viên");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));
        dungUI();
        setUndecorated(true);
        pack();
        setSize(kichThuocGDXemTTNhanVien);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static GDCapNhatTTNhanVien getGdCapNhatTTNhanVien() {
        if (gdCapNhatTTNhanVien == null){
            gdCapNhatTTNhanVien = new GDCapNhatTTNhanVien();
        }
        return gdCapNhatTTNhanVien;
    }

    public static void datRequestFocusVaoTxtSoDTNV(){
        txtSDT.requestFocus();
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

        dungPanelChuaCacBtn();

        panelGDChinh.add(panelThongTinNV);
        panelGDChinh.add(panelChuaCacBtn);
    }

    private void dungPanelThongTinNV() {
        panelThongTinNV.setBackground(mauNenMacDinh);
        panelThongTinNV.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelThongTinNV.setPreferredSize(kichThuocPanelThongTinNV);

        txtMaNhanVien.setEditable(false);
        dungPanelCap1(panelMaNhanVien, lbMaNhanVien, txtMaNhanVien);
        panelThongTinNV.add(panelMaNhanVien);

        txtHoTen.setEditable(false);
        dungPanelCap1(panelHoTen, lbHoTen, txtHoTen);
        panelThongTinNV.add(panelHoTen);

        dungPanelChuaSDTVaCMND();
        panelThongTinNV.add(panelChuaSDTVaCMND);

        dungPanelCap1(panelDiaChi, lbDiaChi, txtDiaChi);
        CacHamDungSan.datMauVienChoTxtDuocPhepChinhSua(txtDiaChi);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtDiaChi);
        panelThongTinNV.add(panelDiaChi);

        txtNgayVaoLam.setEditable(false);
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
        pnl.add(txt);
    }

    private void dungPanelChuaSDTVaCMND() {
        panelChuaSDTVaCMND.setLayout(new BoxLayout(panelChuaSDTVaCMND, BoxLayout.X_AXIS));
        panelChuaSDTVaCMND.setBackground(mauNenMacDinh);
        panelChuaSDTVaCMND.setPreferredSize(kichThuocCacPanelChuaTTNV);

        dungPanelCap2(panelSDT, lbSDT, txtSDT);
        CacHamDungSan.datMauVienChoTxtDuocPhepChinhSua(txtSDT);
        datHanhDongChoTxtSDT();
        panelChuaSDTVaCMND.add(panelSDT);

        dungPanelCap2(panelSoCMND, lbSoCMND, txtSoCMND);
        CacHamDungSan.datMauVienChoTxtDuocPhepChinhSua(txtSoCMND);
        datHanhDongChoTxtCMND();
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
        cbGioiTinh.setEnabled(false);
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

        CacHamDungSan.datThuocTinhChoBtn(
                btnDongGD,
                null,
                frgMacDinh,
                kichThuocBtn
        );
        datHanhDongChoBtnDongGD();
        panelChuaCacBtn.add(btnDongGD);

        CacHamDungSan.datThuocTinhChoBtn(
                btnCapNhatTTNV,
                mauNenBtnCapNhatTTNV,
                mauNenMacDinh,
                kichThuocBtn
        );
        datHanhDongChoBtnCapNhatTTNhanVien();
        panelChuaCacBtn.add(btnCapNhatTTNV);
    }

    private void datHanhDongChoBtnDongGD() {
        btnDongGD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    datDuLieuTTNhanVienVienMacDinh();
                    dispose();
                });
            }
        });
    }

    private void datHanhDongChoBtnCapNhatTTNhanVien(){
        btnCapNhatTTNV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!kiemTraDuLieu())
                    return;

                   int luaChon = JOptionPane.showConfirmDialog(
                           null,
                           "Bạn chắc chắn muốn cập nhật thông tin cho nhân viên này chứ?",
                           "Cảnh báo",
                           JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE
                   );

                   if (luaChon == JOptionPane.YES_OPTION){
                       capNhatThongTinMoi();

                       dispose();

                       if(NhanVienDAO.capNhatThongTinNhanVien(nhanVienHienTai.getMaNV(), nhanVienHienTai)){
                           CacHamDungSan.hienThiThongBaoKetQua(
                                   GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                                   "Cập nhật thông tin nhân viên thành công!"
                           );

                           PnlQLNhanVien.locLaiDuLieuSauKhiThemHoacCapNhat();

                           PnlQLNhanVien.capNhatDSNhanVien();
                       }
                   };
                   }
        });
    }

    private void capNhatThongTinMoi(){
        String soDT = txtSDT.getText().trim();
        String soCMND = txtSoCMND.getText().trim();
        String diChi = txtDiaChi.getText().trim();

        boolean capBac = cbCapBac.getSelectedIndex() == 0;
        boolean trangThaiLamViec = cbTTLamViec.getSelectedIndex() != 0;
        boolean maCaLamViec = cbCaLamViec.getSelectedIndex() == 0;

        nhanVienHienTai.setSoDT(soDT);
        nhanVienHienTai.setSoCMND(soCMND);
        nhanVienHienTai.setDiaChi(diChi);

        nhanVienHienTai.setQuanLi(capBac);
        nhanVienHienTai.setNghiLam(trangThaiLamViec);
        nhanVienHienTai.setCaLamViec(new CaLamViec(maCaLamViec));
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
                    -1,
                    "Vui lòng nhập số điện thoại!"
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

        String maNVHienTai = txtMaNhanVien.getText().trim();

        dsNhanVien.values().forEach(V -> {
            if (!V.getMaNV().equals(maNVHienTai)){
                if (V.getSoDT().equals(sdt)){
                    rsKiemTraKhongTrungSoSDT.set(false);

                    CacHamDungSan.hienThiThongBaoKetQua(
                            -1, "Số điện thoại này đang thuộc sở hữu của nhân viên khác!"
                    );
                    txtSDT.requestFocus();
                }
            }
        });

        return rsKiemTraKhongTrungSoSDT.get();
    }

    private boolean kiemTraCMND(){
        String soCMND = txtSoCMND.getText().trim();

        if(soCMND.isEmpty()){
            CacHamDungSan.hienThiThongBaoKetQua(
                    -1, "Vui lòng nhập số số chứng minh nhân dân!"
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

        String maNVHienTai = txtMaNhanVien.getText().trim();

        dsNhanVien.values().forEach(V -> {
            if (!V.getMaNV().equals(maNVHienTai)){
                if (V.getSoCMND().equals(soCMND)){
                    rsKiemTraKhongTrungSoCMND.set(false);

                    CacHamDungSan.hienThiThongBaoKetQua(
                            -1, "Số CMND này đang thuộc về nhân viên khác!"
                    );
                    txtSoCMND.requestFocus();
                }
            }
        });

        return rsKiemTraKhongTrungSoCMND.get();
    }

    private boolean kiemTraDuLieu(){
        return kiemTraSDT() && kiemTraCMND() && kiemTraDiaChi();
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

    private void datDuLieuTTNhanVienVienMacDinh() {
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtMaNhanVien, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtHoTen, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtDiaChi, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtSDT, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtNgayVaoLam, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtSoCMND, "");

        cbGioiTinh.setSelectedIndex(0);
        cbCaLamViec.setSelectedIndex(0);
        cbCapBac.setSelectedIndex(0);
        cbTTLamViec.setSelectedIndex(0);
    }

    public static void hienThiThongTinNhanVienLenTxt(NhanVien nv) {

        nhanVienHienTai = nv;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(
                "dd/MM/yyyy hh:mm:ss a",
                new Locale("vi", "vn")
        );

        txtMaNhanVien.setText(nv.getMaNV());
        txtHoTen.setText(nv.getHoTen());
        txtSDT.setText(nv.getSoDT());
        txtSoCMND.setText(nv.getSoCMND());
        txtDiaChi.setText(nv.getDiaChi());
        txtNgayVaoLam.setText(dtf.format(nv.getNgayVaoLam()));

        cbCaLamViec.setSelectedIndex((nv.getCaLamViec().isCaSang()) ? 0 : 1);
        cbCapBac.setSelectedIndex((nv.isQuanLi()) ? 0 : 1);
        cbGioiTinh.setSelectedIndex((nv.isNam()) ? 0 : 1);
        cbTTLamViec.setSelectedIndex((nv.isNghiLam()) ? 1 : 0);
    }
}
