package ui.giaodienchinh.pnlqlnhanvien;

import com.github.lgooddatepicker.components.DatePicker;
import entity.NhanVien;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public interface IDSBienQLNhanVien extends IDSBienMacDinh, IDSBienGDChinh {
    JPanel pnlQLNV = new JPanel();
    Dimension dimPnlQLNV = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhTienIch = new JPanel();
    Dimension dimPnlThanhTienIch = new Dimension(
            chieuRongThanhTienIch,
            dimPnlNoiDung.height
    );

    JPanel pnlLocTheoCaLamViec = new JPanel();
    Dimension dimPnlLocTheoCaLamViec = new Dimension(
            dimPnlThanhTienIch.width,
            180
    );

    JPanel pnlTieuDeLocTheoCaLamViec = new JPanel();
    JLabel lblTieuDeLocTheoCaLamViec = new JLabel("Ca làm việc");

    JPanel pnlLuaChonLocTheoCaLamViec = new JPanel();
    Dimension dimPnlLuaChonLocTheoCaLamViec = new Dimension(
            dimPnlLocTheoCaLamViec.width,
            dimPnlLocTheoCaLamViec.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoCaLamViec = new ButtonGroup();
    JRadioButton radTatCaCaLamViec = new JRadioButton("Tất cả");
    JRadioButton radCaSang = new JRadioButton("Ca sáng                 ");
    JRadioButton radCaToi = new JRadioButton("Ca tối");

    JPanel pnlLocTheoTinhTrangLamViec = new JPanel();
    Dimension dimPnlLocTheoTinhTrangLamViec = new Dimension(
            dimPnlThanhTienIch.width,
            180
    );

    JPanel pnlTieuDeLocTheoTinhTrangLamViec = new JPanel();
    JLabel lblTieuDeLocTheoTinhTrangLamViec = new JLabel("Tình trạng làm việc");

    JPanel pnlLuaChonLocTheoTinhTrangLamViec = new JPanel();
    Dimension dimPnlLuaChonLocTheoTinhTrangLamViec = new Dimension(
            dimPnlLocTheoTinhTrangLamViec.width,
            dimPnlLocTheoTinhTrangLamViec.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoTinhTrangLamViec = new ButtonGroup();
    JRadioButton radTatCaTinhTrangLamViec = new JRadioButton("Tất cả        ");
    JRadioButton radConLam = new JRadioButton("Còn làm           ");
    JRadioButton radDaNghi = new JRadioButton("Đã nghỉ");


    JPanel pnlLocTheoThoiGian = new JPanel();
    Dimension dimPnlLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIch.width,
            310
    );

    JPanel pnlTieuDeLocTheoThoiGian = new JPanel();
    JLabel lblLocTheoThoiGian = new JLabel("Thời điểm vào làm");

    JPanel pnlLuaChonLocTheoThoiGian = new JPanel();
    Dimension dimPnlLuaChonLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIch.width,
            77
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

    JPanel pnlNoiDungQLNVChinh = new JPanel();
    Dimension dimPnlNoiDungQLNVChinh = new Dimension(
            dimPnlQLNV.width
                    - dimPnlThanhTienIch.width,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhCongCu = new JPanel();
    Dimension dimPnlThanhCongCu = new Dimension(
            dimPnlNoiDungQLNVChinh.width,
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

    Map<String, NhanVien> dsNhanVien = new TreeMap<>();

    int chieuRongPmnChuaDSNVTimDuoc = dimTxtTimKiem.width + 200;
    JPopupMenu pmnChuaDSNVTimDuoc = new JPopupMenu();

    String[] tieuDeTblTam = {"Mã NV", "Ca làm", "Họ tên", "Số ĐT"};
    DefaultTableModel dtmDSNVTimDuoc = new DefaultTableModel(tieuDeTblTam, 0);
    JTable tblDSNVTimDuoc = new JTable(dtmDSNVTimDuoc){
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

    JButton btnThemNhanVien = new JButton("Nhân viên",
            new ImageIcon("src/main/resources/BieuTuong/Add_24px_1.png"));
    JButton btnXuatDuLieuTrongTableRaFile = new JButton("Xuất dữ liệu",
            new ImageIcon("src/main/resources/BieuTuong/Export_24px_1.png")
    );

    JPopupMenu pmnXuatData = new JPopupMenu();
    Dimension dimPmnXuatData = new Dimension(
            dimBtnThanhCongCu.width,
            80
    );

    JMenuItem mniXuatExcel = new JMenuItem(
            "Xuất Excel",
            new ImageIcon("src/main/resources/BieuTuong/Excel_24px_1.png")
    );

    JMenuItem mniXuatPDF = new JMenuItem(
            "Xuất PDF",
            new ImageIcon("src/main/resources/BieuTuong/PDF_24px_1.png")
    );

    JPanel pnlKetQuaTraCuu = new JPanel();
    Dimension dimPnlKetQuaTraCuu = new Dimension(
            dimPnlNoiDungQLNVChinh.width,
            dimPnlNoiDungQLNVChinh.height
                    - dimPnlThanhCongCu.height
    );

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    String[] tieuDeTable = {"Mã NV", "Họ tên", "SĐT", "Số CMND/CCCD", "Cấp bậc", "Tình trạng"};
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
