package ui.giaodienchinh.pnlqlkhachhang;

import com.github.lgooddatepicker.components.DatePicker;
import entity.KhachHang;
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

public interface IDSBienPnlQLKhachHang extends IDSBienMacDinh, IDSBienGDChinh {
    JPanel pnlQLKH = new JPanel();
    Dimension dimPnlQLKH = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhTienIch = new JPanel();
    Dimension dimPnlThanhTienIch = new Dimension(
            chieuRongThanhTienIch,
            dimPnlQLKH.height
    );

    JPanel pnlLocTheoGioiTinh = new JPanel();
    Dimension dimPnlLocTheoGioiTinh = new Dimension(
            dimPnlThanhTienIch.width,
            250
    );

    JPanel pnlTieuDeLocTheoGioiTinh = new JPanel();
    JLabel lblTieuDeLocTheoGioiTinh = new JLabel("Giới tính");

    JPanel pnlLuaChonLocTheoGioiTinh = new JPanel();
    Dimension dimPnlLuaChonLocTheoGioiTinh = new Dimension(
            dimPnlLocTheoGioiTinh.width,
            dimPnlLocTheoGioiTinh.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoGioiTinh = new ButtonGroup();
    JRadioButton radTatCaCacGioi = new JRadioButton("Tất cả             ");
    JRadioButton radNam = new JRadioButton("Nam                         ");
    JRadioButton radNu = new JRadioButton("Nữ");

    JPanel pnlLocTheoThoiGian = new JPanel();
    Dimension dimPnlLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIch.width,
            420
    );

    JPanel pnlTieuDeLocTheoThoiGian = new JPanel();
    JLabel lblLocTheoThoiGian = new JLabel("Thời điểm mua lần đầu");

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

    JPanel pnlNoiDungQLKHChinh = new JPanel();
    Dimension dimPnlNoiDungQLKHChinh = new Dimension(
            dimPnlQLKH.width
                    - dimPnlThanhTienIch.width,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhCongCu = new JPanel();
    Dimension dimPnlThanhCongCu = new Dimension(
            dimPnlNoiDungQLKHChinh.width,
            50
    );

    JTextField txtTimKiem = new JTextField();
    Dimension dimTxtTimKiem = new Dimension(
            300,
            43
    );

    JPopupMenu pmnCheDoTimKiem = new JPopupMenu();

    ImageIcon imiIconTimKiemTrongCSDL = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/find_24px_2.png")
    );
    JMenuItem mniTimKiemTrongCSDL = new JMenuItem("Tìm trong kho dữ liệu");

    ImageIcon imiIconTimKiemTrenTrenTable = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/find_24px_1.png")
    );
    JMenuItem mniTimKiemTrenTable = new JMenuItem("Tìm trên bảng bên dưới");

    Map<Integer, KhachHang> dsKhachHang = new TreeMap<>();

    int chieuRongPmnChuaDSKHTimDuoc = dimTxtTimKiem.width + 120;
    JPopupMenu pmnChuaDSKHTimDuoc = new JPopupMenu();

    String[] tieuDeTblTam = {"Mã KH", "Số ĐT", "Họ tên"};
    DefaultTableModel dtmDSKHTimDuoc = new DefaultTableModel(tieuDeTblTam, 0);
    JTable tblDSKHTimDuoc = new JTable(dtmDSKHTimDuoc){
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
            dimPnlNoiDungQLKHChinh.width,
            dimPnlNoiDungQLKHChinh.height
                    - dimPnlThanhCongCu.height
    );

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    String[] tieuDeTable = {"Mã KH", "Họ tên", "Số ĐT", "Địa chỉ", "Giới tính", "Ngày thêm"};
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
