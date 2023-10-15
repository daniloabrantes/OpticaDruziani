package br.com.opticadruziani.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.opticadruziani.factory.ConnectionFactory;
import br.com.opticadruziani.model.Produto;

public class OpticaDao {
	
	/*
	 * CRUD
	 * CREATE (save)
	 * READ (list)
	 * UPDATE
	 * DELETE
	 */

	public void save (Produto produto){
		
	//insere um produto(Create)
		String sql = "INSERT INTO PRODUTOS (nome, preco, descricao) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//tenta criar uma conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//cria uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adiciona os valores esperados pela query
			pstm.setString(1, produto.getNome());
			pstm.setFloat(2, produto.getPreco());
			pstm.setString(3, produto.getDescricao());
			
			//executa a query
			pstm.execute();
			System.out.println("Salvo");
			System.out.println("");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
						
			//fechar conexões
			try {
				if (pstm!=null) {
					pstm.close();
				}
				if (conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Produto> getProdutos(){
		
		//seleciona os produtos no banco(Read)
		String sql = "SELECT * FROM PRODUTOS";
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//recupera os dados do banco
		ResultSet rset = null;
		
		try {
			//tenta criar uma conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//cria uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//executa a query
			rset = pstm.executeQuery();
			System.out.println("Lista criada");
			
			while (rset.next()) {
				
				Produto produto = new Produto();
				
				/*
				 * Separar os dados colhidos do banco
				 * referenciar as variaveis criadas no arquivo produto
				 * armazenar os dados organizados na lista
				 */
				//recuperar Id
				produto.setId(rset.getInt("id"));
				//recuperar nome
				produto.setNome(rset.getString("nome"));
				//recuperar preco
				produto.setPreco(rset.getFloat("preco"));
				//recuperar descrição
				produto.setDescricao(rset.getString("descricao"));
				//salva o produto separado na lista
				produtos.add(produto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
						
			//fechar conexões
			try {
				if (pstm!=null) {
					pstm.close();
				}
				if (conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}

		return produtos;
	}
	
	public void update (Produto produto){
		
		String sql = "UPDATE PRODUTOS SET nome = ?, preco = ?, descricao = ?" + "WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//tenta criar uma conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//cria uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//atualiza os valores referenciados pela query
			pstm.setString(1, produto.getNome());
			pstm.setFloat(2, produto.getPreco());
			pstm.setString(3, produto.getDescricao());
			
			//qual o id do produto
			pstm.setInt(4, produto.getId());
						
			//executa a query
			pstm.execute();
			System.out.println("Salvo");
			System.out.println("");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
						
			//fechar conexões
			try {
				if (pstm!=null) {
					pstm.close();
				}
				if (conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	public void deleteByID(int id){
		
		String sql = "DELETE FROM PRODUTOS WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//tenta criar uma conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//cria uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//qual o id do produto
			pstm.setInt(1, id);
						
			//executa a query
			pstm.execute();
			System.out.println("Deletado");
			System.out.println("");
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
						
			//fechar conexões
			try {
				if (pstm!=null) {
					pstm.close();
				}
				if (conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
}
