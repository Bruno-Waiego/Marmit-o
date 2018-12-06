/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.daoImpl;

import br.com.marmitao.dao.Dao;
import br.com.marmitao.dao.DaoI;
import br.com.marmitao.model.EncomendaProduto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class EncomendaProdutoDao extends Dao implements DaoI<EncomendaProduto> {

    private PreparedStatement stmt;
    private ResultSet res;

    public EncomendaProdutoDao() {
        super();
    }

    @Override
    public boolean salvar(EncomendaProduto obj) {
        try {
            stmt = conn.prepareStatement("INSERT INTO encomenda_produtos(quantidade, valor, Produto_id, Encomenda_id) "
                    + "VALUES (?, ?, ?, ?)");
            stmt.setInt(1, obj.getQuantidade());
            stmt.setFloat(2, obj.getValor());
            stmt.setInt(3, obj.getProduto().getId());
            stmt.setInt(4, obj.getEncomenda().getId());
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
    public boolean atualizar(EncomendaProduto obj) {
        try {
            stmt = conn.prepareStatement("UPDATE encomenda_produtos SET quantidade = ?, valor = ?, Produto_id = ? , Encomenda_id = ? "
                    + "WHERE id = ?");
            stmt.setInt(1, obj.getQuantidade());
            stmt.setFloat(2, obj.getValor());
            stmt.setInt(3, obj.getProduto().getId());
            stmt.setInt(4, obj.getEncomenda().getId());
            stmt.setInt(5, obj.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao atualizar: " + ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean excluir(EncomendaProduto obj) {
        try {
            stmt = conn.prepareStatement("UPDATE encomenda_produtos SET ativo = 0 Where id = ?");
            stmt.setInt(1, obj.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao excluir: " + ex.getMessage());
            return false;
        }
    }
    /**
     * Metodo nao utilizado, utilizado o listarVinculoEncomendaProduto para listar a encomenda da combobox
     * @return 
     */
    @Override
    public List<EncomendaProduto> listar() {
        return null;
    }

    public List<EncomendaProduto> listarVinculoEncomendaProduto(int id) {
        try {
            stmt = conn.prepareStatement("SELECT ep.id, ep.quantidade, ep.valor, ep.Produto_id, ep.Encomenda_id, e.id as 'e.id', e.status, e.dataPedido, e.dataEntrega, e.Endereco_id,\n"
                    + "e.Cliente_id,e.entregador_id, p.id as 'p.id', p.nome as 'p.nome', p.valor as 'p.valor', p.descricao, p.Categoria_id, cate.nome as 'cate.nome',\n"
                    + "en.id as 'en.id', en.logradouro, en.bairro, en.cidade, en.cep, en.complemento,\n"
                    + "ent.id as 'ent.id', ent.nome as 'ent.nome', ent.cnh, ent.telefone as 'ent.telefone',\n"
                    + "c.id as 'c.id', c.nome as 'c.nome', c.dataNascimento, c.email, c.telefone\n"
                    + "FROM encomenda_produtos as ep\n"
                    + "inner join encomenda as e on ep.Encomenda_id = e.id\n"
                    + "inner join produto as p on ep.Produto_id = p.id\n"
                    + "inner join endereco as en on en.id = e.Endereco_id\n"
                    + "inner join entregador as ent on ent.id = e.entregador_id\n"
                    + "inner join categoria as cate on p.Categoria_id = cate.id\n"
                    + "inner join cliente as c on e.Cliente_id =c.id where e.id like ? AND e.ativo = 1 AND ep.ativo = 1");
            stmt.setInt(1, id);
            res = stmt.executeQuery();
            List<EncomendaProduto> listaEncoP = new ArrayList<>();
            while (res.next()) {
                EncomendaProduto encoP = new EncomendaProduto();
                encoP.setId(res.getInt("id"));
                encoP.setQuantidade(res.getInt("quantidade"));
                encoP.setValor(res.getFloat("valor"));

                encoP.getEncomenda().setId(res.getInt("e.id"));
                encoP.getEncomenda().setStatus(res.getInt("status"));
                encoP.getEncomenda().setDataPedido(res.getDate("dataPedido"));
                encoP.getEncomenda().setDataEntrega(res.getDate("dataEntrega"));

                encoP.getProduto().setId(res.getInt("p.id"));
                encoP.getProduto().setNome(res.getString("p.nome"));
                encoP.getProduto().setValor(res.getFloat("p.valor"));
                encoP.getProduto().setDescricao(res.getString("descricao"));
                encoP.getProduto().getCategoria().setId(res.getInt("Categoria_id"));
                encoP.getProduto().getCategoria().setNome(res.getString("cate.nome"));

                encoP.getEncomenda().getEndereco().setId(res.getInt("en.id"));
                encoP.getEncomenda().getEndereco().setLogradouro(res.getString("logradouro"));
                encoP.getEncomenda().getEndereco().setBairro(res.getString("bairro"));
                encoP.getEncomenda().getEndereco().setCidade(res.getString("cidade"));
                encoP.getEncomenda().getEndereco().setCep(res.getString("cep"));
                encoP.getEncomenda().getEndereco().setComplemento(res.getString("complemento"));

                encoP.getEncomenda().getEntregador().setId(res.getInt("ent.id"));
                encoP.getEncomenda().getEntregador().setNome(res.getString("ent.nome"));
                encoP.getEncomenda().getEntregador().setCnh(res.getString("cnh"));
                encoP.getEncomenda().getEntregador().setTelefone(res.getString("telefone"));

                encoP.getEncomenda().getCliente().setId(res.getInt("c.id"));
                encoP.getEncomenda().getCliente().setNome(res.getString("c.nome"));
                encoP.getEncomenda().getCliente().setDataNascimento(res.getDate("dataNascimento"));
                encoP.getEncomenda().getCliente().setTelefone(res.getString("telefone"));
                encoP.getEncomenda().getCliente().setEmail(res.getString("email"));

                listaEncoP.add(encoP);
            }
            return listaEncoP;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        }

    }

    //pesquisa nao usada
    @Override
    public List<EncomendaProduto> pesquisarPorNome(String palavra) {
        return null;
    }
}
