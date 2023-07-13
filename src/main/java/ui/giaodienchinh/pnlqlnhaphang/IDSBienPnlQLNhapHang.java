package ui.giaodienchinh.pnlqlnhaphang;

import com.github.lgooddatepicker.components.DatePicker;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface IDSBienPnlQLNhapHang extends IDSBienMacDinh, IDSBienGDChinh {
    JPanel pnlQuanLiNhapHang = new JPanel();
    Dimension dimPnlQuanLiNhapHang = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhTienIch = new JPanel();
    Dimension dimPnlThanhTienIch = new Dimension(
            chieuRongThanhTienIch,
            dimPnlNoiDung.height
    );

    JPanel pnlLocTheoTongTien = new JPanel();
    Dimension dimPnlLocTheoTongTien = new Dimension(
            dimPnlThanhTienIch.width,
            250
    );

    JPanel pnlTieuDeLocTheoTongTien = new JPanel();
    JLabel lblTieuDeLocTheoTongTien = new JLabel("Tổng tiền");

    JPanel pnlLuaChonLocTheoTongTien = new JPanel();
    Dimension dimPnlLuaChonLocTheoTongTien = new Dimension(
            dimPnlLocTheoTongTien.width,
            dimPnlLocTheoTongTien.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoTongTien = new ButtonGroup();
    JRadioButton radTatCaMucTien = new JRadioButton("Tất cả         ");
    JRadioButton radDuoi8tr = new JRadioButton("Dưới 8 triệu");
    JRadioButton radTu8trDen20tr = new JRadioButton("8 triệu - 20 triệu");
    JRadioButton radTu20trDen40tr = new JRadioButton("20 triệu - 40 triệu");
    JRadioButton radTren40tr = new JRadioButton("Trên 40 triệu");

    JPanel pnlLocTheoThoiGian = new JPanel();
    Dimension dimPnlLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIch.width,
            420
    );

    JPanel pnlTieuDeLocTheoThoiGian = new JPanel();
    JLabel lblLocTheoThoiGian = new JLabel("Thời gian nhập hàng");

    JPanel pnlLuaChonLocTheoThoiGian = new JPanel();
    Dimension dimPnlLuaChonLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIch.width,
            85
    );

    ButtonGroup bngLocTheoThoiGian = new ButtonGroup();
    JRadioButton radLocTheoMocThoiGian = new JRadioButton("");
    JRadioButton radTuyChonThoiGian = new JRadioButton("Tuỳ chọn thời gian");

    JPanel pnlLocTheoMocThoiGian = new JPanel();
    String[] cacMocThoiGian = {
            "Hôm nay",
            "Tuần này",
            "Tháng này"
    };
    JComboBox<String> cbbCacMocThoiGian = new JComboBox<>(cacMocThoiGian);
    Dimension dimCbbCacMocThoiGian = new Dimension(
            160, 35
    );

    JPanel pnlKhungChonThoiGian = new JPanel();
    Dimension dimPnlKhungChonThoiGian = new Dimension(
            dimPnlThanhTienIch.width,
            dimPnlLocTheoThoiGian.height
                    - dimPnlTieuDeThanhTienIch.height
                    - dimPnlLuaChonLocTheoThoiGian.height
                    - 95
    );

    Dimension dimPnlNgayBatDauVaKetThuc = new Dimension(
            dimPnlThanhTienIch.width,
            dimPnlKhungChonThoiGian.height / 2
                    - khoangCachSoVoiLeTrenCuaTieuDe * 2
                    - 10
    );
    Dimension dimDatePicker = new Dimension(
            dimPnlNgayBatDauVaKetThuc.width
                    - khoangCachSoVoiLeTraiCuaTieuDe * 2,
            dimPnlNgayBatDauVaKetThuc.height
                    - 35
    );

    JPanel pnlNgayBatDau = new JPanel();
    JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
    DatePicker dtpNgayBatDau = new DatePicker();

    JPanel pnlNgayKetThuc = new JPanel();
    JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
    DatePicker dtpNgayKetThuc = new DatePicker();


    /**
     *
     */

    JPanel pnlNoiDungQLNHChinh = new JPanel();
    Dimension dimPnlNoiDungQLNHChinh = new Dimension(
            dimPnlQuanLiNhapHang.width
                    - dimPnlThanhTienIch.width,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhCongCu = new JPanel();
    Dimension dimPnlThanhCongCu = new Dimension(
            dimPnlNoiDungQLNHChinh.width,
            50
    );

    JTextField txtTimKiem = new JTextField();
    Dimension dimTxtTimKiem = new Dimension(
            300,
            43
    );

    JPopupMenu pmnCheDoTimKiem = new JPopupMenu();

    JMenuItem mniTimKiemTrongCSDL = new JMenuItem(
            "Tìm trong kho dữ liệu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/find_24px_2.png")
            )
    );

    JMenuItem mniTimKiemTrenTable = new JMenuItem(
            "Tìm trên bảng bên dưới",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/find_24px_1.png")
            )
    );

    int chieuRongPmnChuaDSHDNHTimDuoc = dimTxtTimKiem.width + 400;
    JPopupMenu pmnChuaDSHDNHTimDuoc = new JPopupMenu();

    //MaHDBH, maKH, tgLap, tongSLSP, tongTien
    String[] tieuDeTblTam = {"Mã HDNH", "Mã NV lập", "Mã lô hàng", "TG giao", "SLSP", "Tổng tiền"};
    DefaultTableModel dtmHDNHTimDuoc = new DefaultTableModel(tieuDeTblTam, 0);
    JTable tblHDNHTimDuoc = new JTable(dtmHDNHTimDuoc){
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

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

    JPanel pnlHopCongCu = new JPanel();
    Dimension dimPnlHopCongCu = new Dimension(
            dimPnlThanhCongCu.width
                    - dimTxtTimKiem.width
                    - 10,
            dimTxtTimKiem.height
    );

    Dimension dimBtnThanhCongCu = new Dimension(
            160,
            dimPnlHopCongCu.height
    );

    JButton btnTaoHoaDonNhapHang = new JButton(" Nhập hàng",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_24px_1.png")
            )
    );

    JButton btnXuatDuLieuTrongTableRaFile = new JButton("Xuất dữ liệu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Export_24px_1.png")
            )
    );

    JPopupMenu pmnXuatData = new JPopupMenu();
    Dimension dimPmnXuatData = new Dimension(
            dimBtnThanhCongCu.width,
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


    JPanel pnlKetQuaTraCuu = new JPanel();
    Dimension dimPnlKetQuaTraCuu = new Dimension(
            dimPnlNoiDungQLNHChinh.width,
            dimPnlNoiDungQLNHChinh.height
                    - dimPnlThanhCongCu.height
    );

    Locale vn = new Locale("vi", "vn");

    String pt = "###.###";
    DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(
            vn
    );

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            vn
    );

    String[] tieuDeTable = {"Mã HĐNH", "Mã NV lập", "Mã lô hàng", "Thời gian đặt", "Thời gian giao", "Tổng SLSP", "Tổng tiền"};
    DefaultTableModel dtmDuLieuTraCuuDuoc = new DefaultTableModel(tieuDeTable, 0);
    TableRowSorter trsDuLieuTraCuuDuoc = new TableRowSorter(dtmDuLieuTraCuuDuoc);

    JTable tblDuLieuTraCuuDuoc = new JTable(dtmDuLieuTraCuuDuoc){
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

    Dimension dimScrChuaTableDulieuTraCuuDuoc = new Dimension(
            dimPnlKetQuaTraCuu.width
                    - 10,
            dimPnlKetQuaTraCuu.height
    );
    JScrollPane scrChuaTableDuLieuTraCuuDuoc = new JScrollPane(tblDuLieuTraCuuDuoc);
}
