package DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgendamentoDAO {
    private Connection conexao;

    public AgendamentoDAO() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
    }

    /**
     * Busca o ID do cliente pelo nome.
     * @param nome Nome do cliente
     * @return ID do cliente ou -1 caso não seja encontrado
     * @throws SQLException Caso ocorra um erro na consulta
     */
    public int buscarClienteId(String nome) throws SQLException {
        String sql = "SELECT id FROM Clientes WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1; // Retorna -1 caso o cliente não seja encontrado
    }

    /**
     * Adiciona um agendamento ao banco de dados.
     * @param clienteId ID do cliente
     * @param data Data do agendamento
     * @param hora Hora do agendamento
     * @param tipoServico Tipo de serviço
     * @param observacao Observações ou trabalho realizado
     * @throws SQLException Caso ocorra um erro ao salvar os dados
     */
    public void adicionarAgendamento(int clienteId, String data, String hora, String tipoServico, String observacao) throws SQLException {
        String sql = "INSERT INTO Agendamentos (cliente_id, data, hora, tipo_servico, observacao) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.setString(2, data);
            stmt.setString(3, hora);
            stmt.setString(4, tipoServico);
            stmt.setString(5, observacao);
            stmt.executeUpdate();
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     * @throws SQLException Caso ocorra um erro ao fechar a conexão
     */
    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
