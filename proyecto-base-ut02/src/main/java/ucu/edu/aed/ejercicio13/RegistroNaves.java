package ucu.edu.aed.ejercicio13;

import ucu.edu.aed.structures.hierarchical.ArbolAVL;
import ucu.edu.aed.structures.linear.ListaSimple;

public class RegistroNaves {
    ArbolAVL<Nave> flota;

    public RegistroNaves() {
        this.flota = new ArbolAVL<>();
    }

    public RegistroNaves(Nave rootNave) {
        this();
        this.flota.insertar(rootNave);
    }

    public void insertarNaves(ListaSimple<Nave> listaNaves) {
        for (Nave n : listaNaves) {
            this.flota.insertar(n);
        }
    }

    public ListaSimple<Integer> devolverNaveEspecifica(ClaseNave clase) {
        ListaSimple<Integer> naves = new ListaSimple<>();

        this.flota.inOrder(nave -> {
            if (nave.getClase() == clase) {
                naves.agregar(nave.getId());
            }
        });

        return naves;
    }

    public double combustiblePromedio() {
        ListaSimple<Nave> exploradoras = new ListaSimple<>();

        this.flota.inOrder(nave -> {
            if (nave.getClase() == ClaseNave.EXPLORADOR) {
                exploradoras.agregar(nave);
            }
        });

        if (exploradoras.esVacio()) {
            return 0.0;
        }

        double total = 0;
        for (Nave nave : exploradoras) {
            total += nave.getCombustible();
        }

        return total / exploradoras.tamaño();
    }

}
