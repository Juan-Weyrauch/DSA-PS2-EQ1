package ucu.edu.aed.structures;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolBinario;
import ucu.edu.aed.tda.TDAElemento;

public class ArbolBinario<T> implements TDAArbolBinario<T> {

    TDAElemento<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public ArbolBinario(TDAElemento<T> raiz) {
        this.raiz = raiz;
    }

    @Override
    public T buscar(Comparable<T> predicate) {
        if (raizEsNula()) {
            return null;
        }

        TDAElemento<T> nodoEncontrado = this.raiz.buscar(predicate);
        if (nodoEncontrado != null) {
            return nodoEncontrado.getDato();
        }

        return null;
    }

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return this.raiz;
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void inOrder(Consumer<T> consumidor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inOrder'");
    }

    @Override
    public void preOrder(Consumer<T> consumidor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'preOrder'");
    }

    @Override
    public void postOrder(Consumer<T> consumidor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postOrder'");
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }

    @Override
    public int cantidadNodos() {
        return this.raizEsNula() ? 0 : this.raiz.cantidadNodos();
    }

    @Override
    public int cantidadHojas() {
        return this.raizEsNula() ? 0 : this.raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos() {
        return this.raizEsNula() ? 0 : this.raiz.cantidadNodosInternos();
    }

    private boolean raizEsNula() {
        return this.raiz == null;
    }

}
