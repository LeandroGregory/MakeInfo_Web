package com.makeinfo_web.service;

import com.makeinfo_web.model.ItemVenda;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testes unitários da regra de cálculo do total de uma venda.
 */
public class VendaServiceTest {

    private final VendaService vendaService = new VendaService();
    
    
    /* Teste que soma a quantidade de itens vezes o valor de cada item */
    @Test
    public void calcularTotalItens_somaCorretaItens() {
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(new ItemVenda(0, 2, 10.0, null, null));  // 2 x 10.0 = 20.0
        itens.add(new ItemVenda(0, 3, 5.5, null, null));   // 3 x 5.5 = 16.5

        double total = vendaService.calcularTotalItens(itens);

        assertEquals(36.5, total, 0.001);
    }
    
    /* Teste que Lanca a Exceção quando a lista estiver vazia */
    @Test
    public void calcularTotalItens_excecaoListaVazia() {
        List<ItemVenda> itens = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            vendaService.calcularTotalItens(itens);
        });
    }
    
    /* Teste que lanca a exceção quando a lista for nula */
    @Test
    public void calcularTotalItens_excecaoListaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            vendaService.calcularTotalItens(null);
        });
    }
}