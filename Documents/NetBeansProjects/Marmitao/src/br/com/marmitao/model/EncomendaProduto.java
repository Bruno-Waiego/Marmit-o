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
public class EncomendaProduto {

    private Integer id;
    private int quantidade;
    private float valor;
    private Produto produto;
    private Encomenda encomenda;

    public EncomendaProduto() {
        this.encomenda = new Encomenda();
        this.produto = new Produto();
    }

    public EncomendaProduto(Integer id, int quantidade, float valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.encomenda = new Encomenda();
        this.produto = new Produto();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setQuantidadeSTR(String quantidade) {
        setQuantidade(Integer.valueOf(quantidade));
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setValorSTR(String valor) {
        setValor(Float.valueOf(valor));
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    @Override
    public String toString() {
        return "Pedido:\n "
                + "ID Encomenda: "
                + encomenda.getId()
                + "\n Encomenda Data de Pedido: "
                + encomenda.getDataPedido()
                + "\n Encomenda Data de Entrega: "
                + encomenda.getDataEntrega()
                + "\n Encomenda Status: "
                + encomenda.getStatus()
                + "\n Cliente ID: "
                + encomenda.getCliente().getId()
                + "\n Cliente Nome: "
                + encomenda.getCliente().getNome()
                + "\nData de Nascimento: "
                + encomenda.getCliente().getDataNascimento()
                + "\n Cliente Telefone: "
                + encomenda.getCliente().getTelefone()
                + "\n Cliente Email: "
                + encomenda.getCliente().getEmail()
                + "\n Endereco ID: "
                + encomenda.getEndereco().getId()
                + "\n Logradouro: "
                + encomenda.getEndereco().getLogradouro()
                + "\n Bairro: "
                + encomenda.getEndereco().getBairro()
                + "\n Cidade: "
                + encomenda.getEndereco().getCidade()
                + "\n CEP: "
                + encomenda.getEndereco().getCep()
                + "\n Complemento: "
                + encomenda.getEndereco().getComplemento()
                + "\n Entregador ID: "
                + encomenda.getEntregador().getId()
                + "\n Nome: "
                + encomenda.getEntregador().getNome()
                + "\n CNH: "
                + encomenda.getEntregador().getCnh()
                + "\n Telefone: "
                + encomenda.getEntregador().getTelefone()
                + "\nProduto ID: "
                + produto.getId()
                + "\nProduto Nome: "
                + produto.getNome()
                + "\nProduto Valor: "
                + produto.getValor()
                + "\nProduto Categoria ID: "
                + produto.getCategoria().getId()
                + "\nProduto Categoria Nome: "
                + produto.getCategoria().getNome()
                + "\nID Encomenda Produto: "
                + this.id
                + "\nValor Encomenda: "
                + this.valor
                + "\nQuantidade Encomenda: "
                + this.quantidade;
    }

}
