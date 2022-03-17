package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class DBManager implements IAdmin {
    private static ArrayList<Client> clients = new ArrayList<Client>();
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/bank", "postgres", "qwe");

            System.out.println("DB IS CONNECTED");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean addBalance(int index, Double balance) {

        return false;
    }
     @Override
    public  boolean addClient(Client client) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO client(name, surname, cardnumber, balance) VALUES(?, ? ,? ,? )");

            statement.setString(1,client.getName());
            statement.setString(2,client.getSurname());
            statement.setString(3,client.getCardnumber());
            statement.setDouble(4,client.getBalance());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    @Override
    public void deleteClient() {

    }

    @Override
    public void getClients() {



    }

    @Override
    public Client getClient(String name, String surname) {
        Client client = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client" +
                    " WHERE name = ? AND surname = ?");
            statement.setString(1, name);
            statement.setString(2, surname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("cardnumber"),
                        resultSet.getDouble("balance")
                );
            }
            statement.close();
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean addBalance(Client client, double balance) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE client SET balance = ? " +
                    " WHERE id = ?");
            statement.setDouble(1, balance);
            statement.setInt(2, client.getId());
            rows = statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    @Override
    public boolean sendMoneyByCard(String card, Double money) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client " +
                    "WHERE cardnumber = ? LIMIT 1");
            statement.setString(1, card);
            ResultSet resultSet = statement.executeQuery();
            double balance = 0;
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }

            PreparedStatement statement1 = connection.prepareStatement("UPDATE client SET balance = ? WHERE cardnumber = ?");
            statement1.setDouble(1,balance + money);
            statement1.setString(2, card);
            rows = statement1.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


}
