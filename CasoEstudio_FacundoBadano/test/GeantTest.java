/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Facundo Badano
 */
public class GeantTest {

    Geant geantTest;
    Sucursal sucursalTest1;
    Sucursal sucursalTest2;
    Sucursal sucursalTest3;
    Producto productoTest1;
    Producto productoTest2;
    Producto productoTest3;
    
    public GeantTest() {
    }

    @Before
    public void setUp() {
        geantTest = new Geant();
        sucursalTest1 = new Sucursal("Local Test1", 12345, "Calle 1", 11200, "Centro", "Montevideo");
        sucursalTest2 = new Sucursal("Local Test2", 12345, "Calle 5", 11200, "Carrasco", "Montevideo");
        sucursalTest3 = new Sucursal("Local Test3", 12345, "Calle 20", 11200, "Ciudad Vieja", "Montevideo");
        productoTest1 = new Producto("Producto Test1", "Producto Test1", 100);
        productoTest2 = new Producto("Producto Test2", "Producto Test2", 200);
        productoTest3 = new Producto("Producto Test3", "Producto Test3", 150);
    }

    @Test
    public void testInsertarSucursal() {
        geantTest.insertarSucursal(sucursalTest1);
        assertEquals(geantTest.getListaSucursales().getPrimero().getDato().getId(), sucursalTest1.getId());
    }

    @Test
    public void testInsertarProductoEnSucursal() {
        //Se inserta sucursal
        geantTest.insertarSucursal(sucursalTest1);

        //Se inserta producto a la sucursal indicada
        geantTest.insertarProductoEnSucursal(productoTest1, "Local Test1");

        //Se obtiene stock de la sucursal
        TArbolBB stockSucursal = geantTest.getListaSucursales().getPrimero().getDato().getStockSucursal();

        //Se busca el producto anteriormente insertado
        TElementoAB productoEncontrado = stockSucursal.buscar("Producto Test1");

        assertEquals(productoEncontrado.getEtiqueta(), "Producto Test1");
    }

    @Test
    public void testInsertarProductoEnSucursalCantidad() {
        geantTest.insertarSucursal(sucursalTest1);
        geantTest.insertarProductoEnSucursalCantidad(productoTest1, 10, "Local Test1");

        //Se obtiene stock de la sucursal
        TArbolBB stockSucursal = geantTest.getListaSucursales().getPrimero().getDato().getStockSucursal();

        //Se busca el producto anteriormente insertado
        TElementoAB<Producto> productoEncontrado = stockSucursal.buscar("Producto Test1");

        Integer resEsperado = 10;

        assertEquals(productoEncontrado.getDatos().getStock(), resEsperado);
    }

    @Test
    public void testVenderProductoCorrecto() {
        geantTest.insertarSucursal(sucursalTest1);
        //Se ingresan 10 unidades
        geantTest.insertarProductoEnSucursalCantidad(productoTest1, 10, "Local Test1");
        //Se venden 5 unidades
        geantTest.venderProducto("Producto Test1", 5, "Local Test1");
        
        //Se obtiene stock de la sucursal
        TArbolBB stockSucursal = geantTest.getListaSucursales().getPrimero().getDato().getStockSucursal();

        //Se busca el producto anteriormente insertado
        TElementoAB<Producto> productoEncontrado = stockSucursal.buscar("Producto Test1");
        
        Integer resEsperado = 5;
        assertEquals(productoEncontrado.getDatos().getStock(), resEsperado);
    }
    
    @Test
    public void testVenderProductoIncorrecto() {
        geantTest.insertarSucursal(sucursalTest1);
        //Se ingresan 10 unidades
        geantTest.insertarProductoEnSucursalCantidad(productoTest1, 10, "Local Test1");
        //Se intentan vender 15 unidades, por lo tanto la venta no debe efectuarse
        geantTest.venderProducto("Producto Test1", 15, "Local Test1");
        
        //Se obtiene stock de la sucursal
        TArbolBB stockSucursal = geantTest.getListaSucursales().getPrimero().getDato().getStockSucursal();

        //Se busca el producto anteriormente insertado
        TElementoAB<Producto> productoEncontrado = stockSucursal.buscar("Producto Test1");
        
        Integer resEsperado = 10;
        assertEquals(productoEncontrado.getDatos().getStock(), resEsperado);
    }
    
    @Test
    public void testObtenerSucursalPorID(){
        geantTest.insertarSucursal(sucursalTest1);
        geantTest.insertarSucursal(sucursalTest2);
        
        Sucursal sucursalObtenida = geantTest.obtenerSucursalPorID("Local Test2");
        
        //Como no puedo comparar 2 objetos directamente, corroboro que todos sus datos sean los mismos
        
        // ID
        assertEquals(sucursalTest2.getId(), sucursalObtenida.getId());
        // Telefono
        assertEquals(sucursalTest2.getTelefono(), sucursalObtenida.getTelefono());
        // Direccion
        assertEquals(sucursalTest2.getDireccion(), sucursalObtenida.getDireccion());
        // Codigo postal
        assertEquals(sucursalTest2.getCodigoPostal(), sucursalObtenida.getCodigoPostal());
        // Ciudad
        assertEquals(sucursalTest2.getCiudad(), sucursalObtenida.getCiudad());
        // Departamento
        assertEquals(sucursalTest2.getDepartamento(), sucursalObtenida.getDepartamento());
        
    }
   
    @Test
    public void testEliminarProducto (){
        sucursalTest1.insertarProductoCantidad(productoTest1, 5);
        sucursalTest2.insertarProductoCantidad(productoTest1, 10);
        sucursalTest3.insertarProductoCantidad(productoTest1, 3);
        
        geantTest.insertarSucursal(sucursalTest1);
        geantTest.insertarSucursal(sucursalTest2);
        geantTest.insertarSucursal(sucursalTest3);
        
        // Se eliminan los productos te todas las sucursales
        geantTest.eliminarProducto("Producto Test1");
        
        boolean resEsperado = false;
        
        // Se corrobora que no exista el producto en ninguna sucursal
        assertEquals(sucursalTest1.existeProducto("Producto Test1"), resEsperado);
        assertEquals(sucursalTest2.existeProducto("Producto Test1"), resEsperado);
        assertEquals(sucursalTest3.existeProducto("Producto Test1"), resEsperado);
    }
}
