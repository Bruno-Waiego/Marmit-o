/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.dao;

import br.com.marmitao.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Bruno
 */
public class Dao {
    
    protected Connection conn = null;
    private static final String DATABASE_NAME = "final";

    public Dao() {
        conn = Conexao.abrirConexao();
        openOrCreateDataBase();
    }

    public void openOrCreateDataBase() {
        try {
            PreparedStatement stmt = conn.prepareStatement("create database if not exists " + DATABASE_NAME);
            stmt.execute();
            stmt = conn.prepareStatement("USE " + DATABASE_NAME);
            stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
