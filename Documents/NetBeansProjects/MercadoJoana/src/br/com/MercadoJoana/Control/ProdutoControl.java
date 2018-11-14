/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MercadoJoana.Control;

import br.com.MercadoJoana.daoImpl.ProdutoDao;
import br.com.MercadoJoana.uteis.Categoria;
import br.com.MercadoJoana.uteis.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class ProdutoControl {

    private JTextField tfCodigo;
    private JComboBox<String> cbCategoria;
    private JTextField tfNomeProduto;
    private JTextField tfValor;
    private JTextField tfQuantidade;
    private JTextField tfPesquisa;
    private JTable tabelaProduto;

    private List<Produto> listaProduto;
    private Produto produto = null;
    private Categoria categoria = null;
    private ProdutoDao produtoDao;
    private CategoriaControl cateControl;

    public ProdutoControl(JTextField tfCodigo, JComboBox<String> cbCategoria, JTextField tfNomeProduto,
            JTextField tfValor, JTextField tfQuantidade, JTextField tfPesquisa, JTable tabelaProduto) {
        this.tfCodigo = tfCodigo;
        this.cbCategoria = cbCategoria;
        this.tfNomeProduto = tfNomeProduto;
        this.tfValor = tfValor;
        this.tfQuantidade = tfQuantidade;
        this.tfPesquisa = tfPesquisa;
        this.tabelaProduto = tabelaProduto;
        this.listaProduto = new ArrayList<>();
        this.produtoDao = new ProdutoDao();
        this.cateControl = new CategoriaControl();
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void limparCampos() {
        tfCodigo.setText("");
        tfNomeProduto.setText("");
        tfQuantidade.setText("");
        tfValor.setText("");
        cbCategoria.setSelectedIndex(0);
    }

    public Produto produtoSelecionado() {
        try {
            int sele = tabelaProduto.getSelectedRow();
            return listaProduto.get(sele);
        } catch (Exception e) {
            showJOP("Selecione uma produto para edição");
            return null;
        }
    }

    public void SetarCamposProduto() {
        produto = produtoSelecionado();
        if (produto == null) {
            return;
        }
        tfCodigo.setText(produto.getCodigo());
        tfNomeProduto.setText(produto.getNome());
        tfValor.setText(String.valueOf(produto.getValor()));
        tfQuantidade.setText(String.valueOf(produto.getQuantidade()));
        cbCategoria.setSelectedItem(produto.getCategoria());
    }

    private void showTableProdutos() {
        DefaultTableModel model = (DefaultTableModel) tabelaProduto.getModel();
        model.setNumRows(0);
        for (Produto pro : listaProduto) {
            model.addRow(new Object[]{
                pro.getId(),
                pro.getCodigo(),
                pro.getNome(),
                pro.getCategoria().getNome(),
                pro.getValor(),
                pro.getQuantidade(),
                pro.SubTotal()
            });
        }
    }

    public void listarProdutos() {
        listaProduto = produtoDao.listar();
        showTableProdutos();
    }

    public void salvarAction() {
        if (produto == null) {
            cadastrarAction();
        } else {
            editarAction();
        }
        limparCampos();
        listarProdutos();
        produto = null;
        categoria = null;
    }

    public Categoria categoriaComboBox(List<Categoria> lista) {
        int id = cbCategoria.getSelectedIndex();
        categoria = new Categoria();
        categoria = lista.get(id);
        return categoria;
    }

    private void cadastrarAction() {
        Produto pro = new Produto();
        try {
            pro.setCodigo(tfCodigo.getText());
            pro.setNome(tfNomeProduto.getText());
            pro.setCategoria(categoriaComboBox(cateControl.listarCategoriaComboBox()));
            pro.setValorStr(tfValor.getText());
            pro.setQuantidade(tfQuantidade.getText());
            if (produtoDao.salvar(pro)) {
                showJOP("Salvo com sucesso!!");
            } else {
                showJOP("Erro ao salvar!!");
            }
        } catch (Exception e) {
            showJOP("Preencha o cadastro corretamente!!");
        }
        pro = null;
    }

    private void editarAction() {
        produto.setCodigo(tfCodigo.getText());
        produto.setNome(tfNomeProduto.getText());
        produto.setQuantidade(tfQuantidade.getText());
        produto.setValorStr(tfValor.getText());
        produto.setCategoria(categoriaComboBox(cateControl.listarCategoriaComboBox()));
        if (produtoDao.atualizar(produto)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar");
        }

    }

    public void excluirAction() {
        produto = produtoSelecionado();
        if (produto == null) {
            return;
        }
        if (produtoDao.excluir(produto)) {
            showJOP("Excluido com sucesso");
        } else {
            showJOP("Erro ao excluir!!");
        }
        limparCampos();
        listarProdutos();
        produto = null;
    }

    public void pesquisarAction() {
        listaProduto = produtoDao.pesquisaPorNome(tfPesquisa.getText());
        showTableProdutos();
    }
}
