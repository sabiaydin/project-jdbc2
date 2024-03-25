package org.example;

import org.example.entity.Customers;
import org.example.process.CRUDOperations;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       // DBConnection.getTableConnection();
       // DBProcess.createTable();
      //  DBProcess.createSchema();
        Customers customer1=new Customers(7,"Sevil","Aydinli",18,"sabiaydin@gmail.com","123455");
        Customers customer2=new Customers(5,"ilaha","Mammadova",17,"ilahamamm12@gmail.com","123455");
        Customers customer3=new Customers(6,"Lala","Karimli",23,"lala_karim@gmail.com","123455");
          Customers updatedCustomer=new Customers(5,"Zarifa","Hasanli",24,"zarif.hh@gmail.com","123455");
        List<Customers> listOfCustomers=new ArrayList<>();
        listOfCustomers.add(customer1);
        listOfCustomers.add(customer2);
        listOfCustomers.add(customer3);
       // DBProcess.updateTable(updatedCustomer);
    //   DBProcess.insertTable(listOfCustomers);
   //  DBProcess.deleteTable();
        CRUDOperations.getInfoFromDB();
    }
}