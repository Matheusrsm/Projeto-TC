package projeto;
// e aqui ficaria as mudancas de estado.. inicial <-> final
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Estado {
	private Map<String, Transicao> transicao;
	private boolean isFinal;
	private boolean isInicial;
	
	
	public Estado() {
		super();
		this.transicao = new HashMap<String, Transicao>();
	}

	public Map<String, Transicao> getTransicao() {
		return transicao;
	}

	public void setTransicao(Map<String, Transicao> transicao) {
		this.transicao = transicao;
	}
	
	public void addTransicao(Transicao transicao, String entrada) {
		this.transicao.put(entrada, transicao);
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
	
}
