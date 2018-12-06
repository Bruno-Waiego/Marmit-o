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
public class EncomendaDao extends Dao implements DaoI<Encomenda> {

    private PreparedStatement stmt;
    private ResultSet res;

    public EncomendaDao() {
        super();
    }

    @Override
    public boolean salvar(Encomenda obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO encomenda(dataEntrega , status, Endereco_id, Cliente_id, entregador_id, dataPedido ) "
                    + "VALUES(? ,?, ?, ?, ?, CURRENT_TIMESTAMP)");
            stmt.setDate(1, obj.getDataEntrega());
            stmt.setInt(2, obj.getStatus());
            stmt.setInt(3, obj.getEndereco().getId());
            stmt.setInt(4, obj.getCliente().getId());
            stmt.setInt(5, obj.getEntregador().getId());
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
            stmt = conn.prepareStatement("UPDATE encomenda SET dataPedido = ?, dataEntrega = ?, status = ?, Endereco_id = ?, Cliente_id = ?,"
                    + " entregador_id = ? WHERE id = ?");
            stmt.setDate(1, obj.getDataPedido());
            stmt.setDate(2, obj.getDataEntrega());
            stmt.setInt(3, obj.getStatus());
            stmt.setInt(4, obj.getEndereco().getId());
            stmt.setInt(5, obj.getCliente().getId());
            stmt.setInt(6, obj.getEntregador().getId());
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
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', en.logradouro, en.cep, ent.nome as 'entregador', e.dataPedido, e.dataEntrega, e.status, en.id as\n"
                    + "'en.id', c.id as 'c.id', ent.id as 'ent.id'  FROM encomenda as e\n"
                    + "inner join cliente as c on c.id = e.Cliente_id\n"
                    + "inner join endereco as en on en.id = e.Endereco_id\n"
                    + "inner join entregador as ent on e.entregador_id = ent.id where e.ativo = 1 AND e.status between 1 AND 2 order by e.id");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.getEndereco().setLogradouro(res.getString("logradouro"));
                enco.getEndereco().setCep(res.getString("cep"));
                enco.getEntregador().setNome(res.getString("entregador"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                enco.setStatus(res.getInt("status"));
                enco.getEndereco().setId(res.getInt("en.id"));
                enco.getCliente().setId(res.getInt("c.id"));
                enco.getEntregador().setId(res.getInt("ent.id"));
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
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', en.logradouro, en.cep, ent.nome as 'entregador', e.dataPedido, e.dataEntrega, e.status, en.id as\n"
                    + "'en.id', c.id as 'c.id', ent.id as 'ent.id'  FROM encomenda as e\n"
                    + "inner join cliente as c on c.id = e.Cliente_id\n"
                    + "inner join endereco as en on en.id = e.Endereco_id\n"
                    + "inner join entregador as ent on e.entregador_id = ent.id where e.ativo = 1 AND e.status between 1 AND 2 AND c.nome like ? ||"
                    + " e.ativo = 1 AND e.status between 1 AND 2 AND ent.nome like ?");
            stmt.setString(1, palavra + "%");
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.getEndereco().setLogradouro(res.getString("logradouro"));
                enco.getEndereco().setCep(res.getString("cep"));
                enco.getEntregador().setNome(res.getString("entregador"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                enco.setStatus(res.getInt("status"));
                enco.getEndereco().setId(res.getInt("en.id"));
                enco.getCliente().setId(res.getInt("c.id"));
                enco.getEntregador().setId(res.getInt("ent.id"));
                listaE.add(enco);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }

    }

    public boolean statusEnviado(Encomenda obj) {
        try {
            stmt = conn.prepareStatement("UPDATE encomenda SET status = 2 WHERE id = ?");
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

    public boolean statusRecebido(Encomenda obj) {
        try {
            stmt = conn.prepareStatement("UPDATE encomenda SET status = 3, dataEntrega = now() WHERE id = ?");
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
