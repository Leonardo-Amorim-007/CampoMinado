package jogo.logica;

import java.util.ArrayList;

public class Celula {
    private boolean bomba = false;
    private boolean bandeira = false;
    private boolean aberto = false;
    private int qtdBombas;
    private ArrayList<Celula> vizinhos = new ArrayList<>();


    // Metodos de entreda
    public void setBomba (){
        bomba = true;
    }

    public void setqtdBombas (int num) {
        qtdBombas = num;
    }

    public void setabrir () {
        if (!aberto && !bandeira) {
            aberto = true;
        }
    }

    public void setalternarBandeira () {
        if (!aberto) {
            bandeira = !bandeira;
        }
    }

    public void setVizinho (Celula vizinho) {
        vizinhos.add(vizinho);
        if (vizinho.isBomba()) {
            qtdBombas++;
        }
    }

    // Metodos de retono
    public boolean isAberto () {
        return aberto;
    }

    public boolean isBomba () {
        return bomba;
    }

    public int getQtdBombas () {
        return qtdBombas;
    }

    public boolean isBandeira () {
        return bandeira;
    }
}
