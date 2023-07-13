package dao;

import connectDB.KetNoiCSDL;
import entity.NhanVien;
import entity.NhatKiBanHangTheoCa;
import services.TienIch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Arrays;

public class NhatKiBanHangTheoCaDAO {
    public static boolean themNhatKiBanHangTheoCa(NhatKiBanHangTheoCa nhatKiBanHangTheoCa){
        String query =
                "insert NhatKiBanHangTheoCa(" +
                        "[maNhanVienLapNKBHTC], " +
                        "[thoiGianLap], " +
                        "[tongSLSPBanDuoc], " +
                        "[tongSLSPConLai], " +
                        "[tongSLSPMoiNhap], " +
                        "[tongDoanhThu]) " +
                        "values (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, nhatKiBanHangTheoCa.getNhanVienLapNKBHTC().getMaNV());
            ps.setTimestamp(2, Timestamp.valueOf(nhatKiBanHangTheoCa.getThoiGianLap()));
            ps.setInt(3, nhatKiBanHangTheoCa.getTongSLSPBanDuoc());
            ps.setInt(4, nhatKiBanHangTheoCa.getTongSLSPConLai());
            ps.setInt(5, nhatKiBanHangTheoCa.getTongSLSPMoiNhap());
            ps.setDouble(6, nhatKiBanHangTheoCa.getTongDoanhThu());

            return ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public static NhatKiBanHangTheoCa layLenNhatKiBanHangTheoCaMoiLap(){
        NhatKiBanHangTheoCa nhatKiBanHangTheoCa = null;

        String query = "select top 1 * from NhatKiBanHangTheoCa " +
                "order by maNKBHTC desc";

        try{
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                NhanVien nv = NhanVienDAO.timNhanVienTheoMa(rs.getString(2));

                nhatKiBanHangTheoCa = new NhatKiBanHangTheoCa(
                        rs.getInt(1),
                        nv,
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDouble(7)
                );
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return nhatKiBanHangTheoCa;
    }

    public static NhatKiBanHangTheoCa timNhatKiBanHangTheoMa(int maNhatKi){
        NhatKiBanHangTheoCa nhatKiBanHangTheoCa = null;

        try {
            ResultSet rs = TienIch.layDuLieuCoDieuKien("NhatKiBanHangTheoCa", Arrays.asList("maNKBHTC-"+ maNhatKi +""));

            while (rs.next()){
                NhanVien nhanVien = NhanVienDAO.timNhanVienTheoMa(rs.getString(2));

                nhatKiBanHangTheoCa = new NhatKiBanHangTheoCa(
                        rs.getInt(1),
                        nhanVien,
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDouble(7)
                );
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return nhatKiBanHangTheoCa;
    }

    public static ResultSet timNhatKiBanHangTheoMaNhatKiHoacMaNhanVienLap(String tuKhoa){
        ResultSet resultSet = null;

        try {
            String sql =
                    "select top 7 maNKBHTC, maNhanVienLapNKBHTC, thoiGianLap from NhatKiBanHangTheoCa " +
                    "where maNKBHTC like ? or maNhanVienLapNKBHTC like ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setString(1, "%"+ tuKhoa +"%");
            ps.setString(2, "%"+ tuKhoa +"%");

            resultSet = ps.executeQuery();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return resultSet;
    }
}
