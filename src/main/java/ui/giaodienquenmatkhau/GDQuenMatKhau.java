package ui.giaodienquenmatkhau;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import services.CacHamDungSan;
import ui.giaodiendangnhap.GDDangNhap;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GDQuenMatKhau extends JFrame implements IDSBienGDQuenMatKhau {
    private static GDQuenMatKhau gdQuenMatKhau = null;
    private NhanVien nhanVien = null;

    private GDQuenMatKhau(){
        setTitle(tieuDe);
        setIconImage(
                Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh)
        );

        dungUI();

        setSize(dimGDQuenMatKhau);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                txtMaNhanVien.requestFocus();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    quayVeGDDangNhap();
                });
            }
        });
    }

    public static GDQuenMatKhau getGdQuenMatKhau() {
        if (gdQuenMatKhau == null)
            gdQuenMatKhau = new GDQuenMatKhau();
        return gdQuenMatKhau;
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setPreferredSize(dimGDQuenMatKhau);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0 ,0));
        pnlChinh.setBackground(bgrMacDinh);

        dungPnlTieuDe();
        pnlChinh.add(pnlTieuDe);

        dungPnlNoiDung();
        pnlChinh.add(pnlNoiDung);
    }

    private void dungPnlTieuDe(){
        pnlTieuDe.setBackground(bgrMacDinh);
        pnlTieuDe.setPreferredSize(dimPnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDe,
                fntLblTieuDe,
                frgLblTieuDe
        );
        pnlTieuDe.add(lblTieuDe);
    }

    private void dungPnlNoiDung(){
        pnlNoiDung.setBackground(bgrMacDinh);
        pnlNoiDung.setPreferredSize(dimPnlNoiDung);
        pnlNoiDung.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        CacHamDungSan.datThuocTinhChoTxt(
                txtMaNhanVien,
                plhTxtMaNhanVien,
                dimTxtBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtMaNhanVien);
        datHanhDongChoTxtMaNhanVien();
        pnlNoiDung.add(txtMaNhanVien);

        CacHamDungSan.datThuocTinhChoTxt(
                txtSoCMND,
                plhTxtSoCMND,
                dimTxtBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSoCMND);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoCMND);
        datHanhDongChoTxtSoCMND();
        pnlNoiDung.add(txtSoCMND);

        CacHamDungSan.datThuocTinhChoTxt(
                txtSoDienThoai,
                plhTxtSoDienThoai,
                dimTxtBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSoDienThoai);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoDienThoai);
        datHanhDongChoTxtSoDienThoai();
        pnlNoiDung.add(txtSoDienThoai);

        CacHamDungSan.datThuocTinhChoPwf(
                pwfMatKhau,
                plhPwfMatKhau,
                dimTxtBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoPwf(pwfMatKhau);
        datHanhDongChoPwfMatKhau();
        pnlNoiDung.add(pwfMatKhau);

        CacHamDungSan.datThuocTinhChoPwf(
                pwfXacNhanMK,
                plhPwfXacNhanMK,
                dimTxtBtn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoPwf(pwfXacNhanMK);
        datHanhDongChoPwfXacNhanMatKhau();
        pnlNoiDung.add(pwfXacNhanMK);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXacNhan,
                bgrBtnXacNhan,
                bgrMacDinh,
                dimTxtBtn
        );
        datHanhDongChoBtnXacNhan();
        pnlNoiDung.add(btnXacNhan);
    }

    private void datHanhDongChoTxtMaNhanVien(){
        txtMaNhanVien.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMaNhanVien()){
                        if (kiemTraSuTonTaiCuaMaNhanVien()){
                            txtSoCMND.requestFocus();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Mã nhân viên này lạ lắm. Cả cửa hàng không ai có mã nhân viên như thế cả."
                            );
                        }
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Mã nhân viên đã được điền gì đâu :(("
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtSoCMND(){
        txtSoCMND.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraSoCMND()){
                        if (kiemTraSuChinhXacCuaSoCMND()){
                            txtSoDienThoai.requestFocus();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Số chứng minh nhân dân này không thuộc sở hữu của nhân viên nào ở đây cả."
                            );
                        }
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Số chứng minh nhân dân trống trơn kìa :(("
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtSoDienThoai(){
        txtSoDienThoai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraSoDienThoai()){
                        if (kiemTraSuTonTaiCuaSoDienThoai()){
                            pwfMatKhau.requestFocus();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Số điện thoại này không thuộc sở hữu của nhân viên nào ở đây cả."
                            );
                        }
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Trường số điện thoại trống trơn kìa :(("
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
                        pwfXacNhanMK.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Đừng để trường mật khẩu rỗng."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoPwfXacNhanMatKhau(){
        pwfXacNhanMK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMatKhauXacNhan()){
                        if (kiemTraSuTrungKhopCuaHaiTruongMatKhau()){
                            thucHienThuTucKhoiPhucMatKhau();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Hai trường mật khẩu không khớp nhau."
                            );
                        }
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Đừng để trường xác nhận mật khẩu rỗng."
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
                SwingUtilities.invokeLater(() -> {
                    thucHienThuTucKhoiPhucMatKhau();
                });
            }
        });
    }

    private void quayVeGDDangNhap(){
        SwingUtilities.invokeLater(() -> {
            datCacTxtVaPwfVeTrangThaiBanDau();

            dispose();

            GDDangNhap gd = GDDangNhap.getGdDangNhap();
            gd.setVisible(true);
            gd.requestFocusInWindow();
        });
    }

    private void datCacTxtVaPwfVeTrangThaiBanDau(){
        CacHamDungSan.duaTxtVeTrangThaiBanDau(txtMaNhanVien, plhTxtMaNhanVien);

        CacHamDungSan.duaTxtVeTrangThaiBanDau(txtSoCMND, plhTxtSoCMND);

        CacHamDungSan.duaTxtVeTrangThaiBanDau(txtSoDienThoai, plhTxtSoDienThoai);

        CacHamDungSan.duaPwfVeTrangThaiBanDau(pwfMatKhau, plhPwfMatKhau);

        CacHamDungSan.duaPwfVeTrangThaiBanDau(pwfXacNhanMK, plhPwfXacNhanMK);
    }

    private boolean kiemTraMaNhanVien(){
        String maNhanVien = txtMaNhanVien.getText().trim();

        if (maNhanVien.isEmpty() || maNhanVien.equals(plhTxtMaNhanVien)){
            txtMaNhanVien.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraSuTonTaiCuaMaNhanVien(){
        String maNhanVien = txtMaNhanVien.getText().trim();

        NhanVien nv = NhanVienDAO.timNhanVienTheoMa(maNhanVien);

        if (nv == null){
            txtMaNhanVien.requestFocus();
            return false;
        }

        nhanVien = nv;
        System.out.println(nhanVien);
        return true;
    }

    private boolean kiemTraSoCMND(){
        String soCMND = txtSoCMND.getText().trim();

        if (soCMND.isEmpty() || soCMND.equals(plhTxtSoCMND)){
            txtSoCMND.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraSuChinhXacCuaSoCMND(){
        String soCMND = txtSoCMND.getText().trim();

        if (nhanVien != null){
            if (!soCMND.equals(nhanVien.getSoCMND())){
                txtSoCMND.requestFocus();
                return false;
            }
        }
        else{
            NhanVien nv = NhanVienDAO.timNhanVienTheoSoCMND(soCMND);

            if (nv == null){
                txtSoCMND.requestFocus();
                return false;
            }
            else{
                nhanVien = nv;
            }
        }

        return true;
    }

    private boolean kiemTraSoDienThoai(){
        String soDT = txtSoDienThoai.getText().trim();

        if (soDT.equals("") || soDT.equals(plhTxtSoDienThoai)){
            txtSoDienThoai.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraSuTonTaiCuaSoDienThoai(){
        String soDT = txtSoDienThoai.getText().trim();

        if (nhanVien != null){
            if (!soDT.equals(nhanVien.getSoDT())){
                txtSoDienThoai.requestFocus();
                return false;
            }
        }
        else{
            NhanVien nv = NhanVienDAO.timNhanVienTheoSoDienThoai(soDT);

            if (nv == null){
                txtSoDienThoai.requestFocus();
                return false;
            }
            else{
                nhanVien = nv;
            }
        }

        return true;
    }

    private boolean kiemTraMatKhau(){
        String matKhau = new String(pwfMatKhau.getPassword());

        if (matKhau.isEmpty() || matKhau.equals(plhPwfMatKhau)){
            pwfMatKhau.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraMatKhauXacNhan(){
        String matKhauXacNhan = new String(pwfXacNhanMK.getPassword());

        if (matKhauXacNhan.isEmpty() || matKhauXacNhan.equals(plhPwfXacNhanMK)){
            pwfXacNhanMK.requestFocus();
            return false;
        }
        return true;
    }

    private boolean kiemTraSuTrungKhopCuaHaiTruongMatKhau(){
        String mk = new String(pwfMatKhau.getPassword());
        String mkXacNhan = new String(pwfXacNhanMK.getPassword());

        if (!mkXacNhan.equals(mk)){
            pwfXacNhanMK.requestFocus();
            return false;
        }

        return true;
    }

    private void thucHienThuTucKhoiPhucMatKhau(){
        if (
            kiemTraMaNhanVien() && kiemTraSuTonTaiCuaMaNhanVien()
            && kiemTraSoCMND() && kiemTraSuChinhXacCuaSoCMND()
            && kiemTraSoDienThoai() && kiemTraSuTonTaiCuaSoDienThoai()
            && kiemTraMatKhau() && kiemTraMatKhauXacNhan()
            && kiemTraSuTrungKhopCuaHaiTruongMatKhau()
        ){
            boolean rsCapNhatMK = TaiKhoanDAO.capNhatMatKhau(
                    txtMaNhanVien.getText().trim(),
                    new String(pwfXacNhanMK.getPassword())
            );

            if (rsCapNhatMK){
                new Thread(() -> {
                    dispose();

                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                            "Đã cập nhật mật khẩu mới thành công."
                    );
                }).start();

                quayVeGDDangNhap();
            }
            else{
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Rất tiếc. Đã có lỗi xảy ra. Vui lòng thử lại"
                );
            }
        }
        else{
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Chà chà, có một số trường chưa hợp lệ. Hãy sửa lại theo trình tự nhé."
            );
        }
    }
}
