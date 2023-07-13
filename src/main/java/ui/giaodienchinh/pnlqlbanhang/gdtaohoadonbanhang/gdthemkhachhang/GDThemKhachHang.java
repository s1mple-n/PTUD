package ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.gdthemkhachhang;

import dao.KhachHangDAO;
import entity.KhachHang;
import services.CacHamDungSan;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlbanhang.gdtaohoadonbanhang.GDTaoHoaDonBanHang;
import ui.giaodienchinh.pnlqlkhachhang.PnlQLKhachHang;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

public class GDThemKhachHang extends JDialog implements IDSBienMacDinh {

    public GDThemKhachHang(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));
        setTitle("Thêm khách hàng mới");
        dungUI();
        setUndecorated(true);
        setSize(dimGDThemKhachHang);
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setPreferredSize(dimGDThemKhachHang);
        pnlChinh.setBackground(bgrMacDinh);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlChinh.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, doDayVienMacDinh, false
                )
        );

        dungPnlConCuaPnlChinh(pnlSoDT, lblSoDT, txtSoDT);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoDT);
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txtSoDT);
        datHanhDongChoTxtSDT();
        pnlChinh.add(pnlSoDT);

        dungPnlConCuaPnlChinh(pnlHoTen, lblHoTen, txtHoTen);
        datHanhDongChoTxtHoTen();
        pnlChinh.add(pnlHoTen);

        dungPnlConCuaPnlChinh(pnlDiaChi, lblDiaChi, txtDiaChi);
        datHanhDongChoTxtDiaChi();
        pnlChinh.add(pnlDiaChi);

        dungPnlConCuaPnlChinh(pnlGioiTinh, lblGioiTinh, cbbGioiTinh);
        pnlChinh.add(pnlGioiTinh);

        dungPnlConCuaPnlChinh(pnlChuaCacNut, btnThoat, btnThemKH);
        pnlChinh.add(pnlChuaCacNut);
    }

    private void dungPnlConCuaPnlChinh(JPanel pnl, JLabel lbl, JTextField txt){
        pnl.setPreferredSize(dimPnlConCuaPnlChinh);
        pnl.setBackground(bgrMacDinh);
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                dimTxtCuaCacPnlCn
        );
        CacHamDungSan.datPhimTatXoaTrangCtrlDChoTxt(txt);
        pnl.add(txt);
    }

    private void dungPnlConCuaPnlChinh(JPanel pnl, JLabel lbl, JComboBox cbb){
        pnl.setPreferredSize(dimPnlConCuaPnlChinh);
        pnl.setBackground(bgrMacDinh);
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl);

        cbb.setPreferredSize(dimTxtCuaCacPnlCn);
        pnl.add(cbb);
    }

    private void dungPnlConCuaPnlChinh(JPanel pnl, JButton btnThoat, JButton btnThemKH){
        pnl.setPreferredSize(dimPnlChuaCacNut);
        pnl.setBackground(bgrMacDinh);
        pnl.setLayout(new BorderLayout());

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                null,
                frgMacDinh,
                dimBtn
        );
        datHanhDongChoBtnThoat();
        pnl.add(btnThoat, BorderLayout.WEST);

        CacHamDungSan.datThuocTinhChoBtn(
                btnThemKH,
                IDSBienMacDinh.bgrThanhPhanXacNhanMacDinh,
                IDSBienMacDinh.bgrMacDinh,
                dimBtn
        );
        datHanhDongChoBtnThemKH();
        pnl.add(btnThemKH, BorderLayout.EAST);
    }

    private void datHanhDongChoTxtSDT(){
        txtSoDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    boolean rs = kiemTraSoDienThoat();

                    if (rs) {
                        if (!kiemTraTrungSoDienThoai()){
                            txtHoTen.requestFocus();
                        }
                        else{
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Số điện thoại này đã thuộc sở hữu của một khách hàng khác!"
                            );
                        }
                    }
                    else{
                      CacHamDungSan.hienThiThongBaoKetQua(
                              GDThongBaoKetQua.THONG_BAO_LOI,
                              "Số điện thoại này không hợp lệ. Số điện thoại phải đủ 10 chữ số và " +
                                      "bắt đầu bằng các đầu số của các nhà mạng tại Việt Nam."
                      );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtHoTen(){
        txtHoTen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    boolean rs = kiemTraHoTenHoacDiaChi(txtHoTen);

                    if (rs){
                        txtDiaChi.requestFocus();
                    }
                    else{
                        CacHamDungSan.hienThiThongBaoKetQua(
                                GDThongBaoKetQua.THONG_BAO_LOI,
                                "Hãy cung cấp họ tên của khách hàng."
                        );
                    }
                }
            }
        });
    }

    private void datHanhDongChoTxtDiaChi(){
        txtDiaChi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    boolean rs = kiemTraHoTenHoacDiaChi(txtDiaChi);

                    if (!rs){
                       CacHamDungSan.hienThiThongBaoKetQua(
                               GDThongBaoKetQua.THONG_BAO_LOI,
                               "Hãy cung cấp địa chỉ của khách hàng."
                       );
                    }
                }
            }
        });
    }

    private boolean kiemTraSoDienThoat(){
        String sdt = txtSoDT.getText().trim();

        String cacDauSoHopLe = "^(032|033|034|035|036|037|038|039|086|096|097|098|" +
                "070|079|077|076|078|089|090|093|" +
                "083|084|085|081|082|088|091|094|" +
                "056|058|092|" +
                "059|099)[0-9]{7}$";

        if (!sdt.matches(cacDauSoHopLe)){
            txtSoDT.setText("");
            txtSoDT.requestFocus();

            return false;
        }

        return true;
    }

    private boolean kiemTraHoTenHoacDiaChi(JTextField txt){
        String chuoiNhapVao = txt.getText().trim();

        if (chuoiNhapVao.isEmpty()){
            txt.requestFocus();
            txt.setText("");

            return false;
        }

        return true;
    }

    private void datHanhDongChoBtnThoat(){
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int luaChon = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn chắc chắc muốn dừng nhập thông tin cho khách hàng mới này?",
                            "Cảnh báo",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (luaChon == JOptionPane.YES_OPTION){
                        dispose();
                    }
                });
            }
        });
    }

    private void datHanhDongChoBtnThemKH(){
        btnThemKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (
                        kiemTraSoDienThoat() &&
                        !kiemTraTrungSoDienThoai() &&
                        kiemTraHoTenHoacDiaChi(txtHoTen) &&
                        kiemTraHoTenHoacDiaChi(txtDiaChi)
                ){
                    String sdtKH = txtSoDT.getText().trim();
                    String hoTen = txtHoTen.getText().trim();
                    String diaChi = txtDiaChi.getText().trim();
                    boolean gioiTinh = cbbGioiTinh.getSelectedIndex() == 0;

                    GDTaoHoaDonBanHang.hienThiThongTinKhachHangLenTxt(
                            hoTen + " - " + sdtKH
                    );

                    CountDownLatch countDownLatch = new CountDownLatch(1);

                    Thread luongThemKhachHangVaoCSDL = new Thread(() -> {
                        KhachHangDAO.themKhachHangMoi(
                                new KhachHang(
                                        hoTen,
                                        sdtKH,
                                        diaChi,
                                        gioiTinh
                                )
                        );

                        countDownLatch.countDown();
                    });

                    luongThemKhachHangVaoCSDL.start();

                    try {
                        countDownLatch.await();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                    GDTaoHoaDonBanHang.chuanBiDuLieuKhachHang();

                    new Thread(PnlQLKhachHang::capNhatDSKhachHang).start();

                    dispose();
                }
                else{
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_LOI,
                            "Phát hiện một số trường thông tin không hợp lệ hoặc chưa được nhập đủ."
                                    + " Vui lòng kiểm tra lại."
                    );
                }
            }
        });
    }


    private boolean kiemTraTrungSoDienThoai(){
        for (KhachHang kh : GDTaoHoaDonBanHang.dsKhachHang){
            if (kh.getSoDT().equals(txtSoDT.getText().trim())){
                txtSoDT.setText("");
                txtSoDT.requestFocus();
                return true;
            }
        }

        return false;
    }

    /**
     * Danh sach bien
     */

    private final Dimension dimGDThemKhachHang = new Dimension(
            470,
            400
    );

    private final JPanel pnlChinh = new JPanel();

    private final Dimension dimPnlConCuaPnlChinh = new Dimension(
            dimGDThemKhachHang.width - 40,
            70
    );
    private final Dimension dimTxtCuaCacPnlCn = new Dimension(
            dimPnlConCuaPnlChinh.width,
            40
    );

    private final JPanel pnlSoDT = new JPanel();
    private final JLabel lblSoDT = new JLabel("Số điện thoại:");
    private final JTextField txtSoDT = new JTextField();

    private final JPanel pnlHoTen = new JPanel();
    private final JLabel lblHoTen = new JLabel("Họ tên:");
    private final JTextField txtHoTen = new JTextField();

    private final JPanel pnlDiaChi = new JPanel();
    private final JLabel lblDiaChi = new JLabel("Địa chỉ");
    private final JTextField txtDiaChi = new JTextField();

    private final JPanel pnlGioiTinh = new JPanel();
    private final JLabel lblGioiTinh = new JLabel("Giới tính:");
    private final String[] dsLuaChon = {"Nam", "Nữ"};
    private final JComboBox<String> cbbGioiTinh = new JComboBox<>(dsLuaChon);

    private final Dimension dimPnlChuaCacNut = new Dimension(
            dimPnlConCuaPnlChinh.width,
            dimPnlConCuaPnlChinh.height - 20
    );

    private final Dimension dimBtn = new Dimension(
            dimPnlChuaCacNut.width / 2 - 10,
            dimPnlChuaCacNut.height
    );

    private final JPanel pnlChuaCacNut = new JPanel();
    private final JButton btnThoat = new JButton("Thoát");
    private final JButton btnThemKH = new JButton("Thêm khách hàng");

}
