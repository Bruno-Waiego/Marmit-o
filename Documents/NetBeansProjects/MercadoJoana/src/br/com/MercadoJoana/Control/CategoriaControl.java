/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MercadoJoana.Control;

import br.com.MercadoJoana.daoImpl.CategoriaDao;
import br.com.MercadoJoana.uteis.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class CategoriaControl {

    private JTextField tfNomeCategoria;
    private JTextField tfPesquisa;
    private JTable tabelaCategoria;
    private Categoria categoria = null;
    protected List<Categoria> listaCategoria;
    private CategoriaDao categoriaDao;

    public CategoriaControl() {
        this.categoriaDao = new CategoriaDao();
        this.listaCategoria = new ArrayList<>();
    }

    public CategoriaControl(JTextField tfNomeCategoria, JTable tabelaCategoria, JTextField tfPesquisa) {
        this.tfNomeCategoria = tfNomeCategoria;
        this.tabelaCategoria = tabelaCategoria;
        this.tfPesquisa = tfPesquisa;
        this.categoriaDao = new CategoriaDao();
        this.listaCategoria = new ArrayList<>();
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void listarCategoria() {
        listaCategoria = categoriaDao.listar();
        showTableCategoria();
    }

    public List<Categoria> listarCategoriaComboBox() {
        listaCategoria = categoriaDao.listar();
        return listaCategoria;
    }

    public void salvar() {
        if (categoria == null) {
            cadastrarAction();
        } else {
            editarAction();
        }
        limparCampos();
        listarCategoria();
    }

    public void cadastrarAction() {
        categoria = new Categoria();
        if (tfNomeCategoria.getText().equals("")) {
            showJOP("Preencha o campo vazio");
            return;
        }
        categoria.setNome(tfNomeCategoria.getText());
        if (categoriaDao.salvar(categoria)) {
            showJOP("Salvo com sucesso");
        } else {
            showJOP("Erro ao salvar");
        }
        categoria = null;
    }

    public Categoria categoriaSelecionado() {
        int sele = tabelaCategoria.getSelectedRow();
        return listaCategoria.get(sele);
    }

    public void SetarCamposCategoria() {
        try {
            categoria = categoriaSelecionado();
            tfNomeCategoria.setText(categoria.getNome());
        } catch (Exception e) {
            showJOP("Selecione uma categoria!!");
            return;
        }
    }

    public void editarAction() {
        categoria.setNome(tfNomeCategoria.getText());
        if (categoriaDao.atualizar(categoria)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar");
        }
        categoria = null;
    }

    public void showTableCategoria() {
        DefaultTableModel model = (DefaultTableModel) tabelaCategoria.getModel();
        model.setNumRows(0);
        for (Categoria cate : listaCategoria) {
            model.addRow(new Object[]{
                cate.getId(),
                cate.getNome()

            });
        }
    }

    public void excluirAction() {
        try {
            categoria = categoriaSelecionado();
            if (categoriaDao.excluir(categoria)) {
                showJOP("Excluido com sucesso!!");
            } else {
                showJOP("Erro ao excluir!!");
            }
        } catch (Exception e) {
            showJOP("Selecione uma categoria");
            return;
        }
        limparCampos();
        listarCategoria();
        categoria = null;
    }

    public void pequisarAction() {
        listaCategoria = categoriaDao.pesquisaPorNome(tfPesquisa.getText());
        showTableCategoria();
    }

    private void limparCampos() {
        tfNomeCategoria.setText("");
    }

}
