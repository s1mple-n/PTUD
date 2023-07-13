package ui.giaodienchinh.pnlqlsanpham.gdcapnhatthongtinsanpham;

import dao.SanPhamDAO;
import entity.SanPham;
import services.CacHamDungSan;
import services.TienIch;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class GDCapNhatThongTinSanPham extends JFrame implements IDSBienGDCapNhatThongTinSanPham {
    private static GDCapNhatThongTinSanPham gdCapNhatThongTinSanPham = null;

    private GDCapNhatThongTinSanPham() {
        setTitle("Xem thông tin sản phẩm");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));

        dungUI();

        setUndecorated(true);
        setSize(kichThuocGDXemTTSanPham);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static GDCapNhatThongTinSanPham getGdCapNhatThongTinSanPham() {
        if (gdCapNhatThongTinSanPham == null)
            gdCapNhatThongTinSanPham = new GDCapNhatThongTinSanPham();
        return gdCapNhatThongTinSanPham;
    }

    private void dungUI() {
        dungPanelChinh();

        getContentPane().add(panelChinh, BorderLayout.CENTER);
    }

    private void dungPanelChinh() {
        panelChinh.setBackground(bgrMacDinh);
        panelChinh.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panelChinh.setPreferredSize(kichThuocGDXemTTSanPham);

        dungPanelHienThiTTSanPham();

        dungPanelChuaBtn();

        panelChinh.add(panelHienThiTTSanPham);
        panelChinh.add(panelChuaBtn);
    }

    private void dungPanelHienThiTTSanPham() {
        panelHienThiTTSanPham.setBackground(bgrMacDinh);
        panelHienThiTTSanPham.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelHienThiTTSanPham.setPreferredSize(kichThuocPanelHienThiTTSanPham);
        panelHienThiTTSanPham.setBorder(BorderFactory.createMatteBorder(
                2, 2, 0, 2, bgrVienMacDinh
        ));

        dungPanelTenSanPham();
        panelHienThiTTSanPham.add(panelTenSanPham);

        dungPanelChuaMaSPVaMauSac();
        panelHienThiTTSanPham.add(panelChuaMaSPVaMauSac);

        dungPanelChuaSizeVaChatLieu();
        panelHienThiTTSanPham.add(panelChuaSizeVaChatLieu);

        dungPanelChuaThuongHieuVaNguonGoc();
        panelHienThiTTSanPham.add(panelChuaThuongHieuVaNguonGoc);

        dungPanelChuaDonGiaVaSLTon();
        panelHienThiTTSanPham.add(panelChuaDonGiaVaSLTon);

        dungPanelMoTa();
        panelHienThiTTSanPham.add(panelMoTa);
    }

    private void dungPanelTenSanPham() {
        panelTenSanPham.setBackground(bgrMacDinh);
        panelTenSanPham.setPreferredSize(kichThuocPanelConLoai1);
        panelTenSanPham.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lbTenSanPham.setForeground(frgMacDinh);
        lbTenSanPham.setFont(fntMacDinh);
        panelTenSanPham.add(lbTenSanPham);

        CacHamDungSan.datThuocTinhChoTxt(
                txtTenSanPham,
                "",
                kichThuocTxtLoai1
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTenSanPham);
        datHanhDongChoTxtTenSanPham();
        txtTenSanPham.setForeground(frgMacDinh);
        panelTenSanPham.add(txtTenSanPham);

        CacHamDungSan.datMauVienChoTxtDuocPhepChinhSua(txtTenSanPham);
        txtTenSanPham.requestFocus();
    }

    private void dungPanelChuaMaSPVaMauSac() {
        panelChuaMaSPVaMauSac.setBackground(bgrMacDinh);
        panelChuaMaSPVaMauSac.setLayout(new BoxLayout(panelChuaMaSPVaMauSac, BoxLayout.X_AXIS));
        panelChuaMaSPVaMauSac.setPreferredSize(kichThuocPanelConLoai1);

        dungCacPanelConLoai2(panelMaSP, lbMaSP, txtMaSP);

        dungCacPanelConLoai2(panelMauSac, lbMauSac, txtMauSac);

        panelChuaMaSPVaMauSac.add(panelMaSP);

        panelChuaMaSPVaMauSac.add(Box.createHorizontalStrut(15));

        panelChuaMaSPVaMauSac.add(panelMauSac);
    }

    private void dungPanelChuaSizeVaChatLieu() {
        panelChuaSizeVaChatLieu.setBackground(bgrMacDinh);
        panelChuaSizeVaChatLieu.setLayout(new BoxLayout(panelChuaSizeVaChatLieu, BoxLayout.X_AXIS));
        panelChuaSizeVaChatLieu.setPreferredSize(kichThuocPanelConLoai1);

        dungCacPanelConLoai2(panelSize, lbSize, txtSize);

        dungCacPanelConLoai2(panelChatLieu, lbChatLieu, txtChatLieu);

        panelChuaSizeVaChatLieu.add(panelSize);

        panelChuaSizeVaChatLieu.add(Box.createHorizontalStrut(15));

        panelChuaSizeVaChatLieu.add(panelChatLieu);
    }

    private void dungPanelChuaThuongHieuVaNguonGoc() {
        panelChuaThuongHieuVaNguonGoc.setBackground(bgrMacDinh);
        panelChuaThuongHieuVaNguonGoc.setLayout(new BoxLayout(panelChuaThuongHieuVaNguonGoc, BoxLayout.X_AXIS));
        panelChuaThuongHieuVaNguonGoc.setPreferredSize(kichThuocPanelConLoai1);

        dungCacPanelConLoai2(panelThuongHieu, lbThuongHieu, txtThuongHieu);

        dungCacPanelConLoai2(panelNguonGoc, lbNguonGoc, txtNguonGoc);

        panelChuaThuongHieuVaNguonGoc.add(panelThuongHieu);

        panelChuaThuongHieuVaNguonGoc.add(Box.createHorizontalStrut(15));

        panelChuaThuongHieuVaNguonGoc.add(panelNguonGoc);
    }

    private void dungPanelChuaDonGiaVaSLTon() {
        panelChuaDonGiaVaSLTon.setBackground(bgrMacDinh);
        panelChuaDonGiaVaSLTon.setLayout(new BoxLayout(panelChuaDonGiaVaSLTon, BoxLayout.X_AXIS));
        panelChuaDonGiaVaSLTon.setPreferredSize(kichThuocPanelConLoai1);

        dungCacPanelConLoai2(panelDonGia, lbDonGia, txtDonGia);
        txtDonGia.setEditable(true);
        datHanhDongChoTxtDonGia();
        CacHamDungSan.datMauVienChoTxtDuocPhepChinhSua(txtDonGia);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtDonGia);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtDonGia);
        panelChuaDonGiaVaSLTon.add(panelDonGia);

        dungCacPanelConLoai2(panelSLTon, lbSLTon, txtSLTon);
        panelChuaDonGiaVaSLTon.add(Box.createHorizontalStrut(15));
        panelChuaDonGiaVaSLTon.add(panelSLTon);
    }

    private void dungPanelMoTa() {
        panelMoTa.setBackground(bgrMacDinh);
        panelMoTa.setPreferredSize(new Dimension(kichThuocPanelMoTa));
        panelMoTa.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lbMoTa.setForeground(frgMacDinh);
        lbMoTa.setFont(fntMacDinh);
        panelMoTa.add(lbMoTa);

        CacHamDungSan.datThuocTinhChoTxa(txaMoTa, kichThuocTxaMoTa, true);
        CacHamDungSan.datMauVienChoTxaDuocPhepChinhSua(txaMoTa);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxa(txaMoTa);
        txaMoTa.setForeground(frgMacDinh);
        panelMoTa.add(txaMoTa);
    }

    private void dungCacPanelConLoai2(JPanel panel, JLabel lb, JTextField txt) {
        panel.setBackground(bgrMacDinh);
        panel.setPreferredSize(kichThuocPanelConLoai2);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lb.setFont(fntMacDinh);
        lb.setForeground(frgMacDinh);
        panel.add(lb);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                kichThuocTxtLoai2
        );
        txt.setForeground(frgMacDinh);
        txt.setEditable(false);
        panel.add(txt);
    }

    private void dungPanelChuaBtn() {
        panelChuaBtn.setPreferredSize(kichThuocPanelChuaBtn);
        panelChuaBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 7));
        panelChuaBtn.setBackground(bgrMacDinh);
        panelChuaBtn.setBorder(BorderFactory.createMatteBorder(
                0, 2, 2, 2, bgrVienMacDinh
        ));

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                null,
                frgMacDinh,
                kichThuocBtn
        );
        datHanhDongChoBtnThoat();
        panelChuaBtn.add(btnThoat);

        CacHamDungSan.datThuocTinhChoBtn(
                btnCapNhat,
                mauNenBtnCapNhat,
                bgrMacDinh,
                kichThuocBtn
        );
        datHanhDongChoBtnCapNhat();
        panelChuaBtn.add(btnCapNhat);
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

    private void datHanhDongChoBtnCapNhat(){
        btnCapNhat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (kiemTraDonGia() && kiemTraTenSanPham()){
                    String maSP = txtMaSP.getText().trim();
                    String tenSanPhamMoi = txtTenSanPham.getText().trim();
                    double donGiaMoi = TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(txtDonGia);

                    String moTaMoi = txaMoTa.getText().trim();

                    boolean kqCapNhat = SanPhamDAO.capNhatThongTinChoSanPham(
                            maSP,
                            tenSanPhamMoi,
                            donGiaMoi,
                            moTaMoi
                    );

                    if (kqCapNhat){
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                                "Đã cập nhật thông tin mới thành công cho sản phẩm có mã " + maSP
                        );
                        dispose();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                                "Đã có lỗi xảy ra."
                        );
                        dispose();
                    }
                }
                else{
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_LOI,
                            "Phát hiện trường thông tin không hợp lệ. " +
                                    "Hãy kiểm tra lại."
                    );
                }
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

    private boolean kiemTraTenSanPham(){
        if (txtTenSanPham.getText().trim().isEmpty()){
            txtTenSanPham.requestFocus();
            return false;
        }

        return true;
    }

    private boolean kiemTraDonGia(){
        if (txtDonGia.getText().trim().isEmpty()){
            txtDonGia.requestFocus();
            return false;
        }

        return true;
    }

    private void datHanhDongChoTxtTenSanPham(){
        txtTenSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3 && e.getClickCount() == 2){
                    txtTenSanPham.setText("");
                }
            }
        });

        txtTenSanPham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraTenSanPham()){
                        txtDonGia.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Tên sản phẩm không được rỗng"
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtDonGia(){
        DecimalFormat df = new DecimalFormat("#,###.###");

        txtDonGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3 && e.getClickCount() == 2){
                    txtDonGia.setText("");
                }
            }
        });

        txtDonGia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraDonGia()){
                        txaMoTa.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Đơn giá nhập không được rỗng."
                        );
                    }
                }
                else{
                    txtDonGia.setText(
                            df.format(
                                    TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(
                                            txtDonGia
                                    )
                            )
                    );
                }
            }
        });
    }
}