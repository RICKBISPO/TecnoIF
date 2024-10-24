package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Address;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.DataSourceSearcher;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddressDao {

    private final DataSource dataSource;

    public AddressDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean save(Address address, User user){
        if(user.getId() == null) {
            return false;
        }
        String sql = "INSERT INTO address (street_name, house_number, address_complement, neighborhood, postal_code, city, state, id_user)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, address.getStreetName());
            ps.setString(2, address.getHouseNumber());
            ps.setString(3, address.getAddressComplement());
            ps.setString(4, address.getNeighborhood());
            ps.setString(5, address.getPostalCode());
            ps.setString(6, address.getCity());
            ps.setString(7, address.getState());
            ps.setLong(8, user.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }

}
