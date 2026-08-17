package com.makeinfo_web.dao;

import com.makeinfo_web.model.ItemVenda;

/** 
 * DAO responsável apenas pela persistência de ItemVenda. *
 * @author Leandro Gregory
 * @version 1.0
 */
public class ItemVendaDAO extends GenericDAOImpl<ItemVenda> {

    public ItemVendaDAO() {
        super(ItemVenda.class);
    }

}
