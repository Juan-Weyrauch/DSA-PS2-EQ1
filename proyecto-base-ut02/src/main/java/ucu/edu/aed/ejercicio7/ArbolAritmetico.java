package ucu.edu.aed.ejercicio7;

import ucu.edu.aed.structures.element.Nodo;
import ucu.edu.aed.structures.hierarchical.ArbolBinarioBusqueda;
import ucu.edu.aed.tda.element.TDAElemento;

public class ArbolAritmetico<T> extends ArbolBinarioBusqueda<T> {
    private TDAElemento<String> root;
    private String[] tokens;
    private int position;

    public ArbolAritmetico(TDAElemento<String> raiz) {
        this.root = raiz;
    }

    private double evaluar(TDAElemento<String> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("Nodo en el metodo privado evaluar es nulo. Falta un operando.");
        }

        if (nodo.esHoja()) {
            return Double.parseDouble(nodo.getDato());
        }

        double izquierda = evaluar(nodo.getHijoIzquierdo());
        double derecha = evaluar(nodo.getHijoDerecho());

        switch (nodo.getDato()) {
            case "+":
                return izquierda + derecha;
            case "-":
                return izquierda - derecha;
            case "*":
                return izquierda * derecha;
            case "/":
                if (derecha == 0) {
                    throw new IllegalArgumentException("Division entre 0");
                }
                return izquierda / derecha;
            default:
                throw new IllegalArgumentException("Operador desconocido.");
        }
    }

    public double evaluar() {
        if (this.root == null) {
            throw new IllegalArgumentException("La root del arbol es nula");
        }
        return evaluar(this.root);
    }

    public void sustituirVariable(String nombre, double valor) {
        sustituir(this.root, nombre, valor);
    }

    private void sustituir(TDAElemento<String> nodo, String nombre, double valor) {
        if (nodo == null) {
            throw new IllegalArgumentException("Nodo en el metodo sustituir no puede ser nulo.");
        }

        if (nodo.esHoja()) {
            if (nodo.getDato().equals(nombre)) {
                nodo.setDato(Double.toString(valor));
            }
            return;
        }

        sustituir(nodo.getHijoIzquierdo(), nombre, valor);
        sustituir(nodo.getHijoDerecho(), nombre, valor);
    }

    public void construirDesdePrefija(String texto) {
        if (texto == null || texto.isBlank()) { throw new IllegalArgumentException("texto in construirDesdePrefija no puede ser nulo."); }
        this.tokens = texto.trim().split("\\s+");
        this.position = 0;

        TDAElemento<String> newRoot = leerSubarbol();

        if (this.position < tokens.length) {
            throw new IndexOutOfBoundsException("sobran piezas.");
        }

        this.root = newRoot;
    }

    private TDAElemento<String> leerSubarbol() {
        // Si position llegó a tokens.length, falta un operando: lanzar error.
        if (this.position == this.tokens.length) {throw new IndexOutOfBoundsException("position exceeded tokens length."); }

        String dato = this.tokens[position];
        this.position++;

        Nodo<String> nodo = new Nodo<>(dato);

        boolean esOperador = dato.equals("+")
                || dato.equals("-")
                || dato.equals("*")
                || dato.equals("/");

        if (esOperador) {
            nodo.setHijoIzquierdo(leerSubarbol());
            nodo.setHijoDerecho(leerSubarbol());
        }

        return nodo;
    }

}
