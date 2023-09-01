package jdbc.dao.example;

import jdbc.dao.core.ConnectionFactory;
import jdbc.dao.core.DBConn;

public class ProcessCustomers4 {

    public static void main(String[] args) {

        try (DBConn conn = ConnectionFactory.getConnection()) {

            conn.beginTransaction();

            CustomerDAO customerDAO = new CustomerDAO(conn);

            Customer customer = new Customer("Maria", "a@a.com", 54);
            Integer customerId = customerDAO.insert(customer);

            PhoneDAO phoneDAO = new PhoneDAO(conn);
            Phone phone = new Phone(customerId, "Celular", "3476384");
            phoneDAO.insert(phone);

            conn.commitTransaction();
        }
    }
}
