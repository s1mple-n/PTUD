package ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang;

import entity.ChiTietHoaDonBanHang;
import entity.KhachHang;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public interface IDSBienGDTaoHoaDonBanHang extends IDSBienMacDinh, IDSBienGDChinh {
    Dimension dimGDTaoHoaDonBanHang = new Dimension(
            dimGDChinh.width,
            dimGDChinh.height
    );

    /**
     *
     */

    JPanel pnlThanhCongCu = new JPanel();
    Color mauNenPnlThanhCongCu = new Color(9, 136, 184);
    Dimension dimPnlThanhCongCu = new Dimension(
            dimGDTaoHoaDonBanHang.width,
            70
    );

    JPanel pnlHopTienIch = new JPanel();
    Dimension dimPnlHopTienIch = new Dimension(
            500,
            dimPnlThanhCongCu.height
            - 25
    );

    /**
     *
     */

    JTextField txtTimKiemSanPham = new JTextField();
    Dimension dimTxtTimKiemSanPham = new Dimension(
            400,
            dimPnlHopTienIch.height
    );

    int[] danhSachKeyMap = {
            KeyEvent.VK_F1, //Btn xoa tat ca san pham
            KeyEvent.VK_F2, //Btn tam thoat
            KeyEvent.VK_F3, //Btn Huy
            KeyEvent.VK_F4, //Txt tim khach hang
            KeyEvent.VK_F5, //Btn them khach hang
            KeyEvent.VK_F6, //Txt tien khach dua
            KeyEvent.VK_F8 // Nut thanh toan
    };

    JPopupMenu pmnKetQuaTimKiemSanPham = new JPopupMenu();
    int chieuRongPmnKetQuaTimKiemSanPham = dimTxtTimKiemSanPham.width + 200;

    String[] tieuDeDanhDauChoTblKetQuaTimKiemSanPham = {"", "", ""};
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

    /**
     *
     */
    JButton btnXoaTatCaSanPhamDaThem = new JButton("",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Bin_32px_1.png")
            )
    );
    Dimension dimBtnXoaTatCaSanPhamDaThem = new Dimension(
            40, 40
    );


    //

    JPanel pnlHopCongCu = new JPanel();
    Dimension dimPnlHopCongCu = new Dimension(
            dimPnlThanhCongCu.width
                    - dimPnlHopTienIch.width,
                    dimTxtTimKiemSanPham.height
    );

    Dimension dimBtnThuNhoVaHuyHD = new Dimension(
            150,
            dimTxtTimKiemSanPham.height
    );

    Color mauNenBtnThuNhoManHinh = new Color(16, 188, 194);
    JButton btnThuNhoManHinh = new JButton("Tạm thoát (F2)");

    Color mauNenBtnHuyHoaDonBanHang = new Color(209, 69, 109);
    JButton btnHuyHoaDonBanHang = new JButton("Huỷ (F3)");


    /**
     *
     */
    JPanel pnlHoaDonBanHangChinh = new JPanel();
    Dimension dimPnlTaoHoaDonBanHangChinh = new Dimension(
            dimGDTaoHoaDonBanHang.width,
            dimGDTaoHoaDonBanHang.height
            - dimPnlThanhCongCu.height
    );


    /**
     *
     */

    JPanel pnlMucTongKetHoaDonVaThanhToan = new JPanel();
    Dimension dimPnlMucTongKetHoaDonVaThanhToan = new Dimension(
            300,
            dimPnlTaoHoaDonBanHangChinh.height
    );

    //
    JPanel pnlThanhToan = new JPanel();
    Dimension dimPnlThanhToan = new Dimension(
        dimPnlMucTongKetHoaDonVaThanhToan.width,
        80
    );

    Color mauNenBtnThanhToan = new Color(59, 209, 176);
    Font fntBtnThanhToan = new Font(tenFontMacDinh, Font.PLAIN, 25);
    JButton btnThanhToan = new JButton("Thanh toán (F8)");
    Dimension dimBtnThanhToan = new Dimension(
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
    JPanel pnlKhachHangMua = new JPanel();
    Dimension dimPnlKhachHangMua = new Dimension(
            dimPnlThongTinHoaDonBanHang.width - 20,
            50
    );

    JTextField txtTimKiemKH = new JTextField();
    Dimension dimTxtTimKiemKH = new Dimension(
            dimPnlKhachHangMua.width - 40,
            40
    );

    String plhTxtTimKiemKH = "Tìm khách hàng theo SĐT (F4)";

    JPopupMenu pmnKetQuaTimKiemKH = new JPopupMenu();

    ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

    String[] tieuDeDanhDauChoTblKetQuaTimKiemKH = {"", ""};
    DefaultTableModel dtmKetQuaTimKiemKH = new DefaultTableModel(tieuDeDanhDauChoTblKetQuaTimKiemKH, 0);
    JTable tblKetQuaTimKiemKH = new JTable(dtmKetQuaTimKiemKH);


    JButton btnThemKHMoi = new JButton("",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Add_32px_1.png")
            )
    );
    Dimension dimBtnThemKHMoi = new Dimension(
            40,
            dimPnlKhachHangMua.height - 10
    );

    Dimension dimTxtChuaThongTinCuaHDBH = new Dimension(
              120,
            dimTxtTimKiemKH.height - 20

    );

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
    JLabel lblTieuDePnlTienKhachDua = new JLabel("Tiền khách đưa (F6)");
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
     * <p>1: Nut delete</p>
     * <p>2: MaSP</p>
     * <p>3: TenSP</p>
     * <p>4: Chevron tangSLSP</p>
     * <p>5: SLSP</p>
     * <p>6: Chevron giamSLSP</p>
     * <p>7: Don gia</p>
     * <p>8: Thanh tien</p>
     */
    String[] tieuDeTableDSSPKHMua = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    DefaultTableModel dtmDanhSachSanPhamKHMua = new DefaultTableModel(tieuDeTableDSSPKHMua, 0);

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

    ArrayList<ChiTietHoaDonBanHang> dsChiTietHoaDonBanHang = new ArrayList<>();

    NumberFormat nf = NumberFormat.getCurrencyInstance(
            new Locale("vi", "vn")
    );

    DecimalFormat df = new DecimalFormat("#,###.###");
}
