package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NhanVien {
    private String maNV;
    private CaLamViec caLamViec;
    private String hoTen;
    private String soDT;
    private String soCMND;
    private String diaChi;
    private boolean isNam;
    private LocalDateTime ngayVaoLam;
    /**
     * - True: Quan Li
     * - False: Nhan vien ban hang
     */
    private boolean isQuanLi = false;
    /**
     * - True: Da nghi
     * - False: Chua nghi
     */
    private boolean isNghiLam = false;
    private NhanVien nguoiQuanLiThemVao;

    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    /**
     * <p>Constructor này dùng cho nhân viên khi thêm vào có cấp bậc là người quản lí</p>
     *
     * @param caLamViec
     * @param hoTen
     * @param soDT
     * @param soCMND
     * @param diaChi
     * @param isNam
     * @param isQuanLi
     * @param sttNV
     */
    public NhanVien(CaLamViec caLamViec, String hoTen, String soDT, String soCMND, String diaChi, boolean isNam, boolean isQuanLi, int sttNV) {
        this.maNV = sinhMaNhanVien(sttNV);
        this.caLamViec = caLamViec;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.soCMND = soCMND;
        this.diaChi = diaChi;
        this.isNam = isNam;
        this.ngayVaoLam = LocalDateTime.now();
        this.isQuanLi = isQuanLi;
        this.isNghiLam = false;
        nguoiQuanLiThemVao = null;
    }

    /*
     * <p>dùng khi load lên màn hình chính (nhân viên sử dụng)</p>
     * */
    public NhanVien(String maNV, CaLamViec caLamViec, String hoTen, String soDT, String soCMND, String diaChi, boolean isNam, boolean isQuanLi, boolean isNghiLam,
                    LocalDateTime ngayVaoLam) {
        this.maNV = maNV;
        this.caLamViec = caLamViec;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.soCMND = soCMND;
        this.diaChi = diaChi;
        this.isNam = isNam;
        this.isQuanLi = isQuanLi;
        this.isNghiLam = isNghiLam;
        this.ngayVaoLam = ngayVaoLam;
        nguoiQuanLiThemVao = null;
    }

    public NhanVien(String maNV, CaLamViec caLamViec, String hoTen, String soDT, String soCMND, String diaChi, boolean isNam, LocalDateTime ngayVaoLam, boolean isQuanLi, boolean isNghiLam, NhanVien nguoiQuanLiThemVao) {
        this.maNV = maNV;
        this.caLamViec = caLamViec;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.soCMND = soCMND;
        this.diaChi = diaChi;
        this.isNam = isNam;
        this.ngayVaoLam = ngayVaoLam;
        this.isQuanLi = isQuanLi;
        this.isNghiLam = isNghiLam;
        this.nguoiQuanLiThemVao = nguoiQuanLiThemVao;
    }

    /**
     * <p>Dùng khi thêm nhân viên</p>
     * <p>Constructor này dùng cho nhân viên thêm vào có cấp bậc là nhân viên bán hàng</p>
     *
     * @param caLamViec
     * @param hoTen
     * @param soDT
     * @param soCMND
     * @param diaChi
     * @param isNam
     * @param isQuanLi
     * @param nguoiQuanLiThemVao
     */
    public NhanVien(CaLamViec caLamViec, String hoTen, String soDT, String soCMND, String diaChi, boolean isNam, boolean isQuanLi, NhanVien nguoiQuanLiThemVao, int sttNV) {
        this.caLamViec = caLamViec;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.soCMND = soCMND;
        this.diaChi = diaChi;
        this.isNam = isNam;
        this.ngayVaoLam = LocalDateTime.now();
        this.isQuanLi = isQuanLi;
        this.isNghiLam = false;
        this.nguoiQuanLiThemVao = nguoiQuanLiThemVao;
        this.maNV = sinhMaNhanVien(sttNV);
    }

    /**
     * @param soThuTuNV: số thứ tự của nhân viên = tổng số NV + 1
     * @return mã nhân viên
     * <p>mã nhiên viên gồm 8 kí tự:</p>
     * <ul>
     *     <li>2 kí tự đầu là 2 số cuối năm vào làm của NV</li>
     *     <li>2 kí tự tiếp theo là tháng vào làm của NV</li>
     *     <li>3 kí tự tiếp theo là số thứ tự của nhân viên</li>
     *     <li>1 kí tự cuối là cấp bậc của NV <i>[ 0: nhân viên ~ 1: quản lí ]</i></li>
     * </ul>
     * @author: Chí Hiếu
     */
    private String sinhMaNhanVien(int soThuTuNV) {
        String dinhDangMaNV = DateTimeFormatter.ofPattern("yyMM").format(ngayVaoLam);
        String soTT = String.format("%3s", (soThuTuNV)%1000 + "").replace(" ", "0");
        return String.format("%s%s%s", dinhDangMaNV, soTT, isQuanLi ? "1" : "0");
    }

    public String getMaNV() {
        return maNV;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isNam() {
        return isNam;
    }

    public LocalDateTime getNgayVaoLam() {
        return ngayVaoLam;
    }

    public boolean isQuanLi() {
        return isQuanLi;
    }

    public void setQuanLi(boolean quanLi) {
        isQuanLi = quanLi;
    }

    public boolean isNghiLam() {
        return isNghiLam;
    }

    public void setNghiLam(boolean nghiLam) {
        isNghiLam = nghiLam;
    }

    public NhanVien getNguoiQuanLiThemVao() {
        return nguoiQuanLiThemVao;
    }

    public void setNguoiQuanLiThemVao(NhanVien nguoiQuanLiThemVao) {
        this.nguoiQuanLiThemVao = nguoiQuanLiThemVao;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", caLamViec=" + caLamViec +
                ", hoTen='" + hoTen + '\'' +
                ", soDT='" + soDT + '\'' +
                ", soCMND='" + soCMND + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh=" + isNam +
                ", ngayVaoLam=" + ngayVaoLam +
                ", isQuanLi=" + isQuanLi +
                ", isNghiLam=" + isNghiLam +
                ", nguoiQuanLiThemVao=" + nguoiQuanLiThemVao +
                '}';
    }
}
