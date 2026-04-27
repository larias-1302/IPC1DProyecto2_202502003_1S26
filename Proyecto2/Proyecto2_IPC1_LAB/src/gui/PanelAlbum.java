package gui;
import archivos.GestorArchivos;
import estructuras.*;
import modelos.*;
import javax.swing.*;
import java.awt.*;
import proyecto2_ipc1_lab.GameZone;
import sistema.SistemaRecompensas;

public class PanelAlbum extends JPanel {
    private MatrizCartas matriz;
    private NodoMatriz seleccion1 = null;
    private NodoMatriz seleccion2 = null;
    private JTextField txtBuscar;
    private JPanel panelGrid;
    private PanelAlbum album;
    private GameZone ventana;
    private JTextArea areaDetalle;
    private SistemaRecompensas sistema;
    
    public PanelAlbum(GameZone ventana) {
        this.ventana = ventana;
        this.sistema = sistema;
        matriz = new MatrizCartas(4, 6);
        GestorArchivos.cargarAlbum(matriz);
        setLayout(new BorderLayout());

//----------------CREACION DEL PANEL SUPERIOR-------------------
        JPanel top = new JPanel();
        txtBuscar = new JTextField(15);
        top.add(new JLabel("Buscar:"));
        top.add(txtBuscar);

//----------------CREACION DEL BOTON AGREGAR-------------------
        JButton btnAgregar = new JButton("Agregar Carta");
        btnAgregar.addActionListener(e -> agregarCartaAuto());
        top.add(btnAgregar);
        add(top, BorderLayout.NORTH);

//----------------CREACION DEL PANEL GRID-------------------
        panelGrid = new JPanel(new GridLayout(4, 6, 5, 5));
        add(new JScrollPane(panelGrid), BorderLayout.CENTER);
        areaDetalle = new JTextArea();
        areaDetalle.setEditable(false);
        add(new JScrollPane(areaDetalle), BorderLayout.EAST);

//----------------CREACION DEL BOTON VOLVER-------------------
        JButton btnMenu = new JButton("Volver al Menú");
        btnMenu.addActionListener(e -> ventana.mostrar("MENU"));
        add(btnMenu, BorderLayout.SOUTH);

//----------------CREACION DEL CAMPO BUSQUEDA-------------------
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                dibujar();
            }
        });

        dibujar();
    }

//----------------CREACION VISUAL DE LA MATRIZ-------------------
    private void dibujar() {
        panelGrid.removeAll();
        String filtro = txtBuscar.getText().toLowerCase();
        NodoMatriz fila = matriz.getInicio();

        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                NodoMatriz nodoFinal = actual;

                JPanel celda = new JPanel();
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celda.setLayout(new BoxLayout(celda, BoxLayout.Y_AXIS));

                if (actual.dato == null){
                    celda.setBackground(Color.LIGHT_GRAY);
                    celda.add(new JLabel("Vacía"));

                }else {
                    Carta c = actual.dato;
                    celda.add(new JLabel(c.nombre));
                    celda.add(new JLabel(c.tipo));
                    celda.add(new JLabel(c.rareza));
                    
                    //------------COLOCARLES COLOR--------------
                    if (c.tipo.equals("Fuego")) celda.setBackground(Color.PINK);
                    if (c.tipo.equals("Agua")) celda.setBackground(Color.CYAN);
                    if (c.tipo.equals("Planta")) celda.setBackground(Color.GREEN);

                    //------------RESALTAR LA BUSQUEDA--------------
                    if (c.nombre.toLowerCase().contains(filtro)
                            || c.tipo.toLowerCase().contains(filtro)
                            || c.rareza.toLowerCase().contains(filtro)) {

                        celda.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    }
                }

                celda.addMouseListener(new java.awt.event.MouseAdapter(){
                    public void mouseClicked(java.awt.event.MouseEvent e){
                        manejarClick(nodoFinal);
                    }
                });

                panelGrid.add(celda);
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
        panelGrid.revalidate();
        panelGrid.repaint();
    }

//---------------------------GUARDA LOS CLICKS---------------------------------
    private void manejarClick(NodoMatriz nodo) {
        if (nodo.dato != null) {
            Carta c = nodo.dato;
            areaDetalle.setText("Nombre: " + c.nombre + "\nTipo: " + c.tipo + "\nRareza: " + c.rareza + "\nATK: " + c.ataque + "\nDEF: " + c.defensa + "\nHP: " + c.vida);
        }

        if (seleccion1 == null) {
            seleccion1 = nodo;
        } else {
            seleccion2 = nodo;
            matriz.intercambiar(seleccion1, seleccion2);
            seleccion1 = null;
            seleccion2 = null;
            dibujar();
        }
        
    }

//------------------------------AGREGAR CARTA NUEVA----------------------------
    private void agregarCartaAuto() {
        NodoMatriz fila = matriz.getInicio();

        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                if (actual.dato == null) {
                    actual.dato = new Carta("CARTA-" + actual.fila + "-" + actual.columna,"Nueva Carta","Fuego","Común",50,50,100);
                    dibujar();
                    sistema.registrarCarta();
                    ventana.sumarXP(150);
                    return;                
                }
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
    }
    
//------------AGREGA VARTA AL COMPRAR--------------
    public void agregarCartaDesdeJuego(Juego j) {
        NodoMatriz fila = matriz.getInicio();

        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                if (actual.dato == null) {
                    //CONVIERTE EL JUEGO
                    Carta c = new Carta(j.codigo,j.nombre,convertirTipo(j.genero),"Común",(int) (j.precio * 2),(int) (j.precio * 1.5),100);
                    actual.dato = c;
                    dibujar();
                    return;
                }
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
    }
    
//------------CONVIERTE EL GENERO EN TIPO--------------
    private String convertirTipo(String genero) {
        if (genero.equalsIgnoreCase("Accion")) return "Fuego";
        if (genero.equalsIgnoreCase("Aventura")) return "Planta";
        if (genero.equalsIgnoreCase("Deportes")) return "Normal";
        return "Normal";
    }
    

    public void agregarCartaDesdeTienda(Carta c) {
        matriz.insertarPrimeroVacio(c);
        dibujar();
    }
    
    public MatrizCartas getMatriz() {
        return matriz;
    }
}