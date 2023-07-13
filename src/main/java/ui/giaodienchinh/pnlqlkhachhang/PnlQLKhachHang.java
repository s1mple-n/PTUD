package ui.giaodienchinh.pnlqlkhachhang;

import connectDB.KetNoiCSDL;
import dao.KhachHangDAO;
import entity.KhachHang;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlkhachhang.gdcapnhatthongtinkhachhang.GDCapNhatThongTinKhachHang;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PnlQLKhachHang implements IDSBienPnlQLKhachHang, ActionListener{
    private static JPanel pnlQLKhachHang = null;
    
    private JPanel dungPnlQLKhachHang(){
        pnlQLKH.setBackground(bgrPnlChinh);
        pnlQLKH.setPreferredSize(dimPnlQLKH);
        pnlQLKH.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        dungPnlThanhTienIch();
        pnlQLKH.add(pnlThanhTienIch);

        dungPnlNoiDungQLKHChinh();
        pnlQLKH.add(pnlNoiDungQLKHChinh);

        capNhatDSKhachHang();

        return pnlQLKH;
    }

    public static JPanel getPnlQLKhachHang() {
        if (pnlQLKhachHang == null)
            pnlQLKhachHang = new PnlQLKhachHang().dungPnlQLKhachHang();
        return pnlQLKhachHang;
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
        pnlThanhTienIch.setBackground(bgrMacDinh);
        pnlThanhTienIch.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThanhTienIch.setPreferredSize(dimPnlThanhTienIch);

        dungPnlLocTheoGioiTinh();
        pnlThanhTienIch.add(pnlLocTheoGioiTinh);

        dungPnlLocTheoThoiGian();
        pnlThanhTienIch.add(pnlLocTheoThoiGian);

        datHanhDongChoRadioButtons();
    }

    private void dungPnlLocTheoGioiTinh(){
        pnlLocTheoGioiTinh.setBackground(bgrMacDinh);
        pnlLocTheoGioiTinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoGioiTinh.setPreferredSize(dimPnlLocTheoGioiTinh);

        CacHamDungSan.dungPnlTieuDeLoc(
                pnlTieuDeLocTheoGioiTinh,
                lblTieuDeLocTheoGioiTinh
        );
        pnlLocTheoGioiTinh.add(pnlTieuDeLocTheoGioiTinh);

        dungPnlLuaChonLocTheoGioiTinh();
        pnlLocTheoGioiTinh.add(pnlLuaChonLocTheoGioiTinh);
    }

    private void dungPnlLuaChonLocTheoGioiTinh(){
        pnlLuaChonLocTheoGioiTinh.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoGioiTinh.setLayout(new FlowLayout(
                FlowLayout.LEADING,
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));
        pnlLuaChonLocTheoGioiTinh.setPreferredSize(dimPnlLuaChonLocTheoGioiTinh);

        tapHopThanhVienCuaBngLocTheoGioiTinh();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaCacGioi);
        pnlLuaChonLocTheoGioiTinh.add(radTatCaCacGioi);

        CacHamDungSan.datThuocTinhChoCacRad(radNam);
        pnlLuaChonLocTheoGioiTinh.add(radNam);

        CacHamDungSan.datThuocTinhChoCacRad(radNu);
        pnlLuaChonLocTheoGioiTinh.add(radNu);
    }

    private void tapHopThanhVienCuaBngLocTheoGioiTinh(){
        bngLocTheoGioiTinh.add(radTatCaCacGioi);
        bngLocTheoGioiTinh.add(radNam);
        bngLocTheoGioiTinh.add(radNu);
    }

    private void dungPnlLocTheoThoiGian(){
        pnlLocTheoThoiGian.setBackground(bgrMacDinh);
        pnlLocTheoThoiGian.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnlLocTheoThoiGian.setPreferredSize(dimPnlLocTheoThoiGian);

        CacHamDungSan.dungPnlTieuDeLoc(
                pnlTieuDeLocTheoThoiGian,
                lblLocTheoThoiGian
        );
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
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));

        tapHopThanhVienCuaBngLocTheoThoiGian();

        dungPnlLocTheoCacMocThoiGian();
        pnlLuaChonLocTheoThoiGian.add(pnlLocTheoMocThoiGian);

        CacHamDungSan.datThuocTinhChoCacRad(radTuyChonThoiGian);
        datHanhDongChoRadTuyChonThoiGian();
        pnlLuaChonLocTheoThoiGian.add(radTuyChonThoiGian);
    }

    private void dungPnlLocTheoCacMocThoiGian(){
        pnlLocTheoMocThoiGian.setLayout(new BoxLayout(pnlLocTheoMocThoiGian, BoxLayout.X_AXIS));
        pnlLocTheoMocThoiGian.setBackground(bgrMacDinh);

        cbbCacMocThoiGian.setEnabled(false);
        CacHamDungSan.datThuocTinhChoCacRad(radLocTheoMocThoiGian);
        datHanhDongChoRadLocTheoMocThoiGian();
        pnlLocTheoMocThoiGian.add(radLocTheoMocThoiGian);

        pnlLocTheoMocThoiGian.add(Box.createHorizontalStrut(4));
        cbbCacMocThoiGian.setPreferredSize(dimCbbCacMocThoiGian);
        cbbCacMocThoiGian.setFont(fntMacDinh);
        cbbCacMocThoiGian.addActionListener((e) -> {
            radLocTheoMocThoiGian.doClick();
        });
        pnlLocTheoMocThoiGian.add(cbbCacMocThoiGian);
    }

    private void tapHopThanhVienCuaBngLocTheoThoiGian(){
        bngLocTheoThoiGian.add(radLocTheoMocThoiGian);
        bngLocTheoThoiGian.add(radTuyChonThoiGian);
    }

    private void datHanhDongChoRadLocTheoMocThoiGian(){
        radLocTheoMocThoiGian.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cbbCacMocThoiGian.setEnabled(true);
                pnlKhungChonThoiGian.setVisible(false);
            }
        });
    }

    private void datHanhDongChoRadTuyChonThoiGian(){
        radTuyChonThoiGian.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cbbCacMocThoiGian.setEnabled(false);
                pnlKhungChonThoiGian.setVisible(true);
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
                pnlNgayBatDau,
                lblNgayBatDau,
                dtpNgayBatDau,
                dimPnlNgayBatDauVaKetThuc,
                dimDatePicker
        );
        pnlKhungChonThoiGian.add(pnlNgayBatDau);

        CacHamDungSan.dungPnlNgayBatDauHoacKetThuc(
                pnlNgayKetThuc,
                lblNgayKetThuc,
                dtpNgayKetThuc,
                dimPnlNgayBatDauVaKetThuc,
                dimDatePicker
        );
        pnlKhungChonThoiGian.add(pnlNgayKetThuc);

        dtpNgayBatDau.addDateChangeListener((e) -> {
            radTuyChonThoiGian.doClick();
        });

        dtpNgayKetThuc.addDateChangeListener((e) -> {
            radTuyChonThoiGian.doClick();
        });
    }

    private void dungPnlNoiDungQLKHChinh(){
        pnlNoiDungQLKHChinh.setBackground(bgrPnlChinh);
        pnlNoiDungQLKHChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungQLKHChinh.setPreferredSize(dimPnlNoiDungQLKHChinh);

        dungPnlThanhCongCu();
        pnlNoiDungQLKHChinh.add(pnlThanhCongCu);

        dungPnlKetQuaTraCuu();
        pnlNoiDungQLKHChinh.add(pnlKetQuaTraCuu);
    }

    private void dungPnlThanhCongCu(){
        pnlThanhCongCu.setBackground(bgrPnlChinh);
        pnlThanhCongCu.setPreferredSize(dimPnlThanhCongCu);
        pnlThanhCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiem,
                "Tìm kiếm",
                dimTxtTimKiem
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiem);
        datHanhDongChoTxtTimKiem();
        txtTimKiem.setToolTipText("Tìm khách hàng theo mã hoặc số điện thoại");
        pnlThanhCongCu.add(txtTimKiem);

        dungPmnCheDoTimKiem();

        dungPnlHopCongCu();
        pnlThanhCongCu.add(pnlHopCongCu);

        dungPmnChuaDSKHTimDuoc();
    }

    private void datHanhDongChoTxtTimKiem(){
        txtTimKiem.addKeyListener(
                cheDoTimKiemTrongCSDL
        );

        txtTimKiem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3){
                    pmnCheDoTimKiem.show(
                            txtTimKiem,
                            e.getX(),
                            e.getY()
                    );
                }
            }
        });
    }

    KeyListener cheDoTimKiemTrongCSDL = traVeCheDoTimKiemTrongCSDL();
    KeyListener cheDoTimKiemTrenTable = traVeCheDoTimKiemTrenTable();

    private KeyListener traVeCheDoTimKiemTrenTable(){
        KeyListener cheDoTimKiemTrenTable = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsDuLieuTraCuuDuoc.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiem.getText().trim()
                        )
                );
            }
        };

        return cheDoTimKiemTrenTable;
    }

    private KeyListener traVeCheDoTimKiemTrongCSDL(){
        KeyListener cheDoTimKiemTrongCSDL = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtTimKiem.getText().trim().toLowerCase();

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
                                txtTimKiem,
                                0,
                                txtTimKiem.getHeight()
                        );

                        txtTimKiem.requestFocus();
                    }
                    else{
                        tblDSKHTimDuoc.setEnabled(false);

                        dtmDSKHTimDuoc.setRowCount(0);

                        Object[] thongBaoKhongTimRaKHNao = {
                                "",
                                "( ^ _ ^ )",
                                ""
                        };

                        dtmDSKHTimDuoc.addRow(thongBaoKhongTimRaKHNao);

                        datKichThuocChoPmnChuaDSKHTimDuoc(2);
                    }
                }
            }
        };

        return cheDoTimKiemTrongCSDL;
    }

    private void dungPmnCheDoTimKiem(){
        pmnCheDoTimKiem.setBorder(BorderFactory.createEmptyBorder());

        mniTimKiemTrongCSDL.setFont(fntMacDinh);
        mniTimKiemTrongCSDL.setIcon(
                imiIconTimKiemTrongCSDL
        );
        datHanhDongChoMniTimKiemTrongCSDL();
        pmnCheDoTimKiem.add(mniTimKiemTrongCSDL);

        mniTimKiemTrenTable.setFont(fntMacDinh);
        mniTimKiemTrenTable.setIcon(
                imiIconTimKiemTrenTrenTable
        );
        datHanhDongChoMniTimKiemTrenTable();
        pmnCheDoTimKiem.add(mniTimKiemTrenTable);
    }

    private void datHanhDongChoMniTimKiemTrongCSDL(){
        mniTimKiemTrongCSDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTimKiem.setText("");

                txtTimKiem.removeKeyListener(cheDoTimKiemTrenTable);

                txtTimKiem.addKeyListener(cheDoTimKiemTrongCSDL);

                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(
                        txtTimKiem,
                        ""
                );
            }
        });
    }

    private void datHanhDongChoMniTimKiemTrenTable(){
        mniTimKiemTrenTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTimKiem.setText("");

                txtTimKiem.removeKeyListener(cheDoTimKiemTrongCSDL);

                txtTimKiem.addKeyListener(cheDoTimKiemTrenTable);

                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(
                        txtTimKiem,
                        ""
                );
            }
        });
    }

    private void dungPmnChuaDSKHTimDuoc(){
        pmnChuaDSKHTimDuoc.setBackground(bgrMacDinh);
        pmnChuaDSKHTimDuoc.setBorder(BorderFactory.createEmptyBorder());
        pmnChuaDSKHTimDuoc.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 1, false
                )
        );

        dungTblChuaDSKHTimDuoc();
        datHanhDongChoTblDSKHTimDuoc();
        pmnChuaDSKHTimDuoc.add(tblDSKHTimDuoc.getTableHeader());
        pmnChuaDSKHTimDuoc.add(tblDSKHTimDuoc);
    }

    private void dungTblChuaDSKHTimDuoc(){
        tblDSKHTimDuoc.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblDSKHTimDuoc.setFont(fntMacDinh);
        tblDSKHTimDuoc.setDefaultEditor(Object.class, null);

        tblDSKHTimDuoc.getTableHeader().setBackground(bgrTieuDeTable);
        tblDSKHTimDuoc.getTableHeader().setPreferredSize(new Dimension(
                chieuRongPmnChuaDSKHTimDuoc,
                chieuCaoHangDuLieuTrongTable
        ));
        tblDSKHTimDuoc.getTableHeader().setFont(fntMacDinh);

        TableColumnModel tcm = tblDSKHTimDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(60);
        tcm.getColumn(0).setMaxWidth(60);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
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
                int row = tblDSKHTimDuoc.getSelectedRow();

                if (row != -1){
                    int maKH = (int) tblDSKHTimDuoc.getValueAt(
                            row, 0
                    );

                    SwingUtilities.invokeLater(() -> {
                        GDXemThongTinKhachHang gdXemThongTinKhachHang = GDXemThongTinKhachHang.getGdXemThongTinKhachHang();

                        gdXemThongTinKhachHang.cungCapMaKhachHang(maKH);

                        gdXemThongTinKhachHang.setVisible(true);
                    });
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
                    KhachHangTimDuoc kh = new KhachHangTimDuoc(
                            V.getMaKH(),
                            V.getSoDT(),
                            V.getHoTen()
                    );

                    ketQuaTimKiemKH.add(kh);

                    coHieu.getAndIncrement();
                }
            }
        });

        return ketQuaTimKiemKH;
    }

    private void dungPnlHopCongCu(){
        pnlHopCongCu.setBackground(bgrPnlChinh);
        pnlHopCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnlHopCongCu.setPreferredSize(dimPnlHopCongCu);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatDuLieuTrongTableRaFile,
                bgrBtnXuatDuLieuRaFile,
                bgrMacDinh,
                dimBtnThanhCongCu
        );
        datHanhDongChoBtnXuatDuLieuRaFile();
        pnlHopCongCu.add(btnXuatDuLieuTrongTableRaFile);

        dungPmnXuatData();
    }

    private void datHanhDongChoBtnXuatDuLieuRaFile(){
        btnXuatDuLieuTrongTableRaFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pmnXuatData.show(
                        btnXuatDuLieuTrongTableRaFile,
                        0,
                        btnXuatDuLieuTrongTableRaFile.getHeight()
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
                TienIch.xuatDataTrongTableRaFile(
                        tblDuLieuTraCuuDuoc,
                        true,
                        IDSBienMacDinh.THONG_KE_KHACH_HANG,
                        ""
                );
            }
        });
    }

    private void datHanhDongChoMniXuatPDF(){
        mniXuatPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TienIch.xuatDataTrongTableRaFile(
                        tblDuLieuTraCuuDuoc,
                        false,
                        IDSBienMacDinh.THONG_KE_KHACH_HANG,
                        ""
                );
            }
        });
    }

    private void dungPnlKetQuaTraCuu(){
        pnlKetQuaTraCuu.setBackground(bgrPnlChinh);
        pnlKetQuaTraCuu.setPreferredSize(dimPnlKetQuaTraCuu);
        pnlKetQuaTraCuu.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        dungScrChuaTableDuLieuTraCuuDuoc();
        pnlKetQuaTraCuu.add(scrChuaTableDuLieuTraCuuDuoc);
    }

    private void dungScrChuaTableDuLieuTraCuuDuoc(){
        datThuocTinhChoTblDuLieuTraCuuDuoc();
        hienThiGDXemTTKHKhiDoubleClickChuotTraiVaoHangDuocChon();
        hienThiGDCapNhatTTKHKhiDoubleClickChuotPhaiVaoHangDuocChon();

        scrChuaTableDuLieuTraCuuDuoc.setPreferredSize(dimScrChuaTableDulieuTraCuuDuoc);
        scrChuaTableDuLieuTraCuuDuoc.getViewport().setBackground(bgrMacDinh);
    }

    private void datThuocTinhChoTblDuLieuTraCuuDuoc(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDuLieuTraCuuDuoc,
                dimScrChuaTableDulieuTraCuuDuoc,
                trsDuLieuTraCuuDuoc
        );

        canLeChoTableTuyTheoDangDuLieu();

        datThuocTinhChoTieuDeCuaTable();

        datChieuRongChoCacCotCuaTable();
    }

    private void datThuocTinhChoTieuDeCuaTable(){
        tblDuLieuTraCuuDuoc.getTableHeader().setPreferredSize(
                new Dimension(
                        dimScrChuaTableDulieuTraCuuDuoc.width,
                        chieuCaoHangDuLieuTrongTable
                )
        );

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tblDuLieuTraCuuDuoc.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        tblDuLieuTraCuuDuoc.getTableHeader().setOpaque(false);
        tblDuLieuTraCuuDuoc.getTableHeader().setBackground(bgrTieuDeTable);
    }

    private void canLeChoTableTuyTheoDangDuLieu(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel tcm = tblDuLieuTraCuuDuoc.getColumnModel();

        tcm.getColumn(0).setCellRenderer(centerRenderer);
        tcm.getColumn(4).setCellRenderer(centerRenderer);
        tcm.getColumn(5).setCellRenderer(centerRenderer);
    }

    private void datChieuRongChoCacCotCuaTable(){
        TableColumnModel tcm = tblDuLieuTraCuuDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(0).setMaxWidth(100);

        tcm.getColumn(1).setPreferredWidth(180);
        tcm.getColumn(1).setMaxWidth(180);

        tcm.getColumn(2).setPreferredWidth(110);
        tcm.getColumn(2).setMaxWidth(110);

        tcm.getColumn(4).setPreferredWidth(70);
        tcm.getColumn(4).setMaxWidth(70);

        tcm.getColumn(5).setPreferredWidth(110);
        tcm.getColumn(5).setMaxWidth(110);
    }

    /*
    * @author Hiếu
    * Code xử lí chức năng lọc dữ liệu trên bảng
    * List btnsLocTheoGioiTinh và btnsLocTheoThoiGianMua
    * mục đích để thêm sự kiện click cho tất cả các radio button nhanh chóng
    * */
    private List<JRadioButton> btnsLocTheoGioiTinh = Arrays.asList(radTatCaCacGioi, radNam, radNu);
    private List<JRadioButton> btnsLocTheoThoiGianMua = Arrays.asList(radLocTheoMocThoiGian, radTuyChonThoiGian);

    /**
     * @author Hiếu
     * <p><i>
     * Hàm này có chức năng tên cho các radio button
     * và thêm sự kiện cho tất cả các radio button đó
     * </i></p>
     */
    public void datHanhDongChoRadioButtons() {
        radTatCaCacGioi.setName("btnTatCaGioi");
        radNam.setName("Nam");
        radNu.setName("Nu");

        radLocTheoMocThoiGian.setName("LocTheoMoc");
        radTuyChonThoiGian.setName("TuyChon");

        btnsLocTheoGioiTinh.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoThoiGianMua.forEach(btn -> {
            btn.addActionListener(this);
        });
    }

    /**
     * @author Hiếu
     * <p><i>Hàm có chức năng xử lý sự kiện cho các nút lọc</i></p>
     * @param e: Sự kiện được bắt
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoGioiTinh = new AtomicInteger(-1);
        AtomicInteger locTheoNgay = new AtomicInteger(-1);

        btnsLocTheoGioiTinh.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("Nam")) {
                    locTheoGioiTinh.set(1);
                } else if(btn.getName().equals("Nu"))
                    locTheoGioiTinh.set(0);
            }
        });

        btnsLocTheoThoiGianMua.forEach(btn -> {
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

        capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoGioiTinh.get(), locTheoNgay.get()));
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Hàm có chức năng sinh câu lệnh cơ sở dữ liệu tương ứng
     * </i></p>
     * @param locGioiTinh <br> -1 -> lọc theo tất cả giới tính <br> 0 -> lọc theo Nữ <br> 1 -> lọc theo Nam
     * @param locTheoNgay <br> -1 -> không lọc theo thời gian <br> 1 -> lọc theo mốc thời gian <br> 0 -> lọc theo ngày được chọn
     * @return Câu lệnh sql tương ứng
     */
    private String sinhCauTruyVanCSDL(int locGioiTinh, int locTheoNgay) {
        String query = "Select * from KhachHang ";

        if(locGioiTinh != -1 && locTheoNgay != -1) {
            if(locGioiTinh == 1 && locTheoNgay == 1) {
                query += sinhCauTruyVanLocTheoMocThoiGian(1, cbbCacMocThoiGian.getSelectedIndex());
            }
            else if(locGioiTinh == 1 && locTheoNgay == 0) {
                if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate()))
                    query += String.format("where gioiTinh = %d and cast (ngayThem as date) = '%s'",
                            1, TienIch.dinhDangNgay(dtpNgayKetThuc.getDate(), "yyyy-MM-dd"));
                else
                    query += String.format("where gioiTinh = %d and ngayThem >= '%s' and ngayThem < '%s'", 1,
                            TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                            TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));
            }
            else if(locGioiTinh == 0 && locTheoNgay == 1) {
                query += sinhCauTruyVanLocTheoMocThoiGian(0, cbbCacMocThoiGian.getSelectedIndex());
            }
            else if(locGioiTinh == 0 && locTheoNgay == 0) {
                if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate()))
                    query += String.format("where gioiTinh = %d and cast (ngayThem as date) = '%s'",
                            0, TienIch.dinhDangNgay(dtpNgayKetThuc.getDate(), "yyyy-MM-dd"));
                else
                    query += String.format("where gioiTinh = %d and ngayThem >= '%s' and ngayThem < '%s'", 0,
                            TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                            TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));
            }
        }
        else if(locGioiTinh != -1 && locTheoNgay == -1) {
            if(locGioiTinh == 1)
                query += "where gioiTinh = 1";
            else if(locGioiTinh == 0)
                query += "where gioiTinh = 0";
        }
        else if(locGioiTinh== -1 && locTheoNgay != -1) {
            if(locTheoNgay == 0) {
                if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate()))
                    query += String.format("where cast (ngayThem as date) = '%s'",
                            TienIch.dinhDangNgay(dtpNgayKetThuc.getDate(), "yyyy-MM-dd"));
                else
                    query += String.format("where ngayThem >= '%s' and ngayThem < '%s'",
                            TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                            TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));
            } else if(locTheoNgay == 1) {
                query += sinhCauTruyVanLocTheoMocThoiGian(-1, cbbCacMocThoiGian.getSelectedIndex());
            }
        }

        return query;
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Hàm có chức năng sinh câu truy vấn để lọc dữ liệu theo mốc thời gian được chọn
     * </i></p>
     * @param gioiTinh <br>-1 -> không lọc theo giới tính <br> 0 -> lọc theo Nữ <br> 1 -> lọc theo Nam
     * @param cheDoLocMocThoiGian <br> 0 -> Lọc theo ngày hôm nay <br> 1 -> Lọc theo tuần này <br> 2 -> Lọc theo tháng này
     * @return
     */
    private String sinhCauTruyVanLocTheoMocThoiGian(int gioiTinh, int cheDoLocMocThoiGian) {
        /**
         * gioiTinh != -1 có nghĩa người dùng muốn lọc theo giới tính, nên điền điều kiện của <br>
         * câu truy vấn sẽ gồm where và and. Ngược lại nếu gioiTinh == -1, tức người dùng không muốn <br>
         * lọc theo giới tính nên câu truy vấn chỉ có where
         * <br> Để hình dung rõ hơn, hãy uncomment câu lệnh ở dòng 946
         */
        String query = "";
        String dieuKien = "where";
        if(gioiTinh != -1) {
            query = String.format("where gioiTinh = %d ", gioiTinh);
            dieuKien = "and";
        }

        LocalDate homNay = LocalDate.now();
        if(cheDoLocMocThoiGian == 0) {
            query += String.format("%s cast(ngayThem as date) = '%s'", dieuKien, TienIch.dinhDangNgay(homNay, "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 1) {
            query += String.format("%s ngayThem >= '%s' and ngayThem < '%s'", dieuKien, TienIch.getNgayDauTrongTuan(),
                    TienIch.dinhDangNgay(homNay.plusDays(1), "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 2) {
            query += String.format("%s month(ngayThem) = '%s' and year(ngayThem) = '%s'",
                    dieuKien, homNay.getMonthValue(), homNay.getYear());
        }

        return query;
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Hàm có chức năng hiển thị thông tin khách hàng lên JTable với những dữ liệu
     *     đã được lọc tương ứng
     * </i></p>
     * @param query: câu query tương ứng
     */
    private void capNhatDuLieuLenTable(String query) {
        try {
            PreparedStatement pState = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ResultSet res = pState.executeQuery();

            if(!res.isBeforeFirst()) {
               CacHamDungSan.hienThiThongBaoKhongCoDuLieuPhuHop();

                dtmDuLieuTraCuuDuoc.setRowCount(0);
                return;
            }

            dtmDuLieuTraCuuDuoc.setRowCount(0);

            while(res.next()) {
                KhachHang kh = new KhachHang(res.getInt("maKH"),
                        MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                        MaHoaDuLieu.giaiMa(res.getString("soDT")),
                        MaHoaDuLieu.giaiMa(res.getString("diaChi")),
                        res.getBoolean("gioiTinh"),
                        res.getTimestamp("ngayThem").toLocalDateTime()
                        );

                dtmDuLieuTraCuuDuoc.addRow(chuyenDuLieuTruocKhiLoadLenTable(kh));
            }

            themDongTongKetKetQuaTimKiemVaoHangSo0CuaTable(tblDuLieuTraCuuDuoc.getRowCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Object[] chuyenDuLieuTruocKhiLoadLenTable(KhachHang kh) throws SQLException {
        return new Object[] {
                KhachHangDAO.dinhDangMaKH(kh.getMaKH()), kh.getHoTen(), kh.getSoDT(),
                kh.getDiaChi(), kh.isNam() ? "Nam" : "Nữ",
                TienIch.dinhDangNgay(kh.getNgayThem().toLocalDate(), "dd-MM-yyyy")
        };
    }

//  Phần này của Tú, lần sau nhớ comment lại nhe
    public static void suaThongTinTrenTableCuaKhachHangVuaDuocCapNhat(int row, String sdtMoi, String diaChiMoi){
        tblDuLieuTraCuuDuoc.setValueAt(
                sdtMoi,
                row,
                2
        );

        tblDuLieuTraCuuDuoc.setValueAt(
                diaChiMoi,
                row,
                3
        );
    }

    private void hienThiGDXemTTKHKhiDoubleClickChuotTraiVaoHangDuocChon(){
        tblDuLieuTraCuuDuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && e.getClickCount() == 2){
                    int hangDuocChon = tblDuLieuTraCuuDuoc.getSelectedRow();

                    if (
                            hangDuocChon != -1 &&
                            !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                    tblDuLieuTraCuuDuoc,
                                    hangDuocChon
                            )
                    ){
                        int maKH = Integer.parseInt(
                                (String) tblDuLieuTraCuuDuoc.getValueAt(
                                        hangDuocChon, 0
                                )
                        );

                        hienThiGDXemTTKhachHang(maKH);
                    }
                }
            }
        });
    }

    private void hienThiGDXemTTKhachHang(int maKH){
        SwingUtilities.invokeLater(() -> {
            GDXemThongTinKhachHang gdXemThongTinKhachHang = GDXemThongTinKhachHang.getGdXemThongTinKhachHang();

            gdXemThongTinKhachHang.cungCapMaKhachHang(maKH);

            gdXemThongTinKhachHang.setVisible(true);
        });
    }

    private void hienThiGDCapNhatTTKHKhiDoubleClickChuotPhaiVaoHangDuocChon(){
        tblDuLieuTraCuuDuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == 3){
                    int hangDuocChon = tblDuLieuTraCuuDuoc.getSelectedRow();

                    if (
                            hangDuocChon != -1 &&
                            !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                    tblDuLieuTraCuuDuoc,
                                    hangDuocChon
                            )
                    ){
                        String maKH = (String) tblDuLieuTraCuuDuoc.getValueAt(
                                hangDuocChon, 0
                        );
                        String hoTenKH = (String) tblDuLieuTraCuuDuoc.getValueAt(
                                hangDuocChon, 1
                        );
                        String sdtCu = (String) tblDuLieuTraCuuDuoc.getValueAt(
                                hangDuocChon, 2
                        );
                        String diaChiCu = (String) tblDuLieuTraCuuDuoc.getValueAt(
                                hangDuocChon, 3
                        );

                        SwingUtilities.invokeLater(() -> {
                            GDCapNhatThongTinKhachHang gd = new GDCapNhatThongTinKhachHang(
                                    hangDuocChon,
                                    maKH,
                                    hoTenKH,
                                    sdtCu,
                                    diaChiCu
                            );
                            gd.setVisible(true);
                        });

                    }
                }
            }
        });
    }

    private void themDongTongKetKetQuaTimKiemVaoHangSo0CuaTable(int slkh){
        Object[] o = {
                "Tổng cộng:",
                slkh + " khách hàng",
                "",
                "",
                "",
                ""
        };

        dtmDuLieuTraCuuDuoc.insertRow(0, o);
    }
}
