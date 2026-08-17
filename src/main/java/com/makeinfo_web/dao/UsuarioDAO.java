package com.makeinfo_web.dao;

import com.makeinfo_web.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;

/**
 * DAO responsável apenas pela persistência de Usuario. 
 * @author Leandro Gregory
 * @version 1.0
 */
public class UsuarioDAO extends GenericDAOImpl<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    /** Busca um usuário por login e senha (usado apenas internamente pelo serviço de login) */
    public Usuario buscarPorLoginSenha(String login, String senha) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery(
                    "SELECT u FROM usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class)
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            liberarConexao();
        }
    }

    /** Busca usuários cujo login contenha o texto informado */
    public List<Usuario> buscarPorLogin(String login) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery(
                    "SELECT u FROM usuario u WHERE u.login LIKE :login", Usuario.class)
                    .setParameter("login", "%" + login + "%")
                    .getResultList();
        } finally {
            liberarConexao();
        }
    }

}
