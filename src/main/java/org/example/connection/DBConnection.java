package org.example.connection;

import java.sql.*;

public class DBConnection {
    private static Connection tableConnection=null;
    private static Connection schemaConnection=null;
    public static Connection getTableConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            tableConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/project-jdbc2","root","12345678");
            System.out.println("Table connection to database has created successfully !");
            return tableConnection;
        } catch (ClassNotFoundException | SQLException  e) {
            throw new RuntimeException("Error connecting to the database for tables", e);
        }}
    public static Connection getSchemaConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            schemaConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","12345678");
            System.out.println("Schema connection to database has created successfully !");
            return schemaConnection;
        } catch (ClassNotFoundException | SQLException  e) {
            throw new RuntimeException("Error connecting to the database for schema", e);
        }}
    public static boolean schemaExists(Connection connection,String schemaName){
        String query="SHOW DATABASES LIKE ?";
        try(PreparedStatement ps=connection.prepareStatement(query)){
            ps.setString(1,schemaName);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean tableExists(Connection connection,String schemaName){
        String query="SHOW TABLES LIKE ?";
        try(PreparedStatement ps=connection.prepareStatement(query)){
            ps.setString(1,schemaName);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeTableConnection(){
        if (tableConnection!=null){
        try {
           tableConnection.close();
            System.out.println("Table connection closed successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error closing the table connection", e);
        }}
    }
    public static void closeSchemaConnection(){
        if(schemaConnection!=null){
            try {
                schemaConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing the schema connection", e);
            }
        }
    }
}
