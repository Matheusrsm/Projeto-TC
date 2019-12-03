package projeto;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ConverterParaAFD {
	public Automato afndToAfd (Automato afnd) {
		String inicial = afnd.getInicial();
		Map<String, EstadoAFD> estados = new HashMap<String, EstadoAFD>();

		estados.put(inicial, null);

		List<String> aceitaEstados = new ArrayList<String>();
		estados = this.GerarTabelaAfd(inicial, afnd, estados, aceitaEstados);
		
		Automato afd = new Automato();
		afd.setInicial(afnd.getInicial());
		afd.setEstadosAFD(estados);
		afd.setAceita(aceitaEstados);

		return afd;
	}
	
	private Map<String, EstadoAFD> GerarTabelaAfd(String estadoNome, Automato afnd, Map<String, EstadoAFD>estadosAfd, List<String> aceitaEstados) {
		String[] estadosCompoe = estadoNome.split(",");
		EstadoAFD estado = new EstadoAFD();
		estadosAfd.put(estadoNome, estado);

		boolean aceita = false;
		for(String estadoAtual : estadosCompoe) {
			if(afnd.getAceita().contains(estadoAtual)){
				aceita = true;
			}
		}

		if(aceita){
			estado.setFinal(true);
			aceitaEstados.add(estadoNome);
		}

		//para cada entrada
		for(String entrada : afnd.getAlfabeto()) {
			List<String> caminhos = new ArrayList<String>();
			//pego os caminhos de cada estado que compe o estado do AFD
			for(String estadoAtual : estadosCompoe) {
				if (afnd.getEstado().get(estadoAtual).getTransicoes().get(entrada) != null) {
					String[] caminho = afnd.getEstado().get(estadoAtual).getCaminhos(entrada);
					for(String vai : caminho){
						caminhos.add(vai);
					}
					Transicao aux = new Transicao();
					aux.setCaminhos(Util.ListToString(caminhos));
					aux = Util.RemoverRepeticoes(aux);
					String estadoNome2 = Util.arrayToString(aux.getCaminhos());
					if(!estadosAfd.containsKey(estadoNome2)) {
						estadosAfd.put(estadoNome2, null);
						GerarTabelaAfd(estadoNome2, afnd, estadosAfd, aceitaEstados);
					}
				}
			}
			//Crio uma transicao
			Transicao transicao = new Transicao();
			transicao.setCaminhos(Util.ListToString(caminhos));
			transicao = Util.RemoverRepeticoes(transicao);
			estado.addTransicao(estadosAfd.get(Util.arrayToString(transicao.getCaminhos())), entrada);
			System.out.println(estadoNome + " " + entrada + " " + Util.arrayToString(transicao.getCaminhos()));
			estadosAfd.put(estadoNome, estado);
		}
		return estadosAfd;
	}	
}