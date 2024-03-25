package org.example.process;

import org.example.connection.DBConnection;
import org.example.entity.Customers;

import java.sql.*;
import java.util.List;

public class CRUDOperations {
    public static void createSchema() {
        String schemaName = "teachers";
        try (Connection connection = DBConnection.getSchemaConnection()) {
            if (DBConnection.schemaExists(connection, schemaName)) {
                System.out.println("Schema already exists");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
        try (Connection connection = DBConnection.getSchemaConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.execute();
            System.out.println("Schema created successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTable() {
        String tableName = "customers";
        try (Connection connection = DBConnection.getTableConnection()) {
            if (DBConnection.tableExists(connection, tableName)) {
                System.out.println("Table already exists");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "CREATE TABLE IF NOT EXISTS customers(customer_id INT PRIMARY KEY NOT NULL," +
                "name VARCHAR(45),surname VARCHAR(45),age INT,email VARCHAR(45)," +
                "phone_number VARCHAR(45))";
        try (Connection connection = DBConnection.getTableConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertTable(List<Customers> listOfCustomers) {
        String query = "INSERT INTO customers(customer_id,name,surname,age,email,phone_number)  VALUES(?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getTableConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            for (Customers customers : listOfCustomers) {
                ps.setInt(1, customers.getCustomer_id());
                ps.setString(2, customers.getName());
                ps.setString(3, customers.getSurname());
                ps.setInt(4, customers.getAge());
                ps.setString(5, customers.getEmail());
                ps.setString(6, customers.getPhoneNumber());
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Data inserted to students successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTable(Customers customers) {
        String query = "UPDATE customers set name= ?,surname = ? ,age = ? ,email = ? WHERE customer_id=5";
        try (Connection connection = DBConnection.getTableConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, customers.getName());
            ps.setString(2, customers.getSurname());
            ps.setInt(3, customers.getAge());
            ps.setString(4, customers.getEmail());
            ps.executeUpdate();
            System.out.println("Student table has updated successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTable() {
        String query = "DELETE FROM customers WHERE customer_id=?";
        try (Connection connection = DBConnection.getTableConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, 7);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data with customer_id 6 deleted from customers successfully !");
            } else {
                System.out.println("No data found with customer_id 6 to delete.");
            }
            ps.executeUpdate();
            System.out.println("Data deleted from customers successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getInfoFromDB(){
        String query = "SELECT * FROM customers where customer_id = ?";
        try(Connection connection=DBConnection.getTableConnection();
        PreparedStatement ps=connection.prepareStatement(query)){
        ps.setInt(1,1);
        try(ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                Integer id=rs.getInt("customer_id");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                Integer age=rs.getInt("age");
                String email=rs.getString("email");
                String phoneNumber=rs.getString("phone_number");
                System.out.println("      \n <<< About Personal >>>\n           ");
                System.out.println("CustomerID : " + id);
                System.out.println("Name : " + name);
                System.out.println("Surname : " + surname);
                System.out.println("Age : " + age);
                System.out.println("Email : " + email);
                System.out.println("PhoneNumber : " + phoneNumber);
            }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
