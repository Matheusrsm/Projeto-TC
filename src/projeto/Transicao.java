package projeto;
// aqui ficaria aquela tua função transições

public class Transicao {
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
