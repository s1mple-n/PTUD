package dao;

import connectDB.KetNoiCSDL;
import entity.TaiKhoan;
import services.MaHoaDuLieu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoanDAO {

    public static boolean kiemTraThongTinDangNhap(String tenDangNhap, String matKhau){
        String matKhauTam = layMatKhauChuaGiaiMaTheoTenDangNhap(tenDangNhap);

        return !matKhauTam.equals("") && MaHoaDuLieu.giaiMa(matKhauTam).equals(matKhau);
    }

    public static boolean themTaiKhoanMoi(TaiKhoan taiKhoan){
        boolean rs = false;

        try {
            String sql = "insert into TaiKhoan values(?, ?, ?, ?)";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setString(1, taiKhoan.getNhanVien().getMaNV());
            ps.setString(2, taiKhoan.getTenDangNhap());
            ps.setString(3, MaHoaDuLieu.maHoa(taiKhoan.getMatKhau()));
            ps.setBoolean(4, taiKhoan.isKichHoat());

            rs = ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return rs;
    }

    public static boolean doiMatKhau(String maNhanVien, String matKhauMoi){
        boolean rs = false;

        try {
            String sql = "update TaiKhoan " +
                        "set matKhau = ? " +
                        "where tenDangNhap = ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);
            ps.setString(1, MaHoaDuLieu.maHoa(matKhauMoi));
            ps.setString(2, maNhanVien);

            rs = ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return rs;
    }

    public static String layMatKhauChuaGiaiMaTheoTenDangNhap(String tenDangNhap){
        String matKhau = "";

        try {
            String sql = "select matKhau from TaiKhoan " +
                            "where tenDangNhap = ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setString(1, tenDangNhap);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                matKhau = resultSet.getString(1);

                break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return matKhau;
    }

    public static boolean capNhatMatKhau(String maNhanVien, String matKhauMoi){
        boolean rs = false;

        try {
            String sql = "UPDATE TaiKhoan " +
                        "SET matKhau = ? " +
                        "WHERE tenDangNhap = ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setString(1, MaHoaDuLieu.maHoa(matKhauMoi));
            ps.setString(2, maNhanVien);

            rs = ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return rs;
    }

    public static int layTrangThaiKichHoatCuaTaiKhoan(String tenDangNhap){
        int trangThaiKichHoat = -1;

        try{
            String sql =
                    "select trangThaiKichHoat from TaiKhoan " +
                    "where maNhanVien = ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);
            ps.setString(1, tenDangNhap);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                trangThaiKichHoat = rs.getInt(1);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return trangThaiKichHoat;
    }

    public static boolean capNhatTrangThaiKichHoatLaDaKichHoatChoTaiKhoan(String maNhanVien){
        try {
            String sql = "UPDATE TaiKhoan " +
                    "set trangThaiKichHoat = 1 " +
                    "where maNhanVien = ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);
            ps.setString(1, maNhanVien);

            return ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }
}
