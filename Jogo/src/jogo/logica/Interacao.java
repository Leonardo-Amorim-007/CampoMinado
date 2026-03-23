package jogo.logica;

public class Interacao {
    private Tabuleiro tabuleiro;
    private int qtdBandeirasColocadas = 0;

    public boolean verificarJogada(int linha, int coluna) {
        return  linha >= 0 && linha < tabuleiro.getlinha() && coluna >= 0 && coluna < tabuleiro.getColuna();
    }

    public void getTabuleiro(Tabuleiro tabuleiro) {this.tabuleiro = tabuleiro;}

    public record RepostaRevelarCelula (boolean fim, String mensagem) {}

    // Revela a celula e retorna uma mensagem dizendo o que aconteceu
    public RepostaRevelarCelula revelarCelula(int linha, int coluna) {
        String mensagem;
        boolean fim = false;
        if (verificarJogada(linha, coluna)) {
            if (tabuleiro.setAberto(linha, coluna)) {
                if (tabuleiro.getBomb(linha, coluna)) {
                    fim = true;
                    mensagem = "Que pena, você explodiu e não sobreviveu.";
                } else {
                    mensagem = "Celula revelada com sucesso.";
                }
            } else if (tabuleiro.getBandeira(linha, coluna)){
                mensagem = "A celula contem uma BANDEIRA!";
            } else {
                mensagem = "A celula ja esta ABERTA!";
            }
        } else {
            mensagem = "Os parametros foram inseridos INCORRETAMENTE!";
        }
        return new RepostaRevelarCelula(fim, mensagem);
    }

    public String trocarBandeira (int linha, int coluna) {
        String mensagem;
        if (verificarJogada(linha, coluna)) {
            if (tabuleiro.getQtdBombas() >= qtdBandeirasColocadas) {
                if (!tabuleiro.getBandeira(linha, coluna) & tabuleiro.setBandeira(linha, coluna)) {
                    mensagem = "Bandeira colocada com sucesso.";
                    qtdBandeirasColocadas++;
                } else {
                    mensagem = "Bandeira retirada com sucesso.";
                    qtdBandeirasColocadas--;
                }
            } else {
                mensagem = "Você já colocou todas as bandeiras que tinha!";
            }
        } else {
            mensagem = "Os parametros foram inseridos INCORRETAMENTE!";
        }
        return mensagem;
    }

}