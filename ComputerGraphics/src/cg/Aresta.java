package cg;

public class Aresta {
	private Integer p1;
	private Integer p2;
	
	public Aresta(Integer p1, Integer p2) {
		this.setP1(p1);
		this.setP2(p2);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != Aresta.class) {
			return false;
		}
		Aresta a = (Aresta) obj;
		if (this.getP1().equals(a.getP1()) && this.getP2().equals(a.getP2())) {
			return true;
		}
		if (this.getP1().equals(a.getP2()) && this.getP2().equals(a.getP1())) {
			return true;
		}
		return false;
	}

	public Integer getP1() {
		return p1;
	}

	public void setP1(Integer p1) {
		this.p1 = p1;
	}

	public Integer getP2() {
		return p2;
	}

	public void setP2(Integer p2) {
		this.p2 = p2;
	}
	
}
