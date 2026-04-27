package gui;
import estructuras.*;
import modelos.*;
import archivos.*;
import javax.swing.*;
import java.awt.*;
import gui.PanelAlbum;
import proyecto2_ipc1_lab.GameZone;
import sistema.SistemaRecompensas;

public class PanelTienda extends JPanel {
    private ListaSimple catalogo;
    private ListaSimple carrito;
    private ListaSimple historial;
    private JPanel panelCatalogo;
    private JPanel panelCarrito;
    private JPanel panelItems;
    private JTextArea areaHistorial;
    private JTextField txtBuscar;
    private JComboBox<String> cbGenero;
    private JComboBox<String> cbPlataforma;
    private PanelAlbum album;
    private GameZone ventana;
    private SistemaRecompensas sistema;

    public PanelTienda(PanelAlbum album, GameZone ventana, SistemaRecompensas sistema){
    this.album = album;
    this.ventana = ventana;
    this.sistema = sistema;
    setLayout(new BorderLayout());

//----------------------------------------CARGAR LOS DATOS-------------------------------------------
    catalogo = GestorArchivos.cargarCatalogo(
        "C:\\Users\\laria\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto2_IPC1_LAB\\src\\archivos\\catalogo.txt"
    );

    carrito = new ListaSimple();
    historial = new ListaSimple();

//----------------------------------------PANEL DE FILTROS-------------------------------------------
    JPanel top = new JPanel(new BorderLayout());

//----------------------------------------CREACION DEL BOTON VOLVER-------------------------------------------
    JButton btnMenu = new JButton("Volver al Menú");
    btnMenu.addActionListener(e -> ventana.mostrar("MENU"));
    JPanel panelFiltros = new JPanel();

    txtBuscar = new JTextField(15);
    cbGenero = new JComboBox<>(new String[]{
        "Todos", "Accion", "RPG", "Estrategia", "Deportes", "Terror", "Aventura"
    });

    cbPlataforma = new JComboBox<>(new String[]{
        "Todas", "PC", "PlayStation", "Xbox", "Nintendo Switch"
    });

    panelFiltros.add(new JLabel("Buscar:"));
    panelFiltros.add(txtBuscar);

    panelFiltros.add(new JLabel("Genero:"));
    panelFiltros.add(cbGenero);

    panelFiltros.add(new JLabel("Plataforma:"));
    panelFiltros.add(cbPlataforma);

    top.add(btnMenu, BorderLayout.NORTH);
    top.add(panelFiltros, BorderLayout.SOUTH);
    add(top, BorderLayout.NORTH);

//----------------------------------------PANEL DE JUEGOS-------------------------------------------
    panelCatalogo = new JPanel(new GridLayout(0, 3, 10, 10));
    JScrollPane scrollCatalogo = new JScrollPane(panelCatalogo);

//----------------------------------------PANEL DEL CARRITO-------------------------------------------
    panelCarrito = new JPanel(new BorderLayout());
    panelCarrito.setBorder(BorderFactory.createTitledBorder("Carrito"));

    panelItems = new JPanel();
    panelItems.setLayout(new BoxLayout(panelItems, BoxLayout.Y_AXIS));
    JScrollPane scrollItems = new JScrollPane(panelItems);

    panelCarrito.add(scrollItems, BorderLayout.CENTER);

    JButton comprar = new JButton("Comprar");
    comprar.addActionListener(e -> procesarCompra());
    panelCarrito.add(comprar, BorderLayout.NORTH);

//----------------------------------------AREA DEL HISTORIAL DE COMPRA-------------------------------------------
    areaHistorial = new JTextArea(8, 20);
    areaHistorial.setEditable(false);

    JScrollPane scrollHistorial = new JScrollPane(areaHistorial);
    scrollHistorial.setBorder(BorderFactory.createTitledBorder("Historial"));

    panelCarrito.add(scrollHistorial, BorderLayout.SOUTH);

    JSplitPane split = new JSplitPane(
        JSplitPane.HORIZONTAL_SPLIT,
        scrollCatalogo,
        panelCarrito
    );

    split.setDividerLocation(500);
    add(split, BorderLayout.CENTER);
    
//----------------------------------------FILTRAR-------------------------------------------
    txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent e) {
            filtrar();
        }
    });

    cbGenero.addActionListener(e -> filtrar());
    cbPlataforma.addActionListener(e -> filtrar());

    cargarCatalogoUI();
    actualizarCarritoUI();
}

//----------------------------------------INTERFAZ DEL CATALOGO-------------------------------------------
    private void cargarCatalogoUI() {
        panelCatalogo.removeAll();
        Nodo temp = catalogo.getCabeza();

        while (temp != null) {
            Juego j = (Juego) temp.dato;

            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            info.add(new JLabel("Nombre: " + j.nombre));
            info.add(new JLabel("Precio: Q" + j.precio));
            info.add(new JLabel("Género: " + j.genero));
            info.add(new JLabel("Plataforma: " + j.plataforma));
            info.add(new JLabel("Stock: " + j.stock));
            info.add(new JLabel(j.descripcion));

//----------------------------------------MOSTRAR LOSS DETALLES-------------------------------------------
            info.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    mostrarDetalle(j);
                }
            });

            JButton btn = new JButton("Agregar");
            btn.addActionListener(e -> agregarAlCarrito(j));

            card.add(info, BorderLayout.CENTER);
            card.add(btn, BorderLayout.SOUTH);
            panelCatalogo.add(card);
            temp = temp.siguiente;
        }
        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }

//----------------------------------------AGREGAR AL CARRITO-------------------------------------------
    private void agregarAlCarrito(Juego j) {
        Nodo temp = carrito.getCabeza();

        while (temp != null) {
            CarritoItem item = (CarritoItem) temp.dato;
            
            if (item.juego.codigo.equals(j.codigo)) {
                item.cantidad++;
                actualizarCarritoUI();
                return;
            }
            temp = temp.siguiente;
        }
        carrito.insertar(new CarritoItem(j, 1));
        actualizarCarritoUI();
    }

//----------------------------------------MUESTRA EL CARRITO-------------------------------------------
    private void actualizarCarritoUI() {
        panelItems.removeAll();
        Nodo temp = carrito.getCabeza();

        while (temp != null) {
            CarritoItem item = (CarritoItem) temp.dato;

            JPanel fila = new JPanel(new FlowLayout());
            JLabel nombre = new JLabel(item.juego.nombre + " x" + item.cantidad);
            
            JButton eliminar = new JButton("X");
            eliminar.addActionListener(e -> eliminarItem(item));
            fila.add(nombre);
            fila.add(eliminar);
            panelItems.add(fila);
            temp = temp.siguiente;
        }
        panelItems.revalidate();
        panelItems.repaint();
    }

//----------------------------------------QUITA DEL CARRITO-------------------------------------------
    private void eliminarItem(CarritoItem itemEliminar) {
        Nodo temp = carrito.getCabeza();
        Nodo anterior = null;

        while (temp != null) {
            if (temp.dato == itemEliminar) {
                if (anterior == null) {
                    carrito = new ListaSimple();
                }else {
                    anterior.siguiente = temp.siguiente;
                }
                break;
            }
            anterior = temp;
            temp = temp.siguiente;
        }
        actualizarCarritoUI();
    }

//----------------------------------------HACE LA COMPRA-------------------------------------------
    private void procesarCompra() {
    Nodo temp = carrito.getCabeza();

    // validar stock
    while (temp != null) {
        CarritoItem item = (CarritoItem) temp.dato;
        if (item.juego.stock < item.cantidad) {
            JOptionPane.showMessageDialog(this,"Stock insuficiente para " + item.juego.nombre);
            return;
        }
        temp = temp.siguiente;
    }
    
    // descontar stock
    temp = carrito.getCabeza();
    
    while (temp != null) {
        CarritoItem item = (CarritoItem) temp.dato;
        item.juego.stock -= item.cantidad;
        temp = temp.siguiente;
    }
    JOptionPane.showMessageDialog(this, "Compra realizada");

    // cambia el historial y manda la carta
    temp = carrito.getCabeza();

    while (temp != null) {
        CarritoItem item = (CarritoItem) temp.dato;
        double total = item.cantidad * item.juego.precio;
        Compra compra = new Compra(
                item.juego.nombre,
                item.cantidad,
                total
        );
        historial.insertarInicio(compra);
        GestorArchivos.guardarCompra(compra);
        sistema.registrarCompra(total);
        
//----------------------------------------CREA LA CARTA-------------------------------------------
        for (int i = 0; i < item.cantidad; i++) {
            Carta carta = new Carta("CARTA-" + System.currentTimeMillis(),item.juego.nombre,"Normal","Común",50,50,100);
            album.agregarCartaDesdeTienda(carta);
            ventana.sumarXP(150);
        }
        temp = temp.siguiente;
    }
    carrito.limpiar();
    actualizarCarritoUI();
    actualizarHistorialUI();
    panelCatalogo.removeAll();
    cargarCatalogoUI();
}

//----------------------------------------MUESTRA EL HISTORIAL-------------------------------------------
    private void actualizarHistorialUI() {
        areaHistorial.setText("");
        Nodo temp = historial.getCabeza();

        while (temp != null) {
            Compra c = (Compra) temp.dato;
            java.util.Date fecha = new java.util.Date(c.timestamp);
            areaHistorial.append(c.nombreJuego + " x" + c.cantidad + " = Q" + c.total + " | " + fecha + "\n");
            temp = temp.siguiente;
        }
    }

//----------------------------------------MUESTRA LOS DETALLES-------------------------------------------
    private void mostrarDetalle(Juego j) {
        JDialog dialog = new JDialog();
        dialog.setTitle(j.nombre);
        dialog.setSize(300, 300);
        dialog.setLayout(new BorderLayout());
        
        JTextArea info = new JTextArea("Nombre: " + j.nombre + "\n" + "Genero: " + j.genero + "\n" + "Plataforma: " + j.plataforma + "\n" + "Precio: Q" + j.precio + "\n" + "Stock: " + j.stock + "\n\n" + j.descripcion);
        info.setEditable(false);

        JButton agregar = new JButton("Agregar al carrito");
        agregar.addActionListener(e -> {
            agregarAlCarrito(j);
            dialog.dispose();
        });

        dialog.add(new JScrollPane(info), BorderLayout.CENTER);
        dialog.add(agregar, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
//----------------------------------------FILTRA LOS JUEGOS-------------------------------------------
    private void filtrar() {
    panelCatalogo.removeAll();
    String texto = txtBuscar.getText().toLowerCase();
    String genero = cbGenero.getSelectedItem().toString();
    String plataforma = cbPlataforma.getSelectedItem().toString();
    Nodo temp = catalogo.getCabeza();

    while (temp != null) {
        Juego j = (Juego) temp.dato;
        boolean coincideTexto = j.nombre.toLowerCase().contains(texto) || j.codigo.toLowerCase().contains(texto);
        boolean coincideGenero = genero.equals("Todos") || j.genero.equalsIgnoreCase(genero);
        boolean coincidePlataforma = plataforma.equals("Todas") || j.plataforma.equalsIgnoreCase(plataforma);

        if (coincideTexto && coincideGenero && coincidePlataforma) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

            info.add(new JLabel("Nombre: " + j.nombre));
            info.add(new JLabel("Precio: Q" + j.precio));
            info.add(new JLabel("Género: " + j.genero));
            info.add(new JLabel("Plataforma: " + j.plataforma));
            info.add(new JLabel("Stock: " + j.stock));
            info.add(new JLabel(j.descripcion));

            info.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    mostrarDetalle(j);
                }
            });

            JButton btn = new JButton("Agregar");
            btn.addActionListener(e -> agregarAlCarrito(j));
            card.add(info, BorderLayout.CENTER);
            card.add(btn, BorderLayout.SOUTH);
            panelCatalogo.add(card);
        }
        temp = temp.siguiente;
    }
        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }
    
    public ListaSimple getCatalogo() {
        return catalogo;
    }

    public ListaSimple getHistorial() {
        return historial;
    }
}