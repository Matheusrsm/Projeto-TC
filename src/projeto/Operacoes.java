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
	
	public void complemento(Automato automato) {
		for(Map.Entry<String, Estado> map : automato.getEstado().entrySet()) {
			if(!map.getValue().isFinal()) {
				map.getValue().setFinal(true);
			} else {
				map.getValue().setFinal(false);
			}
		}
		
	}
}