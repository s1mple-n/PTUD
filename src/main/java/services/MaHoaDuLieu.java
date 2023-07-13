package services;

public class MaHoaDuLieu {
    private static final int BIENRANDOMLONNHAT = 10;
    /**
     * - Mã hóa dữ liệu từ dữ liệu thuần sang dạng không thể đọc hiểu được
     *
     * @param duLieu: Dữ liệu đầu vào cần mã hóa
     * @return: Dữ liệu đầu vào sau khi đã mã hóa
     */
    public static String maHoa(String duLieu) {
        StringBuilder chuoiDaMaHoa = new StringBuilder();

        final long soChan = Math.round(
                Math.random() * (BIENRANDOMLONNHAT - 1)
        );
        final long soLe = Math.round(
                Math.random() * (BIENRANDOMLONNHAT - 1)
        );

        char[] chuoiTam = duLieu.toCharArray();

        chuoiDaMaHoa.append(soChan);

        for (int i = 0; i < chuoiTam.length; ++i) {
            if (i % 2 == 0)
                chuoiTam[i] += soChan;
            else
                chuoiTam[i] -= soLe;

            chuoiDaMaHoa.append(chuoiTam[i]);
        }

        chuoiDaMaHoa.append(soLe);

        return new StringBuilder(chuoiDaMaHoa.toString())
                .reverse()
                .toString();
    }

    /**
     * - Giải mã chuỗi dữ liệu đã bị mã hóa trở về trạng thái ban đầu (trước khi bị mã hóa)
     *
     * @param duLieu: Dữ liệu đầu vào cần được giải mã
     * @return: Chuỗi dữ liệu sau khi đã giải mã
     */
    public static String giaiMa(String duLieu) {
        int doDaiChuoiDauVao = duLieu.length();
        StringBuilder chuoiDaGiaiMa = new StringBuilder();

        String chuoiDaoNguoc = new StringBuilder(duLieu)
                .reverse()
                .toString();

        final int soChan = Integer.parseInt(
                chuoiDaoNguoc.charAt(0) + ""
        );
        final int soLe = Integer.parseInt(
                chuoiDaoNguoc.charAt(doDaiChuoiDauVao - 1) + ""
        );

        char[] chuoiTam = chuoiDaoNguoc.substring(
                1, doDaiChuoiDauVao - 1
        ).toCharArray();

        for (int i = 0; i < chuoiTam.length; ++i) {
            if (i % 2 == 0)
                chuoiTam[i] -= soChan;
            else
                chuoiTam[i] += soLe;

            chuoiDaGiaiMa.append(chuoiTam[i]);
        }
        return chuoiDaGiaiMa.toString();
    }
}
