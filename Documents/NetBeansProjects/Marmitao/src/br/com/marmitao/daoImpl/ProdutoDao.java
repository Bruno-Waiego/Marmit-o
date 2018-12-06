/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoI;
import br.com.marmitao.model.Produto;
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

    private PreparedStatement stmt;
    private ResultSet res;

    public ProdutoDao() {
        super();
    }

    @Override
    public boolean salvar(Produto obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO produto(nome, valor, descricao, Categoria_id) VALUES (?, ?, ? ,?)");
            stmt.setString(1, obj.getNome());
            stmt.setFloat(2, obj.getValor());
            stmt.setString(3, obj.getDescricao());
            stmt.setInt(4, obj.getCategoria().getId());
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
            stmt = conn.prepareStatement("UPDATE produto SET nome = ?, valor = ?, descricao = ?, Categoria_id = ? WHERE id = ? ");
            stmt.setString(1, obj.getNome());
            stmt.setFloat(2, obj.getValor());
            stmt.setString(3, obj.getDescricao());
            stmt.setInt(4, obj.getCategoria().getId());
            stmt.setInt(5, obj.getId());
            if (stmt.executeUpdate() > 0) {
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
            stmt = conn.prepareStatement("UPDATE produto SET ativo = 0 WHERE id = ? ");
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
            stmt = conn.prepareStatement("SELECT p.id, p.nome,p.valor, p.descricao, c.id as 'c.id', c.nome as 'c.nome' FROM produto as p\n"
                    + "inner join categoria as c on c.id = p.Categoria_id\n"
                    + " WHERE p.ativo =1");
            List<Produto> listaP = new ArrayList<>();
            res = stmt.executeQuery();
            while (res.next()) {
                Produto pro = new Produto();
                pro.setId(res.getInt("id"));
                pro.setNome(res.getString("nome"));
                pro.setValor(res.getFloat("valor"));
                pro.setDescricao(res.getString("descricao"));
                pro.getCategoria().setId(res.getInt("c.id"));
                pro.getCategoria().setNome(res.getString("c.nome"));
                listaP.add(pro);
            }
            return listaP;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Produto> pesquisarPorNome(String palavra) {
        try {
            stmt = conn.prepareStatement("SELECT p.id, p.nome,p.valor, p.descricao, c.id as 'c.id', c.nome as 'c.nome' FROM produto as p\n"
                    + " inner join categoria as c on c.id = p.Categoria_id WHERE p.ativo =1 AND p.nome like ? || p.ativo = 1 AND p.id like ?");
            stmt.setString(1, palavra + "%");
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            List<Produto> listaP = new ArrayList<>();
            while (res.next()) {
                Produto pro = new Produto();
                pro.setId(res.getInt("id"));
                pro.setNome(res.getString("nome"));
                pro.setValor(res.getFloat("valor"));
                pro.setDescricao(res.getString("descricao"));
                pro.getCategoria().setId(res.getInt("c.id"));
                pro.getCategoria().setNome(res.getString("c.nome"));
                listaP.add(pro);
            }
            return listaP;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }
    }
}
