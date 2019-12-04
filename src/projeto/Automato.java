package projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automato {

	private String inicial;
	private Map<String, Estado> estados;
	private List<String> aceita, transicoes;

	public Automato(String inicial, List<String> aceita, List<String> transicoes) {
		this.estados = new HashMap<>();
		this.aceita = aceita;
		this.inicial = inicial;
		this.transicoes = transicoes;
		adicionaTransicoesAosEstados(transicoes);
	}

	public void adicionaTransicoesAosEstados(List<String> listaDeEstados) {
		for (String estado : listaDeEstados) {
			String rotuloDoEstado = Character.toString(estado.charAt(0));
			estados.put(rotuloDoEstado, new Estado(rotuloDoEstado));
			if (rotuloDoEstado.equals(inicial)) {
				estados.get(rotuloDoEstado).setInicial(true);
			}
			if (aceita.contains(rotuloDoEstado)) {
				estados.get(rotuloDoEstado).setFinal(true);
			}
		}

		for (String string : listaDeEstados) {
			String estado = Character.toString(string.charAt(0));
			String proxEstado = Character.toString(string.charAt(2));
			String valorDaTransicao = Character.toString(string.charAt(4));
			estados.get(estado).addTransicao(estados.get(proxEstado), valorDaTransicao);
		}
	}

	public String getInicial() {
		return inicial;
	}
	
	public void setInicial(String novoInicial) {
		this.inicial = novoInicial;
	}

	public List<String> getAceita() {
		return aceita;
	}

	public void setAceita(List<String> novosAceita) {
		this.aceita = novosAceita;
	}

	public Map<String, Estado> getEstados() {
		return estados;
	}
	
	public List<String> getTransicoes() {
		return transicoes;
	}

	public void mudaEstadosComplemento() {
		List<String> novosAceita = new ArrayList<>();
		for (String estado : estados.keySet()) {
			if (!aceita.contains(estado)) {
				novosAceita.add(estado);
			}
		}
		setAceita(novosAceita);
	}

	public String toStringLista(List<String> lista) {
		String string = "\n";
		for (String elemento : lista)
			string += elemento + "\n";
		return string;
	}

	public String toStringEstados() {
		String string = "\n";
		for (String estado : estados.keySet()) {
			string += estado + "\n";
		}
		return string;
	}

	@Override
	public String toString() {
		return "estados:" + toStringEstados() + "\ninicial:\n" + this.inicial + "\n\naceita:"
				+ toStringLista(this.aceita) + "\ntransições:" + toStringLista(this.transicoes);
	}
}