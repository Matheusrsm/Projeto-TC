package projeto;

import java.io.File;

public class Main {

	private static Util util = new Util();

	public static void main(String[] args) {

		File arq = new File(args[0]);
		Automato automato = util.criaAutomato(util.separaLinhas(util.leArquivo(arq)));
		Operacoes operacoes = new Operacoes();
		System.out.println(operacoes.simulador(automato.getEstado().get(automato.getInicial()), args[1])); 
	}
}