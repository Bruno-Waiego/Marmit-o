/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.Control;

import br.com.marmitao.daoImpl.EncomendaDao;
import br.com.marmitao.daoImpl.EncomendaProdutoDao;
import br.com.marmitao.daoImpl.ProdutoDao;
import br.com.marmitao.model.Encomenda;
import br.com.marmitao.model.EncomendaProduto;
import br.com.marmitao.model.Produto;
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
public class EncomendaProdutoControl {

    private JComboBox cbEncomenda;
    private JComboBox cbProduto;
    private JLabel lblValorTotalPedido;
    private JTextField tfQuantidade;
    private JTextField tfValor;
    private JTable tabelaEncomendaProdutos;
    //-------------
    private List<Encomenda> listaEncomenda;
    private EncomendaDao encoDao;
    //--------------------------------------
    private EncomendaProduto encomendaProduto = null;
    private List<EncomendaProduto> listaEncomendaProduto;
    private EncomendaProdutoDao encoProdutoDao;
    //---------------------------
    Produto pro = null;
    private List<Produto> listaProduto;
    private ProdutoDao produtoDao;
    //---------------------------

    public EncomendaProdutoControl() {
        this.listaEncomenda = new ArrayList<>();
        this.encoDao = new EncomendaDao();
        //--------
        this.listaEncomendaProduto = new ArrayList<>();
        this.encoProdutoDao = new EncomendaProdutoDao();
        //--------
        this.listaProduto = new ArrayList<>();
        this.produtoDao = new ProdutoDao();
    }

    public EncomendaProdutoControl(JComboBox cbEncomenda, JComboBox cbProduto, JLabel lblValorTotalPedido,
            JTextField tfQuantidade, JTextField tfValor, JTable tabelaEncomendaProdutos) {
        this.cbEncomenda = cbEncomenda;
        this.cbProduto = cbProduto;
        this.lblValorTotalPedido = lblValorTotalPedido;
        this.tfQuantidade = tfQuantidade;
        this.tfValor = tfValor;
        this.tabelaEncomendaProdutos = tabelaEncomendaProdutos;
        //--------
        this.listaEncomenda = new ArrayList<>();
        this.encoDao = new EncomendaDao();
        //--------
        this.listaEncomendaProduto = new ArrayList<>();
        this.encoProdutoDao = new EncomendaProdutoDao();
        //--------
        this.listaProduto = new ArrayList<>();
        this.produtoDao = new ProdutoDao();

    }

    public void popularComboBox() {
        listaEncomenda = new ArrayList<>();
        encoDao = new EncomendaDao();
        listaEncomenda = encoDao.listar();
        for (Encomenda en : listaEncomenda) {
            cbEncomenda.addItem(en.toString());
        }
        listaProduto = new ArrayList<>();
        produtoDao = new ProdutoDao();
        listaProduto = produtoDao.listar();
        for (Produto pro : listaProduto) {
            cbProduto.addItem(pro.toString());
        }
    }

    public float getValorProduto() {
        EncomendaProduto e = new EncomendaProduto();
        e.setProduto(listaProduto.get(cbProduto.getSelectedIndex()));
        float valorProduto = e.getProduto().getValor();
        return valorProduto;
    }

    public void popularTFValor() {
        getResultadoTFValor();
        tfValor.setText(String.valueOf(getResultadoTFValor()));
    }

    public float getResultadoTFValor() {
        try {
            if (tfQuantidade.getText().equals("")) {
                return 0;
            }
            int quantidadeProduto = Integer.parseInt(tfQuantidade.getText());
            float valor = getValorProduto();
            float result = (quantidadeProduto * valor);
            return result;
        } catch (Exception e) {
            showJOP("Digite somente numeros inteiros!!");
            return 0;
        }
    }

    private void showJOP(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public Encomenda addEncomendaProduto() {
        try {
            int id = cbEncomenda.getSelectedIndex();
            return listaEncomenda.get(id);
        } catch (Exception e) {
            showJOP("Não há encomendas cadastradas");
            return null;
        }
    }

    public void listarVinculoEncomendaProduto() {
        listaEncomendaProduto = encoProdutoDao.listarVinculoEncomendaProduto(addEncomendaProduto().getId());
        showTableEncomendaProduto();
    }

    public void listarEncomendaProduto() {
        listaEncomendaProduto = encoProdutoDao.listar();
        showTableEncomendaProduto();

    }

    private void showTableEncomendaProduto() {
        DefaultTableModel model = (DefaultTableModel) tabelaEncomendaProdutos.getModel();
        model.setNumRows(0);
        float resultTotal = 0;
        for (EncomendaProduto encoP : listaEncomendaProduto) {
            model.addRow(new Object[]{
                encoP.getId(),
                encoP.getEncomenda().getCliente().getNome(),
                encoP.getEncomenda().getEndereco().getLogradouro(),
                encoP.getProduto().getNome(),
                encoP.getProduto().getValor(),
                encoP.getQuantidade(),
                encoP.getValor(),});
            resultTotal += encoP.getValor();
            lblValorTotalPedido.setText(String.valueOf(resultTotal));

        }

    }

    public void cadastrarEncomendaProdutoAction() {
        try {
            EncomendaProduto encoP = new EncomendaProduto();
            encoP.setEncomenda(listaEncomenda.get(cbEncomenda.getSelectedIndex()));
            encoP.setProduto(listaProduto.get(cbProduto.getSelectedIndex()));
            encoP.setQuantidadeSTR(tfQuantidade.getText());
            encoP.setValorSTR(tfValor.getText());
            if (encoProdutoDao.salvar(encoP)) {
                showJOP("Salvo com sucesso!!");
            } else {
                showJOP("Faltou algo");
            }
            encoP = null;
        } catch (Exception e) {
            showJOP("Preencha os campos corretamente!!");
            return;
        }
    }

    public void limparCamposEncomendaProduto() {
        tfQuantidade.setText("");
        tfValor.setText("");
        cbEncomenda.setSelectedIndex(0);
        cbProduto.setSelectedIndex(0);
    }

    public void salvarEncomendaProdutoAction() {
        if (encomendaProduto == null) {
            cadastrarEncomendaProdutoAction();
        } else {
            editarEncomendaProdutoAction();
        }
        limparCamposEncomendaProduto();
        listarVinculoEncomendaProduto();
        encomendaProduto = null;
    }

    public EncomendaProduto encomendaProdutoSelecionada() {
        int sele = tabelaEncomendaProdutos.getSelectedRow();
        return listaEncomendaProduto.get(sele);
    }

    public void setarCamposEncomenda() {
        try {
            encomendaProduto = encomendaProdutoSelecionada();
            tfQuantidade.setText(String.valueOf(encomendaProduto.getQuantidade()));
            tfValor.setText(String.valueOf(encomendaProduto.getValor()));
        } catch (Exception e) {
            showJOP("Selecione uma encomenda!!");
            return;
        }

    }

    public void editarEncomendaProdutoAction() {
        encomendaProduto.setEncomenda(listaEncomenda.get(cbEncomenda.getSelectedIndex()));
        encomendaProduto.setProduto(listaProduto.get(cbProduto.getSelectedIndex()));
        encomendaProduto.setQuantidadeSTR(tfQuantidade.getText());
        encomendaProduto.setValorSTR(tfValor.getText());

        if (encoProdutoDao.atualizar(encomendaProduto)) {
            showJOP("Editado com sucesso!!");
        } else {
            showJOP("Erro ao editar");
        }
    }

    public void excluirEncomendaAction() {
        try {
            encomendaProduto = encomendaProdutoSelecionada();
            if (encoProdutoDao.excluir(encomendaProduto)) {
                showJOP("Excluido com sucesso!!");
            } else {
                showJOP("Erro ao excluir!!");
            }
        } catch (Exception e) {
            showJOP("Selecione uma encomenda");
            return;
        }
        limparCamposEncomendaProduto();
        listarVinculoEncomendaProduto();
        encomendaProduto = null;
    }

    public void cartãoEncomenda() {
        try {
            encomendaProduto = encomendaProdutoSelecionada();
            JOptionPane.showMessageDialog(null, encomendaProduto.toString(), "Dados Totais do Pedido", 1);
            encomendaProduto = null;
        } catch (Exception e) {
            showJOP("Selecione uma encomenda");
        }

    }
}
