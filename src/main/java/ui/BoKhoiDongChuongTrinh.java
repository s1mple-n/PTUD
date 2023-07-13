package ui;

import connectDB.KetNoiCSDL;
import services.CacHamDungSan;
import ui.cauhinhchung.CaNhanHoaLookAndFeel;
import ui.giaodiendangnhap.GDDangNhap;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;

public class BoKhoiDongChuongTrinh {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            boolean isKetNoiCSDLThanhCong = KetNoiCSDL.getInstance().thietLapketNoi();

            CaNhanHoaLookAndFeel.caNhanHoaLookAndFeel();

            if (isKetNoiCSDLThanhCong){
                SwingUtilities.invokeLater(() -> {
                    GDDangNhap gd = GDDangNhap.getGdDangNhap();
//                    gd.datTaiKhoanMacDinh();
                    gd.setVisible(true);
                    gd.requestFocusInWindow();
                });
            }
            else{
                CacHamDungSan.hienThiThongBaoKetQua(
                        GDThongBaoKetQua.THONG_BAO_LOI,
                        "Kết nối đến CSDL của bạn có vấn đề." +
                                "Hãy bật SQL Configuration để kiểm tra lại kết nối TCP/IP xem đã được thiết lập hay chưa."
                );
            }
        });
    }
}
