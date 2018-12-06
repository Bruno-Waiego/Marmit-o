/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoI;
import br.com.marmitao.model.Cliente;
import br.com.marmitao.model.Entregador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class EntregadorDao extends Dao implements DaoI<Entregador> {

    private PreparedStatement stmt;
    private ResultSet res;

    public EntregadorDao() {
        super();
    }

    @Override
    public boolean salvar(Entregador obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO entregador(nome, telefone, cnh) VALUES (?, ?, ?)");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTelefone());
            stmt.setString(3, obj.getCnh());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Entregador obj) {
        try {
            stmt = conn.prepareStatement("UPDATE entregador SET nome=?,telefone=?,cnh=? WHERE id=?");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTelefone());
            stmt.setString(3, obj.getCnh());
            stmt.setInt(4, obj.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluir(Entregador obj) {
        try {
            stmt = conn.prepareStatement("UPDATE entregador SET ativo = 0 WHERE id=?");
            stmt.setInt(1, obj.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Entregador> listar() {
        try {
            stmt = conn.prepareStatement("SELECT * FROM entregador WHERE ativo = 1");
            res = stmt.executeQuery();
            List<Entregador> listaEntre = new ArrayList<>();
            while (res.next()) {
                Entregador entregador = new Entregador();
                entregador.setId(res.getInt("id"));
                entregador.setNome(res.getString("nome"));
                entregador.setTelefone(res.getString("telefone"));
                entregador.setCnh(res.getString("cnh"));
                listaEntre.add(entregador);
            }
            return listaEntre;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        } finally {
            res = null;
            stmt = null;
        }
    }

    @Override
    public List<Entregador> pesquisarPorNome(String palavra) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM entregador WHERE ativo = 1 AND nome like ? || ativo = 1 AND id like ?");
            stmt.setString(1, palavra + "%");
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            List<Entregador> listaEntre = new ArrayList<>();
            while (res.next()) {
                Entregador entregador = new Entregador();
                entregador.setId(res.getInt("id"));
                entregador.setNome(res.getString("nome"));
                entregador.setTelefone(res.getString("telefone"));
                entregador.setCnh(res.getString("cnh"));
                listaEntre.add(entregador);
            }
            return listaEntre;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        } finally {
            res = null;
            stmt = null;
        }
    }

}
