package src;

public class MapaHabitacion {
    private String identificador;
    private int filas;
    private int columnas;
    private Celda[][] cuadricula;

    public MapaHabitacion(String identificador, int filas, int columnas) {
        this.identificador = identificador;
        this.filas = filas;
        this.columnas = columnas;
        this.cuadricula = new Celda[filas][columnas];
        limpiarMapa();
    }

    public void limpiarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.cuadricula[i][j] = new Celda(i, j);
            }
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Celda[][] getCuadricula() {
        return cuadricula;
    }

    public void setCuadricula(Celda[][] cuadricula) {
        this.cuadricula = cuadricula;
    }

    public Celda getCelda(int f, int c) {
        if(f >= 0 && f < filas && c >= 0 && c < columnas) {
            return cuadricula[f][c];
        }
        return null;
    }
}
