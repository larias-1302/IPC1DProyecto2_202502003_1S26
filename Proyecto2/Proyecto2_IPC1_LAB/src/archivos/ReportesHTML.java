package archivos;
import modelos.*;
import estructuras.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Desktop;

public class ReportesHTML {
//-------------------------------------------GENERAR NOMBRE------------------------------------------------
    private static String generarNombre(String tipo) {
        String fecha = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
        return fecha + "_" + tipo + ".html";
    }

//-------------------------------------------ABRIR ARCHIVO-------------------------------------------------
    private static void abrirArchivo(File archivo) {
        try {
            Desktop.getDesktop().browse(archivo.toURI());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------GENERAR NOMBRE------------------------------------------------
    private static String estilo() {
        return "<style>" + "body{font-family:Arial;background:#1e1e2f;color:white;text-align:center;}" + "table{border-collapse:collapse;margin:auto;width:80%;}" + 
                "th,td{border:1px solid #555;padding:8px;}" + "th{background:#333;}" + ".vacio{background:gray;}" + ".legendaria{background:gold;color:black;}" + "</style>";
    }

//-------------------------------------------GENERAR INVENTARIO------------------------------------------------
    public static void generarInventario(ListaSimple catalogo) {
        String nombre = generarNombre("Inventario");

        try (PrintWriter pw = new PrintWriter(nombre)) {
            pw.println("<html><head>" + estilo() + "</head><body>");
            pw.println("<h1>Inventario de Tienda</h1>");
            pw.println("<table>");
            pw.println("<tr><th>Nombre</th><th>Precio</th><th>Stock</th><th>Plataforma</th></tr>");
            Nodo temp = catalogo.getCabeza();

            while (temp != null) {
                Juego j = (Juego) temp.dato;
                pw.println("<tr>");
                pw.println("<td>" + j.nombre + "</td>");
                pw.println("<td>" + j.precio + "</td>");
                pw.println("<td>" + j.stock + "</td>");
                pw.println("<td>" + j.plataforma + "</td>");
                pw.println("</tr>");
                temp = temp.siguiente;
            }

            pw.println("</table></body></html>");
            abrirArchivo(new File(nombre));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------GENERAR VENTAS------------------------------------------------
    public static void generarVentas(ListaSimple historial) {
        String nombre = generarNombre("Ventas");

        try (PrintWriter pw = new PrintWriter(nombre)) {
            pw.println("<html><head>" + estilo() + "</head><body>");
            pw.println("<h1>Reporte de Ventas</h1>");
            pw.println("<table>");
            pw.println("<tr><th>Juego</th><th>Cantidad</th><th>Total</th></tr>");
            Nodo temp = historial.getCabeza();

            while (temp != null) {
                Compra c = (Compra) temp.dato;
                pw.println("<tr>");
                pw.println("<td>" + c.nombreJuego + "</td>");
                pw.println("<td>" + c.cantidad + "</td>");
                pw.println("<td>" + c.total + "</td>");
                pw.println("</tr>");
                temp = temp.siguiente;
            }
            
            pw.println("</table></body></html>");
            abrirArchivo(new File(nombre));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------GENERAR ALBUM------------------------------------------------
    public static void generarAlbum(MatrizCartas matriz) {
        String nombre = generarNombre("Album");

        try (PrintWriter pw = new PrintWriter(nombre)) {
            pw.println("<html><head>" + estilo() + "</head><body>");
            pw.println("<h1>Álbum de Cartas</h1>");
            pw.println("<table>");
            NodoMatriz fila = matriz.getInicio();

            while (fila != null) {
                pw.println("<tr>");
                NodoMatriz actual = fila;

                while (actual != null) {
                    if (actual.dato == null) {
                        pw.println("<td class='vacio'>Vacía</td>");
                    }else {
                        Carta c = actual.dato;
                        String clase = c.rareza.equalsIgnoreCase("Legendaria") ? "legendaria" : "";
                        pw.println("<td class='" + clase + "'>" + c.nombre + "</td>");
                    }
                    actual = actual.derecha;
                }

                pw.println("</tr>");
                fila = fila.abajo;
            }

            pw.println("</table></body></html>");
            abrirArchivo(new File(nombre));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------GENERAR TORNEOS------------------------------------------------
    public static void generarTorneos(ListaSimple torneos) {
        String nombre = generarNombre("Torneos");

        try (PrintWriter pw = new PrintWriter(nombre)) {
            pw.println("<html><head>" + estilo() + "</head><body>");
            pw.println("<h1>Reporte de Torneos</h1>");
            pw.println("<table>");
            pw.println("<tr><th>Nombre</th><th>Juego</th><th>Tickets</th></tr>");
            Nodo temp = torneos.getCabeza();

            while (temp != null) {
                Torneo t = (Torneo) temp.dato;
                pw.println("<tr>");
                pw.println("<td>" + t.nombre + "</td>");
                pw.println("<td>" + t.juego + "</td>");
                pw.println("<td>" + t.ticketsDisponibles + "</td>");
                pw.println("</tr>");
                temp = temp.siguiente;
            }

            pw.println("</table></body></html>");
            abrirArchivo(new File(nombre));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}