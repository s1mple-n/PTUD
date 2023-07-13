package ui.giaodienchinh.pnllapthongke.pnlthongkedoanhthutheokhachhang;

import com.github.lgooddatepicker.components.DatePicker;
import entity.KhachHang;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;
import ui.giaodienchinh.pnlqlkhachhang.kieudulieudacbiet.KhachHangTimDuoc;

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

public interface IDSBienPnlTKDoanhThuTheoKhachHang extends IDSBienGDChinh, IDSBienMacDinh {
    Dimension dimTabNoiDung = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhTienIchTKDoanhThuTheoKhachHang = new JPanel();
    Dimension dimPnlThanhTienIchTKDoanhThuTheoKhachHang = new Dimension(
            chieuRongThanhTienIch - 20,
            dimTabNoiDung.height
    );

    JPanel pnlKhachHangCanThongKe = new JPanel();
    Dimension dimPnlKhachHangCanThongKe = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
            130
    );

    JPanel pnlTieuDeLocTheoKhachHang = new JPanel();
    JLabel lblTieuDeLocTheoKhachHang = new JLabel("Đối tượng");

    JPanel pnlLuaChonLocTheoKhachHang = new JPanel();
    Dimension dimPnlLuaChonLocTheoKhachHang = new Dimension(
            dimPnlKhachHangCanThongKe.width,
            dimPnlKhachHangCanThongKe.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoKhachHang = new ButtonGroup();
    JRadioButton radLocTheoMoiKhachHang = new JRadioButton("Mọi khách hàng");
    JRadioButton radLocTheoTungKhachHang = new JRadioButton("");

    JPanel pnlLocTheoTungKhachHang = new JPanel();
    JTextField txtLocTheoTungKhachHang = new JTextField();
    Dimension dimTxtLocTheoTungKhachHang = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width - 50,
            35
    );

    Map<Integer, KhachHang> dsKhachHang = new TreeMap<>();

    int chieuRongPmnChuaDSKHTimDuoc = dimTxtLocTheoTungKhachHang.width + 300;
    JPopupMenu pmnChuaDSKHTimDuoc = new JPopupMenu();

    String[] tieuDeTblTam = {"Mã KH", "Số ĐT", "Họ tên"};
    DefaultTableModel dtmDSKHTimDuoc = new DefaultTableModel(tieuDeTblTam, 0);
    JTable tblDSKHTimDuoc = new JTable(dtmDSKHTimDuoc);

    ArrayList<KhachHangTimDuoc> dsKHTimDuoc = new ArrayList<>();
    JPopupMenu pmnKhachHangTimDuoc = new JPopupMenu();
    String[] tieuDeTam = {"", ""};
    DefaultTableModel dtmKhachHangTimDuoc = new DefaultTableModel(tieuDeTam, 0);
    JTable tblKhachHangTimDuoc = new JTable(dtmKhachHangTimDuoc);

    /**
     *
     */

    JPanel pnlLocTheoThoiGian = new JPanel();
    Dimension dimPnlLocTheoThoiGian_1 = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
            130
    );
    Dimension dimPnlLocTheoThoiGian_2 = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
            255
    );

    JPanel pnlTieuDeLocTheoThoiGian = new JPanel();
    JLabel lblTieuDeLocTheoThoiGian = new JLabel("Thời gian");

    JPanel pnlLuaChonLocTheoThoiGian = new JPanel();
    Dimension dimPnlLuaChonLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
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
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width - 50,
            35
    );

    JPanel pnlKhungChonThoiGian = new JPanel();
    Dimension dimPnlKhungChonThoiGian = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
            dimPnlLocTheoThoiGian_2.height
                    - dimPnlTieuDeThanhTienIch.height
                    - dimPnlLuaChonLocTheoThoiGian.height
    );

    Dimension dimPnlNgayBatDauVaNgayKetThuc = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
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
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
            200
    );

    JPanel pnlTieuDeLocTheoDoanhSo = new JPanel();
    JLabel lblTieuDeLocTheoDoanhSo = new JLabel("Sức mua");

    JPanel pnlLuaChonLocTheoDoanhSo = new JPanel();
    Dimension dimPnlLuaChonLocTheoDoanhSo = new Dimension(
            dimPnlLocTheoDoanhSo.width,
            dimPnlLocTheoDoanhSo.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoDoanhSo = new ButtonGroup();
    JRadioButton radKhachHangCoDoanhSoLonNhat = new JRadioButton("Sức mua lớn nhất");
    JRadioButton radKhachHangCoDoanhSoNhoNhat = new JRadioButton("Sức mua nhỏ nhất");
    JRadioButton radTatCaDoanhSo = new JRadioButton("Tất cả     ");
    JRadioButton radDuoi30tr = new JRadioButton("Dưới 10 triệu");
    JRadioButton radTren30tr = new JRadioButton("Trên 10 triệu");

    /**
     *
     */

    JPanel pnlLocTheoTongTienHDBH = new JPanel();
    Dimension dimPnlLocTheoTongTienHDBH = new Dimension(
            dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width,
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

    JPanel pnlNoiDungTKDoanhThuTheoKhachHang = new JPanel();
    Dimension dimPnlNoiDungTKDoanhThuTheoKhachHang = new Dimension(
            dimTabNoiDung.width
                    - dimPnlThanhTienIchTKDoanhThuTheoKhachHang.width
                    - 5,
            dimTabNoiDung.height
    );

    JPanel pnlThanhCongCuTKDoanhThuTheoKhachHang = new JPanel();
    Dimension dimPnlThanhCongCuTKDoanhThuTheoKhachHang = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoKhachHang.width,
            50
    );

    JTextField txtTimKiemTrenTblTKDoanhThuTheoKhachHang = new JTextField();
    Dimension dimTxtTimKiemHDBH = new Dimension(
            300,
            43
    );

    JPanel pnlHopCongCuTKDoanhThuTheoKhachHang = new JPanel();
    Dimension dimPnlHopCongCuTKDoanhThuTheoKhachHang = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoKhachHang.width
                    - dimTxtTimKiemHDBH.width,
            dimTxtTimKiemHDBH.height
    );

    Dimension dimBtnXuatTKDoanhThuTheoKhachHangRaExcel = new Dimension(
            160,
            dimPnlHopCongCuTKDoanhThuTheoKhachHang.height
    );
    JButton btnXuatTKDoanhThuTheoKhachHangRaFile = new JButton(
            "Xuất dữ liệu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Export_24px_1.png")
            )
    );

    JPopupMenu pmnXuatData = new JPopupMenu();
    Dimension dimPmnXuatData = new Dimension(
            dimBtnXuatTKDoanhThuTheoKhachHangRaExcel.width,
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

    String[] tieuDeTblTKDoanhThuTheoNhanVien = {"Mã KH", "Họ tên", "Số ĐT", "Giới tính", "Số HĐ", "SLSP", "Sức mua"};
    DefaultTableModel dtmTKDTTheoKhachHang = new DefaultTableModel(tieuDeTblTKDoanhThuTheoNhanVien, 0);
    TableRowSorter trsTKDTTheoKhachHang = new TableRowSorter(dtmTKDTTheoKhachHang);

    String[] tieuDeTableTKDoanhThuTheoHDBH = {"Mã HĐBH", "SĐT KH", "Thời gian lập", "SLSP", "Chưa thuế", "VAT", "Tổng tiền"};
    DefaultTableModel dtmDuLieuTKDoanhThuTheoHDBH = new DefaultTableModel(tieuDeTableTKDoanhThuTheoHDBH, 0);
    TableRowSorter trsDuLieuTKDoanhThuTheoHDBH = new TableRowSorter(dtmDuLieuTKDoanhThuTheoHDBH);

    JTable tblDuLieuTKDoanhThuTheoKhachHang = new JTable(){
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

    JScrollPane scrChuaTblDuLieuTKDoanhThuTheoKhachHang = new JScrollPane(tblDuLieuTKDoanhThuTheoKhachHang);
    Dimension dimScrChuaTblDuLieuTKDoanhThuTheoKhachHang = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoKhachHang.width,
            dimPnlNoiDungTKDoanhThuTheoKhachHang.height
                    - dimPnlThanhCongCuTKDoanhThuTheoKhachHang.height
                    - 73
    );

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );
}
