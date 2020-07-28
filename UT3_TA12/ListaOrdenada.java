/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 * @param <T>
 */
public class ListaOrdenada<T> extends Lista<T> {

    @Override
    public void insertar(Nodo<T> unNodo) {
        int largo = this.cantElementos();
        int comparaciones = this.getCantComparaciones();
        int movimientos = this.getCantMovimientos();
        // verificamos que el nodo no sea nulo, tambien tendriamos que verificar que ya
        // no este en la lista
        comparaciones += 1;
        if (unNodo == null) {
            System.out.println("no se puede agregar el producto");
            return;
        }
        // se verifica si la lista esta vacia
        comparaciones += 1;
        if (this.esVacia()) {
            this.setPrimero(unNodo);
            this.setCantMovimientos(movimientos + 1);
            this.setCantElementos(largo + 1);
            this.setCantComparaciones(comparaciones);
        } else {
            Nodo<T> cabeza = this.getPrimero();
            if (this.cantElementos() == 1) {
                // se analizan los dos casos posibles para una lista de un elemento
                comparaciones += 1;
                if (unNodo.getEtiqueta().compareTo(cabeza.getEtiqueta()) <= 0) {
                    unNodo.setSiguiente(cabeza);
                    this.setPrimero(unNodo);
                    this.setCantElementos(largo + 1);
                    this.setCantMovimientos(movimientos + 2);
                } else {
                    this.getPrimero().setSiguiente(unNodo);
                    this.setCantElementos(largo + 1);
                    this.setCantMovimientos(movimientos + 1);
                }
                // en caso de una lista no vacia y con mas de un elemento
            } else if (unNodo.getEtiqueta().compareTo(cabeza.getEtiqueta()) <= 0) {
                comparaciones += 2;
                unNodo.setSiguiente(cabeza);
                this.setPrimero(unNodo);
                this.setCantMovimientos(movimientos + 2);
                this.setCantElementos(largo + 1);
            } else {
                comparaciones += 2;
                Nodo<T> actual = this.getPrimero();
                while (actual.getSiguiente() != null) {
                    comparaciones += 1;
                    Nodo<T> siguiente = actual.getSiguiente();
                    comparaciones += 1;
                    if (unNodo.getEtiqueta().compareTo(siguiente.getEtiqueta()) <= 0) {
                        unNodo.setSiguiente(actual.getSiguiente());
                        actual.setSiguiente(unNodo);
                        this.setCantElementos(largo + 1);
                        this.setCantMovimientos(movimientos + 2);
                        this.setCantComparaciones(comparaciones);
                        return;
                    }
                    actual = actual.getSiguiente();
                }
                comparaciones += 1;
                actual.setSiguiente(unNodo);
                this.setCantMovimientos(movimientos + 1);
                this.setCantElementos(largo + 1);
            }
            this.setCantComparaciones(comparaciones);
        }
    }
}
