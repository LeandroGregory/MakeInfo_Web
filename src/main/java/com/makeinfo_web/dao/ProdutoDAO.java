package com.makeinfo_web.dao;

import com.makeinfo_web.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * DAO responsável apenas pela persistência de Produto.
 * @author Leandro Gregory
 * @version 1.0
 */
public class ProdutoDAO extends GenericDAOImpl<Produto> {

    public ProdutoDAO() {
        super(Produto.class);
    }

    /** Busca produtos cujo nome contenha o texto informado */
    public List<Produto> buscarPorNome(String nome) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery(
                    "SELECT p FROM produto p WHERE p.nomeProduto LIKE :nome", Produto.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } finally {
            liberarConexao();
        }
    }

    /** Retorna a quantidade atual em estoque; -1 se o produto não existir */
    public int getQuantidadeEstoque(int idProduto) {
        EntityManager manager = getManager();
        try {
            Produto produto = manager.find(Produto.class, idProduto);
            return produto != null ? produto.getQuantidadeProduto() : -1;
        } finally {
            liberarConexao();
        }
    }

    /**
     * Operação mecânica de decremento de estoque.
     */
    public void decrementarEstoque(int idProduto, int quantidade) {
        EntityManager manager = getManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            Produto produto = manager.find(Produto.class, idProduto);
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidade);
            manager.merge(produto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            liberarConexao();
        }
    }

}
