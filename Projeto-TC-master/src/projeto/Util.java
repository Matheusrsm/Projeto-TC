package projeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
			else if(palavra.equals("Alfabeto")){
				List<String> alfabeto = new ArrayList<String>();
				for(int i = 0; i < linhaQuebrada[1].length(); i++){
					String s = "";
					s += linhaQuebrada[1].charAt(i);
					alfabeto.add(s);
				}
				mapaDoAutomato.put("Alfabeto", alfabeto);
			}
			contadorDeLinhas++;
			if (contadorDeLinhas >= 5) {
				if (contadorDeLinhas == 5)
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
		Map<String, EstadoAFD> estadosAFD = new HashMap<>();
		List<String> aceita = mapaDoAutomato.get("aceita");
		List<String> transicoes = mapaDoAutomato.get("transicoes");
		String inicial = mapaDoAutomato.get("inicial").get(0);
		Automato automato = new Automato(estados, estadosAFD, inicial, aceita, transicoes);
		for(String letra : mapaDoAutomato.get("Alfabeto")){
			automato.adicionaNoAlfabeto(letra);
		}
		return automato;
	}
	
	public static boolean Exists (String[] vecString, String str) {
		for (int i = 0; i < vecString.length; i++) {
			if (vecString[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	public static Transicao RemoverRepeticoes(Transicao transicao) {
		String[] original = transicao.getCaminhos();
		if (original != null) {	
			HashSet<String> hsOriginal = new HashSet<String>();
	        for(String n : original) {
	            hsOriginal.add(n);
	        }
	        int i = 0;
	        String[] semRepeticoes = new String[hsOriginal.size()];
	        for(String nome : hsOriginal) {
	            semRepeticoes[i++] = nome;
	        }
	        transicao.setCaminhos(semRepeticoes);
	        return transicao;
		}
		return null;
	}

	public static String[] concatenarArray(String[]...arrays ) {
		int length = 0;
        for (String[] array : arrays) { 
        	if (array != null)
        		length += array.length; 
        }
        String[] retorno = new String[length];
        int destPos = 0;
        for (String[] array : arrays) {
        	if (array != null) {
	            System.arraycopy (array, 0, retorno, destPos, array.length);
	            destPos += array.length;
        	}
        }
        return retorno;
	}

	public static String[] ListToString(List<String> valores){
		String[] caminhos = new String[valores.size()];
		for(int i = 0; i < valores.size(); i++){
			caminhos[i] = valores.get(i);
		}
		return caminhos;
	}

	public static String arrayToString(String[] array) {
		String str = "";
		for(String s : array) {
			str+=s;
			str+=",";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
}
