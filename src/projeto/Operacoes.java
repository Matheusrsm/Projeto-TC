package projeto;

import java.util.Map;

public class Operacoes {

	private Util util = new Util();

	public String simulador(Estado estadoInicial, String palavra) {
		String resultado = "Estado / Palavra";
		return resultado + simulacao(estadoInicial, palavra, 0);
	}

	private String simulacao(Estado estado, String palavra, int i) {
		if (i == palavra.length()) {
			if (estado.isFinal())
				return "\n" + estado.getRotulo() + " / e\n\nA palavra foi aceita";
			else {
				if (estado.existeTransicaoVazia()) {
					return "\n" + estado.getRotulo() + " / " + "e"
							+ simulacao(estado.getTransicoes().get("e"), palavra, i);
				} else
					return "\n\nA palavra nao foi aceita";
			}
		} else {
			if (estado.existeTransicaoVazia()) {
				return "\n" + estado.getRotulo() + " / " + palavra.substring(i, palavra.length())
						+ simulacao(estado.getTransicoes().get("e"), palavra, i);
			} else {
				String simbolo = Character.toString(palavra.charAt(i));
				return "\n" + estado.getRotulo() + " / " + palavra.substring(i, palavra.length())
						+ simulacao(estado.getTransicoes().get(simbolo), palavra, i + 1);
			}
		}
	}

	public String complemento(Automato automato) {
		for (Map.Entry<String, Estado> map : automato.getMapaDeEstados().entrySet()) {
			if (!map.getValue().isFinal()) {
				map.getValue().setFinal(true);
			} else {
				map.getValue().setFinal(false);
			}
		}
		automato.mudaEstadosComplemento();
		return automato.toString();
	}

	public String uniao(Automato automato1, Automato automato2) {
		Automato automato3 = util.criaAutomatoUniao(automato1, automato2);
		return automato3.toString();
	}
}