package com.makeinfo_web.service;

import com.makeinfo_web.dao.ItemVendaDAO;
import com.makeinfo_web.dao.VendaDAO;
import com.makeinfo_web.model.ItemVenda;
import com.makeinfo_web.model.Venda;
import java.util.List;

/**
 * Camada de regras de negócio de Venda. 
 * @author Leandro Gregory
 * @version 1.0
 */
public class VendaService {

    private final VendaDAO vendaDAO = new VendaDAO();
    private final ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
    private final ProdutoService produtoService = new ProdutoService();

    /**
     * Método de registro de uma venda que faz os seguintes passos:
     * 1) valida e desconta o estoque de cada item.
     * 2) calcula o total da venda a partir dos itens.
     * 3) grava a venda e em seguida os itens já vinculados a ela.
     */
    public Venda registrarVendaComItens(Venda venda, List<ItemVenda> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Uma venda precisa ter ao menos um item.");
        }

        double total = 0;
        for (ItemVenda item : itens) {
            produtoService.baixarEstoque(item.getProduto().getId(), item.getQuantidade());
            total += item.getQuantidade() * item.getValorUnitario();
        }
        venda.setTotalVenda(total);

        Venda vendaSalva = vendaDAO.salvar(venda);

        for (ItemVenda item : itens) {
            item.setVenda(vendaSalva);
            itemVendaDAO.salvar(item);
        }

        return vendaSalva;
    }

    public List<Venda> listarTodas() {
        return vendaDAO.listarTodos();
    }

    public Venda buscarPorId(int id) {
        return vendaDAO.buscarPorId(id);
    }

    public List<ItemVenda> listarItens() {
        return itemVendaDAO.listarTodos();
    }

}
