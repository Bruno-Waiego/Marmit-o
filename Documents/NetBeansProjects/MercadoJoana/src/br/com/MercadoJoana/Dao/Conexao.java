/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MercadoJoana.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bruno
 */
public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static Connection conn;

    public static Connection getConexao() { // metodo static e sem instancia de classe (Sem new )
        if (conn == null) { //validacao para unica vez, usuario da proxima vez entra direto com a conexao 
            try {
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conectou...");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }
}
