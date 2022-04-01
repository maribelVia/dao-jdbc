package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConexao() {

		try {
			final String url ="jdbc:mysql://localhost/meu_banco";
			final String usuario ="root";
			final String senha ="senhaAleatoria";
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
