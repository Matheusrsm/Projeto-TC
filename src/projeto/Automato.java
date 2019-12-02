package projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Automato {

	private String inicial;
	private Map<String, Estado> estado;
	private Map<String, EstadoAFD> estadoAFD;
	private List<String> aceita;
	private List<String> alfabeto;

	public Automato() {
		this.estadoAFD = new HashMap<String, EstadoAFD>();
		this.alfabeto = new ArrayList<String>();
	}
	
	public Automato(List<String> estados, Map<String, EstadoAFD> estadoAFD, String inicial, List<String> aceita, List<String> transicoes) {
		this.estado = new HashMap<>();
		this.setEstadosAFD(estadoAFD); 
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


	public Map<String, Estado> getEstado() {
		return estado;
	}


	public void setEstado(Map<String, Estado> estados) {
		this.estado = estados;
	}




	public Map<String, EstadoAFD> getEstadosAFD() {
		return estadoAFD;
	}

	public void setEstadosAFD(Map<String, EstadoAFD> estadosAFD) {
		this.estadoAFD = estadosAFD;
	}

	public List<String> getAlfabeto() {
		return this.alfabeto;
	}

	public void setAlfabeto(String[] ling) {
		for (String s : ling) {
			this.alfabeto.add(s);
		}
	}
	public String getEstadoInicialAFD() {
		for(Entry<String, EstadoAFD> a : estadoAFD.entrySet()) {
			if(a.getValue() != null && a.getValue().isInicial()) {
				return a.getKey();
			}
		}
		return null;
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
