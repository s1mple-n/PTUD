package ui.giaodienchinh.pnlqlthuchi;

import connectDB.KetNoiCSDL;
import dao.*;
import entity.NhanVien;
import entity.NhatKiBanHangTheoCa;
import entity.PhieuDoiChung;
import services.CacHamDungSan;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlthuchi.gdxemnhatkibanhangtheoca.GDXemNhatKiBanHangTheoCa;
import ui.giaodienchinh.pnlqlthuchi.gdtaophieudoichung.GDTaoPhieuDoiChung;
import ui.giaodienchinh.pnlqlthuchi.gdxemthongtinphieudoichung.GDXemTTPhieuDoiChung;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PnlQLThuChi implements IDSBienGDQLThuChi, ActionListener {
    private static JPanel pnlQLThuChi = null;

    private JPanel dungPnlQLThuChi(){
        pnlQLTC.setBackground(bgrPnlChinh);
        pnlQLTC.setPreferredSize(dimPnlQLTC);

        NhanVien nv = GDChinh.getNhanVienDangSuDung();

        if (nv.isQuanLi()){
            pnlQLTC.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

            dungPnlThanhTienIch();
            pnlQLTC.add(pnlThanhTienIch);

            dungPnlNoiDungQLThuChiChinh();
            pnlQLTC.add(pnlNoiDungQLThuChiChinh);
        }
        else{
//            pnlQLTC.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 220));
            pnlQLTC.setLayout(new GridBagLayout());

            dungPnlTaoPDCHoacNhatKiBHTC(pnlTaoPhieuDoiChung, lblBtTaoPhieuDoiChung, lblTieuDeTaoPhieuDoiChung);
            datThuocTinhChoPblTaoPDCVaNhatKiBHTC(pnlTaoPhieuDoiChung, mauVienPnlTaoPhieuDoiChung);
            datHanhDongChoPnlTaoPhieuDoiChung();
            pnlQLTC.add(pnlTaoPhieuDoiChung);

            dungPnlTaoPDCHoacNhatKiBHTC(pnlTaoNhatKiBHTC, lblBtTaoNhatKiBHTC, lblTieuDeTaoNhatKiBHTC);
            datThuocTinhChoPblTaoPDCVaNhatKiBHTC(pnlTaoNhatKiBHTC, mauVienPnlTaoNhatKiBHTC);
            datHanhDongChoPnlTaoNhatKiBHTC();
            pnlQLTC.add(Box.createHorizontalStrut(30));
            pnlQLTC.add(pnlTaoNhatKiBHTC);
        }

        return pnlQLTC;
    }

    public static JPanel getPnlQLThuChi() {
        if (pnlQLThuChi == null)
            pnlQLThuChi = new PnlQLThuChi().dungPnlQLThuChi();
        return pnlQLThuChi;
    }

    private void dungPnlTaoPDCHoacNhatKiBHTC(JPanel pnl, JLabel lbIcon, JLabel lbTieuDe) {
        pnl.setBackground(bgrMacDinh);
        pnl.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 18));
        pnl.setPreferredSize(new Dimension(
                kichThuocCacPnlTomTat.width - 5,
                kichThuocCacPnlTomTat.height
        ));
        pnl.setBorder(BorderFactory.createMatteBorder(
                3, 3, 3, 3, mauVienPnlTaoPhieuDoiChung
        ));

        lbTieuDe.setFont(fontLbTieuDeNut);
        lbTieuDe.setForeground(mauChuLbNut);

        pnl.add(lbIcon);
        pnl.add(lbTieuDe);
    }

    private void datThuocTinhChoPblTaoPDCVaNhatKiBHTC(JPanel pnl, Color mauVienGoc){
        pnl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnl.setBackground(mauVienPnlTaoPhieuDoiChungKhiHover);
                pnl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnl.setBackground(bgrMacDinh);
                pnl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pnl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                pnl.setBorder(BorderFactory.createMatteBorder(
                        3, 3, 3, 3, mauVienPnlTaoPhieuDoiChungKhiHover
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                pnl.setBorder(BorderFactory.createMatteBorder(
                        3, 3, 3, 3, mauVienGoc
                ));
            }
        });
    }

    private void datHanhDongChoPnlTaoPhieuDoiChung(){
        pnlTaoPhieuDoiChung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    GDTaoPhieuDoiChung gd = new GDTaoPhieuDoiChung();
                    gd.setVisible(true);
                });
            }
        });
    }

    public static void anPnlTaoPhieuDoiChung(){
        pnlTaoPhieuDoiChung.setVisible(false);
    }

    private void datHanhDongChoPnlTaoNhatKiBHTC(){
        pnlTaoNhatKiBHTC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taoNhatKiBanHangTheoCa();

                pnlTaoNhatKiBHTC.setVisible(false);
            }
        });
    }

    private void dungPnlThanhTienIch(){
        pnlThanhTienIch.setBackground(bgrMacDinh);
        pnlThanhTienIch.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThanhTienIch.setPreferredSize(dimPnlThanhTienIch);

        dungPnlLocTheoLoaiPhieu();
        pnlThanhTienIch.add(pnlLocTheoLoaiPhieu);

        dungPnlLocTheoThoiGian();
        pnlThanhTienIch.add(pnlLocTheoThoiGian);

        datHanhDongChoRadioButtons();
    }

    private void dungPnlLocTheoLoaiPhieu(){
        pnlLocTheoLoaiPhieu.setBackground(bgrMacDinh);
        pnlLocTheoLoaiPhieu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoLoaiPhieu.setPreferredSize(dimPnlLocTheoLoaiPhieu);

        CacHamDungSan.dungPnlTieuDeLoc(
                pnlTieuDeLocTheoLoaiPhieu,
                lblTieuDeLocTheoLoaiPhieu
        );
        pnlLocTheoLoaiPhieu.add(pnlTieuDeLocTheoLoaiPhieu);

        dungPnlLuaChonLocTheoLoaiPhieu();
        pnlLocTheoLoaiPhieu.add(pnlLuaChonLocTheoLoaiPhieu);
    }

    private void dungPnlLuaChonLocTheoLoaiPhieu(){
        pnlLuaChonLocTheoLoaiPhieu.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoLoaiPhieu.setLayout(new FlowLayout(
                FlowLayout.LEADING,
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));
        pnlLuaChonLocTheoLoaiPhieu.setPreferredSize(dimPnlLuaChonLocTheoLoaiPhieu);

        tapHopThanhVienCuaBngLocTheoLoaiPhieu();

        CacHamDungSan.datThuocTinhChoCacRad(radPhieuDoiChung);
        datHanhDongChoRadPhieuDoiChung();
        pnlLuaChonLocTheoLoaiPhieu.add(radPhieuDoiChung);

        CacHamDungSan.datThuocTinhChoCacRad(radNhatKiBHTC);
        datHanhDongChoRadNhatKiBanHang();
        pnlLuaChonLocTheoLoaiPhieu.add(radNhatKiBHTC);
    }

    private void datHanhDongChoRadPhieuDoiChung(){
        radPhieuDoiChung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tblDuLieuTraCuuDuoc.removeMouseListener(molXemTTNhatKiBanHangTheoCa);
                tblDuLieuTraCuuDuoc.addMouseListener(molXemTTPhieuDoiChung);
            }
        });
    }

    private void datHanhDongChoRadNhatKiBanHang(){
        radNhatKiBHTC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tblDuLieuTraCuuDuoc.removeMouseListener(molXemTTPhieuDoiChung);
                tblDuLieuTraCuuDuoc.addMouseListener(molXemTTNhatKiBanHangTheoCa);
            }
        });
    }

    private void tapHopThanhVienCuaBngLocTheoLoaiPhieu(){
        bngLocTheoGioiTinh.add(radPhieuDoiChung);
        bngLocTheoGioiTinh.add(radNhatKiBHTC);
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

    private void dungPnlNoiDungQLThuChiChinh(){
        pnlNoiDungQLThuChiChinh.setBackground(bgrPnlChinh);
        pnlNoiDungQLThuChiChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungQLThuChiChinh.setPreferredSize(dimPnlNoiDungQLThuChiChinh);

        dungPnlThanhCongCu();
        pnlNoiDungQLThuChiChinh.add(pnlThanhCongCu);

        dungPnlKetQuaTraCuu();
        pnlNoiDungQLThuChiChinh.add(pnlKetQuaTraCuu);
    }

    private void dungPnlThanhCongCu(){
        pnlThanhCongCu.setBackground(bgrPnlChinh);
        pnlThanhCongCu.setPreferredSize(dimPnlThanhCongCu);
        pnlThanhCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        dungPmnCheDoTimKiem();

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiem,
                "Tìm kiếm",
                dimTxtTimKiem
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiem);
        datHanhDongChoTxtTimKiem();
        txtTimKiem.setToolTipText("Tìm kiếm PĐC bằng mã phiếu hoặc mã NV lập");
        pnlThanhCongCu.add(txtTimKiem);

        dungPnlHopCongCu();
        pnlThanhCongCu.add(pnlHopCongCu);

        dungPmnKetQuaTimKiem();
    }

    private void datHanhDongChoTxtTimKiem(){
        txtTimKiem.addKeyListener(klnTimKiemPDC);

        txtTimKiem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == 3){
                    pmnCheDoTimKiem.show(
                            txtTimKiem,
                            e.getX(),
                            e.getY()
                    );
                }
            }
        });
    }

    private void dungPmnCheDoTimKiem(){
        pmnCheDoTimKiem.setBackground(bgrMacDinh);
        pmnCheDoTimKiem.setBorder(BorderFactory.createEmptyBorder());

        datHanhDongChoMniTimKiemPDCTrongCSDL();
        pmnCheDoTimKiem.add(mniTimKiemPDCTrongCSDL);

        datHanhDongChoMniTimKiemNKBHTCTrongCSDL();
        pmnCheDoTimKiem.add(mniTimKiemNKBHTrongCSDL);

        datHanhDongChoMniTimKiemTrenTable();
        pmnCheDoTimKiem.add(mniTimKiemTrenTable);
    }

    private void datHanhDongChoMniTimKiemPDCTrongCSDL(){
        mniTimKiemPDCTrongCSDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTimKiem.setText("");
                txtTimKiem.setToolTipText("Tìm kiếm PĐC bằng mã phiếu hoặc mã NV lập");

                txtTimKiem.removeKeyListener(klnTimKiemNKBHTC);
                txtTimKiem.removeKeyListener(klnTimKiemTrenTable);

                txtTimKiem.addKeyListener(klnTimKiemPDC);

                tblKetQuaTimKiem.setModel(dtmKetQuaTimKiemPhieuDoiChung);
                tblKetQuaTimKiem.removeMouseListener(molXemTTNhatKiBHTuPmnKQTimKiem);
                tblKetQuaTimKiem.addMouseListener(molXemTTPhieuDoiChungTuPmnKQTimKiem);

                canLeChoTblKQTimKiem(true);

                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(
                        txtTimKiem,
                        ""
                );
            }
        });
    }

    private void datHanhDongChoMniTimKiemNKBHTCTrongCSDL(){
        mniTimKiemNKBHTrongCSDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTimKiem.setText("");
                txtTimKiem.setToolTipText("Tìm kiếm NKBHTC bằng mã nhật kí hoặc mã NV lập");

                txtTimKiem.removeKeyListener(klnTimKiemPDC);
                txtTimKiem.removeKeyListener(klnTimKiemTrenTable);

                txtTimKiem.addKeyListener(klnTimKiemNKBHTC);

                tblKetQuaTimKiem.setModel(dtmKetQuaTimKiemNhatKiBHTC);
                tblKetQuaTimKiem.removeMouseListener(molXemTTPhieuDoiChungTuPmnKQTimKiem);
                tblKetQuaTimKiem.addMouseListener(molXemTTNhatKiBHTuPmnKQTimKiem);

                canLeChoTblKQTimKiem(false);

                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(
                        txtTimKiem,
                        ""
                );
            }
        });
    }

    private void datHanhDongChoMniTimKiemTrenTable() {
        mniTimKiemTrenTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTimKiem.setText("");
                txtTimKiem.setToolTipText("Tìm kiếm cục bộ trên table dựa theo loại phiếu đã chọn");

                txtTimKiem.removeKeyListener(klnTimKiemPDC);
                txtTimKiem.removeKeyListener(klnTimKiemNKBHTC);

                txtTimKiem.addKeyListener(klnTimKiemTrenTable);

                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(
                        txtTimKiem,
                        ""
                );
            }
        });
    }

    private KeyListener klnTimKiemPDC = traVeKlnTimKiemPDC();
    private KeyListener klnTimKiemNKBHTC = traVeKlnTimKiemNKBH();
    private KeyListener klnTimKiemTrenTable = traVeKlnTimKiemTrenTbl();

    private KeyListener traVeKlnTimKiemPDC(){
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtTimKiem.getText().trim().toLowerCase();

                if (tuKhoa.isEmpty()){
                    pmnKetQuaTimKiem.setVisible(false);
                }
                else{
                    ResultSet dsPhieuDoiChung = PhieuDoiChungDAO.timPhieuDoiChungTheoMaPhieuHoacMaNhanVienLap(tuKhoa);

                    try {
                        if (dsPhieuDoiChung.isBeforeFirst()){
                            duaDuLieuPDCVaoTblKQTimKiem(dsPhieuDoiChung);

                            tblKetQuaTimKiem.setEnabled(true);

                            pmnKetQuaTimKiem.setVisible(true);

                            pmnKetQuaTimKiem.show(
                                    txtTimKiem,
                                    0, txtTimKiem.getHeight()
                            );

                            txtTimKiem.requestFocus();
                        }
                        else {
                            tblKetQuaTimKiem.setEnabled(false);

                            dtmKetQuaTimKiemPhieuDoiChung.setRowCount(0);

                            Object[] duLieuTrong = {
                                    "",
                                    "( ^ _ ^ )",
                                    "",
                                    ""
                            };
                            dtmKetQuaTimKiemPhieuDoiChung.addRow(duLieuTrong);

                            datKichThuocChoPmnKQTimKiem(2);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        return keyListener;
    }

    private KeyListener traVeKlnTimKiemNKBH(){
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtTimKiem.getText().trim().toLowerCase();

                if (tuKhoa.isEmpty()){
                    pmnKetQuaTimKiem.setVisible(false);
                }
                else{
                    ResultSet dsNhatKiBanHang = NhatKiBanHangTheoCaDAO.timNhatKiBanHangTheoMaNhatKiHoacMaNhanVienLap(tuKhoa);

                    try {
                        if (dsNhatKiBanHang.isBeforeFirst()){
                            duaDuLieuNKBHTCVaoTblKQTimKiem(dsNhatKiBanHang);

                            tblKetQuaTimKiem.setEnabled(true);

                            pmnKetQuaTimKiem.setVisible(true);

                            pmnKetQuaTimKiem.show(
                                    txtTimKiem,
                                    0, txtTimKiem.getHeight()
                            );

                            txtTimKiem.requestFocus();
                        }
                        else {
                            tblKetQuaTimKiem.setEnabled(false);

                            dtmKetQuaTimKiemNhatKiBHTC.setRowCount(0);

                            Object[] duLieuTrong = {
                                    "",
                                    "( ^ _ ^ )",
                                    ""
                            };
                            dtmKetQuaTimKiemNhatKiBHTC.addRow(duLieuTrong);

                            datKichThuocChoPmnKQTimKiem(2);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        return keyListener;
    }

    private KeyListener traVeKlnTimKiemTrenTbl(){
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtTimKiem.getText().trim().toLowerCase();

                if (radPhieuDoiChung.isSelected()){
                    trsDuLieuPhieuDoiChungTraCuuDuoc.setRowFilter(
                            RowFilter.regexFilter(
                                    "(?i)" + tuKhoa
                            )
                    );
                }
                else if (radNhatKiBHTC.isSelected()){
                    trsDuLieuNhatKiBHTC.setRowFilter(
                            RowFilter.regexFilter(
                                    "(?i)" + tuKhoa
                            )
                    );
                }
            }
        };

        return keyListener;
    }

    private void dungPmnKetQuaTimKiem(){
        pmnKetQuaTimKiem.setBackground(bgrMacDinh);
        pmnKetQuaTimKiem.setBorder(BorderFactory.createEmptyBorder());
        pmnKetQuaTimKiem.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh,
                        1
                )
        );

        dungTblKQTimKiem();
        tblKetQuaTimKiem.addMouseListener(molXemTTPhieuDoiChungTuPmnKQTimKiem);
        canLeChoTblKQTimKiem(true);
        pmnKetQuaTimKiem.add(tblKetQuaTimKiem.getTableHeader());
        pmnKetQuaTimKiem.add(tblKetQuaTimKiem, BorderLayout.CENTER);
    }

    private void dungTblKQTimKiem(){
        tblKetQuaTimKiem.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblKetQuaTimKiem.setFont(fntMacDinh);
        tblKetQuaTimKiem.setDefaultEditor(Object.class, null);

        tblKetQuaTimKiem.getTableHeader().setPreferredSize(
                new Dimension(
                        chieuRongPmnKetQuaTimKiem,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblKetQuaTimKiem.getTableHeader().setBackground(bgrTieuDeTable);
        tblKetQuaTimKiem.getTableHeader().setFont(fntMacDinh);
    }

    private void canLeChoTblKQTimKiem(boolean isTimPhieuDoiChung){
        TableColumnModel tcm = tblKetQuaTimKiem.getColumnModel();

        if (isTimPhieuDoiChung){
            tcm.getColumn(0).setPreferredWidth(80);
            tcm.getColumn(0).setMaxWidth(80);

            tcm.getColumn(1).setPreferredWidth(120);
            tcm.getColumn(1).setMaxWidth(120);

            tcm.getColumn(2).setPreferredWidth(120);
            tcm.getColumn(2).setMaxWidth(120);
        }
        else{
            tcm.getColumn(0).setPreferredWidth(80);
            tcm.getColumn(0).setMaxWidth(80);

            tcm.getColumn(1).setPreferredWidth(120);
            tcm.getColumn(1).setMaxWidth(120);
        }
    }

    private void datKichThuocChoPmnKQTimKiem(int sl){
        pmnKetQuaTimKiem.setPopupSize(new Dimension(
                chieuRongPmnKetQuaTimKiem,
                chieuCaoHangDuLieuTrongTable * sl
        ));
    }

    private void duaDuLieuPDCVaoTblKQTimKiem(ResultSet dsPhieuDoiChung) {
        dtmKetQuaTimKiemPhieuDoiChung.setRowCount(0);

        int count = 0;

        try{
            while (dsPhieuDoiChung.next()){
                Object[] o = {
                        dsPhieuDoiChung.getInt("maPhieuDoiChung"),
                        dsPhieuDoiChung.getString("maNhanVienLapPhieu"),
                        dsPhieuDoiChung.getString("maNhanVienKiemPhieu"),
                        dtf.format(
                                dsPhieuDoiChung.getTimestamp("thoiGianLapPhieu").toLocalDateTime()
                        )
                };

                count++;

                dtmKetQuaTimKiemPhieuDoiChung.addRow(o);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        datKichThuocChoPmnKQTimKiem(count + 1);
    }

    private void duaDuLieuNKBHTCVaoTblKQTimKiem(ResultSet dsNKBHTC){
        dtmKetQuaTimKiemNhatKiBHTC.setRowCount(0);

        int count = 0;

        try{
            while (dsNKBHTC.next()){
                Object[] o = {
                        dsNKBHTC.getInt("maNKBHTC"),
                        dsNKBHTC.getString("maNhanVienLapNKBHTC"),
                        dtf.format(
                                dsNKBHTC.getTimestamp("thoiGianLap").toLocalDateTime()
                        )
                };

                count++;

                dtmKetQuaTimKiemNhatKiBHTC.addRow(o);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        datKichThuocChoPmnKQTimKiem(count + 1);
    }

    private final MouseListener molXemTTPhieuDoiChungTuPmnKQTimKiem = traVeMolXemTTPhieuDoiChung(tblKetQuaTimKiem);
    private final MouseListener molXemTTNhatKiBHTuPmnKQTimKiem = traVeMolXemTTNhatKiBanHangTheoCa(tblKetQuaTimKiem);

    private void dungPnlHopCongCu(){
        pnlHopCongCu.setBackground(bgrPnlChinh);
        pnlHopCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnlHopCongCu.setPreferredSize(dimPnlHopCongCu);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThemPhieu,
                bgrBtnThem,
                bgrMacDinh,
                dimBtnThanhCongCu
        );
        datHanhDongChoBtnThemPhieu();
        pnlHopCongCu.add(btnThemPhieu);

        dungPmnTinhNangMoRong();

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

    private void datHanhDongChoBtnThemPhieu(){
        btnThemPhieu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pmnCacLoaiPhieuCanThem.show(
                        btnThemPhieu,
                        0,
                        btnThemPhieu.getHeight()
                );
            }
        });
    }

    private void dungPmnTinhNangMoRong() {
        pmnCacLoaiPhieuCanThem.setBorder(BorderFactory.createEmptyBorder());
        pmnCacLoaiPhieuCanThem.setBorderPainted(false);
        pmnCacLoaiPhieuCanThem.setPreferredSize(kichThuocPmnCacLoaiPhieuCanThem);
        pmnCacLoaiPhieuCanThem.setBackground(mauNenPmnCacLoaiPhieuCanThem);

        mniLapPhieuDoiChung.setToolTipText("Tạo phiếu đối chứng cho ca làm.");
        datHanhDongChoMniLapPhieuDoiChung();
        pmnCacLoaiPhieuCanThem.add(mniLapPhieuDoiChung);

        mniLapNhatKiBHTC.setToolTipText("Tạo nhật kí bán hàng cho ca làm.");
        datHanhDongChoMniLapNhatKiBHTC();
        pmnCacLoaiPhieuCanThem.add(mniLapNhatKiBHTC);
    }

    private void datHanhDongChoMniLapPhieuDoiChung(){
        mniLapPhieuDoiChung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GDTaoPhieuDoiChung gd = new GDTaoPhieuDoiChung();
                gd.setVisible(true);
            }
        });
    }

    private void datHanhDongChoMniLapNhatKiBHTC(){
        mniLapNhatKiBHTC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taoNhatKiBanHangTheoCa();

                anMniLapNKBHTC();
            }
        });
    }

    private void taoNhatKiBanHangTheoCa(){
        NhanVien nvLapNhatKiBanHangTheoCa = GDChinh.getNhanVienDangSuDung();

        int slspDaBanTrongCaLam = HoaDonBanHangDAO.tinhTongSLSPBanDuocTrongCaLam(
                nvLapNhatKiBanHangTheoCa.getMaNV()
        );
        int slspConLaiTrongKho = new SanPhamDAO().tinhTongSLSPTonKho();
        int slspMoiNhapTrongCaLam = HoaDonNhapHangDAO.tinhSLSPDaNhapHangTrongCaLam(
                nvLapNhatKiBanHangTheoCa.getMaNV()
        );
        double tongDoanhThuCaLam = HoaDonBanHangDAO.tinhTongDoanhThuCuaCaLam(
                nvLapNhatKiBanHangTheoCa.getMaNV()
        );

        NhatKiBanHangTheoCa nhatKiBanHangTheoCa = new NhatKiBanHangTheoCa(
                nvLapNhatKiBanHangTheoCa,
                LocalDateTime.now(),
                slspDaBanTrongCaLam,
                slspConLaiTrongKho,
                slspMoiNhapTrongCaLam,
                tongDoanhThuCaLam
        );

        NhatKiBanHangTheoCaDAO.themNhatKiBanHangTheoCa(nhatKiBanHangTheoCa);

        NhatKiBanHangTheoCa nhatKiBanHangTheoCaMoiLap = NhatKiBanHangTheoCaDAO.layLenNhatKiBanHangTheoCaMoiLap();

        SwingUtilities.invokeLater(() -> {
            GDXemNhatKiBanHangTheoCa gd = GDXemNhatKiBanHangTheoCa.getGdTaoNhatKiBanHangTheoCa();

            GDXemNhatKiBanHangTheoCa.hienThiDuLieuCuaNhatKiBanHangTheoCa(nhatKiBanHangTheoCaMoiLap);

            gd.setVisible(true);
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
        pmnXuatData.setBorder(BorderFactory.createEmptyBorder());
        pmnXuatData.setPreferredSize(dimPmnXuatData);

        datHanhDongChoMniXuatExcel();
        pmnXuatData.add(mniXuatExcel);

        datHanhDongChoMniXuatPDF();
        pmnXuatData.add(mniXuatPDF);
    }

    private void datHanhDongChoMniXuatExcel(){
        mniXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radPhieuDoiChung.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTraCuuDuoc,
                            true,
                            IDSBienMacDinh.THONG_KE_PHIEU_DOI_CHUNG,
                            ""
                    );
                }
                else if (radNhatKiBHTC.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTraCuuDuoc,
                            true,
                            IDSBienMacDinh.THONG_KE_NHAT_KI_BAN_HANG_THEO_CA,
                            ""
                    );
                }
            }
        });
    }

    private void datHanhDongChoMniXuatPDF(){
        mniXuatPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radPhieuDoiChung.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTraCuuDuoc,
                            false,
                            IDSBienMacDinh.THONG_KE_PHIEU_DOI_CHUNG,
                            ""
                    );
                }
                else if (radNhatKiBHTC.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTraCuuDuoc,
                            false,
                            IDSBienMacDinh.THONG_KE_NHAT_KI_BAN_HANG_THEO_CA,
                            ""
                    );
                }
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

        scrChuaTableDuLieuTraCuuDuoc.setPreferredSize(dimScrChuaTableDulieuTraCuuDuoc);
        scrChuaTableDuLieuTraCuuDuoc.getViewport().setBackground(bgrMacDinh);
    }

    private void datThuocTinhChoTblDuLieuTraCuuDuoc(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDuLieuTraCuuDuoc,
                dimScrChuaTableDulieuTraCuuDuoc,
                null
        );

        datThuocTinhChoTieuDeCuaTable();
    }

    private void canLeChoTableTuyTheoDangDuLieu(boolean isDtmDuLieuPhieuDoiChung){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblDuLieuTraCuuDuoc.getColumnModel();

        if (isDtmDuLieuPhieuDoiChung){
            tcm.getColumn(4).setCellRenderer(rightRenderer);
            tcm.getColumn(5).setCellRenderer(rightRenderer);

            tcm.getColumn(3).setPreferredWidth(250);
            tcm.getColumn(3).setMaxWidth(250);
        }
        else{
            tcm.getColumn(3).setCellRenderer(rightRenderer);
            tcm.getColumn(4).setCellRenderer(rightRenderer);
            tcm.getColumn(5).setCellRenderer(rightRenderer);
            tcm.getColumn(6).setCellRenderer(rightRenderer);

            tcm.getColumn(2).setPreferredWidth(250);
            tcm.getColumn(2).setMaxWidth(250);
        }

        tcm.getColumn(0).setPreferredWidth(110);
        tcm.getColumn(0).setMaxWidth(110);
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

    private List<JRadioButton> btnsLoaiPhieu = Arrays.asList(radPhieuDoiChung, radNhatKiBHTC);
    private List<JRadioButton> btnsLocTheoThoiGianMua = Arrays.asList(radLocTheoMocThoiGian, radTuyChonThoiGian);

    public void datHanhDongChoRadioButtons() {
        radPhieuDoiChung.setName("PhieuDoiChung");
        radNhatKiBHTC.setName("NhatKi");

        radLocTheoMocThoiGian.setName("LocTheoMoc");
        radTuyChonThoiGian.setName("TuyChon");

        btnsLoaiPhieu.forEach(btn -> {
            btn.addActionListener(this);
        });

        btnsLocTheoThoiGianMua.forEach(btn -> {
            btn.addActionListener(this);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoLoaiPhieu = new AtomicInteger(-1);
        AtomicInteger locTheoNgay = new AtomicInteger(-1);

        dtmDuLieuPhieuDoiChungTraCuuDuoc.setRowCount(0);
        dtmDuLieuNhatKiBHTheoCa.setRowCount(0);

        btnsLoaiPhieu.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("NhatKi")) {

                    locTheoLoaiPhieu.set(1);
                    tblDuLieuTraCuuDuoc.setModel(dtmDuLieuNhatKiBHTheoCa);
                    tblDuLieuTraCuuDuoc.setRowSorter(trsDuLieuNhatKiBHTC);
                    canLeChoTableTuyTheoDangDuLieu(false);

                }
                else if(btn.getName().equals("PhieuDoiChung")) {

                    locTheoLoaiPhieu.set(0);
                    tblDuLieuTraCuuDuoc.setModel(dtmDuLieuPhieuDoiChungTraCuuDuoc);
                    tblDuLieuTraCuuDuoc.setRowSorter(trsDuLieuPhieuDoiChungTraCuuDuoc);
                    canLeChoTableTuyTheoDangDuLieu(true);

                }
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

        if(locTheoNgay.get() != -1 && locTheoLoaiPhieu.get() != -1)
            capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoLoaiPhieu.get(), locTheoNgay.get()));
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
            chuyenDuLieuLenTableTuongUng(res);

            chinhSuaThongTinDongTongKetKetQuaTraCuu(tblDuLieuTraCuuDuoc.getRowCount());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void xoaDuLieuTheoBang() {
        if(radPhieuDoiChung.isSelected()) {
            dtmDuLieuPhieuDoiChungTraCuuDuoc.setRowCount(0);
        }
        else if(radNhatKiBHTC.isSelected()) {
            dtmDuLieuNhatKiBHTheoCa.setRowCount(0);
        }
    }

    private String sinhCauTruyVanCSDL(int loaiPhieu, int locTheoNgay) {
        String query = "";

        if(locTheoNgay != -1 && loaiPhieu != -1) {
            query = String.format("Select * from %s ", loaiPhieu == 0 ? "PhieuDoiChung" : "NhatKiBanHangTheoCa");
            if(locTheoNgay == 0)
                query += sinhCauTruyVanTuyChonNgay(loaiPhieu);
            else if(locTheoNgay == 1)
                query += sinhCauTruyVanLocTheoMocThoiGian(loaiPhieu, cbbCacMocThoiGian.getSelectedIndex());
        }

        return query;
    }

    private String sinhCauTruyVanLocTheoMocThoiGian(int loaiPhieu, int cheDoLocMocThoiGian) {
        String query = "";
        String ngay = String.format("%s", loaiPhieu == 0 ? "thoiGianLapPhieu" : "thoiGianLap" );

        LocalDate homNay = LocalDate.now();
        if(cheDoLocMocThoiGian == 0) {
            query += String.format("where cast(%s as date) = '%s'", ngay, TienIch.dinhDangNgay(homNay, "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 1) {
            query += String.format("where %s >= '%s' and %s < '%s'", ngay, TienIch.getNgayDauTrongTuan(), ngay,
                    TienIch.dinhDangNgay(homNay.plusDays(1), "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 2) {
            query += String.format("where month(%s) = '%s' and year(%s) = '%s'", ngay, homNay.getMonthValue(), ngay, homNay.getYear());
        }

        return query;
    }

    private String sinhCauTruyVanTuyChonNgay(int loaiPhieu) {
        String query = "";
        String ngay = String.format("%s", loaiPhieu == 0 ? "thoiGianLapPhieu" : "thoiGianLap" );

        if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate()))
            query += String.format("where cast (%s as date) = '%s'", ngay,
                    TienIch.dinhDangNgay(dtpNgayKetThuc.getDate(), "yyyy-MM-dd"));
        else
            query += String.format("where %s >= '%s' and %s < '%s'", ngay,
                    TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                    ngay,
                    TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));

        return query;
    }

    private void chuyenDuLieuLenTableTuongUng(ResultSet res) throws SQLException {
        if(radPhieuDoiChung.isSelected())
            while(res.next())
                dtmDuLieuPhieuDoiChungTraCuuDuoc.addRow(chuyenDuLieuSangPhieuDoiChung(res));

        else if(radNhatKiBHTC.isSelected())
            while(res.next())
                dtmDuLieuNhatKiBHTheoCa.addRow(chuyenDuLieuSangNKBHTC(res));
    }

    private Object[] chuyenDuLieuSangPhieuDoiChung(ResultSet res) throws SQLException {
        return new Object[] {
                res.getInt("maPhieuDoiChung"),
                res.getString("maNhanVienLapPhieu"),
                res.getString("maNhanVienKiemPhieu"),
                dtf.format(res.getTimestamp("thoiGianLapPhieu").toLocalDateTime()),
                nf.format(res.getDouble("soTienNguoiGiaoCaTinh")),
                nf.format(res.getDouble("soTienNguoiNhanCaTinh"))
        };
    }

    private Object[] chuyenDuLieuSangNKBHTC(ResultSet res) throws SQLException {
        return new Object[] {
                res.getInt("maNKBHTC"),
                res.getString("maNhanVienLapNKBHTC"),
                dtf.format(res.getTimestamp("thoiGianLap").toLocalDateTime()),
                res.getInt("tongSLSPBanDuoc"),
                res.getInt("tongSLSPConLai"),
                res.getInt("tongSLSPMoiNhap"),
                nf.format(res.getDouble("tongDoanhThu"))
        };
    }

    private void chinhSuaThongTinDongTongKetKetQuaTraCuu(int slhd) {
        Object[] o = {
                "Tổng cộng:",
                slhd + " phiếu",
                "",
                "",
                "",
                "",
                ""
        };

        if(radPhieuDoiChung.isSelected())
            dtmDuLieuPhieuDoiChungTraCuuDuoc.insertRow(0, o);
        else if(radNhatKiBHTC.isSelected())
            dtmDuLieuNhatKiBHTheoCa.insertRow(0, o);
    }

    public static void anMniLapPhieuDoiChung(){
        mniLapPhieuDoiChung.setEnabled(false);

        mniLapPhieuDoiChung.setToolTipText("Bạn đã lập phiếu đối chứng cho ca làm này rồi.");
    }

    private void anMniLapNKBHTC(){
        mniLapNhatKiBHTC.setEnabled(false);

        mniLapNhatKiBHTC.setToolTipText("Bạn đã lập nhật kí bán hàng cho ca làm này rồi.");
    }

    private MouseListener molXemTTPhieuDoiChung = traVeMolXemTTPhieuDoiChung(tblDuLieuTraCuuDuoc);
    private MouseListener molXemTTNhatKiBanHangTheoCa = traVeMolXemTTNhatKiBanHangTheoCa(tblDuLieuTraCuuDuoc);

    private MouseListener traVeMolXemTTPhieuDoiChung(JTable tbl){
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int hangDuocChon = tbl.getSelectedRow();

                if (
                        hangDuocChon != -1 &&
                        e.getClickCount() == 2 &&
                        e.getButton() == 1 &&
                        !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                tbl,
                                hangDuocChon
                        )
                ){
                    int maPhieu = Integer.parseInt(tbl.getValueAt(
                            hangDuocChon, 0
                    ).toString());

                    PhieuDoiChung phieuDoiChung = PhieuDoiChungDAO.timPhieuDoiChungTheoMa(maPhieu);

                    SwingUtilities.invokeLater(() -> {
                        GDXemTTPhieuDoiChung gd = GDXemTTPhieuDoiChung.getGdXemTTPhieuDoiChung();
                        GDXemTTPhieuDoiChung.hienThiThongTinPhieuDoiChung(phieuDoiChung);
                        gd.setVisible(true);
                    });
                }
            }
        };

        return mouseListener;
    }

    private MouseListener traVeMolXemTTNhatKiBanHangTheoCa(JTable tbl){
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int hangDuocChon = tbl.getSelectedRow();

                if (
                        hangDuocChon != -1 &&
                        e.getClickCount() == 2 &&
                        e.getButton() == 1 &&
                        !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                tbl, hangDuocChon
                        )
                ){
                    int maPhieu = Integer.parseInt(tbl.getValueAt(
                            hangDuocChon, 0
                    ).toString());

                    NhatKiBanHangTheoCa nhatKiBanHangTheoCa = NhatKiBanHangTheoCaDAO.timNhatKiBanHangTheoMa(maPhieu);

                    SwingUtilities.invokeLater(() -> {
                        GDXemNhatKiBanHangTheoCa gd = GDXemNhatKiBanHangTheoCa.getGdTaoNhatKiBanHangTheoCa();
                        GDXemNhatKiBanHangTheoCa.hienThiDuLieuCuaNhatKiBanHangTheoCa(nhatKiBanHangTheoCa);
                        gd.setVisible(true);
                    });
                }
            }
        };

        return mouseListener;
    }
}
