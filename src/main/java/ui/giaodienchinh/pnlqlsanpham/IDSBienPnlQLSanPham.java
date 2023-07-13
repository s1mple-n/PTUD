package ui.giaodienchinh.pnlqlsanpham;

import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public interface IDSBienPnlQLSanPham extends IDSBienMacDinh, IDSBienGDChinh {
    JPanel pnlQLSP = new JPanel();
    Dimension dimPnlQLSP = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );


    JPanel pnlThanhTienIch = new JPanel();
    Dimension dimPnlThanhTienIch = new Dimension(
            chieuRongThanhTienIch,
            dimPnlQLSP.height
    );

    JPanel pnlLocTheoDonGia = new JPanel();
    Dimension dimPnlLocTheoDonGia = new Dimension(
            dimPnlThanhTienIch.width,
            250
    );

    JPanel pnlTieuDeLocTheoDonGia = new JPanel();
    JLabel lblTieuDeLocTheoDonGia = new JLabel("Đơn giá");

    JPanel pnlLuaChonLocTheoDonGia = new JPanel();
    Dimension dimPnlLuaChonLocTheoDonGia = new Dimension(
            dimPnlLocTheoDonGia.width,
            dimPnlLocTheoDonGia.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoDonGia= new ButtonGroup();
    JRadioButton radTatCaMucGia = new JRadioButton("Tất cả          ");
    JRadioButton radDuoi500k = new JRadioButton("Dưới 500 nghìn");
    JRadioButton radTu500kDen2tr = new JRadioButton("500 nghìn - 2 triệu");
    JRadioButton radTu2trDen5tr = new JRadioButton("2 triệu - 5 triệu");
    JRadioButton radTren5tr = new JRadioButton("Trên 5 triệu");

    JPanel pnlLocTheoTinhTrang = new JPanel();
    Dimension dimPnlLocTheoTinhTrang = new Dimension(
            dimPnlThanhTienIch.width,
            dimPnlThanhTienIch.height
                    - dimPnlLocTheoDonGia.height
    );

    JPanel pnlTieuDeLocTheoTinhTrang = new JPanel();
    JLabel lblLocTheoTinhTrang = new JLabel("Tình trạng");

    JPanel pnlLuaChonLocTheoTinhTrang = new JPanel();
    Dimension dimPnlLuaChonLocTheoTinhTrang = new Dimension(
            dimPnlThanhTienIch.width,
            110
    );

    ButtonGroup bngLocTheoTinhTrang = new ButtonGroup();
    JRadioButton radMoiTinhTrang = new JRadioButton("Tất cả            ");
    JRadioButton radConHang = new JRadioButton("Còn hàng");
    JRadioButton radHetHang = new JRadioButton("Đã hết hàng");

    /**
     *
     */

    JPanel pnlNoiDungQLSPChinh = new JPanel();
    Dimension dimPnlNoiDungQLSPChinh = new Dimension(
            dimPnlQLSP.width
                    - dimPnlThanhTienIch.width,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhCongCu = new JPanel();
    Dimension dimPnlThanhCongCu = new Dimension(
            dimPnlNoiDungQLSPChinh.width,
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

    JPopupMenu pmnKetQuaTimKiemSanPham = new JPopupMenu();
    int chieuRongPmnKetQuaTimKiemSanPham = dimTxtTimKiem.width + 400;

    String[] tieuDeDanhDauChoTblKetQuaTimKiemSanPham = {"Mã SP", "Tên SP", "Thương hiệu", "Đơn giá", "SL tồn"};
    DefaultTableModel dtmKetQuaTimKiemSanPham = new DefaultTableModel(tieuDeDanhDauChoTblKetQuaTimKiemSanPham, 0);
    JTable tblKetQuaTimKiemSanPham = new JTable(dtmKetQuaTimKiemSanPham){
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
            dimPnlNoiDungQLSPChinh.width,
            dimPnlNoiDungQLSPChinh.height
                    - dimPnlThanhCongCu.height
    );

    Locale vn = new Locale("vi", "vn");

    String pt = "###.###";
    DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(
            vn
    );

    String[] tieuDeTable = {"Mã SP", "Tên SP", "Chất liệu", "Thương hiệu", "Đơn giá", "SL Tồn"};
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

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );
}
