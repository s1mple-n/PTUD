package ui.giaodienthongbaongoaivi;

import services.CacHamDungSan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GDThongBaoKetQua extends JDialog implements IDSBienGDThongBaoKetQua{
    private static GDThongBaoKetQua gdThongBaoKetQua = null;

    public static final int THONG_BAO_LOI = -1;
    public static final int THONG_BAO_BINH_THUONG = 0;
    public static final int THONG_BAO_THANH_CONG = 1;

    private GDThongBaoKetQua(){
        setTitle(tieuDe);
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));
        setModal(true);

        dungUI();

        setUndecorated(true);
        setSize(dimGDThongBao);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        datPhimTatAnGiaoDien();
    }

    public static GDThongBaoKetQua getGdThongBaoKetQua() {
        if (gdThongBaoKetQua == null)
            gdThongBaoKetQua = new GDThongBaoKetQua();
        return gdThongBaoKetQua;
    }

    private void datPhimTatAnGiaoDien(){

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK),
                KeyEvent.VK_ENTER
        );
        getRootPane().getActionMap().put(
                KeyEvent.VK_ENTER,
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        anGiaoDienThongBaoKetQua();
                    }
                }
        );
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setPreferredSize(dimGDThongBao);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlChinh.setBackground(bgrMacDinh);

        dungPnlDanhDauMau();
        pnlChinh.add(pnlDanhDauMau);

        dungPnlHienThiBieuTuong();
        pnlChinh.add(pnlHienThiBieuTuong);

        dungPnlHienThiTieuDe();
        pnlChinh.add(pnlHienThiTieuDe);

        dungPnlHienThiThongBao();
        pnlChinh.add(pnlHienThiThongBao);

        dungPnlChuaBtnXacNhan();
        pnlChinh.add(pnlChuaBtnXacNhan);
    }

    private void dungPnlDanhDauMau(){
        pnlDanhDauMau.setPreferredSize(dimPnlDanhDauMau);
    }

    private void dungPnlHienThiBieuTuong(){
        pnlHienThiBieuTuong.setPreferredSize(dimPnlHienThiBieuTuong);
        pnlHienThiBieuTuong.setBackground(mauNenMacDinhGDThongBao);
        pnlHienThiBieuTuong.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        pnlHienThiBieuTuong.add(lblBieuTuong);
    }

    private void dungPnlHienThiTieuDe(){
        pnlHienThiTieuDe.setBackground(mauNenMacDinhGDThongBao);
        pnlHienThiTieuDe.setPreferredSize(dimPnlHienThiTieuDe);
        pnlHienThiTieuDe.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDe,
                fntLblTieuDe,
                frgMacDinh
        );
        pnlHienThiTieuDe.add(lblTieuDe);
    }

    private void dungPnlHienThiThongBao(){
        pnlHienThiThongBao.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlHienThiThongBao.setBackground(mauNenMacDinhGDThongBao);
        pnlHienThiThongBao.setPreferredSize(dimPnlHienThiThongBao);

        CacHamDungSan.datThuocTinhChoTxp(
                txpHienThiNoiDungThongBao,
                false,
                true,
                mauNenMacDinhGDThongBao,
                dimTxpHienThiNoiDungThongBao
        );
        txpHienThiNoiDungThongBao.setEditable(false);

        pnlHienThiThongBao.add(txpHienThiNoiDungThongBao);
    }

    private void dungPnlChuaBtnXacNhan(){
        pnlChuaBtnXacNhan.setBackground(mauNenMacDinhGDThongBao);
        pnlChuaBtnXacNhan.setPreferredSize(dimPnlChuaBtnXacNhan);
        pnlChuaBtnXacNhan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        CacHamDungSan.datThuocTinhChoBtn(
                btnXacNhan,
                bgrMacDinh,
                bgrMacDinh,
                dimBtnXacNhan
        );
        btnXacNhan.setToolTipText("Đóng giao diện thông báo (Ctrl + Enter)");
        btnXacNhan.setFont(fntBtnXacNhan);
        datHanhDongChoBtnXacNhan();

        pnlChuaBtnXacNhan.add(btnXacNhan);
    }

    private void datHanhDongChoBtnXacNhan(){
        btnXacNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                anGiaoDienThongBaoKetQua();
            }
        });
    }

    private void anGiaoDienThongBaoKetQua(){
        SwingUtilities.invokeLater(() -> {
            dispose();
        });
    }

    public void cungCapLoaiThongBaoVaThongDiep(int loaiThongBao, String thongDiep){
        new Thread(() -> {
            txpHienThiNoiDungThongBao.setText(thongDiep);
        }).start();

        if (loaiThongBao == THONG_BAO_LOI){
            pnlDanhDauMau.setBackground(bgrThatBai);

            lblBieuTuong.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(pathIcnThatBai)));

            lblTieuDe.setText(tieuDeThatBai);

            btnXacNhan.setBackground(bgrThatBai);
        }
        else if (loaiThongBao == THONG_BAO_BINH_THUONG){
            pnlDanhDauMau.setBackground(bgrBinhThuong);

            lblBieuTuong.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(pathIcnBinhThuong)));

            lblTieuDe.setText(tieuDeBinhThuong);

            btnXacNhan.setBackground(bgrBinhThuong);
        }
        else if (loaiThongBao == THONG_BAO_THANH_CONG){
            pnlDanhDauMau.setBackground(bgrThanhCong);

            lblBieuTuong.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(pathIcnThanhCong)));

            lblTieuDe.setText(tieuDeThanhCong);

            btnXacNhan.setBackground(bgrThanhCong);
        }
    }
}
