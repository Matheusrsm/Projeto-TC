package projeto;
// aqui ficaria aquela tua função transições

public class Transicao {
	
//	public String getCaminho() {
//		return caminho;
//	}
//	public void setCaminhos(String caminho) {
//		this.caminho = caminho;
//	}
//	public boolean existe (String str) {
//		for (String s : caminhos) {
//			if (s != null && s.equals(str)) {
//				return true;
//			}
//		}
//		return false;
//	}

	public boolean transicao(EstadoAFD estado, String cadeia, int i) {
		System.out.println(estado + " " + cadeia + " " + i);
		if(i == cadeia.length()) {
			return estado.isFinal();
		} else {
			String letra = Character.toString(cadeia.charAt(i));
			return transicao(estado.getTransicoes().get(letra), cadeia, i + 1);
		}
	}
	
	public boolean rodaAutomato(EstadoAFD estado, String caminho) {
		// System.out.println(estado + " " + caminho);
		// return false;
		return transicao(estado, caminho, 0);
	}
	private String[] caminhos;
	
	public String[] getCaminhos() {
		return caminhos;
	}
	
	public void setCaminhos(String[] caminhos) {
		this.caminhos = caminhos;
	}
	public boolean existe (String str) {
		for (String s : caminhos) {
			if (s != null && s.equals(str)) {
				return true;
			}
		}
		return false;
	}
	public void removerTransicao(String str) {
		int j = 0;
		for(int i = 0; i < caminhos.length; i++){
		    if(!caminhos[i].equals(str)){
		        j++;
		    }
		}
		String[] vetorAux = new String[j];
        int index = 0;
        for(int i = 0; i < caminhos.length; i++){
            if(!caminhos[i].equals(str)){
                vetorAux[index] = caminhos[i];
                index++;
            }
        }
        caminhos = vetorAux;
	}


}
