package src;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapaHabitacionTest {
    
    private MapaHabitacion mapaPrueba;

    /**
     * El método setUp se ejecuta siempre antes de cada prueba.
     */
    @Before
    public void setUp() {
        // Inicializamos un mapa de 5x5 dinámicamente para las pruebas
        mapaPrueba = new MapaHabitacion("Habitacion_Test", 5, 5);
    }

    /**
     * Prueba 1: Verificar que el constructor crea bien la matriz.
     */
    @Test
    public void testInicializacionMatriz() {
        assertEquals(5, mapaPrueba.getFilas());
        assertEquals(5, mapaPrueba.getColumnas());
        assertNotNull("La matriz cuadricula no debería ser nula", mapaPrueba.getCuadricula());
    }

    /**
     * Prueba 2: Verificar la modificación de celdas y el borrado del mapa.
     */
    @Test
    public void testModificarYLimpiarMapa() {
        // 1. Obtenemos una celda válida dentro del mapa (por ejemplo la 2,2)
        Celda celda = mapaPrueba.getCelda(2, 2);
        
        // Según tu código, por defecto todas nacen siendo AIRE
        assertEquals("La celda debería ser AIRE por defecto", TipoZona.AIRE, celda.getTipoZona());
        
        // 2. Modificamos la celda (le ponemos una PARED)
        celda.setTipoZona(TipoZona.PARED);
        assertEquals("La celda ahora debería ser PARED", TipoZona.PARED, celda.getTipoZona());
        
        // 3. Ejecutamos tu método limpiarMapa()
        mapaPrueba.limpiarMapa();
        
        // 4. Volvemos a pedir esa misma coordenada y comprobamos que se ha reseteado
        Celda celdaRestablecida = mapaPrueba.getCelda(2, 2);
        assertEquals("Tras limpiar el mapa, la celda debe volver a ser AIRE", TipoZona.AIRE, celdaRestablecida.getTipoZona());
    }
}