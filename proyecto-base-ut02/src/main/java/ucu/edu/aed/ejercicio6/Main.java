/*
 * Este Main fue generado por Codex, a pedido del estudiante,
 * para probar las funcionalidades del ejercicio 6 y mostrar sus resultados.
 */
package ucu.edu.aed.ejercicio6;

import ucu.edu.aed.structures.element.Nodo;
import ucu.edu.aed.structures.hierarchical.ArbolBinarioBusqueda;
import ucu.edu.aed.tda.element.TDAElemento;
import ucu.edu.aed.tda.linear.TDALista;

public class Main {
    public static void main(String[] args) {
        ArbolBinarioBusqueda<Integer> vacio = new ArbolBinarioBusqueda<>();
        System.out.println("Árbol vacío:");
        comprobar("altura", 0, vacio.altura());
        comprobar("tamaño", 0, vacio.tamaño());
        comprobar("hojas", 0, vacio.hojas());
        comprobar("internos", 0, vacio.internos());
        comprobarLista("completos", vacio.completos());
        comprobarLista("nivel 0", vacio.enNivel(0));

        Nodo<Integer> hoja = new Nodo<>(10);
        ArbolBinarioBusqueda<Integer> unico = new ArbolBinarioBusqueda<>(hoja);
        System.out.println("\nUna raíz sola:");
        comprobar("altura", 1, unico.altura());
        comprobar("tamaño", 1, unico.tamaño());
        comprobar("hojas", 1, unico.hojas());
        comprobar("internos", 0, unico.internos());
        comprobarLista("completos", unico.completos());
        comprobarLista("nivel 0", unico.enNivel(0), 10); // el dato es 10 bro (this was me teach) (pa que vean que si leo lo que escribe el pana)

        //       10
        //      /  \
        //     5    20
        //      \
        //       7
        Nodo<Integer> cinco = new Nodo<>(5, null, new Nodo<>(7));
        Nodo<Integer> raiz = new Nodo<>(10, cinco, new Nodo<>(20));
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>(raiz);
        System.out.println("\nÁrbol con cuatro nodos:");
        comprobar("altura", 3, arbol.altura());
        comprobar("tamaño", 4, arbol.tamaño());
        comprobar("hojas", 2, arbol.hojas());
        comprobar("internos", 2, arbol.internos());
        comprobarLista("completos", arbol.completos(), 10);
        comprobarLista("nivel 0", arbol.enNivel(0), 10);
        comprobarLista("nivel 1", arbol.enNivel(1), 5, 20);
        comprobarLista("nivel 2", arbol.enNivel(2), 7);
        comprobarLista("nivel inexistente", arbol.enNivel(3));
        if (arbol.completos().obtener(0) != raiz) {
            throw new AssertionError("La lista debe devolver los nodos originales");
        }

        System.out.println("\nMétodos directamente sobre el nodo 5:");
        comprobar("altura", 2, cinco.altura());
        comprobar("tamaño", 2, cinco.tamaño());
        comprobar("hojas", 1, cinco.hojas());
        comprobar("internos", 1, cinco.internos());
        comprobarLista("completos", cinco.completos());
        comprobarLista("nivel relativo 0", cinco.enNivel(0), 5);
        comprobarLista("nivel relativo 1", cinco.enNivel(1), 7);

        // Un nodo no completo también puede tener descendientes completos.
        Nodo<Integer> completo = new Nodo<>(5, new Nodo<>(3), new Nodo<>(7));
        Nodo<Integer> padre = new Nodo<>(10, completo, null);
        comprobarLista("completo bajo un padre con un solo hijo", padre.completos(), 5);

        comprobarNivelNegativo("árbol vacío", () -> vacio.enNivel(-1));
        comprobarNivelNegativo("árbol con nodos", () -> arbol.enNivel(-1));
        comprobarNivelNegativo("nodo", () -> cinco.enNivel(-1));
        System.out.println("\nTodas las comprobaciones pasaron.");
    }

    private static void comprobar(String nombre, int esperado, int obtenido) {
        System.out.println(nombre + ": esperado " + esperado + ", obtenido " + obtenido);
        if (esperado != obtenido) {
            throw new AssertionError(nombre + ": resultado incorrecto");
        }
    }

    private static void comprobarLista(String nombre,
            TDALista<TDAElemento<Integer>> nodos, int... esperados) {
        if (nodos.tamaño() != esperados.length) {
            throw new AssertionError(nombre + ": cantidad incorrecta");
        }
        String resultado = "[";
        for (int i = 0; i < nodos.tamaño(); i++) {
            int dato = nodos.obtener(i).getDato();
            if (dato != esperados[i]) {
                throw new AssertionError(nombre + ": nodo incorrecto en posición " + i);
            }
            if (i > 0) {
                resultado += ", ";
            }
            resultado += dato;
        }
        System.out.println(nombre + ": " + resultado + "] (correcto)");
    }

    private static void comprobarNivelNegativo(String nombre, Runnable consulta) {
        try {
            consulta.run();
        } catch (IllegalArgumentException esperado) {
            System.out.println("Nivel negativo en " + nombre + ": rechazado correctamente");
            return;
        }
        throw new AssertionError("Se aceptó un nivel negativo en " + nombre);
    }
}
