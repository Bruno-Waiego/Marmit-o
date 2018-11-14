/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MercadoJoana.daoImpl;

import br.com.MercadoJoana.Dao.Dao;
import br.com.MercadoJoana.Dao.DaoI;
import br.com.MercadoJoana.uteis.Categoria;
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
            stmt = conn.prepareStatement("INSERT INTO categoria(id, nome) VALUES(?, ?)");
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getNome());
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
            stmt = conn.prepareStatement("UPDATE categoria SET situacao =0 WHERE id =?");
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
            stmt = conn.prepareStatement("SELECT id, nome FROM categoria WHERE situacao = 1");
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
    /**
     *  Metodo Dao não usado!!
     * @param id
     * @return 
     */
    @Override
    public Categoria lerPorID(int id) {
        Categoria c = new Categoria();
        try {
            stmt = conn.prepareStatement("SELECT * FROM categorias WHERE id like ?");
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

    public List<Categoria> pesquisaPorNome(String nome) {
        try {
            stmt = conn.prepareStatement("SELECT id, nome FROM categoria WHERE situacao = 1 AND nome like ?");
            stmt.setString(1, nome + "%");
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
