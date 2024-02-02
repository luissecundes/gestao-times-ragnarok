package br.com.duxusdesafio.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IntegranteTest {
	
	@Test
    public void testEquals() {
        Integrante integrante1 = new Integrante(1L, "Franquia A", "João", "Analista");
        Integrante integrante2 = new Integrante(1L, "Franquia A", "João", "Analista");
        Integrante integrante3 = new Integrante(2L, "Franquia B", "Maria", "Desenvolvedora");

        assertTrue(integrante1.equals(integrante2));
        assertFalse(integrante1.equals(integrante3));
    }

    @Test
    public void testHashCode() {
        Integrante integrante1 = new Integrante(1L, "Franquia A", "João", "Analista");
        Integrante integrante2 = new Integrante(1L, "Franquia A", "João", "Analista");

        assertEquals(integrante1.hashCode(), integrante2.hashCode());
    }

}
