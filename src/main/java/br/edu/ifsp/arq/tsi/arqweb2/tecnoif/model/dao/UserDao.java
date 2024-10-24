package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {

	private final DataSource dataSource;
	
	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Boolean save(User user){
		Optional<User> userByEmail = getUserByEmail(user.getEmail());
		Optional<User> userByCpf = getUserByCpf(user.getCpf());
		if(userByEmail.isPresent() || userByCpf.isPresent()) {
			return false;
		}
		String sql = "INSERT INTO user (name, email, telephone, cpf) VALUES (?,?,?,?)";
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getTelephone());
			ps.setString(4, user.getCpf());
			ps.executeUpdate();
			var rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}

		return true;
	}

	public Optional<User> getUserByEmail(String email) {
		String sql = "SELECT id, name, email FROM user WHERE email=?";
		Optional<User> optional = Optional.empty();
		try (Connection con = dataSource.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong(1));
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					optional = Optional.of(user);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}

		return optional;
	}

	public Optional<User> getUserByCpf(String cpf) {
		String sql = "SELECT id, name, email FROM user WHERE cpf=?";
		Optional<User> optional = Optional.empty();
		try (Connection con = dataSource.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cpf);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong(1));
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					optional = Optional.of(user);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}

		return optional;
	}

}
