package src;

// import algorithm.EnrutadorAStar;
// import io.EntradaDeDatos;
// import models.Celda;
// import models.MapaHabitacion;
// import models.RutaCalculada;
// import models.TipoZona;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PrincipalGUI extends JFrame {
    private MapaHabitacion mapaActual;
    private JPanel panelCuadricula;
    private JButton btnCargarDatos;
    private JButton btnCalcularRuta;
    private JButton btnLimpiar;

    public PrincipalGUI() {
        setTitle("Automatización de una ICT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panelControles = new JPanel();
        btnCargarDatos = new JButton("Cargar Datos");
        btnCalcularRuta = new JButton("Calcular Ruta");
        btnLimpiar = new JButton("Limpiar");

        panelControles.add(btnCargarDatos);
        panelControles.add(btnCalcularRuta);
        panelControles.add(btnLimpiar);
        add(panelControles, BorderLayout.NORTH);

        panelCuadricula = new JPanel();
        add(panelCuadricula, BorderLayout.CENTER);

        btnCargarDatos.addActionListener(e -> cargarConfiguracionInicial());
        btnLimpiar.addActionListener(e -> limpiarMapa());
        btnCalcularRuta.addActionListener(e -> iniciarCalculoRuta());

        setVisible(true);
    }

    public void cargarConfiguracionInicial() {
        EntradaDeDatos lector = new EntradaDeDatos();
        mapaActual = lector.cargarConfiguracionInicial("mapas_base.txt");
        if (mapaActual != null) {
            actualizarCuadriculaGUI();
            JOptionPane.showMessageDialog(this, "Datos cargados correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar mapas_base.txt. Asegúrese de que el archivo existe en la ruta de ejecución.");
        }
    }

    public void limpiarMapa() {
        if (mapaActual != null) {
            mapaActual.limpiarMapa();
            actualizarCuadriculaGUI();
        }
    }

    public void actualizarCuadriculaGUI() {
        panelCuadricula.removeAll();
        int filas = mapaActual.getFilas();
        int columnas = mapaActual.getColumnas();
        panelCuadricula.setLayout(new GridLayout(filas, columnas));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda c = mapaActual.getCelda(i, j);
                JButton btnCelda = new JButton();

                actualizarColorBoton(btnCelda, c.getTipoZona());

                btnCelda.addActionListener(e -> {
                    TipoZona nuevaZona = ciclarZona(c.getTipoZona());
                    c.setTipoZona(nuevaZona);
                    actualizarColorBoton(btnCelda, nuevaZona);
                });

                panelCuadricula.add(btnCelda);
            }
        }
        panelCuadricula.revalidate();
        panelCuadricula.repaint();
    }

    public TipoZona ciclarZona(TipoZona actual) {
        switch (actual) {
            case AIRE: return TipoZona.PARED;
            case PARED: return TipoZona.OBSTACULO;
            case OBSTACULO: return TipoZona.TOMA;
            case TOMA: return TipoZona.AIRE;
        }
        return TipoZona.AIRE;
    }

    public void actualizarColorBoton(JButton btn, TipoZona zona) {
        switch (zona) {
            case TOMA: btn.setBackground(Color.BLUE); break;
            case PARED: btn.setBackground(Color.DARK_GRAY); break;
            case OBSTACULO: btn.setBackground(Color.RED); break;
            case AIRE: btn.setBackground(Color.WHITE); break;
            default: btn.setBackground(Color.WHITE); break;
        }
    }

    public void iniciarCalculoRuta() {
        if (mapaActual == null) return;
        
        Celda inicio = null;
        Celda fin = null;

        for (int i = 0; i < mapaActual.getFilas(); i++) {
            for (int j = 0; j < mapaActual.getColumnas(); j++) {
                Celda c = mapaActual.getCelda(i, j);
                if (c.getTipoZona() == TipoZona.TOMA) {
                    if (inicio == null) inicio = c;
                    else if (fin == null) fin = c;
                }
            }
        }

        if (inicio == null || fin == null) {
            JOptionPane.showMessageDialog(this, "Deben existir al menos 2 zonas TOMA (Inicio y Fin) azules.");
            return;
        }

        actualizarCuadriculaGUI();

        EnrutadorAStar enrutador = new EnrutadorAStar();
        RutaCalculada ruta = enrutador.calcularRuta(mapaActual, inicio, fin);
        
        if (ruta != null && !ruta.getCamino().isEmpty()) {
             for (Celda c : ruta.getCamino()) {
                 if (c != inicio && c != fin) {
                     int pos = c.getCoordenadaX() * mapaActual.getColumnas() + c.getCoordenadaY();
                     JButton b = (JButton) panelCuadricula.getComponent(pos);
                     b.setBackground(Color.YELLOW);
                 }
             }
             almacenarTrazado(ruta);
             String msg = ruta.isEsViable() ? 
                "Ruta viable encontrada por las paredes y guardada en registro_rutas.txt." : 
                "Ruta forzada (cruza aire u obstáculo) calculada y guardada.";
             JOptionPane.showMessageDialog(this, msg);
        } else {
             JOptionPane.showMessageDialog(this, "No se encontró ninguna ruta.");
        }
    }

    public void almacenarTrazado(RutaCalculada ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("registro_rutas.txt"))) {
            bw.write("Distancia Total (pasos): " + ruta.getDistanciaTotal() + "\n");
            bw.write("Coste Acumulado: " + ruta.getCosteAcumulado() + "\n");
            bw.write("Es Viable: " + ruta.isEsViable() + "\n");
            bw.write("Camino (X, Y):\n");
            for (Celda c : ruta.getCamino()) {
                bw.write("(" + c.getCoordenadaX() + ", " + c.getCoordenadaY() + ") -> Tipo: " + c.getTipoZona() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar registro_rutas.txt");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrincipalGUI::new);
    }
}
