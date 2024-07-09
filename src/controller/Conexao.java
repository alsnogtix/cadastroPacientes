package controller;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class Conexao {
	private static String url;
	private static String usuario;
	private static String senha;
	private static String driver;
	private static Connection con;
	private static Conexao instancia;
	
	public Connection abrirConexao() {
		url = "jdbc:postgresql://localhost:5432/postgres";
		usuario = "postgres";
		senha = "12345";
		driver = "org.postgresql.Driver";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, senha);
			con.setAutoCommit(false);
			System.out.println("Conexão estabelecida com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao conectar com o banco de dados " + e.getMessage());
		}
		return con;
	}
	
	public static Conexao getInstancia() {
		if(instancia == null) instancia = new Conexao();
		return instancia;
	}
	
	public void fecharConexao(){
		try {
			if(con != null && !con.isClosed()) con.close();
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexao " + e.getMessage());
		}finally {
			con = null;
		}
		
	}

}
