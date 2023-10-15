package br.com.opticadruziani.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//nome do usuário para conectar no banco de dados
	private static final String USERNAME = "root";
	
	//senha do banco de dados
	private static final String PASSWORD = "#root250";
	
	//caminho do banco de dados, porta e nome do banco
	private static final String DATABASE_URL= "jdbc:mysql://localhost:3306/dbdruziani";
	
	/*
	 * /conexão com o SQL
	 */
	public static Connection createConnectionToMySQL() throws Exception {
		
		//faz com que a classe seja carregada na JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//recuperar conexao com banco de dados
		Connection con = createConnectionToMySQL();
		
		//testar conexao nula
		if(con!=null)
			System.out.println("Conexão obtida com sucesso!");
		con.close();
	}

}
