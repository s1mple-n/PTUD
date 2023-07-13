package dao;

import connectDB.KetNoiCSDL;
import entity.ChiTietHoaDonBanHang;
import entity.SanPham;
import services.TienIch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SanPhamDAO {
    public static boolean themSanPhamMoi(SanPham sanPham){
        try {
            PreparedStatement pstm = KetNoiCSDL.layKetNoi().prepareStatement("insert into " + "SanPham values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstm.setString(1, sanPham.getMaSP());
            pstm.setString(2, sanPham.getTenSP());
            pstm.setString(3, sanPham.getMauSac());
            pstm.setString(4, sanPham.getSize());
            pstm.setString(5, sanPham.getChatLieu());
            pstm.setDouble(6,sanPham.getDonGia());
            pstm.setString(7, sanPham.getThuongHieu());
            pstm.setString(8, sanPham.getNguonGoc());
            pstm.setInt(9,sanPham.getSoLuongTon());
            pstm.setString(10,sanPham.getMoTa());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean capNhatSLTonVaDonGiaChoSanPhamKhiNhapHangTheoMaKhiNhapHang(String maSP, int soLuongCanThem, double donGiaMoi){
        String query =  "update SanPham " +
                "set soLuongTon = soLuongTon + ? , donGia = ? " +
                "where maSP = ?";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setInt(1, soLuongCanThem);
            ps.setDouble(2, donGiaMoi);
            ps.setString(3, maSP);

            return ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public int timSoLuongTonNhieuNhat() throws SQLException {
        int slt = 0;

        Connection con = KetNoiCSDL.layKetNoi();

        ResultSet res = con.prepareStatement("select max(soLuongTon) as SLT from SanPham").executeQuery();

        while(res.next())
            slt = res.getInt("SLT");

        return slt;
    }

    public String dinhDangSoLuongTon(int soLuongTon)  {
        String dinhDang = null;
        try {
            dinhDang = "%" + Integer.toString(timSoLuongTonNhieuNhat()).length() + "d";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format(dinhDang, soLuongTon).replace(" ", "0");
    }

    public static int laySoLuongTonCuaSanPham(String maSP){
        AtomicInteger slTon = new AtomicInteger(0);

        String query = "select soLuongTon from SanPham " +
                "where maSP = ?";

        try{
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, maSP);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                slTon.set(rs.getInt(1));

                break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return slTon.get();
    }

    public static boolean capNhatLaiSoLuongTonSanPhamSauKhiTaoHoaDonBanHang(List<ChiTietHoaDonBanHang> dsChiTietHoaDonBanHang){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        String query = "update SanPham " +
                "set soLuongTon = soLuongTon - ? " +
                "where maSP = ?";

        dsChiTietHoaDonBanHang.forEach(ct -> {
            try {
                PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

                ps.setInt(1, ct.getSoLuongBan());
                ps.setString(2, ct.getSanPham().getMaSP());

                atomicBoolean.set(ps.executeUpdate() > 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return atomicBoolean.get();
    }

    public static boolean kiemTraSuTonTaiCuaSanPhamTheoMa(String maSP){
        try{
            ResultSet rs = TienIch.layDuLieuCoDieuKien("SanPham", Arrays.asList("maSP-"+ maSP +""));

            if (!rs.isBeforeFirst()){
                return false;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return true;
    }

    public static boolean capNhatThongTinChoSanPham(String maSP, String tenSPMoi, double donGiaMoi, String moTaMoi){
        String query = "update SanPham " +
                "set tenSP = ?, donGia = ?, moTa = ? " +
                "where maSP = ?";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, tenSPMoi);
            ps.setDouble(2, donGiaMoi);
            ps.setString(3, moTaMoi);
            ps.setString(4, maSP);

            return ps.executeUpdate() > 0;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public int tinhTongSLSPTonKho(){
        AtomicInteger slspTonKho = new AtomicInteger(0);

        String query = "select sum(soLuongTon) from SanPham";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                slspTonKho.set(rs.getInt(1));

                break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return slspTonKho.get();
    }

    public static ResultSet timKiemSanPhamTheoMaHoacTen(String tuKhoa){
        ResultSet resultSet = null;

        try {
            String query = "select top 7 " +
                    "maSP, tenSP, thuongHieu, donGia, soLuongTon from SanPham " +
                    "where soLuongTon > 0 and " +
                    "(maSP like ? or " +
                    "tenSP like ?)";

            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, "%"+ tuKhoa +"%");
            ps.setString(2, "%"+ tuKhoa +"%");

            resultSet = ps.executeQuery();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return resultSet;
    }

    public static SanPham laySanPhamTheoMa(String maSP){
        try {
            ResultSet rs = TienIch.layDuLieuCoDieuKien("SanPham", Arrays.asList("maSP-"+ maSP +""));

            while (rs.next()) {
                String maSPham = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                String mau = rs.getString("mauSac");
                String size = rs.getString("size");
                String chatLieu = rs.getString("chatLieu");
                double dg = rs.getDouble("donGia");
                String thuongHieu = rs.getString("thuongHieu");
                String nguonGoc = rs.getString("nguonGoc");
                int slt = rs.getInt("soLuongTon");
                String moTa = rs.getString("moTa");

                SanPham sp = new SanPham(maSPham, tenSP, mau, size, chatLieu, dg, thuongHieu, nguonGoc, slt, moTa);

                return sp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet thongKeDoanhThuTheoSanPham(String query){
        return null;
    }


}
