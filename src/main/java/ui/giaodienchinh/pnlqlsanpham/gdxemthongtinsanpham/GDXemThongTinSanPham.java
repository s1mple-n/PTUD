package ui.giaodienchinh.pnlqlsanpham.gdxemthongtinsanpham;

import entity.SanPham;
import services.CacHamDungSan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;

public class GDXemThongTinSanPham extends JDialog implements IDSBienGDXemThongTinSanPham {
    private static GDXemThongTinSanPham gdXemThongTinSanPham = null;

    private GDXemThongTinSanPham() {
        setTitle("Xem thông tin sản phẩm");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));
        dungUI();
        setUndecorated(true);
        setModal(true);
        setSize(dimGDXemThongTinSanPham);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static GDXemThongTinSanPham getGdXemTTSanPham() {
        if (gdXemThongTinSanPham == null)
            gdXemThongTinSanPham = new GDXemThongTinSanPham();
        return gdXemThongTinSanPham;
    }

    private void dungUI() {
        dungPanelChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPanelChinh() {
        pnlChinh.setBackground(bgrMacDinh);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnlChinh.setPreferredSize(dimGDXemThongTinSanPham);

        dungPanelHienThiTTSanPham();

        dungPanelChuaBtn();

        pnlChinh.add(pnlHienThiTTSanPham);
        pnlChinh.add(pnlChuaBtn);
    }

    private void dungPanelHienThiTTSanPham() {
        pnlHienThiTTSanPham.setBackground(bgrMacDinh);
        pnlHienThiTTSanPham.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlHienThiTTSanPham.setPreferredSize(dimPnlHienThiTTSanPham);
        pnlHienThiTTSanPham.setBorder(BorderFactory.createMatteBorder(
                2, 2, 0, 2, bgrVienMacDinh
        ));

        dungPanelTenSanPham();
        pnlHienThiTTSanPham.add(pnlTenSanPham);

        dungPanelChuaMaSPVaMauSac();
        pnlHienThiTTSanPham.add(pnlChuaMaSPVaMauSac);

        dungPanelChuaSizeVaChatLieu();
        pnlHienThiTTSanPham.add(pnlChuaSizeVaChatLieu);

        dungPanelChuaThuongHieuVaNguonGoc();
        pnlHienThiTTSanPham.add(pnlChuaThuongHieuVaNguonGoc);

        dungPanelChuaDonGiaVaSLTon();
        pnlHienThiTTSanPham.add(pnlChuaDonGiaVaSLTon);

        dungPanelMoTa();
        pnlHienThiTTSanPham.add(pnlMota);
    }

    private void dungPanelTenSanPham() {
        pnlTenSanPham.setBackground(bgrMacDinh);
        pnlTenSanPham.setPreferredSize(dimPnlConLoai1);
        pnlTenSanPham.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lblTenSanPham.setForeground(frgMacDinh);
        lblTenSanPham.setFont(fntMacDinh);
        pnlTenSanPham.add(lblTenSanPham);

        CacHamDungSan.datThuocTinhChoTxt(
                txtTenSanPham,
                "",
                dimTxtLoai1
        );
        txtTenSanPham.setEditable(false);
        txtTenSanPham.setForeground(frgMacDinh);
        pnlTenSanPham.add(txtTenSanPham);
    }

    private void dungPanelChuaMaSPVaMauSac() {
        pnlChuaMaSPVaMauSac.setBackground(bgrMacDinh);
        pnlChuaMaSPVaMauSac.setLayout(new BoxLayout(pnlChuaMaSPVaMauSac, BoxLayout.X_AXIS));
        pnlChuaMaSPVaMauSac.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlMaSP, lblMaSP, txtMaSP);

        dungCacPanelConLoai2(pnlMauSac, lblMauSac, txtMauSac);

        pnlChuaMaSPVaMauSac.add(pnlMaSP);

        pnlChuaMaSPVaMauSac.add(Box.createHorizontalStrut(15));

        pnlChuaMaSPVaMauSac.add(pnlMauSac);
    }

    private void dungPanelChuaSizeVaChatLieu() {
        pnlChuaSizeVaChatLieu.setBackground(bgrMacDinh);
        pnlChuaSizeVaChatLieu.setLayout(new BoxLayout(pnlChuaSizeVaChatLieu, BoxLayout.X_AXIS));
        pnlChuaSizeVaChatLieu.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlSize, lblSize, txtSize);

        dungCacPanelConLoai2(pnlChatLieu, lblChatLieu, txtChatLieu);

        pnlChuaSizeVaChatLieu.add(pnlSize);

        pnlChuaSizeVaChatLieu.add(Box.createHorizontalStrut(15));

        pnlChuaSizeVaChatLieu.add(pnlChatLieu);
    }

    private void dungPanelChuaThuongHieuVaNguonGoc() {
        pnlChuaThuongHieuVaNguonGoc.setBackground(bgrMacDinh);
        pnlChuaThuongHieuVaNguonGoc.setLayout(new BoxLayout(pnlChuaThuongHieuVaNguonGoc, BoxLayout.X_AXIS));
        pnlChuaThuongHieuVaNguonGoc.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlThuongHieu, lblThuongHieu, txtThuongHieu);

        dungCacPanelConLoai2(pnlNguonGoc, lblNguonGoc, txtNguonGoc);

        pnlChuaThuongHieuVaNguonGoc.add(pnlThuongHieu);

        pnlChuaThuongHieuVaNguonGoc.add(Box.createHorizontalStrut(15));

        pnlChuaThuongHieuVaNguonGoc.add(pnlNguonGoc);
    }

    private void dungPanelChuaDonGiaVaSLTon() {
        pnlChuaDonGiaVaSLTon.setBackground(bgrMacDinh);
        pnlChuaDonGiaVaSLTon.setLayout(new BoxLayout(pnlChuaDonGiaVaSLTon, BoxLayout.X_AXIS));
        pnlChuaDonGiaVaSLTon.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlDonGia, lblDonGia, txtDonGia);

        dungCacPanelConLoai2(pnlSLTon, lblSLTon, txtSLTon);

        pnlChuaDonGiaVaSLTon.add(pnlDonGia);

        pnlChuaDonGiaVaSLTon.add(Box.createHorizontalStrut(15));

        pnlChuaDonGiaVaSLTon.add(pnlSLTon);
    }

    private void dungPanelMoTa() {
        pnlMota.setBackground(bgrMacDinh);
        pnlMota.setPreferredSize(new Dimension(dimPnlMoTa));
        pnlMota.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lblMoTa.setForeground(frgMacDinh);
        lblMoTa.setFont(fntMacDinh);
        pnlMota.add(lblMoTa);

        datThuocTinhMacDinhChoTxa(txaMoTa, dimTxaMoTa);
        pnlMota.add(txaMoTa);
    }

    private void dungCacPanelConLoai2(JPanel panel, JLabel lb, JTextField txt) {
        panel.setBackground(bgrMacDinh);
        panel.setPreferredSize(dimPnlConLoai2);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lb.setFont(fntMacDinh);
        lb.setForeground(frgMacDinh);
        panel.add(lb);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                dimTxtLoai2
        );
        txt.setEditable(false);
        txt.setForeground(frgMacDinh);
        panel.add(txt);
    }

    private void datThuocTinhMacDinhChoTxa(JTextArea txa, Dimension kichThuocTxa) {
        txa.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1),
                        BorderFactory.createEmptyBorder(0, 5, 0, 0)
                )
        );
        txa.setBackground(bgrMacDinh);
        txa.setPreferredSize(kichThuocTxa);
        txa.setForeground(frgMacDinh);
        txa.setFont(fntMacDinh);
        txa.setEditable(false);
        txa.setLineWrap(true);
        txa.setWrapStyleWord(true);
    }

    private void dungPanelChuaBtn() {
        pnlChuaBtn.setPreferredSize(dimPnlChuaBtn);
        pnlChuaBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlChuaBtn.setBackground(bgrMacDinh);
        pnlChuaBtn.setBorder(BorderFactory.createMatteBorder(
                0, 2, 2, 2, bgrVienMacDinh
        ));

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                bgrBtnThoat,
                bgrMacDinh,
                dimBtnThoat
        );
        datHanhDongChoBtnThoat();
        pnlChuaBtn.add(btnThoat);
    }

    private void datHanhDongChoBtnThoat(){
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    dispose();
                });
            }
        });
    }

    /**
     * <p>Hàm này có nhiệm vụ hiển thị thông tin sản phẩm lên các TextField tương ứng</p>
     * <b>Hàm này được dùng khi người dùng double click vào dữ liệu sản phẩm trong table chứa dữ liệu sản phẩm</b>
     *
     * @param sp: Sản phẩm cần hiển thị lên màn hình
     */
    public static void hienThiThongTinSanPham(SanPham sp) {
        txtTenSanPham.setText(sp.getTenSP());
        txtMaSP.setText(sp.getMaSP());
        txtMauSac.setText(sp.getMauSac());
        txtChatLieu.setText(sp.getChatLieu());
        txtThuongHieu.setText(sp.getThuongHieu());
        txtNguonGoc.setText(sp.getNguonGoc());
        txtDonGia.setText(  NumberFormat.getCurrencyInstance(
                new Locale("vi", "vn")
        ).format(sp.getDonGia()));
        txtSLTon.setText(sp.getSoLuongTon() + "");
        txtSize.setText(sp.getSize());
        txaMoTa.setText(sp.getMoTa());
    }
}
