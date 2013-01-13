package cg;

public class JanelaVisualizacao {
	Ponto min;
	Ponto max;
	
	public void calcularJanela(Poliedro poliedro) {
		if (min == null) {
			min = new Ponto(poliedro.getVertices().get(0).getX(), poliedro.getVertices().get(0).getY(), poliedro.getVertices().get(0).getZ());
		}
		if (max == null) {
			max = new Ponto(poliedro.getVertices().get(0).getX(), poliedro.getVertices().get(0).getY(), poliedro.getVertices().get(0).getZ());
		}
		for (Ponto pt : poliedro.getVertices()) {
			if(pt.getX() > max.getX()) {
				max.setX(pt.getX());
			}
			if(pt.getY() > max.getY()) {
				max.setY(pt.getY());
			}
			if(pt.getX() < min.getX()) {
				min.setX(pt.getX());
			}
			if(pt.getY() < min.getY()) {
				min.setY(pt.getY());
			}
			
		}
	}
	
	public double[][] getLimites(){
		double[][] limites = new double[2][];
		limites[0] = min.paraMatrizTransposta();
		limites[1] = max.paraMatrizTransposta();
		return Matrix.transposta(limites);
	}
}
