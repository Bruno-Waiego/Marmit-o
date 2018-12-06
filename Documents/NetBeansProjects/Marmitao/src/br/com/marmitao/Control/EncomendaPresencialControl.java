/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.ClienteDao;
import br.com.marmitao.daoImpl.EncomendaPresencialDao;
import br.com.marmitao.model.Cliente;
import br.com.marmitao.model.Encomenda;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class EncomendaPresencialControl {

    private JComboBox cbCliente;
    private JLabel lblDatePedido;
    private JTextField tfDataEntrega;
    private JTextField tfPesquisa;
    private JTable tabelaEncomendaPresencial;
    //-------------
    private Encomenda encomendaPresencial = null;
    private List<Encomenda> listaEncomendaPresencial;
    private EncomendaPresencialDao encoPresencialDao;

    //----
    private List<Cliente> listaCliente;
    private ClienteDao cliDao;
    //-------------

    public EncomendaPresencialControl() {
        this.cliDao = new ClienteDao();
        this.listaCliente = new ArrayList<>();
        //--------
        this.listaEncomendaPresencial = new ArrayList<>();
        this.encoPresencialDao = new EncomendaPresencialDao();
    }

    public EncomendaPresencialControl(JComboBox cbCliente, JLabel lblDatePedido, JTextField tfDataEntrega,
            JTextField tfPesquisa, JTable tabelaEncomendaPresencial) {
        this.cbCliente = cbCliente;
        this.lblDatePedido = lblDatePedido;
        this.tfDataEntrega = tfDataEntrega;
        this.tfPesquisa = tfPesquisa;
        this.tabelaEncomendaPresencial = tabelaEncomendaPresencial;
        //--------------
        this.listaEncomendaPresencial = new ArrayList<>();
        this.encoPresencialDao = new EncomendaPresencialDao();
        //--------------
        this.cliDao = new ClienteDao();
        this.listaCliente = new ArrayList<>();
        //-----------------
    }

    public void popularComboBox() {
        listaCliente = new ArrayList<>();
        cliDao = new ClienteDao();
        listaCliente = cliDao.listar();
        for (Cliente cliente : listaCliente) {
            cbCliente.addItem(cliente.toString());
        }
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void listarEncomendaPresencial() {
        listaEncomendaPresencial = encoPresencialDao.listar();
        showTableEncomendaPresencial();
    }

    private void showTableEncomendaPresencial() {
        DefaultTableModel model = (DefaultTableModel) tabelaEncomendaPresencial.getModel();
        model.setNumRows(0);
        String status = "";
        for (Encomenda encop : listaEncomendaPresencial) {
            if (encop.getStatus() == 1) {
                status = "Emitido";
            } else {
                status = "Enviado";
            }
            model.addRow(new Object[]{
                encop.getId(),
                encop.getCliente().getNome(),
                dataBancoParaUsuario(encop.getDataPedido()),
                dataBancoParaUsuario(encop.getDataEntrega()),
                status
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

    public void cadastrarEncomendaPresencialAction() {
        try {
            Encomenda encop = new Encomenda();
            encop.setCliente(listaCliente.get(cbCliente.getSelectedIndex()));
            encop.setDataPedido(dataUsuarioParaBanco(lblDatePedido.getText()));
            encop.setDataEntrega(dataUsuarioParaBanco(tfDataEntrega.getText()));
            if (encop.getDataEntrega() == null) {
                showJOP("Digite a data de entrega!!");
                return;
            }
            encop.setStatus(1);
            if (encoPresencialDao.salvar(encop)) {
                showJOP("Salvo com sucesso!!");
            } else {
                showJOP("Erro ao salvar");
            }
            encop = null;
        } catch (Exception e) {
            showJOP("Preencha os campos corretamente!!");
            return;
        }
    }

    public void limparCamposEncomendaPresencial() {
        tfDataEntrega.setText("");
        cbCliente.setSelectedIndex(0);
    }

    public void salvarEncomendaPresencialAction() {
        if (encomendaPresencial == null) {
            cadastrarEncomendaPresencialAction();
        } else {
            editarEncomendaPresencialAction();
        }
        limparCamposEncomendaPresencial();
        listarEncomendaPresencial();
        encomendaPresencial = null;
    }

    public Encomenda encomendaPresencialSelecionada() {
        int sele = tabelaEncomendaPresencial.getSelectedRow();
        return listaEncomendaPresencial.get(sele);
    }

    public void setarCamposEncomendaPresencial() {
        try {
            encomendaPresencial = encomendaPresencialSelecionada();
            tfDataEntrega.setText(dataBancoParaUsuario(encomendaPresencial.getDataEntrega()));
        } catch (Exception e) {
            showJOP("Selecione uma encomenda!!");
            return;
        }

    }

    public void editarEncomendaPresencialAction() {
        encomendaPresencial.setCliente(listaCliente.get(cbCliente.getSelectedIndex()));
        encomendaPresencial.setDataPedido(dataUsuarioParaBanco(lblDatePedido.getText()));
        encomendaPresencial.setDataEntrega(dataUsuarioParaBanco(tfDataEntrega.getText()));
        encomendaPresencial.setStatus(1);

        if (encoPresencialDao.atualizar(encomendaPresencial)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar");
        }
    }

    public void excluirEncomendaPresencialAction() {
        try {
            encomendaPresencial = encomendaPresencialSelecionada();
            if (encoPresencialDao.excluir(encomendaPresencial)) {
                showJOP("Excluido com sucesso!!");
            } else {
                showJOP("Erro ao excluir!!");
            }
        } catch (Exception e) {
            showJOP("Selecione uma encomenda");
            return;
        }
        limparCamposEncomendaPresencial();
        listarEncomendaPresencial();
        encomendaPresencial = null;
    }

    public void alterarStatusRecebido() {
        try {
            encomendaPresencial = encomendaPresencialSelecionada();
            if (encoPresencialDao.statusRecebido(encomendaPresencial)) {
                showJOP("Status alterado com sucesso!!");
            } else {
                showJOP("Erro ao alterar status!!");
            }
        } catch (Exception e) {
            showJOP("Selecione uma encomenda");
            return;
        }
        limparCamposEncomendaPresencial();
        listarEncomendaPresencial();
        encomendaPresencial = null;
    }

    public void pequisarEncomendaPresencialAction() {
        listaEncomendaPresencial = encoPresencialDao.pesquisarPorNome(tfPesquisa.getText());
        showTableEncomendaPresencial();

    }
}
