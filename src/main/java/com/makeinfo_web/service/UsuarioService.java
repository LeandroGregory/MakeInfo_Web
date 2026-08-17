package com.makeinfo_web.service;

import com.makeinfo_web.dao.UsuarioDAO;
import com.makeinfo_web.model.Usuario;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /* Método que autentica um usuário pelo login e senha informados. */
    public Usuario autenticar(String login, String senha) {
        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Informe usuário e senha.");
        }
        return usuarioDAO.buscarPorLoginSenha(login, senha);
    }

    public Usuario cadastrar(Usuario usuario) {
        validar(usuario);
        return usuarioDAO.salvar(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        validar(usuario);
        return usuarioDAO.atualizar(usuario);
    }

    public void excluir(int id) {
        usuarioDAO.excluir(id);
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> buscarPorLogin(String login) {
        return usuarioDAO.buscarPorLogin(login);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    private void validar(Usuario usuario) {
        if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new IllegalArgumentException("O login é obrigatório.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 4) {
            throw new IllegalArgumentException("A senha deve ter ao menos 4 caracteres.");
        }
        if (usuario.getNivel() == null || usuario.getNivel().isBlank()) {
            throw new IllegalArgumentException("O nível de acesso do usuário é obrigatório.");
        }
    }

}
