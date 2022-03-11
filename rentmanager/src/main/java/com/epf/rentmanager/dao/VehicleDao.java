package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {
	

	private VehicleDao() {}

	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT constructeur, modele,  nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	
	public int create(Vehicle vehicle) throws DaoException {
		int id = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_VEHICLE_QUERY,
					id = Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, vehicle.getConstructor());
			ps.setString(2, vehicle.getModel());
			ps.setInt(3, vehicle.getNumPlace());
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
			PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int count() throws DaoException {
		int count=0;
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstat = conn.prepareStatement(FIND_VEHICLES_QUERY);
			
			ResultSet rs = pstat.executeQuery();
			while(rs.next()){
			 count++;
			}
			pstat.close();
			conn.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		try {
			Connection conn =ConnectionManager.getConnection();
			PreparedStatement pstat = conn.prepareStatement(FIND_VEHICLE_QUERY);
			
			pstat.setInt(1, id);
			
			ResultSet rs = pstat.executeQuery();
			
			System.out.println(rs);
			
			rs.next();
			
			String vehicleconstructor = rs.getString("constructeur");
			String vehiclemodel = rs.getString("modele");
			int vehicleplaces = rs.getInt("nb_places");
			
			Vehicle vehicle = new Vehicle (id,vehicleconstructor,vehiclemodel,vehicleplaces);
			pstat.close();
			conn.close();
			return Optional.of(vehicle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<Vehicle> findAll() throws DaoException {
		try {
			Connection conn =ConnectionManager.getConnection();
			PreparedStatement pstat = conn.prepareStatement(FIND_VEHICLES_QUERY);
						
			ResultSet rs = pstat.executeQuery();
			
			System.out.println(rs);
			List<Vehicle> vehicleList = new ArrayList<Vehicle>();
			
			while(rs.next()){
			
			int vehicleId = rs.getInt("id");
			String vehicleconstructor = rs.getString("constructeur");
			String vehiclemodel = rs.getString("modele");
			int vehicleplaces = rs.getInt("nb_places");
			
			
			 vehicleList.add(new Vehicle (vehicleId,vehicleconstructor,vehiclemodel,vehicleplaces));
			}
			pstat.close();
			conn.close();
			return vehicleList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
