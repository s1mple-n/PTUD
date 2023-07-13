package services;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connectDB.KetNoiCSDL;
import entity.ChiTietHoaDonNhapHang;
import entity.SanPham;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class TienIch {
    /**
     * @author Hiếu
     *
     * <p>Hàm này thay thế một số phương thức cơ bản của các lớp DAO và
     * giúp lấy dữ liệu của các bảng từ CSDLnhưng ko cần tạo lớp DAO cụ thể</p>
     *
     * @param tenBang tên Table muốn thao tác
     * @param dieuKien một List các điều kiện (sử dụng phép and)
     * @return một ResultSet trỏ tới bảng và chứa những dữ liệu phù hợp với điều kiện
     *
     * <p><i>Cách sử dụng:</i></p>
     * <ul>
     * 	<li>
     * 		Nếu muốn load toàn bộ dữ liệu bảng, tham số dieuKien sẽ là null
     * 		<ul>
     * 			<li>Ex: TienIch.layDuLieuCoDieuKien("NhanVien", null);</li>
     * 		</ul>
     * 	</li>
     * 	<li>
     * 		Nếu muốn load kèm điều kiện, truyền vào một list các điều kiện, mỗi điều kiện sẽ theo format:</br>
     * 		[tên cột]-[điều kiện]
     * 		<ul>
     * 			<li>Ex: TienIch.layDuLieuCoDieuKien("NhanVien", Arrays.asList("gioiTinh-1", "capBac-1"));</li>
     * 		</ul>
     * 	</li>
     * </ul>
     *
     * @throws SQLException Quăng lỗi SQLException
     */
    public static ResultSet layDuLieuCoDieuKien(String tenBang, List<String> dieuKien) throws SQLException {
        StringBuilder query = new StringBuilder(String.format("select * from %s", tenBang));

        if (dieuKien != null)
            tachDieuKien(query, dieuKien);

        /*
         * Do các table sẽ có những trường dùng UTF-8, nên bắt buộc phải tách điều kiện trước,
         * sau đó tạo PreparedStatement rồi mới có thể đặt giá trị cho từng điệu kiện tương ứng.
         * */
        PreparedStatement pState = KetNoiCSDL.layKetNoi().prepareStatement(query.toString());

        datDuLieuChoDieuKien(pState, dieuKien);

        return pState.executeQuery();
    }

    private static void tachDieuKien(StringBuilder query, List<String> dieuKien) {
        query.append(" where ");

        dieuKien.forEach(dk -> {
            String[] temp = dk.split("-");
            query.append(String.format("%s = ? and ", temp[0]));
        });

        query.delete(query.lastIndexOf("and"), query.length());
    }

    private static void datDuLieuChoDieuKien(PreparedStatement pState, List<String> dieuKien) throws SQLException {
        if (dieuKien == null)
            return;

        for (int i = 0; i < dieuKien.size(); i++)
            pState.setString(i + 1, dieuKien.get(i).split("-")[1]);
    }

    /**
     * @author Hiếu
     * @param res: ResultSet chứa dữ liệu sẽ được xuất ra file excel
     * @param loaiThongKe: loại thống kê, vd: ThongKeSanPhamBanChay,...
     * @param duongDan: Đường dẫn sẽ lưu file mà người dùng đã chọn/chỉ định
     * @throws FileNotFoundException
     * @throws IOException
     * @throws SQLException
     */
    private static void xuatFileRaExcel(ResultSet res, String loaiThongKe ,String duongDan, String thongTinThem) throws FileNotFoundException, IOException, SQLException {
        @SuppressWarnings("resource")
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet(loaiThongKe);

        XSSFCellStyle tCs = workBook.createCellStyle();
        tCs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        tCs.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        ResultSetMetaData meta = res.getMetaData();
        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < meta.getColumnCount(); i++) {
            headerRow.createCell(i).setCellValue(meta.getColumnName(i + 1));
            sheet.getRow(0).getCell(i).setCellStyle(tCs);
        }

        int contentRowCount = 1;
        while(res.next()) {
            Row currentRow = sheet.createRow(contentRowCount++);
            for(int i = 0; i < meta.getColumnCount(); i++)
                currentRow.createCell(i).setCellValue(res.getString(i + 1));
        }

        try(FileOutputStream fos = new FileOutputStream(String.format("%s/%s.xlsx", duongDan, dinhDangTenFile(loaiThongKe, thongTinThem)))) {
            workBook.write(fos);
        }
    }

    /**
     * @author Hiếu
     * @param table: JTable chứa dữ liệu sẽ được xuất ra Excel
     * @param loaiThongKe: loại thống kê, vd: ThongKeSanPhamBanChay,...
     * @param duongDan: Đường dẫn sẽ lưu file mà người dùng đã chọn/chỉ định
     * @throws FileNotFoundException
     * @throws IOException
     * @throws SQLException
     */
    private static void xuatFileRaExcel(JTable table, String loaiThongKe , String duongDan, String thongTinThem) throws FileNotFoundException, IOException, SQLException {
        @SuppressWarnings("resource")
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet(loaiThongKe);

        XSSFCellStyle tCs = workBook.createCellStyle();
        tCs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        tCs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

        Row headerRow = sheet.createRow(0);
        int soLuongCot = table.getColumnCount();
        for(int i = 0; i < soLuongCot; i++) {
            headerRow.createCell(i).setCellValue(table.getColumnName(i));
            sheet.getRow(0).getCell(i).setCellStyle(tCs);
        }

        int contentRowCount = 1;
        int soLuongHang = table.getRowCount();
        for(int i = 0; i < soLuongHang; i++) {
            Row currentRow = sheet.createRow(contentRowCount++);
            for(int j = 0; j < soLuongCot; j++) {
                currentRow.createCell(j).setCellValue(table.getValueAt(i, j).toString());
            }
        }

        try(FileOutputStream fos = new FileOutputStream(String.format("%s/%s.xlsx", duongDan, dinhDangTenFile(loaiThongKe, thongTinThem)))) {
            workBook.write(fos);
        }
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Method có chức năng hỗ trợ đọc dữ liệu từ file excel
     * </i></p>
     * @param duongDan đường dẫn file muốn import
     * @return Trả về một Map với key là mã lô hàng, values là danh sách chi tiết hóa đơn nhập hàng
     * @throws IOException quăng lỗi
     */
    public static Map<Integer, ArrayList<ChiTietHoaDonNhapHang>> nhapHangTuFileExcel(String duongDan) throws IOException {
        Map<Integer, ArrayList<ChiTietHoaDonNhapHang>> hmNhapHang = new HashMap<>();
        ArrayList<ChiTietHoaDonNhapHang> dsChiTietHDNH = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(new File(duongDan));

        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet currentSheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = currentSheet.iterator();

        //Trường hợp file excel không có dữ liệu
        if(currentSheet.getRow(0) == null)
            return null;

        Integer maLoHang = (int)currentSheet.getRow(0).getCell(0).getNumericCellValue();

        rowIterator.next();
        rowIterator.next();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();

            SanPham sp = new SanPham(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(),
                    row.getCell(4).getStringCellValue(), row.getCell(9).getNumericCellValue(),
                    row.getCell(5).getStringCellValue(), row.getCell(6).getStringCellValue(),
                    (int)row.getCell(8).getNumericCellValue(), row.getCell(7).getStringCellValue()
                    );

            ChiTietHoaDonNhapHang cthdnh = new ChiTietHoaDonNhapHang(sp, sp.getSoLuongTon(), sp.getDonGia(),
                    row.getCell(10).getNumericCellValue());

            dsChiTietHDNH.add(cthdnh);
        }

        hmNhapHang.put(maLoHang, dsChiTietHDNH);

        fileInputStream.close();

        return hmNhapHang;
    }

    /**
     * <p>Tên file sẽ được định dạng theo mẫu: [Tên loại thống kê]_[Ngày-tháng-năm].xlsx</p>
     * EX:
     * + Input: ThongKeSanPham và ngày hiện tại là 24/10/2021
     * + Output: ThongKeSanPham_24-10-2021.xlsx
     * @param loaiThongKe Tên loại thống kê muốn xuất ra file excel
     * @return Trả về tên file đã định dạng theo mẫu
     */
    private static String dinhDangTenFile(String loaiThongKe, String thongTinThem) {
        String date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
        return String.format("%s_%s_%s", loaiThongKe, thongTinThem, date);
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Hàm có chức năng tính toán và định dạng ngày bắt đầu trong tuần(thứ hai) dựa vào ngày hiện tại<br>
     *     ví dụ: hôm nay là thứ ba, 9 tháng 11, thì ngày đầu tuần sẽ là thứ hai, 8 tháng 11
     * </i></p>
     * @return trả về ngày đầu tiên trong tuần <br>
     */
    public static String getNgayDauTrongTuan() {
        LocalDate thuHai = LocalDate.now();

        while(thuHai.getDayOfWeek() != DayOfWeek.MONDAY)
            thuHai = thuHai.minusDays(1);

        return dinhDangNgay(thuHai, "yyyy-MM-dd");
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Method có chức năng định dạng ngày tháng năm theo định dạng mong muốn
     * </i></p>
     * @param ngay Thời gian(ngày, tháng năm) cần định dạng
     * @param dinhDang mẫu định dạng, vd: dd-MM-yyyy
     * @return ngày đã được định dạng theo mẫu
     */
    public static String dinhDangNgay(LocalDate ngay, String dinhDang) {
        return ngay.format(DateTimeFormatter.ofPattern(dinhDang));
    }

    /**
     * @author Hiếu
     * <p><i>
     *     Hàm có chức năng lấy mã (kiểu int) lớn nhất
     * </i></p>
     * @param so tên của mã muốn lấy ở csdl, ví dụ: maHDBH
     * @param biDanh bí danh(as ở csdl), ví dụ: select max(maHDBH) as [bí danh] from ...
     * @param tenBang tên bảng muốn truy vấn
     * @return mã lớn nhất tương ứng
     */
    public static int laySoLonNhat(String so, String biDanh, String tenBang) throws SQLException {
        String query = String.format("select max(%s) as %s from %s", so, biDanh, tenBang);

        Connection con = KetNoiCSDL.getInstance().layKetNoi();
        PreparedStatement pState = con.prepareStatement(query);

        ResultSet res = pState.executeQuery();

        res.next();

        return res.getInt(biDanh);
    }

    public static String dinhDangSo(int soLonNhat, int soCanDinhDang) {
        String dinhDang = "%" + Integer.toString(soLonNhat).length() + "d";
        return String.format(dinhDang, soCanDinhDang).replace(" ", "0");
    }

    private static void xuatFileRaPDF(JTable table, String loaiThongKe , String duongDan, String thongTinThem) throws FileNotFoundException, IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());

        BaseFont baseFont = BaseFont.createFont(
                new File("src/main/resources/Fonts/vuArial.ttf").getAbsolutePath(),
                BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED
        );

        com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 11);

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(
                    document,
                    new PrintStream(
                            new FileOutputStream(String.format("%s/%s.pdf", duongDan, dinhDangTenFile(loaiThongKe, thongTinThem))),
                            true,
                            StandardCharsets.UTF_8
                    )
            );

            document.open();

            PdfPTable pdfPTable = new PdfPTable(
                    table.getColumnCount()
            );

            for (int i =0; i < table.getColumnCount(); i++){
                pdfPTable.addCell(
                        new Paragraph(
                                table.getColumnName(i),
                                font
                        )
                );
            }

            for (int row = 0; row < table.getRowCount(); row++){
                for (int col = 0; col < table.getColumnCount(); col++){
                    pdfPTable.addCell(
                            new Paragraph(
                                    table.getModel().getValueAt(
                                            row, col
                                    ).toString(),
                                    font
                            )
                    );
                }
            }

            document.add(pdfPTable);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        document.close();
    }

    public static void xuatDataTrongTableRaFile(JTable table, boolean isXuatExcel, int loaiThongKe, String thongTinThem){
        String[] phanLoai = getTieuDeCuaSoVaTenLoaiThongKeTheoLoaiThongKe(loaiThongKe);

        JFileChooser fileChooser = new JFileChooser(
                FileSystemView.getFileSystemView()
                        .getHomeDirectory().toPath().toString()
        ){
            @Override
            protected JDialog createDialog( Component parent ) throws HeadlessException {
                JDialog dialog = super.createDialog( parent );

                dialog.setIconImage(new ImageIcon(
                        "src/main/resources/BieuTuong/Logo2.jpg"
                ).getImage());
                return dialog;
            }
        };

        fileChooser.setDialogTitle(phanLoai[0]);

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if(table.getRowCount() < 1) {
            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Không có dữ liệu để xuất thống kê!"
            );

            return;
        }

        int approve = fileChooser.showSaveDialog(fileChooser);
        if(approve == JFileChooser.APPROVE_OPTION) {
            String duongDan = fileChooser.getSelectedFile().toString().replace("\\", "/");

            try {
                if (isXuatExcel){
                    xuatFileRaExcel(
                            table,
                            phanLoai[1],
                            duongDan,
                            thongTinThem
                    );
                }
                else{
                    xuatFileRaPDF(
                            table,
                            phanLoai[1],
                            duongDan,
                            thongTinThem
                    );
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            CacHamDungSan.hienThiThongBaoKetQua(
                    GDThongBaoKetQua.THONG_BAO_THANH_CONG,
                    "Đã xuất dữ liệu xong!"
            );
        }
    }

    private static String[] getTieuDeCuaSoVaTenLoaiThongKeTheoLoaiThongKe(int loaiThongKe){
        String[][] danhSach = new String[][]{
                {"Xuất thống kê hoá đơn bán hàng", "ThongKeHoaDonBanHang"},
                {"Xuất thống kê khách hàng", "ThongKeKhachHang"},
                {"Xuất thống kê sản phẩm", "ThongKeSanPham"},
                {"Xuất thống kê hoá đơn nhập hàng", "ThongKeHoaDonNhapHang"},
                {"Xuất thống kê phiếu đối chứng", "ThongKePhieuDoiChung"},
                {"Xuất thống kê nhật kí bán hàng theo ca", "ThongKeNhatKiBanHangTheoCa"},
                {"Xuất thống kê nhân viên", "ThongKeNhanVien"},
                {"Xuất thống kê doanh thu theo hoá đơn bán hàng", "ThongKeDoanhThuTheoHoaDonBanHang"},
                {"Xuất thống kê doanh thu theo tất cả nhân viên", "ThongKeDoanhThuTheoTatCaNhanVien"},
                {"Xuất thống kê doanh thu theo từng nhân viên", "ThongKeDoanhThuTheoTungNhanVien"},
                {"Xuất thống kê doanh thu theo tất cả khách hàng", "ThongKeDoanhThuTheoTatCaKhachHang"},
                {"Xuất thống kê doanh thu theo từng khách hàng", "ThongKeDoanhThuTheoTungKhachHang"},
                {"Xuất thống kê theo mọi sản phẩm", "ThongKeTheoMoiSanPham"},
                {"Xuất thống kê theo từng sản phẩm", "ThongKeTheoTungSanPham"}
        };


        String[] rs;

        switch (loaiThongKe){
            case IDSBienMacDinh.THONG_KE_HOA_DON_BAN_HANG -> {
                rs = new String[]{danhSach[0][0], danhSach[0][1]};
            }

            case IDSBienMacDinh.THONG_KE_KHACH_HANG -> {
                rs = new String[]{danhSach[1][0], danhSach[1][1]};
            }

            case IDSBienMacDinh.THONG_KE_SAN_PHAM -> {
                rs = new String[]{danhSach[2][0], danhSach[2][1]};
            }

            case IDSBienMacDinh.THONG_KE_HOA_DON_NHAP_HANG -> {
                rs = new String[]{danhSach[3][0], danhSach[3][1]};
            }

            case IDSBienMacDinh.THONG_KE_PHIEU_DOI_CHUNG -> {
                rs = new String[]{danhSach[4][0], danhSach[4][1]};
            }

            case IDSBienMacDinh.THONG_KE_NHAT_KI_BAN_HANG_THEO_CA -> {
                rs = new String[]{danhSach[5][0], danhSach[5][1]};
            }

            case IDSBienMacDinh.THONG_KE_NHAN_VIEN -> {
                rs = new String[]{danhSach[6][0], danhSach[6][1]};
            }

            case IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_HOA_DON_BAN_HANG -> {
                rs = new String[]{danhSach[7][0], danhSach[7][1]};
            }

            case IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TAT_CA_NHAN_VIEN -> {
                rs = new String[]{danhSach[8][0], danhSach[8][1]};
            }

            case IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TUNG_NHAN_VIEN -> {
                rs = new String[]{danhSach[9][0], danhSach[9][1]};
            }

            case IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TAT_CA_KHACH_HANG -> {
                rs = new String[]{danhSach[10][0], danhSach[10][1]};
            }

            case IDSBienMacDinh.THONG_KE_DOANH_THU_THEO_TUNG_KHACH_HANG -> {
                rs = new String[]{danhSach[11][0], danhSach[11][1]};
            }

            case IDSBienMacDinh.THONG_KE_THEO_TAT_CA_SAN_PHAM -> {
                rs = new String[]{danhSach[12][0], danhSach[12][1]};
            }

            case IDSBienMacDinh.THONG_KE_THEO_TUNG_SAN_PHAM -> {
                rs = new String[]{danhSach[13][0], danhSach[13][1]};
            }

            default -> throw new IllegalStateException("Unexpected value: " + loaiThongKe);
        }

        return rs;
    }

    public static double chuyenDinhDangTienTeDaFormatSangNguyenGoc(JTextField txt){
        double rs = 0.0;

        try {
            rs = Double.parseDouble(
                            txt.getText().trim()
                            .replace(".", "")
                            .replace(",", "")
                            .replace("₫", "")
                            .replace(" ", "")
            );
        } catch (Exception ignored){

        }

        return rs;
    }

    /**
     * <p>Đây là một hàm cực kì nhạy cảm</p>
     * <p>Đường dẫn sẽ trông như thế này:   "/duongDan"</p>
     * <p>Ví dụ, cần khởi tạo một ảnh tại class Test thì hàm sẽ được gọi như sau:</p>
     * <li><b>layIconTuongUngTheoDuongDan(Test.class, "/duongDan")</b></li>
     *
     * @param cls: Class tương ứng cần set ImageIcon
     * @param duongDan: đường dẫn tương đối đến file ảnh
     * @return: ImageIcon tương ứng
     */
    public static ImageIcon layIconTuongUngTheoDuongDan(Class cls, String duongDan){
        return new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(
                        cls.getResource(duongDan)
                )
        );
    }
}