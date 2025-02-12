package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Cliente;

import java.util.List;

public interface ClienteDao {

    void cadastrarCliente(Cliente cliente) throws DBException;
    void atualizarCliente(Cliente cliente) throws DBException;
    void removerCliente (int id) throws DBException;
    Cliente buscarCliente(int id) throws DBException;
    List<Cliente> listarCliente() throws DBException;

}
