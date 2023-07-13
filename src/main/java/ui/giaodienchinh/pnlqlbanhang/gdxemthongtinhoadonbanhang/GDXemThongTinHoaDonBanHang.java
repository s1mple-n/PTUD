package ui.giaodienchinh.pnlqlbanhang.gdxemthongtinhoadonbanhang;

import entity.ChiTietHoaDonBanHang;
import entity.HoaDonBanHang;
import services.CacHamDungSan;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GDXemThongTinHoaDonBanHang extends JDialog implements IDSBienGDXemThongTinHoaDonBanHang {
    private static GDXemThongTinHoaDonBanHang gdXemTTHoaDonBanHang = null;

    public GDXemThongTinHoaDonBanHang(){
        setTitle("Tạo hoá đơn bán hàng");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setSize(dimGDXemTTHoaDonBanHang);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static GDXemThongTinHoaDonBanHang getGdXemTTHoaDonBanHang() {
        if (gdXemTTHoaDonBanHang == null)
            gdXemTTHoaDonBanHang = new GDXemThongTinHoaDonBanHang();
        return gdXemTTHoaDonBanHang;
    }

    private void dungUI(){
        dungPnlThanhCongCu();
        getContentPane().add(pnlThanhCongCu, BorderLayout.NORTH);

        dungPnlHoaDonBanHangChinh();
        getContentPane().add(pnlHoaDonBanHangChinh, BorderLayout.CENTER);
    }

    private void dungPnlThanhCongCu(){
        pnlThanhCongCu.setBackground(mauNenPnlThanhCongCu);
        pnlThanhCongCu.setPreferredSize(dimPnlThanhCongCu);
        pnlThanhCongCu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 15));

        dungPnlHopTienIch();
        pnlThanhCongCu.add(pnlHopTienIch);

        pnlThanhCongCu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    private void dungPnlHopTienIch(){
        pnlHopTienIch.setBackground(mauNenPnlThanhCongCu);
        pnlHopTienIch.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        pnlHopTienIch.setPreferredSize(dimPnlHopTienIch);

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiemSanPham,
                "Tìm sản phẩm theo mã hoặc tên",
                dimTxtTimKiemSanPham
        );
        datHanhDongChoTxtTimKiemSanPham();
        pnlHopTienIch.add(txtTimKiemSanPham);
    }

    private void datHanhDongChoTxtTimKiemSanPham(){
        txtTimKiemSanPham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsDanhSachSPKHMua.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiemSanPham.getText().trim()
                        )
                );
            }
        });
    }

    private static Object[] chuyenChiTietHoaDonBanHangSangObject(ChiTietHoaDonBanHang chiTietHoaDonBanHang){

        return new Object[]{
                tblDanhSachSanPhamKHMua.getRowCount() + 1,
                chiTietHoaDonBanHang.getSanPham().getMaSP(),
                chiTietHoaDonBanHang.getSanPham().getTenSP(),
                chiTietHoaDonBanHang.getSoLuongBan(),
                nf.format(chiTietHoaDonBanHang.getDonGiaBan()),
                nf.format(chiTietHoaDonBanHang.getThanhTien())
        };
    }

    private void dungPnlHoaDonBanHangChinh(){
        pnlHoaDonBanHangChinh.setBackground(bgrMacDinh);
        pnlHoaDonBanHangChinh.setPreferredSize(dimPnlTaoHoaDonBanHangChinh);
        pnlHoaDonBanHangChinh.setLayout(new BorderLayout());

        dungPnlDanhSachSanPhamKHMua();
        pnlHoaDonBanHangChinh.add(pnlDanhSachSanPhamKHMua, BorderLayout.CENTER);

        dungPnlMucTongKetHoaDonVaThoat();
        pnlHoaDonBanHangChinh.add(pnlMucTongKetHoaDonVaThanhToan, BorderLayout.EAST);
    }

    private void dungPnlDanhSachSanPhamKHMua(){
        pnlDanhSachSanPhamKHMua.setBackground(bgrMacDinh);
        pnlDanhSachSanPhamKHMua.setPreferredSize(dimPnlDanhSachSanPhamKHMua);
        pnlDanhSachSanPhamKHMua.setLayout(new BorderLayout());

        dungScrChuaTblDanhSachSPKHMua();
        pnlDanhSachSanPhamKHMua.add(scrChuaTblDSSanPhamKHMua, BorderLayout.CENTER);
    }

    private void dungScrChuaTblDanhSachSPKHMua(){
        dungTblDanhSachSPKHMua();

        scrChuaTblDSSanPhamKHMua.setPreferredSize(dimPnlDanhSachSanPhamKHMua);
        scrChuaTblDSSanPhamKHMua.getViewport().setBackground(bgrMacDinh);
        scrChuaTblDSSanPhamKHMua.setBorder(null);
        scrChuaTblDSSanPhamKHMua.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 0, 3, bgrVienMacDinh
                )
        );
    }

    private void dungTblDanhSachSPKHMua(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDanhSachSanPhamKHMua,
                dimPnlDanhSachSanPhamKHMua,
                trsDanhSachSPKHMua
        );

        canLeVaDatChieuRongChoCacGiaTriTrongTblDanhSachSPKHMua();
    }

    private void canLeVaDatChieuRongChoCacGiaTriTrongTblDanhSachSPKHMua(){
        TableColumnModel tcm = tblDanhSachSanPhamKHMua.getColumnModel();

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        tcm.getColumn(0).setPreferredWidth(50);
        tcm.getColumn(0).setMaxWidth(50);

        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(1).setMaxWidth(100);

        tcm.getColumn(3).setPreferredWidth(80);
        tcm.getColumn(3).setMaxWidth(80);
        tcm.getColumn(3).setCellRenderer(rightRenderer);

        tcm.getColumn(4).setPreferredWidth(120);
        tcm.getColumn(4).setMaxWidth(120);
        tcm.getColumn(4).setCellRenderer(rightRenderer);

        tcm.getColumn(5).setPreferredWidth(120);
        tcm.getColumn(5).setMaxWidth(120);
        tcm.getColumn(5).setCellRenderer(rightRenderer);
    }

    private void dungPnlMucTongKetHoaDonVaThoat(){
        pnlMucTongKetHoaDonVaThanhToan.setBackground(bgrMacDinh);
        pnlMucTongKetHoaDonVaThanhToan.setPreferredSize(dimPnlMucTongKetHoaDonVaThanhToan);
        pnlMucTongKetHoaDonVaThanhToan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThongTinHoaDonBanHang();
        pnlMucTongKetHoaDonVaThanhToan.add(pnlThongTinHoaDonBanHang);

        dungPnlThoat();
        pnlMucTongKetHoaDonVaThanhToan.add(pnlThanhToan);
    }

    private void dungPnlThongTinHoaDonBanHang(){
        pnlThongTinHoaDonBanHang.setBackground(bgrMacDinh);
        pnlThongTinHoaDonBanHang.setPreferredSize(dimPnlThongTinHoaDonBanHang);
        pnlThongTinHoaDonBanHang.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));

        dungCacPnlChuaThongTinCuaHDBH(pnlMaHDBH, lblMaHDBH, txtMaHDBH);
        pnlThongTinHoaDonBanHang.add(pnlMaHDBH);

        dungCacPnlChuaThongTinCuaHDBH(pnlMaNVLap, lblMaNVLap, txtMaNVLap);
        pnlThongTinHoaDonBanHang.add(pnlMaNVLap);

        dungCacPnlChuaThongTinCuaHDBH(pnlTTKH, lblTTKH, scrTTKH);
        pnlThongTinHoaDonBanHang.add(pnlTTKH);

        dungCacPnlChuaThongTinCuaHDBH(pnlTGLap, lblTGLap, txtTGLap);
        pnlThongTinHoaDonBanHang.add(pnlTGLap);

        dungCacPnlChuaThongTinCuaHDBH(pnlMaHDBH, lblMaHDBH, txtMaHDBH);
        pnlThongTinHoaDonBanHang.add(pnlMaHDBH);

        dungCacPnlChuaThongTinCuaHDBH(pnlSLSP, lblTieuDePnlSLSP, txtSLSP);
        pnlThongTinHoaDonBanHang.add(pnlSLSP);

        dungCacPnlChuaThongTinCuaHDBH(pnlTongTien, lblTieuDePnlTongTien, txtTongTien);
        pnlThongTinHoaDonBanHang.add(pnlTongTien);

        dungCacPnlChuaThongTinCuaHDBH(pnlVAT, lblTieuDePnlVAT, txtVAT);
        pnlThongTinHoaDonBanHang.add(pnlVAT);

        dungCacPnlChuaThongTinCuaHDBH(pnlTienKhachPhaiTra, lblTieuDePnlTienKhachPhaiTra, txtTienKhachPhaiTra);
        lblTieuDePnlTienKhachPhaiTra.setFont(
                new Font(tenFontMacDinh, Font.BOLD, 18)
        );
        pnlThongTinHoaDonBanHang.add(pnlTienKhachPhaiTra);

        dungCacPnlChuaThongTinCuaHDBH(pnlTienKhachDua, lblTieuDePnlTienKhachDua, txtTienKhachDua);
        pnlThongTinHoaDonBanHang.add(pnlTienKhachDua);

        dungCacPnlChuaThongTinCuaHDBH(pnlTienThua, lblTieuDePnlTienThua, txtTienThua);
        pnlThongTinHoaDonBanHang.add(pnlTienThua);
    }

    private void dungCacPnlChuaThongTinCuaHDBH(JPanel pnl, JLabel lbl, JTextField txt){
        pnl.setLayout(new BorderLayout());
        pnl.setPreferredSize(dimPnlConCuaPnlTTHoaDonBanHang);
        pnl.setBackground(bgrMacDinh);

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl, BorderLayout.WEST);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                dimTxtChuaThongTinCuaHDBH
        );

        txt.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, doDayVienMacDinh, 0, bgrVienMacDinh
                )
        );

        txt.setForeground(frgMacDinh);

        txt.setBorder(null);
        txt.setEnabled(false);
        txt.setBackground(bgrMacDinh);

        txt.setHorizontalAlignment(SwingConstants.RIGHT);
        pnl.add(txt, BorderLayout.EAST);
    }

    private void dungCacPnlChuaThongTinCuaHDBH(JPanel pnl, JLabel lbl, JScrollPane scr){
        pnl.setLayout(new BorderLayout());
        pnl.setPreferredSize(dimPnlConCuaPnlTTHoaDonBanHang);
        pnl.setBackground(bgrMacDinh);

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl, BorderLayout.WEST);

        CacHamDungSan.datThuocTinhChoTxt(
                txtTTKH,
                "",
                null
        );
        txtTTKH.setEnabled(false);
        txtTTKH.setForeground(frgMacDinh);
        txtTTKH.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTTKH.setBorder(BorderFactory.createEmptyBorder());

        scr.setPreferredSize(dimTxtChuaThongTinCuaHDBH);
        scr.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, doDayVienMacDinh, 0, bgrVienMacDinh
                )
        );
        scr.setBorder(null);
        scr.setBackground(bgrMacDinh);
        scr.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scr.getViewport().setBackground(bgrMacDinh);

        pnl.add(scr, BorderLayout.EAST);
    }

    private void dungPnlThoat(){
        pnlThanhToan.setBackground(bgrMacDinh);
        pnlThanhToan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThanhToan.setPreferredSize(dimPnlThanhToan);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                mauNenBtnThoat,
                Color.WHITE,
                dimBtnThoat
        );
        btnThoat.setFont(fntBtnThoat);
        datHanhDongChoBtnThanhToan();
        pnlThanhToan.add(btnThoat);
    }

    private void datHanhDongChoBtnThanhToan(){
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    datCacThanhPhanCuaGDTaoHoaDonBanHangVeTinhTrangBanDau();

                    dispose();
                });
            }
        });
    }

    private static void datCacThanhPhanCuaGDTaoHoaDonBanHangVeTinhTrangBanDau(){
        txtTimKiemSanPham.setText("");
        txtTimKiemSanPham.requestFocus();

        txtTTKH.setText("");

        dtmDanhSachSanPhamKHMua.setRowCount(0);

        txtSLSP.setText("0");
        txtTongTien.setText("0");
        txtVAT.setText("0");
        txtTienKhachPhaiTra.setText("0");
        txtTienKhachDua.setText("");
        txtTienThua.setText("0");
    }

    public static void hienThiTTHDBanHang(HoaDonBanHang hoaDonBanHang){
        ArrayList<ChiTietHoaDonBanHang> dsChiTietHDBH = new ArrayList<>(hoaDonBanHang.getDanhSachChiTietHDBanHang());

        dsChiTietHDBH.forEach(ct -> {
            dtmDanhSachSanPhamKHMua.addRow(
                    chuyenChiTietHoaDonBanHangSangObject(ct)
            );
        });

        txtMaHDBH.setText(hoaDonBanHang.getMaHoaDonBH() + "");
        txtMaNVLap.setText(hoaDonBanHang.getNhanVienLapHDBH().getMaNV());
        txtTTKH.setText(
                hoaDonBanHang.getKhachHang().getHoTen() + " - "
                + hoaDonBanHang.getKhachHang().getSoDT()
        );
        txtTGLap.setText(dtf.format(hoaDonBanHang.getThoiGianLap()));
        txtSLSP.setText(df.format(hoaDonBanHang.getTongSLSP()));
        txtTongTien.setText(nf.format(hoaDonBanHang.getThanhTienChuaThue()));
        txtVAT.setText(nf.format(hoaDonBanHang.getThueGTGT()));
        txtTienKhachPhaiTra.setText(nf.format(hoaDonBanHang.getTongTien()));
        txtTienKhachDua.setText(nf.format(hoaDonBanHang.getSoTienKhachDua()));
        txtTienThua.setText(nf.format(hoaDonBanHang.getSoTienThoiLai()));
    }
}
