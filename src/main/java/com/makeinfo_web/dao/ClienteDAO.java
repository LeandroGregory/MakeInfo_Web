package com.makeinfo_web.dao;

import com.makeinfo_web.model.Cliente;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class ClienteDAO extends GenericDAOImpl<Cliente> {

    public ClienteDAO() {
        super(Cliente.class);
    }

    /** Busca clientes cujo nome contenha o texto informado */
    public List<Cliente> buscarPorNome(String nome) {
        EntityManager manager = getManager();
        try {
            return manager.createQuery(
                    "SELECT c FROM cliente c WHERE c.nomeCliente LIKE :nome", Cliente.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } finally {
            liberarConexao();
        }
    }

}
