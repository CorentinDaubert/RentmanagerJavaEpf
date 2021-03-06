package com.ensta.rentmanager.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.epf.rentmanager.model.Client;

import org.junit.jupiter.api.Test;

public class ClientsTest {
   
    @Test
    void isLegal_should_return_true_when_age_is_over_18() {
        // Given
        Client legalClient = new Client("Smith", "John", "smith.john@ensta.fr", LocalDate.parse("1999-10-09"));
        // Then
        assertTrue(legalClient.isLegal());
    }

    @Test
    void isLegal_should_return_false_when_age_is_under_18() {
        // Given
        Client illegalClient = new Client("Smith", "John", "smith.john@ensta.fr", LocalDate.now());
        // Then
        assertFalse(illegalClient.isLegal());
    }
    

    @Test
    void isLong_should_return_true_when_first_name_is_over_3() {
        // Given
        Client longClient = new Client("Smith", "John", "smith.john@ensta.fr", LocalDate.parse("1999-10-09"));
        // Then
        assertTrue(longClient.isLongFirst());
    }
    
    @Test
    void isLong_should_return_false_when_first_name_is_under_3() {
        // Given
        Client shortClient = new Client("Smith", "Jo", "smith.john@ensta.fr", LocalDate.parse("1999-10-09"));
        // Then
        assertFalse(shortClient.isLongFirst());
    }


    @Test
    void isLong_should_return_true_when_last_name_is_over_3() {
        // Given
        Client longClient = new Client("Smith", "John", "smith.john@ensta.fr", LocalDate.parse("1999-10-09"));
        // Then
        assertTrue(longClient.isLongLast());
    }
    
    @Test
    void isLong_should_return_false_when_last_name_is_under_3() {
        // Given
        Client shortClient = new Client("Sm", "John", "smith.john@ensta.fr", LocalDate.parse("1999-10-09"));
        // Then
        assertFalse(shortClient.isLongLast());
    }

}