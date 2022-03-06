package br.com.jkassner.ce.model;

public enum TipoRole {
	
	ROLE_LEITURA("Leitura"),
	ROLE_ESCRITA("Escrita"),
	ROLE_ADMIN("Administrador");
	
	private String nome;
	
	TipoRole(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public TipoRole [] getNomes() {
		return TipoRole.values();
	}
}
