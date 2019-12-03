package projeto;
// e aqui ficaria as mudancas de estado.. inicial <-> final
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Estado {
	private Map<String, List<Estado>> transicoes;
	private boolean isFinal;
	private boolean isInicial;
	private String nome;
	
	
	public Estado() {
		super();
		this.transicoes = new HashMap<String, List<Estado>>();
	}

	public Map<String, List<Estado>> getTransicoes() {
		return transicoes;
	}

	public void setTransicao(Map<String, List<Estado>> transicao) {
		this.transicoes = transicao;
	}
	
	public void addTransicao(Estado estado, String entrada) {
		if(this.transicoes.get(entrada) == null){
			this.transicoes.put(entrada, new ArrayList<Estado>());
		}
		this.transicoes.get(entrada).add(estado);
	}

	public String[] getCaminhos(String entrada){
		List<Estado> estados = this.transicoes.get(entrada);
		List<String> valores = new ArrayList<String>();
		for(int i = 0; i < estados.size(); i++){
			valores.add(estados.get(i).getNome());
		}
		return Util.ListToString(valores);
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isInicial() {
		return isInicial;
	}

	public void setInicial(boolean isInicial) {
		this.isInicial = isInicial;
	}
	public boolean teste() {
		return this.isFinal;
	}

	public String getNome(){
		return this.nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	
}
