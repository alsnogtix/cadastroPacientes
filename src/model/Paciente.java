package model;

public class Paciente {
	protected String codigo;
	protected String nome;
	protected String nascimento;
	protected String endereco;
	protected String observacoes;
	
	
	public Paciente() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Paciente(String codigo, String nome, String nascimento, String endereco, String observacoes) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.nascimento = nascimento;
		this.endereco = endereco;
		this.observacoes = observacoes;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNascimento() {
		return nascimento;
	}


	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	
	
	
	
	

}
