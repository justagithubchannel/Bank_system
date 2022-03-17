package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class RunMethod {
    public static ArrayList<Client> clients = new ArrayList<>();
    public static void run() {

        Scanner in = new Scanner(System.in);
        DBManager dbManager = new DBManager();
        System.out.println("WELCOME TO BANK"); //TODO WHERE OUR PROJECT IS STARTED
        while (true) {
            System.out.println("PRESS[1] TO SIGN IN");
            System.out.println("PRESS[2] TO SIGN UP");
            System.out.println("PRESS[3] TO EXIT");
            int choose = in.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("ENTER YOUR NAME: ");
                    String name = in.next();
                    System.out.println("ENTER YOUR SURNAME: ");
                    String surname = in.next();
                   Client client =  dbManager.getClient(name, surname);
                   if (client != null) {
                       while (true) {
                           System.out.println("PRESS[1] TO ADD BALANCE");
                           System.out.println("PRESS[2] TO SHOW BALANCE");
                           System.out.println("PRESS[3] TO ADD YOUR BALANCE TO DEPOSIT");
                           System.out.println("PRESS[4] TO SEND MONEY");
                           System.out.println("PRESS[0] TO BACK");
                           int choice = in.nextInt();
                           switch (choice) {
                               case 1:
                                   System.out.println("INSERT HOW MUCH MONEY DO YOU WANNA ADD: ");
                                   Double balance = in.nextDouble();
                                   balance = balance + client.getBalance();
                                   if (dbManager.addBalance(client, balance)) {
                                       client.setBalance(balance);
                                       System.out.println("SUCCESFULLY ADDED!");
                                   }
                                   else {
                                       System.out.println("SOME ERROR!");
                                   }
                                   break;
                               case 2:
                                   System.out.println("Your balance is " + client.getBalance());
                                   break;
                               case 3:
                                   System.out.println("ENTER HOW MUCH MONEY DO YOU WANNA INSERT INTO DEPOSIT: ");
                                   double balance2 = in.nextDouble();
                                   if (balance2 <= client.getBalance()) {
                                       System.out.println("INSERT FOR HOW MANY YEARS: ");
                                       int year = in.nextInt();
                                       System.out.println("WHAT PERCENTAGE: ");
                                       int percent = in.nextInt();
                                       for (int i = 1; i <= year ; i++) {
                                           balance2 = balance2 + balance2 * percent / 100;
                                           System.out.println(i + " year = " + balance2);
                                       }
                                       System.out.println("TOTAL SUM IS : " + balance2);

                                       client.setBalance(balance2);
                                       dbManager.addBalance(client, balance2);
                                   }
                                   else {
                                       System.out.println("FIRST OF ALL ADD BALANCE");
                                   }
                                   break;
                               case 4:
                                   System.out.println("ENTER THE CARD NUMBER TO WHOM YOU WANT TO SEND MONEY: ");
                                   String cardNumber = in.next();
                                   System.out.println("ENTER THE MONEY HOW MUCH YOU WANT TO SEND: ");
                                   double money = in.nextDouble();
                                   if (client.getBalance() >= money) {
                                        dbManager.sendMoneyByCard(cardNumber, money);

                                        if (dbManager.addBalance(client, client.getBalance() - money)) {
                                            client.setBalance(client.getBalance() - money);
                                            System.out.println("SUCCESSFULLY ADDED!");
                                        }
                                   }
                                   else {
                                       System.out.println("YOU DO NOT HAVE ENOUGH MONEY");
                                   }
                                   break;

                           }
                           if (choice == 0) {
                               break;
                           }
                       }
                   }
                   else {
                       System.out.println("Something went wrong!");
                   }
                   break;
                case 2:
                    System.out.println("ENTER YOUR NAME: ");
                    String name1 = in.next();
                    System.out.println("ENTER YOUR SURNAME: ");
                    String surname1 = in.next();

                    System.out.println("ENTER YOUR CARD NUMBER: ");
                    String cardNumber = in.next();
                    Client client1 = new Client();
                    client1.setName(name1);
                    client1.setSurname(surname1);
                    client1.setCardnumber(cardNumber);
                    if (dbManager.addClient(client1)) {
                        clients.add(client1);
                        System.out.println("SUCCESFULLY ADDED!");
                    }
                    else
                    {
                        System.out.println("some error");
                    }


                    break;
                case 3:
                    System.out.println("Goodbye");
                    break;
            }
            if (choose == 3) {
                break;
            }
        }
    }
}
