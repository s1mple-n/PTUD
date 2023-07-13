package ui.giaodienchinh.pnlqlthuchi.gdxemnhatkibanhangtheoca;

import entity.NhatKiBanHangTheoCa;
import services.CacHamDungSan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GDXemNhatKiBanHangTheoCa extends JDialog implements IDSBienGDXemNhatKiBanHangTheoCa {
    private static GDXemNhatKiBanHangTheoCa gdXemNhatKiBanHangTheoCa = null;
    private static boolean isDaLapNhatKiBanHangTheoCa = false;

    private GDXemNhatKiBanHangTheoCa() {
        setTitle("Nhật kí bán hàng ca này");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setModal(true);
        setUndecorated(true);
        setSize(kichThuocGDTaoNhatKiBanHangTheoCa);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * <li>Nhật kí bán hàng theo ca chỉ được lập 1 lần duy nhất trong 1 ca</li>
     * <li>Nếu chưa lập thì load dữ liệu và hiển thị lên giao diện</li>
     * <li>Nếu đã lập rồi thì chỉ hiển thị ra, không thêm nhật kí mới vào CSDL lần nữa</li>
     *
     * @return: Giao diện chứa thông tin về nhật kí bán hàng theo ca
     */
    public static GDXemNhatKiBanHangTheoCa getGdTaoNhatKiBanHangTheoCa() {
        if (gdXemNhatKiBanHangTheoCa == null) {
            isDaLapNhatKiBanHangTheoCa = true;
            gdXemNhatKiBanHangTheoCa = new GDXemNhatKiBanHangTheoCa();
        }
        return gdXemNhatKiBanHangTheoCa;
    }

    private void dungUI() {
        dungPanelChinh();

        getContentPane().add(panelChinh, BorderLayout.CENTER);
    }

    private void dungPanelChinh() {
        panelChinh.setBackground(mauNenMacDinh);
        panelChinh.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelChinh.setPreferredSize(kichThuocGDTaoNhatKiBanHangTheoCa);
        panelChinh.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, 2
                )
        );

        dungPanelGioiThieu();
        panelChinh.add(panelGioiThieu);

        dungPanelNoiDungNhatKi();
        panelChinh.add(panelNoiDungNhatKi);

        dungPanelXacNhan();
        panelChinh.add(panelXacNhan);
    }

    private void dungPanelGioiThieu() {
        panelGioiThieu.setBackground(mauNenMacDinh);
        panelGioiThieu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelGioiThieu.setPreferredSize(kichThuocPanelGioiThieu);

        lbGioiThieu.setFont(fontLbGioiThieu);
        lbGioiThieu.setForeground(mauLbGioiThieu);

        panelGioiThieu.add(lbGioiThieu);
    }

    private void dungPanelNoiDungNhatKi() {
        panelNoiDungNhatKi.setBackground(mauNenMacDinh);
        panelNoiDungNhatKi.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelNoiDungNhatKi.setPreferredSize(kichThuocPanelNoiDungNhatKi);

        dungPanelChuaHoTenVaMaNV();
        panelNoiDungNhatKi.add(panelChuaHoTenVaMaNV);

        dungPanelChuaMaNhatKiVaNgayLap();
        panelNoiDungNhatKi.add(panelChuaMaNhatKiVaNgayLap);

        dungPanelChuaTTVeSLSanPham();
        panelNoiDungNhatKi.add(panelChuaTTVeSLSanPham);

        dungPanelDoanhThu();
        panelNoiDungNhatKi.add(panelDoanhThu);
    }

    private void dungPanelChuaHoTenVaMaNV() {
        panelChuaHoTenVaMaNV.setBackground(mauNenMacDinh);
        panelChuaHoTenVaMaNV.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelChuaHoTenVaMaNV.setPreferredSize(kichThuocPanelChuaHotenVaMaNV);

        dungPanelHoTen();

        dungPanelMaNV();

        panelChuaHoTenVaMaNV.add(panelHoTen);
        panelChuaHoTenVaMaNV.add(panelMaNV);
    }

    private void dungPanelHoTen() {
        panelHoTen.setPreferredSize(kichThuocPanelHoTenVaMaNV);
        panelHoTen.setBackground(mauNenMacDinh);
        panelHoTen.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lbHoTen.setFont(fontLbTieuDe);
        panelHoTen.add(lbHoTen);

        CacHamDungSan.datThuocTinhChoTxt(
                txtHoTen,
                "",
                kichThuocTxtLoai1
        );
        txtHoTen.setForeground(frgMacDinh);
        txtHoTen.setEditable(false);
        panelHoTen.add(txtHoTen);
    }

    private void dungPanelMaNV() {
        panelMaNV.setPreferredSize(kichThuocPanelHoTenVaMaNV);
        panelMaNV.setBackground(mauNenMacDinh);
        panelMaNV.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lbMaNV.setFont(fontLbTieuDe);
        panelMaNV.add(lbMaNV);

        CacHamDungSan.datThuocTinhChoTxt(
                txtMaNV,
                "",
                kichThuocTxtLoai1
        );
        txtMaNV.setForeground(frgMacDinh);
        txtMaNV.setEditable(false);
        panelMaNV.add(txtMaNV);
    }

    private void dungPanelChuaMaNhatKiVaNgayLap() {
        panelChuaMaNhatKiVaNgayLap.setBackground(mauNenMacDinh);
        panelChuaMaNhatKiVaNgayLap.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelChuaMaNhatKiVaNgayLap.setPreferredSize(kichThuocPanelChuaHotenVaMaNV);

        dungPanelMaNhatKi();

        dungPanelNgayLap();

        panelChuaMaNhatKiVaNgayLap.add(panelMaNhatKi);
        panelChuaMaNhatKiVaNgayLap.add(panelNgayLap);
    }

    private void dungPanelMaNhatKi() {
        panelMaNhatKi.setPreferredSize(kichThuocPanelHoTenVaMaNV);
        panelMaNhatKi.setBackground(mauNenMacDinh);
        panelMaNhatKi.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lbMaNhatKi.setFont(fontLbTieuDe);
        panelMaNhatKi.add(lbMaNhatKi);

        CacHamDungSan.datThuocTinhChoTxt(
                txtMaNhatKi,
                "",
                kichThuocTxtLoai1
        );
        txtMaNhatKi.setForeground(frgMacDinh);
        txtMaNhatKi.setEditable(false);
        panelMaNhatKi.add(txtMaNhatKi);
    }

    private void dungPanelNgayLap() {
        panelNgayLap.setPreferredSize(kichThuocPanelHoTenVaMaNV);
        panelNgayLap.setBackground(mauNenMacDinh);
        panelNgayLap.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lbNgayLap.setFont(fontLbTieuDe);
        panelNgayLap.add(lbNgayLap);

        CacHamDungSan.datThuocTinhChoTxt(
                txtNgayLap,
                "",
                kichThuocTxtLoai1
        );
        txtNgayLap.setForeground(frgMacDinh);
        txtNgayLap.setEditable(false);
        panelNgayLap.add(txtNgayLap);
    }

    private void dungPanelChuaTTVeSLSanPham() {
        panelChuaTTVeSLSanPham.setBackground(mauNenMacDinh);
        panelChuaTTVeSLSanPham.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelChuaTTVeSLSanPham.setPreferredSize(kichThuocPanelChuaHotenVaMaNV);

        dungPanelConTrongPanelChuaTTVeSLSanPham(panelSLSPDaBan, lbSLSPDaBan, txtSLSPDaBan);
        dungPanelConTrongPanelChuaTTVeSLSanPham(panelSLSPConLai, lbSLSPConLai, txtSLSPConLai);
        dungPanelConTrongPanelChuaTTVeSLSanPham(panelSLSPMoiNhap, lbSLSPMoiNhap, txtSLSPMoiNhap);

        panelChuaTTVeSLSanPham.add(panelSLSPDaBan);
        panelChuaTTVeSLSanPham.add(panelSLSPConLai);
        panelChuaTTVeSLSanPham.add(panelSLSPMoiNhap);
    }

    private void dungPanelConTrongPanelChuaTTVeSLSanPham(JPanel panel, JLabel lbTieuDe, JTextField txt) {
        panel.setPreferredSize(kichThuocPanelTTVeSLSanPham);
        panel.setBackground(mauNenMacDinh);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lbTieuDe.setFont(fontLbTieuDe);
        panel.add(lbTieuDe);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                kichThuocTxtLoai2
        );
        txt.setForeground(frgMacDinh);
        txt.setEditable(false);
        panel.add(txt);
    }

    private void dungPanelDoanhThu() {
        panelDoanhThu.setBackground(mauNenMacDinh);
        panelDoanhThu.setPreferredSize(kichThuocPanelChuaHotenVaMaNV);
        panelDoanhThu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 3));

        lbDoanhThu.setFont(fontLbTieuDe);
        panelDoanhThu.add(lbDoanhThu);

        CacHamDungSan.datThuocTinhChoTxt(
                txtDoanhThu,
                "",
                kichThuocTxtLoai3
        );
        txtDoanhThu.setForeground(frgMacDinh);
        txtDoanhThu.setEditable(false);
        panelDoanhThu.add(txtDoanhThu);
    }

    private void dungPanelXacNhan() {
        panelXacNhan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelXacNhan.setBackground(mauNenMacDinh);
        panelXacNhan.setPreferredSize(kichThuocPanelXacNhan);

        CacHamDungSan.datThuocTinhChoBtn(
                btnXacNhan,
                mauNenBtnXacNhan,
                mauNenMacDinh,
                kichThuocBtn
        );
        datHanhDongChoBtnXacNhan();
        panelXacNhan.add(btnXacNhan);
    }

    private void datHanhDongChoBtnXacNhan(){
        btnXacNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    dispose();
                });
            }
        });
    }

    /**
     * <p>Hàm này chưa cập nhật đầy đủ các thông tin cần thiết</p>
     * <p>Chỉ mới cập nhật các thông tin liên quan đến người lập nhật kí bán hàng theo ca và ngày lập</p>
     */
    public static void hienThiDuLieuCuaNhatKiBanHangTheoCa(NhatKiBanHangTheoCa nhatKiBanHangTheoCa){
        NumberFormat nf = NumberFormat.getCurrencyInstance(
                new Locale("vi", "vn")
        );
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

        txtHoTen.setText(nhatKiBanHangTheoCa.getNhanVienLapNKBHTC().getHoTen());
        txtMaNV.setText(nhatKiBanHangTheoCa.getNhanVienLapNKBHTC().getMaNV());
        txtMaNhatKi.setText(nhatKiBanHangTheoCa.getMaNKBHTC() + "");
        txtNgayLap.setText(dtf.format(nhatKiBanHangTheoCa.getThoiGianLap()));
        txtSLSPDaBan.setText(nhatKiBanHangTheoCa.getTongSLSPBanDuoc() + "");
        txtSLSPConLai.setText(nhatKiBanHangTheoCa.getTongSLSPConLai() + "");
        txtSLSPMoiNhap.setText(nhatKiBanHangTheoCa.getTongSLSPMoiNhap() + "");
        txtDoanhThu.setText(nf.format(nhatKiBanHangTheoCa.getTongDoanhThu()));
    }

}
