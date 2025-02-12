package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.*;
import br.com.fiap.fintech.impl.*;

public class DaoFactory {

    public static ClienteDao getClienteDao() {
        return new OracleClienteDao();
    }

    public static ContaDao getContaDao() {
        return new OracleContaDao();
    }

    public static TransacaoDao getTransacaoDao() {
        return new OracleTransacaoDao();
    }

    public static EnderecoDao getEnderecoDao() {
        return new OracleEnderecoDao();
    }

    public static TelefoneDao getTelefoneDao() {
        return new OracleTelefoneDao();
    }
    public static UsuarioDao getUsuarioDAO() {
        return new OracleUsuarioDao();
    }
}
