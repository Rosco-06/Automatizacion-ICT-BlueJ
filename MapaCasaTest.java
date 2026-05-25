package src;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapaCasaTest {
    
    private MapaCasa casaPrueba;

    /**
     * Preparamos el entorno antes de cada test creando una casa nueva y vacía.
     */
    @Before
    public void setUp() {
        casaPrueba = new MapaCasa();
    }

    /**
     * Prueba 1: Verificar que las listas se inicializan correctamente 
     * (esto demuestra que evitas el temido NullPointerException).
     */
    @Test
    public void testInicializacionListas() {
        // Comprobamos que las listas existen (no son nulas) y empiezan vacías
        assertNotNull("La lista de habitaciones no debe ser nula", casaPrueba.getHabitaciones());
        assertTrue("La lista de habitaciones debe empezar vacía", casaPrueba.getHabitaciones().isEmpty());
        
        assertNotNull("La lista de rutas no debe ser nula", casaPrueba.getRutasCalculadas());
        assertTrue("La lista de rutas debe empezar vacía", casaPrueba.getRutasCalculadas().isEmpty());
    }

    /**
     * Prueba 2: Verificar que podemos agregar habitaciones a la casa correctamente.
     */
    @Test
    public void testAgregarHabitacion() {
        // Creamos una habitación de prueba simulando una matriz 10x10
        MapaHabitacion salon = new MapaHabitacion("Salon", 10, 10);
        
        // La añadimos a la casa
        casaPrueba.agregarHabitacion(salon);
        
        // Verificamos que la lista ahora tiene 1 elemento
        assertEquals("Debe haber exactamente 1 habitación guardada", 1, casaPrueba.getHabitaciones().size());
        
        // Verificamos que el elemento guardado es exactamente el que metimos
        assertEquals("El nombre de la habitación debe ser Salon", "Salon", casaPrueba.getHabitaciones().get(0).getIdentificador());
    }

    /**
     * Prueba 3: Verificar que el historial de rutas se guarda correctamente.
     */
    @Test
    public void testAgregarRuta() {
        // Creamos una ruta simulada
        RutaCalculada rutaSimulada = new RutaCalculada();
        rutaSimulada.setDistanciaTotal(25.5);
        
        // La añadimos al historial de la casa
        casaPrueba.agregarRuta(rutaSimulada);
        
        // Verificamos que se ha guardado en la lista
        assertEquals("Debe haber exactamente 1 ruta en el historial", 1, casaPrueba.getRutasCalculadas().size());
        assertEquals("La distancia de la ruta guardada debe ser 25.5", 25.5, casaPrueba.getRutasCalculadas().get(0).getDistanciaTotal(), 0.0);
    }
}