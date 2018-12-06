/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.EnderecoDao;
import br.com.marmitao.model.Cliente;
import br.com.marmitao.model.Endereco;
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
public class EnderecoControl {

    private boolean verificacao = false;
    private JTextField tfLogradouro;
    private JTextField tfBairro;
    private JTextField tfCidade;
    private JTextField tfCep;
    private JTextField tfComplemento;
    private JTextField tfPesquisa;
    private JLabel lblClienteSelecionado;
    private JTable tabelaEndereco;
    private JTable tabelaCliente;

    private Cliente cliente = null;
    private Endereco endereco = null;
    private EnderecoDao enderecoDao;
    private ClienteControl cliControl;
    private List<Endereco> listaEndereco;

    public EnderecoControl() {
        this.cliControl = new ClienteControl();
        this.enderecoDao = new EnderecoDao();
        this.listaEndereco = new ArrayList<>();
    }

    public EnderecoControl(JTextField tfLogradouro, JTextField tfBairro, JTextField tfCidade, JTextField tfCep,
            JTextField tfComplemento, JTable tabelaEndereco, JTable tabelaCliente,JTextField tfPesquisa, JLabel lblClienteSelecionado) {
        this.tfLogradouro = tfLogradouro;
        this.tfBairro = tfBairro;
        this.tfCidade = tfCidade;
        this.tfCep = tfCep;
        this.tfComplemento = tfComplemento;
        this.tfPesquisa = tfPesquisa;
        this.lblClienteSelecionado = lblClienteSelecionado;
        this.tabelaEndereco = tabelaEndereco;
        this.tabelaCliente = tabelaCliente;
        this.enderecoDao = new EnderecoDao();
        this.cliControl = new ClienteControl();
        this.listaEndereco = new ArrayList<>();
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public Cliente addClienteEndereco(List<Cliente> lista) {
        int sele = tabelaCliente.getSelectedRow();
        return lista.get(sele);
    }

    private void limparCampos() {
        tfLogradouro.setText("");
        tfBairro.setText("");
        tfCidade.setText("");
        tfCep.setText("");
        tfComplemento.setText("");
        lblClienteSelecionado.setText("");
    }

    private void showTableEndereco() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaEndereco.getModel();
        modelo.setNumRows(0);
        for (Endereco endereco : listaEndereco) {
            modelo.addRow(new Object[]{
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getCep(),
                endereco.getComplemento()
            });
        }
    }

    public void listarTableEndereco() {
        listaEndereco = enderecoDao.listar();
        showTableEndereco();
    }

    public void cadastrarEnderecoAction() {
        verificacao = true;
        if (!verificacao) {
            showJOP("Cadastro incorreto!! Preencha novamente");
            return;
        }
        //-------------------------------
        endereco = new Endereco();
        
        if ((!tfLogradouro.getText().equals("") && !tfBairro.getText().equals("")
                && !tfCidade.getText().equals("") && !tfComplemento.getText().equals(""))) {
            
            
            
            endereco.setLogradouro(tfLogradouro.getText());
            endereco.setBairro(tfBairro.getText());
            endereco.setCidade(tfCidade.getText());
            endereco.setComplemento(tfComplemento.getText());
        } else {
            showJOP("Cadastro incorreto!! Preencha novamente");
            verificacao = false;
            return;
        }
        //----------------------- 

        if (!tfCep.getText().equals("     -   ")) {
            endereco.setCep(tfCep.getText());
        } else {
            showJOP("Cadastro incorreto!! Preencha novamente");
            tfCep.requestFocus();
            verificacao = false;
            return;
        }
        try {
            endereco.setCliente(addClienteEndereco(cliControl.listarClienteRetorno()));
        } catch (Exception ex) {
            showJOP("Selecione um cliente para cadastrar endereço!!");
            verificacao = false;
            return;
        }
        if (verificacao) {

            enderecoDao.salvar(endereco);

            showJOP("Salvo com sucesso!!");
        } else {
            showJOP("Erro ao salvar");
        }
        verificacao = false;

    }

    public void salvarAction() {
        if (endereco != null) {
            editarAction();
        } else {
            cadastrarEnderecoAction();
        }
        limparCampos();
        endereco = null;
        cliente = null;

    }

    public Endereco enderecoSelecionado() {
        try {
            int indice = tabelaEndereco.getSelectedRow();
            return listaEndereco.get(indice);

        } catch (Exception ex) {
            showJOP("Preencha o cadastro corretamente!!");
            return null;
        }
    }

    public void SetarCampoEdicao() {
        endereco = enderecoSelecionado();
        if (endereco == null) {
            return;
        }
        tfLogradouro.setText(endereco.getLogradouro());
        tfBairro.setText(endereco.getBairro());
        tfCidade.setText(endereco.getCidade());
        tfCep.setText(endereco.getCep());
        tfComplemento.setText(endereco.getComplemento());

    }

    public void editarAction() {
        endereco.setLogradouro(tfLogradouro.getText());
        endereco.setBairro(tfBairro.getText());
        endereco.setCidade(tfCidade.getText());
        endereco.setCep(tfCep.getText());
        endereco.setComplemento(tfComplemento.getText());
        endereco.setCliente(addClienteEndereco(cliControl.listarClienteRetorno()));

        if (enderecoDao.atualizar(endereco)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar!!");
        }
        showTableEndereco();
    }

    public void excluirAction() {
        endereco = enderecoSelecionado();
        if (endereco == null) {
            return;
        }
        if (enderecoDao.excluir(endereco)) {
            showJOP("Excluido com sucesso");
        } else {
            showJOP("Erro ao excluir");
        }
        listarTableEndereco();
        endereco = null;
    }

    public void pesquisarEndereco() {
      listaEndereco = enderecoDao.pesquisarPorNome(tfPesquisa.getText()); 
      showTableEndereco();
    }
    
    public void pesquisarVinculoEndereco(){
        listaEndereco = enderecoDao.tabelaVinculoEndereco(addClienteEndereco(cliControl.listarClienteRetorno()).getId());
        showTableEndereco();
    }

}
