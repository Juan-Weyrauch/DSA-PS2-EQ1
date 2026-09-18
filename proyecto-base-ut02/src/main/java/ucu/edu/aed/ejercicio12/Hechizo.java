package ucu.edu.aed.ejercicio12;

public class Hechizo implements Comparable<Hechizo> {
    Integer id;
    String nombre;

    public Hechizo (Integer id, String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException(nombre + 
                " in Hechizo constructor is blank.");
        }
        if (id == null) {
            throw new IllegalArgumentException(id + 
                " in Hechizo constructor is blank.");
        }
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() { return this.id; }
    public String getNombre() { return this.nombre; }

    public int compareTo(Hechizo hechizo) {
        return this.getId().compareTo(hechizo.getId());
    }
    
}
