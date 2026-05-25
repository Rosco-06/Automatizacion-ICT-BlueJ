package src;

import static org.junit.Assert.*;
import org.junit.Test;

public class CeldaTest {

    @Test
    public void testCostesParadigmaArquitectonico() {
        // 1. Creamos una celda genérica en las coordenadas 1,1
        Celda celda = new Celda(1, 1);
        
        // 2. Comprobar coste de PARED (debe ser 1, camino natural)
        celda.setTipoZona(TipoZona.PARED);
        assertEquals("Una PARED debe tener un coste de paso de 1", 1, celda.getCostePaso());

        // 3. Comprobar coste de AIRE (debe ser 9999, infranqueable)
        celda.setTipoZona(TipoZona.AIRE);
        assertEquals("El AIRE debe ser infranqueable con coste 9999", 9999, celda.getCostePaso());
        
        // 4. Comprobar coste de OBSTACULO (debe ser 9999)
        celda.setTipoZona(TipoZona.OBSTACULO);
        assertEquals("Un OBSTACULO debe tener coste 9999", 9999, celda.getCostePaso());
        
        // 5. Comprobar coste de TOMA (debe ser 1, inicio/fin válido)
        celda.setTipoZona(TipoZona.TOMA);
        assertEquals("Una TOMA debe tener coste 1", 1, celda.getCostePaso());
    }
}