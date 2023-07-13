package ui.giaodienchinh.giaodiencanhbaodangxuat;

import services.CacHamDungSan;
import ui.giaodienchinh.GDChinh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GDCanhBaoDangXuat extends JDialog implements IDSBienGDCanhBaoDangXuat {
    private static GDCanhBaoDangXuat gdCanhBaoDangXuat = null;

    private GDCanhBaoDangXuat(){
        setTitle(tieuDe);
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setModal(true);
        setSize(dimGDCanhBaoDangXuat);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static GDCanhBaoDangXuat getGdCanhBaoDangXuat() {
        if (gdCanhBaoDangXuat == null)
            gdCanhBaoDangXuat = new GDCanhBaoDangXuat();
        return gdCanhBaoDangXuat;
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setBackground(mauNenMacDinhGDThongBao);
        pnlChinh.setPreferredSize(dimGDCanhBaoDangXuat);
        pnlChinh.setLayout(new FlowLayout(
                FlowLayout.CENTER, 0, demTren
        ));

        dungPnlNoiDung();
        pnlChinh.add(pnlNoiDung);
    }

    private void dungPnlNoiDung(){
        pnlNoiDung.setPreferredSize(dimPnlNoiDung);
        pnlNoiDung.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDung.setBackground(mauNenMacDinhGDThongBao);

        dungPnlTieuDe();
        pnlNoiDung.add(pnlTieuDe);

        dungPnlBieuTuong();
        pnlNoiDung.add(pnlBieuTuong);

        dungPnlThongDiep();
        pnlNoiDung.add(pnlThongDiep);

        dungPnlChuaCacNutDieuHuong();
        pnlNoiDung.add(pnlChuaCacNutDieuHuong);
    }

    private void dungPnlTieuDe(){
        pnlTieuDe.setPreferredSize(dimPnlTieuDe);
        pnlTieuDe.setBackground(mauNenMacDinhGDThongBao);
        pnlTieuDe.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDe,
                fntLblTieuDe,
                frgMacDinh
        );
        pnlTieuDe.add(lblTieuDe);
    }

    private void dungPnlBieuTuong(){
        pnlBieuTuong.setPreferredSize(dimPnlBieuTuong);
        pnlBieuTuong.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlBieuTuong.setBackground(mauNenMacDinhGDThongBao);

        pnlBieuTuong.add(lblBieuTuong);
    }

    private void dungPnlThongDiep(){
        pnlThongDiep.setPreferredSize(dimPnlThongDiep);
        pnlThongDiep.setBackground(bgrPnlThongDiep);
        pnlThongDiep.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        pnlDanhDauMau.setPreferredSize(dimPnlDanhDauMau);
        pnlDanhDauMau.setBackground(bgrPnlDanhDauMau);
        pnlThongDiep.add(pnlDanhDauMau);

        pnlBieuTuongCanhBao.setPreferredSize(dimPnlBieuTuongCanhBao);
        pnlBieuTuongCanhBao.setBackground(bgrPnlThongDiep);
        pnlBieuTuongCanhBao.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlBieuTuongCanhBao.add(lblBieuTuongCanhBao);
        pnlThongDiep.add(pnlBieuTuongCanhBao);

        dungPnlHienThiThongDiep();
        pnlThongDiep.add(pnlHienThiThongDiep);
    }

    private void dungPnlHienThiThongDiep(){
        pnlHienThiThongDiep.setBackground(bgrPnlThongDiep);
        pnlHienThiThongDiep.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        pnlHienThiThongDiep.setPreferredSize(dimPnlHienThiThongDiep);

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDeCanhBao,
                fntLblTieuDeCanhBao,
                frgLblTieuDeCanhBao
        );
        pnlHienThiThongDiep.add(lblTieuDeCanhBao);

        CacHamDungSan.datThuocTinhChoTxp(
                txpThongDiepCanhBao,
                false,
                false,
                bgrPnlThongDiep,
                dimTxpThongDiepCanhBao
        );
        txpThongDiepCanhBao.setBorder(BorderFactory.createEmptyBorder());
        txpThongDiepCanhBao.setEditable(false);
        txpThongDiepCanhBao.setFont(fntTxpThongDiepCanhBao);
        txpThongDiepCanhBao.setForeground(frgTxpThongDiepCanhBao);
        txpThongDiepCanhBao.setText(thongDiepCanhBao);

        pnlHienThiThongDiep.add(txpThongDiepCanhBao);
    }

    private void dungPnlChuaCacNutDieuHuong(){
        pnlChuaCacNutDieuHuong.setBackground(mauNenMacDinhGDThongBao);
        pnlChuaCacNutDieuHuong.setPreferredSize(dimPnlChuaCacNutDieuHuong);
        pnlChuaCacNutDieuHuong.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 35));

        CacHamDungSan.datThuocTinhChoBtn(
                btnDangXuat,
                bgrBtnDangXuat,
                frgMacDinh,
                dimBtn
        );
        datHanhDongChoBtnDangXuat();
        pnlChuaCacNutDieuHuong.add(btnDangXuat);

        CacHamDungSan.datThuocTinhChoBtn(
                btnOLai,
                bgrBtnOLai,
                bgrMacDinh,
                dimBtn
        );
        datHanhDongChoBtnOLai();

        pnlChuaCacNutDieuHuong.add(Box.createHorizontalStrut(20));
        pnlChuaCacNutDieuHuong.add(btnOLai);
    }

    private void datHanhDongChoBtnDangXuat(){
        btnDangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDangXuat.setBackground(bgrBtnDangXuatKhiHover);
                btnDangXuat.setForeground(bgrMacDinh);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDangXuat.setBackground(bgrBtnDangXuat);
                btnDangXuat.setForeground(frgMacDinh);
            }
        });
    }

    private void datHanhDongChoBtnOLai(){
        btnOLai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                GDChinh.getInstance().setOpacity(1);
            }
        });
    }
}
