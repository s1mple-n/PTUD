package ui.giaodienchinh.pnllapthongke;

import com.formdev.flatlaf.ui.FlatTabbedPaneUI;
import ui.giaodienchinh.pnllapthongke.pnlthongkesanpham.PnlThongKeSanPham;
import ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheohdbh.PnlTKDoanhThuTheoHDBH;
import ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheokhachhang.PnlTKDoanhThuTheoKhachHang;
import ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheonhanvien.PnlTKDoanhThuTheoNhanVien;

import javax.swing.*;
import java.awt.*;

public class PnlLapThongKe implements IDSBienPnlLapThongKe{
    private static JPanel pnlLapThongKe = null;

    private JPanel dungPnlLapTK(){
        pnlLapTK.setPreferredSize(dimPnlLapTk);
        pnlLapTK.setBackground(bgrPnlChinh);
        pnlLapTK.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        dungTabNoiDung();
        pnlLapTK.add(tabNoiDung);

        return pnlLapTK;
    }

    public static JPanel getPnlLapThongKe() {
        if (pnlLapThongKe == null)
            pnlLapThongKe = new PnlLapThongKe().dungPnlLapTK();
        return pnlLapThongKe;
    }

    private void dungTabNoiDung(){
        tabNoiDung.setPreferredSize(dimTabNoiDung);
        tabNoiDung.setBackground(bgrMacDinh);
        tabNoiDung.setFont(fntMacDinh);
        tabNoiDung.setUI(new FlatTabbedPaneUI() {
            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return 40;
            }
        });

        dungPnlThongKeDoanhThu();
        tabNoiDung.add("Doanh thu", pnlThongKeDoanhThu);

        dungPnlThongKeSanPham();
        tabNoiDung.add("Sản phẩm", pnlThongKeSanPham);
    }

    private void dungPnlThongKeDoanhThu(){
        pnlThongKeDoanhThu.setPreferredSize(dimPnlConCuaTabNoiDung);
        pnlThongKeDoanhThu.setBackground(bgrMacDinh);
        pnlThongKeDoanhThu.setLayout(new BorderLayout());

        dungTabThongKeDoanhThu();
        pnlThongKeDoanhThu.add(tabThongKeDoanhThu, BorderLayout.CENTER);
    }

    private void dungTabThongKeDoanhThu(){
        tabThongKeDoanhThu.setPreferredSize(dimPnlConCuaTabNoiDung);
        tabThongKeDoanhThu.setBackground(bgrMacDinh);
        tabThongKeDoanhThu.setFont(fntMacDinh);

        new PnlTKDoanhThuTheoHDBH().dungPnlTKDTTheoHoaDonBanHang(pnlTKDTTheoHoaDonBanHang);
        tabThongKeDoanhThu.add("Hoá đơn BH", pnlTKDTTheoHoaDonBanHang);

        new Thread(() -> {
            new PnlTKDoanhThuTheoNhanVien().dungPnlTKDoanhThuTheoNhanVien(pnlTKDTTheoNhanVien);
        }).start();
        tabThongKeDoanhThu.add("Nhân viên", pnlTKDTTheoNhanVien);

        new Thread(() -> {
            new PnlTKDoanhThuTheoKhachHang().dungPnlTKDoanhThuTheoKhachHang(pnlTKDTTheoKhachHang);
        }).start();
        tabThongKeDoanhThu.add("Khách hàng", pnlTKDTTheoKhachHang);
    }

    /**
     *
     */

    private void dungPnlThongKeSanPham(){
        pnlThongKeSanPham.setPreferredSize(dimPnlConCuaTabNoiDung);
        pnlThongKeSanPham.setBackground(bgrMacDinh);
        pnlThongKeSanPham.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        SwingUtilities.invokeLater(() -> {
            new PnlThongKeSanPham().dungPnlTKSanPham(pnlThongKeSanPham);
        });
    }
}
