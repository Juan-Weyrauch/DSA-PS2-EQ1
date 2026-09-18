package ucu.edu.aed.ejercicio13;


public class Nave implements Comparable<Nave> {
    Integer id;
    ClaseNave clase;
    int cantidadCombustible;

    public Nave(int id, ClaseNave clase, int cantidadCombustible) {
        if (id <= 0) { throw new IllegalArgumentException("Nave: Id cannot be <= 0"); }
        if (clase == null) { throw new IllegalArgumentException("Nave: Clase cannot be null"); }

        this.id = id;
        this.clase = clase;
        this.cantidadCombustible = cantidadCombustible;
    }

    public Integer getId() { return this.id; }
    public ClaseNave getClase() { return this.clase; }
    public int getCombustible() { return this.cantidadCombustible; }

    @Override
    public int compareTo(Nave nave) {
        return this.getId().compareTo(nave.getId());
    }
}
