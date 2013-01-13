package cg;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;

public class Display extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cena cena;
	private double[][] matrizViewport;
	private Integer modoRenderizacao;
	
	public static enum TipoRenderizacao { 
		WIREFRAME, SUPERFICIE};
	
	public void calcularMatrizViewport() {
		double maxX = cena.getJanelaVisualizacao().max.getX();
		double minX = cena.getJanelaVisualizacao().min.getX();
		double maxY = cena.getJanelaVisualizacao().max.getY();
		double minY = cena.getJanelaVisualizacao().min.getY();
		double sx = this.getWidth()/(maxX - minX);
		double sy = this.getHeight()/(maxY - minY);
		matrizViewport = new double[][]
				{{sx,  0,  0, this.getWidth()/2.},
				 { 0, -sy, 0, this.getHeight()},
				 { 0,  0,  1, 0},
				 { 0,  0,  0, 1}};
			
	}

	public Display(Cena cena, TipoRenderizacao modoRenderizacao) {
		super();
		this.setSize(320,240);
		this.setPreferredSize(getSize());
		this.cena = cena;
		this.modoRenderizacao = modoRenderizacao.ordinal();
		calcularMatrizViewport();
		calcularViewport();
	}
	
	public void calcularViewport() {
		for (Poliedro obj : cena.getObjetos()) {
			double[][] pontos = obj.getProjecao().verticesListParaMatriz();
			pontos = Matrix.produto(matrizViewport, pontos);
			obj.getProjecao().setVertices(pontos);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (modoRenderizacao == TipoRenderizacao.SUPERFICIE.ordinal()) {
			for (Poliedro obj : cena.getObjetos()) {
				if (cena.observador != null) {
					obj.paintSuperficie(g2, cena.observador);
				} else {
					obj.paintSuperficie(g2, cena.direcaoProjecao);
				}
				
			}
		} else if (modoRenderizacao == TipoRenderizacao.WIREFRAME.ordinal()) {
			for (Poliedro obj : cena.getObjetos()) {
				if (cena.observador != null) {
					obj.paintWire(g2, cena.observador);
				} else {
					obj.paintWire(g2, cena.direcaoProjecao);
				}
			}
		}
	}

}
