/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoI;
import br.com.marmitao.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ClienteDao extends Dao implements DaoI<Cliente> {

    private PreparedStatement stmt;
    private ResultSet res;

    public ClienteDao() {
        super();
    }

    @Override
    public boolean salvar(Cliente obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO cliente(nome, telefone, dataNascimento, email) VALUES (?, ?, ?, ?)");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTelefone());
            stmt.setDate(3, obj.getDataNascimento());
            stmt.setString(4, obj.getEmail());
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
    public boolean atualizar(Cliente obj) {
        try {
            stmt = conn.prepareStatement("UPDATE cliente SET nome=?,telefone=?,dataNascimento=?, email=? WHERE id=?");
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTelefone());
            stmt.setDate(3, obj.getDataNascimento());
            stmt.setString(4, obj.getEmail());
            stmt.setInt(5, obj.getId());
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
    public boolean excluir(Cliente obj) {
        try {
            stmt = conn.prepareStatement("UPDATE cliente SET ativo = 0 WHERE id=?");
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
    public List<Cliente> listar() {
        try {
            stmt = conn.prepareStatement("SELECT * FROM cliente WHERE ativo = 1");
            res = stmt.executeQuery();
            List<Cliente> listaCli = new ArrayList<>();
            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("dataNascimento"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setEmail(res.getString("email"));
                listaCli.add(cliente);
            }
            return listaCli;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        } finally {
            res = null;
            stmt = null;
        }
    }

    @Override
    public List<Cliente> pesquisarPorNome(String palavra) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM cliente WHERE ativo = 1 AND nome like ? || ativo = 1 AND id like ?");
            stmt.setString(1, palavra + "%"); //mudar 
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            List<Cliente> listacli = new ArrayList<>();
            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("dataNascimento"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setEmail(res.getString("email"));
                listacli.add(cliente);
            }
            return listacli;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar por nome: " + ex.getMessage());
            return null;
        }

    }

    public List<Cliente> listarClientesEncomenda() {
        try {
            stmt = conn.prepareStatement("SELECT c.id, c.nome, c.dataNascimento, c.email, c.telefone, c.ativo FROM cliente as c\n"
                    + "right join endereco as e on c.id = e.Cliente_id\n"
                    + "WHERE c.ativo = 1");
            res = stmt.executeQuery();
            List<Cliente> listaCli = new ArrayList<>();
            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("dataNascimento"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setEmail(res.getString("email"));
                listaCli.add(cliente);
            }
            return listaCli;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        } finally {
            res = null;
            stmt = null;
        }
    }

}
