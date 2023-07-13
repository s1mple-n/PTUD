package ui.cauhinhchung;

import java.awt.*;
import java.io.File;

public interface IDSBienMacDinh {
    Rectangle dimManHinhKhongTaskbar = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getMaximumWindowBounds();

    String tenFontMacDinh = "Helvetica World";
    String fontFile = "src/main/resources/Fonts/HelveticaWorld.ttf";

    Font fntMacDinh = new Font(
            tenFontMacDinh,
            Font.PLAIN,
            17
    );

    Color bgrMacDinh = new Color(255, 255, 255);

    Color bgrPnlChinh = new Color(243,246,249);

    Color frgMacDinh = new Color(0, 0, 0);

    int doDayVienMacDinh = 2;
    Color bgrVienMacDinh = new Color(10, 118, 145);

    Color bgrThanhPhanDanhDauLoiMacDinh = new Color(252, 3, 90);
    Color bgrThanhPhanXacNhanMacDinh = new Color(35, 175, 207);

    Color bgrCacThanhPhanKhiDuocClickMacDinh = new Color(194, 233, 237);

    String pathLogoMacDinh = "src/main/resources/BieuTuong/Logo2.jpg";

    int chieuCaoHangDuLieuTrongTable = 45;
    Color bgrTieuDeTable = new Color(181, 225, 247);
    Color bgrHangTableLe = new Color(235, 235, 235);

    Color bgrTieuDeThanhTienIch = new Color(71, 169, 191);

    Color bgrTableRowKhiDuocChon = new Color(12, 110, 156);

    int chieuRongThanhTienIch = 230;

    Dimension dimPnlTieuDeThanhTienIch = new Dimension(
            chieuRongThanhTienIch,
            40
    );

    Font fntTieuDeThanhTienIch = new Font(
            tenFontMacDinh,
            Font.BOLD,
            18
    );

    int khoangCachSoVoiLeTraiCuaTieuDe = 15;
    int khoangCachSoVoiLeTrenCuaTieuDe = 8;

    Color bgrVienThanhPhanDuocPhepSua = new Color(24, 168, 147);

    Color bgrBtnThem = new Color(15, 184, 169);

    Color bgrBtnXuatDuLieuRaFile = new Color(
            38, 129, 199
    );

    Color mauNenMacDinhGDThongBao = new Color(225, 245, 245);

    Color bgrBtnThoat = new Color(12, 132, 150);

    /**
     * Phục vụ cho việc phân loại kiểu thống kê khiXuất dữ liệu ra Excel hoặc PDF
     */

    int THONG_KE_HOA_DON_BAN_HANG = 1;
    int THONG_KE_KHACH_HANG = 2;
    int THONG_KE_SAN_PHAM = 3;
    int THONG_KE_HOA_DON_NHAP_HANG = 4;
    int THONG_KE_PHIEU_DOI_CHUNG = 5;
    int THONG_KE_NHAT_KI_BAN_HANG_THEO_CA = 6;
    int THONG_KE_NHAN_VIEN = 7;
    int THONG_KE_DOANH_THU_THEO_HOA_DON_BAN_HANG = 8;
    int THONG_KE_DOANH_THU_THEO_TAT_CA_NHAN_VIEN = 9;
    int THONG_KE_DOANH_THU_THEO_TUNG_NHAN_VIEN = 10;
    int THONG_KE_DOANH_THU_THEO_TAT_CA_KHACH_HANG = 11;
    int THONG_KE_DOANH_THU_THEO_TUNG_KHACH_HANG = 12;
    int THONG_KE_THEO_TAT_CA_SAN_PHAM = 13;
    int THONG_KE_THEO_TUNG_SAN_PHAM = 14;
}
