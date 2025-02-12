package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Telefone;

import java.util.List;

public interface TelefoneDao {

    void cadastrarTelefone(Telefone telefone) throws DBException;
    void atualizarTelefone(Telefone telefone) throws DBException;
    void removerTelefone (int id) throws DBException;
    Telefone buscarTelefone(int id) throws DBException;
    List<Telefone> listarTelefone() throws DBException;

}
