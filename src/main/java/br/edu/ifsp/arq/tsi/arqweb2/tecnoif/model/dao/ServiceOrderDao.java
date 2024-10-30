package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Payment;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceOrderDao {

	private final DataSource dataSource;
	
	public ServiceOrderDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Boolean save(ServiceOrder serviceOrder, User user, Payment payment){
		String sql = "INSERT INTO service_order (description, status, emission_date, "
				+ "finalization_date, price, notes, id_user, id_payment)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, serviceOrder.getDescription());
			ps.setString(2, serviceOrder.getStatus().toString());
			ps.setDate(3, Date.valueOf(serviceOrder.getEmissionDate()));
			ps.setDate(4, Date.valueOf(serviceOrder.getFinalizationDate()));
			ps.setDouble(5, serviceOrder.getPrice());
			ps.setString(6, serviceOrder.getNotes());
			ps.setLong(7, user.getId());
			ps.setLong(8, payment.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}

		return true;
	}

	public Optional<List<ServiceOrder>> getAllServiceOrders() {
		String sql = "SELECT * FROM service_order";
		Optional<List<ServiceOrder>> optional = Optional.empty();
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()) {
				List<ServiceOrder> serviceOrders = new ArrayList<>();
				while(rs.next()) {
					ServiceOrder serviceOrder = new ServiceOrder();
					serviceOrder.setId(rs.getLong("id"));
					serviceOrder.setDescription(rs.getString("description"));
					serviceOrder.setStatus(Status.valueOf(rs.getString("status")));
					serviceOrder.setEmissionDate(rs.getDate("emission_date").toLocalDate());
					serviceOrder.setFinalizationDate(rs.getDate("finalization_date").toLocalDate());
					serviceOrder.setPrice(rs.getDouble("price"));
					serviceOrder.setNotes(rs.getString("notes"));
					serviceOrders.add(serviceOrder);
				}
				optional = Optional.of(serviceOrders);
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a leitura no BD", e);
		}

		return optional;
	}

	public Optional<ServiceOrder> getServiceOrderById(Long id) {
		String sql = "SELECT * FROM service_order WHERE id = ?";
		Optional<ServiceOrder> optional = Optional.empty();
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					ServiceOrder serviceOrder = new ServiceOrder();
					serviceOrder.setId(rs.getLong("id"));
					serviceOrder.setDescription(rs.getString("description"));
					serviceOrder.setStatus(Status.valueOf(rs.getString("status")));
					serviceOrder.setEmissionDate(rs.getDate("emission_date").toLocalDate());
					serviceOrder.setFinalizationDate(rs.getDate("finalization_date").toLocalDate());
					serviceOrder.setPrice(rs.getDouble("price"));
					serviceOrder.setNotes(rs.getString("notes"));
					optional = Optional.of(serviceOrder);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a leitura no BD", e);
		}

		return optional;
	}

	public Boolean editServiceOrder(ServiceOrder serviceOrder) {
		String sql = "UPDATE service_order SET status = ?, finalization_date = ?, notes = ?"
				+ " WHERE id = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, serviceOrder.getStatus().toString());
			ps.setDate(2, Date.valueOf(serviceOrder.getFinalizationDate()));
			ps.setString(3, serviceOrder.getNotes());
			ps.setLong(4, serviceOrder.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}

		return true;
	}

	public Boolean deleteServiceOrder(Long id) {
		String sql = "DELETE FROM service_order WHERE id = ?";
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setLong(1, id);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}

		return true;
	}

}
