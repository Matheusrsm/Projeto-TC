package projeto;

import java.util.List;

public class Automato {

	private String inicial;
	private List<String> estados, aceita, transicoes;

	public Automato(List<String> estados, String inicial, List<String> aceita, List<String> transicoes) {
		this.estados = estados;
		this.inicial = inicial;
		this.aceita = aceita;
		this.transicoes = transicoes;
	}

	public String getInicial() {
		return inicial;
	}

	public List<String> getEstados() {
		return estados;
	}

	public List<String> getAceita() {
		return aceita;
	}

	public List<String> getTransicoes() {
		return transicoes;
	}

	public String toStringLista(List<String> lista) {
		String string = "\n";
		for (String elemento : lista)
			string += elemento + "\n";
		return string;
	}

	@Override
	public String toString() {
		return "estados:" + toStringLista(this.estados) + "\ninicial:\n" + this.inicial + "\n\naceita:"
				+ toStringLista(this.aceita) + "\ntransições:" + toStringLista(this.transicoes);
	}
}
