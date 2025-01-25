package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {

    private Connection conn;

    public Connection getConexao() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/clinica_db";
                String user = "root";
                String password = "root";
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
  
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectado.");
            }
        } catch (SQLException e) {
        }
    }
}
