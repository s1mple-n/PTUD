package entity;

import java.time.LocalDateTime;
import java.util.List;

public class HoaDonBanHang {
    private int maHoaDonBH;
    private NhanVien nhanVienLapHDBH;
    private KhachHang khachHang;
    private LocalDateTime thoiGianLap;
    private List<ChiTietHoaDonBanHang> danhSachChiTietHDBanHang;
    private int tongSLSP;
    private double thanhTienChuaThue;
    private double thueGTGT;
    /**
     * Tong tien = thanhTienChuaThue + thueGTGT
     */
    private double tongTien;
    private double soTienKhachDua;
    private double soTienThoiLai;

    public HoaDonBanHang() {
    }

    public HoaDonBanHang(int maHoaDonBH) {
        this.maHoaDonBH = maHoaDonBH;
    }

    /**
     * <p>Dùng khi load hoá đơn từ csdl lên</p>
     * @param maHoaDonBH
     * @param nhanVienLapHDBH
     * @param khachHang
     * @param thoiGianLap
     * @param danhSachChiTietHDBanHang
     * @param soTienKhachDua
     */
    public HoaDonBanHang(int maHoaDonBH, NhanVien nhanVienLapHDBH, KhachHang khachHang, LocalDateTime thoiGianLap, List<ChiTietHoaDonBanHang> danhSachChiTietHDBanHang, double soTienKhachDua) {
        this.maHoaDonBH = maHoaDonBH;
        this.nhanVienLapHDBH = nhanVienLapHDBH;
        this.khachHang = khachHang;
        this.thoiGianLap = thoiGianLap;
        this.danhSachChiTietHDBanHang = danhSachChiTietHDBanHang;
        this.soTienKhachDua = soTienKhachDua;
        this.tongSLSP = tinhTongSLSP(danhSachChiTietHDBanHang);
        this.thanhTienChuaThue = tinhThanhTienChuaThue(danhSachChiTietHDBanHang);
        this.thueGTGT = tinhThueGTGT(this.thanhTienChuaThue);
        this.tongTien = tinhTongTien(this.thanhTienChuaThue, this.thueGTGT);
        this.soTienThoiLai = tinhSoTienThoiLoai(this.soTienKhachDua, this.tongTien);
    }

    /**
     * <p>Dùng khi tạo hoá đơn trên Java</p>
     * @param nhanVienLapHDBH
     * @param khachHang
     * @param danhSachChiTietHDBanHang
     * @param soTienKhachDua
     */
    public HoaDonBanHang(NhanVien nhanVienLapHDBH, KhachHang khachHang, List<ChiTietHoaDonBanHang> danhSachChiTietHDBanHang, double soTienKhachDua) {
        this.nhanVienLapHDBH = nhanVienLapHDBH;
        this.khachHang = khachHang;
        this.danhSachChiTietHDBanHang = danhSachChiTietHDBanHang;
        this.soTienKhachDua = soTienKhachDua;
        this.thoiGianLap = LocalDateTime.now();
        this.tongSLSP = tinhTongSLSP(danhSachChiTietHDBanHang);
        this.thanhTienChuaThue = tinhThanhTienChuaThue(danhSachChiTietHDBanHang);
        this.thueGTGT = tinhThueGTGT(this.thanhTienChuaThue);
        this.tongTien = tinhTongTien(this.thanhTienChuaThue, this.thueGTGT);
        this.soTienThoiLai = tinhSoTienThoiLoai(this.soTienKhachDua, this.tongTien);
    }

    private int tinhTongSLSP(List<ChiTietHoaDonBanHang> danhSachChiTietHDBanHang){
        int tongSLSP = danhSachChiTietHDBanHang.stream().mapToInt(ChiTietHoaDonBanHang::getSoLuongBan).sum();

        return tongSLSP;
    }

    private double tinhThanhTienChuaThue(List<ChiTietHoaDonBanHang> danhSachChiTietHDBanHang){
        double tongTienChuaThue = danhSachChiTietHDBanHang.stream().mapToDouble(ChiTietHoaDonBanHang::getThanhTien).sum();

        return tongTienChuaThue;
    }

    private double tinhThueGTGT(double thanhTienChuaThue){
        return  thanhTienChuaThue * 0.1;
    }

    private double tinhTongTien(double thanhTienChuaThue, double thueGTGT){
        return thanhTienChuaThue + thueGTGT;
    }

    private double tinhSoTienThoiLoai(double soTienKhachDua, double tongTien){
        return soTienKhachDua - tongTien;
    }

    public int getMaHoaDonBH() {
        return maHoaDonBH;
    }

    public void setMaHoaDonBH(int maHoaDonBH) {
        this.maHoaDonBH = maHoaDonBH;
    }

    public NhanVien getNhanVienLapHDBH() {
        return nhanVienLapHDBH;
    }

    public void setNhanVienLapHDBH(NhanVien nhanVienLapHDBH) {
        this.nhanVienLapHDBH = nhanVienLapHDBH;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDateTime getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(LocalDateTime thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public List<ChiTietHoaDonBanHang> getDanhSachChiTietHDBanHang() {
        return danhSachChiTietHDBanHang;
    }

    public void setDanhSachChiTietHDBanHang(List<ChiTietHoaDonBanHang> danhSachChiTietHDBanHang) {
        this.danhSachChiTietHDBanHang = danhSachChiTietHDBanHang;
    }

    public int getTongSLSP() {
        return tongSLSP;
    }

    public void setTongSLSP(int tongSLSP) {
        this.tongSLSP = tongSLSP;
    }

    public double getThanhTienChuaThue() {
        return thanhTienChuaThue;
    }

    public void setThanhTienChuaThue(double thanhTienChuaThue) {
        this.thanhTienChuaThue = thanhTienChuaThue;
    }

    public double getThueGTGT() {
        return thueGTGT;
    }

    public void setThueGTGT(double thueGTGT) {
        this.thueGTGT = thueGTGT;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getSoTienKhachDua() {
        return soTienKhachDua;
    }

    public void setSoTienKhachDua(double soTienKhachDua) {
        this.soTienKhachDua = soTienKhachDua;
    }

    public double getSoTienThoiLai() {
        return soTienThoiLai;
    }

    public void setSoTienThoiLai(double soTienThoiLai) {
        this.soTienThoiLai = soTienThoiLai;
    }

    @Override
    public String toString() {
        return "HoaDonBanHang{" +
                "maHoaDonBH=" + maHoaDonBH +
                ", nhanVienLapHDBH=" + nhanVienLapHDBH +
                ", khachHang=" + khachHang +
                ", thoiGianLap=" + thoiGianLap +
                ", danhSachChiTietHDBanHang=" + danhSachChiTietHDBanHang +
                ", tongSLSP=" + tongSLSP +
                ", thanhTienChuaThue=" + thanhTienChuaThue +
                ", thueGTGT=" + thueGTGT +
                ", tongTien=" + tongTien +
                ", soTienKhachDua=" + soTienKhachDua +
                ", soTienThoiLai=" + soTienThoiLai +
                '}';
    }
}
