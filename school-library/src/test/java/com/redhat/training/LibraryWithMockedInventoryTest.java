package com.redhat.training;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.redhat.training.books.BookNotAvailableException;
import com.redhat.training.inventory.Inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LibraryWithMockedInventoryTest {

    Inventory inventory;
    Library library;

    @BeforeEach
    public void setUp() {
        inventory = mock(Inventory.class);
        library = new Library(inventory);
    }

    @Test
    public void checkingOutWithdrawsFromInventoryWhenBookIsAvailable() 
	    throws BookNotAvailableException {

	// GIVEN
	when(inventory.isBookAvailable("book1")).thenReturn(true);

	// WHEN
	library.checkOut("student1", "book1");

	// THEN
	verify(inventory).withdraw("book1");
    	
    }

    @Test
    public void checkingOutDoesNotWithdrawFromInventoryWhenBookIsNotAvailable() { 

	// GIVEN
	when(inventory.isBookAvailable("book1")).thenReturn(false);

	// WHEN
	try {
		library.checkOut("student1", "book1");
	} catch (BookNotAvailableException e) {
		// empty
	 }

	// THEN
	verify(inventory, times(0)).withdraw("book1");
    	
    }
}
