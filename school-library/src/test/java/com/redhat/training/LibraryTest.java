package com.redhat.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.redhat.training.books.Book;
import com.redhat.training.books.BookNotAvailableException;
import com.redhat.training.inventory.InMemoryInventory;


public class LibraryTest {

    InMemoryInventory inventory;
    Library library;

    @BeforeEach
    public void setUp() {
        inventory = new InMemoryInventory();
        library = new Library(inventory);
    }

    @Test
    public void checkInventoryDecreaseByOne() throws BookNotAvailableException  {

	// GIVEN
	inventory.add(new Book("book1"));
	inventory.add(new Book("book1"));

	// WHEN	
	library.checkOut("studentID", "book1");

	// THEN
	assertEquals(1, inventory.countCopies("book1"));
    }

    @Test
    public void checkEmptyInventory()  throws BookNotAvailableException {
    	// GIVEN
	inventory.add(new Book("book1"));
	inventory.add(new Book("book1"));
	
	library.checkOut("studentID", "book1");
	library.checkOut("studentID", "book1");

	// WHEN
	final BookNotAvailableException exception = assertThrows(
			BookNotAvailableException.class,
			() -> {
				library.checkOut("student", "book1");
			}
			);

	// THEN
	assertTrue(exception.getMessage().matches("Book book1 is not available"));
	

    }
}
