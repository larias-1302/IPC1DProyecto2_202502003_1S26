```java
    private void jbtnTiendaActionPerformed(java.awt.event.ActionEvent evt) {                                           
        mostrar("TIENDA");
    }                                          

    private void jbtnAlbumActionPerformed(java.awt.event.ActionEvent evt) {                                          
        mostrar("ALBUM");
    }                                         

    private void jbtnEventosActionPerformed(java.awt.event.ActionEvent evt) {                                            
        mostrar("EVENTOS");
    }                                           

    private void jbtnGamificacionActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        mostrar("RECOMPENSAS");
    }                                                

    private void jbtnClasificacionActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        mostrar("LEADERBOARD");
    }                                                 

    private void jbtnReportesActionPerformed(java.awt.event.ActionEvent evt) {                                             
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
    }                                            

    private void jbtnDatosActionPerformed(java.awt.event.ActionEvent evt) {                                          
        mostrar("DATOS");
    }                                         

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {                                          
        System.exit(0);
    } 
