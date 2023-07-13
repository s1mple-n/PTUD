package dao;

import connectDB.KetNoiCSDL;
import entity.ChiTietHoaDonNhapHang;
import entity.HoaDonNhapHang;
import entity.NhanVien;
import entity.SanPham;
import services.TienIch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class HoaDonNhapHangDAO {
    public static boolean themHoaDonNhapHang(HoaDonNhapHang hoaDonNhapHang){
        boolean kqThemHDNHVaoCSDL = themHoaDonNhapHangVaoCSDL(hoaDonNhapHang);

        boolean kqThemDSChiTietHDNHVaoCSDL = themDSChiTietHDNHVaoCSDL(hoaDonNhapHang.getDanhSachChiTietHDNhapHang());

        if (kqThemHDNHVaoCSDL && kqThemDSChiTietHDNHVaoCSDL)
            return true;

        return false;
    }

    private static boolean themHoaDonNhapHangVaoCSDL(HoaDonNhapHang hoaDonNhapHang){
        String query =
                "insert into HoaDonNhapHang ([maNhanVienLapHDNH], [maLoHang], [tenNguoiGiaoHang], [thoiGianDatHang], [thoiGianGiaoHang], [tongSLSP], [tongTien]) " +
                        "values (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, hoaDonNhapHang.getNhanVienLapHDNH().getMaNV());
            ps.setInt(2, hoaDonNhapHang.getMaLoHang());
            ps.setString(3, hoaDonNhapHang.getTenNguoiGiaoHang());
            ps.setTimestamp(4, Timestamp.valueOf(hoaDonNhapHang.getThoiGianDatHang()));
            ps.setTimestamp(5, Timestamp.valueOf(hoaDonNhapHang.getThoiGianGiaoHang()));
            ps.setInt(6, hoaDonNhapHang.getTongSLSP());
            ps.setDouble(7, hoaDonNhapHang.getTongTien());

            return ps.executeUpdate() > 0;
        } catch (Exception ignored){
        }

        return false;
    }

    private static boolean themDSChiTietHDNHVaoCSDL(List<ChiTietHoaDonNhapHang> dsChiTietHoaDonNhapHang){
        AtomicBoolean kqInsert = new AtomicBoolean(false);

        try {
            int maHDNHMoiNhat = layMaHDNhapHangMoiNhat();

            String queryInsertVaoBangChiTietHDNH =
                    "insert into ChiTietHoaDonNhapHang ([maSP], [soLuongNhap], [donGiaNhap], [thanhTien], [maHDNH]) " +
                            "values (?, ?, ?, ?, ?)";

            PreparedStatement pState = KetNoiCSDL.layKetNoi().prepareStatement(queryInsertVaoBangChiTietHDNH);

            dsChiTietHoaDonNhapHang.forEach(ct -> {
                try {
                    boolean daTonTaiSanPhamTrongCSDL = SanPhamDAO.kiemTraSuTonTaiCuaSanPhamTheoMa(ct.getSanPham().getMaSP());

                    if (daTonTaiSanPhamTrongCSDL){
                        boolean kqCapNhatSLChoSanPhamDaTonTai = SanPhamDAO.capNhatSLTonVaDonGiaChoSanPhamKhiNhapHangTheoMaKhiNhapHang(
                                ct.getSanPham().getMaSP(),
                                ct.getSoLuongNhap(),
                                ct.getDonGiaNhap()
                        );

                        kqInsert.set(kqCapNhatSLChoSanPhamDaTonTai);
                    }
                    else{
                        SanPhamDAO.themSanPhamMoi(ct.getSanPham());
                    }

                    pState.setString(1, ct.getSanPham().getMaSP());
                    pState.setInt(2, ct.getSoLuongNhap());
                    pState.setDouble(3, ct.getDonGiaNhap());
                    pState.setDouble(4, ct.getThanhTien());
                    pState.setInt(5, maHDNHMoiNhat);

                    kqInsert.set(pState.executeUpdate() > 0);
                } catch (Exception ignored){

                }
            });
        } catch (Exception ignored){

        }

        return kqInsert.get();
    }

    private static int layMaHDNhapHangMoiNhat(){
        AtomicInteger maHDNHMoiNhat = new AtomicInteger(0);

        String query = "select top 1 maHDNH from HoaDonNhapHang " +
                "order by maHDNH desc";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                maHDNHMoiNhat.set(rs.getInt(1));

                break;
            }
        } catch (Exception ignored){
        }

        return maHDNHMoiNhat.get();
    }

    public static ResultSet timDSHoaDonNhapHangTheoMaNVHoacMaLoHang(String tuKhoa){
        ResultSet resultSet = null;

        try {
            String query = "select top 7 maHDNH, maNhanVienLapHDNH, maLoHang, thoiGianGiaoHang, tongSLSP, tongTien from HoaDonNhapHang " +
                    "where maNhanVienLapHDNH like ? or maLoHang like ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, "%" + tuKhoa + "%");
            ps.setString(2, "%" + tuKhoa + "%");

            resultSet = ps.executeQuery();
        } catch (Exception ignored){
        }

        return resultSet;
    }

    public static HoaDonNhapHang timHoaDonNhapHangTheoMa(int maHDNH){
        HoaDonNhapHang hoaDonNhapHang = null;

        try{
            ResultSet rs = TienIch.layDuLieuCoDieuKien("HoaDonNhapHang", Arrays.asList("maHDNH-"+ maHDNH +""));

            while (rs.next()){
                ArrayList<ChiTietHoaDonNhapHang> dsChiTietHDNH = new ArrayList<>();

                ResultSet rsCTHDNH =  TienIch.layDuLieuCoDieuKien("ChiTietHoaDonNhapHang", Arrays.asList("maHDNH-"+ maHDNH +""));

                while (rsCTHDNH.next()){
                    SanPham sp = SanPhamDAO.laySanPhamTheoMa(rsCTHDNH.getString(1));

                    ChiTietHoaDonNhapHang chiTietHoaDonNhapHang = new ChiTietHoaDonNhapHang(
                            sp,
                            rsCTHDNH.getInt(2),
                            rsCTHDNH.getDouble(3),
                            rsCTHDNH.getDouble(4)
                    );

                    dsChiTietHDNH.add(chiTietHoaDonNhapHang);
                }

                hoaDonNhapHang = new HoaDonNhapHang(
                        maHDNH,
                        new NhanVien(rs.getString(2)),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getTimestamp(6).toLocalDateTime(),
                        dsChiTietHDNH
                );
            }
        } catch (Exception ignored){
        }

        return hoaDonNhapHang;
    }

    public static boolean kiemTraTrungMaLoHang(int maLoHang){
        String sql = "select * from HoaDonNhapHang where maLoHang = ?";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setInt(1, maLoHang);

            ResultSet rs = ps.executeQuery();

            if (rs.isBeforeFirst())
                return true;
        } catch (Exception ignored){
        }
        return false;
    }

    public static int tinhSLSPDaNhapHangTrongCaLam(String maNhanVien){
        AtomicInteger slspDaNhap = new AtomicInteger(0);

        String query =
                "select sum(tongSLSP) from HoaDonNhapHang " +
                        "where maNhanVienLapHDNH = ? and convert(date, thoiGianGiaoHang, 102) = convert(date, getdate(), 102)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, maNhanVien);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                slspDaNhap.set(rs.getInt(1));

                break;
            }
        } catch (Exception ignored){
        }

        return slspDaNhap.get();
    }
}
