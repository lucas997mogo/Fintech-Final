package br.com.fiap.fintech.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.TelefoneDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleTelefoneDao implements TelefoneDao {

    private Connection conexao;

    @Override
    public void cadastrarTelefone(Telefone telefone) throws DBException {

        PreparedStatement stmt = null;

        conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO telefone " + "(id_telefone, ddi, ddd, numero, tipo, cliente_id_cliente) "
                + "VALUES (SQ_telefone.NEXTVAL, ?, ?, ?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, telefone.getDdi());
            stmt.setString(2, telefone.getDdd());
            stmt.setString(3, telefone.getNumero());
            stmt.setString(4, telefone.getTipo());
            stmt.setInt(4, telefone.getIdCliente().getIdCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar o telefone", e);
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizarTelefone(Telefone telefone) throws DBException {

        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE telefone SET " + "ddi = ?, " + "ddd = ?, " + "numero = ?, " + "tipo = ? WHERE id_conta = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, telefone.getDdi());
            stmt.setString(2, telefone.getDdd());
            stmt.setString(3, telefone.getNumero());
            stmt.setString(4, telefone.getTipo());


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar o telefone", e);
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
    public void removerTelefone(int id) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM telefone WHERE id_telefone = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erro ao remover o telefone", e);
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
    public Telefone buscarTelefone(int id) throws DBException {

        Telefone telefone = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        // testar sintaxe sql:
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM telefone " +
                    "INNER JOIN cliente " +
                    "ON telefone.id_cliente = cliente.id_cliente " +
                    "WHERE id_telefone = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idConta = rs.getInt("id_conta");
                String ddi = rs.getString("ddi");
                String ddd = rs.getString("ddd");
                String numero = rs.getString("numero");
                String tipo = rs.getString("tipo");
                int idCliente = rs.getInt("id_cliente");

                telefone = new Telefone(idConta, ddi, ddd, numero, tipo, idCliente);
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
        return telefone;
    }

    @Override
    public List<Telefone> listarTelefone() throws DBException {

        List<Telefone> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM telefone " +
                    "INNER JOIN cliente " +
                    "ON telefone.id_cliente = cliente.id_cliente ";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idConta = rs.getInt("id_conta");
                String ddi = rs.getString("ddi");
                String ddd = rs.getString("ddd");
                String numero = rs.getString("numero");
                String tipo = rs.getString("tipo");
                int idCliente = rs.getInt("id_cliente");

                Telefone telefone = new Telefone(idConta, ddi, ddd, numero, tipo, idCliente);
                lista.add(telefone);


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