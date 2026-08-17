package com.makeinfo_web.service;

import com.makeinfo_web.dao.ProdutoDAO;
import com.makeinfo_web.model.Produto;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class ProdutoService {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public Produto cadastrar(Produto produto) {
        validar(produto);
        return produtoDAO.salvar(produto);
    }

    public Produto atualizar(Produto produto) {
        validar(produto);
        return produtoDAO.atualizar(produto);
    }

    public void excluir(int id) {
        produtoDAO.excluir(id);
    }

    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoDAO.buscarPorNome(nome);
    }

    public List<Produto> listarTodos() {
        return produtoDAO.listarTodos();
    }

    /* Método que verifica se há saldo em estoque para a quantidade solicitada.*/
    
    public void baixarEstoque(int idProduto, int quantidade) {
        int estoqueAtual = produtoDAO.getQuantidadeEstoque(idProduto);

        if (estoqueAtual < 0) {
            throw new IllegalArgumentException("Produto com ID " + idProduto + " não encontrado no estoque.");
        }
        if (estoqueAtual < quantidade) {
            throw new IllegalArgumentException(
                    "Estoque insuficiente para o produto ID " + idProduto
                    + ". Disponível: " + estoqueAtual + " | Solicitado: " + quantidade);
        }

        produtoDAO.decrementarEstoque(idProduto, quantidade);
    }
    
    /* Método que verifica se os campos de nome do produto, quantidade de estoque e fornecedor estão preenchidos*/
    
    private void validar(Produto produto) {
        if (produto.getNomeProduto() == null || produto.getNomeProduto().isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (produto.getQuantidadeProduto() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        if (produto.getFornecedor() == null) {
            throw new IllegalArgumentException("É necessário informar o fornecedor do produto.");
        }
    }

}
