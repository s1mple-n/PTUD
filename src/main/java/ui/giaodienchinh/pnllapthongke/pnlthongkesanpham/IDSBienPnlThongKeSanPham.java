package ui.giaodienchinh.pnllapthongke.pnlthongkesanpham;

import com.github.lgooddatepicker.components.DatePicker;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface IDSBienPnlThongKeSanPham extends IDSBienMacDinh, IDSBienGDChinh {
    Dimension dimTabNoiDung = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhTienIchTKSanPham = new JPanel();
    Dimension dimPnlThanhTienIchTKSanPham = new Dimension(
            chieuRongThanhTienIch - 20,
            dimTabNoiDung.height
    );

    JPanel pnlSanPhamCanThongKe = new JPanel();
    Dimension dimPnlSanPhamCanThongKe = new Dimension(
            dimPnlThanhTienIchTKSanPham.width,
            130
    );

    JPanel pnlTieuDeLocTheoDoiTuong = new JPanel();
    JLabel lblTieuDeLocTheoDoiTuong = new JLabel("Đối tượng");

    JPanel pnlLuaChonLocTheoDoiTuong = new JPanel();
    Dimension dimPnlLuaChonLocTheoDoiTuong = new Dimension(
            dimPnlSanPhamCanThongKe.width,
            dimPnlSanPhamCanThongKe.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoDoiTuong = new ButtonGroup();
    JRadioButton radLocTheoMoiSanPham = new JRadioButton("Mọi sản phẩm");
    JRadioButton radLocTheoTungSanPham = new JRadioButton("");

    JPanel pnlLocTheoTungSanPham = new JPanel();
    JTextField txtLocTheoTungSanPham = new JTextField();
    Dimension dimTxtLocTheoTungSanPham = new Dimension(
            dimPnlThanhTienIchTKSanPham.width - 50,
            35
    );

    int chieuRongPmnChuaDSSanPhamTimDuoc = dimTxtLocTheoTungSanPham.width + 300;
    JPopupMenu pmnChuaDSSanPhamTimDuoc = new JPopupMenu();

    String[] tieuDeTblTam = {"Mã SP", "Tên Sản phẩm"};
    DefaultTableModel dtmDSSanPhamTimDuoc = new DefaultTableModel(tieuDeTblTam, 0);
    JTable tblDSSanPhamTimDuoc = new JTable(dtmDSSanPhamTimDuoc);

    /**
     *
     */

    JPanel pnlLocTheoThoiGian = new JPanel();
    Dimension dimPnlLocTheoThoiGian_1 = new Dimension(
            dimPnlThanhTienIchTKSanPham.width,
            130
    );
    Dimension dimPnlLocTheoThoiGian_2 = new Dimension(
            dimPnlThanhTienIchTKSanPham.width,
            255
    );

    JPanel pnlTieuDeLocTheoThoiGian = new JPanel();
    JLabel lblTieuDeLocTheoThoiGian = new JLabel("Thời gian");

    JPanel pnlLuaChonLocTheoThoiGian = new JPanel();
    Dimension dimPnlLuaChonLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIchTKSanPham.width,
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
            dimPnlThanhTienIchTKSanPham.width - 50,
            35
    );

    JPanel pnlKhungChonThoiGian = new JPanel();
    Dimension dimPnlKhungChonThoiGian = new Dimension(
            dimPnlThanhTienIchTKSanPham.width,
            dimPnlLocTheoThoiGian_2.height
                    - dimPnlTieuDeThanhTienIch.height
                    - dimPnlLuaChonLocTheoThoiGian.height
    );

    Dimension dimPnlNgayBatDauVaNgayKetThuc = new Dimension(
            dimPnlThanhTienIchTKSanPham.width,
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

    JPanel pnlLocTheoDoanhThu = new JPanel();
    Dimension dimPnlLocTheoDoanhThu = new Dimension(
            dimPnlThanhTienIchTKSanPham.width,
            210
    );

    JPanel pnlTieuDeLocTheoDoanhThu = new JPanel();
    JLabel lblTieuDeLocTheoDoanhThu = new JLabel("Doanh thu");

    JPanel pnlLuaChonLocTheoDoanhThu = new JPanel();
    Dimension dimPnlLuaChonLocTheoDoanhThu = new Dimension(
            dimPnlLocTheoDoanhThu.width,
            dimPnlLocTheoDoanhThu.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoDoanhThu = new ButtonGroup();
    JRadioButton radSanPhamCoDoanhThuLonNhat = new JRadioButton("Doanh thu lớn nhất");
    JRadioButton radSanPhamCoDoanhThuNhoNhat = new JRadioButton("Doanh thu nhỏ nhất");
    JRadioButton radTatCaDoanhThu = new JRadioButton("Tất cả");
    JRadioButton radDuoi5SanPham = new JRadioButton("Dưới 5 sản phẩm");
    JRadioButton radTren5SanPham = new JRadioButton("Trên 5 sản phẩm");

    /**
     *
     */

    JPanel pnlLocTheoSucTieuThu = new JPanel();
    Dimension dimPnlLocTheoSucTieuThu = new Dimension(
            dimPnlThanhTienIchTKSanPham.width ,
            205
    );

    JPanel pnlTieuDeLocTheoSucTieuThu = new JPanel();
    JLabel lblTieuDeLocTheoSucTieuThu = new JLabel("  Sức tiêu thụ");

    JPanel pnlLuaChonLocTheoSucTieuThu = new JPanel();
    Dimension dimPnlLuaChonLocTheoSucTieuThu = new Dimension(
            dimPnlLocTheoSucTieuThu.width,
            dimPnlLocTheoSucTieuThu.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoSucTieuThu = new ButtonGroup();
    JRadioButton radTatCaMucTien = new JRadioButton("Tất cả             ");
    JRadioButton radDuoi600K = new JRadioButton("Dưới 600 nghìn");
    JRadioButton radTu600KDen1_5Tr = new JRadioButton("600 nghìn - 1.5 triệu");
    JRadioButton radTu1_5trDen3tr = new JRadioButton("1.5 triệu - 3 triệu");
    JRadioButton radTren3tr = new JRadioButton("Trên 3 triệu");

    /**
     *
     */

    JPanel pnlNoiDungTKDoanhThuTheoSanPham = new JPanel();
    Dimension dimPnlNoiDungTKDoanhThuTheoSanPham = new Dimension(
            dimTabNoiDung.width
                    - dimPnlThanhTienIchTKSanPham.width
                    - 5,
            dimTabNoiDung.height
    );

    JPanel pnlThanhCongCuTKSanPham = new JPanel();
    Dimension dimPnlThanhCongCuTKSanPham = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoSanPham.width,
            50
    );

    JTextField txtTimKiemTrenTblTKSanPham = new JTextField();
    Dimension dimTxtTimKiemSanPham = new Dimension(
            300,
            43
    );

    JPanel pnlHopCongCuTKSanPham = new JPanel();
    Dimension dimPnlHopCongCuTKSanPham = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoSanPham.width
                    - dimTxtTimKiemSanPham.width,
            dimTxtTimKiemSanPham.height
    );

    Dimension dimBtnXuatTKSanPhamRaFile = new Dimension(
            160,
            dimPnlHopCongCuTKSanPham.height
    );
    JButton btnXuatTKSanPhamRaFile = new JButton(
            "Xuất dữ liệu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Export_24px_1.png")
            )
    );

    JPopupMenu pmnXuatData = new JPopupMenu();
    Dimension dimPmnXuatData = new Dimension(
            dimBtnXuatTKSanPhamRaFile.width,
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

    String[] tieuDeTblTKDoanhThuTheoSanPham = {"Mã SP", "Tên sản phẩm", "Thương hiệu", "SL tồn", "SLSP", "Sức tiêu thụ"};
    DefaultTableModel dtmTKDTTheoSanPham = new DefaultTableModel(tieuDeTblTKDoanhThuTheoSanPham, 0);
    TableRowSorter trsTKDTTheoSanPham = new TableRowSorter(dtmTKDTTheoSanPham);

    String[] tieuDeTableTKDoanhThuTheoHDBH = {"Mã HĐBH", "Mã NV lập", "SĐT KH", "Thời gian lập", "SLSP", "Thành tiền SP", "Tổng tiền HĐ"};
    DefaultTableModel dtmDuLieuTKDoanhThuTheoHDBH = new DefaultTableModel(tieuDeTableTKDoanhThuTheoHDBH, 0);
    TableRowSorter trsDuLieuTKDoanhThuTheoHDBH = new TableRowSorter(dtmDuLieuTKDoanhThuTheoHDBH);

    JTable tblDuLieuTKDoanhThuTheoSanPham = new JTable(){
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

    JScrollPane scrChuaTblDuLieuTKDoanhThuTheoSanPham = new JScrollPane(tblDuLieuTKDoanhThuTheoSanPham);
    Dimension dimScrChuaTblDuLieuTKDoanhThuTheoSanPham = new Dimension(
            dimPnlNoiDungTKDoanhThuTheoSanPham.width,
            dimPnlNoiDungTKDoanhThuTheoSanPham.height
                    - dimPnlThanhCongCuTKSanPham.height
    );

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy hh:mm:ss a"
    );
}
