package projeto;

import java.io.File;

public class Main {

	private static Util util = new Util();

	public static void main(String[] args) {

		// Exemplo de passos para transformacao de um arquivo em um objeto do tipo automato

		File arq = new File("Teste.txt");
		Automato automato = util.criaAutomato(util.separaLinhas(util.leArquivo(arq)));
//		System.out.println(automato);
		automato.transicao("10011");
	}
}
