package com.makeinfo_web.service;

import com.makeinfo_web.dao.FuncionarioDAO;
import com.makeinfo_web.model.Funcionario;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class FuncionarioService {

    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public Funcionario cadastrar(Funcionario funcionario) {
        validar(funcionario);
        return funcionarioDAO.salvar(funcionario);
    }

    public Funcionario atualizar(Funcionario funcionario) {
        validar(funcionario);
        return funcionarioDAO.atualizar(funcionario);
    }

    public void excluir(int id) {
        funcionarioDAO.excluir(id);
    }

    public Funcionario buscarPorId(int id) {
        return funcionarioDAO.buscarPorId(id);
    }

    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioDAO.buscarPorNome(nome);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioDAO.listarTodos();
    }

    private void validar(Funcionario funcionario) {
        if (funcionario.getNomeFuncionario() == null || funcionario.getNomeFuncionario().isBlank()) {
            throw new IllegalArgumentException("O nome do funcionário é obrigatório.");
        }
        if (funcionario.getCargo() == null) {
            throw new IllegalArgumentException("É necessário informar o cargo do funcionário.");
        }
    }

}
