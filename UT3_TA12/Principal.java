
// para testear la pila y la cola
public class Principal {

    public static void main(String[] args){
        Pila<Integer> pila = new Pila<>();
        Cola<Integer> cola = new Cola<>();

        Nodo<Integer> nodo1 = new Nodo<>(1,"1");
        Nodo<Integer> nodo2 = new Nodo<>(2,"2");
        Nodo<Integer> nodo3 = new Nodo<>(3,"3");
        Nodo<Integer> nodo4 = new Nodo<>(4,"4");

        pila.push(nodo1.clonar());
        pila.push(nodo2.clonar());
        System.out.println("2 elementos insertados");
        System.out.println(pila.cantElementos());

        System.out.println();

        pila.pop();
        System.out.println("1 elemento eliminado");
        System.out.println(pila.cantElementos());
        pila.pop();
        System.out.println("otro elemento eliminado, la pila es vacia?");
        System.out.println(pila.esVacia());

        System.out.println("----- metodod de cola ------");

        cola.insertar(nodo1.clonar());
        cola.insertar(nodo2.clonar());
        cola.insertar(nodo3.clonar());
        cola.insertar(nodo3.clonar());
        System.out.println("4 elementos insertados");
        System.out.println(cola.cantElementos());
        System.out.println(cola.esVacia());

        cola.eliminar();
        System.out.println("1 elemento eliminado");
        System.out.println(cola.cantElementos());

        cola.insertar(nodo4.clonar());
        System.out.println("1 elemento insertado");
        System.out.println(cola.cantElementos());

        cola.vaciar();
        System.out.println("vaciamos cola");
        System.out.println(pila.cantElementos());
        System.out.println(cola.esVacia());

    }
}