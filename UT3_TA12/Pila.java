
public class Pila<T>{
    private Lista<T> listaEnlazada;

    public Pila(){
        listaEnlazada = new Lista<>();
    }

    public void push(Nodo<T> unNodo){
        listaEnlazada.insertarPrincipio(unNodo);
    }

    public boolean pop(){
        return listaEnlazada.eliminarPrincipio();
    }

    public void vaciar(){
        listaEnlazada.vaciar();
    }

    public boolean esVacia(){
        return listaEnlazada.esVacia();
    }

    public int cantElementos(){
        return listaEnlazada.cantElementos();
    }
}