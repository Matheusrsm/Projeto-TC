package projeto;

import java.util.Map;

public class Operacoes {
	
	public String simulador(Estado estadoInicial, String palavra) {
		String resultado = "Estado / Palavra";
		return resultado + simulacao(estadoInicial, palavra, 0);
	}
	
	private String simulacao(Estado estado, String palavra, int i) {
		if(i == palavra.length()) {
			if (estado.isFinal())
				return "\n" + estado.getSimbolo() + " / e\n\nA palavra foi aceita";
			else
				return "\n\nA palavra nao foi aceita";
		} else {
			String simbolo = Character.toString(palavra.charAt(i));
			return "\n" + estado.getSimbolo() + " / " + palavra.substring(i, palavra.length()) + simulacao(estado.getTransicoes().get(simbolo), palavra, i + 1);
		}
	}
	
	public String complemento(Automato automato) {
		for(Map.Entry<String, Estado> map : automato.getEstado().entrySet()) {
			if(!map.getValue().isFinal()) {
				map.getValue().setFinal(true);
			} else {
				map.getValue().setFinal(false);
			}
		}
		automato.mudaEstadosComplemento();
		return automato.toString();
	}
	
	public boolean uniao(Automato automato1, Automato automato2) {
		boolean automatoUM = false;
		boolean automatoDOIS = false;
		
		for(Map.Entry<String, Estado> map : automato1.getEstado().entrySet()){
			if(map.getValue().isFinal()) {
				automatoUM = true;
			}
		}
		
		for(Map.Entry<String, Estado> map: automato2.getEstado().entrySet()) {
			if(map.getValue().isFinal()) {
				automatoDOIS = true;
			}
		}
		return (automatoUM || automatoDOIS);
	}
}