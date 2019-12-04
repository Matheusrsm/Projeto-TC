package projeto;

import java.io.File;

public class Main {

	private static Util util = new Util();
	private static Operacoes operacoes = new Operacoes();

	public static void main(String[] args) {

		if (args[0].equals("complemento")) {
			File arq = new File(args[1]);
			Automato automato = util.criaAutomato(util.separaLinhas(util.leArquivo(arq)));
			System.out.println("Complemento:\n");
			System.out.println(operacoes.complemento(automato));
		}

		else if (args[0].equals("uniao")) {
			File arq1 = new File(args[1]);
			File arq2 = new File(args[2]);
			Automato automato1 = util.criaAutomato(util.separaLinhas(util.leArquivo(arq1)));
			Automato automato2 = util.criaAutomato(util.separaLinhas(util.leArquivo(arq2)));
			System.out.println("União:\n");
			System.out.println(operacoes.uniao(automato1, automato2));
		}

		else {
			File arq = new File(args[0]);
			Automato automato = util.criaAutomato(util.separaLinhas(util.leArquivo(arq)));
			System.out.println("Simulador:\n");
			System.out.println(operacoes.simulador(automato.getMapaDeEstados().get(automato.getInicial()), args[1]));
		}
	}
}