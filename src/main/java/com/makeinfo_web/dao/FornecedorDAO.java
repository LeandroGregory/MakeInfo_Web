package com.makeinfo_web.dao;

import com.makeinfo_web.model.Fornecedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;

/** 
 * @author Leandro Gregory
 * @version 1.0
 */
public class FornecedorDAO extends GenericDAOImpl<Fornecedor> {

    public FornecedorDAO() {
        super(Fornecedor.class);
    }

    /** Busca fornecedores cujo nome contenha o texto informado */
    public List<Fornecedor> buscarPorNome(String nome) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery(
                    "SELECT f FROM fornecedor f WHERE f.nomeFornecedor LIKE :nome", Fornecedor.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } finally {
            liberarConexao();
        }
    }

    /** Lista os nomes de todos os fornecedores cadastrados */
    public List<String> listarNomes() {
        EntityManager manager = getManager();
        try {
            return manager.createQuery("SELECT f.nomeFornecedor FROM fornecedor f", String.class).getResultList();
        } finally {
            liberarConexao();
        }
    }

    /** Busca um fornecedor pelo nome exato */
    public Fornecedor buscarPorNomeExato(String nome) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery(
                    "SELECT f FROM fornecedor f WHERE f.nomeFornecedor = :nome", Fornecedor.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            liberarConexao();
        }
    }

}
