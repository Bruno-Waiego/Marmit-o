/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.model;

/**
 *
 * @author Bruno
 */
public class Entregador {
    private Integer id;
    private String nome;
    private String telefone;
    private String cnh;

    public Entregador() {
    }

    public Entregador(Integer id, String nome, String telefone, String cnh) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cnh = cnh;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
