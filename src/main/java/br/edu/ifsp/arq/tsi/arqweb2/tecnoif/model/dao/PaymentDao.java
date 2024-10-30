package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Payment;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.ServiceOrder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDao {

    private final DataSource dataSource;

    public PaymentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean save(Payment payment){
        Optional<Payment> optional = getPaymentByPaymentType(payment.getPaymentType());
        if (optional.isPresent()) {
            return false;
        }
        String sql = "INSERT INTO payment (payment_type)"
                + " VALUES (?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, payment.getPaymentType());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }

    public Optional<Payment> getPaymentByPaymentType(String paymentType) {
        String sql = "SELECT id, payment_type FROM payment WHERE payment_type=?";
        Optional<Payment> optional = Optional.empty();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, paymentType);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong(1));
                    payment.setPaymentType(rs.getString(2));
                    optional = Optional.of(payment);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
        return optional;
    }

    public Optional<List<Payment>> getAllPayments() {
        String sql = "SELECT payment_type FROM payment";
        Optional<List<Payment>> optional = Optional.empty();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            List<Payment> payments = new ArrayList<>();
            while(rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentType(rs.getString(1));
                payments.add(payment);
            }
            optional = Optional.of(payments);
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
        return optional;
    }

}
