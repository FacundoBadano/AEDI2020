/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

public class TArbolBBTest {

    TArbolBB<Integer> arbolPrueba;
    TElementoAB<Integer> elemento1;
    TElementoAB<Integer> elemento2;
    TElementoAB<Integer> elemento3;

    public TArbolBBTest() {

    }

    /**
     * seteo el arbol y los elementos que voy a usar.
     */
    @Before
    public void setUp() {
        arbolPrueba = new TArbolBB<>();
        elemento1 = new TElementoAB<>(1, 1);
        elemento2 = new TElementoAB<>(2, 2);
        elemento3 = new TElementoAB<>(3, 3);
    }

    /**
     * test de inOrden de un arbol vacio.
     */
    @Test
    public void testInordenVacio() {
        String stringInorden = arbolPrueba.inOrden();
        assertEquals(stringInorden, null);
    }

    /**
     * test de inOrden de un arbol con 1 elemento.
     */
    @Test
    public void testInordenElemento() {
        arbolPrueba.insertar(elemento1);
        String stringInorden = arbolPrueba.inOrden();
        String stringEsperado = elemento1.getEtiqueta().toString() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS;
        assertEquals(stringInorden, stringEsperado);
    }

    /**
     * test de inOrden con un arbol desbalanceado de 3 elementos.
     */
    @Test
    public void testInordenElemento2() {
        arbolPrueba.insertar(elemento1);
        arbolPrueba.insertar(elemento2);
        arbolPrueba.insertar(elemento3);
        String stringInorden = arbolPrueba.inOrden();
        String stringEsperado = elemento1.getEtiqueta().toString() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS
                + elemento2.getEtiqueta().toString() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS
                + elemento3.getEtiqueta().toString() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS;
        assertEquals(stringInorden, stringEsperado);
    }

    /**
     * test inOrden de un arbol balanceado de 3 elementos.
     */
    @Test
    public void testInordenElemento3() {
        arbolPrueba.insertar(elemento2);
        arbolPrueba.insertar(elemento3);
        arbolPrueba.insertar(elemento1);
        String stringInorden = arbolPrueba.inOrden();
        String stringEsperado = elemento1.getEtiqueta().toString() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS
                + elemento2.getEtiqueta().toString() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS
                + elemento3.getEtiqueta().toString() + TArbolBB.SEPARADOR_ELEMENTOS_IMPRESOS;
        assertEquals(stringInorden, stringEsperado);
    }

    /**
     * test de tamaño de un arbol vacio.
     */
    @Test
    public void testTamanioVacio() {
        int tamanioEsperado = 0;
        int tamanioReal = arbolPrueba.obtenerTamanio();
        assertEquals(tamanioReal, tamanioEsperado);
    }

    /**
     * test de tamaño de un arbol con un elemento.
     */
    @Test
    public void testTamanioElemento1() {
        arbolPrueba.insertar(elemento1);
        int tamanioEsperado = 1;
        int tamanioReal = arbolPrueba.obtenerTamanio();
        assertEquals(tamanioReal, tamanioEsperado);
    }

    /**
     * test de tamaño de un arbol desbalanceado de 3 elementos.
     */
    @Test
    public void testTamanioElemento2() {
        arbolPrueba.insertar(elemento1);
        arbolPrueba.insertar(elemento2);
        arbolPrueba.insertar(elemento3);
        int tamanioEsperado = 3;
        int tamanioReal = arbolPrueba.obtenerTamanio();
        assertEquals(tamanioReal, tamanioEsperado);
    }

    /**
     * test de tamaño de un arbol balanceado de 3 elementos.
     */
    @Test
    public void testTamanioElemento3() {
        arbolPrueba.insertar(elemento3);
        arbolPrueba.insertar(elemento1);
        arbolPrueba.insertar(elemento2);
        int tamanioEsperado = 3;
        int tamanioReal = arbolPrueba.obtenerTamanio();
        assertEquals(tamanioReal, tamanioEsperado);
    }

    /**
     * test de altura de arbol vacio.
     */
    @Test
    public void testAlturaVacio() {
        int alturaEsperada = -1;
        int alturaReal = arbolPrueba.obtenerAltura();
        assertEquals(alturaReal, alturaEsperada);
    }

    /**
     * test de altura de arbol con un elemento.
     */
    @Test
    public void testAlturaElemento1() {
        arbolPrueba.insertar(elemento1);
        int alturaEsperada = 0;
        int alturaReal = arbolPrueba.obtenerAltura();
        assertEquals(alturaReal, alturaEsperada);
    }

    /**
     * test de altura de arbol desbalanceado con 3 elementos.
     */
    @Test
    public void testAlturaNoBalanceado() {
        arbolPrueba.insertar(elemento1);
        arbolPrueba.insertar(elemento2);
        arbolPrueba.insertar(elemento3);
        int alturaEsperada = 2;
        int alturaReal = arbolPrueba.obtenerAltura();
        assertEquals(alturaReal, alturaEsperada);
    }

    /**
     * test de altura de arbol balanceado con 3 elementos.
     */
    @Test
    public void testAlturaBalanceado() {
        arbolPrueba.insertar(elemento2);
        arbolPrueba.insertar(elemento1);
        arbolPrueba.insertar(elemento3);
        int alturaEsperada = 1;
        int alturaReal = arbolPrueba.obtenerAltura();
        assertEquals(alturaReal, alturaEsperada);
    }
    
    @Test
    public void testNivelVacio(){
        int resEsperado = -0;
        assertEquals(resEsperado, arbolPrueba.obtenerNivel(elemento1.getEtiqueta()));
    }
            
    @Test
    public void testNivelRaiz(){
        arbolPrueba.insertar(elemento1);
        int resEsperado = 0;
        assertEquals(resEsperado,arbolPrueba.obtenerNivel(elemento1.getEtiqueta()));
    }
    
    public void testNivelElementoEncontrado(){
        arbolPrueba.insertar(elemento1);
        arbolPrueba.insertar(elemento2);
        arbolPrueba.insertar(elemento3);
        int resEsperado = 2;
        assertEquals(resEsperado,arbolPrueba.obtenerNivel(elemento3.getEtiqueta()));
        
    }
    
    public void testNivelElementoNoEncontrado(){
        arbolPrueba.insertar(elemento1);
        arbolPrueba.insertar(elemento2);
        arbolPrueba.insertar(elemento3);
        int resEsperado = -3;
        assertEquals(resEsperado,arbolPrueba.obtenerNivel(4));
    }

}
