package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Usuario;

public interface UsuarioDao {

    boolean validarUsuario(Usuario usuario);

}