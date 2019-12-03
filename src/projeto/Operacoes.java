package projeto;

import java.util.Map;

public class Operacoes {
	
	public boolean simulador(Estado estadoInicial, String palavra) {
		return simulacao(estadoInicial, palavra, 0);
	}
	
	private boolean simulacao(Estado estado, String palavra, int i) {
		if(i == palavra.length()) {
			return estado.isFinal();
		} else {
			String simbolo = Character.toString(palavra.charAt(i));
			return simulacao(estado.getTransicoes().get(simbolo), palavra, i + 1);
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