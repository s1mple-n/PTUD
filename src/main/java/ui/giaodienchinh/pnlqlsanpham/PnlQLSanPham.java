package ui.giaodienchinh.pnlqlsanpham;

import connectDB.KetNoiCSDL;
import dao.SanPhamDAO;
import entity.SanPham;
import services.CacHamDungSan;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.GDChinh;
import ui.giaodienchinh.pnlqlsanpham.gdcapnhatthongtinsanpham.GDCapNhatThongTinSanPham;
import ui.giaodienchinh.pnlqlsanpham.gdxemthongtinsanpham.GDXemThongTinSanPham;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class PnlQLSanPham implements IDSBienPnlQLSanPham, ActionListener{
    private static JPanel pnlQLSanPham = null;

    private JPanel dungPnlQLSanPham(){
        pnlQLSP.setBackground(bgrPnlChinh);
        pnlQLSP.setPreferredSize(dimPnlQLSP);
        pnlQLSP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        dungPnlThanhTienIch();
        pnlQLSP.add(pnlThanhTienIch);

        dungPnlNoiDungQLSPChinh();
        pnlQLSP.add(pnlNoiDungQLSPChinh);

        return pnlQLSP;
    }

    public static JPanel getPnlQLSanPham() {
        if (pnlQLSanPham == null)
            pnlQLSanPham = new PnlQLSanPham().dungPnlQLSanPham();
        return pnlQLSanPham;
    }

    private void dungPnlThanhTienIch(){
        pnlThanhTienIch.setBackground(bgrMacDinh);
        pnlThanhTienIch.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlThanhTienIch.setPreferredSize(dimPnlThanhTienIch);

        dungPnlLocTheoDonGia();
        pnlThanhTienIch.add(pnlLocTheoDonGia);

        dungPnlLocTheoTinhTrang();
        pnlThanhTienIch.add(pnlLocTheoTinhTrang);

        datHanhDongChoRadioButtons();
    }

    private void dungPnlLocTheoDonGia(){
        pnlLocTheoDonGia.setBackground(bgrMacDinh);
        pnlLocTheoDonGia.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoDonGia.setPreferredSize(dimPnlLocTheoDonGia);

        CacHamDungSan.dungPnlTieuDeLoc(
                pnlTieuDeLocTheoDonGia,
                lblTieuDeLocTheoDonGia
        );
        pnlLocTheoDonGia.add(pnlTieuDeLocTheoDonGia);

        dungPnlLuaChonLocTheoDonGia();
        pnlLocTheoDonGia.add(pnlLuaChonLocTheoDonGia);
    }

    private void dungPnlLuaChonLocTheoDonGia(){
        pnlLuaChonLocTheoDonGia.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoDonGia.setLayout(new FlowLayout(
                FlowLayout.LEADING,
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));
        pnlLuaChonLocTheoDonGia.setPreferredSize(dimPnlLuaChonLocTheoDonGia);

        tapHopThanhVienCuaBngLocTheoDonGia();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaMucGia);
        pnlLuaChonLocTheoDonGia.add(radTatCaMucGia);

        CacHamDungSan.datThuocTinhChoCacRad(radDuoi500k);
        pnlLuaChonLocTheoDonGia.add(radDuoi500k);

        CacHamDungSan.datThuocTinhChoCacRad(radTu500kDen2tr);
        pnlLuaChonLocTheoDonGia.add(radTu500kDen2tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu2trDen5tr);
        pnlLuaChonLocTheoDonGia.add(radTu2trDen5tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTren5tr);
        pnlLuaChonLocTheoDonGia.add(radTren5tr);
    }

    private void tapHopThanhVienCuaBngLocTheoDonGia(){
        bngLocTheoDonGia.add(radTatCaMucGia);
        bngLocTheoDonGia.add(radDuoi500k);
        bngLocTheoDonGia.add(radTu500kDen2tr);
        bngLocTheoDonGia.add(radTu2trDen5tr);
        bngLocTheoDonGia.add(radTren5tr);
    }

    private void dungPnlLocTheoTinhTrang(){
        pnlLocTheoTinhTrang.setBackground(bgrMacDinh);
        pnlLocTheoTinhTrang.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnlLocTheoTinhTrang.setPreferredSize(dimPnlLocTheoTinhTrang);

        CacHamDungSan.dungPnlTieuDeLoc(
                pnlTieuDeLocTheoTinhTrang,
                lblLocTheoTinhTrang
        );
        pnlLocTheoTinhTrang.add(pnlTieuDeLocTheoTinhTrang);

        dungPnlLuaChonLocTheoTinhTrang();
        pnlLocTheoTinhTrang.add(pnlLuaChonLocTheoTinhTrang);
    }

    private void dungPnlLuaChonLocTheoTinhTrang(){
        pnlLuaChonLocTheoTinhTrang.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoTinhTrang.setPreferredSize(dimPnlLuaChonLocTheoTinhTrang);
        pnlLuaChonLocTheoTinhTrang.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe
        ));

        tapHopThanhVienCuaBngLocTheoTinhTrang();

        CacHamDungSan.datThuocTinhChoCacRad(radMoiTinhTrang);
        pnlLuaChonLocTheoTinhTrang.add(radMoiTinhTrang);

        CacHamDungSan.datThuocTinhChoCacRad(radConHang);
        pnlLuaChonLocTheoTinhTrang.add(radConHang);

        CacHamDungSan.datThuocTinhChoCacRad(radHetHang);
        pnlLuaChonLocTheoTinhTrang.add(radHetHang);
    }

    private void tapHopThanhVienCuaBngLocTheoTinhTrang(){
        bngLocTheoTinhTrang.add(radMoiTinhTrang);
        bngLocTheoTinhTrang.add(radConHang);
        bngLocTheoTinhTrang.add(radHetHang);
    }

    private void dungPnlNoiDungQLSPChinh(){
        pnlNoiDungQLSPChinh.setBackground(bgrPnlChinh);
        pnlNoiDungQLSPChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungQLSPChinh.setPreferredSize(dimPnlNoiDungQLSPChinh);

        dungPnlThanhCongCu();
        pnlNoiDungQLSPChinh.add(pnlThanhCongCu);

        dungPnlKetQuaTraCuu();
        pnlNoiDungQLSPChinh.add(pnlKetQuaTraCuu);
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
        txtTimKiem.setToolTipText("Tìm kiếm sản phẩm qua mã hoặc tên");
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiem);
        datHanhDongChoTxtTimKiem();
        pnlThanhCongCu.add(txtTimKiem);

        dungPmnKetQuaTimKiemSanPham();

        dungPnlHopCongCu();
        pnlThanhCongCu.add(pnlHopCongCu);
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
                    pmnKetQuaTimKiemSanPham.setVisible(false);
                }
                else{
                    ResultSet dssp = SanPhamDAO.timKiemSanPhamTheoMaHoacTen(tuKhoa);

                    try {
                        if (dssp.isBeforeFirst()){
                            duaDuLieuSanPhamTimDuocVaoTable(dssp);

                            tblKetQuaTimKiemSanPham.setEnabled(true);

                            pmnKetQuaTimKiemSanPham.setVisible(true);

                            pmnKetQuaTimKiemSanPham.show(
                                    txtTimKiem,
                                    0,
                                    txtTimKiem.getHeight()
                            );

                            txtTimKiem.requestFocus();
                        }
                        else {
                            tblKetQuaTimKiemSanPham.setEnabled(false);

                            dtmKetQuaTimKiemSanPham.setRowCount(0);

                            Object[] duLieuTrong = {
                                    "",
                                    "( ^ _ ^ )",
                                    ""
                            };
                            dtmKetQuaTimKiemSanPham.addRow(duLieuTrong);

                            datKichThuocChoPmnKetQuaTimKiemSP(2);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
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

    private void duaDuLieuSanPhamTimDuocVaoTable(ResultSet dssp){
        dtmKetQuaTimKiemSanPham.setRowCount(0);

        int count = 0;

        try {
            while (dssp.next()){
                Object[] o = {
                        dssp.getString("maSP"),
                        dssp.getString("tenSP"),
                        dssp.getString("thuongHieu"),
                        nf.format(dssp.getDouble("donGia")),
                        dssp.getInt("soLuongTon")
                };

                count++;

                dtmKetQuaTimKiemSanPham.addRow(o);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        datKichThuocChoPmnKetQuaTimKiemSP(count + 1);
    }

    private void dungPmnKetQuaTimKiemSanPham(){
        pmnKetQuaTimKiemSanPham.setBackground(bgrMacDinh);
        pmnKetQuaTimKiemSanPham.setBorder(BorderFactory.createEmptyBorder());
        pmnKetQuaTimKiemSanPham.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 1
                )
        );

        dungTblKetQuaTimKiemSanPham();
        datHanhDongChoTblKetQuaTimKiemSP();
        pmnKetQuaTimKiemSanPham.add(tblKetQuaTimKiemSanPham.getTableHeader());
        pmnKetQuaTimKiemSanPham.add(tblKetQuaTimKiemSanPham, BorderLayout.CENTER);
    }

    private void dungTblKetQuaTimKiemSanPham(){
        tblKetQuaTimKiemSanPham.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblKetQuaTimKiemSanPham.setFont(fntMacDinh);
        tblKetQuaTimKiemSanPham.setDefaultEditor(Object.class, null);

        tblKetQuaTimKiemSanPham.getTableHeader().setPreferredSize(
                new Dimension(
                        chieuRongPmnKetQuaTimKiemSanPham,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblKetQuaTimKiemSanPham.getTableHeader().setBackground(bgrTieuDeTable);
        tblKetQuaTimKiemSanPham.getTableHeader().setFont(fntMacDinh);


        TableColumnModel tcm = tblKetQuaTimKiemSanPham.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(0).setMaxWidth(100);

        tcm.getColumn(2).setPreferredWidth(100);
        tcm.getColumn(2).setMaxWidth(100);

        tcm.getColumn(3).setPreferredWidth(100);
        tcm.getColumn(3).setMaxWidth(100);

        tcm.getColumn(4).setPreferredWidth(60);
        tcm.getColumn(4).setMaxWidth(60);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        tcm.getColumn(0).setCellRenderer(centerRenderer);

        tcm.getColumn(3).setCellRenderer(rightRenderer);
        tcm.getColumn(4).setCellRenderer(rightRenderer);
    }

    private void datHanhDongChoTblKetQuaTimKiemSP(){
        tblKetQuaTimKiemSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == 1){
                    int row = tblKetQuaTimKiemSanPham.getSelectedRow();

                    String maSPDuocChon = (String) tblKetQuaTimKiemSanPham.getValueAt(                          /* Lấy mã của sản phẩm tương ứng */
                            row,
                            0
                    );

                    SanPham sp = SanPhamDAO.laySanPhamTheoMa(maSPDuocChon);

                    GDXemThongTinSanPham gd = GDXemThongTinSanPham.getGdXemTTSanPham();

                    GDXemThongTinSanPham.hienThiThongTinSanPham(sp);

                    gd.setVisible(true);
                }
            }
        });
    }

    private void datKichThuocChoPmnKetQuaTimKiemSP(int sl){
        pmnKetQuaTimKiemSanPham.setPopupSize(new Dimension(
                chieuRongPmnKetQuaTimKiemSanPham,
                sl * chieuCaoHangDuLieuTrongTable
        ));
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
        datHanhDongChoBtnXuatDuLieuRaExcel();
        pnlHopCongCu.add(btnXuatDuLieuTrongTableRaFile);

        dungPmnXuatData();
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
                TienIch.xuatDataTrongTableRaFile(
                        tblDuLieuTraCuuDuoc,
                        true,
                        IDSBienMacDinh.THONG_KE_SAN_PHAM,
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
                        IDSBienMacDinh.THONG_KE_SAN_PHAM,
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
        dinhDangDoRongChoCacCotCuaTblDuLieuTraCuuDuoc();
        datHanhDongChoTblDuLieuTraCuuDuoc();

        scrChuaTableDuLieuTraCuuDuoc.setPreferredSize(dimScrChuaTableDulieuTraCuuDuoc);
        scrChuaTableDuLieuTraCuuDuoc.getViewport().setBackground(bgrMacDinh);
    }

    private void canLeChoTableTuyTheoDangDuLieu(){
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < tblDuLieuTraCuuDuoc.getColumnCount(); ++i) {
            if (i == 4 || i == 5){
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

    private void dinhDangDoRongChoCacCotCuaTblDuLieuTraCuuDuoc(){
        TableColumnModel tcm = tblDuLieuTraCuuDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(120);
        tcm.getColumn(0).setMaxWidth(120);

        tcm.getColumn(2).setPreferredWidth(150);
        tcm.getColumn(2).setMaxWidth(150);

        tcm.getColumn(3).setPreferredWidth(120);
        tcm.getColumn(3).setMaxWidth(120);

        tcm.getColumn(4).setPreferredWidth(120);
        tcm.getColumn(4).setMaxWidth(120);

        tcm.getColumn(5).setPreferredWidth(120);
        tcm.getColumn(5).setMaxWidth(120);
    }

    private void datHanhDongChoTblDuLieuTraCuuDuoc(){
        tblDuLieuTraCuuDuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int hangDuocChon = tblDuLieuTraCuuDuoc.getSelectedRow();

                if (
                        hangDuocChon != -1 &&
                        !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                tblDuLieuTraCuuDuoc,
                                hangDuocChon
                        )
                ){
                    int nutDuocChon = e.getButton();

                    int soLanClick = e.getClickCount();

                    String maSP = (String) tblDuLieuTraCuuDuoc.getValueAt(
                            hangDuocChon, 0
                    );

                    SanPham sanPham = SanPhamDAO.laySanPhamTheoMa(maSP);

                    if (nutDuocChon == 1 && soLanClick == 2){
                        SwingUtilities.invokeLater(() -> {
                            GDXemThongTinSanPham gd = GDXemThongTinSanPham.getGdXemTTSanPham();

                            GDXemThongTinSanPham.hienThiThongTinSanPham(sanPham);

                            gd.setVisible(true);
                        });
                    }
                    else if (nutDuocChon == 3 && soLanClick == 2){
                        if (GDChinh.getNhanVienDangSuDung().isQuanLi()){
                            SwingUtilities.invokeLater(() -> {
                                GDCapNhatThongTinSanPham gd = GDCapNhatThongTinSanPham.getGdCapNhatThongTinSanPham();

                                GDCapNhatThongTinSanPham.hienThiThongTinSanPham(sanPham);

                                gd.setVisible(true);
                            });
                        }
                    }
                }
            }
        });
    }

    private static final java.util.List<JRadioButton> btnsLocTheoGia = Arrays.asList(radTatCaMucGia, radDuoi500k,
            radTu500kDen2tr, radTu2trDen5tr, radTren5tr);
    private static final java.util.List<JRadioButton> btnsLocTheoTinhTrang = Arrays.asList(radMoiTinhTrang, radConHang, radHetHang);

    /**
     * @author Hiếu
     * <p><i>
     * Hàm này có chức năng tên cho các radio button<br>
     * và thêm sự kiện cho tất cả các radio button đó
     * </i></p>
     */
    public void datHanhDongChoRadioButtons() {
        radTatCaMucGia.setName("TatCaGia");
        radDuoi500k.setName("Duoi500");
        radTu500kDen2tr.setName("500Den2Tr");
        radTu2trDen5tr.setName("2TrDen5Tr");
        radTren5tr.setName("Tren5Tr");

        radMoiTinhTrang.setName("MoiTinhTrang");
        radConHang.setName("ConHang");
        radHetHang.setName("HetHang");

        btnsLocTheoGia.forEach(btn -> btn.addActionListener(this));

        btnsLocTheoTinhTrang.forEach(btn -> btn.addActionListener(this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AtomicInteger locTheoGia = new AtomicInteger(-1);
        AtomicInteger LocTheoTinhTrang = new AtomicInteger(-1);

        btnsLocTheoGia.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("Duoi500"))
                    locTheoGia.set(0);
                else if(btn.getName().equals("500Den2Tr"))
                    locTheoGia.set(1);
                else if(btn.getName().equals("2TrDen5Tr"))
                    locTheoGia.set(2);
                else if(btn.getName().equals("Tren5Tr"))
                    locTheoGia.set(3);
            }
        });

        btnsLocTheoTinhTrang.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.getName().equals("ConHang")) {
                    LocTheoTinhTrang.set(1);
                } else if(btn.getName().equals("HetHang"))
                    LocTheoTinhTrang.set(0);
            }
        });

        capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoGia.get(), LocTheoTinhTrang.get()));
    }

    private String sinhCauTruyVanCSDL(int locTheoGia, int locTheoTinhTrang) {
        String query = "Select * from SanPham ";

        String soSanhSLT = "";
        String donGia = "";

        if(locTheoTinhTrang == 0)
            soSanhSLT = "soLuongTon = 0";
        else if(locTheoTinhTrang == 1)
            soSanhSLT = "soLuongTon <> 0";

        if(locTheoGia == 0)
            donGia = "donGia < 500000";
        else if(locTheoGia == 1)
            donGia = "donGia >= 500000 and donGia <= 2000000";
        else if(locTheoGia == 2)
            donGia = "donGia > 2000000 and donGia <= 5000000";
        else if(locTheoGia == 3)
            donGia = "donGia > 5000000";

        if(locTheoGia != -1 && locTheoTinhTrang != -1) {
            query += String.format("where %s and %s", donGia, soSanhSLT);
        }
        else if(locTheoGia == -1 && locTheoTinhTrang != -1) {
            query += String.format("where %s", soSanhSLT);
        }
        else if(locTheoGia != -1 && locTheoTinhTrang == -1) {
            query += String.format("where %s", donGia);
        }

        return query;
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

            AtomicInteger tongSLT = new AtomicInteger(0);

            while(res.next()) {
                SanPham sp = new SanPham(
                    res.getString("maSP"), res.getString("tenSP"), res.getString("mauSac"),
                        res.getString("size"), res.getString("chatLieu"), res.getDouble("donGia"),
                        res.getString("thuongHieu"), res.getString("nguonGoc"), res.getInt("soLuongTon"),
                        res.getString("moTa")
                );

                tongSLT.getAndAdd(sp.getSoLuongTon());

                dtmDuLieuTraCuuDuoc.addRow(chuyenDuLieuTruocKhiLoadLenTable(sp));
            }

            themDongTongKetKetQuaTimKiemVaoHangSo0CuaTable(tblDuLieuTraCuuDuoc.getRowCount() - 1, tongSLT.get());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Object[] chuyenDuLieuTruocKhiLoadLenTable(SanPham sp) throws SQLException {
        NumberFormat nf = NumberFormat.getCurrencyInstance(
                new Locale("vi", "vn")
        );

        return new Object[] {
                sp.getMaSP(), sp.getTenSP(), sp.getChatLieu(), sp.getThuongHieu(), nf.format(sp.getDonGia()), sp.getSoLuongTon()
        };
    }

    private void themDongTongKetKetQuaTimKiemVaoHangSo0CuaTable(int slsp, int tongSLT){
        Object[] o = {
                "Tổng cộng:",
                slsp + " danh mục",
                "",
                "",
                "",
                df.format(tongSLT)
        };

        dtmDuLieuTraCuuDuoc.insertRow(0, o);
    }

    public static void locLaiDuLieuSauKhiThemHoacCapNhat(){
        btnsLocTheoGia.forEach(btn -> {
            if (btn.isSelected())
                btn.doClick();
        });

        btnsLocTheoTinhTrang.forEach(btn -> {
            if (btn.isSelected())
                btn.doClick();
        });
    }
}
