package ui.giaodienchinh.pnlqlnhaphang.gdxemthongtinhoadonnhaphang;

import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface IDSBienGDXemThongTinHoaDonNhapHang extends IDSBienMacDinh, IDSBienGDChinh {
    Dimension dimGDXemTTHoaDonNhapHang = new Dimension(
            dimGDChinh.width,
            dimGDChinh.height
    );

    /**
     *
     */

    JPanel pnlThanhCongCu = new JPanel();
    Color mauNenPnlThanhCongCu = new Color(9, 136, 184);
    Dimension dimPnlThanhCongCu = new Dimension(
            dimGDXemTTHoaDonNhapHang.width,
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
    JPanel pnlHoaDonNhapHangChinh = new JPanel();
    Dimension dimPnlTaoHoaDonNhapHangChinh = new Dimension(
            dimGDXemTTHoaDonNhapHang.width,
            dimGDXemTTHoaDonNhapHang.height
                    - dimPnlThanhCongCu.height
    );


    /**
     *
     */

    JPanel pnlMucTongKetVaThoat = new JPanel();
    Dimension dimPnlMucTongKetVaThoat = new Dimension(
            350,
            dimPnlTaoHoaDonNhapHangChinh.height
    );

    //
    JPanel pnlThoat = new JPanel();
    Dimension dimPnlInHoaDon = new Dimension(
            dimPnlMucTongKetVaThoat.width,
            80
    );

    Color mauNenBtnThoat = new Color(194, 52, 177);
    Font fntBtnThoat = new Font(tenFontMacDinh, Font.PLAIN, 25);
    JButton btnThoat = new JButton("Thoát");
    Dimension dimBtnThoat = new Dimension(
            dimPnlInHoaDon.width - 20,
            dimPnlInHoaDon.height
    );

    //
    JPanel pnlThongTinHoaDonNhapHang = new JPanel();
    Dimension dimPnlThongTinHoaDonNhapHang = new Dimension(
            dimPnlMucTongKetVaThoat.width,
            dimPnlMucTongKetVaThoat.height
                    - dimPnlInHoaDon.height
    );

    //
    Dimension dimPnlConCuaPnlTTHoaDonNhapHang = new Dimension(
            dimPnlThongTinHoaDonNhapHang.width - 20,
            50
    );

    Dimension dimTxtChuaThongTinCuaHDNH = new Dimension(
            230,
            20
    );

    JPanel pnlMaHDNH = new JPanel();
    JLabel lblMaHDNH = new JLabel("Mã HDNH:");
    JTextField txtMaHDNH = new JTextField("0");

    JPanel pnlMaNVLap = new JPanel();
    JLabel lblMaNVLap = new JLabel("Mã NV lập:");
    JTextField txtMaNVLap = new JTextField("0");

    JPanel pnlMaLoHang = new JPanel();
    JLabel lblMaLoHang = new JLabel("Mã lô hàng:");
    JTextField txtMaLoHang = new JTextField("0");

    JPanel pnlTenNguoiGiaoHang = new JPanel();
    JLabel lblTenNguoiGiaoHang = new JLabel("Tên shipper:");
    JTextField txtTenNguoiGiaoHang = new JTextField("0");

    JPanel pnlTGDat = new JPanel();
    JLabel lblTGDat = new JLabel("TG đặt:");
    JTextField txtTGDat = new JTextField("0");

    JPanel pnlTGGiao = new JPanel();
    JLabel lblTGGiao = new JLabel("TG giao:");
    JTextField txtTGGiao = new JTextField("0");

    JPanel pnlSLSP = new JPanel();
    JLabel lblTieuDePnlSLSP = new JLabel("SL sản phẩm");
    JTextField txtSLSP = new JTextField("0");

    JPanel pnlTongTien = new JPanel();
    JLabel lblTieuDePnlTongTien = new JLabel("Tổng tiền");
    JTextField txtTongTien = new JTextField("0");

    JPanel pnlDanhSachSanPhamNhapHang = new JPanel();
    Dimension dimPnlDanhSachSanPhamNhapHang = new Dimension(
            dimPnlTaoHoaDonNhapHangChinh.width
                    - dimPnlMucTongKetVaThoat.width,
            dimPnlTaoHoaDonNhapHangChinh.height
    );

    /**
     * <p>0: STT</p>
     * <p>1: MaSP</p>
     * <p>2: TenSP</p>
     * <p>3: SLSP nhap</p>
     * <p>4: Don gia nhap</p>
     * <p>5: Thanh tien</p>
     */
    String[] tieuDeTableDSSPKHMua = {
            "STT",
            "MaSP",
            "TenSP",
            "SL nhập",
            "Đơn giá nhập",
            "Thành tiền"
    };
    DefaultTableModel dtmDanhSachSanPhamNhapHang = new DefaultTableModel(tieuDeTableDSSPKHMua, 0);
    TableRowSorter trsDanhSachSPNhapHang = new TableRowSorter(dtmDanhSachSanPhamNhapHang);

    JTable tblDanhSachSanPhamNhapHang = new JTable(dtmDanhSachSanPhamNhapHang);

    JScrollPane scrChuaTblDSSanPhamNhapHang = new JScrollPane(tblDanhSachSanPhamNhapHang);

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    DecimalFormat df = new DecimalFormat("#,###.###");

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
}
