/*
 * Este Main fue generado por Codex, a pedido del estudiante,
 * para probar las implementaciones del ejercicio y mostrar sus resultados.
 */
package ucu.edu.aed.ejercicio12;

import ucu.edu.aed.structures.linear.ListaArray;
import ucu.edu.aed.structures.linear.ListaSimple;

public class Main {
    public static void main(String[] args) {
        Grimorio grimorio = new Grimorio();
        ListaArray<Hechizo> hechizos = new ListaArray<>();

        hechizos.agregar(new Hechizo(42, "Fireball"));
        hechizos.agregar(new Hechizo(17, "Ice Lance"));
        hechizos.agregar(new Hechizo(58, "Thunder"));
        hechizos.agregar(new Hechizo(9, "Invisibility"));
        hechizos.agregar(new Hechizo(31, "Levitate"));
        hechizos.agregar(new Hechizo(73, "Summon"));
        hechizos.agregar(new Hechizo(25, "Heal"));
        hechizos.agregar(new Hechizo(50, "Teleport"));
        hechizos.agregar(new Hechizo(65, "Shield"));
        hechizos.agregar(new Hechizo(88, "Curse"));

        grimorio.insertar(hechizos);

        System.out.println("Hechizos insertados: " + hechizos.tamaño());
        System.out.println("\nHechizos prohibidos (ID impar), en inorden:");
        ListaSimple<Hechizo> prohibidos = grimorio.getHechizosProhibidos();
        for (Hechizo hechizo : prohibidos) {
            System.out.println(hechizo.getId() + " - " + hechizo.getNombre());
        }

        System.out.println("\nCántico secreto de Aldric:");
        System.out.println(grimorio.generarCantico(grimorio));
    }
}
