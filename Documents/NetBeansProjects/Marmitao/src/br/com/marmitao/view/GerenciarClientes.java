/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.view;

import br.com.marmitao.Control.ClienteControl;
import br.com.marmitao.Control.EnderecoControl;

/**
 *
 * @author Bruno
 */
public class GerenciarClientes extends javax.swing.JInternalFrame {

    private ClienteControl clienteControl;
    private EnderecoControl enderecoControl;

    /**
     * Creates new form GerenciarClientes
     */
    public GerenciarClientes() {
        initComponents();
        clienteControl = new ClienteControl(tfNomeCliente, tfTelefone, tfDataNascimento, tfEmail, tfPesquisa,
                tabelaCliente, lblClienteSelecionado);
        clienteControl.listarTablecliente();
        enderecoControl = new EnderecoControl(tfLogradouro, tfBairro, tfCidade, tfCep, tfComplemento, tabelaEndereco, tabelaCliente,
                tfPesquisa, lblClienteSelecionado);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfDataNascimento = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter data;
            data = new javax.swing.text.MaskFormatter("##/##/####");
            tfDataNascimento = new javax.swing.JFormattedTextField(data);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        tfEmail = new javax.swing.JTextField();
        tfNomeCliente = new javax.swing.JTextField();
        tfTelefone = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter telefone;
            telefone = new javax.swing.text.MaskFormatter("(##)####-####");
            tfTelefone = new javax.swing.JFormattedTextField(telefone);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        tfPesquisa = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter data;
            data = new javax.swing.text.MaskFormatter("##/##/####");
            tfDataNascimento = new javax.swing.JFormattedTextField(data);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaEndereco = new javax.swing.JTable();
        tfLogradouro = new javax.swing.JTextField();
        tfBairro = new javax.swing.JTextField();
        tfCidade = new javax.swing.JTextField();
        tfCep = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter cep;
            cep = new javax.swing.text.MaskFormatter("#####-###");
            tfCep = new javax.swing.JFormattedTextField(cep);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        tfComplemento = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lblClienteSelecionado = new javax.swing.JLabel();
        btExcluirEndereco = new javax.swing.JButton();
        btSalvarEndereco = new javax.swing.JButton();
        btEditarEndereco = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gerenciador de Clientes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 11, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 65, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Email: ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 179, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Telefone: ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 103, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Data de Nascimento: ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 141, -1, -1));
        getContentPane().add(tfDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 141, 128, -1));
        getContentPane().add(tfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 179, 241, -1));
        getContentPane().add(tfNomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 65, 280, -1));
        getContentPane().add(tfTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 103, 140, -1));

        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 243, 99, 34));

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 243, 99, 34));

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 243, 99, 34));

        tabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Telefone", "Data Nascimento", "Email"
            }
        ));
        tabelaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCliente);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 295, 499, 235));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Pesquisar Cliente e Endereço: ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, -1, -1));

        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(tfPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 550, 259, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Cliente Selecionado: ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Bairro: ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 103, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Cidade:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 141, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Cep:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 179, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Complemento: ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 217, -1, -1));

        tabelaEndereco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Endereço", "Logradouro", "Bairro", "Cidade", "Cep", "Complemento"
            }
        ));
        jScrollPane2.setViewportView(tabelaEndereco);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 295, 510, 235));
        getContentPane().add(tfLogradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 240, -1));
        getContentPane().add(tfBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(574, 103, 222, -1));
        getContentPane().add(tfCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 141, 222, -1));
        getContentPane().add(tfCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(559, 179, 134, -1));
        getContentPane().add(tfComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 217, 222, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Logradouro:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, -1, -1));

        lblClienteSelecionado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(lblClienteSelecionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 210, 20));

        btExcluirEndereco.setText("Excluir endereço");
        btExcluirEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(btExcluirEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 450, 120, 30));

        btSalvarEndereco.setText("Salvar endereço");
        btSalvarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(btSalvarEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 300, 120, 30));

        btEditarEndereco.setText("Editar endereço");
        btEditarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(btEditarEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 380, 120, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        clienteControl.salvarAction();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        clienteControl.setarCampoEdicao();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        clienteControl.excluirAction();
        enderecoControl.listarTableEndereco();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void tfPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyReleased
        clienteControl.pesquisarCliente();
        enderecoControl.pesquisarEndereco();
    }//GEN-LAST:event_tfPesquisaKeyReleased

    private void btSalvarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarEnderecoActionPerformed
        enderecoControl.salvarAction();
        lblClienteSelecionado.setText("");
        enderecoControl.listarTableEndereco();
        enderecoControl.pesquisarVinculoEndereco();
    }//GEN-LAST:event_btSalvarEnderecoActionPerformed

    private void btEditarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarEnderecoActionPerformed
        enderecoControl.SetarCampoEdicao();
        lblClienteSelecionado.setText("");
        enderecoControl.listarTableEndereco();
        enderecoControl.pesquisarVinculoEndereco();
    }//GEN-LAST:event_btEditarEnderecoActionPerformed

    private void btExcluirEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirEnderecoActionPerformed
        enderecoControl.excluirAction();
        lblClienteSelecionado.setText("");
        enderecoControl.listarTableEndereco();
        enderecoControl.pesquisarVinculoEndereco();
    }//GEN-LAST:event_btExcluirEnderecoActionPerformed

    private void tabelaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteMouseClicked
        enderecoControl.addClienteEndereco(clienteControl.listarClienteRetorno());
        lblClienteSelecionado.setText(clienteControl.ClienteSelecionado().getNome());
        enderecoControl.pesquisarVinculoEndereco();
    }//GEN-LAST:event_tabelaClienteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEditarEndereco;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btExcluirEndereco;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btSalvarEndereco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClienteSelecionado;
    private javax.swing.JTable tabelaCliente;
    private javax.swing.JTable tabelaEndereco;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfComplemento;
    private javax.swing.JTextField tfDataNascimento;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfLogradouro;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfPesquisa;
    private javax.swing.JTextField tfTelefone;
    // End of variables declaration//GEN-END:variables
}