package ucu.edu.aed.ejercicio12;

import ucu.edu.aed.structures.hierarchical.ArbolBinarioBusqueda;
import ucu.edu.aed.structures.linear.ListaArray;
import ucu.edu.aed.ejercicio12.Hechizo;

public class Grimorio {
    ArbolBinarioBusqueda<Hechizo> grimorio;

    public Grimorio() {
        this.grimorio = new ArbolBinarioBusqueda<>();
    }

    public Grimorio(Hechizo hechizo) {
        this();
        grimorio.insertar(hechizo);        
    }

    public void insertar(ListaArray<Hechizo> hechizos) {
        for (int i = 0; i < hechizos.tamaño(); i++) {
            this.grimorio.insertar(hechizos.obtener(i));
        }
    }

    public static void main(String[] args) {
        Grimorio grimorio = new Grimorio();

        Hechizo hechizo1 = new Hechizo(42, "Fireball");
        Hechizo hechizo2 = new Hechizo(17, "Ice Lance");
        Hechizo hechizo3 = new Hechizo(58, "Thunder");
        Hechizo hechizo4 = new Hechizo(9, "Invisibility");
        Hechizo hechizo5 = new Hechizo(31, "Levitate");
        Hechizo hechizo6 = new Hechizo(73, "Summon");
        Hechizo hechizo7 = new Hechizo(25, "Heal");
        Hechizo hechizo8 = new Hechizo(50, "Teleport");
        Hechizo hechizo9 = new Hechizo(65, "Shield");
        Hechizo hechizo10 = new Hechizo(88, "Curse");

        ListaArray<Hechizo> listaHechizos = new ListaArray<>();

        listaHechizos.agregar(hechizo1);
        listaHechizos.agregar(hechizo2);
        listaHechizos.agregar(hechizo3);
        listaHechizos.agregar(hechizo4);
        listaHechizos.agregar(hechizo5);
        listaHechizos.agregar(hechizo6);
        listaHechizos.agregar(hechizo7);
        listaHechizos.agregar(hechizo8);
        listaHechizos.agregar(hechizo9);
        listaHechizos.agregar(hechizo10);

        grimorio.insertar(listaHechizos);
    }


}
