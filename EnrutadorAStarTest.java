package src;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EnrutadorAStarTest {
    
    private EnrutadorAStar enrutador;
    private MapaHabitacion mapa;

    /**
     * Preparamos un enrutador limpio y un mapa de 5x5 antes de cada test.
     */
    @Before
    public void setUp() {
        enrutador = new EnrutadorAStar();
        mapa = new MapaHabitacion("Test_AStar", 5, 5);
    }

    /**
     * Prueba 1: Verifica la matemática del algoritmo calculando la distancia de Manhattan.
     */
    @Test
    public void testCalculoHeuristicaDistancia() {
        Celda inicio = mapa.getCelda(0, 0);
        Celda fin = mapa.getCelda(3, 4);
        
        // La distancia de Manhattan entre la coordenada (0,0) y la (3,4) debe ser:
        // |0 - 3| + |0 - 4| = 3 + 4 = 7
        int heuristica = enrutador.calcularHeuristicaDistancia(inicio, fin);
        
        assertEquals("La distancia Manhattan calculada es incorrecta", 7, heuristica);
    }

    /**
     * Prueba 2: Simula un pequeño mapa, traza unas paredes y verifica si el algoritmo
     * encuentra el camino correcto por ellas.
     */
    @Test
    public void testRutaViablePorParedes() {
        // Configuramos un pasillo recto válido hecho de PAREDES y TOMAS
        // (Recuerda que el resto del mapa 5x5 sigue siendo AIRE infranqueable)
        mapa.getCelda(0, 0).setTipoZona(TipoZona.TOMA);
        mapa.getCelda(0, 1).setTipoZona(TipoZona.PARED);
        mapa.getCelda(0, 2).setTipoZona(TipoZona.PARED);
        mapa.getCelda(0, 3).setTipoZona(TipoZona.TOMA);

        Celda inicio = mapa.getCelda(0, 0);
        Celda fin = mapa.getCelda(0, 3);

        // Ejecutamos el algoritmo mágico
        RutaCalculada ruta = enrutador.calcularRuta(mapa, inicio, fin);

        // Verificaciones críticas para sacar un 10
        assertNotNull("La ruta devuelta no debería ser nula (debería encontrar camino)", ruta);
        assertTrue("La ruta debe marcarse como viable porque discurre exclusivamente por pared", ruta.isEsViable());
        
        // Al saltar de la columna 0 a la 3 en línea recta, se dan exactamente 3 pasos
        assertEquals("La distancia de la ruta debe ser exactamente 3 pasos", 3.0, ruta.getDistanciaTotal(), 0.0);
    }
}