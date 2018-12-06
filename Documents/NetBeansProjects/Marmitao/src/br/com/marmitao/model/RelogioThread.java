/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.model;

import javax.swing.JLabel;

/**
 *
 * @author Bruno
 */
public class RelogioThread {

    private static JLabel lblRelogio;

    public RelogioThread(JLabel label) {
        this.lblRelogio = label;

    }

    public void iniciarRelogio() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                Relogio r = new Relogio();
                while (true) {
                    lblRelogio.setText(Relogio.getDataAtual());

                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        System.out.println("Erro ao ");
                    }
                }

            }
        };
        t1.start();
    }
}
