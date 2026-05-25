package src;

public class Componente {
    private String nombre;
    private int numeroTomas;

    public Componente() {
    }

    public Componente(String nombre, int numeroTomas) {
        this.nombre = nombre;
        this.numeroTomas = numeroTomas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroTomas() {
        return numeroTomas;
    }

    public void setNumeroTomas(int numeroTomas) {
        this.numeroTomas = numeroTomas;
    }
}
