package hilos;
import estructuras.Cola;
import modelos.Torneo;
import archivos.GestorArchivos;
import modelos.Ticket;
import javax.swing.*;
import java.util.Random;

public class Taquilla extends Thread {
    private String nombreTaquilla;
    private Cola cola;
    private Torneo torneo;
    private JTextArea log;
    private JLabel estado;
    private JTextArea areaCola;

    public Taquilla(String nombreTaquilla, Cola cola, Torneo torneo,JTextArea log, JLabel estado, JTextArea areaCola) {
        this.nombreTaquilla = nombreTaquilla;
        this.cola = cola;
        this.torneo = torneo;
        this.log = log;
        this.estado = estado;
        this.areaCola = areaCola;
    }
    @Override
    
    public void run() {
        Random r = new Random();
        while (true) {
            String nombre;
            
            synchronized (torneo) {
                if (torneo.ticketsDisponibles <= 0 || cola.estaVacia()) {
                    break;
                }
                nombre = (String) cola.desencolar();

                if (nombre == null) {
                    break;
                }
                torneo.ticketsDisponibles--;
            }

//-----------------------------CAMBIA EL ESTADO A  PROCESANDO----------------------
            SwingUtilities.invokeLater(() -> {
                estado.setText(nombreTaquilla + ": Procesando " + nombre);
            });

            try {
                Thread.sleep(800 + r.nextInt(1200));
            }catch (Exception e) {
                e.printStackTrace();
            }

//-----------------------------CREA Y GUARDA EL TICKET----------------------
            Ticket t = new Ticket(nombre, torneo.nombre);
            GestorArchivos.guardarTicket(t);

            SwingUtilities.invokeLater(() -> {
                log.append("Vendido: " + nombre + " → " + torneo.nombre + "\n");
            });

//-----------------------------CAMBIA EL ESTADO A  LIBRE----------------------
            SwingUtilities.invokeLater(() -> {
                estado.setText(nombreTaquilla + ": Libre");
            });

//-----------------------------ACTUALIZA LA COLA----------------------
            SwingUtilities.invokeLater(() -> {
                areaCola.setText("");
                estructuras.NodoCola temp = cola.getFrente();

                while (temp != null) {
                    areaCola.append(temp.dato + "\n");
                    temp = temp.siguiente;
                }
            });
        }

//-----------------------------INDICA QUE NO HAY TICKETS RESTANTES----------------------
        synchronized (torneo) {
            if (torneo.ticketsDisponibles <= 0) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null,
                            "Tickets agotados para " + torneo.nombre);
                });
            }
        }
    }
}
