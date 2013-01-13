package cg;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Poliedro {
	private ArrayList<Face> faces;
	private ArrayList<Ponto> vertices;
	private Poliedro projecao;
	
	public void projetarPerspectiva(Ponto pontoDoOlho, PlanoProjecao plano) {
		projetarPerspectiva(pontoDoOlho, plano.getOrigem(), plano.getN());
	}

	public void projetarPerspectiva(Ponto pontoDoOlho, Ponto pontoPlano, Vetor3D normalPlano) {
		double a = pontoDoOlho.getX();
		double b = pontoDoOlho.getY();
		double c = pontoDoOlho.getZ();		
		double nx = normalPlano.getI();
		double ny = normalPlano.getJ();
		double nz = normalPlano.getK();		
		double d0 = pontoPlano.getX()*nx + pontoPlano.getY()*ny + pontoPlano.getZ()*nz;
		double d1 = a*nx + b*ny + c*nz;
		double d = d0 - d1;
		double[][] matrizPerspectiva = new double[][]
										{{d+a*nx, a*ny, a*nz, -(a*d0)},
										{b*nx, d+b*ny, b*nz, -(b*d0)},
										{c*nx, c*ny, d+c*nz, -(c*d0)},
										{nx,    ny,    nz,     (-d1)}};
		double[][] projecaoMatriz = Matrix.produto(matrizPerspectiva, verticesListParaMatriz());
		criarProjecao(projecaoMatriz);			
	} 
	
	private void criarProjecao(double[][] matrizProjecao) {
		ArrayList<Ponto> verticesProjecao = new ArrayList<Ponto>();		
		for (int i = 0; i <  matrizProjecao[0].length; i++) {
			verticesProjecao.add(new Ponto(matrizProjecao[0][i], matrizProjecao[1][i], matrizProjecao[2][i], matrizProjecao[3][i]));
		}
		ArrayList<Face> facesProjecao = new ArrayList<Face>();
		for (Face face : faces) {
			facesProjecao.add(new Face(face.getVertices(), verticesProjecao));
		}
		setProjecao(new Poliedro(facesProjecao, verticesProjecao));		
		getProjecao().paraCoordenadaCartesiana();	
	}
	
	public void projetarParalelo(Vetor3D direcaoProjecao, PlanoProjecao plano) {
		projetarParalelo(direcaoProjecao, plano.getOrigem(), plano.getN());
	}
	
	public void projetarParalelo(Vetor3D direcaoProjecao, Ponto pontoPlano, Vetor3D normalPlano) {
		double a = direcaoProjecao.getI();
		double b = direcaoProjecao.getJ();
		double c = direcaoProjecao.getK();		
		double nx = normalPlano.getI();
		double ny = normalPlano.getJ();
		double nz = normalPlano.getK();		
		double d0 = pontoPlano.getX()*nx + pontoPlano.getY()*ny + pontoPlano.getZ()*nz;
		double d1 = a*nx + b*ny + c*nz;
		double[][] matrizParalela = {{d1-a*nx, -a*ny, -a*nz, a*d0},
									 {-b*nx, d1-b*ny, -b*nz, b*d0},
									 {-c*nx, -c*ny, d1-c*nz, c*d0},
									 {0,       0,       0,     d1}};
		double[][] matrizProjecao = Matrix.produto(matrizParalela, verticesListParaMatriz());
		criarProjecao(matrizProjecao);
		getProjecao().paraCoordenadaCartesiana();
	}
	
	public void paraCoordenadaCartesiana() {
		for (Ponto pt : vertices) {
			pt.paraCoordenadaCartesiana();
		}
	}
	
	public double[][] verticesListParaMatriz() {
		double[][] novoVertices = new double[4][getVertices().size()];
		for (int i = 0; i < getVertices().size(); i++) {
			novoVertices[0][i] = getVertices().get(i).getX();
			novoVertices[1][i] = getVertices().get(i).getY();
			novoVertices[2][i] = getVertices().get(i).getZ();
			novoVertices[3][i] = getVertices().get(i).getW();
		}
		return novoVertices;
	}

	public Poliedro(ArrayList<Face> faces, ArrayList<Ponto> vertices) {
		this.faces = faces;
		this.vertices = vertices;
	}

	public ArrayList<Face> getFaces() {
		return faces;
	}

	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
	}

	public ArrayList<Ponto> getVertices() {
		return vertices;
	}

	public void setVertices(double[][] vertices) {
		this.vertices = new ArrayList<Ponto>();
		for (int i = 0; i < vertices[0].length; i++) {
			this.vertices.add(new Ponto(vertices[0][i], vertices[1][i], vertices[2][i], vertices[3][i]));
		}
		for (Face f : faces) {
			f.remapearPontos(this.vertices);
		}
	}

	public void paintWire(Graphics2D g2, Ponto observador) {
		ArrayList<Face> facesProjecao = projecao.getFaces();
		for (int i = 0; i < faces.size(); i++) {
			if (faces.get(i).isVisible(observador)) {
				facesProjecao.get(i).paintWire(g2);
			}
		}
	}
	
	public void paintSuperficie(Graphics2D g2, Ponto observador) {
		ArrayList<Face> facesProjecao = projecao.getFaces();
		for (int i = 0; i < faces.size(); i++) {
			if (faces.get(i).isVisible(observador)) {
				facesProjecao.get(i).paintSuperficie(g2);
			}
		}
	}

	public Poliedro getProjecao() {
		return projecao;
	}

	public void setProjecao(Poliedro projecao) {
		this.projecao = projecao;
	}

	public void paintSuperficie(Graphics2D g2, Vetor3D direcaoProjecao) {
		ArrayList<Face> facesProjecao = projecao.getFaces();
		for (int i = 0; i < faces.size(); i++) {
			if (faces.get(i).isVisible(direcaoProjecao)) {
				facesProjecao.get(i).paintSuperficie(g2);
			}
		}
	}

	public void paintWire(Graphics2D g2, Vetor3D direcaoProjecao) {
		ArrayList<Face> facesProjecao = projecao.getFaces();
		for (int i = 0; i < faces.size(); i++) {
			if (faces.get(i).isVisible(direcaoProjecao)) {
				facesProjecao.get(i).paintWire(g2);
			}
		}
	}
	
	

}
