package jogo.logica;
import java.util.Random;


public class Tabuleiro {
    private int qtdCelulas, qtdBombas;
    private int coluna, linha;
    private int porcentagemBombas;
    private Celula[][] tabuleiro;

    // Criação do tabuleiro
    // Coleta e verifica os parametros para a criação do tabuleiro
    public boolean definirParametros (int col, int lin, int porBombas) {
        boolean resposta = false;
        if (col >= 9 && col <= 30) {
            coluna = col;
            resposta = true;
        }
        if (lin >= 9 && lin <= 30) {
            linha = lin;
            resposta = true;
        }
        porcentagemBombas = porBombas;
        return resposta;
    }

    // Cria o tabuleiro
    public void criarTabueleiro () {
        tabuleiro = new Celula[linha][coluna];
        qtdCelulas = coluna * linha;
        for (int lin=0; lin<linha; lin++){
            for (int col=0; col<coluna; col++){
                tabuleiro[lin][col] = new Celula();
            }
        }
        distribuirBombas();
        inserirNumeros();
    }

    // Distribui as bombas de acordo com a porcentagem enviada
    public void distribuirBombas () {
        Random random = new Random();
        qtdBombas = (int) Math.ceil((double) (porcentagemBombas * qtdCelulas) /100) ;
        int posicaoColuna, posicaoLinha;
        for (int i=0; i<qtdBombas; i++){
            do {
                posicaoColuna = random.nextInt(0, (coluna-1));
                posicaoLinha = random.nextInt(0, (linha-1));
            } while (tabuleiro[posicaoLinha][posicaoColuna].isBomba());
            tabuleiro[posicaoLinha][posicaoColuna].setBomba();
        }

    }

    // Coloca os números de acordo com as bombas
    public void inserirNumeros () {
        for(int lin=0; lin<linha; lin++) {
            for (int col= 0; col<coluna; col++) {
                if (tabuleiro[lin][col].isBomba()) {continue;}
                for (int linvizinha=-1; linvizinha<=1; linvizinha++) {
                    for (int colvizinha = -1; colvizinha <= 1; colvizinha++) {
                        if ((col+colvizinha) < 0 || (col+colvizinha) == coluna) continue;
                        if ((lin+linvizinha) <0 || (lin+linvizinha) == linha) continue;
                        tabuleiro[lin][col].setVizinho(tabuleiro[(lin+linvizinha)][(col+colvizinha)]);
                    }
                }
            }
        }
    }
    
    // Objeto de teste
    public void mostrarTabuleiro () {
        for (int i=0; i<linha; i++) {
            for (int j=0; j<coluna; j++) {
                System.out.print(" [");
                if (tabuleiro[i][j].isBomba()) {
                    System.out.print("B");
                } else {
                    System.out.print(tabuleiro[i][j].getQtdBombas());
                }
                System.out.print("] ");
            }
            System.out.println(" ");
        }
    }
}
