package src;

// import models.Celda;
// import models.MapaHabitacion;
// import models.RutaCalculada;
// import models.TipoZona;
import java.util.*;

public class EnrutadorAStar {

    public class Nodo implements Comparable<Nodo> {
        private Celda celda;
        private Nodo padre;
        private int gScore; 
        private int fScore; 

        public Nodo(Celda celda, Nodo padre, int gScore, int hScore) {
            this.celda = celda;
            this.padre = padre;
            this.gScore = gScore;
            this.fScore = gScore + hScore;
        }

        @Override
        public int compareTo(Nodo otro) {
            return Integer.compare(this.fScore, otro.fScore);
        }

        public Celda getCelda() {
            return celda;
        }

        public Nodo getPadre() {
            return padre;
        }

        public int getGScore() {
            return gScore;
        }

        public int getFScore() {
            return fScore;
        }
    }

    private PriorityQueue<Nodo> openSet;
    private Map<Celda, Nodo> mejoresCaminos;
    private int[][] direcciones;

    public EnrutadorAStar() {
        this.openSet = new PriorityQueue<>();
        this.mejoresCaminos = new HashMap<>();
        this.direcciones = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
    }

    public RutaCalculada calcularRuta(MapaHabitacion mapa, Celda inicio, Celda fin) {
        if (inicio == null || fin == null) return null;

        this.openSet.clear();
        this.mejoresCaminos.clear();

        Nodo nodoInicio = new Nodo(inicio, null, 0, calcularHeuristicaDistancia(inicio, fin));
        this.openSet.add(nodoInicio);
        this.mejoresCaminos.put(inicio, nodoInicio);

        while (!this.openSet.isEmpty()) {
            Nodo actual = this.openSet.poll();

            if (actual.getCelda().equals(fin)) {
                return construirRuta(actual);
            }

            for (int[] dir : this.direcciones) {
                int nx = actual.getCelda().getCoordenadaX() + dir[0];
                int ny = actual.getCelda().getCoordenadaY() + dir[1];

                Celda vecino = mapa.getCelda(nx, ny);
                if (vecino != null) {
                    
                    int costoTotalTransicion = vecino.getCostePaso();

                    int nuevoGScore = actual.getGScore() + costoTotalTransicion;

                    Nodo nodoVecinoPrevio = this.mejoresCaminos.get(vecino);
                    if (nodoVecinoPrevio == null || nuevoGScore < nodoVecinoPrevio.getGScore()) {
                        int hScore = calcularHeuristicaDistancia(vecino, fin);
                        Nodo nuevoNodoVecino = new Nodo(vecino, actual, nuevoGScore, hScore);
                        
                        this.mejoresCaminos.put(vecino, nuevoNodoVecino);
                        this.openSet.add(nuevoNodoVecino);
                    }
                }
            }
        }

        RutaCalculada rutaError = new RutaCalculada();
        rutaError.setEsViable(false);
        return rutaError;
    }

    public int calcularHeuristicaDistancia(Celda a, Celda b) {
        return Math.abs(a.getCoordenadaX() - b.getCoordenadaX()) + Math.abs(a.getCoordenadaY() - b.getCoordenadaY());
    }

    public RutaCalculada construirRuta(Nodo nodoFinal) {
        RutaCalculada ruta = new RutaCalculada();
        List<Celda> camino = ruta.getCamino();
        Nodo actual = nodoFinal;
        boolean esViable = true;
        
        while (actual != null) {
            camino.add(0, actual.getCelda());
            if (actual.getCelda().getTipoZona() == TipoZona.AIRE || actual.getCelda().getTipoZona() == TipoZona.OBSTACULO) {
                esViable = false;
            }
            actual = actual.getPadre();
        }

        ruta.setDistanciaTotal(camino.size() - 1); 
        ruta.setCosteAcumulado(nodoFinal.getGScore());
        ruta.setEsViable(esViable);

        return ruta;
    }
}
