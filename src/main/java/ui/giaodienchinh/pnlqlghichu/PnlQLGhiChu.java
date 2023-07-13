package ui.giaodienchinh.pnlqlghichu;

import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlghichu.gdchinhsuaghichu.GDCapNhatGhiChu;
import ui.giaodienchinh.pnlqlghichu.kieudulieudacbiet.GhiChu;
import ui.giaodienchinh.pnlqlghichu.kieudulieudacbiet.TrangThaiGhiChu;
import services.CacHamDungSan;
import ui.giaodienchinh.pnlqlghichu.gdthemghichu.GDThemGhiChu;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class PnlQLGhiChu implements IDSBienPnlQLGhiChu{
    private static JPanel pnlQLGhiChu = null;

    private JPanel dungPnlQuanLiGhiChu(){
        pnlQLGC.setBackground(bgrPnlChinh);
        pnlQLGC.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlQLGC.setPreferredSize(dimPnlQLGC);

        dungPnlDSGhiChuChuaHoanThanh();
        pnlDSGhiChuChuaHoanThanh.setBackground(bgrPnlChinh);
        pnlQLGC.add(pnlDSGhiChuChuaHoanThanh);

        pnlQLGC.add(Box.createHorizontalStrut(khoangCachGiuaCacPnlLon));

        dungPnlDSGhiChuQuaHan();
        pnlDSGhiChuQuaHan.setBackground(bgrPnlChinh);
        pnlQLGC.add(pnlDSGhiChuQuaHan);

        pnlQLGC.add(Box.createHorizontalStrut(khoangCachGiuaCacPnlLon));

        dungPnlDSGhiChuDaHoanThanh();
        pnlDSGhiChuDaHoanThanh.setBackground(bgrPnlChinh);
        pnlQLGC.add(pnlDSGhiChuDaHoanThanh);

        new Thread(PnlQLGhiChu::datBaoThucChoDSGhiChu).start();

        return pnlQLGC;
    }

    public static JPanel getPnlQLGhiChu() {
        if (pnlQLGhiChu == null){
            pnlQLGhiChu = new PnlQLGhiChu().dungPnlQuanLiGhiChu();
        }
        return pnlQLGhiChu;
    }

    private void dungPnlDSGhiChuChuaHoanThanh(){
        pnlDSGhiChuChuaHoanThanh.setBackground(bgrPnlChinh);
        pnlDSGhiChuChuaHoanThanh.setPreferredSize(dimPnlDSGhiChuChuaHoanThanh);
        pnlDSGhiChuChuaHoanThanh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThanhTieuDeCacPnlChuaDSGhiChu(
                pnlThanhTieuDeDSGhiChuChuaHoanThanh,
                lblTieuDeDsGhiChuChuaHoanThanh,
                lblSLGhiChuChuaHoanThanh,
                TrangThaiGhiChu.CHUA_HOAN_THANH.getTrangThaiGhiChu()
        );
        pnlThanhTieuDeDSGhiChuChuaHoanThanh.add(Box.createHorizontalStrut(demTrai * 6));
        datThuocTinhVaHanhDongChoLblThemGhiChu();
        lblThemGhiChu.setToolTipText("Tạo ghi chú mới");
        pnlThanhTieuDeDSGhiChuChuaHoanThanh.add(lblThemGhiChu);

        pnlDSGhiChuChuaHoanThanh.add(pnlThanhTieuDeDSGhiChuChuaHoanThanh);

        dungCacScrPaneChuaDSGhiChu(scrChuaPnlChuaDSGhiChuChuaHoanThanh);
        dungPnlChuaDSGhiChu(pnlChuaDSGhiChuChuaHoanThanh);
        pnlDSGhiChuChuaHoanThanh.add(scrChuaPnlChuaDSGhiChuChuaHoanThanh);
    }

    private void datThuocTinhVaHanhDongChoLblThemGhiChu(){
        lblThemGhiChu.setBackground(bgrMacDinh);
        lblThemGhiChu.setBorder(
                BorderFactory.createEmptyBorder(
                        3, 3, 3, 3
                )
        );

        lblThemGhiChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    GDThemGhiChu gdThemGhiChu = GDThemGhiChu.getGdThemGhiChu();
                    gdThemGhiChu.setVisible(true);
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lblThemGhiChu.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lblThemGhiChu.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblThemGhiChu.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblThemGhiChu.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void dungPnlDSGhiChuQuaHan(){
        pnlDSGhiChuQuaHan.setBackground(bgrPnlChinh);
        pnlDSGhiChuQuaHan.setPreferredSize(dimPnlDSGhiChuChuaHoanThanh);
        pnlDSGhiChuQuaHan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThanhTieuDeCacPnlChuaDSGhiChu(
                pnlThanhTieuDeDSGhiChuQuaHan,
                lblTieuDeDSGhiChuQuaHan,
                lblSLGhiChuQuaHan,
                TrangThaiGhiChu.QUA_HAN.getTrangThaiGhiChu()
        );
        pnlDSGhiChuQuaHan.add(pnlThanhTieuDeDSGhiChuQuaHan);

        dungCacScrPaneChuaDSGhiChu(scrChuaPnlChuaDSGhiChuQuaHan);
        dungPnlChuaDSGhiChu(pnlChuaDSGhiChuQuaHan);
        pnlDSGhiChuQuaHan.add(scrChuaPnlChuaDSGhiChuQuaHan);
    }

    private void dungPnlChuaDSGhiChu(JPanel pnl){
        pnl.setBackground(bgrPnlChinh);
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
    }

    private void dungPnlDSGhiChuDaHoanThanh(){
        pnlDSGhiChuDaHoanThanh.setBackground(bgrPnlChinh);
        pnlDSGhiChuDaHoanThanh.setPreferredSize(dimPnlDSGhiChuChuaHoanThanh);
        pnlDSGhiChuDaHoanThanh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThanhTieuDeCacPnlChuaDSGhiChu(
                pnlThanhTieuDeDSGhiChuDaHoanThanh,
                lblTieuDeDSGhiChuDaHoanThanh,
                lblSLGhiChuDaHoanThanh,
                TrangThaiGhiChu.DA_HOAN_THANH.getTrangThaiGhiChu()
        );
        pnlDSGhiChuDaHoanThanh.add(pnlThanhTieuDeDSGhiChuDaHoanThanh);

        dungCacScrPaneChuaDSGhiChu(scrChuaPnlChuaDSGhiChuDaHoanThanh);
        dungPnlChuaDSGhiChu(pnlChuaDSGhiChuDaHoanThanh);
        pnlDSGhiChuDaHoanThanh.add(scrChuaPnlChuaDSGhiChuDaHoanThanh);
    }

    private void dungPnlThanhTieuDeCacPnlChuaDSGhiChu(JPanel pnl, JLabel lblTieuDe, JLabel lblSoLuongGC, int loaiGhiChu){
        pnl.setBackground(bgrMacDinh);
        pnl.setPreferredSize(dimPnlThanhTieuDeDSGhiChuChuaHoanThanh);
        pnl.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                0,
                demTren
        ));

        pnl.add(Box.createHorizontalStrut(demTrai));
        lblTieuDe.setFont(fntFrgLblTieuDeDsGhiChuChuaHoanThanh);
        pnl.add(lblTieuDe);

        pnl.add(Box.createHorizontalStrut(demTrai - 10));
        lblSoLuongGC.setFont(fntMacDinh);
        lblSoLuongGC.setFont(fntLblSLGhiChuChuaHoanThanh);
        pnl.add(lblSoLuongGC);

        if (loaiGhiChu == TrangThaiGhiChu.CHUA_HOAN_THANH.getTrangThaiGhiChu()){
            lblTieuDe.setForeground(frgLblTieuDeDsGhiChuChuaHoanThanh);
            lblSoLuongGC.setForeground(frgLblTieuDeDsGhiChuChuaHoanThanh);
        }
        else if (loaiGhiChu == TrangThaiGhiChu.QUA_HAN.getTrangThaiGhiChu()){
            lblTieuDe.setForeground(frgLblTieuDeDsGhiChuQuaHan);
            lblSoLuongGC.setForeground(frgLblTieuDeDsGhiChuQuaHan);
        }
        else if (loaiGhiChu == TrangThaiGhiChu.DA_HOAN_THANH.getTrangThaiGhiChu()){
            lblTieuDe.setForeground(frgLblTieuDeDsGhiChuDaHoanThanh);
            lblSoLuongGC.setForeground(frgLblTieuDeDsGhiChuDaHoanThanh);
        }
    }

    private static JPanel taoPnlGhiChu(GhiChu ghiChu){
        JPanel pnlBaoNgoai = new JPanel();
        pnlBaoNgoai.setLayout(new FlowLayout(FlowLayout.CENTER, 0, demTren));
        pnlBaoNgoai.setPreferredSize(dimPnlChuaPnlGhiChu);
        pnlBaoNgoai.setBackground(bgrPnlChinh);

        if (
                ghiChu.getTrangThaiGhiChu() == TrangThaiGhiChu.CHUA_HOAN_THANH
        ){
            dsPnlChuaGhiChu.add(pnlBaoNgoai);
        }

        /**
         *
         */

        int coHieu = dsGhiChu.size() - 1;

        /**
         *
         */

        JPanel pnlChinh = new JPanel();
        datThuocTinhVaHanhDongChoPnlChinh(pnlChinh);

        /**
         *
         */

        JPanel pnlNoiDung = new JPanel();
        datThuocTinhChoPnlNoiDung(pnlNoiDung);

        /**
         *
         */

        JPanel pnlChuaLblChuDe = new JPanel();
        datThuocTinhChoPnlChuaLblChuDe(pnlChuaLblChuDe);

        JLabel lblChuDe = new JLabel(ghiChu.getChuDe());
        datThuocTinhChoLblChuDe(lblChuDe);
        dsLblChuDe.add(lblChuDe);
        pnlChuaLblChuDe.add(lblChuDe, BorderLayout.WEST);

        if (
                ghiChu.getTrangThaiGhiChu() == TrangThaiGhiChu.CHUA_HOAN_THANH
        ){
            JLabel lblChinhSua = new JLabel(
                    new ImageIcon(
                            Toolkit.getDefaultToolkit().getImage(
                                    pathIcnChinhSua
                            )
                    )
            );
            lblChinhSua.setToolTipText("Chỉnh sửa ghi chú này");
            datHanhDongCoBanChoLbl(lblChinhSua, pnlChinh);
            datHanhDongChoLblCapNhatGhiChu(lblChinhSua, coHieu);
            dsLblChinhSua.add(lblChinhSua);
            pnlChuaLblChuDe.add(Box.createHorizontalStrut(demTraiPnlGhiChu * 7 + demTrenPnlGhiChu));
            pnlChuaLblChuDe.add(lblChinhSua, BorderLayout.EAST);
        }
        pnlNoiDung.add(pnlChuaLblChuDe);

        /**
         *
         */

        JPanel pnlChuaLblHanThucHien = new JPanel();
        datThuocTinhChoPnlChuaLblHanThucHien(pnlChuaLblHanThucHien);
        JLabel lblHanThucHien = new JLabel(
                dtf.format(
                        ghiChu.getHanThucHien().toLocalTime()
                )
        );
        datThuocTinhChoLblHanThucHien(lblHanThucHien);
        pnlChuaLblHanThucHien.add(lblHanThucHien);
        dsLblHanThucHien.add(lblHanThucHien);
        pnlNoiDung.add(Box.createVerticalStrut(demTrenPnlGhiChu));
        pnlNoiDung.add(pnlChuaLblHanThucHien);

        /**
         *
         */

        JTextArea txaNoiDungGhiChu = new JTextArea();
        dsTxaNoiDungGhiChu.add(txaNoiDungGhiChu);
        txaNoiDungGhiChu.setText(ghiChu.getNoiDungGhiChu());
        datHanhDongChoTxaNoiDungGhiChu(txaNoiDungGhiChu, pnlChinh);
        CacHamDungSan.datThuocTinhChoTxa(txaNoiDungGhiChu, null, false);
        JScrollPane scrChuaTxa = new JScrollPane(txaNoiDungGhiChu);
        scrChuaTxa.setPreferredSize(dimScrChuaTxa);
        datHanhDongChoScrNoiDungGhiChu(scrChuaTxa, pnlChinh);
        pnlNoiDung.add(Box.createVerticalStrut(demTrenPnlGhiChu));
        pnlNoiDung.add(scrChuaTxa);

        /**
         *
         */

        JPanel pnlChuaCacNut = new JPanel();
        dungPnlChuaCacNutGhiChu(pnlChuaCacNut);

        if (
                ghiChu.getTrangThaiGhiChu() == TrangThaiGhiChu.CHUA_HOAN_THANH
        ){
            JLabel lblXoaGhiChu = new JLabel(
                    new ImageIcon(
                            Toolkit.getDefaultToolkit().getImage(
                                    "src/main/resources/BieuTuong/Delete_32px_1.png"
                            )
                    )
            );

            datHanhDongChoLblXoaGhiChu(lblXoaGhiChu, coHieu);

            lblXoaGhiChu.setToolTipText("Xoá ghi chú này");
            datHanhDongCoBanChoLbl(lblXoaGhiChu, pnlChinh);
            dsLblXoaGhiChu.add(lblXoaGhiChu);
            pnlChuaCacNut.add(lblXoaGhiChu);

            /**
             *
             */

            JLabel lblXacNhanDaHoanThanh = new JLabel(
                    new ImageIcon(
                            Toolkit.getDefaultToolkit().getImage(
                                    "src/main/resources/BieuTuong/Success_32px_1.png"
                            )
                    )
            );
            lblXacNhanDaHoanThanh.setToolTipText("Xác nhận rằng ghi chú này đã được hoàn thành");
            datHanhDongCoBanChoLbl(lblXacNhanDaHoanThanh, pnlChinh);
            dsLblXacNhanDaHoanThanh.add(lblXacNhanDaHoanThanh);
            datHanhDongChoLblXacNhanDaHoanThanh(lblXacNhanDaHoanThanh, coHieu);

            pnlChuaCacNut.add(Box.createHorizontalStrut(demTrai));
            pnlChuaCacNut.add(lblXacNhanDaHoanThanh);

            pnlNoiDung.add(Box.createVerticalStrut(demTrenPnlGhiChu));
            pnlNoiDung.add(pnlChuaCacNut);
        }

        pnlChinh.add(pnlNoiDung);

        pnlBaoNgoai.add(pnlChinh);

        return pnlBaoNgoai;
    }

    private static void datHanhDongChoLblCapNhatGhiChu(JLabel lblCapNhatGhiChu, int coHieu){
        lblCapNhatGhiChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GDCapNhatGhiChu gdCapNhatGhiChu = GDCapNhatGhiChu.getGdThemGhiChu();
                gdCapNhatGhiChu.cungCapViTriVaGhiChuCanCapNhat(
                        coHieu,
                        dsGhiChu.get(coHieu)
                );
                gdCapNhatGhiChu.setVisible(true);
            }
        });
    }

    private static void datHanhDongChoLblXoaGhiChu(JLabel lblXoaGhiChu, int coHieu){

        lblXoaGhiChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int luaChon = JOptionPane.showConfirmDialog(
                        null,
                        "Bạn chắc chắn muốn xoá ghi chú này chứ?",
                        "Cảnh báo",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (luaChon == JOptionPane.YES_OPTION){
                    dsPnlChuaGhiChu.get(coHieu).setVisible(false);

                    dsGhiChu.get(coHieu).setTrangThaiGhiChu(TrangThaiGhiChu.DA_XOA);

                    capNhatSoLuongGhiChuTheoTungLoai();

                    GDChinh.capNhatSoLuongGhiChuChuaHoanThanh(laySoLuongGhiChuChuaHoanThanh());
                }
            }
        });
    }

    private static void datHanhDongChoLblXacNhanDaHoanThanh(JLabel lbl, int coHieu){

        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int luaChon = JOptionPane.showConfirmDialog(
                        null,
                        "Ghi chú này đã hoàn thành thật chứ?",
                        "Cảnh báo",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (luaChon == JOptionPane.YES_OPTION){
                    JPanel pnlTmp = dsPnlChuaGhiChu.get(coHieu);

                    pnlChuaDSGhiChuDaHoanThanh.add(pnlTmp);

                    dsLblChinhSua.get(coHieu).setVisible(false);
                    dsLblXoaGhiChu.get(coHieu).setVisible(false);
                    dsLblXacNhanDaHoanThanh.get(coHieu).setVisible(false);

                    dsGhiChu.get(coHieu).setTrangThaiGhiChu(TrangThaiGhiChu.DA_HOAN_THANH);

                    capNhatSoLuongGhiChuTheoTungLoai();

                    pnlChuaDSGhiChuChuaHoanThanh.repaint();
                    pnlChuaDSGhiChuChuaHoanThanh.revalidate();

                    pnlChuaDSGhiChuQuaHan.repaint();
                    pnlChuaDSGhiChuQuaHan.revalidate();

                    GDChinh.capNhatSoLuongGhiChuChuaHoanThanh(laySoLuongGhiChuChuaHoanThanh());
                }
            }
        });
    }

    private static void datThuocTinhVaHanhDongChoPnlChinh(JPanel pnl){
        pnl.setLayout(new FlowLayout(FlowLayout.CENTER, 0, demTrenPnlGhiChu));
        pnl.setBackground(bgrMacDinh);
        pnl.setPreferredSize(dimPnlGhiChu);
        pnl.setMaximumSize(dimPnlGhiChu);
        pnl.setBorder(
                BorderFactory.createLineBorder(
                        bgrMacDinh, 2
                )
        );

        pnl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrMacDinh, 2
                        )
                );
            }
        });
    }

    private static void datHanhDongChoTxaNoiDungGhiChu(JTextArea txa, JPanel pnl){
        txa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }
        });

        txa.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void focusLost(FocusEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }
        });
    }

    private static void datHanhDongChoScrNoiDungGhiChu(JScrollPane scr, JPanel pnl){
        scr.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }
        });

        scr.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void focusLost(FocusEvent e) {
                pnl.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }
        });
    }

    private static void datThuocTinhChoPnlNoiDung(JPanel pnl){
        pnl.setBackground(bgrMacDinh);
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.setPreferredSize(dimPnlNoiDungChinhGhiChu);
    }

    private static void datThuocTinhChoPnlChuaLblChuDe(JPanel pnl){
        pnl.setBackground(bgrMacDinh);
        pnl.setPreferredSize(dimPnlChuaChuDe);
        pnl.setMaximumSize(dimPnlChuaChuDe);
        pnl.setLayout(new BorderLayout());
    }

    private static void datThuocTinhChoLblChuDe(JLabel lbl){
        lbl.setFont(new Font(
                tenFontMacDinh, Font.BOLD, 20
        ));
        lbl.setForeground(frgMacDinh);
    }

    private static void datThuocTinhChoPnlChuaLblHanThucHien(JPanel pnl){
        pnl.setBackground(bgrMacDinh);
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnl.setPreferredSize(dimPnlHanThucHien);
    }

    private static void datThuocTinhChoLblHanThucHien(JLabel lbl){
        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
    }

    private static void dungCacScrPaneChuaDSGhiChu(JScrollPane scr){
        scr.setPreferredSize(dimScrChuaPnlChuaDSGhiChuChuaHoanThanh);
        scr.setBackground(bgrPnlChinh);
        scr.setBorder(BorderFactory.createEmptyBorder());
    }

    private static void dungPnlChuaCacNutGhiChu(JPanel pnl){
        pnl.setBackground(bgrMacDinh);
        pnl.setPreferredSize(dimPnlChuaChuDe);
        pnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    }

    private static void datHanhDongCoBanChoLbl(JLabel lbl, JPanel pnlChinh){
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                pnlChinh.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                pnlChinh.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                pnlChinh.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                pnlChinh.setBorder(
                        BorderFactory.createLineBorder(
                                bgrVienMacDinh, 2
                        )
                );
            }
        });
    }

    public static void themGhiChuMoi(GhiChu ghiChu){
        dsGhiChu.add(ghiChu);

        pnlChuaDSGhiChuChuaHoanThanh.add(
                taoPnlGhiChu(ghiChu)
        );

        pnlChuaDSGhiChuChuaHoanThanh.repaint();
        pnlChuaDSGhiChuChuaHoanThanh.revalidate();

        GDChinh.capNhatSoLuongGhiChuChuaHoanThanh(laySoLuongGhiChuChuaHoanThanh());
    }

    public static int laySoLuongGhiChuChuaHoanThanh(){
        return (int) dsGhiChu.stream().filter(
                c -> c.getTrangThaiGhiChu() == TrangThaiGhiChu.CHUA_HOAN_THANH
                        || c.getTrangThaiGhiChu() == TrangThaiGhiChu.QUA_HAN
        ).count();
    }

    public static void datBaoThucChoDSGhiChu(){
        while (true){
            int coHieu = 0;
            int slGhiChu = dsGhiChu.size();

            while (coHieu < slGhiChu) {
                GhiChu ghiChu = dsGhiChu.get(coHieu);
                TrangThaiGhiChu trangThaiGhiChu = ghiChu.getTrangThaiGhiChu();

                if (
                        trangThaiGhiChu == TrangThaiGhiChu.CHUA_HOAN_THANH
                        || trangThaiGhiChu == TrangThaiGhiChu.QUA_HAN
                ) {
                    Time thoiGianHienTai = Time.valueOf(LocalTime.now());

                    if (ghiChu.getHanThucHien().equals(thoiGianHienTai)) {
                        dsGhiChu.get(coHieu).setTrangThaiGhiChu(TrangThaiGhiChu.QUA_HAN);

                        JPanel pnlTmp = dsPnlChuaGhiChu.get(coHieu);
                        pnlChuaDSGhiChuQuaHan.add(pnlTmp);

                        capNhatSoLuongGhiChuTheoTungLoai();

                        new Thread(() -> {
                            pnlChuaDSGhiChuChuaHoanThanh.repaint();
                            pnlChuaDSGhiChuChuaHoanThanh.revalidate();
                        }).start();

                        if (!GDThongBaoKetQua.getGdThongBaoKetQua().isDisplayable()){
                            new Thread(() -> {
                                CacHamDungSan.hienThiThongBaoKetQua(
                                        GDThongBaoKetQua.THONG_BAO_BINH_THUONG,
                                        "Đã đến lúc thực hiện ghi chú "
                                                + "'"
                                                + ghiChu.getChuDe()
                                                + "'"
                                                + " rồi nha!!!"
                                );
                            }).start();
                        }
                    }
                }

                coHieu++;
            }

            try {
                Thread.sleep(1000);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void capNhatSoLuongGhiChuTheoTungLoai(){
        AtomicInteger slGCChuaHoanThanh = new AtomicInteger(0);
        AtomicInteger slGCQuaHan = new AtomicInteger(0);
        AtomicInteger slGCDaHoanThanh = new AtomicInteger(0);

        dsGhiChu.forEach(g -> {
            TrangThaiGhiChu trangThaiGhiChu = g.getTrangThaiGhiChu();

            if (trangThaiGhiChu == TrangThaiGhiChu.CHUA_HOAN_THANH)
                slGCChuaHoanThanh.getAndIncrement();
            else if (trangThaiGhiChu == TrangThaiGhiChu.QUA_HAN)
                slGCQuaHan.getAndIncrement();
            else if (trangThaiGhiChu == TrangThaiGhiChu.DA_HOAN_THANH)
                slGCDaHoanThanh.getAndIncrement();
        });

        lblSLGhiChuChuaHoanThanh.setText("(" + slGCChuaHoanThanh.get() + ")");
        lblSLGhiChuQuaHan.setText("(" + slGCQuaHan.get() + ")");
        lblSLGhiChuDaHoanThanh.setText("(" + slGCDaHoanThanh.get() + ")");
    }

    public static void capNhatGhiChu(int viTri, GhiChu ghiChuMoi){
        GhiChu ghiChu = dsGhiChu.get(viTri);

        ghiChu.setChuDe(ghiChuMoi.getChuDe());
        dsLblChuDe.get(viTri).setText(ghiChuMoi.getChuDe());

        ghiChu.setHanThucHien(ghiChuMoi.getHanThucHien());
        dsLblHanThucHien.get(viTri).setText(ghiChuMoi.getHanThucHien().toString());

        ghiChu.setNoiDungGhiChu(ghiChuMoi.getNoiDungGhiChu());
        dsTxaNoiDungGhiChu.get(viTri).setText(ghiChuMoi.getNoiDungGhiChu());

        if (ghiChuMoi.getHanThucHien().after(Time.valueOf(LocalTime.now()))){
            JPanel pnlTmp = dsPnlChuaGhiChu.get(viTri);

            pnlChuaDSGhiChuChuaHoanThanh.add(pnlTmp);

            ghiChu.setTrangThaiGhiChu(TrangThaiGhiChu.CHUA_HOAN_THANH);

            capNhatSoLuongGhiChuTheoTungLoai();

            pnlChuaDSGhiChuChuaHoanThanh.repaint();
            pnlChuaDSGhiChuChuaHoanThanh.revalidate();

            pnlChuaDSGhiChuQuaHan.repaint();
            pnlChuaDSGhiChuQuaHan.revalidate();

            GDChinh.capNhatSoLuongGhiChuChuaHoanThanh(laySoLuongGhiChuChuaHoanThanh());
        }
    }
}