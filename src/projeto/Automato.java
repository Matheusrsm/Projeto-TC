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
	
	public void transicao(String cadeia) {
		System.out.println(transicoes);
		String estadoAtual = inicial;
		System.out.println(aceita);
		System.out.println(inicial);
		int percorre = 0;
		
		while(percorre != cadeia.length()) {
			String altera = estadoAtual;
			for(int i = 0; i < transicoes.size(); i++) {
			String primeiroEstado = Character.toString(transicoes.get(i).charAt(0));
			String segundoEstado =	Character.toString(transicoes.get(i).charAt(2));	
			char leitura  = transicoes.get(i).charAt(4);
			
				if (primeiroEstado.equals(estadoAtual) && cadeia.charAt(percorre) == leitura) {
					System.out.println("true");
					altera = segundoEstado;
					System.out.println(primeiroEstado + " " + segundoEstado + " " + leitura);
					System.out.println(cadeia.charAt(percorre));
				}
			}
			estadoAtual = altera;
			percorre += 1;
		}
		System.out.println(estadoAtual);
		if(aceita.contains(estadoAtual)) {
			System.out.println("deu Bom");
		} else {
			System.out.println("deu ruim");
		}
	}
}
