package cg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Face {
	private ArrayList<Integer> vertices;
	private HashMap<Integer, Ponto> verticesPonto;
	private Vetor3D vetorNormal;
	
	public Face(ArrayList<Integer> vertices, List<Ponto> pontos) {
		this.setVertices(vertices);
		verticesPonto = new HashMap<Integer, Ponto>();
		acertarVertices();
		mapearPontos(pontos);
		calcularVetorNormal();
	}
	
	private void mapearPontos(List<Ponto> pontos) {
		for (Integer vert : getVertices()) {
			verticesPonto.put(vert, pontos.get(vert));
		}	
		System.out.println();
	}
	
	public void remapearPontos(List<Ponto> pontos) {
		verticesPonto = new HashMap<Integer, Ponto>();
		mapearPontos(pontos);
	}

	private void acertarVertices() {		
		int indiceUltimoVertice = getVertices().size()-1;
		Integer ultimoVertice = getVertices().get(indiceUltimoVertice);
		Integer primeiroVertice = getVertices().get(0);
		if (!primeiroVertice.equals(ultimoVertice)) {
			getVertices().add(primeiroVertice);
		}
	}

	private void calcularVetorNormal() {
		Ponto p1 = verticesPonto.get(getVertices().get(0));
		Ponto p2 = verticesPonto.get(getVertices().get(1));
		Ponto p3 = verticesPonto.get(getVertices().get(2));
		Vetor3D v1 = new Vetor3D(p1, p2);
		Vetor3D v2 = new Vetor3D(p2, p3);
		vetorNormal = v1.produtoVetorial(v2);		
	}

	public void paintWire(Graphics2D g2) {
		Polygon p = criaPoligono();
		g2.draw(p);
	}

	public void paintSuperficie(Graphics2D g2) {		
			Polygon p = criaPoligono();
			int r = (int)(Math.random() * 255);
			int g = (int)(Math.random() * 255);
			int b = (int)(Math.random() * 255);
			g2.draw(p);
			g2.setColor(new Color(r, g, b));
			g2.fill(p);
	}

	private Polygon criaPoligono() {
		int[] xpoints = new int[getVertices().size()-1];
		int[] ypoints = new int[getVertices().size()-1];
		for (int i = 0; i < getVertices().size()-1; i++) {			
			xpoints[i] = (int) Math.round(verticesPonto.get(getVertices().get(i)).getX());
			ypoints[i] = (int) Math.round(verticesPonto.get(getVertices().get(i)).getY());
		}
		Polygon p = new Polygon(xpoints, ypoints, getVertices().size()-1);
		return p;
	}
	
	public boolean isVisible(Ponto observador) {
		Vetor3D visao = new Vetor3D(verticesPonto.get(getVertices().get(0)), observador);
		double cosAngulo = visao.produtoInterno(vetorNormal);
		if (cosAngulo > 0.0) {
			return true;
		}
		return false;
	}

	public ArrayList<Integer> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}

	public boolean isVisible(Vetor3D direcaoProjecao) {
		double cosAngulo = direcaoProjecao.produtoInterno(vetorNormal);
		if (cosAngulo > 0.0) {
			return true;
		}
		return false;
	}	

	
}
