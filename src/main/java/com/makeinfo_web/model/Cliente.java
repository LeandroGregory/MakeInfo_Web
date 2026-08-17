package com.makeinfo_web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author Leandro Gregory
 */
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nomeCliente;

    @Column(name = "cpf")
    private String cpfCliente;

    @Column(name = "logradouro")
    private String logradouroCliente;

    @Column(name = "numero")
    private int numEnderecoCliente;

    @Column(name = "telefone")
    private String telefoneCliente;

    public Cliente() {
    }

    public Cliente(int id, String nomeCliente, String cpfCliente, String logradouroCliente,
            int numEnderecoCliente, String telefoneCliente) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.logradouroCliente = logradouroCliente;
        this.numEnderecoCliente = numEnderecoCliente;
        this.telefoneCliente = telefoneCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getLogradouroCliente() {
        return logradouroCliente;
    }

    public void setLogradouroCliente(String logradouroCliente) {
        this.logradouroCliente = logradouroCliente;
    }

    public int getNumEnderecoCliente() {
        return numEnderecoCliente;
    }

    public void setNumEnderecoCliente(int numEnderecoCliente) {
        this.numEnderecoCliente = numEnderecoCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

}
