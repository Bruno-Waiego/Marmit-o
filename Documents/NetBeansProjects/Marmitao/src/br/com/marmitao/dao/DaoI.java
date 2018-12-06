/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marmitao.dao;

import java.util.List;

/**
 *
 * @author Bruno
 */
public interface DaoI<T> {

    public boolean salvar(T obj);

    public boolean atualizar(T obj);

    public boolean excluir(T obj);

    public List<T> listar();

    public List<T> pesquisarPorNome(String palavra);
}
