package projeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

	public List<String> leArquivo(File arquivo) {
		List<String> linhasDoArquivo = new ArrayList<String>();
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
			String linha = leitor.readLine();
			while (linha != null) {
				linhasDoArquivo.add(linha);
				linha = leitor.readLine();
			}
			leitor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linhasDoArquivo;
	}

	public Map<String, List<String>> separaLinhas(List<String> linhas) {
		Map<String, List<String>> mapaDoAutomato = new HashMap<>();
		List<String> transicoes = new ArrayList<>();
		int contadorDeLinhas = 0;
		for (String linha : linhas) {
			String[] linhaQuebrada = linha.split(" ");
			String palavra = linhaQuebrada[0];
			if (palavra.equals("estados") || palavra.equals("inicial") || palavra.equals("aceita"))
				adicionaEstados(linhaQuebrada, palavra, mapaDoAutomato);
			contadorDeLinhas++;
			if (contadorDeLinhas >= 4) {
				if (contadorDeLinhas == 4)
					mapaDoAutomato.put("transicoes", transicoes);
				mapaDoAutomato.get("transicoes").add(linha);
			}
		}
		return mapaDoAutomato;
	}

	public void adicionaEstados(String[] linhaQuebrada, String palavra, Map<String, List<String>> mapaDoAutomato) {
		List<String> estados = new ArrayList<>();
		for (String estado : linhaQuebrada) {
			if (estado != palavra) {
				if (estado.contains(","))
					estados.add(estado.substring(0, estado.length() - 1));
				else
					estados.add(estado);
			}
		}
		mapaDoAutomato.put(palavra, estados);
	}

	public Automato criaAutomato(Map<String, List<String>> mapaDoAutomato) {
		List<String> estados = mapaDoAutomato.get("estados");
		List<String> aceita = mapaDoAutomato.get("aceita");
		List<String> transicoes = mapaDoAutomato.get("transicoes");
		String inicial = mapaDoAutomato.get("inicial").get(0);
		return new Automato(estados, inicial, aceita, transicoes);
	}
	
	public Automato criaAutomatoUniao(Automato automato1, Automato automato2) {
		Automato automatoFinal = new Automato(new ArrayList<>(), "$", new ArrayList<>(), new ArrayList<>());
		automatoFinal.getListaDeEstados().addAll(automato1.getListaDeEstados());
		automatoFinal.getListaDeEstados().addAll(automato2.getListaDeEstados());
		automatoFinal.getAceita().addAll(automato1.getAceita());
		automatoFinal.getAceita().addAll(automato2.getAceita());
		if (automato1.getInicial().equals(automato2.getInicial()))
			automatoFinal.getTransicoes().add("$ " + automato1.getInicial() + " e");
		else {
			automatoFinal.getTransicoes().add("$ " + automato1.getInicial() + " e");
			automatoFinal.getTransicoes().add("$ " + automato2.getInicial() + " e");
		}
		automatoFinal.getTransicoes().addAll(automato1.getTransicoes());
		automatoFinal.getTransicoes().addAll(automato2.getTransicoes());
		automatoFinal.adicionaTransicoesAosEstados();
		return automatoFinal;
	}
}