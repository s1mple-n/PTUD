package ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheokhachhang;

import connectDB.KetNoiCSDL;
import dao.KhachHangDAO;
import entity.KhachHang;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlkhachhang.gdxemthongtinkhachhang.GDXemThongTinKhachHang;
import ui.giaodienchinh.pnlqlkhachhang.kieudulieudacbiet.KhachHangTimDuoc;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PnlTKDoanhThuTheoKhachHang implements IDSBienPnlTKDoanhThuTheoKhachHang, ActionListener{

    public void dungPnlTKDoanhThuTheoKhachHang(JPanel pnlTKDTTheoKhachHang){
        pnlTKDTTheoKhachHang.setBackground(bgrPnlChinh);
        pnlTKDTTheoKhachHang.setPreferredSize(dimTabNoiDung);
        pnlTKDTTheoKhachHang.setLayout(new FlowLayout(
                FlowLayout.CENTER, 0, 0
        ));

        dungPnlThanhTienIch();
        pnlTKDTTheoKhachHang.add(pnlThanhTienIchTKDoanhThuTheoKhachHang);

        pnlTKDTTheoKhachHang.add(Box.createHorizontalStrut(5));

        dungPnlNoiDungTKDoanhThuTheoKhachHang();
        pnlTKDTTheoKhachHang.add(pnlNoiDungTKDoanhThuTheoKhachHang);

        capNhatDSKhachHang();
        datHanhDongChoRadioButtons();
    }

    public static void capNhatDSKhachHang(){
        dsKhachHang.clear();

        Thread luongCapNhatDSKH = new Thread(() -> {
            Map<Integer, KhachHang> dsKH = KhachHangDAO.layToanBoDuLieuKhachHang();

            dsKhachHang.putAll(dsKH);
        });

        luongCapNhatDSKH.start();
    }

    private void dungPnlThanhTienIch(){
        pnlThanhTienIchTKDoanhThuTheoKhachHang.setBackground(bgrMacDinh);
        pnlThanhTienIchTKDoanhThuTheoKhachHang.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        0,
                        0
                )
        );
        pnlThanhTienIchTKDoanhThuTheoKhachHang.setPreferredSize(dimPnlThanhTienIchTKDoanhThuTheoKhachHang);

        dungPnlKhachHangCanThongKe();
        pnlThanhTienIchTKDoanhThuTheoKhachHang.add(pnlKhachHangCanThongKe);

        dungPnlLocTheoThoiGian();
        pnlThanhTienIchTKDoanhThuTheoKhachHang.add(pnlLocTheoThoiGian);
    }

    private void dungPnlKhachHangCanThongKe(){
        pnlKhachHangCanThongKe.setBackground(bgrMacDinh);
        pnlKhachHangCanThongKe.setPreferredSize(dimPnlKhachHangCanThongKe);
        pnlKhachHangCanThongKe.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoKhachHang, lblTieuDeLocTheoKhachHang);
        pnlKhachHangCanThongKe.add(pnlTieuDeLocTheoKhachHang);

        dungPnlLuaChonLocTheoKhachHang();
        pnlKhachHangCanThongKe.add(pnlLuaChonLocTheoKhachHang);

        dungPnlLocTheoDoanhSo();

        dungPnlLocTheoTongTienHDBH();

        dungPmnKetQuaTimKiemKhachHang();
    }

    private void dungPnlLuaChonLocTheoKhachHang(){
        pnlLuaChonLocTheoKhachHang.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoKhachHang.setPreferredSize(dimPnlLuaChonLocTheoKhachHang);
        pnlLuaChonLocTheoKhachHang.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                3
        ));

        bngLocTheoKhachHang.add(radLocTheoMoiKhachHang);
        bngLocTheoKhachHang.add(radLocTheoTungKhachHang);

        CacHamDungSan.datThuocTinhChoCacRad(radLocTheoMoiKhachHang);
        datHanhDongChoRadMoiKhachHang();
        pnlLuaChonLocTheoKhachHang.add(radLocTheoMoiKhachHang);

        dungPnlLocTheoTungKhachHang();
        pnlLuaChonLocTheoKhachHang.add(pnlLocTheoTungKhachHang);
    }

    private void dungPnlLocTheoTungKhachHang(){
        pnlLocTheoTungKhachHang.setBackground(bgrMacDinh);
        pnlLocTheoTungKhachHang.setLayout(
                new BoxLayout(pnlLocTheoTungKhachHang, BoxLayout.X_AXIS)
        );

        CacHamDungSan.datThuocTinhChoCacRad(
                radLocTheoTungKhachHang
        );
        datHanhDongChoRadLocTheoTungKhachHang();
        pnlLocTheoTungKhachHang.add(radLocTheoTungKhachHang);

        pnlLocTheoTungKhachHang.add(Box.createHorizontalStrut(4));

        txtLocTheoTungKhachHang.setEnabled(false);
        CacHamDungSan.datThuocTinhChoTxt(
                txtLocTheoTungKhachHang,
                "Tìm kiếm KH",
                dimTxtLocTheoTungKhachHang
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtLocTheoTungKhachHang);
        txtLocTheoTungKhachHang.setToolTipText("Tìm khách hàng theo mã");
        datHanhDongChoTxtLocTheoTungKhachHang();
        pnlLocTheoTungKhachHang.add(txtLocTheoTungKhachHang);
    }

    private void datHanhDongChoTxtLocTheoTungKhachHang(){
        txtLocTheoTungKhachHang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtLocTheoTungKhachHang.getText().trim().toLowerCase();

                if (tuKhoa.isEmpty()){
                    pmnChuaDSKHTimDuoc.setVisible(false);
                }
                else{
                    ArrayList<KhachHangTimDuoc> dsTimDuoc = timKiemKHTheoTuKhoa(tuKhoa);

                    if (!dsTimDuoc.isEmpty()){
                        duaDuLieuVaoTable(dsTimDuoc);

                        pmnChuaDSKHTimDuoc.setVisible(true);

                        tblDSKHTimDuoc.setEnabled(true);

                        pmnChuaDSKHTimDuoc.show(
                                txtLocTheoTungKhachHang,
                                0,
                                txtLocTheoTungKhachHang.getHeight()
                        );

                        txtLocTheoTungKhachHang.requestFocus();
                    }
                    else{
                        tblDSKHTimDuoc.setEnabled(false);

                        dtmDSKHTimDuoc.setRowCount(0);

                        Object[] thongBaoKhongTimRaKHNao = {
                                "",
                                "( ^ _ ^ )",
                                "",
                                ""
                        };

                        dtmDSKHTimDuoc.addRow(thongBaoKhongTimRaKHNao);

                        datKichThuocChoPmnChuaDSKHTimDuoc(2);
                    }
                }
            }
        });

        txtLocTheoTungKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungKhachHang.setFocusable(true);
                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtLocTheoTungKhachHang, "");
            }
        });
    }

    private void dungPmnKetQuaTimKiemKhachHang(){
        pmnChuaDSKHTimDuoc.setBackground(bgrMacDinh);
        pmnChuaDSKHTimDuoc.setBorder(BorderFactory.createEmptyBorder());
        pmnChuaDSKHTimDuoc.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 1
                )
        );

        dungTblChuaDSKHTimDuoc();
        datHanhDongChoTblDSKHTimDuoc();
        pmnChuaDSKHTimDuoc.add(tblDSKHTimDuoc.getTableHeader());
        pmnChuaDSKHTimDuoc.add(tblDSKHTimDuoc, BorderLayout.CENTER);
    }

    private void dungTblChuaDSKHTimDuoc(){
        tblDSKHTimDuoc.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblDSKHTimDuoc.setFont(fntMacDinh);
        tblDSKHTimDuoc.setDefaultEditor(Object.class, null);

        tblDSKHTimDuoc.getTableHeader().setFont(fntMacDinh);
        tblDSKHTimDuoc.getTableHeader().setPreferredSize(
                new Dimension(
                        chieuRongPmnChuaDSKHTimDuoc,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblDSKHTimDuoc.getTableHeader().setBackground(bgrTieuDeTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel tcm = tblDSKHTimDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(0).setMaxWidth(100);
        tcm.getColumn(0).setCellRenderer(
                centerRenderer
        );

        tcm.getColumn(1).setPreferredWidth(120);
        tcm.getColumn(1).setMaxWidth(120);
    }

    private void datHanhDongChoTblDSKHTimDuoc(){
        tblDSKHTimDuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int hangDuocChon = tblDSKHTimDuoc.getSelectedRow();

                if (hangDuocChon != -1){
                    int maKH = (int) tblDSKHTimDuoc.getValueAt(
                            hangDuocChon, 0
                    );

                    KhachHang kh = dsKhachHang.get(maKH);

                    int soLanClick = e.getClickCount();
                    int nutChuotDuocNhan = e.getButton();

                    if (soLanClick == 1 && nutChuotDuocNhan == 1){
                        txtLocTheoTungKhachHang.setText(
                                kh.getSoDT() + ""
                        );

                        locLaiDuLieuSauKhiThemHoacCapNhat();

                        txtLocTheoTungKhachHang.setFocusable(false);

                        pmnChuaDSKHTimDuoc.setVisible(false);
                    }
                }
            }
        });
    }

    private void datKichThuocChoPmnChuaDSKHTimDuoc(int slKetQua){
        pmnChuaDSKHTimDuoc.setPopupSize(
                new Dimension(
                        chieuRongPmnChuaDSKHTimDuoc,
                        chieuCaoHangDuLieuTrongTable * slKetQua
                )
        );
    }

    private void duaDuLieuVaoTable(ArrayList<KhachHangTimDuoc> dsKH){
        dtmDSKHTimDuoc.setRowCount(0);

        dsKH.forEach(kh -> {
            Object[] o = {
                    kh.getMaKH(),
                    kh.getSdt(),
                    kh.getTenKH()
            };

            dtmDSKHTimDuoc.addRow(o);
        });

        datKichThuocChoPmnChuaDSKHTimDuoc(dsKH.size() + 1);
    }

    private ArrayList<KhachHangTimDuoc> timKiemKHTheoTuKhoa(String tuKhoa){
        ArrayList<KhachHangTimDuoc> ketQuaTimKiemKH = new ArrayList<>();

        AtomicInteger coHieu = new AtomicInteger(0);

        dsKhachHang.forEach((K, V) -> {
            if (coHieu.get() < 7){
                if (V.getSoDT().contains(tuKhoa)){
                    KhachHangTimDuoc khachHangTimDuoc = new KhachHangTimDuoc(
                            V.getMaKH(),
                            V.getSoDT(),
                            V.getHoTen()
                    );

                    ketQuaTimKiemKH.add(khachHangTimDuoc);

                    coHieu.getAndIncrement();
                }
            }
        });

        return ketQuaTimKiemKH;
    }

    private void datHanhDongChoRadMoiKhachHang(){
        radLocTheoMoiKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungKhachHang.setEnabled(false);
                txtLocTheoTungKhachHang.setText("");

                pnlThanhTienIchTKDoanhThuTheoKhachHang.add(pnlLocTheoDoanhSo);

                pnlThanhTienIchTKDoanhThuTheoKhachHang.remove(pnlLocTheoTongTienHDBH);

                pnlThanhTienIchTKDoanhThuTheoKhachHang.revalidate();
                pnlThanhTienIchTKDoanhThuTheoKhachHang.repaint();

                tblDuLieuTKDoanhThuTheoKhachHang.setModel(dtmTKDTTheoKhachHang);
                tblDuLieuTKDoanhThuTheoKhachHang.setRowSorter(trsTKDTTheoKhachHang);
                canLeChoDuLieuTrongTblTKDTTheoKhachHang(false);

                txtTimKiemTrenTblTKDoanhThuTheoKhachHang.removeKeyListener(cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH);
                txtTimKiemTrenTblTKDoanhThuTheoKhachHang.addKeyListener(cheDoTimKiemTrenDtmTKDTTheoKhachHang);
            }
        });
    }

    private void datHanhDongChoRadLocTheoTungKhachHang(){
        radLocTheoTungKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungKhachHang.setEnabled(true);

                pnlThanhTienIchTKDoanhThuTheoKhachHang.remove(pnlLocTheoDoanhSo);

                pnlThanhTienIchTKDoanhThuTheoKhachHang.add(pnlLocTheoTongTienHDBH);

                pnlThanhTienIchTKDoanhThuTheoKhachHang.revalidate();
                pnlThanhTienIchTKDoanhThuTheoKhachHang.repaint();

                tblDuLieuTKDoanhThuTheoKhachHang.setModel(dtmDuLieuTKDoanhThuTheoHDBH);
                tblDuLieuTKDoanhThuTheoKhachHang.setRowSorter(trsDuLieuTKDoanhThuTheoHDBH);
                canLeChoDuLieuTrongTblTKDTTheoKhachHang(true);

                txtTimKiemTrenTblTKDoanhThuTheoKhachHang.removeKeyListener(cheDoTimKiemTrenDtmTKDTTheoKhachHang);
                txtTimKiemTrenTblTKDoanhThuTheoKhachHang.addKeyListener(cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH);
            }
        });
    }

    private void dungPnlLocTheoDoanhSo(){
        pnlLocTheoDoanhSo.setBackground(bgrMacDinh);
        pnlLocTheoDoanhSo.setPreferredSize(dimPnlLocTheoDoanhSo);
        pnlLocTheoDoanhSo.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoDoanhSo, lblTieuDeLocTheoDoanhSo);
        pnlLocTheoDoanhSo.add(pnlTieuDeLocTheoDoanhSo);

        dungPnlLuaChonLocTheoDoanhSo();
        pnlLocTheoDoanhSo.add(pnlLuaChonLocTheoDoanhSo);
    }

    private void dungPnlLuaChonLocTheoDoanhSo(){
        pnlLuaChonLocTheoDoanhSo.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoDoanhSo.setPreferredSize(dimPnlLuaChonLocTheoDoanhSo);
        pnlLuaChonLocTheoDoanhSo.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                3
        ));

        bngLocTheoDoanhSo.add(radKhachHangCoDoanhSoLonNhat);
        bngLocTheoDoanhSo.add(radKhachHangCoDoanhSoNhoNhat);
        bngLocTheoDoanhSo.add(radTatCaDoanhSo);
        bngLocTheoDoanhSo.add(radDuoi30tr);
        bngLocTheoDoanhSo.add(radTren30tr);

        CacHamDungSan.datThuocTinhChoCacRad(radKhachHangCoDoanhSoLonNhat);
        pnlLuaChonLocTheoDoanhSo.add(radKhachHangCoDoanhSoLonNhat);

        CacHamDungSan.datThuocTinhChoCacRad(radKhachHangCoDoanhSoNhoNhat);
        pnlLuaChonLocTheoDoanhSo.add(radKhachHangCoDoanhSoNhoNhat);

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaDoanhSo);
        pnlLuaChonLocTheoDoanhSo.add(radTatCaDoanhSo);

        CacHamDungSan.datThuocTinhChoCacRad(radDuoi30tr);
        pnlLuaChonLocTheoDoanhSo.add(radDuoi30tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTren30tr);
        pnlLuaChonLocTheoDoanhSo.add(radTren30tr);
    }

    private void dungPnlLocTheoTongTienHDBH(){
        pnlLocTheoTongTienHDBH.setBackground(bgrMacDinh);
        pnlLocTheoTongTienHDBH.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoTongTienHDBH.setPreferredSize(dimPnlLocTheoTongTienHDBH);

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoTongTienHDBH, lblTieuDeLocTheoTongTienHDBH);
        pnlLocTheoTongTienHDBH.add(pnlTieuDeLocTheoTongTienHDBH);

        dungPnlLuaChonLocTheoTongTien();
        pnlLocTheoTongTienHDBH.add(pnlLuaChonLocTheoTongTienHDBH);
    }

    private void dungPnlLuaChonLocTheoTongTien(){
        pnlLuaChonLocTheoTongTienHDBH.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoTongTienHDBH.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 3));
        pnlLuaChonLocTheoTongTienHDBH.setPreferredSize(dimPnlLuaChonLocTheoTongTienHDBH);

        tapHopThanhVienCuaBngLocTheoTongTien();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaMucTien);
        pnlLuaChonLocTheoTongTienHDBH.add(radTatCaMucTien);

        CacHamDungSan.datThuocTinhChoCacRad(radDuoi1tr);
        pnlLuaChonLocTheoTongTienHDBH.add(radDuoi1tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu1trDen3tr);
        pnlLuaChonLocTheoTongTienHDBH.add(radTu1trDen3tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu3trDen7tr);
        pnlLuaChonLocTheoTongTienHDBH.add(radTu3trDen7tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTren7tr);
        pnlLuaChonLocTheoTongTienHDBH.add(radTren7tr);
    }

    private void tapHopThanhVienCuaBngLocTheoTongTien(){
        bngLocTheoTongTienHDBH.add(radTatCaMucTien);
        bngLocTheoTongTienHDBH.add(radDuoi1tr);
        bngLocTheoTongTienHDBH.add(radTu1trDen3tr);
        bngLocTheoTongTienHDBH.add(radTu3trDen7tr);
        bngLocTheoTongTienHDBH.add(radTren7tr);
    }

    private void dungPnlLocTheoThoiGian() {
        pnlLocTheoThoiGian.setBackground(bgrMacDinh);
        pnlLocTheoThoiGian.setPreferredSize(dimPnlLocTheoThoiGian_1);
        pnlLocTheoThoiGian.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoThoiGian, lblTieuDeLocTheoThoiGian);
        pnlLocTheoThoiGian.add(pnlTieuDeLocTheoThoiGian);

        dungPnlLuaChonLocTheoThoiGian();
        pnlLocTheoThoiGian.add(pnlLuaChonLocTheoThoiGian);

        dungPnlKhungChonThoiGian();
        pnlLocTheoThoiGian.add(pnlKhungChonThoiGian);
    }

    private void dungPnlLuaChonLocTheoThoiGian(){
        pnlLuaChonLocTheoThoiGian.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoThoiGian.setPreferredSize(dimPnlLuaChonLocTheoThoiGian);
        pnlLuaChonLocTheoThoiGian.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                5
        ));

        bngLocTheoThoiGian.add(radLocTheoMocThoiGian);
        bngLocTheoThoiGian.add(radLocTuyChonThoiGian);

        dungPnlLocTheoCacMocThoiGian();
        pnlLuaChonLocTheoThoiGian.add(pnlLocTheoMocThoiGian);

        CacHamDungSan.datThuocTinhChoCacRad(radLocTuyChonThoiGian);
        datHanhDongChoRadTuyChonThoiGian();
        pnlLuaChonLocTheoThoiGian.add(radLocTuyChonThoiGian);
    }

    private void dungPnlLocTheoCacMocThoiGian(){
        pnlLocTheoMocThoiGian.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlLocTheoMocThoiGian.setBackground(bgrMacDinh);

        cbbCacMocThoiGian.setEnabled(false);
        CacHamDungSan.datThuocTinhChoCacRad(radLocTheoMocThoiGian);
        datHanhDongChoRadLocTheoMocThoiGian();
        pnlLocTheoMocThoiGian.add(radLocTheoMocThoiGian);

        cbbCacMocThoiGian.setPreferredSize(dimCbbCacMocThoiGian);
        cbbCacMocThoiGian.setFont(fntMacDinh);
        cbbCacMocThoiGian.addActionListener((e) -> {
            radLocTheoMocThoiGian.doClick();
        });
        pnlLocTheoMocThoiGian.add(cbbCacMocThoiGian);
    }

    private void datHanhDongChoRadLocTheoMocThoiGian(){
        radLocTheoMocThoiGian.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cbbCacMocThoiGian.setEnabled(true);
                pnlKhungChonThoiGian.setVisible(false);

                pnlLocTheoThoiGian.setPreferredSize(dimPnlLocTheoThoiGian_1);
            }
        });
    }

    private void datHanhDongChoRadTuyChonThoiGian(){
        radLocTuyChonThoiGian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbbCacMocThoiGian.setEnabled(false);
                pnlKhungChonThoiGian.setVisible(true);

                pnlLocTheoThoiGian.setPreferredSize(dimPnlLocTheoThoiGian_2);
            }
        });
    }

    private void dungPnlKhungChonThoiGian(){
        pnlKhungChonThoiGian.setBackground(bgrMacDinh);
        pnlKhungChonThoiGian.setPreferredSize(dimPnlKhungChonThoiGian);
        pnlKhungChonThoiGian.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                0,
                0
        ));
        pnlKhungChonThoiGian.setVisible(false);

        CacHamDungSan.dungPnlNgayBatDauHoacKetThuc(
                pnlNgayBatDauTK,
                lblNgayBatDauTk,
                dtpNgayBatDau,
                dimPnlNgayBatDauVaNgayKetThuc,
                dimDatePicker
        );
        pnlKhungChonThoiGian.add(pnlNgayBatDauTK);

        CacHamDungSan.dungPnlNgayBatDauHoacKetThuc(
                pnlNgayKetThuc,
                lblNgayKetThuc,
                dtpNgayKetThuc,
                dimPnlNgayBatDauVaNgayKetThuc,
                dimDatePicker
        );
        pnlKhungChonThoiGian.add(pnlNgayKetThuc);

        dtpNgayBatDau.addDateChangeListener((e) -> {
            radLocTuyChonThoiGian.doClick();
        });

        dtpNgayKetThuc.addDateChangeListener((e) -> {
            radLocTuyChonThoiGian.doClick();
        });
    }

    private void dungPnlNoiDungTKDoanhThuTheoKhachHang(){
        pnlNoiDungTKDoanhThuTheoKhachHang.setBackground(bgrPnlChinh);
        pnlNoiDungTKDoanhThuTheoKhachHang.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungTKDoanhThuTheoKhachHang.setPreferredSize(dimPnlNoiDungTKDoanhThuTheoKhachHang);

        dungPnlThanhCongCuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoKhachHang.add(pnlThanhCongCuTKDoanhThuTheoKhachHang);

        dungScrChuaTblDuLieuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoKhachHang.add(scrChuaTblDuLieuTKDoanhThuTheoKhachHang);
    }

    private void dungPnlThanhCongCuTKDoanhThuTheoHDBH(){
        pnlThanhCongCuTKDoanhThuTheoKhachHang.setBackground(bgrPnlChinh);
        pnlThanhCongCuTKDoanhThuTheoKhachHang.setPreferredSize(dimPnlThanhCongCuTKDoanhThuTheoKhachHang);
        pnlThanhCongCuTKDoanhThuTheoKhachHang.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 3));

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiemTrenTblTKDoanhThuTheoKhachHang,
                "Tìm kiếm",
                dimTxtTimKiemHDBH
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiemTrenTblTKDoanhThuTheoKhachHang);
        pnlThanhCongCuTKDoanhThuTheoKhachHang.add(txtTimKiemTrenTblTKDoanhThuTheoKhachHang);

        dungPnlHopCongCuTKDoanhThuTheoHDBH();
        pnlThanhCongCuTKDoanhThuTheoKhachHang.add(pnlHopCongCuTKDoanhThuTheoKhachHang);
    }

    private final KeyListener cheDoTimKiemTrenDtmTKDTTheoKhachHang = traVeCheDoTimKiemTrenDtmTKDTTheoKhachHang();
    private final KeyListener cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH = traVeCheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH();

    private KeyListener traVeCheDoTimKiemTrenDtmTKDTTheoKhachHang(){
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsTKDTTheoKhachHang.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiemTrenTblTKDoanhThuTheoKhachHang.getText().trim()
                        )
                );
            }
        };

        return keyListener;
    }

    private KeyListener traVeCheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH(){
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsDuLieuTKDoanhThuTheoHDBH.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiemTrenTblTKDoanhThuTheoKhachHang.getText().trim()
                        )
                );
            }
        };

        return keyListener;
    }

    private void dungPnlHopCongCuTKDoanhThuTheoHDBH(){
        pnlHopCongCuTKDoanhThuTheoKhachHang.setBackground(bgrPnlChinh);
        pnlHopCongCuTKDoanhThuTheoKhachHang.setPreferredSize(dimPnlHopCongCuTKDoanhThuTheoKhachHang);
        pnlHopCongCuTKDoanhThuTheoKhachHang.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatTKDoanhThuTheoKhachHangRaFile,
                bgrBtnXuatDuLieuRaFile,
                bgrMacDinh,
                dimBtnXuatTKDoanhThuTheoKhachHangRaExcel
        );
        datHanhDongChoBtnXuatRaExcel();
        pnlHopCongCuTKDoanhThuTheoKhachHang.add(btnXuatTKDoanhThuTheoKhachHangRaFile);

        dungPmnXuatData();
    }

    private void dungScrChuaTblDuLieuTKDoanhThuTheoHDBH(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDuLieuTKDoanhThuTheoKhachHang,
                dimScrChuaTblDuLieuTKDoanhThuTheoKhachHang,
                null
        );
        datHanhDongChoTblTKDoanhThuTheoKhachHang();

        scrChuaTblDuLieuTKDoanhThuTheoKhachHang.getViewport().setBackground(bgrMacDinh);
        scrChuaTblDuLieuTKDoanhThuTheoKhachHang.setPreferredSize(dimScrChuaTblDuLieuTKDoanhThuTheoKhachHang);
    }

    /**
     * <p>Can le, dat khoang cach cho cac cot cua tbl</p>
     * @param isDtmTKDoanhThuTheoHDBH: <li>true: tbl.getTableModel() = dtmDuLieuTKDoanhThuTheoHDBH</li>
     */
    private void canLeChoDuLieuTrongTblTKDTTheoKhachHang(boolean isDtmTKDoanhThuTheoHDBH){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblDuLieuTKDoanhThuTheoKhachHang.getColumnModel();

        if (isDtmTKDoanhThuTheoHDBH){
            for (int i = 0; i < tblDuLieuTKDoanhThuTheoKhachHang.getColumnCount(); ++i) {
                if (i == 3 || i == 4 || i == 5 || i == 6){
                    tcm.getColumn(i).setCellRenderer(rightRenderer);
                }
            }

            tcm.getColumn(0).setPreferredWidth(80);
            tcm.getColumn(0).setMaxWidth(80);

            tcm.getColumn(1).setPreferredWidth(150);
            tcm.getColumn(1).setMaxWidth(150);

            tcm.getColumn(2).setPreferredWidth(150);
            tcm.getColumn(2).setMaxWidth(150);

            tcm.getColumn(3).setPreferredWidth(100);
            tcm.getColumn(3).setMaxWidth(100);
        }
        else{
            for (int i = 0; i < tblDuLieuTKDoanhThuTheoKhachHang.getColumnCount(); ++i) {
                if (i == 4 || i == 5 || i == 6){
                    tcm.getColumn(i).setCellRenderer(rightRenderer);
                }
            }

            tcm.getColumn(0).setPreferredWidth(110);
            tcm.getColumn(0).setMaxWidth(110);

            tcm.getColumn(1).setPreferredWidth(300);
            tcm.getColumn(1).setMaxWidth(300);

            tcm.getColumn(2).setPreferredWidth(150);
            tcm.getColumn(2).setMaxWidth(150);

            tcm.getColumn(3).setPreferredWidth(100);
            tcm.getColumn(3).setMaxWidth(100);

            tcm.getColumn(4).setPreferredWidth(100);
            tcm.getColumn(4).setMaxWidth(100);

            tcm.getColumn(5).setPreferredWidth(100);
            tcm.getColumn(5).setMaxWidth(100);
        }
    }

    private void datHanhDongChoTblTKDoanhThuTheoKhachHang(){
        tblDuLieuTKDoanhThuTheoKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == 1){
                    if (radLocTheoMoiKhachHang.isSelected()){
                        int row = tblDuLieuTKDoanhThuTheoKhachHang.getSelectedRow();

                        if (
                                row != -1 &&
                                !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(tblDuLieuTKDoanhThuTheoKhachHang, row)
                        ){
                            int maKH = Integer.parseInt(
                                    tblDuLieuTKDoanhThuTheoKhachHang.getValueAt(
                                            row, 0
                                    ).toString()
                            );

                            SwingUtilities.invokeLater(() -> {
                                GDXemThongTinKhachHang gdXemThongTinKhachHang = GDXemThongTinKhachHang.getGdXemThongTinKhachHang();

                                gdXemThongTinKhachHang.cungCapMaKhachHang(maKH);

                                gdXemThongTinKhachHang.setVisible(true);
                            });
                        }
                    }
                }
            }
        });
    }

    private void datHanhDongChoBtnXuatRaExcel(){
        btnXuatTKDoanhThuTheoKhachHangRaFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pmnXuatData.show(
                        btnXuatTKDoanhThuTheoKhachHangRaFile,
                        0,
                        btnXuatTKDoanhThuTheoKhachHangRaFile.getHeight()
                );
            }
        });
    }

    private void dungPmnXuatData(){
        pmnXuatData.setBackground(bgrMacDinh);
        pmnXuatData.setPreferredSize(dimPmnXuatData);
        pmnXuatData.setBorder(BorderFactory.createEmptyBorder());

        datHanhDongChoMniXuatExcel();
        pmnXuatData.add(mniXuatExcel);

        datHanhDongChoMniXuatPDF();
        pmnXuatData.add(mniXuatPDF);
    }

    private void datHanhDongChoMniXuatExcel(){
        mniXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radLocTheoMoiKhachHang.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoKhachHang,
                            true,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TAT_CA_KHACH_HANG,
                            ""
                    );
                }
                else if (radLocTheoTungKhachHang.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoKhachHang,
                            true,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TUNG_KHACH_HANG,
                            "KH-"
                                    + tblDuLieuTKDoanhThuTheoKhachHang.getValueAt(1, 1)
                    );
                }
            }
        });
    }

    private void datHanhDongChoMniXuatPDF(){
        mniXuatPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radLocTheoMoiKhachHang.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoKhachHang,
                            false,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TAT_CA_KHACH_HANG,
                            ""
                    );
                }
                else if (radLocTheoTungKhachHang.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoKhachHang,
                            false,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TUNG_KHACH_HANG,
                            "KH-"
                                    + tblDuLieuTKDoanhThuTheoKhachHang.getValueAt(1, 1)
                    );
                }
            }
        });
    }

    private List<JRadioButton> btnsLocTheoDoiTuong = Arrays.asList(radLocTheoMoiKhachHang, radLocTheoTungKhachHang);
    private List<JRadioButton> btnsLocTheoGiaTien =
            Arrays.asList(radTatCaMucTien, radDuoi1tr, radTu1trDen3tr, radTu3trDen7tr, radTren7tr);
    private List<JRadioButton> btnsLocTheoDoanhThu =
            Arrays.asList(radTatCaDoanhSo, radKhachHangCoDoanhSoLonNhat, radKhachHangCoDoanhSoNhoNhat, radDuoi30tr, radTren30tr);
    private List<JRadioButton> btnsLocTheoThoiGian = Arrays.asList(radLocTheoMocThoiGian, radLocTuyChonThoiGian);

    /**
     * @author Hiếu
     * <p><i>
     * Hàm này có chức năng tên cho các radio button <br>
     * và thêm sự kiện cho tất cả các radio button đó
     * </i></p>
     */
    public void datHanhDongChoRadioButtons() {
        radLocTheoMoiKhachHang.setName("MoiKhachHang");
        radLocTheoTungKhachHang.setName("TungKhachHang");

        radTatCaMucTien.setName("TatCaMucTien");
        radDuoi1tr.setName("Duoi1tr");
        radTu1trDen3tr.setName("1trDen3tr");
        radTu3trDen7tr.setName("3trDen7tr");
        radTren7tr.setName("Tren7tr");

        radTatCaDoanhSo.setName("TatCaDoanhThu");
        radKhachHangCoDoanhSoLonNhat.setName("DoanhThuLonNhat");
        radKhachHangCoDoanhSoNhoNhat.setName("DoanhThuNhoNhat");
        radDuoi30tr.setName("DoanhThuDuoi10tr");
        radTren30tr.setName("DoanhThuTren10tr");

        radLocTheoMocThoiGian.setName("LocTheoMoc");
        radLocTuyChonThoiGian.setName("TuyChon");

        btnsLocTheoDoiTuong.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoGiaTien.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoDoanhThu.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoThoiGian.forEach(btn -> {
            btn.addActionListener(this);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoDoiTuong = new AtomicInteger(-1);
        AtomicInteger locTheoMucTien = new AtomicInteger(-1);
        AtomicInteger locTheoDoanhThu = new AtomicInteger(-1);
        AtomicInteger locTheoNgay = new AtomicInteger(-1);

        btnsLocTheoDoiTuong.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("TungKhachHang")) {
                    locTheoDoiTuong.set(1);
                } else if(btn.getName().equals("MoiKhachHang"))
                    locTheoDoiTuong.set(0);
            }
        });

        btnsLocTheoGiaTien.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.isSelected()) {
                    if(btn.getName().equals("Duoi1tr"))
                        locTheoMucTien.set(0);
                    else if(btn.getName().equals("1trDen3tr"))
                        locTheoMucTien.set(1);
                    else if(btn.getName().equals("3trDen7tr"))
                        locTheoMucTien.set(2);
                    else if(btn.getName().equals("Tren7tr"))
                        locTheoMucTien.set(3);
                }
            }
        });

        btnsLocTheoDoanhThu.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.isSelected()) {
                    if(btn.getName().equals("DoanhThuLonNhat"))
                        locTheoDoanhThu.set(0);
                    else if(btn.getName().equals("DoanhThuNhoNhat"))
                        locTheoDoanhThu.set(1);
                    else if(btn.getName().equals("DoanhThuDuoi10tr"))
                        locTheoDoanhThu.set(2);
                    else if(btn.getName().equals("DoanhThuTren10tr"))
                        locTheoDoanhThu.set(3);
                }
            }
        });

        btnsLocTheoThoiGian.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("LocTheoMoc")) {
                    locTheoNgay.set(1);
                } else
                    locTheoNgay.set(0);
            }
        });

        if(locTheoNgay.get() == 0) {
            if(dtpNgayBatDau.getDate() == null || dtpNgayKetThuc.getDate() == null) {
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Vui lòng chọn ngày để lọc!"
                );
                return;
            }

            if(dtpNgayBatDau.getDate().isAfter(dtpNgayKetThuc.getDate())) {
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Ngày bắt đầu phải trước ngày kết thúc!"
                );
                return;
            }
        }

        if(locTheoDoiTuong.get() != -1) {
            if(locTheoDoiTuong.get() == 1) {
                if(!txtLocTheoTungKhachHang.getText().equals("Tìm kiếm KH") && !txtLocTheoTungKhachHang.getText().isEmpty())
                    capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoDoiTuong.get(), locTheoMucTien.get(), locTheoNgay.get()));
            }
            else if(locTheoDoiTuong.get() == 0)
                capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoDoiTuong.get(), locTheoDoanhThu.get(), locTheoNgay.get()));
        }
    }

    private void capNhatDuLieuLenTable(String query) {
        try {
            PreparedStatement pState = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ResultSet res = pState.executeQuery();

            if(!res.isBeforeFirst()) {
                CacHamDungSan.hienThiThongBaoKhongCoDuLieuPhuHop();

                xoaDuLieuTheoBang();
                return;
            }

            xoaDuLieuTheoBang();
            chuyenDuLieuSangTableTuongUng(res);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void xoaDuLieuTheoBang() {
        if(radLocTheoMoiKhachHang.isSelected()) {
            dtmTKDTTheoKhachHang.setRowCount(0);
            tblDuLieuTKDoanhThuTheoKhachHang.setModel(dtmTKDTTheoKhachHang);
            tblDuLieuTKDoanhThuTheoKhachHang.setRowSorter(trsTKDTTheoKhachHang);
            canLeChoDuLieuTrongTblTKDTTheoKhachHang(false);
        }
        else if(radLocTheoTungKhachHang.isSelected()) {
            dtmDuLieuTKDoanhThuTheoHDBH.setRowCount(0);
            tblDuLieuTKDoanhThuTheoKhachHang.setModel(dtmDuLieuTKDoanhThuTheoHDBH);
            tblDuLieuTKDoanhThuTheoKhachHang.setRowSorter(trsDuLieuTKDoanhThuTheoHDBH);
            canLeChoDuLieuTrongTblTKDTTheoKhachHang(true);
        }
    }

    private void chuyenDuLieuSangTableTuongUng(ResultSet res) throws SQLException {
        if(radLocTheoMoiKhachHang.isSelected()) {
            AtomicInteger tongSoHoaDon = new AtomicInteger(0);
            AtomicInteger tongSLSP = new AtomicInteger(0);
            AtomicReference<Double> tongDoanhSo = new AtomicReference<>(0.0);

            while(res.next()){
                dtmTKDTTheoKhachHang.addRow(chuyenDuLieuSangTKTheoMoiKH(res));

                tongSoHoaDon.set(tongSoHoaDon.get() + res.getInt("SLSP"));
                tongSLSP.set(tongSLSP.get() +  res.getInt("SoHD"));
                tongDoanhSo.set(tongDoanhSo.get() + res.getDouble("SucMua"));
            }

            themDongTongKetKhiRadMoiKhachHangDuocChon(tongSoHoaDon.get(), tongSLSP.get(), tongDoanhSo.get());
        }
        else if(radLocTheoTungKhachHang.isSelected()) {
            AtomicInteger tongSLSP = new AtomicInteger(0);
            AtomicReference<Double> tongDoanhThuChuaThue = new AtomicReference<>(0.0);
            AtomicReference<Double> tongVAT = new AtomicReference<>(0.0);
            AtomicReference<Double> tongTien = new AtomicReference<>(0.0);

            while(res.next()){
                dtmDuLieuTKDoanhThuTheoHDBH.addRow(chuyenDuLieuSangTKTheoTungKH(res));

                tongSLSP.set(tongSLSP.get() + res.getInt("tongSLSP"));
                tongDoanhThuChuaThue.set(tongDoanhThuChuaThue.get() + res.getDouble("thanhTienChuaThue"));
                tongVAT.set(tongVAT.get() + res.getDouble("thueGTGT"));
                tongTien.set(tongTien.get() + res.getDouble("tongTien"));
            }

            themDongTongKetKhiRadTungKhachHangDuocChon(
                    tongSLSP.get(),
                    tongDoanhThuChuaThue.get(),
                    tongVAT.get(),
                    tongTien.get()
            );
        }
    }

    private void themDongTongKetKhiRadMoiKhachHangDuocChon(int soHoaDon, int slsp, double doanhThu){
        dtmTKDTTheoKhachHang.insertRow(
                0,
                new Object[]{
                        "Tổng cộng:",
                        dtmTKDTTheoKhachHang.getRowCount() + " khách hàng",
                        "",
                        "",
                        soHoaDon,
                        slsp,
                        nf.format(doanhThu)
                }
        );
    }

    private void themDongTongKetKhiRadTungKhachHangDuocChon(int slsp, double tongDoanhThuChuaThue, double tongVAT, double tongTien){
        dtmDuLieuTKDoanhThuTheoHDBH.insertRow(
                0,
                new Object[]{
                        "Tổng kết:",
                        dtmDuLieuTKDoanhThuTheoHDBH.getRowCount() + " hoá đơn",
                        "",
                        slsp,
                        nf.format(tongDoanhThuChuaThue),
                        nf.format(tongVAT),
                        nf.format(tongTien)
                }
        );
    }

    private Object[] chuyenDuLieuSangTKTheoMoiKH(ResultSet res) throws SQLException {
        int maKHLonNhat = TienIch.laySoLonNhat("maKH", "maKH", "Khachhang");
        return new Object[] {
                TienIch.dinhDangSo(maKHLonNhat,res.getInt("maKH")),
                MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                MaHoaDuLieu.giaiMa(res.getString("soDT")),
                res.getBoolean("gioiTinh") ? "Nam" : "Nữ",
                res.getInt("SLSP"),
                res.getInt("SoHD"),
                dinhDangTien(res.getDouble("SucMua"))
        };
    }

    private Object[] chuyenDuLieuSangTKTheoTungKH(ResultSet res) throws SQLException {
        int maHDBHLonNhat = TienIch.laySoLonNhat("maHDBH", "maHDBH", "HoaDonBanHang");
        return new Object[] {
                TienIch.dinhDangSo(maHDBHLonNhat, res.getInt("maHDBH")),
                MaHoaDuLieu.giaiMa(res.getString("soDT")),
                TienIch.dinhDangNgay(res.getTimestamp("thoiGianLap").toLocalDateTime().toLocalDate(), "dd-MM-yyyy"),
                res.getInt("tongSLSP"),
                dinhDangTien(res.getDouble("thanhTienChuaThue")),
                dinhDangTien(res.getDouble("thueGTGT")),
                dinhDangTien(res.getDouble("tongTien"))
        };
    }

    private String sinhCauTruyVanCSDL(int doiTuong, int locTheoTien, int locTheoNgay) {
        String query = sinhCauTruyVanTheoDoiTuong(doiTuong);

        if(locTheoTien != -1 && locTheoNgay != -1) {
            if(doiTuong == 0) {
                if(locTheoNgay == 1) {
                    query += String.format("where %s group by kh.maKH, kh.hoTen, kh.soDT, kh.gioiTinh %s",
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()),
                            sinhCauTruyVanLocTheoDoanhThu(locTheoTien));

                } else if(locTheoNgay == 0) {
                    query += String.format("where %s group by kh.maKH, kh.hoTen, kh.soDT, kh.gioiTinh %s",
                            sinhCauTruyVanLocTheoTuyChonThoiGian(),
                            sinhCauTruyVanLocTheoDoanhThu(locTheoTien));
                }
                if(locTheoTien == 0)
                    query = String.format("Select top 10 %s", query);
                else if(locTheoTien == 1)
                    query = String.format("Select top 20 %s", query);
                else
                    query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                if(locTheoNgay == 0) {
                    query += String.format("where kh.maKH = '%s' and %s and %s",
                            layMaKHTheoSDT(txtLocTheoTungKhachHang.getText().trim()),
                            sinhCauTruyVanLocTheoTongTien(locTheoTien),
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }
                else if(locTheoNgay == 1) {
                    query += String.format("where kh.maKH = '%s' and %s and %s",
                            layMaKHTheoSDT(txtLocTheoTungKhachHang.getText().trim()),
                            sinhCauTruyVanLocTheoTongTien(locTheoTien),
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));
                }
            }
        }
        else if(locTheoTien == -1 && locTheoNgay != -1) {
            if(doiTuong == 0) {
                if(locTheoNgay == 1) {
                    query += String.format("where %s group by kh.maKH, kh.hoTen, kh.soDT, kh.gioiTinh",
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));

                } else if(locTheoNgay == 0) {
                    query += String.format("where %s group by kh.maKH, kh.hoTen, kh.soDT, kh.gioiTinh",
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }

                query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                if(locTheoNgay == 0) {
                    query += String.format("where kh.maKH = '%s' and %s",
                            layMaKHTheoSDT(txtLocTheoTungKhachHang.getText().trim()),
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }
                else if(locTheoNgay == 1) {
                    query += String.format("where kh.maKH = '%s' and %s",
                            layMaKHTheoSDT(txtLocTheoTungKhachHang.getText().trim()),
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));
                }
            }
        }
        else if(locTheoTien != -1 && locTheoNgay == -1) {
            if(doiTuong == 0) {
                query += String.format(" group by kh.maKH, kh.hoTen, kh.soDT, kh.gioiTinh %s",
                        sinhCauTruyVanLocTheoDoanhThu(locTheoTien));
                if(locTheoTien == 0)
                    query = String.format("Select top 10 %s", query);
                else if(locTheoTien == 1)
                    query = String.format("Select top 20 %s", query);
                else
                    query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                query += String.format("where kh.maKH = '%s' and %s",
                        layMaKHTheoSDT(txtLocTheoTungKhachHang.getText().trim()),
                        sinhCauTruyVanLocTheoTongTien(locTheoTien));
            }
        }
        else if(locTheoTien == -1 && locTheoNgay == -1) {
            if(doiTuong == 0)
                query = String.format("Select top 20 %s group by kh.maKH, kh.hoTen, kh.soDT, kh.gioiTinh order by kh.maKH desc", query);
            else if(doiTuong == 1)
                query += String.format("where kh.maKH = %s", layMaKHTheoSDT(txtLocTheoTungKhachHang.getText().trim()));
        }

        return query;
    }

    private int layMaKHTheoSDT(String sdt) {
        AtomicInteger maKH = new AtomicInteger(-1);

        dsKhachHang.entrySet().forEach(kh -> {
            if(kh.getValue().getSoDT().equals(sdt)) {
                maKH.set(kh.getKey());
            }
        });

        return maKH.get();
    }

    private String sinhCauTruyVanTheoDoiTuong(int doiTuong) {
        String query = "";

        if(doiTuong == 0) {
            query += " kh.maKH, kh.hoTen, kh.soDT, kh.gioiTinh, sum(hd.tongSLSP) as SLSP, count(hd.maHDBH) as SoHD, " +
                    "sum(hd.tongTien) as SucMua from HoaDonBanHang hd join KhachHang kh on kh.maKH = hd.maKH ";
        }
        else if(doiTuong == 1) {
            query += "Select hd.maHDBH, kh.soDT, hd.thoiGianLap, " +
                    "hd.tongSLSP, hd.thanhTienChuaThue, hd.thueGTGT, hd.tongTien " +
                    "from HoaDonBanHang hd  join KhachHang kh on kh.maKH = hd.maKH ";
        }

        return query;
    }

    private String sinhCauTruyVanLocTheoTongTien(int locTheoTongTien) {
        String query = "";

        if(locTheoTongTien == 0)
            query = "hd.tongTien < 1000000";
        else if(locTheoTongTien == 1)
            query = "hd.tongTien >= 1000000 and hd.tongTien <= 3000000";
        else if(locTheoTongTien == 2)
            query = "hd.tongTien > 3000000 and hd.tongTien <= 7000000";
        else if(locTheoTongTien == 3)
            query = "hd.tongTien > 7000000";

        return query;
    }

    private String sinhCauTruyVanLocTheoDoanhThu(int locTheoDoanhThu) {
        String query = "";

        if(locTheoDoanhThu == 0)
            query = "order by SucMua desc";
        else if(locTheoDoanhThu == 1)
            query = "order by SucMua asc";
        else if(locTheoDoanhThu == 2)
            query = "having sum(hd.tongTien) < 10000000";
        else if(locTheoDoanhThu == 3)
            query = "having sum(hd.tongTien) > 10000000";

        return query;
    }

    private String sinhCauTruyVanLocTheoMocThoiGian(int cheDoLocMocThoiGian) {
        String query = "";

        LocalDate homNay = LocalDate.now();
        if(cheDoLocMocThoiGian == 0) {
            query += String.format("cast(hd.thoiGianLap as date) = '%s'",  TienIch.dinhDangNgay(homNay, "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 1) {
            query += String.format("hd.thoiGianLap >= '%s' and hd.thoiGianLap < '%s'", TienIch.getNgayDauTrongTuan(),
                    TienIch.dinhDangNgay(homNay.plusDays(1), "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 2) {
            query += String.format("month(hd.thoiGianLap) = '%s' and year(hd.thoiGianLap) = '%s'",
                    homNay.getMonthValue(), homNay.getYear());
        }

        return query;
    }

    private String sinhCauTruyVanLocTheoTuyChonThoiGian() {
        String query = String.format("hd.thoiGianLap >= '%s' and hd.thoiGianLap < '%s'",
                TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));

        if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate())){
            query = String.format("cast (hd.thoiGianLap as date) = '%s'",
                    TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"));
        }

        return query;
    }

    private String dinhDangTien(double tien) {
        return nf.format(tien);
    }

    public void locLaiDuLieuSauKhiThemHoacCapNhat() {
        btnsLocTheoDoiTuong.forEach(btn -> {
            if(btn.isSelected())
                btn.doClick();
        });

        btnsLocTheoThoiGian.forEach(btn -> {
            if(btn.isSelected())
                btn.doClick();
        });

        btnsLocTheoDoanhThu.forEach(btn -> {
            if(btn.isSelected())
                btn.doClick();
        });

        btnsLocTheoGiaTien.forEach(btn -> {
            if(btn.isSelected())
                btn.doClick();
        });
    }
}