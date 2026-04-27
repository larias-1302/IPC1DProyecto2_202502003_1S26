package gui;
import modelos.Usuario;
import archivos.GestorArchivos;
import sistema.Leaderboard;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelLeaderboard extends JPanel {
    public PanelLeaderboard(String usuarioActual) {
        setLayout(new BorderLayout());
        Usuario[] usuarios = GestorArchivos.cargarLeaderboard("src/archivos/leaderboard.txt");
        Leaderboard.ordenar(usuarios);

//---------------------------------PANEL DEL TOP3---------------------------------
        JPanel top = new JPanel(new GridLayout(1,3));

        JLabel primero = new JLabel(" 1 " + usuarios[0], SwingConstants.CENTER);
        JLabel segundo = new JLabel(" 2 " + usuarios[1], SwingConstants.CENTER);
        JLabel tercero = new JLabel(" 3 " + usuarios[2], SwingConstants.CENTER);

        primero.setForeground(Color.ORANGE);
        segundo.setForeground(Color.GRAY);
        tercero.setForeground(new Color(139,69,19));

        top.add(primero);
        top.add(segundo);
        top.add(tercero);
        add(top, BorderLayout.NORTH);

//---------------------------------TABLA COMPLETA---------------------------------
        String[] columnas = {"Posición", "Nombre", "XP"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < usuarios.length && i < 10; i++) {
            modelo.addRow(new Object[]{
                    i + 1,
                    usuarios[i].nombre,
                    usuarios[i].xp
            });
        }
        JTable tabla = new JTable(modelo);

//---------------------------------RESALTA AL USUARIO ACTUAL---------------------------------
        tabla.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column){
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String nombre = table.getValueAt(row, 1).toString();

                if (nombre.equals(usuarioActual)) {
                    c.setBackground(Color.YELLOW);
                }else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);

//-----------------------------------CREACION DEL BOTON VOLVER-------------------------------------
        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> {
            ((proyecto2_ipc1_lab.GameZone) SwingUtilities.getWindowAncestor(this)).mostrar("MENU");
        });
        add(volver, BorderLayout.SOUTH);
    }
}