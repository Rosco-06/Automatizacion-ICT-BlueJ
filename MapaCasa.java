package src;

import java.util.ArrayList;
import java.util.List;

public class MapaCasa {
    private List<MapaHabitacion> habitaciones;
    private List<RutaCalculada> rutasCalculadas;

    public MapaCasa() {
        this.habitaciones = new ArrayList<>();
        this.rutasCalculadas = new ArrayList<>();
    }

    public List<MapaHabitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<MapaHabitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<RutaCalculada> getRutasCalculadas() {
        return rutasCalculadas;
    }

    public void setRutasCalculadas(List<RutaCalculada> rutasCalculadas) {
        this.rutasCalculadas = rutasCalculadas;
    }

    public void agregarHabitacion(MapaHabitacion habitacion) {
        this.habitaciones.add(habitacion);
    }

    public void agregarRuta(RutaCalculada ruta) {
        this.rutasCalculadas.add(ruta);
    }
}
