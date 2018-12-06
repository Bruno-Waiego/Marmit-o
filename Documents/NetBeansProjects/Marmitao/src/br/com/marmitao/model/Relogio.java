/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Bruno
 */
public class Relogio {

    public static String getDataAtual() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataAtual = new Date();
        return formatador.format(dataAtual);
    }

    public static String getDataAtualSegundos() {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
        Date dataAtual = new Date();
        return formatador.format(dataAtual);
    }
}
