package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private ConectorBD conectorBD;
    private SequenceManager sequenceManager;

    public PessoaFisicaDAO() {
        conectorBD = ConectorBD.getInstance();
        sequenceManager = new SequenceManager();
    }

    public PessoaFisica getPessoa(int id) {
        try {
            String sql = "SELECT pessoaFisicaID, nome, logradouro, cidade, estado, telefone, email, cpf FROM pessoa p INNER JOIN pessoaFisica pf on p.pessoaID = pf.pessoaFisicaID WHERE pessoaFisicaID = ?";
            PreparedStatement stmt = conectorBD.getPrepared(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica(
                    rs.getInt("pessoaFisicaID"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                return pessoaFisica;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar a pessoa
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();

        try {
            String sql = "SELECT pessoaFisicaID, nome, logradouro, cidade, estado, telefone, email, cpf FROM pessoa p INNER JOIN pessoaFisica pf on p.pessoaID = pf.pessoaFisicaID";
            ResultSet rs = conectorBD.getSelect(sql);

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica(
                    rs.getInt("pessoaFisicaID"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                pessoas.add(pessoaFisica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public PessoaFisica incluir(PessoaFisica pessoaFisica) {
        try {
            String sqlPessoa = "INSERT INTO pessoa (pessoaID, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conectorBD.getPrepared(sqlPessoa);
            int pessoaID = SequenceManager.getValue("Seq_PessoaID");

            stmt.setInt(1, pessoaID);
            stmt.setString(2, pessoaFisica.getNome());
            stmt.setString(3, pessoaFisica.getLogradouro());
            stmt.setString(4, pessoaFisica.getCidade());
            stmt.setString(5, pessoaFisica.getEstado());
            stmt.setString(6, pessoaFisica.getTelefone());
            stmt.setString(7, pessoaFisica.getEmail());
            stmt.executeUpdate();

            String sqlPessoaFisica = "INSERT INTO pessoaFisica (pessoaFisicaID, cpf) VALUES (?, ?)";
            PreparedStatement stmt2 = conectorBD.getPrepared(sqlPessoaFisica);
            stmt2.setInt(1, pessoaID);
            stmt2.setString(2, pessoaFisica.getCpf());
            stmt2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PessoaFisica alterar(PessoaFisica pessoaFisica) {
        try {
            String sqlPessoa = "UPDATE pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE pessoaID = ?";
            PreparedStatement stmt = conectorBD.getPrepared(sqlPessoa);

            stmt.setString(1, pessoaFisica.getNome());
            stmt.setString(2, pessoaFisica.getLogradouro());
            stmt.setString(3, pessoaFisica.getCidade());
            stmt.setString(4, pessoaFisica.getEstado());
            stmt.setString(5, pessoaFisica.getTelefone());
            stmt.setString(6, pessoaFisica.getEmail());
            stmt.setInt(7, pessoaFisica.getPessoaID());
            stmt.executeUpdate();

            String sqlPessoaFisica = "UPDATE pessoaFisica SET cpf = ? WHERE pessoaFisicaID = ?";
            PreparedStatement stmt2 = conectorBD.getPrepared(sqlPessoaFisica);
            stmt2.setString(1, pessoaFisica.getCpf());
            stmt2.setInt(2, pessoaFisica.getPessoaID());
            stmt2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PessoaFisica excluir(PessoaFisica pessoaFisica) {
        try {
            String sqlPessoaFisica = "DELETE FROM pessoaFisica WHERE pessoaFisicaID = ?";
            PreparedStatement stmt = conectorBD.getPrepared(sqlPessoaFisica);
            stmt.setInt(1, pessoaFisica.getPessoaID());
            stmt.executeUpdate();

            String sqlPessoa = "DELETE FROM pessoa WHERE pessoaID = ?";
            PreparedStatement stmt2 = conectorBD.getPrepared(sqlPessoa);
            stmt2.setInt(1, pessoaFisica.getPessoaID());
            stmt2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
