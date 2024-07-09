package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import dao.DAO;

import javax.swing.JButton;
import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;

import model.ModelTabela;
import model.Paciente;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Paciente> pacientes;
	private JPrincipal jPrincipal;
	private JTextField textFieldBusca;
	private TableRowSorter<ModelTabela> rowSorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setLocationRelativeTo(frame);
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
	public JPrincipal() {
		this.jPrincipal = this;
		DAO dao = new DAO();
		try {
			pacientes = dao.listarPaciente();
		} catch (Exception e) {
			System.out.println("Não existem usuários cadastrados.");
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 68, 702, 348);
		contentPane.add(scrollPane);
		
		ModelTabela modeloTabela = new ModelTabela(pacientes);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == 1) {
					try {
						Paciente pacienteSelecionado = dao.consultarPaciente(modeloTabela.getValueAt(table.getSelectedRow(),0).toString());
						JCadastro jCadastro = new JCadastro(pacienteSelecionado, jPrincipal);
						jCadastro.setLocationRelativeTo(jCadastro);
						jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jCadastro.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jCadastro = new JCadastro(null, jPrincipal);
				jCadastro.setLocationRelativeTo(jCadastro);
				jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jCadastro.setVisible(true);
			}
		});
		btnCadastrar.setBounds(22, 34, 110, 23);
		contentPane.add(btnCadastrar);
		
		textFieldBusca = new JTextField();
		textFieldBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
				
			}
		});
		textFieldBusca.setBounds(158, 37, 566, 20);
		contentPane.add(textFieldBusca);
		textFieldBusca.setColumns(10);

		rowSorter = new TableRowSorter<>(modeloTabela);
		table.setRowSorter(rowSorter);
	}
	
	public void filtrar() {
		String busca = textFieldBusca.getText().trim();
		
		if(busca.length() == 0) {
			rowSorter.setRowFilter(null);
		}else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+busca));
		}
	}
}
