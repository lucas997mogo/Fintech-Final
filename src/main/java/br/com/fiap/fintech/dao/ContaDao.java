package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Conta;

import java.util.List;

public interface ContaDao {

    void cadastrarConta(Conta conta) throws DBException;

    void atualizarConta(Conta conta) throws DBException;

    void removerConta(int id) throws DBException;

    Conta buscarConta(int id) throws DBException;

    List<Conta> listarContas(int idCliente) throws DBException;
}
