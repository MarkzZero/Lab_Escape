package model;

public class Personagem {
	private int posicaox;
	private int posicaoy;
	private int energia;
	
	public Personagem(int posicaox, int posicaoy, int energia) {
		super();
		this.posicaox = posicaox;
		this.posicaoy = posicaoy;
		this.energia = energia;
	}

	public int getPosicaox() {
		return posicaox;
	}

	public void setPosicaox(int posicaox) {
		this.posicaox = posicaox;
	}

	public int getPosicaoy() {
		return posicaoy;
	}

	public void setPosicaoy(int posicaoy) {
		this.posicaoy = posicaoy;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	
	
	

}
