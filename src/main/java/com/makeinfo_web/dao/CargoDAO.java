package com.makeinfo_web.dao;

import com.makeinfo_web.model.Cargo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class CargoDAO extends GenericDAOImpl<Cargo> {

    public CargoDAO() {
        super(Cargo.class);
    }

    /** Lista as descrições de todos os cargos cadastrados */
    public List<String> listarDescricoes() {
        EntityManager manager = getManager();
        try {
            return manager.createQuery("SELECT c.descricao FROM cargo c", String.class).getResultList();
        } finally {
            liberarConexao();
        }
    }

    /** Busca um cargo pela descrição */
    public Cargo buscarPorDescricao(String descricao) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery("SELECT c FROM cargo c WHERE c.descricao = :descricao", Cargo.class)
                    .setParameter("descricao", descricao)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            liberarConexao();
        }
    }

}
