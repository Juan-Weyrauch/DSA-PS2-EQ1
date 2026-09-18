/*
 * Este Main fue generado por Codex, a pedido del estudiante,
 * para probar las implementaciones del ejercicio y mostrar sus resultados.
 */
package ucu.edu.aed.ejercicio13;

import ucu.edu.aed.structures.linear.ListaSimple;

public class Main {
    public static void main(String[] args) {
        RegistroNaves registro = new RegistroNaves();
        ListaSimple<Nave> naves = new ListaSimple<>();

        // Datos y orden de inserción del ejercicio 13.
        naves.agregar(new Nave(10, ClaseNave.EXPLORADOR, 0));
        naves.agregar(new Nave(20, ClaseNave.DESTRUCTOR, 90));
        naves.agregar(new Nave(30, ClaseNave.MEDICA, 100));
        naves.agregar(new Nave(40, ClaseNave.EXPLORADOR, 50));
        naves.agregar(new Nave(50, ClaseNave.CARGUERO, 20));
        naves.agregar(new Nave(60, ClaseNave.DESTRUCTOR, 28));
        naves.agregar(new Nave(70, ClaseNave.EXPLORADOR, 14));
        naves.agregar(new Nave(80, ClaseNave.MEDICA, 7));
        naves.agregar(new Nave(90, ClaseNave.CARGUERO, 23));
        naves.agregar(new Nave(100, ClaseNave.EXPLORADOR, 26));

        registro.insertarNaves(naves);

        System.out.println("Flota registrada en inorden (ID - clase - combustible):");
        registro.flota.inOrder(nave -> {
            System.out.println(nave.getId() + " - " + nave.getClase()
                    + " - " + nave.getCombustible());
        });

        System.out.println("\nCódigos de las naves exploradoras:");
        ListaSimple<Integer> exploradoras =
                registro.devolverNaveEspecifica(ClaseNave.EXPLORADOR);
        for (Integer codigo : exploradoras) {
            System.out.println(codigo);
        }

        System.out.println("\nCombustible promedio de las exploradoras: "
                + registro.combustiblePromedio());
    }
}
