package gui;
import javax.swing.*;
import java.awt.*;
import proyecto2_ipc1_lab.GameZone;

public class PanelDatos extends JPanel {
    public PanelDatos(GameZone ventana) {
        setLayout(new BorderLayout());

//---------------------------------CREACION DEL PANEL------------------------------------
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre: Luis Emilio Arias Santizo"));
        panel.add(new JLabel("Carné: 202502003"));
        panel.add(new JLabel("Correo: lariassantizo@gmail.com"));
        panel.add(new JLabel("Sección: D"));
        panel.add(new JLabel("Semestre: 1er Semestre 2026"));
        panel.add(Box.createVerticalStrut(15));

        JTextArea descripcion = new JTextArea(
                "GameZone Pro es una plataforma que simula una tienda de videojuegos, "
                + "un sistema de álbum de cartas, torneos con concurrencia mediante hilos, "
                + "un sistema de recompensas y generación de reportes HTML."
        );

        descripcion.setWrapStyleWord(true);
        descripcion.setLineWrap(true);
        descripcion.setEditable(false);
        panel.add(new JScrollPane(descripcion));
        add(panel, BorderLayout.CENTER);

//---------------------------------CREACION DEL BOTON VOLVER------------------------------------
        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> {
            ventana.mostrar("MENU");
        });
        add(volver, BorderLayout.SOUTH);
    }
}