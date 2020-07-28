
import java.util.LinkedList;

public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private T datos;

    /**
     * @param unaEtiqueta
     * @param unosDatos 
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }

    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    /**
     * @param unElemento
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden() {
         String result = "";
        if (this.hijoIzq != null) {
            result += hijoIzq.inOrden();
        }
        result += this.imprimir() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS;
        if (this.hijoDer != null) {
            result += this.hijoDer.inOrden();
        }
        return result;   
    }

  // @Override
    public void inOrden(Lista<T> unaLista) {
          String result = "";
        if (this.hijoIzq != null) {
            result += hijoIzq.inOrden();
        }
        result += this.imprimir() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS;
        if (this.hijoDer != null) {
            result += this.hijoDer.inOrden();
        }
        String[] aux = result.split(TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS);
        for (int i= 0; i< aux.length; i++){
            Nodo <T> nodoAux = new Nodo(aux[i], aux[i]); 
            unaLista.insertar(nodoAux);
        }  
        
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    

    @Override
    public int obtenerAltura() {
        int a = -1, b = -1;
        if (this.hijoIzq != null) {
            a = this.hijoIzq.obtenerAltura();
        }
        if (this.hijoDer != null) {
            b = this.hijoDer.obtenerAltura();
        }
        if (a >= b) {
            return a + 1;
        }
        return b + 1;
    }

    @Override
    public int obtenerTamanio() {
        int nodos = 1;
        if (this.hijoIzq != null) {
            nodos += this.hijoIzq.obtenerTamanio();
        }
        if (this.hijoDer != null) {
            nodos += this.hijoDer.obtenerTamanio();
        }
        return nodos;
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        int nivel = 0;
        if (unaEtiqueta.compareTo(this.getEtiqueta()) == 0) {
            return nivel;
        }
        if (unaEtiqueta.compareTo(this.getEtiqueta()) < 0) {
            if (this.hijoIzq == null) {
                System.out.println("el nodo no esta en el arbol");
                return -1;
            }
            return this.hijoIzq.obtenerNivel(unaEtiqueta) + 1;
        } else {
            if (this.hijoDer == null) {
                System.out.println("el nodo no esta en el arbol");
                return -1;
            }
            return this.hijoDer.obtenerNivel(unaEtiqueta) + 1;
        }
    }

    
    @Override
    public int obtenerCantidadHojas() {
        int hojas = 0;
        if (this.hijoIzq == null && this.hijoDer == null) {
            hojas++;
        }
        if (this.hijoIzq != null) {
            hojas += this.hijoIzq.obtenerCantidadHojas();
        }
        if (this.hijoDer != null) {
            hojas += this.hijoDer.obtenerCantidadHojas();
        }
        return hojas;
    }

    @Override
    public void inOrden(LinkedList<T> unaLista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   	
}
