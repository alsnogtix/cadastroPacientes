package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelTabela extends AbstractTableModel{
	
	private static final String[] colunas = {"C\u00F3digo", "Nome", "Nascimento", "Endere\u00E7o", "Observa\u00E7\u00F5es"};
	
	
	
	public ModelTabela(ArrayList<Paciente> pacientes) {
		super();
		this.pacientes = pacientes;
	}

	private ArrayList<Paciente> pacientes;

	@Override
	public int getRowCount() {
		if(pacientes != null)
		return pacientes.size();
		return 0;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Paciente paciente = pacientes.get(rowIndex);
		if(columnIndex == 0) {
			return paciente.getCodigo();
		}else if(columnIndex == 1){
			return paciente.getNome();
		}else if(columnIndex == 2) {
			return paciente.getNascimento();
		}else if(columnIndex == 3) {
			return paciente.getEndereco();
		}else if(columnIndex == 4) {
			return paciente.getObservacoes();
		}else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

}
