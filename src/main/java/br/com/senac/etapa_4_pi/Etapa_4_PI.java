package br.com.senac.etapa_4_pi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Etapa_4_PI {

     public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC carregado");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Driver JDBC não encontrado: "+ cnfe.getMessage());
        }

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica_db", "root", "root");
            System.out.println("Conexao estabelecida.");
        } catch (SQLException sqle) {
            System.out.println("Falha ao conectar ao banco de dados. " + sqle.getMessage());
        }

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            System.out.println("Pronto para execucao de comandos.");
        } catch (SQLException sqle) {
            System.out.println("Erro ao acessar o Banco de Dados: " + sqle.getMessage());
        }

        try {
            String sql = null;

            sql = "insert into medicos (nome, crm) values ('zé', '090909-M')";
            stmt.executeUpdate(sql);
            System.out.println("Dados gravados.");
        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir: " + sqle.getMessage());
        }

        try {
            con.close(); 
            System.out.println("Conexao com o banco de dados encerrada.");
        } catch (SQLException sqle) {
            System.out.println("Erro no fechamento da conexao: " + sqle.getMessage());
        }

    }
}
