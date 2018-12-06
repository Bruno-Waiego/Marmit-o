/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.model;

import java.sql.Date;

/**
 *
 * @author Bruno
 */
public class Encomenda {

    private Integer id;
    private Date dataPedido;
    private Date dataEntrega;
    private int status;
    private Entregador entregador;
    private Cliente cliente;
    private Endereco endereco;

    public Encomenda() {
        this.entregador = new Entregador();
        this.cliente = new Cliente();
        this.endereco = new Endereco();
    }

    public Encomenda(Integer id, Date dataPedido, Date dataEntrega, int status, String entregador) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.entregador = new Entregador();
        this.cliente = new Cliente();
        this.endereco = new Endereco();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "ID= " + id + ", ID Cliente= " + cliente.getId() + ", cliente= " + cliente.getNome()
                + ", endereco= " + endereco.getLogradouro();
    }

}
