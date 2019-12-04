package projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automato {

	private String inicial;
	private Map<String, Estado> mapaDeEstados;
	private List<String> aceita, transicoes, listaDeEstados;

	public Automato(List<String> listaDeEstados, String inicial, List<String> aceita, List<String> transicoes) {
		this.mapaDeEstados = new HashMap<>();
		this.aceita = aceita;
		this.inicial = inicial;
		this.transicoes = transicoes;
		this.listaDeEstados = listaDeEstados;
		adicionaTransicoesAosEstados();
	}

	public void adicionaTransicoesAosEstados() {
		for (String transicao : transicoes) {
			String estado1 = Character.toString(transicao.charAt(0));
			mapaDeEstados.put(estado1, new Estado(estado1));
			if (estado1.equals(inicial)) {
				mapaDeEstados.get(estado1).setInicial(true);
			}
			if (aceita.contains(estado1)) {
				mapaDeEstados.get(estado1).setFinal(true);
			}
		}
		
		for (String estado : listaDeEstados) {
			if (!mapaDeEstados.containsKey(estado))
				mapaDeEstados.put(estado, new Estado(estado));
			if (aceita.contains(estado))
				mapaDeEstados.get(estado).setFinal(true);
		}

		for (String string : transicoes) {
			String estado = Character.toString(string.charAt(0));
			String proxEstado = Character.toString(string.charAt(2));
			String valorDaTransicao = Character.toString(string.charAt(4));
			mapaDeEstados.get(estado).addTransicao(mapaDeEstados.get(proxEstado), valorDaTransicao);
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

	public Map<String, Estado> getMapaDeEstados() {
		return mapaDeEstados;
	}

	public List<String> getTransicoes() {
		return transicoes;
	}

	public List<String> getListaDeEstados() {
		return listaDeEstados;
	}

	public void mudaEstadosComplemento() {
		List<String> novosAceita = new ArrayList<>();
		for (String estado : mapaDeEstados.keySet()) {
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
		for (String estado : mapaDeEstados.keySet()) {
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