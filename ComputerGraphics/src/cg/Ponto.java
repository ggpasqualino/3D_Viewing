package cg;

public class Ponto {
	private double x;
	private double y;
	private double z;
	private double w;
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
	
	public Ponto(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Ponto(double x, double y, double z) {
		this(x, y, z, 1.0);
	}
	
	public void paraCoordenadaCartesiana() {
		x = x/w;
		y = y/w;
		z = z/w;
		w = w/w;
	}
	
	public double[][] paraMatriz(){
		double[][] ponto = new double[4][1];
		ponto[0][0] = x;
		ponto[1][0] = y;
		ponto[2][0] = z;
		ponto[3][0] = w;
		return ponto;
	}
	
	public double[] paraMatrizTransposta() {
		return new double[]{x, y, z, w};
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != Ponto.class) {
			return false;
		}
		Ponto p = (Ponto) obj;
		if (x == p.getX() && y == p.getY() && z == p.getZ() && w == p.getW()) {
			return true;
		}
		return false;
	}	
	
}
