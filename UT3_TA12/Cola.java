
public class Cola<T>{
    private Lista<T> listaEnlazada;

    public Cola(){
        listaEnlazada = new Lista<>();
    }

    public void insertar(Nodo<T> unNodo){
        listaEnlazada.insertarFinal(unNodo);
    }

    public boolean eliminar(){
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