package src;

public class Celda {
    private int coordenadaX;
    private int coordenadaY;
    private TipoZona tipoZona;
    private int costePaso;
    private Componente componente;

    public Celda() {
        this.tipoZona = TipoZona.AIRE; // Por defecto es aire infranqueable
        this.costePaso = 9999;
    }

    public Celda(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.tipoZona = TipoZona.AIRE; // Por defecto es aire infranqueable
        this.costePaso = 9999;
    }

    public void actualizarZona(TipoZona nuevaZona, int coste) {
        this.tipoZona = nuevaZona;
        this.costePaso = coste;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public TipoZona getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(TipoZona tipoZona) {
        this.tipoZona = tipoZona;
        // Asignación de costes según el nuevo paradigma arquitectónico
        if (tipoZona == TipoZona.PARED) {
            this.costePaso = 1; // Camino natural
        } else if (tipoZona == TipoZona.TOMA) {
            this.costePaso = 1; // Inicio/Fin
        } else {
            this.costePaso = 9999; // Aire u Obstáculo (Restricción alta)
        }
    }

    public int getCostePaso() {
        return costePaso;
    }

    public void setCostePaso(int costePaso) {
        this.costePaso = costePaso;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }
}
