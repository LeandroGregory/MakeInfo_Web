package com.makeinfo_web.dao;

import com.makeinfo_web.model.Venda;

/**
 * DAO responsável apenas pela persistência de Venda.
 * @author Leandro Gregory
 * @version 1.0
 */
public class VendaDAO extends GenericDAOImpl<Venda> {

    public VendaDAO() {
        super(Venda.class);
    }

}
