package dao;

import connectDB.KetNoiCSDL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

public class CaLamViecDAO {
    public static Time layThoiGianBatDauCaLam(int maCaLamViec){
        Time time = null;

        try {
            String sql = "select gioBatDauCa from CaLamViec where maCaLamViec = ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setInt(1, maCaLamViec);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                time = rs.getTime(1);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return time;
    }

    public static Time layThoiGianKetThucCaLam(int maCaLamViec){
        Time time = null;

        try {
            String sql = "select gioKetThucCa from CaLamViec where maCaLamViec = ?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setInt(1, maCaLamViec);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                time = rs.getTime(1);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return time;
    }
}
