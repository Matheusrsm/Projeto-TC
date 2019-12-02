package projeto;

import java.util.HashMap;
import java.util.List;

public class Automato {

	private String inicial;
	private HashMap<String, Estado> estados;
	private List<String> aceita;

	public Automato(List<String> estados, String inicial, List<String> aceita, List<String> transicoes) {
		this.estados = new HashMap<>();
		this.aceita = aceita;
		this.inicial = inicial;
		adicionaTransicoes(transicoes);
	}

	public void adicionaTransicoes(List<String> listaDeEstados) {
		for (String string : listaDeEstados) {
			String estado = Character.toString(string.charAt(0));
			estados.put(estado, new Estado());
			if (estado == inicial) {
				estados.get(estado).setInicial(true);
			}
			if (aceita.contains(estado)) {
				estados.get(estado).setFinal(true);
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

	public List<String> getAceita() {
		return aceita;
	}

	public HashMap<String, Estado> getEstado() {
		return estados;
	}
}