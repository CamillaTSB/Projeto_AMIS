package etapaDao;

import conexao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Agendamento;
import model.Paciente;
import model.UsuarioAtend;
import model.UsuarioEnf;
import model.UsuarioMed;

public class etapaDao {

    private final conexao conectar;
    private Connection conn;

    public etapaDao() {
        this.conectar = new conexao();
        this.conn = this.conectar.getConexao();
    }

    public void inserirMed(UsuarioMed medico) {
        String sql = "INSERT INTO medicos (id, nome, crm) VALUES " + "(?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, medico.getId());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getCrm());
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public void inserirEnf(UsuarioEnf enferm) {
        String sql = "INSERT INTO enfermeiros (id, nome, coren, telefone) VALUES " + "(?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, enferm.getId());
            stmt.setString(2, enferm.getNome());
            stmt.setString(3, enferm.getCoren());
            stmt.setString(4, enferm.getTelefone());
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public void inserirAtend(UsuarioAtend atend) {
        String sql = "INSERT INTO atendentes (id, nome, cpf, telefone) VALUES " + "(?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, atend.getId());
            stmt.setString(2, atend.getNome());
            stmt.setString(3, atend.getCpf());
            stmt.setString(4, atend.getTelefone());
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public void inserirPac(Paciente paciente) {
        String sql = "INSERT INTO pacientes (id, nome, data_nasc, cpf, telefone, convenio, numero_carteira, ultima_atualizacao) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, paciente.getId());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getDataNasc());
            stmt.setString(4, paciente.getCpf());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getConvenio());
            stmt.setString(7, paciente.getNumCarteira());
            stmt.setString(8, paciente.getUltimAtualizacao());
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public void inserirConsulta(Agendamento agenda) {
        String sql = "INSERT INTO consultas (id, paciente_id, atendente_id, medico_id, data_hora) VALUES " + "(?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, agenda.getId());
            stmt.setInt(2, agenda.getPaciente());
            stmt.setInt(3, agenda.getAtendente());
            stmt.setInt(4, agenda.getMedico());
            stmt.setString(5, agenda.getDataHora());
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public Agendamento getAgenda(String dataHora) {
        String sql = "SELECT * FROM consultas WHERE id = ?";
        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            Agendamento agenda = new Agendamento();

            rs.next();
            agenda.setId(rs.getInt("id"));
            agenda.setPaciente(rs.getInt("paciente_id"));
            agenda.setAtendente(rs.getInt("atendente_id"));
            agenda.setMedico(rs.getInt("medico_id"));
            agenda.setDataHora(rs.getString("data_hora"));

            return agenda;

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }

    }

    public static List<Agendamento> listarAgenda() {
        List<Agendamento> lista = new ArrayList<Agendamento>();

        try {
            conexao conexao = new conexao();
            Connection conn = conexao.getConexao();
            if (conn == null) {
                System.out.println("Erro: Conexão não inicializada!");
            } else {
                System.out.println("Conexão está ativa!");
            }

            String sql = "SELECT * FROM consultas";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                Agendamento a = new Agendamento();

                a.setId(resposta.getInt("id"));
                a.setPaciente(resposta.getInt("paciente_id"));
                a.setAtendente(resposta.getInt("atendente_id"));
                a.setMedico(resposta.getInt("medico_id"));
                a.setDataHora(resposta.getString("data_hora"));
                lista.add(a);
            }
            conexao.desconectar();

        } catch (SQLException e) {
            System.out.println("Erro ao listar os registros do banco de dados!");
        }
        return lista;
    }

    public static Agendamento busca(int id) {
        Agendamento a = new Agendamento();

        try {
            conexao conexao = new conexao();
            Connection conn = conexao.getConexao();
            if (conn == null) {
                System.out.println("Erro: Conexão não inicializada!");
            } else {
                System.out.println("Conexão está ativa!");
            }

            String sql = "SELECT * FROM consultas WHERE id=?";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setInt(1, id);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                a.setId(resposta.getInt("id"));
                a.setPaciente(resposta.getInt("paciente_id"));
                a.setAtendente(resposta.getInt("atendente_id"));
                a.setMedico(resposta.getInt("medico_id"));
                a.setDataHora(resposta.getString("data_hora"));
            }

            conexao.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o registro " + id + " do banco de dados");
        }
        return a;
    }

    public static boolean atualizar(Agendamento a) {
        try {
            conexao conexao = new conexao();
            Connection conn = conexao.getConexao();
            if (conn == null) {
                System.out.println("Erro: Conexão não inicializada!");
            } else {
                System.out.println("Conexão está ativa!");
            }

            String sql = "UPDATE consultas SET paciente_id=?, atendente_id=?, medico_id=?, data_hora=? WHERE id=?";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            consulta.setInt(1, a.getId());
            consulta.setInt(2, a.getPaciente());
            consulta.setInt(3, a.getAtendente());
            consulta.setInt(4, a.getMedico());
            consulta.setString(5, a.getDataHora());

            consulta.execute();

            conexao.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o registro do banco de dados");
            return false;
        }

    }

}
