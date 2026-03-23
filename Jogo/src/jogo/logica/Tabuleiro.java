package jogo.logica;
import java.util.Random;
import jogo.interfaces.CelulaObserver;

public class Tabuleiro implements CelulaObserver {
    private int qtdCelulas, qtdBombas, qtdCelulasAbertas=0;
    private int coluna, linha;
    private int porcentagemBombas;
    private Celula[][] tabuleiro;

    // Inicio - Criacao do tabuleiro //

    // Verifica os parametros para a criação do tabuleiro
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
                tabuleiro[lin][col].setObserver(this);
            }
        }
        distribuirBombas();
        inserirNumeros();
    }

    // Distribui as bombas de acordo com a porcentagem
    public void distribuirBombas () {
        Random random = new Random();
        qtdBombas = (int) Math.ceil((double) (porcentagemBombas * qtdCelulas) /100) ;
        int posicaoColuna, posicaoLinha;
        for (int i=0; i<qtdBombas; i++){
            do {
                posicaoColuna = random.nextInt(0, (coluna-1));
                posicaoLinha = random.nextInt(0, (linha-1));
            } while (tabuleiro[posicaoLinha][posicaoColuna].isBomb());
            tabuleiro[posicaoLinha][posicaoColuna].setBomba();
        }

    }

    // Coloca os números de acordo com a posicao das bombas
    public void inserirNumeros () {
        for(int lin=0; lin<linha; lin++) {
            for (int col= 0; col<coluna; col++) {
                if (tabuleiro[lin][col].isBomb()) {continue;}
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
    // Fim - Criacao do tabuleiro //

    // Inicio - Interface para modificacoes das variaveis internas //
    @Override
    public void aoAbrirCelula() {
        this.qtdCelulasAbertas++;
    }
    // Fim - Modificacoes das variaveis internas //

    // Inicio - Modificacao das celulas //
    // Resposta com multi variaveis da funcao setaberto
    public record RespostaSetAberto (boolean aberto, boolean fim, boolean vitoria) {}

    // Abre a celula e verifica o fim do jogo
    public RespostaSetAberto setAberto (int linha, int coluna) {
        if (tabuleiro[linha][coluna].setAbrir()) {
            RespostaVerificacaoFimDeJogo resposta = verificacaoFimDeJogo(linha, coluna);
            return new RespostaSetAberto(true, resposta.fim(), resposta.vitoria());
        } else {
            return new RespostaSetAberto(false, false, false);
        }
    }

    // Coloca a bandeira na celula
    public boolean setBandeira (int linha, int coluna) {return tabuleiro[linha][coluna].setBandeira();}
    // Fim - Modificacao das celulas //

    // Inicio - Verificacoes do tabuleiro //
    // Verifica se a coordenada inserida e valida
    public boolean VerificarCoordenada (int linha, int coluna) {
        return  linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna;}

    // Resposta com multi variaveis da funcao verificacaoFimDeJogo
    public record RespostaVerificacaoFimDeJogo (boolean fim, boolean vitoria) {}

    // Verifica como o jogo se encerrou em vitoria ou derrota
    public RespostaVerificacaoFimDeJogo verificacaoFimDeJogo (int linha, int coluna) {
        if (tabuleiro[linha][coluna].isBomb()) {
            return new RespostaVerificacaoFimDeJogo(true, false);
        } else if (qtdCelulasAbertas == (qtdCelulas-qtdBombas)) {
            return new RespostaVerificacaoFimDeJogo(true, true);
        } else {
            return new RespostaVerificacaoFimDeJogo(false, false);
        }
    }
    // Fim - Verificacoes do tabuleiro //

    // Incio - Saída de dados //
    public boolean getAberto (int linha, int coluna) {return tabuleiro[linha][coluna].isAberto();}

    public int getlinha() {return linha;}

    public int getColuna() {return coluna;}

    public boolean getBomb (int linha, int coluna) {return tabuleiro[linha][coluna].isBomb();}

    public boolean getBandeira (int linha, int coluna) {return tabuleiro[linha][coluna].isBandeira();}

    public int getQtdBombasCelulas (int linha, int coluna) {return tabuleiro[linha][coluna].getQtdBombas();}

    public int getQtdBombas () {return qtdBombas;}
    // Fim - Saida de dados //
}