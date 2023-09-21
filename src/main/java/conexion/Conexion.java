package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectar() {
        Connection con = null;
        String driver = "com.mysql.cj.jdbc.Driver";

        String usuario = "root";
        String password = "Tin1959_2023";
        String url = "jdbc:mysql://localhost/prev_riesgo";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
