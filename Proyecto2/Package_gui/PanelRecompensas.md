```java
package gui;
import sistema.*;
import modelos.*;
import javax.swing.*;
import java.awt.*;
import proyecto2_ipc1_lab.GameZone;

public class PanelRecompensas extends JPanel {
    private SistemaRecompensas sistema;
    private JProgressBar barra;
    private JLabel lblNivel;
    private JTextArea areaLogros;
    private GameZone ventana;

    public PanelRecompensas(GameZone ventana, SistemaRecompensas sistema) {
        this.ventana = ventana;
        this.sistema = sistema;
        setLayout(new BorderLayout());

//---------------------------------CREACION DEL BOTON VOLVER-------------------------
        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> ventana.mostrar("MENU"));
        add(volver, BorderLayout.SOUTH);

//---------------------------------CREACION DEL PANEL-------------------------
        JPanel top = new JPanel(new GridLayout(2,1));
        lblNivel = new JLabel();
        barra = new JProgressBar();

        top.add(lblNivel);
        top.add(barra);
        add(top, BorderLayout.NORTH);

//---------------------------------CREACION DE LOS LOGROS-------------------------
        areaLogros = new JTextArea();
        add(new JScrollPane(areaLogros), BorderLayout.CENTER);
        actualizar();
    }

//---------------------------------ACTUALIZAR LOS LOGROS AL OBTENERLOS-------------------------
    public void actualizar() {
        lblNivel.setText("Nivel " + sistema.getNivel() + " - " + sistema.getRango());
        barra.setMaximum(sistema.getXPMax());
        barra.setValue(sistema.getXPProgreso());
        areaLogros.setText("");
        estructuras.Nodo temp = sistema.getLogros().getCabeza();

        while (temp != null) {
            Logro l = (Logro) temp.dato;
            if (l.desbloqueado) {
                areaLogros.append("✔ " + l.nombre + "\n");
            } else {
                areaLogros.append("🔒 " + l.nombre + "\n");
            }
            temp = temp.siguiente;
        }
    }
}
