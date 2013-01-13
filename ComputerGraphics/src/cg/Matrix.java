package cg;

public class Matrix {
	public static double[][] produto(double[][] matriz1, double[][] matriz2) {
		double[][] resultado = new double[matriz1.length][matriz2[0].length];
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[0].length; j++) {
				resultado[i][j] = 0;
				for (int k = 0; k < matriz1.length; k++) {
					resultado[i][j] = resultado[i][j] + matriz1[i][k]*matriz2[k][j];
				}
			}
		}
		return resultado;
	}
	
	public static double[][] transposta(double[][] matriz){
		double[][] matrizTransposta = new double[matriz[0].length][matriz.length];
		for (int i = 0; i < matrizTransposta[0].length; i++) {
			for (int j = 0; j < matrizTransposta.length; j++) {
				matrizTransposta[i][j] = matriz[j][i];
			}
		}
		return matrizTransposta;
	}
}
