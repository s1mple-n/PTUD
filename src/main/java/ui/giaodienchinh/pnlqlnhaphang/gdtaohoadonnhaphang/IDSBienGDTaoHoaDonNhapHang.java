package ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang;

import entity.ChiTietHoaDonNhapHang;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public interface IDSBienGDTaoHoaDonNhapHang extends IDSBienMacDinh, IDSBienGDChinh {
    Dimension dimGDTaoHoaDonNhapHang = new Dimension(
            dimGDChinh.width,
            dimGDChinh.height
    );

    int[] danhSachKeyMap = {
            KeyEvent.VK_F1, //Btn xoa tat ca san pham
            KeyEvent.VK_F2, //Btn them tung san pham
            KeyEvent.VK_F3, //Btn nhap hang bang file Excel
            KeyEvent.VK_F4, //Txt thu nho man hinh
            KeyEvent.VK_F5, //Btn huy hoa don nhap hang
            KeyEvent.VK_F6, //Txt ma lo hang
            KeyEvent.VK_F7, //Txt ten nguoi giao hang
            KeyEvent.VK_F8 // Nut in hoa don
    };

    AtomicReference<LocalDateTime> thoiGianDatHang = new AtomicReference<>();
    AtomicInteger slsp = new AtomicInteger(0);
    AtomicReference<Double> tongTien = new AtomicReference<>(0.0);

    /**
     *
     */

    JPanel pnlThanhCongCu = new JPanel();
    Color mauNenPnlThanhCongCu = new Color(9, 136, 184);
    Dimension dimPnlThanhCongCu = new Dimension(
            dimGDTaoHoaDonNhapHang.width,
            70
    );

    JPanel pnlHopTienIch = new JPanel();
    Dimension dimPnlHopTienIch = new Dimension(
            500,
            dimPnlThanhCongCu.height
                    - 20
    );

    /**
     *
     */

    JTextField txtTimKiemChiTietHoaDonNhapHang = new JTextField();
    Dimension dimTxtTimKiemNhapHang = new Dimension(
            400,
            dimPnlHopTienIch.height
            - 5
    );

    /**
     *
     */
    JButton btnXoaTatCaChiTietHDNHDaThem = new JButton("",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Bin_32px_1.png")
            )
    );
    Dimension dimBtnXoaTatCaChiTietHDNHDaThem = new Dimension(
            40, 40
    );


    //

    JPanel pnlHopCongCu = new JPanel();
    Dimension dimPnlHopCongCu = new Dimension(
            dimPnlThanhCongCu.width
                    - dimPnlHopTienIch.width,
            dimTxtTimKiemNhapHang.height
            + 10
    );

    Dimension dimBtnThuNhoVaHuyHD = new Dimension(
            150, 45
    );

    Dimension dimBtnThemCTHDNHVaNhapExcel = new Dimension(
            dimBtnThuNhoVaHuyHD.width + 45,
            dimBtnThuNhoVaHuyHD.height
    );

    JButton btnThemChiTietHDNH = new JButton(
            "Sản phẩm (F2)",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_24px_1.png")
            )
    );

    JButton btnNhapFileExcel = new JButton(
            "Nhập Excel (F3)",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Excel_24px_1.png")
            )
    );

    Color mauNenBtnThuNhoManHinh = new Color(16, 188, 194);
    JButton btnThuNhoManHinh = new JButton("Tạm thoát (F4)");

    Color mauNenBtnHuyHoaDonNhapHang = new Color(209, 69, 109);
    JButton btnHuyHoaDonNhapHang = new JButton("Huỷ (F5)");


    /**
     *
     */
    JPanel pnlHoaDonNhapHangChinh = new JPanel();
    Dimension dimPnlTaoHoaDonNhapHangChinh = new Dimension(
            dimGDTaoHoaDonNhapHang.width,
            dimGDTaoHoaDonNhapHang.height
                    - dimPnlThanhCongCu.height
    );


    /**
     *
     */

    JPanel pnlMucTongKetVaInHoaDon = new JPanel();
    Dimension dimPnlMucTongKetVaInHoaDon = new Dimension(
            310,
            dimPnlTaoHoaDonNhapHangChinh.height
    );

    //
    JPanel pnlInHoaDon = new JPanel();
    Dimension dimPnlInHoaDon = new Dimension(
            dimPnlMucTongKetVaInHoaDon.width,
            80
    );

    Color mauNenBtnInHoaDon = new Color(32, 193, 230);
    Font fntBtnInHoaDon = new Font(tenFontMacDinh, Font.PLAIN, 25);
    JButton btnInHoaDon = new JButton("In hoá đơn (F8)");
    Dimension dimBtnInHoaDon = new Dimension(
            dimPnlInHoaDon.width - 20,
            dimPnlInHoaDon.height
    );

    //
    JPanel pnlThongTinHoaDonNhapHang = new JPanel();
    Dimension dimPnlThongTinHoaDonNhapHang = new Dimension(
            dimPnlMucTongKetVaInHoaDon.width,
            dimPnlMucTongKetVaInHoaDon.height
                    - dimPnlInHoaDon.height
    );

    Dimension dimCacPnlThongTin = new Dimension(
            dimPnlThongTinHoaDonNhapHang.width - 20,
            50
    );

    Dimension dimTxtMaLoHang = new Dimension(
            dimCacPnlThongTin.width,
            40
    );
    JTextField txtMaLoHang = new JTextField();

    Dimension dimTxtChuaThongTinNH = new Dimension(
            220,
            dimTxtMaLoHang.height - 20
    );

    JTextField txtTenNguoiGiaoHang = new JTextField();

    JPanel pnlTGDatHang = new JPanel();
    JLabel lblTieuDeTGDatHang = new JLabel("TG đặt hàng:");
    JTextField txtTGDatHang = new JTextField("0");

    JPanel pnlSLSP = new JPanel();
    JLabel lblTieuDePnlSLSP = new JLabel("SL sản phẩm");
    JTextField txtSLSP = new JTextField("0");

    JPanel pnlTongTien = new JPanel();
    JLabel lblTieuDePnlTongTien = new JLabel("Tổng tiền");
    JTextField txtTongTien = new JTextField("0");

    /**
     *
     */
    JPanel pnlDanhSachChiTietHDNH = new JPanel();
    Dimension dimPnlDanhSachChiTietHDNH = new Dimension(
            dimPnlTaoHoaDonNhapHangChinh.width
                    - dimPnlMucTongKetVaInHoaDon.width,
            dimPnlTaoHoaDonNhapHangChinh.height
    );

    /**
     * <p>0: STT</p>
     * <p>1: Nut delete</p>
     * <p>2: MaSP</p>
     * <p>3: TenSP</p>
     * <p>4: SLSP</p>
     * <p>5: Don gia</p>
     * <p>6: Thanh tien</p>
     */
    String[] tieuDeTblDSChiTietHDNH = {
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    DefaultTableModel dtmDSChiTietHDNH = new DefaultTableModel(tieuDeTblDSChiTietHDNH, 0);
    TableRowSorter trsDSChiTietHDNH = new TableRowSorter(dtmDSChiTietHDNH);

    JTable tblDSChiTietHDNH = new JTable(dtmDSChiTietHDNH){
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

    JScrollPane scrChuaTblDSChiTietHDNH = new JScrollPane(tblDSChiTietHDNH);

    ArrayList<ChiTietHoaDonNhapHang> dsChiTietHoaDonNhapHang = new ArrayList<>();

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    DecimalFormat df = new DecimalFormat("#,###.###");

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
}
