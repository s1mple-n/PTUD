package ui.giaodienchinh.pnlqlnhaphang;

import connectDB.KetNoiCSDL;
import dao.HoaDonNhapHangDAO;
import entity.HoaDonNhapHang;
import services.CacHamDungSan;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.GDTaoHoaDonNhapHang;
import ui.giaodienchinh.pnlqlnhaphang.gdxemthongtinhoadonnhaphang.GDXemThongTinHoaDonNhapHang;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PnlQLNhapHang implements IDSBienPnlQLNhapHang, ActionListener {
    private static JPanel pnlQLNhapHang = null;

    private JPanel dungPnlQLNhapHang(){
        pnlQuanLiNhapHang.setBackground(bgrPnlChinh);
        pnlQuanLiNhapHang.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlQuanLiNhapHang.setPreferredSize(dimPnlQuanLiNhapHang);

        dungPnlThanhTienIch();
        pnlQuanLiNhapHang.add(pnlThanhTienIch);

        dungPnlNoiDungQLNHChinh();
        pnlQuanLiNhapHang.add(pnlNoiDungQLNHChinh);

        return pnlQuanLiNhapHang;
    }

    public static JPanel getPnlQLNhapHang() {
        if (pnlQLNhapHang == null){
            pnlQLNhapHang = new PnlQLNhapHang().dungPnlQLNhapHang();
        }
        return pnlQLNhapHang;
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

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoTongTien, lblTieuDeLocTheoTongTien);
        pnlLocTheoTongTien.add(pnlTieuDeLocTheoTongTien);

        dungPnlLuaChonLocTheoTongTien();
        pnlLocTheoTongTien.add(pnlLuaChonLocTheoTongTien);
    }

    private void dungPnlLuaChonLocTheoTongTien(){
        pnlLuaChonLocTheoTongTien.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoTongTien.setLayout(new FlowLayout(FlowLayout.LEADING, khoangCachSoVoiLeTraiCuaTieuDe, khoangCachSoVoiLeTrenCuaTieuDe));
        pnlLuaChonLocTheoTongTien.setPreferredSize(dimPnlLuaChonLocTheoTongTien);

        tapHopThanhVienCuaBngLocTheoTongTien();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaMucTien);
        pnlLuaChonLocTheoTongTien.add(radTatCaMucTien);

        CacHamDungSan.datThuocTinhChoCacRad(radDuoi8tr);
        pnlLuaChonLocTheoTongTien.add(radDuoi8tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu8trDen20tr);
        pnlLuaChonLocTheoTongTien.add(radTu8trDen20tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu20trDen40tr);
        pnlLuaChonLocTheoTongTien.add(radTu20trDen40tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTren40tr);
        pnlLuaChonLocTheoTongTien.add(radTren40tr);
    }

    private void tapHopThanhVienCuaBngLocTheoTongTien(){
        bngLocTheoTongTien.add(radTatCaMucTien);
        bngLocTheoTongTien.add(radDuoi8tr);
        bngLocTheoTongTien.add(radTu8trDen20tr);
        bngLocTheoTongTien.add(radTu20trDen40tr);
        bngLocTheoTongTien.add(radTren40tr);
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

    private void dungPnlNoiDungQLNHChinh(){
        pnlNoiDungQLNHChinh.setBackground(bgrPnlChinh);
        pnlNoiDungQLNHChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungQLNHChinh.setPreferredSize(dimPnlNoiDungQLNHChinh);

        dungPnlThanhCongCu();
        pnlNoiDungQLNHChinh.add(pnlThanhCongCu);

        dungPnlKetQuaTraCuu();
        pnlNoiDungQLNHChinh.add(pnlKetQuaTraCuu);
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
        txtTimKiem.setToolTipText("Tìm hoá đơn nhập hàng qua mã lô hàng hoặc mã nhân viên lập");
        pnlThanhCongCu.add(txtTimKiem);

        dungPmnCheDoTimKiem();

        dungPnlHopCongCu();
        pnlThanhCongCu.add(pnlHopCongCu);

        dungPmnKetQuaTimKiemHDNH();
    }

    private void datHanhDongChoTxtTimKiem(){
        txtTimKiem.addKeyListener(cheDoTimKiemTrongCSDL);

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

    private void dungPmnKetQuaTimKiemHDNH(){
        pmnChuaDSHDNHTimDuoc.setBackground(bgrPnlChinh);
        pmnChuaDSHDNHTimDuoc.setBorder(BorderFactory.createEmptyBorder());
        pmnChuaDSHDNHTimDuoc.setBorder(
                BorderFactory.createLineBorder(bgrVienMacDinh, 1)
        );

        dungTblChuaDSHDNHTimDuoc();
        datHanhDongChoTbl(tblHDNHTimDuoc);
        pmnChuaDSHDNHTimDuoc.add(tblHDNHTimDuoc.getTableHeader());
        pmnChuaDSHDNHTimDuoc.add(tblHDNHTimDuoc, BorderLayout.CENTER);
    }

    private void dungTblChuaDSHDNHTimDuoc(){
        tblHDNHTimDuoc.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblHDNHTimDuoc.setFont(fntMacDinh);
        tblHDNHTimDuoc.setBackground(bgrMacDinh);
        tblHDNHTimDuoc.setDefaultEditor(Object.class, null);
        tblHDNHTimDuoc.getTableHeader().setPreferredSize(
                new Dimension(
                        chieuRongPmnChuaDSHDNHTimDuoc,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblHDNHTimDuoc.getTableHeader().setFont(fntMacDinh);
        tblHDNHTimDuoc.getTableHeader().setBackground(bgrTieuDeTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblHDNHTimDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(0).setMaxWidth(100);
        tcm.getColumn(0).setCellRenderer(centerRenderer);

        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(1).setMaxWidth(100);

        tcm.getColumn(2).setPreferredWidth(90);
        tcm.getColumn(2).setMaxWidth(90);

        tcm.getColumn(3).setPreferredWidth(200);
        tcm.getColumn(3).setMaxWidth(200);

        tcm.getColumn(4).setPreferredWidth(70);
        tcm.getColumn(4).setMaxWidth(70);
        tcm.getColumn(4).setCellRenderer(rightRenderer);

        tcm.getColumn(5).setCellRenderer(rightRenderer);
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
                    pmnChuaDSHDNHTimDuoc.setVisible(false);
                }
                else{
                    ResultSet dsTimDuoc = HoaDonNhapHangDAO.timDSHoaDonNhapHangTheoMaNVHoacMaLoHang(tuKhoa);

                    try {
                        if (dsTimDuoc.isBeforeFirst()){
                            duaDuLieuVaoTable(dsTimDuoc);

                            pmnChuaDSHDNHTimDuoc.setVisible(true);

                            tblHDNHTimDuoc.setEnabled(true);

                            pmnChuaDSHDNHTimDuoc.show(
                                    txtTimKiem,
                                    0,
                                    txtTimKiem.getHeight()
                            );

                            txtTimKiem.requestFocus();
                        }
                        else{
                            tblHDNHTimDuoc.setEnabled(false);

                            dtmHDNHTimDuoc.setRowCount(0);

                            Object[] thongBaoKhongTimRaHDBHNao = {
                                    "",
                                    "",
                                    "( ^ _ ^ )",
                                    "",
                                    ""
                            };

                            dtmHDNHTimDuoc.addRow(thongBaoKhongTimRaHDBHNao);

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
        pmnChuaDSHDNHTimDuoc.setPopupSize(
                new Dimension(
                        chieuRongPmnChuaDSHDNHTimDuoc,
                        chieuCaoHangDuLieuTrongTable * slKetQua
                )
        );
    }

    private void duaDuLieuVaoTable(ResultSet dshd){
        dtmHDNHTimDuoc.setRowCount(0);

        int count = 0;

        try {
            while (dshd.next()){
                Object[] o = {
                        dshd.getInt("maHDNH"),
                        dshd.getString("maNhanVienLapHDNH"),
                        dshd.getInt("maLoHang"),
                        dtf.format(
                                dshd.getTimestamp("thoiGianGiaoHang").toLocalDateTime()
                        ),
                        df.format(
                                dshd.getInt("tongSLSP")
                        ),
                        nf.format(dshd.getDouble("tongTien"))
                };

                count++;

                dtmHDNHTimDuoc.addRow(o);
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
                btnTaoHoaDonNhapHang,
                bgrBtnThem,
                bgrMacDinh,
                dimBtnThanhCongCu
        );
        datHanhDongChoBtnTaoHoaDonNhapHang();
        pnlHopCongCu.add(btnTaoHoaDonNhapHang);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatDuLieuTrongTableRaFile,
                bgrBtnXuatDuLieuRaFile,
                bgrMacDinh,
                dimBtnThanhCongCu
        );
        datHanhDongChoBtnXuatDuLieuRaFile();

        pnlHopCongCu.add(Box.createHorizontalStrut(5));
        pnlHopCongCu.add(btnXuatDuLieuTrongTableRaFile);

        dungPmnXuatData();
    }

    private void datHanhDongChoBtnTaoHoaDonNhapHang(){
        btnTaoHoaDonNhapHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if (!GDTaoHoaDonNhapHang.isDangHienThi()){
                   SwingUtilities.invokeLater(() -> {
                       GDTaoHoaDonNhapHang gd = GDTaoHoaDonNhapHang.getGdTaoHoaDonNhapHang();
                       gd.setVisible(true);
                   });
               }
               else{
                   CacHamDungSan.hienThiThongBaoKetQua(
                           GDThongBaoKetQua.THONG_BAO_BINH_THUONG,
                           "Bạn có một hoá đơn nhập hàng đang dang dở."
                   );
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
                TienIch.xuatDataTrongTableRaFile(
                        tblDuLieuTraCuuDuoc,
                        true,
                        IDSBienMacDinh.THONG_KE_HOA_DON_NHAP_HANG,
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
                        IDSBienMacDinh.THONG_KE_HOA_DON_NHAP_HANG,
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
        canLeChoTableTuyTheoDangDuLieu();
        datThuocTinhChoTieuDeCuaTable();
        datHanhDongChoTbl(tblDuLieuTraCuuDuoc);

        scrChuaTableDuLieuTraCuuDuoc.setPreferredSize(dimScrChuaTableDulieuTraCuuDuoc);
        scrChuaTableDuLieuTraCuuDuoc.getViewport().setBackground(bgrMacDinh);
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
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < tblDuLieuTraCuuDuoc.getColumnCount(); ++i) {
            if (i == 5 || i == 6){
                tblDuLieuTraCuuDuoc
                        .getColumnModel()
                        .getColumn(i)
                        .setCellRenderer(rightRenderer);
            }
            else{
                tblDuLieuTraCuuDuoc
                        .getColumnModel()
                        .getColumn(i)
                        .setCellRenderer(leftRenderer);
            }
        }
    }

    private void themHangTongKetDuLieuVaoTable(int tongSoHD, int tongSLSP, double tongTien){
        df.applyPattern(pt);

        Object[] duLieuSauTongKet = {
                "Tổng cộng:",
                df.format(tongSoHD) + " hoá đơn",
                "",
                "",
                "",
                df.format(tongSLSP),
                nf.format(tongTien)
        };

        dtmDuLieuTraCuuDuoc.insertRow(0, duLieuSauTongKet);
    }

    private List<JRadioButton> btnsLocTheoTongTien = Arrays.asList(radTatCaMucTien, radDuoi8tr,
            radTu8trDen20tr, radTu20trDen40tr, radTren40tr);
    private List<JRadioButton> btnsLocTheoThoiGianBanHang = Arrays.asList(radLocTheoMocThoiGian, radTuyChonThoiGian);

    public void datHanhDongChoRadioButtons() {
        radTatCaMucTien.setName("TatCaGia");
        radDuoi8tr.setName("Duoi3tr");
        radTu8trDen20tr.setName("3trDen10Tr");
        radTu20trDen40tr.setName("10TrDen20Tr");
        radTren40tr.setName("Tren20Tr");

        radLocTheoMocThoiGian.setName("LocTheoMoc");
        radTuyChonThoiGian.setName("TuyChon");

        btnsLocTheoTongTien.forEach(btn -> btn.addActionListener(this));
        btnsLocTheoThoiGianBanHang.forEach(btn -> btn.addActionListener(this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoTongTien = new AtomicInteger(-1);
        AtomicInteger locTheoThoiGianNH = new AtomicInteger(-1);

        btnsLocTheoTongTien.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("Duoi3tr"))
                    locTheoTongTien.set(0);
                else if(btn.getName().equals("3trDen10Tr"))
                    locTheoTongTien.set(1);
                else if(btn.getName().equals("10TrDen20Tr"))
                    locTheoTongTien.set(2);
                else if(btn.getName().equals("Tren20Tr"))
                    locTheoTongTien.set(3);
            }
        });

        btnsLocTheoThoiGianBanHang.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("LocTheoMoc")) {
                    locTheoThoiGianNH.set(1);
                } else
                    locTheoThoiGianNH.set(0);
            }
        });

        capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoTongTien.get(), locTheoThoiGianNH.get()));
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
            AtomicReference<Double> tongTien = new AtomicReference<>(0.0);

            int maHDNHLonNhat = TienIch.laySoLonNhat("maHDNH", "maHDNH", "HoaDonNhapHang");

            while(res.next()) {
                dtmDuLieuTraCuuDuoc.addRow(
                        new Object[] {
                                TienIch.dinhDangSo(maHDNHLonNhat,res.getInt("maHDNH")),
                                res.getString("maNhanVienLapHDNH"), res.getInt("maLoHang"),
                                TienIch.dinhDangNgay(res.getTimestamp("thoiGianDatHang").toLocalDateTime()
                                                .toLocalDate(), "dd-MM-yyyy"),
                                TienIch.dinhDangNgay(res.getTimestamp("thoiGianGiaoHang").toLocalDateTime()
                                                .toLocalDate(),"dd-MM-yyyy"), res.getInt("tongSLSP"),
                                nf.format(res.getDouble("tongTien"))
                        });

                tongSoHD.getAndIncrement();
                tongSLSP.getAndAdd(res.getInt("tongSLSP"));
                tongTien.getAndSet(tongTien.get() + res.getDouble("tongTien"));
            }

            themHangTongKetDuLieuVaoTable(tongSoHD.get(), tongSLSP.get(), tongTien.get());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String sinhCauTruyVanCSDL(int locTheoTongTien, int locTheoTGBH) {
        String query = "select maHDNH, maNhanVienLapHDNH, maLoHang, thoiGianDatHang, " +
                "thoiGianGiaoHang, tongSLSP, tongTien " +
                "from HoaDonNhapHang ";

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
            query += String.format("%s cast(thoiGianGiaoHang as date) = '%s'", dieuKien, TienIch.dinhDangNgay(homNay, "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 1) {
            query += String.format("%s thoiGianGiaoHang >= '%s' and thoiGianGiaoHang < '%s'", dieuKien, TienIch.getNgayDauTrongTuan(),
                    TienIch.dinhDangNgay(homNay.plusDays(1), "yyyy-MM-dd"));
        } else if(cheDoLocMocThoiGian == 2) {
            query += String.format("%s month(thoiGianGiaoHang) = '%s' and year(thoiGianGiaoHang) = '%s'",
                    dieuKien, homNay.getMonthValue(), homNay.getYear());
        }

        return query;
    }

    private String sinhCauTruyVanLocTheoTuyChonNgay(int locTheoTongTien){
        String query = "";
        String truyVanTheoNgay = String.format("thoiGianGiaoHang >= '%s' and thoiGianGiaoHang < '%s'",
                TienIch.dinhDangNgay(dtpNgayBatDau.getDate(), "yyyy-MM-dd"),
                TienIch.dinhDangNgay(dtpNgayKetThuc.getDate().plusDays(1), "yyyy-MM-dd"));

        if(dtpNgayBatDau.getDate().equals(dtpNgayKetThuc.getDate())){
            truyVanTheoNgay = String.format("cast (thoiGianGiaoHang as date) = '%s'", TienIch.dinhDangNgay(dtpNgayKetThuc.getDate(), "yyyy-MM-dd"));
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
            tongTien = "tongTien < 8000000";
        else if(locTheoTongTien == 1)
            tongTien = "tongTien >= 8000000 and tongTien <= 20000000";
        else if(locTheoTongTien == 2)
            tongTien = "tongTien > 20000000 and tongTien <= 40000000";
        else if(locTheoTongTien == 3)
            tongTien = "tongTien > 40000000";

        return tongTien;
    }

    private void datHanhDongChoTbl(JTable tbl){
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int hangDuocChon = tbl.getSelectedRow();

                if (
                        hangDuocChon != -1 &&
                        !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                tbl,
                                hangDuocChon
                        )
                ){
                    if (e.getClickCount() == 2 && e.getButton() == 1){
                        int maHDNH = Integer.parseInt(
                                tbl.getValueAt(
                                        hangDuocChon, 0
                                ).toString()
                        );

                        HoaDonNhapHang hoaDonNhapHang =HoaDonNhapHangDAO.timHoaDonNhapHangTheoMa(maHDNH);

                        SwingUtilities.invokeLater(() -> {
                            GDXemThongTinHoaDonNhapHang gd = GDXemThongTinHoaDonNhapHang.getGdXemThongTinHoaDonNhapHang();

                            GDXemThongTinHoaDonNhapHang.hienThiTTHDNhapHang(hoaDonNhapHang);

                            gd.setVisible(true);
                        });
                    }
                }
            }
        });
    }
}
