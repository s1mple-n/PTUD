package ui.giaodienchinh.pnlqlnhaphang.gdxemthongtinhoadonnhaphang;

import entity.ChiTietHoaDonNhapHang;
import entity.HoaDonNhapHang;
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

public class GDXemThongTinHoaDonNhapHang extends JDialog implements IDSBienGDXemThongTinHoaDonNhapHang {
    private static GDXemThongTinHoaDonNhapHang gdXemThongTinHoaDonNhapHang = null;

    public GDXemThongTinHoaDonNhapHang(){
        setTitle("Tạo hoá đơn bán hàng");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setSize(dimGDXemTTHoaDonNhapHang);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static GDXemThongTinHoaDonNhapHang getGdXemThongTinHoaDonNhapHang() {
        if (gdXemThongTinHoaDonNhapHang == null)
            gdXemThongTinHoaDonNhapHang = new GDXemThongTinHoaDonNhapHang();
        return gdXemThongTinHoaDonNhapHang;
    }

    private void dungUI(){
        dungPnlThanhCongCu();
        getContentPane().add(pnlThanhCongCu, BorderLayout.NORTH);

        dungPnlHoaDonNhapHangChinh();
        getContentPane().add(pnlHoaDonNhapHangChinh, BorderLayout.CENTER);
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
                trsDanhSachSPNhapHang.setRowFilter(
                        RowFilter.regexFilter(
                                txtTimKiemSanPham.getText().trim()
                        )
                );
            }
        });
    }

    private static Object[] chuyenChiTietHoaDonNhapHangSangObject(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang){
        Object[] o = {
                tblDanhSachSanPhamNhapHang.getRowCount() + 1,
                chiTietHoaDonNhapHang.getSanPham().getMaSP(),
                chiTietHoaDonNhapHang.getSanPham().getTenSP(),
                chiTietHoaDonNhapHang.getSoLuongNhap(),
                nf.format(chiTietHoaDonNhapHang.getDonGiaNhap()),
                nf.format(chiTietHoaDonNhapHang.getThanhTien())
        };

        return o;
    }

    private void dungPnlHoaDonNhapHangChinh(){
        pnlHoaDonNhapHangChinh.setBackground(bgrMacDinh);
        pnlHoaDonNhapHangChinh.setPreferredSize(dimPnlTaoHoaDonNhapHangChinh);
        pnlHoaDonNhapHangChinh.setLayout(new BorderLayout());

        dungPnlDanhSachSanPhamNhapHang();
        pnlHoaDonNhapHangChinh.add(pnlDanhSachSanPhamNhapHang, BorderLayout.CENTER);

        dungPnlMucTongKetHoaDonVaThoat();
        pnlHoaDonNhapHangChinh.add(pnlMucTongKetVaThoat, BorderLayout.EAST);
    }

    private void dungPnlDanhSachSanPhamNhapHang(){
        pnlDanhSachSanPhamNhapHang.setBackground(bgrMacDinh);
        pnlDanhSachSanPhamNhapHang.setPreferredSize(dimPnlDanhSachSanPhamNhapHang);
        pnlDanhSachSanPhamNhapHang.setLayout(new BorderLayout());

        dungScrChuaTblDanhSachSPNhapHang();
        pnlDanhSachSanPhamNhapHang.add(scrChuaTblDSSanPhamNhapHang, BorderLayout.CENTER);
    }

    private void dungScrChuaTblDanhSachSPNhapHang(){
        dungTblDanhSachSPNhapHang();

        scrChuaTblDSSanPhamNhapHang.setPreferredSize(dimPnlDanhSachSanPhamNhapHang);
        scrChuaTblDSSanPhamNhapHang.getViewport().setBackground(bgrMacDinh);
        scrChuaTblDSSanPhamNhapHang.setBorder(null);
        scrChuaTblDSSanPhamNhapHang.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 0, 3, bgrVienMacDinh
                )
        );
    }

    private void dungTblDanhSachSPNhapHang(){
        tblDanhSachSanPhamNhapHang.setDefaultEditor(Object.class, null);
        tblDanhSachSanPhamNhapHang.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblDanhSachSanPhamNhapHang.setFont(fntMacDinh);
        tblDanhSachSanPhamNhapHang.getTableHeader().setFont(fntMacDinh);
        tblDanhSachSanPhamNhapHang.setShowGrid(false);
        tblDanhSachSanPhamNhapHang.setGridColor(Color.WHITE);
        tblDanhSachSanPhamNhapHang.setSelectionBackground(bgrTableRowKhiDuocChon);
        tblDanhSachSanPhamNhapHang.setRowSorter(trsDanhSachSPNhapHang);

        tblDanhSachSanPhamNhapHang.getTableHeader().setFont(fntMacDinh);
        tblDanhSachSanPhamNhapHang.getTableHeader().setPreferredSize(
                new Dimension(
                        dimPnlDanhSachSanPhamNhapHang.width,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblDanhSachSanPhamNhapHang.getTableHeader().setBackground(bgrTieuDeTable);

        canLeVaDatChieuRongChoCacGiaTriTrongTblDanhSachSPNhapHang();
    }

    private void canLeVaDatChieuRongChoCacGiaTriTrongTblDanhSachSPNhapHang(){
        TableColumnModel tcm = tblDanhSachSanPhamNhapHang.getColumnModel();

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
        pnlMucTongKetVaThoat.setBackground(bgrMacDinh);
        pnlMucTongKetVaThoat.setPreferredSize(dimPnlMucTongKetVaThoat);
        pnlMucTongKetVaThoat.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThongTinHoaDonNhapHang();
        pnlMucTongKetVaThoat.add(pnlThongTinHoaDonNhapHang);

        dungPnlThoat();
        pnlMucTongKetVaThoat.add(pnlThoat);
    }

    private void dungPnlThongTinHoaDonNhapHang(){
        pnlThongTinHoaDonNhapHang.setBackground(bgrMacDinh);
        pnlThongTinHoaDonNhapHang.setPreferredSize(dimPnlThongTinHoaDonNhapHang);
        pnlThongTinHoaDonNhapHang.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));

        dungCacPnlChuaThongTinCuaHDNH(pnlMaHDNH, lblMaHDNH, txtMaHDNH);
        pnlThongTinHoaDonNhapHang.add(pnlMaHDNH);

        dungCacPnlChuaThongTinCuaHDNH(pnlMaNVLap, lblMaNVLap, txtMaNVLap);
        pnlThongTinHoaDonNhapHang.add(pnlMaNVLap);

        dungCacPnlChuaThongTinCuaHDNH(pnlMaLoHang, lblMaLoHang, txtMaLoHang);
        pnlThongTinHoaDonNhapHang.add(pnlMaLoHang);

        dungCacPnlChuaThongTinCuaHDNH(pnlTenNguoiGiaoHang, lblTenNguoiGiaoHang, txtTenNguoiGiaoHang);
        pnlThongTinHoaDonNhapHang.add(pnlTenNguoiGiaoHang);

        dungCacPnlChuaThongTinCuaHDNH(pnlTGDat, lblTGDat, txtTGDat);
        pnlThongTinHoaDonNhapHang.add(pnlTGDat);

        dungCacPnlChuaThongTinCuaHDNH(pnlTGGiao, lblTGGiao, txtTGGiao);
        pnlThongTinHoaDonNhapHang.add(pnlTGGiao);

        dungCacPnlChuaThongTinCuaHDNH(pnlSLSP, lblTieuDePnlSLSP, txtSLSP);
        pnlThongTinHoaDonNhapHang.add(pnlSLSP);

        dungCacPnlChuaThongTinCuaHDNH(pnlTongTien, lblTieuDePnlTongTien, txtTongTien);
        pnlThongTinHoaDonNhapHang.add(pnlTongTien);
    }

    private void dungCacPnlChuaThongTinCuaHDNH(JPanel pnl, JLabel lbl, JTextField txt){
        pnl.setLayout(new BorderLayout());
        pnl.setPreferredSize(dimPnlConCuaPnlTTHoaDonNhapHang);
        pnl.setBackground(bgrMacDinh);

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl, BorderLayout.WEST);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                dimTxtChuaThongTinCuaHDNH
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

    private void dungPnlThoat(){
        pnlThoat.setBackground(bgrMacDinh);
        pnlThoat.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThoat.setPreferredSize(dimPnlInHoaDon);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                mauNenBtnThoat,
                Color.WHITE,
                dimBtnThoat
        );
        btnThoat.setFont(fntBtnThoat);
        datHanhDongChoBtnThoat();
        pnlThoat.add(btnThoat);
    }

    private void datHanhDongChoBtnThoat(){
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    datCacThanhPhanCuaGDXemTTHDNHVeTinhTrangBanDau();

                    dispose();
                });
            }
        });
    }

    private static void datCacThanhPhanCuaGDXemTTHDNHVeTinhTrangBanDau(){
        txtTimKiemSanPham.setText("");
        txtTimKiemSanPham.requestFocus();

        txtMaLoHang.setText("");

        dtmDanhSachSanPhamNhapHang.setRowCount(0);

        txtSLSP.setText("0");
        txtTongTien.setText("0");
    }

    public static void hienThiTTHDNhapHang(HoaDonNhapHang hoaDonNhapHang){
        ArrayList<ChiTietHoaDonNhapHang> dsChiTietHDNH = new ArrayList<>(hoaDonNhapHang.getDanhSachChiTietHDNhapHang());

        dsChiTietHDNH.forEach(ct -> {
            dtmDanhSachSanPhamNhapHang.addRow(
                    chuyenChiTietHoaDonNhapHangSangObject(ct)
            );
        });

        txtMaHDNH.setText(hoaDonNhapHang.getMaHDNH() + "");
        txtMaNVLap.setText(hoaDonNhapHang.getNhanVienLapHDNH().getMaNV());
        txtMaLoHang.setText(hoaDonNhapHang.getMaLoHang() + "");
        txtTenNguoiGiaoHang.setText(hoaDonNhapHang.getTenNguoiGiaoHang());
        txtTGDat.setText(dtf.format(hoaDonNhapHang.getThoiGianDatHang()));
        txtTGGiao.setText(dtf.format(hoaDonNhapHang.getThoiGianGiaoHang()));
        txtSLSP.setText(df.format(hoaDonNhapHang.getTongSLSP()));
        txtTongTien.setText(nf.format(hoaDonNhapHang.getTongTien()));
    }
}
