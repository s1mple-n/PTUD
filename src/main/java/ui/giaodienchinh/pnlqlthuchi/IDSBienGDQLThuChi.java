package ui.giaodienchinh.pnlqlthuchi;

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

public interface IDSBienGDQLThuChi extends IDSBienMacDinh, IDSBienGDChinh {
    JPanel pnlQLTC = new JPanel();
    Dimension dimPnlQLTC = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    /**
     * <p>Dung khi cap bac nhan vien la nhan vien ban hang</p>
     */

    Dimension kichThuocCacPnlTomTat = new Dimension(
            235, 140
    );
    Font fontLbTieuDeNut = new Font(tenFontMacDinh, Font.BOLD, 17);
    Color mauChuLbNut = new Color(94, 107, 151);

    JPanel pnlTaoPhieuDoiChung = new JPanel();
    JLabel lblBtTaoPhieuDoiChung = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_64px_3.png")
            )
    );
    JLabel lblTieuDeTaoPhieuDoiChung = new JLabel("Tạo phiếu đối chứng");
    Color mauVienPnlTaoPhieuDoiChung = new Color(94, 107, 151);
    Color mauVienPnlTaoPhieuDoiChungKhiHover = new Color(45, 196, 148);

    JPanel pnlTaoNhatKiBHTC = new JPanel();
    JLabel lblBtTaoNhatKiBHTC = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_64px_4.png")
            )
    );
    JLabel lblTieuDeTaoNhatKiBHTC = new JLabel("Tạo nhật kí BHTC");
    Color mauVienPnlTaoNhatKiBHTC = new Color(57, 148, 227);

    /**
     * <p>Dung khi cap bac nhan vien la nguoi quan li cua hang</p>
     */

    JPanel pnlThanhTienIch = new JPanel();
    Dimension dimPnlThanhTienIch = new Dimension(
            chieuRongThanhTienIch,
            dimPnlNoiDung.height
    );

    JPanel pnlLocTheoLoaiPhieu = new JPanel();
    Dimension dimPnlLocTheoLoaiPhieu = new Dimension(
            dimPnlThanhTienIch.width,
            250
    );

    JPanel pnlTieuDeLocTheoLoaiPhieu = new JPanel();
    JLabel lblTieuDeLocTheoLoaiPhieu = new JLabel("Loại phiếu");

    JPanel pnlLuaChonLocTheoLoaiPhieu = new JPanel();
    Dimension dimPnlLuaChonLocTheoLoaiPhieu = new Dimension(
            dimPnlLocTheoLoaiPhieu.width,
            dimPnlLocTheoLoaiPhieu.height
                    - dimPnlTieuDeThanhTienIch.height
    );

    ButtonGroup bngLocTheoGioiTinh = new ButtonGroup();
    JRadioButton radPhieuDoiChung = new JRadioButton("Phiếu đối chứng               ");
    JRadioButton radNhatKiBHTC = new JRadioButton("Nhật kí bán hàng theo ca");

    JPanel pnlLocTheoThoiGian = new JPanel();
    Dimension dimPnlLocTheoThoiGian = new Dimension(
            dimPnlThanhTienIch.width,
            420
    );

    JPanel pnlTieuDeLocTheoThoiGian = new JPanel();
    JLabel lblLocTheoThoiGian = new JLabel("Thời điểm lập phiếu");

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

    JPanel pnlNoiDungQLThuChiChinh = new JPanel();
    Dimension dimPnlNoiDungQLThuChiChinh = new Dimension(
            dimPnlQLTC.width
                    - dimPnlThanhTienIch.width,
            dimPnlNoiDung.height
    );

    JPanel pnlThanhCongCu = new JPanel();
    Dimension dimPnlThanhCongCu = new Dimension(
            dimPnlNoiDungQLThuChiChinh.width,
            50
    );

    JTextField txtTimKiem = new JTextField();
    Dimension dimTxtTimKiem = new Dimension(
            300,
            43
    );

    JPopupMenu pmnCheDoTimKiem = new JPopupMenu();

    JMenuItem mniTimKiemPDCTrongCSDL = new JMenuItem(
            "Tìm phiếu đối chứng trong kho dữ liệu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/find_24px_2.png")
            )
    );
    JMenuItem mniTimKiemNKBHTrongCSDL = new JMenuItem(
            "Tìm nhật kí bán hàng trong kho dữ liệu",
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

    JPopupMenu pmnKetQuaTimKiem = new JPopupMenu();
    int chieuRongPmnKetQuaTimKiem = dimTxtTimKiem.width + 230;

    String[] tieuDeDanhDauChoTblKetQuaTimKiemPhieuDoiChung = {"Mã PDC", "Mã NV lập", "Mã NV kiểm", "Thời gian lập"};
    DefaultTableModel dtmKetQuaTimKiemPhieuDoiChung = new DefaultTableModel(tieuDeDanhDauChoTblKetQuaTimKiemPhieuDoiChung, 0);

    String[] tieuDeDanhDauChoTblKetQuaTimKiemNhatKiBHTC = {"Mã NKBH", "Mã NV lập", "Thời gian lập"};
    DefaultTableModel dtmKetQuaTimKiemNhatKiBHTC = new DefaultTableModel(tieuDeDanhDauChoTblKetQuaTimKiemNhatKiBHTC, 0);

    JTable tblKetQuaTimKiem = new JTable(dtmKetQuaTimKiemPhieuDoiChung){
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

    JButton btnThemPhieu = new JButton(
            "Thêm phiếu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_24px_1.png")
            )
    );
    JPopupMenu pmnCacLoaiPhieuCanThem = new JPopupMenu();
    Color mauNenPmnCacLoaiPhieuCanThem = new Color(212, 247, 255);
    Dimension kichThuocPmnCacLoaiPhieuCanThem = new Dimension(240, 80);

    JMenuItem mniLapPhieuDoiChung = new JMenuItem(
            "Phiếu đối chứng",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_24px_1.png")
            )
    );

    JMenuItem mniLapNhatKiBHTC = new JMenuItem(
            "Nhật kí bán hàng theo ca",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_24px_1.png")
            )
    );


    JButton btnXuatDuLieuTrongTableRaFile = new JButton(
            "Xuất dữ liệu",
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
            dimPnlNoiDungQLThuChiChinh.width,
            dimPnlNoiDungQLThuChiChinh.height
                    - dimPnlThanhCongCu.height
    );

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    String[] tieuDeTablePhieuDoiChung = {"Mã PĐC", "Mã NV lập", "Mã NV kiểm", "Thời gian lập", "Số tiền giao", "Số tiền kiểm"};
    DefaultTableModel dtmDuLieuPhieuDoiChungTraCuuDuoc = new DefaultTableModel(tieuDeTablePhieuDoiChung, 0);
    TableRowSorter trsDuLieuPhieuDoiChungTraCuuDuoc = new TableRowSorter(dtmDuLieuPhieuDoiChungTraCuuDuoc);

    String[] tieuDeTableNhatKiBHTheoCa = {"Mã NKBHTC", "Mã NV lập", "Thời gian lập", "SLSP bán được", "SLSP còn lại", "SLSP mới nhập", "Doanh thu"};
    DefaultTableModel dtmDuLieuNhatKiBHTheoCa = new DefaultTableModel(tieuDeTableNhatKiBHTheoCa, 0);
    TableRowSorter trsDuLieuNhatKiBHTC = new TableRowSorter(dtmDuLieuNhatKiBHTheoCa);

    JTable tblDuLieuTraCuuDuoc = new JTable(){
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

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy hh:mm:ss a"
    );
}
