package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	@Autowired
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	
	
	public int create(Vehicle vehicle) throws ServiceException {
		try {
			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void delete(int id) throws ServiceException {
		try {
			vehicleDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}


	public Vehicle findById(int id) throws ServiceException {
		// TODO: récupérer un véhicule par son id
		try {
			return vehicleDao.findById(id).get();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Vehicle> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return vehicleDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public int count() throws ServiceException {
		int count = 0;
		try {
			count = vehicleDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return count;
	}
}
