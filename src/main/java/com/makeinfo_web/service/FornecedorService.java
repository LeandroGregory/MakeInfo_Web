package com.makeinfo_web.service;

import com.makeinfo_web.dao.FornecedorDAO;
import com.makeinfo_web.model.Fornecedor;
import java.util.List;

/** 
 * @author Leandro Gregory
 * @version 1.0
 */
public class FornecedorService {

    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public Fornecedor cadastrar(Fornecedor fornecedor) {
        validar(fornecedor);
        return fornecedorDAO.salvar(fornecedor);
    }

    public Fornecedor atualizar(Fornecedor fornecedor) {
        validar(fornecedor);
        return fornecedorDAO.atualizar(fornecedor);
    }

    public void excluir(int id) {
        fornecedorDAO.excluir(id);
    }

    public Fornecedor buscarPorId(int id) {
        return fornecedorDAO.buscarPorId(id);
    }

    public List<Fornecedor> buscarPorNome(String nome) {
        return fornecedorDAO.buscarPorNome(nome);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorDAO.listarTodos();
    }

    private void validar(Fornecedor fornecedor) {
        if (fornecedor.getNomeFornecedor() == null || fornecedor.getNomeFornecedor().isBlank()) {
            throw new IllegalArgumentException("O nome do fornecedor é obrigatório.");
        }
        if (fornecedor.getUfFornecedor() == null || fornecedor.getUfFornecedor().length() != 2) {
            throw new IllegalArgumentException("Informe a UF do fornecedor com 2 letras.");
        }
    }

}
