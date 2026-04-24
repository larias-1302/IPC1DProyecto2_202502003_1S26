```java
package gui;
import estructuras.*;
import modelos.*;
import archivos.GestorArchivos;
import hilos.Taquilla;
import javax.swing.*;
import java.awt.*;
import proyecto2_ipc1_lab.GameZone;
import sistema.SistemaRecompensas;

public class PanelEventos extends JPanel {
    private Cola cola;
    private ListaSimple torneos;
    private JList lista;
    private DefaultListModel modeloLista;
    private JTextArea areaCola;
    private JTextArea log;
    private JLabel estado1;
    private JLabel estado2;
    private Torneo seleccionado;
    private GameZone ventana;
    private SistemaRecompensas sistema;

    public PanelEventos(GameZone ventana, SistemaRecompensas sistema) {
        this.ventana = ventana;
        this.sistema = sistema;
        setLayout(new BorderLayout());
        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> ventana.mostrar("MENU"));
        add(volver, BorderLayout.SOUTH);
        
        cola = new Cola();
        torneos = GestorArchivos.cargarTorneos("src/archivos/torneos.txt");
        modeloLista = new DefaultListModel();
        lista = new JList(modeloLista);

        Nodo temp = torneos.getCabeza();
        while (temp != null) {
            modeloLista.addElement(temp.dato);
            temp = temp.siguiente;
        }

        add(new JScrollPane(lista), BorderLayout.WEST);
        
        JPanel centro = new JPanel(new BorderLayout());
        JButton inscribir = new JButton("Inscribirse");
        centro.add(inscribir, BorderLayout.NORTH);

        areaCola = new JTextArea();
        centro.add(new JScrollPane(areaCola), BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);

        JPanel derecha = new JPanel(new BorderLayout());
        estado1 = new JLabel("Taquilla1: Libre");
        estado2 = new JLabel("Taquilla2: Libre");

        JPanel estados = new JPanel(new GridLayout(2,1));
        estados.add(estado1);
        estados.add(estado2);
        derecha.add(estados, BorderLayout.NORTH);

        log = new JTextArea();
        derecha.add(new JScrollPane(log), BorderLayout.CENTER);
        
        JButton iniciar = new JButton("Iniciar Venta");
        derecha.add(iniciar, BorderLayout.SOUTH);
        add(derecha, BorderLayout.EAST);

        lista.addListSelectionListener(e -> {
            seleccionado = (Torneo) lista.getSelectedValue();
        });

        inscribir.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre:");

            if (nombre != null) {
                cola.encolar(nombre);
                sistema.registrarTorneo();
                ventana.actualizarRecompensas();
                actualizarCola();
                ventana.sumarXP(150);
            }
        });

        iniciar.addActionListener(e -> {
            Taquilla t1 = new Taquilla("Taquilla1", cola, seleccionado, log, estado1, areaCola);
            Taquilla t2 = new Taquilla("Taquilla2", cola, seleccionado, log, estado2, areaCola);

            t1.start();
            t2.start();
        });
    }

//---------------------------------ACTUALIZAR LA COLA--------------------------
    private void actualizarCola() {
        areaCola.setText("");
        NodoCola temp = cola.getFrente();

        while (temp != null) {
            areaCola.append(temp.dato + "\n");
            temp = temp.siguiente;
        }
    }
    
    public ListaSimple getTorneos() {
        return torneos;
    }
}
