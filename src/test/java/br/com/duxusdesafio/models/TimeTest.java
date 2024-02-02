package br.com.duxusdesafio.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeTest {
	
	  @Test
	    public void testEquals() {
	        Time time1 = new Time();
	        time1.setId(1L);

	        Time time2 = new Time();
	        time2.setId(1L);

	        Time time3 = new Time();
	        time3.setId(2L);

	        assertTrue(time1.equals(time2));
	        assertFalse(time1.equals(time3));
	    }

	    @Test
	    public void testHashCode() {
	        Time time1 = new Time();
	        time1.setId(1L);

	        Time time2 = new Time();
	        time2.setId(1L);

	        assertEquals(time1.hashCode(), time2.hashCode());
	    }

}
