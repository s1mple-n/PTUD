package ui.giaodienchinh;

import services.TienIch;
import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;

public interface IDSBienGDChinh extends IDSBienMacDinh {
    Dimension dimGDChinh = new Dimension(
            dimManHinhKhongTaskbar.width,
            dimManHinhKhongTaskbar.height
    );

    String tieuDe = "Quản lí bán hàng - PHT";

    Color bgrBtnDieuHuongKhiDuocChon = new Color(30, 122, 141);
    Color bgrBtnDieuHuongKhiHover = new Color(242, 246, 255);

    JLayeredPane lypKhungDaLopChinh = new JLayeredPane();

    /**
     * <p>Danh sach bien cua Panel Dieu huong ben trai man hinh</p>
     */

    JPanel pnlDieuHuong = new JPanel();

    Color bgrPnlDieuHuong = new Color(206, 229, 240);

    Dimension dimPnlDieuHuongMacDinh = new Dimension(
            60,
            dimGDChinh.height
    );
    Dimension dimPnlDieuHuongMoRong = new Dimension(
            180,
            dimGDChinh.height
    );

    Font fntBtnDieuHuong = new Font(tenFontMacDinh, Font.PLAIN, 15);

    JPanel pnlLogo = new JPanel();
    Dimension dimPnlLoGoMacDinh = new Dimension(
            dimPnlDieuHuongMacDinh.width,
            55
    );
    Dimension dimPnlLogoMoRong = new Dimension(
            dimPnlDieuHuongMoRong.width,
            dimPnlLoGoMacDinh.height
    );

    JLabel lblLogo = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Logo.jpg")
            )
    );
    Dimension dimLblLogo = new Dimension(50, 50);

    Dimension dimBtnDieuHuongMacDinh = new Dimension(
            dimPnlDieuHuongMacDinh.width,
            50
    );
    Dimension dimBtnDieuHuongMoRong = new Dimension(
            dimPnlDieuHuongMoRong.width,
            dimBtnDieuHuongMacDinh.height
    );

    JPanel pnlNutDangXuat = new JPanel();
    JLabel lblIcnNutDangXuat = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Logout_32px_1.png")
            )
    );
    JLabel lblTieuDeNutDangXuat = new JLabel("Đăng xuất");

    JPanel pnlThanhDieuHuongChinh = new JPanel();
    Dimension dimPnlThanhDieuHuongChinhMacDinh = new Dimension(
            dimPnlDieuHuongMacDinh.width,
            dimGDChinh.height
            - dimBtnDieuHuongMacDinh.height
            - dimPnlLoGoMacDinh.height
    );
    Dimension dimPnlThanhDieuHuongChinhMoRong = new Dimension(
            dimPnlDieuHuongMoRong.width,
            dimGDChinh.height
            - dimBtnDieuHuongMoRong.height
            - dimPnlLogoMoRong.height
    );

    JPanel pnlNutQLBanHang = new JPanel();
    JLabel lblIcnNutQLBanHang = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Sell_32px_1.png")
            )
    );
    JLabel lblTieuDeNutQLBanHang = new JLabel("QL Bán hàng");

    JPanel pnlNutQLKhachHang = new JPanel();
    JLabel lblIcnNutQLKhachHang = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/CustomerMan_32px_1.png")
            )
    );
    JLabel lblTieuDeNutQLKhachHang = new JLabel("QL Khách hàng");

    JPanel pnlNutQLSanPham = new JPanel();
    JLabel lblIcnNutQLSanPham = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/ProductMan_32px_1.png")
            )
    );
    JLabel lblTieuDeNutQLSanPham = new JLabel("QL Sản phẩm");

    JPanel pnlNutQLNhapHang = new JPanel();
    JLabel lblIcnNutQLNhapHang = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/ImportMan_32px_1.png")
            )
    );
    JLabel lblTieuDeNutQLNhapHang = new JLabel("QL Nhập hàng");

    JPanel pnlNutQLThuChi = new JPanel();
    JLabel lblIcnNutQLThuChi = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Receipt_32px_1.png")
            )
    );
    JLabel lblTieuDeNutQLThuChi = new JLabel("QL Thu chi");

    JPanel pnlNutQLGhiChu = new JPanel();
    JLabel lblIcnNutQLGhiChu = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/NoteMan_32px_1.png")
            )
    );
    JLabel lblTieuDeNutQLGhiChu = new JLabel("QL Ghi chú");

    JPanel pnlNutQLNhanVien = new JPanel();
    JLabel lblIcnNutQLNhanVien = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/StaffMan_32px_1.png")
            )
    );
    JLabel lblTieuDeNutQLNhanVien = new JLabel("QL Nhân viên");

    JPanel pnlNutThongKe = new JPanel();
    JLabel lblIcnNutQLThongKe = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Analystic_32px_1.png")
            )
    );
    JLabel lblTieuDeNutThongKe = new JLabel("Lập thống kê");

    /**
     * <p>Danh sach bien Panel noi dung chinh ben phai cua man hinh</p>
     */
    JPanel pnlChinhThuc = new JPanel();

    JPanel pnlKhoangTrong = new JPanel();
    Dimension dimPnlKhoangTrong = new Dimension(
            dimPnlDieuHuongMacDinh.width,
            dimGDChinh.height
    );

    /**
     * <p>Danh sach bien panel chinh nam o ben phai</p>
     */

    JPanel pnlChinh = new JPanel();
    Dimension dimPnlChinh = new Dimension(
            dimGDChinh.width
            - dimPnlKhoangTrong.width,
            dimGDChinh.height
    );

    JPanel pnlTrangThai = new JPanel();
    Dimension dimPnlTrangThai = new Dimension(
            dimPnlChinh.width,
            60
    );

    JPanel pnlHopCongCu = new JPanel();
    Dimension dimPnlHopCongCu = new Dimension(
            200,
            dimPnlTrangThai.height
    );

    JPanel pnlGhiChu = new JPanel();
    Dimension dimPnlGhiChu = new Dimension(
            60,
            dimPnlHopCongCu.height
    );

    JPanel pnlThongBaoSLGhiChuDenHan = new JPanel();
    Dimension dimPnlThongBaoSLGhiChuDenHan = new Dimension(
            dimPnlGhiChu.width - 15,
            16
    );
    JLabel lblHienThiSLGhiChuDenHan = new JLabel("0");

    JLabel lblIcnTaoGhiChu = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Clock_32px_1.png")
            )
    );

    JLabel lblSachHDSD = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Book_32px_1.png")
            )
    );

    JPanel pnlAvatarNhanVien = new JPanel();
    Dimension dimPnlAvatarNhanVien = new Dimension(
            65,
            dimPnlTrangThai.height
    );

    String pathIcnNhanVienNam = "src/main/resources/BieuTuong/MaleUser_48px_1.png";
    String pathIcnNhanVienNu = "src/main/resources/BieuTuong/FemaleUser_48px_1.png";

    JLabel lblAvatarNhanVien = new JLabel(
            new ImageIcon()
    );

    JPopupMenu pmnThongTinNhanVien = new JPopupMenu();
    Dimension dimPmnThongTinNhanVien = new Dimension(
            250,
            80
    );
    Color bgrPmn = new Color(250, 250, 250);

    JMenuItem mniHoTenNV = new JMenuItem(
            "",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Info_24px_1.png")
            )
    );

    JMenuItem mniChucVuNV = new JMenuItem(
            "",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Hierachy_24px_1.png")
            )
    );

    JPopupMenu pmnTinhNangMoRong = new JPopupMenu();
    Dimension dimPmnTinhNangMoRong = new Dimension(
            240,
            80
    );

    JMenuItem mniDoiMatKhau = new JMenuItem(
            "Đổi mật khẩu",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Lock_24px_1.png")
            )
    );

    JMenuItem mniXemTTNVDangSuDung = new JMenuItem(
            "Xem TT NV đang sử dụng",
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Profile_24px_1.png")
            )
    );

    JPanel pnlHienThiThaoTacDieuHuong = new JPanel();
    Dimension dimPnlHienThiThaoTacDieuHuong = new Dimension(
            dimPnlTrangThai.width
            - dimPnlAvatarNhanVien.width
            - dimPnlHopCongCu.width,
            dimPnlTrangThai.height
    );

    JLabel lblHienThiThaoTacDieuHuong = new JLabel("QL BÁN HÀNG");
    Font fntLblHienThiThaoTacDieuHuong = new Font(tenFontMacDinh, Font.PLAIN, 22);

    JPanel pnlNoiDung = new JPanel();
    Dimension dimPnlNoiDung = new Dimension(
            dimPnlChinh.width,
            dimPnlChinh.height
            - dimPnlTrangThai.height
    );
}
