package dao;

import connectDB.KetNoiCSDL;
import entity.CaLamViec;
import entity.NhanVien;
import services.MaHoaDuLieu;
import services.TienIch;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class NhanVienDAO {

    public static Map<String, NhanVien> layToanBoDuLieuNhanVien() {
        Map<String, NhanVien> dsnv = new TreeMap<>();
        try {
            ResultSet rs = TienIch.layDuLieuCoDieuKien("NhanVien", null);

            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String hoTen = MaHoaDuLieu.giaiMa(rs.getString("hoTen"));
                String sdt = MaHoaDuLieu.giaiMa(rs.getString("soDT"));
                String cmnd = MaHoaDuLieu.giaiMa(rs.getString("soCMND"));
                String diaChi = MaHoaDuLieu.giaiMa(rs.getString("diaChi"));
                boolean gt = rs.getBoolean("gioiTinh");
                LocalDateTime ngayVaoLam = rs.getTimestamp("ngayVaoLam").toLocalDateTime();
                boolean capBac = rs.getBoolean("capBac");
                boolean trangThaiLamViec = rs.getBoolean("trangThaiLamViec");
                boolean maCaLamViec = rs.getBoolean("maCaLamViec");
                String maNguoiQuanLiThemVao = rs.getString("maNguoiQuanLiThemVao");

                NhanVien nv = new NhanVien(
                        maNV,
                        new CaLamViec(maCaLamViec),
                        hoTen,
                        sdt,
                        cmnd,
                        diaChi,
                        gt,
                        ngayVaoLam,
                        capBac,
                        trangThaiLamViec,
                        new NhanVien(maNguoiQuanLiThemVao)
                );

                dsnv.put(nv.getMaNV(), nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsnv;
    }

    public static NhanVien timNhanVienTheoMa(String maNhanVien){
        NhanVien nhanVien = null;

        try {
            ResultSet res = TienIch.layDuLieuCoDieuKien(
                    "NhanVien",
                    Arrays.asList("maNhanVien-" + maNhanVien)
            );

            while (res.next())
                nhanVien = new NhanVien(
                        res.getString("maNhanVien"),
                        new CaLamViec(res.getBoolean("maCaLamViec")),
                        MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                        MaHoaDuLieu.giaiMa(res.getString("soDT")),
                        MaHoaDuLieu.giaiMa(res.getString("soCMND")),
                        MaHoaDuLieu.giaiMa(res.getString("diaChi")),
                        res.getBoolean("gioiTinh"),
                        res.getBoolean("capBac"),
                        res.getBoolean("trangThaiLamViec"),
                        res.getTimestamp("ngayVaoLam").toLocalDateTime()
                );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return nhanVien;
    }

    public static NhanVien timNhanVienTheoSoCMND(String soCMND){
        NhanVien nhanVien = null;

        try {
            ResultSet res = TienIch.layDuLieuCoDieuKien(
                    "NhanVien",
                    Arrays.asList("soCMND-" + soCMND)
            );

            while (res.next())
                nhanVien = new NhanVien(
                        res.getString("maNhanVien"),
                        new CaLamViec(res.getBoolean("maCaLamViec")),
                        MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                        MaHoaDuLieu.giaiMa(res.getString("soDT")),
                        MaHoaDuLieu.giaiMa(res.getString("soCMND")),
                        MaHoaDuLieu.giaiMa(res.getString("diaChi")),
                        res.getBoolean("gioiTinh"),
                        res.getBoolean("capBac"),
                        res.getBoolean("trangThaiLamViec"),
                        res.getTimestamp("ngayVaoLam").toLocalDateTime()
                );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return nhanVien;
    }

    public static NhanVien timNhanVienTheoSoDienThoai(String soDT){
        NhanVien nhanVien = null;

        try {
            ResultSet res = TienIch.layDuLieuCoDieuKien(
                    "NhanVien",
                    Arrays.asList("soDT-" + soDT)
            );

            while (res.next())
                nhanVien = new NhanVien(
                        res.getString("maNhanVien"),
                        new CaLamViec(res.getBoolean("maCaLamViec")),
                        MaHoaDuLieu.giaiMa(res.getString("hoTen")),
                        MaHoaDuLieu.giaiMa(res.getString("soDT")),
                        MaHoaDuLieu.giaiMa(res.getString("soCMND")),
                        MaHoaDuLieu.giaiMa(res.getString("diaChi")),
                        res.getBoolean("gioiTinh"),
                        res.getBoolean("capBac"),
                        res.getBoolean("trangThaiLamViec"),
                        res.getTimestamp("ngayVaoLam").toLocalDateTime()
                );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return nhanVien;
    }

    public static int xacDinhSoLuongNhanVien(){
        int n = 0;

        try {
            PreparedStatement pstm = KetNoiCSDL.layKetNoi().prepareStatement("select count(*) from NhanVien");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                n = rs.getInt(1);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return n;
    }

    public static boolean themNhanVienMoi(NhanVien nhanVien){
        try {
            PreparedStatement pstm = KetNoiCSDL.layKetNoi().prepareStatement(
                    "insert into " + "NhanVien values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            pstm.setString(1, nhanVien.getMaNV());
            pstm.setBoolean(2, nhanVien.getCaLamViec().isCaSang());
            pstm.setString(3, MaHoaDuLieu.maHoa(nhanVien.getHoTen()));
            pstm.setString(4, MaHoaDuLieu.maHoa(nhanVien.getSoDT()));
            pstm.setString(5, MaHoaDuLieu.maHoa(nhanVien.getSoCMND()));
            pstm.setString(6, MaHoaDuLieu.maHoa(nhanVien.getDiaChi()));
            pstm.setBoolean(7,nhanVien.isNam());
            pstm.setTimestamp(8, Timestamp.valueOf(nhanVien.getNgayVaoLam()));
            pstm.setBoolean(9, nhanVien.isQuanLi());
            pstm.setBoolean(10, nhanVien.isNghiLam());
            pstm.setString(11, nhanVien.getNguoiQuanLiThemVao().getMaNV());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean capNhatThongTinNhanVien(String maNhanVien, NhanVien thongTinNVMoi){
        boolean res = true;

        try {
            PreparedStatement pstm = KetNoiCSDL.layKetNoi().prepareStatement(
                    "update NhanVien " +
                    "set soDT = ?, soCMND = ?, diaChi = ?, maCaLamViec = ?, capBac = ?, trangThaiLamViec = ? " +
                    "where maNhanVien = ?");

            pstm.setString(1, MaHoaDuLieu.maHoa(thongTinNVMoi.getSoDT()));
            pstm.setString(2, MaHoaDuLieu.maHoa(thongTinNVMoi.getSoCMND()));
            pstm.setString(3, MaHoaDuLieu.maHoa(thongTinNVMoi.getDiaChi()));
            pstm.setBoolean(4, thongTinNVMoi.getCaLamViec().isCaSang());
            pstm.setBoolean(5, thongTinNVMoi.isQuanLi());
            pstm.setBoolean(6, thongTinNVMoi.isNghiLam());
            pstm.setString(7, maNhanVien);

            res = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


}
