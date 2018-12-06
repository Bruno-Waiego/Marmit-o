/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoI;
import br.com.marmitao.model.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class CategoriaDao extends Dao implements DaoI<Categoria> {

    PreparedStatement stmt;
    ResultSet res;

    public CategoriaDao() {
        super();
    }

    @Override
    public boolean salvar(Categoria obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO categoria(nome) VALUES(?)");
            stmt.setString(1, obj.getNome());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Categoria obj) {
        try {
            stmt = conn.prepareStatement("UPDATE categoria SET nome=? Where id=?");
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao editar: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluir(Categoria obj) {
        try {
            stmt = conn.prepareStatement("UPDATE categoria SET ativo =0 WHERE id =?");
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
    public List<Categoria> listar() {
        try {
            stmt = conn.prepareStatement("SELECT id, nome FROM categoria WHERE ativo = 1");
            res = stmt.executeQuery();
            List<Categoria> listaC = new ArrayList<>();
            while (res.next()) {
                Categoria c = new Categoria();
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
                listaC.add(c);
            }
            return listaC;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        }

    }

    public Categoria lerPorID(int id) {
        Categoria c = new Categoria();
        try {
            stmt = conn.prepareStatement("SELECT * FROM categoria WHERE id like ?");
            stmt.setInt(1, id);
            res = stmt.executeQuery();
            while (res.next()) {
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }
        return c;

    }

    @Override
    public List<Categoria> pesquisarPorNome(String palavra) {
        try {
            stmt = conn.prepareStatement("SELECT id, nome FROM categoria WHERE ativo = 1 AND nome like ? || ativo = 1 AND id like ?");
            stmt.setString(1, palavra + "%");
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            List<Categoria> listaC = new ArrayList<>();
            while (res.next()) {
                Categoria c = new Categoria();
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
                listaC.add(c);
            }
            return listaC;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

}
