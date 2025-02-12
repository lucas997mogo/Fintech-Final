package br.com.fiap.fintech.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.EnderecoDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleEnderecoDao implements EnderecoDao {

    private Connection conexao;

    @Override
    public void cadastrarEndereco(Endereco endereco) throws DBException {

        PreparedStatement stmt = null;

        conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO endereco " + "(id_endereco, pais, estado, cidade, cep, logradouro, cliente_id_cliente) "
                + "VALUES (SQ_endereco.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, endereco.getPais());
            stmt.setString(2, endereco.getEstado());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getLogradouro());
            stmt.setInt(6, endereco.getIdCliente().getIdCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar o endereço", e);
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizarEndereco(Endereco endereco) throws DBException {

        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE endereco SET " + "pais = ?, " + "estado = ?, " + "cidade = ?, " + "cep = ?, " + "logradouro = ? WHERE id_endereco = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, endereco.getPais());
            stmt.setString(2, endereco.getEstado());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getLogradouro());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar o endereço", e);
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void removerEndereco(int id) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM endereco WHERE id_endereco = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erro ao remover o endereço", e);
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Endereco buscarEndereco(int id) throws DBException {

        Endereco endereco = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        // testar sintaxe sql:
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM endereco " +
                    "INNER JOIN cliente " +
                    "ON endereco.id_cliente = cliente.id_cliente " +
                    "WHERE id_endereco = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idEndereco = rs.getInt("id_endereco");
                String pais = rs.getString("pais");
                String estado = rs.getString("estado");
                String cidade = rs.getString("cidade");
                String cep = rs.getString("cep");
                String logradouro = rs.getString("logradouro");

                endereco = new Endereco(idEndereco, pais, estado, cidade, cep, logradouro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return endereco;
    }

    @Override
    public List<Endereco> listarEndereco() throws DBException {

        List<Endereco> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM endereco " +
                    "INNER JOIN cliente " +
                    "ON endereco.id_cliente = cliente.id_cliente ";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idEndereco = rs.getInt("id_endereco");
                String pais = rs.getString("pais");
                String estado = rs.getString("estado");
                String cidade = rs.getString("cidade");
                String cep = rs.getString("cep");
                String logradouro = rs.getString("logradouro");

                Endereco endereco = new Endereco(idEndereco, pais, estado, cidade, cep, logradouro);
                lista.add(endereco);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}