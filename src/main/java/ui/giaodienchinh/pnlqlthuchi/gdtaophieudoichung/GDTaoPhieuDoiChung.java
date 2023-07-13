package ui.giaodienchinh.pnlqlthuchi.gdtaophieudoichung;

import dao.NhanVienDAO;
import dao.PhieuDoiChungDAO;
import entity.NhanVien;
import entity.PhieuDoiChung;
import services.CacHamDungSan;
import services.TienIch;
import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlthuchi.PnlQLThuChi;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

public class GDTaoPhieuDoiChung extends JDialog implements IDSBienGDTaoPhieuDoiChung {
    /**
     * <p>Số tiền định mức nếu ca làm việc của nhân viên lập phiếu đc là ca sáng</p>
     * <p>Mặc định là 20 triệu đồng cho ca sáng</p>
     */
    private static final double SOTIENDINHMUCCHONVCASANG = 20000000;

    public GDTaoPhieuDoiChung() {
        setTitle("Tạo phiếu đối chứng");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setModal(true);
        setUndecorated(true);
        setSize(kichThuocGDTaoPhieuDoiChung);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        hienThiLenNhungThongTinMacDinhDaDuocTinhSan();
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

        dungPanelChuaTTSoTienHHTinh();
        panelNoiDungPhieu.add(panelChuaTTSoTienHeThongTinh);

        dungPanelChuaTTNhanVienLapPhieu();
        panelNoiDungPhieu.add(panelChuaTTNhanVienLapPhieu);

        dungPanelChuaTTNhanVienKiemPhieu();
        panelNoiDungPhieu.add(panelChuaTTNhanVienKiemPhieu);
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
        panel.add(txt);
    }

    private void dungPanelChuaTTSoTienHHTinh() {
        panelChuaTTSoTienHeThongTinh.setBackground(mauNenMacDinh);
        panelChuaTTSoTienHeThongTinh.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaTTSoTienHeThongTinh.setPreferredSize(kichThuocPanelChuaMaPhieuDCVaTGLap);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienBanDau, lbSoTienBanDau, txtSoTienBanDau);
        txtSoTienBanDau.setEditable(false);
        txtSoTienBanDau.setForeground(frgMacDinh);
        panelChuaTTSoTienHeThongTinh.add(panelSoTienBanDau);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienConLaiHTTinh, lbSoTienConLaiHTTinh, txtSoTienConLaiHTTinh);
        txtSoTienConLaiHTTinh.setEditable(false);
        txtSoTienConLaiHTTinh.setForeground(frgMacDinh);
        panelChuaTTSoTienHeThongTinh.add(panelSoTienConLaiHTTinh);
    }

    private void dungPanelChuaTTNhanVienLapPhieu() {
        panelChuaTTNhanVienLapPhieu.setBackground(mauNenMacDinh);
        panelChuaTTNhanVienLapPhieu.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaTTNhanVienLapPhieu.setPreferredSize(kichThuocPanelChuaMaPhieuDCVaTGLap);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelMaNhanVienLP, lbMaNhanVienLP, txtMaNhanVienLP);
        txtMaNhanVienLP.setEditable(false);
        txtMaNhanVienLP.setForeground(frgMacDinh);
        panelChuaTTNhanVienLapPhieu.add(panelMaNhanVienLP);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienNVLPTinh, lbSoTienNVLPTinh, txtSoTienNVLPTinh);
        datHanhDongChoTxtSoTienNVLPTinh();
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSoTienNVLPTinh);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoTienNVLPTinh);
        panelChuaTTNhanVienLapPhieu.add(panelSoTienNVLPTinh);
    }

    private void dungPanelChuaTTNhanVienKiemPhieu() {
        panelChuaTTNhanVienKiemPhieu.setBackground(mauNenMacDinh);
        panelChuaTTNhanVienKiemPhieu.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelChuaTTNhanVienKiemPhieu.setPreferredSize(kichThuocPanelChuaMaPhieuDCVaTGLap);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelMaNhanVienKP, lbMaNhanVienKP, txtMaNhanVienKP);
        datHanhDongChoTxtMaNVKiemPhieu();
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtMaNhanVienKP);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtMaNhanVienKP);
        panelChuaTTNhanVienKiemPhieu.add(panelMaNhanVienKP);

        dungPanelConCuaPanelChuaMaPhieuVaTGLap(panelSoTienNVKPTinh, lbSoTienNVKPTinh, txtSoTienNVKPTinh);
        datHanhDongChoTxtSoTienNVKPTinh();
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoTienNVKPTinh);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSoTienNVKPTinh);
        panelChuaTTNhanVienKiemPhieu.add(panelSoTienNVKPTinh);
    }

    private void dungPanelChuaCacBtn() {
        panelChuaCacBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelChuaCacBtn.setPreferredSize(kichThuocPanelChuaCacBtn);
        panelChuaCacBtn.setBackground(mauNenMacDinh);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                null,
                frgMacDinh,
                kichThuocCacBtn
        );
        datHanhDongChoBtnThoat();
        panelChuaCacBtn.add(btnThoat);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXacNhanTaoPhieuDC,
                mauNenBtnXacNhanTaoPhieuDC,
                mauNenMacDinh,
                kichThuocCacBtn
        );
        datHanhDongChoBtnTaoPhieuDoiChung();

        panelChuaCacBtn.add(btnXacNhanTaoPhieuDC);
    }

    private void datHanhDongChoBtnThoat() {
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    dispose();
                });
            }
        });
    }

    private void datHanhDongChoBtnTaoPhieuDoiChung() {
        btnXacNhanTaoPhieuDC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NhanVien nvKiemPhieu =NhanVienDAO.timNhanVienTheoMa(txtMaNhanVienKP.getText().trim());

                if (
                        kiemTraSoTienNVLPTinh() &&
                                kiemTraMaNVKiemPhieu() &&
                                kiemTraSuTrungLapCuaHaiMaNhanVienLapVaKiem() &&
                                kiemTraSuTonTaiCuaMaNVKiemPhieu(nvKiemPhieu) &&
                                kiemTraCaLamCuaNVKiemPhieu(nvKiemPhieu) &&
                                kiemTraSoTienNVKPTinh()
                ){
                    String maNVLapPhieu = txtMaNhanVienLP.getText().trim();
                    double soTienNVLapPhieuTinh = TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(txtSoTienNVLPTinh);

                    String maNVKiemPhieu = txtMaNhanVienKP.getText().trim();
                    double soTienNVKiemPhieuTinh = TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(txtSoTienNVKPTinh);

                    boolean kqThemPhieuDoiChung = PhieuDoiChungDAO.themPhieuDoiChung(
                            new PhieuDoiChung(
                                    new NhanVien(maNVLapPhieu),
                                    new NhanVien(maNVKiemPhieu),
                                    LocalDateTime.now(),
                                    soTienBanDau.get(),
                                    soTienConLaiTrongKetHTTinh.get(),
                                    soTienNVLapPhieuTinh,
                                    soTienNVKiemPhieuTinh
                            )
                    );

                    if (kqThemPhieuDoiChung){
                       CacHamDungSan.hienThiThongBaoKetQua(
                               GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                               "Đã lưu phiếu đối chứng cho ca làm này. " +
                                       "Nhớ lập nhật kí bán hàng theo ca trước khi đăng xuất nhé."
                       );
                       dispose();

                       PnlQLThuChi.anMniLapPhieuDoiChung();

                       PnlQLThuChi.anPnlTaoPhieuDoiChung();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Đã có lỗi xảy ra."
                        );
                    }
                }
                else{
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_LOI,
                            "Có trường thông tin không phù hợp. Vui lòng kiểm tra lại."
                    );
                }
            }
        });
    }

    private void hienThiLenNhungThongTinMacDinhDaDuocTinhSan(){
        NhanVien nvLapPhieu = GDChinh.getNhanVienDangSuDung();

        hienThiMaNhanVienLapPhieu(nvLapPhieu);

        hienThiSoTienBanDauVaSoTienConLaiHTTinh(nvLapPhieu);

        txtSoTienNVLPTinh.requestFocus();
    }

    private void hienThiMaNhanVienLapPhieu(NhanVien nvLapPhieu){
        txtMaNhanVienLP.setText(nvLapPhieu.getMaNV());
    }

    private void hienThiSoTienBanDauVaSoTienConLaiHTTinh(NhanVien nvLapPhieu){
        if (nvLapPhieu.getCaLamViec().isCaSang()){
            double soTienConLaiTrongKetSauKhiHetCa = PhieuDoiChungDAO.xacDinhSoTienConLaiTrongKetSauKhiKetThucCaLam(
                    nvLapPhieu.getMaNV(),
                    SOTIENDINHMUCCHONVCASANG
            );

            txtSoTienBanDau.setText(
                    nf.format(SOTIENDINHMUCCHONVCASANG)
            );

            txtSoTienConLaiHTTinh.setText(
                    nf.format(soTienConLaiTrongKetSauKhiHetCa)
            );

            soTienBanDau.set(SOTIENDINHMUCCHONVCASANG);
            soTienConLaiTrongKetHTTinh.set(soTienConLaiTrongKetSauKhiHetCa);
        }
        else{
            double soTienDuocThuaKeTuCaTruoc = PhieuDoiChungDAO.xacDinhSoTienNVCaSangGiaoLaiChoNVCaToi();

            if (soTienDuocThuaKeTuCaTruoc != 0.0){
                double soTienConLaiTrongKetSauKhiHetCa = PhieuDoiChungDAO.xacDinhSoTienConLaiTrongKetSauKhiKetThucCaLam(
                        nvLapPhieu.getMaNV(),
                        soTienDuocThuaKeTuCaTruoc
                );

                txtSoTienBanDau.setText(nf.format(soTienDuocThuaKeTuCaTruoc));
                txtSoTienConLaiHTTinh.setText(
                        nf.format(soTienConLaiTrongKetSauKhiHetCa)
                );

                soTienBanDau.set(soTienDuocThuaKeTuCaTruoc);
                soTienConLaiTrongKetHTTinh.set(soTienConLaiTrongKetSauKhiHetCa);
            }
            else{
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Tạm thời không xác định được số tiền ca trước đã giao. " +
                                "Hãy liên lạc ngay với người quản lí để xác nhận sự cố nhé."
                );

                txtSoTienBanDau.setText(nf.format(SOTIENDINHMUCCHONVCASANG));
                txtSoTienConLaiHTTinh.setText(nf.format(SOTIENDINHMUCCHONVCASANG));

                soTienBanDau.set(SOTIENDINHMUCCHONVCASANG);
                soTienConLaiTrongKetHTTinh.set(SOTIENDINHMUCCHONVCASANG);
            }
        }
    }

    private void datHanhDongChoTxtSoTienNVLPTinh(){
        txtSoTienNVLPTinh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraSoTienNVLPTinh()){
                        txtMaNhanVienKP.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Số tiền nhân viên lập phiếu tính không được rỗng."
                        );
                    }
                }
                else{
                    String s = txtSoTienNVLPTinh.getText().trim()
                            .replace(".", "")
                            .replace(",", "");

                    txtSoTienNVLPTinh.setText(
                            df.format(Double.parseDouble(s))
                    );
                }
            }
        });
    }

    private void datHanhDongChoTxtMaNVKiemPhieu(){
        txtMaNhanVienKP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (!kiemTraMaNVKiemPhieu()){
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Mã nhân viên kiểm phiếu không được rỗng."
                        );
                    }
                    else{
                        NhanVien nvKiemPhieu = NhanVienDAO.timNhanVienTheoMa(txtMaNhanVienKP.getText().trim());

                        if (kiemTraSuTrungLapCuaHaiMaNhanVienLapVaKiem()){
                            if (!kiemTraSuTonTaiCuaMaNVKiemPhieu(nvKiemPhieu)){
                                CacHamDungSan.hienThiThongBaoKetQua(
                                        GDThongBaoKetQua.THONG_BAO_LOI,
                                        "Nhân viên kiểm phiếu có mã " +
                                                txtMaNhanVienKP.getText().trim() +
                                                " không tồn tại."
                                );
                            }
                            else{
                                if (kiemTraCaLamCuaNVKiemPhieu(nvKiemPhieu)){
                                    txtSoTienNVKPTinh.requestFocus();
                                }
                                else{
                                    CacHamDungSan.hienThiThongBaoKetQua(
                                            GDThongBaoKetQua.THONG_BAO_LOI,
                                            "Nhân viên kiểm phiếu có mã " +
                                                    txtMaNhanVienKP.getText().trim() +
                                                    " là một nhân viên làm ca sáng, " +
                                                    "không thế tham gia xác nhận cho phiếu đối chứng này."
                                    );
                                }
                            }
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Hai mã nhân viên lập và kiểm phiếu đối chứng không thể cùng là một người"
                            );
                        }
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtSoTienNVKPTinh(){
        txtSoTienNVKPTinh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (!kiemTraSoTienNVKPTinh()){
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Số tiền nhân viên kiểm phiếu tính không được rỗng."
                        );
                    }
                }
                else{
                    String s = txtSoTienNVKPTinh.getText().trim().replace(".", "");

                    txtSoTienNVKPTinh.setText(
                            df.format(Double.parseDouble(s))
                    );
                }
            }
        });
    }

    private boolean kiemTraSoTienNVLPTinh(){
        if (txtSoTienNVLPTinh.getText().trim().isEmpty()){
            txtSoTienNVLPTinh.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraSoTienNVKPTinh(){
        if (txtSoTienNVKPTinh.getText().trim().isEmpty()){
            txtSoTienNVKPTinh.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraMaNVKiemPhieu(){
        if (txtMaNhanVienKP.getText().trim().isEmpty()){
            txtMaNhanVienKP.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraSuTrungLapCuaHaiMaNhanVienLapVaKiem(){
        String maNVLapPhieu = txtMaNhanVienLP.getText().trim();
        String maNVKiemPhieu = txtMaNhanVienKP.getText().trim();

        if (maNVLapPhieu.equals(maNVKiemPhieu)) {
            txtMaNhanVienKP.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraSuTonTaiCuaMaNVKiemPhieu(NhanVien nvKiemPhieu){
        if (nvKiemPhieu == null){
            txtMaNhanVienKP.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraCaLamCuaNVKiemPhieu(NhanVien nvKiemPhieu){
        if (nvKiemPhieu.getCaLamViec().isCaSang()){
            txtMaNhanVienKP.requestFocus();

            return false;
        }

        return true;
    }
}
