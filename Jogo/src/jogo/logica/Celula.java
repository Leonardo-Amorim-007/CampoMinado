package jogo.logica;

import java.util.ArrayList;

public class Celula {
    private boolean bomba = false;
    private boolean bandeira = false;
    private boolean aberto = false;
    private int qtdBombas = 0;
    private ArrayList<Celula> vizinhos = new ArrayList<>();


    // Metodos de entreda
    public void setBomba (){
        bomba = true;
    }

    public void setqtdBombas (int num) {
        qtdBombas = num;
    }

    public boolean setAbrir () {
        if (!aberto && !bandeira) {
            aberto = true;
            // Abertura dos vizinhos
            if (this.qtdBombas == 0 && !this.bomba) {
                for (Celula vizinho : vizinhos) {
                    vizinho.setAbrir();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void setalternarBandeira () {
        if (!aberto) {
            bandeira = !bandeira;
        }
    }

    public void setVizinho (Celula vizinho) {
        vizinhos.add(vizinho);
        if (vizinho.isBomb()) {
            qtdBombas++;
        }
    }

    // Metodos de retono
    public boolean isAberto () {return aberto;}

    public boolean isBomb() {return bomba;}

    public int getQtdBombas () {return qtdBombas;}

    public boolean isBandeira () {
        return bandeira;
    }
}
