/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoRelatorios;
import br.com.marmitao.model.Encomenda;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class RelatoriosEncomendaDao extends Dao implements DaoRelatorios<Encomenda> {

    private PreparedStatement stmt;
    private ResultSet res;

    public RelatoriosEncomendaDao() {
        super();
    }

    @Override
    public List<Encomenda> listarTodos() {
        try {
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', ent.nome as 'entregador', e.dataPedido, e.dataEntrega FROM encomenda as e\n"
                    + "left join cliente as c on c.id = e.Cliente_id\n"
                    + "left join entregador as ent on e.entregador_id = ent.id where e.ativo = 1 AND (e.entregador_id is null || e.entregador_id is not null) order by e.id");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.getEntregador().setNome(res.getString("entregador"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                listaE.add(enco);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Encomenda> pesquisarPorNomeCliente(String palavra) {
        try {
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', ent.nome as 'entregador', e.dataPedido, e.dataEntrega FROM encomenda as e\n"
                    + "left join cliente as c on c.id = e.Cliente_id\n"
                    + "left join entregador as ent on e.entregador_id = ent.id where e.ativo = 1 AND (e.entregador_id is null || e.entregador_id is not null)\n"
                    + "AND c.nome like ?");
            stmt.setString(1, palavra + "%");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.getEntregador().setNome(res.getString("entregador"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                listaE.add(enco);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Encomenda> pesquisarPorNomeEntregador(String palavra) {
        try {
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', ent.nome as 'entregador', e.dataPedido, e.dataEntrega FROM encomenda as e\n"
                    + "left join cliente as c on c.id = e.Cliente_id\n"
                    + "left join entregador as ent on e.entregador_id = ent.id where e.ativo = 1 AND (e.entregador_id is null || e.entregador_id is not null)\n"
                    + "AND ent.nome like ?");
            stmt.setString(1, palavra + "%");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.getEntregador().setNome(res.getString("entregador"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                listaE.add(enco);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Encomenda> pesquisarDataEspecifica(Date palavra) {
        try {
            stmt = conn.prepareStatement("SELECT e.id, c.nome as 'cli.nome', ent.nome as 'entregador', e.dataPedido, e.dataEntrega FROM encomenda as e\n"
                    + "left join cliente as c on c.id = e.Cliente_id\n"
                    + "left join entregador as ent on e.entregador_id = ent.id where e.ativo = 1 AND (e.entregador_id is null || e.entregador_id is not null)\n"
                    + "AND e.dataPedido like ? || e.dataEntrega like ?");
            stmt.setString(1, palavra + "%");
            stmt.setString(2, palavra + "%");
            res = stmt.executeQuery();
            List<Encomenda> listaE = new ArrayList<>();
            while (res.next()) {
                Encomenda enco = new Encomenda();
                enco.setId(res.getInt("id"));
                enco.getCliente().setNome(res.getString("cli.nome"));
                enco.getEntregador().setNome(res.getString("entregador"));
                enco.setDataPedido(res.getDate("dataPedido"));
                enco.setDataEntrega(res.getDate("dataEntrega"));
                listaE.add(enco);
            }
            return listaE;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }

    }

}
