package dao;

import connectDB.KetNoiCSDL;
import entity.NhanVien;
import entity.PhieuDoiChung;
import services.TienIch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class PhieuDoiChungDAO {
    public static boolean themPhieuDoiChung(PhieuDoiChung phieuDoiChung){
        String query =
                "insert into PhieuDoiChung(" +
                        "[maNhanVienLapPhieu], " +
                        "[maNhanVienKiemPhieu], " +
                        "[thoiGianLapPhieu], " +
                        "[soTienBanDau], " +
                        "[soTienTrongKetHeThongTinh], " +
                        "[soTienNguoiGiaoCaTinh], " +
                        "[soTienNguoiNhanCaTinh]) " +
                        "values(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, phieuDoiChung.getNhanVienLapPhieu().getMaNV());
            ps.setString(2, phieuDoiChung.getNhanVienKiemPhieu().getMaNV());
            ps.setTimestamp(3, Timestamp.valueOf(phieuDoiChung.getThoiGianLapPhieu()));
            ps.setDouble(4, phieuDoiChung.getSoTienBanDau());
            ps.setDouble(5, phieuDoiChung.getSoTienTrongKetHeThongTinh());
            ps.setDouble(6, phieuDoiChung.getSoTienNguoiGiaoCaTinh());
            ps.setDouble(7, phieuDoiChung.getSoTienNguoiNhanCaTinh());

            return ps.executeUpdate() > 0;

        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public static double xacDinhSoTienNVCaSangGiaoLaiChoNVCaToi(){
        AtomicReference<Double> soTienThuaKeTrongKet = new AtomicReference<>(0.0);

        String query = "select soTienNguoiNhanCaTinh from PhieuDoiChung " +
                "where convert(date, thoiGianLapPhieu, 102) = convert(date, getdate(), 102)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                soTienThuaKeTrongKet.set(rs.getDouble(1));

                break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return soTienThuaKeTrongKet.get();
    }

    public static double xacDinhSoTienConLaiTrongKetSauKhiKetThucCaLam(String maNV, double soTienCaTruocGiao){
        AtomicReference<Double> soTienDaThoi = new AtomicReference<>(0.0);

        String query =
                "select sum(soTienKhachDua) - sum(soTienThoiLai) from HoaDonBanHang " +
                        "where maNhanVienLapHDBH = ? and CONVERT(date, thoiGianLap, 102) = convert(date, getdate(), 102)";

        try {
            PreparedStatement ps = KetNoiCSDL.layKetNoi().prepareStatement(query);

            ps.setString(1, maNV);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                soTienDaThoi.set(rs.getDouble(1));

                break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return soTienDaThoi.get() + soTienCaTruocGiao;
    }

    public static PhieuDoiChung timPhieuDoiChungTheoMa(int maPhieu){
        PhieuDoiChung phieuDoiChung = null;

        try{
            ResultSet rs = TienIch.layDuLieuCoDieuKien("PhieuDoiChung", Arrays.asList("maPhieuDoiChung-"+ maPhieu +""));

            while (rs.next()){
                phieuDoiChung = new PhieuDoiChung(
                        rs.getInt(1),
                        new NhanVien(rs.getString(2)),
                        new NhanVien(rs.getString(3)),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8)
                );

                break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return phieuDoiChung;
    }


    public static ResultSet timPhieuDoiChungTheoMaPhieuHoacMaNhanVienLap(String tuKhoa){
        ResultSet resultSet = null;

        try{
            String sql =
                    "select top 7 maPhieuDoiChung, maNhanVienLapPhieu, maNhanVienKiemPhieu, thoiGianLapPhieu " +
                    "from PhieuDoiChung " +
                    "where maPhieuDoiChung like ? or maNhanVienLapPhieu like ?";

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
