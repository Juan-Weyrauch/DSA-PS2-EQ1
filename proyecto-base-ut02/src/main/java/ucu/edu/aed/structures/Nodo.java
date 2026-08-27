package ucu.edu.aed.structures;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAElemento;

public class Nodo<T> implements TDAElemento<T> {
    private T dato;
    private TDAElemento<T> hijoIzquierdo;
    private TDAElemento<T> hijoDerecho;

    // permite crear un elemento con hijos nulos.
    // o sea, tambien podes poner (x, null, null). Pero facilita la creacion
    // de un elemento haciendo "new Elemento(x)".
    public Nodo(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException(
                    "Dato en 'constructor' de la clase Elemento es null");
        }
        this.dato = dato;
    }

    public Nodo(T dato, TDAElemento<T> hijoIzquierdo,
            TDAElemento<T> hijoDerecho) {
        this(dato);
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return this.hijoDerecho;
    }

    @Override
    public T getDato() {
        return this.dato;
    }

    @Override
    public void setDato(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException(
                    "Dato en 'setDato' de la clase Elemento es null");
        }
        this.dato = dato;
    }

    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda) {
        if (criterioBusqueda == null) {
            throw new IllegalArgumentException(
                    "criterioBusqueda en el metodo 'buscar' es nulo");
        }

        int criterio = criterioBusqueda.compareTo(this.dato);

        if (criterio == 0) {
            return this;
        } else if (criterio < 0) {
            if (this.getHijoIzquierdo() != null) {
                return this.getHijoIzquierdo().buscar(criterioBusqueda);
            }
        } else {
            if (this.getHijoDerecho() != null) {
                return this.getHijoDerecho().buscar(criterioBusqueda);
            }
        }

        return null;
    }

    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {
        if (criterioBusqueda == null) {
            throw new IllegalArgumentException(
                    "criterioBusqueda en el metodo 'eliminar' es nulo");
        }

        int criterio = criterioBusqueda.compareTo(this.dato);

        // Este caso tendría que ser manejado por la clase que contiene
        // la raíz del árbol.
        if (criterio == 0) {
            throw new IllegalStateException(
                    "La eliminacion de la raiz debe ser manejada por el arbol");
        }

        return eliminarRecursivo(this, criterioBusqueda);
    }

    private TDAElemento<T> eliminarRecursivo(TDAElemento<T> nodoActual,
            Comparable<T> criterioBusqueda) {

        int criterio = criterioBusqueda.compareTo(nodoActual.getDato());

        if (criterio < 0) {

            TDAElemento<T> hijo = nodoActual.getHijoIzquierdo();

            if (hijo == null) {
                return null;
            }

            // Encontramos el nodo que queremos eliminar
            if (criterioBusqueda.compareTo(hijo.getDato()) == 0) {
                return eliminarHijo(nodoActual, hijo, true);
            }

            // Todavía no lo encontramos: seguimos bajando
            return eliminarRecursivo(hijo, criterioBusqueda);

        } else {

            TDAElemento<T> hijo = nodoActual.getHijoDerecho();

            if (hijo == null) {
                return null;
            }

            // Encontramos el nodo que queremos eliminar
            if (criterioBusqueda.compareTo(hijo.getDato()) == 0) {
                return eliminarHijo(nodoActual, hijo, false);
            }

            // Todavía no lo encontramos: seguimos bajando
            return eliminarRecursivo(hijo, criterioBusqueda);
        }
    }

    private TDAElemento<T> eliminarHijo(
            TDAElemento<T> padre,
            TDAElemento<T> nodoEliminar,
            boolean esHijoIzquierdo) {

        TDAElemento<T> izquierdo = nodoEliminar.getHijoIzquierdo();
        TDAElemento<T> derecho = nodoEliminar.getHijoDerecho();

        TDAElemento<T> reemplazo;

        // CASO 1:
        // No tiene hijo izquierdo.
        // Esto incluye:
        // - hoja
        // - solamente hijo derecho
        if (izquierdo == null) {

            reemplazo = derecho;

            // CASO 2:
            // Tiene hijo izquierdo pero no derecho.
        } else if (derecho == null) {

            reemplazo = izquierdo;

            // CASO 3:
            // Tiene ambos hijos.
        } else {

            // Buscamos el menor del subárbol derecho
            TDAElemento<T> padreSucesor = nodoEliminar;
            TDAElemento<T> sucesor = derecho;

            while (sucesor.getHijoIzquierdo() != null) {
                padreSucesor = sucesor;
                sucesor = sucesor.getHijoIzquierdo();
            }

            /*
             * Si el sucesor NO es el hijo derecho inmediato,
             * tenemos que quitarlo de su posición original.
             */
            if (padreSucesor != nodoEliminar) {

                padreSucesor.setHijoIzquierdo(
                        sucesor.getHijoDerecho());

                sucesor.setHijoDerecho(derecho);
            }

            sucesor.setHijoIzquierdo(izquierdo);

            reemplazo = sucesor;
        }

        // El padre deja de apuntar al nodo eliminado
        // y empieza a apuntar al reemplazo.
        if (esHijoIzquierdo) {
            padre.setHijoIzquierdo(reemplazo);
        } else {
            padre.setHijoDerecho(reemplazo);
        }

        // Dejamos completamente desconectado al nodo eliminado.
        nodoEliminar.setHijoIzquierdo(null);
        nodoEliminar.setHijoDerecho(null);

        return nodoEliminar;
    }

    @Override
    public boolean insertar(Comparable<T> nuevoDato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void preOrder(Consumer<TDAElemento<T>> consumidor) {
        consumidor.accept(this);
        if (this.hijoIzquierdo != null)
            this.hijoIzquierdo.preOrder(consumidor);
        if (this.hijoDerecho != null)
            this.hijoDerecho.preOrder(consumidor);
    }

    @Override
    public void inOrder(Consumer<TDAElemento<T>> consumidor) {
        if (this.hijoIzquierdo != null)
            this.hijoIzquierdo.inOrder(consumidor);
        consumidor.accept(this);
        if (this.hijoDerecho != null)
            this.hijoDerecho.inOrder(consumidor);
    }

    @Override
    public void postOrder(Consumer<TDAElemento<T>> consumidor) {
        if (this.hijoIzquierdo != null)
            this.hijoIzquierdo.postOrder(consumidor);
        if (this.hijoDerecho != null)
            this.hijoDerecho.postOrder(consumidor);
        consumidor.accept(this);
    }

    @Override
    public boolean esHoja() {
        return this.getHijoDerecho() == null && this.getHijoIzquierdo() == null;
    }

    @Override
    public int cantidadHojas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cantidadHojas'");
    }

    @Override
    public int cantidadNodosInternos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cantidadNodosInternos'");
    }

    @Override
    public int cantidadNodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cantidadNodos'");
    }

    @Override
    public int altura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altura'");
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerNivel'");
    }
}
