package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

@Service
public class ClientService {

	private ClientDao clientDao;
	@Autowired
	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
		}
	
	
	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		if (!client.isLegal()) {
		} else if (!client.isLongFirst()) {
		} else if (!client.isLongLast()) {
		}else {
		try {
			String nameMaj = client.getName().toUpperCase();
			client.setName(nameMaj);
			return clientDao.create(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		}
		
		return 0;
	}

	public void delete(int id) throws ServiceException {
		try {
			clientDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public int count() throws ServiceException {
		try {
			return clientDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Client findById(int id) throws ServiceException {
		// TODO: récupérer un client par son id
		
		try {
			return clientDao.findById(id).get();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
