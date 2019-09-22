package atmjdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CustomerDaoImp implements CustomerDao {
    JdbcTemplate jdbcTemplate;

    public CustomerDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(int pin) {
        String query = "INSERT INTO customers (Pin) VALUES (?);";
        Object[] data = new Object[]
                { pin };
        jdbcTemplate.update(query, data);
    }

    public void update(int number, Customer customer) {
        String query = "UPDATE customers SET Price = ? WHERE Customer = ?";
        Object[] data = new Object[]
                { customer.getAccount().getBalance(), number };
        jdbcTemplate.update(query, data);
    }

    public void deleteByCustomerNumber(int number) {
        String query = "DELETE FROM customers WHERE Customer=?";
        Object[] data = new Object[]
                { number };
        jdbcTemplate.update(query, data);
    }

    public Customer findByCustomerNumber(int number) {
        String query = "SELECT * FROM customers WHERE Customer = " + number;
        Customer customer = jdbcTemplate.queryForObject(query, new CustomerRowMapper());
        return customer;
    }

    public HashMap<Integer, Customer> findAll() throws DataAccessException {
        String query = "SELECT * FROM customers";
        HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();

        for (Customer c : jdbcTemplate.query(query, new CustomerRowMapper())){
            customers.put(c.getCustomerNumber(), c);
        }
        return customers;
    }

    class CustomerRowMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer(
                    rs.getInt("Customer"),
                    rs.getInt("Pin"),
                    rs.getDouble("Price")
            );
            return customer;
        }
    }
}
