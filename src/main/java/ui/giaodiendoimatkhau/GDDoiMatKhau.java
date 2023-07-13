package ui.giaodiendoimatkhau;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import ui.giaodienchinh.GDChinh;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GDDoiMatKhau extends JFrame implements IDSBienGDDoiMatKhau {
    private static GDDoiMatKhau gdDoiMatKhau = null;

    public static int DOI_MAT_KHAU_BAT_BUOC = 1;
    public static int DOI_MAT_KHAU_KHONG_BAT_BUOC = -1;

    private int cheDoSuDung = DOI_MAT_KHAU_KHONG_BAT_BUOC;
    private String tenDangNhap;

    private GDDoiMatKhau(){
        setTitle(tieuDe);
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setSize(dimGDDoiMatKhau);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pwfMatKhauCu.requestFocus();
            }
        });
    }

    public static GDDoiMatKhau getGdDoiMatKhau() {
        if (gdDoiMatKhau == null)
            gdDoiMatKhau = new GDDoiMatKhau();
        return gdDoiMatKhau;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setCheDoSuDung(int cheDoSuDung) {
        this.cheDoSuDung = cheDoSuDung;
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setBackground(bgrMacDinh);
        pnlChinh.setPreferredSize(dimGDDoiMatKhau);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlTieuDe();
        pnlChinh.add(pnlTieuDe);

        dungPnlNoiDung();
        pnlChinh.add(pnlNoiDung);
    }

    private void dungPnlTieuDe(){
        pnlTieuDe.setBackground(bgrMacDinh);
        pnlTieuDe.setPreferredSize(dimPnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDe,
                fntLblTieuDe,
                frgMacDinh
        );
        lblTieuDe.setForeground(frgLblTieuDe);
        pnlTieuDe.add(lblTieuDe);
    }

    private void dungPnlNoiDung(){
        pnlNoiDung.setBackground(bgrMacDinh);
        pnlNoiDung.setPreferredSize(dimPnlNoiDung);
        pnlNoiDung.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        CacHamDungSan.datThuocTinhChoPwf(
                pwfMatKhauCu,
                plhMatKhauCu,
                dimPwfBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoPwf(pwfMatKhauCu);
        datHanhDongChoPwfMatKhauCu();
        pnlNoiDung.add(pwfMatKhauCu);

        CacHamDungSan.datThuocTinhChoPwf(
                pwfMatKhauMoi,
                plhMatKhauMoi,
                dimPwfBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoPwf(pwfMatKhauMoi);
        datHanhDongChoPwfMatKhauMoi();
        pnlNoiDung.add(pwfMatKhauMoi);

        CacHamDungSan.datThuocTinhChoPwf(
                pwfXacNhanMatKhauMoi,
                plhXacNhanMatKhauMoi,
                dimPwfBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoPwf(pwfXacNhanMatKhauMoi);
        datHanhDongChoPwfXacNhanMatKhau();
        pnlNoiDung.add(pwfXacNhanMatKhauMoi);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXacNhan,
                bgrBtnXacNhan,
                bgrMacDinh,
                dimPwfBtn
        );
        datHanhDongChoBtnXacNhan();
        pnlNoiDung.add(btnXacNhan);
    }

    private void datHanhDongChoPwfMatKhauCu(){
        pwfMatKhauCu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMatKhauCu()){
                        if (kiemTraMatKhauDaDienGiongVoiMatKhauTrongCSDL()){
                            pwfMatKhauMoi.requestFocus();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Mật khẩu này không giống với mật khẩu cũ của bạn."
                            );
                        }
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Trường mật khẩu cũ chưa được điền gì hết trơn :))"
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoPwfMatKhauMoi(){
        pwfMatKhauMoi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMatKhauMoi()){
                        if (kiemTraMatKhauMoiBiTrungSoVoiMatKhauCu()){
                            pwfXacNhanMatKhauMoi.requestFocus();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Đừng để mật khẩu mới giống với mật khẩu cũ bạn nhé!"
                            );
                        }
                    }
                    else{
                       CacHamDungSan.hienThiThongBaoKetQua(
                               GDThongBaoKetQua.THONG_BAO_LOI,
                               "Trường mật khẩu mới đã được điền gì đâu :))"
                       );
                    }
                }
            }
        });
    }

    private void datHanhDongChoPwfXacNhanMatKhau(){
        pwfXacNhanMatKhauMoi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMatKhauXacNhan()){
                        if (kiemTraMatKhauXacNhanTrungVoiMatKhauMoi()){
                            thucHienThuTucDoiMatKhau();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Mật khẩu được xác nhận không giống với mật khẩu mới."
                            );
                        }
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Trường xác nhận mật khẩu trống trơn nha.))"
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoBtnXacNhan(){
        btnXacNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thucHienThuTucDoiMatKhau();
            }
        });
    }

    private boolean kiemTraMatKhauCu(){
        String mkCu = new String(pwfMatKhauCu.getPassword());

        if (mkCu.isEmpty() || mkCu.equals(plhMatKhauCu)){
            pwfMatKhauCu.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraMatKhauDaDienGiongVoiMatKhauTrongCSDL(){
        String mkCu = TaiKhoanDAO.layMatKhauChuaGiaiMaTheoTenDangNhap(
                tenDangNhap
        );

        if (mkCu.isEmpty() || !MaHoaDuLieu.giaiMa(mkCu).equals(new String(pwfMatKhauCu.getPassword()))){
            pwfMatKhauCu.requestFocus();
            pwfMatKhauCu.setText("");
            return false;
        }

        return true;
    }

    private boolean kiemTraMatKhauMoi(){
        String mkMoi = new String(pwfMatKhauMoi.getPassword());

        if (mkMoi.isEmpty() || mkMoi.equals(plhMatKhauMoi)){
            pwfMatKhauMoi.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraMatKhauMoiBiTrungSoVoiMatKhauCu(){
        String mkCu = new String(pwfMatKhauCu.getPassword());
        String mkMoi = new String(pwfMatKhauMoi.getPassword());

        if (mkMoi.equals(mkCu)){
            pwfMatKhauMoi.requestFocus();
            pwfMatKhauMoi.setText("");
            return false;
        }

        return true;
    }

    private boolean kiemTraMatKhauXacNhan(){
        String mkXacNhan = new String(pwfXacNhanMatKhauMoi.getPassword());

        if (mkXacNhan.isEmpty() || mkXacNhan.equals(plhXacNhanMatKhauMoi)){
            pwfXacNhanMatKhauMoi.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraMatKhauXacNhanTrungVoiMatKhauMoi(){
        String mkMoi = new String(pwfMatKhauMoi.getPassword());
        String xacNhanMKMoi = new String(pwfXacNhanMatKhauMoi.getPassword());

        if (!xacNhanMKMoi.equals(mkMoi)){
            pwfXacNhanMatKhauMoi.requestFocus();
            pwfXacNhanMatKhauMoi.setText("");
            return false;
        }

        return true;
    }

    private void thucHienThuTucDoiMatKhau(){
        if (
                kiemTraMatKhauCu() && kiemTraMatKhauDaDienGiongVoiMatKhauTrongCSDL()
                && kiemTraMatKhauMoi() && kiemTraMatKhauMoiBiTrungSoVoiMatKhauCu()
                && kiemTraMatKhauXacNhan() && kiemTraMatKhauXacNhanTrungVoiMatKhauMoi()
        ){
            boolean rsDoiMatKhau = TaiKhoanDAO.doiMatKhau(tenDangNhap, new String(pwfXacNhanMatKhauMoi.getPassword()));

            if (rsDoiMatKhau){
                if (cheDoSuDung == DOI_MAT_KHAU_KHONG_BAT_BUOC){
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                            "Đã cập nhật mật khẩu mới cho nhân viên có mã " + tenDangNhap + "."
                    );
                    new Thread(this::datCacPwfTroVeTrangThaiBanDau).start();
                }
                else if (cheDoSuDung == DOI_MAT_KHAU_BAT_BUOC){
                    new Thread(() -> {
                        TaiKhoanDAO.capNhatTrangThaiKichHoatLaDaKichHoatChoTaiKhoan(tenDangNhap);
                    }).start();

                    NhanVien nhanVien = NhanVienDAO.timNhanVienTheoMa(tenDangNhap);

                    SwingUtilities.invokeLater(() -> {
                        GDChinh gd = GDChinh.getInstance();
                        gd.datNhanVienDangSuDung(nhanVien);
                        gd.setVisible(true);
                        gd.requestFocusInWindow();
                    });
                }

                new Thread(this::dispose).start();
            }
            else{
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Rất tiếc. Đã xảy ra lỗi. Hãy thử lại sau."
                );
            }
        }
        else{
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Chà chà. Có một số lỗi đã được phát hiện. Bạn hãy sửa chúng theo các trường được đánh dấu nhé."
            );
        }
    }

    private void datCacPwfTroVeTrangThaiBanDau(){
        CacHamDungSan.duaPwfVeTrangThaiBanDau(pwfMatKhauCu, plhMatKhauCu);
        CacHamDungSan.duaPwfVeTrangThaiBanDau(pwfMatKhauMoi, plhMatKhauMoi);
        CacHamDungSan.duaPwfVeTrangThaiBanDau(pwfXacNhanMatKhauMoi, plhXacNhanMatKhauMoi);
    }
}
