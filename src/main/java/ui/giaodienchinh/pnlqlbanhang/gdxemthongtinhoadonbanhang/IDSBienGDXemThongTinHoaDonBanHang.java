package ui.giaodienchinh.pnlqlbanhang.gdxemthongtinhoadonbanhang;

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

public interface IDSBienGDXemThongTinHoaDonBanHang extends IDSBienMacDinh, IDSBienGDChinh {
    Dimension dimGDXemTTHoaDonBanHang = new Dimension(
            dimGDChinh.width,
            dimGDChinh.height
    );

    /**
     *
     */

    JPanel pnlThanhCongCu = new JPanel();
    Color mauNenPnlThanhCongCu = new Color(9, 136, 184);
    Dimension dimPnlThanhCongCu = new Dimension(
            dimGDXemTTHoaDonBanHang.width,
            70
    );

    JPanel pnlHopTienIch = new JPanel();
    Dimension dimPnlHopTienIch = new Dimension(
            500,
            dimPnlThanhCongCu.height
                    - 30
    );

    /**
     *
     */

    JTextField txtTimKiemSanPham = new JTextField();
    Dimension dimTxtTimKiemSanPham = new Dimension(
            400,
            dimPnlHopTienIch.height
    );


    //

    JPanel pnlHopCongCu = new JPanel();
    Dimension dimPnlHopCongCu = new Dimension(
            dimPnlThanhCongCu.width
                    - dimPnlHopTienIch.width,
            dimTxtTimKiemSanPham.height
    );


    /**
     *
     */
    JPanel pnlHoaDonBanHangChinh = new JPanel();
    Dimension dimPnlTaoHoaDonBanHangChinh = new Dimension(
            dimGDXemTTHoaDonBanHang.width,
            dimGDXemTTHoaDonBanHang.height
                    - dimPnlThanhCongCu.height
    );


    /**
     *
     */

    JPanel pnlMucTongKetHoaDonVaThanhToan = new JPanel();
    Dimension dimPnlMucTongKetHoaDonVaThanhToan = new Dimension(
            350,
            dimPnlTaoHoaDonBanHangChinh.height
    );

    //
    JPanel pnlThanhToan = new JPanel();
    Dimension dimPnlThanhToan = new Dimension(
            dimPnlMucTongKetHoaDonVaThanhToan.width,
            80
    );

    Color mauNenBtnThoat = new Color(219, 68, 111);
    Font fntBtnThoat = new Font(tenFontMacDinh, Font.PLAIN, 25);
    JButton btnThoat = new JButton("Thoát");
    Dimension dimBtnThoat = new Dimension(
            dimPnlThanhToan.width - 20,
            dimPnlThanhToan.height
    );

    //
    JPanel pnlThongTinHoaDonBanHang = new JPanel();
    Dimension dimPnlThongTinHoaDonBanHang = new Dimension(
            dimPnlMucTongKetHoaDonVaThanhToan.width,
            dimPnlMucTongKetHoaDonVaThanhToan.height
                    - dimPnlThanhToan.height
    );

    //
    Dimension dimPnlConCuaPnlTTHoaDonBanHang = new Dimension(
            dimPnlThongTinHoaDonBanHang.width - 20,
            50
    );

    Dimension dimTxtChuaThongTinCuaHDBH = new Dimension(
            230,
            20
    );

    JPanel pnlMaHDBH = new JPanel();
    JLabel lblMaHDBH = new JLabel("Mã HDBH:");
    JTextField txtMaHDBH = new JTextField("0");

    JPanel pnlMaNVLap = new JPanel();
    JLabel lblMaNVLap = new JLabel("Mã NV lập:");
    JTextField txtMaNVLap = new JTextField("0");

    JPanel pnlTTKH = new JPanel();
    JLabel lblTTKH = new JLabel("Khách hàng:");
    JTextField txtTTKH = new JTextField("0");
    JScrollPane scrTTKH = new JScrollPane(txtTTKH);

    JPanel pnlTGLap = new JPanel();
    JLabel lblTGLap = new JLabel("TG lập:");
    JTextField txtTGLap = new JTextField("0");

    JPanel pnlSLSP = new JPanel();
    JLabel lblTieuDePnlSLSP = new JLabel("SL sản phẩm");
    JTextField txtSLSP = new JTextField("0");

    JPanel pnlTongTien = new JPanel();
    JLabel lblTieuDePnlTongTien = new JLabel("Tổng tiền");
    JTextField txtTongTien = new JTextField("0");

    JPanel pnlVAT = new JPanel();
    JLabel lblTieuDePnlVAT = new JLabel("VAT");
    JTextField txtVAT = new JTextField("0");

    JPanel pnlTienKhachPhaiTra = new JPanel();
    JLabel lblTieuDePnlTienKhachPhaiTra = new JLabel("Khách phải trả");
    JTextField txtTienKhachPhaiTra = new JTextField("0");

    JPanel pnlTienKhachDua = new JPanel();
    JLabel lblTieuDePnlTienKhachDua = new JLabel("Tiền khách đưa");
    JTextField txtTienKhachDua = new JTextField();

    JPanel pnlTienThua = new JPanel();
    JLabel lblTieuDePnlTienThua = new JLabel("Tiền thừa");
    JTextField txtTienThua = new JTextField("0");


    JPanel pnlDanhSachSanPhamKHMua = new JPanel();
    Dimension dimPnlDanhSachSanPhamKHMua = new Dimension(
            dimPnlTaoHoaDonBanHangChinh.width
                    - dimPnlMucTongKetHoaDonVaThanhToan.width,
            dimPnlTaoHoaDonBanHangChinh.height
    );

    /**
     * <p>0: STT</p>
     * <p>1: MaSP</p>
     * <p>2: TenSP</p>
     * <p>3: SLSP ban</p>
     * <p>4: Don gia ban</p>
     * <p>5: Thanh tien</p>
     */
    String[] tieuDeTableDSSPKHMua = {
            "STT",
            "MaSP",
            "TenSP",
            "SLSP",
            "Đơn giá",
            "Thành tiền"
    };
    DefaultTableModel dtmDanhSachSanPhamKHMua = new DefaultTableModel(tieuDeTableDSSPKHMua, 0);
    TableRowSorter trsDanhSachSPKHMua = new TableRowSorter(dtmDanhSachSanPhamKHMua);

    JTable tblDanhSachSanPhamKHMua = new JTable(dtmDanhSachSanPhamKHMua){
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

    JScrollPane scrChuaTblDSSanPhamKHMua = new JScrollPane(tblDanhSachSanPhamKHMua);

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    DecimalFormat df = new DecimalFormat("#,###.###");

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
}
