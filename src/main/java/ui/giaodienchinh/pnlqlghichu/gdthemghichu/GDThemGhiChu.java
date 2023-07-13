package ui.giaodienchinh.pnlqlghichu.gdthemghichu;

import dao.CaLamViecDAO;
import ui.giaodienchinh.pnlqlghichu.kieudulieudacbiet.GhiChu;
import services.CacHamDungSan;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlghichu.PnlQLGhiChu;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.time.LocalTime;

public class GDThemGhiChu extends JDialog implements IDSBienGDThemGhiChu {
    private static GDThemGhiChu gdThemGhiChu = null;

    private GDThemGhiChu(){
        setTitle("Thêm ghi chú");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setSize(dimGDThemGhiChu);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                duaCacThanhPhanTroVeTrangThaiBanDau();
                dispose();
            }
        });
    }

    public static GDThemGhiChu getGdThemGhiChu() {
        if (gdThemGhiChu == null)
            gdThemGhiChu = new GDThemGhiChu();
        return gdThemGhiChu;
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setBackground(bgrMacDinh);
        pnlChinh.setPreferredSize(dimGDThemGhiChu);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlTieuDe();
        pnlChinh.add(pnlTieuDe);

        dungPnlNoiDung();
        pnlChinh.add(pnlNoiDung);
    }

    private void dungPnlTieuDe(){
        pnlTieuDe.setBackground(bgrMacDinh);
        pnlTieuDe.setPreferredSize(dimPnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        lblTieuDe.setForeground(frgLblTieuDe);
        lblTieuDe.setFont(fntLblTieuDe);
        pnlTieuDe.add(lblTieuDe);
    }

    private void dungPnlNoiDung(){
        pnlNoiDung.setPreferredSize(dimPnlNoiDung);
        pnlNoiDung.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDung.setBackground(bgrMacDinh);

        dungPnlChuDe();
        pnlNoiDung.add(pnlChuDe);

        dungPnlHanThucHien();
        pnlNoiDung.add(pnlHanThucHien);

        dungPnlNoiDungGhiChu();
        pnlNoiDung.add(pnlNoiDungGhiChu);

        dungPnlChuaBtn();
        pnlNoiDung.add(pnlChuaBtn);
    }

    private void dungPnlChuDe(){
        pnlChuDe.setBackground(bgrMacDinh);
        pnlChuDe.setPreferredSize(dimPnlChuDe);
        pnlChuDe.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.datThuocTinhChoLbl(lblChuDe, fntMacDinh, frgMacDinh);
        pnlChuDe.add(lblChuDe);

        pnlHanThucHien.add(Box.createVerticalStrut(3));

        CacHamDungSan.datThuocTinhChoTxt(
                txtChuDe,
                "",
                dimTxt
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtChuDe);
        datHanhDongChoTxtChuDe();

        pnlChuDe.add(Box.createVerticalStrut(3));
        pnlChuDe.add(txtChuDe);
    }

    private void datHanhDongChoTxtChuDe(){
        txtChuDe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraChuDe()){
                        tmpHanThucHien.getComponentTimeTextField().requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Đừng để chủ đề ghi chú bị rỗng!"
                        );
                    }
                }
            }
        });
    }

    private void dungPnlHanThucHien(){
        pnlHanThucHien.setBackground(bgrMacDinh);
        pnlHanThucHien.setPreferredSize(dimPnlChuDe);
        pnlHanThucHien.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.datThuocTinhChoLbl(lblHanThucHien, fntMacDinh, frgMacDinh);
        pnlHanThucHien.add(lblHanThucHien);

        pnlHanThucHien.add(Box.createVerticalStrut(3));

        datHanhDongChoTmpHanThucHien();
        pnlHanThucHien.add(tmpHanThucHien);
    }

    private void datHanhDongChoTmpHanThucHien(){
        tmpHanThucHien.getComponentTimeTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraHanThucHien()){
                        if (kiemTraSuHopLeCuaHanThucHien()){
                            txaNoiDungGhiChu.requestFocus();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Hạn thực hiện phải xảy ra trước khi bạn kết thúc ca làm và " +
                                            "bắt đầu sau thời điểm hiện tại!"
                            );
                        }
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Hạn thực hiện không đúng với quy chuẩn giờ của Việt Nam."
                        );
                    }
                }
            }
        });
    }

    private void dungPnlNoiDungGhiChu(){
        pnlNoiDungGhiChu.setBackground(bgrMacDinh);
        pnlNoiDungGhiChu.setPreferredSize(dimPnlNoiDungGhiChu);
        pnlNoiDungGhiChu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.datThuocTinhChoLbl(lblNoiDungGhiChu, fntMacDinh, frgMacDinh);
        pnlNoiDungGhiChu.add(lblNoiDungGhiChu);

        pnlHanThucHien.add(Box.createVerticalStrut(3));

        CacHamDungSan.datThuocTinhChoTxa(txaNoiDungGhiChu, null, true);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxa(txaNoiDungGhiChu);
        datHanhDongChoTxaNoiDungGhiChu();
        scrNoiDungGhiChu.setPreferredSize(dimTxa);

        pnlNoiDungGhiChu.add(scrNoiDungGhiChu);
    }

    private void datHanhDongChoTxaNoiDungGhiChu(){
        txaNoiDungGhiChu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraNoiDungGhiChu()){
                        thucHienThuTucThemGhiChuMoi();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Đừng để nội dung ghi chú bị rỗng nhé."
                        );
                    }
                }
            }
        });
    }

    private void dungPnlChuaBtn(){
        pnlChuaBtn.setPreferredSize(dimPnlChuaBtn);
        pnlChuaBtn.setBackground(bgrMacDinh);
        pnlChuaBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        CacHamDungSan.datThuocTinhChoBtn(
                btnThemGhiChu,
                IDSBienMacDinh.bgrBtnXuatDuLieuRaFile,
                bgrMacDinh,
                dimTxt
        );
        datHanhDongChoBtnThemGhiChu();
        pnlChuaBtn.add(btnThemGhiChu);
    }

    private void datHanhDongChoBtnThemGhiChu(){
        btnThemGhiChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thucHienThuTucThemGhiChuMoi();
            }
        });
    }

    private boolean kiemTraChuDe(){
        String chuDe = txtChuDe.getText().trim();

        if (chuDe.isEmpty()){
            txtChuDe.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraHanThucHien(){
        JTextField txt = tmpHanThucHien.getComponentTimeTextField();

        if (txt.getText().trim().isEmpty()){
            txt.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraSuHopLeCuaHanThucHien(){
        int maCaLamViec = GDChinh.getNhanVienDangSuDung().getCaLamViec().isCaSang() ? 1 : 0;

        Time gioHienTai = Time.valueOf(LocalTime.now());
        Time gioBatDauCa = CaLamViecDAO.layThoiGianBatDauCaLam(maCaLamViec);
        Time gioKetThucCa = CaLamViecDAO.layThoiGianKetThucCaLam(maCaLamViec);

        Time hanThucHien = Time.valueOf(tmpHanThucHien.getTime());

        if (!(hanThucHien.after(gioBatDauCa) && hanThucHien.after(gioHienTai) && hanThucHien.before(gioKetThucCa))){
            tmpHanThucHien.getComponentTimeTextField().requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraNoiDungGhiChu(){
        String noiDungGhiChu = txaNoiDungGhiChu.getText().trim();

        if (noiDungGhiChu.isEmpty()){
            txaNoiDungGhiChu.requestFocus();
            return false;
        }

        return true;
    }

    private void thucHienThuTucThemGhiChuMoi(){
        if (
                kiemTraChuDe() &&
                kiemTraHanThucHien() && kiemTraSuHopLeCuaHanThucHien()
                && kiemTraNoiDungGhiChu()
        ){
            GhiChu ghiChu = new GhiChu(
                    txtChuDe.getText().trim(),
                    Time.valueOf(tmpHanThucHien.getTime()),
                    txaNoiDungGhiChu.getText().trim()
            );

            PnlQLGhiChu.themGhiChuMoi(ghiChu);

            new Thread(this::duaCacThanhPhanTroVeTrangThaiBanDau).start();

            dispose();

            PnlQLGhiChu.capNhatSoLuongGhiChuTheoTungLoai();

            GDChinh.capNhatSoLuongGhiChuChuaHoanThanh(PnlQLGhiChu.laySoLuongGhiChuChuaHoanThanh());

            new Thread(() -> {
              CacHamDungSan.hienThiThongBaoKetQua(
                      GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                      "Ghi chú mới đã được thêm thành công (^_^)"
              );
            }).start();
        }
        else{
           CacHamDungSan.hienThiThongBaoKetQua(
                   GDThongBaoKetQua.THONG_BAO_LOI,
                   "Chà chà, có vài trường thông tin chưa hợp lệ. " +
                           "Hãy sửa lại theo hướng dẫn nhé."
           );
        }
    }

    private void duaCacThanhPhanTroVeTrangThaiBanDau(){
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtChuDe, "");

        CacHamDungSan.duaTxaVeTrangThaiSanSangHienThiThongTin(txaNoiDungGhiChu, "");

        CacHamDungSan.duaTimePickerVeTrangThaiBanDau(tmpHanThucHien);
    }
}
