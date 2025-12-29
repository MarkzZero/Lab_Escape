package view;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import controller.IndexController;
import model.Personagem;

public class Index {
	
	private static IndexController c;

	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		c = new IndexController();
		Personagem p = new Personagem(1, 1, 20);
		String[][] mapa = new String[19][19];
		mapa = c.gerarMapa(mapa);
		
		String recado =
					"Você acorda em um laboratório abandonado.\n"
					      + "As luzes estão fracas, os corredores vazios e\n"
					      + "a saída não está tão próxima quanto parece.\n\n"
					      + "Explore o ambiente, gerencie sua energia\n"
					      + "e encontre o caminho certo para escapar.\n\n"
					      + "Escolha uma opção para continuar:";
		
		String regras = 
		        "================= REGRAS DO JOGO =================\n\n" +
		                "1. OBJETIVO\n" +
		                "Seu objetivo é explorar o laboratório e encontrar a saída.\n" +
		                "A saída só será liberada após cumprir as condições do jogo.\n\n" +

		                "2. MOVIMENTAÇÃO\n" +
		                "- O jogador pode se mover para cima, baixo, esquerda ou direita.\n" +
		                "- Cada movimento consome energia.\n" +
		                "- Não é possível atravessar paredes.\n\n" +

		                "3. ENERGIA\n" +
		                "- O jogador inicia com uma quantidade limitada de energia.\n" +
		                "- A energia é gasta ao se mover e ao destruir caixas.\n" +
		                "- Se a energia chegar a zero, o jogo termina.\n\n" +

		                "4. BATERIAS\n" +
		                "- As baterias restauram energia.\n" +
		                "- Elas podem estar visíveis ou escondidas atrás de caixas.\n\n" +

		                "5. CAIXAS\n" +
		                "- Caixas podem ser destruídas.\n" +
		                "- Ao serem destruídas, viram espaço livre.\n\n" +

		                "6. INIMIGOS\n" +
		                "- Inimigos se movem pelo mapa.\n" +
		                "- Encostar em um inimigo causa perda de energia.\n\n" +

		                "7. FIM DE JOGO\n" +
		                "- O jogador vence ao encontrar a saída.\n" +
		                "- O jogador perde se a energia chegar a zero.\n\n" +

		                "===============================================\n";
		
		
		String[] op = {"Regras", "Iniciar jogo", "Encerrar"};
		String[] voltar = { "Voltar"};
		
		int teste = JOptionPane.showOptionDialog(null, recado, "Menu", 0, JOptionPane.PLAIN_MESSAGE, null, op, op[0]);
		
		switch(teste) {
			case 0:
				JOptionPane.showOptionDialog(null, regras, "Regras", 0, JOptionPane.PLAIN_MESSAGE, null, voltar, voltar[0]);
				menu();
				break;
			case 1:
				jogo(mapa, p);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Sessão encerrada!");
				System.exit(0);
		}
	}
	
	 private static void jogo(String[][] mapa, Personagem p) {
		String[] op = {"↑", "↓", "←", "→"};
		
		JTextArea apresentarMapa = c.ApresentarMapa(mapa, p);
		
		if(mapa[p.getPosicaox() - 1][p.getPosicaoy()].equals("*") || mapa[p.getPosicaox() + 1][p.getPosicaoy()].equals("*") || mapa[p.getPosicaox()][p.getPosicaoy() - 1].equals("*") || mapa[p.getPosicaox()][p.getPosicaoy() + 1].equals("*")) {
			caixas(mapa, p, apresentarMapa);
			return;
		}
		
		int jogo = JOptionPane.showOptionDialog(null, apresentarMapa, "Jogo", 0, JOptionPane.PLAIN_MESSAGE, null, op, op[0]);
		
	    if (jogo == JOptionPane.CLOSED_OPTION) {
	        JOptionPane.showMessageDialog(null, "Sessão encerrada!");
	        System.exit(0); 
	    }
		
		switch(jogo) {
			case 0: case 1: case 2: case 3:
				mapa = c.movimentacao(mapa, p, jogo);
				jogo(mapa, p);
				break;
		}
	}
	 
	private static void caixas(String[][] mapa, Personagem p, JTextArea apresentarMapa) {
		String[] op = {"Cima", "Baixo", "Esquerda", "Direita", "Quebrar caixa"};
		
		int jogo = JOptionPane.showOptionDialog(null, apresentarMapa, "Jogo", 0, JOptionPane.PLAIN_MESSAGE, null, op, op[0]);
		
	    if (jogo == JOptionPane.CLOSED_OPTION) {
	        JOptionPane.showMessageDialog(null, "Sessão encerrada!");
	        System.exit(0); 
	    }
		
		switch(jogo) {
			case 0: case 1: case 2: case 3:case 4:
				mapa = c.movimentacao(mapa, p, jogo);
				jogo(mapa, p);
				break;
		}
	}
	


}
