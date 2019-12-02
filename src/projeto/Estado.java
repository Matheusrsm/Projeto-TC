package projeto;

import java.util.HashMap;
import java.util.Map;

public class Estado {

	private Map<String, Estado> transicoes;
	private boolean isFinal;
	private boolean isInicial;

	public Estado() {
		super();
		this.transicoes = new HashMap<String, Estado>();
	}

	public Map<String, Estado> getTransicoes() {
		return transicoes;
	}

	public void setTransicao(Map<String, Estado> transicao) {
		this.transicoes = transicao;
	}

	public void addTransicao(Estado estado, String entrada) {
		this.transicoes.put(entrada, estado);
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isInicial() {
		return isInicial;
	}

	public void setInicial(boolean isInicial) {
		this.isInicial = isInicial;
	}

	public boolean teste() {
		return this.isFinal;
	}
}