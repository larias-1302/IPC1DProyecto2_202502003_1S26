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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPantalla = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnTienda = new javax.swing.JButton();
        jbtnAlbum = new javax.swing.JButton();
        jbtnEventos = new javax.swing.JButton();
        jbtnGamificacion = new javax.swing.JButton();
        jbtnReportes = new javax.swing.JButton();
        jbtnDatos = new javax.swing.JButton();
        jbtnSalir = new javax.swing.JButton();
        jbtnClasificacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPantalla.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GameZone Pro");

        jbtnTienda.setBackground(new java.awt.Color(255, 204, 255));
        jbtnTienda.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnTienda.setText("Tienda");
        jbtnTienda.addActionListener(this::jbtnTiendaActionPerformed);

        jbtnAlbum.setBackground(new java.awt.Color(255, 204, 255));
        jbtnAlbum.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnAlbum.setText("Album");
        jbtnAlbum.addActionListener(this::jbtnAlbumActionPerformed);

        jbtnEventos.setBackground(new java.awt.Color(255, 204, 255));
        jbtnEventos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnEventos.setText("Eventos");
        jbtnEventos.addActionListener(this::jbtnEventosActionPerformed);

        jbtnGamificacion.setBackground(new java.awt.Color(255, 204, 255));
        jbtnGamificacion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnGamificacion.setText("Logros");
        jbtnGamificacion.addActionListener(this::jbtnGamificacionActionPerformed);

        jbtnReportes.setBackground(new java.awt.Color(255, 204, 255));
        jbtnReportes.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnReportes.setText("Reportes");
        jbtnReportes.addActionListener(this::jbtnReportesActionPerformed);

        jbtnDatos.setBackground(new java.awt.Color(255, 204, 255));
        jbtnDatos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnDatos.setText("Datos del Estudiante");
        jbtnDatos.addActionListener(this::jbtnDatosActionPerformed);

        jbtnSalir.setBackground(new java.awt.Color(255, 204, 255));
        jbtnSalir.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnSalir.setText("Salir");
        jbtnSalir.addActionListener(this::jbtnSalirActionPerformed);

        jbtnClasificacion.setBackground(new java.awt.Color(255, 204, 255));
        jbtnClasificacion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jbtnClasificacion.setText("Clasificacion");
        jbtnClasificacion.addActionListener(this::jbtnClasificacionActionPerformed);

        javax.swing.GroupLayout jPantallaLayout = new javax.swing.GroupLayout(jPantalla);
        jPantalla.setLayout(jPantallaLayout);
        jPantallaLayout.setHorizontalGroup(
            jPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPantallaLayout.createSequentialGroup()
                .addGroup(jPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPantallaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPantallaLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtnDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnGamificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnEventos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnAlbum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnTienda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPantallaLayout.setVerticalGroup(
            jPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTienda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnEventos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnGamificacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTiendaActionPerformed
        mostrar("TIENDA");
    }//GEN-LAST:event_jbtnTiendaActionPerformed

    private void jbtnAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAlbumActionPerformed
        mostrar("ALBUM");
    }//GEN-LAST:event_jbtnAlbumActionPerformed

    private void jbtnEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEventosActionPerformed
        mostrar("EVENTOS");
    }//GEN-LAST:event_jbtnEventosActionPerformed

    private void jbtnGamificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGamificacionActionPerformed
        mostrar("RECOMPENSAS");
    }//GEN-LAST:event_jbtnGamificacionActionPerformed

    private void jbtnClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClasificacionActionPerformed
        mostrar("LEADERBOARD");
    }//GEN-LAST:event_jbtnClasificacionActionPerformed

    private void jbtnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReportesActionPerformed
        String[] opciones = {"Inventario","Ventas","Álbum","Torneos"};
        int opcion = JOptionPane.showOptionDialog(this,"Seleccione el reporte","Reportes",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opciones,opciones[0]);

        switch (opcion){
            case 0:
                ReportesHTML.generarInventario(tienda.getCatalogo());
                break;
            case 1:
                ReportesHTML.generarVentas(tienda.getHistorial());
                break;
            case 2:
                ReportesHTML.generarAlbum(album.getMatriz());
                break;
            case 3:
                ReportesHTML.generarTorneos(eventos.getTorneos());
                break;
        }
    }//GEN-LAST:event_jbtnReportesActionPerformed

    private void jbtnDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDatosActionPerformed
        mostrar("DATOS");
    }//GEN-LAST:event_jbtnDatosActionPerformed

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnSalirActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new GameZone().setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPantalla;
    private javax.swing.JButton jbtnAlbum;
    private javax.swing.JButton jbtnClasificacion;
    private javax.swing.JButton jbtnDatos;
    private javax.swing.JButton jbtnEventos;
    private javax.swing.JButton jbtnGamificacion;
    private javax.swing.JButton jbtnReportes;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JButton jbtnTienda;
    // End of variables declaration//GEN-END:variables
}