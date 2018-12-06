/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.EntregadorDao;
import br.com.marmitao.model.Cliente;
import br.com.marmitao.model.Entregador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class EntregadorControl {

    private boolean verificacao = false;
    private JTextField tfNomeEntregador;
    private JTextField tfTelefoneEntregador;
    private JTextField tfCnh;
    private JTextField tfPesquisa;
    private JTable tabelaEntregador;

    private Entregador entregador = null;
    private List<Entregador> listaEntregador;
    private EntregadorDao entregadorDao;

    public EntregadorControl() {
        this.listaEntregador = new ArrayList<>();
        this.entregadorDao = new EntregadorDao();
    }

    public EntregadorControl(JTextField tfNomeEntregador, JTextField tfTelefoneEntregador,
            JTextField tfCnh, JTextField tfPesquisa, JTable tabelaEntregador) {
        this.tfNomeEntregador = tfNomeEntregador;
        this.tfTelefoneEntregador = tfTelefoneEntregador;
        this.tfCnh = tfCnh;
        this.tfPesquisa = tfPesquisa;
        this.tabelaEntregador = tabelaEntregador;
        this.listaEntregador = new ArrayList<>();
        this.entregadorDao = new EntregadorDao();
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void limparCampos() {
        tfNomeEntregador.setText("");
        tfTelefoneEntregador.setText("");
        tfCnh.setText("");
    }

    private void showTableEntregador() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEntregador.getModel();
        modelo.setNumRows(0);
        for (Entregador entre : listaEntregador) {
            modelo.addRow(new Object[]{
                entre.getId(),
                entre.getNome(),
                entre.getTelefone(),
                entre.getCnh()
            });
        }
    }

    public void listarTableEntregador() {
        listaEntregador = entregadorDao.listar();
        showTableEntregador();
    }

    public void cadastrarEntregadorAction() {
        verificacao = true;
        if (!verificacao) {
            showJOP("Cadastro incorreto!! Preencha novamente");
            return;
        }
        entregador = new Entregador();
        if (!tfNomeEntregador.getText().equals("")) {
            entregador.setNome(tfNomeEntregador.getText());
        } else {
            showJOP("Cadastro incorreto!! Preencha novamente");
            verificacao = false;
            return;
        }
        //-----------------
        if (!tfTelefoneEntregador.getText().equals("(  )    -    ")) {
            entregador.setTelefone(tfTelefoneEntregador.getText());

        } else {
            verificacao = false;
            tfTelefoneEntregador.requestFocus();
            showJOP("Preencha o Telefone corretamente!!");
            return;
        }
        //----------------------
        if (!tfCnh.getText().equals("")) {
            entregador.setCnh(tfCnh.getText());

        } else {
            verificacao = false;
            tfCnh.requestFocus();
            showJOP("Preencha o CNH corretamente!!");
            return;
        }
        //-----------------------
        if (verificacao) {
            entregadorDao.salvar(entregador);
            showJOP("Salvo com sucesso!!");
        } else {
            showJOP("Erro ao salvar");
        }
        verificacao = false;
    }

    public void salvarAction() {
        if (entregador != null) {
            editarAction();
        } else {
            cadastrarEntregadorAction();
        }
        limparCampos();
        listarTableEntregador();
        entregador = null;

    }

    public Entregador entregadorSelecionado() {
        try {
            int indice = tabelaEntregador.getSelectedRow();
            return listaEntregador.get(indice);

        } catch (Exception ex) {
            showJOP("Preencha o cadastro corretamente!!");
            return null;
        }
    }

    public void SetarCampoEdicao() {
        entregador = entregadorSelecionado();
        if (entregador == null) {
            return;
        }
        tfNomeEntregador.setText(entregador.getNome());
        tfTelefoneEntregador.setText(entregador.getTelefone());
        tfCnh.setText(entregador.getCnh());
    }

    public void editarAction() {
        entregador.setNome(tfNomeEntregador.getText());
        entregador.setTelefone(tfTelefoneEntregador.getText());
        entregador.setCnh(tfCnh.getText());
        if (entregadorDao.atualizar(entregador)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar!!");
        }
    }

    public void excluirAction() {
        entregador = entregadorSelecionado();
        if (entregador == null) {
            return;
        }
        if (entregadorDao.excluir(entregador)) {
            showJOP("Excluido com sucesso");
        } else {
            showJOP("Erro ao excluir");
        }
        listarTableEntregador();
        entregador = null;
    }

    public void pesquisarEntregador() {
        listaEntregador = entregadorDao.pesquisarPorNome(tfPesquisa.getText());
        showTableEntregador();
    }
}
