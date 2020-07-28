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
public class SucursalTest {
    Sucursal sucursalTest;
    Producto productoTest1;
    Producto productoTest2;
    TElementoAB<Producto> productoETest1;
    TElementoAB<Producto> productoETest2;
    
    public SucursalTest() {
    }

    @Before
    public void setUp() {
        sucursalTest = new Sucursal("Local Test", 12345, "Calle 1", 11200, "Centro", "Montevideo");
        productoTest1 = new Producto ("Producto Test1", "Producto Test1", 500);
        productoTest2 = new Producto ("Producto Test2", "Producto Test2", 500);
        productoETest1 = new TElementoAB<Producto>(productoTest1.getEtiqueta(), productoTest1);
        productoETest2 = new TElementoAB<Producto>(productoTest2.getEtiqueta(), productoTest2);
    }

    @Test
    public void testExisteProductoNo(){
        boolean resEsperado = false;
        
        assertEquals(sucursalTest.existeProducto("Producto Test1"), resEsperado);
    }
    
    @Test
    public void testExisteProductoSi(){
        boolean resEsperado = true;
        sucursalTest.insertarProductos(productoTest1);
        assertEquals(sucursalTest.existeProducto("Producto Test1"), resEsperado);
    }
    
    @Test
    public void testObtenerStockProductoSucursalVacia() {
        int resEsperado = 0;
        assertEquals(sucursalTest.obtenerStockProducto("Producto Test1"), resEsperado);
    }
    
    @Test
    public void testObtenerStockProducto(){
        int resEsperado = 5;
        sucursalTest.insertarProductoCantidad(productoTest1, 5);
        assertEquals(sucursalTest.obtenerStockProducto("Producto Test1"), resEsperado);
    }
    
    @Test
    public void testObtenerStockProductoNoExiste(){
        int resEsperado = 0;
        sucursalTest.insertarProductoCantidad(productoTest1, 5);
        assertEquals(sucursalTest.obtenerStockProducto("Producto Test2"), resEsperado);
    }
    
    @Test
    public void testInsertarProductoCantidad(){
        int resEsperado = 10;
        sucursalTest.insertarProductoCantidad(productoTest1, 10);
        TElementoAB<Producto> elementoProducto = sucursalTest.getStockSucursal().buscar(productoTest1.getEtiqueta());
        int stockProducto = elementoProducto.getDatos().getStock();
        assertEquals(stockProducto, resEsperado);
    }
    
    @Test
    public void testVenderStock (){
        // Se agregan 10 unidades de stock de Producto Test1
        sucursalTest.insertarProductoCantidad(productoTest1, 10);
        
        // Se venden 5 unidades
        sucursalTest.venderStock("Producto Test1", 5);
        int resEsperado = 5;
        TElementoAB<Producto> elementoProducto = sucursalTest.getStockSucursal().buscar(productoTest1.getEtiqueta());
        int stockProducto = elementoProducto.getDatos().getStock();
        assertEquals(stockProducto, resEsperado);
    }
    
    @Test
    public void consultarStockTrue(){
        //Se agregan 2 unidades de stock de Producto Test1
        sucursalTest.insertarProductoCantidad(productoTest1, 2);
        
        //Se consulta si la sucursal tiene disponible 1 unidad.
        boolean resEsperado = true;
        assertEquals(sucursalTest.consultarStock(productoTest1.getEtiqueta(), 1), resEsperado);
    }
    
    @Test
    public void consultarStockFalse(){
        //Se agregan 2 unidades de stock de Producto Test1
        sucursalTest.insertarProductoCantidad(productoTest1, 2);
        
        //Se consulta si la sucursal tiene disponible 3 unidades.
        boolean resEsperado = false;
        assertEquals(sucursalTest.consultarStock(productoTest1.getEtiqueta(), 3), resEsperado);
    }
    
}
