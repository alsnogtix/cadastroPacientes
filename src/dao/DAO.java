package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Paciente;

public class DAO {
	public static PreparedStatement preparedStatment = null;
	private static ResultSet resultSet = null; 
	
	private static String CADASTRAR_PACIENTE = "INSERT INTO PACIENTE (CODIGO, NOME, NASCIMENTO, ENDERECO, OBSERVACOES) "
											 + "VALUES (DEFAULT, ?, ?, ?, ?)";
	
	private static String CONSULTAR_PACIENTE = "SELECT * FROM PACIENTE "
											 + "WHERE CODIGO = ?";


	private static String ALTERAR_PACIENTE = "UPDATE PACIENTE SET "
										   + "NOME = ?, NASCIMENTO= ?, ENDERECO = ?, OBSERVACOES = ? "
										   + "WHERE CODIGO = ?";
	
	private static String EXCLUIR_PACIENTE = "DELETE FROM PACIENTE " 
			   							   + "WHERE CODIGO = ?";
	
	private static String LISTAR_PACIENTE = "SELECT * FROM PACIENTE "
										  + "WHERE 1 = 1";
	
	public DAO() {
		
	}
	
	private void fecharConexao() {
		try {
			if(resultSet != null) resultSet.close();
			if(preparedStatment != null) preparedStatment.close();
			Conexao.getInstancia().fecharConexao();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cadastrarPaciente(Paciente paciente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_PACIENTE;
		try {
			preparedStatment = connection.prepareStatement(query);
			
			preparedStatment.setString(1, paciente.getNome());
			preparedStatment.setString(2, paciente.getNascimento());
			preparedStatment.setString(3, paciente.getEndereco());
			preparedStatment.setString(4, paciente.getObservacoes());
			
			preparedStatment.executeUpdate();
			
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso.");
			
		} catch (SQLException e) {
			System.out.println("Não foi possivel cadastrar o paciente. " + e.getMessage());
		} finally {
			fecharConexao();
		}
		
	}
	
	
	public Paciente consultarPaciente(String codigo) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Paciente paciente = null;
		String query = CONSULTAR_PACIENTE;
		try {
			preparedStatment = connection.prepareStatement(query);
			
			preparedStatment.setInt(1, Integer.parseInt(codigo));
			
			resultSet = preparedStatment.executeQuery();
			
			while(resultSet.next()) {
				paciente = new Paciente(resultSet.getString("CODIGO"),
										resultSet.getString("NOME"), 
										resultSet.getString("NASCIMENTO"), 
										resultSet.getString("ENDERECO"), 
										resultSet.getString("OBSERVACOES"));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o paciente. " + e.getMessage());
		} finally {
			fecharConexao();
		}
		
		if(paciente == null) {
			JOptionPane.showMessageDialog(null, "Paciente não encontrado.", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não foi possivel retornar o paciente selecionado.");
		}
		
		return paciente;
		
	}

	public void alterarPaciente(String codigo, Paciente paciente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = ALTERAR_PACIENTE;
		try {
			preparedStatment = connection.prepareStatement(query);
			
			preparedStatment.setString(1, paciente.getNome());
			preparedStatment.setString(2, paciente.getNascimento());
			preparedStatment.setString(3, paciente.getEndereco());
			preparedStatment.setString(4, paciente.getObservacoes());
			preparedStatment.setInt(5, Integer.parseInt(codigo));
			
			System.out.println(preparedStatment.toString());
			preparedStatment.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso.");
			
		} catch (SQLException e) {
			System.out.println("Não foi possivel alterar o paciente. " + e.getMessage());
		} finally {
			fecharConexao();
		}
		
	}

	public void excluirPaciente(String codigo) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = EXCLUIR_PACIENTE;
		try {
			preparedStatment = connection.prepareStatement(query);
			
			preparedStatment.setInt(1, Integer.parseInt(codigo));
			
			preparedStatment.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Paciente excluido com sucesso.");
			
		} catch (SQLException e) {
			System.out.println("Não foi possivel excluir o paciente. " + e.getMessage());
		} finally {
			fecharConexao();
		}
		
	}

	public ArrayList<Paciente> listarPaciente() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		ArrayList<Paciente> pacientes = new ArrayList<>();
		String query = LISTAR_PACIENTE;
		try {
			preparedStatment = connection.prepareStatement(query);
			
			resultSet = preparedStatment.executeQuery();

			while(resultSet.next()) {
				pacientes.add(new Paciente(resultSet.getString("CODIGO"),
										   resultSet.getString("NOME"), 
										   resultSet.getString("NASCIMENTO"), 
										   resultSet.getString("ENDERECO"), 
										   resultSet.getString("OBSERVACOES")));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o paciente. " + e.getMessage());
		} finally {
			fecharConexao();
		}
		
		if(pacientes.size() <= 0) {
			JOptionPane.showMessageDialog(null, "Não há pacientes cadastrados.", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há pacientes cadastrados.");
		}
		
		return pacientes;
		
	}

}

