package projeto;

import java.util.HashMap;
import java.util.Map;

public class ConverterParaAFD {
	public Automato afndToAfd (Automato afnd) {
		String inicial = afnd.getInicial();
		Map<String, EstadoAFD> estados = new HashMap<String, EstadoAFD>();

		estados = this.GerarTabelaAfd(inicial, afnd, estados);
		
		afnd.setEstadosAFD(estados);
		Automato afd = new Automato();
		return afd;
	}
	
	private Map<String, EstadoAFD> GerarTabelaAfd(String estadoNome, Automato afnd, Map<String, EstadoAFD>estadosAfd) {
		String[] estadosCompoe = estadoNome.split(",");
		EstadoAFD estado = new EstadoAFD();

		//para cada entrada
		for(String entrada : afnd.getAlfabeto()) {
			String[] caminhos = null;
			//pego os caminhos de cada estado que compõe o estado do AFD
			for(String estadoAtual : estadosCompoe) {
				if (afnd.getEstadosAFD().get(estadoAtual).getTransicao().get(entrada) != null) {
					caminhos = Util.concatenarArray(caminhos, afnd.getEstadosAFD().get(estadoAtual).getTransicao().get(entrada).getCaminhos());
					Transicao aux = new Transicao();
					aux.setCaminhos(caminhos);
					aux = Util.RemoverRepeticoes(aux);
					String estadoNome2 = Util.arrayToString(aux.getCaminhos());
					if(!estadosAfd.containsKey(estadoNome2)) {
						estadosAfd.put(estadoNome2, null);
						GerarTabelaAfd(estadoNome2, afnd, estadosAfd);
					}
					caminhos = aux.getCaminhos();
				}
			}
			//Crio uma transicao
			Transicao transicao = new Transicao();
			transicao.setCaminhos(caminhos);
			transicao = Util.RemoverRepeticoes(transicao);
			estado.addTransicao(transicao, entrada);
			estadosAfd.put(estadoNome, estado);
		}
		return estadosAfd;
	}	
}