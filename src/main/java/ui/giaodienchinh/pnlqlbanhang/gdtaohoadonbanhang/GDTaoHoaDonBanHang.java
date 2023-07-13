package ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang;

import dao.HoaDonBanHangDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDonBanHang;
import entity.HoaDonBanHang;
import entity.KhachHang;
import entity.SanPham;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.gdthemkhachhang.GDThemKhachHang;
import ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.giaodiencanhbaohuyhoadonbanhang.GDCanhBaoHuyHoaDonBanHang;
import ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.kieudulieudacbiet.KhachHangTimDuoc;
import ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.kieudulieudacbiet.NutDuocNhungTrongJtable;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class GDTaoHoaDonBanHang extends JFrame implements IDSBienGDTaoHoaDonBanHang{
    private static GDTaoHoaDonBanHang gdTaoHoaDonBanHang = null;

    //
    AtomicInteger slsp = new AtomicInteger(0);
    AtomicReference<Double> tongTien = new AtomicReference<>(0.0);
    AtomicReference<Double> VAT = new AtomicReference<>(0.0);
    AtomicReference<Double> tienKhachPhaiTra = new AtomicReference<>(0.0);
    AtomicReference<Double> tienKhachDua = new AtomicReference<>(0.0);
    AtomicReference<Double> tienThua = new AtomicReference<>(0.0);
    //

    private GDTaoHoaDonBanHang(){
        setTitle("Tạo hoá đơn bán hàng");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setSize(dimGDTaoHoaDonBanHang);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        datKeyMap();

        chongDongGDTaoHoaDonBanHangBuaBai();

        chuanBiDuLieuKhachHang();
    }

    public static GDTaoHoaDonBanHang getGdTaoHoaDonBanHang() {
        if (gdTaoHoaDonBanHang == null)
            gdTaoHoaDonBanHang = new GDTaoHoaDonBanHang();
        return gdTaoHoaDonBanHang;
    }

    private void datKeyMap(){
        for (int i = 0; i < danhSachKeyMap.length; ++i) {

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
                            if (finalI == 0) {
                                xoaTatCaSanPhamDaThem();
                            } else if (finalI == 1) {
                                thuNhoManHinh();
                            } else if (finalI == 2) {
                                hienThiGDCanhBaoHuyHDBH();
                            } else if (finalI == 3) {
                                txtTimKiemKH.requestFocus();
                            } else if (finalI == 4) {
                                hienThiGDThemKhachHang();
                            } else if (finalI == 5) {
                                txtTienKhachDua.requestFocus();
                            } else {
                                thucHienThuTucThanhToan();
                            }
                        }
                    }
            );
        }
    }

    private void chongDongGDTaoHoaDonBanHangBuaBai(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    GDCanhBaoHuyHoaDonBanHang gdCanhBaoHuyHoaDonBanHang = GDCanhBaoHuyHoaDonBanHang.getGdCanhBaoHuyHoaDonBanHang();
                    gdCanhBaoHuyHoaDonBanHang.setVisible(true);
                });
            }
        });
    }

    public static boolean isDangHienThi(){
        if (gdTaoHoaDonBanHang != null && gdTaoHoaDonBanHang.isDisplayable())
            return true;
        return false;
    }

    public static void huyHoaDonBanHang(){
        datCacThanhPhanCuaGDTaoHoaDonBanHangVeTinhTrangBanDau();

        gdTaoHoaDonBanHang.dispose();
    }

    public static void chuanBiDuLieuKhachHang(){
        dsKhachHang.clear();

        Thread luongLoadDuLieuKhachHang = new Thread(() -> {
            try {
                ResultSet rs = TienIch.layDuLieuCoDieuKien("KhachHang", null);

                while (rs.next()){
                    KhachHang kh = new KhachHang(
                            rs.getInt(1),
                            MaHoaDuLieu.giaiMa(rs.getString(2)),
                            MaHoaDuLieu.giaiMa(rs.getString(3)),
                            MaHoaDuLieu.giaiMa(rs.getString(4)),
                            rs.getBoolean(5),
                            rs.getTimestamp(6).toLocalDateTime()
                    );

                    dsKhachHang.add(kh);
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        luongLoadDuLieuKhachHang.start();
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
        pnlThanhCongCu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 12));

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
                txtTimKiemSanPham,
                "Tìm sản phẩm theo mã hoặc tên",
                dimTxtTimKiemSanPham
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiemSanPham);
        datHanhDongChoTxtTimKiemSanPham();
        pnlHopTienIch.add(txtTimKiemSanPham);

        dungPmnKetQuaTimKiemSanPham();

        CacHamDungSan.datThuocTinhChoBtn(btnXoaTatCaSanPhamDaThem, mauNenPnlThanhCongCu, Color.BLACK, dimBtnXoaTatCaSanPhamDaThem);
        datHanhDongChoBtnXoaTatCaSanPhamDaThem();
        btnXoaTatCaSanPhamDaThem.setToolTipText("Xoá tất cả sản phẩm đã thêm (F1)");
        pnlHopTienIch.add(btnXoaTatCaSanPhamDaThem);
    }

    private void datHanhDongChoTxtTimKiemSanPham(){
        txtTimKiemSanPham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtTimKiemSanPham.getText().trim().toLowerCase();

                ResultSet dssp = SanPhamDAO.timKiemSanPhamTheoMaHoacTen(tuKhoa);

                if (tuKhoa.isEmpty()){
                    pmnKetQuaTimKiemSanPham.setVisible(false);
                }
                else{
                    if (tblKetQuaTimKiemSanPham.getRowCount() > 0){
                        duaDuLieuSanPhamTimDuocVaoTable(dssp);

                        tblKetQuaTimKiemSanPham.setEnabled(true);

                        pmnKetQuaTimKiemSanPham.setVisible(true);

                        pmnKetQuaTimKiemSanPham.show(
                                txtTimKiemSanPham,
                                0,
                                txtTimKiemSanPham.getHeight()
                        );

                        txtTimKiemSanPham.requestFocus();
                    }
                    else if (tblKetQuaTimKiemSanPham.getRowCount() == 0){
                        tblKetQuaTimKiemSanPham.setEnabled(false);

                        dtmKetQuaTimKiemSanPham.setRowCount(0);

                        Object[] duLieuTrong = {
                                "",
                                "( ^ _ ^ )",
                                ""
                        };
                        dtmKetQuaTimKiemSanPham.addRow(duLieuTrong);

                        datKichThuocChoPmnKetQuaTimKiemSP(1);
                    }
                }
            }
        });
    }

    private void duaDuLieuSanPhamTimDuocVaoTable(ResultSet dssp){
        dtmKetQuaTimKiemSanPham.setRowCount(0);

        int count = 0;

        try {
            while (dssp.next()){
                Object[] o = {
                    dssp.getString("maSP"),
                    dssp.getString("tenSP"),
                    dssp.getInt("soLuongTon")
                };

                count++;

                dtmKetQuaTimKiemSanPham.addRow(o);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        datKichThuocChoPmnKetQuaTimKiemSP(count);
    }

    private void dungPmnKetQuaTimKiemSanPham(){
        dungTblKetQuaTimKiemSanPham();
        pmnKetQuaTimKiemSanPham.add(tblKetQuaTimKiemSanPham, BorderLayout.CENTER);
        pmnKetQuaTimKiemSanPham.setBorder(BorderFactory.createEmptyBorder());
    }

    private void dungTblKetQuaTimKiemSanPham(){
        tblKetQuaTimKiemSanPham.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblKetQuaTimKiemSanPham.setFont(fntMacDinh);
        tblKetQuaTimKiemSanPham.setBackground(bgrMacDinh);
        tblKetQuaTimKiemSanPham.setDefaultEditor(Object.class, null);
        tblKetQuaTimKiemSanPham.getTableHeader().setPreferredSize(new Dimension(0, 0));

        tblKetQuaTimKiemSanPham.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblKetQuaTimKiemSanPham.getColumnModel().getColumn(0).setMaxWidth(100);

        tblKetQuaTimKiemSanPham.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblKetQuaTimKiemSanPham.getColumnModel().getColumn(2).setMaxWidth(50);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        tblKetQuaTimKiemSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        tblKetQuaTimKiemSanPham.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

        datHanhDongChoTblKetQuaTimKiemSP();
    }

    private void datHanhDongChoTblKetQuaTimKiemSP(){
        tblKetQuaTimKiemSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                themSanPhamVaoTblDanhSachSPKHMua();
            }
        });
    }

    private void themSanPhamVaoTblDanhSachSPKHMua(){
        int row = tblKetQuaTimKiemSanPham.getSelectedRow();

        String maSPDuocChon = (String) tblKetQuaTimKiemSanPham.getValueAt(                          /* Lấy mã của sản phẩm tương ứng */
                row,
                0
        );

        themHoacBoBotSanPhamTrongDSSPKHMua(maSPDuocChon, 1);
    }

    private void themHoacBoBotSanPhamTrongDSSPKHMua(String maSP, int soLuong){
        AtomicBoolean isDaTonTaiSanPhamTrongDSSPKHMua = new AtomicBoolean(false);

        AtomicInteger viTriCuaSPTrongMap = new AtomicInteger(0);

        dsChiTietHoaDonBanHang.forEach(ct -> {
            if (ct.getSanPham().getMaSP().equals(maSP)){
                isDaTonTaiSanPhamTrongDSSPKHMua.set(true);

               if (soLuong < 0 && soLuong <= ct.getSoLuongBan() * -1){
                   int row = tblDanhSachSanPhamKHMua.getSelectedRow();

                   dtmDanhSachSanPhamKHMua.removeRow(row);

                   dsChiTietHoaDonBanHang.removeIf(t -> (t.getSanPham().getMaSP().equals(maSP)));

                   capNhatTinhHinhMuaSamCuaKH();

                   Thread luongDanhLaiSTTChoCacChiTietHoaDonBanHang = new Thread(() -> {
                       capNhatSTTChoTblDanhSachSanPhamKHMua();
                   });

                   luongDanhLaiSTTChoCacChiTietHoaDonBanHang.start();
               }
               else{
                   ct.setSoLuongBan(ct.getSoLuongBan() + soLuong);
                   ct.setThanhTien(ct.getSoLuongBan() * ct.getDonGiaBan());

                   tblDanhSachSanPhamKHMua.setValueAt(
                           ct.getSoLuongBan(), viTriCuaSPTrongMap.get(), 5
                   );

                   tblDanhSachSanPhamKHMua.setValueAt(
                           nf.format(ct.getThanhTien()), viTriCuaSPTrongMap.get(), 8
                   );
               }
            }
            else{
                viTriCuaSPTrongMap.getAndIncrement();
            }
        });

        if (!isDaTonTaiSanPhamTrongDSSPKHMua.get()){
            SanPham spDuocChon = SanPhamDAO.laySanPhamTheoMa(maSP);                         /* Chọc vào CSDL lấy lên đầy đủ thông tin của SP tương ứng */

            ChiTietHoaDonBanHang chiTietHoaDonBanHang = new ChiTietHoaDonBanHang(                   /* Tạo 1 chi tiết hoá đơn bán hàng với sl mua mặc định là 1 */
                    spDuocChon,
                    1,
                    spDuocChon.getDonGia(),
                    spDuocChon.getDonGia()
            );

            dsChiTietHoaDonBanHang.add(chiTietHoaDonBanHang);

            dtmDanhSachSanPhamKHMua.addRow(
                    chuyenChiTietHoaDonBanHangSangObject(chiTietHoaDonBanHang)
            );
        }

        pmnKetQuaTimKiemSanPham.setVisible(false);

        txtTimKiemSanPham.setText("");

        capNhatTinhHinhMuaSamCuaKH();
    }

    private void capNhatTinhHinhMuaSamCuaKH(){
        slsp.set(0);
        tongTien.set(0.0);
        VAT.set(0.0);
        tienKhachPhaiTra.set(0.0);


        dsChiTietHoaDonBanHang.forEach(ct -> {
            slsp.getAndSet(slsp.get() + ct.getSoLuongBan());
            tongTien.getAndSet(tongTien.get() + ct.getThanhTien());
        });

        VAT.set(tongTien.get() * 0.1);
        tienKhachPhaiTra.set(tongTien.get() + VAT.get());

        txtSLSP.setText(slsp.get() + "");
        txtTongTien.setText(nf.format(tongTien.get()));
        txtVAT.setText(nf.format(VAT.get()));
        txtTienKhachPhaiTra.setText(nf.format(tienKhachPhaiTra.get()));
    }

    private void capNhatSTTChoTblDanhSachSanPhamKHMua(){
        int stt = 1;

        for (int i = 0; i < tblDanhSachSanPhamKHMua.getRowCount(); ++i){
            tblDanhSachSanPhamKHMua.setValueAt(
                    stt,
                    i,
                    0
            );

            stt++;
        }
    }

    private Object[] chuyenChiTietHoaDonBanHangSangObject(ChiTietHoaDonBanHang chiTietHoaDonBanHang){
        NutDuocNhungTrongJtable btnXoaChiTietHDBH = new NutDuocNhungTrongJtable(
                tblDanhSachSanPhamKHMua,
                xoaMotChiTietHoaDonBanHang(),
                1);

        NutDuocNhungTrongJtable btnTangSLSPThem1 = new NutDuocNhungTrongJtable(
                tblDanhSachSanPhamKHMua,
                tangHoacGiamSoLuongSPChoMotChiTietHoaDonBanHang(true),
                4
        );

        NutDuocNhungTrongJtable btnGiamSLSPThem1 = new NutDuocNhungTrongJtable(
                tblDanhSachSanPhamKHMua,
                tangHoacGiamSoLuongSPChoMotChiTietHoaDonBanHang(false),
                6
        );

        Object[] o = {
                tblDanhSachSanPhamKHMua.getRowCount() + 1,
                new ImageIcon(
                        "src/main/resources/BieuTuong/Bin_24px_1.png"
                ),
                chiTietHoaDonBanHang.getSanPham().getMaSP(),
                chiTietHoaDonBanHang.getSanPham().getTenSP(),
                new ImageIcon(
                        "src/main/resources/BieuTuong/ChevronToTop_24px_1.png"
                ),
                1,
                new ImageIcon(
                        "src/main/resources/BieuTuong/ChevronToBot_24px_1.png"
                ),
                nf.format(chiTietHoaDonBanHang.getSanPham().getDonGia()),
                nf.format(chiTietHoaDonBanHang.getSanPham().getDonGia())
        };

        return o;
    }

    private Action xoaMotChiTietHoaDonBanHang(){
        Action a = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );

                String maSP = (String) table.getValueAt(modelRow, 2);

                ((DefaultTableModel)table.getModel()).removeRow(modelRow);

                dsChiTietHoaDonBanHang.removeIf(t -> (t.getSanPham().getMaSP().equals(maSP)));

                capNhatTinhHinhMuaSamCuaKH();

                Thread luongDanhLaiSTTChoCacChiTietHoaDonBanHang = new Thread(() -> {
                    capNhatSTTChoTblDanhSachSanPhamKHMua();
                });

                luongDanhLaiSTTChoCacChiTietHoaDonBanHang.start();
            }
        };

        return a;
    }

    private Action tangHoacGiamSoLuongSPChoMotChiTietHoaDonBanHang(boolean isTangSLSP){
        Action a = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );

                String maSP = (String) table.getValueAt(modelRow, 2);

                AtomicInteger coHieu = new AtomicInteger(0);

                dsChiTietHoaDonBanHang.forEach(ct -> {
                    if (ct.getSanPham().getMaSP().equals(maSP)){

                        if (ct.getSoLuongBan() == 1 && !isTangSLSP){
                            dsChiTietHoaDonBanHang.remove(ct);

                            ((DefaultTableModel)table.getModel()).removeRow(modelRow);

                            capNhatSTTChoTblDanhSachSanPhamKHMua();
                        }
                        else{

                            if (isTangSLSP){
                                String maSPCanThem = ct.getSanPham().getMaSP();

                                int slHienTai = ct.getSoLuongBan();

                                boolean khaNangThem = kiemTraSoLuongThemDaVuotSoLuongTonCuaSPChua(
                                        maSPCanThem,
                                        slHienTai, 1
                                );

                                if (khaNangThem){
                                    ct.setSoLuongBan(ct.getSoLuongBan() + 1);
                                }
                                else{
                                    CacHamDungSan.hienThiThongBaoKetQua(
                                            GDThongBaoKetQua.THONG_BAO_LOI,
                                            "Số lượng còn lại của sản phẩm này " +
                                                    "không đủ để đáp ứng."
                                    );
                                }
                            }
                            else{
                                ct.setSoLuongBan(ct.getSoLuongBan() - 1);
                            }

                            ct.setThanhTien(ct.getSoLuongBan() * ct.getDonGiaBan());

                            tblDanhSachSanPhamKHMua.setValueAt(
                                    ct.getSoLuongBan(),
                                    modelRow,
                                    5
                            );

                            tblDanhSachSanPhamKHMua.setValueAt(
                                    ct.getThanhTien(),
                                    modelRow,
                                    8
                            );
                        }

                        capNhatTinhHinhMuaSamCuaKH();
                    }
                    else{
                        coHieu.getAndIncrement();
                    }
                });
            }
        };

        return a;
    }

    private void datKichThuocChoPmnKetQuaTimKiemSP(int sl){
        pmnKetQuaTimKiemSanPham.setPopupSize(new Dimension(
                chieuRongPmnKetQuaTimKiemSanPham,
                sl * chieuCaoHangDuLieuTrongTable
        ));
    }

    private void datHanhDongChoBtnXoaTatCaSanPhamDaThem(){
        btnXoaTatCaSanPhamDaThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tblDanhSachSanPhamKHMua.getRowCount() != 0){
                    xoaTatCaSanPhamDaThem();
                }
            }
        });
    }

    private void xoaTatCaSanPhamDaThem(){
        if (tblDanhSachSanPhamKHMua.getRowCount() > 0){
            int luaChon = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn chắc chắn muốn xoá hết các sản phẩm đã thêm chứ?",
                    "Cảnh báo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (luaChon == JOptionPane.YES_OPTION){
                dtmDanhSachSanPhamKHMua.setRowCount(0);

                dsChiTietHoaDonBanHang.clear();

                capNhatTinhHinhMuaSamCuaKH();

                txtTienKhachDua.setText("");
                txtTienThua.setText("");
            }
        }
    }

    private void dungPnlHopCongCu(){
        pnlHopCongCu.setPreferredSize(dimPnlHopCongCu);
        pnlHopCongCu.setBackground(mauNenPnlThanhCongCu);
        pnlHopCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));

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
                btnHuyHoaDonBanHang,
                mauNenBtnHuyHoaDonBanHang,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnThuNhoVaHuyHD
        );
        datHanhDongChoBtnHuyHoaDonBanHang();
        btnHuyHoaDonBanHang.setToolTipText("Huỷ hoá đơn bán hàng");
        pnlHopCongCu.add(btnHuyHoaDonBanHang);
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

    private void datHanhDongChoBtnHuyHoaDonBanHang(){
        btnHuyHoaDonBanHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hienThiGDCanhBaoHuyHDBH();
            }
        });
    }

    private void hienThiGDCanhBaoHuyHDBH(){
        SwingUtilities.invokeLater(() -> {
            GDCanhBaoHuyHoaDonBanHang gdCanhBaoHuyHoaDonBanHang = GDCanhBaoHuyHoaDonBanHang.getGdCanhBaoHuyHoaDonBanHang();
            gdCanhBaoHuyHoaDonBanHang.setVisible(true);
        });
    }

    private void dungPnlHoaDonBanHangChinh(){
        pnlHoaDonBanHangChinh.setBackground(bgrMacDinh);
        pnlHoaDonBanHangChinh.setPreferredSize(dimPnlTaoHoaDonBanHangChinh);
        pnlHoaDonBanHangChinh.setLayout(new BorderLayout());

        dungPnlDanhSachSanPhamKHMua();
        pnlHoaDonBanHangChinh.add(pnlDanhSachSanPhamKHMua, BorderLayout.CENTER);

        dungPnlMucTongKetHoaDonVaThanhToan();
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
        datHanhDongChoTblDSSPKHMua();

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
        tblDanhSachSanPhamKHMua.setDefaultEditor(Object.class, null);
        tblDanhSachSanPhamKHMua.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblDanhSachSanPhamKHMua.setFont(fntMacDinh);
        tblDanhSachSanPhamKHMua.getTableHeader().setFont(fntMacDinh);
        tblDanhSachSanPhamKHMua.setShowGrid(false);
        tblDanhSachSanPhamKHMua.setGridColor(Color.WHITE);
        tblDanhSachSanPhamKHMua.getTableHeader().setPreferredSize(new Dimension(0, 0));
        tblDanhSachSanPhamKHMua.setSelectionBackground(bgrTableRowKhiDuocChon);

        canLeChoCacGiaTriTrongTblDanhSachSPKHMua();

        datChieuRongChoCacGiaTriTrongTblDanhSachSPKHMua();
    }

    private void canLeChoCacGiaTriTrongTblDanhSachSPKHMua(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < tblDanhSachSanPhamKHMua.getColumnCount(); ++i){
            if (i == 0 || i == 1|| i == 4 || i == 5 || i == 6){
                tblDanhSachSanPhamKHMua.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else if (i == 7 || i == 8){
                tblDanhSachSanPhamKHMua.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            }
        }
    }

    private void datChieuRongChoCacGiaTriTrongTblDanhSachSPKHMua(){
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(0).setMaxWidth(50);

        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(1).setMaxWidth(60);

        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(2).setMaxWidth(120);

        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(4).setPreferredWidth(30);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(4).setMaxWidth(30);

        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(5).setMaxWidth(50);

        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(6).setPreferredWidth(30);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(6).setMaxWidth(30);

        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(7).setPreferredWidth(120);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(7).setMaxWidth(120);

        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(8).setPreferredWidth(120);
        tblDanhSachSanPhamKHMua.getColumnModel().getColumn(8).setMaxWidth(120);
    }

    private void datHanhDongChoTblDSSPKHMua(){
        tblDanhSachSanPhamKHMua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int soLanClick = e.getClickCount();
                    int nutChuotDuocNhan = e.getButton();

                    if (soLanClick == 2 && nutChuotDuocNhan == 1){
                        int row = tblDanhSachSanPhamKHMua.getSelectedRow();

                        if (row != -1) {
                            JTextField txtSoLuong = new JTextField();
                            CacHamDungSan.datThuocTinhChoTxt(
                                    txtSoLuong,
                                    "",
                                    dimTxtTimKiemKH
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
                                String maSP = (String) tblDanhSachSanPhamKHMua.getValueAt(
                                        row, 2
                                );

                                int slHienTai = (int) tblDanhSachSanPhamKHMua.getValueAt(
                                        row, 5
                                );

                                int slCanThem = Integer.parseInt(txtSoLuong.getText().trim());

                                boolean khaNangThem = kiemTraSoLuongThemDaVuotSoLuongTonCuaSPChua(
                                        maSP,
                                        slHienTai,
                                        slCanThem
                                );

                                if (khaNangThem){
                                    themHoacBoBotSanPhamTrongDSSPKHMua(maSP, slCanThem);
                                }
                                else{
                                   CacHamDungSan.hienThiThongBaoKetQua(
                                           GDThongBaoKetQua.THONG_BAO_LOI,
                                           "Số lượng còn lại của sản phẩm này không đủ" +
                                                   " để đáp ứng."
                                   );
                                }
                            }
                        }
                    }

                    else if (soLanClick == 2 && nutChuotDuocNhan == 3){
                        int row = tblDanhSachSanPhamKHMua.getSelectedRow();

                        if (row != -1) {
                            JTextField txtSoLuong = new JTextField();
                            CacHamDungSan.datThuocTinhChoTxt(
                                    txtSoLuong,
                                    "",
                                    dimTxtTimKiemKH
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
                                String maSP = (String) tblDanhSachSanPhamKHMua.getValueAt(
                                        row, 2
                                );

                                themHoacBoBotSanPhamTrongDSSPKHMua(maSP, Integer.parseInt(txtSoLuong.getText().trim()) * -1);
                            }
                        }
                    }
                });
            }
        });
    }

    private void dungPnlMucTongKetHoaDonVaThanhToan(){
        pnlMucTongKetHoaDonVaThanhToan.setBackground(bgrMacDinh);
        pnlMucTongKetHoaDonVaThanhToan.setPreferredSize(dimPnlMucTongKetHoaDonVaThanhToan);
        pnlMucTongKetHoaDonVaThanhToan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThongTinHoaDonBanHang();
        pnlMucTongKetHoaDonVaThanhToan.add(pnlThongTinHoaDonBanHang);

        dungPnlThanhToan();
        pnlMucTongKetHoaDonVaThanhToan.add(pnlThanhToan);
    }

    private void dungPnlThongTinHoaDonBanHang(){
        pnlThongTinHoaDonBanHang.setBackground(bgrMacDinh);
        pnlThongTinHoaDonBanHang.setPreferredSize(dimPnlThongTinHoaDonBanHang);
        pnlThongTinHoaDonBanHang.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        dungPnlKhachHangMua();
        pnlThongTinHoaDonBanHang.add(pnlKhachHangMua);

        dungCacPnlChuaThongTinCuaHDBH(pnlSLSP, lblTieuDePnlSLSP, txtSLSP, true);
        pnlThongTinHoaDonBanHang.add(pnlSLSP);

        dungCacPnlChuaThongTinCuaHDBH(pnlTongTien, lblTieuDePnlTongTien, txtTongTien, true);
        pnlThongTinHoaDonBanHang.add(pnlTongTien);

        dungCacPnlChuaThongTinCuaHDBH(pnlVAT, lblTieuDePnlVAT, txtVAT, true);
        pnlThongTinHoaDonBanHang.add(pnlVAT);

        dungCacPnlChuaThongTinCuaHDBH(pnlTienKhachPhaiTra, lblTieuDePnlTienKhachPhaiTra, txtTienKhachPhaiTra, true);
        lblTieuDePnlTienKhachPhaiTra.setFont(
                new Font(tenFontMacDinh, Font.BOLD, 18)
        );
        pnlThongTinHoaDonBanHang.add(pnlTienKhachPhaiTra);

        //
        dungCacPnlChuaThongTinCuaHDBH(pnlTienKhachDua, lblTieuDePnlTienKhachDua, txtTienKhachDua, false);
        datHanhDongChoTXtTienKhachDua();
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtTienKhachDua);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTienKhachDua);
        datHanhDongChoTxtTienKhachDua();
        pnlThongTinHoaDonBanHang.add(pnlTienKhachDua);

        dungCacPnlChuaThongTinCuaHDBH(pnlTienThua, lblTieuDePnlTienThua, txtTienThua, true);
        pnlThongTinHoaDonBanHang.add(pnlTienThua);
    }

    private void datHanhDongChoTXtTienKhachDua(){
        txtTienKhachDua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (txtTienKhachDua.getText().trim().isEmpty()){
                    txtTienThua.setText("");
                }
                else{
                    String s = txtTienKhachDua.getText().trim()
                            .replace(".", "")
                            .replace(",", "");

                    txtTienKhachDua.setText(
                            df.format(Double.parseDouble(s))
                    );
                }
            }
        });
    }

    private void dungCacPnlChuaThongTinCuaHDBH(JPanel pnl, JLabel lbl, JTextField txt, boolean isAnVien){
        pnl.setLayout(new BorderLayout());
        pnl.setPreferredSize(dimPnlKhachHangMua);
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

        if (isAnVien){
            txt.setBorder(null);
            txt.setEnabled(false);
        }
        txt.setBackground(bgrMacDinh);

        txt.setHorizontalAlignment(SwingConstants.RIGHT);
        pnl.add(txt, BorderLayout.EAST);
    }

    private void datHanhDongChoTxtTienKhachDua(){
        txtTienKhachDua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    double tienKhachDua = TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(txtTienKhachDua);

                    if (tienKhachDua >= tienKhachPhaiTra.get()){
                        txtTienThua.setText(
                                nf.format(
                                        tienKhachDua
                                                - tienKhachPhaiTra.get()
                                )
                        );

                        requestFocusInWindow();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Số tiền khách đưa không đủ để thanh toán."
                        );

                        txtTienKhachDua.setText("");
                        txtTienThua.setText("0");
                    }
                }
            }
        });
    }

    private void dungPnlKhachHangMua(){
        pnlKhachHangMua.setBackground(bgrMacDinh);
        pnlKhachHangMua.setPreferredSize(dimPnlKhachHangMua);
        pnlKhachHangMua.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
        pnlKhachHangMua.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, doDayVienMacDinh, 0, bgrVienMacDinh
                )
        );

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiemKH,
                plhTxtTimKiemKH,
                dimTxtTimKiemKH
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiemKH);
        datHanhDongChoTxtTimKiemKhachHang();
        txtTimKiemKH.setBorder(null);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtTimKiemKH);
        pnlKhachHangMua.add(txtTimKiemKH);

        dungPmnKetQuaTimKiemKhachHang();

        CacHamDungSan.datThuocTinhChoBtn(
                btnThemKHMoi,
                bgrMacDinh,
                IDSBienMacDinh.frgMacDinh,
                dimBtnThemKHMoi
        );
        btnThemKHMoi.setToolTipText("Thêm khách hàng mới (F5)");
        datHanhDongChoBtnThemKhachHangMoi();
        pnlKhachHangMua.add(btnThemKHMoi);
    }

    private void datHanhDongChoTxtTimKiemKhachHang(){
        txtTimKiemKH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtTimKiemKH.getText().toLowerCase();

                if (tuKhoa.isEmpty()){
                    pmnKetQuaTimKiemKH.setVisible(false);
                    txtTimKiemKH.setToolTipText("");
                }
                else{
                    ArrayList<KhachHangTimDuoc> dsTimDuoc = timKiemKhachHang(tuKhoa);

                    if (!dsTimDuoc.isEmpty()){
                        duaDuLieuVaoTblKetQuaTimKiemKH(dsTimDuoc);

                        pmnKetQuaTimKiemKH.setVisible(true);

                        tblKetQuaTimKiemKH.setEnabled(true);

                        pmnKetQuaTimKiemKH.show(
                                txtTimKiemKH,
                                0,
                                txtTimKiemKH.getHeight()
                        );

                        txtTimKiemKH.requestFocus();
                    }
                    else{
                        tblKetQuaTimKiemKH.setEnabled(false);

                        dtmKetQuaTimKiemKH.setRowCount(0);

                        Object[] thongBaoKhongTimRaKHNao = {
                                "",
                                "( ^ _ ^ )"
                        };

                        dtmKetQuaTimKiemKH.addRow(thongBaoKhongTimRaKHNao);

                        datKichThuocChoPmnKetQuaTimKiemKhachHang(1);
                    }
                }
            }
        });

        txtTimKiemKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtTimKiemKH.setFocusable(true);

                if (e.getButton() == 3 && e.getClickCount() == 2){
                    txtTimKiemKH.setText("");

                    btnThemKHMoi.setEnabled(true);
                }
            }
        });
    }

    /**
     * <p>Kiểm tra khi tăng số lượng sản phẩm</p>
     * <p>Nếu sản phẩm vẫn còn đủ số lượng để đáp ứng thì cho phép thêm.</p>
     * @param maSP: Mã sản phẩm cần thêm
     * @param slHienTai: Số lượng hiện tại trên hoá đơn của sản phẩm đó
     * @param slThem: Số lượng cần thêm
     * @return: <li>True: thêm được</li>
     *          <li>False: không đủ số lượng tồn để đáp ứng</li>
     */
    private boolean kiemTraSoLuongThemDaVuotSoLuongTonCuaSPChua(String maSP, int slHienTai, int slThem){
        SanPhamDAO sanPhamDAO = new SanPhamDAO();

        int slTonCuaSP = sanPhamDAO.laySoLuongTonCuaSanPham(maSP);

        if (slTonCuaSP >= slHienTai + slThem)
            return true;
        return false;
    }

    private void dungPmnKetQuaTimKiemKhachHang(){
        pmnKetQuaTimKiemKH.setBackground(bgrMacDinh);
        pmnKetQuaTimKiemKH.setBorder(BorderFactory.createEmptyBorder());

        dungTblKetQuaTimKiemKhachHang();
        datHanhDongChoTblKetQuaTimKiemKhachHang();
        pmnKetQuaTimKiemKH.add(tblKetQuaTimKiemKH, BorderLayout.CENTER);
    }

    private void dungTblKetQuaTimKiemKhachHang(){
        tblKetQuaTimKiemKH.setDefaultEditor(Object.class, null);
        tblKetQuaTimKiemKH.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblKetQuaTimKiemKH.setFont(fntMacDinh);
        tblKetQuaTimKiemKH.getTableHeader().setFont(fntMacDinh);
        tblKetQuaTimKiemKH.setShowGrid(false);
        tblKetQuaTimKiemKH.setGridColor(Color.WHITE);
        tblKetQuaTimKiemKH.getTableHeader().setPreferredSize(new Dimension(0, 0));

        tblKetQuaTimKiemKH.getColumnModel().getColumn(0).setPreferredWidth(115);
        tblKetQuaTimKiemKH.getColumnModel().getColumn(0).setMaxWidth(115);
    }

    private void datHanhDongChoTblKetQuaTimKiemKhachHang(){
        tblKetQuaTimKiemKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblKetQuaTimKiemKH.getSelectedRow();

                String thongTinSeHienThi =
                        tblKetQuaTimKiemKH.getValueAt(row, 1)
                        + " - "
                        + tblKetQuaTimKiemKH.getValueAt(row, 0);

                hienThiThongTinKhachHangLenTxt(thongTinSeHienThi);

                pmnKetQuaTimKiemKH.setVisible(false);
            }
        });
    }

    private void duaDuLieuVaoTblKetQuaTimKiemKH(ArrayList<KhachHangTimDuoc> dsKH){
        dtmKetQuaTimKiemKH.setRowCount(0);

        dsKH.forEach(kh -> {
            Object[] o = {
                kh.getSdt(),
                kh.getHoTen()
            };

            dtmKetQuaTimKiemKH.addRow(o);
        });

        datKichThuocChoPmnKetQuaTimKiemKhachHang(dsKH.size());
    }

    private void datKichThuocChoPmnKetQuaTimKiemKhachHang(int slKH){
        pmnKetQuaTimKiemKH.setPopupSize(
                new Dimension(
                        dimPnlKhachHangMua.width,
                        slKH * chieuCaoHangDuLieuTrongTable
                )
        );
    }

    private ArrayList<KhachHangTimDuoc> timKiemKhachHang(String tuKhoa){
        ArrayList<KhachHangTimDuoc> ketQuaTimKiem = new ArrayList<>();

        AtomicInteger coHieu = new AtomicInteger(0);

        dsKhachHang.forEach(kh -> {
            if (coHieu.get() < 5){
                if (kh.getSoDT().contains(tuKhoa)){
                    KhachHangTimDuoc khKhop = new KhachHangTimDuoc(
                            kh.getSoDT(),
                            kh.getHoTen()
                    );

                    ketQuaTimKiem.add(khKhop);
                    coHieu.getAndIncrement();
                }
            }
        });

        return ketQuaTimKiem;
    }

    private void datHanhDongChoBtnThemKhachHangMoi(){
        btnThemKHMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (btnThemKHMoi.isEnabled()){
                    hienThiGDThemKhachHang();
                }
            }
        });
    }

    private void hienThiGDThemKhachHang(){
        if (btnThemKHMoi.isEnabled()){
            SwingUtilities.invokeLater(() -> {
                new GDThemKhachHang().setVisible(true);
            });
        }
    }

    public static void hienThiThongTinKhachHangLenTxt(String thongTinSeHienThi){
        txtTimKiemKH.setText(thongTinSeHienThi);
        txtTimKiemKH.setToolTipText(thongTinSeHienThi);
        txtTimKiemKH.setFocusable(false);
        txtTimKiemKH.setForeground(frgMacDinh);

        btnThemKHMoi.setEnabled(false);
    }

    private void dungPnlThanhToan(){
        pnlThanhToan.setBackground(bgrMacDinh);
        pnlThanhToan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThanhToan.setPreferredSize(dimPnlThanhToan);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThanhToan,
                mauNenBtnThanhToan,
                Color.WHITE,
                dimBtnThanhToan
        );
        btnThanhToan.setFont(fntBtnThanhToan);
        datHanhDongChoBtnThanhToan();
        pnlThanhToan.add(btnThanhToan);
    }

    private void datHanhDongChoBtnThanhToan(){
        btnThanhToan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            thucHienThuTucThanhToan();
            }
        });
    }

    private void thucHienThuTucThanhToan(){
        if (dtmDanhSachSanPhamKHMua.getRowCount() > 0){
            String thongTinKhachHang = txtTimKiemKH.getText().trim();
            if (!thongTinKhachHang.isEmpty() && !thongTinKhachHang.equals(plhTxtTimKiemKH)){

                if (!txtTienKhachDua.getText().trim().isEmpty()){
                    double soTienKhachPhaiTra = tinhSoTienKhachPhaiTra(dsChiTietHoaDonBanHang);
                    double soTienKhachDua = TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(txtTienKhachDua);

                    if (soTienKhachDua >= soTienKhachPhaiTra){

                        int luaChon = JOptionPane.showConfirmDialog(
                                null,
                                "Bạn chắc chắn muốn thanh toán cho hoá đơn này chứ?",
                                "Cảnh báo",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (luaChon == JOptionPane.YES_OPTION){
                            String tmp = txtTimKiemKH.getText().trim();
                            String sdtKH = tmp.substring(tmp.length() - 10);

                            KhachHang khachHang = xacDinhKhachHangQuaSoDienThoai(sdtKH);

                            HoaDonBanHang hoaDonBanHang = new HoaDonBanHang(
                                    GDChinh.getNhanVienDangSuDung(),
                                    khachHang,
                                    dsChiTietHoaDonBanHang,
                                    soTienKhachDua
                            );

                            boolean kqThemHoaDonBanHang = HoaDonBanHangDAO.themHoaDonBanHang(hoaDonBanHang);

                            if (kqThemHoaDonBanHang){
                                Thread luongHienThiThongBaoThanhCong = new Thread(() -> {
                                    CacHamDungSan.hienThiThongBaoKetQua(
                                            GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                                            "Thanh toán thành công. Đợi máy in hoá đơn một chút nhé."
                                    );

                                    datCacThanhPhanCuaGDTaoHoaDonBanHangVeTinhTrangBanDau();
                                });

                                luongHienThiThongBaoThanhCong.start();
                            }
                            else{
                                CacHamDungSan.hienThiThongBaoKetQua(
                                        GDThongBaoKetQua.THONG_BAO_LOI,
                                        "Đã có lỗi xảy ra. Hãy kiểm tra lại thông tin khách hàng " +
                                                "và các trường liên quan khác."
                                );

                                txtTienKhachDua.requestFocus();
                            }
                        }
                    }
                    else{
                       CacHamDungSan.hienThiThongBaoKetQua(
                               GDThongBaoKetQua.THONG_BAO_LOI,
                               "Số tiền khách đưa không đủ để thanh toán đơn hàng này."
                       );

                        txtTienKhachDua.requestFocus();
                    }
                }
                else{
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_LOI,
                            "Chưa nhập số tiền khách đưa."
                    );
                }

            }
            else{
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Chưa xác định được khách hàng cần thanh toán."
                );

                txtTimKiemKH.requestFocus();
            }
        }
        else{
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Khách hàng này không mua gì cả nên không chấp nhận thanh toán được."
            );
        }
    }

    private KhachHang xacDinhKhachHangQuaSoDienThoai(String sdtKH){
        AtomicReference<KhachHang> khachHang = new AtomicReference<>();

        dsKhachHang.forEach(kh -> {
            if (kh.getSoDT().equals(sdtKH))
                khachHang.set(kh);
        });

        return khachHang.get();
    }

    private double tinhSoTienKhachPhaiTra(List<ChiTietHoaDonBanHang> dsChiTietHoaDonBanHang){
        return dsChiTietHoaDonBanHang.stream().mapToDouble(
                ChiTietHoaDonBanHang::getThanhTien
        ).sum();
    }

    private static void datCacThanhPhanCuaGDTaoHoaDonBanHangVeTinhTrangBanDau(){
        gdTaoHoaDonBanHang.requestFocusInWindow();

        CacHamDungSan.duaTxtVeTrangThaiBanDau(
                txtTimKiemSanPham,
                "Tìm sản phẩm theo mã hoặc tên"
        );

        CacHamDungSan.duaTxtVeTrangThaiBanDau(
                txtTimKiemKH,
                plhTxtTimKiemKH
        );

        dtmDanhSachSanPhamKHMua.setRowCount(0);

        btnThemKHMoi.setEnabled(true);

        txtSLSP.setText("0");
        txtTongTien.setText("0");
        txtVAT.setText("0");
        txtTienKhachPhaiTra.setText("0");
        txtTienKhachDua.setText("");
        txtTienThua.setText("0");

        dsChiTietHoaDonBanHang.clear();
    }
}
