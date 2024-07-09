package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import dao.DAO;
import model.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldNascimento;
	private JTextField textFieldEndereco;
	private JTextArea textAreaObservacoes;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCadastro(Paciente pacienteSelecionado, JPrincipal jPrincipal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 11, 58, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 26, 397, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNascimento = new JLabel("Nascimento");
		lblNascimento.setBounds(10, 53, 77, 14);
		contentPane.add(lblNascimento);
		
		textFieldNascimento = new JTextField();
		textFieldNascimento.setBounds(10, 67, 173, 20);
		contentPane.add(textFieldNascimento);
		textFieldNascimento.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(10, 98, 77, 14);
		contentPane.add(lblEndereco);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(10, 111, 397, 20);
		contentPane.add(textFieldEndereco);
		textFieldEndereco.setColumns(10);
		
		JLabel lblObservacoes = new JLabel("Observações");
		lblObservacoes.setBounds(10, 142, 77, 14);
		contentPane.add(lblObservacoes);
		
		textAreaObservacoes = new JTextArea();
		textAreaObservacoes.setBorder(new LineBorder(new Color(150, 150, 150)));
		textAreaObservacoes.setBounds(10, 158, 397, 114);
		contentPane.add(textAreaObservacoes);
		
		JButton btnSalvar = new JButton(pacienteSelecionado == null ? "Inserir" : "Alterar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Paciente paciente = new Paciente(null, textFieldNome.getText(), textFieldNascimento.getText(), textFieldEndereco.getText(), textAreaObservacoes.getText());
				if(pacienteSelecionado == null) {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textAreaObservacoes.getText()) && !"".equalsIgnoreCase(textFieldEndereco.getText()) && !"".equalsIgnoreCase(textFieldNascimento.getText())) {
						dao.cadastrarPaciente(paciente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
					}
					
				}else {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textAreaObservacoes.getText()) && !"".equalsIgnoreCase(textFieldEndereco.getText()) && !"".equalsIgnoreCase(textFieldNascimento.getText())) {
						dao.alterarPaciente(pacienteSelecionado.getCodigo(), paciente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
					}
					
				}
			}
		});
		btnSalvar.setBounds(318, 283, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirPaciente(pacienteSelecionado.getCodigo());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnExcluir.setBackground(new Color(239, 63, 58));
		btnExcluir.setBounds(218, 283, 89, 23);
		btnExcluir.setVisible(false);
		contentPane.add(btnExcluir);
		
		if(pacienteSelecionado != null) {
			preencherCampos(pacienteSelecionado);
			btnExcluir.setVisible(true);
		}
	}
	
	private void preencherCampos(Paciente pacienteSelecionado) {
		textFieldNome.setText(pacienteSelecionado.getNome());
		textFieldNascimento.setText(pacienteSelecionado.getNascimento());
		textFieldEndereco.setText(pacienteSelecionado.getEndereco());
		textAreaObservacoes.setText(pacienteSelecionado.getObservacoes());
		
		
	}
	
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose();
		dispose();
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);
	}
	
	
	
}
