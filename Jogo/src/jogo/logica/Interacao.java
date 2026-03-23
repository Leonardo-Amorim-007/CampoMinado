package jogo.logica;

public class Interacao {
    private Tabuleiro tabuleiro;
    private int qtdBandeirasColocadas = 0;

    public void getTabuleiro(Tabuleiro tabuleiro) {this.tabuleiro = tabuleiro;}

    public record RespostaRevelarCelula (boolean fim, String mensagem) {}

    // Revela a celula e retorna uma mensagem dizendo o que aconteceu
    public RespostaRevelarCelula revelarCelula(int linha, int coluna) {
        if (!tabuleiro.VerificarCoordenada(linha, coluna)) {
            return new RespostaRevelarCelula(false, "Os parametros foram inseridos INCORRETAMENTE!");
        }

        if (tabuleiro.getBandeira(linha, coluna)) {
            return new RespostaRevelarCelula(false, "A celula contem uma BANDEIRA!");
        }

        // Abre a celula e coleta a respota se foi aberta, se terminou o jogo, se terminou o estado que terminaou
        Tabuleiro.RespostaSetAberto resposta = tabuleiro.setAberto(linha, coluna);

        if (!resposta.aberto()) {
            return new RespostaRevelarCelula(false, "A celula ja esta ABERTA!");
        }

        // Verificacao da vitoria e caso positiva, retorna a mensagem de vitoria ou derrota
        if (resposta.fim()) {
            return new RespostaRevelarCelula(true, fimDeJogo(resposta.vitoria()));
        }

        return new RespostaRevelarCelula(false, "Celula revelada com sucesso.");
    }

    // Realiza a mudanca no status da bandeira
    public String trocarBandeira (int linha, int coluna) {
        if (!tabuleiro.VerificarCoordenada(linha, coluna)) {return "Os parametros foram inseridos INCORRETAMENTE!";}

        if (qtdBandeirasColocadas == tabuleiro.getQtdBombas()) {return "Você já colocou todas as bandeiras que tinha!";}

        if (!tabuleiro.setBandeira(linha, coluna)) {return "A celula esta revelada, nao pode colocar a bandeira!";}

        if (!tabuleiro.getBandeira(linha, coluna)) {
            qtdBandeirasColocadas--;
            return "Bandeira retirada com sucesso.";
        }
        // Caso todas as condicoes sejam falsas
        qtdBandeirasColocadas++;
        return "Bandeira colocada com sucesso.";
    }

    public String fimDeJogo (boolean vitoria) {
        if (!vitoria) {
            return "Infelizmente, você pisou numa bomba e se explodiu.";
        } else {
            return "Parabéns, você conseguiu atravessar o campo sem se explodir. Agora tem outro te esperando!";
        }
    }
}