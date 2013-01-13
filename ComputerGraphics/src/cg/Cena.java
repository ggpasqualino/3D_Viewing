package cg;

import java.util.ArrayList;

public class Cena {
	
	Ponto observador;
	Vetor3D direcaoProjecao;
	ArrayList<Poliedro> objetos;
	PlanoProjecao planoProjecao;
	JanelaVisualizacao janelaVisualizacao;	
	
	public static enum TipoProjecao {
		PARALELA, PERSPECTIVA
	}	
	
	public Cena(Ponto observador, PlanoProjecao planoProjecao) {
		this(planoProjecao);
		this.observador = observador;		
	}
	
	public Cena(Vetor3D direcaoProjecao, PlanoProjecao planoProjecao) {
		this(planoProjecao);
		this.direcaoProjecao = direcaoProjecao;
	}
	
	private Cena(PlanoProjecao planoProjecao) {
		this.planoProjecao = planoProjecao;
		this.objetos = new ArrayList<Poliedro>();
		this.janelaVisualizacao = new JanelaVisualizacao();
	}
	
	public Ponto getObservador() {
		return observador;
	}
	public ArrayList<Poliedro> getObjetos() {
		return objetos;
	}
	public PlanoProjecao getPlanoProjecao() {
		return planoProjecao;
	}
	public JanelaVisualizacao getJanelaVisualizacao() {
		return janelaVisualizacao;
	}
	
	public void adicionarObjeto(Poliedro p) {
		objetos.add(p);
	}
	
	public void renderizar(TipoProjecao projecao) {
		if (projecao.equals(TipoProjecao.PARALELA)) {
			for (Poliedro p : objetos) {
				p.projetarParalelo(direcaoProjecao, planoProjecao);
			}
		} else if (projecao.equals(TipoProjecao.PERSPECTIVA)) {
			for (Poliedro p : objetos) {
				p.projetarPerspectiva(observador, planoProjecao);				
			}
		}
		for (Poliedro p : objetos) {
			janelaVisualizacao.calcularJanela(p.getProjecao());			
		}		
		
	}
	
}
