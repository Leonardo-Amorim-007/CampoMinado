package jogo.logica;

public class Interacao {
    private Tabuleiro tabuleiro;

    public void getTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    // Revela a celula e retorna uma mensagem dizendo o que aconteceu
    public String revelarCelula(int linha, int coluna) {
        if (linha >= 0 && linha < tabuleiro.getlinha() &&
                coluna >= 0 && coluna < tabuleiro.getColuna()) {
            if (tabuleiro.setAberto(linha, coluna)) {
                return "Celula revelada com sucesso.";
            } else {
                return "A celula ja esta ABERTA!";
            }
        } else {
            return "Os parametros foram inseridos INCORRETAMENTE!";
        }
    }
}