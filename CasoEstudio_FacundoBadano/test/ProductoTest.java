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
public class ProductoTest {
    
    Producto productoTest1;
    Producto productoTest2;
    
    
    public ProductoTest() {
    }
    
    @Before
    public void setUp() {
        productoTest1 = new Producto ("Producto Test1", "Producto Test1", 100);
        productoTest2 = new Producto ("Producto Test2", "Producto Test2", 200);
    }

    @Test
    public void testAgregarStock(){
        Integer resEsperado = 2;
        productoTest1.agregarStock(1);
        
        assertEquals(productoTest1.getStock(), resEsperado);
    }
    @Test
    public void testRestarStockInsuficiente() {
        //Se intentan retirar más unidades de las disponibles.
        Integer resEsperado = -1;
        assertEquals(productoTest1.restarStock(10), resEsperado);
        
    }
    
    @Test
    public void testRestarStockCorrecto() {
        
        Integer resEsperado = 1;
        productoTest1.agregarStock(5);
        
        assertEquals(productoTest1.restarStock(5), resEsperado);
        
    }
    
}
