public class Lista<T> implements ILista<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int cantElementos;
    private int cantComparaciones;
    private int cantMovimientos;

    public Lista() {
        primero = null;
        cantComparaciones = 0;
        cantMovimientos = 0;
    }

    /**
     * @return the cantComparaciones
     */
    public int getCantComparaciones() {
        return this.cantComparaciones;
    }

    public void setCantComparaciones(int comparaciones){
        this.cantComparaciones = comparaciones;
    }

    /**
     * @return the cantMovimientos
     */
    public int getCantMovimientos() {
        return cantMovimientos;
    }

    public void setCantMovimientos(int movimientos){
        this.cantMovimientos = movimientos;
    }

    @Override
    public void insertar(Nodo<T> unNodo) {
        if (esVacia()) {
            primero = unNodo;
            this.cantComparaciones += 1;
            this.cantElementos += 1;
        } else {
            Nodo<T> aux = primero;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
                this.cantComparaciones += 1;
            }
            aux.setSiguiente(unNodo);
            this.cantComparaciones += 1;
            this.cantMovimientos += 1;
            this.cantElementos += 1;
        }
        this.cantComparaciones += 1;
    }

//----------------------------------------------------------------------
// metodos para pilas y colas
//----------------------------------------------------------------------
    public void insertarPrincipio(Nodo<T> unNodo){
        unNodo.setSiguiente(primero);
        primero = unNodo;
        this.cantElementos += 1;
    }

    public void insertarFinal(Nodo<T> unNodo){
        if (esVacia()) {
            primero = unNodo;
            ultimo = unNodo;
            this.cantElementos += 1;
        }
        else{
            ultimo.setSiguiente(unNodo);
            this.cantElementos += 1;
        }
    }

    public boolean eliminarPrincipio(){
        if (esVacia()){
            return false;
        }
        primero = primero.getSiguiente();
        this.cantElementos -= 1;
        return true;
    }

    public void vaciar(){
        while(this.getPrimero() != null){
            eliminarPrincipio();
        }
        this.cantElementos = 0;
    }
//--------------------------------------------------------------------------

    @Override
    public Nodo<T> buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        } else {
            Nodo<T> aux = primero;
            while (aux != null) {
                if (aux.getEtiqueta().equals(clave)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (esVacia()) {
            return false;
        }
        if (primero.getSiguiente() == null) {
            if (primero.getEtiqueta().equals(clave)) {
                primero = null;
                this.cantElementos -= 1;
                return true;
            }
        }
        Nodo<T> aux = primero;
        if (aux.getEtiqueta().compareTo(clave) == 0) {
            //Eliminamos el primer elemento
            Nodo<T> temp1 = aux;
            Nodo<T> temp = aux.getSiguiente();
            primero = temp;
            this.cantElementos -= 1;
            return true;
        }
        while (aux.getSiguiente() != null) {
            if (aux.getSiguiente().getEtiqueta().equals(clave)) {
                Nodo<T> temp = aux.getSiguiente();
                aux.setSiguiente(temp.getSiguiente());
                this.cantElementos -= 1;
                return true;

            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    public String imprimir() {
        String aux = "";
        if (!esVacia()) {
            Nodo<T> temp = primero;
            while (temp != null) {
                temp.imprimirEtiqueta();
                temp = temp.getSiguiente();
            }
        }
        return aux;
    }

    public String imprimir(String separador) {
        String aux = "";
        if (esVacia()) {
            return "";
        } else {
            Nodo<T> temp = primero;
            aux = "" + temp.getEtiqueta();
            while (temp.getSiguiente() != null) {
                aux = aux + separador + temp.getSiguiente().getEtiqueta();
                temp = temp.getSiguiente();
            }

        }
        return aux;

    }

    @Override
    public int cantElementos() {
        return this.cantElementos;
    }

    public void setCantElementos(int cantElementos){
        this.cantElementos = cantElementos;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        this.primero = unNodo;
    }
}
