/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoI;
import br.com.marmitao.model.Encomenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class EncomendaPresencialDao extends Dao implements DaoI<Encomenda> {

    private PreparedStatement stmt;
    private ResultSet res;

    public EncomendaPresencialDao() {
        super();
    }

    @Override
    public boolean salvar(Encomenda obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO encomenda(dataPedido, dataEntrega, status, Cliente_id) "
                    + "VALUES(? ,? ,?, ?)");
            stmt.setDate(1, obj.getDataPedido());
            stmt.setDate(2, obj.getDataEntrega());
            stmt.setInt(3, obj.getStatus());
            stmt.setInt(4, obj.getCliente().getId());
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
    public boolean atualizar(Encomenda obj) {
        try {
            stmt = conn.prepareStatement("UPDATE encomenda SET dataPedido = ?, dataEntrega = ?, status = ?, Cliente_id = ? WHERE id = ?");
            stmt.setDate(1, obj.getDataPedido());
            stmt.setDate(2, obj.getDataEntrega());
            stmt.setInt(3, obj.getStatus());
            stmt.setInt(4, obj.getCliente().getId());
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
    public boolean excluir(Encomenda obj) {
        try {
            stmt = conn.prepareStatement("UPDATE encomenda SET ativo = 0, status = 4 WHERE id = ?");
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
    public List<Encomenda> listar() {
        try {
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', e.dataPedido, e.dataEntrega, e.status FROM encomenda as e\n"
                    + "join cliente as c on c.id = e.Cliente_id where e.ativo = 1 AND e.status between 1 AND 2");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                enco.setStatus(res.getInt("status"));
                listaE.add(enco);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Encomenda> pesquisarPorNome(String palavra) {
        try {
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', e.dataPedido, e.dataEntrega, e.status FROM encomenda as e\n"
                    + "join cliente as c on c.id = e.Cliente_id "
                    + "where e.ativo = 1 AND e.status between 1 AND 2 AND c.nome like ? || e.ativo = 1 AND e.status between 1 AND 2 AND e.id like ?");
            stmt.setString(1, palavra + "%");
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                enco.setStatus(res.getInt("status"));
                listaE.add(enco);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }
    }

    public boolean statusRecebido(Encomenda obj) {
        try {
            stmt = conn.prepareStatement("UPDATE encomenda SET status = 3 WHERE id = ?");
            stmt.setInt(1, obj.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar status: " + ex.getMessage());
            return false;
        }
    }
}
