

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TArbolBB arbol = new TArbolBB();

        arbol.insertar(new TElementoAB(10, 10));
        arbol.insertar(new TElementoAB(1, 1));
        arbol.insertar(new TElementoAB(84, 84));
        arbol.insertar(new TElementoAB(20, 20));
        arbol.insertar(new TElementoAB(45, 45));
        arbol.insertar(new TElementoAB(23, 23));
        arbol.insertar(new TElementoAB(7, 7));
        arbol.insertar(new TElementoAB(100, 100));
        System.out.println("El tamaño del arbol es de: " + arbol.obtenerTamanio());
        System.out.println("La altura del arbol es de: " + arbol.obtenerAltura());
    }

}
