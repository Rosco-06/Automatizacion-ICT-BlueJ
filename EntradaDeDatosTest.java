package src;

import static org.junit.Assert.*;
import org.junit.Test;

public class EntradaDeDatosTest {

    @Test
    public void testArchivoInexistente() {
        EntradaDeDatos lector = new EntradaDeDatos();
        
        // Simulamos el peor caso: el usuario ha borrado el archivo mapas_base.txt
        // Le pasamos una ruta inventada que sabemos que no existe
        MapaHabitacion mapa = lector.cargarConfiguracionInicial("ruta_falsa_inventada.txt");
        
        // Según tu código (línea 49 de EntradaDeDatos), si el try-catch captura un 
        // IOException, imprime un error pero devuelve "null" para no colgar el programa.
        // Vamos a verificar que efectivamente devuelve null.
        assertNull("Si el archivo no existe, debe atrapar el error y devolver null", mapa);
    }
}