/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MercadoJoana.uteis;

/**
 *
 * @author Bruno
 */
public class Produto {

    private int id;
    private String codigo;
    private String nome;
    private int quantidade;
    private double valor;
    private Categoria categoria;

    public Produto() {
        categoria = new Categoria();
    }

    public Produto(int id, String codigo, String nome, int quantidade, double valor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoria = categoria;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = Integer.parseInt(quantidade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setValorStr(String valor) {
        setValor(Double.valueOf(valor));

    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double SubTotal() {
        return this.valor * quantidade;
    }
}
