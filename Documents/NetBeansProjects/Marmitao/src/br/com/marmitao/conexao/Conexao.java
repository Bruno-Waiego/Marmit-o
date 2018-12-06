/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Bruno
 */
public class Conexao {
    private static final String caminho = "jdbc:mysql://localhost:3306/";
    private static final String login = "root";
    private static final String senha = "root";
    private static Connection conn = null;

    public static Connection abrirConexao() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(caminho, login, senha);
                System.out.println("Conectou....");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
}
