/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.CategoriaDao;
import br.com.marmitao.daoImpl.ProdutoDao;
import br.com.marmitao.model.Categoria;
import br.com.marmitao.model.Produto;
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

    private JTextField tfNomeProduto;
    private JTextField tfValor;
    private JTextField tfDescricao;
    private JTextField tfPesquisa;
    private JComboBox<String> cbCategoria;
    private JTable tabelaProduto;

    private Categoria categoria = null;
    private CategoriaDao categoriaDao;
    private CategoriaControl categoriaControl;

    private Produto produto = null;
    private List<Produto> listaProduto;
    private ProdutoDao produtoDao;

    public ProdutoControl() {
        this.listaProduto = new ArrayList<>();
        this.produtoDao = new ProdutoDao();

        this.categoria = new Categoria();
        this.categoriaDao = new CategoriaDao();
        this.categoriaControl = new CategoriaControl();
    }

    public ProdutoControl(JTextField tfNomeProduto, JTextField tfValor, JTextField tfDescricao, JTextField tfPesquisa, JComboBox cbCategoria, JTable tabelaProduto) {
        this.tfNomeProduto = tfNomeProduto;
        this.tfValor = tfValor;
        this.tfDescricao = tfDescricao;
        this.tfPesquisa = tfPesquisa;
        this.cbCategoria = cbCategoria;
        this.tabelaProduto = tabelaProduto;
        this.listaProduto = new ArrayList<>();
        this.produtoDao = new ProdutoDao();
        this.categoria = new Categoria();
        this.categoriaDao = new CategoriaDao();
        this.categoriaControl = new CategoriaControl();
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void listarProduto() {
        listaProduto = produtoDao.listar();
        showTableProduto();
    }

    public void salvarProdutoAction() {
        if (produto == null) {
            cadastrarProdutoAction();
        } else {
            editarProdutoAction();
        }
        limparCamposProduto();
        listarProduto();
        categoria = null;
    }

    public void cadastrarProdutoAction() {
        Produto produto = new Produto();
        if ((!tfNomeProduto.getText().equals("") && !tfValor.getText().equals("")
                && !tfDescricao.getText().equals(""))) {
            produto.setNome(tfNomeProduto.getText());
        } else {
            showJOP("Preencha o campo vazio");
            return;
        }
        if (tfValor.getText().startsWith("-")) {
            showJOP("Digite um número inteiro!!");
            return;
        } else {
            try {
                produto.setValor(Float.valueOf(tfValor.getText()));
            } catch (Exception e) {
                showJOP("Digite somente números!!");
                return;
            }
            produto.setDescricao(tfDescricao.getText());
            produto.setCategoria(categoriaDao.lerPorID(categoriaControl.listarCategoriaCombobox().get(cbCategoria.getSelectedIndex()).getId()));
        }
        if (produtoDao.salvar(produto)) {
            showJOP("Salvo com sucesso");

        } else {
            showJOP("Erro ao salvar");
        }
        produto = null;
    }

    public Produto produtoSelecionado() {
        int sele = tabelaProduto.getSelectedRow();
        return listaProduto.get(sele);
    }

    public void setarCamposProduto() {
        try {
            produto = produtoSelecionado();
            tfNomeProduto.setText(produto.getNome());
            tfValor.setText(String.valueOf(produto.getValor()));
            tfDescricao.setText(produto.getDescricao());
        } catch (Exception e) {
            showJOP("Selecione um Produto!!");
            return;
        }
    }

    public void editarProdutoAction() {
        produto.setNome(tfNomeProduto.getText());
        produto.setValor(Float.valueOf(tfValor.getText()));
        produto.setDescricao(tfDescricao.getText());
        produto.setCategoria(categoriaDao.lerPorID(categoriaControl.listarCategoriaCombobox().get(cbCategoria.getSelectedIndex()).getId()));
        if (produtoDao.atualizar(produto)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar");
        }
        produto = null;
    }

    public void showTableProduto() {
        DefaultTableModel model = (DefaultTableModel) tabelaProduto.getModel();
        model.setNumRows(0);
        for (Produto pro : listaProduto) {
            model.addRow(new Object[]{
                pro.getId(),
                pro.getNome(),
                pro.getValor(),
                pro.getDescricao(),
                pro.getCategoria().getId(),
                pro.getCategoria().getNome()
            });
        }
    }

    public void excluirProdutoAction() {
        try {
            produto = produtoSelecionado();
            if (produtoDao.excluir(produto)) {
                showJOP("Excluido com sucesso!!");
            } else {
                showJOP("Erro ao excluir!!");
            }
        } catch (Exception e) {
            showJOP("Selecione um produto");
            return;
        }
        limparCamposProduto();
        listarProduto();
        produto = null;
    }

    public void pequisarProdutoAction() {
        listaProduto = produtoDao.pesquisarPorNome(tfPesquisa.getText());
        showTableProduto();
    }

    private void limparCamposProduto() {
        tfNomeProduto.setText("");
        tfValor.setText("");
        tfDescricao.setText("");
        cbCategoria.setSelectedIndex(0);
    }

}
