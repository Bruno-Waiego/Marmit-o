/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.RelatoriosEncomendaDao;
import br.com.marmitao.model.Encomenda;
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
public class RelatóriosEncomendaControl {

    private JTable tabelaRelatorios;
    private JTextField tfPesquisaData;
    private JTextField tfPesquisaCliente;
    private JTextField tfPesquisaEntregador;

    List<Encomenda> listaRelatorio;
    RelatoriosEncomendaDao rlDao;

    public RelatóriosEncomendaControl() {
    }

    public RelatóriosEncomendaControl(JTable tabelaRelatorios, JTextField tfPesquisaData,
            JTextField tfPesquisaCliente, JTextField tfPesquisaEntregador) {
        this.tabelaRelatorios = tabelaRelatorios;
        this.tfPesquisaData = tfPesquisaData;
        this.tfPesquisaCliente = tfPesquisaCliente;
        this.tfPesquisaEntregador = tfPesquisaEntregador;
        this.listaRelatorio = new ArrayList<>();
        this.rlDao = new RelatoriosEncomendaDao();
    }

    public void listarTodos() {
        listaRelatorio = rlDao.listarTodos();
        showTableRelatorios();
    }

    public void listarNomeCliente() {
        if (tfPesquisaCliente.getText().equals("")) {
            showJOP("Digite um Cliente");
            return;
        }
        listaRelatorio = rlDao.pesquisarPorNomeCliente(tfPesquisaCliente.getText());
        showTableRelatorios();
    }

    public void listarDataEspecifica() {
        if (tfPesquisaData.getText().equals("  /  /    ")) {
            showJOP("Digite uma Data");
            return;
        }
        listaRelatorio = rlDao.pesquisarDataEspecifica(dataUsuarioParaBanco(tfPesquisaData.getText()));
        showTableRelatorios();
    }

    public void listarNomeEntregador() {
        if (tfPesquisaEntregador.getText().equals("")) {
            showJOP("Digite um entregador");
            return;
        }
        listaRelatorio = rlDao.pesquisarPorNomeEntregador(tfPesquisaEntregador.getText());
        showTableRelatorios();
    }

    private void showTableRelatorios() {
        DefaultTableModel model = (DefaultTableModel) tabelaRelatorios.getModel();
        model.setNumRows(0);
        for (Encomenda enco : listaRelatorio) {
            model.addRow(new Object[]{
                enco.getId(),
                enco.getCliente().getNome(),
                enco.getEntregador().getNome(),
                dataBancoParaUsuario(enco.getDataPedido()),
                dataBancoParaUsuario(enco.getDataEntrega())
            });
        }
    }

    public String dataBancoParaUsuario(java.sql.Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    private static java.sql.Date dataUsuarioParaBanco(String data) {
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formatador.parse(data);
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException ex) {
            System.out.println("Erro ao converter data");
            return null;
        }
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
