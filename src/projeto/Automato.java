package projeto;

import java.util.HashMap;
import java.util.List;

public class Automato {

	private String inicial;
	private HashMap<String, Estado> estado;
	private List<String> aceita;

	public Automato(List<String> estados, String inicial, List<String> aceita, List<String> transicoes) {
		this.estado = new HashMap<>();
		this.aceita = aceita;
		this.inicial = inicial;
		converte(transicoes);
	}
	
	public void converte(List<String> estados) {
		 for (String string : estados) {
			 String state = Character.toString(string.charAt(0));
			 estado.put(state, new Estado());
			 if(state == inicial) {
				 estado.get(state).setInicial(true);
			 }
			 if(aceita.contains(state)) {
				 estado.get(state).setFinal(true);
			 }
		 }
		 
		 
		 for (String string : estados) {
			 String state = Character.toString(string.charAt(0));
			 String proxEstado = Character.toString(string.charAt(2));
			 String transicao = Character.toString(string.charAt(4));
			 estado.get(state).addTransicao(estado.get(proxEstado), transicao);
			
		}
	}
	
	public String getInicial() {
		return inicial;
	}

//	public List<String> getEstados() {
//		return estados;
//	}

	public List<String> getAceita() {
		return aceita;
	}

//	public List<String> getTransicoes() {
//		return transicoes;
//	}

	public String toStringLista(List<String> lista) {
		String string = "\n";
		for (String elemento : lista)
			string += elemento + "\n";
		return string;
	}


	public HashMap<String, Estado> getEstado() {
		return estado;
	}
	
//	public void transicao(String cadeia) {
//		System.out.println(transicoes);
//		String estadoAtual = inicial;
//		System.out.println(aceita);
//		System.out.println(inicial);
//		int percorre = 0;
//		
//		while(percorre != cadeia.length()) {
//			String altera = estadoAtual;
//			for(int i = 0; i < transicoes.size(); i++) {
//			String primeiroEstado = Character.toString(transicoes.get(i).charAt(0));
//			String segundoEstado =	Character.toString(transicoes.get(i).charAt(2));	
//			char leitura  = transicoes.get(i).charAt(4);
//			
//				if (primeiroEstado.equals(estadoAtual) && cadeia.charAt(percorre) == leitura) {
//					System.out.println("true");
//					altera = segundoEstado;
//					System.out.println(primeiroEstado + " " + segundoEstado + " " + leitura);
//					System.out.println(cadeia.charAt(percorre));
//				}
//			}
//			estadoAtual = altera;
//			percorre += 1;
//		}
//		System.out.println(estadoAtual);
//		if(aceita.contains(estadoAtual)) {
//			System.out.println("deu Bom");
//		} else {
//			System.out.println("deu ruim");
//		}
//	}
}
