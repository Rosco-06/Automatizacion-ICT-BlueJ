package src;

// import models.Celda;
// import models.MapaHabitacion;
// import models.TipoZona;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EntradaDeDatos {
    
    public MapaHabitacion cargarConfiguracionInicial(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String identificador = br.readLine();
            if (identificador == null) return null;
            identificador = identificador.trim();
            
            int filas = Integer.parseInt(br.readLine().trim());
            int columnas = Integer.parseInt(br.readLine().trim());
            int inicioX = Integer.parseInt(br.readLine().trim());
            int inicioY = Integer.parseInt(br.readLine().trim());
            int finX = Integer.parseInt(br.readLine().trim());
            int finY = Integer.parseInt(br.readLine().trim());

            MapaHabitacion mapa = new MapaHabitacion(identificador, filas, columnas);
            
            // Dibujar plano de la casa (3 habitaciones) si el mapa es 20x20 o mayor
            if (filas >= 20 && columnas >= 20) {
                // Pared exterior de la casa (delimitando el espacio desde la celda 2 hasta la 18)
                for (int i = 2; i <= 18; i++) {
                    mapa.getCelda(2, i).setTipoZona(TipoZona.PARED); // Arriba
                    mapa.getCelda(18, i).setTipoZona(TipoZona.PARED); // Abajo
                    mapa.getCelda(i, 2).setTipoZona(TipoZona.PARED); // Izquierda
                    mapa.getCelda(i, 18).setTipoZona(TipoZona.PARED); // Derecha
                }
                
                // Pared divisoria vertical central (crea 2 mitades: izquierda y derecha)
                for (int i = 2; i <= 18; i++) {
                    mapa.getCelda(i, 10).setTipoZona(TipoZona.PARED);
                }
                
                // Pared divisoria horizontal (solo en la mitad izquierda, creando 3 habitaciones en total)
                for (int i = 2; i <= 10; i++) {
                    mapa.getCelda(10, i).setTipoZona(TipoZona.PARED);
                }
            }

            Celda inicio = mapa.getCelda(inicioX, inicioY);
            if (inicio != null) {
                inicio.setTipoZona(TipoZona.TOMA);
            }
            
            Celda fin = mapa.getCelda(finX, finY);
            if (fin != null) {
                fin.setTipoZona(TipoZona.TOMA);
            }

            return mapa;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar la configuración inicial: " + e.getMessage());
            return null;
        }
    }
}
