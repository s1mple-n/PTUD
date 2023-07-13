package ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.gdthemsanpham;

import entity.ChiTietHoaDonNhapHang;
import entity.SanPham;
import services.CacHamDungSan;
import services.TienIch;
import ui.giaodienchinh.pnlqlnhaphang.gdtaohoadonnhaphang.GDTaoHoaDonNhapHang;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GDThemSanPham extends JDialog implements IDSBienGDThemSanPham {
    private static GDThemSanPham gdThemSanPham = null;

    private GDThemSanPham() {
        setTitle("Thêm sản phẩm");
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogo));
        dungUI();
        setUndecorated(true);
        setModal(true);
        setSize(dimGDXemTTSanPham);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static GDThemSanPham getGdXemTTSanPham() {
        if (gdThemSanPham == null)
            gdThemSanPham = new GDThemSanPham();
        return gdThemSanPham;
    }

    private void dungUI() {
        dungPanelChinh(pnlChinh);

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPanelChinh(JPanel panelChinh) {
        panelChinh.setBackground(mauNenMacDinh);
        panelChinh.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panelChinh.setPreferredSize(dimGDXemTTSanPham);

        dungPanelHienThiTTSanPham(pnlHienThiTTSanPham);

        dungPanelChuaBtn(panelChuaBtn);

        panelChinh.add(pnlHienThiTTSanPham);
        panelChinh.add(panelChuaBtn);
    }

    private void dungPanelHienThiTTSanPham(JPanel panelHienThiTTSanPham) {
        panelHienThiTTSanPham.setBackground(mauNenMacDinh);
        panelHienThiTTSanPham.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelHienThiTTSanPham.setPreferredSize(dimPnlHienThiTTSanPham);
        panelHienThiTTSanPham.setBorder(BorderFactory.createMatteBorder(
                2, 2, 0, 2, mauKhungVien
        ));

        dungPanelTenSanPham(pnlTenSanPham);
        datHanhDongChoTxtTenSanPham();
        panelHienThiTTSanPham.add(pnlTenSanPham);

        dungPanelChuaMaSPVaMauSac(pnlChuaMaSPVaMauSac);
        datHanhDongChoTxtMaSanPham();
        datHanhDongChoTxtMauSac();
        panelHienThiTTSanPham.add(pnlChuaMaSPVaMauSac);

        dungPanelChuaSizeVaChatLieu(pnlChuaSizeVaChatLieu);
        datHanhDongChoTxtSize();
        datHanhDongChoTxtChatLieu();
        panelHienThiTTSanPham.add(pnlChuaSizeVaChatLieu);

        dungPanelChuaThuongHieuVaNguonGoc(pnlChuaThuongHieuVaNguonGoc);
        datHanhDongChoTxtThuongHieu();
        datHanhDongChoTxtNguonGoc();
        panelHienThiTTSanPham.add(pnlChuaThuongHieuVaNguonGoc);

        dungPanelMoTa(pnlMota);
        datHanhDongChoTxaMoTa();
        panelHienThiTTSanPham.add(pnlMota);

        dungPnlChuaDonGiaNhapVaSLNhap(pnlChuaDonGiaNhapVaSLNhap);
        datHanhDongChoTxtDonGiaNhap();
        datHanhDongChoTxtSoLuongNhap();
        panelHienThiTTSanPham.add(pnlChuaDonGiaNhapVaSLNhap);
    }

    private void dungPanelTenSanPham(JPanel panelTenSanPham) {
        panelTenSanPham.setBackground(mauNenMacDinh);
        panelTenSanPham.setPreferredSize(dimPnlConLoai1);
        panelTenSanPham.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lblTenSanPham.setForeground(frgMacDinh);
        lblTenSanPham.setFont(fontChinh);
        panelTenSanPham.add(lblTenSanPham);

        CacHamDungSan.datThuocTinhChoTxt(
                txtTenSanPham,
                "",
                dimTxtLoai1
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtTenSanPham);
        panelTenSanPham.add(txtTenSanPham);
    }

    private void dungPanelChuaMaSPVaMauSac(JPanel panelChuaMaSPVaMauSac) {
        panelChuaMaSPVaMauSac.setBackground(mauNenMacDinh);
        panelChuaMaSPVaMauSac.setLayout(new BoxLayout(panelChuaMaSPVaMauSac, BoxLayout.X_AXIS));
        panelChuaMaSPVaMauSac.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlMaSP, lblMaSP, txtMaSP);

        dungCacPanelConLoai2(pnlMauSac, lblMauSac, txtMauSac);

        panelChuaMaSPVaMauSac.add(pnlMaSP);

        panelChuaMaSPVaMauSac.add(Box.createHorizontalStrut(15));

        panelChuaMaSPVaMauSac.add(pnlMauSac);
    }

    private void dungPanelChuaSizeVaChatLieu(JPanel panelChuaSizeVaChatLieu) {
        panelChuaSizeVaChatLieu.setBackground(mauNenMacDinh);
        panelChuaSizeVaChatLieu.setLayout(new BoxLayout(panelChuaSizeVaChatLieu, BoxLayout.X_AXIS));
        panelChuaSizeVaChatLieu.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlSize, lblSize, txtSize);

        dungCacPanelConLoai2(pnlChatLieu, lblChatLieu, txtChatLieu);

        panelChuaSizeVaChatLieu.add(pnlSize);

        panelChuaSizeVaChatLieu.add(Box.createHorizontalStrut(15));

        panelChuaSizeVaChatLieu.add(pnlChatLieu);
    }

    private void dungPanelChuaThuongHieuVaNguonGoc(JPanel panelChuaThuongHieuVaNguonGoc) {
        panelChuaThuongHieuVaNguonGoc.setBackground(mauNenMacDinh);
        panelChuaThuongHieuVaNguonGoc.setLayout(new BoxLayout(panelChuaThuongHieuVaNguonGoc, BoxLayout.X_AXIS));
        panelChuaThuongHieuVaNguonGoc.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlThuongHieu, lblThuongHieu, txtThuongHieu);

        dungCacPanelConLoai2(pnlNguonGoc, lblNguonGoc, txtNguonGoc);

        panelChuaThuongHieuVaNguonGoc.add(pnlThuongHieu);

        panelChuaThuongHieuVaNguonGoc.add(Box.createHorizontalStrut(15));

        panelChuaThuongHieuVaNguonGoc.add(pnlNguonGoc);
    }

    private void dungPnlChuaDonGiaNhapVaSLNhap(JPanel pnlChuaDonGiaNhapVaSLNhap) {
        pnlChuaDonGiaNhapVaSLNhap.setBackground(mauNenMacDinh);
        pnlChuaDonGiaNhapVaSLNhap.setLayout(new BoxLayout(pnlChuaDonGiaNhapVaSLNhap, BoxLayout.X_AXIS));
        pnlChuaDonGiaNhapVaSLNhap.setPreferredSize(dimPnlConLoai1);

        dungCacPanelConLoai2(pnlDonGiaNhap, lblDonGiaNhap, txtDonGiaNhap);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtDonGiaNhap);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtDonGiaNhap);

        dungCacPanelConLoai2(pnlSLNhap, lblSLNhap, txtSLNhap);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSLNhap);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSLNhap);
        datHanhDongChoTxtDonGiaNhap();
        pnlChuaDonGiaNhapVaSLNhap.add(pnlDonGiaNhap);

        pnlChuaDonGiaNhapVaSLNhap.add(Box.createHorizontalStrut(15));

        pnlChuaDonGiaNhapVaSLNhap.add(pnlSLNhap);
    }

    private void datHanhDongChoTxtDonGiaNhap(){
        txtDonGiaNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraDonGiaNhap()){
                        txtSLNhap.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Đơn giá nhập không được rỗng."
                        );
                    }
                }
                else{
                    try{
                        txtDonGiaNhap.setText(
                                df.format(
                                        TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(txtDonGiaNhap)
                                )
                        );
                    } catch (Exception ignored){

                    }
                }
            }
        });
    }

    private void dungPanelMoTa(JPanel panelMoTa) {
        panelMoTa.setBackground(mauNenMacDinh);
        panelMoTa.setPreferredSize(new Dimension(dimPnlMoTa));
        panelMoTa.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lblMoTa.setForeground(frgMacDinh);
        lblMoTa.setFont(fontChinh);
        panelMoTa.add(lblMoTa);

        datThuocTinhMacDinhChoTxa(txaMoTa, dimTxaMoTa);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxa(txaMoTa);
        panelMoTa.add(txaMoTa);
    }

    private void dungCacPanelConLoai2(JPanel panel, JLabel lb, JTextField txt) {
        panel.setBackground(mauNenMacDinh);
        panel.setPreferredSize(dimPnlConLoai2);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));

        lb.setFont(fontChinh);
        lb.setForeground(frgMacDinh);
        panel.add(lb);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                dimTxtLoai2
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txt);
        panel.add(txt);
    }

    private void datThuocTinhMacDinhChoTxa(JTextArea txa, Dimension kichThuocTxa) {
        txa.setBackground(Color.WHITE);
        txa.setPreferredSize(kichThuocTxa);
        txa.setForeground(frgMacDinh);
        txa.setFont(fontChinh);
        txa.setLineWrap(true);
        txa.setWrapStyleWord(true);
        txa.setToolTipText(
                "Ấn tổ hợp phím Ctrl + D để xoá trắng trường mô tả"
        );
    }

    private void dungPanelChuaBtn(JPanel panelChuaBtn) {
        panelChuaBtn.setPreferredSize(kichThuocPanelChuaBtn);
        panelChuaBtn.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT, 0, 7
                )
        );
        panelChuaBtn.setBackground(mauNenMacDinh);
        panelChuaBtn.setBorder(BorderFactory.createMatteBorder(
                0, 2, 2, 2, mauKhungVien
        ));

        panelChuaBtn.add(Box.createHorizontalStrut(10));

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                null,
                frgMacDinh,
                dimBtn
        );
        datHanhDongChoBtnThoat();
        panelChuaBtn.add(btnThoat);

        panelChuaBtn.add(Box.createHorizontalStrut(15));

        CacHamDungSan.datThuocTinhChoBtn(
                btnThemSanPham,
                mauNenBtnThemSanPham,
                mauNenMacDinh,
                dimBtn
        );
        datHanhDongChoBtnThemSanPham();
        panelChuaBtn.add(btnThemSanPham);
    }

    private void datHanhDongChoBtnThoat(){
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int luaChon = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn chắc chắn muốn dừng nhập thông tin cho sản phẩm này chứ?",
                            "Cảnh báo",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (luaChon == JOptionPane.YES_OPTION){
                        datCacTruongThongTinVeTrangThaiBanDau();
                        dispose();
                    }
                });
            }
        });
    }

    private void datHanhDongChoBtnThemSanPham(){
        btnThemSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (kiemTraTatCaCacTruongDuLieu()){
                    String tenSP = txtTenSanPham.getText().trim();
                    String maSP = txtMaSP.getText().trim();
                    String mauSac = txtMauSac.getText().trim();
                    String size = txtSize.getText().trim();
                    String chatLieu = txtChatLieu.getText().trim();
                    String thuongHieu = txtThuongHieu.getText().trim();
                    String nguonGoc = txtNguonGoc.getText().trim();
                    String moTa = txaMoTa.getText().trim();
                    double donGiaNhap = TienIch.chuyenDinhDangTienTeDaFormatSangNguyenGoc(txtDonGiaNhap);
                    int slNhap = Integer.parseInt(txtSLNhap.getText().trim());

                    SanPham sp = new SanPham(
                            maSP,
                            tenSP,
                            mauSac,
                            size,
                            chatLieu,
                            donGiaNhap,
                            thuongHieu,
                            nguonGoc,
                            slNhap,
                            moTa
                    );

                    ChiTietHoaDonNhapHang chiTietHoaDonNhapHang = new ChiTietHoaDonNhapHang(
                            sp,
                            slNhap,
                            donGiaNhap,
                            slNhap * donGiaNhap
                    );

                    GDTaoHoaDonNhapHang.themChiTietHoaDonNhapHangVaoTbl(chiTietHoaDonNhapHang);

                    datCacTruongThongTinVeTrangThaiBanDau();

                    txtTenSanPham.requestFocus();
                }
                else{
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_LOI,
                            "Có một số trường thông tin chưa hợp lệ. Vui lòng kiểm tra lại."
                    );
                }
            }
        });
    }

    private void datCacTruongThongTinVeTrangThaiBanDau() {
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtTenSanPham, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtMaSP, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtNguonGoc, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtThuongHieu, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtMauSac, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtChatLieu, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtSize, "");
        CacHamDungSan.duaTxaVeTrangThaiSanSangHienThiThongTin(txaMoTa, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtSLNhap, "");
        CacHamDungSan.duaTxtVeTrangThaiSanSangHienThiThongTin(txtDonGiaNhap, "");
    }

    private boolean kiemTraTenSanPham(){
        if (txtTenSanPham.getText().trim().isEmpty()){
            txtTenSanPham.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraMaSanPham(){
        if (txtMaSP.getText().trim().isEmpty()){
            txtMaSP.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraMauSac(){
        if (txtMauSac.getText().trim().isEmpty()){
            txtMauSac.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraSize(){
        if (txtSize.getText().trim().isEmpty()){
            txtSize.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraChatLieu(){
        if (txtChatLieu.getText().trim().isEmpty()){
            txtChatLieu.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraThuongHieu(){
        if (txtThuongHieu.getText().trim().isEmpty()){
            txtThuongHieu.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraNguonGoc(){
        if (txtNguonGoc.getText().trim().isEmpty()){
            txtNguonGoc.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraDonGiaNhap(){
        if (txtDonGiaNhap.getText().trim().isEmpty()){
            txtDonGiaNhap.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraSoLuongNhap(){
        if (txtSLNhap.getText().trim().isEmpty()){
            txtSLNhap.requestFocus();

            return false;
        }

        return true;
    }

    private void datHanhDongChoTxtTenSanPham(){
        txtTenSanPham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraTenSanPham()){
                        txtMaSP.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Tên sản phẩm không được rỗng."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtMaSanPham(){
        txtMaSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMaSanPham()){
                        txtMauSac.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Mã sản phẩm không được rỗng."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtMauSac(){
        txtMauSac.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraMauSac()){
                        txtSize.requestFocus();
                    }
                    else{
                       CacHamDungSan.hienThiThongBaoKetQua(
                               GDThongBaoKetQua.THONG_BAO_LOI,
                               "Màu sắc không được rỗng."
                       );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtSize(){
        txtSize.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraSize()){
                        txtChatLieu.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Size không được rỗng."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtChatLieu(){
        txtChatLieu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraChatLieu()){
                        txtThuongHieu.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Chất liệu không được rỗng."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtThuongHieu(){
        txtThuongHieu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraThuongHieu()){
                        txtNguonGoc.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Thương hiệu không được rỗng."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxaMoTa(){
        txaMoTa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    txtDonGiaNhap.requestFocus();
                }
            }
        });
    }

    private void datHanhDongChoTxtNguonGoc(){
        txtNguonGoc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (kiemTraNguonGoc()){
                        txaMoTa.requestFocus();
                    }
                    else{
                       CacHamDungSan.hienThiThongBaoKetQua(
                               GDThongBaoKetQua.THONG_BAO_LOI,
                               "Nguồn gốc không được rỗng."
                       );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtSoLuongNhap(){
        txtSLNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (!kiemTraSoLuongNhap()){
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Số lượng nhập không được rỗng."
                        );
                    }
                }
            }
        });
    }

    private boolean kiemTraTatCaCacTruongDuLieu(){
        if (
                kiemTraTenSanPham() && kiemTraMaSanPham() && kiemTraMauSac() && kiemTraSize() && kiemTraChatLieu()
                && kiemTraThuongHieu() && kiemTraNguonGoc() && kiemTraDonGiaNhap() && kiemTraSoLuongNhap()
        ){
            return true;
        }
        return false;
    }
}
