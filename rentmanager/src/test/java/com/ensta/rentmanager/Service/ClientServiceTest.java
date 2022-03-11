package com.ensta.rentmanager.Service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientDao clientDao;


    @Test
    public void find_All_dao_throws_exception() throws DaoException {
        // When
        when(clientDao.findAll()).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> clientService.findAll());
    }


    @Test
    public void find_by_id_dao_throws_exception() throws DaoException {
        // When
        when(clientDao.findById(1)).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> clientService.findById(1));
    }

    @Test
    public void count_dao_throws_exception() throws DaoException {
        // When
        when(clientDao.count()).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> clientService.count());
    }


    @Test
    public void create_dao_throws_exception() throws DaoException {
        // Given
        Client client = new Client("corentin", "daubert", "aaaa@bb.bom", LocalDate.parse("1999-06-12"));
        // When
        when(clientDao.create(client)).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> clientService.create(client));
    }

    @Test
    public void delete_dao_throws_exception() throws DaoException {
        // When
        doThrow(new DaoException()).when(clientDao).delete(1);
        // Then
        assertThrows(ServiceException.class, () -> clientService.delete(1));
    }

}