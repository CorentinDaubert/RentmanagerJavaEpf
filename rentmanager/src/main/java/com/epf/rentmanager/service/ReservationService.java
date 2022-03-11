package com.epf.rentmanager.service;
import java.util.List;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	private ReservationDao reservationDao;
	@Autowired
	private ReservationService(ReservationDao reservationDao){
		this.reservationDao = reservationDao;
		}

	public List<Reservation> findAll() throws ServiceException {
		try {
			return reservationDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public int create(Reservation reservation) throws ServiceException {

			try {
				return reservationDao.create(reservation);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			return 0;
		}

	
	public int count() throws ServiceException {
		int count = 0;
		try {
			count = reservationDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void delete(int id) throws ServiceException {
		try {
			reservationDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	
	public List<Reservation> findResaByClientId(int client_id) throws ServiceException {
		try {
			return reservationDao.findResaByClientId(client_id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public List<Reservation> findResaByVehicleId(int vehicule_id) throws ServiceException {
		try {
			return reservationDao.findResaByVehicleId(vehicule_id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
		}
	}
