package com.makeinfo_web.service;

import com.makeinfo_web.dao.CargoDAO;
import com.makeinfo_web.model.Cargo;
import java.util.List;

/**
 * @author Leandro Gregory
 * @version 1.0
 */
public class CargoService {

    private final CargoDAO cargoDAO = new CargoDAO();

    public Cargo cadastrar(String descricao, double salario, int comissao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do cargo é obrigatória.");
        }
        if (salario < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo.");
        }

        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargo.setSalario(salario);
        cargo.setComissao(comissao);

        return cargoDAO.salvar(cargo);
    }

    public Cargo buscarPorId(int id) {
        return cargoDAO.buscarPorId(id);
    }

    public Cargo buscarPorDescricao(String descricao) {
        return cargoDAO.buscarPorDescricao(descricao);
    }

    public List<Cargo> listarTodos() {
        return cargoDAO.listarTodos();
    }

}
