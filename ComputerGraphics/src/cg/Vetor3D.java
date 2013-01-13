package cg;

public class Vetor3D {
	private double[] vetor;	
	
	public Vetor3D(double[] vetor1) {
		this.setVetor(vetor1.length == 3 ? vetor1 : new double[3]);
	}
	
	public Vetor3D(Ponto p1, Ponto p2) {
		vetor = new double[3];
		vetor[0] = p2.getX() - p1.getX();
		vetor[1] = p2.getY() - p1.getY();
		vetor[2] = p2.getZ() - p1.getZ();
	}
	
	public Vetor3D(double i, double j, double k) {
		vetor = new double[3];
		vetor[0] = i;
		vetor[1] = j;
		vetor[2] = k;
	}

	public double norma() {
		double norma = Math.pow(getI(), 2);
		norma = norma + Math.pow(getJ(), 2);
		norma = norma + Math.pow(getK(), 2);
		norma = Math.sqrt(norma);
		return norma;
	}
	
	public Vetor3D getVetor3DUnitario() {
		double norma = norma();
		double i = getI()/norma;
		double j = getJ()/norma;
		double k = getK()/norma;
		return new Vetor3D(i, j, k);
	}
	
	public double produtoInterno(Vetor3D vetor2) {
		double produto = 0;
		for (int i = 0; i < getVetor().length; i++) {
			produto = produto + getVetor()[i] * vetor2.getVetor()[i];
		}
		return produto;
	}
	
	public Vetor3D produtoVetorial(Vetor3D vetor2) {
		double a = getVetor()[1]*vetor2.getVetor()[2] - vetor2.getVetor()[1]*getVetor()[2];
		double b = -(getVetor()[0]*vetor2.getVetor()[2] - vetor2.getVetor()[0]*getVetor()[2]);
		double c = getVetor()[0]*vetor2.getVetor()[1] - vetor2.getVetor()[0]*getVetor()[1];
		return new Vetor3D(a,b,c);
	}
	
	public double[] getVetor() {
		return vetor;
	}

	public void setVetor(double[] vetor) {
		this.vetor = vetor;
	}

	public double getI() {
		return vetor[0];
	}
	public double getJ() {
		return vetor[1];
	}
	public double getK() {
		return vetor[2];
	}
}
