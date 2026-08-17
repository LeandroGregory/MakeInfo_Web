package com.makeinfo_web.dao;

import com.makeinfo_web.model.Funcionario;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class FuncionarioDAO extends GenericDAOImpl<Funcionario> {

    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    /** Busca funcionários cujo nome contenha o texto informado */
    public List<Funcionario> buscarPorNome(String nome) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery(
                    "SELECT f FROM funcionario f WHERE f.nomeFuncionario LIKE :nome", Funcionario.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } finally {
            liberarConexao();
        }
    }

}
