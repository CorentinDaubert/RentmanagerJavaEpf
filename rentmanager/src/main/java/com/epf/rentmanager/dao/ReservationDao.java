package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public int create(Reservation reservation) throws DaoException {
		int id = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY,
					id = Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, reservation.getIdClient());
			ps.setInt(2, reservation.getIdVehicle());
			ps.setDate(3, Date.valueOf(reservation.getDateStart()));
			ps.setDate(4, Date.valueOf(reservation.getDateEnd()));
			ps.execute();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}
	
	public void delete(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		List<Reservation> result = new ArrayList<>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			ps.setInt(1, clientId);
			ps.execute();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setIdClient(clientId);
				reservation.setIdVehicle(rs.getInt("vehicle_id"));
				reservation.setDateStart(rs.getDate("debut").toLocalDate());
				reservation.setDateEnd(rs.getDate("fin").toLocalDate());
				result.add(reservation);
			}
			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> result = new ArrayList<>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			ps.setInt(1, vehicleId);
			ps.execute();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setIdClient(rs.getInt("client_id"));
				reservation.setIdVehicle(vehicleId);
				reservation.setDateStart(rs.getDate("debut").toLocalDate());
				reservation.setDateEnd(rs.getDate("fin").toLocalDate());
				result.add(reservation);
			}
			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> result = new ArrayList<>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setIdClient(rs.getInt("client_id"));
				reservation.setIdVehicle(rs.getInt("vehicle_id"));
				reservation.setDateStart(rs.getDate("debut").toLocalDate());
				reservation.setDateEnd(rs.getDate("fin").toLocalDate());
				result.add(reservation);
			}
			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int count() throws DaoException {
		int count = 0;

		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				 count++;
				}
			ps.execute();
			rs.close();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
