package ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheonhanvien;

import connectDB.KetNoiCSDL;
import dao.NhanVienDAO;
import entity.NhanVien;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlnhanvien.gdxemthongtinnhanvien.GDXemTTNhanVien;
import ui.giaodienchinh.pnlqlnhanvien.kieudulieudacbiet.NhanVienTimDuoc;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PnlTKDoanhThuTheoNhanVien implements IDSBienPnlTKDoanhThuTheoNhanVien, ActionListener{

    public void dungPnlTKDoanhThuTheoNhanVien(JPanel pnlTKDTTheoNhanVien){
        pnlTKDTTheoNhanVien.setBackground(bgrPnlChinh);
        pnlTKDTTheoNhanVien.setPreferredSize(dimTabNoiDung);
        pnlTKDTTheoNhanVien.setLayout(new FlowLayout(
                FlowLayout.CENTER, 0, 0
        ));

        dungPnlThanhTienIch();
        pnlTKDTTheoNhanVien.add(pnlThanhTienIchTKDoanhThuTheoNhanVien);

        pnlTKDTTheoNhanVien.add(Box.createHorizontalStrut(5));

        dungPnlNoiDungTKDoanhThuTheoNhanVien();
        pnlTKDTTheoNhanVien.add(pnlNoiDungTKDoanhThuTheoNhanVien);

        capNhatDSNhanVien();

        datHanhDongChoRadioButtons();
    }

    public static void capNhatDSNhanVien(){
        dsNhanVien.clear();

        Thread luongCapNhatDSKH = new Thread(() -> {
            ArrayList<NhanVien> dsKH = new ArrayList<>(
                    NhanVienDAO.layToanBoDuLieuNhanVien().values()
            );

            dsKH.forEach(nv -> {
                dsNhanVien.put(
                        nv.getMaNV(),
                        nv
                );
            });
        });

        luongCapNhatDSKH.start();
    }

    private void dungPnlThanhTienIch(){
        pnlThanhTienIchTKDoanhThuTheoNhanVien.setBackground(bgrMacDinh);
        pnlThanhTienIchTKDoanhThuTheoNhanVien.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        0,
                        0
                )
        );
        pnlThanhTienIchTKDoanhThuTheoNhanVien.setPreferredSize(dimPnlThanhTienIchTKDoanhThuTheoNhanVien);

        dungPnlNhanVienCanThongKe();
        pnlThanhTienIchTKDoanhThuTheoNhanVien.add(pnlNhanVienCanThongKe);

        dungPnlLocTheoThoiGian();
        pnlThanhTienIchTKDoanhThuTheoNhanVien.add(pnlLocTheoThoiGian);
    }

    private void dungPnlNhanVienCanThongKe(){
        pnlNhanVienCanThongKe.setBackground(bgrMacDinh);
        pnlNhanVienCanThongKe.setPreferredSize(dimPnlNhanVienCanThongKe);
        pnlNhanVienCanThongKe.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoNhanVien, lblTieuDeLocTheoNhanVien);
        pnlNhanVienCanThongKe.add(pnlTieuDeLocTheoNhanVien);

        dungPnlLuaChonLocTheoNhanVien();
        pnlNhanVienCanThongKe.add(pnlLuaChonLocTheoNhanVien);

        dungPnlLocTheoDoanhSo();

        dungPnlLocTheoTongTienHDBH();

        dungPmnKetQuaTimKiemNhanVien();
    }

    private void dungPnlLuaChonLocTheoNhanVien(){
        pnlLuaChonLocTheoNhanVien.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoNhanVien.setPreferredSize(dimPnlLuaChonLocTheoNhanVien);
        pnlLuaChonLocTheoNhanVien.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                3
        ));

        bngLocTheoNhanVien.add(radLocTheoMoiNhanVien);
        bngLocTheoNhanVien.add(radLocTheoTungNhanVien);

        CacHamDungSan.datThuocTinhChoCacRad(radLocTheoMoiNhanVien);
        datHanhDongChoRadMoiNhanVien();
        pnlLuaChonLocTheoNhanVien.add(radLocTheoMoiNhanVien);

        dungPnlLocTheoTungNhanVien();
        pnlLuaChonLocTheoNhanVien.add(pnlLocTheoTungNhanVien);
    }

    private void dungPnlLocTheoTungNhanVien(){
        pnlLocTheoTungNhanVien.setBackground(bgrMacDinh);
        pnlLocTheoTungNhanVien.setLayout(
                new BoxLayout(pnlLocTheoTungNhanVien, BoxLayout.X_AXIS)
        );

        CacHamDungSan.datThuocTinhChoCacRad(
                radLocTheoTungNhanVien
        );
        datHanhDongChoRadLocTheoTungNhanVien();
        pnlLocTheoTungNhanVien.add(radLocTheoTungNhanVien);

        pnlLocTheoTungNhanVien.add(Box.createHorizontalStrut(4));

        txtLocTheoTungNhanVien.setEnabled(false);
        CacHamDungSan.datThuocTinhChoTxt(
                txtLocTheoTungNhanVien,
                "Tìm kiếm NV",
                dimTxtLocTheoTungNhanVien
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiemTrenTblTKDoanhThuTheoNhanVien);
        txtLocTheoTungNhanVien.setToolTipText("Tìm nhân viên theo mã");
        datHanhDongChoTxtLocTheoTungNhanVien();
        pnlLocTheoTungNhanVien.add(txtLocTheoTungNhanVien);
    }

    private void datHanhDongChoTxtLocTheoTungNhanVien(){
        txtLocTheoTungNhanVien.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtLocTheoTungNhanVien.getText().trim().toLowerCase();

                if (tuKhoa.isEmpty()){
                    pmnChuaDSNVTimDuoc.setVisible(false);
                }
                else{
                    ArrayList<NhanVienTimDuoc> dsTimDuoc = timKiemNVTheoTuKhoa(tuKhoa);

                    if (!dsTimDuoc.isEmpty()){
                        duaDuLieuVaoTable(dsTimDuoc);

                        pmnChuaDSNVTimDuoc.setVisible(true);

                        tblDSNVTimDuoc.setEnabled(true);

                        pmnChuaDSNVTimDuoc.show(
                                txtLocTheoTungNhanVien,
                                0,
                                txtLocTheoTungNhanVien.getHeight()
                        );

                        txtLocTheoTungNhanVien.requestFocus();
                    }
                    else{
                        tblDSNVTimDuoc.setEnabled(false);

                        dtmDSNVTimDuoc.setRowCount(0);

                        Object[] thongBaoKhongTimRaKHNao = {
                                "",
                                "( ^ _ ^ )",
                                "",
                                ""
                        };

                        dtmDSNVTimDuoc.addRow(thongBaoKhongTimRaKHNao);

                        datKichThuocChoPmnChuaDSNVTimDuoc(2);
                    }
                }
            }
        });

        txtLocTheoTungNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungNhanVien.setFocusable(true);
                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtLocTheoTungNhanVien, "");
            }
        });
    }

    private void dungPmnKetQuaTimKiemNhanVien(){
        pmnChuaDSNVTimDuoc.setBackground(bgrMacDinh);
        pmnChuaDSNVTimDuoc.setBorder(BorderFactory.createEmptyBorder());
        pmnChuaDSNVTimDuoc.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 1
                )
        );

        dungTblChuaDSNVTimDuoc();
        datHanhDongChoTblDSNVTimDuoc();
        pmnChuaDSNVTimDuoc.add(tblDSNVTimDuoc.getTableHeader());
        pmnChuaDSNVTimDuoc.add(tblDSNVTimDuoc, BorderLayout.CENTER);
    }

    private void dungTblChuaDSNVTimDuoc(){
        tblDSNVTimDuoc.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblDSNVTimDuoc.setFont(fntMacDinh);
        tblDSNVTimDuoc.setDefaultEditor(Object.class, null);

        tblDSNVTimDuoc.getTableHeader().setFont(fntMacDinh);
        tblDSNVTimDuoc.getTableHeader().setPreferredSize(
                new Dimension(
                        chieuRongPmnChuaDSNVTimDuoc,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblDSNVTimDuoc.getTableHeader().setBackground(bgrTieuDeTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel tcm = tblDSNVTimDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(0).setMaxWidth(100);
        tcm.getColumn(0).setCellRenderer(
                centerRenderer
        );

        tcm.getColumn(1).setPreferredWidth(80);
        tcm.getColumn(1).setMaxWidth(80);

        tcm.getColumn(3).setPreferredWidth(110);
        tcm.getColumn(3).setMaxWidth(110);
    }

    private void datHanhDongChoTblDSNVTimDuoc(){
        tblDSNVTimDuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int hangDuocChon = tblDSNVTimDuoc.getSelectedRow();

                if (hangDuocChon != -1){
                    String maNV = (String) tblDSNVTimDuoc.getValueAt(
                            hangDuocChon, 0
                    );

                    NhanVien nv = dsNhanVien.get(maNV);

                    int soLanClick = e.getClickCount();
                    int nutChuotDuocNhan = e.getButton();

                    if (soLanClick == 1 && nutChuotDuocNhan == 1){
                        txtLocTheoTungNhanVien.setText(
                                nv.getMaNV()
                        );

                        locLaiDuLieuSauKhiThemHoacCapNhat();

                        txtLocTheoTungNhanVien.setFocusable(false);

                        pmnChuaDSNVTimDuoc.setVisible(false);
                    }
                }
            }
        });
    }

    private void datKichThuocChoPmnChuaDSNVTimDuoc(int slKetQua){
        pmnChuaDSNVTimDuoc.setPopupSize(
                new Dimension(
                        chieuRongPmnChuaDSNVTimDuoc,
                        chieuCaoHangDuLieuTrongTable * slKetQua
                )
        );
    }

    private void duaDuLieuVaoTable(ArrayList<NhanVienTimDuoc> dsNV){
        dtmDSNVTimDuoc.setRowCount(0);

        dsNV.forEach(kh -> {
            Object[] o = {
                    kh.getMaNV(),
                    kh.isCaSang() ? "Ca sáng" : "Ca tối",
                    kh.getHoTen(),
                    kh.getSdt()
            };

            dtmDSNVTimDuoc.addRow(o);
        });

        datKichThuocChoPmnChuaDSNVTimDuoc(dsNV.size() + 1);
    }

    private ArrayList<NhanVienTimDuoc> timKiemNVTheoTuKhoa(String tuKhoa){
        ArrayList<NhanVienTimDuoc> ketQuaTimKiemNV = new ArrayList<>();

        AtomicInteger coHieu = new AtomicInteger(0);

        dsNhanVien.forEach((K, V) -> {
            if (coHieu.get() < 7){
                if (V.getSoDT().contains(tuKhoa) || V.getMaNV().contains(tuKhoa)){
                    NhanVienTimDuoc nhanVienTimDuoc = new NhanVienTimDuoc(
                            V.getMaNV(),
                            V.getCaLamViec().isCaSang(),
                            V.getHoTen(),
                            V.getSoDT()
                    );

                    ketQuaTimKiemNV.add(nhanVienTimDuoc);

                    coHieu.getAndIncrement();
                }
            }
        });

        return ketQuaTimKiemNV;
    }

    private void datHanhDongChoRadMoiNhanVien(){
        radLocTheoMoiNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungNhanVien.setEnabled(false);

                pnlThanhTienIchTKDoanhThuTheoNhanVien.add(pnlLocTheoDoanhSo);

                pnlThanhTienIchTKDoanhThuTheoNhanVien.remove(pnlLocTheoTongTienHDBH);

                pnlThanhTienIchTKDoanhThuTheoNhanVien.revalidate();
                pnlThanhTienIchTKDoanhThuTheoNhanVien.repaint();

                tblDuLieuTKDoanhThuTheoNhanVien.setModel(dtmTKDTTheoNhanVien);
                tblDuLieuTKDoanhThuTheoNhanVien.setRowSorter(trsTKDTTheoNhanVien);
                canLeChoDuLieuTrongTblTKDTTheoNhanVien(false);

                txtTimKiemTrenTblTKDoanhThuTheoNhanVien.removeKeyListener(cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH);
                txtTimKiemTrenTblTKDoanhThuTheoNhanVien.addKeyListener(cheDoTimKiemTrenDtmTKDTTheoNhanVien);
            }
        });
    }

    private void datHanhDongChoRadLocTheoTungNhanVien(){
        radLocTheoTungNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungNhanVien.setEnabled(true);

                pnlThanhTienIchTKDoanhThuTheoNhanVien.remove(pnlLocTheoDoanhSo);

                pnlThanhTienIchTKDoanhThuTheoNhanVien.add(pnlLocTheoTongTienHDBH);

                pnlThanhTienIchTKDoanhThuTheoNhanVien.revalidate();
                pnlThanhTienIchTKDoanhThuTheoNhanVien.repaint();

                tblDuLieuTKDoanhThuTheoNhanVien.setModel(dtmDuLieuTKDoanhThuTheoHDBH);
                tblDuLieuTKDoanhThuTheoNhanVien.setRowSorter(trsDuLieuTKDoanhThuTheoHDBH);
                canLeChoDuLieuTrongTblTKDTTheoNhanVien(true);

                txtTimKiemTrenTblTKDoanhThuTheoNhanVien.removeKeyListener(cheDoTimKiemTrenDtmTKDTTheoNhanVien);
                txtTimKiemTrenTblTKDoanhThuTheoNhanVien.addKeyListener(cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH);
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

        bngLocTheoDoanhSo.add(radNhanVienCoDoanhSoLonNhat);
        bngLocTheoDoanhSo.add(radNhanVienCoDoanhSoNhoNhat);
        bngLocTheoDoanhSo.add(radTatCaDoanhSo);
        bngLocTheoDoanhSo.add(radDuoi30tr);
        bngLocTheoDoanhSo.add(radTren30tr);

        CacHamDungSan.datThuocTinhChoCacRad(radNhanVienCoDoanhSoLonNhat);
        pnlLuaChonLocTheoDoanhSo.add(radNhanVienCoDoanhSoLonNhat);

        CacHamDungSan.datThuocTinhChoCacRad(radNhanVienCoDoanhSoNhoNhat);
        pnlLuaChonLocTheoDoanhSo.add(radNhanVienCoDoanhSoNhoNhat);

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

    private void dungPnlNoiDungTKDoanhThuTheoNhanVien(){
        pnlNoiDungTKDoanhThuTheoNhanVien.setBackground(bgrPnlChinh);
        pnlNoiDungTKDoanhThuTheoNhanVien.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungTKDoanhThuTheoNhanVien.setPreferredSize(dimPnlNoiDungTKDoanhThuTheoNhanVien);

        dungPnlThanhCongCuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoNhanVien.add(pnlThanhCongCuTKDoanhThuTheoNhanVien);

        dungScrChuaTblDuLieuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoNhanVien.add(scrChuaTblDuLieuTKDoanhThuTheoNhanVien);
    }

    private void dungPnlThanhCongCuTKDoanhThuTheoHDBH(){
        pnlThanhCongCuTKDoanhThuTheoNhanVien.setBackground(bgrPnlChinh);
        pnlThanhCongCuTKDoanhThuTheoNhanVien.setPreferredSize(dimPnlThanhCongCuTKDoanhThuTheoNhanVien);
        pnlThanhCongCuTKDoanhThuTheoNhanVien.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 3));

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiemTrenTblTKDoanhThuTheoNhanVien,
                "Tìm kiếm",
                dimTxtTimKiemHDBH
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiemTrenTblTKDoanhThuTheoNhanVien);
        pnlThanhCongCuTKDoanhThuTheoNhanVien.add(txtTimKiemTrenTblTKDoanhThuTheoNhanVien);

        dungPnlHopCongCuTKDoanhThuTheoHDBH();
        pnlThanhCongCuTKDoanhThuTheoNhanVien.add(pnlHopCongCuTKDoanhThuTheoNhanVien);
    }

    private KeyListener cheDoTimKiemTrenDtmTKDTTheoNhanVien = traVeCheDoTimKiemTrenDtmTKDTTheoNhanVien();
    private KeyListener cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH = traVeCheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH();

    private KeyListener traVeCheDoTimKiemTrenDtmTKDTTheoNhanVien(){
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsTKDTTheoNhanVien.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiemTrenTblTKDoanhThuTheoNhanVien.getText().trim()
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
                                "(?i)" + txtTimKiemTrenTblTKDoanhThuTheoNhanVien.getText().trim()
                        )
                );
            }
        };

        return keyListener;
    }

    private void dungPnlHopCongCuTKDoanhThuTheoHDBH(){
        pnlHopCongCuTKDoanhThuTheoNhanVien.setBackground(bgrPnlChinh);
        pnlHopCongCuTKDoanhThuTheoNhanVien.setPreferredSize(dimPnlHopCongCuTKDoanhThuTheoNhanVien);
        pnlHopCongCuTKDoanhThuTheoNhanVien.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatTKDoanhThuTheoNhanVienRaFile,
                bgrBtnXuatDuLieuRaFile,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnXuatTKDoanhThuTheoNhanVienRaExcel
        );
        datHanhDongChoBtnXuatRaExcel();
        pnlHopCongCuTKDoanhThuTheoNhanVien.add(btnXuatTKDoanhThuTheoNhanVienRaFile);

        dungPmnXuatData();
    }

    private void dungScrChuaTblDuLieuTKDoanhThuTheoHDBH(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDuLieuTKDoanhThuTheoNhanVien,
                dimScrChuaTblDuLieuTKDoanhThuTheoNhanVien,
                 null
        );
        datHanhDongChoTblDuLieuThongKeDoanhThuTheoNhanVien();

        scrChuaTblDuLieuTKDoanhThuTheoNhanVien.getViewport().setBackground(bgrMacDinh);
        scrChuaTblDuLieuTKDoanhThuTheoNhanVien.setPreferredSize(dimScrChuaTblDuLieuTKDoanhThuTheoNhanVien);
    }

    /**
     * <p>Can le, dat khoang cach cho cac cot cua tbl</p>
     * @param isDtmTKDoanhThuTheoHDBH: <li>true: tbl.getTableModel() = dtmDuLieuTKDoanhThuTheoHDBH</li>
     */
    private void canLeChoDuLieuTrongTblTKDTTheoNhanVien(boolean isDtmTKDoanhThuTheoHDBH){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblDuLieuTKDoanhThuTheoNhanVien.getColumnModel();

        if (isDtmTKDoanhThuTheoHDBH){
            tcm.getColumn(3).setCellRenderer(rightRenderer);
            tcm.getColumn(4).setCellRenderer(rightRenderer);
            tcm.getColumn(5).setCellRenderer(rightRenderer);
            tcm.getColumn(6).setCellRenderer(rightRenderer);

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
            tcm.getColumn(4).setCellRenderer(rightRenderer);
            tcm.getColumn(5).setCellRenderer(rightRenderer);
            tcm.getColumn(6).setCellRenderer(rightRenderer);

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

    private void datHanhDongChoTblDuLieuThongKeDoanhThuTheoNhanVien(){
        tblDuLieuTKDoanhThuTheoNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && e.getClickCount() == 2){
                    if (radLocTheoMoiNhanVien.isSelected()){
                        int row = tblDuLieuTKDoanhThuTheoNhanVien.getSelectedRow();

                        if (
                                row != -1 &&
                                !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                        tblDuLieuTKDoanhThuTheoNhanVien,
                                        row
                                )
                        ){
                            String maNV = tblDuLieuTKDoanhThuTheoNhanVien.getValueAt(
                                    row, 0
                            ).toString();

                            NhanVien nhanVien = NhanVienDAO.timNhanVienTheoMa(maNV);

                            SwingUtilities.invokeLater(() -> {
                                GDXemTTNhanVien gdXemTTNhanVien = GDXemTTNhanVien.getGdXemTTNhanVien();

                                GDXemTTNhanVien.hienThiThongTinNhanVienLenTxt(nhanVien);

                                gdXemTTNhanVien.setVisible(true);
                            });
                        }
                    }
                }
            }
        });
    }

    private void datHanhDongChoBtnXuatRaExcel(){
        btnXuatTKDoanhThuTheoNhanVienRaFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pmnXuatData.show(
                        btnXuatTKDoanhThuTheoNhanVienRaFile,
                        0,
                        btnXuatTKDoanhThuTheoNhanVienRaFile.getHeight()
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
                if (radLocTheoMoiNhanVien.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoNhanVien,
                            true,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TAT_CA_NHAN_VIEN,
                            ""
                    );
                }
                else if (radLocTheoTungNhanVien.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoNhanVien,
                            true,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TUNG_NHAN_VIEN,
                            "NV-" + txtLocTheoTungNhanVien.getText().trim()
                    );
                }
            }
        });
    }

    private void datHanhDongChoMniXuatPDF(){
        mniXuatPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radLocTheoMoiNhanVien.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoNhanVien,
                            false,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TAT_CA_NHAN_VIEN,
                            ""
                    );
                }
                else if (radLocTheoTungNhanVien.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoNhanVien,
                            false,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TUNG_NHAN_VIEN,
                            "NV-" + txtLocTheoTungNhanVien.getText().trim()
                    );
                }
            }
        });
    }

    private static final List<JRadioButton> btnsLocTheoDoiTuong = Arrays.asList(radLocTheoMoiNhanVien, radLocTheoTungNhanVien);
    private static final List<JRadioButton> btnsLocTheoGiaTien =
            Arrays.asList(radTatCaMucTien, radDuoi1tr, radTu1trDen3tr, radTu3trDen7tr, radTren7tr);
    private static final List<JRadioButton> btnsLocTheoDoanhThu =
            Arrays.asList(radTatCaDoanhSo, radNhanVienCoDoanhSoLonNhat, radNhanVienCoDoanhSoNhoNhat, radDuoi30tr, radTren30tr);
    private static final List<JRadioButton> btnsLocTheoThoiGian = Arrays.asList(radLocTheoMocThoiGian, radLocTuyChonThoiGian);

    /**
     * @author Hiếu
     * <p><i>
     * Hàm này có chức năng tên cho các radio button <br>
     * và thêm sự kiện cho tất cả các radio button đó
     * </i></p>
     */
    public void datHanhDongChoRadioButtons() {
        radLocTheoMoiNhanVien.setName("MoiNhanVien");
        radLocTheoTungNhanVien.setName("TungNhanVien");

        radTatCaMucTien.setName("TatCaMucTien");
        radDuoi1tr.setName("Duoi1tr");
        radTu1trDen3tr.setName("1trDen3tr");
        radTu3trDen7tr.setName("3trDen7tr");
        radTren7tr.setName("Tren7tr");

        radTatCaDoanhSo.setName("TatCaDoanhThu");
        radNhanVienCoDoanhSoLonNhat.setName("DoanhThuLonNhat");
        radNhanVienCoDoanhSoNhoNhat.setName("DoanhThuNhoNhat");
        radDuoi30tr.setName("DoanhThuDuoi30tr");
        radTren30tr.setName("DoanhThuTren30tr");

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
                if(btn.getName().equals("TungNhanVien")) {
                    locTheoDoiTuong.set(1);
                } else if(btn.getName().equals("MoiNhanVien"))
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
                    else if(btn.getName().equals("DoanhThuDuoi30tr"))
                        locTheoDoanhThu.set(2);
                    else if(btn.getName().equals("DoanhThuTren30tr"))
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
                if(!txtLocTheoTungNhanVien.getText().equals("Tìm kiếm NV") && !txtLocTheoTungNhanVien.getText().isEmpty())
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void xoaDuLieuTheoBang() {
        if(radLocTheoMoiNhanVien.isSelected()) {
            dtmTKDTTheoNhanVien.setRowCount(0);
            tblDuLieuTKDoanhThuTheoNhanVien.setModel(dtmTKDTTheoNhanVien);
            tblDuLieuTKDoanhThuTheoNhanVien.setRowSorter(trsTKDTTheoNhanVien);
            canLeChoDuLieuTrongTblTKDTTheoNhanVien(false);
        }
        else if(radLocTheoTungNhanVien.isSelected()) {
            dtmDuLieuTKDoanhThuTheoHDBH.setRowCount(0);
            tblDuLieuTKDoanhThuTheoNhanVien.setModel(dtmDuLieuTKDoanhThuTheoHDBH);
            tblDuLieuTKDoanhThuTheoNhanVien.setRowSorter(trsDuLieuTKDoanhThuTheoHDBH);
            canLeChoDuLieuTrongTblTKDTTheoNhanVien(true);
        }
    }

    private void chuyenDuLieuSangTableTuongUng(ResultSet res) throws SQLException {
        if(radLocTheoMoiNhanVien.isSelected()) {
            AtomicInteger tongSoHoaDon = new AtomicInteger(0);
            AtomicInteger tongSLSP = new AtomicInteger(0);
            AtomicReference<Double> tongDoanhSo = new AtomicReference<>(0.0);

            while(res.next()){
                dtmTKDTTheoNhanVien.addRow(chuyenDuLieuSangTKTheoMoiNV(res));

                tongSoHoaDon.set(tongSoHoaDon.get() + res.getInt("soHoaDon"));
                tongSLSP.set(tongSLSP.get() +  res.getInt("soLuongSanPham"));
                tongDoanhSo.set(tongDoanhSo.get() + res.getDouble("doanhThu"));
            }

            themDongTongKetKhiRadMoiNhanVienDuocChon(tongSoHoaDon.get(), tongSLSP.get(), tongDoanhSo.get());
        }
        else if(radLocTheoTungNhanVien.isSelected()) {
            AtomicInteger tongSLSP = new AtomicInteger(0);
            AtomicReference<Double> tongDoanhThuChuaThue = new AtomicReference<>(0.0);
            AtomicReference<Double> tongVAT = new AtomicReference<>(0.0);
            AtomicReference<Double> tongTien = new AtomicReference<>(0.0);

            while(res.next()){
                dtmDuLieuTKDoanhThuTheoHDBH.addRow(chuyenDuLieuSangTKTheoTungNV(res));

                tongSLSP.set(tongSLSP.get() + res.getInt("tongSLSP"));
                tongDoanhThuChuaThue.set(tongDoanhThuChuaThue.get() + res.getDouble("thanhTienChuaThue"));
                tongVAT.set(tongVAT.get() + res.getDouble("thueGTGT"));
                tongTien.set(tongTien.get() + res.getDouble("tongTien"));
            }

            themDongTongKetKhiRadTungNhanVienDuocChon(
                    tongSLSP.get(),
                    tongDoanhThuChuaThue.get(),
                    tongVAT.get(),
                    tongTien.get()
            );
        }
    }

    private void themDongTongKetKhiRadMoiNhanVienDuocChon(int soHoaDon, int slsp, double doanhThu){
        dtmTKDTTheoNhanVien.insertRow(
                0,
                new Object[]{
                        "Tổng cộng:",
                        dtmTKDTTheoNhanVien.getRowCount() + " nhân viên",
                        "",
                        "",
                        soHoaDon,
                        slsp,
                        nf.format(doanhThu)
                }
        );
    }

    private void themDongTongKetKhiRadTungNhanVienDuocChon(int slsp, double tongDoanhThuChuaThue, double tongVAT, double tongTien){
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

    private Object[] chuyenDuLieuSangTKTheoMoiNV(ResultSet res) throws SQLException {
        return new Object[] {
                res.getString("maNhanVien"),
                MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                MaHoaDuLieu.giaiMa(res.getString("soDT")),
                res.getBoolean("capBac") ? "Quản lý" : "Nhân viên",
                res.getInt("soHoaDon"),
                res.getInt("soLuongSanPham"),
                dinhDangTien(res.getDouble("doanhThu"))
        };
    }

    private Object[] chuyenDuLieuSangTKTheoTungNV(ResultSet res) throws SQLException {
        return new Object[] {
                res.getInt("maHDBH"),
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
                    query += String.format("where %s group by nv.maNhanVien, nv.hoTen, nv.soDT, nv.capBac %s",
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()),
                            sinhCauTruyVanLocTheoDoanhThu(locTheoTien));

                } else if(locTheoNgay == 0) {
                    query += String.format("where %s group by nv.maNhanVien, nv.hoTen, nv.soDT, nv.capBac %s",
                            sinhCauTruyVanLocTheoTuyChonThoiGian(),
                            sinhCauTruyVanLocTheoDoanhThu(locTheoTien));
                }
                if(locTheoTien == 0)
                    query = String.format("Select top 3 %s", query);
                else if(locTheoTien == 1)
                    query = String.format("Select top 5 %s", query);
                else
                    query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                if(locTheoNgay == 0) {
                    query += String.format("where hd.maNhanVienLapHDBH = '%s' and %s and %s",
                            txtLocTheoTungNhanVien.getText().trim(),
                            sinhCauTruyVanLocTheoTongTien(locTheoTien),
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }
                else if(locTheoNgay == 1) {
                    query += String.format("where hd.maNhanVienLapHDBH = '%s' and %s and %s",
                            txtLocTheoTungNhanVien.getText().trim(),
                            sinhCauTruyVanLocTheoTongTien(locTheoTien),
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));
                }
            }
        }
        else if(locTheoTien == -1 && locTheoNgay != -1) {
            if(doiTuong == 0) {
                if(locTheoNgay == 1) {
                    query += String.format("where %s group by nv.maNhanVien, nv.hoTen, nv.soDT, nv.capBac",
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));

                } else if(locTheoNgay == 0) {
                    query += String.format("where %s group by nv.maNhanVien, nv.hoTen, nv.soDT, nv.capBac",
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }

                query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                if(locTheoNgay == 0) {
                    query += String.format("where hd.maNhanVienLapHDBH = '%s' and %s",
                            txtLocTheoTungNhanVien.getText().trim(),
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }
                else if(locTheoNgay == 1) {
                    query += String.format("where hd.maNhanVienLapHDBH = '%s' and %s",
                            txtLocTheoTungNhanVien.getText().trim(),
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));
                }
            }
        }
        else if(locTheoTien != -1 && locTheoNgay == -1) {
            if(doiTuong == 0) {
                query += String.format(" group by nv.maNhanVien, nv.hoTen, nv.soDT, nv.capBac %s",
                            sinhCauTruyVanLocTheoDoanhThu(locTheoTien));
                if(locTheoTien == 0)
                    query = String.format("Select top 3 %s", query);
                else if(locTheoTien == 1)
                    query = String.format("Select top 5 %s", query);
                else
                    query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                query += String.format("where hd.maNhanVienLapHDBH = '%s' and %s",
                        txtLocTheoTungNhanVien.getText().trim(),
                        sinhCauTruyVanLocTheoTongTien(locTheoTien));
            }
        }
        else if(locTheoTien == -1 && locTheoNgay == -1) {
            if(doiTuong == 0)
                query = String.format("Select %s group by nv.maNhanVien, nv.hoTen, nv.soDT, nv.capBac", query);
            else if(doiTuong == 1)
                query += String.format("where hd.maNhanVienLapHDBH = %s", txtLocTheoTungNhanVien.getText().trim());
        }

        return query;
    }

    private String sinhCauTruyVanTheoDoiTuong(int doiTuong) {
        String query = "";

        if(doiTuong == 0) {
            query += " nv.maNhanVien, nv.hoTen, nv.soDT, nv.capBac, count(hd.maHDBH) as soHoaDon, " +
                    "sum(hd.tongSLSP) as soLuongSanPham, sum(hd.tongTien) as doanhThu " +
                    "from HoaDonBanHang hd join NhanVien nv on nv.maNhanVien = hd.maNhanVienLapHDBH ";
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
            query = "order by doanhThu desc";
        else if(locTheoDoanhThu == 1)
            query = "order by doanhThu asc";
        else if(locTheoDoanhThu == 2)
            query = "having sum(hd.tongTien) < 30000000";
        else if(locTheoDoanhThu == 3)
            query = "having sum(hd.tongTien) > 30000000";

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