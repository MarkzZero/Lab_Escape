package controller;

import java.awt.Font;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.Inimigo;
import model.Personagem;

public class IndexController {
	
	private Inimigo[] inimigos;
	
	public IndexController() {
		super();
		inimigos = new Inimigo[0];
	}

	public String[][] gerarMapa(String mapa[][]) {
		Random r = new Random();
		int linhas = mapa.length;
		int colunas = mapa[0].length;

		// Cria a base do mapa

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (i == 0 || j == 0 || i == linhas - 1 || j == colunas - 1) {
					mapa[i][j] = "#";
				} else {
					mapa[i][j] = ".";
				}
			}
		}

		// Insere as paredes internas do mapa

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (i < 5 && j == 2) {
					mapa[i][j] = "#";
				}
				if (j < 5 && i == 6) {
					mapa[i][j] = "#";
				}
				if (i > 1 && j == 4 && i < 6) {
					mapa[i][j] = "#";
				}
				if (i > 1 && j == 6 && i < 8) {
					mapa[i][j] = "#";
				}
				if (i == 8 && j < 7) {
					mapa[i][j] = "#";
				}
				if (i == 2 && j > 5 && j < 11) {
					mapa[i][j] = "#";
				}
				if (i == 2 && j > 11 && j < 18) {
					mapa[i][j] = "#";
				}
				if (i == 4 && j > 6 && j < 11) {
					mapa[i][j] = "#";
				}
				if (i == 6 && j > 6 && j < 11) {
					mapa[i][j] = "#";
				}
				if (i == 8 && j > 6 && j < 11) {
					mapa[i][j] = "#";
				}
				if (i == 4 && j > 11 && j < 18) {
					mapa[i][j] = "#";
				}
				if (i == 6 && j > 11 && j < 18) {
					mapa[i][j] = "#";
				}
				if (i == 8 && j > 11 && j < 18) {
					mapa[i][j] = "#";
				}
				if (j == 12 && i > 7 && i < 14) {
					mapa[i][j] = "#";
				}
				if (i == 14 && j > 11 && j < 17) {
					mapa[i][j] = "#";
				}
				if (j == 10 && i > 8 && i < 15) {
					mapa[i][j] = "#";
				}
				if (i == 14 && j > 0 && j < 8) {
					mapa[i][j] = "#";
				}
				if (i == 14 && j == 9) {
					mapa[i][j] = "#";
				}
				if (i == 12 && j > 0 && j < 8) {
					mapa[i][j] = "#";
				}
				if (i == 10 && j > 0 && j < 8) {
					mapa[i][j] = "#";
				}
				if (i == 16 && j > 0 && j < 11) {
					mapa[i][j] = "#";
				}
				if (i == 16 && j > 11 && j < 19) {
					mapa[i][j] = "#";
				}
			}
		}

		// Insere as baterias

		int quantidade = r.nextInt(16) + 5;

		for (int i = 0; i < quantidade; i++) {
			int y = r.nextInt(19);
			int x = r.nextInt(19);

			while (mapa[x][y].equals("#") || mapa[x + 1][y + 1].equals("B")) {
				y = r.nextInt(19);
				x = r.nextInt(19);
			}

			mapa[x][y] = "B";
		}

		// Insere caixas

		quantidade = r.nextInt(16) + 10;

		for (int i = 0; i < quantidade; i++) {
			int y = r.nextInt(19);
			int x = r.nextInt(19);

			while (mapa[x][y].equals("#")) {
				y = r.nextInt(19);
				x = r.nextInt(19);
			}

			mapa[x][y] = "*";
		}

		mapa[1][1] = "P";

		inimigos = gerarInimigos(mapa, 5);
		
		return mapa;
	}

	public JTextArea ApresentarMapa(String[][] mapa, Personagem p) {
		int linhas = mapa.length;
		int colunas = mapa[0].length;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				sb.append(mapa[i][j]).append("  ");
			}
			sb.append("\n");
		}

		String resultado = sb.toString();
		String energia = "\n Energia: " + p.getEnergia() + "/20";
		resultado = resultado + energia;
		
		JTextArea textArea = new JTextArea(resultado);
		textArea.setFont(new Font("Consolas", Font.BOLD, 16));
		textArea.setEditable(false);

		return textArea;
	}

	public String[][] movimentacao(String[][] mapa, Personagem p, int direcao) {
		switch (direcao) {
		case 0:
			if (podeAndar(mapa[p.getPosicaox() - 1][p.getPosicaoy()])) {
				fimJogo(mapa, p, p.getPosicaox() - 1, p.getPosicaoy());
				if(p.getEnergia() != 20 && mapa[p.getPosicaox() - 1][p.getPosicaoy()].equals("B")) energia(p, 1);
				
				mapa[p.getPosicaox() - 1][p.getPosicaoy()] = "P";
				mapa[p.getPosicaox()][p.getPosicaoy()] = ".";
				p.setPosicaox(p.getPosicaox() - 1);
				energia(p, 0);
				
				moverInimigos(mapa, p);
				return mapa;
			} else {
				JOptionPane.showMessageDialog(null, "Movimento inválido!");
				return mapa;
			}
			
		case 1:
			if(podeAndar(mapa[p.getPosicaox() + 1][p.getPosicaoy()])) {
				fimJogo(mapa, p, p.getPosicaox() + 1, p.getPosicaoy());
				if(p.getEnergia() != 20 && mapa[p.getPosicaox() + 1][p.getPosicaoy()].equals("B")) {
					energia(p, 1);
				}
				mapa[p.getPosicaox() + 1][p.getPosicaoy()] = "P";
				mapa[p.getPosicaox()][p.getPosicaoy()] = ".";
				p.setPosicaox(p.getPosicaox() + 1);
				energia(p, 0);
				
				moverInimigos(mapa, p);
				return mapa;
			}else {
				JOptionPane.showMessageDialog(null, "Movimento inválido!");
				return mapa;

			}
			
		case 2:
			if(podeAndar(mapa[p.getPosicaox()][p.getPosicaoy() - 1])) {
				fimJogo(mapa, p, p.getPosicaox(), p.getPosicaoy() - 1);
				if(p.getEnergia() != 20 && mapa[p.getPosicaox()][p.getPosicaoy() - 1].equals("B")) {
					energia(p, 1);
				}
				mapa[p.getPosicaox()][p.getPosicaoy() - 1] = "P";
				mapa[p.getPosicaox()][p.getPosicaoy()] = ".";
				p.setPosicaoy(p.getPosicaoy() - 1);
				energia(p, 0);
				
				moverInimigos(mapa, p);
				return mapa;
			}else {
				JOptionPane.showMessageDialog(null, "Movimento inválido!");
				return mapa;
			}
			
			
		
		case 3:
			if(podeAndar(mapa[p.getPosicaox()][p.getPosicaoy() + 1])){
				fimJogo(mapa, p, p.getPosicaox(), p.getPosicaoy() + 1);
				if(p.getEnergia() != 20 && mapa[p.getPosicaox()][p.getPosicaoy() + 1].equals("B")) {
					energia(p, 1);
				}
				mapa[p.getPosicaox()][p.getPosicaoy() + 1] = "P";
				mapa[p.getPosicaox()][p.getPosicaoy()] = ".";
				p.setPosicaoy(p.getPosicaoy() + 1);
				energia(p, 0);
				
				moverInimigos(mapa, p);
				return mapa;
			}else {
				JOptionPane.showMessageDialog(null, "Movimento inválido!");
				return mapa;
			}
			
		case 4:
			if(mapa[p.getPosicaox() - 1][p.getPosicaoy()].equals("*")) caixas(mapa, p, p.getPosicaox() - 1, p.getPosicaoy());

			if(mapa[p.getPosicaox() + 1][p.getPosicaoy()].equals("*")) caixas(mapa, p, p.getPosicaox() + 1, p.getPosicaoy());
				
			if(mapa[p.getPosicaox()][p.getPosicaoy() - 1].equals("*")) caixas(mapa, p, p.getPosicaox(), p.getPosicaoy() - 1);
				
			if(mapa[p.getPosicaox()][p.getPosicaoy() + 1].equals("*")) caixas(mapa, p, p.getPosicaox(), p.getPosicaoy() + 1);
			
			moverInimigos(mapa, p);
			
			return mapa;
		}
			
		return mapa;
	}
	
	private boolean podeAndar(String valor) {
		return !valor.equals("#") && !valor.equals("*");
	}
	
	private void fimJogo(String[][] mapa, Personagem p, int x, int y) {
	    if (mapa[x][y].equals("S")) {
	        JOptionPane.showMessageDialog(null, 
	            "Parabéns! Você encontrou a saída!! Fim de jogo.");
	        System.exit(0);
	    }
	}
	
	private void energia(Personagem p, int estado) {
		if(p.getEnergia() == 0) {
			JOptionPane.showMessageDialog(null, "Suas energias acabaram! Fim de jogo!");
			System.exit(0);
		}
		if(estado == 0) {
			p.setEnergia(p.getEnergia() - 1);
 		}else {
 			p.setEnergia(Math.min(20, p.getEnergia() + 7));
			
		}
	}
	
	public void caixas(String[][] mapa, Personagem p, int x, int y) {
		Random r = new Random();
		int i = r.nextInt(3);
		
		if(i == 0) {
			mapa[x][y] = "S";
		}else if(i == 1) {
			mapa[x][y] = "B";
		}else {
			mapa[x][y] = ".";
		}
		
		energia(p, 0);
		
	}
	
	private Inimigo[] gerarInimigos(String[][] mapa, int quantidade) {
	    Random r = new Random();

	    inimigos = new Inimigo[quantidade];
	    
	    int linhas = mapa.length;
	    int colunas = mapa[0].length;

	    for (int i = 0; i < quantidade; i++) {
	        int x, y;

	        do {
	            x = r.nextInt(linhas);
	            y = r.nextInt(colunas);
	        } while (!mapa[x][y].equals("."));

	        inimigos[i] = new Inimigo(x, y);
	        mapa[x][y] = "E";
	    }

	    
	    return inimigos;
	}
	
	public void moverInimigos(String[][] mapa, Personagem p) {
	    Random r = new Random();

	    for (Inimigo inimigo : inimigos) {
	        boolean moveu = false;

	        for (int tentativas = 0; tentativas < 4 && !moveu; tentativas++) {
	            int direcao = r.nextInt(4);

	            int novoX = inimigo.getPosicaox();
	            int novoY = inimigo.getPosicaoy();

	            switch (direcao) {
	                case 0 -> novoX--;
	                case 1 -> novoX++;
	                case 2 -> novoY--;
	                case 3 -> novoY++;
	            }

	            if (novoX < 0 || novoY < 0 || 
	                novoX >= mapa.length || novoY >= mapa[0].length) {
	                continue;
	            }

	            if (novoX == p.getPosicaox() && novoY == p.getPosicaoy()) {
	                energia(p, 0);
	            }

	            if (!mapa[novoX][novoY].equals(".")) {
	                continue;
	            }

	            mapa[inimigo.getPosicaox()][inimigo.getPosicaoy()] = ".";
	            inimigo.setPosicaox(novoX);
	            inimigo.setPosicaoy(novoY);
	            mapa[novoX][novoY] = "E";

	            moveu = true;
	        }
	    }
	}
	
	public String centralizar(String texto, int largura) {
		int espacos = (largura - texto.length()) / 2;
		return " ".repeat(Math.max(0, espacos)) + texto;
	}
}
