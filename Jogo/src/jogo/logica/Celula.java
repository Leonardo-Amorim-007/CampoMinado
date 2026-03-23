package jogo.logica;

import java.util.ArrayList;
import jogo.interfaces.CelulaObserver;

public class Celula {
    private boolean bomba = false;
    private boolean bandeira = false;
    private boolean aberto = false;
    private int qtdBombas = 0;
    private ArrayList<Celula> vizinhos = new ArrayList<>();
    private CelulaObserver observer;

    // Criacao da interface saida
    public void setObserver(CelulaObserver observer) {
        this.observer = observer;
    }

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
            if (observer != null) {
                observer.aoAbrirCelula(); // Avisa quem estiver ouvindo
            }
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

    public boolean setBandeira () {
        if (!aberto) {
            bandeira = !bandeira;
            return true;
        } else {
            return false;
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