/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MercadoJoana.daoImpl;

import br.com.MercadoJoana.Dao.Dao;
import br.com.MercadoJoana.Dao.DaoI;
import br.com.MercadoJoana.uteis.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class ProdutoDao extends Dao implements DaoI<Produto> {

    PreparedStatement stmt;
    ResultSet res;

    public ProdutoDao() {
        super();
    }

    @Override
    public boolean salvar(Produto obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO produto(codigo, nome, valor, quantidade, categoria_id) VALUES (?, ?, ?, ? ,?)");
            stmt.setString(1, obj.getCodigo());
            stmt.setString(2, obj.getNome());
            stmt.setDouble(3, obj.getValor());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setInt(5, obj.getCategoria().getId());
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
    public boolean atualizar(Produto obj) {
        try {
            stmt = conn.prepareStatement("UPDATE produto SET codigo = ?, nome = ?,valor = ?, quantidade = ?,"
                    + " categoria_id = ? WHERE id = ?");
            stmt.setString(1, obj.getCodigo());
            stmt.setString(2, obj.getNome());
            stmt.setDouble(3, obj.getValor());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setInt(5, obj.getCategoria().getId());
            stmt.setInt(6, obj.getId());
            if (stmt.executeUpdate()>0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluir(Produto obj) {
        try {
            stmt = conn.prepareStatement("UPDATE produto SET situacao = 0 WHERE id = ?");
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
    public List<Produto> listar() {
        try {
            stmt = conn.prepareStatement("SELECT p.id, p.codigo, p.nome, c.nome as 'Nome categoria', p.valor, p.quantidade FROM produto as p \n"
                    + "left join categoria as c on p.categoria_id = c.id\n"
                    + "WHERE p.situacao =1");
            res = stmt.executeQuery();
            List<Produto> listaP = new ArrayList<>();
            while (res.next()) {
                Produto p = new Produto();
                p.setId(res.getInt("id"));
                p.setNome(res.getString("nome"));
                p.setCodigo(res.getString("codigo"));
                p.getCategoria().setNome(res.getString("Nome categoria"));
                p.setValor(res.getDouble("valor"));
                p.setQuantidade(res.getInt("quantidade"));
                listaP.add(p);
            }
            return listaP;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produto: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Não usado no projeto!!
     *
     * @param id
     * @return
     */
    @Override
    public Produto lerPorID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Produto> pesquisaPorNome(String nome) {
        try {
            stmt = conn.prepareStatement("SELECT p.id, p.codigo, p.nome,c.nome as 'Nome categoria', p.valor, p.quantidade FROM produto as p\n"
                    + "left join categoria as c on p.categoria_id = c.id\n"
                    + "Where (p.codigo like ? AND p.situacao = 1) || (p.nome like ? AND p.situacao = 1) || (c.nome like ? AND p.situacao =1)");
            stmt.setString(1, nome + "%");
            stmt.setString(2, nome + "%");
            stmt.setString(3, nome + "%");
            res = stmt.executeQuery();
            List<Produto> listaP = new ArrayList<>();
            while (res.next()) {
                Produto p = new Produto();
                p.setId(res.getInt("id"));
                p.setCodigo(res.getString("codigo"));
                p.setNome(res.getString("nome"));
                p.getCategoria().setNome(res.getString("Nome categoria"));
                p.setValor(res.getDouble("valor"));
                p.setQuantidade(res.getInt("quantidade"));
                listaP.add(p);
            }
            return listaP;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}
