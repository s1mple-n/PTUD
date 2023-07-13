package ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheohdbh;

import connectDB.KetNoiCSDL;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlbanhang.PnlQLBanHang;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PnlTKDoanhThuTheoHDBH implements IDSBienPnlTKDoanhThuTheoHDBH, ActionListener {

    public void dungPnlTKDTTheoHoaDonBanHang(JPanel pnlTKDTTheoHDBH){
        pnlTKDTTheoHDBH.setBackground(bgrPnlChinh);
        pnlTKDTTheoHDBH.setPreferredSize(dimTabNoiDung);
        pnlTKDTTheoHDBH.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungPnlThanhTienIchTKDoanhThuTheoHDBH();
        pnlTKDTTheoHDBH.add(pnlThanhTienIchTKDoanhThuTheoHDBH);

        pnlTKDTTheoHDBH.add(Box.createHorizontalStrut(5));

        dungPnlNoiDungTKDoanhThuTheoHDBH();
        pnlTKDTTheoHDBH.add(pnlNoiDungTKDoanhThuTheoHDBH);
    }

    private void dungPnlThanhTienIchTKDoanhThuTheoHDBH(){
        pnlThanhTienIchTKDoanhThuTheoHDBH.setBackground(bgrMacDinh);
        pnlThanhTienIchTKDoanhThuTheoHDBH.setLayout(new BoxLayout(
                pnlThanhTienIchTKDoanhThuTheoHDBH,
                BoxLayout.Y_AXIS
        ));
        pnlThanhTienIchTKDoanhThuTheoHDBH.setPreferredSize(dimPnlThanhTienIchTKDoanhThuTheoHDBH);

        dungPnlLocTheoThoiGianLapHDBH();
        pnlThanhTienIchTKDoanhThuTheoHDBH.add(pnlLocTheoThoiGianLapHDBH);

        dungPnlLocTheoTongTienHDBH();
        pnlThanhTienIchTKDoanhThuTheoHDBH.add(pnlLocTheoTongTienHDBH);

        dungPnlLocTheoGioiTinhKhachHang();
        pnlThanhTienIchTKDoanhThuTheoHDBH.add(pnlLocTheoGioiTinhKhachHang);

        datHanhDongChoRadioButtons();
    }

    private void dungPnlLocTheoThoiGianLapHDBH(){
        pnlLocTheoThoiGianLapHDBH.setBackground(bgrMacDinh);
        pnlLocTheoThoiGianLapHDBH.setPreferredSize(dimPnlLocTheoThoiGianLapHDBH_1);
        pnlLocTheoThoiGianLapHDBH.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoThoiGianLapHDBH, lblTieuDeLocTheoThoiGianLapHDBH);
        pnlLocTheoThoiGianLapHDBH.add(pnlTieuDeLocTheoThoiGianLapHDBH);

        dungPnlLuaChonLocTheoThoiGian();
        pnlLocTheoThoiGianLapHDBH.add(pnlLuaChonLocTheoThoiGianLapHDBH);

        dungPnlKhungChonThoiGian();
        pnlLocTheoThoiGianLapHDBH.add(pnlKhungChonThoiGianLapHDBH);
    }

    private void dungPnlLuaChonLocTheoThoiGian(){
        pnlLuaChonLocTheoThoiGianLapHDBH.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoThoiGianLapHDBH.setPreferredSize(dimPnlLuaChonLocTheoThoiGianLapHDBH);
        pnlLuaChonLocTheoThoiGianLapHDBH.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));

        tapHopThanhVienCuaBngLocTheoThoiGian();

        dungPnlLocTheoCacMocThoiGian();
        pnlLuaChonLocTheoThoiGianLapHDBH.add(pnlLocTheoMocThoiGianLapHDBH);

        CacHamDungSan.datThuocTinhChoCacRad(radTuyChonThoiGianLapHDBH);
        datHanhDongChoRadTuyChonThoiGian();
        pnlLuaChonLocTheoThoiGianLapHDBH.add(radTuyChonThoiGianLapHDBH);
    }

    private void dungPnlLocTheoCacMocThoiGian(){
        pnlLocTheoMocThoiGianLapHDBH.setLayout(new BoxLayout(pnlLocTheoMocThoiGianLapHDBH, BoxLayout.X_AXIS));
        pnlLocTheoMocThoiGianLapHDBH.setBackground(bgrMacDinh);

        cbbCacMocThoiGianLapHDBH.setEnabled(false);
        CacHamDungSan.datThuocTinhChoCacRad(radLocTheoMocThoiGianLapHDBH);
        datHanhDongChoRadLocTheoMocThoiGian();
        pnlLocTheoMocThoiGianLapHDBH.add(radLocTheoMocThoiGianLapHDBH);

        pnlLocTheoMocThoiGianLapHDBH.add(Box.createHorizontalStrut(4));
        cbbCacMocThoiGianLapHDBH.setPreferredSize(dimCbbCacMocThoiGianLapHDBH);
        cbbCacMocThoiGianLapHDBH.setFont(fntMacDinh);
        cbbCacMocThoiGianLapHDBH.addActionListener((e) -> {
            radLocTheoMocThoiGianLapHDBH.doClick();
        });
        pnlLocTheoMocThoiGianLapHDBH.add(cbbCacMocThoiGianLapHDBH);
    }

    private void tapHopThanhVienCuaBngLocTheoThoiGian(){
        bngLocTheoThoiGianLapHDBH.add(radLocTheoMocThoiGianLapHDBH);
        bngLocTheoThoiGianLapHDBH.add(radTuyChonThoiGianLapHDBH);
    }

    private void datHanhDongChoRadLocTheoMocThoiGian(){
        radLocTheoMocThoiGianLapHDBH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cbbCacMocThoiGianLapHDBH.setEnabled(true);
                pnlKhungChonThoiGianLapHDBH.setVisible(false);

                pnlLocTheoThoiGianLapHDBH.setPreferredSize(dimPnlLocTheoThoiGianLapHDBH_1);
            }
        });
    }

    private void datHanhDongChoRadTuyChonThoiGian(){
        radTuyChonThoiGianLapHDBH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbbCacMocThoiGianLapHDBH.setEnabled(false);
                pnlKhungChonThoiGianLapHDBH.setVisible(true);

                pnlLocTheoThoiGianLapHDBH.setPreferredSize(dimPnlLocTheoThoiGianLapHDBH_2);
            }
        });
    }

    private void dungPnlKhungChonThoiGian(){
        pnlKhungChonThoiGianLapHDBH.setBackground(bgrMacDinh);
        pnlKhungChonThoiGianLapHDBH.setPreferredSize(dimPnlKhungChonThoiGianLapHDBH);
        pnlKhungChonThoiGianLapHDBH.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                0,
                0
        ));
        pnlKhungChonThoiGianLapHDBH.setVisible(false);

        CacHamDungSan.dungPnlNgayBatDauHoacKetThuc(
                pnlNgayBatDauTKTheoHDBH,
                lblNgayBatDauTKTheoHDBH,
                dtpNgayBatDauTKTheoHDBH,
                dimPnlNgayBatDauVaKetThucTKTheoHDBH,
                dimDatePicker
        );
        pnlKhungChonThoiGianLapHDBH.add(pnlNgayBatDauTKTheoHDBH);

        CacHamDungSan.dungPnlNgayBatDauHoacKetThuc(
                pnlNgayKetThucTKTheoHDBH,
                lblNgayKetThucTKTheoHDBH,
                dtpNgayKetThucTKTheoHDBH,
                dimPnlNgayBatDauVaKetThucTKTheoHDBH,
                dimDatePicker
        );
        pnlKhungChonThoiGianLapHDBH.add(pnlNgayKetThucTKTheoHDBH);

        dtpNgayBatDauTKTheoHDBH.addDateChangeListener((e) -> {
            radTuyChonThoiGianLapHDBH.doClick();
        });

        dtpNgayKetThucTKTheoHDBH.addDateChangeListener((e) -> {
            radTuyChonThoiGianLapHDBH.doClick();
        });
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
        pnlLuaChonLocTheoTongTienHDBH.setLayout(new FlowLayout(
                FlowLayout.LEADING,
                10,
                3
        ));
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

    private void dungPnlLocTheoGioiTinhKhachHang(){
        pnlLocTheoGioiTinhKhachHang.setBackground(bgrMacDinh);
        pnlLocTheoGioiTinhKhachHang.setPreferredSize(dimPnlLocTheoGioiTinhKhachHang);
        pnlLocTheoGioiTinhKhachHang.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoGioiTinhKhachHang, lblTieuDeLocTheoGioiTinhKhachHang);
        pnlLocTheoGioiTinhKhachHang.add(pnlTieuDeLocTheoGioiTinhKhachHang);

        dungPnlLuaChonLocTheoGioiTinhKhachHang();
        pnlLocTheoGioiTinhKhachHang.add(pnlLuaChonLocTheoGioiTinhKhachHang);
    }

    private void dungPnlLuaChonLocTheoGioiTinhKhachHang(){
        pnlLuaChonLocTheoGioiTinhKhachHang.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoGioiTinhKhachHang.setPreferredSize(dimPnlLuaChonLocTheoGioiTinhKhachHanga);
        pnlLuaChonLocTheoGioiTinhKhachHang.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                3
        ));

        bngLocTheoGioiTinhKhachHang.add(radMoiGioiTinhKH);
        bngLocTheoGioiTinhKhachHang.add(radKHNam);
        bngLocTheoGioiTinhKhachHang.add(radKHNu);

        CacHamDungSan.datThuocTinhChoCacRad(radMoiGioiTinhKH);
        pnlLuaChonLocTheoGioiTinhKhachHang.add(radMoiGioiTinhKH);

        CacHamDungSan.datThuocTinhChoCacRad(radKHNam);
        pnlLuaChonLocTheoGioiTinhKhachHang.add(radKHNam);

        CacHamDungSan.datThuocTinhChoCacRad(radKHNu);
        pnlLuaChonLocTheoGioiTinhKhachHang.add(radKHNu);
    }

    private void dungPnlNoiDungTKDoanhThuTheoHDBH(){
        pnlNoiDungTKDoanhThuTheoHDBH.setBackground(bgrPnlChinh);
        pnlNoiDungTKDoanhThuTheoHDBH.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungTKDoanhThuTheoHDBH.setPreferredSize(dimPnlNoiDungTKDoanhThuTheoHDBH);

        dungPnlThanhCongCuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoHDBH.add(pnlThanhCongCuTKDoanhThuTheoHDBH);

        dungScrChuaTblDuLieuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoHDBH.add(scrChuaTblDuLieuTKDoanhThuTheoHDBH);
    }

    private void dungPnlThanhCongCuTKDoanhThuTheoHDBH(){
        pnlThanhCongCuTKDoanhThuTheoHDBH.setBackground(bgrPnlChinh);
        pnlThanhCongCuTKDoanhThuTheoHDBH.setPreferredSize(dimPnlThanhCongCuTKDoanhThuTheoHDBH);
        pnlThanhCongCuTKDoanhThuTheoHDBH.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 3));

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiemTrenTblTKDoanhThuTheoHDBH,
                "Tìm kiếm",
                dimTxtTimKiemHDBH
        );
        datHanhDongChoTxtTimKiem();
        pnlThanhCongCuTKDoanhThuTheoHDBH.add(txtTimKiemTrenTblTKDoanhThuTheoHDBH);

        dungPnlHopCongCuTKDoanhThuTheoHDBH();
        pnlThanhCongCuTKDoanhThuTheoHDBH.add(pnlHopCongCuTKDoanhThuTheoHDBH);
    }

    private void datHanhDongChoTxtTimKiem(){
        txtTimKiemTrenTblTKDoanhThuTheoHDBH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsDuLieuTKDoanhThuTheoHDBH.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiemTrenTblTKDoanhThuTheoHDBH.getText().trim()
                        )
                );
            }
        });
    }

    private void dungPnlHopCongCuTKDoanhThuTheoHDBH(){
        pnlHopCongCuTKDoanhThuTheoHDBH.setBackground(bgrPnlChinh);
        pnlHopCongCuTKDoanhThuTheoHDBH.setPreferredSize(dimPnlHopCongCuTKDoanhThuTheoHDBH);
        pnlHopCongCuTKDoanhThuTheoHDBH.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatTKDoanhThuTheoHDBHRaFile,
                bgrBtnXuatDuLieuRaFile,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnXuatTKDoanhThuTheoHDBHRaExcel
        );
        datHanhDongChoBtnXuatRaExcel();
        pnlHopCongCuTKDoanhThuTheoHDBH.add(btnXuatTKDoanhThuTheoHDBHRaFile);

        dungPmnXuatData();
    }

    private void dungScrChuaTblDuLieuTKDoanhThuTheoHDBH(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDuLieuTKDoanhThuTheoHDBH,
                dimScrChuaTblDuLieuTKDoanhThuTheoHDBH,
                trsDuLieuTKDoanhThuTheoHDBH
        );
        canLeChoDuLieuTrongTblTKDTTheoHDBH();
        PnlQLBanHang.datHanhDongChoTblDSHoaDonBanHang(tblDuLieuTKDoanhThuTheoHDBH);

        scrChuaTblDuLieuTKDoanhThuTheoHDBH.getViewport().setBackground(bgrMacDinh);
        scrChuaTblDuLieuTKDoanhThuTheoHDBH.setPreferredSize(dimScrChuaTblDuLieuTKDoanhThuTheoHDBH);
    }

    private void canLeChoDuLieuTrongTblTKDTTheoHDBH(){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblDuLieuTKDoanhThuTheoHDBH.getColumnModel();

        for (int i = 0; i < tblDuLieuTKDoanhThuTheoHDBH.getColumnCount(); ++i) {
            if (i == 4 || i == 5 || i == 6 || i == 7){
                tcm.getColumn(i).setCellRenderer(rightRenderer);
            }
        }

        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(0).setMaxWidth(100);

        tcm.getColumn(1).setPreferredWidth(110);
        tcm.getColumn(1).setMaxWidth(110);

        tcm.getColumn(2).setPreferredWidth(130);
        tcm.getColumn(2).setMaxWidth(130);

        tcm.getColumn(3).setPreferredWidth(130);
        tcm.getColumn(3).setMaxWidth(130);

        tcm.getColumn(4).setPreferredWidth(80);
        tcm.getColumn(4).setMaxWidth(80);
    }

    /**
     * <p>Hàm này chưa hoàn thiện</p>
     * <p>Xuất dữ liệu trong tbl ra Excel</p>
     */
    private void datHanhDongChoBtnXuatRaExcel(){
        btnXuatTKDoanhThuTheoHDBHRaFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pmnXuatData.show(
                        btnXuatTKDoanhThuTheoHDBHRaFile,
                        0,
                        btnXuatTKDoanhThuTheoHDBHRaFile.getHeight()
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
                        tblDuLieuTKDoanhThuTheoHDBH,
                        true,
                        IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_HOA_DON_BAN_HANG,
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
                        tblDuLieuTKDoanhThuTheoHDBH,
                        false,
                        IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_HOA_DON_BAN_HANG,
                        ""
                );
            }
        });
    }

    private List<JRadioButton> btnsLocTheoGioiTinh = Arrays.asList(radMoiGioiTinhKH, radKHNam, radKHNu);
    private List<JRadioButton> btnsLocTheoGiaTien =
            Arrays.asList(radTatCaMucTien, radDuoi1tr, radTu1trDen3tr, radTu3trDen7tr, radTren7tr);
    private List<JRadioButton> btnsLocTheoThoiGian = Arrays.asList(radLocTheoMocThoiGianLapHDBH, radTuyChonThoiGianLapHDBH);

    /**
     * @author Hiếu
     * <p><i>
     * Hàm này có chức năng tên cho các radio button <br>
     * và thêm sự kiện cho tất cả các radio button đó
     * </i></p>
     */
    public void datHanhDongChoRadioButtons() {
        radMoiGioiTinhKH.setName("TatCaGioi");
        radKHNam.setName("Nam");
        radKHNu.setName("Nu");

        radTatCaMucTien.setName("TatCaMucTien");
        radDuoi1tr.setName("Duoi1Tr");
        radTu1trDen3tr.setName("1trDen3tr");
        radTu3trDen7tr.setName("3trDen7tr");
        radTren7tr.setName("Tren7tr");

        radLocTheoMocThoiGianLapHDBH.setName("LocTheoMoc");
        radTuyChonThoiGianLapHDBH.setName("TuyChon");

        btnsLocTheoGioiTinh.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoGiaTien.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoThoiGian.forEach(btn -> {
            btn.addActionListener(this);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoGioiTinh = new AtomicInteger(-1);
        AtomicInteger locTheoMucTien = new AtomicInteger(-1);
        AtomicInteger locTheoNgay = new AtomicInteger(-1);

        btnsLocTheoGioiTinh.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("Nam")) {
                    locTheoGioiTinh.set(1);
                } else if(btn.getName().equals("Nu"))
                    locTheoGioiTinh.set(0);
            }
        });

        btnsLocTheoGiaTien.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.isSelected()) {
                    if(btn.getName().equals("Duoi1Tr"))
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

        btnsLocTheoThoiGian.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("LocTheoMoc")) {
                    locTheoNgay.set(1);
                } else
                    locTheoNgay.set(0);
            }
        });

        if(locTheoNgay.get() == 0) {
            if(dtpNgayBatDauTKTheoHDBH.getDate() == null || dtpNgayKetThucTKTheoHDBH.getDate() == null) {
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Vui lòng chọn ngày để lọc!"
                );
                return;
            }

            if(dtpNgayBatDauTKTheoHDBH.getDate().isAfter(dtpNgayKetThucTKTheoHDBH.getDate())) {
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Ngày bắt đầu phải trước ngày kết thúc!"
                );
                return;
            }
        }

        capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoGioiTinh.get(), locTheoMucTien.get(), locTheoNgay.get()));
    }

    private String sinhCauTruyVanCSDL(int locTheoGioiTinh, int locTheoMucTien, int locTheoNgay) {
        String query = "Select hd.maHDBH, hd.maNhanVienLapHDBH, kh.soDT, hd.thoiGianLap, " +
                "hd.tongSLSP, hd.thanhTienChuaThue, hd.thueGTGT, hd.tongTien " +
                "from HoaDonBanHang hd  join KhachHang kh on kh.maKH = hd.maKH ";

        if(locTheoGioiTinh != -1 && locTheoMucTien != -1 && locTheoNgay != -1) {
            if(locTheoNgay == 0) {
                query += String.format("where %s and %s and %s",
                        sinhCauTruyVanLocTheoGioiTinh(locTheoGioiTinh),
                        sinhCauTruyVanLocTheoGiaTien(locTheoMucTien),
                        sinhCauTruyVanLocTheoTuyChonThoiGian());
            }
            else if(locTheoNgay == 1) {
                query += String.format("where %s and %s and %s",
                        sinhCauTruyVanLocTheoGioiTinh(locTheoGioiTinh),
                        sinhCauTruyVanLocTheoGiaTien(locTheoMucTien),
                        sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGianLapHDBH.getSelectedIndex()));
            }
        }
        else  if(locTheoGioiTinh == -1 && locTheoMucTien != -1 && locTheoNgay != -1) {
            if(locTheoNgay == 0) {
                query += String.format("where %s and %s",
                        sinhCauTruyVanLocTheoGiaTien(locTheoMucTien),
                        sinhCauTruyVanLocTheoTuyChonThoiGian());
            }
            else if(locTheoNgay == 1) {
                query += String.format("where %s and %s",
                        sinhCauTruyVanLocTheoGiaTien(locTheoMucTien),
                        sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGianLapHDBH.getSelectedIndex()));
            }
        }
        else  if(locTheoGioiTinh != -1 && locTheoMucTien == -1 && locTheoNgay != -1) {
            if(locTheoNgay == 0) {
                query += String.format("where %s and %s",
                        sinhCauTruyVanLocTheoGioiTinh(locTheoGioiTinh),
                        sinhCauTruyVanLocTheoTuyChonThoiGian());
            }
            else if(locTheoNgay == 1) {
                query += String.format("where %s and %s",
                        sinhCauTruyVanLocTheoGioiTinh(locTheoGioiTinh),
                        sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGianLapHDBH.getSelectedIndex()));
            }
        }
        else  if(locTheoGioiTinh != -1 && locTheoMucTien != -1 && locTheoNgay == -1) {
                query += String.format("where %s and %s",
                        sinhCauTruyVanLocTheoGioiTinh(locTheoGioiTinh),
                        sinhCauTruyVanLocTheoGiaTien(locTheoMucTien));
        }
        else  if(locTheoGioiTinh == -1 && locTheoMucTien == -1 && locTheoNgay != -1) {
            if(locTheoNgay == 0) {
                query += String.format("where %s", sinhCauTruyVanLocTheoTuyChonThoiGian());
            }
            else if(locTheoNgay == 1) {
                query += String.format("where %s", sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGianLapHDBH.getSelectedIndex()));
            }
        }
        else  if(locTheoGioiTinh == -1 && locTheoMucTien != -1 && locTheoNgay == -1) {
            query += String.format("where %s", sinhCauTruyVanLocTheoGiaTien(locTheoMucTien));
        }
        else  if(locTheoGioiTinh != -1 && locTheoMucTien == -1 && locTheoNgay == -1) {
            query += String.format("where %s", sinhCauTruyVanLocTheoGioiTinh(locTheoGioiTinh));
        }

        query += " order by hd.maHDBH asc";

            return query;
    }

    private String sinhCauTruyVanLocTheoGioiTinh(int gioiTinh) {
        return String.format("kh.gioiTinh = %d", gioiTinh);
    }

    private String sinhCauTruyVanLocTheoGiaTien(int locTheoTongTien) {
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
                TienIch.dinhDangNgay(dtpNgayBatDauTKTheoHDBH.getDate(), "yyyy-MM-dd"),
                TienIch.dinhDangNgay(dtpNgayKetThucTKTheoHDBH.getDate().plusDays(1), "yyyy-MM-dd"));

        if(dtpNgayBatDauTKTheoHDBH.getDate().equals(dtpNgayKetThucTKTheoHDBH.getDate())){
            query = String.format("cast (hd.thoiGianLap as date) = '%s'",
                    TienIch.dinhDangNgay(dtpNgayBatDauTKTheoHDBH.getDate(), "yyyy-MM-dd"));
        }

        return query;
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Hàm có chức năng hiển thị thông tin nhân viên lên JTable với những dữ liệu
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

                dtmDuLieuTKDoanhThuTheoHDBH.setRowCount(0);
                return;
            }

            dtmDuLieuTKDoanhThuTheoHDBH.setRowCount(0);
            int maHDBHLonNhat = TienIch.laySoLonNhat("maHDBH", "maHDBH", "HoaDonBanHang");

            AtomicReference<Double> tongChuaThue = new AtomicReference<>(0.0);
            AtomicReference<Double> tongVAT = new AtomicReference<>(0.0);
            AtomicReference<Double> tongTien = new AtomicReference<>(0.0);
            AtomicInteger tongSLSP = new AtomicInteger(0);

            while(res.next()) {
                dtmDuLieuTKDoanhThuTheoHDBH.addRow(
                        new Object[] {
                                TienIch.dinhDangSo(maHDBHLonNhat, res.getInt("maHDBH")), res.getString("maNhanVienLapHDBH"),
                                MaHoaDuLieu.giaiMa(res.getString("soDT")),
                                TienIch.dinhDangNgay(res.getTimestamp("thoiGianLap").toLocalDateTime().toLocalDate(), "dd-MM-yyyy"),
                                res.getInt("tongSLSP"),
                                dinhDangTien(res.getDouble("thanhTienChuaThue")),
                                dinhDangTien(res.getDouble("thueGTGT")),
                                dinhDangTien(res.getDouble("tongTien"))
                        });

                tongSLSP.getAndAdd(res.getInt("tongSLSP"));
                tongTien.getAndSet(tongTien.get() + res.getDouble("tongTien"));
                tongVAT.getAndSet(tongVAT.get() + res.getDouble("thueGTGT"));
                tongChuaThue.getAndSet(tongChuaThue.get() + res.getDouble("thanhTienChuaThue"));
            }

            themHangTongKetDuLieuVaoTable(dtmDuLieuTKDoanhThuTheoHDBH.getRowCount(), tongSLSP.get(),
                    tongTien.get(), tongVAT.get(), tongChuaThue.get());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void themHangTongKetDuLieuVaoTable(int tongSoHD, int tongSLSP, double tongTien, double tongVAT, double tongTienChuaThue){
        Object[] duLieuSauTongKet = {
                "Tổng cộng:",
                tongSoHD + " hoá đơn",
                "",
                "",
                tongSLSP,
                dinhDangTien(tongTienChuaThue),
                dinhDangTien(tongVAT),
                dinhDangTien(tongTien)
        };

        dtmDuLieuTKDoanhThuTheoHDBH.insertRow(0, duLieuSauTongKet);
    }

    private String dinhDangTien(double tien) {
        return NumberFormat.getCurrencyInstance(new Locale("vi", "vn")).format(tien);
    }
}
