/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.view;

import br.com.marmitao.model.RelogioThread;

/**
 *
 * @author Bruno
 */
public class Principal extends javax.swing.JFrame {

    private GerenciarCategorias gCategoria = null;
    private GerenciarClientes gCliente = null;
    private GerenciarEntregador gEntregador = null;
    private GerenciarProdutos gProdutos = null;
    private GerenciarEncomenda gEncomenda = null;
    private ProdutosEncomenda pEncomenda = null;
    private GerenciarEncomendaPresencial gEncomendaPresencial = null;
    private RelatóriosEncomenda rEncomenda = null;

    /**
     * SELECT max(id) as id FROM ltimo cliente; -- Retorna id do ultimo cliente
     * cadastrado Pode inserir em um metodo do Dao chamado:
     * cliente.setID(dao.getUltimoID()) = retorna um int ;
     *
     */
    public Principal() {
        initComponents();
        RelogioThread rt = new RelogioThread(lblRelogio);
        rt.iniciarRelogio();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        JDesktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblRelogio = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        Produto = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JDesktop.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout JDesktopLayout = new javax.swing.GroupLayout(JDesktop);
        JDesktop.setLayout(JDesktopLayout);
        JDesktopLayout.setHorizontalGroup(
            JDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        JDesktopLayout.setVerticalGroup(
            JDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("                                              Sistema de Encomendas de Marmita");

        lblRelogio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Add Property.png"))); // NOI18N
        jMenu1.setText("Categorias");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Create.png"))); // NOI18N
        jMenuItem1.setText("Gerenciar Categoria");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Queue.png"))); // NOI18N
        jMenu2.setText("Clientes");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Checked User Male.png"))); // NOI18N
        jMenuItem2.setText("Gerenciar Cliente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Container Truck.png"))); // NOI18N
        jMenu3.setText("Entregador");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Office Worker in a Suit Female.png"))); // NOI18N
        jMenuItem3.setText("Gerenciar Funcionário");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        Produto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Add Basket.png"))); // NOI18N
        Produto.setText("Produtos");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-produto-filled.png"))); // NOI18N
        jMenuItem4.setText("Gerenciar Produto");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Produto.add(jMenuItem4);

        jMenuBar1.add(Produto);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-Ringer Volume.png"))); // NOI18N
        jMenu7.setText("Pedidos");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-encomenda-enviada-filled.png"))); // NOI18N
        jMenuItem6.setText("Encomenda");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem6);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-dar-presente-filled-50.png"))); // NOI18N
        jMenuItem8.setText("Encomenda Cliente Presencial");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem8);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-finalizar-pedido-filled.png"))); // NOI18N
        jMenuItem7.setText("Produto Encomenda");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem7);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/marmitao/icones/icons8-lista-de-verificação-filled-50.png"))); // NOI18N
        jMenuItem5.setText("Relatórios de Pedido/ Encomenda");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JDesktop)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JDesktop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRelogio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (gCategoria == null) {
            gCategoria = new GerenciarCategorias();
            JDesktop.add(gCategoria);
            gCategoria.setVisible(true);
        }
        if (gCategoria.isClosed()) {
            gCategoria = new GerenciarCategorias();
            JDesktop.add(gCategoria);
            gCategoria.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (gCliente == null) {
            gCliente = new GerenciarClientes();
            JDesktop.add(gCliente);
            gCliente.setVisible(true);
        }
        if (gCliente.isClosed()) {
            gCliente = new GerenciarClientes();
            JDesktop.add(gCliente);
            gCliente.setVisible(true);
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (gEntregador == null) {
            gEntregador = new GerenciarEntregador();
            JDesktop.add(gEntregador);
            gEntregador.setVisible(true);
        }
        if (gEntregador.isClosed()) {
            gEntregador = new GerenciarEntregador();
            JDesktop.add(gEntregador);
            gEntregador.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (gProdutos == null) {
            gProdutos = new GerenciarProdutos();
            JDesktop.add(gProdutos);
            gProdutos.setVisible(true);
        }
        if (gProdutos.isClosed()) {
            gProdutos = new GerenciarProdutos();
            JDesktop.add(gProdutos);
            gProdutos.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (pEncomenda == null) {
            pEncomenda = new ProdutosEncomenda();
            JDesktop.add(pEncomenda);
            pEncomenda.setVisible(true);
        }
        if (pEncomenda.isClosed()) {
            pEncomenda = new ProdutosEncomenda();
            JDesktop.add(pEncomenda);
            pEncomenda.setVisible(true);
        }


    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (gEncomenda == null) {
            gEncomenda = new GerenciarEncomenda();
            JDesktop.add(gEncomenda);
            gEncomenda.setVisible(true);
        }
        if (gEncomenda.isClosed()) {
            gEncomenda = new GerenciarEncomenda();
            JDesktop.add(gEncomenda);
            gEncomenda.setVisible(true);
        }


    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if (gEncomendaPresencial == null) {
            gEncomendaPresencial = new GerenciarEncomendaPresencial();
            JDesktop.add(gEncomendaPresencial);
            gEncomendaPresencial.setVisible(true);
        }
        if (gEncomendaPresencial.isClosed()) {
            gEncomendaPresencial = new GerenciarEncomendaPresencial();
            JDesktop.add(gEncomendaPresencial);
            gEncomendaPresencial.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (rEncomenda == null) {
            rEncomenda = new RelatóriosEncomenda();
            JDesktop.add(rEncomenda);
            rEncomenda.setVisible(true);
        }
        if (rEncomenda.isClosed()) {
            rEncomenda = new RelatóriosEncomenda();
            JDesktop.add(rEncomenda);
            rEncomenda.setVisible(true);
        }


    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane JDesktop;
    private javax.swing.JMenu Produto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JLabel lblRelogio;
    // End of variables declaration//GEN-END:variables
}
