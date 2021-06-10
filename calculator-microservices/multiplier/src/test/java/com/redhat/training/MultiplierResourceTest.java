package com.redhat.training;

import static org.mockito.Mockito.mock;

import com.redhat.training.service.SolverService;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MultiplierResourceTest {
    
    SolverService solverService;
    MultiplierResource multiplierResource;

    @BeforeEach
    public void setup() {
        solverService = mock(SolverService.class);
        multiplierResource = new MultiplierResource(solverService);
    }

    @Test
    public void testMultiplier() {
	// GIVEN
	Mockito.when(solverService.solve("3")).thenReturn(Float.valueOf("3"));
	Mockito.when(solverService.solve("5")).thenReturn(Float.valueOf("5"));

	// WHEN
	Float result = multiplierResource.multiply("3", "5");
	
	// THEN
    	assertEquals(15.0f, result);
    }

    @Test
    public void testNegativeMultiplier() {
	// GIVEN
	Mockito.when(solverService.solve("-3")).thenReturn(Float.valueOf("-3"));
	Mockito.when(solverService.solve("5")).thenReturn(Float.valueOf("5"));

	// WHEN
	Float result = multiplierResource.multiply("-3", "5");
	
	// THEN
    	assertEquals(-15.0f, result);
    }


    @Test
    public void testWrongValue() {
    	    WebApplicationException cause = new WebApplicationException("Unknown error", Response.Status.BAD_REQUEST);
    Mockito.when(solverService.solve("a")).thenThrow( new ResteasyWebApplicationException(cause) );
    Mockito.when(solverService.solve("3")).thenReturn(Float.valueOf("3"));

    // When
    Executable multiplication = () -> multiplierResource.multiply("a", "3");

    // Then
    assertThrows( ResteasyWebApplicationException.class, multiplication );
    }
}
