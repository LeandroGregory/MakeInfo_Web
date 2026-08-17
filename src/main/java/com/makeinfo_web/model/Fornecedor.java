package com.makeinfo_web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author Leandro Gregory 
 */
@Entity(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nomeFornecedor;

    @Column(name = "telefone")
    private String telefoneFornecedor;

    @Column(name = "logradouro")
    private String logradouroFornecedor;

    @Column(name = "numero")
    private int numeroFornecedor;

    @Column(name = "uf")
    private String ufFornecedor;

    public Fornecedor() {
    }

    public Fornecedor(int id, String nomeFornecedor, String telefoneFornecedor, String logradouroFornecedor,
            int numeroFornecedor, String ufFornecedor) {
        this.id = id;
        this.nomeFornecedor = nomeFornecedor;
        this.telefoneFornecedor = telefoneFornecedor;
        this.logradouroFornecedor = logradouroFornecedor;
        this.numeroFornecedor = numeroFornecedor;
        this.ufFornecedor = ufFornecedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getTelefoneFornecedor() {
        return telefoneFornecedor;
    }

    public void setTelefoneFornecedor(String telefoneFornecedor) {
        this.telefoneFornecedor = telefoneFornecedor;
    }

    public String getLogradouroFornecedor() {
        return logradouroFornecedor;
    }

    public void setLogradouroFornecedor(String logradouroFornecedor) {
        this.logradouroFornecedor = logradouroFornecedor;
    }

    public int getNumeroFornecedor() {
        return numeroFornecedor;
    }

    public void setNumeroFornecedor(int numeroFornecedor) {
        this.numeroFornecedor = numeroFornecedor;
    }

    public String getUfFornecedor() {
        return ufFornecedor;
    }

    public void setUfFornecedor(String ufFornecedor) {
        this.ufFornecedor = ufFornecedor;
    }

}
