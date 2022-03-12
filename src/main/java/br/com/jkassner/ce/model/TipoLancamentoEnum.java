package br.com.jkassner.ce.model;

public enum TipoLancamentoEnum {

	ENTRADA("Entrada"), 
	SAIDA("Saída");
	
	private String tipo;
	
	private TipoLancamentoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
