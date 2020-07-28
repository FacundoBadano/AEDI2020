/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Facundo Badano
 */
public class Geant {

    private Lista<Sucursal> listaSucursales;
    private TArbolBB<Producto> todosProductos;

    public Geant() {
        this.listaSucursales = new Lista();
        this.todosProductos = new TArbolBB();

    }

    public Lista<Sucursal> getListaSucursales() {
        return listaSucursales;
    }
    
    public void imprimirProductos() {
        System.out.println(todosProductos.inOrden());
    }

    public void insertarSucursal(Sucursal sucursal) {

        Nodo nodoSucursal = new Nodo(sucursal.getId(), sucursal);
        this.listaSucursales.insertar(nodoSucursal);
    }

    public void insertarProductoEnSucursal(Producto unProducto, Comparable idSucursal) {
        Sucursal sucursalAInsertar = this.obtenerSucursalPorID(idSucursal);
        if (sucursalAInsertar != null){
            sucursalAInsertar.insertarProductos(unProducto);
        }
    }

    public void insertarProductoEnSucursalCantidad (Producto unProducto, Integer cantidad, Comparable idSucursal) {
        Sucursal sucursalAInsertar = this.obtenerSucursalPorID(idSucursal);
        unProducto.setStock(cantidad);
        if (sucursalAInsertar != null){
            sucursalAInsertar.insertarProductos(unProducto);
        }
    }
    
    public Lista<Producto> listarStockSucursalPorNombreProducto(Comparable idSucursal) {
        Sucursal sucursalAListar = this.obtenerSucursalPorID(idSucursal);
        Lista<Producto> resultado = new Lista();
        if(sucursalAListar != null){
            Lista<Producto> listaProd = sucursalAListar.getStockSucursal().inorden();
            Nodo<Producto> aux = listaProd.getPrimero();
            
            while (aux != null){
                Nodo<Producto> temp = new Nodo(aux.getDato().getNombre() + " - stock: "+aux.getDato().getStock(), aux);
                resultado.insertarOrdenado(temp);
                aux = aux.getSiguiente();
            }
        }
        return resultado;
    }

    public void venderProducto(Comparable nombreProducto, Integer cantidad, Comparable idSucursal) {

        Integer aux;
        Sucursal sucursal = this.obtenerSucursalPorID(idSucursal);
        Lista<Sucursal> listaSucursalesConStock;
        //Sucursal no válida.
        if (sucursal == null) {
            System.out.println("La sucursal " + idSucursal + " no es válida.");
            System.out.println("Prueba indicando alguna de las siguientes:");
            System.out.println(listaSucursales.imprimir(" - "));
        } else {
            aux = sucursal.venderStock(nombreProducto, cantidad);

            //No hay stock suficiente
            if (aux == -1 || aux == -2) {
                System.out.println("No hay stock suficiente para realizar la venta.");
                System.out.println("Sucursales con stock disponible: ");
                // Se imprime en pantalla las sucursales con stock disponible.

                listaSucursalesConStock = this.stockDisponibleSucursal(nombreProducto, cantidad);
                Nodo<Sucursal> sucursalAux = listaSucursalesConStock.getPrimero();
                while (sucursalAux != null) {
                    System.out.println("Sucursal: " + sucursalAux.getDato().getId() + " / Stock disponible: " + sucursalAux.getEtiqueta() + " unidades.");
                }

            }
        }

    }

    public Sucursal obtenerSucursalPorID(Comparable IDSucursal) {

        Nodo<Sucursal> nodoSucursal = this.listaSucursales.buscar(IDSucursal);

        //Si se encontro la sucursal
        if (nodoSucursal != null) {
            Sucursal sucursalEncontrada = nodoSucursal.getDato();
            return sucursalEncontrada;
        } else {
            return null;
        }

    }

    public Lista<Sucursal> insertarOrdenadoPorCantidad(Lista<Sucursal> listaProductos, Comparable idProducto) {
        // Con la lista de sucursales que tienen stock del producto.
        // Devuelve una lista de sucursales ordenada por cantidad del mismo.

        Lista<Sucursal> listaResultado = new Lista();
        Nodo<Sucursal> auxSucursal = listaProductos.getPrimero();
        TElementoAB<Producto> auxProducto;

        while (auxSucursal != null) {
            TArbolBB stockSucursal = auxSucursal.getDato().getStockSucursal();
            TElementoAB<Producto> unProducto = stockSucursal.buscar(idProducto);

            //Creo nodo sucursal con etiqueta stock del producto, para poder instarlo ordenado
            Nodo<Sucursal> sucursalOrdenada = new Nodo(unProducto.getDatos().getStock(), auxSucursal.getDato());

            listaResultado.insertarOrdenado(sucursalOrdenada);

            auxSucursal = auxSucursal.getSiguiente();
        }

        return listaResultado;
    }
    
    public Lista<Sucursal> stockDisponibleSucursal(Comparable nombreProducto, Integer cantidadProducto) {
        // Lista ordenada por cantidad stock, de sucursales que tienen stock disponible de producto X.

        Lista<Sucursal> listaSucursalesConStock = new Lista();
        Nodo<Sucursal> sucursalActual = listaSucursales.getPrimero();

        while (sucursalActual.getSiguiente() != null) {
            Sucursal sucursalAux = sucursalActual.getDato();
            boolean res = sucursalAux.consultarStock(nombreProducto, cantidadProducto);

            //Si tiene stock disponible.
            if (res == true) {
                listaSucursalesConStock.insertar(sucursalActual);
            }

            sucursalActual = sucursalActual.getSiguiente();
        }
        return insertarOrdenadoPorCantidad(listaSucursalesConStock, nombreProducto);
    }

    public void stockProductoPorCodigo(Comparable codigoProducto) {
        //Dado un código de producto, se indica las existencias del mismo en todas las sucursales, ordenada por sucursal.
        

        Nodo<Sucursal> aux = listaSucursales.getPrimero();

        System.out.println("Stock de producto codigo " + codigoProducto + " en cada sucursal: ");
        
        while (aux != null) {
            int stockActual = aux.getDato().obtenerStockProducto(codigoProducto);
            System.out.println("Sucursal: " + aux.getDato().getId() + "  - Stock: " + stockActual);

            aux = aux.getSiguiente();

        }

    }

    public void eliminarProducto(Comparable idProducto){
        //Elimina un producto de todas las sucursales.
        Nodo<Sucursal> auxSucursal = listaSucursales.getPrimero();
        while (auxSucursal != null){
            TElementoAB unProducto = auxSucursal.getDato().getStockSucursal().buscar(idProducto);
            if (unProducto != null){
                auxSucursal.getDato().getStockSucursal().eliminar(idProducto);
            }
            auxSucursal = auxSucursal.getSiguiente();
        }
    }
    
    /* 
        Se carga la informacion desde los archivos.
    */
    private void cargarSucursales(String archivo) {

        String[] lineasArchivo = ManejadorArchivosGenerico.leerArchivo(archivo);

        for (int i = 1; i < lineasArchivo.length; i++) {
            String[] dato = lineasArchivo[i].split(",");

            Sucursal sucursalAux = new Sucursal(dato[0], Integer.parseInt(dato[1]), dato[2], Integer.parseInt(dato[3]), dato[4], dato[5]);
            Nodo<Sucursal> sucursalNodo = new Nodo(sucursalAux.getId(), sucursalAux);
            listaSucursales.insertar(sucursalNodo);
        }

    }

    private void cargarProductos(String archivo) {

        String[] lineasArchivo = ManejadorArchivosGenerico.leerArchivo(archivo);

        for (int i = 1; i < lineasArchivo.length; i++) {

            String[] dato = lineasArchivo[i].split(",");

            String nombre = "";

            //Corroboro que tenga precio, si no tiene no lo tengo en cuenta.
            boolean tienePrecio = true;
            try {
                Float.parseFloat(dato[dato.length - 1]);
            } catch (NumberFormatException e) {
                tienePrecio = false;
            }

            if (tienePrecio == true) {

                //Controlo si se separó mas veces por la coma.
                if (dato.length > 3) {
                    int aux = 1;
                    while (aux != (dato.length - 2)) {
                        nombre = dato[aux].concat(dato[aux + 1]);
                        aux++;
                    }
                    Producto productoAux = new Producto(dato[0], nombre, Float.parseFloat(dato[dato.length - 1]));
                    TElementoAB<Producto> productoElemento = new TElementoAB(productoAux.getEtiqueta(), productoAux);
                    todosProductos.insertar(productoElemento);
                } else {
                    Producto productoAux = new Producto(dato[0], dato[1], Float.parseFloat(dato[dato.length - 1]));
                    TElementoAB<Producto> productoElemento = new TElementoAB(productoAux.getEtiqueta(), productoAux);
                    todosProductos.insertar(productoElemento);
                }
            }
        }
    }

    private void cargarStock(String archivo) {

        String[] lineasArchivo = ManejadorArchivosGenerico.leerArchivo(archivo);

        for (int i = 1; i < lineasArchivo.length; i++) {
            String[] dato = lineasArchivo[i].split(",");

            Nodo<Sucursal> sucursalAIngresarStock = listaSucursales.buscar(dato[0]);

            TElementoAB<Producto> elementoProducto = todosProductos.buscar(dato[1]);

            //Si encuentro el producto y la sucursal 
            if (elementoProducto != null && sucursalAIngresarStock != null) {
                // Creo producto y le pongo la cantidad de stock solicitada
                Producto unProducto = elementoProducto.getDatos();
                //Ingreso producto al stock de la sucursal
                Sucursal unaSucursal = sucursalAIngresarStock.getDato();
                unaSucursal.insertarProductoCantidad(unProducto, Integer.parseInt(dato[2]));
            }

        }
    }
    
    private Lista<Producto> listarProductosOrdenados (){
        Lista<Producto> resultado = new Lista();
        
        Nodo<Sucursal> auxSucursal = listaSucursales.getPrimero();
        
        //Para cada sucursal se recorre su stock y se agrega en una lista a retornar ordenados por ciudad, departamento,
        // nombre y stock
        while (auxSucursal != null){
            String ciudadSucursal = auxSucursal.getDato().getCiudad();
            String departamentoSucursal = auxSucursal.getDato().getDepartamento();
            Lista<Producto> listaProductoSucursal = auxSucursal.getDato().getStockSucursal().inorden();
            Nodo<Producto> aux = listaProductoSucursal.getPrimero();
            
            while (aux != null){
                String etiqueta = (ciudadSucursal + ", " + departamentoSucursal + ", " + aux.getDato().getNombre() + ", stock: " + aux.getDato().getStock());
                Nodo<Producto> temp = new Nodo (etiqueta, aux);
                resultado.insertarOrdenado(temp);
                
                aux = aux.getSiguiente();
            }
            auxSucursal = auxSucursal.getSiguiente();
        }
        
        return resultado;
    }

    public static void main(String[] args) {

        
        
        /*
        1. Incorporar un nuevo producto a una sucursal del supermercado. 
        */
        
        System.out.println("---------------------------1---------------------------");
        Geant pruebaMetodos = new Geant();
        Sucursal sucursalPruebaMetodos = new Sucursal("Local 1", 1, "Sucursal Prueba", 1, "Barrio Prado", "Montevideo");
        Sucursal sucursalPruebaMetodos2 = new Sucursal ("Local 2", 1, "Sucursal Prueba 2", 1, "Barrio Cordon", "Montevideo");
        
        pruebaMetodos.insertarSucursal(sucursalPruebaMetodos);
        pruebaMetodos.insertarSucursal(sucursalPruebaMetodos2);
        
        Producto prodPrueba1 = new Producto("Prueba1", "Prueba1", 10);
        Producto prodPrueba2 = new Producto("Producto2", "Prueba2", 10);
        Producto prodPrueba22 = new Producto ("Producto2", "Prueba2",10);
        Producto prodPrueba3 = new Producto("Producto3", "Prueba3", 10);
        Producto prodPrueba33 = new Producto ("Producto3", "Prueba3", 10);
        
        pruebaMetodos.insertarProductoEnSucursal(prodPrueba1, "Local 1" );
        pruebaMetodos.insertarProductoEnSucursal(prodPrueba2, "Local 1" );
        pruebaMetodos.insertarProductoEnSucursal(prodPrueba2, "Local 1" );
        pruebaMetodos.insertarProductoEnSucursal(prodPrueba3, "Local 1" );
        
        pruebaMetodos.insertarProductoEnSucursal(prodPrueba33, "Local 2" );
        pruebaMetodos.insertarProductoEnSucursal(prodPrueba22, "Local 2");
        
        pruebaMetodos.listarStockSucursalPorNombreProducto("Local 1").imprimir();
  
        /*
        2. Agregar stock a un producto existente en una sucursal.         
        */
        
        System.out.println("---------------------------2---------------------------");
        System.out.println("Se agregan 10 unidades de Producto3 ya existente en Local 1.\n" +
        "Deben quedar 11 unidades de Producto3.");
        pruebaMetodos.insertarProductoEnSucursalCantidad(prodPrueba33, 10, "Local 1");
        pruebaMetodos.listarStockSucursalPorNombreProducto("Local 1").imprimir();
        
        /*
        3. Simular la venta de un producto en una sucursal (reducir el stock de un producto existente).
        De no haber stock suficiente para la venta en esa sucursal, deberá indicarse la lista de
        sucursales que tengan el stock suficiente, ordenada por cantidad de producto. 
        */
        System.out.println("---------------------------3---------------------------");
        System.out.println("Se venden 5 unidades de Producto3 en el Local 1.\n" +
        "Deben quedar 6 unidades de Producto3.");
        
        pruebaMetodos.venderProducto("Producto3", 5, "Local 1");
        pruebaMetodos.listarStockSucursalPorNombreProducto("Local 1").imprimir();
        
        /*
        4. Eliminar productos que ya no se venden (por no ser comercializados más) en todas las
        sucursales del supermercado.
        */
        System.out.println("---------------------------4---------------------------");
        System.out.println("Se elimina Producto2 de todas las sucursales.");
        pruebaMetodos.eliminarProducto("Producto2");
        pruebaMetodos.stockProductoPorCodigo("Producto2");
        
        /*
        5. Dado un código de producto, indicar las existencias del mismo en todas las sucursales,
        ordenada por sucursal.
        */
        
        System.out.println("---------------------------5---------------------------");
        pruebaMetodos.stockProductoPorCodigo("Producto3");
      
        
        /*
        6. Listar todos los productos registrados, en una sucursal, ordenado por nombre de producto,
        presentando además su stock.
        */
        System.out.println("---------------------------6---------------------------");
        System.out.println("Lista productos ordenados por nombre de sucursal 'Local 1':");
        pruebaMetodos.listarStockSucursalPorNombreProducto("Local 1").imprimir();
        
        /*
        7. Listar todos los productos registrados, ordenados por ciudad, barrio, y nombre de producto,
        presentando además su stock.
        */
        System.out.println("---------------------------7---------------------------");
        System.out.println("Listar todos los productos registrados, ordenados por ciudad, barrio, y nombre de producto,\n" +
        "presentando además su stock.");
        pruebaMetodos.listarProductosOrdenados().imprimir();
        System.out.println("-------------------------------------------------------");
        
        Geant geant = new Geant();
        geant.cargarSucursales("./src/sucursales.txt");
        geant.cargarProductos("./src/productos.txt");
        geant.cargarStock("./src/stock.txt");
        
    }
}
