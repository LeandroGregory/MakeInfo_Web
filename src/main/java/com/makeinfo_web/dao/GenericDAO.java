package com.makeinfo_web.dao;

import java.util.List;

/**
 * Interface genérica de CRUD
 * @author Leandro Gregory
 * @version 1.0
 */
public interface GenericDAO<T> {

    T salvar(T entidade);

    T atualizar(T entidade);

    void excluir(int id);

    T buscarPorId(int id);

    List<T> listarTodos();

}
