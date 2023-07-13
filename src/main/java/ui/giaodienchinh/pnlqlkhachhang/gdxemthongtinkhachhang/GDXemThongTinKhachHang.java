package ui.giaodienchinh.pnlqlkhachhang.gdxemthongtinkhachhang;

import dao.KhachHangDAO;
import entity.KhachHang;
import services.CacHamDungSan;
import ui.cauhinhchung.IDSBienMacDinh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;

public class GDXemThongTinKhachHang extends JDialog implements IDSBienMacDinh {
    private static GDXemThongTinKhachHang gdXemThongTinKhachHang = null;

    private GDXemThongTinKhachHang(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(pathLogoMacDinh));
        setTitle("Xem thông tin khách hàng");

        dungUI();

        setUndecorated(true);
        setSize(dimGDThemKhachHang);
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static GDXemThongTinKhachHang getGdXemThongTinKhachHang() {
        if (gdXemThongTinKhachHang == null)
            gdXemThongTinKhachHang = new GDXemThongTinKhachHang();
        return gdXemThongTinKhachHang;
    }

    public void cungCapMaKhachHang(int maKH){
        KhachHang khachHang = KhachHangDAO.layKhachHangTheoMa(maKH);

        hienThiThongTinKhachHang(
                khachHang
        );
    }

    private void dungUI(){
        dungPnlChinh();

        getContentPane().add(pnlChinh, BorderLayout.CENTER);
    }

    private void dungPnlChinh(){
        pnlChinh.setPreferredSize(dimGDThemKhachHang);
        pnlChinh.setBackground(bgrPnlChinh);
        pnlChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 6));
        pnlChinh.setBorder(
                BorderFactory.createLineBorder(
                        bgrVienMacDinh, doDayVienMacDinh, false
                )
        );

        dungPnlConCuaPnlChinh(pnlMaKH, lblMaKH, txtMaKH);
        pnlChinh.add(pnlMaKH);

        dungPnlConCuaPnlChinh(pnlHoTen, lblHoTen, txtHoTen);
        pnlChinh.add(pnlHoTen);

        dungPnlConCuaPnlChinh(pnlSoDT, lblSoDT, txtSoDT);
        pnlChinh.add(pnlSoDT);

        dungPnlConCuaPnlChinh(pnlDiaChi, lblDiaChi, txtDiaChi);
        pnlChinh.add(pnlDiaChi);

        dungPnlConCuaPnlChinh(pnlGioiTinh, lblGioiTinh, txtGioiTinh);
        pnlChinh.add(pnlGioiTinh);

        dungPnlConCuaPnlChinh(pnlNgayMuaLanDau, lblNgayMuaLanDau, txtNgayMuaLanDau);
        pnlChinh.add(pnlNgayMuaLanDau);

        dungPnlConCuaPnlChinh(pnlChuaCacNut, btnThoat);
        pnlChinh.add(pnlChuaCacNut);
    }

    private void dungPnlConCuaPnlChinh(JPanel pnl, JLabel lbl, JTextField txt){
        pnl.setPreferredSize(dimPnlConCuaPnlChinh);
        pnl.setBackground(bgrPnlChinh);
        pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl);

        CacHamDungSan.datThuocTinhChoTxt(
                txt,
                "",
                dimTxtCuaCacPnlCon
        );
        txt.setForeground(frgMacDinh);
        txt.setEditable(false);
        pnl.add(txt);
    }

    private void dungPnlConCuaPnlChinh(JPanel pnl, JButton btnThoat){
        pnl.setPreferredSize(dimPnlChuaCacNut);
        pnl.setBackground(bgrPnlChinh);
        pnl.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 6));

        CacHamDungSan.datThuocTinhChoBtn(
                btnThoat,
                bgrBtnThoat,
                IDSBienMacDinh.bgrMacDinh,
                dimBtn
        );
        datHanhDongChoBtnThoat();
        pnl.add(btnThoat);
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

    private void hienThiThongTinKhachHang(KhachHang khachHang){
        txtMaKH.setText(khachHang.getMaKH() + "");
        txtHoTen.setText(khachHang.getHoTen());
        txtSoDT.setText(khachHang.getSoDT());
        txtDiaChi.setText(khachHang.getDiaChi());txtGioiTinh.setText(
                (khachHang.isNam() ? "Nam" : "Nữ")
        );
        txtDiaChi.setToolTipText(khachHang.getDiaChi());
        txtNgayMuaLanDau.setText(
                dtf.format(khachHang.getNgayThem())
        );
    }

    /**
     * Danh sach bien
     */

    private final Dimension dimGDThemKhachHang = new Dimension(
            470,
            540
    );

    private final JPanel pnlChinh = new JPanel();

    private final Dimension dimPnlConCuaPnlChinh = new Dimension(
            dimGDThemKhachHang.width - 40,
            70
    );
    private final Dimension dimTxtCuaCacPnlCon = new Dimension(
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
    private final JLabel lblDiaChi = new JLabel("Địa chỉ:");
    private final JTextField txtDiaChi = new JTextField();

    private final JPanel pnlGioiTinh = new JPanel();
    private final JLabel lblGioiTinh = new JLabel("Giới tính:");
    private final JTextField txtGioiTinh = new JTextField();

    private final JPanel pnlNgayMuaLanDau = new JPanel();
    private final JLabel lblNgayMuaLanDau = new JLabel("Ngày mua hàng lần đầu:");
    private final JTextField txtNgayMuaLanDau = new JTextField();

    private final Dimension dimPnlChuaCacNut = new Dimension(
            dimPnlConCuaPnlChinh.width,
            dimPnlConCuaPnlChinh.height
    );

    private final Dimension dimBtn = new Dimension(
            dimPnlChuaCacNut.width / 2 - 10,
            dimPnlChuaCacNut.height - 20
    );

    private final JPanel pnlChuaCacNut = new JPanel();
    private final JButton btnThoat = new JButton("Thoát");

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
}
