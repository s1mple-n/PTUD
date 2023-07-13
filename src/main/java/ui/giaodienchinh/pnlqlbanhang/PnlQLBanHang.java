package ui.giaodienchinh.pnlqlbanhang;

import connectDB.KetNoiCSDL;
import dao.HoaDonBanHangDAO;
import entity.HoaDonBanHang;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.GDTaoHoaDonBanHang;
import ui.giaodienchinh.pnlqlbanhang.gdxemthongtinhoadonbanhang.GDXemThongTinHoaDonBanHang;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PnlQLBanHang implements IDSBienPnlQLBanHang, ActionListener {
    private static JPanel pnlQLBanHang = null;

    private static final int TIMKIEMCUCBO = 1;
    private static final int TIMKIEMTRONGCSDL = -1;
    private int cheDoTimKiem = 0;

    private JPanel dungPnlQLBanHang(){
        pnlQuanLiBanHang.setBackground(bgrPnlChinh);
        pnlQuanLiBanHang.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlQuanLiBanHang.setPreferredSize(dimPnlQuanLiBanHang);

        dungPnlThanhTienIch();
        pnlQuanLiBanHang.add(pnlThanhTienIch);

        dungPnlNoiDungQLBHChinh();
        pnlQuanLiBanHang.add(pnlNoiDungQLBHChinh);

        return pnlQuanLiBanHang;
    }

    public static JPanel getPnlQLBanHang() {
        if (pnlQLBanHang == null){
            pnlQLBanHang = new PnlQLBanHang().dungPnlQLBanHang();
        }
        return pnlQLBanHang;
    }

    private void dungPnlThanhTienIch(){
        pnlThanhTienIch.setBackground(bgrMacDinh);
        pnlThanhTienIch.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThanhTienIch.setPreferredSize(dimPnlThanhTienIch);

        dungPnlLocTheoTongTien();
        pnlThanhTienIch.add(pnlLocTheoTongTien);

        dungPnlLocTheoThoiGian();
        pnlThanhTienIch.add(pnlLocTheoThoiGian);

        datHanhDongChoRadioButtons();
    }

    private void dungPnlLocTheoTongTien(){
        pnlLocTheoTongTien.setBackground(bgrMacDinh);
        pnlLocTheoTongTien.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoTongTien.setPreferredSize(dimPnlLocTheoTongTien);

        CacHamDungSan.dungPnlTieuDeLoc(
                pnlTieuDeLocTheoTongTien, lblTieuDeLocTheoTongTien
        );
        pnlLocTheoTongTien.add(pnlTieuDeLocTheoTongTien);

        dungPnlLuaChonLocTheoTongTien();
        pnlLocTheoTongTien.add(pnlLuaChonLocTheoTongTien);
    }

    private void dungPnlLuaChonLocTheoTongTien(){
        pnlLuaChonLocTheoTongTien.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoTongTien.setLayout(new FlowLayout(
                FlowLayout.LEADING,
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));
        pnlLuaChonLocTheoTongTien.setPreferredSize(dimPnlLuaChonLocTheoTongTien);

        tapHopThanhVienCuaBngLocTheoTongTien();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaMucTien);
        pnlLuaChonLocTheoTongTien.add(radTatCaMucTien);

        CacHamDungSan.datThuocTinhChoCacRad(radDuoi1tr);
        pnlLuaChonLocTheoTongTien.add(radDuoi1tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu1trDen3tr);
        pnlLuaChonLocTheoTongTien.add(radTu1trDen3tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu3trDen7tr);
        pnlLuaChonLocTheoTongTien.add(radTu3trDen7tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTren7tr);
        pnlLuaChonLocTheoTongTien.add(radTren7tr);
    }

    private void tapHopThanhVienCuaBngLocTheoTongTien(){
        bngLocTheoTongTien.add(radTatCaMucTien);
        bngLocTheoTongTien.add(radDuoi1tr);
        bngLocTheoTongTien.add(radTu1trDen3tr);
        bngLocTheoTongTien.add(radTu3trDen7tr);
        bngLocTheoTongTien.add(radTren7tr);
    }

    private void dungPnlLocTheoThoiGian(){
        pnlLocTheoThoiGian.setBackground(bgrMacDinh);
        pnlLocTheoThoiGian.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnlLocTheoThoiGian.setPreferredSize(dimPnlLocTheoThoiGian);

        CacHamDungSan.dungPnlTieuDeLoc(
                pnlTieuDeLocTheoThoiGian, lblLocTheoThoiGian
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

    private void dungPnlNoiDungQLBHChinh(){
        pnlNoiDungQLBHChinh.setBackground(bgrPnlChinh);
        pnlNoiDungQLBHChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungQLBHChinh.setPreferredSize(dimPnlNoiDungQLBHChinh);

        dungPnlThanhCongCu();
        pnlNoiDungQLBHChinh.add(pnlThanhCongCu);

        dungPnlKetQuaTraCuu();
        pnlNoiDungQLBHChinh.add(pnlKetQuaTraCuu);
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
        txtTimKiem.setToolTipText("Tìm kiếm hoá đơn bằng mã hoá đơn hoặc mã khách hàng");
        pnlThanhCongCu.add(txtTimKiem);

        dungPmnCheDoTimKiem();

        dungPnlHopCongCu();
        pnlThanhCongCu.add(pnlHopCongCu);

        dungPmnKetQuaTimKiemHDBH();
    }

    private void dungPmnKetQuaTimKiemHDBH(){
        pmnChuaDSHDBHTimDuoc.setBackground(bgrPnlChinh);
        pmnChuaDSHDBHTimDuoc.setBorder(BorderFactory.createEmptyBorder());
        pmnChuaDSHDBHTimDuoc.setBorder(
                BorderFactory.createLineBorder(bgrVienMacDinh, 1)
        );

        dungTblChuaDSHDBHTimDuoc();
        datHanhDongChoTblDSHoaDonBanHang(tblHDBHTimDuoc);
        pmnChuaDSHDBHTimDuoc.add(tblHDBHTimDuoc.getTableHeader());
        pmnChuaDSHDBHTimDuoc.add(tblHDBHTimDuoc, BorderLayout.CENTER);
    }

    private void dungTblChuaDSHDBHTimDuoc(){
        tblHDBHTimDuoc.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblHDBHTimDuoc.setFont(fntMacDinh);
        tblHDBHTimDuoc.setBackground(bgrMacDinh);
        tblHDBHTimDuoc.setDefaultEditor(Object.class, null);
        tblHDBHTimDuoc.getTableHeader().setPreferredSize(
                new Dimension(
                        chieuRongPmnChuaDSHDBHTimDuoc,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblHDBHTimDuoc.getTableHeader().setFont(fntMacDinh);
        tblHDBHTimDuoc.getTableHeader().setBackground(bgrTieuDeTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblHDBHTimDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(0).setMaxWidth(100);
        tcm.getColumn(0).setCellRenderer(centerRenderer);

        tcm.getColumn(1).setPreferredWidth(60);
        tcm.getColumn(1).setMaxWidth(60);
        tcm.getColumn(1).setCellRenderer(centerRenderer);

        tcm.getColumn(2).setPreferredWidth(200);
        tcm.getColumn(2).setMaxWidth(200);

        tcm.getColumn(3).setPreferredWidth(60);
        tcm.getColumn(3).setMaxWidth(60);
        tcm.getColumn(3).setCellRenderer(rightRenderer);

        tcm.getColumn(4).setCellRenderer(rightRenderer);
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

    private void dungPmnCheDoTimKiem(){
        pmnCheDoTimKiem.setBorder(BorderFactory.createEmptyBorder());

        mniTimKiemTrongCSDL.setFont(fntMacDinh);
        datHanhDongChoMniTimKiemTrongCSDL();
        pmnCheDoTimKiem.add(mniTimKiemTrongCSDL);

        mniTimKiemTrenTable.setFont(fntMacDinh);
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
                        txtTimKiem, ""
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
                        txtTimKiem, ""
                );
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
                    pmnChuaDSHDBHTimDuoc.setVisible(false);
                }
                else{
                    ResultSet dsTimDuoc = HoaDonBanHangDAO.timHoaDonBanHangTheoMaHDHoacMaKH(tuKhoa);

                    try {
                        if (dsTimDuoc.isBeforeFirst()){
                            duaDuLieuVaoTable(dsTimDuoc);

                            pmnChuaDSHDBHTimDuoc.setVisible(true);

                            tblHDBHTimDuoc.setEnabled(true);

                            pmnChuaDSHDBHTimDuoc.show(
                                    txtTimKiem,
                                    0,
                                    txtTimKiem.getHeight()
                            );

                            txtTimKiem.requestFocus();
                        }
                        else{
                            tblHDBHTimDuoc.setEnabled(false);

                            dtmHDBHTimDuoc.setRowCount(0);

                            Object[] thongBaoKhongTimRaHDBHNao = {
                                    "",
                                    "",
                                    "( ^ _ ^ )",
                                    "",
                                    ""
                            };

                            dtmHDBHTimDuoc.addRow(thongBaoKhongTimRaHDBHNao);

                            datKichThuocChoPmnChuaDSHDBHTimDuoc(2);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        return cheDoTimKiemTrongCSDL;
    }

    private void datKichThuocChoPmnChuaDSHDBHTimDuoc(int slKetQua){
        pmnChuaDSHDBHTimDuoc.setPopupSize(
                new Dimension(
                        chieuRongPmnChuaDSHDBHTimDuoc,
                        chieuCaoHangDuLieuTrongTable * slKetQua
                )
        );
    }

    private void duaDuLieuVaoTable(ResultSet dshd){
        dtmHDBHTimDuoc.setRowCount(0);

        int count = 0;

        try {
            while (dshd.next()){
                count++;

                Object[] o = {
                        dshd.getInt("maHDBH"),
                        dshd.getInt("maKH"),
                        dtf.format(
                                dshd.getTimestamp(
                                        "thoiGianLap"
                                ).toLocalDateTime()
                        ),
                        dshd.getInt("tongSLSP"),
                        nf.format(dshd.getDouble("tongTien"))
                };

                dtmHDBHTimDuoc.addRow(o);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        datKichThuocChoPmnChuaDSHDBHTimDuoc(count + 1);
    }

    private void dungPnlHopCongCu(){
        pnlHopCongCu.setBackground(bgrPnlChinh);
        pnlHopCongCu.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnlHopCongCu.setPreferredSize(dimPnlHopCongCu);

        CacHamDungSan.datThuocTinhChoBtn(
                btnTaoHoaDonBanHang,
                bgrBtnThem,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnThanhCongCu);
        datHanhDongChoBtnTaoHoaDonBanHang();
        pnlHopCongCu.add(btnTaoHoaDonBanHang);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatDuLieuTrongTableRaFile,
                bgrBtnXuatDuLieuRaFile,
                IDSBienMacDinh.bgrMacDinh,
                dimBtnThanhCongCu
        );
        datHanhDongChoBtnXuatDuLieuRaFile();

        pnlHopCongCu.add(Box.createHorizontalStrut(5));
        pnlHopCongCu.add(btnXuatDuLieuTrongTableRaFile);

        dungPmnXuatData();
    }

    private void datHanhDongChoBtnTaoHoaDonBanHang(){
        btnTaoHoaDonBanHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (GDTaoHoaDonBanHang.isDangHienThi()){
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_BINH_THUONG,
                            "Bạn có một hoá đơn bán hàng đang dang dở."
                    );
                }
                else{

                    GDTaoHoaDonBanHang gd = GDTaoHoaDonBanHang.getGdTaoHoaDonBanHang();

                    gd.setVisible(true);
                }
            }
        });
    }

    private void datHanhDongChoBtnXuatDuLieuRaFile(){
        btnXuatDuLieuTrongTableRaFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    pmnXuatData.show(
                            btnXuatDuLieuTrongTableRaFile,
                            0,
                            btnXuatDuLieuTrongTableRaFile.getHeight()
                    );
                });
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
                        IDSBienMacDinh.THONG_KE_HOA_DON_BAN_HANG,
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
                        IDSBienMacDinh.THONG_KE_HOA_DON_BAN_HANG,
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
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDuLieuTraCuuDuoc,
                dimScrChuaTableDulieuTraCuuDuoc,
                trsDuLieuTraCuuDuoc
        );
        datHanhDongChoTblDSHoaDonBanHang(tblDuLieuTraCuuDuoc);
        canLeChoTableTuyTheoDangDuLieu();

        scrChuaTableDuLieuTraCuuDuoc.setPreferredSize(dimScrChuaTableDulieuTraCuuDuoc);
        scrChuaTableDuLieuTraCuuDuoc.getViewport().setBackground(bgrPnlChinh);
    }

    private void canLeChoTableTuyTheoDangDuLieu(){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblDuLieuTraCuuDuoc.getColumnModel();

        for (int i = 0; i < tblDuLieuTraCuuDuoc.getColumnCount(); ++i) {
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


    private void capNhatDuLieuTongKet(int tongSoHD, int tongSLSP, double thanhTienChuaThue, double thueGTGT, double tongTien){
        df.applyPattern(pt);

        Object[] duLieuSauTongKet = {
                "Tổng cộng:",
                df.format(tongSoHD) + " hoá đơn",
                "",
                "",
                df.format(tongSLSP),
                nf.format(thanhTienChuaThue),
                nf.format(thueGTGT),
                nf.format(tongTien)
        };

        dtmDuLieuTraCuuDuoc.insertRow(0, duLieuSauTongKet);
    }

    private List<JRadioButton> btnsLocTheoTongTien = Arrays.asList(radTatCaMucTien, radDuoi1tr,
            radTu1trDen3tr, radTu3trDen7tr, radTren7tr);
    private List<JRadioButton> btnsLocTheoThoiGianBanHang = Arrays.asList(radLocTheoMocThoiGian, radTuyChonThoiGian);

    public void datHanhDongChoRadioButtons() {
        radTatCaMucTien.setName("TatCaGia");
        radDuoi1tr.setName("Duoi1tr");
        radTu1trDen3tr.setName("1trDen3Tr");
        radTu3trDen7tr.setName("3TrDen7Tr");
        radTren7tr.setName("Tren7Tr");

        radLocTheoMocThoiGian.setName("LocTheoMoc");
        radTuyChonThoiGian.setName("TuyChon");

        btnsLocTheoTongTien.forEach(btn -> btn.addActionListener(this));
        btnsLocTheoThoiGianBanHang.forEach(btn -> btn.addActionListener(this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoTongTien = new AtomicInteger(-1);
        AtomicInteger locTheoThoiGianBH = new AtomicInteger(-1);

        btnsLocTheoTongTien.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("Duoi1tr"))
                    locTheoTongTien.set(0);
                else if(btn.getName().equals("1trDen3Tr"))
                    locTheoTongTien.set(1);
                else if(btn.getName().equals("3TrDen7Tr"))
                    locTheoTongTien.set(2);
                else if(btn.getName().equals("Tren7Tr"))
                    locTheoTongTien.set(3);
            }
        });

        btnsLocTheoThoiGianBanHang.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("LocTheoMoc")) {
                    locTheoThoiGianBH.set(1);
                } else
                    locTheoThoiGianBH.set(0);
            }
        });

        capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoTongTien.get(), locTheoThoiGianBH.get()));
    }

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

            AtomicInteger tongSoHD = new AtomicInteger(0);
            AtomicInteger tongSLSP = new AtomicInteger(0);
            AtomicReference<Double> thanhTienChuaThue = new AtomicReference<>(0.0);
            AtomicReference<Double> thueGTGT = new AtomicReference<>(0.0);
            AtomicReference<Double> tongTien = new AtomicReference<Double>(Double.valueOf(0));

            int maHDBHLonNhat = TienIch.laySoLonNhat("maHDBH", "maHDBH", "HoaDonBanHang");

            while(res.next()) {
                dtmDuLieuTraCuuDuoc.addRow(
                        new Object[] {
                                TienIch.dinhDangSo(maHDBHLonNhat, res.getInt("maHDBH")),
                                res.getString("maNhanVienLapHDBH"),
                                MaHoaDuLieu.giaiMa(res.getString("soDT")),
                                TienIch.dinhDangNgay(res.getTimestamp("thoiGianLap").toLocalDateTime().toLocalDate(), "dd-MM-yyyy"),
                                res.getInt("tongSLSP"),
                                nf.format(res.getDouble("thanhTienChuaThue")),
                                nf.format(res.getDouble("thueGTGT")),
                                nf.format(res.getDouble("tongTien"))
                        });

                tongSoHD.getAndIncrement();
                tongSLSP.getAndAdd(res.getInt("tongSLSP"));
                thanhTienChuaThue.getAndSet(thanhTienChuaThue.get() + res.getDouble("thanhTienChuaThue"));
                thueGTGT.getAndSet(thueGTGT.get() + res.getDouble("thueGTGT"));
                tongTien.getAndSet(tongTien.get() + res.getDouble("tongTien"));
            }

            capNhatDuLieuTongKet(tongSoHD.get(), tongSLSP.get(), thanhTienChuaThue.get(), thueGTGT.get(), tongTien.get());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String sinhCauTruyVanCSDL(int locTheoTongTien, int locTheoTGBH) {
        String query = "select hd.maHDBH, hd.maNhanVienLapHDBH," +
                "kh.soDT, hd.thoiGianLap," +
                "hd.tongSLSP, hd.thanhTienChuaThue, hd.thueGTGT, hd.tongTien " +
                "from HoaDonBanHang hd join KhachHang kh on kh.maKH = hd.maKH ";

        if(locTheoTongTien != -1 && locTheoTGBH != -1) {
            if(locTheoTGBH == 1)
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoTongTien, locTheoTGBH, cbbCacMocThoiGian.getSelectedIndex());
            else if(locTheoTGBH == 0)
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoTongTien);
        }
        else if(locTheoTongTien == -1 && locTheoTGBH != -1) {
            if(locTheoTGBH == 1)
                query += sinhCauTruyVanLocTheoMocThoiGian(locTheoTongTien, locTheoTGBH, cbbCacMocThoiGian.getSelectedIndex());
            else if(locTheoTGBH == 0)
                query += sinhCauTruyVanLocTheoTuyChonNgay(locTheoTongTien);
        }
        else if(locTheoTongTien != -1 && locTheoTGBH == -1) {
            query += String.format("where %s", sinhCauTruyVanLocTheoTongTien(locTheoTongTien));
        }

        query += " order by hd.maHDBH";

        return query;
    }

    private String sinhCauTruyVanLocTheoMocThoiGian(int locTheoTongTien, int locTheoTGBH, int cheDoLocMocThoiGian) {
        String query = "";
        String dieuKien = "where";
        String tongTien = sinhCauTruyVanLocTheoTongTien(locTheoTongTien);

        if(locTheoTongTien != -1 && locTheoTGBH != -1) {
            query = String.format("where %s ", tongTien);
            dieuKien = "and";
        }

        LocalDate homNay = LocalDate.now();
        if(cheDoLocMocThoiGian == 0) {
            query += String.format("%s cast(thoiGianLap as date) = '%s'", dieuKien, TienIch.dinhDangNgay(homNay, "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 1) {
            query += String.format("%s thoiGianLap >= '%s' and thoiGianLap < '%s'", dieuKien, TienIch.getNgayDauTrongTuan(),
                    TienIch.dinhDangNgay(homNay.plusDays(1), "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 2) {
            query += String.format("%s month(thoiGianLap) = '%s' and year(thoiGianLap) = '%s'",
                    dieuKien, homNay.getMonthValue(), homNay.getYear());
        }

        return query;
    }

    private String sinhCauTruyVanLocTheoTuyChonNgay(int locTheoTongTien){
        String query = "";
        String truyVanTheoNgay = String.format("thoiGianLap >= '%s' and thoiGianLap < '%s'",
                TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));

        if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate())){
            truyVanTheoNgay = String.format("cast (thoiGianLap as date) = '%s'", TienIch.dinhDangNgay(dtpNgayKetThuc.getDate(), "yyyy-MM-dd"));
        }

        String tongTien = sinhCauTruyVanLocTheoTongTien(locTheoTongTien);

        if(locTheoTongTien != -1)
            query += String.format("where %s and %s", tongTien, truyVanTheoNgay);
        else
            query += String.format("where %s", truyVanTheoNgay);

        return query;
    }

    private String sinhCauTruyVanLocTheoTongTien(int locTheoTongTien) {
        String tongTien = "";

        if(locTheoTongTien == 0)
            tongTien = "tongTien < 1000000";
        else if(locTheoTongTien == 1)
            tongTien = "tongTien >= 1000000 and tongTien <= 3000000";
        else if(locTheoTongTien == 2)
            tongTien = "tongTien > 3000000 and tongTien <= 7000000";
        else if(locTheoTongTien == 3)
            tongTien = "tongTien > 7000000";

        return tongTien;
    }

    public static void datHanhDongChoTblDSHoaDonBanHang(JTable tbl){
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == 1){
                    int hangDuocChon = tbl.getSelectedRow();

                    if (
                            hangDuocChon != -1 &&
                            !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(tbl, hangDuocChon)
                    ){
                        int maHDBH = Integer.parseInt(tbl.getValueAt(hangDuocChon, 0).toString());

                        HoaDonBanHang hd = HoaDonBanHangDAO.timHoaDonBanHangTheoMa(maHDBH);

                        SwingUtilities.invokeLater(() -> {
                            GDXemThongTinHoaDonBanHang gd = GDXemThongTinHoaDonBanHang.getGdXemTTHoaDonBanHang();

                            GDXemThongTinHoaDonBanHang.hienThiTTHDBanHang(hd);

                            gd.setVisible(true);
                        });
                    }
                }
            }
        });
    }
}