package modelos;

public class Carta {
    public String codigo;
    public String nombre;
    public String tipo;
    public String rareza;
    public int ataque;
    public int defensa;
    public int vida;

    public Carta(String codigo, String nombre, String tipo, String rareza,int ataque, int defensa, int vida) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = rareza;
        this.ataque = ataque;
        this.defensa = defensa;
        this.vida = vida;
    }
}