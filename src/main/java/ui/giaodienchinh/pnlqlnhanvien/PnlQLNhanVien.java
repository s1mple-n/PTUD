package ui.giaodienchinh.pnlqlnhanvien;

import connectDB.KetNoiCSDL;
import dao.NhanVienDAO;
import entity.CaLamViec;
import entity.NhanVien;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlnhanvien.gdcapnhatthongtinnhanvien.GDCapNhatTTNhanVien;
import ui.giaodienchinh.pnlqlnhanvien.gdthemnhanvien.GDThemNhanVien;
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
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PnlQLNhanVien implements IDSBienQLNhanVien, ActionListener{
    private static JPanel pnlQLNhanVien = null;

    private JPanel dungPnlQLNhanVien(){
        pnlQLNV.setBackground(bgrPnlChinh);
        pnlQLNV.setPreferredSize(dimPnlQLNV);
        pnlQLNV.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        dungPnlThanhTienIch();
        pnlQLNV.add(pnlThanhTienIch);

        dungPnlNoiDungQLKHChinh();
        pnlQLNV.add(pnlNoiDungQLNVChinh);

        capNhatDSNhanVien();

        return pnlQLNV;
    }

    public static JPanel getPnlQLNhanVien() {
        if (pnlQLNhanVien == null)
            pnlQLNhanVien = new PnlQLNhanVien().dungPnlQLNhanVien();
        return pnlQLNhanVien;
    }

    public static void capNhatDSNhanVien(){
        dsNhanVien.clear();

        Thread luongCapNhatDSKH = new Thread(() -> {
            Map<String, NhanVien> dsNV = NhanVienDAO.layToanBoDuLieuNhanVien();

            dsNhanVien.putAll(dsNV);
        });

        luongCapNhatDSKH.start();
    }

    private void dungPnlThanhTienIch(){
        pnlThanhTienIch.setBackground(bgrMacDinh);
        pnlThanhTienIch.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThanhTienIch.setPreferredSize(dimPnlThanhTienIch);

        dungPnlLocTheoGioiTinh();
        pnlThanhTienIch.add(pnlLocTheoCaLamViec);

        dungPnlLocTheoTinhTrangLamViec();
        pnlThanhTienIch.add(pnlLocTheoTinhTrangLamViec);

        dungPnlLocTheoThoiGian();
        pnlThanhTienIch.add(pnlLocTheoThoiGian);

        datHanhDongChoRadioButtons();
    }

    private void dungPnlLocTheoGioiTinh(){
        pnlLocTheoCaLamViec.setBackground(bgrMacDinh);
        pnlLocTheoCaLamViec.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoCaLamViec.setPreferredSize(dimPnlLocTheoCaLamViec);

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoCaLamViec, lblTieuDeLocTheoCaLamViec);
        pnlLocTheoCaLamViec.add(pnlTieuDeLocTheoCaLamViec);

        dungPnlLuaChonLocTheoGioiTinh();
        pnlLocTheoCaLamViec.add(pnlLuaChonLocTheoCaLamViec);
    }

    private void dungPnlLuaChonLocTheoGioiTinh(){
        pnlLuaChonLocTheoCaLamViec.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoCaLamViec.setLayout(new FlowLayout(
                FlowLayout.LEADING,
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));
        pnlLuaChonLocTheoCaLamViec.setPreferredSize(dimPnlLuaChonLocTheoCaLamViec);

        tapHopThanhVienCuaBngLocTheoGioiTinh();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaCaLamViec);
        pnlLuaChonLocTheoCaLamViec.add(radTatCaCaLamViec);

        CacHamDungSan.datThuocTinhChoCacRad(radCaSang);
        pnlLuaChonLocTheoCaLamViec.add(radCaSang);

        CacHamDungSan.datThuocTinhChoCacRad(radCaToi);
        pnlLuaChonLocTheoCaLamViec.add(radCaToi);
    }

    private void tapHopThanhVienCuaBngLocTheoGioiTinh(){
        bngLocTheoCaLamViec.add(radTatCaCaLamViec);
        bngLocTheoCaLamViec.add(radCaSang);
        bngLocTheoCaLamViec.add(radCaToi);
    }

    private void dungPnlLocTheoTinhTrangLamViec(){
        pnlLocTheoTinhTrangLamViec.setBackground(bgrMacDinh);
        pnlLocTheoTinhTrangLamViec.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoTinhTrangLamViec.setPreferredSize(dimPnlLocTheoTinhTrangLamViec);

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoTinhTrangLamViec, lblTieuDeLocTheoTinhTrangLamViec);
        pnlLocTheoTinhTrangLamViec.add(pnlTieuDeLocTheoTinhTrangLamViec);

        dungPnlLuaChonLocTheoTinhTrangLamViec();
        pnlLocTheoTinhTrangLamViec.add(pnlLuaChonLocTheoTinhTrangLamViec);
    }

    private void dungPnlLuaChonLocTheoTinhTrangLamViec(){
        pnlLuaChonLocTheoTinhTrangLamViec.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoTinhTrangLamViec.setLayout(new FlowLayout(FlowLayout.LEADING, khoangCachSoVoiLeTraiCuaTieuDe, 10));
        pnlLuaChonLocTheoTinhTrangLamViec.setPreferredSize(dimPnlLuaChonLocTheoTinhTrangLamViec);

        tapHopThanhVienCuaBngLocTheoTinhTrangLamViec();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaTinhTrangLamViec);
        pnlLuaChonLocTheoTinhTrangLamViec.add(radTatCaTinhTrangLamViec);

        CacHamDungSan.datThuocTinhChoCacRad(radConLam);
        pnlLuaChonLocTheoTinhTrangLamViec.add(radConLam);

        CacHamDungSan.datThuocTinhChoCacRad(radDaNghi);
        pnlLuaChonLocTheoTinhTrangLamViec.add(radDaNghi);
    }

    private void tapHopThanhVienCuaBngLocTheoTinhTrangLamViec(){
        bngLocTheoTinhTrangLamViec.add(radTatCaTinhTrangLamViec);
        bngLocTheoTinhTrangLamViec.add(radConLam);
        bngLocTheoTinhTrangLamViec.add(radDaNghi);
    }

    private void dungPnlLocTheoThoiGian(){
        pnlLocTheoThoiGian.setBackground(bgrMacDinh);
        pnlLocTheoThoiGian.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnlLocTheoThoiGian.setPreferredSize(dimPnlLocTheoThoiGian);

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoThoiGian, lblLocTheoThoiGian);
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
        radTuyChonThoiGian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        pnlNoiDungQLNVChinh.setBackground(bgrPnlChinh);
        pnlNoiDungQLNVChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungQLNVChinh.setPreferredSize(dimPnlNoiDungQLNVChinh);

        dungPnlThanhCongCu();
        pnlNoiDungQLNVChinh.add(pnlThanhCongCu);

        dungPnlKetQuaTraCuu();
        pnlNoiDungQLNVChinh.add(pnlKetQuaTraCuu);
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
        datHanhDongChoTxtTimKiem();
        pnlThanhCongCu.add(txtTimKiem);

        dungPmnCheDoTimKiem();

        dungPnlHopCongCu();
        pnlThanhCongCu.add(pnlHopCongCu);

        dungPmnChuaDSNVTimDuoc();
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
                    pmnChuaDSNVTimDuoc.setVisible(false);
                }
                else{
                    ArrayList<NhanVienTimDuoc> dsTimDuoc = timKiemNVTheoTuKhoa(tuKhoa);

                    if (!dsTimDuoc.isEmpty()){
                        duaDuLieuVaoTable(dsTimDuoc);

                        pmnChuaDSNVTimDuoc.setVisible(true);

                        tblDSNVTimDuoc.setEnabled(true);

                        pmnChuaDSNVTimDuoc.show(
                                txtTimKiem,
                                0,
                                txtTimKiem.getHeight()
                        );

                        txtTimKiem.requestFocus();
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
        };

        return cheDoTimKiemTrongCSDL;
    }

    private void dungPmnCheDoTimKiem(){
        pmnCheDoTimKiem.setBorder(BorderFactory.createEmptyBorder());

        datHanhDongChoMniTimKiemTrongCSDL();
        pmnCheDoTimKiem.add(mniTimKiemTrongCSDL);

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

    private void dungPmnChuaDSNVTimDuoc(){
        pmnChuaDSNVTimDuoc.setBackground(bgrMacDinh);
        pmnChuaDSNVTimDuoc.setBorder(BorderFactory.createEmptyBorder());
        pmnChuaDSNVTimDuoc.setBorder(
                BorderFactory.createLineBorder(bgrVienMacDinh, 1)
        );

        pmnChuaDSNVTimDuoc.add(tblDSNVTimDuoc.getTableHeader());
        dungTblChuaDSNVTimDuoc();
        datHanhDongChoTblDSNVTimDuoc();
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

                if (
                        hangDuocChon != -1 &&
                        !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                tblDSNVTimDuoc,
                                hangDuocChon
                        )
                ){
                    String maNV = (String) tblDSNVTimDuoc.getValueAt(
                            hangDuocChon, 0
                    );

                    NhanVien nv = dsNhanVien.get(maNV);

                    int soLanClick = e.getClickCount();
                    int nutChuotDuocNhan = e.getButton();

                    if (soLanClick == 2 && nutChuotDuocNhan == 1){
                        SwingUtilities.invokeLater(() -> {
                            GDXemTTNhanVien gd = GDXemTTNhanVien.getGdXemTTNhanVien();

                            GDXemTTNhanVien.hienThiThongTinNhanVienLenTxt(nv);

                            gd.setVisible(true);
                        });
                    }
                    else if (soLanClick == 1 && nutChuotDuocNhan == 3){
                        SwingUtilities.invokeLater(() -> {
                            GDCapNhatTTNhanVien gd = GDCapNhatTTNhanVien.getGdCapNhatTTNhanVien();

                            GDCapNhatTTNhanVien.hienThiThongTinNhanVienLenTxt(nv);
                            GDCapNhatTTNhanVien.datRequestFocusVaoTxtSoDTNV();

                            gd.setVisible(true);
                        });
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

    private void dungPnlHopCongCu(){
        pnlHopCongCu.setBackground(bgrPnlChinh);
        pnlHopCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnlHopCongCu.setPreferredSize(dimPnlHopCongCu);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThemNhanVien,
                bgrBtnThem,
                bgrMacDinh,
                dimBtnThanhCongCu
        );
        datHanhDongChoBtnThemNhanVien();
        pnlHopCongCu.add(btnThemNhanVien);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatDuLieuTrongTableRaFile,
                bgrBtnXuatDuLieuRaFile,
                bgrMacDinh,
                dimBtnThanhCongCu
        );
        datHanhDongChoBtnXuatDuLieuRaExcel();
        pnlHopCongCu.add(Box.createHorizontalStrut(5));
        pnlHopCongCu.add(btnXuatDuLieuTrongTableRaFile);

        dungPmnXuatData();
    }

    private void datHanhDongChoBtnThemNhanVien(){
        btnThemNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    GDThemNhanVien gd = GDThemNhanVien.getGdThemNhanVien();
                    gd.setVisible(true);
                });
            }
        });
    }

    private void datHanhDongChoBtnXuatDuLieuRaExcel(){
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
        datHanhDongChoTblDuLieuTraCuuDuoc();

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

        tcm.getColumn(0).setPreferredWidth(120);
        tcm.getColumn(0).setMaxWidth(120);
        tcm.getColumn(0).setCellRenderer(centerRenderer);

        tcm.getColumn(2).setPreferredWidth(150);
        tcm.getColumn(2).setMaxWidth(150);

        tcm.getColumn(3).setPreferredWidth(160);
        tcm.getColumn(3).setMaxWidth(160);

        tcm.getColumn(4).setPreferredWidth(100);
        tcm.getColumn(4).setMaxWidth(100);

        tcm.getColumn(5).setPreferredWidth(100);
        tcm.getColumn(5).setMaxWidth(100);
    }

    private void datHanhDongChoTblDuLieuTraCuuDuoc(){
        tblDuLieuTraCuuDuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int nutChuotDuocClick = e.getButton();
                int soLanClick = e.getClickCount();

                int hangDuocChon = tblDuLieuTraCuuDuoc.getSelectedRow();

                if (
                        hangDuocChon != -1 &&
                        !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                tblDuLieuTraCuuDuoc,
                                hangDuocChon
                        )
                ){
                    String maNV = (String) tblDuLieuTraCuuDuoc.getValueAt(
                            hangDuocChon, 0
                    );

                    NhanVien nv = dsNhanVien.get(maNV);

                    if (nutChuotDuocClick == 1 && soLanClick == 2){
                       SwingUtilities.invokeLater(() -> {
                           GDXemTTNhanVien gd = GDXemTTNhanVien.getGdXemTTNhanVien();

                           GDXemTTNhanVien.hienThiThongTinNhanVienLenTxt(nv);

                           gd.setVisible(true);
                       });
                    }
                    else if (nutChuotDuocClick == 3 && soLanClick == 2){
                        SwingUtilities.invokeLater(() -> {
                            GDCapNhatTTNhanVien gd = GDCapNhatTTNhanVien.getGdCapNhatTTNhanVien();

                            GDCapNhatTTNhanVien.hienThiThongTinNhanVienLenTxt(nv);
                            GDCapNhatTTNhanVien.datRequestFocusVaoTxtSoDTNV();

                            gd.setVisible(true);
                        });
                    }
                }
            }
        });
    }

    private static List<JRadioButton> btnsLocTheoCaLam = Arrays.asList(radTatCaCaLamViec, radCaSang, radCaToi);
    private static List<JRadioButton> btnsLocTheoTinhTrangLamViec = Arrays.asList(radTatCaTinhTrangLamViec, radConLam, radDaNghi);
    private static List<JRadioButton> btnsLocTheoThoiGianVaoLam = Arrays.asList(radLocTheoMocThoiGian, radTuyChonThoiGian);

    /**
     * @author Hiếu
     * <p><i>
     * Hàm này có chức năng tên cho các radio button <br>
     * và thêm sự kiện cho tất cả các radio button đó
     * </i></p>
     */
    public void datHanhDongChoRadioButtons() {
        radTatCaCaLamViec.setName("btnTatCaCaLam");
        radCaSang.setName("Sang");
        radCaToi.setName("Toi");

        radTatCaTinhTrangLamViec.setName("btnTatCaTinhTrang");
        radConLam.setName("ConLam");
        radDaNghi.setName("DaNghi");

        radLocTheoMocThoiGian.setName("LocTheoMoc");
        radTuyChonThoiGian.setName("TuyChon");

        btnsLocTheoCaLam.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoTinhTrangLamViec.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoThoiGianVaoLam.forEach(btn -> {
            btn.addActionListener(this);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoCaLam = new AtomicInteger(-1);
        AtomicInteger locTheoTinhTrangLam = new AtomicInteger(-1);
        AtomicInteger locTheoNgay = new AtomicInteger(-1);

        btnsLocTheoCaLam.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("Sang")) {
                    locTheoCaLam.set(1);
                } else if(btn.getName().equals("Toi"))
                    locTheoCaLam.set(0);
            }
        });

        btnsLocTheoTinhTrangLamViec.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("DaNghi")) {
                    locTheoTinhTrangLam.set(1);
                } else if(btn.getName().equals("ConLam"))
                    locTheoTinhTrangLam.set(0);
            }
        });

        btnsLocTheoThoiGianVaoLam.forEach(btn -> {
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

        capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoCaLam.get(), locTheoTinhTrangLam.get(), locTheoNgay.get()));
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Hàm có chức năng sinh câu truy vấn csdl với điều kiện lọc tương ứng
     * </i></p>
     * @param locTheoCa <br> -1 -> Tất cả ca <br> 0 -> Ca tối <br> 1 -> Ca sáng
     * @param locTheoTinhTrangLamViec <br> -1 -> Tất cả tình trạng làm việc <br> 0 -> Còn làm <br> 1 -> Đã nghỉ
     * @param locTheoNgay <br> -1 -> Không lọc theo ngày <br> 0 -> Tùy chọn ngày <br> 1 -> Lọc theo mốc thời gian
     * @return
     */
    private String sinhCauTruyVanCSDL(int locTheoCa, int locTheoTinhTrangLamViec, int locTheoNgay) {
        String query = "Select * from NhanVien ";

        if(locTheoCa != -1 && locTheoTinhTrangLamViec != -1 && locTheoNgay != -1) {
            if(locTheoCa == 1 && locTheoTinhTrangLamViec == 1 && locTheoNgay == 1) {//
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoCa, locTheoTinhTrangLamViec, cbbCacMocThoiGian.getSelectedIndex());
            }
            else if(locTheoCa == 1 && locTheoTinhTrangLamViec == 1 && locTheoNgay == 0){
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoCa, locTheoTinhTrangLamViec);
            }
            else if(locTheoCa == 1 && locTheoTinhTrangLamViec == 0 && locTheoNgay == 1) {//
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoCa, locTheoTinhTrangLamViec, cbbCacMocThoiGian.getSelectedIndex());
            }
            else if(locTheoCa == 0 && locTheoTinhTrangLamViec == 1 && locTheoNgay == 1) {//
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoCa, locTheoTinhTrangLamViec, cbbCacMocThoiGian.getSelectedIndex());
            }
            else if(locTheoCa == 1 && locTheoTinhTrangLamViec == 0 && locTheoNgay == 0) {
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoCa, locTheoTinhTrangLamViec);
            }
            else if(locTheoCa == 0 && locTheoTinhTrangLamViec == 1 && locTheoNgay == 0) {
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoCa, locTheoTinhTrangLamViec);
            }
            else if(locTheoCa == 0 && locTheoTinhTrangLamViec == 0 && locTheoNgay == 1) {//
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoCa, locTheoTinhTrangLamViec, cbbCacMocThoiGian.getSelectedIndex());
            }
            else if(locTheoCa == 0 && locTheoTinhTrangLamViec == 0 && locTheoNgay == 0) {
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoCa, locTheoTinhTrangLamViec);
            }
        }
        else if(locTheoCa != -1 && locTheoTinhTrangLamViec == -1 && locTheoNgay == -1){
            query += String.format("where maCaLamViec = %d", locTheoCa);
        }
        else if(locTheoCa == -1 && locTheoTinhTrangLamViec != -1 && locTheoNgay == -1){
            query += String.format("where trangThaiLamViec = %d", locTheoTinhTrangLamViec);
        }
        else if(locTheoCa == -1 && locTheoTinhTrangLamViec == -1 && locTheoNgay != -1){
            if(locTheoNgay == 0)
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoCa, locTheoTinhTrangLamViec);
            else
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoCa, locTheoTinhTrangLamViec, cbbCacMocThoiGian.getSelectedIndex());
        }
        else if(locTheoCa != -1 && locTheoTinhTrangLamViec != -1 && locTheoNgay == -1){
            query += String.format("where maCaLamViec = %d and trangThaiLamViec = %d", locTheoCa, locTheoTinhTrangLamViec);
        }
        else if(locTheoCa != -1 && locTheoTinhTrangLamViec == -1 && locTheoNgay != -1){
            if(locTheoNgay == 0)
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoCa, locTheoTinhTrangLamViec);
            else
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoCa, locTheoTinhTrangLamViec, cbbCacMocThoiGian.getSelectedIndex());
        }
        else if(locTheoCa == -1 && locTheoTinhTrangLamViec != -1 && locTheoNgay != -1){
            if(locTheoNgay == 0)
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoCa, locTheoTinhTrangLamViec);
            else
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoCa, locTheoTinhTrangLamViec, cbbCacMocThoiGian.getSelectedIndex());
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
                CacHamDungSan.hienThiThongBaoKetQua(
                       GDThongBaoKetQua.THONG_BAO_LOI,
                       "Không có dữ liệu phù hợp!"
               );
                dtmDuLieuTraCuuDuoc.setRowCount(0);
                return;
            }

            dtmDuLieuTraCuuDuoc.setRowCount(0);

            while(res.next()) {
                NhanVien nv = new NhanVien(
                        res.getString("maNhanVien"),
                        new CaLamViec(res.getBoolean("maCaLamViec")),
                        MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                        MaHoaDuLieu.giaiMa(res.getString("soDT")),
                        MaHoaDuLieu.giaiMa(res.getString("soCMND")),
                        MaHoaDuLieu.giaiMa(res.getString("diaChi")),
                        res.getBoolean("gioiTinh"),
                        res.getBoolean("capBac"),
                        res.getBoolean("trangThaiLamViec"),
                        res.getTimestamp("ngayVaoLam").toLocalDateTime());

                dtmDuLieuTraCuuDuoc.addRow(chuyenDuLieuTruocKhiLoadLenTable(nv));
            }

            themDongTongKetKetQuaTimKiemVaoHangSo0CuaTable(tblDuLieuTraCuuDuoc.getRowCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Object[] chuyenDuLieuTruocKhiLoadLenTable(NhanVien nv) throws SQLException {
        return new Object[] {
                nv.getMaNV(), nv.getHoTen(), nv.getSoDT(), nv.getSoCMND(), nv.isQuanLi() ? "Quản lí" : "Nhân viên",
                nv.isNghiLam() ? "Đã nghỉ" : "Còn làm"
        };
    }

    private String sinhCauTruyVanLocTheoMocThoiGian(int caLamViec, int tinhTrangLamViec, int cheDoLocMocThoiGian) {
        String query = "";
        String dieuKien = "where";

        if(caLamViec != -1 && tinhTrangLamViec != -1) {
            query += String.format("where maCaLamViec = %d and trangThaiLamViec = %d ", caLamViec, tinhTrangLamViec);
            dieuKien = "and";
        }
        else if(caLamViec == -1 && tinhTrangLamViec != -1) {
            query += String.format("where trangThaiLamViec = %d ", tinhTrangLamViec);
            dieuKien = "and";
        }
        else if(caLamViec != -1 && tinhTrangLamViec == -1) {
            query += String.format("where maCaLamViec = %d ", caLamViec);
            dieuKien = "and";
        }

        LocalDate homNay = LocalDate.now();
        if(cheDoLocMocThoiGian == 0) {
            query += String.format("%s cast(ngayVaoLam as date) = '%s'", dieuKien, TienIch.dinhDangNgay(homNay, "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 1) {
            query += String.format("%s ngayVaoLam >= '%s' and ngayVaoLam < '%s'", dieuKien, TienIch.getNgayDauTrongTuan(),
                    TienIch.dinhDangNgay(homNay.plusDays(1), "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 2) {
            query += String.format("%s month(ngayVaoLam) = '%s' and year(ngayVaoLam) = '%s'",
                    dieuKien, homNay.getMonthValue(), homNay.getYear());
        }

        return query;
    }

    private String sinhCauTruyVanLocTheoTuyChonNgay(int caLamViec, int tinhTrangLamViec){
        String query = "";
        String truyVanTheoNgay = String.format("ngayVaoLam >= '%s' and ngayVaoLam < '%s'",
                TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));

        if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate())){
            truyVanTheoNgay = String.format("cast (ngayVaoLam as date) = '%s'", TienIch.dinhDangNgay(dtpNgayKetThuc.getDate(), "yyyy-MM-dd"));
        }

        if(caLamViec != -1 && tinhTrangLamViec != -1) {
            query += String.format("where maCaLamViec = %d and trangThaiLamViec = %d and %s", caLamViec, tinhTrangLamViec,
                    truyVanTheoNgay);
        }
        else if(caLamViec == -1 && tinhTrangLamViec != -1) {
            query += String.format("where trangThaiLamViec = %d and %s", tinhTrangLamViec,
                    truyVanTheoNgay);
        }
        else if(caLamViec != -1 && tinhTrangLamViec == -1) {
            query += String.format("where maCaLamViec = %d and %s", caLamViec,
                    truyVanTheoNgay);
        }
        else if(caLamViec == -1 && tinhTrangLamViec == -1) {
            query += String.format("where %s", truyVanTheoNgay);
        }

        return query;
    }

    private void themDongTongKetKetQuaTimKiemVaoHangSo0CuaTable(int slnv){
        Object[] o = {
                "Tổng cộng:",
                slnv + " nhân viên",
                "",
                "",
                "",
                ""
        };

        dtmDuLieuTraCuuDuoc.insertRow(0, o);
    }

    public static void locLaiDuLieuSauKhiThemHoacCapNhat() {
        btnsLocTheoCaLam.forEach(btn -> {
            if(btn.isSelected())
                btn.doClick();
        });

        btnsLocTheoThoiGianVaoLam.forEach(btn -> {
            if(btn.isSelected())
                btn.doClick();
        });

        btnsLocTheoTinhTrangLamViec.forEach(btn -> {
            if(btn.isSelected())
                btn.doClick();
        });
    }
}
