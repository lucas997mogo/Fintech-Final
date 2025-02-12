package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Endereco;

import java.util.List;

public interface EnderecoDao {

    void cadastrarEndereco(Endereco endereco) throws DBException;
    void atualizarEndereco(Endereco endereco) throws DBException;
    void removerEndereco (int id) throws DBException;
    Endereco buscarEndereco(int id) throws DBException;
    List<Endereco> listarEndereco() throws DBException;

}
