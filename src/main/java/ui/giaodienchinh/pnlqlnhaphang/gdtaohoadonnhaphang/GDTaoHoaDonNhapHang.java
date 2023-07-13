package ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang;

import dao.HoaDonNhapHangDAO;
import entity.ChiTietHoaDonNhapHang;
import entity.HoaDonNhapHang;
import services.CacHamDungSan;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.kieudulieudacbiet.NutDuocNhungTrongJtable;
import ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.gdthemsanpham.GDThemSanPham;
import ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.giaodiencanhbaohuyhoadonnhaphang.GDCanhBaoHuyHoaDonNhapHang;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class GDTaoHoaDonNhapHang extends JFrame implements IDSBienGDTaoHoaDonNhapHang {
    private static GDTaoHoaDonNhapHang gdTaoHoaDonNhapHang = null;

    private GDTaoHoaDonNhapHang(){
        setTitle("Tạo hoá đơn nhập hàng");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setSize(dimGDTaoHoaDonNhapHang);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        datKeyMap();

        chongDongGDTaoHoaDonNhapHangBuaBai();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                txtTimKiemChiTietHoaDonNhapHang.requestFocus();
            }
        });
    }

    public static GDTaoHoaDonNhapHang getGdTaoHoaDonNhapHang() {
        if (gdTaoHoaDonNhapHang == null)
            gdTaoHoaDonNhapHang = new GDTaoHoaDonNhapHang();
        thoiGianDatHang.set(LocalDateTime.now());
        return gdTaoHoaDonNhapHang;
    }

    private void chongDongGDTaoHoaDonNhapHangBuaBai(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                hienThiGDCanhBaoHuyHoaDonNhapHang();
            }
        });
    }

    private void datKeyMap(){
        for (int i = 0; i < danhSachKeyMap.length; ++i){

            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                    KeyStroke.getKeyStroke(danhSachKeyMap[i], 0),
                    danhSachKeyMap[i]
            );

            int finalI = i;

            getRootPane().getActionMap().put(
                    danhSachKeyMap[i],
                    new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (finalI == 0){
                                xoaTatCaSanPhamDaThem();
                            }
                            else if (finalI == 1){
                                hienThiGDThemSanPham();
                            }
                            else if (finalI == 2){
                                thucHienThuTucNhapHangBangExcel();
                            }
                            else if (finalI == 3){
                                thuNhoManHinh();
                            }
                            else if (finalI == 4){
                                hienThiGDCanhBaoHuyHoaDonNhapHang();
                            }
                            else if (finalI == 5){
                                txtMaLoHang.requestFocus();
                            }
                            else if (finalI == 6){
                                txtTenNguoiGiaoHang.requestFocus();
                            }
                            else {
                                thucHienThuTucInHoaDonNhapHang();
                            }
                        }
                    }
            );
        }
    }

    private void hienThiGDCanhBaoHuyHoaDonNhapHang(){
        SwingUtilities.invokeLater(() -> {
            GDCanhBaoHuyHoaDonNhapHang gdCanhBaoHuyHoaDonNhapHang = GDCanhBaoHuyHoaDonNhapHang.getGdCanhBaoHuyHoaDonNhapHang();
            gdCanhBaoHuyHoaDonNhapHang.setVisible(true);
        });
    }

    public static boolean isDangHienThi(){
        if (gdTaoHoaDonNhapHang != null && gdTaoHoaDonNhapHang.isDisplayable())
            return true;
        return false;
    }

    public static void huyHoaDonNhapHang(){
        datCacThanhPhanCuaGDTaoHoaDonNhapHangHangVeTinhTrangBanDau();

        gdTaoHoaDonNhapHang.dispose();
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
        pnlThanhCongCu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));

        dungPnlHopTienIch();
        pnlThanhCongCu.add(pnlHopTienIch);

        dungPnlHopCongCu();
        pnlThanhCongCu.add(pnlHopCongCu);

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
                txtTimKiemChiTietHoaDonNhapHang,
                "Tìm sản phẩm theo mã hoặc tên",
                dimTxtTimKiemNhapHang
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiemChiTietHoaDonNhapHang);
        datHanhDongChoTxtTimKiemSanPham();
        pnlHopTienIch.add(txtTimKiemChiTietHoaDonNhapHang);

        CacHamDungSan.datThuocTinhChoBtn(btnXoaTatCaChiTietHDNHDaThem, mauNenPnlThanhCongCu, Color.BLACK, dimBtnXoaTatCaChiTietHDNHDaThem);
        datHanhDongChoBtnXoaTatCaSanPhamDaThem();
        btnXoaTatCaChiTietHDNHDaThem.setToolTipText("Xoá tất cả sản phẩm đã thêm (F1)");
        pnlHopTienIch.add(btnXoaTatCaChiTietHDNHDaThem);
    }

    private void datHanhDongChoTxtTimKiemSanPham(){
        txtTimKiemChiTietHoaDonNhapHang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsDSChiTietHDNH.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiemChiTietHoaDonNhapHang.getText().trim()
                        )
                );
            }
        });
    }

    private static void themHoacBoBotSanPhamTrongDSChiTietHDNH(String maSP, int soLuong){
        AtomicInteger viTriCuaSPTrongMap = new AtomicInteger(0);

        dsChiTietHoaDonNhapHang.forEach(ct -> {
            if (ct.getSanPham().getMaSP().equals(maSP)){
                if (soLuong < 0 && soLuong <= ct.getSoLuongNhap() * -1){
                    int row = tblDSChiTietHDNH.getSelectedRow();

                    dtmDSChiTietHDNH.removeRow(row);

                    dsChiTietHoaDonNhapHang.removeIf(t -> (t.getSanPham().getMaSP().equals(maSP)));

                    capNhatTinhHinhNhapHang();

                    new Thread(GDTaoHoaDonNhapHang::capNhatSTTChoTblDanhSachSanPhamKHMua).start();
                }
                else{
                    ct.setSoLuongNhap(ct.getSoLuongNhap() + soLuong);
                    ct.setThanhTien(ct.getSoLuongNhap() * ct.getDonGiaNhap());

                    tblDSChiTietHDNH.setValueAt(
                            ct.getSoLuongNhap(), viTriCuaSPTrongMap.get(), 4
                    );

                    tblDSChiTietHDNH.setValueAt(
                            nf.format(ct.getThanhTien()), viTriCuaSPTrongMap.get(), 6
                    );
                }
            }
            else{
                viTriCuaSPTrongMap.getAndIncrement();
            }
        });

        capNhatTinhHinhNhapHang();
    }

    private static void capNhatTinhHinhNhapHang(){
        slsp.set(0);
        tongTien.set(0.0);


        dsChiTietHoaDonNhapHang.forEach(ct -> {
            slsp.getAndSet(slsp.get() + ct.getSoLuongNhap());
            tongTien.getAndSet(tongTien.get() + ct.getThanhTien());
        });

        txtSLSP.setText(slsp.get() + "");
        txtTongTien.setText(nf.format(tongTien.get()));
    }

    private static void capNhatSTTChoTblDanhSachSanPhamKHMua(){
        int stt = 1;

        for (int i = 0; i < tblDSChiTietHDNH.getRowCount(); ++i){
            tblDSChiTietHDNH.setValueAt(
                    stt,
                    i,
                    0
            );

            stt++;
        }
    }

    private static Object[] chuyenChiTietHoaDonNhapHangSangObject(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang){
        NutDuocNhungTrongJtable btnXoaChiTietHDBH = new NutDuocNhungTrongJtable(
                tblDSChiTietHDNH,
                xoaMotChiTietHoaDonBanHang(),
                1);

        Object[] o = {
                tblDSChiTietHDNH.getRowCount() + 1,
                new ImageIcon(
                        "src/main/resources/BieuTuong/Bin_24px_1.png"
                ),
                chiTietHoaDonNhapHang.getSanPham().getMaSP(),
                chiTietHoaDonNhapHang.getSanPham().getTenSP(),
                chiTietHoaDonNhapHang.getSoLuongNhap(),
                nf.format(chiTietHoaDonNhapHang.getDonGiaNhap()),
                nf.format(chiTietHoaDonNhapHang.getThanhTien())
        };

        return o;
    }

    private static Action xoaMotChiTietHoaDonBanHang(){
        Action a = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.parseInt( e.getActionCommand() );

                String maSP = (String) table.getValueAt(modelRow, 2);

                ((DefaultTableModel)table.getModel()).removeRow(modelRow);

                dsChiTietHoaDonNhapHang.removeIf(t -> (t.getSanPham().getMaSP().equals(maSP)));

                capNhatTinhHinhNhapHang();

                new Thread(GDTaoHoaDonNhapHang::capNhatSTTChoTblDanhSachSanPhamKHMua).start();
            }
        };

        return a;
    }

    private void datHanhDongChoBtnXoaTatCaSanPhamDaThem(){
        btnXoaTatCaChiTietHDNHDaThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xoaTatCaSanPhamDaThem();
            }
        });
    }

    private void xoaTatCaSanPhamDaThem(){
        if (tblDSChiTietHDNH.getRowCount() > 0){
            int luaChon = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn chắc chắn muốn xoá hết các sản phẩm đã thêm chứ?",
                    "Cảnh báo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (luaChon == JOptionPane.YES_OPTION){
                dtmDSChiTietHDNH.setRowCount(0);

                dsChiTietHoaDonNhapHang.clear();

                capNhatTinhHinhNhapHang();
            }
        }
    }

    private void dungPnlHopCongCu(){
        pnlHopCongCu.setPreferredSize(dimPnlHopCongCu);
        pnlHopCongCu.setBackground(mauNenPnlThanhCongCu);
        pnlHopCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));

        CacHamDungSan.datThuocTinhChoBtn(
                btnThemChiTietHDNH,
                IDSBienMacDinh.bgrBtnThem,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnThemCTHDNHVaNhapExcel
        );
        btnThemChiTietHDNH.setToolTipText("Thêm từng sản phẩm một");
        datHanhDongChoBtnThemCTHDNH();
        pnlHopCongCu.add(btnThemChiTietHDNH);

        CacHamDungSan.datThuocTinhChoBtn(
                btnNhapFileExcel,
                IDSBienMacDinh.bgrBtnThem,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnThemCTHDNHVaNhapExcel
        );
        btnNhapFileExcel.setToolTipText("Thêm bằng file Excel có sẵn");
        datHanhDongChoBtnNhapFileExcel();
        pnlHopCongCu.add(btnNhapFileExcel);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThuNhoManHinh,
                mauNenBtnThuNhoManHinh,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnThuNhoVaHuyHD
        );
        datHanhDongChoBtnThuNhoManHinh();
        btnThuNhoManHinh.setToolTipText("Tạm thoát khỏi giao diện bán hàng");
        pnlHopCongCu.add(btnThuNhoManHinh);

        CacHamDungSan.datThuocTinhChoBtn(
                btnHuyHoaDonNhapHang,
                mauNenBtnHuyHoaDonNhapHang,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnThuNhoVaHuyHD
        );
        datHanhDongChoBtnHuyHoaDonNhapHang();
        btnHuyHoaDonNhapHang.setToolTipText("Huỷ hoá đơn bán hàng");
        pnlHopCongCu.add(btnHuyHoaDonNhapHang);
    }

    private void datHanhDongChoBtnThemCTHDNH(){
        btnThemChiTietHDNH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hienThiGDThemSanPham();
            }
        });
    }

    private void hienThiGDThemSanPham(){
        SwingUtilities.invokeLater(() -> {
            GDThemSanPham gd = GDThemSanPham.getGdXemTTSanPham();
            gd.setVisible(true);
        });
    }

    private void datHanhDongChoBtnThuNhoManHinh(){
        btnThuNhoManHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thuNhoManHinh();
            }
        });
    }

    private void thuNhoManHinh(){
        setState(ICONIFIED);
    }

    private void datHanhDongChoBtnHuyHoaDonNhapHang(){
        btnHuyHoaDonNhapHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hienThiGDCanhBaoHuyHoaDonNhapHang();
            }
        });
    }

    private void dungPnlHoaDonNhapHangChinh(){
        pnlHoaDonNhapHangChinh.setBackground(bgrMacDinh);
        pnlHoaDonNhapHangChinh.setPreferredSize(dimPnlTaoHoaDonNhapHangChinh);
        pnlHoaDonNhapHangChinh.setLayout(new BorderLayout());

        dungPnlDSChiTietHDNH();
        pnlHoaDonNhapHangChinh.add(pnlDanhSachChiTietHDNH, BorderLayout.CENTER);

        dungPnlMucTongKetVaInHoaDon();
        pnlHoaDonNhapHangChinh.add(pnlMucTongKetVaInHoaDon, BorderLayout.EAST);
    }

    private void dungPnlDSChiTietHDNH(){
        pnlDanhSachChiTietHDNH.setBackground(bgrMacDinh);
        pnlDanhSachChiTietHDNH.setPreferredSize(dimPnlDanhSachChiTietHDNH);
        pnlDanhSachChiTietHDNH.setLayout(new BorderLayout());

        dungScrChuaTblDSChiTietHDNH();
        pnlDanhSachChiTietHDNH.add(scrChuaTblDSChiTietHDNH, BorderLayout.CENTER);
    }

    private void dungScrChuaTblDSChiTietHDNH(){
        dungTblDSChiTietHDNH();
        datHanhDongChoTblDSChiTietHDNH();

        scrChuaTblDSChiTietHDNH.setPreferredSize(dimPnlDanhSachChiTietHDNH);
        scrChuaTblDSChiTietHDNH.getViewport().setBackground(bgrMacDinh);
        scrChuaTblDSChiTietHDNH.setBorder(null);
        scrChuaTblDSChiTietHDNH.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 0, 3, bgrVienMacDinh
                )
        );
    }

    private void dungTblDSChiTietHDNH(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDSChiTietHDNH,
                dimPnlDanhSachChiTietHDNH,
                trsDSChiTietHDNH
        );
        tblDSChiTietHDNH.getTableHeader().setPreferredSize(new Dimension(0, 0));

        canLeChoCacGiaTriTrongTblDanhSachSPKHMua();

        datChieuRongChoCacGiaTriTrongTblDanhSachSPKHMua();
    }

    private void canLeChoCacGiaTriTrongTblDanhSachSPKHMua(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < tblDSChiTietHDNH.getColumnCount(); ++i){
            if (i == 0 || i == 1|| i == 4 || i == 5 || i == 6){
                tblDSChiTietHDNH.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else if (i == 7 || i == 8){
                tblDSChiTietHDNH.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            }
        }
    }

    private void datChieuRongChoCacGiaTriTrongTblDanhSachSPKHMua(){
        tblDSChiTietHDNH.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDSChiTietHDNH.getColumnModel().getColumn(0).setMaxWidth(50);

        tblDSChiTietHDNH.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblDSChiTietHDNH.getColumnModel().getColumn(1).setMaxWidth(60);

        tblDSChiTietHDNH.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblDSChiTietHDNH.getColumnModel().getColumn(2).setMaxWidth(120);

        tblDSChiTietHDNH.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblDSChiTietHDNH.getColumnModel().getColumn(4).setMaxWidth(60);

        tblDSChiTietHDNH.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblDSChiTietHDNH.getColumnModel().getColumn(5).setMaxWidth(100);

        tblDSChiTietHDNH.getColumnModel().getColumn(6).setPreferredWidth(120);
        tblDSChiTietHDNH.getColumnModel().getColumn(6).setMaxWidth(120);
    }

    private void datHanhDongChoTblDSChiTietHDNH(){
        tblDSChiTietHDNH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int soLanClick = e.getClickCount();
                    int nutChuotDuocNhan = e.getButton();

                    if (soLanClick == 2 && nutChuotDuocNhan == 1){
                        int row = tblDSChiTietHDNH.getSelectedRow();

                        if (row != -1) {
                            JTextField txtSoLuong = new JTextField();
                            CacHamDungSan.datThuocTinhChoTxt(
                                    txtSoLuong,
                                    "",
                                    dimTxtMaLoHang
                            );
                            CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSoLuong);
                            CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoLuong);

                            int soLuong = JOptionPane.showConfirmDialog(
                                    null,
                                    txtSoLuong,
                                    "Số lượng sản phẩm cần thêm",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );

                            if (soLuong == JOptionPane.OK_OPTION){
                                String maSP = (String) tblDSChiTietHDNH.getValueAt(
                                        row, 2
                                );

                                int slHienTai = (int) tblDSChiTietHDNH.getValueAt(
                                        row, 4
                                );

                                int slCanThem = Integer.parseInt(txtSoLuong.getText().trim());

                                themHoacBoBotSanPhamTrongDSChiTietHDNH(maSP, slCanThem);
                            }
                        }
                    }

                    else if (soLanClick == 2 && nutChuotDuocNhan == 3){
                        int row = tblDSChiTietHDNH.getSelectedRow();

                        if (row != -1) {
                            JTextField txtSoLuong = new JTextField();
                            CacHamDungSan.datThuocTinhChoTxt(
                                    txtSoLuong,
                                    "",
                                    dimTxtMaLoHang
                            );
                            CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSoLuong);
                            CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoLuong);

                            int soLuong = JOptionPane.showConfirmDialog(
                                    null,
                                    txtSoLuong,
                                    "Số lượng sản phẩm cần bỏ bớt",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );

                            if (soLuong == JOptionPane.OK_OPTION){
                                String maSP = (String) tblDSChiTietHDNH.getValueAt(
                                        row, 2
                                );

                                themHoacBoBotSanPhamTrongDSChiTietHDNH(maSP, Integer.parseInt(txtSoLuong.getText().trim()) * -1);
                            }
                        }
                    }
                });
            }
        });
    }

    private void dungPnlMucTongKetVaInHoaDon(){
        pnlMucTongKetVaInHoaDon.setBackground(bgrMacDinh);
        pnlMucTongKetVaInHoaDon.setPreferredSize(dimPnlMucTongKetVaInHoaDon);
        pnlMucTongKetVaInHoaDon.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThongTinHoaDonNhapHang();
        pnlMucTongKetVaInHoaDon.add(pnlThongTinHoaDonNhapHang);

        dungPnlInHoaDon();
        pnlMucTongKetVaInHoaDon.add(pnlInHoaDon);
    }

    private void dungPnlThongTinHoaDonNhapHang(){
        pnlThongTinHoaDonNhapHang.setBackground(bgrMacDinh);
        pnlThongTinHoaDonNhapHang.setPreferredSize(dimPnlThongTinHoaDonNhapHang);
        pnlThongTinHoaDonNhapHang.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        CacHamDungSan.datThuocTinhChoTxt(
                txtMaLoHang,
                "Mã lô hàng (F6)",
                dimTxtMaLoHang
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtMaLoHang);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtMaLoHang);
        datHanhDongChoTxtMaLoHang();
        pnlThongTinHoaDonNhapHang.add(txtMaLoHang);

        CacHamDungSan.datThuocTinhChoTxt(
                txtTenNguoiGiaoHang,
                "Tên người giao hàng (F7)",
                dimTxtMaLoHang
        );
        datHanhDongChoTxtTenNguoiGiaoHang();
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTenNguoiGiaoHang);

        pnlThongTinHoaDonNhapHang.add(txtTenNguoiGiaoHang);

        dungCacPnlChuaThongTinCuaHDNH(pnlTGDatHang, lblTieuDeTGDatHang, txtTGDatHang, true);
        txtTGDatHang.setText(
                dtf.format(
                        LocalDateTime.now()
                )
        );
        pnlThongTinHoaDonNhapHang.add(pnlTGDatHang);

        dungCacPnlChuaThongTinCuaHDNH(pnlSLSP, lblTieuDePnlSLSP, txtSLSP, true);
        pnlThongTinHoaDonNhapHang.add(pnlSLSP);

        dungCacPnlChuaThongTinCuaHDNH(pnlTongTien, lblTieuDePnlTongTien, txtTongTien, true);
        pnlThongTinHoaDonNhapHang.add(pnlTongTien);
    }

    private void datHanhDongChoTxtMaLoHang(){
        txtMaLoHang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (txtMaLoHang.getText().trim().isEmpty()){
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Mã lô hàng không được rỗng."
                        );
                        txtMaLoHang.requestFocus();
                    }
                    else{
                        txtTenNguoiGiaoHang.requestFocus();
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtTenNguoiGiaoHang(){
        txtTenNguoiGiaoHang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    thucHienThuTucInHoaDonNhapHang();
                }
            }
        });
    }

    private void dungCacPnlChuaThongTinCuaHDNH(JPanel pnl, JLabel lbl, JTextField txt, boolean isAnVien){
        pnl.setLayout(new BorderLayout());
        pnl.setPreferredSize(dimCacPnlThongTin);
        pnl.setBackground(bgrMacDinh);

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl, BorderLayout.WEST);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                dimTxtChuaThongTinNH
        );

        txt.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, doDayVienMacDinh, 0, bgrVienMacDinh
                )
        );

        txt.setForeground(frgMacDinh);

        if (isAnVien){
            txt.setBorder(null);
            txt.setEnabled(false);
        }
        txt.setBackground(bgrMacDinh);

        txt.setHorizontalAlignment(SwingConstants.RIGHT);
        pnl.add(txt, BorderLayout.EAST);
    }

    private void dungPnlInHoaDon(){
        pnlInHoaDon.setBackground(bgrMacDinh);
        pnlInHoaDon.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlInHoaDon.setPreferredSize(dimPnlInHoaDon);

        CacHamDungSan.datThuocTinhChoBtn(
                btnInHoaDon,
                mauNenBtnInHoaDon,
                Color.WHITE,
                dimBtnInHoaDon
        );
        btnInHoaDon.setFont(fntBtnInHoaDon);
        datHanhDongChoBtnInHoaDon();
        pnlInHoaDon.add(btnInHoaDon);
    }

    private void datHanhDongChoBtnInHoaDon(){
        btnInHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thucHienThuTucInHoaDonNhapHang();
            }
        });
    }

    private void thucHienThuTucInHoaDonNhapHang(){
        if (dsChiTietHoaDonNhapHang.size() == 0){
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Không thể thực hiện tác vụ in hoá đơn nhập hàng. " +
                            "Đơn nhập hàng này không có sản phẩm nào cả."
            );
            return;
        }

        if (txtMaLoHang.getText().trim().equals("Mã lô hàng (F6)") || txtMaLoHang.getText().trim().isEmpty()){
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Chưa xác định được mã lô hàng."
            );
            txtMaLoHang.requestFocus();
            return;
        }

        if (HoaDonNhapHangDAO.kiemTraTrungMaLoHang(
                Integer.parseInt(txtMaLoHang.getText().trim().toString())
        )){
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Cửa hàng đã từng nhập một lô hàng có mã này rồi."
            );
            txtMaLoHang.requestFocus();
            return;
        }

        if (txtTenNguoiGiaoHang.getText().trim().equals("Tên người giao hàng (F7)") || txtTenNguoiGiaoHang.getText().trim().isEmpty()){
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Chưa xác định được tên người giao hàng"
            );
            txtTenNguoiGiaoHang.requestFocus();
            return;
        }

        int maLoHang = Integer.parseInt(txtMaLoHang.getText().trim().toString());
        String tenNguoiGiaoHang = txtTenNguoiGiaoHang.getText().trim();
        LocalDateTime tgGiaoHang = LocalDateTime.now();

        HoaDonNhapHang hoaDonNhapHang = new HoaDonNhapHang(
                GDChinh.getNhanVienDangSuDung(),
                maLoHang,
                tenNguoiGiaoHang,
                thoiGianDatHang.get(),
                tgGiaoHang,
                dsChiTietHoaDonNhapHang
        );

        boolean kqThemHDNH = HoaDonNhapHangDAO.themHoaDonNhapHang(hoaDonNhapHang);

        if (kqThemHDNH){
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                    "Thêm thành công hoá đơn nhập hàng mới."
            );
            datCacThanhPhanCuaGDTaoHoaDonNhapHangHangVeTinhTrangBanDau();
        }
        else{
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Đã có lỗi xảy ra."
            );
        }
    }

    public static void themChiTietHoaDonNhapHangVaoTbl(ChiTietHoaDonNhapHang chiTietHoaDonNhapHang){
        AtomicInteger coHieu = new AtomicInteger(0);

        AtomicBoolean daTonTaiTrongDSNhap = new AtomicBoolean(false);

        dsChiTietHoaDonNhapHang.forEach(ct -> {
            if (ct.getSanPham().getMaSP().equals(chiTietHoaDonNhapHang.getSanPham().getMaSP())){
                themHoacBoBotSanPhamTrongDSChiTietHDNH(
                        ct.getSanPham().getMaSP(),
                        chiTietHoaDonNhapHang.getSoLuongNhap()
                );

                capNhatTinhHinhNhapHang();

                daTonTaiTrongDSNhap.set(true);
            }

            coHieu.getAndIncrement();
        });

        if (!daTonTaiTrongDSNhap.get()){
            dsChiTietHoaDonNhapHang.add(chiTietHoaDonNhapHang);

            dtmDSChiTietHDNH.addRow(
                    chuyenChiTietHoaDonNhapHangSangObject(chiTietHoaDonNhapHang)
            );

            capNhatTinhHinhNhapHang();

            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                    "Đã thêm sản phẩm vào danh sách nhập hàng."
            );
        }
    }

    /**
     * <p>Dang cho Hieu do viet them. Da goi ham san r.</p>
     */
    private void datHanhDongChoBtnNhapFileExcel(){
        btnNhapFileExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thucHienThuTucNhapHangBangExcel();
            }
        });
    }

    private void thucHienThuTucNhapHangBangExcel(){
        JFileChooser fileChooser = new JFileChooser(
                FileSystemView.getFileSystemView()
                        .getHomeDirectory().toPath().toString()
        ){
            @Override
            protected JDialog createDialog( Component parent ) throws HeadlessException {
                JDialog dialog = super.createDialog( parent );

                dialog.setIconImage(new ImageIcon(
                        "src/main/resources/BieuTuong/Logo2.jpg"
                ).getImage());
                return dialog;
            }
        };

        fileChooser.setDialogTitle("Nhập Hàng");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filterXLSX = new FileNameExtensionFilter("xlsx", "xlsx");
        FileNameExtensionFilter filterCSV = new FileNameExtensionFilter("csv", "csv");
        fileChooser.addChoosableFileFilter(filterXLSX);
        fileChooser.addChoosableFileFilter(filterCSV);

        int approve = fileChooser.showOpenDialog(fileChooser);
        if(approve == JFileChooser.APPROVE_OPTION) {
            String duongDan = fileChooser.getSelectedFile().toString().replace("\\", "/");

            try {

                Map<Integer, ArrayList<ChiTietHoaDonNhapHang>> hmNhapHang = TienIch.nhapHangTuFileExcel(duongDan);

                if(hmNhapHang == null) {
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_LOI,
                            String.format("Không có dữ liệu để nhập hàng! Vui lòng kiểm tra lại file %s!",
                                    duongDan.substring(duongDan.lastIndexOf("/") + 1))
                    );
                    return;
                }

                Integer maLoHang = Integer.parseInt(hmNhapHang.keySet().toArray()[0].toString());

                if (!HoaDonNhapHangDAO.kiemTraTrungMaLoHang(maLoHang)){
                    CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(
                            txtMaLoHang,
                            maLoHang + ""
                    );
                    dsChiTietHoaDonNhapHang.addAll(hmNhapHang.get(maLoHang));

                    dsChiTietHoaDonNhapHang.forEach(i -> {
                        dtmDSChiTietHDNH.addRow(chuyenChiTietHoaDonNhapHangSangObject(i));
                    });

                    capNhatTinhHinhNhapHang();
                }
                else{
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_LOI,
                            "Không nhập được. Cửa hàng đã từng nhập " +
                                    "một lô hàng có mã này rồi."
                    );
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                    "Đã nhập thành công!"
            );
        }
    }

    private static void datCacThanhPhanCuaGDTaoHoaDonNhapHangHangVeTinhTrangBanDau(){
        gdTaoHoaDonNhapHang.requestFocusInWindow();

        CacHamDungSan.duaTxtVeTrangThaiBanDau(
                txtTimKiemChiTietHoaDonNhapHang,
                "Tìm sản phẩm theo mã hoặc tên"
        );

        CacHamDungSan.duaTxtVeTrangThaiBanDau(
                txtMaLoHang,
                "Mã lô hàng (F6)"
        );

        CacHamDungSan.duaTxtVeTrangThaiBanDau(
                txtTenNguoiGiaoHang,
                "Tên người giao hàng (F7)"
        );

        CacHamDungSan.duaTxtVeTrangThaiBanDau(
                txtSLSP,
                "0"
        );

        CacHamDungSan.duaTxtVeTrangThaiBanDau(
                txtTongTien,
                "0"
        );

        dtmDSChiTietHDNH.setRowCount(0);

        dsChiTietHoaDonNhapHang.clear();
    }
}
