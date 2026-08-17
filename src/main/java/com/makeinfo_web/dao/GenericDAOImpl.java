package com.makeinfo_web.dao;

import com.makeinfo_web.connection.MakeInfoUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Classe genérica coma a lógica de conexão, transação e tratamento de erro do CRUD
 * @author Leandro Gregory
 * @version 1.0
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private final Class<T> tipo;

    protected GenericDAOImpl(Class<T> tipo) {
        this.tipo = tipo;
    }

    @Override
    public T salvar(T entidade) {
        EntityManager manager = MakeInfoUtil.conectar();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.persist(entidade);
            tx.commit();
            return entidade;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Erro ao salvar " + tipo.getSimpleName() + ": " + e.getMessage());
            return null;
        } finally {
            MakeInfoUtil.desconectar();
        }
    }

    @Override
    public T atualizar(T entidade) {
        EntityManager manager = MakeInfoUtil.conectar();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            T atualizado = manager.merge(entidade);
            tx.commit();
            return atualizado;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Erro ao atualizar " + tipo.getSimpleName() + ": " + e.getMessage());
            return null;
        } finally {
            MakeInfoUtil.desconectar();
        }
    }

    @Override
    public void excluir(int id) {
        EntityManager manager = MakeInfoUtil.conectar();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            T entidade = manager.find(tipo, id);
            if (entidade != null) {
                manager.remove(entidade);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Erro ao excluir " + tipo.getSimpleName() + ": " + e.getMessage());
        } finally {
            MakeInfoUtil.desconectar();
        }
    }

    @Override
    public T buscarPorId(int id) {
        EntityManager manager = MakeInfoUtil.conectar();
        try {
            return manager.find(tipo, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar " + tipo.getSimpleName() + " por id: " + e.getMessage());
            return null;
        } finally {
            MakeInfoUtil.desconectar();
        }
    }

    
       @Override
    public List<T> listarTodos() {
        EntityManager manager = MakeInfoUtil.conectar();
        try {
   
            CriteriaQuery<T> criteria = manager.getCriteriaBuilder().createQuery(tipo);
            criteria.select(criteria.from(tipo));
            return manager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar " + tipo.getSimpleName() + ": " + e.getMessage());
            return List.of();
        } finally {
            MakeInfoUtil.desconectar();
        }
    }
  
    protected EntityManager getManager() {
        return MakeInfoUtil.conectar();
    }

    protected void liberarConexao() {
        MakeInfoUtil.desconectar();
    }

}

