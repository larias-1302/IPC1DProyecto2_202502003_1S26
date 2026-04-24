```java
package archivos;
import modelos.Juego;
import estructuras.ListaSimple;
import estructuras.MatrizCartas;
import estructuras.NodoMatriz;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import modelos.Carta;
import modelos.Compra;
import modelos.Ticket;
import modelos.Torneo;
import modelos.Usuario;

public class GestorArchivos {
    public static ListaSimple cargarCatalogo(String ruta) {
        ListaSimple lista = new ListaSimple();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                Juego j = new Juego(p[0],p[1],p[2],Double.parseDouble(p[3]),p[4],Integer.parseInt(p[5]),p[6]);
                lista.insertar(j);
            }
        }catch (Exception e) {
            System.out.println("ERROR LEYENDO ARCHIVO:");
            e.printStackTrace();
        }
        return lista;
    }

//------------------------------------------------CARGAR TORNEOS-------------------------------------------------------
    public static ListaSimple cargarTorneos(String ruta) {
        ListaSimple lista = new ListaSimple();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                Torneo t = new Torneo(p[0], p[1], p[2],p[3], p[4],Double.parseDouble(p[5]),Integer.parseInt(p[6]));
                lista.insertar(t);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    return lista;
    }

//------------------------------------------------GUARDAR COMPRA-------------------------------------------------------
    public static void guardarCompra(Compra c){
        try (PrintWriter pw = new PrintWriter(new FileWriter("historial.txt", true))) {
            pw.println(c.nombreJuego + "|" + c.cantidad + "|" + c.total + "|" + c.timestamp);
        }catch (Exception e) {
            System.out.println("Error guardando compra");
            e.printStackTrace();
        }
        System.out.println("Guardando con timestamp: " + c.timestamp);
    }

//------------------------------------------------GUARDAR TICKET-------------------------------------------------------
    public static void guardarTicket(modelos.Ticket t) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter("src/archivos/tickets_vendidos.txt", true))) {
            pw.println(t.nombrePersona + "|" + t.nombreTorneo);
        }catch (Exception e) {
            System.out.println("Error guardando ticket");
            e.printStackTrace();
        }
    }

//------------------------------------------------GUARDAR ALBUM-------------------------------------------------------
    public static void guardarAlbum(MatrizCartas matriz) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("src/archivos/album.txt"))) {
            NodoMatriz fila = matriz.getInicio();

            while (fila != null) {
                NodoMatriz actual = fila;
                while (actual != null) {
                    if (actual.dato != null){
                        Carta c = actual.dato;
                        pw.println(actual.fila + "|" + actual.columna + "|" + c.codigo + "|" + c.nombre + "|" + c.tipo + "|" + c.rareza + "|" + c.ataque + "|" +c.defensa + "|" + c.vida);
                    }
                    actual = actual.derecha;
                }
                fila = fila.abajo;
            }
        }catch (Exception e) {
            System.out.println("Error guardando álbum");
            e.printStackTrace();
        }
    }

//------------------------------------------------CARGAR ALBUM-------------------------------------------------------
    public static void cargarAlbum(MatrizCartas matriz) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/archivos/album.txt"))){
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                int fila = Integer.parseInt(p[0]);
                int col = Integer.parseInt(p[1]);

                Carta c = new Carta(p[2],p[3],p[4],p[5],Integer.parseInt(p[6]),Integer.parseInt(p[7]),Integer.parseInt(p[8]));
                NodoMatriz nodo = matriz.getNodo(fila, col);

                if (nodo != null) {
                    nodo.dato = c;
                }
            }

        }catch (Exception e) {
            System.out.println("No se pudo cargar álbum (puede estar vacío)");
        }
    }
    
//------------------------------------------------CARGAR LEADBOARD-------------------------------------------------------
    public static Usuario[] cargarLeaderboard(String ruta) {
        Usuario[] usuarios = new Usuario[100];
        int count = 0;

        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(ruta));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                String nombre = p[0];
                int xp = Integer.parseInt(p[1]);
                usuarios[count++] = new Usuario(nombre, xp);
            }
            br.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Usuario[] resultado = new Usuario[count];

        for (int i = 0; i < count; i++) {
            resultado[i] = usuarios[i];
        }
        return resultado;
    }
}

