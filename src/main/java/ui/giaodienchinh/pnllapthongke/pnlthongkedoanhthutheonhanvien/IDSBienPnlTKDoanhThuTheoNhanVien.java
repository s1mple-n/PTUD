package ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheonhanvien;

import com.github.lgooddatepicker.components.DatePicker;
import entity.NhanVien;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;
import ui.giaodienchinh.pnlqlnhanvien.kieudulieudacbiet.NhanVienTimDuoc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public interface IDSBienPnlTKDoanhThuTheoNhanVien extends IDSBienMacDinh, IDSBienGDChinh {
    Dimension dimTabNoiDung = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhTienIchTKDoanhThuTheoNhanVien = new JPanel();
    Dimension dimPnlThanhTienIchTKDoanhThuTheoNhanVien = new Dimension(
            chieuRongThanhTienIch - 20,
            dimTabNoiDung.height
    );

    JPanel pnlNhanVienCanThongKe = new JPanel();
    Dimension dimPnlNhanVienCanThongKe = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
            130
    );

    JPanel pnlTieuDeLocTheoNhanVien = new JPanel();
    JLabel lblTieuDeLocTheoNhanVien = new JLabel("Đối tượng");

    JPanel pnlLuaChonLocTheoNhanVien = new JPanel();
    Dimension dimPnlLuaChonLocTheoNhanVien = new Dimension(
            dimPnlNhanVienCanThongKe.width,
            dimPnlNhanVienCanThongKe.height
            - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoNhanVien = new ButtonGroup();
    JRadioButton radLocTheoMoiNhanVien = new JRadioButton("Mọi nhân viên");
    JRadioButton radLocTheoTungNhanVien = new JRadioButton("");

    JPanel pnlLocTheoTungNhanVien = new JPanel();
    JTextField txtLocTheoTungNhanVien = new JTextField();
    Dimension dimTxtLocTheoTungNhanVien = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width - 50,
            35
    );

    Map<String, NhanVien> dsNhanVien = new TreeMap<>();

    int chieuRongPmnChuaDSNVTimDuoc = dimTxtLocTheoTungNhanVien.width + 350;
    JPopupMenu pmnChuaDSNVTimDuoc = new JPopupMenu();

    String[] tieuDeTblTam = {"Mã NV", "Ca làm", "Họ tên", "Số ĐT"};
    DefaultTableModel dtmDSNVTimDuoc = new DefaultTableModel(tieuDeTblTam, 0);
    JTable tblDSNVTimDuoc = new JTable(dtmDSNVTimDuoc);

    ArrayList<NhanVienTimDuoc> dsNVTimDuoc = new ArrayList<>();
    JPopupMenu pmnNhanVienTimDuoc = new JPopupMenu();
    String[] tieuDeTam = {"", ""};
    DefaultTableModel dtmNhanVienTimDuoc = new DefaultTableModel(tieuDeTam, 0);
    JTable tblNhanVienTimDuoc = new JTable(dtmNhanVienTimDuoc);

    /**
     *
     */

    JPanel pnlLocTheoThoiGian = new JPanel();
    Dimension dimPnlLocTheoThoiGian_1 = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
            130
    );
    Dimension dimPnlLocTheoThoiGian_2 = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
            255
    );

    JPanel pnlTieuDeLocTheoThoiGian = new JPanel();
    JLabel lblTieuDeLocTheoThoiGian = new JLabel("Thời gian");

    JPanel pnlLuaChonLocTheoThoiGian = new JPanel();
    Dimension dimPnlLuaChonLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
            75
    );

    ButtonGroup bngLocTheoThoiGian = new ButtonGroup();
    JRadioButton radLocTheoMocThoiGian = new JRadioButton("");
    JRadioButton radLocTuyChonThoiGian = new JRadioButton("Tuỳ chọn thời gian");

    JPanel pnlLocTheoMocThoiGian = new JPanel();
    String[] cacMocThoiGian = {
            "Hôm nay",
            "Tuần này",
            "Tháng này"
    };
    JComboBox<String> cbbCacMocThoiGian = new JComboBox<>(cacMocThoiGian);
    Dimension dimCbbCacMocThoiGian = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width - 50,
            35
    );

    JPanel pnlKhungChonThoiGian = new JPanel();
    Dimension dimPnlKhungChonThoiGian = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
            dimPnlLocTheoThoiGian_2.height
            - dimPnlTieuDeThanhTienIch.height
            - dimPnlLuaChonLocTheoThoiGian.height
    );

    Dimension dimPnlNgayBatDauVaNgayKetThuc = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
            dimPnlKhungChonThoiGian.height  / 2
    );

    Dimension dimDatePicker = new Dimension(
            dimPnlNgayBatDauVaNgayKetThuc.width
            - khoangCachSoVoiLeTraiCuaTieuDe - 8,
            dimPnlNgayBatDauVaNgayKetThuc.height
            - 33
    );

    JPanel pnlNgayBatDauTK = new JPanel();
    JLabel lblNgayBatDauTk = new JLabel("Ngày bắt đầu");
    DatePicker dtpNgayBatDau = new DatePicker();

    JPanel pnlNgayKetThuc = new JPanel();
    JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
    DatePicker dtpNgayKetThuc = new DatePicker();

    /**
     *
     */

    JPanel pnlLocTheoDoanhSo = new JPanel();
    Dimension dimPnlLocTheoDoanhSo = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
            200
    );

    JPanel pnlTieuDeLocTheoDoanhSo = new JPanel();
    JLabel lblTieuDeLocTheoDoanhSo = new JLabel("Doanh số");

    JPanel pnlLuaChonLocTheoDoanhSo = new JPanel();
    Dimension dimPnlLuaChonLocTheoDoanhSo = new Dimension(
            dimPnlLocTheoDoanhSo.width,
            dimPnlLocTheoDoanhSo.height
            - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoDoanhSo = new ButtonGroup();
    JRadioButton radNhanVienCoDoanhSoLonNhat = new JRadioButton("Doanh số lớn nhất");
    JRadioButton radNhanVienCoDoanhSoNhoNhat = new JRadioButton("Doanh số nhỏ nhất");
    JRadioButton radTatCaDoanhSo = new JRadioButton("Tất cả các mức");
    JRadioButton radDuoi30tr = new JRadioButton("Dưới 30 triệu");
    JRadioButton radTren30tr = new JRadioButton("Trên 30 triệu");

    /**
     *
     */

    JPanel pnlLocTheoTongTienHDBH = new JPanel();
    Dimension dimPnlLocTheoTongTienHDBH = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width,
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

    /**
     *
     */

    JPanel pnlNoiDungTKDoanhThuTheoNhanVien = new JPanel();
    Dimension dimPnlNoiDungTKDoanhThuTheoNhanVien = new Dimension(
            dimTabNoiDung.width
                    - dimPnlThanhTienIchTKDoanhThuTheoNhanVien.width
                    - 5,
            dimTabNoiDung.height
    );

    JPanel pnlThanhCongCuTKDoanhThuTheoNhanVien = new JPanel();
    Dimension dimPnlThanhCongCuTKDoanhThuTheoNhanVien = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoNhanVien.width,
            50
    );

    JTextField txtTimKiemTrenTblTKDoanhThuTheoNhanVien = new JTextField();
    Dimension dimTxtTimKiemHDBH = new Dimension(
            300,
            43
    );

    JPanel pnlHopCongCuTKDoanhThuTheoNhanVien = new JPanel();
    Dimension dimPnlHopCongCuTKDoanhThuTheoNhanVien = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoNhanVien.width
                    - dimTxtTimKiemHDBH.width,
            dimTxtTimKiemHDBH.height
    );

    Dimension dimBtnXuatTKDoanhThuTheoNhanVienRaExcel = new Dimension(
            160,
            dimPnlHopCongCuTKDoanhThuTheoNhanVien.height
    );
    JButton btnXuatTKDoanhThuTheoNhanVienRaFile = new JButton(
            "Xuất dữ liệu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Export_24px_1.png")
            )
    );

    JPopupMenu pmnXuatData = new JPopupMenu();
    Dimension dimPmnXuatData = new Dimension(
            dimBtnXuatTKDoanhThuTheoNhanVienRaExcel.width,
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

    String[] tieuDeTblTKDoanhThuTheoNhanVien = {"Mã NV", "Họ tên", "SĐT", "Cấp bậc", "Số HĐ", "SLSP", "Doanh số"};
    DefaultTableModel dtmTKDTTheoNhanVien = new DefaultTableModel(tieuDeTblTKDoanhThuTheoNhanVien, 0);
    TableRowSorter trsTKDTTheoNhanVien = new TableRowSorter(dtmTKDTTheoNhanVien);

    String[] tieuDeTableTKDoanhThuTheoHDBH = {"Mã HĐBH", "SĐT KH", "Thời gian lập", "SLSP", "Chưa thuế", "VAT", "Tổng tiền"};
    DefaultTableModel dtmDuLieuTKDoanhThuTheoHDBH = new DefaultTableModel(tieuDeTableTKDoanhThuTheoHDBH, 0);
    TableRowSorter trsDuLieuTKDoanhThuTheoHDBH = new TableRowSorter(dtmDuLieuTKDoanhThuTheoHDBH);

    JTable tblDuLieuTKDoanhThuTheoNhanVien = new JTable(){
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

    JScrollPane scrChuaTblDuLieuTKDoanhThuTheoNhanVien = new JScrollPane(tblDuLieuTKDoanhThuTheoNhanVien);
    Dimension dimScrChuaTblDuLieuTKDoanhThuTheoNhanVien = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoNhanVien.width,
            dimPnlNoiDungTKDoanhThuTheoNhanVien.height
                    - dimPnlThanhCongCuTKDoanhThuTheoNhanVien.height
                    - 73
    );

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );
}
