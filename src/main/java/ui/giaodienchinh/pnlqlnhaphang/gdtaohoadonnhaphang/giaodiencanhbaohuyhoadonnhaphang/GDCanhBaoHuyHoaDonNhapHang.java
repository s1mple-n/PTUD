package ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.giaodiencanhbaohuyhoadonnhaphang;

import services.CacHamDungSan;
import ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.GDTaoHoaDonNhapHang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GDCanhBaoHuyHoaDonNhapHang extends JDialog implements IDSBienGDCanhBaoHuyHoaDonNhapHang {
    private static GDCanhBaoHuyHoaDonNhapHang gdCanhBaoHuyHoaDonNhapHang = null;

    private GDCanhBaoHuyHoaDonNhapHang(){
        setTitle(tieuDe);
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setModal(true);
        setUndecorated(true);
        setSize(dimGDCanhBaoHuyHoaDonNhapHang);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static GDCanhBaoHuyHoaDonNhapHang getGdCanhBaoHuyHoaDonNhapHang() {
        if (gdCanhBaoHuyHoaDonNhapHang == null)
            gdCanhBaoHuyHoaDonNhapHang = new GDCanhBaoHuyHoaDonNhapHang();
        return gdCanhBaoHuyHoaDonNhapHang;
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setBackground(bgrMacDinh);
        pnlChinh.setPreferredSize(dimGDCanhBaoHuyHoaDonNhapHang);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnlChinh.setBorder(
                BorderFactory.createMatteBorder(
                        1, 1, 1, 1, bgrChuDao
                )
        );

        pnlDanhDauMau.setPreferredSize(dimPnlDanhDauMau);
        pnlDanhDauMau.setBackground(bgrChuDao);
        pnlChinh.add(pnlDanhDauMau);

        dungPnlNoiDung();
        pnlChinh.add(pnlNoiDung);
    }

    private void dungPnlNoiDung(){
        pnlNoiDung.setPreferredSize(dimPnlNoiDung);
        pnlNoiDung.setBackground(bgrMacDinh);
        pnlNoiDung.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlTieuDe();
        pnlNoiDung.add(pnlTieuDe);

        dungTxpThongDiep();
        pnlNoiDung.add(txpThongDiep);

        dungPnlChuaCacNut();
        pnlNoiDung.add(pnlChuaCacNut);
    }

    private void dungPnlTieuDe(){
        pnlTieuDe.setBackground(bgrMacDinh);
        pnlTieuDe.setPreferredSize(dimPnlTieuDe);
        pnlTieuDe.setLayout(new BoxLayout(pnlTieuDe, BoxLayout.X_AXIS));

        CacHamDungSan.datThuocTinhChoLbl(
                lblTieuDe,
                fntLblTieuDe,
                bgrChuDao
        );
        pnlTieuDe.add(lblTieuDe);
    }

    private void dungTxpThongDiep(){
        CacHamDungSan.datThuocTinhChoTxp(
                txpThongDiep,
                false,
                false,
                bgrMacDinh,
                dimTxpThongDiep
        );
        txpThongDiep.setForeground(bgrChuDao);
        txpThongDiep.setEditable(false);

        txpThongDiep.setText(thongDiep);
    }

    private void dungPnlChuaCacNut(){
        pnlChuaCacNut.setPreferredSize(dimPnlChuaCacNut);
        pnlChuaCacNut.setBackground(bgrMacDinh);
        pnlChuaCacNut.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        CacHamDungSan.datThuocTinhChoBtn(
                btnHuyDon,
                bgrChuDao,
                bgrMacDinh,
                dimBtn
        );
        datHanhDongChoBtnHuyDon();
        pnlChuaCacNut.add(btnHuyDon);

        CacHamDungSan.datThuocTinhChoBtn(
                btnOLai,
                null,
                frgMacDinh,
                dimBtn
        );
        datHanhDongChoBtnOLai();
        pnlChuaCacNut.add(btnOLai);
    }

    private void datHanhDongChoBtnOLai(){
        btnOLai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }

    private void datHanhDongChoBtnHuyDon(){
        btnHuyDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1){
                    GDTaoHoaDonNhapHang.huyHoaDonNhapHang();
                    dispose();
                }
            }
        });
    }
}
