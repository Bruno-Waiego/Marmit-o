/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.ClienteDao;
import br.com.marmitao.daoImpl.EncomendaDao;
import br.com.marmitao.daoImpl.EnderecoDao;
import br.com.marmitao.daoImpl.EntregadorDao;
import br.com.marmitao.model.Cliente;
import br.com.marmitao.model.Encomenda;
import br.com.marmitao.model.Endereco;
import br.com.marmitao.model.Entregador;
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
public class EncomendaControl {

    private JComboBox cbCliente;
    private JComboBox cbEndereco;
    private JComboBox cbEntregador;
    private JLabel lblDatePedido;
    private JTextField tfDataEntrega;
    private JTextField tfPesquisa;
    private JTable tabelaEncomenda;
    //-------------
    private Encomenda encomenda = null;
    private List<Encomenda> listaEncomenda;
    private EncomendaDao encoDao;

    //----
    private List<Cliente> listaCliente;
    private ClienteDao cliDao;
    //-------------
    private List<Endereco> listaEndereco;
    private EnderecoDao endDao;
    //------------------------
    private List<Entregador> listaEntregador;
    private EntregadorDao entreDao;

    public EncomendaControl() {
        this.cliDao = new ClienteDao();
        this.listaCliente = new ArrayList<>();
        //--------
        this.listaEndereco = new ArrayList<>();
        this.endDao = new EnderecoDao();
        //--------
        this.listaEntregador = new ArrayList<>();
        this.entreDao = new EntregadorDao();
    }

    public EncomendaControl(JComboBox cbCliente, JComboBox cbEndereco, JComboBox cbEntregador,
            JLabel lblDatePedido, JTextField tfDataEntrega, JTextField tfPesquisa, JTable tabelaEncomenda) {
        this.cbCliente = cbCliente;
        this.cbEndereco = cbEndereco;
        this.cbEntregador = cbEntregador;
        this.lblDatePedido = lblDatePedido;
        this.tfDataEntrega = tfDataEntrega;
        this.tfPesquisa = tfPesquisa;
        this.tabelaEncomenda = tabelaEncomenda;
        //--------------
        this.listaEncomenda = new ArrayList<>();
        this.encoDao = new EncomendaDao();
        //--------------
        this.cliDao = new ClienteDao();
        this.listaCliente = new ArrayList<>();
        //-----------------
        this.listaEndereco = new ArrayList<>();
        this.endDao = new EnderecoDao();
        //----------------------
        this.listaEntregador = new ArrayList<>();
        this.entreDao = new EntregadorDao();
    }

    public void popularComboBox() {
        listaCliente = new ArrayList<>();
        cliDao = new ClienteDao();
        listaCliente = cliDao.listarClientesEncomenda();
        String verificar = "";
        for (Cliente cliente : listaCliente) {
            if (!verificar.equalsIgnoreCase(cliente.getNome())) {
                verificar = cliente.getNome();
                cbCliente.addItem(cliente.toString());
            } else {
                cbCliente.removeItem(cliente);
            }
        }
        listaEntregador = new ArrayList<>();
        entreDao = new EntregadorDao();
        listaEntregador = entreDao.listar();
        for (Entregador entre : listaEntregador) {
            cbEntregador.addItem(entre.getNome());
        }
    }

    public void popularEnderecoComboBox() {
        cbEndereco.removeAllItems();
        listaCliente = new ArrayList<>();
        listaCliente = cliDao.listar();
        listaEndereco = endDao.tabelaVinculoEndereco(listaCliente.get(cbCliente.getSelectedIndex()).getId());
        for (Endereco ende : listaEndereco) {
            cbEndereco.addItem(ende.toString());
        }

    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void listarEncomenda() {
        listaEncomenda = encoDao.listar();
        showTableEncomenda();
    }

    private void showTableEncomenda() {
        DefaultTableModel model = (DefaultTableModel) tabelaEncomenda.getModel();
        model.setNumRows(0);
        String status = "";
        for (Encomenda enco : listaEncomenda) {
            if (enco.getStatus() == 1) {
                status = "Emitido";
            } else {
                status = "Enviado";
            }

            model.addRow(new Object[]{
                enco.getId(),
                enco.getCliente().getNome(),
                enco.getEndereco().getLogradouro(),
                enco.getEndereco().getCep(),
                enco.getEntregador().getNome(),
                dataBancoParaUsuario(enco.getDataPedido()),
                dataBancoParaUsuario(enco.getDataEntrega()),
                status
            });

        }

    }

    public String dataBancoParaUsuario(java.sql.Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    private java.sql.Date dataUsuarioParaBanco(String data) {
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formatador.parse(String.valueOf(data));
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException ex) {
            System.out.println("Erro ao converter data " + ex.getMessage());
            return null;
        }
    }

    public void cadastrarEncomendaAction() {
        try {
            Encomenda enco = new Encomenda();
            enco.setCliente(listaCliente.get(cbCliente.getSelectedIndex()));
            enco.setEntregador(listaEntregador.get(cbEntregador.getSelectedIndex()));
            enco.setEndereco(listaEndereco.get(cbEndereco.getSelectedIndex()));
            enco.setDataEntrega(dataUsuarioParaBanco(tfDataEntrega.getText()));
            enco.setStatus(1);
            if (enco.getDataEntrega() == null) {
                showJOP("Digite a data de entrega!!");
                return;
            }
            enco.setStatus(1);
            if (encoDao.salvar(enco)) {
                showJOP("Salvo com sucesso!!");
            } else {
                showJOP("Erro ao salvar");
            }
            enco = null;
        } catch (Exception e) {
            showJOP("Preencha os campos corretamente!!");
            return;
        }
    }

    public void limparCamposEncomenda() {
        tfDataEntrega.setText("");
        cbCliente.setSelectedIndex(0);
        cbEntregador.setSelectedIndex(0);
    }

    public void salvarEncomendaAction() {
        if (encomenda == null) {
            cadastrarEncomendaAction();
        } else {
            editarEncomendaAction();
        }
        limparCamposEncomenda();
        listarEncomenda();
        encomenda = null;
    }

    public Encomenda encomendaSelecionada() {
        int sele = tabelaEncomenda.getSelectedRow();
        return listaEncomenda.get(sele);
    }

    public void setarCamposEncomenda() {
        try {
            encomenda = encomendaSelecionada();
            tfDataEntrega.setText(dataBancoParaUsuario(encomenda.getDataEntrega()));
        } catch (Exception e) {
            showJOP("Selecione uma encomenda!!");
            return;
        }

    }

    public void editarEncomendaAction() {
        try {
            encomenda.setCliente(listaCliente.get(cbCliente.getSelectedIndex()));
            encomenda.setDataPedido(dataUsuarioParaBanco(lblDatePedido.getText()));
            encomenda.setDataEntrega(dataUsuarioParaBanco(tfDataEntrega.getText()));
            encomenda.setEntregador(listaEntregador.get(cbEntregador.getSelectedIndex()));
            encomenda.setEndereco(listaEndereco.get(cbEndereco.getSelectedIndex()));

            if (encoDao.atualizar(encomenda)) {
                showJOP("Editado com sucesso!!");
            } else {
                showJOP("Erro ao editar");
            }
        } catch (Exception e) {
            showJOP("Preencha os dados corretamente!!");
            return;
        }

    }

    public void excluirEncomendaAction() {
        try {
            encomenda = encomendaSelecionada();
            if (encoDao.excluir(encomenda)) {
                showJOP("Excluido com sucesso!!");
            } else {
                showJOP("Erro ao excluir!!");
            }
        } catch (Exception e) {
            showJOP("Selecione uma encomenda");
            return;
        }
        limparCamposEncomenda();
        listarEncomenda();
        encomenda = null;
    }

    public void alterarStatusEnviado() {
        try {
            encomenda = encomendaSelecionada();
            if (encoDao.statusEnviado(encomenda)) {
                showJOP("Status alterado com sucesso!!");
            } else {
                showJOP("Erro ao alterar!!");
            }
        } catch (Exception e) {
            showJOP("Selecione uma encomenda");
            return;
        }
        limparCamposEncomenda();
        listarEncomenda();
        encomenda = null;
    }

    public void alterarStatusRecebido() {
        try {
            encomenda = encomendaSelecionada();
            if (encoDao.statusRecebido(encomenda)) {
                showJOP("Status alterado com sucesso!!");
            } else {
                showJOP("Erro ao alterar!!");
            }
        } catch (Exception e) {
            showJOP("Selecione uma encomenda");
            return;
        }
        limparCamposEncomenda();
        listarEncomenda();
        encomenda = null;
    }

    public void pequisarEncomendaAction() {
        listaEncomenda = encoDao.pesquisarPorNome(tfPesquisa.getText());
        showTableEncomenda();
    }
}
