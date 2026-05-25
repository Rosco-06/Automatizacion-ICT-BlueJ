package src;

import java.util.ArrayList;
import java.util.List;

public class RutaCalculada {
    private double distanciaTotal;
    private int costeAcumulado;
    private boolean esViable;
    private List<Celda> camino;

    public RutaCalculada() {
        this.camino = new ArrayList<>();
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public int getCosteAcumulado() {
        return costeAcumulado;
    }

    public void setCosteAcumulado(int costeAcumulado) {
        this.costeAcumulado = costeAcumulado;
    }

    public boolean isEsViable() {
        return esViable;
    }

    public void setEsViable(boolean esViable) {
        this.esViable = esViable;
    }

    public List<Celda> getCamino() {
        return camino;
    }

    public void setCamino(List<Celda> camino) {
        this.camino = camino;
    }
}
