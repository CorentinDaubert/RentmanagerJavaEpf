package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {
	
	//private static ClientDao instance = null;
	private ClientDao() {}
	/*public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}*/
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	
	public long create(Client client) throws DaoException {
		
		int id = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY,
					id = Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getName());
			ps.setString(2, client.getLastname());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getBirthDate()));

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
			PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY);
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
			PreparedStatement pstat = conn.prepareStatement(FIND_CLIENTS_QUERY);
			
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
	
	public Optional<Client> findById(int id) throws DaoException {
		try {
			Connection conn =ConnectionManager.getConnection();
			PreparedStatement pstat = conn.prepareStatement(FIND_CLIENT_QUERY);
			
			pstat.setInt(1, id);
			
			ResultSet rs = pstat.executeQuery();
			
			System.out.println(rs);
			
			rs.next();
			
			//int clientId = rs.getInt("id");
			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail =rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
			
			Client client = new Client (id,clientLastname,clientFirstname,clientEmail,clientBirthdate);
			pstat.close();
			conn.close();
			return Optional.of(client);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<Client> findAll() throws DaoException {
		try {
			Connection conn =ConnectionManager.getConnection();
			PreparedStatement pstat = conn.prepareStatement(FIND_CLIENTS_QUERY);
						
			ResultSet rs = pstat.executeQuery();
			
			System.out.println(rs);
			List<Client> clientList = new ArrayList<Client>();
			
			while(rs.next()){
			
			int clientId = rs.getInt("id");
			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail =rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
			
			
			 clientList.add(new Client (clientId,clientLastname,clientFirstname,clientEmail,clientBirthdate));
			}
			pstat.close();
			conn.close();
			return clientList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
