package com.company;

public interface IAdmin {

    public  boolean addClient(Client client);

    public void deleteClient();

    public void getClients();

    public Client getClient(String name, String surname);

    public boolean addBalance(Client client, double balance);

    public boolean sendMoneyByCard(String card, Double money);
}
