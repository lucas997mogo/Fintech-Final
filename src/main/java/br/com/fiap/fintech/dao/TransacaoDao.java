package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Transacao;

import java.util.List;

public interface TransacaoDao {

    void depositar(int idConta, double valor) throws DBException;
    void sacar(int idConta, double valor) throws DBException;
    List<Transacao> listarTransacoes() throws DBException;

}
