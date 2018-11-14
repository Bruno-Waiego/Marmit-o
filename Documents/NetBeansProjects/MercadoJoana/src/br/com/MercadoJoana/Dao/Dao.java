/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MercadoJoana.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Bruno
 */
public class Dao {
    protected Connection conn = null;
    private static final String DATABASE_NAME = "locadora";

    public Dao() {
        conn = Conexao.getConexao();
        openOrCreateDatabase();
    }

    private void openOrCreateDatabase() {
        try {
            PreparedStatement stmt = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            stmt.execute();

            stmt = conn.prepareStatement("USE " + DATABASE_NAME);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
