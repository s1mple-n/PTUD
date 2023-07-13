package ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheohdbh;

import com.github.lgooddatepicker.components.DatePicker;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public interface IDSBienPnlTKDoanhThuTheoHDBH extends IDSBienMacDinh, IDSBienGDChinh {
    Dimension dimTabNoiDung = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhTienIchTKDoanhThuTheoHDBH = new JPanel();
    Dimension dimPnlThanhTienIchTKDoanhThuTheoHDBH = new Dimension(
            chieuRongThanhTienIch - 20,
            dimTabNoiDung.height
    );

    JPanel pnlLocTheoThoiGianLapHDBH = new JPanel();
    Dimension dimPnlLocTheoThoiGianLapHDBH_1 = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width,
            130
    );
    Dimension dimPnlLocTheoThoiGianLapHDBH_2 = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width,
            255
    );

    JPanel pnlTieuDeLocTheoThoiGianLapHDBH = new JPanel();
    JLabel lblTieuDeLocTheoThoiGianLapHDBH = new JLabel("Thời gian bán hàng");

    JPanel pnlLuaChonLocTheoThoiGianLapHDBH = new JPanel();
    Dimension dimPnlLuaChonLocTheoThoiGianLapHDBH = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width,
            80
    );

    ButtonGroup bngLocTheoThoiGianLapHDBH = new ButtonGroup();
    JRadioButton radLocTheoMocThoiGianLapHDBH = new JRadioButton("");
    JRadioButton radTuyChonThoiGianLapHDBH = new JRadioButton("Tuỳ chọn thời gian");

    JPanel pnlLocTheoMocThoiGianLapHDBH = new JPanel();
    String[] cacMocThoiGianLapHDBH = {
            "Hôm nay",
            "Tuần này",
            "Tháng này"
    };
    JComboBox<String> cbbCacMocThoiGianLapHDBH = new JComboBox<>(cacMocThoiGianLapHDBH);
    Dimension dimCbbCacMocThoiGianLapHDBH = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width - 50,
            35
    );

    JPanel pnlKhungChonThoiGianLapHDBH = new JPanel();
    Dimension dimPnlKhungChonThoiGianLapHDBH = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width,
            dimPnlLocTheoThoiGianLapHDBH_2.height
                    - dimPnlTieuDeThanhTienIch.height
                    - dimPnlLuaChonLocTheoThoiGianLapHDBH.height
    );

    Dimension dimPnlNgayBatDauVaKetThucTKTheoHDBH = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width,
            dimPnlKhungChonThoiGianLapHDBH.height / 2
    );
    Dimension dimDatePicker = new Dimension(
            dimPnlNgayBatDauVaKetThucTKTheoHDBH.width
                    - khoangCachSoVoiLeTraiCuaTieuDe
                    - 8,
            dimPnlNgayBatDauVaKetThucTKTheoHDBH.height
                    - 30
    );

    JPanel pnlNgayBatDauTKTheoHDBH = new JPanel();
    JLabel lblNgayBatDauTKTheoHDBH = new JLabel("Ngày bắt đầu:");
    DatePicker dtpNgayBatDauTKTheoHDBH = new DatePicker();

    JPanel pnlNgayKetThucTKTheoHDBH = new JPanel();
    JLabel lblNgayKetThucTKTheoHDBH = new JLabel("Ngày kết thúc:");
    DatePicker dtpNgayKetThucTKTheoHDBH = new DatePicker();

    JPanel pnlLocTheoTongTienHDBH = new JPanel();
    Dimension dimPnlLocTheoTongTienHDBH = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width,
            205
    );

    JPanel pnlTieuDeLocTheoTongTienHDBH = new JPanel();
    JLabel lblTieuDeLocTheoTongTienHDBH = new JLabel("  Tổng tiền HDBH");

    JPanel pnlLuaChonLocTheoTongTienHDBH = new JPanel();
    Dimension dimPnlLuaChonLocTheoTongTienHDBH = new Dimension(
            dimPnlLocTheoTongTienHDBH.width,
            dimPnlLocTheoTongTienHDBH.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoTongTienHDBH = new ButtonGroup();
    JRadioButton radTatCaMucTien = new JRadioButton("Tất cả             ");
    JRadioButton radDuoi1tr = new JRadioButton("Dưới 1 triệu");
    JRadioButton radTu1trDen3tr = new JRadioButton("1 triệu - 3 triệu");
    JRadioButton radTu3trDen7tr = new JRadioButton("3 triệu - 7 triệu");
    JRadioButton radTren7tr = new JRadioButton("Trên 7 triệu");


    JPanel pnlLocTheoGioiTinhKhachHang = new JPanel();
    Dimension dimPnlLocTheoGioiTinhKhachHang = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.width,
            dimPnlThanhTienIchTKDoanhThuTheoHDBH.height
                    - dimPnlLocTheoThoiGianLapHDBH_2.height
                    - dimPnlLocTheoTongTienHDBH.height
    );

    JPanel pnlTieuDeLocTheoGioiTinhKhachHang = new JPanel();
    JLabel lblTieuDeLocTheoGioiTinhKhachHang = new JLabel("Giới tính khách hàng");

    JPanel pnlLuaChonLocTheoGioiTinhKhachHang = new JPanel();
    Dimension dimPnlLuaChonLocTheoGioiTinhKhachHanga = new Dimension(
            dimPnlLocTheoGioiTinhKhachHang.width,
            dimPnlLocTheoGioiTinhKhachHang.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoGioiTinhKhachHang = new ButtonGroup();
    JRadioButton radMoiGioiTinhKH = new JRadioButton("Tất cả");
    JRadioButton radKHNam = new JRadioButton("Khách hàng nam");
    JRadioButton radKHNu = new JRadioButton("Khách hàng nữ");

    JPanel pnlNoiDungTKDoanhThuTheoHDBH = new JPanel();
    Dimension dimPnlNoiDungTKDoanhThuTheoHDBH = new Dimension(
            dimTabNoiDung.width
                    - dimPnlThanhTienIchTKDoanhThuTheoHDBH.width
                    - 5,
            dimTabNoiDung.height
    );

    JPanel pnlThanhCongCuTKDoanhThuTheoHDBH = new JPanel();
    Dimension dimPnlThanhCongCuTKDoanhThuTheoHDBH = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoHDBH.width,
            50
    );

    JTextField txtTimKiemTrenTblTKDoanhThuTheoHDBH = new JTextField();
    Dimension dimTxtTimKiemHDBH = new Dimension(
            300,
            43
    );

    JPanel pnlHopCongCuTKDoanhThuTheoHDBH = new JPanel();
    Dimension dimPnlHopCongCuTKDoanhThuTheoHDBH = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoHDBH.width
                    - dimTxtTimKiemHDBH.width,
            dimTxtTimKiemHDBH.height
    );

    Dimension dimBtnXuatTKDoanhThuTheoHDBHRaExcel = new Dimension(
            160,
            dimPnlHopCongCuTKDoanhThuTheoHDBH.height
    );
    JButton btnXuatTKDoanhThuTheoHDBHRaFile = new JButton(
            "Xuất dữ liệu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Export_24px_1.png")
            )
    );

    JPopupMenu pmnXuatData = new JPopupMenu();
    Dimension dimPmnXuatData = new Dimension(
            dimBtnXuatTKDoanhThuTheoHDBHRaExcel.width,
            80
    );

    JMenuItem mniXuatExcel = new JMenuItem(
            "Xuất Excel",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Excel_24px_1.png")
            )
    );

    JMenuItem mniXuatPDF = new JMenuItem(
            "Xuất PDF",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/PDF_24px_1.png")
            )
    );

    String[] tieuDeTableTKDoanhThuTheoHDBH = {"Mã HĐBH", "Mã NV lập", "SĐT KH", "Thời gian lập", "SLSP", "Chưa thuế", "VAT", "Tổng tiền"};
    DefaultTableModel dtmDuLieuTKDoanhThuTheoHDBH = new DefaultTableModel(tieuDeTableTKDoanhThuTheoHDBH, 0);
    TableRowSorter trsDuLieuTKDoanhThuTheoHDBH = new TableRowSorter(dtmDuLieuTKDoanhThuTheoHDBH);

    JTable tblDuLieuTKDoanhThuTheoHDBH = new JTable(dtmDuLieuTKDoanhThuTheoHDBH){
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component component = super.prepareRenderer(renderer, row, column);

            if (!component.getBackground().equals(getSelectionBackground())){
                component.setBackground(
                        row % 2 == 0 ? Color.white : bgrHangTableLe
                );
            }

            return component;
        }
    };

    JScrollPane scrChuaTblDuLieuTKDoanhThuTheoHDBH = new JScrollPane(tblDuLieuTKDoanhThuTheoHDBH);
    Dimension dimScrChuaTblDuLieuTKDoanhThuTheoHDBH = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoHDBH.width,
            dimPnlNoiDungTKDoanhThuTheoHDBH.height
                    - dimPnlThanhCongCuTKDoanhThuTheoHDBH.height
                    - 73
    );

}
