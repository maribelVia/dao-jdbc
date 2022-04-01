package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	private Connection conexao;
	
	public void menu() {
		System.out.println("******************* MENU *******************");
		System.out.println("1. Buscar ");
		System.out.println("2. Adicionar ");
		System.out.println("3. Editar ");
		System.out.println("4. Excluir ");
		System.out.println("5. Sair ");
		System.out.println("Digite a opcao desejada : ");
	}
	
	public void close() {
		try {
			getConexao().close();
		}catch(SQLException e) {
			
		}finally {
			conexao = null;
		}
	}
	
	public void excluir(String sql, int id) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			
			System.out.println("Sucesso!");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public void editar(String sql, String novoNome, int id) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setString(1,novoNome);
			stmt.setInt(2, id);
			stmt.execute();
			
			System.out.println("Sucesso!");
		} catch (SQLException e) {
			throw new RuntimeException();		}
	}
	
	public void buscar(String sql, String chave) {
		ResultSet resultado;
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setString(1,"%" + chave + "%");
			stmt.execute();
			resultado = stmt.executeQuery();
			
			System.out.println("Sucesso!");
			
			while(resultado.next()) {
				int codigo = resultado.getInt("codigo");
				String nome = resultado.getString("nome");
				System.out.println(codigo + " "+ nome);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int incluir(String sql, Object... atributos) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql); 
			adicionarAtributos(stmt, atributos);
			
			return -1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void adicionarAtributos(PreparedStatement stmt, 
			Object[] atributos) throws SQLException{
		int indice = 1;
		for(Object atributo : atributos) {
			if(atributo instanceof String) {
				stmt.setString(indice, (String) atributo);
				stmt.execute();
				System.out.println("Sucesso!");
			}
			indice++;
		}
	}
	
	private Connection getConexao(){
		
		try {
			if(conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexao = Conexao.getConexao();
		return conexao;
	}
	
}
