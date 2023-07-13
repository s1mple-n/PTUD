package ui.giaodienchinh.pnlqlkhachhang.gdcapnhatthongtinkhachhang;

import connectDB.KetNoiCSDL;
import dao.KhachHangDAO;
import entity.KhachHang;
import services.CacHamDungSan;
import services.MaHoaDuLieu;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienchinh.pnlqlkhachhang.PnlQLKhachHang;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GDCapNhatThongTinKhachHang extends JDialog implements IDSBienMacDinh {
    int hangDuocChon = 0;
    String hoTen = "";
    String maKHCanCapNhat = "";
    String sdtCu = "";
    String diaChiCu = "";

    public GDCapNhatThongTinKhachHang(int hangDuocChon, String maKHCanCapNhat, String hoTen, String sdtCu, String diaChiCu){
        this.hangDuocChon = hangDuocChon;
        this.hoTen = hoTen;
        this.maKHCanCapNhat = maKHCanCapNhat;
        this.sdtCu = sdtCu;
        this.diaChiCu = diaChiCu;

        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));
        setTitle("Cập nhật khách hàng mới");
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

        dungPnlConCuaPnlChinh(pnlMaKH, lblMaKH, txtMaKH);
        txtMaKH.setEnabled(false);
        txtMaKH.setText(maKHCanCapNhat);
        pnlChinh.add(pnlMaKH);

        dungPnlConCuaPnlChinh(pnlHoTen, lblHoTen, txtHoTen);
        txtHoTen.setEnabled(false);
        txtHoTen.setText(hoTen);
        pnlChinh.add(pnlHoTen);

        dungPnlConCuaPnlChinh(pnlSoDT, lblSoDT, txtSoDT);
        txtSoDT.setText(sdtCu);
        CacHamDungSan.nganCanViecNhapKiTuKhongPhaiSoVaoTxt(txtSoDT);
        datHanhDongChoTxtSDT();
        pnlChinh.add(pnlSoDT);

        dungPnlConCuaPnlChinh(pnlDiaChi, lblDiaChi, txtDiaChi);
        txtDiaChi.setText(diaChiCu);
        datHanhDongChoTxtDiaChi();
        pnlChinh.add(pnlDiaChi);

        dungPnlConCuaPnlChinh(pnlChuaCacNut, btnThoat, btnCapNhat);
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
        txt.setForeground(frgMacDinh);

        pnl.add(txt);
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
        datHanhDongChoBtnCapNhat();
        pnl.add(btnThemKH, BorderLayout.EAST);
    }

    private void datHanhDongChoTxtSDT(){
        txtSoDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    boolean rs = kiemTraSoDienThoai();

                    if (rs) {
                        if ( kiemTraTrungSoDienThoai() ) {
                            CacHamDungSan.hienThiThongBaoKetQua(
                                    GDThongBaoKetQua.THONG_BAO_LOI,
                                    "Số điện thoại này không được chấp nhận vì trùng với số điện thoại của khách hàng khác " +
                                            "hoặc không có thay đổi gì so với số cũ."
                            );
                        }
                        else{
                            txtDiaChi.requestFocus();
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
                        txtDiaChi.requestFocus();
                    }
                    else{
                        capNhatThongTinKhachHang();
                    }
                }
            }
        });
    }

    private boolean kiemTraSoDienThoai(){
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

    private boolean kiemTraTrungSoDienThoai(){
        String sdt = txtSoDT.getText().trim();

        List<KhachHang> dsKH = new ArrayList<KhachHang>(PnlQLKhachHang.dsKhachHang.values());

        for (KhachHang kh : dsKH){
            if (kh.getMaKH() != Integer.parseInt(txtMaKH.getText().trim().toString())){
                if (kh.getSoDT().equals(sdt)){
                    txtSoDT.setText("");
                    txtSoDT.requestFocus();

                    return true;
                }
            }
        }

        txtDiaChi.requestFocus();
        return false;
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
                    dispose();
                });
            }
        });
    }

    private void datHanhDongChoBtnCapNhat(){
        btnCapNhat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                capNhatThongTinKhachHang();
            }
        });
    }

    private void capNhatThongTinKhachHang(){
        if ( kiemTraSoDienThoai() && !kiemTraTrungSoDienThoai() && kiemTraHoTenHoacDiaChi(txtDiaChi) ){
            int luaChon = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn chắc chắn muốn cập nhật thông tin mới cho khách hàng này?",
                    "Cảnh báo cập nhật thông tin khách hàng",
                    JOptionPane.YES_NO_OPTION
            );

            if (luaChon == JOptionPane.YES_OPTION){
                String maKH = txtMaKH.getText().trim();
                String sdtMoi = txtSoDT.getText().trim();
                String diaChiMoi = txtDiaChi.getText().trim();

                CountDownLatch countDownLatch = new CountDownLatch(1);

                Thread luongCapNhatKhachHangVaoCSDL = new Thread(() -> {
                    KhachHangDAO.capNhatThongTinKhachHang(
                            Integer.parseInt(maKH),
                            sdtMoi,
                            diaChiMoi
                    );

                    countDownLatch.countDown();
                });

                luongCapNhatKhachHangVaoCSDL.start();

                try {
                    countDownLatch.await();
                } catch (Exception ex){
                    ex.printStackTrace();
                }

                PnlQLKhachHang.suaThongTinTrenTableCuaKhachHangVuaDuocCapNhat(hangDuocChon, sdtMoi, diaChiMoi);

                Thread luongHienThiThongBaoThanhCong = new Thread(() -> {
                    CacHamDungSan.hienThiThongBaoKetQua(
                            GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                            "Đã cập nhật thông tin mới thành công cho khách hàng có mã " + maKH
                    );
                });

                luongHienThiThongBaoThanhCong.start();

                dispose();
            }
        }
        else{
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Phát hiện một số trường thông tin không hợp lệ hoặc chưa được nhập đủ."
                            + " Vui lòng kiểm tra lại."
            );
        }
    }


    /**
     * Danh sach bien
     */

    private final Dimension dimGDThemKhachHang = new Dimension(
            470,
            400
    );

    private final JPanel pnlChinh = new JPanel();

    private final  Dimension dimPnlConCuaPnlChinh = new Dimension(
            dimGDThemKhachHang.width - 40,
            70
    );
    private final Dimension dimTxtCuaCacPnlCn = new Dimension(
            dimPnlConCuaPnlChinh.width,
            40
    );

    private final JPanel pnlMaKH = new JPanel();
    private final JLabel lblMaKH = new JLabel("Mã khách hàng:");
    private final JTextField txtMaKH = new JTextField();

    private final JPanel pnlHoTen = new JPanel();
    private final JLabel lblHoTen = new JLabel("Họ tên:");
    private final JTextField txtHoTen = new JTextField();

    private final JPanel pnlSoDT = new JPanel();
    private final JLabel lblSoDT = new JLabel("Số điện thoại:");
    private final JTextField txtSoDT = new JTextField();

    private final JPanel pnlDiaChi = new JPanel();
    private final JLabel lblDiaChi = new JLabel("Địa chỉ");
    private final JTextField txtDiaChi = new JTextField();

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
    private final JButton btnCapNhat = new JButton("Cập nhật");
}
