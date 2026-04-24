```java
package proyecto2_ipc1_lab;
import archivos.GestorArchivos;
import archivos.ReportesHTML;
import gui.PanelAlbum;
import gui.PanelEventos;
import gui.PanelLeaderboard;
import gui.PanelRecompensas;
import gui.PanelTienda;
import gui.PanelDatos;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;
import sistema.SistemaRecompensas;

public class GameZone extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameZone.class.getName());
    private CardLayout layout;
    private JPanel contenedor;
    private PanelAlbum album;
    private PanelRecompensas recompensas;
    private SistemaRecompensas sistema;
    private PanelTienda tienda;
    private PanelEventos eventos;
    
    public GameZone() {
    initComponents();
    layout = new CardLayout();
    contenedor = new JPanel(layout);
    sistema = new SistemaRecompensas();
    
//---------------------------------CREACION DEL ALBUM-------------------------
    album = new PanelAlbum(this);

//---------------------------------CREACION DE LA TIENDA-------------------------
    tienda = new PanelTienda(album, this, sistema);

    recompensas = new PanelRecompensas(this, sistema);
    PanelLeaderboard leaderboard = new PanelLeaderboard("Milo");
    PanelDatos datos = new PanelDatos(this);
    
    eventos = new PanelEventos(this, sistema);

//---------------------------------GUARDAMOS EL MENU-------------------------
    JPanel menu = jPantalla;

//---------------------------------AGREGACION DE LOS PANELES-------------------------
    contenedor.add(menu, "MENU");
    contenedor.add(tienda, "TIENDA");
    contenedor.add(album, "ALBUM");
    contenedor.add(eventos, "EVENTOS");
    contenedor.add(recompensas, "RECOMPENSAS");
    contenedor.add(leaderboard, "LEADERBOARD");
    contenedor.add(datos, "DATOS");
    setContentPane(contenedor);
    revalidate();
    repaint();

//--------------------------------GUARDAMOS ALBUM AL CERRAR-------------------------
    addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent e) {
            GestorArchivos.guardarAlbum(album.getMatriz());
        }
    });
}
//---------------------------------METODO PARA CAMBIAR DE PANELES-------------------------
    public void mostrar(String nombre) {
        layout.show(contenedor, nombre);
}
    
//---------------------------------ACTUALIZA LAS RECOMPENSAS-------------------------
    public void actualizarRecompensas() {
    if (recompensas != null) {
        recompensas.actualizar();
    }
}
    
//---------------------------------CVERIFICA LAS ACCIONES DE XP-------------------------
public void sumarXP(int xp) {
    sistema.sumarXP(xp);
    actualizarRecompensas();
}
