package dao;

import connectDB.KetNoiCSDL;
import entity.*;
import services.TienIch;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class HoaDonBanHangDAO {
    public static boolean themHoaDonBanHang(HoaDonBanHang hoaDonBanHang){
        boolean kqThemHDBH = themHoaDonBanHangVaoCSDL(hoaDonBanHang);

        boolean kqThemDSChiTietHDBH = themDSChiTietHoaDonBanHangVaoCSDL(hoaDonBanHang.getDanhSachChiTietHDBanHang());

        SanPhamDAO sanPhamDAO = new SanPhamDAO();

        boolean kqCapNhatSoLuongTonCuaSanPham = sanPhamDAO.capNhatLaiSoLuongTonSanPhamSauKhiTaoHoaDonBanHang(
                hoaDonBanHang.getDanhSachChiTietHDBanHang()
        );

        if (kqThemHDBH && kqThemDSChiTietHDBH && kqCapNhatSoLuongTonCuaSanPham)
            return true;

        return false;
    }

    private static boolean themHoaDonBanHangVaoCSDL(HoaDonBanHang hoaDonBanHang){
        try {

            //9 truong thong tin tat ca
            String sql =
                    "insert into HoaDonBanHang([maNhanVienLapHDBH], [maKH], [thoiGianLap], [tongSLSP], [thanhTienChuaThue], [thueGTGT], [tongTien], [soTienKhachDua], [soTienThoiLai]) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setString(1, hoaDonBanHang.getNhanVienLapHDBH().getMaNV());
            ps.setInt(2, hoaDonBanHang.getKhachHang().getMaKH());
            ps.setTimestamp(3, Timestamp.valueOf(hoaDonBanHang.getThoiGianLap()));
            ps.setInt(4, hoaDonBanHang.getTongSLSP());
            ps.setDouble(5, hoaDonBanHang.getThanhTienChuaThue());
            ps.setDouble(6, hoaDonBanHang.getThueGTGT());
            ps.setDouble(7, hoaDonBanHang.getTongTien());
            ps.setDouble(8, hoaDonBanHang.getSoTienKhachDua());
            ps.setDouble(9, hoaDonBanHang.getSoTienThoiLai());

            return ps.executeUpdate() > 0;

        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    private static int layMaCuaHoaDonBanHangMoiThemVao(){
        AtomicInteger maHDBHMoiNhat = new AtomicInteger(0);

        try {
            String query = "select top 1 maHDBH from HoaDonBanHang " +
                    "order by maHDBH desc";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                maHDBHMoiNhat.set(
                        rs.getInt(1)
                );

                break;
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }

        return maHDBHMoiNhat.get();
    }

    private static boolean themDSChiTietHoaDonBanHangVaoCSDL(List<ChiTietHoaDonBanHang> dsChiTietHoaDonBanHang){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        int maHDBHVuaThemVao = layMaCuaHoaDonBanHangMoiThemVao();

        Connection con = KetNoiCSDL.layKetNoi();

        //5 truong thong tin tat ca
        String query =
                "insert into ChiTietHoaDonBanHang([maSP], [soLuongBan], [donGiaBan], [thanhTien], [maHDBH]) " +
                "values (?, ?, ?, ?, ?)";

        dsChiTietHoaDonBanHang.forEach(ct -> {
            try {
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, ct.getSanPham().getMaSP());
                ps.setInt(2, ct.getSoLuongBan());
                ps.setDouble(3, ct.getDonGiaBan());
                ps.setDouble(4, ct.getThanhTien());
                ps.setInt(5, maHDBHVuaThemVao);

                atomicBoolean.set(ps.executeUpdate() > 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return atomicBoolean.get();
    }

    public static ResultSet timHoaDonBanHangTheoMaHDHoacMaKH(String tuKhoa){
        ResultSet resultSet = null;

        try {
            String query = "select top 7 maHDBH, maKH, thoiGianLap, tongSLSP, tongTien from HoaDonBanHang " +
                    "where maHDBH like ? or maKH like ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, "%" + tuKhoa + "%");
            ps.setString(2, "%" + tuKhoa + "%");

            resultSet = ps.executeQuery();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return resultSet;
    }

    public static HoaDonBanHang timHoaDonBanHangTheoMa(int maHDBH){
        HoaDonBanHang hoaDonBanHang = null;

        try {

            ResultSet rs = TienIch.layDuLieuCoDieuKien("HoaDonBanHang", Arrays.asList("maHDBH-"+ maHDBH +""));

            while (rs.next()){
                ArrayList<ChiTietHoaDonBanHang> dsChiTietHDBH = new ArrayList<>();

                KhachHang kh = KhachHangDAO.layKhachHangTheoMa(rs.getInt(3));

                ResultSet resultSet = TienIch.layDuLieuCoDieuKien("ChiTietHoaDonBanHang", Arrays.asList("maHDBH-"+ maHDBH +""));

                while (resultSet.next()){
                    SanPham sp = SanPhamDAO.laySanPhamTheoMa(resultSet.getString(1));

                    ChiTietHoaDonBanHang ct = new ChiTietHoaDonBanHang(
                            sp,
                            resultSet.getInt(2),
                            resultSet.getDouble(3),
                            resultSet.getDouble(4)
                    );

                    dsChiTietHDBH.add(ct);
                }

                hoaDonBanHang = new HoaDonBanHang(
                        maHDBH,
                        new NhanVien(rs.getString(2)),
                        kh,
                        rs.getTimestamp(4).toLocalDateTime(),
                        dsChiTietHDBH,
                        rs.getDouble(9)
                );
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return hoaDonBanHang;
    }

    public static int tinhTongSLSPBanDuocTrongCaLam(String maNhanVien){
        AtomicInteger slspBanDuoc = new AtomicInteger(0);

        String query =
                "select sum(tongSLSP) from HoaDonBanHang " +
                        "where maNhanVienLapHDBH = ? and " +
                        "convert(date, thoiGianLap, 102) = convert(date, getdate(), 102)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, maNhanVien);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                slspBanDuoc.set(rs.getInt(1));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return slspBanDuoc.get();
    }

    public static double tinhTongDoanhThuCuaCaLam(String maNhanVien){
        AtomicReference<Double> tongDoanhThuCaLam = new AtomicReference(0.0);

        String query =
                "select sum(tongTien) from HoaDonBanHang " +
                        "where maNhanVienLapHDBH = ? and convert(date, thoiGianLap, 102) = convert(date, getdate(), 102)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, maNhanVien);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                tongDoanhThuCaLam.set(rs.getDouble(1));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return tongDoanhThuCaLam.get();
    }
}
