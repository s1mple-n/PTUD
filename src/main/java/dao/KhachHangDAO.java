package dao;

import connectDB.KetNoiCSDL;
import entity.KhachHang;
import services.MaHoaDuLieu;
import services.TienIch;

import java.sql.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class KhachHangDAO {

    public static Map<Integer, KhachHang> layToanBoDuLieuKhachHang(){
        Map<Integer, KhachHang> dsKhachHang = new TreeMap<>();

        try{
            ResultSet rs = TienIch.layDuLieuCoDieuKien("KhachHang", null);

            while (rs.next()){
                KhachHang kh = new KhachHang(
                        rs.getInt("maKH"),
                        MaHoaDuLieu.giaiMa(rs.getString("hoTen")),
                        MaHoaDuLieu.giaiMa(rs.getString("soDT")),
                        MaHoaDuLieu.giaiMa(rs.getString("diaChi")),
                        rs.getBoolean("gioiTinh"),
                        rs.getTimestamp("ngayThem").toLocalDateTime()
                );

                dsKhachHang.put(
                        kh.getMaKH(),
                        kh
                );
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return dsKhachHang;
    }

    public static boolean themKhachHangMoi(KhachHang khachHang){
        try {
            String sql = "insert into KhachHang values(?, ?, ?, ?, ?)";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setString(1, MaHoaDuLieu.maHoa(khachHang.getHoTen()));
            ps.setString(2, MaHoaDuLieu.maHoa(khachHang.getSoDT()));
            ps.setString(3, MaHoaDuLieu.maHoa(khachHang.getDiaChi()));
            ps.setBoolean(4, khachHang.isNam());
            ps.setTimestamp(5, Timestamp.valueOf(khachHang.getNgayThem()));

            return ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public static boolean capNhatThongTinKhachHang(int maKH, String sdtMoi, String diaChiMoi){

        try {
            String sql = "update KhachHang set soDT=?, diaChi=? where maKH=?";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(sql);

            ps.setString(1, MaHoaDuLieu.maHoa(sdtMoi));
            ps.setString(2, MaHoaDuLieu.maHoa(diaChiMoi));
            ps.setInt(3, maKH);

            return ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public static KhachHang layKhachHangTheoMa(int maKH){
        KhachHang kh = null;

        try {
            ResultSet res = TienIch.layDuLieuCoDieuKien(
                    "KhachHang",
                    Arrays.asList("maKH-" + maKH)
            );

            while (res.next()){
                kh = new KhachHang(
                        res.getInt("maKH"),
                        MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                        MaHoaDuLieu.giaiMa(res.getString("soDT")),
                        MaHoaDuLieu.giaiMa(res.getString("diaChi")),
                        res.getBoolean("gioiTinh"),
                        res.getTimestamp("ngayThem").toLocalDateTime()
                );
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return kh;
    }

    public static int timMaKhachHangCaoNhat() throws SQLException {
        int maKH = 1;

        ResultSet res = KetNoiCSDL.layKetNoi().prepareStatement("select max(makh) as maKH from KhachHang").executeQuery();

        while(res.next())
            maKH = res.getInt("maKH");

        return maKH;
    }

    public static String dinhDangMaKH(int maKH) throws SQLException {
        String dinhDang = "%" + Integer.toString(timMaKhachHangCaoNhat()).length() + "d";
        return String.format(dinhDang, maKH).replace(" ", "0");
    }
}
