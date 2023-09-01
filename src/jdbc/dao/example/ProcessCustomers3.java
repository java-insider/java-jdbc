package jdbc.dao.example;

import jdbc.dao.core.ConnectionFactory;
import jdbc.dao.core.DBConn;

public class ProcessCustomers3 {

    public static void main(String[] args) {

        try (DBConn conn = ConnectionFactory.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO(conn);
            customerDAO.deleteById(4);
        }
    }
}
