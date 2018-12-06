/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.ClienteDao;
import br.com.marmitao.model.Cliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class ClienteControl {

    private boolean verificacao = false;
    private JTextField tfNomeCliente;
    private JTextField tfTelefone;
    private JTextField tfDataNascimento;
    private JTextField tfEmail;
    private JTextField tfPesquisa;
    private JTable tabelaCliente;
    private JLabel lblClienteSelecionado;

    private Cliente cliente = null;
    private List<Cliente> listaCliente;
    private ClienteDao clienteDao;

    public ClienteControl() {
        this.clienteDao = new ClienteDao();
        this.listaCliente = new ArrayList<>();
    }

    public ClienteControl(JTextField tfNomeCliente, JTextField tfTelefone, JTextField tfDataNascimento,
            JTextField tfEmail, JTextField tfPesquisa, JTable tabelaCliente, JLabel lblClienteSelecionado) {
        this.tfNomeCliente = tfNomeCliente;
        this.tfTelefone = tfTelefone;
        this.tfDataNascimento = tfDataNascimento;
        this.tfEmail = tfEmail;
        this.tfPesquisa = tfPesquisa;
        this.tabelaCliente = tabelaCliente;
        this.lblClienteSelecionado = lblClienteSelecionado;
        this.clienteDao = new ClienteDao();
        this.listaCliente = new ArrayList<>();

    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void limparCampos() {
        tfNomeCliente.setText("");
        tfTelefone.setText("");
        tfDataNascimento.setText("");
        tfEmail.setText("");
        lblClienteSelecionado.setText("");
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

    private void showTableCliente() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaCliente.getModel();
        modelo.setNumRows(0);
        for (Cliente cli : listaCliente) {
            modelo.addRow(new Object[]{
                cli.getId(),
                cli.getNome(),
                cli.getTelefone(),
                cli.getDataNascimento(),
                cli.getEmail()
            });
        }
    }

    public void listarTablecliente() {
        listaCliente = clienteDao.listar();
        showTableCliente();
    }

    public List<Cliente> listarClienteRetorno() {
        listaCliente = clienteDao.listar();
        return listaCliente;
    }

    public void cadastrarClienteAction() {
        verificacao = true;
        if (!verificacao) {
            showJOP("Cadastro incorreto!! Preencha novamente");
            return;
        }
        Cliente cliente = new Cliente();
        if (!tfNomeCliente.getText().equals("")) {
            cliente.setNome(tfNomeCliente.getText());
        } else {
            showJOP("Cadastro incorreto!! Preencha novamente");
            verificacao = false;
            return;
        }
        //-----------------
        if (!tfTelefone.getText().equals("(  )    -    ")) {
            cliente.setTelefone(tfTelefone.getText());

        } else {
            verificacao = false;
            tfTelefone.requestFocus();
            showJOP("Preencha o Telefone corretamente!!");
            return;
        }
        //----------------------------

        cliente.setDataNascimento(dataUsuarioParaBanco(tfDataNascimento.getText()));
        if (cliente.getDataNascimento() == null) {
            showJOP("Preencha o Data de nascimento corretamente!!");
            verificacao = false;
            return;
        }

        //-----------------------
        if (!tfEmail.getText().equals("")) {
            cliente.setEmail(tfEmail.getText());

        } else {
            verificacao = false;
            tfEmail.requestFocus();
            showJOP("Preencha o Email corretamente!!");
            return;
        }

        if (verificacao) {

            clienteDao.salvar(cliente);

            showJOP("Salvo com sucesso!!");
        } else {
            showJOP("Erro ao salvar");
        }
        verificacao = false;

    }

    public void salvarAction() {
        if (cliente != null) {
            editarAction();
        } else {
            cadastrarClienteAction();
        }
        limparCampos();
        listarTablecliente();
        cliente = null;

    }

    public Cliente ClienteSelecionado() {
        try {
            int indice = tabelaCliente.getSelectedRow();
            return listaCliente.get(indice);

        } catch (Exception ex) {
            showJOP("Preencha o cadastro corretamente!!");
            return null;
        }
    }

    public void setarCampoEdicao() {
        cliente = ClienteSelecionado();
        if (cliente == null) {
            return;
        }
        tfNomeCliente.setText(cliente.getNome());
        tfTelefone.setText(cliente.getTelefone());
        tfDataNascimento.setText(dataBancoParaUsuario(cliente.getDataNascimento()));
        tfEmail.setText(cliente.getEmail());
    }

    public void editarAction() {
        cliente.setNome(tfNomeCliente.getText());
        cliente.setTelefone(tfTelefone.getText());
        cliente.setDataNascimento(dataUsuarioParaBanco(tfDataNascimento.getText()));
        cliente.setEmail(tfEmail.getText());
        if (clienteDao.atualizar(cliente)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar!!");
        }
    }

    public void excluirAction() {
        cliente = ClienteSelecionado();
        if (cliente == null) {
            return;
        }
        if (clienteDao.excluir(cliente)) {
            showJOP("Excluido com sucesso");
        } else {
            showJOP("Erro ao excluir");
        }
        limparCampos();
        listarTablecliente();
        cliente = null;
    }

    public void pesquisarCliente() {
        listaCliente = clienteDao.pesquisarPorNome(tfPesquisa.getText());
        showTableCliente();
    }

}
