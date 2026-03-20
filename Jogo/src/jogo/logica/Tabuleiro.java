package jogo.logica;
import java.util.Random;


public class Tabuleiro {
    private byte[][] tabuleiro;
    private int qtdCelulas, qtdBombas;
    private int coluna, linha;

    // Verifica se o tamanho recebido e valido para criar o tabuleiro
    public boolean definirTamanho (int col, int lin) {
        boolean resposta = false;
        if (col >= 9 && col <= 30) {
            coluna = col;
            resposta = true;
        }
        if (lin >= 9 && lin <= 30) {
            linha = lin;
            resposta = true;
        }

        return resposta;
    }
    
    // Cria o tabuleiro
    public void criarTabueleiro () {
        tabuleiro = new byte[linha][coluna];
        qtdCelulas = coluna * linha;
    }

    // Distribui as bombas de acordo com a porcentagem enviada
    public void distribuirBombas (int porcentagemBombas) {
        Random random = new Random();
        qtdBombas = (int) Math.ceil((double) (porcentagemBombas * qtdCelulas) /100) ;
        int posicaoColuna, posicaoLinha;

        for (int i=0; i<qtdBombas; i++){
            do {
                posicaoColuna = random.nextInt(0, coluna);
                posicaoLinha = random.nextInt(0, linha);
            } while (tabuleiro[posicaoLinha][posicaoColuna] == 9);
            tabuleiro[posicaoLinha][posicaoColuna] = 9;
        }

    }

    public void inserirNumeros () {
        for(int lin=0; lin<linha; lin++) {
            for (int col= 0; col<coluna; col++) {
                if (tabuleiro[lin][col] == 9) continue;

                for (int linvizinha=-1; linvizinha<=1; linvizinha++) {
                    for (int colvizinha = -1; colvizinha <= 1; colvizinha++) {
                        if (col==0 || col==(coluna - 1)) continue;
                        if (lin==0 || lin==(linha-1)) continue;
                        if (tabuleiro[(lin-linvizinha)][(col - colvizinha)] == 9)
                            tabuleiro[lin][col]++;
                    }
                }
            }
        }
    }

    // Objeto de teste
    public void mostrarTabuleiro () {
        for (int i=0; i<linha; i++) {
            for (int j=0; j<coluna; j++) {
                System.out.print(" [" + tabuleiro[i][j] + "] ");
            }
            System.out.println(" ");
        }
    }
}
