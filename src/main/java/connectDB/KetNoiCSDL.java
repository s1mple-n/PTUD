package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetNoiCSDL {

    private static final String url = "jdbc:sqlserver://localhost:1433;databasename=QLBH";
    private static final String tenDangNhap = "sa";
    private static final String matKhau = "sapassword";

    private static KetNoiCSDL instance = null;
    private static Connection con = null;

    public static KetNoiCSDL getInstance() {
        if (instance == null)
            instance = new KetNoiCSDL();
        return instance;
    }

    /**
     * <p>Thiết lập kết nối đến cơ sở dữ liệu</p>
     */
    public boolean thietLapketNoi() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, tenDangNhap, matKhau);

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * <p>Trả về kết nối đã được khởi tạo có nhiệm vụ kết nối tới cơ sở dữ liệu.</p>
     * @return Kết nối tới cơ sở dữ liệu đã được khởi tạo
     */
    public synchronized static Connection layKetNoi() {
        return con;
    }

    /**
     * <p>Ngắt kết nối đến cơ sở dữ liệu</p>
     */
    public void huyKetNoi() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
