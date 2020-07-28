/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Facundo Badano
 */
public class Sucursal {

    //id,telefono,direccion,codigoPostal,ciudad,departamento
    private String id;
    private Integer telefono;
    private String direccion;
    private Integer codigoPostal;
    private String ciudad;
    private String departamento;

    private TArbolBB<Producto> stockSucursal;

    public Sucursal(String id, Integer telefono, String direccion, Integer codigoPostal, String ciudad, String departamento) {

        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.stockSucursal = new TArbolBB<>();

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setStockSucursal(TArbolBB<Producto> stockSucursal) {
        this.stockSucursal = stockSucursal;
    }

    public String getId() {
        return id;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public TArbolBB<Producto> getStockSucursal() {
        return stockSucursal;
    }

    public boolean existeProducto(Comparable idProducto){
        TElementoAB unProducto = stockSucursal.buscar(idProducto);
        if (unProducto != null){
            return true;
        }else{
            return false;
        }
    }
    
    public int obtenerStockProducto(Comparable claveProducto) {
        TElementoAB<Producto> productoBuscado = stockSucursal.buscar(claveProducto);
        if (productoBuscado != null){
            return productoBuscado.getDatos().getStock();
        }else{
            return 0;
        }
    }

    public void insertarProductos(Producto unProducto) {
        if (stockSucursal.esVacio() != true) {
            TElementoAB<Producto> elementoExistente = stockSucursal.buscar(unProducto.getEtiqueta());
            if (elementoExistente == null) {

                TElementoAB<Producto> elementoAux = new TElementoAB(unProducto.getEtiqueta(), unProducto);
                stockSucursal.insertar(elementoAux);
            } else {
                Producto productoActual = elementoExistente.getDatos();
                productoActual.agregarStock(unProducto.getStock());
            }
        } else {
            TElementoAB<Producto> elementoAux = new TElementoAB(unProducto.getEtiqueta(), unProducto);
            stockSucursal.insertar(elementoAux);
        }
    }

    
    public void insertarProductoCantidad (Producto unProducto, Integer cantidad){
        unProducto.setStock(cantidad);
        insertarProductos(unProducto);
    }
    
    //public void altasStock (Comparable idProducto, )
    public Integer venderStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> elementoExistente = stockSucursal.buscar(clave);
        if (elementoExistente != null) {

            int aRetornar = elementoExistente.getDatos().restarStock(cantidad);
            System.out.println("Se han vendido " + cantidad + " unidades de " + clave + " satisfactoriamente.");
            return aRetornar;
        } else {
            return -2;
        }
    }

    //Devuelve true si tiene stock disponible, false en caso contrario.
    public boolean consultarStock(Comparable nombre, Integer cantidad) {
        TElementoAB<Producto> elementoExistente = stockSucursal.buscar(nombre);
        if (elementoExistente != null) {
            int auxiliar = elementoExistente.getDatos().restarStock(cantidad);

            //Verifico si hay disponible.
            if (auxiliar == -1) {
                return false;
            } else {
                return true;
            }

        }//Caso que el producto no existe en el stock.
        else {
            return false;
        }
    }

    public void listarPorNombre() {
        Lista<Producto> listaResultado = stockSucursal.inorden();
        Nodo<Producto> aux = listaResultado.getPrimero();

        if (listaResultado.esVacia()) {
            System.out.println("El stock de la sucursal " + this.getId() + " es vacio.");
        } else {
            System.out.println("Stock de sucursal " + this.getId() + " ordenado por nombre:");
            System.out.println("-------------------------------------------------------");
        }
        while (aux != null) {
            System.out.println("Nombre: " + aux.getDato().getNombre() + " Stock: " + aux.getDato().getStock());
            aux = aux.getSiguiente();
        }

    }

}
