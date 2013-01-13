package cg;


public class PlanoProjecao {
	private Ponto origem;
	private Ponto upositivo;
	private Ponto vpositivo;
	private Vetor3D u;
	private Vetor3D v;
	private Vetor3D n;
		
	public PlanoProjecao(Ponto origem, Ponto upositivo, Ponto vpositivo) {
		this.origem = origem;
		this.upositivo = upositivo;
		this.vpositivo = vpositivo;
		calcularVetores();
	}

	private void calcularVetores() {
		u = new Vetor3D(origem, upositivo).getVetor3DUnitario();
		v = new Vetor3D(origem, vpositivo).getVetor3DUnitario();
		n = u.produtoVetorial(v);
	}

	public double[][] paraCoordenadaDoPlano(double[][] pontos){
		double[][] matrizRotacao = 		{{u.getI(), u.getJ(), u.getK(), 0},
										 {v.getI(), v.getJ(), v.getK(), 0},
										 {n.getI(), n.getJ(), n.getK(), 0},
										 {       0,        0,        0, 1}};
		double[][] matrizTranslação = {{1, 0, 0, -origem.getX()},
									   {0, 1, 0, -origem.getY()},
									   {0, 0, 1, -origem.getZ()},
									   {0, 0, 0, -origem.getW()}};
		
		double[][] matrizTransformacao = Matrix.produto(matrizRotacao, matrizTranslação);
		
		return Matrix.produto(matrizTransformacao, pontos);
	}
	
	public void paraCoordenadaDoPlano(Poliedro poliedro){
		double[][] pontos = poliedro.verticesListParaMatriz();
		poliedro.setVertices(paraCoordenadaDoPlano(pontos));
	}

	public Ponto getOrigem() {
		return origem;
	}

	public Ponto getUpositivo() {
		return upositivo;
	}

	public Ponto getVpositivo() {
		return vpositivo;
	}

	public Vetor3D getU() {
		return u;
	}

	public Vetor3D getV() {
		return v;
	}

	public Vetor3D getN() {
		return n;
	}
}
