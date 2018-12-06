/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoI;
import br.com.marmitao.model.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class EnderecoDao extends Dao implements DaoI<Endereco> {

    private PreparedStatement stmt;
    private ResultSet res;

    public EnderecoDao() {
        super();
    }

    @Override
    public boolean salvar(Endereco obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO endereco(logradouro, bairro, cidade, cep, complemento, Cliente_id) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, obj.getLogradouro());
            stmt.setString(2, obj.getBairro());
            stmt.setString(3, obj.getCidade());
            stmt.setString(4, obj.getCep());
            stmt.setString(5, obj.getComplemento());
            stmt.setInt(6, obj.getCliente().getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar" + ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean atualizar(Endereco obj) {
        try {
            stmt = conn.prepareStatement("UPDATE endereco SET logradouro=?, bairro=?, cidade=?, cep=?, complemento=?, Cliente_id=? WHERE id=?");
            stmt.setString(1, obj.getLogradouro());
            stmt.setString(2, obj.getBairro());
            stmt.setString(3, obj.getCidade());
            stmt.setString(4, obj.getCep());
            stmt.setString(5, obj.getComplemento());
            stmt.setInt(6, obj.getCliente().getId());
            stmt.setInt(7, obj.getId());
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
    public boolean excluir(Endereco obj) {
        try {
            stmt = conn.prepareStatement("UPDATE endereco SET ativo = 0 Where id=?");
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
    public List<Endereco> listar() {
        try {
            List<Endereco> listaE = new ArrayList<>();
            stmt = conn.prepareStatement("SELECT * FROM endereco WHERE id = 0");
            res = stmt.executeQuery();
            while (res.next()) {
                Endereco end = new Endereco();
                end.setId(res.getInt("id"));
                end.setLogradouro(res.getString("logradouro"));
                end.setBairro(res.getString("bairro"));
                end.setCidade(res.getString("cidade"));
                end.setCep(res.getString("cep"));
                end.setComplemento(res.getString("complemento"));
                listaE.add(end);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Endereco> pesquisarPorNome(String palavra) {
        try {
            List<Endereco> listaE = new ArrayList<>();
            stmt = conn.prepareStatement("SELECT e.id, e.logradouro, e.bairro, e.cidade, e.cep, e.complemento FROM endereco as e\n"
                    + "left join cliente as c on e.Cliente_id = c.id AND e.ativo = 1 WHERE c.ativo = 1 AND e.logradouro like ? || c.ativo = 1 AND e.id like ?");
            stmt.setString(1, palavra + "%");
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            while (res.next()) {
                Endereco end = new Endereco();
                end.setId(res.getInt("id"));
                end.setLogradouro(res.getString("logradouro"));
                end.setBairro(res.getString("bairro"));
                end.setCidade(res.getString("cidade"));
                end.setCep(res.getString("cep"));
                end.setComplemento(res.getString("complemento"));
                listaE.add(end);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        }
    }

    public List<Endereco> tabelaVinculoEndereco(int id) {
        try {
            List<Endereco> listaE = new ArrayList<>();
            stmt = conn.prepareStatement("SELECT * FROM endereco WHERE cliente_id like ? AND ativo = 1");
            stmt.setInt(1, id);
            res = stmt.executeQuery();
            while (res.next()) {
                Endereco end = new Endereco();
                end.setId(res.getInt("id"));
                end.setLogradouro(res.getString("logradouro"));
                end.setBairro(res.getString("bairro"));
                end.setCidade(res.getString("cidade"));
                end.setCep(res.getString("cep"));
                end.setComplemento(res.getString("complemento"));
                listaE.add(end);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar vinculo: " + ex.getMessage());
            return null;
        }
    }
}
