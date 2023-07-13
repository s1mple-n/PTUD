package ui.giaodienchinh.pnlqlghichu;

import ui.giaodienchinh.pnlqlghichu.kieudulieudacbiet.GhiChu;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.IDSBienGDChinh;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public interface IDSBienPnlQLGhiChu extends IDSBienMacDinh, IDSBienGDChinh {
    JPanel pnlQLGC = new JPanel();
    Dimension dimPnlQLGC = new Dimension(
            dimPnlNoiDung.width - 30,
            dimPnlNoiDung.height
    );

    int khoangCachGiuaCacPnlLon = 20;

    int demTrai = 20;
    int demTren = 10;

    JPanel pnlDSGhiChuChuaHoanThanh = new JPanel();
    Dimension dimPnlDSGhiChuChuaHoanThanh = new Dimension(
            (dimPnlQLGC.width - khoangCachGiuaCacPnlLon * 2) / 3,
            dimPnlQLGC.height
    );

    JPanel pnlThanhTieuDeDSGhiChuChuaHoanThanh = new JPanel();
    Dimension dimPnlThanhTieuDeDSGhiChuChuaHoanThanh = new Dimension(
            dimPnlDSGhiChuChuaHoanThanh.width,
            60
    );

    JLabel lblTieuDeDsGhiChuChuaHoanThanh = new JLabel("Chưa hoàn thành");
    Color frgLblTieuDeDsGhiChuChuaHoanThanh = new Color(11, 133, 163);
    Font fntFrgLblTieuDeDsGhiChuChuaHoanThanh = new Font(
            tenFontMacDinh, Font.BOLD, 22
    );
    JLabel lblSLGhiChuChuaHoanThanh = new JLabel("(0)");
    Font fntLblSLGhiChuChuaHoanThanh = new Font(
            tenFontMacDinh, Font.PLAIN, 20
    );

    JLabel lblThemGhiChu = new JLabel(
            new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage(
                            "src/main/resources/BieuTuong/Write_32px_1.png"
                    )
            )
    );

    JPanel pnlChuaDSGhiChuChuaHoanThanh = new JPanel();
    JScrollPane scrChuaPnlChuaDSGhiChuChuaHoanThanh = new JScrollPane(pnlChuaDSGhiChuChuaHoanThanh);
    Dimension dimScrChuaPnlChuaDSGhiChuChuaHoanThanh = new Dimension(
            dimPnlDSGhiChuChuaHoanThanh.width,
            dimPnlDSGhiChuChuaHoanThanh.height
            - dimPnlThanhTieuDeDSGhiChuChuaHoanThanh.height
    );

    /**
     *
     */

    JPanel pnlDSGhiChuQuaHan = new JPanel();
    JPanel pnlThanhTieuDeDSGhiChuQuaHan = new JPanel();

    JLabel lblTieuDeDSGhiChuQuaHan = new JLabel("Quá hạn thực hiện");
    Color frgLblTieuDeDsGhiChuQuaHan = new Color(163, 11, 62);
    JLabel lblSLGhiChuQuaHan = new JLabel("(0)");

    JPanel pnlChuaDSGhiChuQuaHan = new JPanel();
    JScrollPane scrChuaPnlChuaDSGhiChuQuaHan = new JScrollPane(pnlChuaDSGhiChuQuaHan);

    /**
     *
     */

    JPanel pnlDSGhiChuDaHoanThanh = new JPanel();

    JPanel pnlThanhTieuDeDSGhiChuDaHoanThanh = new JPanel();
    JLabel lblTieuDeDSGhiChuDaHoanThanh = new JLabel("Đã hoàn thành");
    Color frgLblTieuDeDsGhiChuDaHoanThanh = new Color(11, 163, 130);
    JLabel lblSLGhiChuDaHoanThanh = new JLabel("(0)");

    JPanel pnlChuaDSGhiChuDaHoanThanh = new JPanel();
    JScrollPane scrChuaPnlChuaDSGhiChuDaHoanThanh = new JScrollPane(pnlChuaDSGhiChuDaHoanThanh);

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(
            "hh:mm a",
            new Locale("vi", "vn")
    );

    /**
     *
     */

    int demTrenPnlGhiChu = 20;
    int demTraiPnlGhiChu = 30;

    Dimension dimPnlChuaPnlGhiChu = new Dimension(
            dimScrChuaPnlChuaDSGhiChuChuaHoanThanh.width - 12,
            300 + demTren * 2
    );
    Dimension dimPnlGhiChu = new Dimension(
            dimScrChuaPnlChuaDSGhiChuChuaHoanThanh.width - 12,
            300
    );
    Dimension dimPnlNoiDungChinhGhiChu = new Dimension(
            dimPnlGhiChu.width - demTraiPnlGhiChu * 2,
            dimPnlGhiChu.height
                    - demTrenPnlGhiChu * 2 - 4
    );
    Dimension dimPnlChuaChuDe = new Dimension(
            dimPnlNoiDungChinhGhiChu.width,
            33
    );
    Dimension dimPnlHanThucHien = new Dimension(
            dimPnlNoiDungChinhGhiChu.width,
            25
    );
    Dimension dimScrChuaTxa = new Dimension(
            dimPnlNoiDungChinhGhiChu.width,
            100
    );

    /**
     *
     */

    String pathIcnChinhSua = "src/main/resources/BieuTuong/Edit_24px_1.png";

    /**
     *
     */

    List<GhiChu> dsGhiChu = new ArrayList<>();

    List<JPanel> dsPnlChuaGhiChu = new ArrayList<>();
    List<JLabel> dsLblChuDe = new ArrayList<>();
    List<JLabel> dsLblChinhSua = new ArrayList<>();
    List<JLabel> dsLblHanThucHien = new ArrayList<>();
    List<JTextArea> dsTxaNoiDungGhiChu = new ArrayList<>();
    List<JLabel> dsLblXoaGhiChu = new ArrayList<>();
    List<JLabel> dsLblXacNhanDaHoanThanh = new ArrayList<>();
}
