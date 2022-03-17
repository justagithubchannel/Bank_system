package com.company;

public class Client extends User {

    private String cardnumber;
    private Double balance = 0d;


    public Client() {
    }

    public Client(Integer id, String name, String surname, String cardnumber, Double balance) {
        super(id, name, surname);
        this.cardnumber = cardnumber;
        this.balance = balance;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.cardnumber + " " + this.balance + "\n";
    }
}
