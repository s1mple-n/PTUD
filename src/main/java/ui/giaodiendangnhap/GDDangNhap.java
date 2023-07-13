package ui.giaodiendangnhap;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import services.CacHamDungSan;
import ui.cauhinhchung.CaNhanHoaLookAndFeel;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.GDChinh;
import ui.giaodiendoimatkhau.GDDoiMatKhau;
import ui.giaodienquenmatkhau.GDQuenMatKhau;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Map;

public class GDDangNhap extends JFrame implements IDSBienGDDangNhap {
    private static GDDangNhap gdDangNhap = null;

    private GDDangNhap(){

        setTitle(tieuDe);
        setIconImage(
                Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh)
        );

        dungUI();

        setSize(dimGDDangNhap);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        new Thread(GDThongBaoKetQua::getGdThongBaoKetQua).start();;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                txtTenDangNhap.requestFocus();
            }
        });
    }

    public static GDDangNhap getGdDangNhap() {
        if (gdDangNhap == null)
            gdDangNhap = new GDDangNhap();
        return gdDangNhap;
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    public void datTaiKhoanMacDinh(){
        Time thoiDiemHienTai = Time.valueOf(LocalTime.now());

        if (
                thoiDiemHienTai.after(Time.valueOf("08:00:00")) &&
                        thoiDiemHienTai.before(Time.valueOf("16:00:00"))
        ){
            txtTenDangNhap.setText("21100061");
            pwfMatKhau.setText("1111");
        }
        else if (
                thoiDiemHienTai.after(Time.valueOf("16:00:00")) &&
                        thoiDiemHienTai.before(Time.valueOf("23:00:00"))
        ){
            txtTenDangNhap.setText("21100011");
            pwfMatKhau.setText("1111");
        }
    }

    private void dungPnlChinh(){
        pnlChinh.setPreferredSize(dimGDDangNhap);
        pnlChinh.setBackground(bgrMacDinh);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlTieuDe();
        pnlChinh.add(pnlTieuDe);

        dungPnlNoiDung();
        pnlChinh.add(pnlNoiDung);
    }

    private void dungPnlTieuDe(){
        pnlTieuDe.setBackground(bgrMacDinh);
        pnlTieuDe.setPreferredSize(dimPnlTieuDe);
        pnlTieuDe.setLayout(new BoxLayout(
                pnlTieuDe, BoxLayout.Y_AXIS
        ));

        pnlTieuDe.add(Box.createVerticalStrut(30));

        JPanel pnlTmp = new JPanel();
        pnlTmp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDeChinh, fntLblTieuDeChinh, frgLblTieuDeChinh
        );
        pnlTmp.add(lblTieuDeChinh);

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDePhu, fntLblTieuDePhu, frgLblTieuDePhu
        );
        pnlTmp.add(lblTieuDePhu);

        pnlTieuDe.add(pnlTmp);
    }

    private void dungPnlNoiDung(){
        pnlNoiDung.setBackground(bgrMacDinh);
        pnlNoiDung.setPreferredSize(dimPnlNoiDung);
        pnlNoiDung.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        CacHamDungSan.datThuocTinhChoTxt(
                txtTenDangNhap,
                plhTxtTenDangNhap,
                dimTxtTenDangNhapPwfMatKhauBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTenDangNhap);
        datHanhDongChoTxtTenDangNhap();
        pnlNoiDung.add(txtTenDangNhap);

        CacHamDungSan.datThuocTinhChoPwf(
                pwfMatKhau,
                plhPwfMatKhau,
                dimTxtTenDangNhapPwfMatKhauBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoPwf(pwfMatKhau);
        datHanhDongChoPwfMatKhau();
        pnlNoiDung.add(pwfMatKhau);

        CacHamDungSan.datThuocTinhChoBtn(
                btnDangNhap,
                bgrBtnDangNhap,
                IDSBienMacDinh.bgrMacDinh,
                dimTxtTenDangNhapPwfMatKhauBtn
        );
        datHanhDongChoBtnDangNhap();
        pnlNoiDung.add(btnDangNhap);

        CacHamDungSan.datThuocTinhChoLbl(
                lblQuenMatKhau,
                fntLblQuenMatKhau,
                IDSBienMacDinh.frgMacDinh
        );
        datHanhDongChoLblQuenMK();
        pnlNoiDung.add(lblQuenMatKhau);
    }

    private void datHanhDongChoTxtTenDangNhap(){
        txtTenDangNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraTenDangNhap()){
                        pwfMatKhau.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Tên đăng nhập chưa được điền gì nha."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoPwfMatKhau(){
        pwfMatKhau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMatKhau()){
                        thucHienThuTucDangNhap();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Chưa điền mật khẩu kìa."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoBtnDangNhap(){
        btnDangNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thucHienThuTucDangNhap();
            }
        });
    }

    private void datHanhDongChoLblQuenMK() {
        lblQuenMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    duaTxtVaPwfVeTrangThaiBanDau();

                    dispose();

                    GDQuenMatKhau gd = GDQuenMatKhau.getGdQuenMatKhau();
                    gd.setVisible(true);
                    gd.requestFocusInWindow();
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblQuenMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Font fontLbQuenMK = lblQuenMatKhau.getFont();
                Map thuocTinh = fontLbQuenMK.getAttributes();
                thuocTinh.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                lblQuenMatKhau.setFont(fontLbQuenMK.deriveFont(thuocTinh));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblQuenMatKhau.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                lblQuenMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Font fontLbQuenMK = lblQuenMatKhau.getFont();
                Map thuocTinh = fontLbQuenMK.getAttributes();
                thuocTinh.put(TextAttribute.UNDERLINE, -1);
                lblQuenMatKhau.setFont(fontLbQuenMK.deriveFont(thuocTinh));
                lblQuenMatKhau.setFont(fontLbQuenMK.deriveFont(thuocTinh));
            }
        });
    }

    private void duaTxtVaPwfVeTrangThaiBanDau(){
        CacHamDungSan.duaTxtVeTrangThaiBanDau(txtTenDangNhap, plhTxtTenDangNhap);

        CacHamDungSan.duaPwfVeTrangThaiBanDau(pwfMatKhau, plhPwfMatKhau);
    }

    private boolean kiemTraTenDangNhap(){
        String tenDangNhap = txtTenDangNhap.getText().trim();

        if (tenDangNhap.equals("") || tenDangNhap.equals(plhTxtTenDangNhap)){
            txtTenDangNhap.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraMatKhau(){
        String matKhau = new String(pwfMatKhau.getPassword());

        if (matKhau.equals("") || matKhau.equals(plhPwfMatKhau)){
            pwfMatKhau.requestFocus();
            return false;
        }

        return true;
    }

    private void thucHienThuTucDangNhap(){
        if (
            kiemTraTenDangNhap() && kiemTraMatKhau()
        ){
            String tenDangNhap =  txtTenDangNhap.getText().trim();

            boolean ketQuaKiemTraTaiKhoan = TaiKhoanDAO.kiemTraThongTinDangNhap(
                    tenDangNhap,
                    new String(pwfMatKhau.getPassword())
            );

            if (ketQuaKiemTraTaiKhoan){
                NhanVien nhanVien = NhanVienDAO.timNhanVienTheoMa(
                        txtTenDangNhap.getText().trim()
                );

                int trangThaiKichHoatCuaTaiKhoanTuongUng = TaiKhoanDAO.layTrangThaiKichHoatCuaTaiKhoan(
                        tenDangNhap
                );

                Time thoiDiemHienTai = Time.valueOf(LocalTime.now());

                if (nhanVien.getCaLamViec().isCaSang()){
                    if (
                            !(thoiDiemHienTai.after(Time.valueOf("08:00:00")) &&
                            thoiDiemHienTai.before(Time.valueOf("16:00:00")))
                    ){
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Bạn đã vào nhầm ca. Ca làm của bạn không phải lúc này!"
                        );
                        return;
                    }
                }
                else if (!nhanVien.getCaLamViec().isCaSang()){
                    if (
                            !(thoiDiemHienTai.after(Time.valueOf("16:00:00")) &&
                                    thoiDiemHienTai.before(Time.valueOf("22:00:00")))
                    ){
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Bạn đã vào nhầm ca. Ca làm của bạn không phải lúc này!"
                        );
                        return;
                    }
                }


                if (trangThaiKichHoatCuaTaiKhoanTuongUng == 1){
                    SwingUtilities.invokeLater(() -> {
                        dispose();

                        GDChinh gd = GDChinh.getInstance();
                        gd.datNhanVienDangSuDung(nhanVien);
                        gd.setVisible(true);
                    });
                }
                else{
                    dispose();

                    SwingUtilities.invokeLater(() -> {
                        GDDoiMatKhau gdDoiMatKhau = GDDoiMatKhau.getGdDoiMatKhau();

                        gdDoiMatKhau.setCheDoSuDung(GDDoiMatKhau.DOI_MAT_KHAU_BAT_BUOC);
                        gdDoiMatKhau.setTenDangNhap(tenDangNhap);

                        gdDoiMatKhau.setVisible(true);
                        gdDoiMatKhau.requestFocusInWindow();
                    });
                }
            }
            else{
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Tên đăng nhập hoặc mật khẩu chưa đúng. Vui lòng thử lại!"
                );
            }
        }
        else{
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Không đăng nhập được đâu. Có trường thông tin chưa được điền kìa :(("
            );
        }
    }


    public static void main(String[] args) {
        CaNhanHoaLookAndFeel.caNhanHoaLookAndFeel();
        GDDangNhap gd = GDDangNhap.getGdDangNhap();
        gd.setVisible(true);
        gd.requestFocusInWindow();
    }
}
