package ui.giaodienchinh.pnllapthongke.pnlthongkesanpham;

import connectDB.KetNoiCSDL;
import dao.SanPhamDAO;
import entity.SanPham;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlsanpham.gdxemthongtinsanpham.GDXemThongTinSanPham;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PnlThongKeSanPham implements IDSBienPnlThongKeSanPham, ActionListener{

    public void dungPnlTKSanPham(JPanel pnlTKHangBanChay){
        pnlTKHangBanChay.setBackground(bgrPnlChinh);
        pnlTKHangBanChay.setPreferredSize(dimTabNoiDung);
        pnlTKHangBanChay.setLayout(new FlowLayout(
                FlowLayout.CENTER, 0, 0
        ));

        dungPnlThanhTienIch();
        pnlTKHangBanChay.add(pnlThanhTienIchTKSanPham);

        pnlTKHangBanChay.add(Box.createHorizontalStrut(5));

        dungPnlNoiDungTKDoanhThuTheoKhachHang();
        pnlTKHangBanChay.add(pnlNoiDungTKDoanhThuTheoSanPham);

        datHanhDongChoRadioButtons();
    }

    private void dungPnlThanhTienIch(){
        pnlThanhTienIchTKSanPham.setBackground(bgrMacDinh);
        pnlThanhTienIchTKSanPham.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        0,
                        0
                )
        );
        pnlThanhTienIchTKSanPham.setPreferredSize(dimPnlThanhTienIchTKSanPham);

        dungPnlSanPhamCanThongKe();
        pnlThanhTienIchTKSanPham.add(pnlSanPhamCanThongKe);

        dungPnlLocTheoThoiGian();
        pnlThanhTienIchTKSanPham.add(pnlLocTheoThoiGian);
    }

    private void dungPnlSanPhamCanThongKe(){
        pnlSanPhamCanThongKe.setBackground(bgrMacDinh);
        pnlSanPhamCanThongKe.setPreferredSize(dimPnlSanPhamCanThongKe);
        pnlSanPhamCanThongKe.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoDoiTuong, lblTieuDeLocTheoDoiTuong);
        pnlSanPhamCanThongKe.add(pnlTieuDeLocTheoDoiTuong);

        dungPnlLuaChonLocTheoDoiTuong();
        pnlSanPhamCanThongKe.add(pnlLuaChonLocTheoDoiTuong);

        dungPnlLocTheoDoanhSo();

        dungPnlLocTheoTongTienHDBH();

        dungPmnKetQuaTimKiemSanPham();
    }

    private void dungPnlLuaChonLocTheoDoiTuong(){
        pnlLuaChonLocTheoDoiTuong.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoDoiTuong.setPreferredSize(dimPnlLuaChonLocTheoDoiTuong);
        pnlLuaChonLocTheoDoiTuong.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                5
        ));

        bngLocTheoDoiTuong.add(radLocTheoMoiSanPham);
        bngLocTheoDoiTuong.add(radLocTheoTungSanPham);

        CacHamDungSan.datThuocTinhChoCacRad(radLocTheoMoiSanPham);
        datHanhDongChoRadMoiSanPham();
        pnlLuaChonLocTheoDoiTuong.add(radLocTheoMoiSanPham);

        dungPnlLocTheoTungSanPham();
        pnlLuaChonLocTheoDoiTuong.add(pnlLocTheoTungSanPham);
    }

    private void dungPnlLocTheoTungSanPham(){
        pnlLocTheoTungSanPham.setBackground(bgrMacDinh);
        pnlLocTheoTungSanPham.setLayout(
                new BoxLayout(pnlLocTheoTungSanPham, BoxLayout.X_AXIS)
        );

        CacHamDungSan.datThuocTinhChoCacRad(
                radLocTheoTungSanPham
        );
        datHanhDongChoRadLocTheoTungSanPham();
        pnlLocTheoTungSanPham.add(radLocTheoTungSanPham);

        pnlLocTheoTungSanPham.add(Box.createHorizontalStrut(4));

        txtLocTheoTungSanPham.setEnabled(false);
        CacHamDungSan.datThuocTinhChoTxt(
                txtLocTheoTungSanPham,
                "Tìm kiếm SP",
                dimTxtLocTheoTungSanPham
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtLocTheoTungSanPham);
        txtLocTheoTungSanPham.setToolTipText("Tìm sản phẩm theo mã");
        datHanhDongChoTxtLocTheoTungSanPham();
        pnlLocTheoTungSanPham.add(txtLocTheoTungSanPham);
    }

    private void datHanhDongChoTxtLocTheoTungSanPham(){
        txtLocTheoTungSanPham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String tuKhoa = txtLocTheoTungSanPham.getText().trim().toLowerCase();

                if (tuKhoa.isEmpty()){
                    pmnChuaDSSanPhamTimDuoc.setVisible(false);
                }
                else{
                    ResultSet resultSet = SanPhamDAO.timKiemSanPhamTheoMaHoacTen(tuKhoa);

                    try {
                        if (resultSet.isBeforeFirst()){
                            duaDuLieuVaoTable(resultSet);

                            pmnChuaDSSanPhamTimDuoc.setVisible(true);

                            tblDSSanPhamTimDuoc.setEnabled(true);

                            pmnChuaDSSanPhamTimDuoc.show(
                                    txtLocTheoTungSanPham,
                                    0,
                                    txtLocTheoTungSanPham.getHeight()
                            );

                            txtLocTheoTungSanPham.requestFocus();
                        }
                        else{
                            tblDSSanPhamTimDuoc.setEnabled(false);

                            dtmDSSanPhamTimDuoc.setRowCount(0);

                            Object[] thongBaoKhongTimRaSPNao = {
                                    "",
                                    "( ^ _ ^ )",
                                    "",
                                    ""
                            };

                            dtmDSSanPhamTimDuoc.addRow(thongBaoKhongTimRaSPNao);

                            datKichThuocChoPmnChuaDSSPTimDuoc(2);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        txtLocTheoTungSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungSanPham.setFocusable(true);
                CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtLocTheoTungSanPham, "");
            }
        });
    }

    private void dungPmnKetQuaTimKiemSanPham(){
        pmnChuaDSSanPhamTimDuoc.setBackground(bgrMacDinh);
        pmnChuaDSSanPhamTimDuoc.setBorder(BorderFactory.createEmptyBorder());
        pmnChuaDSSanPhamTimDuoc.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 1
                )
        );

        dungTblChuaDSSPTimDuoc();
        datHanhDongChoTblDSSPTimDuoc();
        pmnChuaDSSanPhamTimDuoc.add(tblDSSanPhamTimDuoc.getTableHeader());
        pmnChuaDSSanPhamTimDuoc.add(tblDSSanPhamTimDuoc, BorderLayout.CENTER);
    }

    private void dungTblChuaDSSPTimDuoc(){
        tblDSSanPhamTimDuoc.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tblDSSanPhamTimDuoc.setFont(fntMacDinh);
        tblDSSanPhamTimDuoc.setDefaultEditor(Object.class, null);

        tblDSSanPhamTimDuoc.getTableHeader().setFont(fntMacDinh);
        tblDSSanPhamTimDuoc.getTableHeader().setPreferredSize(
                new Dimension(
                        chieuRongPmnChuaDSSanPhamTimDuoc,
                        chieuCaoHangDuLieuTrongTable
                )
        );
        tblDSSanPhamTimDuoc.getTableHeader().setBackground(bgrTieuDeTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel tcm = tblDSSanPhamTimDuoc.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(110);
        tcm.getColumn(0).setMaxWidth(110);
        tcm.getColumn(0).setCellRenderer(
                centerRenderer
        );
    }

    private void datHanhDongChoTblDSSPTimDuoc(){
        tblDSSanPhamTimDuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int hangDuocChon = tblDSSanPhamTimDuoc.getSelectedRow();

                if (hangDuocChon != -1){
                    String maSP = tblDSSanPhamTimDuoc.getValueAt(
                            hangDuocChon, 0
                    ).toString();

                    int soLanClick = e.getClickCount();
                    int nutChuotDuocNhan = e.getButton();

                    if (soLanClick == 1 && nutChuotDuocNhan == 1){
                        txtLocTheoTungSanPham.setText(
                                maSP
                        );

                        locLaiDuLieuSauKhiThemHoacCapNhat();

                        txtLocTheoTungSanPham.setFocusable(false);

                        pmnChuaDSSanPhamTimDuoc.setVisible(false);
                    }
                }
            }
        });
    }

    private void datKichThuocChoPmnChuaDSSPTimDuoc(int slKetQua){
        pmnChuaDSSanPhamTimDuoc.setPopupSize(
                new Dimension(
                        chieuRongPmnChuaDSSanPhamTimDuoc,
                        chieuCaoHangDuLieuTrongTable * slKetQua
                )
        );
    }

    private void duaDuLieuVaoTable(ResultSet dsSP){
        dtmDSSanPhamTimDuoc.setRowCount(0);

        int count = 0;

        try {
            while (dsSP.next()){
                Object[] o = {
                        dsSP.getString("maSP"),
                        dsSP.getString("tenSP")
                };

                count++;

                dtmDSSanPhamTimDuoc.addRow(o);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        datKichThuocChoPmnChuaDSSPTimDuoc(count + 1);
    }

    private void datHanhDongChoRadMoiSanPham(){
        radLocTheoMoiSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungSanPham.setEnabled(false);
                txtLocTheoTungSanPham.setText("");

                pnlThanhTienIchTKSanPham.add(pnlLocTheoDoanhThu);

                pnlThanhTienIchTKSanPham.remove(pnlLocTheoSucTieuThu);

                pnlThanhTienIchTKSanPham.revalidate();
                pnlThanhTienIchTKSanPham.repaint();

                tblDuLieuTKDoanhThuTheoSanPham.setModel(dtmTKDTTheoSanPham);
                tblDuLieuTKDoanhThuTheoSanPham.setRowSorter(trsTKDTTheoSanPham);
                canLeChoDuLieuTrongTblTKDTTheoKhachHang(false);

                txtTimKiemTrenTblTKSanPham.removeKeyListener(cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH);
                txtTimKiemTrenTblTKSanPham.addKeyListener(cheDoTimKiemTrenDtmTKDTTheoKhachHang);
            }
        });
    }

    private void datHanhDongChoRadLocTheoTungSanPham(){
        radLocTheoTungSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLocTheoTungSanPham.setEnabled(true);

                pnlThanhTienIchTKSanPham.remove(pnlLocTheoDoanhThu);

                pnlThanhTienIchTKSanPham.add(pnlLocTheoSucTieuThu);

                pnlThanhTienIchTKSanPham.revalidate();
                pnlThanhTienIchTKSanPham.repaint();

                tblDuLieuTKDoanhThuTheoSanPham.setModel(dtmDuLieuTKDoanhThuTheoHDBH);
                tblDuLieuTKDoanhThuTheoSanPham.setRowSorter(trsDuLieuTKDoanhThuTheoHDBH);
                canLeChoDuLieuTrongTblTKDTTheoKhachHang(true);

                txtTimKiemTrenTblTKSanPham.removeKeyListener(cheDoTimKiemTrenDtmTKDTTheoKhachHang);
                txtTimKiemTrenTblTKSanPham.addKeyListener(cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH);
            }
        });
    }

    private void dungPnlLocTheoDoanhSo(){
        pnlLocTheoDoanhThu.setBackground(bgrMacDinh);
        pnlLocTheoDoanhThu.setPreferredSize(dimPnlLocTheoDoanhThu);
        pnlLocTheoDoanhThu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoDoanhThu, lblTieuDeLocTheoDoanhThu);
        pnlLocTheoDoanhThu.add(pnlTieuDeLocTheoDoanhThu);

        dungPnlLuaChonLocTheoDoanhSo();
        pnlLocTheoDoanhThu.add(pnlLuaChonLocTheoDoanhThu);
    }

    private void dungPnlLuaChonLocTheoDoanhSo(){
        pnlLuaChonLocTheoDoanhThu.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoDoanhThu.setPreferredSize(dimPnlLuaChonLocTheoDoanhThu);
        pnlLuaChonLocTheoDoanhThu.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                5
        ));

        bngLocTheoDoanhThu.add(radSanPhamCoDoanhThuLonNhat);
        bngLocTheoDoanhThu.add(radSanPhamCoDoanhThuNhoNhat);
        bngLocTheoDoanhThu.add(radTatCaDoanhThu);
        bngLocTheoDoanhThu.add(radDuoi5SanPham);
        bngLocTheoDoanhThu.add(radTren5SanPham);

        CacHamDungSan.datThuocTinhChoCacRad(radSanPhamCoDoanhThuLonNhat);
        pnlLuaChonLocTheoDoanhThu.add(radSanPhamCoDoanhThuLonNhat);

        CacHamDungSan.datThuocTinhChoCacRad(radSanPhamCoDoanhThuNhoNhat);
        pnlLuaChonLocTheoDoanhThu.add(radSanPhamCoDoanhThuNhoNhat);

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaDoanhThu);
        pnlLuaChonLocTheoDoanhThu.add(radTatCaDoanhThu);

        CacHamDungSan.datThuocTinhChoCacRad(radDuoi5SanPham);
        pnlLuaChonLocTheoDoanhThu.add(radDuoi5SanPham);

        CacHamDungSan.datThuocTinhChoCacRad(radTren5SanPham);
        pnlLuaChonLocTheoDoanhThu.add(radTren5SanPham);
    }

    private void dungPnlLocTheoTongTienHDBH(){
        pnlLocTheoSucTieuThu.setBackground(bgrMacDinh);
        pnlLocTheoSucTieuThu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlLocTheoSucTieuThu.setPreferredSize(dimPnlLocTheoSucTieuThu);

        CacHamDungSan.dungPnlTieuDeLoc(pnlTieuDeLocTheoSucTieuThu, lblTieuDeLocTheoSucTieuThu);
        pnlLocTheoSucTieuThu.add(pnlTieuDeLocTheoSucTieuThu);

        dungPnlLuaChonLocTheoTongTien();
        pnlLocTheoSucTieuThu.add(pnlLuaChonLocTheoSucTieuThu);
    }

    private void dungPnlLuaChonLocTheoTongTien(){
        pnlLuaChonLocTheoSucTieuThu.setBackground(bgrMacDinh);
        pnlLuaChonLocTheoSucTieuThu.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
        pnlLuaChonLocTheoSucTieuThu.setPreferredSize(dimPnlLuaChonLocTheoSucTieuThu);

        tapHopThanhVienCuaBngLocTheoTongTien();

        CacHamDungSan.datThuocTinhChoCacRad(radTatCaMucTien);
        pnlLuaChonLocTheoSucTieuThu.add(radTatCaMucTien);

        CacHamDungSan.datThuocTinhChoCacRad(radDuoi600K);
        pnlLuaChonLocTheoSucTieuThu.add(radDuoi600K);

        CacHamDungSan.datThuocTinhChoCacRad(radTu600KDen1_5Tr);
        pnlLuaChonLocTheoSucTieuThu.add(radTu600KDen1_5Tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTu1_5trDen3tr);
        pnlLuaChonLocTheoSucTieuThu.add(radTu1_5trDen3tr);

        CacHamDungSan.datThuocTinhChoCacRad(radTren3tr);
        pnlLuaChonLocTheoSucTieuThu.add(radTren3tr);
    }

    private void tapHopThanhVienCuaBngLocTheoTongTien(){
        bngLocTheoSucTieuThu.add(radTatCaMucTien);
        bngLocTheoSucTieuThu.add(radDuoi600K);
        bngLocTheoSucTieuThu.add(radTu600KDen1_5Tr);
        bngLocTheoSucTieuThu.add(radTu1_5trDen3tr);
        bngLocTheoSucTieuThu.add(radTren3tr);
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
                3
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
        pnlNoiDungTKDoanhThuTheoSanPham.setBackground(bgrMacDinh);
        pnlNoiDungTKDoanhThuTheoSanPham.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlNoiDungTKDoanhThuTheoSanPham.setPreferredSize(dimPnlNoiDungTKDoanhThuTheoSanPham);

        dungPnlThanhCongCuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoSanPham.add(pnlThanhCongCuTKSanPham);

        dungScrChuaTblDuLieuTKDoanhThuTheoHDBH();
        pnlNoiDungTKDoanhThuTheoSanPham.add(scrChuaTblDuLieuTKDoanhThuTheoSanPham);
    }

    private void dungPnlThanhCongCuTKDoanhThuTheoHDBH(){
        pnlThanhCongCuTKSanPham.setBackground(bgrPnlChinh);
        pnlThanhCongCuTKSanPham.setPreferredSize(dimPnlThanhCongCuTKSanPham);
        pnlThanhCongCuTKSanPham.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 3));

        CacHamDungSan.datThuocTinhChoTxt(
                txtTimKiemTrenTblTKSanPham,
                "Tìm kiếm",
                dimTxtTimKiemSanPham
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTimKiemTrenTblTKSanPham);
        pnlThanhCongCuTKSanPham.add(txtTimKiemTrenTblTKSanPham);

        dungPnlHopCongCuTKDoanhThuTheoHDBH();
        pnlThanhCongCuTKSanPham.add(pnlHopCongCuTKSanPham);
    }

    private KeyListener cheDoTimKiemTrenDtmTKDTTheoKhachHang = traVeCheDoTimKiemTrenDtmTKDTTheoKhachHang();
    private KeyListener cheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH = traVeCheDoTimKiemTrenDtmDuLieuTKDoanhThuTheoHDBH();

    private KeyListener traVeCheDoTimKiemTrenDtmTKDTTheoKhachHang(){
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trsTKDTTheoSanPham.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + txtTimKiemTrenTblTKSanPham.getText().trim()
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
                                "(?i)" + txtTimKiemTrenTblTKSanPham.getText().trim()
                        )
                );
            }
        };

        return keyListener;
    }

    private void dungPnlHopCongCuTKDoanhThuTheoHDBH(){
        pnlHopCongCuTKSanPham.setBackground(bgrPnlChinh);
        pnlHopCongCuTKSanPham.setPreferredSize(dimPnlHopCongCuTKSanPham);
        pnlHopCongCuTKSanPham.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        CacHamDungSan.datThuocTinhChoBtn(
                btnXuatTKSanPhamRaFile,
                bgrBtnXuatDuLieuRaFile,
                bgrMacDinh,
                dimBtnXuatTKSanPhamRaFile
        );
        datHanhDongChoBtnXuatRaExcel();
        pnlHopCongCuTKSanPham.add(btnXuatTKSanPhamRaFile);

        dungPmnXuatData();
    }

    private void dungScrChuaTblDuLieuTKDoanhThuTheoHDBH(){
        CacHamDungSan.datThuocTinhChoTblDuLieu(
                tblDuLieuTKDoanhThuTheoSanPham,
                dimScrChuaTblDuLieuTKDoanhThuTheoSanPham,
                null
        );
        datHanhDongChoTblDuLieuTKDoanhThuTheoSanPham();

        scrChuaTblDuLieuTKDoanhThuTheoSanPham.getViewport().setBackground(bgrMacDinh);
        scrChuaTblDuLieuTKDoanhThuTheoSanPham.setPreferredSize(dimScrChuaTblDuLieuTKDoanhThuTheoSanPham);
    }

    /**
     * <p>Can le, dat khoang cach cho cac cot cua tbl</p>
     * @param isDtmTKDoanhThuTheoHDBH: <li>true: tbl.getTableModel() = dtmDuLieuTKDoanhThuTheoHDBH</li>
     */
    private void canLeChoDuLieuTrongTblTKDTTheoKhachHang(boolean isDtmTKDoanhThuTheoHDBH){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel tcm = tblDuLieuTKDoanhThuTheoSanPham.getColumnModel();

        if (!isDtmTKDoanhThuTheoHDBH){
            for (int i = 0; i < tblDuLieuTKDoanhThuTheoSanPham.getColumnCount(); ++i) {
                if (i == 3 || i == 4 || i == 5){
                    tcm.getColumn(i).setCellRenderer(rightRenderer);
                }
            }

            tcm.getColumn(0).setPreferredWidth(110);
            tcm.getColumn(0).setMaxWidth(110);

            tcm.getColumn(2).setPreferredWidth(150);
            tcm.getColumn(2).setMaxWidth(150);

            tcm.getColumn(3).setPreferredWidth(100);
            tcm.getColumn(3).setMaxWidth(100);

            tcm.getColumn(4).setPreferredWidth(100);
            tcm.getColumn(4).setMaxWidth(100);

            tcm.getColumn(5).setPreferredWidth(130);
            tcm.getColumn(5).setMaxWidth(130);
        }
        else{
            for (int i = 0; i < tblDuLieuTKDoanhThuTheoSanPham.getColumnCount(); ++i) {
                if (i == 4 || i == 5 || i == 6){
                    tcm.getColumn(i).setCellRenderer(rightRenderer);
                }
            }

            tcm.getColumn(0).setPreferredWidth(110);
            tcm.getColumn(0).setMaxWidth(110);

            tcm.getColumn(1).setPreferredWidth(110);
            tcm.getColumn(1).setMaxWidth(110);

            tcm.getColumn(2).setPreferredWidth(150);
            tcm.getColumn(2).setMaxWidth(150);

            tcm.getColumn(4).setPreferredWidth(100);
            tcm.getColumn(4).setMaxWidth(100);

            tcm.getColumn(5).setPreferredWidth(150);
            tcm.getColumn(5).setMaxWidth(150);

            tcm.getColumn(6).setPreferredWidth(150);
            tcm.getColumn(6).setMaxWidth(150);
        }
    }

    private void datHanhDongChoTblDuLieuTKDoanhThuTheoSanPham(){
        tblDuLieuTKDoanhThuTheoSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == 1){
                    if (radLocTheoMoiSanPham.isSelected()){
                        int row = tblDuLieuTKDoanhThuTheoSanPham.getSelectedRow();

                        if (
                                row != -1 &&
                                !CacHamDungSan.kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(
                                        tblDuLieuTKDoanhThuTheoSanPham,
                                        row
                                )
                        ){
                            String maSP = tblDuLieuTKDoanhThuTheoSanPham.getValueAt(
                                    row, 0
                            ).toString();

                            SanPham sanPham = SanPhamDAO.laySanPhamTheoMa(maSP);

                            SwingUtilities.invokeLater(() -> {
                                GDXemThongTinSanPham gdXemThongTinSanPham = GDXemThongTinSanPham.getGdXemTTSanPham();

                                GDXemThongTinSanPham.hienThiThongTinSanPham(sanPham);

                                gdXemThongTinSanPham.setVisible(true);
                            });
                        }
                    }
                }
            }
        });
    }

    private void datHanhDongChoBtnXuatRaExcel(){
        btnXuatTKSanPhamRaFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pmnXuatData.show(
                        btnXuatTKSanPhamRaFile,
                        0,
                        btnXuatTKSanPhamRaFile.getHeight()
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
                if (radLocTheoMoiSanPham.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoSanPham,
                            true,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TAT_CA_NHAN_VIEN,
                            ""
                    );
                }
                else if (radLocTheoTungSanPham.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoSanPham,
                            true,
                            IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TUNG_NHAN_VIEN,
                            "SP-" + txtLocTheoTungSanPham.getText().trim()
                    );
                }
            }
        });
    }

    private void datHanhDongChoMniXuatPDF(){
        mniXuatPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radLocTheoMoiSanPham.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoSanPham,
                            false,
                            IDSBienMacDinh.THONG_KE_THEO_TAT_CA_SAN_PHAM,
                            ""
                    );
                }
                else if (radLocTheoTungSanPham.isSelected()){
                    TienIch.xuatDataTrongTableRaFile(
                            tblDuLieuTKDoanhThuTheoSanPham,
                            false,
                            IDSBienMacDinh.THONG_KE_THEO_TUNG_SAN_PHAM,
                            "SP-" + txtLocTheoTungSanPham.getText().trim()
                    );
                }
            }
        });
    }

    private List<JRadioButton> btnsLocTheoDoiTuong = Arrays.asList(radLocTheoMoiSanPham, radLocTheoTungSanPham);
    private List<JRadioButton> btnsLocTheoGiaTien =
            Arrays.asList(radTatCaMucTien, radDuoi600K, radTu600KDen1_5Tr, radTu1_5trDen3tr, radTren3tr);
    private List<JRadioButton> btnsLocTheoDoanhThu =
            Arrays.asList(radTatCaDoanhThu, radSanPhamCoDoanhThuLonNhat, radSanPhamCoDoanhThuNhoNhat, radDuoi5SanPham, radTren5SanPham);
    private List<JRadioButton> btnsLocTheoThoiGian = Arrays.asList(radLocTheoMocThoiGian, radLocTuyChonThoiGian);

    public void datHanhDongChoRadioButtons() {
        radLocTheoMoiSanPham.setName("MoiSanPham");
        radLocTheoTungSanPham.setName("TungSanPham");

        radTatCaMucTien.setName("TatCaMucTien");
        radDuoi600K.setName("Duoi600K");
        radTu600KDen1_5Tr.setName("6000KDen1_5Tr");
        radTu1_5trDen3tr.setName("1_5TrDen3Tr");
        radTren3tr.setName("Tren3tr");

        radTatCaDoanhThu.setName("TatCaDoanhThu");
        radSanPhamCoDoanhThuLonNhat.setName("DoanhThuLonNhat");
        radSanPhamCoDoanhThuNhoNhat.setName("DoanhThuNhoNhat");
        radDuoi5SanPham.setName("DoanhThuDuoi10SanPham");
        radTren5SanPham.setName("DoanhThuTren10SanPham");

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
                if(btn.getName().equals("TungSanPham")) {
                    locTheoDoiTuong.set(1);
                } else if(btn.getName().equals("MoiSanPham"))
                    locTheoDoiTuong.set(0);
            }
        });

        btnsLocTheoGiaTien.forEach(btn -> {
            if(btn.isSelected()) {
                if(btn.isSelected()) {
                    if(btn.getName().equals("Duoi600K"))
                        locTheoMucTien.set(0);
                    else if(btn.getName().equals("6000KDen1_5Tr"))
                        locTheoMucTien.set(1);
                    else if(btn.getName().equals("1_5TrDen3Tr"))
                        locTheoMucTien.set(2);
                    else if(btn.getName().equals("Tren3tr"))
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
                    else if(btn.getName().equals("DoanhThuDuoi10SanPham"))
                        locTheoDoanhThu.set(2);
                    else if(btn.getName().equals("DoanhThuTren10SanPham"))
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
                if(!txtLocTheoTungSanPham.getText().equals("Tìm kiếm SP") && !txtLocTheoTungSanPham.getText().isEmpty())
                    capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoDoiTuong.get(), locTheoMucTien.get(), locTheoNgay.get()));
            }
            else if(locTheoDoiTuong.get() == 0)
                capNhatDuLieuLenTable(sinhCauTruyVanCSDL(locTheoDoiTuong.get(), locTheoDoanhThu.get(), locTheoNgay.get()));
        }
    }

    private void capNhatDuLieuLenTable(String query) {
        try {

            if (radLocTheoMoiSanPham.isSelected()){
                xoaDuLieuTheoBang();

                ResultSet resultSet = KetNoiCSDL.layKetNoi().prepareStatement(query).executeQuery();

                if (!resultSet.isBeforeFirst()){
                    CacHamDungSan.hienThiThongBaoKhongCoDuLieuPhuHop();

                    xoaDuLieuTheoBang();
                }
                else{
                    AtomicInteger tongSLTon = new AtomicInteger(0);
                    AtomicInteger tongSLSP = new AtomicInteger(0);
                    AtomicReference<Double> tongDoanhSo = new AtomicReference<>(0.0);

                    try {
                        while (resultSet.next()){
                            dtmTKDTTheoSanPham.addRow(new Object[] {
                                    resultSet.getString("maSP"),
                                    resultSet.getString("tenSP"),
                                    resultSet.getString("thuongHieu"),
                                    resultSet.getInt("soLuongTon"),
                                    resultSet.getInt("SLSP"),
                                    nf.format(resultSet.getDouble("SucMua"))
                            });

                            tongSLTon.getAndAdd(resultSet.getInt("soLuongTon"));
                            tongSLSP.set(tongSLSP.get() +  resultSet.getInt("SLSP"));
                            tongDoanhSo.set(tongDoanhSo.get() + resultSet.getDouble("SucMua"));
                        }
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                    themDongTongKetKhiRadMoiKhachHangDuocChon(tongSLTon.get(), tongSLSP.get(), tongDoanhSo.get());
                }
            }
            else if (radLocTheoTungSanPham.isSelected()){
                xoaDuLieuTheoBang();

                ResultSet resultSet = KetNoiCSDL.layKetNoi().prepareStatement(query).executeQuery();

                if (!resultSet.isBeforeFirst()){
                    CacHamDungSan.hienThiThongBaoKhongCoDuLieuPhuHop();

                    xoaDuLieuTheoBang();
                }
                else{
                    AtomicInteger tongSLSP = new AtomicInteger(0);
                    AtomicReference<Double> thanhTienSP = new AtomicReference<>(0.0);
                    AtomicReference<Double> tongTienHD = new AtomicReference<>(0.0);

                    try {
                        while (resultSet.next()){
                            dtmDuLieuTKDoanhThuTheoHDBH.addRow(
                                    new Object[] {
                                            resultSet.getInt("maHDBH"),
                                            resultSet.getString("maNhanVienLapHDBH"),
                                            MaHoaDuLieu.giaiMa(resultSet.getString("soDT")),
                                            dtf.format(resultSet.getTimestamp("thoiGianLap").toLocalDateTime()),
                                            resultSet.getInt("soLuongBan"),
                                            nf.format(resultSet.getDouble("thanhTien")),
                                            nf.format(resultSet.getDouble("tongTien"))
                                    }
                            );

                            tongSLSP.set(tongSLSP.get() + resultSet.getInt("soLuongBan"));
                            thanhTienSP.set(thanhTienSP.get() + resultSet.getDouble("thanhTien"));
                            tongTienHD.set(tongTienHD.get() + resultSet.getDouble("tongTien"));

                        }
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                    themDongTongKetKhiRadTungKhachHangDuocChon(
                            tongSLSP.get(),
                            thanhTienSP.get(),
                            tongTienHD.get()
                    );
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void xoaDuLieuTheoBang() {
        if(radLocTheoMoiSanPham.isSelected()) {
            dtmTKDTTheoSanPham.setRowCount(0);
            tblDuLieuTKDoanhThuTheoSanPham.setModel(dtmTKDTTheoSanPham);
            tblDuLieuTKDoanhThuTheoSanPham.setRowSorter(trsTKDTTheoSanPham);
            canLeChoDuLieuTrongTblTKDTTheoKhachHang(false);
        }
        else if(radLocTheoTungSanPham.isSelected()) {
            dtmDuLieuTKDoanhThuTheoHDBH.setRowCount(0);
            tblDuLieuTKDoanhThuTheoSanPham.setModel(dtmDuLieuTKDoanhThuTheoHDBH);
            tblDuLieuTKDoanhThuTheoSanPham.setRowSorter(trsDuLieuTKDoanhThuTheoHDBH);
            canLeChoDuLieuTrongTblTKDTTheoKhachHang(true);
        }
    }

    private void themDongTongKetKhiRadMoiKhachHangDuocChon(int soLuongTon, int slsp, double doanhThu){
        dtmTKDTTheoSanPham.insertRow(
                0,
                new Object[]{
                        "Tổng cộng:",
                        dtmTKDTTheoSanPham.getRowCount() + " danh mục",
                        "",
                        soLuongTon,
                        slsp,
                        nf.format(doanhThu)
                }
        );
    }

    private void themDongTongKetKhiRadTungKhachHangDuocChon(int slsp, double thanhTienSP, double tongTienHD){
        dtmDuLieuTKDoanhThuTheoHDBH.insertRow(
                0,
                new Object[]{
                        "Tổng kết:",
                        dtmDuLieuTKDoanhThuTheoHDBH.getRowCount() + " hoá đơn",
                        "",
                        "",
                        slsp,
                        nf.format(thanhTienSP),
                        nf.format(tongTienHD)
                }
        );
    }

    private String sinhCauTruyVanCSDL(int doiTuong, int locTheoTien, int locTheoNgay) {
        String query = sinhCauTruyVanTheoDoiTuong(doiTuong);

        if(locTheoTien != -1 && locTheoNgay != -1) {
            if(doiTuong == 0) {
                if(locTheoNgay == 1) {
                    query += String.format("where %s group by sp.maSP, sp.tenSP, sp.thuongHieu, sp.soLuongTon %s",
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()),
                            sinhCauTruyVanLocTheoDoanhThu(locTheoTien));

                } else if(locTheoNgay == 0) {
                    query += String.format("where %s group by sp.maSP, sp.tenSP, sp.thuongHieu, sp.soLuongTon %s",
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
                    query += String.format("where ct.maSP = '%s' and %s and %s",
                            txtLocTheoTungSanPham.getText().trim(),
                            sinhCauTruyVanLocTheoTongTien(locTheoTien),
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }
                else if(locTheoNgay == 1) {
                    query += String.format("where ct.maSP = '%s' and %s and %s",
                            txtLocTheoTungSanPham.getText().trim(),
                            sinhCauTruyVanLocTheoTongTien(locTheoTien),
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));
                }
            }
        }
        else if(locTheoTien == -1 && locTheoNgay != -1) {
            if(doiTuong == 0) {
                if(locTheoNgay == 1) {
                    query += String.format("where %s group by sp.maSP, sp.tenSP, sp.thuongHieu, sp.soLuongTon",
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));

                } else if(locTheoNgay == 0) {
                    query += String.format("where %s group by sp.maSP, sp.tenSP, sp.thuongHieu, sp.soLuongTon",
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }

                query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                if(locTheoNgay == 0) {
                    query += String.format("where ct.maSP = '%s' and %s",
                            txtLocTheoTungSanPham.getText().trim(),
                            sinhCauTruyVanLocTheoTuyChonThoiGian());
                }
                else if(locTheoNgay == 1) {
                    query += String.format("where ct.maSP = '%s' and %s",
                            txtLocTheoTungSanPham.getText().trim(),
                            sinhCauTruyVanLocTheoMocThoiGian(cbbCacMocThoiGian.getSelectedIndex()));
                }
            }
        }
        else if(locTheoTien != -1 && locTheoNgay == -1) {
            if(doiTuong == 0) {
                query += String.format(" group by sp.maSP, sp.tenSP, sp.thuongHieu, sp.soLuongTon %s",
                        sinhCauTruyVanLocTheoDoanhThu(locTheoTien));
                if(locTheoTien == 0)
                    query = String.format("Select top 10 %s", query);
                else if(locTheoTien == 1)
                    query = String.format("Select top 20 %s", query);
                else
                    query = String.format("Select %s", query);
            }
            else if(doiTuong == 1) {
                query += String.format("where ct.maSP = '%s' and %s",
                        txtLocTheoTungSanPham.getText().trim(),
                        sinhCauTruyVanLocTheoTongTien(locTheoTien));
            }
        }
        else if(locTheoTien == -1 && locTheoNgay == -1) {
            if(doiTuong == 0)
                query = String.format("Select %s group by sp.maSP, sp.tenSP, sp.thuongHieu, sp.soLuongTon order by sp.maSP desc", query);
            else if(doiTuong == 1)
                query += String.format("where ct.maSP = '%s'", txtLocTheoTungSanPham.getText().trim());
        }

        return query;
    }

    private String sinhCauTruyVanTheoDoiTuong(int doiTuong) {
        String query = "";

        if(doiTuong == 0) {
            query += " sp.maSP, sp.tenSP, sp.thuongHieu, sp.soLuongTon, sum(ct.soLuongBan) as SLSP, sum(ct.thanhTien) as SucMua " +
                    "from SanPham sp join ChiTietHoaDonBanHang ct on sp.maSP = ct.maSP " +
                    "join HoaDonBanHang hd on ct.maHDBH = hd.maHDBH ";
        }
        else if(doiTuong == 1) {
            query += "Select hd.maHDBH, hd.maNhanVienLapHDBH, kh.soDT, hd.thoiGianLap, ct.soLuongBan, ct.thanhTien, hd.tongTien " +
                    "from HoaDonBanHang hd  join KhachHang kh on kh.maKH = hd.maKH " +
                    "join ChiTietHoaDonBanHang ct on ct.maHDBH = hd.maHDBH ";
        }

        return query;
    }

    private String sinhCauTruyVanLocTheoTongTien(int locTheoTongTien) {
        String query = "";

        if(locTheoTongTien == 0)
            query = "ct.thanhTien < 600000";
        else if(locTheoTongTien == 1)
            query = "ct.thanhTien >= 600000 and ct.thanhTien <= 1500000";
        else if(locTheoTongTien == 2)
            query = "ct.thanhTien > 1500000 and ct.thanhTien <= 3000000";
        else if(locTheoTongTien == 3)
            query = "ct.thanhTien > 3000000";

        return query;
    }

    private String sinhCauTruyVanLocTheoDoanhThu(int locTheoDoanhThu) {
        String query = "";

        if(locTheoDoanhThu == 0)
            query = "order by SucMua desc";
        else if(locTheoDoanhThu == 1)
            query = "order by SucMua asc";
        else if(locTheoDoanhThu == 2)
            query = "having sum(ct.soLuongBan) < 5";
        else if(locTheoDoanhThu == 3)
            query = "having sum(ct.soLuongBan) > 5";

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
