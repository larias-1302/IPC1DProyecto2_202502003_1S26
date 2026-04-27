package sistema;

import estructuras.*;
import modelos.*;
import javax.swing.*;

public class SistemaRecompensas {

    public int xp = 0;

    private int compras = 0;
    private int cartas = 0;
    private int torneos = 0;
    private double dineroGastado = 0;
    
    private ListaSimple logros;

    public SistemaRecompensas() {
        logros = new ListaSimple();
        inicializarLogros();
    }

    private void inicializarLogros() {

        logros.insertar(new Logro("Primera Compra", "Compra tu primer juego"));
        logros.insertar(new Logro("Coleccionista Novato", "10 cartas"));
        logros.insertar(new Logro("Coleccionista Experto", "Fila completa"));
        logros.insertar(new Logro("Taquillero", "3 torneos"));
        logros.insertar(new Logro("Alta Rareza", "Carta legendaria"));
        logros.insertar(new Logro("Gamer Dedicado", "1000 XP"));
        logros.insertar(new Logro("Leyenda Viviente", "Nivel 5"));
        logros.insertar(new Logro("Gran Gastador", "Q2000 gastados"));
    }

    // ================= XP =================

    public void sumarXP(int cantidad) {
        xp += cantidad;
        verificarLogros();
    }

    // ================= TRACKERS =================

    public void registrarCompra(double monto) {
    compras++;
    dineroGastado += monto;
    sumarXP(50); // 🔥 IMPORTANTE
}

    public void registrarCarta() {
        cartas++;
        verificarLogros();
    }

    public void registrarTorneo() {
    torneos++;
    sumarXP(150); // 🔥 IMPORTANTE
}

    public void registrarCartaLegendaria() {
        desbloquearPorNombre("Alta Rareza");
    }

    // ================= NIVEL =================

    public int getNivel() {
        if (xp < 500) return 1;
        if (xp < 1500) return 2;
        if (xp < 3500) return 3;
        if (xp < 7000) return 4;
        return 5;
    }

    public String getRango() {
        switch (getNivel()) {
            case 1: return "Aprendiz";
            case 2: return "Jugador";
            case 3: return "Veterano";
            case 4: return "Maestro";
            default: return "Leyenda";
        }
    }

    public int getXPProgreso() {
        if (xp < 500) return xp;
        if (xp < 1500) return xp - 500;
        if (xp < 3500) return xp - 1500;
        if (xp < 7000) return xp - 3500;
        return xp - 7000;
    }

    public int getXPMax() {
        if (xp < 500) return 500;
        if (xp < 1500) return 1000;
        if (xp < 3500) return 2000;
        if (xp < 7000) return 3500;
        return 5000;
    }

    // ================= LOGROS =================

private void verificarLogros() {

    Nodo temp = logros.getCabeza();

    while (temp != null) {

        Logro l = (Logro) temp.dato;

        if (!l.desbloqueado) {

            switch (l.nombre) {

                case "Primera Compra":
                    if (compras >= 1) desbloquear(l);
                    break;

                case "Coleccionista Novato":
                    if (cartas >= 10) desbloquear(l);
                    break;

                case "Coleccionista Experto":
                    if (cartas >= 5) desbloquear(l); // fila simulada
                    break;

                case "Taquillero":
                    if (torneos >= 3) desbloquear(l);
                    break;

                case "Alta Rareza":
                    // se desbloquea manualmente, no aquí
                    break;

                case "Gamer Dedicado":
                    if (xp >= 1000) desbloquear(l);
                    break;

                case "Leyenda Viviente":
                    if (getNivel() == 5) desbloquear(l);
                    break;

                case "Gran Gastador":
                    if (dineroGastado >= 2000) desbloquear(l);
                    break;
            }
        }

        temp = temp.siguiente;
    }
}

    private void desbloquear(Logro l) {
        l.desbloqueado = true;
        JOptionPane.showMessageDialog(null,
                "🏆 Logro desbloqueado: " + l.nombre);
    }

    private void desbloquearPorNombre(String nombre) {

        Nodo temp = logros.getCabeza();

        while (temp != null) {
            Logro l = (Logro) temp.dato;

            if (l.nombre.equals(nombre) && !l.desbloqueado) {
                desbloquear(l);
                return;
            }

            temp = temp.siguiente;
        }
    }

    public ListaSimple getLogros() {
        return logros;
    }
}