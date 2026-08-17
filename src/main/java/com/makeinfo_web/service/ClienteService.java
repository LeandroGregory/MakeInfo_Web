package com.makeinfo_web.service;

import com.makeinfo_web.dao.ClienteDAO;
import com.makeinfo_web.model.Cliente;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public Cliente cadastrar(Cliente cliente) {
        validar(cliente);
        return clienteDAO.salvar(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        validar(cliente);
        return clienteDAO.atualizar(cliente);
    }

    public void excluir(int id) {
        clienteDAO.excluir(id);
    }

    public Cliente buscarPorId(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteDAO.buscarPorNome(nome);
    }

    public List<Cliente> listarTodos() {
        return clienteDAO.listarTodos();
    }

    private void validar(Cliente cliente) {
        if (cliente.getNomeCliente() == null || cliente.getNomeCliente().isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (cliente.getCpfCliente() == null || !cliente.getCpfCliente().matches("\\d{11}")) {
            throw new IllegalArgumentException("Informe um CPF válido com 11 dígitos.");
        }
    }

}
