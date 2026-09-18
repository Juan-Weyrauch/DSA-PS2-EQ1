package ucu.edu.aed.ejercicio7;

import ucu.edu.aed.structures.element.Nodo;

public class Main {
    public static void main(String[] args) {
        // Cada prueba captura su error para permitir ejecutar las siguientes.
        probarEvaluacion("Una hoja: 5", new Nodo<>("5"), 5.0);

        // Expresión: (5 + 3) * 2
        //       *
        //      / \
        //     +   2
        //    / \
        //   5   3
        Nodo<String> suma = new Nodo<>("+", new Nodo<>("5"), new Nodo<>("3"));
        Nodo<String> producto = new Nodo<>("*", suma, new Nodo<>("2"));
        probarEvaluacion("(5 + 3) * 2", producto, 16.0);

        probarEvaluacion("8 - 3", new Nodo<>("-", new Nodo<>("8"), new Nodo<>("3")), 5.0);
        probarEvaluacion("7 / 2", new Nodo<>("/", new Nodo<>("7"), new Nodo<>("2")), 3.5);
        probarEvaluacion("0 / 2", new Nodo<>("/", new Nodo<>("0"), new Nodo<>("2")), 0.0);

        System.out.println("\nSustituir todas las apariciones de x en x + x por 4:");
        Nodo<String> variableIzquierda = new Nodo<>("x");
        Nodo<String> variableDerecha = new Nodo<>("x");
        ArbolAritmetico<String> conVariables = new ArbolAritmetico<>(
                new Nodo<>("+", variableIzquierda, variableDerecha));
        System.out.println("Esperado: ambas hojas con 4.0, resultado 8.0");
        try {
            conVariables.sustituirVariable("x", 4.0);
        } catch (IllegalArgumentException error) {
            System.out.println("Error al sustituir: " + error.getMessage());
        }
        System.out.println("Hojas actuales: " + variableIzquierda.getDato()
                + " y " + variableDerecha.getDato());
        try {
            System.out.println("Resultado obtenido: " + conVariables.evaluar());
        } catch (IllegalArgumentException error) {
            System.out.println("Error al evaluar: " + error.getMessage());
        }

        System.out.println("\nDivisión 5 / 0: se espera un error de división entre cero.");
        ArbolAritmetico<String> divisionPorCero = new ArbolAritmetico<>(
                new Nodo<>("/", new Nodo<>("5"), new Nodo<>("0")));
        try {
            System.out.println("Resultado obtenido: " + divisionPorCero.evaluar());
        } catch (IllegalArgumentException error) {
            System.out.println("Error obtenido: " + error.getMessage());
        }

        // PENDIENTE: descomentar cuando construirDesdePrefija esté implementado.
        ArbolAritmetico<String> desdePrefija = new ArbolAritmetico<>(null);
        desdePrefija.construirDesdePrefija("* + x 3 2");
        desdePrefija.sustituirVariable("x", 5.0);
        System.out.println("Prefija: esperado 16.0, obtenido " + desdePrefija.evaluar());
    }

    private static void probarEvaluacion(String expresion, Nodo<String> raiz, double esperado) {
        System.out.println("\nExpresión: " + expresion);
        System.out.println("Resultado esperado: " + esperado);
        ArbolAritmetico<String> arbol = new ArbolAritmetico<>(raiz);
        try {
            System.out.println("Resultado obtenido: " + arbol.evaluar());
        } catch (IllegalArgumentException error) {
            System.out.println("Error obtenido: " + error.getMessage());
        }
    }
}
